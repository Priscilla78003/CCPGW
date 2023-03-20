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

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AESEncryptorRijndael implements IEncryptor{
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;   
    
    public AESEncryptorRijndael(String keyPassword) throws Exception {
        encryptCipher = Cipher.getInstance("Rijndael");
        decryptCipher = Cipher.getInstance("Rijndael");
        
        // create a binary key from the argument key (seed)
        SecureRandom sr = new SecureRandom(keyPassword.getBytes());
        KeyGenerator kg = KeyGenerator.getInstance("Rijndael");
        kg.init(sr);
        SecretKey secretKey = kg.generateKey();        
        
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