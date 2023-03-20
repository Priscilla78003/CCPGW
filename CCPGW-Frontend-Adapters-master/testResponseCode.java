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

import java.util.Date;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testResponseCode {
    
    private static String GenerateTransactionID(){
        
        Date date = new Date();
        return date.getTime()+"";
        
    }
    
    public static void main(String[] args) {
        String resp1 = "00";
        String resp2 = "04";
        String resp3 = "10";

        Integer resp1Val = Integer.parseInt(resp1);
        Integer resp2Val = Integer.parseInt(resp2);
        Integer resp3Val = Integer.parseInt(resp3);
        
        System.out.println(resp1Val);
        System.out.println(resp2Val);
        System.out.println(resp3Val);
        
        System.out.println(GenerateTransactionID());
        
    }
}
