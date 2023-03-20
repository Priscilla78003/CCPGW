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
import com.truteq.ccpgw.payment.gateway.api.soap.CaptureRQ;
import com.truteq.ccpgw.payment.gateway.api.soap.CaptureRS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
//==============================================================================
// SOAP payload for Capture request
//==============================================================================
//<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns="https://ipgw.testbsp.com.pg/sabre">
//   <soapenv:Header/>
//     <soapenv:Body>
//        <CaptureRQ xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" Version="1.0" MerchantAccountCode="AAECOMM">
//            <OriginalTransaction TransactionID="123456789012345"/>
//            <CaptureDetail>
//                <PassengerDetail NameInPNR="SMITH/JOHN MR" FirstName="JOHN" LastName="SMITH" PsgrType="ADT" Gender="M">
//                 <Document DocType="TKT" DocNumber="0011234567890"/>
//                </PassengerDetail>
//                <PassengerDetail NameInPNR="SMITH/JANE MRS" FirstName="JANE" LastName="SMITH" PsgrType="ADT" Gender="F">
//                 <Document DocType="TKT" DocNumber="0011234567891"/>
//                </PassengerDetail>
//                <FlightDetail>
//                    <AirlineCode>AA</AirlineCode>
//                    <FlightNumber>12</FlightNumber>
//                    <ClassOfService>F</ClassOfService>
//                    <DepartureInfo DepartureAirport="DFW" DepartureDateTime="2015-12-17T09:30:00"/>
//                    <ArrivalInfo ArrivalAirport="LAX" ArrivalDateTime="2015-12-17T11:30:00"/>
//                </FlightDetail>
//                <FlightDetail ReturnFlightInd="true">
//                    <AirlineCode>AA</AirlineCode>
//                    <FlightNumber>147</FlightNumber>
//                    <ClassOfService>F</ClassOfService>
//                    <DepartureInfo DepartureAirport="LAX" DepartureDateTime="2015-12-20T14:10:00"/>
//                    <ArrivalInfo ArrivalAirport="DFW" ArrivalDateTime="2001-12-20T19:50:00"/>
//                </FlightDetail>
//                <CaptureAmount Amount="250.00" CurrencyCode="USD"/>
//            </CaptureDetail>
//        </CaptureRQ>
//   </soapenv:Body>
//</soapenv:Envelope> 
//==============================================================================
@Endpoint
public class CaptureRQEndpoint {

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

    private final LogWrapper mLogger = new LogWrapper(CaptureRQEndpoint.class);

    private static final String NAMESPACE_URI = "https://ipgw.testbsp.com.pg/sabre";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CaptureRQ")
    @ResponsePayload
    public CaptureRS getCapture(@RequestPayload CaptureRQ request) {

        mLogger.info("Processing Capture request.", new Throwable().getStackTrace()[0]);

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

        String currCode = request.getCaptureDetail().getCaptureAmount().getCurrencyCode();

        psphandler.setCurrencyCode(currCode);
        psphandler.setMerchantCurrencyMap(request.getMerchantAccountCode(),
                                          currCode);

        CaptureRS response = psphandler.postilionCapture(request.getOriginalTransaction().getTransactionID(), // transactionId,
                request.getCaptureDetail().getCaptureAmount().getAmount().toString(),
                request.getMerchantAccountCode(), // 30-Jul-2021 Mya 
                request.getCaptureDetail().getCaptureAmount().getCurrencyCode());  // transactionAmount,

        return response;
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
