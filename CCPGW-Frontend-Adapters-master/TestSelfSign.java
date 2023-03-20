/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.frontend.adapter.json;

import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestSelfSign {
    public static void main(String[] args) {
            String payload = 
            "{\"order_id\":\"36\",\"amount\":500,\"customer_first_name\":\"Grant\",\"customer_last_name\":\"O\\u0027Reilly\",\"customer_email\":\"grantboreilly@gmail.com\",\"description\":\"Payment for Order #36\",\"callback_url\":\"http://localhost:8083/checkout/order-received/36/?key\\u003dwc_order_ZiScyJpKoZtgs\",\"callback_key\":\"wc_order_ZiScyJpKoZtgs\",\"merchant_key\":\"dc3c62ee4d349a6cda7edd11e5972ec2f16e7e5b4444826c774e59d0af3b0574\",\"currency\":\"PGK\"}";    

            //SSLCommunicator sslComms = new SSLCommunicator("/media/grant/GORData/Truteq-Gitea-Server-Repo/PlatformPAC/CCPGW-Frontend-Adapters/ccpgw-JSON-frontend-adapter/config/keystore/selfsigned.p12","platformpac");
            SSLCommunicator sslComms = new SSLCommunicator("/media/grant/GORData/Truteq-Gitea-Server-Repo/PlatformPAC/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/config/keystore/selfsigned.p12","platformpac");

            Result result = sslComms.sendHttpPostNoCert("https://localhost:9078/transaction/manager/database/order/write", payload.getBytes(),"application/json");
            
            System.out.println(result.getComments()+" "+result.getData());
             
    }     
}
