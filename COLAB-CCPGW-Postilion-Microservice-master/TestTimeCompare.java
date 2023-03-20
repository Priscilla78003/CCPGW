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
package com.truteq.ccpgw.adapter.postilion.test;

import java.time.Instant;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestTimeCompare {

    public static void main(String[] args) {

//        Instant start = Instant.now();
//        try {
//                Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//        } 
//        Instant end = Instant.now();
//        
//        
//        long diff = end.getEpochSecond() - start.getEpochSecond();
//        
//        System.out.println(diff);

    boolean response = false;
    boolean first = true;
    Instant start = Instant.now();
    while (!response) {
        if (first) {
            System.out.println("Waiting for Response......");
            first = false;
        }
        Instant end = Instant.now();
        long diff = end.getEpochSecond() - start.getEpochSecond();

        if (diff >= 10) {
            System.out.println("No response from Postilion after 10sec. Aborting response wait!");
            response = true;
        }
    }
    }  
}
