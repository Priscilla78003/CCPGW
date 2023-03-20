/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Postilion Async Restful server : POSTILION - Restful Web service  
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Aug-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.comms.server.ws.async.services;

import com.truteq.ccpgw.comms.server.model.CommsServerMessage;
import com.truteq.ccpgw.comms.server.ws.async.bindings.CommunicationServerBinding;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.TestRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.TestResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@Service
public class AsyncService {
    
    @Autowired
    CommsBindingService commsBindingService;
    
    private final LogWrapper mLogger = new LogWrapper(AsyncService.class);
    
    /**
     * =========================================================================
     * getAuthorisationCommand [i.e.commands e.g.100, 120]
     * -------------------------------------------------------------------------
     * @param state
     * @param authReq
     * @return 
     * =========================================================================
     */ 
    @Async("asyncExecutor")
    public CompletableFuture<AuthoriseResponse> getAuthorisationCommand(String state, AuthoriseRequest authReq)
    {
        CommsServerMessage commsServMessage = new CommsServerMessage(state,"AuthoriseRequest",authReq);
        
        try {
            CommunicationServerBinding link = commsBindingService.getBinding(UUID.randomUUID().toString()); 
            CommsServerMessage respObj =  link.sendMessage(commsServMessage);
            
            if ((respObj.getType().equals("AuthoriseResponse"))&&(respObj.getState().equals(state))){
                AuthoriseResponse response = (AuthoriseResponse) respObj.getMessageObj();
                link.disconnect();
                return CompletableFuture.completedFuture(response);                
            }
            
            else {
                mLogger.error("Error: "+ (String) respObj.getMessageObj());
                throw new ClassNotFoundException();  
            }           
            
            
        } catch (IOException | ClassNotFoundException ex) {
            mLogger.error("Exception sending message to Comms Server."+ ex);
        }        
        return null;        
    }
       

    
    /**
     * =========================================================================
     * getCaptureCommand [i.e.commands 220]
     * -------------------------------------------------------------------------
     * @param state
     * @param capReq
     * @return 
     * =========================================================================
     */
    @Async("asyncExecutor")
    public CompletableFuture<CaptureResponse> getCaptureCommand(String state, CaptureRequest capReq){
        
        
        CommsServerMessage commsServMessage = new CommsServerMessage(state,"CaptureRequest",capReq);

        try {
            CommunicationServerBinding link = commsBindingService.getBinding(UUID.randomUUID().toString()); 
            CommsServerMessage respObj =  link.sendMessage(commsServMessage);            
            
            if ((respObj.getType().equals("CaptureResponse"))&&(respObj.getState().equals(state))){
                CaptureResponse response = (CaptureResponse) respObj.getMessageObj();
                link.disconnect();
                return CompletableFuture.completedFuture(response);                 
            }
            
            else {
                mLogger.error("Error: "+ (String) respObj.getMessageObj());
                throw new ClassNotFoundException();  
            }           
            
            
        } catch (IOException | ClassNotFoundException ex) {
            mLogger.error("Exception sending message to Comms Server."+ ex);
        }        
        return null;
    }
    
    /**
     * =========================================================================
     * getReversalCommand [i.e.commands 420]
     * -------------------------------------------------------------------------
     * @param state
     * @param revReq
     * @return 
     * =========================================================================
     */
    @Async("asyncExecutor")
    public CompletableFuture<ReversalResponse> getReversalCommand(String state,ReversalRequest revReq){
        
        
        CommsServerMessage commsServMessage = new CommsServerMessage(state,"ReversalRequest",revReq);

        try {
            CommunicationServerBinding link = commsBindingService.getBinding(UUID.randomUUID().toString()); 
            CommsServerMessage respObj =  link.sendMessage(commsServMessage);
            
            if ((respObj.getType().equals("ReversalResponse"))&&(respObj.getState().equals(state))){
                ReversalResponse response = (ReversalResponse) respObj.getMessageObj();
                link.disconnect();
                return CompletableFuture.completedFuture(response); 
                //return CompletableFuture.completedFuture(response).get(120, TimeUnit.SECONDS);
            }
            
            else {
                mLogger.error("Error: "+ (String) respObj.getMessageObj());
                throw new ClassNotFoundException();  
            }           
            
            
        } catch (IOException | ClassNotFoundException ex) {
            mLogger.error("Exception sending message to Comms Server."+ ex);
        }        
        return null;
    } 
    
    /**
     * =========================================================================
     * getTest
     * -------------------------------------------------------------------------
     * @param name
     * @param delay
     * @return
     * @throws InterruptedException 
     * =========================================================================
     */
    @Async("asyncExecutor")
    public CompletableFuture<String> getTest(String name, int delay) throws InterruptedException 
    {
            mLogger.info("Test started for "+name);
            
            mLogger.info("Waiting for "+delay+" millisec");

            Thread.sleep(delay);	//Intentional delay
            
            mLogger.info("Test completed "+name);
            
            return CompletableFuture.completedFuture("The name is "+name);
    }    
    /**
     * =========================================================================
     * getCommServTest
     * -------------------------------------------------------------------------
     * @param state
     * @param authReq
     * @return
     * @throws InterruptedException
     * -------------------------------------------------------------------------
     */
    @Async("asyncExecutor")
    public CompletableFuture<TestResponse> getCommServTest(String state, TestRequest authReq) throws InterruptedException 
    {
        CommsServerMessage commsServMessage = new CommsServerMessage(state,"TestRequest",authReq);

        try {
            
            CommunicationServerBinding link = commsBindingService.getBinding(authReq.getName()); 
            CommsServerMessage respObj =  link.sendMessage(commsServMessage);
            
            if ((respObj.getType().equals("TestResponse"))&&(respObj.getState().equals(state))){
                TestResponse response = (TestResponse) respObj.getMessageObj();
                link.disconnect();
                return CompletableFuture.completedFuture(response);                
            }
            
            else {
                mLogger.error("Error: "+ (String) respObj.getMessageObj());
                throw new ClassNotFoundException();  
            }           
            
            
        } catch (IOException | ClassNotFoundException ex) {
            mLogger.error("Exception sending message to Comms Server."+ ex);
        }        
        return null;
        
    }  


    
}
