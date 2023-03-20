/**
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Postilion Adapter: POSTILION - Transaction Manager Adapter 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 *
 * Note: This is the HTM version. That is it incorporates a HashTable   
 *       Manager for the tasks.
 * 
 *
*/
package com.truteq.ccpgw.adapter.postilion.executor.htm;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 * 
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.truteq.ServiceException;
import com.truteq.ccpgw.adapter.postilion.ISOMsg2CaptureObject;
import com.truteq.ccpgw.adapter.postilion.ISOMsg2ReversalObject;
import com.truteq.ccpgw.adapter.postilion.ISOMsg2SignResponseObject;
import com.truteq.ccpgw.adapter.postilion.MessageListenerHTM;

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
import com.truteq.ccpgw.adapter.postilion.executor.htm.tasks.PostilionSendMessageTaskHTM;
import com.truteq.ccpgw.adapter.postilion.requests.Authorisation;
import com.truteq.ccpgw.adapter.postilion.requests.Capture;
import com.truteq.ccpgw.adapter.postilion.requests.PostilionRequest;
import com.truteq.ccpgw.adapter.postilion.requests.ResponseMessage;
import com.truteq.ccpgw.adapter.postilion.requests.Reversal;
import com.truteq.ccpgw.adapter.postilion.requests.Sign;
import com.truteq.ccpgw.adapter.postilion.requests.SignResponse;
import com.truteq.ccpgw.adapter.postilion.requests.objects.AuthoriseObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.CaptureObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.Field127Object;
import com.truteq.ccpgw.adapter.postilion.requests.objects.RepeatUpdateObj;
import com.truteq.ccpgw.adapter.postilion.requests.objects.ReversalObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.SignObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.SignResponseObject;
import com.truteq.ccpgw.adapter.postilion.utils.Sequence;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.RoutingObj;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import com.truteq.ccpgw.transaction.manager.model.OriginalDataElement;
import com.truteq.ccpgw.transaction.manager.model.RepeatElement;
import com.truteq.ccpgw.transaction.manager.model.RepeatReadRequest;
import com.truteq.general.util.AESEncryptionDecryption;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class PostilionExecutorAdapterHTM implements MessageListenerHTM {

    private String adapterClass;

    protected Sequence mPostilionAuditSequence = null;
    protected String mPostFile;
    protected Long mPostSaveInterval;

    protected static final int DEFAULT_TIMEOUT = 172800000; //48hours //1200000; //600000;

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

    private PostilionExecutorHTMMessageListener postilionExecutorHTMMessageListener;

    private boolean listenerResponse = false;
    
    private boolean received0800 = false;
    
    private boolean signedon = false;
    
    private boolean signonflag = false;
    
    private boolean postilionSignOff = false;
    
    private boolean commServSignOff = false;
  
    private ISOMsg listenerISOMsgResponse = null;
    
    public static Map<String,ResponseMessage> responseMessageHashTable = new ConcurrentHashMap<>();

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
    
    private String repeatWrite;
    //transaction.manager.db.read.repeatdata
    private String repeatRead;
    //transaction.manager.db.read.repeatdata
    private String repeatUpdate;
    private String authorisationRead;
        
    private String keystore;
    private String keystorepassword;
    private String encryptedkeystorepassword;
    private String secret;
    
    private final LogWrapper mLogger = new LogWrapper(PostilionExecutorAdapterHTM.class);

    public PostilionExecutorAdapterHTM() {
        this.mLogger.debug("Load properties file.", new Throwable().getStackTrace()[0]);
        loadProperties("config/application.properties");
        this.mLogger.debug("BSP.Postilion.Executor.Adapter", new Throwable().getStackTrace()[0]);
        mPostilionAuditSequence = new Sequence("config/sequenceSetting.conf");
        
        
        try {
            AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
            keystorepassword = decryptor.decrypt(encryptedkeystorepassword, secret);         
        } catch (Exception ex) {
            mLogger.error("Exception with decryption credentials." + ex, new Throwable().getStackTrace()[0]);
        }        
    }

    private void startListernerThread() {
        
        if (postilionExecutorHTMMessageListener != null)  //If the task exists it was added to the executor so remove it.
             this.executor.remove(postilionExecutorHTMMessageListener);
        
        postilionExecutorHTMMessageListener = new PostilionExecutorHTMMessageListener(this);
        this.executor.execute(postilionExecutorHTMMessageListener);

    }

    public final void loadProperties(String filename) {

        this.mLogger.debug("Working Directory = " + System.getProperty("user.dir"), new Throwable().getStackTrace()[0]);
        try (InputStream input = new FileInputStream(filename)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.mLogger.debug("ISOPack: " + prop.getProperty("isopack"), new Throwable().getStackTrace()[0]);
            this.mLogger.debug("Bank Address: " + prop.getProperty("bankaddress"), new Throwable().getStackTrace()[0]);
            this.mLogger.debug("Bank port: " + prop.getProperty("bankport"), new Throwable().getStackTrace()[0]);
            this.mLogger.debug("Debug : " + prop.getProperty("debug"), new Throwable().getStackTrace()[0]);

            this.mISOPackLoc = prop.getProperty("isopack");
            this.mBankAddress = prop.getProperty("bankaddress");
            this.mBankPort = Integer.parseInt(prop.getProperty("bankport"));
            this.debug = Boolean.parseBoolean(prop.getProperty("debug"));
            this.delay = Integer.parseInt(prop.getProperty("delay"));
            this.responsePeriod = Integer.parseInt(prop.getProperty("responseperiod"));
            
            this.repeatRead = prop.getProperty("transaction.manager.db.read.repeatdata");
            this.repeatUpdate = prop.getProperty("transaction.manager.db.update.repeatdata");
            this.repeatWrite = prop.getProperty("transaction.manager.db.write.repeatdata");
            this.authorisationRead = prop.getProperty("transaction.manager.db.read.financial");
            
            this.keystore = prop.getProperty("transaction.manager.certificate");
            this.encryptedkeystorepassword = prop.getProperty("transaction.manager.keystore.password");
            this.secret = prop.getProperty("communicator.secret");

        } catch (IOException ex) {
            this.mLogger.error("Error load properties file: " + ex.getMessage(), new Throwable().getStackTrace()[0]);
        }
    }

    public void stop() throws ServiceException {

        if (mCoreBankChan != null) {
            mLogger.debug("Disconnecting PostChannel connection ...", new Throwable().getStackTrace()[0]);
            try {
                mCoreBankChan.disconnect();
            } catch (IOException vException) {
                mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
            }
        }

        this.executor.shutdown();
        mLogger.debug("Stopped the audit sequence.", new Throwable().getStackTrace()[0]);
        mPostilionAuditSequence.stop();
    }

    public void start() throws ServiceException {

        try {

            mCoreBankChan = newISOChannel(mISOPackLoc);

        } catch (IOException | ISOException vException) {
            mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
        }

        mLogger.debug("Started the audit sequence.", new Throwable().getStackTrace()[0]);
        mPostilionAuditSequence.start();

        startListernerThread();
        
        checkQueuedMessages();

    }

    @Override
    public void onMessageReceived(ISOMsg vResponse) {
       //this.setListenerResponse(true);
       this.setListenerISOMsgResponse(vResponse);
       
       Integer respStanInt=0;
       try {
            respStanInt = Integer.parseInt((String)vResponse.getComponent(11).getValue());
       } catch (ISOException ex) {
            mLogger.error("Exception when checking response STAN. "+ex, new Throwable().getStackTrace()[0]);
       }
       String respStan = respStanInt.toString();
       
       mLogger.info("Response STAN: "+respStan, new Throwable().getStackTrace()[0]);
       
       ResponseMessage msg = responseMessageHashTable.get(respStan); 
       if (msg != null){
         
         msg.setIsoMessage(vResponse);
         
         if (msg.getIsoMessage() != null){  // When a repeat is happening the msg.getIsoMessage() will be null. 
            printISOMessage(msg.getIsoMessage());
         }
         
         msg.setWaiting(false); // This must be placed after the setting of the IsoMessage.
                                // if NOT then a major BUG arises in threading. 
       }
       
    }
    
    @Override
    public void on0800Recieved() {
       //Will only receive an on0800Recieved when Postilion wants to sign-on or sign-off 
       //this.setReceived0800(true);
       doSignOnOffRespond(this.getListenerISOMsgResponse());
    }
    
    
    /**
     * =========================================================================
     *  DeactivateRepeatMessage
     * =========================================================================
     * This method deactivates the persisted repeat message
     * @param msg
     * =========================================================================
     */    
    private void DeactivateRepeatMessage(ResponseMessage msg) throws ISOException{
        mLogger.info("Check to deactivate repeat messages.");
        if (msg != null){
            switch(msg.getMti()){
                case "0221"  : 
                case "PP0230":
                            mLogger.info("Deactivate found 0221 in queue.");
                            if (msg.getIsoMessage().getMTI().equals("0230")){ 
                                mLogger.info("Deactivating 0221 message with STAN: "+msg.getStan());
                                updatePersistedRepeat(msg.getMti(), msg.getStan());
                            }    
                            break;

                case "0421":
                case "PP0430":
                            mLogger.info("Deactivate found 0421 in queue.");
                            if (msg.getIsoMessage().getMTI().equals("0430")){
                               mLogger.info("Deactivating 0421 message with STAN: "+msg.getStan());                                 
                               updatePersistedRepeat(msg.getMti(), msg.getStan()); 
                            }
                            break;
            }
        }        
    }    
    
    /**
     * ==============================================================
2021-10-05 16:56:37 INFO  PostilionExecutorHTMMessageListener:run:47: Waiting for 
2021-10-05 16:56:37 INFO  PostilionExecutorHTMMessageListener:run:47: Waiting for 
     * =========================================================================
     * This method checks if there are any persisted active repeat message
     * =========================================================================
     */     
    private void checkQueuedMessages(){
       List list  = readActiveRepeats();
        
       for (Object obj : list){
           RepeatElement  element = (RepeatElement) obj;
           try {
            switch (element.getMessageType()){
                case "0221":  
                              CaptureObject captureObj = element.getCaptureObject(eAuth.REPEATCAPTURE);
                              Capture capRequest = new Capture(this.vISOPackager,captureObj); 
                              sendRequest(capRequest,eAuth.REPEATCAPTURE);

                case "0421":  
                              ReversalObject reversalObj = element.getReversalObject(eAuth.REPEATREVERSAL);
                              Reversal revRequest = new Reversal(this.vISOPackager,reversalObj);
                              sendRequest(revRequest,eAuth.REPEATREVERSAL);  
            }
           } catch (ISOException | MessageException ex) {
               mLogger.error("Exception processing repeat message: "+ex, new Throwable().getStackTrace()[0]);
           }
        }        
                   
    }

    
    public PostChannel newISOChannel(String aPackLoc) throws IOException, ISOException {

        mLogger.debug("Started creating new PostChannel ...", new Throwable().getStackTrace()[0]);

        ISOPackager vISOPackager = new GenericPackager(aPackLoc);

        PostChannel vPostChan = new PostChannel(mBankAddress, mBankPort, vISOPackager);

        vPostChan.setTimeout(DEFAULT_TIMEOUT);

        vPostChan.connect();

        mLogger.debug("PostChannel created and connected.", new Throwable().getStackTrace()[0]);

        return vPostChan;
    }


    public void printISOMessage(ISOMsg isoMsg) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        isoMsg.dump(ps, "");
         mLogger.debug(baos.toString(), new Throwable().getStackTrace()[0]);
    }
    

    public ISOMsg doRepeat(ISOMsg isoMsg) throws ISOException{
        mLogger.debug("Going to perform a repeat.", new Throwable().getStackTrace()[0]);
        mLogger.debug("Message MTI: "+isoMsg.getMTI(), new Throwable().getStackTrace()[0]);
        
        switch (isoMsg.getMTI()){
            case "0100" : return doReversalAdviceRepeat(isoMsg); //Send a 0420
            
            case "0200" : return doReversalLostReply(isoMsg); //Send a 0420 on lost reply
  
            case "0220" : 
            case "0221" : 
            case "PP0230":    return doFinancialAdviceRepeat(isoMsg); //Send a 0221

            case "0420" : 
            case "0421" :
            case "PP0430":    return doReversalRepeat(isoMsg);//Send a 0421
            
            case "0800" : mLogger.warn("Currently Signed OFF. Atempting to sign on.", new Throwable().getStackTrace()[0]);
                          return doSignOnOff(eAuth.SIGNON);
      
        }
        return null;
        
    }
    
    /**
     * =========================================================================
     *  waitForResponseOn
     * =========================================================================
     * This method ensures a wait for the response.
     * @param stan
     * @return 
     * =========================================================================
     */
    private ISOMsg waitForResponseOn(String stan){
        
        ResponseMessage msg = responseMessageHashTable.get(stan);
        
        while (msg.isWaiting()){
            msg = responseMessageHashTable.get(stan);
        }
        
        try {
            DeactivateRepeatMessage(msg);
        } catch (ISOException ex) {
            mLogger.error("Exception during deactivating repeat message: "+ex, new Throwable().getStackTrace()[0]);
        }
        
        return msg.getIsoMessage();
    }
    /**
     * =========================================================================
     *  ExecuteTaskandWaitforResponse
     * =========================================================================
     * This method ensures a wait for the response.
     * @param stan
     * @return 
     * =========================================================================
     */    
    private ISOMsg ExecuteTaskandWaitforResponse(ISOMsg isoMessage, 
                                                 String stan,
                                                 PostilionRequest request){
        
        Integer reqStanInt = Integer.parseInt((String)stan);
        String reqStan = reqStanInt.toString();
        
        PostilionSendMessageTaskHTM task = new PostilionSendMessageTaskHTM(this,
                                                                           isoMessage,
                                                                           reqStan,
                                                                           delay,
                                                                           responsePeriod,
                                                                           true,
                                                                           false,
                                                                           request);
        
        
        try {
            responseMessageHashTable.put(reqStan,new ResponseMessage(reqStan,isoMessage.getMTI()));
        } catch (ISOException ex) {
            mLogger.error("Exception processing response message: "+ex, new Throwable().getStackTrace()[0]);
        }
        
        this.executor.execute(task); 

        return  waitForResponseOn(reqStan);    
    }
    
    private void ExecuteTaskandNoWait(ISOMsg isoMessage, String stan){
        
        Integer reqStanInt = Integer.parseInt((String)stan);
        String reqStan = reqStanInt.toString();
        
        PostilionSendMessageTaskHTM task = new PostilionSendMessageTaskHTM(this,
                                                                           isoMessage,
                                                                           reqStan,
                                                                           delay,
                                                                           responsePeriod,
                                                                           false,
                                                                           false,
                                                                           null);
        
        this.executor.execute(task); 
    }    
    
    private ISOMsg sendRequest(PostilionRequest request, eAuth type) throws ISOException, MessageException {
        
        ISOMsg isoMsg;
        
        switch (type) {

            case FINANCIAL:
            case MOTO:
            case REFUND:
                Authorisation finRequest = (Authorisation) request;
                isoMsg = finRequest.getUnpacked();
                isoMsg = assignMTI("0200", isoMsg);
                return ExecuteTaskandWaitforResponse(isoMsg,
                                                     finRequest.getAuthoriseObj().getSystemTraceAuditNumber(),null);

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
                    case REPEATCAPTURE:  isoMsg = assignMTI("0221", isoMsg); break;
                }                
                
                return ExecuteTaskandWaitforResponse(isoMsg,
                                                     capRequest.getCaptureObj().getSystemTraceAuditNumber(),capRequest);

            case REVERSAL:
            case REPEATREVERSAL:    
                Reversal revRequest = (Reversal) request;
                isoMsg = revRequest.getUnpacked();
                switch (type){
                    case REVERSAL: isoMsg = assignMTI("0420", isoMsg); break;
                    case REPEATREVERSAL: isoMsg = assignMTI("0421", isoMsg); break;
                }
                
                return ExecuteTaskandWaitforResponse(isoMsg,
                                                     revRequest.getReversalObj().getSystemTraceAuditNumber(),revRequest);
             
            default: 
                    Authorisation authRequest = (Authorisation) request;
                    isoMsg = authRequest.getUnpacked();
                    isoMsg = assignMTI("0100", isoMsg);
                    return ExecuteTaskandWaitforResponse(isoMsg,
                                                         authRequest.getAuthoriseObj().getSystemTraceAuditNumber(),authRequest);

        }         

    }
    
    private ISOMsg sendRequest(ISOMsg isoMsg, String auditNumber) {
        
        return ExecuteTaskandWaitforResponse(isoMsg,auditNumber,null);
    }

    private void sendResponse(ISOMsg isoMsg, String auditNumber) {
        
        ExecuteTaskandNoWait(isoMsg, auditNumber);
    }    
    
    

    private ISOMsg assignMTI(String mti, ISOMsg isoMsg) {
        try {
            isoMsg.setMTI(mti);
        } catch (ISOException ex) {
            mLogger.error("Exception assigning MTI " + ex, new Throwable().getStackTrace()[0]);
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
        
        mLogger.info("In doReversalAdviceRepeat with isoMsg = "+ isoMsg.getMTI());
        
        ISOMsg2ReversalObject converter = new ISOMsg2ReversalObject(); 
        ReversalObject reversalObj = converter.getReversalObject(isoMsg);
        reversalObj.setMessageType("0420");
        
        DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date();
        
        OriginalDataElement oed = new  OriginalDataElement("",isoMsg.getMTI(),  //Message Type
                                dateFormat.format(date),  //String transmissionDateTime,
                                reversalObj.getSystemTraceAuditNumber(), // systemTraceAuditNumber,
                                "60130200000",  //acquiringInstitutionCode,
                                "00000000000");//forwardingInstitudeId){
        reversalObj.setOriginalDataElements(oed.getDE90());
        reversalObj.setMessageReasonCode("4021");//4021->Timeout waiting for response
        
        reversalObj.setAuth(eAuth.REVERSAL);
        return doReversal(reversalObj);
    }
    
    /**
     * =========================================================================
     * doReversalLostReply
     * =========================================================================
     *
     * @param isoMsg
     * @return
     * @throws ISOException
     * =========================================================================
     * Reversals for lost request/response failed with response code 25(Unable to locate record), 
     * which indicates the reversal is unable to be matched up to its original transaction….
 
     * Standard requirements for reversal formation without having received response to the original transaction message:

        1. DE11 to be present in 0420 and matches the one captured in the original.
        2. DE56 to be present with value of 4021 (meaning Timeout waiting for response) – This is optional but required for best practice or clean ISO
        3. DE90 to be present sent with details refereeing to original transactions – This is Mandatory to facilitate the matching
        4. DE123 is not required in a reversal message.
        5. DE04 zeroed out

        Note: Diagram attached show 0100 as the original transaction - this diagram is also relevant for transactions with 0200 as the original transaction. PPL should never stand-in for a request (purchase or auth) for any bank... only advice messages (0420/0220) can be catered for in stand-in if required by customer.
     */
    public ISOMsg doReversalLostReply(ISOMsg isoMsg) throws ISOException{
        
        mLogger.info("In doReversalLostReply with isoMsg = "+ isoMsg.getMTI());
        
        ISOMsg2ReversalObject converter = new ISOMsg2ReversalObject(); 
        ReversalObject reversalObj = converter.getReversalObject(isoMsg);
        reversalObj.setMessageType("0420");
        
        DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date();
        
        OriginalDataElement oed = new  OriginalDataElement("",isoMsg.getMTI(),  //Message Type
                                reversalObj.getTransmissionDateTime(),//dateFormat.format(date),  //String transmissionDateTime,
                                reversalObj.getSystemTraceAuditNumber(), // systemTraceAuditNumber,
                                "60130200000",  //acquiringInstitutionCode,
                                "00000000000");//forwardingInstitudeId){
        reversalObj.setTransactionAmount("0");
        reversalObj.setOriginalDataElements(oed.getDE90());
        reversalObj.setMessageReasonCode("4021");//4021->Timeout waiting for response        
        
//        if(isoMsg.getMTI().equals("0200")){
//            mLogger.info("isoMsg = "+ isoMsg.getMTI()+ " was detected in a ReversalAdviceRepeat.");
//            
//            com.truteq.ccpgw.transaction.manager.model.Authorisation auth = readAuthorisation(reversalObj.getSystemTraceAuditNumber());
//            
//            mLogger.info("doReversalAdviceRepeat::Routing: " + auth.getRouting(), new Throwable().getStackTrace()[0]);
//
//            RoutingObj routingObj = extractSubfieldsDE127_03(auth.getRouting());
//
//            mLogger.info("doReversalAdviceRepeat::Routing Object: " + routingObj.toJSON(), new Throwable().getStackTrace()[0]);
//            
//            Field127Object field127 = new Field127Object(auth.getRouting(), // Routing                  //DE127.03 
//                                                         auth.getRetrievalRef(), //orginal key               //DE127.11 
//                                                         routingObj.getSinkNode().trim() //original data      //DE127.26  
//                                                        ); 
//            reversalObj.setField127(field127);
//        }

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
        
        PersistRepeat(capObj);
        
        return doCapture(capObj,false);
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
        
        PersistRepeat(reversalObj);
        
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
     * =========================================================================
     */
    public void doSignOnOffRespond(ISOMsg isoMsg) {
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
                    mLogger.info("Signing on: Signed ON is set to true.", new Throwable().getStackTrace()[0]);
                    this.setSignedon(true);
                    this.setPostilionSignOff(false);
                }
                
                if (signRespObj.getNetworkManInfoCode().equals("002")){
                     mLogger.info("Signing off: Signed ON is set to false.", new Throwable().getStackTrace()[0]);
                    this.setSignedon(false);
                    this.setPostilionSignOff(true);
                }
                
                if (signRespObj.getNetworkManInfoCode().equals("301")){
                     mLogger.info("Echo: Postilion sent an Echo.", new Throwable().getStackTrace()[0]);
                }                

                sendResponse(newIsoMsg, vRequest.getSignRespObj().getSystemTraceAuditNumber());

            } catch (MessageException vException) {

                mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
            }
        }
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
                    case SIGNON  : 
                                   this.setSignonflag(true);
                                   ISOMsg isomsg = sendRequest(isoMsg, vRequest.getSignObj().getSystemTraceAuditNumber());
                                   
                                    try {
                                        if (isomsg.getMTI().trim().equals("0810")){
                                            this.setSignedon(true);
                                            this.setCommServSignOff(false);
                                            mLogger.info("Signing on: Signed ON is set to true.", new Throwable().getStackTrace()[0]);
                                        }    
                                    } catch (ISOException ex) {
                                        mLogger.error("Exception reading MTI from 0810 response message: "+ex, new Throwable().getStackTrace()[0]);
                                    }
                                    this.setSignonflag(false);
                                    return isomsg;

                    case SIGNOFF : 
                                   
                                   isomsg = sendRequest(isoMsg, vRequest.getSignObj().getSystemTraceAuditNumber());
                                    try {
                                        if (isomsg.getMTI().trim().equals("0810")){
                                            this.setSignedon(false);
                                            this.setCommServSignOff(true);
                                            mLogger.info("Signing off: Signed ON is set to false.", new Throwable().getStackTrace()[0]);
                                        }     
                                    } catch (ISOException ex) {
                                        mLogger.error("Exception reading MTI from 0810 response message: "+ex, new Throwable().getStackTrace()[0]);
                                    }
                                    return isomsg;
                    
                    case ECHO    : mLogger.info("Currently signed on: "+this.isSignedon(), new Throwable().getStackTrace()[0]);
                                   if (!this.isSignedon()){
                                       mLogger.info("Currently signed off! Cannot perform an ECHO.", new Throwable().getStackTrace()[0]);
                                       return null;
                                   }
                                   else{
                                      mLogger.info("Sending ECHO request.", new Throwable().getStackTrace()[0]); 
                                      return sendRequest(isoMsg, vRequest.getSignObj().getSystemTraceAuditNumber());
                                   }
                }
                
                

            } catch (MessageException vException) {

                mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
            }
        }
        return null;
    }


