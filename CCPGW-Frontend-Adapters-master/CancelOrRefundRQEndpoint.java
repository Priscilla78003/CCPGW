/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * V01.00.01  30-Jul-2021 Mya 
 * ***************************************************************
 */
package com.truteq.ccpgw.frontend.adapter.sabre.api.endpoints;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.payment.gateway.adapter.PaymentGatewayHandler;
import com.truteq.ccpgw.payment.gateway.adapter.SabrePaymentGatewayHandler;
import com.truteq.ccpgw.payment.gateway.api.soap.CancelOrRefundRQ;
import com.truteq.ccpgw.payment.gateway.api.soap.CancelOrRefundRS;
import com.truteq.ccpgw.transaction.manager.model.CaptureElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.truteq.general.util.HashAString;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

//==============================================================================
// SOAP payload for Cancel or Refund request
//==============================================================================
//<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
//				  xmlns="https://ipgw.testbsp.com.pg/sabre">
//   <soapenv:Header/>
//   <soapenv:Body>
//        <CancelOrRefundRQ xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" Version="2.3.0" MerchantAccountCode="AAECOMM" RequestID="123456789012001">
//            <OriginalTransaction TransactionID="123456789012345"/>
//            <AmountDetail Amount="250.00" CurrencyCode="USD"/>
//        </CancelOrRefundRQ>
//   </soapenv:Body>
//</soapenv:Envelope> 
//==============================================================================


//==============================================================================
// SOAP payload for Refund request
//==============================================================================
//<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
//				  xmlns="https://ipgw.testbsp.com.pg/sabre">
//   <soapenv:Header/>
//   <soapenv:Body>
//        <CancelOrRefundRQ xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" Version="2.3.0" MerchantAccountCode="AAECOMM">
//            <OriginalTransaction TransactionID="123456789012345" RequestID="123456789012002"/>
//            <AmountDetail Amount="200.00" CurrencyCode="USD"/>
//            <RefundDetail>
//                <PaymentCard CardNumber="…" ExpireDate="122016"/>
//                <Documents>
//                    <Document DocType="EMD" DocNumber="0011234567893"/>
//                </Documents>
//            </RefundDetail>
//        </CancelOrRefundRQ>
//   </soapenv:Body>
//</soapenv:Envelope> 
//==============================================================================

@Endpoint
public class CancelOrRefundRQEndpoint {
    
