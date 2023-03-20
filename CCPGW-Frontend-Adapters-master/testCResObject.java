/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.frontend.adapter.json;

import com.google.gson.Gson;
import com.truteq.ccpgw.payment.gateway.api.json.CResObject;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testCResObject {
    
     public static void main(String[] args) {
         
         String CresString = "{\"messageType\":\"CRes\",\"threeDSServerTransID\":\"9b6e4cd5-cdd9-49c9-9df8-5a1898a7e936\",\"acsTransID\":\"9fea9914-02ad-46b7-a531-11cb537e497a\",\"challengeCompletionInd\":\"Y\",\"messageVersion\":\"2.1.0\",\"transStatus\":\"Y\"}";
         Gson gson = new Gson();
         
         CResObject cres = gson.fromJson(CresString, CResObject.class);
         
         
         System.out.println(cres.getThreeDSServerTransID());
     
     }    
    }
