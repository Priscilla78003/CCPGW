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


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestField127_22 {
    
    public String createStructure(String tag, String tagValue){
        String taglength = Integer.toString(tag.length());
        String taglengthIndicator = Integer.toString(taglength.length());
        String tagValuelength = Integer.toString(tagValue.length());
        String tagValuelengthIndicator = Integer.toString(tagValuelength.length()); 
        
        StringBuilder builder = new StringBuilder();
        builder.append(taglengthIndicator)
               .append(taglength)
               .append(tag)
               .append(tagValuelengthIndicator)
               .append(tagValuelength)
               .append(tagValue);
        return builder.toString();
    }
    
    public String frontPadding (String stringtopad, int length){
        String paddedString = stringtopad;
        
        while (paddedString.length() < length){
                paddedString = "0"+paddedString;
        }       
        
        return paddedString; 
    }
    
    public static void main(String[] args) {
        
        String output = "000962173D_SECURE_VERSION111231DIRECTORY_SERVER_TRANSACTION_ID236abcdefghijhlmnopqrstuvwxyz1234565789";
        
        String tag1 = "3D_SECURE_VERSION";
        String tag2 = "DIRECTORY_SERVER_TRANSACTION_ID";
        String tag1Value = "1";
        String tag2Value = "abcdefghijhlmnopqrstuvwxyz1234565789";
        
        TestField127_22 test = new TestField127_22();
        
        String structure1 = test.createStructure(tag1, tag1Value);
        String structure2 = test.createStructure(tag2, tag2Value);
       
        String DE127_22_struct = structure1+structure2;

        String DE127_22 = test.frontPadding(Integer.toString(DE127_22_struct.length()),5)+DE127_22_struct;
        
        System.out.println("Required output : "+output);
        
        System.out.println("Processed output: "+DE127_22);
        
        System.out.println("Processed output length: "+DE127_22.length());
    }
}
