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

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class DESEncryptor implements IEncryptor {
    
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;   
    
    public DESEncryptor(String keyPassword) throws Exception {
        encryptCipher = Cipher.getInstance("DES");
        decryptCipher = Cipher.getInstance("DES");
        
        DESKeySpec key = new DESKeySpec(keyPassword.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
        SecretKey skey = keyFactory.generateSecret(key);
        
        encryptCipher.init(Cipher.ENCRYPT_MODE, skey);
        decryptCipher.init(Cipher.DECRYPT_MODE, skey);        
    }    
    /**
     * Encrypt a string using DES encryption, and return the encrypted
     * string as a base64 encoded string.
     * 
     * @param stringToEncode The string to encrypt.
     * @return String The DES encrypted and base 64 encoded string.
     * @throws  Exception If an error occurs.
     */
    @Override
    public String Encode(String stringToEncode) throws Exception  {
        // Encode the string into bytes using utf-8
        byte[] unencryptedByteArray = stringToEncode.getBytes("UTF8");

        // Encrypt
        byte[] encryptedBytes = encryptCipher.doFinal(unencryptedByteArray);

        // Encode bytes to base64 to get a string
        byte [] encodedBytes = Base64.encodeBase64(encryptedBytes);

        return new String(encodedBytes);
    }

    /**
     * Decrypt a base64 encoded, DES encrypted string and return
     * the unencrypted string.
     * 
     * @param stringToDecode  The base64 encoded string to decrypt.
     * @return  String The decrypted string.
     * @throws Exception  If an error occurs.
     */
    @Override
    public String Decode(String stringToDecode) throws Exception  {
        // Encode bytes to base64 to get a string
        byte [] decodedBytes = Base64.decodeBase64(stringToDecode.getBytes());

        // Decrypt
        byte[] unencryptedByteArray = decryptCipher.doFinal(decodedBytes);

        // Decode using utf-8
        return new String(unencryptedByteArray, "UTF8");
    }
 
    
}