    @Value(value = "${transaction.manager.db.write.originaldata}")
    private String originaldataWrite;
    @Value(value = "${transaction.manager.db.read.originaldata}")
    private String originaldataRead;
    @Value(value = "${transaction.manager.db.write.authorisation}")
    private String authorisationWrite;    
    @Value(value = "${transaction.manager.db.read.authorisation}")
    private String authorisationRead;
    @Value(value = "${transaction.manager.db.write.capture}")
    private String captureWrite;    
    @Value(value = "${transaction.manager.db.read.capture}")
    private String captureRead;    
    @Value(value = "${transaction.manager.3ds.v1.createpareq}")
    private String threedsCreateReq;
    @Value(value = "${transaction.manager.3ds.v1.validatepares}")
    private String threedsValidatePaRes;
    @Value(value = "${transaction.manager.3ds.v2.versioning}")
    private String threedsV2Versioning;
    @Value(value = "${transaction.manager.3ds.v2.authorise}")
    private String threedsV2Authorise;    
    @Value(value = "${transaction.manager.3ds.v2.authorise.request.read}")
    private String threedsV2AuthReqRead; 
    @Value(value = "${transaction.manager.3ds.v2.authorise.request.write}")
    private String threedsV2AuthReqWrite;
    @Value(value = "${transaction.manager.3ds.v2.enrolled.write}")
    private String threedsV2EnrolledWrite;       
    @Value(value = "${transaction.manager.3ds.v1.acsurl}")    
    private String acsurl;    
    @Value(value = "${transaction.manager.3ds.v1.useservlet.proxy}")
    private String servletProxy;    
    @Value(value = "${transaction.manager.db.read.acspareq}")
    private String acsParerqWrite;          
    @Value(value = "${postilion.restful.service.authorise}")  
    private String postilionAuthorise;
    @Value(value = "${postilion.restful.service.refund}")
    private String postilionRefund;
    @Value(value = "${postilion.restful.service.capture}")
    private String postilionCapture;
    @Value(value = "${postilion.restful.service.reversal}")
    private String postilionReversal;
    @Value(value = "${merchant.config.cardAcceptorNameLocation}")
    private String cardAcceptorNameLocation;
    @Value(value = "${merchant.config.acquiringInstitutionCode.postilion}")   
    private String acquiringInstitutionCodePostilion;
    @Value(value = "${merchant.config.merchantId}")
    private String merchantId;    
    @Value(value = "${merchant.config.acquiringInstitutionCode.visa}")   
    private String acquiringInstitutionCodeVisa;
    @Value(value = "${merchant.config.acquiringInstitutionCode.mastercard}")   
    private String acquiringInstitutionCodeMastercard; 
    @Value(value = "${merchant.config.posDataCode}")
    private String posDataCode;
    @Value(value = "${merchant.config.categoryCode}")
    private String categoryCode;
    @Value(value = "${merchant.config.currencyCode}")
    private String currencyCode;
    @Value(value = "${debug}")
    private boolean debug;
    @Value("${communicator.user.name}")
    private String communicatorUserName;
    @Value("${communicator.password}")
    private String communicatorPassword;
    @Value("${communicator.secret}")
    private String secret;    
    @Value("${transaction.manager.db.read.merchantcurrencymap}")
    private String merchantCurrencyMapRead;
    @Value("${transaction.manager.certificate}")
    private String keystore;
    @Value("${transaction.manager.platformpac.keystore.password}")
    private String keystorepasswordplatformpac;
    @Value("${merchant.config.countryCode}")
    private String countryCode;
    @Value("${merchant.config.merchantCode}")
    private String merchantCode;       
    @Value("${merchant.config.merchantType}")
    private String merchantType;
    @Value("${transaction.manager.3ds.v2.service.tds.result.response.read}")
    private String threeDSResultResponse;
    @Value("${threeDS.issuer.url}")
    private String threeDSIssuerURL;  

    
    @Value("${transaction.manager.db.write.transaction.detail}")
    private String transactionDetailsWrite;
    @Value("${transaction.manager.db.read.transaction.detail}")
    private String transactionDetailsRead;       
    
    private final LogWrapper mLogger = new LogWrapper(CancelOrRefundRQEndpoint.class);  
    
