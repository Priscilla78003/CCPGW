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
public class testAVD {
    
    public static void main(String[] args) {
        String postalcode = "4556";
        String addressline1 = "19";
        String addressline2 = "Cumberland Way";
        String city = "Buderim";
        String state = "Queensland";
        String country = "Australia";
         
        StringBuilder avd = new StringBuilder();
        
        System.out.println(postalcode);
        
         int padding = 9 - postalcode.length();  
         
         for (int i = 1; i<=padding; i++){
            postalcode = postalcode+" "; 
         }
         
         System.out.println(postalcode+"#");
         
         avd.append(postalcode);
         avd.append(addressline1).append(" ");
         avd.append(addressline2).append(" ");
         avd.append(city).append(" ");
         avd.append(state).append(" ");
         avd.append(country).append(" ");
         
         
         System.out.println(avd.toString());
         System.out.println(avd.toString().substring(0,29));
    }
}
