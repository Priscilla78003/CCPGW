/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Postilion Adapter: POSTILION - Transaction Manager Adapter 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.adapter.postilion.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testTransmissionDateTime {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); 
        
        
        DateFormat dateFormat2 = new SimpleDateFormat("MMddHHmmss");
        System.out.println(dateFormat2.format(date));
        
        String systemTraceAuditNumber = "13378";
        
        while (systemTraceAuditNumber.length() < 6){
           systemTraceAuditNumber = "0"+systemTraceAuditNumber; 
        }
        System.out.println(systemTraceAuditNumber);
        
    }
}