    private static final String NAMESPACE_URI = "https://ipgw.testbsp.com.pg/sabre";
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CancelOrRefundRQ")
    @ResponsePayload
    public CancelOrRefundRS getCancelOrRefund(@RequestPayload CancelOrRefundRQ request) {
        
        mLogger.info("Processing CancelOrRefund request.", new Throwable().getStackTrace()[0]);
        
        
        SabrePaymentGatewayHandler psphandler = new SabrePaymentGatewayHandler(originaldataWrite,
                                                                     originaldataRead,
                                                                     authorisationWrite,
                                                                     authorisationRead,
                                                                     captureWrite,
                                                                     captureRead, 
                                                                     threedsCreateReq,
                                                                     threedsValidatePaRes,
                                                                     threedsV2Versioning,
                                                                     threedsV2Authorise,
                                                                     threedsV2AuthReqRead,
                                                                     threedsV2AuthReqWrite,
                                                                     threedsV2EnrolledWrite,
                                                                     acsurl,
                                                                     servletProxy,  
                                                                     acsParerqWrite,
                                                                     postilionAuthorise,
                                                                     postilionRefund,
                                                                     postilionCapture,
                                                                     postilionReversal,
                                                                     cardAcceptorNameLocation,
                                                                     acquiringInstitutionCodePostilion,
                                                                     merchantId,
                                                                     acquiringInstitutionCodeVisa,
                                                                     acquiringInstitutionCodeMastercard,
                                                                     posDataCode,
                                                                     categoryCode,
                                                                     currencyCode,
                                                                     debug,
                                                                     communicatorUserName,
                                                                     communicatorPassword,
                                                                     secret,
                                                                     merchantCurrencyMapRead,
                                                                     keystore,
                                                                     keystorepasswordplatformpac,
                                                                     countryCode,
                                                                     merchantCode,
                                                                     merchantType,
                                                                     threeDSResultResponse,
                                                                     threeDSIssuerURL,
                                                                     transactionDetailsWrite,
                                                                     transactionDetailsRead);   
        
        
        CaptureElement captureElement =  psphandler.readCapture(request.getOriginalTransaction().getTransactionID());
        
        if (captureElement != null){
            mLogger.debug("Capture Element: "+captureElement.toJSON(), new Throwable().getStackTrace()[0]);
        } else{
            mLogger.debug("No Capture Element found.", new Throwable().getStackTrace()[0]);
        }
        
        if (captureElement != null){
        
        //if (request.getRefundDetail()!=null){
             // Add the code for processing a REFUND here
            //String card = request.getRefundDetail().getPaymentCard().getCardNumber();
            
            String card = captureElement.getPrimaryAccountNumber();
            
            String currCode = request.getAmountDetail().getCurrencyCode();
            
            psphandler.setCurrencyCode(currCode);
            psphandler.setMerchantCurrencyMap(request.getMerchantAccountCode(), 
                                              currCode);
            
            int lastIndex = card.length();
            String cardFrontDigits = card.substring(0,3);
            String cardBackDigits = card.substring(lastIndex-3, lastIndex);

             mLogger.info("Refund request for card: "+ cardFrontDigits + "##########" + cardBackDigits + "(" + HashAString.hash(card) + ")", new Throwable().getStackTrace()[0]);
             CancelOrRefundRS response = psphandler.postilionRefund(captureElement.getPrimaryAccountNumber(),//request.getRefundDetail().getPaymentCard().getCardNumber(),
                                  request.getAmountDetail().getAmount().toString(),
                                  captureElement.getExpiryDate(),//request.getRefundDetail().getPaymentCard().getExpireDate(), 
                                  request.getOriginalTransaction().getTransactionID(), 
                                  request.getMerchantAccountCode(),
                                  request.getAmountDetail().getCurrencyCode());
                                          
            return response;                              
             
        }
        else{
            // Add the code for processing a CANCEL here

            mLogger.info("Cancel request [This is a reversal].", new Throwable().getStackTrace()[0]);
            
            String currCode = request.getAmountDetail().getCurrencyCode();
            
            mLogger.info("Currency code received from Sabre: "+currCode, new Throwable().getStackTrace()[0]);
            psphandler.setCurrencyCode(currCode);            
            psphandler.setMerchantCurrencyMap(request.getMerchantAccountCode(), 
                                              currCode);
            
            CancelOrRefundRS response = psphandler.postilionReversal(request.getOriginalTransaction().getTransactionID(),
                                                                     request.getAmountDetail().getAmount().toString(),
                                                                     request.getMerchantAccountCode(),
                                                                     request.getAmountDetail().getCurrencyCode() 
                                                                     );
            
            return response;
        }
       
    }    

    /**
     * @return the threedsV2AuthReqRead
     */
    public String getThreedsV2AuthReqRead() {
        return threedsV2AuthReqRead;
    }

    /**
     * @param threedsV2AuthReqRead the threedsV2AuthReqRead to set
     */
    public void setThreedsV2AuthReqRead(String threedsV2AuthReqRead) {
        this.threedsV2AuthReqRead = threedsV2AuthReqRead;
    }

    /**
     * @return the threedsV2AuthReqWrite
     */
    public String getThreedsV2AuthReqWrite() {
        return threedsV2AuthReqWrite;
    }

    /**
     * @param threedsV2AuthReqWrite the threedsV2AuthReqWrite to set
     */
    public void setThreedsV2AuthReqWrite(String threedsV2AuthReqWrite) {
        this.threedsV2AuthReqWrite = threedsV2AuthReqWrite;
    }
    
    
}
