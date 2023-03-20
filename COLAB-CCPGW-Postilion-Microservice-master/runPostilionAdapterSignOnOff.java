/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class runPostilionAdapterSignOnOff {
    
    private static final LogWrapper mLogger = new LogWrapper(runPostilionAdapterSignOnOff.class);
    
    public static void main(String[] args) throws ServiceException, MessageException, ISOException {  
        
        PostilionAdapter postilionAdapter = new PostilionAdapter();
        postilionAdapter.start();
        postilionAdapter.doSignOnOff(eAuth.SIGNON);
        try {
                Thread.sleep(10000);
        } catch (InterruptedException ex) {
                mLogger.error("Exception on the thread sleep.", ex);
        }
        postilionAdapter.doSignOnOff(eAuth.SIGNOFF);
    }
}
