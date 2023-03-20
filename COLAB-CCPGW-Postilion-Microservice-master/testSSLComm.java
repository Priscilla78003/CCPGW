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
public class testSSLComm {
    public static void main(String[] args) {
            String payload = 
            "{\n" +
            "  \"Cardholder\": {\n" +
            "    \"pan\": \"341502098634895\",\n" +
            "    \"expiry\": \"1612\"\n" +
            "  },\n" +
            "  \"Purchase\": {\n" +
            "    \"amount\": \"10000\",\n" +
            "    \"currency\": \"756\"\n" +
            "  },\n" +
            "  \"Merchant\": {\n" +
            "    \"acquirerBin\": \"123456\",\n" +
            "    \"countryCode\": \"100\",\n" +
            "    \"id\": \"merId\",\n" +
            "    \"name\": \"Merchant Name\",\n" +
            "    \"url\": \"http://url.net\"\n" +
            "  }\n" +
            "}";    
            
            //SSLCommunicator sslComms = new SSLCommunicator("platform-pacific-prev-signed.crt");
            //SSLCommunicator sslComms = new SSLCommunicator("/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/a93e6455-3ffc-498d-9d64-84fa22dc7095/mycerts.p12");
            SSLCommunicator sslComms = new SSLCommunicator("/home/mho/platformpac/CREDITCARD-CODE/GRANT-202107071556/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/config/keystore/mycerts.p12","mycert");
            Result result = sslComms.sendHttpPost("https://3dss.prev.netcetera-payment.ch/3ds-server/mpi/v1/createPaReqIfEnrolled", payload.getBytes());
            System.out.println(result.getComments()+" "+result.getData());
            Gson gson = new Gson();
            PaReqCreationResponse paResponse = gson.fromJson(result.getData(), PaReqCreationResponse.class);
            System.out.println(paResponse);
             
    }
}
