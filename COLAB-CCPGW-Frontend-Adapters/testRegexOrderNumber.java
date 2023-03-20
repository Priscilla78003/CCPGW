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
public class testRegexOrderNumber {

    

    public boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }

    public static void main(String[] args) {
        String ordernumber = "< qqrdfas21151451";
        
        testRegexOrderNumber test = new testRegexOrderNumber(); 
        
        System.out.println(test.isAlphaNumeric(ordernumber));
    }
}
