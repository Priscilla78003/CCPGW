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
package com.truteq.ccpgw.adapter.postilion.requests;

import com.truteq.ccpgw.adapter.postilion.requests.objects.AuthoriseObject;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.protocol.MessageException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class CreditDebitAdjustment implements IRequest{

    private final LogWrapper mLogger = new LogWrapper(CreditDebitAdjustment.class);
    private final AuthoriseObject authoriseObj;

    public CreditDebitAdjustment (AuthoriseObject authObj) {
        
        this.authoriseObj = authObj;
        
    } 
    @Override
    public ISOMsg getUnpacked() throws MessageException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AuthoriseObject getAuthoriseObj() {
        return authoriseObj;
    }
    
}
