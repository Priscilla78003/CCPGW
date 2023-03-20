/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.frontend.adapter.json.api.controller;

import com.truteq.ccpgw.frontend.adapter.json.database.controller.MerchantData;
import com.google.gson.Gson;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.payment.gateway.adapter.BSPPaymentGatewayHandler;
import com.truteq.ccpgw.payment.gateway.api.json.CResObject;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentDetailType;
import com.truteq.ccpgw.payment.gateway.enums.ThreeDSStep;
import com.truteq.ccpgw.payment.gateway.api.json.EncryptedCard;
import com.truteq.ccpgw.payment.gateway.api.json.Merchant;
import com.truteq.ccpgw.payment.gateway.api.json.MerchantOrder;
import com.truteq.ccpgw.payment.gateway.api.json.Order;
import com.truteq.ccpgw.payment.gateway.api.json.PaResNotify;
import com.truteq.ccpgw.payment.gateway.api.json.PaymentDetails;
import com.truteq.ccpgw.payment.gateway.api.json.Refund;
import com.truteq.ccpgw.payment.gateway.api.json.TempCard;
import com.truteq.ccpgw.payment.gateway.api.json.TransactionFullDetails;
import com.truteq.ccpgw.payment.gateway.api.soap.AddressType;
import com.truteq.ccpgw.payment.gateway.api.soap.AddressType.StateProv;
import com.truteq.ccpgw.payment.gateway.api.soap.AuthRQ;
import com.truteq.ccpgw.payment.gateway.api.soap.AuthRS;
import com.truteq.ccpgw.payment.gateway.api.soap.CancelOrRefundRS;
import com.truteq.ccpgw.payment.gateway.api.soap.CountryNameType;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentMethodType;
import com.truteq.ccpgw.payment.gateway.model.AuthRSObject;
import com.truteq.ccpgw.payment.gateway.util.DataEncryption;
import com.truteq.ccpgw.payment.gateway.util.Decryption;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import com.truteq.ccpgw.transaction.manager.merchant.portal.authenticate.authorise.EncryptionSession;
import com.truteq.ccpgw.transaction.manager.model.TransactionDetails;
import com.truteq.general.util.AESEncryptionDecryption;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * 
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@RestController
@RequestMapping("/ccpgwjsonadapter/")
public class FinancialRequestController {

    private final LogWrapper mLogger = new LogWrapper(FinancialRequestController.class);

    private MerchantData mData = new MerchantData();

    @Value(value = "${transaction.manager.db.write.originaldata.financial}")
    private String originaldataWrite;
    @Value(value = "${transaction.manager.db.read.financial}")
    private String financialRead;
    @Value(value = "${transaction.manager.db.write.financial}")
    private String financialWrite;    
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
    @Value(value = "${transaction.manager.db.read.acspareq}")
    private String acsParerqWrite;
    @Value(value = "${postilion.restful.service.financial}")
    private String postilionFinancial;
    @Value(value = "${merchant.config.cardAcceptorNameLocation}")
    private String cardAcceptorNameLocation;
    @Value(value = "${merchant.config.acquiringInstitutionCode}")
    private String acquiringInstitutionCode;
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
    @Value("${transaction.manager.db.read.merchantcurrencymap.bybusinessnameandcurrency}")
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
    @Value("${transaction.manager.db.read.merchant.rsaprivatekey}")
    private String merchantReadPrivateKey;
    @Value("${transaction.manager.db.read.merchantorder}")
    private String readMerchantOrder;
    @Value("${transaction.manager.db.write.tempcard}")
    private String tempcardWrite;
    @Value("${transaction.manager.db.update.order}")
    private String updateOrder;
    @Value("${security.threeDS.version}")
    private String threeDSversion;
    @Value("${transaction.manager.3ds.v2.service.tds.result.response.read}")
    private String threeDSResultResponse;
    @Value("${threeDS.issuer.url}")
    private String threeDSIssuerURL;       
    