//    public ISOMsg getAuthorisationISOMsg(AuthoriseObject authObj) throws MessageException {
//        authObj.setSystemTraceAuditNumber(Long.toString(mPostilionAuditSequence.next()));
//
//        switch (authObj.getAuth()) {
//
//            case FINANCIAL:
//                authObj.setProcessingCode("000000");
//                authObj.setPosConditionCode("00");
//                break;
//            case MOTO:
//                authObj.setProcessingCode("000000");
//                authObj.setPosConditionCode("08");
//                break;
//
//            case REFUND:
//                authObj.setProcessingCode("200000");
//                authObj.setPosConditionCode("00");
//                break;
//
//            default:
//                authObj.setProcessingCode("000000");
//                authObj.setPosConditionCode("06");
//        }
//
//        Authorisation vRequest = new Authorisation(getvISOPackager(), authObj);
//
//        return vRequest.getUnpacked();
//
//    }

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
                        //authObj.setPosConditionCode("00");
                        break;
                    case MOTO:
                        authObj.setProcessingCode("000000");
                        authObj.setPosConditionCode("08");
                        break;

                    case REFUND:
                        authObj.setProcessingCode("200000");
                        //authObj.setPosConditionCode("00");
                        break;

                    default:
                        authObj.setProcessingCode("000000");
                        //authObj.setPosConditionCode("06");
                }

                Authorisation vRequest = new Authorisation(getvISOPackager(), authObj);

                return sendRequest(vRequest,authObj.getAuth());

            } catch (MessageException vException) {

                mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
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
     * @param isNotRepeat
     * @return
     * @throws ISOException
     * =========================================================================
     */
    public ISOMsg doCapture(CaptureObject captureObj, boolean isNotRepeat) throws ISOException {

        //if (mCoreBankChan == null) {
        //} else {
            mLogger.info("Processing doCapture: "+captureObj.getMessageType(), new Throwable().getStackTrace()[0]);
            try {
                //The STAN MUST NOT be overwritten here. Use the STAN from the 0100 
                if (isNotRepeat)
                   captureObj.setSystemTraceAuditNumber(Long.toString(mPostilionAuditSequence.next())); //Creating a new STAN was requested by Serge form BSP.

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

                mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
            }
        //}
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
        //if (mCoreBankChan == null) {
        //} else {
        
            mLogger.info("Processing doReversal: "+reversalObj.getMessageType(), new Throwable().getStackTrace()[0]);
            try {
                //The STAN MUST NOT be overwritten here. Use the STAN from the 0100 
                //reversalObj.setSystemTraceAuditNumber(Long.toString(mPostilionAuditSequence.next()));

                Reversal vRequest = new Reversal(getvISOPackager(), reversalObj);

                return sendRequest(vRequest,reversalObj.getAuth());

            } catch (MessageException vException) {

                mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
            }
        //}
        return null;
    }
    
    /**
     * =========================================================================
     * doCleanUp()
     * =========================================================================
     */
    public void doCleanUp(){
        mLogger.info("Processing a doCleanUp", new Throwable().getStackTrace()[0]);
        
        mLogger.info("HashMap size before clean: "+responseMessageHashTable.size(), new Throwable().getStackTrace()[0]);
        List<String> toRemove = new ArrayList<>();
        Instant end = Instant.now();
            
        Iterator it = responseMessageHashTable.values().iterator();
        while (it.hasNext()){
            ResponseMessage rMessage = (ResponseMessage)it.next();
            long diff = end.getEpochSecond() - rMessage.getStartTime().getEpochSecond();
            if (diff >= 600){
                toRemove.add(rMessage.getStan());
            }
        } 
        
        for(String stan: toRemove){
           responseMessageHashTable.remove(stan); 
        }
        
        mLogger.info("HashMap size after clean: "+responseMessageHashTable.size(), new Throwable().getStackTrace()[0]);
  
    }
    
    /**
     * =========================================================================
     * doClientConnect()
     * =========================================================================
     */
    public void doClientConnect(){
       if (this.mCoreBankChan == null){
            try {
                mLogger.warn("Reconnecting to Postilion.", new Throwable().getStackTrace()[0]);
                this.mCoreBankChan = this.newISOChannel(this.mISOPackLoc);
                startListernerThread();
            } catch (IOException | ISOException ex) {
                mLogger.error("Exception while trying to connect."+ex, new Throwable().getStackTrace()[0]);
            }           
       }
       else{
        if (!this.mCoreBankChan.isConnected()) {
              checkConnectionState();
        }
       }
    }
    
    /**
     * =========================================================================
     * doClientDisconnect()
     * =========================================================================
     */
     public void doClientDisconnect(){
       if (this.mCoreBankChan.isConnected()) { 
           try {
               this.setSignedon(false);
               this.setCommServSignOff(true);
               this.setPostilionSignOff(true);
               this.mCoreBankChan.disconnect();
               this.mCoreBankChan = null;
           } catch (IOException ex) {
               mLogger.info("Exception on client disconnection.", new Throwable().getStackTrace()[0]);
           }
       } 
     }
     
    /**
     * =========================================================================
     * doClientListenerRefresh()
     * =========================================================================
     */    
     public void doClientListenerRefresh(){
         startListernerThread();
     }
     
     
     
    public boolean checkConnectionState(){
        boolean abnormalDisconnect = false;
        if (!this.mCoreBankChan.isConnected()) {
            
            if(isSignedon()) {
               mLogger.warn("There was an abnormal disconnection. Setting signing off", new Throwable().getStackTrace()[0]);
               this.setSignedon(false);
               this.setCommServSignOff(true);
               this.setPostilionSignOff(true);
               abnormalDisconnect = true;
            }
            
            mLogger.warn("Not connected to Postilion. Delay for "+delay+" milliseconds and then retry.", new Throwable().getStackTrace()[0]);
            while (!this.mCoreBankChan.isConnected()){
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    mLogger.error("Exception will putting PostilionMessageListener to sleep."+ex, new Throwable().getStackTrace()[0]);
                }    
                try {
                    mLogger.warn("Reconnecting to Postilion.", new Throwable().getStackTrace()[0]);
                    this.mCoreBankChan = this.newISOChannel(this.mISOPackLoc);
                } catch (IOException | ISOException ex) {
                    mLogger.error("Exception while trying to connect."+ex, new Throwable().getStackTrace()[0]);
                }
            }
            if (abnormalDisconnect) {
                mLogger.info("There was an abnormal disconnection. Resetting the Postilion Message Listener", new Throwable().getStackTrace()[0]);
                startListernerThread();
            }
        }

        return this.mCoreBankChan.isConnected();
  
    } 
    
    /**
     * =========================================================================
     * doCleanUp()
     * =========================================================================
     */
    public void doCheckConnection(){
        mLogger.info("Processing a doCheckConnection", new Throwable().getStackTrace()[0]);
        
        boolean connected = checkConnectionState();
                 
        if (connected){
            if (isSignedon()){
                mLogger.warn("Currently connected and Signed ON.", new Throwable().getStackTrace()[0]);
            }
            else{
                if (this.isPostilionSignOff()&&this.isCommServSignOff()&&(!this.isSignedon())){
                 mLogger.warn("There was an abnormal termination detected! Currently Signed OFF. Atempting to sign on.", new Throwable().getStackTrace()[0]);
                 doSignOnOff(eAuth.SIGNON);
                }
                else if (this.isPostilionSignOff()){
                  mLogger.warn("Postilion sign-off. Wait for a Postilion sign-on", new Throwable().getStackTrace()[0]);  
                }
                else if (this.isCommServSignOff()){
                  mLogger.warn("CommServ sign-off. Wait for a CommServ sign-on", new Throwable().getStackTrace()[0]);  
                }
                else{
                  mLogger.warn("Currently Signed OFF. Atempting to sign on.", new Throwable().getStackTrace()[0]);
                  doSignOnOff(eAuth.SIGNON);
                }
            }
        }

    }
    
     
    public void updatePersistedRepeat(String mti, String stan){
        
        while (stan.length() < 6){
           stan = "0"+stan; 
        } 
        
       mLogger.info("Updating repeat in database for "+mti+" with STAN :"+stan, new Throwable().getStackTrace()[0]);
       
       RepeatUpdateObj repeatUpdateObj = new RepeatUpdateObj(mti,stan);
       SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.keystorepassword);
       sslComms.sendHttpPost(this.repeatUpdate,repeatUpdateObj.toJSON().getBytes());      
    }
    
    public void PersistRepeat(AuthoriseObject authObj) {
        
        mLogger.info("Persisting "+authObj.getMessageType()+" repeat (STAN = "+authObj.getSystemTraceAuditNumber()+") to database", new Throwable().getStackTrace()[0]);
        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.keystorepassword);

        if (authObj instanceof CaptureObject){
            CaptureObject capObj = (CaptureObject)authObj;
            RepeatElement repeatElement = new RepeatElement(capObj);
            sslComms.sendHttpPost(this.repeatWrite,repeatElement.toJSON().getBytes());
        }
        if (authObj instanceof ReversalObject){
            ReversalObject reversalObj = (ReversalObject)authObj;
            RepeatElement repeatElement = new RepeatElement(reversalObj);
            sslComms.sendHttpPost(this.repeatWrite,repeatElement.toJSON().getBytes());
        }
    }
    
    public List readActiveRepeats() {

        mLogger.info("Read repeats from database", new Throwable().getStackTrace()[0]);
        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.keystorepassword);
        RepeatReadRequest rrr = new RepeatReadRequest(1);
        Result result = sslComms.sendHttpPost(this.repeatRead,rrr.toJSON().getBytes());  
        mLogger.info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        
        Type listType = new TypeToken<ArrayList<RepeatElement>>(){}.getType();
        List<RepeatElement> list = new Gson().fromJson(result.getData(), listType);
       
        return list;
    }
    
    /**
     * =========================================================================
     * readAuthorisation()
     * =========================================================================
     * @param transactionId
     * @return 
     * 
     * This method was add by Grant O'Reilly 2022-05-06
     * It was add due to the Reversal being initiated in this class for a 
     * Financial reversal. 
     * ========================================================================= 
     */
    private com.truteq.ccpgw.transaction.manager.model.Authorisation readAuthorisation(String systemTraceAuditNumber) {
        mLogger.info("Read authorisation from database using STAN = " + systemTraceAuditNumber, new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.keystorepassword);

        com.truteq.ccpgw.transaction.manager.model.Authorisation auth = new com.truteq.ccpgw.transaction.manager.model.Authorisation();
        auth.setSystemTraceAuditNumber(systemTraceAuditNumber);
        Result result = sslComms.sendHttpPost(this.getAuthorisationRead(), auth.toJSON().getBytes());

        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        return gson.fromJson(result.getData(), com.truteq.ccpgw.transaction.manager.model.Authorisation.class);
    }
    /**
     * =========================================================================
     *extractSubfieldsDE127_03()
     * =========================================================================
     * @param routing
     * @return 
     * 
     * This method was add by Grant O'Reilly 2022-05-06
     * It was add due to the Reversal being initiated in this class for a 
     * Financial reversal. 
     * ========================================================================= 
     */
    private RoutingObj extractSubfieldsDE127_03(String routing) {

        String source = routing.substring(0, 12);
        String sink = routing.substring(12, 24);
        String sourceStan = routing.substring(24, 30);
        String sinkStan = routing.substring(30, 36);
        String totalsGroup = routing.substring(36, 48);

        RoutingObj routingObj = new RoutingObj(source, sink, sourceStan, sinkStan, totalsGroup);

        return routingObj;

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

//    /**
//     * @return the responseMessageHashTable
//     */
//    public synchronized Hashtable<String, ResponseMessage> getResponseMessageHashTable() {
//        return responseMessageHashTable;
//    }

    /**
     * @return the postilionSignOff
     */
    public boolean isPostilionSignOff() {
        return postilionSignOff;
    }

    /**
     * @param postilionSignOff the postilionSignOff to set
     */
    public void setPostilionSignOff(boolean postilionSignOff) {
        this.postilionSignOff = postilionSignOff;
    }

    /**
     * @return the commServSignOff
     */
    public boolean isCommServSignOff() {
        return commServSignOff;
    }

    /**
     * @param commServSignOff the commServSignOff to set
     */
    public void setCommServSignOff(boolean commServSignOff) {
        this.commServSignOff = commServSignOff;
    }

    /**
     * @return the signonflag
     */
    public boolean isSignonflag() {
        return signonflag;
    }

    /**
     * @param signonflag the signonflag to set
     */
    public void setSignonflag(boolean signonflag) {
        this.signonflag = signonflag;
    }

    /**
     * @return the authorisationRead
     */
    public String getAuthorisationRead() {
        return authorisationRead;
    }

    /**
     * @param authorisationRead the authorisationRead to set
     */
    public void setAuthorisationRead(String authorisationRead) {
        this.authorisationRead = authorisationRead;
    }


}
