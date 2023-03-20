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
package com.truteq.encryptor;

import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AES256Encryptor implements IEncryptor
{
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;   
    
    public AES256Encryptor(String keyPassword, String salt) throws Exception {
        encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        IvParameterSpec ivspec = new IvParameterSpec(iv);
         
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(keyPassword.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
         
        
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);        
    }  
    @Override
    public String Encode(String stringToEncode) throws Exception {
        return Base64.getEncoder().encodeToString(encryptCipher.doFinal(stringToEncode.getBytes("UTF-8")));
    }

    @Override
    public String Decode(String stringToDecode) throws Exception {
        return new String(decryptCipher.doFinal(Base64.getDecoder().decode(stringToDecode)));
    }
    
}
