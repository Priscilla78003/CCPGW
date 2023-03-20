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

import com.google.gson.Gson;
import com.truteq.ccpgw.netcetera.model.Message;
import com.truteq.ccpgw.threeds.objects.PaReqResponseObject;
import java.util.List;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testPaResponse {
    public static void main(String[] args) {
    String resp = "{\"sessionId\":\"999b1ecc-539a-4251-a308-2fe1efb53110\",\"paReq\":null,\"threeDSecureVEReq\":{\"message\":[{\"id\":\"ve1fdf60d2-8ab6-4d10-9e04-6c2bf1ddf40c\",\"vereq\":{\"version\":\"1.0.2\",\"pan\":\"5293890109903665\",\"extension\":[\"<Extension critical=\\\"false\\\" id=\\\"MasterCard Tokenization\\\"/>\"],\"merchant\":{\"acqBIN\":\"123456\",\"merID\":\"merId\",\"password\":\"12345678\",\"name\":null,\"country\":null,\"url\":null},\"browser\":{\"deviceCategory\":\"0\",\"accept\":null,\"userAgent\":null}},\"veres\":null,\"pareq\":null,\"error\":null,\"pares\":null,\"any\":[]}]},\"threeDSecureVERes\":{\"message\":[{\"id\":\"ve1fdf60d2-8ab6-4d10-9e04-6c2bf1ddf40c\",\"vereq\":null,\"veres\":{\"version\":\"1.0.2\",\"url\":\"https://3dss.extranet.netcetera.biz/3dss-demo/3ds1/acs/authenticate\",\"protocol\":[\"ThreeDSecure\"],\"extension\":[\"<Extension critical=\\\"false\\\" id=\\\"MasterCard Tokenization\\\"/>\"],\"ireq\":null,\"ch\":{\"enrolled\":\"N\",\"acctID\":\"cXtjNFncWVxUOb54S7K0rA\",\"expiry\":null}},\"pareq\":null,\"error\":null,\"pares\":null,\"any\":[]}]},\"sessionData\":null,\"error\":null}";
    
    resp = resp.replaceAll("message","Message");
    resp = resp.replaceAll("veres","VERes");
    resp = resp.replaceAll("ch","CH");
    
    Gson gson = new Gson();
    PaReqResponseObject paResponse = gson.fromJson(resp, PaReqResponseObject.class);
    System.out.println("paResponse :"+ paResponse.toJSON());
    
       // Part of the ENROLL = "N" Dev                
       List<Message> messageList = paResponse.getThreeDSecureVERes().getMessage();
       int count = 0;
       for (Message m : messageList){
           System.out.println("Count :"+count);
           String enrolled = m.getVERes().getCH().getEnrolled();
           System.out.println("Enrolled :"+ enrolled);
           count++;
       }
       
       //Message m = messageList.get(0);
       //String enrolled = m.getVERes().getCH().getEnrolled();    
    
    
    }
}
