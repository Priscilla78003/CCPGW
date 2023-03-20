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

import com.truteq.ccpgw.communication.server.listeners.PostilionListener;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PostilionAdapterThread implements Runnable, PostilionListener {
    
    private final LogWrapper mLogger = new LogWrapper(PostilionAdapterThread.class);
    private PostilionAdapterStub stub;
    
    public PostilionAdapterThread(){
        this.stub = new PostilionAdapterStub(this);
    }

    @Override
    public void run() {
        mLogger.info("Started the Postilion Adapter Thread.");
    }

    @Override
    public void onConnect() {
        mLogger.info("onConnect event fired!");
    }

    @Override
    public void onDisconnect() {
        mLogger.info("onDisconnect event fired!");
    }

    @Override
    public void onSignOn() {
         mLogger.info("onSignOn event fired!");
    }

    @Override
    public void onSignOff() {
        mLogger.info("onSignOff event fired!");
    }
    
    @Override
    public void onPing() {
         mLogger.info("onPing event fired!");
    }    

    @Override
    public void onAuthorise() {
        mLogger.info("onAuthorise event fired!");
    }

    @Override
    public void onCapture() {
        mLogger.info("onCapture event fired!");
    }

    @Override
    public void onReversal() {
        mLogger.info("onReversal event fired!");
    }
    
    @Override
    public void onDebit() {
        mLogger.info("onDebit event fired!");
    }

    @Override
    public void onCredit() {
        mLogger.info("onCredit event fired!");
    }

    @Override
    public void onFinancial() {
         mLogger.info("onFinancial event fired!");
    }

    @Override
    public void onRefund() {
        mLogger.info("onRefund event fired!");
    } 
    
    @Override
    public void onCleanUp() {
        mLogger.info("onCleanUp event fired!");
    } 
        
    @Override
    public void onCheckConnection() {
        mLogger.info("onCheckConnection event fired!");
    }

    @Override
    public void onClientDisconnect() {
        mLogger.info("onClientDisconnect event fired!");
    }

    @Override
    public void onClientConnect() {
        mLogger.info("onClientConnect event fired!");
    }

    @Override
    public void onClientListernerRefresh() {
       mLogger.info("onClientListenerRefresh event fired!");
    }
    
    /**
     * @return the stub
     */
    public PostilionAdapterStub getStub() {
        return stub;
    }    

}
