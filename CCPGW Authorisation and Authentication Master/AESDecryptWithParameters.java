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

import java.security.spec.KeySpec;
import java.util.Arrays;
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
//==============================================================================
// The AESDecryptWithParameters works with the Javascript side:
//    
//    let userId = 'd41cd772-cb57-412c-a864-6e40b2bd3e12';
//    let secretPhrase = 'P@55word';
//    let salt = 'password';
//    let aesKey = CryptoJS.PBKDF2(secretPhrase.toString(), salt, {
//        keySize: 128 / 32, iterations: 65536
//    });
//    let iv = CryptoJS.enc.Utf8.parse(userId.slice(0, 16));
//    let aesOptions = { mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7, iv: iv };
//    let encode = CryptoJS.AES.encrypt(state.cardNumber, aesKey, aesOptions).toString();
//    let decode = CryptoJS.AES.decrypt(encode, aesKey, aesOptions).toString(CryptoJS.enc.Utf8);
//==============================================================================

public class AESDecryptWithParameters{

    private Cipher decryptCipher = null;   
    
    public AESDecryptWithParameters(String keyPassword, String salt, byte[] iv) throws Exception {
        decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        IvParameterSpec ivspec = new IvParameterSpec(iv);
         
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(keyPassword.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
         
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);        
    }  


    public String Decode(String stringToDecode) throws Exception  {
        return new String(decryptCipher.doFinal(Base64.getDecoder().decode(stringToDecode)));
    }
    
}
