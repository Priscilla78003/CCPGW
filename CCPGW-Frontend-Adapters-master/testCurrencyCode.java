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

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testCurrencyCode {
    public static void main(String[] args) {
        String code1 = "36";
        String code2 = "598";
        
        
        if (code1.length()<3)
                code1 = "0"+code1;
        if (code2.length()<3)
                code2 = "0"+code2;
        
        System.out.println(code1);
        System.out.println(code2);
    }
        
}
