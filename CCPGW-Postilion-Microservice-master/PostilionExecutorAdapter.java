/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Postilion Adapter: POSTILION - Transaction Manager Adapter 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.adapter.postilion.executor.main;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 * 
 */

import com.truteq.ServiceException;
import com.truteq.ccpgw.adapter.postilion.ISOMsg2CaptureObject;
import com.truteq.ccpgw.adapter.postilion.ISOMsg2ReversalObject;
import com.truteq.ccpgw.adapter.postilion.ISOMsg2SignResponseObject;
import com.truteq.ccpgw.adapter.postilion.MessageListener;

import com.truteq.protocol.MessageException;
import java.io.FileInputStream;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.channel.PostChannel;
import org.jpos.iso.packager.GenericPackager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.truteq.ccpgw.adapter.postilion.enums.eAuth;
import com.truteq.ccpgw.adapter.postilion.executor.tasks.PostilionSendMessageTask;
import com.truteq.ccpgw.adapter.postilion.requests.Authorisation;
import com.truteq.ccpgw.adapter.postilion.requests.Capture;
import com.truteq.ccpgw.adapter.postilion.requests.PostilionRequest;
import com.truteq.ccpgw.adapter.postilion.requests.Reversal;
import com.truteq.ccpgw.adapter.postilion.requests.Sign;
import com.truteq.ccpgw.adapter.postilion.requests.SignResponse;
import com.truteq.ccpgw.adapter.postilion.requests.objects.AuthoriseObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.CaptureObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.ReversalObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.SignObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.SignResponseObject;
import com.truteq.ccpgw.adapter.postilion.utils.Sequence;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class PostilionExecutorAdapter implements MessageListener {

    private String adapterClass;

    protected Sequence mPostilionAuditSequence = null;
    protected String mPostFile;
    protected Long mPostSaveInterval;

    protected static final int DEFAULT_TIMEOUT = 600000;

    protected java.lang.String mBankAddress;
    protected int mBankPort;
    protected Long mPostResponseWait = 10L;
    protected Integer mPostResponseMax = 900;

    private boolean debug = false;
    private int delay = 10000;
    private int responsePeriod = 30;

    public PostChannel mCoreBankChan;

    public java.lang.String mISOPackLoc;
    
    private ISOPackager vISOPackager;

    private PostilionExecutorMessageListener postilionExecutorMessageListener;

    private boolean listenerResponse = false;
    
    private boolean received0800 = false;
    private boolean received0810 = false;
    private boolean received0110 = false;
    private boolean received0210 = false;
    private boolean received0230 = false;
    private boolean received0430 = false;
    
    private boolean signedon = false;
  
    private ISOMsg listenerISOMsgResponse = null;

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
    
    private final LogWrapper mLogger = new LogWrapper(PostilionExecutorAdapter.class);

    public PostilionExecutorAdapter() {
        this.mLogger.debug("Load properties file.");
        loadProperties("config/application.properties");
        this.mLogger.debug("BSP.Postilion.Executor.Adapter");
        mPostilionAuditSequence = new Sequence("config/sequenceSetting.conf");
    }

    private void startListernerThread() {
        
        postilionExecutorMessageListener = new PostilionExecutorMessageListener(this);
        this.executor.execute(postilionExecutorMessageListener);

    }

    public final void loadProperties(String filename) {

        this.mLogger.debug("Working Directory = " + System.getProperty("user.dir"));
        try (InputStream input = new FileInputStream(filename)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.mLogger.debug("ISOPack: " + prop.getProperty("isopack"));
            this.mLogger.debug("Bank Address: " + prop.getProperty("bankaddress"));
            this.mLogger.debug("Bank port: " + prop.getProperty("bankport"));
            this.mLogger.debug("Debug : " + prop.getProperty("debug"));

            this.mISOPackLoc = prop.getProperty("isopack");
            this.mBankAddress = prop.getProperty("bankaddress");
            this.mBankPort = Integer.parseInt(prop.getProperty("bankport"));
            this.debug = Boolean.parseBoolean(prop.getProperty("debug"));
            this.delay = Integer.parseInt(prop.getProperty("delay"));
            this.responsePeriod = Integer.parseInt(prop.getProperty("responseperiod"));        

        } catch (IOException ex) {
            this.mLogger.error("Error load properties file: " + ex.getMessage());
        }
    }

    public void stop() throws ServiceException {

        if (mCoreBankChan != null) {
            mLogger.debug("Disconnecting PostChannel connection ...");
            try {
                mCoreBankChan.disconnect();
            } catch (IOException vException) {
                mLogger.error(vException.getMessage(), vException);
            }
        }

        this.executor.shutdown();
        mLogger.debug("Stopped the audit sequence.");
        mPostilionAuditSequence.stop();
    }

    public void start() throws ServiceException {

        try {

            mCoreBankChan = newISOChannel(mISOPackLoc);

        } catch (IOException | ISOException vException) {
            mLogger.error(vException.getMessage(), vException);
        }

        mLogger.debug("Started the audit sequence.");
        mPostilionAuditSequence.start();

        startListernerThread();

    }

    @Override
    public void onMessageReceived(ISOMsg vResponse) {
       this.setListenerResponse(true);
       this.setListenerISOMsgResponse(vResponse);
    }
    
    @Override
    public void on0800Recieved() {
       //Will only receive an on0800Recieved when Postilion wants to sign-on or sign-off 
       this.setReceived0800(true);
       doSignOnOffRespond(this.getListenerISOMsgResponse());
    }

    @Override
    public void on0810Recieved() {
       this.setReceived0810(true); 
    }

    @Override
    public void on0110Recieved() {
       this.setReceived0110(true); 
    }

    @Override
    public void on0430Recieved() {
      this.setReceived0430(true);
    }
    
    @Override
    public void on0210Recieved() {
      this.setReceived0210(true);
    }

    @Override
    public void on0230Recieved() {
      this.setReceived0230(true);
    }    

    
    public void checkSTAN(String stan){
        
        try {
            Integer respStanInt = Integer.parseInt((String)this.getListenerISOMsgResponse().getComponent(11).getValue());
            String respStan = respStanInt.toString();            
            if (!respStan.equals(stan)){
               mLogger.debug("Request STAN ("+stan+") and response STAN "+respStan+" DONOT match.");
               this.setListenerISOMsgResponse(null); 
            }
            else
               mLogger.debug("Request STAN and response STAN match.");
        } catch (ISOException ex) {
            mLogger.error("Exception when comparing response STAN to request STAN. "+ex);
        }        
    }
    /**
     * =========================================================================
     * waitingFor0800Response
     * ========================================================================= 
     * @return 
     * =========================================================================
     */
    private ISOMsg waitingFor0800Response(String stan){
        
        boolean checkstan = true;
        Instant start = Instant.now();
        while (!this.isReceived0810()) {
            Instant end = Instant.now();
            long diff = end.getEpochSecond() - start.getEpochSecond();

            if (diff >= responsePeriod) {
                mLogger.debug("No response from Postilion after 30sec. Aborting response wait!");
                checkstan = false;
                this.setReceived0810(true);
                this.setListenerISOMsgResponse(null);
            }
        }
        this.setReceived0810(false);
        
        if (checkstan) checkSTAN(stan);
        
        return this.getListenerISOMsgResponse();         
    }
    
    /**
     * =========================================================================
     * waitingFor0100Response
     * ========================================================================= 
     * @return 
     * =========================================================================
     */
    private ISOMsg waitingFor0100Response(String stan){
        
        boolean checkstan = true;
        Instant start = Instant.now();
        
        while (!this.isReceived0110()) {
            Instant end = Instant.now();
            long diff = end.getEpochSecond() - start.getEpochSecond();
            if (diff >= responsePeriod) {
                mLogger.debug("No response from Postilion after 30sec. Aborting response wait!");
                checkstan = false;
                this.setReceived0110(true);
                this.setListenerISOMsgResponse(null);
            }            
        }
        
        this.setReceived0110(false);
        
        if (checkstan) checkSTAN(stan);
                
        return this.getListenerISOMsgResponse();          
    }
    
    /**
     * =========================================================================
     * waitingFor0420Response
     * ========================================================================= 
     * @return 
     * =========================================================================
     */
    private ISOMsg waitingFor0420Response(String stan){
        
        boolean checkstan = true;
        Instant start = Instant.now();
        
        while (!this.isReceived0430()) {
            Instant end = Instant.now();
            long diff = end.getEpochSecond() - start.getEpochSecond();
            if (diff >= responsePeriod) {
                mLogger.debug("No response from Postilion after 30sec. Aborting response wait!");
                checkstan = false;
                this.setReceived0430(true);
                this.setListenerISOMsgResponse(null);
            }            
        }
        
        this.setReceived0430(false);
        
        if (checkstan) checkSTAN(stan);        
        
        return this.getListenerISOMsgResponse(); 
    }      
    
    /**
     * =========================================================================
     * waitingFor0200Response
     * ========================================================================= 
     * @return 
     * =========================================================================
     */
    private ISOMsg waitingFor0200Response(String stan){
        
        boolean checkstan = true;
        Instant start = Instant.now();
        
        while (!this.isReceived0210()) {
            Instant end = Instant.now();
            long diff = end.getEpochSecond() - start.getEpochSecond();
            if (diff >= responsePeriod) {
                mLogger.debug("No response from Postilion after 30sec. Aborting response wait!");
                checkstan = false;
                this.setReceived0210(true);
                this.setListenerISOMsgResponse(null);
            }            
        }
        
        this.setReceived0210(false);
                
        if (checkstan) checkSTAN(stan);
                
        return this.getListenerISOMsgResponse(); 
    }    
     
    /**
     * =========================================================================
     * waitingFor0220Response
     * ========================================================================= 
     * @return 
     * =========================================================================
     */
    private ISOMsg waitingFor0220Response(String stan){
        
        boolean checkstan = true;
        Instant start = Instant.now();
        
        while (!this.isReceived0230()) {
            Instant end = Instant.now();
            long diff = end.getEpochSecond() - start.getEpochSecond();
            if (diff >= responsePeriod) {
                mLogger.debug("No response from Postilion after 30sec. Aborting response wait!");
                checkstan = false;
                this.setReceived0230(true);
                this.setListenerISOMsgResponse(null);
            }            
        }
        
        this.setReceived0230(false);
                
        if (checkstan) checkSTAN(stan);
                        
        return this.getListenerISOMsgResponse(); 
    }     
     
    /**
     * =========================================================================
     * waitingFor0221Response
     * ========================================================================= 
     * @return 
     * =========================================================================
     */   
    private ISOMsg waitingFor0221Response(String stan){
        
        boolean checkstan = true;
        Instant start = Instant.now();
        
        while (!this.isReceived0230()) {
            Instant end = Instant.now();
            long diff = end.getEpochSecond() - start.getEpochSecond();
            if (diff >= responsePeriod) {
                mLogger.debug("No response from Postilion after 30sec. Aborting response wait!");
                checkstan = false;
                this.setReceived0230(true);
                this.setListenerISOMsgResponse(null);
            }            
        }
        
        this.setReceived0230(false);
        
        if (checkstan) checkSTAN(stan);
                
        return this.getListenerISOMsgResponse(); 
    } 
     
    /**
     * =========================================================================
     * waitingFor0421Response
     * ========================================================================= 
     * @return 
     * =========================================================================
     */   
    private ISOMsg waitingFor0421Response(String stan){
        
        boolean checkstan = true;
        Instant start = Instant.now();
        
        while (!this.isReceived0430()) {
            Instant end = Instant.now();
            long diff = end.getEpochSecond() - start.getEpochSecond();
            if (diff >= responsePeriod) {
                mLogger.debug("No response from Postilion after 30sec. Aborting response wait!");
                checkstan = false;
                this.setReceived0430(true);
                this.setListenerISOMsgResponse(null);
            }            
        }
        
        this.setReceived0430(false);
        
        if (checkstan) checkSTAN(stan);
                
        return this.getListenerISOMsgResponse(); 
    }      
       
    /**
     * =========================================================================
     * waitingForResponse
     * ========================================================================= 
     * @return 
     * =========================================================================
     */    
    private ISOMsg waitingForResponse(ISOMsg messageSent){
 
        try {
            
            Integer stanInt = Integer.parseInt((String)messageSent.getComponent(11).getValue());
            String stan = stanInt.toString();             
            
            switch(messageSent.getMTI()){
                case "0800": return waitingFor0800Response(stan);
                case "0100": return waitingFor0100Response(stan);
                case "0200": return waitingFor0200Response(stan);
                case "0220": return waitingFor0220Response(stan);
                case "0221": return waitingFor0221Response(stan);
                case "0420": return waitingFor0420Response(stan);
                case "0421": return waitingFor0421Response(stan); 
            }
        } catch (ISOException ex) {
            mLogger.error("Exception when waiting for response. "+ex);
        }
        
        return null;
        
    } 
    
    public PostChannel newISOChannel(String aPackLoc) throws IOException, ISOException {

        mLogger.debug("Started creating new PostChannel ...");

        ISOPackager vISOPackager = new GenericPackager(aPackLoc);

        PostChannel vPostChan = new PostChannel(mBankAddress, mBankPort, vISOPackager);

        vPostChan.setTimeout(DEFAULT_TIMEOUT);

        vPostChan.connect();

        mLogger.debug("PostChannel created and connected.");

        return vPostChan;
    }


    public void printISOMessage(ISOMsg isoMsg) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        isoMsg.dump(ps, "");
        mLogger.debug(baos.toString());
    }
    
    private ISOMsg doRepeat(ISOMsg isoMsg) throws ISOException{

        switch (isoMsg.getMTI()){
            case "0100" : 
            case "0200" : return doReversalAdviceRepeat(isoMsg); //Send a 0420
  
            case "0220" : 
            case "0221" : return doFinancialAdviceRepeat(isoMsg); //Send a 0221

            case "0420" : 
            case "0421" : return doReversalRepeat(isoMsg);//Send a 0421
      
        }
        return null;
        
    }
    

    private ISOMsg sendRequest(PostilionRequest request, eAuth type) throws ISOException, MessageException {
        
        ISOMsg response;
        ISOMsg isoMsg;
        
        switch (type) {

            case FINANCIAL:
            case MOTO:
            case REFUND:
                Authorisation finRequest = (Authorisation) request;
                isoMsg = finRequest.getUnpacked();
                isoMsg = assignMTI("0200", isoMsg);
                PostilionSendMessageTask task = new PostilionSendMessageTask(this,isoMsg,finRequest.getAuthoriseObj().getSystemTraceAuditNumber(),delay);
                this.executor.execute(task);           
                response = waitingForResponse(isoMsg);
                break;
            case CAPTURE:
            case DEBIT  :
            case CREDIT :
            case REPEATCAPTURE:    
                Capture capRequest = (Capture) request;
                isoMsg = capRequest.getUnpacked();
                switch (type){
                    case CAPTURE:
                    case DEBIT  :
                    case CREDIT :  isoMsg = assignMTI("0220", isoMsg); break;
                    case REPEATREVERSAL:  isoMsg = assignMTI("0221", isoMsg); break;
                }                
                
                task = new PostilionSendMessageTask(this,isoMsg,capRequest.getCaptureObj().getSystemTraceAuditNumber(),delay);
                this.executor.execute(task);           
                response = waitingForResponse(isoMsg);
                break;
            case REVERSAL:
            case REPEATREVERSAL:    
                Reversal revRequest = (Reversal) request;
                isoMsg = revRequest.getUnpacked();
                switch (type){
                    case REVERSAL: isoMsg = assignMTI("0420", isoMsg); break;
                    case REPEATREVERSAL: isoMsg = assignMTI("0421", isoMsg); break;
                }
                
                task = new PostilionSendMessageTask(this,isoMsg,revRequest.getReversalObj().getSystemTraceAuditNumber(),delay);
                this.executor.execute(task);           
                response = waitingForResponse(isoMsg);                
                break;               
            default: 
                    Authorisation authRequest = (Authorisation) request;
                    isoMsg = authRequest.getUnpacked();
                    isoMsg = assignMTI("0100", isoMsg);
                    task = new PostilionSendMessageTask(this,isoMsg,authRequest.getAuthoriseObj().getSystemTraceAuditNumber(),delay);
                    this.executor.execute(task);
                    response = waitingForResponse(isoMsg);
        }         
        
        if (response != null){
           printISOMessage(response); 
        }
        else{
            try {
                return doRepeat(isoMsg);
            } catch (ISOException ex) {
                mLogger.error("Exception: Error running the doRepeat."+ex);
            }
        }

        return response;
    }
    
    private ISOMsg sendRequest(ISOMsg isoMsg, String auditNumber, boolean hasReponse) {
        
        PostilionSendMessageTask task = new PostilionSendMessageTask(this,isoMsg,auditNumber,delay);
        this.executor.execute(task);           
        ISOMsg response  = waitingForResponse(isoMsg);        

        if (hasReponse){
            printISOMessage(response); 
        }

        return response;
    }
    
