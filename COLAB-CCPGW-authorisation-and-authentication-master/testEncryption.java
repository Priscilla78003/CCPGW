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
package com.truteq.test.ccpgw.aa.encryption;

import com.truteq.ccpgw.authentication.authorisation.server.encryption.CredentialEncryption;
import com.truteq.ccpgw.model.authenticate.authorise.Credentials;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testEncryption {
    public static void main(String args[]) throws Exception {
        CredentialEncryption encrypt = new CredentialEncryption();
        
        Credentials cred = new Credentials("guest", "guest","9F0C94B7D186A411036E1F223D21E690");
        String encryptedString = encrypt.EncryptAES256(cred.toJSON());
        String decryptedString = encrypt.DecryptAES256(encryptedString);
        
        System.out.println("JSON Object           : "+cred.toJSON());
        System.out.println("Encrypted JSON Object : "+encryptedString);
        System.out.println("Decrypted JSON Object : "+decryptedString);
    }    
}
