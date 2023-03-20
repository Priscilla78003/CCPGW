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
package com.truteq.ccpgw.adapter.postilion.executor.htm.tasks;

import com.truteq.ccpgw.adapter.postilion.executor.htm.PostilionExecutorAdapterHTM;
import com.truteq.ccpgw.adapter.postilion.requests.Authorisation;
import com.truteq.ccpgw.adapter.postilion.requests.Capture;
import com.truteq.ccpgw.adapter.postilion.requests.PostilionRequest;
import com.truteq.ccpgw.adapter.postilion.requests.ResponseMessage;
import com.truteq.ccpgw.adapter.postilion.requests.Reversal;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.io.IOException;
import java.time.Instant;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PostilionSendMessageTaskHTM  implements Runnable  {

    private final LogWrapper mLogger = new LogWrapper(PostilionSendMessageTaskHTM.class);
    private final PostilionExecutorAdapterHTM postilionExecutorAdapter;
    private ISOMsg isoMessage;
    private String stan;
    private int delay = 10000;
    private int responsePeriod = 30;
    private boolean hasResponse = true;
    private boolean doRepeat = false;
    private PostilionRequest request = null;
    
    public PostilionSendMessageTaskHTM(PostilionExecutorAdapterHTM postilionExecutorAdapter, 
                                       ISOMsg isoMessage, 
                                       String stan, 
                                       int delay, 
                                       int responsePeriod, 
                                       boolean hasResponse,
                                       boolean doRepeat,
                                       PostilionRequest request) {
        
        this.postilionExecutorAdapter = postilionExecutorAdapter;
        this.isoMessage = isoMessage;
        this.stan = stan;
        this.delay = delay;
        this.responsePeriod = responsePeriod;
        this.hasResponse = hasResponse;
        this.doRepeat = doRepeat;
        this.request = request;
    }
     

    @Override
    public void run() {
         
         sendPostilionMessage();

    }
    /**
     * =========================================================================
     * processWhenOffline
     * ========================================================================= 
     * @param notifiyMessage 
     */
    private void processWhenOffline(String notifiyMessage){
        mLogger.error(notifiyMessage, new Throwable().getStackTrace()[0]); 
        try {
            switch(this.getIsoMessage().getMTI()){
                case "0420": mLogger.error("Reversal [0420] detected and not connect to Postilion!", new Throwable().getStackTrace()[0]); break;
                case "0421": mLogger.error("Reversal repeat [0421] detected and not connect to Postilion!", new Throwable().getStackTrace()[0]); break;
                case "0220": mLogger.error("Capture [0220] detected and not connect to Postilion!", new Throwable().getStackTrace()[0]); break; 
                case "0221": mLogger.error("Capture repeat [0221] detected and not connect to Postilion!", new Throwable().getStackTrace()[0]); break;   
            }
            sendPostilionMessageWhenDisconnected();             
        } catch (ISOException ex) {
            mLogger.error("Exception: Error running the sendPostilionMessageWhenDisconnected."+ex, new Throwable().getStackTrace()[0]);
        }       
    }
    
    /**
     * =========================================================================
     * processWhenSignedOff
     * ========================================================================= 
     * @param notifiyMessage 
     */
    private void processWhenSignedOff(String notifiyMessage){
        mLogger.error(notifiyMessage, new Throwable().getStackTrace()[0]); 
        try {
            
            switch(this.getIsoMessage().getMTI()){
                case "0420": mLogger.error("Reversal [0420] detected and SignedOff to Postilion!", new Throwable().getStackTrace()[0]); break;
                case "0421": mLogger.error("Reversal repeat [0421] detected and SignedOff to Postilion!", new Throwable().getStackTrace()[0]); break;
                case "0220": mLogger.error("Capture [0220] detected and SignedOff to Postilion!", new Throwable().getStackTrace()[0]); break; 
                case "0221": mLogger.error("Capture repeat [0221] detected and SignedOff to Postilion!", new Throwable().getStackTrace()[0]); break;   
            }            
            sendPostilionMessageWhenSignedOff();
        } catch (ISOException ex) {
            mLogger.error("Exception: Error running the sendPostilionMessageWhenSignedOff."+ex, new Throwable().getStackTrace()[0]);
        }       
    }  
        
    private void checkForRepeats(ISOMsg sentMsg, ISOMsg responseMsg){
        try {
            switch (sentMsg.getMTI()){
                case "0221" :  if (responseMsg.getMTI().equals("0230")){
                    this.postilionExecutorAdapter.updatePersistedRepeat("0221",this.getStan());
                }
                break;
                case "0421" :  if (responseMsg.getMTI().equals("0430")){
                    this.postilionExecutorAdapter.updatePersistedRepeat("0421",this.getStan());
                }
                break;
            }
        } catch (ISOException ex) {
             mLogger.error("Exception: Error running the checkForRepeats."+ex, new Throwable().getStackTrace()[0]);
        }
        
    }
    
    /**
     * =========================================================================
     * sendPostilionMessage
     * =========================================================================  
     */
    private void sendPostilionMessage() {

        if ((this.postilionExecutorAdapter.mCoreBankChan != null)&&(this.postilionExecutorAdapter.mCoreBankChan.isConnected())){
            
            if((this.postilionExecutorAdapter.isSignedon()) || ((!this.postilionExecutorAdapter.isPostilionSignOff())&&
               (!this.postilionExecutorAdapter.isCommServSignOff()))){
              
                mLogger.debug("Sending to Postillion message: " + this.getIsoMessage().toString() + " STAN:" + this.getStan(), new Throwable().getStackTrace()[0]);

                this.postilionExecutorAdapter.printISOMessage(this.getIsoMessage());

                try {
                    this.postilionExecutorAdapter.mCoreBankChan.send(this.getIsoMessage());
                } catch (IOException | ISOException ex) {
                    mLogger.error("Exception while sending message to Postilion"+ ex, new Throwable().getStackTrace()[0]);
                }

                if (hasResponse){
                    ISOMsg response = waitingForResponseOn();

                    if (response != null){
                        try {
                            mLogger.info("Received response from Postilion: "+response.getMTI(), new Throwable().getStackTrace()[0]);
                            
                            checkForRepeats(this.getIsoMessage(),response);
                            
                        } catch (ISOException ex) {
                            mLogger.error("Exception while reading MTI on response message from Postilion: "+ ex, new Throwable().getStackTrace()[0]);
                        }
                    }
                    else{
                        try {
                             this.postilionExecutorAdapter.doRepeat(this.getIsoMessage());
                        } catch (ISOException ex) {
                            mLogger.error("Exception: Error running the doRepeat."+ex, new Throwable().getStackTrace()[0]);
                        }
                    }
                }
            }
            else{
                mLogger.error("Not signed on to Postilion! No commands will be executed. [Please sign on for command execution.]", new Throwable().getStackTrace()[0]);
                
                try {
                    mLogger.error("Message received: "+this.getIsoMessage().getMTI(), new Throwable().getStackTrace()[0]);
                    switch (this.getIsoMessage().getMTI()) {
                        case "0800":
                            mLogger.error("SignOn Flag = "+this.postilionExecutorAdapter.isSignonflag(), new Throwable().getStackTrace()[0]);
                            if (this.postilionExecutorAdapter.isSignonflag()){
                                this.postilionExecutorAdapter.setCommServSignOff(false);
                                this.postilionExecutorAdapter.setPostilionSignOff(false);
                                sendPostilionMessage();
                            }   break;
                        case "0810":
                            mLogger.debug("Sending to Postillion message: " + this.getIsoMessage().toString() + " STAN:" + this.getStan(), new Throwable().getStackTrace()[0]);
                            this.postilionExecutorAdapter.printISOMessage(this.getIsoMessage());
                            try {
                                this.postilionExecutorAdapter.mCoreBankChan.send(this.getIsoMessage());
                            } catch (IOException | ISOException ex) {
                                mLogger.error("Exception while sending message to Postilion"+ ex, new Throwable().getStackTrace()[0]);
                            }   break;
                        default:
                            processWhenSignedOff("Not signed on to Postilion!");
                            break;
                    }
                } catch (ISOException ex) {
                     mLogger.error("Exception: Error trying to sign on. " + ex, new Throwable().getStackTrace()[0]);
                }
            }
        }
        else{
               processWhenOffline("Not connect to Postilion!");
        }

    }
    
     /**
     * =========================================================================
     * sendPostilionMessageWhenDisconnected
     * =========================================================================  
     */
    private void sendPostilionMessageWhenDisconnected() {
            
            try {
                if(this.postilionExecutorAdapter.mCoreBankChan != null)
                   this.postilionExecutorAdapter.mCoreBankChan.send(this.getIsoMessage());
            } catch (IOException | ISOException ex) {
                mLogger.error("Exception while sending message to Postilion: "+ ex, new Throwable().getStackTrace()[0]);
            }        

        
            if (hasResponse){
                ISOMsg response = waitingForResponseOn();
                
                if (response != null){
                    try {
                        mLogger.info("Received response from Postilion: "+response.getMTI(), new Throwable().getStackTrace()[0]);
                        if ((response.getMTI().equals("PP0430"))||
                            (response.getMTI().equals("PP0230"))||
                            (response.getMTI().equals("0421"))||
                            (response.getMTI().equals("0221"))){
                            try {
                                 this.postilionExecutorAdapter.doRepeat(this.getIsoMessage());
                            } catch (ISOException ex) {
                                mLogger.error("Exception: Error running the doRepeat."+ex, new Throwable().getStackTrace()[0]);
                            }                        
                        }
                    } catch (ISOException ex) {
                        mLogger.error("Exception while reading MTI on response message from Postilion: "+ ex, new Throwable().getStackTrace()[0]);
                    }                    
                }
            }

    }   
    
     /**
     * =========================================================================
     * sendPostilionMessageWhenSignedOff
     * =========================================================================  
     */
    private void sendPostilionMessageWhenSignedOff() {
            
        ISOMsg response = waitingForResponseOn();

        if (response != null){
            try {
                mLogger.info("Received response from Postilion: "+response.getMTI(), new Throwable().getStackTrace()[0]);
                if ((response.getMTI().equals("PP0430"))||
                    (response.getMTI().equals("PP0230"))||
                    (response.getMTI().equals("0421"))||
                    (response.getMTI().equals("0221"))){
                    try {
                         this.postilionExecutorAdapter.doRepeat(this.getIsoMessage());
                    } catch (ISOException ex) {
                        mLogger.error("Exception: Error running the doRepeat."+ex, new Throwable().getStackTrace()[0]);
                    }                        
                }
            } catch (ISOException ex) {
                mLogger.error("Exception while reading MTI on response message from Postilion: "+ ex, new Throwable().getStackTrace()[0]);
            }                    
        }
    }     
    
    /**
     * =========================================================================
     * waitingForResponseOn
     * ========================================================================= 
     * @return 
     * =========================================================================
     */
    //private ISOMsg waitingForResponseOn(String stan){
    private ISOMsg waitingForResponseOn(){
        boolean noResponse = false;
        boolean wait = true;
        Instant start = Instant.now();
        //ResponseMessage msg = PostilionExecutorAdapterHTM.responseMessageHashTable.get(this.getStan());
        ResponseMessage msg = null;
        while (wait){
            Instant end = Instant.now();
            long diff = end.getEpochSecond() - start.getEpochSecond();
            msg = PostilionExecutorAdapterHTM.responseMessageHashTable.get(this.getStan());
            
            if (msg != null){
                wait = msg.isWaiting();
            }
            
            if (diff >= responsePeriod) {
                mLogger.debug("No response from Postilion after "+responsePeriod+"sec on STAN: "+this.getStan()+". Aborting response wait!", new Throwable().getStackTrace()[0]);
                noResponse = true;
//                if (msg != null){
//                   msg.setWaiting(false);
//                }
                wait = false;
            }                
        }
        if (msg != null){
            if (noResponse){ try {
                //No response detected
                mLogger.info("No response detected for: "+this.isoMessage.getMTI(), new Throwable().getStackTrace()[0]);
                switch (this.isoMessage.getMTI()){
                    case "0420" :
                    case "0421" :  ISOMsg isomsg = ReveralRepeatMessage();
                                   msg.setIsoMessage(isomsg);
                                   msg.setWaiting(false);
                                   return isomsg;
                                   
                    case "0220" :
                    case "0221" : 
                                   isomsg = CaptureRepeatMessage();
                                   msg.setIsoMessage(isomsg);
                                   msg.setWaiting(false);
                                   return isomsg;
                    case "0100" :  
                                   isomsg = AuthorisePPGeneratedResponseMessage();
                                   msg.setIsoMessage(isomsg);
                                   msg.setWaiting(false);
                                   return isomsg;
                }
                } catch (ISOException ex) {
                    mLogger.error("Exception on no response detected: "+ex, new Throwable().getStackTrace()[0]);
                }
            }
            return msg.getIsoMessage();
        }
        return null;
    }
    
    private ISOMsg AuthorisePPGeneratedResponseMessage(){
        mLogger.info("Authorise PP generated message being processed.", new Throwable().getStackTrace()[0]);
        Authorisation authRequest = (Authorisation) this.request;
        return authRequest.generateAuthoriseResponse("91"); //Card issuer temporarily unreachab
    }    
    
    private ISOMsg ReveralRepeatMessage(){
        mLogger.info("Reveral Repeat Message being processed.", new Throwable().getStackTrace()[0]);
        Reversal revRequest = (Reversal) this.request;
        return revRequest.generateReversalResponse("00");
    }
    
    private ISOMsg CaptureRepeatMessage(){
       mLogger.info("Capture Repeat Message being processed.", new Throwable().getStackTrace()[0]);
       Capture capRequest = (Capture) this.request;
       return capRequest.generateCaptureResponse("00");
    }    
    
    /**
     * @return the isoMessage
     */
    public ISOMsg getIsoMessage() {
        return isoMessage;
    }

    /**
     * @param isoMessage the isoMessage to set
     */
    public void setIsoMessage(ISOMsg isoMessage) {
        this.isoMessage = isoMessage;
    }

    /**
     * @return the stan
     */
    public String getStan() {
        return stan;
    }

    /**
     * @param stan the stan to set
     */
    public void setStan(String stan) {
        this.stan = stan;
    }     
    
}
