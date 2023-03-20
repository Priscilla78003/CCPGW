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
package com.truteq.ccpgw.adapter.postilion;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.io.IOException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PostilionMessageListener implements Runnable {
    
    private final LogWrapper mLogger = new LogWrapper(PostilionMessageListener.class);
    private final PostilionAdapter postilionAdapter;
    private boolean active;
    private boolean first;

    
    public PostilionMessageListener(PostilionAdapter postilionAdapter){
        this.active = true;
        this.first = true;
        this.postilionAdapter = postilionAdapter;
    }
    
    
    private boolean checkConnectionState(){
        
            boolean connected = this.postilionAdapter.mCoreBankChan.isConnected();
            
            int delay = 10000;
            
            if (!connected) {
                mLogger.warn("Not connected to Postilion. Delay for "+delay+" milliseconds and then retry.");
                while (!connected){
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        mLogger.error("Exception will putting PostilionMessageListener to sleep."+ex);
                    }    
                    try {
                        this.postilionAdapter.mCoreBankChan = this.postilionAdapter.newISOChannel(this.postilionAdapter.mISOPackLoc);
                    } catch (IOException | ISOException ex) {
                       mLogger.error("Exception trying to reconnect."+ ex);
                    }
                    connected = this.postilionAdapter.mCoreBankChan.isConnected();
                }
            }
            
            return connected;
  
    }    
    
    @Override
    public void run() {

            while (active) {
              if (checkConnectionState()){
                try {
                    
                    ISOMsg vResponse = this.postilionAdapter.mCoreBankChan.receive();
                    if (first) {
                        mLogger.info("Waiting for Response......");
                        first = false;
                    }
                    if (vResponse != null) {
                        mLogger.info("Received response from Postilion for MTI: " + vResponse.getMTI());
                        this.postilionAdapter.onMessageReceived(vResponse);
                        
                        switch(vResponse.getMTI()){
                            case "0800": this.postilionAdapter.on0800Recieved(); break;
                            case "0810": this.postilionAdapter.on0810Recieved(); break;
                            case "0110": this.postilionAdapter.on0110Recieved(); break;
                            case "0430": this.postilionAdapter.on0430Recieved(); break;
                        }
         
                    }

                } catch (IOException | ISOException ex) {
                    mLogger.error("Exception: Error on Postilion response, "+ ex);
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