//    private ISOMsg sendRequest(ISOMsg isoMsg, String auditNumber) {
//
//        printISOMessage(isoMsg);
//
//        ISOMsg response = sendPostilionMessage(isoMsg, auditNumber);
//        
//        if (response != null){
//           printISOMessage(response); 
//        }
//        else{
//            try {
//                return doRepeat(isoMsg);
//            } catch (ISOException ex) {
//                mLogger.error("Exception: Error running the doRepeat."+ex);
//            }
//        }
//
//        return response;
//    }

    private ISOMsg assignMTI(String mti, ISOMsg isoMsg) {
        try {
            isoMsg.setMTI(mti);
        } catch (ISOException ex) {
            mLogger.error("Exception assigning MTI " + ex);
        }
        return isoMsg;
    }

    
    /**
     * =========================================================================
     * doReversalAdviceRepeat
     * =========================================================================
     *
     * @param isoMsg
     * @return
     * @throws ISOException
     * =========================================================================
     */
    public ISOMsg doReversalAdviceRepeat(ISOMsg isoMsg) throws ISOException {
        
        ISOMsg2ReversalObject converter = new ISOMsg2ReversalObject(); 
        ReversalObject reversalObj = converter.getReversalObject(isoMsg);
        reversalObj.setMessageType("0420");
        reversalObj.setAuth(eAuth.REVERSAL);
        return doReversal(reversalObj);
    }
     /**
     * =========================================================================
     * doFinancialAdviceRepeat
     * =========================================================================
     *
     * @param isoMsg
     * @return
     * @throws ISOException
     * =========================================================================
     */
    public ISOMsg doFinancialAdviceRepeat(ISOMsg isoMsg) throws ISOException {
        
        ISOMsg2CaptureObject converter = new ISOMsg2CaptureObject(); 
        CaptureObject capObj = converter.getCaptureObject(isoMsg);
        capObj.setMessageType("0221");
        capObj.setAuth(eAuth.REPEATCAPTURE);
        return doCapture(capObj);
    }
    
    /**
     * =========================================================================
     * doReversalRepeat
     * =========================================================================
     *
     * @param isoMsg
     * @return
     * @throws ISOException
     * =========================================================================
     */
    public ISOMsg doReversalRepeat(ISOMsg isoMsg) throws ISOException {
        
        ISOMsg2ReversalObject converter = new ISOMsg2ReversalObject(); 
        ReversalObject reversalObj = converter.getReversalObject(isoMsg);
        reversalObj.setMessageType("0421");
        reversalObj.setAuth(eAuth.REPEATREVERSAL);
        return doReversal(reversalObj);
    }

    /**
     * =========================================================================
     * SignOnOffRespond
     * =========================================================================
     * The SignOnOff method is called from the doSignOn() and doSignOff() it
     * combines the generic logic for a SignOn/Off request.
     * -------------------------------------------------------------------------
     *
     * @param isoMsg
     * @return
     * =========================================================================
     */
    public ISOMsg doSignOnOffRespond(ISOMsg isoMsg) {
        if (mCoreBankChan == null) {
        } else {
            try {

                ISOMsg2SignResponseObject isoMsg2SignResponseObject = new ISOMsg2SignResponseObject();
                
                SignResponseObject  signRespObj = isoMsg2SignResponseObject.signResponseObject(isoMsg);
                
                signRespObj.setResponseCode("00"); //00: Approved or completed successfully
                
                SignResponse vRequest = new SignResponse(getvISOPackager(), signRespObj);

                ISOMsg newIsoMsg = vRequest.getUnpacked();
                
                newIsoMsg = assignMTI("0810", newIsoMsg);
                
                if (signRespObj.getNetworkManInfoCode().equals("001")){
                    mLogger.info("Signing on: Signed ON is set to true.");
                    this.setSignedon(true);
                }
                
                if (signRespObj.getNetworkManInfoCode().equals("002")){
                     mLogger.info("Signing off: Signed ON is set to false.");
                    this.setSignedon(false);
                }

                return sendRequest(newIsoMsg, vRequest.getSignRespObj().getSystemTraceAuditNumber(),false);

            } catch (MessageException vException) {

                mLogger.error(vException.getMessage(), vException);
            }
        }
        return null;
    }

    
    /**
     * =========================================================================
     * SignOnOff
     * =========================================================================
     * The SignOnOff method is called from the doSignOn() and doSignOff() it
     * combines the generic logic for a SignOn/Off request.
     * -------------------------------------------------------------------------
     *
     * @param auth
     * @return
     * =========================================================================
     */
    public ISOMsg doSignOnOff(eAuth auth) {
        if (mCoreBankChan == null) {
        } else {
            try {
         
                String vPostSeq = Long.toString(mPostilionAuditSequence.next());

                SignObject signObj = new SignObject(auth, vPostSeq);

                Sign vRequest = new Sign(getvISOPackager(), signObj);

                ISOMsg isoMsg = vRequest.getUnpacked();
                
                isoMsg = assignMTI("0800", isoMsg);
                
                
                switch(auth){
                    case SIGNON  : this.setSignedon(true);
                                   mLogger.info("Signing on: Signed ON is set to true.");
                                   return sendRequest(isoMsg, vRequest.getSignObj().getSystemTraceAuditNumber(),true);
                    
                    case SIGNOFF : this.setSignedon(false);
                                   mLogger.info("Signing off: Signed ON is set to false.");
                                   return sendRequest(isoMsg, vRequest.getSignObj().getSystemTraceAuditNumber(),true);
                    
                    case ECHO    : mLogger.info("Currently signed on: "+this.isSignedon());
                                   if (!this.isSignedon()){
                                       mLogger.info("Currently signed off! Cannot perform an ECHO.");
                                       return null;
                                   }
                                   else{
                                      mLogger.info("Sending ECHO request."); 
                                      return sendRequest(isoMsg, vRequest.getSignObj().getSystemTraceAuditNumber(),true);
                                   }
                }
                
                

            } catch (MessageException vException) {

                mLogger.error(vException.getMessage(), vException);
            }
        }
        return null;
    }


    public ISOMsg getAuthorisationISOMsg(AuthoriseObject authObj) throws MessageException {
        authObj.setSystemTraceAuditNumber(Long.toString(mPostilionAuditSequence.next()));

        switch (authObj.getAuth()) {

            case FINANCIAL:
                authObj.setProcessingCode("000000");
                authObj.setPosConditionCode("00");
                break;
            case MOTO:
                authObj.setProcessingCode("000000");
                authObj.setPosConditionCode("08");
                break;

            case REFUND:
                authObj.setProcessingCode("200000");
                authObj.setPosConditionCode("00");
                break;

            default:
                authObj.setProcessingCode("000000");
                authObj.setPosConditionCode("06");
        }

        Authorisation vRequest = new Authorisation(getvISOPackager(), authObj);

        return vRequest.getUnpacked();

    }

    /**
     * =========================================================================
     * doAuthorisation
     * =========================================================================
     *
     * @param authObj
     * @return
     * @throws ISOException
     * =========================================================================
     */
    public ISOMsg doAuthorisation(AuthoriseObject authObj) throws ISOException {
        if (mCoreBankChan == null) {
        } else {
            try {

                authObj.setSystemTraceAuditNumber(Long.toString(mPostilionAuditSequence.next()));

                switch (authObj.getAuth()) {

                    case FINANCIAL:
                        authObj.setProcessingCode("000000");
                        authObj.setPosConditionCode("00");
                        break;
                    case MOTO:
                        authObj.setProcessingCode("000000");
                        authObj.setPosConditionCode("08");
                        break;

                    case REFUND:
                        authObj.setProcessingCode("200000");
                        authObj.setPosConditionCode("00");
                        break;

                    default:
                        authObj.setProcessingCode("000000");
                        authObj.setPosConditionCode("06");
                }

                Authorisation vRequest = new Authorisation(getvISOPackager(), authObj);

                return sendRequest(vRequest,authObj.getAuth());

            } catch (MessageException vException) {

                mLogger.error(vException.getMessage(), vException);
            }
        }
        return null;
    }
  
    /**
     * =========================================================================
     * doCapture
     * =========================================================================
     *
     * @param captureObj
     * @return
     * @throws ISOException
     * =========================================================================
     */
    public ISOMsg doCapture(CaptureObject captureObj) throws ISOException {

        if (mCoreBankChan == null) {
        } else {
            try {
                //The STAN MUST NOT be overwritten here. Use the STAN from the 0100  
                //captureObj.setSystemTraceAuditNumber(Long.toString(mPostilionAuditSequence.next()));

                switch (captureObj.getAuth()) {
                    case CAPTURE:
                        captureObj.setProcessingCode("000000");
                        break;
                    case CREDIT:
                        captureObj.setProcessingCode("220000");
                        break;
                    case DEBIT:
                        captureObj.setProcessingCode("020000");
                        break;
                    default:
                        captureObj.setProcessingCode("000000");
                }

                Capture vRequest = new Capture(getvISOPackager(), captureObj);

                return sendRequest(vRequest,captureObj.getAuth());

            } catch (MessageException vException) {

                mLogger.error(vException.getMessage(), vException);
            }
        }
        return null;
    }

    /**
     * =========================================================================
     * doReversal
     * =========================================================================
     *
     * @param reversalObj
     * @return
     * @throws ISOException
     * =========================================================================
     */
    public ISOMsg doReversal(ReversalObject reversalObj) throws ISOException {
        if (mCoreBankChan == null) {
        } else {
            try {
                //The STAN MUST NOT be overwritten here. Use the STAN from the 0100 
                //reversalObj.setSystemTraceAuditNumber(Long.toString(mPostilionAuditSequence.next()));

                Reversal vRequest = new Reversal(getvISOPackager(), reversalObj);

                return sendRequest(vRequest,reversalObj.getAuth());

            } catch (MessageException vException) {

                mLogger.error(vException.getMessage(), vException);
            }
        }
        return null;
    }

    /**
     * @return the adapterClass
     */
    public String getAdapterClass() {
        return adapterClass;
    }

    /**
     * @return the vISOPackager
     */
    public ISOPackager getvISOPackager() {
        return vISOPackager;
    }

    /**
     * @param vISOPackager the vISOPackager to set
     */
    public void setvISOPackager(ISOPackager vISOPackager) {
        this.vISOPackager = vISOPackager;
    }

    /**
     * @return the listenerResponse
     */
    public boolean isListenerResponse() {
        return listenerResponse;
    }

    /**
     * @param listenerResponse the listenerResponse to set
     */
    public void setListenerResponse(boolean listenerResponse) {
        this.listenerResponse = listenerResponse;
    }

    /**
     * @return the listenerISOMsgResponse
     */
    public ISOMsg getListenerISOMsgResponse() {
        return listenerISOMsgResponse;
    }

    /**
     * @param listenerISOMsgResponse the listenerISOMsgResponse to set
     */
    public void setListenerISOMsgResponse(ISOMsg listenerISOMsgResponse) {
        this.listenerISOMsgResponse = listenerISOMsgResponse;
    }

    /**
     * @return the received0810
     */
    public boolean isReceived0810() {
        return received0810;
    }

    /**
     * @param received0810 the received0810 to set
     */
    public synchronized void setReceived0810(boolean received0810) {
        this.received0810 = received0810;
    }

    /**
     * @return the received0110
     */
    public boolean isReceived0110() {
        return received0110;
    }

    /**
     * @param received0110 the received0110 to set
     */
    public synchronized void setReceived0110(boolean received0110) {
        this.received0110 = received0110;
    }

    /**
     * @return the received0430
     */
    public boolean isReceived0430() {
        return received0430;
    }

    /**
     * @param received0430 the received0430 to set
     */
    public synchronized void setReceived0430(boolean received0430) {
        this.received0430 = received0430;
    }

    /**
     * @return the received0800
     */
    public boolean isReceived0800() {
        return received0800;
    }

    /**
     * @param received0800 the received0800 to set
     */
    public synchronized void setReceived0800(boolean received0800) {
        this.received0800 = received0800;
    }

    /**
     * @return the received0210
     */
    public boolean isReceived0210() {
        return received0210;
    }

    /**
     * @param received0210 the received0210 to set
     */
    public synchronized void setReceived0210(boolean received0210) {
        this.received0210 = received0210;
    }

    /**
     * @return the received0230
     */
    public boolean isReceived0230() {
        return received0230;
    }

    /**
     * @param received0230 the received0230 to set
     */
    public synchronized void setReceived0230(boolean received0230) {
        this.received0230 = received0230;
    }

    /**
     * @return the signedon
     */
    public boolean isSignedon() {
        return signedon;
    }

    /**
     * @param signedon the signedon to set
     */
    public void setSignedon(boolean signedon) {
        this.signedon = signedon;
    }


}
