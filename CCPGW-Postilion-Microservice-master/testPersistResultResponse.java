package com.truteq.ccpgw.transaction.manager.functional.tests;


import com.google.gson.Gson;
import com.truteq.ccpgw.threeds.v2.objects.results.response.ThreeDSServerResultsResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testPersistResultResponse {
 
    public static void main(String[] args) {
        String resultResponse = "{\"threeDSServerTransID\":\"47c4fdfd-d287-4bd9-987e-a692169f0df7\",\"transStatus\":\"Y\",\"authenticationValue\":\"AAIBBgYgU2dnk3NjYSBTAAAAAAA\\u003d\",\"eci\":\"05\",\"resultsRequest\":{\"threeDSServerTransID\":\"47c4fdfd-d287-4bd9-987e-a692169f0df7\",\"acsTransID\":\"d021d30f-4900-4ebb-8242-d193e4427f74\",\"authenticationType\":\"02\",\"authenticationValue\":\"AAIBBgYgU2dnk3NjYSBTAAAAAAA\\u003d\",\"dsTransID\":\"c20b743d-df69-49ca-8c56-5dfc2e2a6d64\",\"eci\":\"05\",\"messageCategory\":\"01\",\"messageType\":\"RReq\",\"messageVersion\":\"2.1.0\",\"transStatus\":\"Y\"},\"resultsResponse\":{\"threeDSServerTransID\":\"47c4fdfd-d287-4bd9-987e-a692169f0df7\",\"acsTransID\":\"d021d30f-4900-4ebb-8242-d193e4427f74\",\"dsTransID\":\"c20b743d-df69-49ca-8c56-5dfc2e2a6d64\",\"messageType\":\"RRes\",\"messageVersion\":\"2.1.0\",\"resultsStatus\":\"01\"}}";
        
        Gson gson = new Gson();
        ThreeDSServerResultsResponse rres = gson.fromJson(resultResponse,ThreeDSServerResultsResponse.class);
        
        
        System.out.println(rres.getThreeDSServerTransID());
        System.out.println(rres.getResultsResponse().getAcsTransID());
        System.out.println(rres.getResultsResponse().getDsTransID());
        System.out.println(rres.getResultsResponse().getMessageType());
        System.out.println(rres.getResultsResponse().getMessageVersion());
        System.out.println(rres.getResultsResponse().getResultsStatus());
        
        
        System.out.println(rres.getResultsRequest().getAcsTransID());
        if (rres.getResultsRequest().getAcsRenderingType() != null){
            System.out.println(rres.getResultsRequest().getAcsRenderingType().getAcsInterface());
            System.out.println(rres.getResultsRequest().getAcsRenderingType().getAcsUiTemplate());
        }
        System.out.println(rres.getResultsRequest().getAuthenticationMethod());
        System.out.println(rres.getResultsRequest().getAuthenticationType());
        System.out.println(rres.getResultsRequest().getAuthenticationValue());
        System.out.println(rres.getResultsRequest().getDsTransID());
        System.out.println(rres.getResultsRequest().getEci());
        System.out.println(rres.getResultsRequest().getInteractionCounte());
        System.out.println(rres.getResultsRequest().getMessageCategory());
        System.out.println(rres.getResultsRequest().getMessageType());
        System.out.println(rres.getResultsRequest().getMessageVersion());
        System.out.println(rres.getResultsRequest().getTransStatus());        
        
        
    }    
    
}
