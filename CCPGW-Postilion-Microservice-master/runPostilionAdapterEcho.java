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
package com.truteq.ccpgw.adapter.postilion.test;

import com.truteq.ServiceException;
import com.truteq.ccpgw.adapter.postilion.PostilionAdapter;
import com.truteq.ccpgw.adapter.postilion.enums.eAuth;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.protocol.MessageException;
import org.jpos.iso.ISOException;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class runPostilionAdapterEcho {
    
    private static final LogWrapper mLogger = new LogWrapper(runPostilionAdapterEcho.class);
    
    public static void main(String[] args) throws ServiceException, MessageException, ISOException {  
        
        PostilionAdapter postilionAdapter = new PostilionAdapter();
        postilionAdapter.start();
        postilionAdapter.doSignOnOff(eAuth.SIGNON);
        try {
                Thread.sleep(5000);
        } catch (InterruptedException ex) {
                mLogger.error("Exception on the thread sleep.", ex);
        }        
//        postilionAdapter.doEcho();
//
//        try {
//                Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//                mLogger.error("Exception on the thread sleep.", ex);
//        }        
        postilionAdapter.doSignOnOff(eAuth.SIGNOFF);      
    }
}
