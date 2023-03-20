/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ipgw.test.postilion.comms;

import com.truteq.ccpgw.transaction.manager.comms.Communicator;
import com.truteq.ccpgw.netcetera.model.Cardholder;
import com.truteq.ccpgw.netcetera.model.Merchant;
import com.truteq.ccpgw.netcetera.model.Purchase;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.threeds.objects.PaReqRequestObject;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testPaReqCreationRequest {
    public static void main(String[] args) {
        
        PaReqRequestObject paReq = new PaReqRequestObject();
        Cardholder cardHolder = new Cardholder();
        cardHolder.setPan("341502098634895");
        cardHolder.setExpiry("1612");
        
        Purchase purchase = new Purchase();
        purchase.setAmount("10000");
        purchase.setCurrency("756");
        
        Merchant merchant = new Merchant();
        merchant.setAcquirerBin("123456");
        merchant.setCountryCode("100");
        merchant.setId("merId");
        merchant.setName("Merchant Name");
        merchant.setUrl("http://url.net");
        
        paReq.setCardholder(cardHolder);
        paReq.setPurchase(purchase);
        paReq.setMerchant(merchant);
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost("http://localhost:9078/transaction/manager/3ds/v1/service/createPaReqIfEnrolled", paReq.toJSON().getBytes());
        
        System.out.println(result.getComments()+" "+result.getData());
    }
}
