/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * ***************************************************************
 */
package com.truteq.ccpgw.encryptor;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AESEncryptor implements IEncryptor{
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;   
    
    public AESEncryptor(String keyPassword) throws Exception {
        encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        byte[] key = keyPassword.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); 
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);        
    }  
    @Override
    public String Encode(String stringToEncode) throws Exception  {
        return Base64.getEncoder().encodeToString(encryptCipher.doFinal(stringToEncode.getBytes("UTF-8")));
    }

    @Override
    public String Decode(String stringToDecode) throws Exception  {
        return new String(decryptCipher.doFinal(Base64.getDecoder().decode(stringToDecode)));
    }
    
}
