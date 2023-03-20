package com.truteq.ccpgw.payment.gateway.adapter;


import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;

/**
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

public class BSPPaymentGatewayHandler extends PaymentGatewayHandler {  
    
    private final LogWrapper mLogger = new LogWrapper(BSPPaymentGatewayHandler.class);


    public BSPPaymentGatewayHandler(          
            
            String originaldataWrite,
            String financialWrite,
            String financialRead,
            String threedsCreateReq,
            String threedsValidatePaRes,
            String threedsV2Versioning,
            String threedsV2Authorise,
            String threedsV2AuthReqRead,
            String threedsV2AuthReqWrite,
            String threedsV2EnrolledWrite,           
            String acsurl,
            String acsParerqWrite,
            String postilionAuthorise,//postilionFinancial
            String postilionRefund,
            String cardAcceptorNameLocation,
            String acquiringInstitutionCodePostilion,
            String merchantId,
            String acquiringInstitutionCodeVisa,
            String acquiringInstitutionCodeMastercard,
            String posDataCode,
            String categoryCode,
            String currencyCode,
            boolean debug,
            String communicatorUserName,
            String communicatorPassword,
            String secret,
            String merchantCurrencyMapRead,
            String keystore,
            String keystorepasswordplatformpac,
            String countryCode,
            String merchantCode,
            String merchantType,
            String updateOrder,
            String threeDSResultResponse,
            String threeDSIssuerURL,
            String transactionDetailsWrite,
            String transactionDetailsRead
    ){
        super(originaldataWrite,
              "", //No originaldataRead with the BSPPay payment gateway handler.
              financialWrite, //No authorisationWrite with theBSPPay payment gateway handler.
              financialRead, //No authorisationRead with the BSPPay payment gateway handler.
              "", //No captureWrite with the BSPPay payment gateway handler.
              "", //No financial write with the BSPPay payment gateway handler.
              //financialWrite, //No captureRead with the BSPPay payment gateway handler.
              threedsCreateReq, 
              threedsValidatePaRes,
              threedsV2Versioning,
              threedsV2Authorise,
              threedsV2AuthReqRead,
              threedsV2AuthReqWrite,
              threedsV2EnrolledWrite,
              acsurl, 
              "", //No servletProxy with the BSPPay payment gateway handler.
              acsParerqWrite, 
              postilionAuthorise, 
              postilionRefund, //Postilion Refund with the BSPPay payment gateway handler.
              "", //No postilionCapture with the BSPPay payment gateway handler.
              "", //No postilionReversal write with the BSPPay payment gateway handler.
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
              updateOrder,
              threeDSResultResponse,
              threeDSIssuerURL,
              transactionDetailsWrite,
              transactionDetailsRead);
    }     
    
                    
}



///*
// * ***************************************************************
// * Truteq Internet Payment Gateway (IPGW) version 1.0.0
// * ***************************************************************
// * Copyright (c) 2021 Truteq Australia 2019
// * ***************************************************************
// * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
// * Support: Grant O'Reilly gbo@truteq.com
// * V01.00.00  20-Nov-2020 
// * V01.01.01  30-Jul-2021 Mya 
// * ***************************************************************
// */
//package com.truteq.ccpgw.payment.gateway.adapter;
//
//import com.google.gson.Gson;
//import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
////import com.truteq.ccpgw.frontend.adapter.json.api.AddressType;
////import com.truteq.ccpgw.frontend.adapter.json.api.AuthResponse;
////import com.truteq.ccpgw.frontend.adapter.json.api.PaymentDetailType;
////import com.truteq.ccpgw.frontend.adapter.json.api.model.AuthResponseObject;
////import com.truteq.ccpgw.frontend.adapter.json.api.model.Order;
//import com.truteq.ccpgw.netcetera.model.CH;
//import com.truteq.ccpgw.netcetera.model.Cardholder;
//import com.truteq.ccpgw.netcetera.model.Merchant;
//import com.truteq.ccpgw.netcetera.model.Merchant2;
//import com.truteq.ccpgw.netcetera.model.Message;
//import com.truteq.ccpgw.netcetera.model.PAReq;
//import com.truteq.ccpgw.netcetera.model.PaResValidationResponse;
//import com.truteq.ccpgw.netcetera.model.Purchase;
//import com.truteq.ccpgw.netcetera.model.Purchase2;
//import com.truteq.ccpgw.netcetera.model.SessionData;
//import com.truteq.ccpgw.netcetera.model.ThreeDSecureType;
//import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseRequest;
//import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseResponse;
//import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
//import com.truteq.ccpgw.threeds.objects.PaReqRequestObject;
//import com.truteq.ccpgw.threeds.objects.PaReqResponseObject;
//import com.truteq.ccpgw.threeds.objects.PaResValidateRequestObject;
//import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
//import com.truteq.ccpgw.transaction.manager.model.AcsPareqElement;
//import com.truteq.ccpgw.transaction.manager.model.Authorisation;
//import com.truteq.ccpgw.transaction.manager.model.MerchantCurrencyMap;
//import com.truteq.ccpgw.frontend.adapter.json.api.model.OriginalDataElement_Financial;
//import com.truteq.general.util.AESEncryptionDecryption;
//import java.util.Base64;
//import java.util.Date;
//
//import com.truteq.general.util.HashAString;
//import java.util.List;
//
///**
// *
// * @author Grant Blaise O'Reilly <gbo@truteq.com>
// */
//public class BSPPaymentGatewayHandler {
//
//    private String originaldataWrite;
//    private String financialWrite;
//    private String threedsCreateReq;
//    private String threedsValidatePaRes;
//    private String acsurl;
//    private String acsParerqWrite;
//    private String postilionFinancial;
//    private String cardAcceptorNameLocation;
//    private String acquiringInstitutionCode;
//    private String acquiringInstitutionCodeVisa;
//    private String acquiringInstitutionCodeMastercard;
//    private String posDataCode;
//    private String categoryCode;
//    private String currencyCode;
//    private boolean debug;
//    private String communicatorUserName;
//    private String communicatorPassword;
//    private String secret;
//    private String merchantCurrencyMapRead;
//    private String keystore;
//    private String keystorepasswordplatformpac;
//    private String countryCode;
//    private String merchantCode;
//    private String merchantType;
//    private String updateOrder;
//
//    private AESEncryptionDecryption decryptor;
//    private String decryptedUserName;
//    private String decryptedPassword;
//    private MerchantCurrencyMap merchantCurrencyMap;
//    private String decryptedkeystorepasswordplatformpac;
//
//    private LogWrapper mLogger = new LogWrapper(BSPPaymentGatewayHandler.class);
//
//    public BSPPaymentGatewayHandler(String originaldataWrite,
//            String financialWrite,
//            String threedsCreateReq,
//            String threedsValidatePaRes,
//            String acsurl,
//            String acsParerqWrite,
//            String postilionFinancial,
//            String cardAcceptorNameLocation,
//            String acquiringInstitutionCode,
//            String acquiringInstitutionCodeVisa,
//            String acquiringInstitutionCodeMastercard,
//            String posDataCode,
//            String categoryCode,
//            String currencyCode,
//            boolean debug,
//            String communicatorUserName,
//            String communicatorPassword,
//            String secret,
//            String merchantCurrencyMapRead,
//            String keystore,
//            String keystorepasswordplatformpac,
//            String countryCode,
//            String merchantCode,
//            String merchantType,
//            String updateOrder
//    ) {
//
//        this.originaldataWrite = originaldataWrite;
//        this.financialWrite = financialWrite;
//        this.threedsCreateReq = threedsCreateReq;
//        this.threedsValidatePaRes = threedsValidatePaRes;
//        this.acsurl = acsurl;
//        this.acsParerqWrite = acsParerqWrite;
//        this.postilionFinancial = postilionFinancial;
//        this.cardAcceptorNameLocation = cardAcceptorNameLocation;
//        this.acquiringInstitutionCode = acquiringInstitutionCode;
//        this.acquiringInstitutionCodeVisa = acquiringInstitutionCodeVisa;
//        this.acquiringInstitutionCodeMastercard = acquiringInstitutionCodeMastercard;
//        this.posDataCode = posDataCode;
//        this.categoryCode = categoryCode;
//        this.currencyCode = currencyCode;
//        this.debug = debug;
//        this.communicatorUserName = communicatorUserName;
//        this.communicatorPassword = communicatorPassword;
//        this.secret = secret;
//        this.merchantCurrencyMapRead = merchantCurrencyMapRead;
//        this.keystore = keystore;
//        this.keystorepasswordplatformpac = keystorepasswordplatformpac;
//        this.countryCode = countryCode;
//        this.merchantCode = merchantCode;
//        this.merchantType = merchantType;
//        this.updateOrder = updateOrder;
//
//        try {
//            decryptor = new AESEncryptionDecryption();
//            decryptedUserName = decryptor.decrypt(communicatorUserName, secret);
//            decryptedPassword = decryptor.decrypt(communicatorPassword, secret);
//            decryptedkeystorepasswordplatformpac = decryptor.decrypt(keystorepasswordplatformpac, secret);
//        } catch (Exception ex) {
//            getmLogger().error("Exception with decryption credentials." + ex, new Throwable().getStackTrace()[0]);
//        }
//    }
//
//    private String checkAmount(String amount) {
//
//        if (amount.contains(".")) {
//            Float floatAmount = new Float(amount) * 100;
//            String centAmount = Integer.toString(floatAmount.intValue());
//            return centAmount;
//        }
//        return amount;
//    }
//
//    private String checkExpiryDate(String datetime) {
//
//        //Receive datetime as MMYYYY from Sabre
//        //Need to return expiry date as YYMM
//        if (datetime.length() > 4) {
//
//            String monthString = datetime.substring(0, 2);
//            String yearString = datetime.substring(4, 6);
//
//            return yearString + monthString;
//
//        } else if (datetime.length() == 4) {
//
//            String monthString = datetime.substring(0, 2);
//            String yearString = datetime.substring(2, 4);
//
//            return yearString + monthString;
//        }
//        return datetime;
//    }
//
//    private byte[] concatBytes(byte[] xid, byte[] cavv) {
//        byte[] result = new byte[xid.length + cavv.length];
//        System.arraycopy(xid, 0, result, 0, xid.length);
//        System.arraycopy(cavv, 0, result, xid.length, cavv.length);
//        return result;
//    }
//
//    private String displayBytes(byte[] barray) {
//        StringBuilder bytes = new StringBuilder();
//        for (byte b : barray) {
//            bytes.append(b).append(" ");
//        }
//        return bytes.toString();
//    }
//
//    public String GenerateTransactionID() {
//
//        Date date = new Date();
//        return date.getTime() + "";
//
//    }
//
//    private String AddressVerificationFormat(AddressType addressType) {
//
//        String postalcode = "";
//        String addressline1 = "";
//        String addressline2 = "";
//        String city = "";
//        String state = "";
//        String country = "";
//
//        StringBuilder avd = new StringBuilder();
//
//        if (addressType.getPostalCode() != null) {
//            postalcode = addressType.getPostalCode();
//            //Postilion: The alphanumeric postal/ZIP code (positions 1 - 9)
//            if (postalcode.length() < 9) {
//                int padding = 9 - addressType.getPostalCode().length();
//
//                for (int i = 1; i <= padding; i++) {
//                    postalcode = postalcode + " ";
//                }
//            }
//
//        }
//
//        if (addressType.getAddressLine1() != null) {
//            addressline1 = addressType.getAddressLine1();
//        }
//        if (addressType.getAddressLine2() != null) {
//            addressline2 = addressType.getAddressLine2();
//        }
//        if (addressType.getCityName() != null) {
//            city = addressType.getCityName();
//        }
//        if (addressType.getStateProv() != null) {
//            if (addressType.getStateProv().getName() != null) {
//                state = addressType.getStateProv().getName();
//            }
//        }
//        if (addressType.getCounty() != null) {
//            if (addressType.getCountry().getName() != null) {
//                country = addressType.getCounty();
//            }
//        }
//
//        avd.append(postalcode);
//        avd.append(addressline1).append(" ");
//        avd.append(addressline2).append(" ");
//        avd.append(city).append(" ");
//        avd.append(state).append(" ");
//        avd.append(country).append(" ");
//
//        String avdStringOut = avd.toString();
//
//        if (avdStringOut.length() < 29) {
//            int advPadding = 29 - avdStringOut.length();
//
//            for (int i = 1; i <= advPadding; i++) {
//                avdStringOut = avdStringOut + " ";
//            }
//        } else {
//            avdStringOut = avdStringOut.substring(0, 29);
//        }
//
//        //Postilion :The alphanumeric cardholder address (positions 10 - 29).
//        return avdStringOut;
//    }
//
//    private String processResponseCode(String responseCode) {
//        switch (responseCode) {
//            case "00":
//                return "Approval";
//            case "01":
//                return "Refer to card issuer";
//            case "02":
//                return "Refer to card issuer, special condition ";
//            case "03":
//                return "Invalid merchant ";
//            case "04":
//                return "Pick-up card ";
//            case "05":
//                return "Do not honor ";
//            case "06":
//                return "Error ";
//            case "07":
//                return "Pick-up card, special condition ";
//            case "08":
//                return "Honor with identification ";
//            case "09":
//                return "Request in progress ";
//            case "10":
//                return "Approved, partial ";
//            case "11":
//                return "Approved, VIP ";
//            case "12":
//                return "Invalid transaction ";
//            case "13":
//                return "Invalid amount ";
//            case "14":
//                return "Invalid card number ";
//            case "15":
//                return "No such issuer ";
//            case "16":
//                return "Approved, update track 3 ";
//            case "17":
//                return "Customer cancellation ";
//            case "18":
//                return "Customer dispute ";
//            case "19":
//                return "Re-enter transaction ";
//            case "20":
//                return "Invalid response ";
//            case "21":
//                return "No action taken ";
//            case "22":
//                return "Suspected malfunction ";
//            case "23":
//                return "Unacceptable transaction fee ";
//            case "24":
//                return "File update not supported ";
//            case "25":
//                return "Unable to locate record ";
//            case "26":
//                return "Duplicate record ";
//            case "27":
//                return "File update field edit error ";
//            case "28":
//                return "File update file locked ";
//            case "29":
//                return "File update failed ";
//            case "30":
//                return "Format error";
//            case "31":
//                return " Bank not supported ";
//            case "32":
//                return " Completed partially ";
//            case "33":
//                return " Expired card, pick-up ";
//            case "34":
//                return " Suspected fraud, pick-up ";
//            case "35":
//                return " Contact acquirer, pick-up ";
//            case "36":
//                return " Restricted card, pick-up ";
//            case "37":
//                return " Call acquirer security, pick-up ";
//            case "38":
//                return " PIN tries exceeded, pick-up ";
//            case "39":
//                return " No credit account ";
//            case "40":
//                return " Function not supported ";
//            case "41":
//                return " Lost card, pick-up ";
//            case "42":
//                return " No universal account ";
//            case "43":
//                return " Stolen card, pick-up ";
//            case "44":
//                return " No investment account ";
//            case "45":
//                return " Account closed ";
//            case "46":
//                return " Identification required ";
//            case "47":
//                return "Identification cross-check required ";
//            case "48":
//            case "49":
//            case "50":
//                return "Reserved for future Postilion use ";
//            case "51":
//                return "Not sufficient funds ";
//            case "52":
//                return "No check account ";
//            case "53":
//                return "No savings account ";
//            case "54":
//                return "Expired card ";
//            case "55":
//                return "Incorrect PIN ";
//            case "56":
//                return "No card record ";
//            case "57":
//                return "Transaction not permitted to cardholder ";
//            case "58":
//                return "Transaction not permitted on terminal ";
//            case "59":
//                return "Suspected fraud ";
//            case "60":
//                return "Contact acquirer ";
//            case "61":
//                return "Exceeds withdrawal limit ";
//            case "62":
//                return "Restricted card ";
//            case "63":
//                return "Security violation ";
//            case "64":
//                return "Original amount incorrect ";
//            case "65":
//                return "Exceeds withdrawal frequency ";
//            case "66":
//                return "Call acquirer security ";
//            case "67":
//                return "Hard capture ";
//            case "68":
//                return "Response received too late ";
//            case "69":
//                return "Advice received too late ";
//            case "70":
//            case "71":
//            case "72":
//            case "73":
//            case "74":
//                return "Reserved for future Postilion use ";
//            case "75":
//                return "PIN tries exceeded";
//            case "76":
//                return "Reserved for future Postilion use";
//            case "77":
//                return "Intervene, bank approval required";
//            case "78":
//                return "Intervene, bank approval required for partial amount";
//            case "79":
//            case "80":
//            case "81":
//            case "82":
//            case "83":
//            case "84":
//            case "85":
//            case "86":
//            case "87":
//            case "88":
//            case "89":
//                return "Reserved for client-specific use (declined)";
//            case "90":
//                return "Cut-off in progress";
//            case "91":
//                return "Issuer or switch inoperative";
//            case "92":
//                return "Routing error";
//            case "93":
//                return "Violation of law";
//            case "94":
//                return "Duplicate transaction";
//            case "95":
//                return "Reconcile error";
//            case "96":
//                return "System malfunction";
//            case "97":
//                return "Reserved for future Postilion use";
//            case "98":
//                return "Exceeds cash limit";
//            case "99":
//                return "Reserved for future Postilion use";
//            default:
//                return "Unknown";
//        }
//    }
//
//    public String processAVS(String avs) {
//
//        switch (avs) {
//            case "A":
//                return "F"; //A:Address matches, postal/ZIP code does not -> F:Address matched; postal code not matched
//            case "E":
//                return "E"; //E:Error                                     -> E:Postal code and address not checked
//            case "N":
//                return "J"; //Neither address nor postal/ZIP code matches -> J:Postal code and address not matched
//            case "R":
//                return "E"; //Retry                                       ->
//            case "U":
//                return "H"; //U:Unavailable                               -> H: Postal code and address not supplied 
//            case "Y":
//                return "A"; //Y:Address and postal/ZIP code matches       -> A:Postal code and address matched
//            case "Z":
//                return "C"; //Z:postal/ZIP code matches, address does not -> C:Postal code matched; address not matched 
//            default:
//                return "E";
//        }
//
//    }
//
//    public String processCVC(String cvc) {
//
//        switch (cvc) {
//            case "M":
//                return "A"; //M:CVV2 valid (match), CVV valid or not available       -> A: CVC Matched
//            case "N":
//                return "D"; //N:CVV2 invalid (non-match), CVV valid or not available -> D: CVC Not Match
//            case "P":
//                return "J"; //P:Unable to process CVV2, CVV valid or not available   -> B: CVC Not Supplied
//            case "U":
//                return "E"; //U:Issuer unregistered to process CVV2, CVV valid or not available -> C:CVC Not Checked
//            case "Y":
//                return "D"; //Y:CVV invalid                                          -> D: CVC Not Match
//            default:
//                return "C";
//        }
//    }
//
//    private void PersistFinancialTransaction(String transactionId, String messageType, AuthoriseResponse postilionAuthRes) {
//
//        OriginalDataElement_Financial ode = new OriginalDataElement_Financial(transactionId,
//                messageType,
//                postilionAuthRes.getTransmissionDateTime(),
//                postilionAuthRes.getSystemTraceAuditNumber(),
//                postilionAuthRes.getAcquiringInstitutionCode(),
//                "00000000000");
//
//        Authorisation auth = new Authorisation(transactionId,
//                postilionAuthRes.getMessageType(),
//                postilionAuthRes.getPrimaryAccountNumber(),
//                postilionAuthRes.getProcessingCode(),
//                new Integer(checkAmount(postilionAuthRes.getTransactionAmount())),
//                postilionAuthRes.getTransmissionDateTime(),
//                postilionAuthRes.getSystemTraceAuditNumber(),
//                postilionAuthRes.getLocalTranTime(),
//                postilionAuthRes.getLocalTranDate(),
//                postilionAuthRes.getAccountExpiryDate(),
//                postilionAuthRes.getSettlementDate(),
//                postilionAuthRes.getPosConditionCode(),
//                postilionAuthRes.getAcquiringInstitutionCode(),
//                postilionAuthRes.getRetrievalRefNumber(),
//                postilionAuthRes.getAuthIdResponse(),
//                postilionAuthRes.getTerminalId(),
//                postilionAuthRes.getPosDataCode(),
//                postilionAuthRes.getRoutingInformation());
//
//        getmLogger().info("Saving original data element to database: " + ode.getDE90(), new Throwable().getStackTrace()[0]);
//
//        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
//        sslComms.sendHttpPost(this.getOriginaldataWrite(), ode.toJSON().getBytes());
//
//        getmLogger().info("Saving authorisation data to database: " + auth.getTransactionId(), new Throwable().getStackTrace()[0]);
//        sslComms.sendHttpPost(this.getFinancialWrite(), auth.toJSON().getBytes());
//    }
//
//    private void PersistACS(AcsPareqElement acsPareqElement) {
//        getmLogger().info("Saving the ACS and PaReq to database", new Throwable().getStackTrace()[0]);
//        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
//        sslComms.sendHttpPost(this.getAcsParerqWrite(), acsPareqElement.toJSON().getBytes());
//    }
//
//    private AuthResponse DeclineAuth(String orderNumber, String transactionId) {
//
//        getmLogger().info("Decline!", new Throwable().getStackTrace()[0]);
//        AuthResponse response = new AuthResponse();
//
//        response.setVersion("1.0");
//        response.setResponseCode("5");
//        response.setDescription("Declined");
//        response.setAvsResultCode("A");
//        response.setCvcResultCode("D");
//        response.setDebitCard(Boolean.FALSE);
//        response.setOrderNumber(orderNumber);
//        response.setTransactionID(transactionId);
//
//        return response;
//
//    }
//
//    /**
//     * =========================================================================
//     * processPosDataCode
//     * =========================================================================
//     * Grant O'Reilly(2021-08-05): PLEASE DO NOT CHANGE without speaking to me!
//     * -------------------------------------------------------------------------
//     *
//     * @param cardType
//     * @param transactionStatus
//     * =========================================================================
//     */
//    private void processPosDataCode(String cardType, String transactionStatus) {
//
//        getmLogger().info("Initial PosDataCode: " + this.getPosDataCode(), new Throwable().getStackTrace()[0]);
//
//        String subPosDataCode = this.getPosDataCode().substring(0, 13);
//
//        switch (cardType) {
//            case "CA"://Mastercard
//                getmLogger().info("Processing PosDataCode for Mastercard.", new Throwable().getStackTrace()[0]);
//                switch (transactionStatus.toUpperCase()) {
//                    case "Y":
//                        this.setPosDataCode(subPosDataCode + "92");
//                        break;
//                    case "N":
//                    case "U":
//                        this.setPosDataCode(subPosDataCode + "90");
//                        break;
//                    case "A":
//                        this.setPosDataCode(subPosDataCode + "91");
//                        break;
//                }
//                break;
//
//            default: //Visa
//                getmLogger().info("Processing PosDataCode for Visa card.", new Throwable().getStackTrace()[0]);
//                switch (transactionStatus.toUpperCase()) {
//                    case "Y":
//                        this.setPosDataCode(subPosDataCode + "92");
//                        break;
//                    case "N":
//                    case "A":
//                    case "ERROR":
//                        this.setPosDataCode(subPosDataCode + "91");
//                        break;
//                    case "U":
//                        this.setPosDataCode(subPosDataCode + "90");
//                        break;
//                    case "":
//                        this.setPosDataCode(subPosDataCode + "91");
//                        break;
//                    case "BLANK":
//                        this.setPosDataCode(subPosDataCode + "91");
//                        break;
//                    case "BLANK2":
//                        this.setPosDataCode(subPosDataCode + "90");
//                        break;
//                    // The default here is usually "" for Visa originally it was set to 91
//                    // but from  SIT21-1105 it was decided to make 90 - Grant O'Reilly 
//                    // 2021-09-20    
//                    //default: this.posDataCode = subPosDataCode + "91";
//                    default:
//                        this.setPosDataCode(subPosDataCode + "90");
//                }
//
//        }
//        getmLogger().info("PosDataCode: " + this.getPosDataCode(), new Throwable().getStackTrace()[0]);
//
//    }
//
//    /**
//     * =========================================================================
//     * processUCAFCollectionInd
//     * =========================================================================
//     * Grant O'Reilly(2021-08-05): PLEASE DO NOT CHANGE without speaking to me!
//     * -------------------------------------------------------------------------
//     * UCAF collection indicator
//     * -------------------------------------------------------------------------
//     * 0 = UCAF data collection is not supported at the merchant's web site. 1 =
//     * UCAF data collection is supported by the merchant, but UCAF data was not
//     * populated. 2 = UCAF data collection is supported by the merchant and the
//     * UCAF data was populated.
//     * -------------------------------------------------------------------------
//     *
//     * @param transactionStatus
//     * @return
//     * =========================================================================
//     */
//    private int processUCAFCollectionInd(String transactionStatus) {
//
//        switch (transactionStatus.toUpperCase()) {
//            case "Y":
//                return 2;
//            case "N":
//            case "U":
//                return 0;
//            case "A":
//                return 1;
//            default:
//                return 0;
//        }
//    }
//
////    private String processUCAFPosition2(String transactionStatus) {
////        switch (transactionStatus.toUpperCase()) {
////            case "Y":
////                return "j";
////            case "A":
////                return "h";
////            case "N":
////            case "U":
////            default:
////                return "";
////        }
////    }
//    /**
//     * =========================================================================
//     * processSecureResult
//     * =========================================================================
//     * Grant O'Reilly(2021-08-12): PLEASE DO NOT CHANGE without speaking to me!
//     * -------------------------------------------------------------------------
//     *
//     * @param transactionStatus
//     * @return
//     * =========================================================================
//     */
//    //private String processSecureResult(String transactionStatus, String cavv){
//    private String processSecureResult(String transactionStatus) {
//
//        switch (transactionStatus.toUpperCase()) {
//            case "":
//            case " ":
//                return "";
//            case "ERROR":
//                return "0";
//            case "N":
//                return "1";
//            case "Y":
//                return "2";
//            case "A":
//                return "3";
//            case "U":
//                return "4";
//            default:
//                return "0";
//        }
//    }
//
//    /**
//     * =========================================================================
//     * ThreeDSTransaction
//     * =========================================================================
//     *
//     * @param paResValidResponse
//     * @param paymentType
//     * @param merchantAccountCode
//     * @return
//     * =========================================================================
//     */
//    private AuthoriseResponse ThreeDSTransactionFinancial(PaResValidationResponse paResValidResponse,
//            PaymentDetailType paymentType) {
//
//        getmLogger().info("Processing 3DS card.", new Throwable().getStackTrace()[0]);
//        byte[] decoded_xid = Base64.getDecoder().decode(paResValidResponse.getXid());
//        byte[] decoded_cavv = Base64.getDecoder().decode(paResValidResponse.getCavv());
//        byte[] ds = concatBytes(decoded_xid, decoded_cavv);
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//        String threeDSecureData = paResValidResponse.getXid() + paResValidResponse.getCavv();
//        String transactionStatus = paResValidResponse.getTransactionStatus();
//        String cavv = paResValidResponse.getCavv();
//        String posConditionCode = "59";
//
//        getmLogger().debug("3DSecureData: " + threeDSecureData, new Throwable().getStackTrace()[0]);
//        getmLogger().debug("Decoded XiD length: " + String.valueOf(decoded_xid.length) + " Byte array: " + String.valueOf(displayBytes(decoded_xid)), new Throwable().getStackTrace()[0]);
//        getmLogger().debug("Decoded CAVV length: " + String.valueOf(decoded_cavv.length) + " Byte array: " + String.valueOf(displayBytes(decoded_cavv)), new Throwable().getStackTrace()[0]);
//        getmLogger().debug("3DSecureData length: " + String.valueOf(ds.length) + " Byte array: " + String.valueOf(displayBytes(ds)), new Throwable().getStackTrace()[0]);
//        getmLogger().debug("Payment method code: " + paymentType.getPaymentMethod().getPaymentMethodCode(), new Throwable().getStackTrace()[0]);
//
//        String avd = AddressVerificationFormat(paymentType.getBillingAddress());
//
//        getmLogger().info("AVD: " + avd, new Throwable().getStackTrace()[0]);
//
//        AuthoriseRequest authObj;
//        if (cardType.equals("CA")) { //This is Mastercard
//
//            getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
//
//            int ucafCollectionInd = processUCAFCollectionInd(transactionStatus);
//
//            String ucaf = ucafCollectionInd + paResValidResponse.getCavv();
//            //------------------------------------------------------------------
//            // Please DONOT remove this code 
//            // Grant O'Reilly : This is incase Netcetera does not take care of 
//            //                  the position 2 "j" or "h"
//            //------------------------------------------------------------------
//            //String position2 = processUCAFPosition2(transactionStatus);
//            //String ucaf = ucafCollectionInd +position2+ paResValidResponse.getCavv(); //2 ->UCAF data collection is supported by the merchant and the UCAF data was populated. 
//            //String ucaf = ucafCollectionInd + "JAmi21makAifmwqo2120cjq1AAA=";
//            //------------------------------------------------------------------
//
//            getmLogger().debug("UCAF: " + ucaf, new Throwable().getStackTrace()[0]);
//
//            posConditionCode = "06";
//            getmLogger().debug("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
//
//            processPosDataCode(cardType, transactionStatus);
//            getmLogger().debug("ucaf Collection Ind : " + ucafCollectionInd, new Throwable().getStackTrace()[0]);
//            getmLogger().debug("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
//            getmLogger().debug("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);
//            getmLogger().debug("paymentType.getPaymentCard().getExpireDate(): " + paymentType.getPaymentCard().getExpireDate());
//            authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
//                    "000000", //processingCode,          //DE3  
//                    checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
//                    checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
//                    //ADD IN settlement date in the format MMDD DE15
//                    "6300", //merchantType,            //DE18 
//                    "010", //posEntryMode             //DE22
//                    posConditionCode, //"00", //posConditionCode         //DE25
//                    getAcquiringInstitutionCode(), //acquiringInstitutionCode //DE32
//                    this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
//                    this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
//                    this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
//                    this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. ,//"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
//                    getPosDataCode(), //DE123 // 4-Aug-2021 Grant O'Reilly
//                    paymentType.getPaymentCard().getCvc(), //cvv2,                  //DE127.10
//                    "", //DE127.15
//                    "", //DE127.27
//                    "", //AmericanExpressCardIdentifier, ////DE127.28 
//                    null, //threeDSecureData,        //DE127.29
//                    "", //threeDSecureResult,      //DE127.30
//                    ucaf, // 4-Aug-2021 Grant O'Reilly
//                    null); // 20-Oct-2021 Grant O'Reilly
//        } else {
//
//            getmLogger().info("Processing Visa card .....", new Throwable().getStackTrace()[0]);
//
//            String secureResult = processSecureResult(transactionStatus);
//
//            processPosDataCode(cardType, transactionStatus);
//
//            getmLogger().info("Secure Result [DE127.30]: " + secureResult, new Throwable().getStackTrace()[0]);
//            getmLogger().info("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
//            getmLogger().info("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);
//
//            posConditionCode = "59";
//            getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
//
//            authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
//                    "000000", //processingCode,          //DE3  
//                    checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
//                    checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
//                    this.getMerchantType(), //"6300", //merchantType,            //DE18 
//                    "010", //posEntryMode             //DE22
//                    posConditionCode, //"00", //posConditionCode         //DE25
//                    getAcquiringInstitutionCode(), //acquiringInstitutionCode //DE32
//                    this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
//                    this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
//                    this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
//                    this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. //"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
//                    getPosDataCode(), //DE123 // 4-Aug-2021 Grant O'Reilly
//                    paymentType.getPaymentCard().getCvc(), //cvv2,                  //DE127.10
//                    avd, //DE127.15
//                    "", //DE127.27
//                    "", //AmericanExpressCardIdentifier, ////DE127.28 
//                    //ds, //threeDSecureData,         //DE127.29
//                    null, //DE127.29
//                    //secureResult);              //threeDSecureResult,      //DE127.30  //"2"  // 4-Aug-2021 Grant O'Reilly
//                    "");//DE127.30
//        }
//
//        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
//        Result result = sslComms.sendHttpPost(this.getPostilionFinancial(), authObj.toJSON().getBytes());
//
//        getmLogger().info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);
//        getmLogger().debug("postilionAuthRes.getResponseCode() " + postilionAuthRes.getResponseCode());
//        getmLogger().debug("postilionAuthRes.getThreeDSecureResult() " + postilionAuthRes.getThreeDSecureResult());
//        getmLogger().debug("postilionAuthRes.getAuthIdResponse() " + postilionAuthRes.getAuthIdResponse());
//        return postilionAuthRes;
//    }
//
//    /**
//     * =========================================================================
//     * NonThreeDSTransaction
//     * =========================================================================
//     *
//     * @param paResValidResponse
//     * @param paymentType
//     * @param merchantAccountCode
//     * @return
//     */
//    private AuthoriseResponse NonThreeDSTransactionFinancial(PaResValidationResponse paResValidResponse,
//            PaymentDetailType paymentType) {
//        getmLogger().info("Processing non-3DS card.", new Throwable().getStackTrace()[0]);
//
//        String transactionStatus = paResValidResponse.getTransactionStatus();
//        String cavv = paResValidResponse.getCavv();
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//
//        if (paResValidResponse.getXid() != null) {
//            byte[] decoded_xid = Base64.getDecoder().decode(paResValidResponse.getXid());
//
//            getmLogger().info("Decoded XiD length: " + String.valueOf(decoded_xid.length) + " Byte array: " + String.valueOf(displayBytes(decoded_xid)), new Throwable().getStackTrace()[0]);
//        }
//
//        String secureResult = "";
//        int ucafCollectionInd = 0;
//        String ucaf = ucafCollectionInd + "";
//        String posConditionCode = "59";
//
//        if (transactionStatus != null) {
//
//            if (cardType.equals("CA")) { //This is Mastercard
//                getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
//                processPosDataCode(cardType, transactionStatus);
//                posConditionCode = "06";
//                getmLogger().info("ucaf Collection Ind : " + ucafCollectionInd, new Throwable().getStackTrace()[0]);
//                getmLogger().info("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
//                getmLogger().info("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);
//                getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
//            } else {
//
//                getmLogger().info("Processing Visa card .....", new Throwable().getStackTrace()[0]);
//                processPosDataCode(cardType, transactionStatus);
//                secureResult = processSecureResult(transactionStatus);
//                ucaf = null;
//                posConditionCode = "59";
//                getmLogger().info("Secure Result [DE127.30]: " + secureResult, new Throwable().getStackTrace()[0]);
//                getmLogger().info("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
//                getmLogger().info("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);
//                getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
//            }
//        } else {
//            if (cardType.equals("CA")) { //This is Mastercard
//                getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
//                posConditionCode = "06";
//                processPosDataCode(cardType, "U");
//            } else {
//                getmLogger().info("Processing Visa card .....", new Throwable().getStackTrace()[0]);
//                processPosDataCode(cardType, "BLANK");
//                secureResult = processSecureResult("ERROR");
//                posConditionCode = "59";
//                ucaf = null;
//            }
//        }
//
//        AuthoriseRequest authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
//                "000000", //processingCode,          //DE3  
//                checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
//                checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
//                //ADD IN settlement date in the format MMDD DE15
//                "6300", //merchantType,            //DE18 
//                "010", //posEntryMode             //DE22
//                posConditionCode, //"00", //posConditionCode         //DE25
//                getAcquiringInstitutionCode(), //acquiringInstitutionCode //DE32
//                this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
//                this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
//                this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
//                this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. , //"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
//                getPosDataCode(), //posDataCode,             //DE123
//                paymentType.getPaymentCard().getCvc(), //cvv2,                  //DE127.10
//                "", //DE127.15
//                "", //DE127.27
//                "", //AmericanExpressCardIdentifier, ////DE127.28 
//                null, //threeDSecureData,        //DE127.29
//                secureResult, //threeDSecureResult,      //DE127.30
//                ucaf,
//                null);
//
//        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
//        Result result = sslComms.sendHttpPost(this.getPostilionFinancial(), authObj.toJSON().getBytes());
//
//        getmLogger().info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);
//        return postilionAuthRes;
//    }
//
//    /**
//     *
//     * @param response
//     * @param paymentType
//     * @return
//     */
//    public AuthResponse NonThreeDSTransactionFinancial(AuthResponse response, PaymentDetailType paymentType) {
//        //Visa will be CAVV resultcode blank and posdata 90, UCAF 0 and posdata 90
//        getmLogger().info("Processing non-3DS card.", new Throwable().getStackTrace()[0]);
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//
//        String secureResult = "";
//        int ucafCollectionInd = 0;
//        String ucaf = ucafCollectionInd + "";
//        String posConditionCode = "59";
//
//        if (cardType.equals("CA")) { //This is Mastercard
//            getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
//            processPosDataCode(cardType, "N");
//            posConditionCode = "06";
//            getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
//
//        } else {
//
//            getmLogger().info("Processing Visa card .....");
//            processPosDataCode(cardType, "BLANK2");
//            posConditionCode = "59";
//            getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
//            ucaf = null;
//        }
//        getmLogger().debug("paymentType.getPaymentCard().getExpireDate(): " + paymentType.getPaymentCard().getExpireDate());
//        AuthoriseRequest authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
//                "000000", //processingCode,          //DE3  
//                checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
//                checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
//                //ADD IN settlement date in the format MMDD DE15
//                this.getMerchantType(), //"6300", //merchantType,            //DE18 
//                "010", //posEntryMode             //DE22
//                posConditionCode, //"00", //posConditionCode         //DE25
//                getAcquiringInstitutionCode(), //acquiringInstitutionCode //DE32
//                this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
//                this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
//                this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
//                this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. ,//"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
//                getPosDataCode(), //DE123 // 4-Aug-2021 Grant O'Reilly
//                paymentType.getPaymentCard().getCvc(), //cvv2,                  //DE127.10
//                "", //DE127.15
//                "", //DE127.27
//                "", //AmericanExpressCardIdentifier, ////DE127.28 
//                null, //threeDSecureData,        //DE127.29
//                secureResult, //threeDSecureResult,      //DE127.30
//                ucaf, // 4-Aug-2021 Grant O'Reilly 
//                null); // 20-Oct-2021 Grant O'Reilly 
//
//        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
//        Result result = sslComms.sendHttpPost(this.getPostilionFinancial(), authObj.toJSON().getBytes());
//
//        getmLogger().info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);
//
//        AuthResponse authResponse = processPostilionAuthRes(postilionAuthRes,
//                null,
//                response.getOrderNumber(),
//                response.getTransactionID());
//        return authResponse;
//    }
//
//    /**
//     *
//     * @param postilionAuthRes
//     * @return
//     */
//    private com.truteq.ccpgw.frontend.adapter.json.api.AuthResponse processPostilionAuthRes(AuthoriseResponse postilionAuthRes,
//            PaResValidationResponse paResValidResponse,
//            String orderNumber,
//            String transactionId) {
//        com.truteq.ccpgw.frontend.adapter.json.api.AuthResponse response = new com.truteq.ccpgw.frontend.adapter.json.api.AuthResponse();
//
//        Integer responseCode = new Integer(postilionAuthRes.getResponseCode());
//        String description = processResponseCode(postilionAuthRes.getResponseCode());
//
//        if ((responseCode == 0)) { //not Sabre 91 response code  || (responseCode == 91)) {
//            getmLogger().info("Persisting transaction as response is approved.", new Throwable().getStackTrace()[0]);
//            PersistFinancialTransaction(transactionId, "0200", postilionAuthRes);
//        }
//
//        String avs = "E";
//        String cvc = "C";
//
//        if (postilionAuthRes.getAddressVerificationResult() != null) {
//            avs = processAVS(postilionAuthRes.getAddressVerificationResult());
//        }
//        if (postilionAuthRes.getThreeDSecureResult() != null) {
//            cvc = processCVC(postilionAuthRes.getThreeDSecureResult());
//        }
//
//        getmLogger().info("ResponseCode :" + responseCode.toString(), new Throwable().getStackTrace()[0]);
//        getmLogger().info("Description  :" + description, new Throwable().getStackTrace()[0]);
//        getmLogger().info("Approval Id  :" + postilionAuthRes.getAuthIdResponse(), new Throwable().getStackTrace()[0]);
//        getmLogger().info("AVS          :" + avs, new Throwable().getStackTrace()[0]);
//        getmLogger().info("CVC          : ###(" + HashAString.hash(cvc) + ")", new Throwable().getStackTrace()[0]);
//
//        if (responseCode != 0) {
//            return DeclineAuth(orderNumber, transactionId);
//        }
//
//        //3DS response
//        response.setVersion("1.0");
//        response.setResponseCode(responseCode.toString());
//        response.setDescription(description);
//        response.setApprovalCode(postilionAuthRes.getAuthIdResponse());
//        response.setAvsResultCode(avs);
//        response.setCvcResultCode(cvc);
//        response.setDebitCard(Boolean.FALSE);
//        response.setOrderNumber(orderNumber);
//        response.setTransactionID(transactionId);
//        response.setDebitCard(Boolean.FALSE);
//
//        if (paResValidResponse != null) {
//            AuthResponse.T3DSResult t3ds = new AuthResponse.T3DSResult();
//
//            t3ds.setCavv(paResValidResponse.getCavv());
//            t3ds.setEci(paResValidResponse.getEci());
//
//            response.setT3DSResult(t3ds);
//        }
//
//        Order order = new Order();
//
//        order.setOrder_id(orderNumber);
//        order.setTransaction_ref(transactionId);
//        order.setStatus("success");
//        order.setStatus_value(responseCode.toString());
//        order.setStatus_description(description);
//
//        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
//        Result result = sslComms.sendHttpPost(this.getUpdateOrder(), order.toJSON().getBytes());
//        
//        return response;
//    }
//
//    /**
//     *
//     * @param businessname
//     * @param currency
//     */
//    //=========================================== 
//    // initially added 30-Jul-2021 Mya
//    // updated to setMerchantCurrencyMap by Grant O'Reilly 09-Aug-2021
//    //===========================================
//    // retrieve reference data from the database according to the currency and merchant account code sent by the client, in this case Sabre
//    //===========================================
//    public void setMerchantCurrencyMap(String businessname, String currency) {
//        getmLogger().info("Read MerchantCurrencyMap from database using businessname and currency = " + businessname + " and " + currency, new Throwable().getStackTrace()[0]);
//
//        this.setMerchantCurrencyMap(new MerchantCurrencyMap());
//        this.getMerchantCurrencyMap().setMerchantName(businessname);
//        this.getMerchantCurrencyMap().setCurrency(currency);
//
//        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
//        Result result = sslComms.sendHttpPost(this.getMerchantCurrencyMapRead(), this.getMerchantCurrencyMap().toJSON().getBytes());
//
//        Gson gson = new Gson();
//        this.setMerchantCurrencyMap(gson.fromJson(result.getData(), MerchantCurrencyMap.class));
//
//        String currCode = this.getMerchantCurrencyMap().getCode();
//        if (currCode.length() < 3) {
//            currCode = "0" + currCode;
//            this.getMerchantCurrencyMap().setCode(currCode);
//        }
//
//    }
//
//    /////////////////////////////////NEW
//    public AuthResponseObject process3DSStepOneFinancial(String orderNumber, String transactionId, PaymentDetailType paymentType) {
//
//        AuthResponse response = new AuthResponse();
//
//        PaReqRequestObject paReq = new PaReqRequestObject();
//        Cardholder CardHolder = new Cardholder();
//        CardHolder.setPan(paymentType.getPaymentCard().getCardNumber());
//        CardHolder.setExpiry(checkExpiryDate(paymentType.getPaymentCard().getExpireDate()));
//
//        Purchase Purchase = new Purchase();
//        Purchase.setExponent("2");
//        Purchase.setAmount(checkAmount(paymentType.getAmountDetail().getAmount().toString()));
//        Purchase.setCurrency(this.getMerchantCurrencyMap().getCode());
//
//        Merchant merchant = new Merchant();
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//        getmLogger().info("Card scheme: " + cardType, new Throwable().getStackTrace()[0]);
//
//        switch (cardType) {
//            case "CA"://Mastercard
//                getmLogger().info("Setting acquire bin for Mastercard.", new Throwable().getStackTrace()[0]);
//                merchant.setAcquirerBin(this.getAcquiringInstitutionCodeMastercard());
//                break;
//            default: //Visa
//                getmLogger().info("Setting acquire bin for Visa.", new Throwable().getStackTrace()[0]);
//                merchant.setAcquirerBin(this.getAcquiringInstitutionCodeVisa());
//                break;
//        }
//
//        merchant.setCountryCode(this.getCountryCode());
//        merchant.setId(this.getMerchantCode());
//        merchant.setName("test");
//        merchant.setUrl("https://www.test.com/");
//        paReq.setCardholder(CardHolder);
//        paReq.setPurchase(Purchase);
//        paReq.setMerchant(merchant);
//
//        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
//        Result result = sslComms.sendHttpPost(this.getThreedsCreateReq(), paReq.toJSON().getBytes());
//
//        String resp = result.getData().replaceAll("message", "Message");
//        resp = resp.replaceAll("veres", "VERes");
//        resp = resp.replaceAll("ch", "CH");
//
//        Gson gson = new Gson();
//        PaReqResponseObject paResponse = gson.fromJson(resp, PaReqResponseObject.class);
//        getmLogger().info("paResponse :" + paResponse.toJSON(), new Throwable().getStackTrace()[0]);
//
//        // Part of the ENROLL = "N" Dev                
//        List<Message> messageList = paResponse.getThreeDSecureVERes().getMessage();
//        Message m = messageList.get(0);
//        String enrolled = m.getVERes().getCH().getEnrolled();
//
//        getmLogger().info("Enrolled :" + enrolled, new Throwable().getStackTrace()[0]);
//
//        if (enrolled.equals("N")) {
//            response.setOrderNumber(orderNumber);
//            response.setTransactionID(transactionId);
//        } else {
//            response.setAvsResultCode("A");
//            response.setCvcResultCode("A");
//            response.setDebitCard(Boolean.FALSE);
//            response.setDescription("Approved");
//            response.setOrderNumber(orderNumber);
//            response.setResponseCode("0");
//            response.setTransactionID(transactionId);
//            response.setVersion("");
//            response.setRedirectURL(paResponse.getSessionData().getAcsUrl());
//            //response.setRedirectURL(this.getAcsurl());
//
//            //3DS response
//            response.setVersion("1.0");
//            response.setResponseCode("1");
//            response.setDescription("3DS Authentication Required");
//            response.setOrderNumber(orderNumber);
//            response.setTransactionID(transactionId);
//            response.setDebitCard(Boolean.FALSE);
//
//            AuthResponse.T3DSResult t3ds = new AuthResponse.T3DSResult();
//            t3ds.setPaRequest(paResponse.getPaReq());
//
//            t3ds.setIssuerURL(paResponse.getSessionData().getAcsUrl());
//
//            t3ds.setMd(this.getMerchantCode());
//
//            this.PersistACS(new AcsPareqElement(paResponse.getSessionData().getAcsUrl(), paResponse.getPaReq()));
//            response.setT3DSResult(t3ds);
//        }
//
//        AuthResponseObject authRes = new AuthResponseObject(response, enrolled);
//
//        getmLogger().debug("authRes: " + authRes.toJSON());
//
//        return authRes;
//    }
//
//    public AuthResponse process3DSStepTwoFinancial(String orderNumber,
//            String transactionId,
//            PaymentDetailType paymentType) {
//
//        PaResValidateRequestObject paResValidate = new PaResValidateRequestObject();
//
//        paResValidate.setPaRes(paymentType.getPaymentCard().getT3DS().getPaResponse());
//
//        getmLogger().info("PAResponse: " + paymentType.getPaymentCard().getT3DS().getPaResponse(), new Throwable().getStackTrace()[0]);
//
//        SessionData sessionData = new SessionData();
//        sessionData.setPaReqCreationTime("20131015 16:45:01");
//        sessionData.setPan(paymentType.getPaymentCard().getCardNumber());
//        sessionData.setAcsUrl("http://www.acs-url.com");
//        sessionData.setScheme("Visa");
//        sessionData.setMasterCardTokenized("false");
//
//        Purchase2 purchase = new Purchase2();
//        purchase.setXid(paymentType.getPaymentCard().getT3DS().getXid());
//        purchase.setDate("20131015 16:45:00");
//        purchase.setAmount(paymentType.getAmountDetail().getAmount().toString());
//        purchase.setCurrency(this.getMerchantCurrencyMap().getCode());
//        purchase.setExponent("2");
//        purchase.setDesc("This is the description");
//
//        Merchant2 merchant = new Merchant2();
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//
//        switch (cardType) {
//            case "CA"://Mastercard	
//                getmLogger().info("Setting acquire bin for Mastercard.", new Throwable().getStackTrace()[0]);
//                merchant.setAcqBIN(this.getAcquiringInstitutionCodeMastercard());
//                break;
//            default: //Visa	
//                getmLogger().info("Setting acquire bin for Visa.", new Throwable().getStackTrace()[0]);
//                merchant.setAcqBIN(this.getAcquiringInstitutionCodeVisa());
//                break;
//        }
//
//        merchant.setCountry(this.getCountryCode());
//        merchant.setMerID(this.getMerchantCode());
//
//        merchant.setName("Mock Company");
//        merchant.setUrl("https://www.mock.com/");
//
//        CH ch = new CH();
//        ch.setAcctID("accountId");
//        ch.setExpiry(paymentType.getPaymentCard().getExpireDate());
//
//        PAReq paReq = new PAReq();
//        paReq.setVersion("1.0.2");
//        paReq.setMerchant(merchant);
//        paReq.setPurchase(purchase);
//        paReq.setCH(ch);
//
//        Message message = new Message();
//        message.setId("pa9d4eb548-db62-43b7-8cc8-8731ac202de2");
//        message.setPAReq(paReq);
//
//        ThreeDSecureType threedsType = new ThreeDSecureType();
//        threedsType.getMessage().add(message);
//        sessionData.setThreeDSecurePAReq(threedsType);
//        paResValidate.setSessionData(sessionData);
//
//        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
//        Result result = sslComms.sendHttpPost(this.getThreedsValidatePaRes(), paResValidate.toJSON().getBytes());
//
//        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//
//        PaResValidationResponse paResValidResponse = gson.fromJson(result.getData(), PaResValidationResponse.class);
//
//        return postilionFinanical(paResValidResponse, paymentType, orderNumber, transactionId);
//
//    }
//
//    private AuthResponse postilionFinanical(PaResValidationResponse paResValidResponse,
//            PaymentDetailType paymentType,
//            String orderNumber,
//            String transactionId) {
//
//        getmLogger().info("Sending to Postilion Authorise.", new Throwable().getStackTrace()[0]);
//
//        AuthoriseResponse postilionFinancialResponse;
//
//        if (paResValidResponse.getCavv() != null) {
//            postilionFinancialResponse = ThreeDSTransactionFinancial(paResValidResponse, paymentType);
//        } else {
//            postilionFinancialResponse = NonThreeDSTransactionFinancial(paResValidResponse, paymentType);
//        }
//
//        return processPostilionAuthRes(postilionFinancialResponse,
//                paResValidResponse,
//                orderNumber,
//                transactionId);
//    }
//
//    /**
//     * @return the originaldataWrite
//     */
//    public String getOriginaldataWrite() {
//        return originaldataWrite;
//    }
//
//    /**
//     * @param originaldataWrite the originaldataWrite to set
//     */
//    public void setOriginaldataWrite(String originaldataWrite) {
//        this.originaldataWrite = originaldataWrite;
//    }
//
//    /**
//     * @return the financialWrite
//     */
//    public String getFinancialWrite() {
//        return financialWrite;
//    }
//
//    /**
//     * @param financialWrite the financialWrite to set
//     */
//    public void setFinancialWrite(String financialWrite) {
//        this.financialWrite = financialWrite;
//    }
//
//    /**
//     * @return the threedsCreateReq
//     */
//    public String getThreedsCreateReq() {
//        return threedsCreateReq;
//    }
//
//    /**
//     * @param threedsCreateReq the threedsCreateReq to set
//     */
//    public void setThreedsCreateReq(String threedsCreateReq) {
//        this.threedsCreateReq = threedsCreateReq;
//    }
//
//    /**
//     * @return the threedsValidatePaRes
//     */
//    public String getThreedsValidatePaRes() {
//        return threedsValidatePaRes;
//    }
//
//    /**
//     * @param threedsValidatePaRes the threedsValidatePaRes to set
//     */
//    public void setThreedsValidatePaRes(String threedsValidatePaRes) {
//        this.threedsValidatePaRes = threedsValidatePaRes;
//    }
//
//    /**
//     * @return the acsurl
//     */
//    public String getAcsurl() {
//        return acsurl;
//    }
//
//    /**
//     * @param acsurl the acsurl to set
//     */
//    public void setAcsurl(String acsurl) {
//        this.acsurl = acsurl;
//    }
//
//    /**
//     * @return the acsParerqWrite
//     */
//    public String getAcsParerqWrite() {
//        return acsParerqWrite;
//    }
//
//    /**
//     * @param acsParerqWrite the acsParerqWrite to set
//     */
//    public void setAcsParerqWrite(String acsParerqWrite) {
//        this.acsParerqWrite = acsParerqWrite;
//    }
//
//    /**
//     * @return the postilionFinancial
//     */
//    public String getPostilionFinancial() {
//        return postilionFinancial;
//    }
//
//    /**
//     * @param postilionFinancial the postilionFinancial to set
//     */
//    public void setPostilionFinancial(String postilionFinancial) {
//        this.postilionFinancial = postilionFinancial;
//    }
//
//    /**
//     * @return the cardAcceptorNameLocation
//     */
//    public String getCardAcceptorNameLocation() {
//        return cardAcceptorNameLocation;
//    }
//
//    /**
//     * @param cardAcceptorNameLocation the cardAcceptorNameLocation to set
//     */
//    public void setCardAcceptorNameLocation(String cardAcceptorNameLocation) {
//        this.cardAcceptorNameLocation = cardAcceptorNameLocation;
//    }
//
//    /**
//     * @return the acquiringInstitutionCode
//     */
//    public String getAcquiringInstitutionCode() {
//        return acquiringInstitutionCode;
//    }
//
//    /**
//     * @param acquiringInstitutionCode the acquiringInstitutionCode to set
//     */
//    public void setAcquiringInstitutionCode(String acquiringInstitutionCode) {
//        this.acquiringInstitutionCode = acquiringInstitutionCode;
//    }
//
//    /**
//     * @return the acquiringInstitutionCodeVisa
//     */
//    public String getAcquiringInstitutionCodeVisa() {
//        return acquiringInstitutionCodeVisa;
//    }
//
//    /**
//     * @param acquiringInstitutionCodeVisa the acquiringInstitutionCodeVisa to
//     * set
//     */
//    public void setAcquiringInstitutionCodeVisa(String acquiringInstitutionCodeVisa) {
//        this.acquiringInstitutionCodeVisa = acquiringInstitutionCodeVisa;
//    }
//
//    /**
//     * @return the acquiringInstitutionCodeMastercard
//     */
//    public String getAcquiringInstitutionCodeMastercard() {
//        return acquiringInstitutionCodeMastercard;
//    }
//
//    /**
//     * @param acquiringInstitutionCodeMastercard the
//     * acquiringInstitutionCodeMastercard to set
//     */
//    public void setAcquiringInstitutionCodeMastercard(String acquiringInstitutionCodeMastercard) {
//        this.acquiringInstitutionCodeMastercard = acquiringInstitutionCodeMastercard;
//    }
//
//    /**
//     * @return the posDataCode
//     */
//    public String getPosDataCode() {
//        return posDataCode;
//    }
//
//    /**
//     * @param posDataCode the posDataCode to set
//     */
//    public void setPosDataCode(String posDataCode) {
//        this.posDataCode = posDataCode;
//    }
//
//    /**
//     * @return the categoryCode
//     */
//    public String getCategoryCode() {
//        return categoryCode;
//    }
//
//    /**
//     * @param categoryCode the categoryCode to set
//     */
//    public void setCategoryCode(String categoryCode) {
//        this.categoryCode = categoryCode;
//    }
//
//    /**
//     * @return the currencyCode
//     */
//    public String getCurrencyCode() {
//        return currencyCode;
//    }
//
//    /**
//     * @param currencyCode the currencyCode to set
//     */
//    public void setCurrencyCode(String currencyCode) {
//        this.currencyCode = currencyCode;
//    }
//
//    /**
//     * @return the debug
//     */
//    public boolean isDebug() {
//        return debug;
//    }
//
//    /**
//     * @param debug the debug to set
//     */
//    public void setDebug(boolean debug) {
//        this.debug = debug;
//    }
//
//    /**
//     * @return the communicatorUserName
//     */
//    public String getCommunicatorUserName() {
//        return communicatorUserName;
//    }
//
//    /**
//     * @param communicatorUserName the communicatorUserName to set
//     */
//    public void setCommunicatorUserName(String communicatorUserName) {
//        this.communicatorUserName = communicatorUserName;
//    }
//
//    /**
//     * @return the communicatorPassword
//     */
//    public String getCommunicatorPassword() {
//        return communicatorPassword;
//    }
//
//    /**
//     * @param communicatorPassword the communicatorPassword to set
//     */
//    public void setCommunicatorPassword(String communicatorPassword) {
//        this.communicatorPassword = communicatorPassword;
//    }
//
//    /**
//     * @return the secret
//     */
//    public String getSecret() {
//        return secret;
//    }
//
//    /**
//     * @param secret the secret to set
//     */
//    public void setSecret(String secret) {
//        this.secret = secret;
//    }
//
//    /**
//     * @return the merchantCurrencyMapRead
//     */
//    public String getMerchantCurrencyMapRead() {
//        return merchantCurrencyMapRead;
//    }
//
//    /**
//     * @param merchantCurrencyMapRead the merchantCurrencyMapRead to set
//     */
//    public void setMerchantCurrencyMapRead(String merchantCurrencyMapRead) {
//        this.merchantCurrencyMapRead = merchantCurrencyMapRead;
//    }
//
//    /**
//     * @return the keystore
//     */
//    public String getKeystore() {
//        return keystore;
//    }
//
//    /**
//     * @param keystore the keystore to set
//     */
//    public void setKeystore(String keystore) {
//        this.keystore = keystore;
//    }
//
//    /**
//     * @return the keystorepasswordplatformpac
//     */
//    public String getKeystorepasswordplatformpac() {
//        return keystorepasswordplatformpac;
//    }
//
//    /**
//     * @param keystorepasswordplatformpac the keystorepasswordplatformpac to set
//     */
//    public void setKeystorepasswordplatformpac(String keystorepasswordplatformpac) {
//        this.keystorepasswordplatformpac = keystorepasswordplatformpac;
//    }
//
//    /**
//     * @return the countryCode
//     */
//    public String getCountryCode() {
//        return countryCode;
//    }
//
//    /**
//     * @param countryCode the countryCode to set
//     */
//    public void setCountryCode(String countryCode) {
//        this.countryCode = countryCode;
//    }
//
//    /**
//     * @return the merchantCode
//     */
//    public String getMerchantCode() {
//        return merchantCode;
//    }
//
//    /**
//     * @param merchantCode the merchantCode to set
//     */
//    public void setMerchantCode(String merchantCode) {
//        this.merchantCode = merchantCode;
//    }
//
//    /**
//     * @return the merchantType
//     */
//    public String getMerchantType() {
//        return merchantType;
//    }
//
//    /**
//     * @param merchantType the merchantType to set
//     */
//    public void setMerchantType(String merchantType) {
//        this.merchantType = merchantType;
//    }
//
//    /**
//     * @return the decryptor
//     */
//    public AESEncryptionDecryption getDecryptor() {
//        return decryptor;
//    }
//
//    /**
//     * @param decryptor the decryptor to set
//     */
//    public void setDecryptor(AESEncryptionDecryption decryptor) {
//        this.decryptor = decryptor;
//    }
//
//    /**
//     * @return the decryptedUserName
//     */
//    public String getDecryptedUserName() {
//        return decryptedUserName;
//    }
//
//    /**
//     * @param decryptedUserName the decryptedUserName to set
//     */
//    public void setDecryptedUserName(String decryptedUserName) {
//        this.decryptedUserName = decryptedUserName;
//    }
//
//    /**
//     * @return the decryptedPassword
//     */
//    public String getDecryptedPassword() {
//        return decryptedPassword;
//    }
//
//    /**
//     * @param decryptedPassword the decryptedPassword to set
//     */
//    public void setDecryptedPassword(String decryptedPassword) {
//        this.decryptedPassword = decryptedPassword;
//    }
//
//    /**
//     * @return the merchantCurrencyMap
//     */
//    public MerchantCurrencyMap getMerchantCurrencyMap() {
//        return merchantCurrencyMap;
//    }
//
//    /**
//     * @param merchantCurrencyMap the merchantCurrencyMap to set
//     */
//    public void setMerchantCurrencyMap(MerchantCurrencyMap merchantCurrencyMap) {
//        this.merchantCurrencyMap = merchantCurrencyMap;
//    }
//
//    /**
//     * @return the decryptedkeystorepasswordplatformpac
//     */
//    public String getDecryptedkeystorepasswordplatformpac() {
//        return decryptedkeystorepasswordplatformpac;
//    }
//
//    /**
//     * @param decryptedkeystorepasswordplatformpac the
//     * decryptedkeystorepasswordplatformpac to set
//     */
//    public void setDecryptedkeystorepasswordplatformpac(String decryptedkeystorepasswordplatformpac) {
//        this.decryptedkeystorepasswordplatformpac = decryptedkeystorepasswordplatformpac;
//    }
//
//    /**
//     * @return the mLogger
//     */
//    public LogWrapper getmLogger() {
//        return mLogger;
//    }
//
//    /**
//     * @param mLogger the mLogger to set
//     */
//    public void setmLogger(LogWrapper mLogger) {
//        this.mLogger = mLogger;
//    }
//
//    /**
//     * @return the updateOrder
//     */
//    public String getUpdateOrder() {
//        return updateOrder;
//    }
//
//    /**
//     * @param updateOrder the updateOrder to set
//     */
//    public void setUpdateOrder(String updateOrder) {
//        this.updateOrder = updateOrder;
//    }
//
//    
//}
