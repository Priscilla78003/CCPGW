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
public class test3DSChallenge {
    public static void main(String[] args) {
            String payload = "base64EncodedChallengeRequest=eyJtZXNzYWdlVHlwZSI6IkNSZXEiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjRjNmUzODcxLTEwMGUtNGQ4YS05M2Y3LWI5YjgwNTI4MjI1OCIsImFjc1RyYW5zSUQiOiI0NmY2NjRmMy1hYjg1LTRiMTgtOTlkMi01MWU4MGEyZThjMzciLCJjaGFsbGVuZ2VXaW5kb3dTaXplIjoiMDEiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIn0";
            
            
            SSLCommunicator sslComms = new SSLCommunicator("/media/grant/GORData/Truteq-Gitea-Server-Repo/PlatformPAC/CCPGW-Postilion-Microservice/ccpgw-acs-servlet/config/keystore/mycerts.p12","mycerts");
            Result result = sslComms.sendHttpPost("https://3dss.extranet.netcetera.biz/3dss-demo/acs/challenge", payload.getBytes(),"application/x-www-form-urlencoded");
            System.out.println(result.getComments()+" "+result.getData());
             
    }      
}
