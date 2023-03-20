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
public class testPosDataCode {
    
    public static void main(String[] args) {
        String posDataCode = "660550600001192";
        
        String subPosDataCode = posDataCode.substring(0, 13);
        
        System.out.println(subPosDataCode);
        System.out.println(subPosDataCode+"92");
    }
    
    
}
