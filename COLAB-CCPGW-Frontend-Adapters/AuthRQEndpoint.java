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
import com.truteq.ccpgw.frontend.adapter.sabre.api.enums.ThreeDSStep;
import com.truteq.ccpgw.payment.gateway.adapter.SabrePaymentGatewayHandler;
import com.truteq.ccpgw.payment.gateway.api.soap.AuthRQ;
import com.truteq.ccpgw.payment.gateway.api.soap.AuthRS;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentDetailType;
import com.truteq.ccpgw.payment.gateway.model.AuthRSObject;
import java.util.List;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
//==============================================================================
// SOAP payload
//==============================================================================
//<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
//				  xmlns="https://ipgw.testbsp.com.pg/sabre">
//   <soapenv:Header/>
//   <soapenv:Body>
//        <AuthRQ xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" Version="1.0" MerchantAccountCode="AAECOMM">
//        <POS CountryCode="US" ChannelID="WEB" IP_Address="123.123.123.123">
//            <BrowserDetail>
//                <BrowserJavaEnabled>true</BrowserJavaEnabled>
//                <BrowserJavaScriptEnabled>true</BrowserJavaScriptEnabled>
//                <BrowserScreenColorDepth>32</BrowserScreenColorDepth>
//                <BrowserScreenHeight>500</BrowserScreenHeight>
//                <BrowserScreenWidth>300</BrowserScreenWidth>
//                <BrowserTimeZoneOffset>-5:00</BrowserTimeZoneOffset>
//                <ChallengeWindowSize>03</ChallengeWindowSize>
//                <HttpHeaders>
//                    <HttpHeader Name="host">ctovm2251.dev.sabre.com:8080</HttpHeader>
//                    <HttpHeader Name="connection">keep-alive</HttpHeader>
//                    <HttpHeader Name="content-length">273</HttpHeader>
//                    <HttpHeader Name="cache-control">max-age=0</HttpHeader>
//                    <HttpHeader Name="accept">text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8</HttpHeader>
//                    <HttpHeader Name="origin">http://ctovm2251.dev.sabre.com:8080</HttpHeader>
//                    <HttpHeader Name="user-agent">Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36</HttpHeader>
//                    <HttpHeader Name="content-type">application/x-www-form-urlencoded</HttpHeader>
//                    <HttpHeader Name="referer">http://ctovm2251.dev.sabre.com:8080/pwsair/payment</HttpHeader>
//                    <HttpHeader Name="accept-encoding">gzip,deflate</HttpHeader>
//                    <HttpHeader Name="accept-language">en-US,en;q=0.8,de;q=0.6</HttpHeader>
//                    <HttpHeader Name="cookie">JSESSIONID=BE6CDD7C4A8D3EFA840EB6A8A5ED2E2E;__test=1; TCSESSIONID=E4017C6CB1E56718B28FC62D823A623E;RememberMe=1902358934^22#-2000813221030363862</HttpHeader>
//                </HttpHeaders>
//            </BrowserDetail>
//        </POS>
//        <OrderDetail OrderNumber="ABC123456" RecordLocator="ABCDEF">
//            <ProductDetail ProductSummaryIndicator="2">
//                <ProductDetailItems>
//                    <ProductDetailItem ProductID="0001" ProductName="Air Ticket" />
//                    <ProductDetailItem ProductID="1004" ProductName="Baggage" />
//                </ProductDetailItems>
//            </ProductDetail>
//            <PassengerDetail NameInPNR="SMITH/JOHN MR" FirstName="JOHN" LastName="SMITH" PsgrType="ADT" Gender="M" />
//            <PassengerDetail NameInPNR="SMITH/JANE MRS" FirstName="JANE" LastName="SMITH" PsgrType="ADT" Gender="F" />
//            <FlightDetail>
//                <AirlineCode>AA</AirlineCode>
//                <FlightNumber>12</FlightNumber>
//                <ClassOfService>F</ClassOfService>
//                <DepartureInfo DepartureAirport="DFW" DepartureDateTime="2015-12- 17T09:30:00" />
//                <ArrivalInfo ArrivalAirport="LAX" ArrivalDateTime="2015-12- 17T11:30:00" />
//            </FlightDetail>
//            <FlightDetail ReturnFlightInd="true">
//                <AirlineCode>AA</AirlineCode>
//                <FlightNumber>147</FlightNumber>
//                <ClassOfService>F</ClassOfService>
//                <DepartureInfo DepartureAirport="LAX" DepartureDateTime="2015-12- 20T14:10:00" />
//                <ArrivalInfo ArrivalAirport="DFW" ArrivalDateTime="2001-12- 20T19:50:00" />
//            </FlightDetail>
//        </OrderDetail>
//        <PaymentDetail>
//            <PaymentMethod PaymentMethodCode="VI" />
//            <PaymentCard CardNumber="4444XXXXXXXX1111" CVC="123" ExpireDate="122025" CardHolderName="JOHN C SMITH" ReadyFor3DSVersion="2.2" />
//            <BillingAddress>
//                <AddressLine1>3150 Sabre Drive</AddressLine1>
//                <CityName>Southlake</CityName>
//                <PostalCode>76092</PostalCode>
//                <StateProv StateCode="TX" />
//                <Country Code="US" />
//            </BillingAddress>
//            <AmountDetail Amount="425.88" CurrencyCode="USD" />
//            <InstallmentDetail NumberOfInstallments="6" PromotionalInd="true" />
//        </PaymentDetail>
//        <CustomerDetail FirstName="John" LastName="Smith" DOB="1967-08-13" Gender="M">
//            <EmailAddress>jsmith@company.com</EmailAddress>
//            <PhoneNumber Number="9999999999" Type="M" />
//        </CustomerDetail>
//        </AuthRQ>
//   </soapenv:Body>
//</soapenv:Envelope> 
//==============================================================================
@Endpoint
public class AuthRQEndpoint {

