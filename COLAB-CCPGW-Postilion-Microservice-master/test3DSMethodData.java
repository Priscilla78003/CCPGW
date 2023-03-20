/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2022 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.functional.tests;

import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class test3DSMethodData {
    public static void main(String[] args) {
            String payload = "threeDSMethodData=eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImViMmI2YzY5LWNjOGMtNGYwZC05NTM4LTA1OTFjODMzYzJkYSJ9";
//"<form name=\"frm\" method=\"POST\" action=\"Rendering URL\">\n" +
//"<input type=\"hidden\" name=\"threeDSMethodData\"\n" +
//"value=\"eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjNhYzdjYWE3LWFhNDItMjY2My03OTFiLTJhYzA1YTU0MmM0YSIsInRocmVlRFNNZX\n" +
//"Rob2ROb3RpZmljYXRpb25VUkwiOiJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIn0\">\n" +
//"</form>";
//            "<form name=\"frm\" method=\"POST\" action=\"Rendering URL\">\n" +
//            "<input type=\"hidden\" name=\"threeDSServerTransID\"\n" +
//            "value=\"3ac7caa7-aa42-2663-791b-2ac05a542c4a\">\n" +
//            "<input type=\"hidden\" name=\"threeDSMethodNotificationURL\"\n" +
//            "value=\"https://ccpgw.testbsp.com.pg/3dsmethodnotification\">\n" +                    
//            "</form>";        
//            "{\n" +
//            "    \"threeDSServerTransID\": \"655698f5-01bc-4dc4-bfdb-3140024b8c0c\",\n" +
//            "    \"threeDSMethodNotificationURL\": \"https://ccpgw.testbsp.com.pg/3dsmethodnotification\"\n" +
//            "}";    
            
            
            
            SSLCommunicator sslComms = new SSLCommunicator("/media/grant/GORData/Truteq-Gitea-Server-Repo/PlatformPAC/CCPGW-Postilion-Microservice/ccpgw-acs-servlet/config/keystore/mycerts.p12","mycerts");
            Result result = sslComms.sendHttpPost("https://3dss.extranet.netcetera.biz/3dss-demo/acs/3ds-method", payload.getBytes(),"application/x-www-form-urlencoded");
            System.out.println(result.getComments()+" "+result.getData());
             
    }    
}
