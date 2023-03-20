/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.authentication.authorisation.server.springoauth.controller;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.model.authenticate.authorise.MerchantOrder;
import com.truteq.ccpgw.model.authenticate.authorise.OrderObj;
import com.truteq.ccpgw.model.authenticate.authorise.TempCard;
import com.truteq.ccpgw.model.objects.Merchant;
import com.truteq.microservice.security.access.MicroServiceHandler;
import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mho
 * 
 * @author Grant Blaise O'Reilly <gbo@truteq.com>  
 */


@RestController
public class CCPGWAuthenticateAuthorizeCardController extends GenericAuthenticationAuthorisationController {

    private final LogWrapper mLogger = new LogWrapper(CCPGWAuthenticateAuthorizeCardController.class);



    @Autowired
    private HttpSession httpSession;
    
    //getMerchant: hostname+'/ccpgwjsonadapter/merchant/read'
    @PostMapping("/keycloak/ccpgw/authenticate/authorise/merchant/read")
    @CrossOrigin(origins = "*")
    public String getMerchant(@RequestBody Merchant merchant) throws IOException {

        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/merchant/read",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                "ccpgwuser",
                "ccpgwu53r");

        return msh.callMicroService(merchant.toJSON().getBytes());
    }
    
    
    //getMerchantOrder: hostname + "/ccpgwjsonadapter/order/read/merchantorder"
    @PostMapping(path = "/keycloak/ccpgw/authenticate/authorise/order/read/merchantorder")
    @CrossOrigin(origins = "*")
    public String getMerchantOrder(@RequestBody MerchantOrder mOrder) throws Exception {

        
        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/order/read/merchantorder",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                "ccpgwuser",
                "ccpgwu53r");

        return msh.callMicroService(mOrder.toJSON().getBytes());
     
    }    
    
    //getTempCard: hostname+'/ccpgwjsonadapter/tempcard/read',
    @PostMapping(path = "/keycloak/ccpgw/authenticate/authorise/tempcard/read", consumes = "application/json")
    @CrossOrigin(origins = "*")
    public String getCard(@RequestBody TempCard theCard) throws IOException {

        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/tempcard/read",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                "ccpgwuser",
                "ccpgwu53r");

        return msh.callMicroService(theCard.toJSON().getBytes());
    }

    //deleteTempCard: hostname+'/ccpgwjsonadapter/tempcard/delete/',
    @PostMapping(path = "/keycloak/ccpgw/authenticate/authorise/tempcard/delete", consumes = "application/json")
    @CrossOrigin(origins = "*")
    public void deleteCard(@RequestBody TempCard theCard) throws IOException {

       mLogger.info("tempcard sessionid = " + theCard.getSessionid()); 
    
        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/tempcard/delete",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                "ccpgwuser",
                "ccpgwu53r");

        msh.callMicroService(theCard.toJSON().getBytes());

    }
    
    
    @PostMapping(path = "/keycloak/ccpgw/authenticate/authorise/order/payment")
    @CrossOrigin(origins = "*")
    public ResponseEntity createOrder(@RequestParam("order_id") String orderId,
            @RequestParam("merchant_key") String merchantKey,
            @RequestParam("order_details") String orderDetails) throws IOException {
        
        
        mLogger.info("Reached createOrder, orderId: "+orderId);
        mLogger.info("Merchant Key: "+merchantKey);
        mLogger.info("Order Details: "+orderDetails);

        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/order/payment",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                "ccpgwuser",
                "ccpgwu53r");

        OrderObj orderObj = new OrderObj(orderId, merchantKey, orderDetails);

        
        mLogger.info("Call to: "+ccpgwJsonFrontEndAdapterServerHostname + "/order/payment");
        mLogger.info("Payload: "+orderObj.toJSON());
        
        String URL = msh.callMicroService(orderObj.toJSON().getBytes());
        
        mLogger.info("Return of callMicroService: "+URL);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(URL));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
    
    @PostMapping(path = "/keycloak/ccpgw/authenticate/authorise/order/object/payment")
    @CrossOrigin(origins = "*")
    public ResponseEntity createOrderbyObj(@RequestBody OrderObj orderObj) throws IOException {
        
        
        mLogger.info("Reached createOrder, orderId: "+orderObj.getOrder_id());
        mLogger.info("Merchant Key: "+orderObj.getMerchant_key());
        mLogger.info("Order Details: "+orderObj.getOrder_detail());

        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/order/payment",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                "ccpgwuser",
                "ccpgwu53r");
        
        mLogger.info("Call to: "+ccpgwJsonFrontEndAdapterServerHostname + "/order/payment");
        mLogger.info("Payload: "+orderObj.toJSON());
        
        String URL = msh.callMicroService(orderObj.toJSON().getBytes());
        
        mLogger.info("Return of callMicroService: "+URL);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(URL));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }    
    

}
