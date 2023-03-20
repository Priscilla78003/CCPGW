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
package com.truteq.ccpgw.transaction.manager.functional.tests;

import com.google.gson.Gson;
import com.truteq.ccpgw.netcetera.model.PaReqCreationResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testHttps {
     public static void main(String[] args) {
            String payload = "{\n" +
                                "\"merchantAccountCode\":\"1009319144\",\n" +
                                "\"currency\":\"PGK\"\n" +
                              "}"; 
            
            SSLCommunicator sslComms = new SSLCommunicator("/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/ssl/PlatformPac/working/platformpacKS.p12","platformpac");
            Result result = sslComms.sendHttpPost("https://localhost:9078/transaction/manager/database/read/merchantId", payload.getBytes());
            //SSLCommunicator sslComms = new SSLCommunicator("/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/ssl/server/test.p12");
            //Result result = sslComms.sendHttpPost("https://localhost:9078/transaction/manager/database/read/merchantId","changeit", payload.getBytes());
            System.out.println(result.getComments()+" "+result.getData());
            Gson gson = new Gson();
            PaReqCreationResponse paResponse = gson.fromJson(result.getData(), PaReqCreationResponse.class);
            System.out.println(paResponse);
             
    }   
}
