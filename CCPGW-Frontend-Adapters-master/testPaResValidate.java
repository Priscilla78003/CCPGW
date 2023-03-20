/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ipgw.test.postilion.comms;

import com.truteq.ccpgw.transaction.manager.comms.Communicator;
import com.truteq.ccpgw.netcetera.model.CH;
import com.truteq.ccpgw.netcetera.model.Merchant2;
import com.truteq.ccpgw.netcetera.model.Message;
import com.truteq.ccpgw.netcetera.model.PAReq;
import com.truteq.ccpgw.netcetera.model.Purchase2;
import com.truteq.ccpgw.netcetera.model.SessionData;
import com.truteq.ccpgw.netcetera.model.ThreeDSecureType;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.threeds.objects.PaResValidateRequestObject;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testPaResValidate {
    public static void main(String[] args) {
        
        PaResValidateRequestObject paResValidate = new PaResValidateRequestObject ();
        
        paResValidate.setPaRes("4j6h89g4kd92ks2==");
        
        SessionData sessionData = new SessionData();
        sessionData.setPaReqCreationTime("20131015 16:45:01");
        sessionData.setPan("0000000000005432");
        sessionData.setAcsUrl("http://www.acs-url.com");
        sessionData.setScheme("Visa");
        sessionData.setMasterCardTokenized("false");
        
        
        Merchant2 merchant = new Merchant2();
        merchant.setAcqBIN("123456");
        merchant.setCountry("756");
        merchant.setMerID("merId");
        merchant.setName("Merchant Name");
        merchant.setUrl("http://groovy-merchant.com");
        
        Purchase2 purchase = new Purchase2();
        purchase.setXid("aXql9ldTUg7OHzBSHJax");
        purchase.setDate("20131015 16:45:00");
        purchase.setAmount("725");
        purchase.setCurrency("756");
        purchase.setExponent("2");
        purchase.setDesc("This is the description");
        
        CH ch = new CH();
        ch.setAcctID("accountId");
        ch.setExpiry("1612");
        
        PAReq paReq = new PAReq();
        paReq.setVersion("1.0.2");
        paReq.setMerchant(merchant);
        paReq.setPurchase(purchase);
        paReq.setCH(ch);
        
        Message message = new Message();
        message.setId("pa9d4eb548-db62-43b7-8cc8-8731ac202de2");
        message.setPAReq(paReq);
 
        ThreeDSecureType threedsType = new ThreeDSecureType();
        threedsType.getMessage().add(message);
        sessionData.setThreeDSecurePAReq(threedsType);
        paResValidate.setSessionData(sessionData);
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost("http://localhost:9078/transaction/manager/3ds/v1/service/validatePaRes", paResValidate.toJSON().getBytes());
        
        System.out.println(result.getComments()+" "+result.getData());
    }    
}
