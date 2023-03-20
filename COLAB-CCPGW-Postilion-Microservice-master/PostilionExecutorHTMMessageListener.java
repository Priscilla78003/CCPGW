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
package com.truteq.ccpgw.adapter.postilion.executor.htm;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.io.IOException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PostilionExecutorHTMMessageListener implements Runnable {
    
    private final LogWrapper mLogger = new LogWrapper(PostilionExecutorHTMMessageListener.class);
    private final PostilionExecutorAdapterHTM postilionExecutorAdapterHTM;
    private boolean active;
    private boolean first;

    
    public PostilionExecutorHTMMessageListener(PostilionExecutorAdapterHTM postilionExecutorAdapterHTM){
        this.active = true;
        this.first = true;
        this.postilionExecutorAdapterHTM = postilionExecutorAdapterHTM;
    }
    
    
    @Override
    public void run() {

            while (active) {
              if (this.postilionExecutorAdapterHTM.mCoreBankChan.isConnected()){
                try {
                    
                    ISOMsg vResponse = this.postilionExecutorAdapterHTM.mCoreBankChan.receive();
                    if (first) {
                        mLogger.info("Waiting for Response......", new Throwable().getStackTrace()[0]);
                        first = false;
                    }
                    if (vResponse != null) {
                        mLogger.info("Received response from Postilion for MTI: " + vResponse.getMTI(), new Throwable().getStackTrace()[0]);
                        this.postilionExecutorAdapterHTM.onMessageReceived(vResponse);
                        
                        switch(vResponse.getMTI()){
                            case "0800": this.postilionExecutorAdapterHTM.on0800Recieved(); break;
                        }
         
                    }

                } catch (IOException | ISOException ex) {
                    mLogger.error("Exception: Error on Postilion response, "+ ex, new Throwable().getStackTrace()[0]);
                }
              }  
            }        
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
}
