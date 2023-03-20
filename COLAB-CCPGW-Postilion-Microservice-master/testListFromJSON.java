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
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.truteq.ccpgw.transaction.manager.model.RepeatElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testListFromJSON {
   public static void main(String[] args) {
       String data = "[{\"messageType\":\"0221\",\"primaryAccountNumber\":\"4123440000000200\",\"processingCode\":\"000000\",\"transactionAmount\":\"1000\",\"systemTraceAuditNumber\":\"000275\",\"expiryDate\":\"2212\",\"posConditionCode\":\"59\",\"acquiringInstitutionCode\":\"70130200000\",\"retrievalRefNumber\":\"000825076283\",\"terminalId\":\"401PGK05\",\"posDataCode\":\"660550600001190\",\"merchantType\":\"4511\",\"posEntryMode\":\"010\",\"merchantId\":\"4001PGK00000005\",\"cardAcceptorNameLocation\":\"BSP MERCHANT PGK       POM            PG\",\"currencyCode\":\"598\"},{\"messageType\":\"0220\",\"primaryAccountNumber\":\"4123440000000200\",\"processingCode\":\"\",\"transactionAmount\":\"1000\",\"systemTraceAuditNumber\":\"12345\",\"posConditionCode\":\"59\",\"acquiringInstitutionCode\":\"70130200000\",\"terminalId\":\"401PGK05\",\"posDataCode\":\"660550600001190\",\"merchantType\":\"4511\",\"posEntryMode\":\"010\",\"merchantId\":\"4001PGK00000005\",\"cardAcceptorNameLocation\":\"BSP MERCHANT PGK       POM            PG\",\"originalDataElements\":\"010006090511300000236013020000000000000000\"}]";
       Gson gson = new Gson();
       
       Type listType = new TypeToken<ArrayList<RepeatElement>>(){}.getType();
       List<RepeatElement> list = new Gson().fromJson(data, listType);
       
       //List<Object> list = gson.fromJson(data,List.class);
       
       
       for (Object obj : list){
           RepeatElement  element = (RepeatElement) obj;
           System.out.println(element.toJSON());
       }
        
       
   } 
}
