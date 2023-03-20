/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Communication server
 * ***************************************************************
 * Used to communicate with different adapters 
 * Support: Grant O'Reilly gbo@truteq.com OR grant@platformpac.com.pg
 * V01.00.00  29-Jum-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.test.util;

import com.google.gson.Gson;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseRequest;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testStringtoJSON {
    
    public static void main(String[] args){
        String data = "{\"primaryAccountNumber\":\"4896780109909073\",\"processingCode\":\"000000\",\"transactionAmount\":\"000000012300\",\"accountExpiryDate\":\"2306\",\"merchantType\":\"6300\",\"posEntryMode\":\"010\",\"posConditionCode\":\"59\",\"acquiringInstitutionCode\":\"60130200000\",\"terminalId\":\"423PGK11\",\"merchantId\":\"4023PGK00000011\",\"cardAcceptorNameLocation\":\"PHA Health Assurance LiPOM            PG\",\"currencyCodeTransaction\":\"598\",\"posDataCode\":\"660550600001192\",\"cvv2\":\"532\",\"threeDSecureResult\":\"2\"}";
        
        Gson gson = new Gson();
        AuthoriseRequest authReq = gson.fromJson(data, AuthoriseRequest.class);
        System.out.println(authReq.getPrimaryAccountNumber());
    }
            
}
