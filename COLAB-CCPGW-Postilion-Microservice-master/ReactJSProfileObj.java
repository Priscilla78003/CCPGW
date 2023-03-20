/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.merchant.portal.model;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ReactJSProfileObj extends Profile{
    
    private Integer merchantId;

    /**
     * @return the merchantId
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
    
}
