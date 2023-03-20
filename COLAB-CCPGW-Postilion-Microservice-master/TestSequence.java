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

import com.truteq.ccpgw.adapter.postilion.utils.Sequence;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestSequence {
    public static void main(String[] args) {
        Sequence mPostilionAuditSequence = new Sequence("config/sequenceSetting.conf");
        
        //mPostilionAuditSequence.start();
        
        System.out.println("Current count = "+mPostilionAuditSequence.getCurrentCount());
        
        System.out.println("Next count = "+mPostilionAuditSequence.next());
        
        mPostilionAuditSequence.stop();
        
    }
}
