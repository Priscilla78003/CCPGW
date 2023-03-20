/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Communication server
 * ***************************************************************
 * Used to communicate with different adapters 
 * Support: Grant O'Reilly gbo@truteq.com OR grant@platformpac.com.pg
 * V01.00.00  29-Jum-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.communication.server.application.threads;

import com.truteq.ccpgw.comms.server.model.CommsServerMessage;
import com.truteq.ccpgw.comms.server.model.ICommands;
import com.truteq.ccpgw.communication.server.listeners.PostilionListener;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalResponse;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AdapterStub implements ICommands{
    
    private final PostilionListener postilionListener;
    
    public AdapterStub (PostilionListener postilionListener){
        this.postilionListener = postilionListener;
    }
    
    private final LogWrapper mLogger = new LogWrapper(AdapterStub.class);

    public void connect(){
        getPostilionListener().onConnect();
    }
    
    public void disconnect(){
        getPostilionListener().onDisconnect();
    }
    
    public CommsServerMessage signon() {
        getPostilionListener().onSignOn();
        mLogger.info("Signing ON to Postilion.");
        return new CommsServerMessage(SIGNON,"String","Signed ON.");
    }

    public CommsServerMessage  signoff() {
        getPostilionListener().onSignOff();
        mLogger.info("Signing OFF from Postilion.");
        return new CommsServerMessage(SIGNOFF,"String","Signed OFF.");
    }

    public CommsServerMessage  ping() {
        getPostilionListener().onPing();
        mLogger.info("Ping Postilion.");
        return new CommsServerMessage(PING,"String","PING....");
    }

    public CommsServerMessage authorise(Object messageObj){
        getPostilionListener().onAuthorise();
        
        if (messageObj instanceof AuthoriseRequest){
            AuthoriseRequest req = (AuthoriseRequest)messageObj;
            
            AuthoriseResponse resp = new AuthoriseResponse();
            resp.setPrimaryAccountNumber(req.getPrimaryAccountNumber());
                    
            return new CommsServerMessage(AUTHORISE,"AuthoriseResponse",resp);
        }
        else 
            return new CommsServerMessage(ERROR,"String","Message object is NOT type AuthoriseRequest");
    }

    public CommsServerMessage capture(Object messageObj) {
        getPostilionListener().onCapture();
        if (messageObj instanceof CaptureRequest) {
            CaptureRequest req = (CaptureRequest) messageObj;

            CaptureResponse resp = new CaptureResponse();
            resp.setPrimaryAccountNumber(req.getPrimaryAccountNumber());

            return new CommsServerMessage(CAPTURE,"CaptureResponse", resp);
        } else {
            return new CommsServerMessage(ERROR,"String", "Message object is NOT type CaptureRequest");
        }
    }

    public CommsServerMessage reversal(Object messageObj) {
        getPostilionListener().onReversal();

        if (messageObj instanceof ReversalRequest) {
            ReversalRequest req = (ReversalRequest) messageObj;

            ReversalResponse resp = new ReversalResponse();
            resp.setPrimaryAccountNumber(req.getPrimaryAccountNumber());

            return new CommsServerMessage(REVERSAL,"ReversalResponse", resp);
        } else {
            return new CommsServerMessage(ERROR,"String", "Message object is NOT type ReversalRequest");
        }        
    }
    
    public CommsServerMessage financial(Object messageObj){
        getPostilionListener().onFinancial();
        
        if (messageObj instanceof AuthoriseRequest){
            AuthoriseRequest req = (AuthoriseRequest)messageObj;
            
            AuthoriseResponse resp = new AuthoriseResponse();
            resp.setPrimaryAccountNumber(req.getPrimaryAccountNumber());
                    
            return new CommsServerMessage(AUTHORISE,"AuthoriseResponse",resp);
        }
        else 
            return new CommsServerMessage(ERROR,"String","Message object is NOT type AuthoriseRequest");
    }
    
    public CommsServerMessage refund(Object messageObj){
        getPostilionListener().onRefund();
        if (messageObj instanceof AuthoriseRequest){
            AuthoriseRequest req = (AuthoriseRequest)messageObj;
            
            AuthoriseResponse resp = new AuthoriseResponse();
            resp.setPrimaryAccountNumber(req.getPrimaryAccountNumber());
                    
            return new CommsServerMessage(AUTHORISE,"AuthoriseResponse",resp);
        }
        else 
            return new CommsServerMessage(ERROR,"String","Message object is NOT type AuthoriseRequest");
    }
            
    
    public CommsServerMessage debit(Object messageObj){
        getPostilionListener().onDebit();
        if (messageObj instanceof CaptureRequest) {
            CaptureRequest req = (CaptureRequest) messageObj;

            CaptureResponse resp = new CaptureResponse();
            resp.setPrimaryAccountNumber(req.getPrimaryAccountNumber());

            return new CommsServerMessage(CAPTURE,"CaptureResponse", resp);
        } else {
            return new CommsServerMessage(ERROR,"String", "Message object is NOT type CaptureRequest");
        }
    }
    
    public CommsServerMessage credit(Object messageObj){
        getPostilionListener().onCredit();
        if (messageObj instanceof CaptureRequest) {
            CaptureRequest req = (CaptureRequest) messageObj;

            CaptureResponse resp = new CaptureResponse();
            resp.setPrimaryAccountNumber(req.getPrimaryAccountNumber());

            return new CommsServerMessage(CAPTURE,"CaptureResponse", resp);
        } else {
            return new CommsServerMessage(ERROR,"String", "Message object is NOT type CaptureRequest");
        }
    }    

    /**
     * @return the postilionListener
     */
    public PostilionListener getPostilionListener() {
        return postilionListener;
    }
}
