/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */

package com.truteq.ccpgw.authentication.authorisation.server.encryption;

import com.truteq.ccpgw.encryptor.AES256Encryptor;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class CredentialEncryption {
    
    //Generate the secret key
    //String password = "PlatformPAC-IPGW-AES256-key";
    String password = "PlatformPAC";
    String salt = getDateString();  
    AES256Encryptor aes256Encryptor = null;
    
    public CredentialEncryption() throws Exception{
        aes256Encryptor = new AES256Encryptor (password,salt);
    }
    
    private String getDateString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return dateFormat.format(date);
    } 

    public String EncryptAES256(String input) throws Exception{

        return this.aes256Encryptor.Encode(input);
    
    }
    
    public String DecryptAES256(String encrptedString) throws Exception{
            
        return this.aes256Encryptor.Decode(encrptedString);
      
    }
    
}
