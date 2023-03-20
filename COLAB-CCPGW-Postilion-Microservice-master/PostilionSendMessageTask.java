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
package com.truteq.ccpgw.adapter.postilion.executor.tasks;

import com.truteq.ccpgw.adapter.postilion.executor.main.PostilionExecutorAdapter;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.io.IOException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PostilionSendMessageTask  implements Runnable  {

    private final LogWrapper mLogger = new LogWrapper(PostilionSendMessageTask.class);
    private final PostilionExecutorAdapter postilionExecutorAdapter;
    private ISOMsg isoMessage;
    private String stan;
    private int delay = 10000;
    
    public PostilionSendMessageTask(PostilionExecutorAdapter postilionExecutorAdapter, ISOMsg isoMessage, String stan, int delay) {
        this.postilionExecutorAdapter = postilionExecutorAdapter;
        this.isoMessage = isoMessage;
        this.stan = stan;
        this.delay = delay;
    }
    
    private boolean checkConnectionState(){
        
            boolean connected = this.postilionExecutorAdapter.mCoreBankChan.isConnected();
            
            if (!connected) {
                mLogger.warn("Not connected to Postilion. Delay for "+delay+" milliseconds and then retry.");
                while (!connected){
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        mLogger.error("Exception will putting PostilionMessageListener to sleep."+ex);
                    }    
                    try {
                        mLogger.warn("Reconnecting to Postilion.");
                        this.postilionExecutorAdapter.mCoreBankChan = this.postilionExecutorAdapter.newISOChannel(this.postilionExecutorAdapter.mISOPackLoc);
                    } catch (IOException | ISOException ex) {
                        mLogger.error("Exception while trying to connect."+ex);
                    }
                    connected = this.postilionExecutorAdapter.mCoreBankChan.isConnected();
                }
            }
            
            return connected;
  
    }     

    @Override
    public void run() {
         
         sendPostilionMessage();

    }
    
    private void sendPostilionMessage() {

        if (checkConnectionState()){
            mLogger.debug("Sending to Postillion message: " + this.getIsoMessage().toString() + " STAN:" + this.getStan());
            
            this.postilionExecutorAdapter.printISOMessage(this.getIsoMessage());
            
            try {
                this.postilionExecutorAdapter.mCoreBankChan.send(this.getIsoMessage());
            } catch (IOException | ISOException ex) {
                mLogger.error("Exception while sending message to Postilion"+ ex);
            }

        }

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
