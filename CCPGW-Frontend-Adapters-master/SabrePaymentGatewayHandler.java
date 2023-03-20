/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 2.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPPGW Front end Adapter: PaymentGatewayHandler
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  07-Apr-2022 
 * ***************************************************************
 */
package com.truteq.ccpgw.payment.gateway.adapter;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

public class SabrePaymentGatewayHandler extends PaymentGatewayHandler {  
    
    private final LogWrapper mLogger = new LogWrapper(SabrePaymentGatewayHandler.class);

    public SabrePaymentGatewayHandler(String originaldataWrite,
            String originaldataRead,
            String authorisationWrite,
            String authorisationRead,
            String captureWrite,
            String captureRead,
            String threedsCreateReq,
            String threedsValidatePaRes,
            String threedsV2Versioning,
            String threedsV2Authorise,
            String threedsV2AuthReqRead,
            String threedsV2AuthReqWrite,
            String threedsV2EnrolledWrite, 
            String acsurl,
            String servletProxy,
            String acsParerqWrite,
            String postilionAuthorise,
            String postilionRefund,
            String postilionCapture,
            String postilionReversal,
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
            String threeDSResultResponse,
            String threeDSIssuerURL,
            String transactionDetailsWrite,
            String transactionDetailsRead
    ){
        super(originaldataWrite,
              originaldataRead, 
              authorisationWrite, 
              authorisationRead, 
              captureWrite, 
              captureRead,
              //"", //No financial write with the Sabre payment gateway handler.
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
              "",
              threeDSResultResponse,
              threeDSIssuerURL,
              transactionDetailsWrite,
              transactionDetailsRead); //No update order with the Sabre payment gateway handler.
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
////import com.truteq.ccpgw.frontend.adapter.sabre.api.AddressType;
////import com.truteq.ccpgw.frontend.adapter.sabre.api.AuthRS;
////import com.truteq.ccpgw.frontend.adapter.sabre.api.CancelOrRefundRS;
////import com.truteq.ccpgw.frontend.adapter.sabre.api.CaptureRS;
////import com.truteq.ccpgw.frontend.adapter.sabre.api.PaymentDetailType;
////import com.truteq.ccpgw.frontend.adapter.sabre.model.AuthRSObject;
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
//import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureRequest;
//import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureResponse;
//import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
//import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalRequest;
//import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalResponse;
//import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.RoutingObj;
//import com.truteq.ccpgw.threeds.objects.PaReqRequestObject;
//import com.truteq.ccpgw.threeds.objects.PaReqResponseObject;
//import com.truteq.ccpgw.threeds.objects.PaResValidateRequestObject;
//import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
//import com.truteq.ccpgw.transaction.manager.model.AcsPareqElement;
//import com.truteq.ccpgw.transaction.manager.model.Authorisation;
//import com.truteq.ccpgw.transaction.manager.model.CaptureElement;
//import com.truteq.ccpgw.transaction.manager.model.MerchantCurrencyMap;
//import com.truteq.ccpgw.transaction.manager.model.OriginalDataElement;
//import com.truteq.general.util.AESEncryptionDecryption;
//import java.util.Base64;
//import java.util.Date;
//
//import com.truteq.general.util.HashAString;
//import java.util.Arrays;
//import java.util.List;
//
///**
// *
// * @author Grant Blaise O'Reilly <gbo@truteq.com>
// */
//public class SabrePaymentGatewayHandler  {
//
//    private String originaldataWrite;
//    private String originaldataRead;
//    private String authorisationWrite;
//    private String authorisationRead;
//    private String captureWrite;
//    private String captureRead;
//    private String threedsCreateReq;
//    private String threedsValidatePaRes;
//    private String acsurl;
//    private String servletProxy;
//    private String acsParerqWrite;
//    private String postilionAuthorise;
//    private String postilionRefund;
//    private String postilionCapture;
//    private String postilionReversal;
//    private String acquiringInstitutionCodePostilion;
//    private String acquiringInstitutionCodeVisa;
//    private String acquiringInstitutionCodeMastercard;
//    private String posDataCode;
//    private String currencyCode;
//    private String merchantCurrencyMapRead;
//    private AESEncryptionDecryption decryptor;
//    private String decryptedUserName;
//    private String decryptedPassword;
//    private MerchantCurrencyMap  merchantCurrencyMap;
//    private String keystore;
//    private String decryptedkeystorepasswordplatformpac;
//    private String merchantCode;
//    private String countryCode;
//    private String merchantType;
//    
//    private final LogWrapper mLogger = new LogWrapper(SabrePaymentGatewayHandler.class);  
//    
//    
//    public SabrePaymentGatewayHandler(String originaldataWrite,
//            String originaldataRead,
//            String authorisationWrite,
//            String authorisationRead,
//            String captureWrite,
//            String captureRead,
//            String threedsCreateReq,
//            String threedsValidatePaRes,
//            String acsurl,
//            String servletProxy,
//            String acsParerqWrite,
//            String postilionAuthorise,
//            String postilionRefund,
//            String postilionCapture,
//            String postilionReversal,
//            String cardAcceptorNameLocation,
//            String acquiringInstitutionCodePostilion,
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
//            String merchantType
//            ) {
//
//        this.originaldataWrite = originaldataWrite;
//        this.originaldataRead = originaldataRead;
//        this.authorisationWrite = authorisationWrite;
//        this.authorisationRead = authorisationRead;
//        this.captureWrite = captureWrite;
//        this.captureRead = captureRead;
//        this.threedsCreateReq = threedsCreateReq;
//        this.threedsValidatePaRes = threedsValidatePaRes;
//        this.acsurl = acsurl;
//        this.servletProxy = servletProxy;
//        this.acsParerqWrite = acsParerqWrite;
//        this.postilionAuthorise = postilionAuthorise;
//        this.postilionRefund = postilionRefund;
//        this.postilionCapture = postilionCapture;
//        this.postilionReversal = postilionReversal;
//        this.acquiringInstitutionCodePostilion = acquiringInstitutionCodePostilion;
//        this.acquiringInstitutionCodeVisa = acquiringInstitutionCodeVisa;
//        this.acquiringInstitutionCodeMastercard = acquiringInstitutionCodeMastercard;
//        this.posDataCode = posDataCode;
//        this.currencyCode = currencyCode;
//        this.merchantCurrencyMapRead = merchantCurrencyMapRead;
//        this.keystore = keystore;
//        this.merchantCode = merchantCode;
//        this.countryCode = countryCode;
//        this.merchantType = merchantType;
//
//        try {
//            decryptor = new AESEncryptionDecryption();
//            decryptedUserName = decryptor.decrypt(communicatorUserName, secret);
//            decryptedPassword = decryptor.decrypt(communicatorPassword, secret);
//            decryptedkeystorepasswordplatformpac = decryptor.decrypt(keystorepasswordplatformpac, secret);
//        } catch (Exception ex) {
//            mLogger.error("Exception with decryption credentials." + ex, new Throwable().getStackTrace()[0]);
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
//    private String byteArrayToHex(byte[] a) {
//        StringBuilder sb = new StringBuilder(a.length * 2);
//        for (byte b : a) {
//            sb.append(String.format("%02x", b));
//        }
//        return sb.toString();
//    }
//
//    private String asciiToHex(String asciiStr) {
//        char[] chars = asciiStr.toCharArray();
//        StringBuilder hex = new StringBuilder();
//        for (char ch : chars) {
//            hex.append(Integer.toHexString((int) ch));
//        }
//
//        return hex.toString();
//    }
//
//    public byte[] hexStringToByteArray(String s) {
//        int len = s.length();
//        byte[] data = new byte[len / 2];
//        for (int i = 0; i < len; i += 2) {
//            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
//                                 + Character.digit(s.charAt(i+1), 16));
//        }
//        return data;
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
//    public String GenerateTransactionID() {
//
//        Date date = new Date();
//        return date.getTime() + "";
//
//    }
//
//    public RoutingObj extractSubfieldsDE127_03(String routing) {
//
//        String source = routing.substring(0, 12);
//        String sink = routing.substring(12, 24);
//        String sourceStan = routing.substring(24, 30);
//        String sinkStan = routing.substring(30, 36);
//        String totalsGroup = routing.substring(36, 48);
//
//        RoutingObj routingObj = new RoutingObj(source, sink, sourceStan, sinkStan, totalsGroup);
//
//        return routingObj;
//
//    }
//
//    
//    private void PersistACS(AcsPareqElement acsPareqElement){
//       mLogger.info("Saving the ACS and PaReq to database", new Throwable().getStackTrace()[0]);
//       SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.decryptedkeystorepasswordplatformpac);
//       sslComms.sendHttpPost(this.getAcsParerqWrite(), acsPareqElement.toJSON().getBytes());         
//    }
//    
//    
//    private void PersistCapture(CaptureElement captureElement) {
//
//        mLogger.info("Saving capture data to database", new Throwable().getStackTrace()[0]);
//        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.decryptedkeystorepasswordplatformpac);
//        sslComms.sendHttpPost(this.getCaptureWrite(), captureElement.toJSON().getBytes());  
//    }
//
//    private void PersistTransaction(String transactionId, String messageType, AuthoriseResponse postilionAuthRes) {
//
//        OriginalDataElement ode = new OriginalDataElement(transactionId,
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
//        mLogger.info("Saving original data element to database: " + ode.getDE90(), new Throwable().getStackTrace()[0]);
//        
//        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.decryptedkeystorepasswordplatformpac);
//        sslComms.sendHttpPost(this.getOriginaldataWrite(), ode.toJSON().getBytes());  
//
//        mLogger.info("Saving authorisation data to database: " + auth.getTransactionId(), new Throwable().getStackTrace()[0]);
//        sslComms.sendHttpPost(this.getAuthorisationWrite(), auth.toJSON().getBytes()); 
//
//    }
//
//    public CaptureElement readCapture(String transactionId) {
//
//        mLogger.info("Read capture data element from database using transactionId= " + transactionId, new Throwable().getStackTrace()[0]);
//        
//        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.decryptedkeystorepasswordplatformpac);
//                  
//        
//        CaptureElement ce = new CaptureElement();
//        ce.setTransactionId(transactionId);
//        Result result = sslComms.sendHttpPost(this.getCaptureRead(), ce.toJSON().getBytes());
//        mLogger.info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//        Gson gson = new Gson();
//        return gson.fromJson(result.getData(), CaptureElement.class);
//    }
//
//    private OriginalDataElement readOriginalDataElement(String stan, String transactionId) {
//
//        mLogger.info("Read original data element from database using STAN = " + stan, new Throwable().getStackTrace()[0]);
//
//        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.decryptedkeystorepasswordplatformpac);
//                  
//        
//        OriginalDataElement ode = new OriginalDataElement();
//        ode.setTransactionId(transactionId);
//        ode.setSystemTraceAuditNumber(stan);
//        Result result = sslComms.sendHttpPost(this.getOriginaldataRead(), ode.toJSON().getBytes());
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//        Gson gson = new Gson();
//        return gson.fromJson(result.getData(), OriginalDataElement.class);
//    }
//
//    private Authorisation readAuthorisation(String transactionId) {
//        mLogger.info("Read authorisation from database using transactionId = " + transactionId, new Throwable().getStackTrace()[0]);
//        
//        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.decryptedkeystorepasswordplatformpac);
//
//        Authorisation auth = new Authorisation();
//        auth.setTransactionId(transactionId);
//        Result result = sslComms.sendHttpPost(this.getAuthorisationRead(), auth.toJSON().getBytes());   
//
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//        return gson.fromJson(result.getData(), Authorisation.class);
//    }
//    
//
//    public AuthRSObject process3DSStepOne(String orderNumber, String transactionId, PaymentDetailType paymentType) {
//
//        AuthRS response = new AuthRS();
//        
//        PaReqRequestObject paReq = new PaReqRequestObject();
//        Cardholder cardHolder = new Cardholder();
//        cardHolder.setPan(paymentType.getPaymentCard().getCardNumber());
//        cardHolder.setExpiry(checkExpiryDate(paymentType.getPaymentCard().getExpireDate()));
//
//        Purchase purchase = new Purchase();
//        purchase.setExponent("2");
//        purchase.setAmount(checkAmount(paymentType.getAmountDetail().getAmount().toString()));
//        purchase.setCurrency(this.merchantCurrencyMap.getCode());
//
//        Merchant merchant = new Merchant();
//        //merchant.setAcquirerBin("123456"); //60130200000
//        //merchant.setCountryCode("100");    //598 
//        
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//        mLogger.info("Card scheme: "+cardType, new Throwable().getStackTrace()[0]);        
//        
//        switch (cardType) {
//            case "CA"://Mastercard
//                mLogger.info("Setting acquire bin for Mastercard.", new Throwable().getStackTrace()[0]);
//                merchant.setAcquirerBin(this.acquiringInstitutionCodeMastercard);
//                break;
//            default: //Visa
//                mLogger.info("Setting acquire bin for Visa.", new Throwable().getStackTrace()[0]);
//                merchant.setAcquirerBin(this.acquiringInstitutionCodeVisa);
//                break;
//        }                
//        
//        merchant.setCountryCode(this.countryCode);        
//        
//        //header.key.3dsorgid=3DS-Organization-ID
//        //header.value.3dsorgid=a93e6455-3ffc-498d-9d64-84fa22dc7095
//        
//        //merchant.setId("a93e6455-3ffc-498d-9d64-84fa22dc7095"); //
//        merchant.setId(this.merchantCode);//merchant.setId("merId");
//        merchant.setName("Sabre");
//        merchant.setUrl("https://www.sabre.com/");
//
//        paReq.setCardholder(cardHolder);
//        paReq.setPurchase(purchase);
//        paReq.setMerchant(merchant);
//        
//        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.decryptedkeystorepasswordplatformpac);
//        Result result = sslComms.sendHttpPost(this.getThreedsCreateReq(), paReq.toJSON().getBytes());          
//
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//
//        String resp = result.getData().replaceAll("message","Message");
//        resp = resp.replaceAll("veres","VERes");
//        resp = resp.replaceAll("ch","CH");
//        
//        Gson gson = new Gson();
//        PaReqResponseObject paResponse = gson.fromJson(resp, PaReqResponseObject.class);
//        mLogger.info("paResponse :"+ paResponse.toJSON(), new Throwable().getStackTrace()[0]);
//        
//        
//       // Part of the ENROLL = "N" Dev                
//       List<Message> messageList = paResponse.getThreeDSecureVERes().getMessage();
//       Message m = messageList.get(0);
//       String enrolled = m.getVERes().getCH().getEnrolled();
//       
//       mLogger.info("Enrolled :"+ enrolled, new Throwable().getStackTrace()[0]);
//       
//       if (enrolled.equals("N")){
//           response.setOrderNumber(orderNumber);
//           response.setTransactionID(transactionId);
//       }
//       else{ 
//        response.setAVSResultCode("A");
//        response.setCVCResultCode("A");
//        response.setDebitCard(Boolean.FALSE);
//        response.setDescription("Approved");
//        response.setOrderNumber(orderNumber);
//        response.setResponseCode("0");
//        response.setTransactionID(transactionId);
//        response.setVersion("");
//        
//        if (servletProxy.toLowerCase().equals("true")){
//            mLogger.info("Servlet Proxy used. ACSURL: "+this.getAcsurl());
//            response.setRedirectURL(this.getAcsurl());
//        }
//        else{
//           mLogger.info("Servlet Proxy NOT used. ACSURL: "+paResponse.getSessionData().getAcsUrl()); 
//           response.setRedirectURL(paResponse.getSessionData().getAcsUrl()); 
//        }
//        
//
//        //3DS response
//        response.setVersion("1.0");
//        response.setResponseCode("1");
//        response.setDescription("3DS Authentication Required");
//        response.setOrderNumber(orderNumber);
//        response.setTransactionID(transactionId);
//        response.setDebitCard(Boolean.FALSE);
//
//        AuthRS.T3DSResult t3ds = new AuthRS.T3DSResult();
//        t3ds.setPARequest(paResponse.getPaReq());
//        
//        if (servletProxy.toLowerCase().equals("true")){
//            mLogger.info("Servlet Proxy used. Issuer URL: "+this.getAcsurl());
//            t3ds.setIssuerURL(this.getAcsurl());
//        }
//        else{
//           mLogger.info("Servlet Proxy NOT used. Issuer URL: "+paResponse.getSessionData().getAcsUrl()); 
//           t3ds.setIssuerURL(paResponse.getSessionData().getAcsUrl()); 
//        }
//                
//        
//        //header.key.3dsorgid=3DS-Organization-ID
//        //header.value.3dsorgid=a93e6455-3ffc-498d-9d64-84fa22dc7095
//        
//        //t3ds.setMD("a93e6455-3ffc-498d-9d64-84fa22dc7095"); //t3ds.setMD("merId");  
//        t3ds.setMD(this.merchantCode); //t3ds.setMD("merId");
//        
//        this.PersistACS(new AcsPareqElement(paResponse.getSessionData().getAcsUrl(),paResponse.getPaReq()));
//        
//        response.setT3DSResult(t3ds);
//       } 
//
//        return new AuthRSObject(response,enrolled);
//    }
//
//    public AuthRS process3DSStepTwo(String orderNumber,
//            String transactionId,
//            PaymentDetailType paymentType) {
//
//        PaResValidateRequestObject paResValidate = new PaResValidateRequestObject();
//
//        paResValidate.setPaRes(paymentType.getPaymentCard().getT3DS().getPAResponse());
//
//        mLogger.info("PAResponse: " + paymentType.getPaymentCard().getT3DS().getPAResponse(), new Throwable().getStackTrace()[0]);
//
//        SessionData sessionData = new SessionData();
//        sessionData.setPaReqCreationTime("20131015 16:45:01");
//        sessionData.setPan(paymentType.getPaymentCard().getCardNumber());
//        sessionData.setAcsUrl("http://www.acs-url.com");
//        sessionData.setScheme("Visa");
//        sessionData.setMasterCardTokenized("false");
//
//        Purchase2 purchase = new Purchase2();
//        purchase.setXid(paymentType.getPaymentCard().getT3DS().getXID());
//        purchase.setDate("20131015 16:45:00");
//        purchase.setAmount(paymentType.getAmountDetail().getAmount().toString());
//        purchase.setCurrency(this.merchantCurrencyMap.getCode());
//        purchase.setExponent("2");
//        purchase.setDesc("This is the description");
//
//        Merchant2 merchant = new Merchant2();
//        //merchant.setAcqBIN("123456");
//        //merchant.setCountry("100");
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//        mLogger.info("Card scheme: "+cardType, new Throwable().getStackTrace()[0]);        
//        
//        switch (cardType) {
//            case "CA"://Mastercard
//                mLogger.info("Setting acquire bin for Mastercard.", new Throwable().getStackTrace()[0]);
//                merchant.setAcqBIN(this.acquiringInstitutionCodeMastercard);
//                break;
//            default: //Visa
//                mLogger.info("Setting acquire bin for Visa.", new Throwable().getStackTrace()[0]);
//                merchant.setAcqBIN(this.acquiringInstitutionCodeVisa);
//                break;
//        }                
//        
//        merchant.setCountry(this.countryCode);  
//        //header.key.3dsorgid=3DS-Organization-ID
//        //header.value.3dsorgid=a93e6455-3ffc-498d-9d64-84fa22dc7095
//        
//        //merchant.setMerID("a93e6455-3ffc-498d-9d64-84fa22dc7095"); //
//        merchant.setMerID(this.merchantCode);//merchant.setMerID("merId");
//        merchant.setName("Sabre");
//        merchant.setUrl("https://www.sabre.com/");
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
//        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.decryptedkeystorepasswordplatformpac);
//        Result result = sslComms.sendHttpPost(this.getThreedsValidatePaRes(), paResValidate.toJSON().getBytes());          
//
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//
//        PaResValidationResponse paResValidResponse = gson.fromJson(result.getData(), PaResValidationResponse.class);
//        mLogger.info("Transaction Status " + paResValidResponse.getTransactionStatus(), new Throwable().getStackTrace()[0]);
//
////        switch(paResValidResponse.getTransactionStatus()){
////            case "Y": return postilionAuth(paResValidResponse, paymentType, orderNumber, transactionId);
////            case "N": 
////            default : return DeclineAuth(orderNumber, transactionId);  
////        }
//        return postilionAuth(paResValidResponse, paymentType, orderNumber, transactionId);
//
//    }
//
//    private AuthRS DeclineAuth(String orderNumber, String transactionId) {
//
//        mLogger.info("Decline!", new Throwable().getStackTrace()[0]);
//        AuthRS response = new AuthRS();
//
//        response.setVersion("1.0");
//        response.setResponseCode("5");
//        response.setDescription("Declined");
//        response.setAVSResultCode("A");
//        response.setCVCResultCode("D");
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
//     * postilionRefund
//     * =========================================================================
//     *
//     * @param primaryAccountNumber
//     * @param transactionAmount
//     * @param accountExpiryDate
//     * @param transactionId
//     * @return
//     * =========================================================================
//     */
//    public CancelOrRefundRS postilionRefund(String primaryAccountNumber,
//            String transactionAmount,
//            String accountExpiryDate,
//            String transactionId,
//            String merchantAccountCode,
//            String currency) {
//
//        mLogger.info("Sending to Postilion Refund.", new Throwable().getStackTrace()[0]);
//        CancelOrRefundRS response = new CancelOrRefundRS();
//
//        CaptureElement captureElement = readCapture(transactionId);
//        //MerchantCurrencyMap map = readMerchantId(merchantAccountCode, currency);
//       
//        
//        AuthoriseRequest authObj = new AuthoriseRequest(primaryAccountNumber,
//                "200000",
//                checkAmount(transactionAmount),
//                captureElement.getTransmissionDateTime(),
//                captureElement.getSystemTraceAuditNumber(),
//                captureElement.getLocalTranTime(),
//                captureElement.getLocalTranDate(),
//                accountExpiryDate,//checkExpiryDate(accountExpiryDate),
//                this.merchantType, //merchantType,            //DE18 
//                "010", //posEntryMode             //DE22
//                captureElement.getPosConditionCode(), //"00", //posConditionCode         //DE25  
//                captureElement.getAcquiringInstitutionCode(),
//                captureElement.getTerminalId(),
//                this.merchantCurrencyMap.getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42    // 30-Jul-2021 Mya
//                this.merchantCurrencyMap.getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43  
//                this.merchantCurrencyMap.getCode(), //"598", //currency <-- This won't work! Needs a curency code! //"598", //currencyCodeTransaction, //DE49    // 30-Jul-2021 Mya
//                captureElement.getPosDataCode());           //posDataCode,             //DE123
//
//        
//        mLogger.info("Refund Authorise Object: "+authObj.toJSON(), new Throwable().getStackTrace()[0]);
//        
//        SSLCommunicator sslComms = new SSLCommunicator(decryptedUserName, decryptedPassword, this.keystore, this.decryptedkeystorepasswordplatformpac);
//        Result result = sslComms.sendHttpPost(this.getPostilionRefund(), authObj.toJSON().getBytes());          
//        
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//
//        Gson gson = new Gson();
//        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);
//
//        Integer responseCode = new Integer(postilionAuthRes.getResponseCode());
//        String description = processResponseCode(postilionAuthRes.getResponseCode());
//
//        mLogger.info("ResponseCode :" + responseCode.toString(), new Throwable().getStackTrace()[0]);
//        mLogger.info("Description  :" + description, new Throwable().getStackTrace()[0]);
//        mLogger.info("Approval Id  :" + postilionAuthRes.getAuthIdResponse(), new Throwable().getStackTrace()[0]);
//
//
//        response.setVersion("1");
//        response.setResponseCode(responseCode.toString());
//        response.setTransactionID(transactionId);
//        response.setDescription(description);
//
//        return response;
//    }
//
//    /**
//     * =========================================================================
//     * postilionCapture
//     * =========================================================================
//     *
//     * @param transactionAmount
//     * @param transactionId
//     * @param merchantAccountCode
//     * @param currency
//     * @return
//     * =========================================================================
//     */
//    public CaptureRS postilionCapture(String transactionId,
//        String transactionAmount, String merchantAccountCode, String currency) {
//
//        Authorisation auth = readAuthorisation(transactionId);
//
//        OriginalDataElement ode = readOriginalDataElement(auth.getSystemTraceAuditNumber(), transactionId);
//
//        RoutingObj routingObj = extractSubfieldsDE127_03(auth.getRouting());
//
//        mLogger.info("Sending to Postilion Capture.", new Throwable().getStackTrace()[0]);
//
//        CaptureRS response = new CaptureRS();
//
//        //MerchantCurrencyMap map = readMerchantId(merchantAccountCode, currency);
//
//        CaptureRequest captureReq = new CaptureRequest(auth.getPrimaryAccountNumber(), //primaryAccountNumber,  //DE2   
//                "000000", //processingCode,        //DE3
//                checkAmount(transactionAmount), //transactionAmount,       // DE4 
//                auth.getSystemTraceAuditNumber(), //DE11
//                auth.getExpiryDate(), //accountExpiryDate, YYMM  //DE14
//                auth.getSettlementDate(),//dateSettlement           //DE15 
//                this.merchantType,//"6300", //merchantType,            //DE18 
//                "010", //posEntryMode             //DE22
//                auth.getPosConditionCode(), //"00", //posConditionCode         //DE25 
//                auth.getAcquiringInstitutionCode(),//acquiringInstitutionCode //DE32 
//                auth.getRetrievalRef(), //retrievalRefString
//                auth.getAuthIdResponse(),//authIdResponse   
//                auth.getTerminalId(), //terminalId,               //DE41 
//                this.merchantCurrencyMap.getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42  // 30-Jul-2021 Mya
//                this.merchantCurrencyMap.getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43 
//                this.merchantCurrencyMap.getCode(), //"598", //currency <-- This won't work! Needs a curency code!currency, //"598", //currencyCodeTransaction,  //DE49 // 30-Jul-2021 Mya
//                ode.getDE90(), // original data element    //DE90
//                auth.getPosDataCode(), //posDataCode,              //DE123
//                auth.getRetrievalRef(), //DE127.11 
//                routingObj.getSinkNode() //DE127.26
//        );
//        
//        SSLCommunicator sslComms = new SSLCommunicator(decryptedUserName, decryptedPassword, this.keystore, this.decryptedkeystorepasswordplatformpac);
//        Result result =  sslComms.sendHttpPost(this.getPostilionCapture(), captureReq.toJSON().getBytes());          
//
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//        CaptureResponse postilionCaptureRes = gson.fromJson(result.getData(), CaptureResponse.class);
//
//        CaptureElement captureElement = new CaptureElement(transactionId,
//                postilionCaptureRes.getMessageType(),
//                postilionCaptureRes.getPrimaryAccountNumber(),
//                postilionCaptureRes.getProcessingCode(),
//                postilionCaptureRes.getTransactionAmount(),
//                postilionCaptureRes.getTransmissionDateTime(),
//                postilionCaptureRes.getSystemTraceAuditNumber(),
//                postilionCaptureRes.getLocalTranTime(),
//                postilionCaptureRes.getLocalTranDate(),
//                postilionCaptureRes.getAccountExpiryDate(),
//                postilionCaptureRes.getPosConditionCode(),
//                postilionCaptureRes.getAcquiringInstitutionCode(),
//                postilionCaptureRes.getRetrievalRefNumber(),
//                postilionCaptureRes.getTerminalId(),
//                postilionCaptureRes.getPosDataCode(),
//                postilionCaptureRes.getRoutingInformation());
//
//        PersistCapture(captureElement);
//
//        Integer responseCode = new Integer(postilionCaptureRes.getResponseCode());
//        String description = processResponseCode(postilionCaptureRes.getResponseCode());
//        response.setVersion("1.0");
//        response.setResponseCode(responseCode.toString());
//        response.setTransactionID(transactionId);
//        response.setDescription(description);
//
//        return response;
//    }
//
//    /**
//     * =========================================================================
//     * postilionReversal
//     * =========================================================================
//     * @param transactionId
//     * @param transactionAmount
//     * @param merchantAccountCode
//     * @param currency
//     * @return
//     * =========================================================================
//     */
//    public CancelOrRefundRS postilionReversal(String transactionId,
//            String transactionAmount,
//            String merchantAccountCode,
//            String currency) {
//
//        mLogger.info("Sending to Postilion Reversal.", new Throwable().getStackTrace()[0]);
//
//        CancelOrRefundRS response = new CancelOrRefundRS();
//
//        Authorisation auth = readAuthorisation(transactionId);
//
//        OriginalDataElement ode = readOriginalDataElement(auth.getSystemTraceAuditNumber(), transactionId);
//
//        mLogger.info("Authorisation Object: "+auth.toJSON(), new Throwable().getStackTrace()[0]);
//        mLogger.info("OriginalDataElement Object: "+ode.toJSON(), new Throwable().getStackTrace()[0]);
//        
//        mLogger.info("Routing: "+auth.getRouting(), new Throwable().getStackTrace()[0]);
//        
//        RoutingObj routingObj = extractSubfieldsDE127_03(auth.getRouting());
//        
//        mLogger.info("Routing Object: "+routingObj.toJSON(), new Throwable().getStackTrace()[0]);
//
//        //MerchantCurrencyMap map = readMerchantId(merchantAccountCode, currency);
//        ReversalRequest reversalReq = new ReversalRequest(auth.getPrimaryAccountNumber(), //primaryAccountNumber,  //DE2   
//                auth.getProcessingCode(), //processingCode,        //DE3
//                checkAmount(auth.getAmount().toString()), //transactionAmount,       // DE4  
//                auth.getTransmissionDateTime(),
//                auth.getSystemTraceAuditNumber(),
//                auth.getLocalTranTime(),
//                auth.getLocalTranDate(),
//                auth.getExpiryDate(), //accountExpiryDate, YYMM   //DE14
//                auth.getSettlementDate(),//dateSettlement            //DE15
//                this.merchantType, //"6300", //merchantType,    4511         //DE18 
//                "010", //posEntryMode              //DE22
//                auth.getPosConditionCode(), //posConditionCode       //DE25    
//                auth.getTerminalId(), //terminalId,               //DE41 
//                this.merchantCurrencyMap.getAutoGeneratedMid(), //merchantId, //merchantId,               //DE42   // 30-Jul-2021 Mya
//                this.merchantCurrencyMap.getCardAcceptorNameLocation(),//cardAcceptorNameLocation,//cardAcceptorNameLocation, //DE43 
//                this.merchantCurrencyMap.getCode(), //"598", //currency <-- This won't work! Needs a curency code!currency, //"598", //currencyCodeTransaction,  //DE49 // 30-Jul-2021 Mya
//                "4000", //message reason code       //DE56
//                ode.getDE90(), //original data element     //DE90
//                auth.getPosDataCode(), //posDataCode,              //DE123
//                auth.getRouting(), // Routing                  //DE127.03 
//                auth.getRetrievalRef(), //orginal key               //DE127.11 
//                routingObj.getSinkNode().trim() //original data      //DE127.26
//        );
//
//         mLogger.info("ReversalRequest Object: "+reversalReq.toJSON(), new Throwable().getStackTrace()[0]);
//         mLogger.info("URL: "+this.getPostilionReversal(), new Throwable().getStackTrace()[0]);
//        
//        SSLCommunicator sslComms = new SSLCommunicator(decryptedUserName, decryptedPassword, this.keystore, this.decryptedkeystorepasswordplatformpac);
//        Result result = sslComms.sendHttpPost(this.getPostilionReversal(), reversalReq.toJSON().getBytes());  
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//        ReversalResponse postilionReversalRes = gson.fromJson(result.getData(), ReversalResponse.class);
//
//        Integer responseCode = new Integer(postilionReversalRes.getResponseCode());
//        String description = processResponseCode(postilionReversalRes.getResponseCode());
//        response.setVersion("1.0");
//        response.setResponseCode(responseCode.toString());
//        response.setTransactionID(transactionId);
//        response.setDescription(description);
//
//        return response;
//    }
//    
//    /**
//     * =========================================================================
//     * processPosDataCode
//     * =========================================================================    
//     * Grant O'Reilly(2021-08-05): PLEASE DO NOT CHANGE without speaking to me!
//     * -------------------------------------------------------------------------
//     * @param cardType
//     * @param transactionStatus
//     * =========================================================================
//     */
//    private void processPosDataCode(String cardType, String transactionStatus) {
//        
//        mLogger.info("Initial PosDataCode: " + this.posDataCode, new Throwable().getStackTrace()[0]);
//
//        String subPosDataCode = this.posDataCode.substring(0, 13);
//
//        switch (cardType) {
//            case "CA"://Mastercard
//                mLogger.info("Processing PosDataCode for Mastercard.", new Throwable().getStackTrace()[0]);
//                switch (transactionStatus.toUpperCase()) {
//                    case "Y":
//                        this.posDataCode = subPosDataCode + "92";
//                        break;                            
//                    case "N":
//                    case "U":
//                        this.posDataCode = subPosDataCode + "90";
//                        break;
//                    case "A":
//                        this.posDataCode = subPosDataCode + "91";
//                        break;
//                }
//                break;
//            
//            default: //Visa
//                mLogger.info("Processing PosDataCode for Visa card.", new Throwable().getStackTrace()[0]);
//                switch (transactionStatus.toUpperCase()) {
//                    case "Y":
//                        this.posDataCode = subPosDataCode + "92";
//                        break;
//                    case "N":
//                    case "A":
//                    case "ERROR":
//                        this.posDataCode = subPosDataCode + "91";
//                        break;
//                    case "U": 
//                        this.posDataCode = subPosDataCode + "90";
//                        break;
//                    case "":
//                        this.posDataCode = subPosDataCode + "91";
//                        break;
//                    case "BLANK":
//                        this.posDataCode = subPosDataCode + "91";
//                        break;    
//                    case "BLANK2":
//                        this.posDataCode = subPosDataCode + "90";
//                        break;                         
//                    // The default here is usually "" for Visa originally it was set to 91
//                    // but from  SIT21-1105 it was decided to make 90 - Grant O'Reilly 
//                    // 2021-09-20    
//                    //default: this.posDataCode = subPosDataCode + "91";
//                      default: this.posDataCode = subPosDataCode + "90";  
//                }
//
//        }
//        mLogger.info("PosDataCode: "+this.posDataCode, new Throwable().getStackTrace()[0]);
//
//    }
//    /**
//     * =========================================================================
//     * processUCAFCollectionInd
//     * =========================================================================    
//     * Grant O'Reilly(2021-08-05): PLEASE DO NOT CHANGE without speaking to me! 
//     * -------------------------------------------------------------------------
//     * UCAF collection indicator
//     * -------------------------------------------------------------------------
//     *  0 = UCAF data collection is not supported at the merchant's web site.
//     *  1 = UCAF data collection is supported by the merchant, but UCAF data was not populated.
//     *  2 = UCAF data collection is supported by the merchant and the UCAF data was populated.
//     * -------------------------------------------------------------------------
//     * @param transactionStatus
//     * @return 
//     * =========================================================================
//     */
//    private int processUCAFCollectionInd(String transactionStatus){
//
//            switch (transactionStatus.toUpperCase()) {
//                case "Y": return 2;
//                case "N":
//                case "U": return 0;
//                case "A": return 1;
//                default : return 0;
//            }     
//    }
//    
//    private String processUCAFPosition2(String transactionStatus){
//            switch (transactionStatus.toUpperCase()) {
//                case "Y": return "j";
//                case "A": return "h";
//                case "N":
//                case "U":         
//                default : return "";
//            }           
//    }
//    /**
//     * =========================================================================
//     * processSecureResult
//     * =========================================================================    
//     * Grant O'Reilly(2021-08-12): PLEASE DO NOT CHANGE without speaking to me! 
//     * -------------------------------------------------------------------------
//     * @param transactionStatus
//     * @return 
//     * =========================================================================
//     */
//    //private String processSecureResult(String transactionStatus, String cavv){
//    private String processSecureResult(String transactionStatus){
//
//        switch (transactionStatus.toUpperCase()) {
//            case "" :
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
//            default: return "0"; 
//        }
//    }    
//    
//    /**
//     * =========================================================================
//     * ThreeDSTransaction
//     * =========================================================================
//     * @param paResValidResponse
//     * @param paymentType
//     * @param merchantAccountCode
//     * @return 
//     * =========================================================================
//     */
//    private AuthoriseResponse ThreeDSTransaction(PaResValidationResponse paResValidResponse,
//            PaymentDetailType paymentType) {
//
//        mLogger.info("Processing 3DS card.", new Throwable().getStackTrace()[0]);
//        //byte[] decoded_xid = Base64.getDecoder().decode(paResValidResponse.getXid());
//        byte[] decoded_xid = Base64.getDecoder().decode("");
//        byte[] decoded_cavv = Base64.getDecoder().decode(paResValidResponse.getCavv());
//        byte[] ds = concatBytes(decoded_xid, decoded_cavv);
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//        String threeDSecureData = paResValidResponse.getXid() + paResValidResponse.getCavv();
//        String transactionStatus = paResValidResponse.getTransactionStatus();
//        String cavv = paResValidResponse.getCavv();
//        String posConditionCode = "59";
//
//        mLogger.info("3DSecureData: " + threeDSecureData, new Throwable().getStackTrace()[0]);
//        mLogger.info("Decoded XiD length: " + String.valueOf(decoded_xid.length) + " Byte array: " + String.valueOf(displayBytes(decoded_xid)), new Throwable().getStackTrace()[0]);
//        mLogger.info("Decoded CAVV length: " + String.valueOf(decoded_cavv.length) + " Byte array: " + String.valueOf(displayBytes(decoded_cavv)), new Throwable().getStackTrace()[0]);
//        mLogger.info("3DSecureData length: " + String.valueOf(ds.length) + " Byte array: " + String.valueOf(displayBytes(ds)), new Throwable().getStackTrace()[0]);
//        mLogger.info("Payment method code: " + paymentType.getPaymentMethod().getPaymentMethodCode(), new Throwable().getStackTrace()[0]);
//
//        String avd = AddressVerificationFormat(paymentType.getBillingAddress());
//
//        mLogger.info("AVD: " + avd, new Throwable().getStackTrace()[0]);
//
//        AuthoriseRequest authObj;
//        if (cardType.equals("CA")) { //This is Mastercard
//
//            mLogger.info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
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
//            mLogger.info("UCAF: " + ucaf, new Throwable().getStackTrace()[0]);
//            
//            posConditionCode = "06";
//            mLogger.info("pos Condition Code: "+posConditionCode, new Throwable().getStackTrace()[0]);
//
//            processPosDataCode(cardType, transactionStatus);
//            mLogger.info("ucaf Collection Ind : "+ucafCollectionInd, new Throwable().getStackTrace()[0]);
//            mLogger.info("CAVV : "+cavv, new Throwable().getStackTrace()[0]);
//            mLogger.info("Transaction Status: "+transactionStatus, new Throwable().getStackTrace()[0]);
//
//            authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
//                    "000000", //processingCode,          //DE3  
//                    checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
//                    checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
//                    this.merchantType, //"6300", //merchantType,            //DE18 
//                    "010", //posEntryMode             //DE22
//                    posConditionCode,//"00", //posConditionCode         //DE25 
//                    acquiringInstitutionCodePostilion, //acquiringInstitutionCode //DE32 
//                    this.merchantCurrencyMap.getAutoGeneratedTid(),//terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
//                    this.merchantCurrencyMap.getAutoGeneratedMid(),//merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
//                    this.merchantCurrencyMap.getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43 
//                    this.merchantCurrencyMap.getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. ,//"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
//                    posDataCode, //DE123 // 4-Aug-2021 Grant O'Reilly
//                    paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
//                    "", //DE127.15
//                    "", //DE127.27
//                    "", //AmericanExpressCardIdentifier, ////DE127.28 
//                    null, //threeDSecureData,        //DE127.29
//                    "", //threeDSecureResult,      //DE127.30
//                    ucaf,  // 4-Aug-2021 Grant O'Reilly
//                    null); // 20-Oct-2021 Grant O'Reilly
//        } else {
//
//            mLogger.info("Processing Visa card .....", new Throwable().getStackTrace()[0]);
//
//            String secureResult = processSecureResult(transactionStatus);
//            
//            processPosDataCode(cardType, transactionStatus);
//            
//            mLogger.info("Secure Result [DE127.30]: "+secureResult, new Throwable().getStackTrace()[0]);
//            mLogger.info("CAVV : "+cavv, new Throwable().getStackTrace()[0]);
//            mLogger.info("Transaction Status: "+transactionStatus, new Throwable().getStackTrace()[0]);
//            
//            posConditionCode = "59";
//            mLogger.info("pos Condition Code: "+posConditionCode, new Throwable().getStackTrace()[0]);
//            
//            
//            //------------------------------------------------------------------
//            // Please DONOT remove this code 2021-11-24
//            // Grant O'Reilly : This is an update due to issues on Visa
//            // Need to replicate the method use for Mastyercard but include the 
//            // 
//            //------------------------------------------------------------------
//            mLogger.info("PaRes XiD: "+paResValidResponse.getXid());
//            mLogger.info("PaRes CAVV: "+paResValidResponse.getCavv());
//            String hexValue = byteArrayToHex(ds);
//            mLogger.info("CAVV Hex: "+(hexValue));
//            mLogger.info("CAVV ASCII: "+(asciiToHex(hexValue)));
//            
//            byte[] dsBytes = hexStringToByteArray(asciiToHex(byteArrayToHex(ds)));
//            
//            mLogger.info("CAVV Hex BYTES: "+Arrays.toString(dsBytes));
//            
//            authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
//                    "000000", //processingCode,          //DE3  
//                    checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
//                    checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
//                    this.merchantType, //"6300", //merchantType,            //DE18 
//                    "010", //posEntryMode             //DE22
//                    posConditionCode, //"00", //posConditionCode         //DE25 
//                    acquiringInstitutionCodePostilion, //acquiringInstitutionCode //DE32 
//                    this.merchantCurrencyMap.getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
//                    this.merchantCurrencyMap.getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
//                    this.merchantCurrencyMap.getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43 
//                    this.merchantCurrencyMap.getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. //"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
//                    posDataCode, //DE123 // 4-Aug-2021 Grant O'Reilly
//                    paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
//                    avd, //DE127.15
//                    "", //DE127.27
//                    "", //AmericanExpressCardIdentifier, ////DE127.28 
//                    //ds, //threeDSecureData,        //DE127.29
//                    dsBytes, //threeDSecureData,     //DE127.29
//                    secureResult);              //threeDSecureResult,      //DE127.30  //"2"  // 4-Aug-2021 Grant O'Reilly
//        }
//        
//        SSLCommunicator sslComms = new SSLCommunicator(decryptedUserName, decryptedPassword,this.keystore, this.decryptedkeystorepasswordplatformpac);
//        Result result = sslComms.sendHttpPost(this.getPostilionAuthorise(), authObj.toJSON().getBytes());          
//        
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);
//
//        return postilionAuthRes;
//    }
//    /**
//     * =========================================================================
//     * NonThreeDSTransaction
//     * =========================================================================
//     * @param paResValidResponse
//     * @param paymentType
//     * @param merchantAccountCode
//     * @return 
//     */
//    private AuthoriseResponse NonThreeDSTransaction(PaResValidationResponse paResValidResponse,
//            PaymentDetailType paymentType) {
//        mLogger.info("Processing non-3DS card.", new Throwable().getStackTrace()[0]);
//
//        String transactionStatus = paResValidResponse.getTransactionStatus();
//        String cavv = paResValidResponse.getCavv();
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//        
//        if(paResValidResponse.getXid()!=null){ 
//            byte[] decoded_xid = Base64.getDecoder().decode(paResValidResponse.getXid());
//
//            mLogger.info("Decoded XiD length: " + String.valueOf(decoded_xid.length) + " Byte array: " + String.valueOf(displayBytes(decoded_xid)), new Throwable().getStackTrace()[0]);
//        }
//        
//        String secureResult = "";
//        int ucafCollectionInd = 0;
//        String ucaf = ucafCollectionInd + "";
//        String posConditionCode = "59";
//        
//        if(transactionStatus!=null){ 
//        
//            if (cardType.equals("CA")) { //This is Mastercard
//                mLogger.info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
//                processPosDataCode(cardType,transactionStatus);
//                posConditionCode = "06";
//                mLogger.info("ucaf Collection Ind : "+ucafCollectionInd, new Throwable().getStackTrace()[0]);
//                mLogger.info("CAVV : "+cavv, new Throwable().getStackTrace()[0]);
//                mLogger.info("Transaction Status: "+transactionStatus, new Throwable().getStackTrace()[0]);
//                mLogger.info("pos Condition Code: "+posConditionCode, new Throwable().getStackTrace()[0]);
//            } else {
//
//                mLogger.info("Processing Visa card .....", new Throwable().getStackTrace()[0]);
//                processPosDataCode(cardType,transactionStatus);
//                secureResult = processSecureResult(transactionStatus);
//                ucaf = null;
//                posConditionCode = "59";
//                mLogger.info("Secure Result [DE127.30]: "+secureResult, new Throwable().getStackTrace()[0]);
//                mLogger.info("CAVV : "+cavv, new Throwable().getStackTrace()[0]);
//                mLogger.info("Transaction Status: "+transactionStatus, new Throwable().getStackTrace()[0]);
//                mLogger.info("pos Condition Code: "+posConditionCode, new Throwable().getStackTrace()[0]);
//            }
//        }
//        else{
//            if (cardType.equals("CA")) { //This is Mastercard
//                mLogger.info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
//                posConditionCode = "06";
//                processPosDataCode(cardType,"U");
//            }
//            else{
//                mLogger.info("Processing Visa card .....", new Throwable().getStackTrace()[0]);
//                processPosDataCode(cardType,"BLANK");
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
//                this.merchantType, //"6300", //merchantType,            //DE18 
//                "010", //posEntryMode             //DE22
//                posConditionCode, //"00", //posConditionCode         //DE25 
//                acquiringInstitutionCodePostilion, //acquiringInstitutionCode //DE32 
//                this.merchantCurrencyMap.getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
//                this.merchantCurrencyMap.getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya 
//                this.merchantCurrencyMap.getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43 
//                this.merchantCurrencyMap.getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. , //"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
//                posDataCode, //posDataCode,             //DE123
//                paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
//                "", //DE127.15
//                "", //DE127.27
//                "", //AmericanExpressCardIdentifier, ////DE127.28 
//                null, //threeDSecureData,        //DE127.29
//                secureResult, //threeDSecureResult,      //DE127.30
//                ucaf,
//                null);
//
//        SSLCommunicator sslComms = new SSLCommunicator(decryptedUserName, decryptedPassword,this.keystore, this.decryptedkeystorepasswordplatformpac);
//        Result result = sslComms.sendHttpPost(this.getPostilionAuthorise(), authObj.toJSON().getBytes());          
//        
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//
//        Gson gson = new Gson();
//        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);
//
//        return postilionAuthRes;
//    }
//    /**
//     * 
//     * @param response
//     * @param paymentType
//     * @return 
//     */
//    public AuthRS NonThreeDSTransaction(AuthRS response, PaymentDetailType paymentType){
//        //Visa will be CAVV resultcode blank and posdata 90, UCAF 0 and posdata 90
//        mLogger.info("Processing non-3DS card.", new Throwable().getStackTrace()[0]);
//        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
//
//        String secureResult = "";
//        int ucafCollectionInd = 0;
//        String ucaf = ucafCollectionInd + "";
//        String posConditionCode = "59";
//
//        if (cardType.equals("CA")) { //This is Mastercard
//            mLogger.info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
//            processPosDataCode(cardType,"N");
//            posConditionCode = "06";
//            mLogger.info("pos Condition Code: "+posConditionCode, new Throwable().getStackTrace()[0]);
//        } else {
//            
//            mLogger.info("Processing Visa card .....");
//            processPosDataCode(cardType,"BLANK2");
//            posConditionCode = "59";
//            mLogger.info("pos Condition Code: "+posConditionCode, new Throwable().getStackTrace()[0]);
//            ucaf = null;
//        }
//              
//        AuthoriseRequest authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
//                "000000", //processingCode,          //DE3  
//                checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
//                checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
//                this.merchantType, //"6300", //merchantType,            //DE18 
//                "010", //posEntryMode             //DE22
//                posConditionCode, //"00", //posConditionCode         //DE25 
//                acquiringInstitutionCodePostilion, //acquiringInstitutionCode //DE32 
//                this.merchantCurrencyMap.getAutoGeneratedTid(),//terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
//                this.merchantCurrencyMap.getAutoGeneratedMid(),//merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
//                this.merchantCurrencyMap.getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43 
//                this.merchantCurrencyMap.getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. ,//"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
//                posDataCode, //DE123 // 4-Aug-2021 Grant O'Reilly
//                paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
//                "", //DE127.15
//                "", //DE127.27
//                "", //AmericanExpressCardIdentifier, ////DE127.28 
//                null, //threeDSecureData,        //DE127.29
//                secureResult, //threeDSecureResult,      //DE127.30
//                ucaf,  // 4-Aug-2021 Grant O'Reilly 
//                null); // 20-Oct-2021 Grant O'Reilly 
//        
//        
//        SSLCommunicator sslComms = new SSLCommunicator(decryptedUserName, decryptedPassword,this.keystore, this.decryptedkeystorepasswordplatformpac);
//         Result result = sslComms.sendHttpPost(this.getPostilionAuthorise(), authObj.toJSON().getBytes());          
//        
//        
//        mLogger.debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
//
//        Gson gson = new Gson();
//        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);
//
//        return processPostilionAuthRes(postilionAuthRes,
//                                       null,
//                                       response.getOrderNumber(),
//                                       response.getTransactionID());
//    }
//    
//    
//    /**
//     * =========================================================================
//     * postilionAuth
//     * =========================================================================
//     *
//     * @param paResValidResponse
//     * @param paymentType
//     * @param orderNumber
//     * @param transactionId
//     * @return
//     * =========================================================================
//     */
//    private AuthRS postilionAuth(PaResValidationResponse paResValidResponse,
//            PaymentDetailType paymentType,
//            String orderNumber,
//            String transactionId) {
//
//        mLogger.info("Sending to Postilion Authorise.", new Throwable().getStackTrace()[0]);
//
//        AuthoriseResponse postilionAuthRes;
//
//        if (paResValidResponse.getCavv() != null) {
//            postilionAuthRes = ThreeDSTransaction(paResValidResponse, paymentType);
//        } else {
//            postilionAuthRes = NonThreeDSTransaction(paResValidResponse, paymentType);
//        }
//        
//        return processPostilionAuthRes(postilionAuthRes,
//                                        paResValidResponse,
//                                        orderNumber,
//                                        transactionId);
//    }
//    
//    /**
//     * 
//     * @param postilionAuthRes
//     * @return 
//     */
//    private AuthRS processPostilionAuthRes(AuthoriseResponse postilionAuthRes,
//                                           PaResValidationResponse paResValidResponse,
//                                           String orderNumber,
//                                           String transactionId){
//        AuthRS response = new AuthRS();
//        
//        Integer responseCode = new Integer(postilionAuthRes.getResponseCode());
//        String description = processResponseCode(postilionAuthRes.getResponseCode());
//
//        if ((responseCode == 0) || (responseCode == 91)) {
//            mLogger.info("Persisting transaction as response is approved.", new Throwable().getStackTrace()[0]);
//            PersistTransaction(transactionId, "0100", postilionAuthRes);
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
//
//        mLogger.info("ResponseCode :" + responseCode.toString(), new Throwable().getStackTrace()[0]);
//        mLogger.info("Description  :" + description, new Throwable().getStackTrace()[0]);
//        mLogger.info("Approval Id  :" + postilionAuthRes.getAuthIdResponse(), new Throwable().getStackTrace()[0]);
//        mLogger.info("AVS          :" + avs, new Throwable().getStackTrace()[0]);
//        mLogger.info("CVC          : ###(" + HashAString.hash(cvc) + ")", new Throwable().getStackTrace()[0]);
//
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
//        response.setAVSResultCode(avs);
//        response.setCVCResultCode(cvc); //response.setCVCResultCode("A");//response.setCVCResultCode(cvc);
//        response.setDebitCard(Boolean.FALSE);
//        response.setOrderNumber(orderNumber);
//        response.setTransactionID(transactionId);
//        response.setDebitCard(Boolean.FALSE);
//
//        if (paResValidResponse != null){
//            AuthRS.T3DSResult t3ds = new AuthRS.T3DSResult();
//
//            t3ds.setCAVV(paResValidResponse.getCavv()); //t3ds.setCAVV("ANANANAN");
//            t3ds.setECI(paResValidResponse.getEci()); //t3ds.setECI("5");  // Electronic commerce indicator 
//
//            response.setT3DSResult(t3ds);
//        }
//
//        return response;        
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
//     * @return the originaldataRead
//     */
//    public String getOriginaldataRead() {
//        return originaldataRead;
//    }
//
//    /**
//     * @return the authorisationWrite
//     */
//    public String getAuthorisationWrite() {
//        return authorisationWrite;
//    }
//
//    /**
//     * @return the authorisationRead
//     */
//    public String getAuthorisationRead() {
//        return authorisationRead;
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
//     * @return the threedsValidatePaRes
//     */
//    public String getThreedsValidatePaRes() {
//        return threedsValidatePaRes;
//    }
//
//    /**
//     * @return the postilionAuthorise
//     */
//    public String getPostilionAuthorise() {
//        return postilionAuthorise;
//    }
//
//    /**
//     * @return the postilionRefund
//     */
//    public String getPostilionRefund() {
//        return postilionRefund;
//    }
//
//    /**
//     * @return the postilionCapture
//     */
//    public String getPostilionCapture() {
//        return postilionCapture;
//    }
//
//    /**
//     * @return the postilionReversal
//     */
//    public String getPostilionReversal() {
//        return postilionReversal;
//    }
//
//    /**
//     * @return the captureWrite
//     */
//    public String getCaptureWrite() {
//        return captureWrite;
//    }
//
//    /**
//     * @return the captureRead
//     */
//    public String getCaptureRead() {
//        return captureRead;
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
//     * 
//     * @param merchantAccountCode
//     * @param currency 
//     */
//    //=========================================== 
//    // initially added 30-Jul-2021 Mya
//    // updated to setMerchantCurrencyMap by Grant O'Reilly 09-Aug-2021
//    //===========================================
//    // retrieve reference data from the database according to the currency and merchant account code sent by the client, in this case Sabre
//    //===========================================
//    public void setMerchantCurrencyMap(String merchantAccountCode, String currency) {
//        mLogger.info("Read MerchantCurrencyMap from database using merchantAccountCode and currency = " + merchantAccountCode + " and " + currency, new Throwable().getStackTrace()[0]);
//
//        this.merchantCurrencyMap = new MerchantCurrencyMap();
//        this.merchantCurrencyMap.setMerchantAccountCode(merchantAccountCode);
//        this.merchantCurrencyMap.setCurrency(currency);
//        
//        mLogger.debug("keystore: "+ this.keystore);
//        mLogger.debug("keystorepassword: "+ this.decryptedkeystorepasswordplatformpac);
//        
//        SSLCommunicator sslComms = new SSLCommunicator(this.keystore, this.decryptedkeystorepasswordplatformpac);
//        Result result = sslComms.sendHttpPost(this.getMerchantCurrencyMapRead(), this.merchantCurrencyMap.toJSON().getBytes());          
//        
//        Gson gson = new Gson();
//        this.merchantCurrencyMap = gson.fromJson(result.getData(), MerchantCurrencyMap.class);
//        
//        String currCode = this.merchantCurrencyMap.getCode();
//        if (currCode.length()<3){
//            currCode = "0"+currCode;
//            this.merchantCurrencyMap.setCode(currCode);
//        }    
//       
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
//}
