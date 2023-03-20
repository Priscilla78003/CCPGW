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
package com.truteq.ccpgw.authentication.authorisation.server.springoauth.controller;

import com.google.gson.Gson;
import com.truteq.ccpgw.authentication.authorisation.server.encryption.CredentialEncryption;
import com.truteq.ccpgw.model.authenticate.authorise.Credentials;
import com.truteq.ccpgw.model.authenticate.authorise.SessionObj;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Result;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public abstract class GenericAuthenticationAuthorisationController {
    //how to flip one java version to another and make it in sync with dockerfile
    //@Value(value = "${ipgw.api.server.hostname}")
    //protected String ipgwApiServerHostname;
    
    @Value(value = "${ccpgw.jsonfrontendadapter.server.hostname}")
    protected String ccpgwJsonFrontEndAdapterServerHostname;  

    @Value(value = "${token.url}")
    protected String keyCloakServerOpenIdConnectToken;  
    
    @Value(value = "${aa.client.id}")
    protected String aaClientId;  
    
    @Value(value = "${aa.client.secret}")
    protected String aaClientSecret;  

    /**
     * =========================================================================
     * This method is a utility method to decrypt credentials in the encrypted
     * key.
     *
     * @param encryptedKey
     * @return
     * @throws Exception
     * =========================================================================
     */
    Credentials getDecryptedCredentials(String encryptedKey) throws Exception {
        CredentialEncryption encrypt = new CredentialEncryption();
        String decryptedString = encrypt.DecryptAES256(encryptedKey);
        Gson gson = new Gson();
        Credentials cred = gson.fromJson(decryptedString, Credentials.class);
        return cred;
    }
    
    SessionObj getSessionObjFromString(String sessionObjStr) {
        Gson gson = new Gson();
        SessionObj sessionObj = gson.fromJson(sessionObjStr, SessionObj.class);
        return sessionObj;
    }

    Result getResultFromString(String sessionObjStr) {
        Gson gson = new Gson();
        Result result = gson.fromJson(sessionObjStr, Result.class);
        return result;
    } 

    
}