    private final LogWrapper mLogger = new LogWrapper(AuthRQEndpoint.class);

    @Value(value = "${ipgw.frontend.apdapter.authrs}")
    private String authrsType;

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
    @Value("${security.threeDS.version}")
    private String threeDSversion; 
    @Value("${transaction.manager.3ds.v2.service.tds.result.response.read}")
    private String threeDSResultResponse;
    

    private static final String NAMESPACE_URI = "https://ipgw.testbsp.com.pg/sabre";

    private boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }
    
    private AuthRSObject process3DSStepOne(SabrePaymentGatewayHandler psphandler,
                                           String orderNumber, 
                                           String transactionId, 
                                           PaymentDetailType paymentType
                                           ){
        switch (threeDSversion) {
            case "1.0":
                return psphandler.process3DSStepOneVersion1(orderNumber, transactionId, paymentType);
            case "2.0":
                return psphandler.process3DSStepOneVersion2x(orderNumber, transactionId, paymentType);
            default:
                mLogger.error("3DS version is NOT correct! It must be set to either 1.0 or 2.0. It is currently set to: "+threeDSversion);
                return null;
        }
    }

    private AuthRS process3DSStepTwo(SabrePaymentGatewayHandler psphandler,
                                     String commandType,
                                     String orderNumber,
                                     String transactionId,
                                     PaymentDetailType paymentType){
        switch (threeDSversion) {
            case "1.0":
                return psphandler.process3DSStepTwoVersion1(commandType,orderNumber, transactionId, paymentType);
            case "2.0":
                return psphandler.process3DSStepTwoVersion2x(commandType,orderNumber, transactionId, paymentType);
            default:
                mLogger.error("3DS version is NOT correct! It must be set to either 1.0 or 2.0. It is currently set to: "+threeDSversion);
                return null;
        }        
    }
    

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthRQ")
    @ResponsePayload
    public AuthRS getAuth(@RequestPayload AuthRQ request) {

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
                threeDSResultResponse);

        ThreeDSStep step = ThreeDSStep.STEP_ONE;

        String orderNumber = request.getOrderDetail().getOrderNumber();

        if (isAlphaNumeric(orderNumber)) {

            List<PaymentDetailType> paymentDetails = request.getPaymentDetail();

            for (PaymentDetailType paymentType : paymentDetails) {
                String paymentMethodCode = paymentType.getPaymentMethod().getPaymentMethodCode();

                if (paymentType.getPaymentCard().getT3DS() == null) {
                    step = ThreeDSStep.STEP_ONE;
                    mLogger.info("No T3DS: Step1.", new Throwable().getStackTrace()[0]);
                } else if (paymentType.getPaymentCard().getT3DS().getPAResponse() == null) {
                    step = ThreeDSStep.STEP_ONE;
                    mLogger.info("No T3DS: Step1.", new Throwable().getStackTrace()[0]);
                } else {
                    step = ThreeDSStep.STEP_TWO;
                    mLogger.info("T3DS found: Step2.", new Throwable().getStackTrace()[0]);
                }

                //String transactionId = UUID.randomUUID().toString();
                String transactionId = psphandler.GenerateTransactionID();

                mLogger.info("Order number: " + orderNumber, new Throwable().getStackTrace()[0]);
                mLogger.info("Transaction Id: " + transactionId, new Throwable().getStackTrace()[0]);

                String currCode = paymentType.getAmountDetail().getCurrencyCode();

                //psphandler.setCurrencyCode(currCode);
                psphandler.setMerchantCurrencyMap(request.getMerchantAccountCode(), 
                                                  currCode,
                                                  request.getPOS().getStationNumber());


                switch (step) {
                    case STEP_ONE:
                        AuthRSObject authrsObj = process3DSStepOne(psphandler, orderNumber, transactionId, paymentType);
                        return authrsObj.getAuthrs();
                        
//Removed the below code: Grant O'Reilly 2022-07-27
// The redirection takes place in the redirection mechanism not here for 3DSv2.x                        
//                        switch (authrsObj.getEnrollment().toUpperCase()) {
//                            case "Y":
//                                return authrsObj.getAuthrs();
//                            case "N":
//                                return psphandler.NonThreeDSTransaction("0100",authrsObj.getAuthrs(), paymentType);
//                        }
                    case STEP_TWO:
                        return process3DSStepTwo(psphandler,"0100",orderNumber, transactionId, paymentType);
                }
                mLogger.info("Payment method code: " + paymentMethodCode, new Throwable().getStackTrace()[0]);
            }

        }

        return null;
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

    /**
     * @return the threeDSResultResponse
     */
    public String getThreeDSResultResponse() {
        return threeDSResultResponse;
    }

    /**
     * @param threeDSResultResponse the threeDSResultResponse to set
     */
    public void setThreeDSResultResponse(String threeDSResultResponse) {
        this.threeDSResultResponse = threeDSResultResponse;
    }

}