    @Value("${transaction.manager.db.write.transaction.detail}")
    private String transactionDetailsWrite;
    @Value("${transaction.manager.db.read.transaction.detail}")
    private String transactionDetailsRead;        
    
    @Value( value = "${postilion.restful.service.refund}")
    private String postilionRefund;

    private AESEncryptionDecryption decryptor;
    private String decryptedkeystorepasswordplatformpac;
    
    private boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }    
    
    
    private AuthRSObject process3DSStepOne(BSPPaymentGatewayHandler psphandler,
                                           String orderNumber, 
                                           String transactionId, 
                                           PaymentDetailType paymentType,
                                           String secretkey
                                           ){
        switch (threeDSversion) {
            case "1.0":
                return psphandler.process3DSStepOneVersion1(orderNumber, transactionId, paymentType);
            case "2.0":
                return psphandler.process3DSStepOneVersion2x(orderNumber, transactionId, paymentType, secretkey);
            default:
                mLogger.error("3DS version is NOT correct! It must be set to either 1.0 or 2.0. It is currently set to: "+threeDSversion);
                return null;
        }
    }

    private AuthRS process3DSStepTwo(BSPPaymentGatewayHandler psphandler,
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
    
    private TransactionFullDetails ReadTransactionDetailsFromDB(String threeDSServerTransID){
        
        mLogger.info("Read transaction detail from database for threeDSServerTransID " + threeDSServerTransID);
        decryptor = new AESEncryptionDecryption();
        decryptedkeystorepasswordplatformpac = decryptor.decrypt(keystorepasswordplatformpac, secret);
        SSLCommunicator sslComms = new SSLCommunicator(keystore, decryptedkeystorepasswordplatformpac);
        
        TransactionDetails detailsIn = new TransactionDetails(threeDSServerTransID,
                                                            "",
                                                            "",
                                                            "", 
                                                            "");
        
        Result result = sslComms.sendHttpPost(transactionDetailsRead, detailsIn.toJSON().getBytes());
        
        Gson gson = new Gson();
        TransactionFullDetails detailsOut = gson.fromJson(result.getData(), TransactionFullDetails.class);
         
        String decryptedString = "";
        
        try {
            DataEncryption encrypt = new DataEncryption();
            
            decryptedString = encrypt.DecryptAES256(detailsOut.getPayload());
            
        } catch (Exception ex) {
            mLogger.error("Exception on decrypting payment details."+ex);
        }            
           
        
        if (!decryptedString.equals("")){
         PaymentDetails payDetail = gson.fromJson(decryptedString, PaymentDetails.class);  
         
         detailsOut.setPaymentDetails(payDetail);
        }
        
        return detailsOut;
    }

    
    @PostMapping(path = "/postilion/financial/step2", consumes = "application/json")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_PERFORM_FINANCIAL')")
    public AuthRS getFinancialStep2(@RequestBody PaResNotify paResNotify) throws Exception { 
        
        byte[] decodedBytes = Base64.getDecoder().decode(paResNotify.getPaRes());
        String decodedString = new String(decodedBytes);
        mLogger.info("PaRes decoded: "+decodedString);
        mLogger.info("MD: "+paResNotify.getMd());
        Gson gson = new Gson();
        CResObject cres = gson.fromJson(decodedString, CResObject.class);
        
        TransactionFullDetails transactionDetails = ReadTransactionDetailsFromDB(cres.getThreeDSServerTransID());
        
        mLogger.info("========================================================");
        mLogger.info("Parameters for STEP 2 processing .....");
        mLogger.info("========================================================");
        mLogger.info("Order id: "+transactionDetails.getOrder_id());
        mLogger.info("Transaction id: "+transactionDetails.getTransaction_id());
        mLogger.info("ThreeDSServerTransID: "+transactionDetails.getThreeDSServerTransID());
        mLogger.info("PaymentDetails: "+transactionDetails.getPaymentDetails().toJSON());
        mLogger.info("PaRes: "+paResNotify.getPaRes()); 
        mLogger.info("MD: "+paResNotify.getMd()); 
        mLogger.info("========================================================");
        
        MerchantOrder mOrder = new MerchantOrder();
        mOrder.setOrder_id(transactionDetails.getOrder_id());
        mOrder.setSecretKey(transactionDetails.getSecretKey());
        MerchantOrder merchantOrder = getMerchantOrder(mOrder);
        

        PaymentDetailType paymentType = new PaymentDetailType();
        PaymentDetailType.PaymentCard paymentCard = new PaymentDetailType.PaymentCard();
        
        paymentCard.setCVC(transactionDetails.getPaymentDetails().getCvv());
        paymentCard.setCardHolderName(transactionDetails.getPaymentDetails().getName());
        paymentCard.setCardNumber(transactionDetails.getPaymentDetails().getNumber());
        paymentCard.setExpireDate(transactionDetails.getPaymentDetails().getExpiryDate());
        
        PaymentDetailType.PaymentCard.T3DS t3ds = new PaymentDetailType.PaymentCard.T3DS();
        t3ds.setPAResponse(paResNotify.getPaRes());
        t3ds.setMD(paResNotify.getMd());
        paymentCard.setT3DS(t3ds);
        paymentType.setPaymentCard(paymentCard);
        
        PaymentDetailType.AmountDetail amtDetail = new PaymentDetailType.AmountDetail();
        amtDetail.setAmount(transactionDetails.getPaymentDetails().getAmount());
        amtDetail.setCurrencyCode(transactionDetails.getPaymentDetails().getCurrency());
        paymentType.setAmountDetail(amtDetail);
        
        AddressType addressType = new AddressType();
        addressType.setAddressLine1(acsurl);
                
        addressType.setCityName(transactionDetails.getPaymentDetails().getCity());
        CountryNameType countryNameType = new CountryNameType();
        countryNameType.setName(transactionDetails.getPaymentDetails().getCountryNam());
        countryNameType.setCode(transactionDetails.getPaymentDetails().getCountryCode());
        addressType.setCountry(countryNameType);
        StateProv stateProv = new StateProv();
        stateProv.setName(transactionDetails.getPaymentDetails().getStateProv());
        addressType.setStateProv(stateProv);
        addressType.setPostalCode(transactionDetails.getPaymentDetails().getPostalCode());
        paymentType.setBillingAddress(addressType);
        
        PaymentMethodType paymentMethodType = new PaymentMethodType();
        paymentMethodType.setPaymentMethodCode(transactionDetails.getPaymentDetails().getPaymentMethodCode());
        paymentType.setPaymentMethod(paymentMethodType);
        
        BSPPaymentGatewayHandler psphandler = new BSPPaymentGatewayHandler(originaldataWrite,
                financialWrite,
                financialRead,
                threedsCreateReq,
                threedsValidatePaRes,
                threedsV2Versioning,
                threedsV2Authorise,
                threedsV2AuthReqRead,
                threedsV2AuthReqWrite,
                threedsV2EnrolledWrite,
                acsurl,
                acsParerqWrite,
                postilionFinancial,
                postilionRefund,
                cardAcceptorNameLocation,
                acquiringInstitutionCode,
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
                updateOrder,
                threeDSResultResponse,
                threeDSIssuerURL,
                transactionDetailsWrite,
                transactionDetailsRead); 
        
        psphandler.setMerchantCurrencyMap(merchantOrder.getBusinessName(), 
                                          paymentType.getAmountDetail().getCurrencyCode());//the businessname value in merchant table and the merchant_name value in merchant_currency_map table must be exactly the same

        
        return process3DSStepTwo(psphandler,
                                 "0200", 
                                 transactionDetails.getOrder_id(), 
                                 transactionDetails.getTransaction_id(), 
                                 paymentType);
       
    }   
    
    
    @PostMapping(path = "postilion/financial", consumes = "application/json")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_PERFORM_FINANCIAL')")
    public AuthRS getFinancial(@RequestBody EncryptedCard cardpayment) throws Exception {

        Decryption decrypt = new Decryption();
        String privateKey = this.getMerchantPrivateKey(cardpayment.getGuid());
        AuthRQ request = decrypt.decryptPayload(cardpayment, privateKey);
        String sessionId = UUID.randomUUID().toString();
        request.setSessionid(sessionId);

        mLogger.info("Processing Financial request for card: " + request.getPaymentDetail().get(0).getPaymentCard().getCardNumber());

        BSPPaymentGatewayHandler psphandler = new BSPPaymentGatewayHandler(originaldataWrite,
                financialWrite,
                financialRead,
                threedsCreateReq,
                threedsValidatePaRes,
                threedsV2Versioning,
                threedsV2Authorise,
                threedsV2AuthReqRead,
                threedsV2AuthReqWrite,
                threedsV2EnrolledWrite,
                acsurl,
                acsParerqWrite,
                postilionFinancial,
                postilionRefund,
                cardAcceptorNameLocation,
                acquiringInstitutionCode,
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
                updateOrder,
                threeDSResultResponse,
                threeDSIssuerURL,
                transactionDetailsWrite,
                transactionDetailsRead);

        
        ThreeDSStep step = ThreeDSStep.STEP_ONE;

        String orderNumber = request.getOrderDetail().getOrderNumber();
        
        if (isAlphaNumeric(orderNumber)) {
                  
            List<PaymentDetailType> paymentDetails = request.getPaymentDetail();
            
            MerchantOrder mOrder = new MerchantOrder();
            mOrder.setOrder_id(orderNumber);
            mOrder.setSecretKey(cardpayment.getSecretkey());
            MerchantOrder merchantOrder = getMerchantOrder(mOrder);

            for (PaymentDetailType paymentType : paymentDetails) {
                String paymentMethodCode = paymentType.getPaymentMethod().getPaymentMethodCode();

                PaymentDetailType.AmountDetail amtDetail = new PaymentDetailType.AmountDetail();
                amtDetail.setAmount(new BigDecimal(merchantOrder.getAmount()));
                amtDetail.setCurrencyCode(merchantOrder.getCurrency());
                paymentType.setAmountDetail(amtDetail);
                
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

                String transactionId = psphandler.GenerateTransactionID();

                mLogger.info("Order number: " + orderNumber, new Throwable().getStackTrace()[0]);
                mLogger.info("Transaction Id: " + transactionId, new Throwable().getStackTrace()[0]);

                String currCode = paymentType.getAmountDetail().getCurrencyCode();
                
//                psphandler.setMerchantCurrencyMap(request.getMerchantAccountCode(), 
//                                                  currCode,
//                                                  request.getPOS().getStationNumber());                

                psphandler.setCurrencyCode(currCode);
                psphandler.setMerchantCurrencyMap(merchantOrder.getBusinessName(), currCode);//the businessname value in merchant table and the merchant_name value in merchant_currency_map table must be exactly the same
                
                
                switch (step) {
                    case STEP_ONE:
                        AuthRSObject authrsObj = process3DSStepOne(psphandler, orderNumber, transactionId, paymentType, mOrder.getSecretKey());
                        mLogger.debug("STEP ONE SUCCESSFUL" + authrsObj.toJSON());
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
                        return process3DSStepTwo(psphandler,"0200",orderNumber, transactionId, paymentType);
                }
                mLogger.info("Payment method code: " + paymentMethodCode, new Throwable().getStackTrace()[0]);
            }
            
        }

        return null;
        
    }
//    @PostMapping(path = "postilion/financial", consumes = "application/json")
//    @CrossOrigin(origins = "*")
//    @PreAuthorize("hasAnyAuthority('ROLE_PERFORM_FINANCIAL')")
//    public AuthRS getFinancial(@RequestBody EncryptedCard cardpayment) throws Exception {
//
//        Decryption decrypt = new Decryption();
//        String privateKey = this.getMerchantPrivateKey(cardpayment.getGuid());
//        AuthRQ request = decrypt.decryptPayload(cardpayment, privateKey);
//        String sessionId = UUID.randomUUID().toString();
//        request.setSessionid(sessionId);
//
//        mLogger.info("Processing Financial request for card: " + request.getPaymentDetail().get(0).getPaymentCard().getCardNumber());
//
//        BSPPaymentGatewayHandler psphandler = new BSPPaymentGatewayHandler(originaldataWrite,
//                financialWrite,
//                financialRead,
//                threedsCreateReq,
//                threedsValidatePaRes,
//                threedsV2Versioning,
//                threedsV2Authorise,
//                threedsV2AuthReqRead,
//                threedsV2AuthReqWrite,
//                threedsV2EnrolledWrite,
//                acsurl,
//                acsParerqWrite,
//                postilionFinancial,
//                cardAcceptorNameLocation,
//                acquiringInstitutionCode,
//                merchantId,
//                acquiringInstitutionCodeVisa,
//                acquiringInstitutionCodeMastercard,
//                posDataCode,
//                categoryCode,
//                currencyCode,
//                debug,
//                communicatorUserName,
//                communicatorPassword,
//                secret,
//                merchantCurrencyMapRead,
//                keystore,
//                keystorepasswordplatformpac,
//                countryCode,
//                merchantCode,
//                merchantType,
//                updateOrder,
//                threeDSResultResponse,
//                threeDSIssuerURL);
//
//        ThreeDSStep step = ThreeDSStep.STEP_ONE;
//
//        String orderNumber = request.getOrderNumber();
//        
//        List<PaymentDetailType> paymentDetails = request.getPaymentDetail();
//        MerchantOrder mOrder = new MerchantOrder();
//        mOrder.setOrder_id(orderNumber);
//        mOrder.setSecretKey(cardpayment.getSecretkey());
//        MerchantOrder merchantOrder = getMerchantOrder(mOrder);
//
//        for (PaymentDetailType paymentType : paymentDetails) {
//            String paymentMethodCode = paymentType.getPaymentMethod().getPaymentMethodCode();
//
//            PaymentDetailType.AmountDetail amtDetail = new PaymentDetailType.AmountDetail();
//            amtDetail.setAmount(new BigDecimal(merchantOrder.getAmount()));
//            amtDetail.setCurrencyCode(merchantOrder.getCurrency());
//            paymentType.setAmountDetail(amtDetail);
//            if (paymentType.getPaymentCard().getT3DS() == null) {
//                step = ThreeDSStep.STEP_ONE;
//                mLogger.info("No T3DS: Step1.");
//            } else if (paymentType.getPaymentCard().getT3DS().getPAResponse() == null) {
//                step = ThreeDSStep.STEP_ONE;
//                mLogger.info("No T3DS: Step1.");
//            } else {
//                step = ThreeDSStep.STEP_TWO;
//                mLogger.info("T3DS found: Step2.");
//                mLogger.info("paresponse in financialRequestController: " + paymentType.getPaymentCard().getT3DS().getPAResponse());
//            }
//
//            String transactionId = psphandler.GenerateTransactionID();
//
//            mLogger.info("Order number: " + orderNumber);
//            mLogger.info("Transaction Id: " + transactionId);
//
//            String currCode = paymentType.getAmountDetail().getCurrencyCode();
//
//            psphandler.setCurrencyCode(currCode);
//            psphandler.setMerchantCurrencyMap(merchantOrder.getBusinessName(), currCode);//the businessname value in merchant table and the merchant_name value in merchant_currency_map table must be exactly the same
//            switch (step) {
//                case STEP_ONE:
//                    AuthRSObject authrsObj = process3DSStepOne(psphandler, orderNumber, transactionId, paymentType);
//                    mLogger.debug("STEP ONE SUCCESSFUL:: " + authrsObj.toJSON());
//
//                    
//                    
//                    switch (authrsObj.getEnrollment().toUpperCase()) {
//                        case "Y": {
//                            createCard(request, authrsObj.getAuthrs().getT3DSResult().getPARequest(), cardpayment.getSecretkey());
//                            AuthRS response = authrsObj.getAuthrs();
//                            response.setSessionid(sessionId);
//                            return response;
//                        }
//                        case "N":
//                            createCard(request, "", cardpayment.getSecretkey());
//                            AuthRS response = authrsObj.getAuthrs();
//                            response.setSessionid(sessionId);
//                            //return psphandler.NonThreeDSTransactionFinancial(authrsObj.getAuthrs(), paymentType);
//                            return psphandler.NonThreeDSTransaction("0200", response, paymentType);
//                    }
//
//                case STEP_TWO:
//                    //return psphandler.process3DSStepTwoFinancial(orderNumber, transactionId, paymentType);
//                    return process3DSStepTwo(psphandler, "0200", orderNumber, transactionId, paymentType);
//            }
//
//            mLogger.info("Payment method code: " + paymentMethodCode);
//
//        }
//
//        return null;
//    }
    
    
    @PostMapping(path = "order/read/merchantorder")//this is the last call from ui to redirect to the merchant; this is also used to fill in the orders after financial is called as AuthRequest in financial does not have order data originally
    @CrossOrigin(origins = "*")
    public MerchantOrder getMerchantOrder(@RequestBody MerchantOrder mOrder) throws Exception {

        mLogger.info("in ccpgwjsonadapter/order/read/merchantorder:: " + mOrder.getOrder_id());
        decryptor = new AESEncryptionDecryption();
        decryptedkeystorepasswordplatformpac = decryptor.decrypt(keystorepasswordplatformpac, secret);
        SSLCommunicator sslComms = new SSLCommunicator(keystore, decryptedkeystorepasswordplatformpac);
        Result result = sslComms.sendHttpPost(readMerchantOrder, mOrder.toJSON().getBytes());
        mLogger.info("Order duplicate check result getMerchantOrder:: " + result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        Gson gson = new Gson();
        MerchantOrder order = gson.fromJson(result.getData(), MerchantOrder.class); //call backend service (MerchantOrder) databaseDao.findById("ccpgw.api.read.merchant.orders", MerchantOrder.class, new Integer(orderId));

        HashMap<String, String> map = new HashMap<>();
        map.put("order_id", mOrder.getOrder_id());
        map.put("order_key", order.getTransaction_id());
        map.put("status_success", "success");
        map.put("status_cancel", "cancel");
        map.put("status_fail", "fail");

        mLogger.debug("order.getOrder_id() " + order.getOrder_id());
        mLogger.debug("order.getTransaction_id() " + order.getTransaction_id());
        mLogger.debug("order.getSuccessurl() " + order.getSuccessurl());
        mLogger.debug("order.getCallback_url() " + order.getCallback_url());

        String newSuccesURL = ParametersInsertIntoString(order.getSuccessurl(), map);
        order.setSuccessurl(newSuccesURL);

        order.setSuccessurl(order.getCallback_url() + "&status=success");
        order.setFailurl(order.getCallback_url() + "&status=failed");

        return order;
    }    
    

    @PostMapping(path = "postilion/refund", consumes = "application/json")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_PERFORM_FINANCIAL')")
    public CancelOrRefundRS processRefund(@RequestBody EncryptionSession encryptedSession) throws Exception {

        Decryption decrypt = new Decryption();
        String privateKey = this.getMerchantPrivateKey(encryptedSession.getGuid());
        Refund request = decrypt.decryptRefundPayload(encryptedSession, privateKey);

        mLogger.info("Processing Refund request for transaction ID: " + request.getTransactionId());

        
        BSPPaymentGatewayHandler psphandler = new BSPPaymentGatewayHandler(
                originaldataWrite,
                financialWrite,
                financialRead,
                threedsCreateReq,
                threedsValidatePaRes,
                threedsV2Versioning,
                threedsV2Authorise,
                threedsV2AuthReqRead,
                threedsV2AuthReqWrite,
                threedsV2EnrolledWrite,
                acsurl,
                acsParerqWrite,
                postilionFinancial,
                postilionRefund,
                cardAcceptorNameLocation,
                acquiringInstitutionCode,
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
                updateOrder,
                threeDSResultResponse,
                threeDSIssuerURL,
                transactionDetailsWrite,
                transactionDetailsRead);        
        
        CancelOrRefundRS response = psphandler.postilionJSONRefund(request.getPrimaryAccountNumber(),
                             request.getTransactionAmount(),
                             request.getAccountExpiryDate(),
                             request.getTransactionId(), 
                             request.getMerchantName(),
                             request.getCurrency());

        Integer responseCode = new Integer(response.getResponseCode());
        
        if(responseCode == 00){
            mLogger.info("Update order status for order id = " + request.getOrderId(), new Throwable().getStackTrace()[0]);
            
            Order order = new Order();
            order.setTransaction_ref(request.getTransactionId());
            order.setStatus_value("0");
            order.setStatus("success");
            order.setStatus_description("Refunded");
            order.setOrder_id(request.getOrderId());
            
            decryptor = new AESEncryptionDecryption();
            decryptedkeystorepasswordplatformpac = decryptor.decrypt(keystorepasswordplatformpac, secret);
            SSLCommunicator sslComms = new SSLCommunicator(keystore, decryptedkeystorepasswordplatformpac);
            Result result = sslComms.sendHttpPost(updateOrder, order.toJSON().getBytes());
            
            mLogger.info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
            
            Gson gson = new Gson();
            Order resultOrder = gson.fromJson(result.getData(), Order.class);
            
            mLogger.info(result.getComments() + "Order id= " + resultOrder.getOrder_id() + " status updated to: " + resultOrder.getStatus_description(), new Throwable().getStackTrace()[0]);
        }
        
        return response;
    }    
    
    public void createCard(AuthRQ request, String parequest, String merchant_key) {

        TempCard tempCard = new TempCard();
        tempCard.setName(request.getPaymentDetail().get(0).getPaymentCard().getCardHolderName());
        tempCard.setNumber(request.getPaymentDetail().get(0).getPaymentCard().getCardNumber());
        tempCard.setCvv(request.getPaymentDetail().get(0).getPaymentCard().getCVC());
        tempCard.setPayment_method_code(request.getPaymentDetail().get(0).getPaymentMethod().getPaymentMethodCode());
        tempCard.setExpiry_month(request.getPaymentDetail().get(0).getPaymentCard().getExpireDate().substring(0, 2));
        tempCard.setExpiry_year(request.getPaymentDetail().get(0).getPaymentCard().getExpireDate().substring(2, 4));
        tempCard.setParequest(parequest);
        tempCard.setSessionid(request.getSessionid());
        tempCard.setOrder_id(request.getOrderDetail().getOrderNumber());
        tempCard.setMerchant_key(request.getMerchantAccountCode());
        tempCard.setAddressline1(request.getPaymentDetail().get(0).getBillingAddress().getAddressLine1());
        tempCard.setAddressline2(request.getPaymentDetail().get(0).getBillingAddress().getAddressLine2());
        tempCard.setCity(request.getPaymentDetail().get(0).getBillingAddress().getCityName());
        tempCard.setState(request.getPaymentDetail().get(0).getBillingAddress().getStateProv().getName());
        tempCard.setCountry(request.getPaymentDetail().get(0).getBillingAddress().getCounty());
        tempCard.setPostal_code(request.getPaymentDetail().get(0).getBillingAddress().getPostalCode());
        mLogger.info("Saving card data to database: orderNumber + merchantKey = " + tempCard.getOrder_id() + "++" + tempCard.getMerchant_key(), new Throwable().getStackTrace()[0]);
        decryptor = new AESEncryptionDecryption();
        decryptedkeystorepasswordplatformpac = decryptor.decrypt(keystorepasswordplatformpac, secret);
        SSLCommunicator sslComms = new SSLCommunicator(keystore, decryptedkeystorepasswordplatformpac);
        sslComms.sendHttpPost(tempcardWrite, tempCard.toJSON().getBytes());
    }    
    
    
    private String getMerchantPrivateKey(String guid) {

        mLogger.info("Retrieving merchant private key: guid = " + guid, new Throwable().getStackTrace()[0]);
        Merchant merchant = new Merchant();
        merchant.setGuid(guid);
        decryptor = new AESEncryptionDecryption();
        decryptedkeystorepasswordplatformpac = decryptor.decrypt(keystorepasswordplatformpac, secret);
        SSLCommunicator sslComms = new SSLCommunicator(keystore, decryptedkeystorepasswordplatformpac);
        Result result = sslComms.sendHttpPost(merchantReadPrivateKey, merchant.toJSON().getBytes());
        mLogger.info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        Gson gson = new Gson();
        Merchant resultMerchant = gson.fromJson(result.getData(), Merchant.class);
        return resultMerchant.getRsaprivatekey();
    }
    
    
    
    private String ParametersInsertIntoString(String inputString, HashMap<String, String> map) {
        StringBuilder stringbuild = new StringBuilder();
        int count = 0;
        int paramcount = 0;
        int startCount = 0;

        ArrayList<String> params = getParameters(inputString);

        while (count < inputString.length()) {
            char val = inputString.charAt(count);
            switch (val) {
                case '[':
                    stringbuild.append(inputString.substring(startCount, count)).append(matchWithFromMap(params.get(paramcount), map));
                    paramcount++;
                    break;
                case ']':
                    startCount = count + 1;
                    break;
            }
            count++;
        }
        if (stringbuild.toString().equals("")) {
            return inputString;
        }

        return stringbuild.toString();
    }

    private ArrayList<String> getParameters(String inputString) {

        int count = 0;
        int beginIndex = 0;
        int endIndex = 0;
        ArrayList<String> params = new ArrayList<>();
        while (count < inputString.length()) {
            char val = inputString.charAt(count);
            switch (val) {
                case '[':
                    beginIndex = count + 1;
                    break;
                case ']':
                    endIndex = count;
                    params.add(inputString.substring(beginIndex, endIndex));
                    break;
            }
            count++;
        }
        return params;
    }

    private String matchWithFromMap(String toMatch, HashMap<String, String> map) {

        return map.get(toMatch);
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
     * @return the merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return the transactionDetailsWrite
     */
    public String getTransactionDetailsWrite() {
        return transactionDetailsWrite;
    }

    /**
     * @param transactionDetailsWrite the transactionDetailsWrite to set
     */
    public void setTransactionDetailsWrite(String transactionDetailsWrite) {
        this.transactionDetailsWrite = transactionDetailsWrite;
    }

    /**
     * @return the transactionDetailsRead
     */
    public String getTransactionDetailsRead() {
        return transactionDetailsRead;
    }

    /**
     * @param transactionDetailsRead the transactionDetailsRead to set
     */
    public void setTransactionDetailsRead(String transactionDetailsRead) {
        this.transactionDetailsRead = transactionDetailsRead;
    }
    
}
