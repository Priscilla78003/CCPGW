/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com 
            Mya OO         mya@platformpac.pg
 * V01.00.00  09-Jul-2021 
 * ***************************************************************
 */
package com.truteq.general.util;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author mho
 */
public class HashAString {

    
    private static final LogWrapper mLogger = new LogWrapper(HashAString.class);

    public static String hash(String strToHash)
    {
        try {

            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashed = sha.digest(strToHash.getBytes());
            sha.reset();
            
            return hashed.toString();
        }
        catch (NoSuchAlgorithmException ex){
            mLogger.info("Hash unsuccessful .... " + ex.getMessage());
        }

        return "";
    }

    static private String hexEncode( byte[] input){
    
        StringBuffer result = new StringBuffer();
    
        char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
        for (int idx = 0; idx < input.length; ++idx) {
            byte b = input[idx];
            result.append( digits[ (b&0xf0) >> 4 ] );
            result.append( digits[ b&0x0f] );
        }
        return result.toString();
    }  

    
}
