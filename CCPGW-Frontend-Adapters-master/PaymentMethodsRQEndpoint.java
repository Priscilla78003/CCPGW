/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.frontend.adapter.sabre.api.endpoints;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.frontend.adapter.sabre.api.enums.ResponseCodes;
import com.truteq.ccpgw.payment.gateway.api.soap.IssuerType;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentMethodsRQ;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentMethodsRS;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentMethodsRS.PaymentMethods;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentMethodsRS.PaymentMethods.PaymentMethod;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentMethodsRS.PaymentMethods.PaymentMethod.Issuers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

//==============================================================================
// SOAP payload for Payment Methods request
//==============================================================================
//<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="https://ipgw.testbsp.com.pg/sabre">
//   <soapenv:Header/>
//     <soapenv:Body>
//        <PaymentMethodsRQ xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" Version="1.0" MerchantAccountCode="AAECOMCC" CountryCode="US"/>
//   </soapenv:Body>
//</soapenv:Envelope> 
//==============================================================================
@Endpoint
public class PaymentMethodsRQEndpoint {
    
    private final LogWrapper mLogger = new LogWrapper(PaymentMethodsRQEndpoint.class);  
    
    private static final String NAMESPACE_URI = "https://ipgw.testbsp.com.pg/sabre";
    
    private Issuers buildIssuers(List<BankIssuer> banks){
        
        Issuers issuers = new Issuers();
        List<IssuerType> issuersList = issuers.getIssuer();
        
        for( BankIssuer b : banks){
            IssuerType issueType = new IssuerType();
            issueType.setIssuerID(b.getId());
            issueType.setIssuerName(b.getName());
            issuersList.add(issueType);            
        }
      
        return issuers;
        
    }
    
    private class BankIssuer {
        
        private String id;
        private String name;
        
        public BankIssuer(){
            
        }
        
        public BankIssuer(String id, String name){
            this();
            this.id = id;
            this.name = name;
        }    

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
        
    }
    
    private PaymentMethod buildPaymentMethod(com.truteq.ccpgw.frontend.adapter.sabre.api.enums.PaymentMethods code,
                                             List<BankIssuer> banks){
        
        PaymentMethod payMethod = new PaymentMethod();
        switch(code){
            case VI : payMethod.setPaymentMethodCode("VI"); break;
            case CA : payMethod.setPaymentMethodCode("CA"); break;
        }
        
        payMethod.setIssuers(buildIssuers(banks));
        
        return payMethod;
        
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PaymentMethodsRQ")
    @ResponsePayload
    public PaymentMethodsRS getPaymentMethodsRQ(@RequestPayload PaymentMethodsRQ request) {
        
        mLogger.info("Processing Payment Methods request.", new Throwable().getStackTrace()[0]);
        
        
        PaymentMethodsRS response = new PaymentMethodsRS();
        response.setVersion("1.0");
        response.setResponseCode(Integer.toString(ResponseCodes.Authorized_Captured_Received));
        response.setDescription("Successful");    
        
        PaymentMethods payMethods = new PaymentMethods();
        List<PaymentMethod> payMethodList = payMethods.getPaymentMethod();
        
        
        List<BankIssuer> banks = new ArrayList<>();
        banks.add(new BankIssuer("BANK001","Bank of South Pacific"));
        banks.add(new BankIssuer("BANK002","National Bank"));
        
        payMethodList.add(buildPaymentMethod(com.truteq.ccpgw.frontend.adapter.sabre.api.enums.PaymentMethods.VI,banks));
        
        
        response.setPaymentMethods(payMethods);
        
        
        return response;        
        
        
    }    
    
    
}