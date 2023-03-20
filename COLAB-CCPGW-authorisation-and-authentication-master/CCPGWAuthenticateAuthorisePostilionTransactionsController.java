/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Mya Htike Oo mho@platformpac.com.pg
 * V01.00.00  01-Dec-2021
 * ***************************************************************
 */
package com.truteq.ccpgw.authentication.authorisation.server.springoauth.controller;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.model.authenticate.authorise.EncryptedCard;
import com.truteq.microservice.security.access.MicroServiceHandler;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mho
 * 
 * @author Grant Blaise O'Reilly <gbo@truteq.com>  
 */

//financial : hostname+"/ccpgwjsonadapter/postilion/financial",
@RestController
public class CCPGWAuthenticateAuthorisePostilionTransactionsController extends GenericAuthenticationAuthorisationController {

    private final LogWrapper mLogger = new LogWrapper(CCPGWAuthenticateAuthorisePostilionTransactionsController.class);

    @Autowired
    private HttpSession httpSession;

    @PostMapping(path = "/keycloak/ccpgw/authenticate/authorise/postilion/financial")
    @CrossOrigin(origins = "*")
    public String doFinancial(@RequestBody EncryptedCard cardpayment) throws IOException {

        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/postilion/financial",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                "ccpgwuser",
                "ccpgwu53r");
        
        return msh.callMicroService(cardpayment.toJSON().getBytes());

    }
}
