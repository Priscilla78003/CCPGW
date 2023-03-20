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

import org.jpos.iso.ISOPackager;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public abstract class PostilionRequest implements IRequest{
    
    private final ISOPackager vISOPackager;
    
    public PostilionRequest(ISOPackager vISOPackager){
        this.vISOPackager = vISOPackager;
    }

    /**
     * @return the vISOPackager
     */
    public ISOPackager getvISOPackager() {
        return vISOPackager;
    }
}
