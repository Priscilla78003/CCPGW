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

import com.google.gson.Gson;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.netcetera.model.CH;
import com.truteq.ccpgw.netcetera.model.Cardholder;
import com.truteq.ccpgw.netcetera.model.Merchant;
import com.truteq.ccpgw.netcetera.model.Merchant2;
import com.truteq.ccpgw.netcetera.model.Message;
import com.truteq.ccpgw.netcetera.model.PAReq;
import com.truteq.ccpgw.netcetera.model.PaResValidationResponse;
import com.truteq.ccpgw.netcetera.model.Purchase;
import com.truteq.ccpgw.netcetera.model.Purchase2;
import com.truteq.ccpgw.netcetera.model.SessionData;
import com.truteq.ccpgw.netcetera.model.ThreeDSecureType;
import com.truteq.ccpgw.payment.gateway.adapter.threeDs.v2.x.ThreeDSV2xHandler;
import com.truteq.ccpgw.payment.gateway.api.json.Order;
import com.truteq.ccpgw.payment.gateway.api.json.PaymentDetails;
import com.truteq.ccpgw.payment.gateway.api.json.TransactionFullDetails;
import com.truteq.ccpgw.payment.gateway.api.soap.AddressType;
import com.truteq.ccpgw.payment.gateway.api.soap.AuthRS;
import com.truteq.ccpgw.payment.gateway.api.soap.CancelOrRefundRS;
import com.truteq.ccpgw.payment.gateway.api.soap.CaptureRS;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentDetailType;
import com.truteq.ccpgw.payment.gateway.model.AuthRSObject;
import com.truteq.ccpgw.payment.gateway.model.PAResponseObject;
import com.truteq.ccpgw.payment.gateway.util.DataEncryption;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.RoutingObj;
import com.truteq.ccpgw.threeds.objects.PaReqRequestObject;
import com.truteq.ccpgw.threeds.objects.PaReqResponseObject;
import com.truteq.ccpgw.threeds.objects.PaResValidateRequestObject;
import com.truteq.ccpgw.threeds.v2.objects.authentication.MessageExtensionAttribute;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerAuthenticationRequest;
import com.truteq.ccpgw.threeds.v2.objects.results.response.ThreeDSServerResultsResponse;
import com.truteq.ccpgw.threeds.v2.objects.versioning.AcsProtocolVersionData;
import com.truteq.ccpgw.threeds.v2.objects.versioning.Enrolled;
import com.truteq.ccpgw.threeds.v2.objects.versioning.ThreeDSServerVersioningV3Response;
//import com.truteq.ccpgw.threeds.v2.objects.versioning.ThreeDSServerVersioningResponse;
import com.truteq.ccpgw.threeds.v2.objects.versioning.ThreeDSV2ServerVersioningRequest;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import com.truteq.ccpgw.transaction.manager.model.AcsPareqElement;
import com.truteq.ccpgw.transaction.manager.model.Authorisation;
import com.truteq.ccpgw.transaction.manager.model.CaptureElement;
import com.truteq.ccpgw.transaction.manager.model.Financial;
import com.truteq.ccpgw.transaction.manager.model.MerchantCurrencyMap;
import com.truteq.ccpgw.transaction.manager.model.OriginalDataElement;
import com.truteq.general.util.AESEncryptionDecryption;
import com.truteq.general.util.HashAString;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public abstract class PaymentGatewayHandler {

    private String originaldataWrite;
    private String originaldataRead;
    private String authorisationWrite;
    private String authorisationRead;
    private String captureWrite;
    private String captureRead;
    //private String financialWrite;
    private String threedsCreateReq;
    private String threedsValidatePaRes;
    private String threedsV2Versioning;
    private String threedsV2Authorise;
    private String threedsV2AuthReqRead;
    private String threedsV2AuthReqWrite;
    private String threedsV2EnrolledWrite;
    private String acsurl;
    private String servletProxy;
    private String acsParerqWrite;
    private String postilionAuthorise;
    private String postilionRefund;
    private String postilionCapture;
    private String postilionReversal;
    private String acquiringInstitutionCodePostilion;
    private String merchantId;
    private String acquiringInstitutionCodeVisa;
    private String acquiringInstitutionCodeMastercard;
    private String posDataCode;
    private String currencyCode;
    private String merchantCurrencyMapRead;
    private AESEncryptionDecryption decryptor;
    private String decryptedUserName;
    private String decryptedPassword;
    private MerchantCurrencyMap merchantCurrencyMap;
    private String keystore;
    private String decryptedkeystorepasswordplatformpac;
    private String merchantCode;
    private String countryCode;
    private String merchantType;
    private String updateOrder;
    private String threeDSResultResponse;
    private String threeDSIssuerURL;
    private String transactionDetailsWrite;
    private String transactionDetailsRead;

    private LogWrapper mLogger = new LogWrapper(PaymentGatewayHandler.class);

    public PaymentGatewayHandler(
            String originaldataWrite,
            String originaldataRead,
            String authorisationWrite,
            String authorisationRead,
            String captureWrite,
            String captureRead,
            //String financialWrite,
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
            String updateOrder,
            String threeDSResultResponse,
            String threeDSIssuerURL,
            String transactionDetailsWrite,
            String transactionDetailsRead        
    ) {

        this.originaldataWrite = originaldataWrite;
        this.originaldataRead = originaldataRead;
        this.authorisationWrite = authorisationWrite;
        this.authorisationRead = authorisationRead;
        this.captureWrite = captureWrite;
        this.captureRead = captureRead;
        //this.financialWrite = financialWrite;
        this.threedsCreateReq = threedsCreateReq;
        this.threedsValidatePaRes = threedsValidatePaRes;
        this.threedsV2Versioning = threedsV2Versioning;
        this.threedsV2Authorise = threedsV2Authorise;
        this.threedsV2AuthReqRead = threedsV2AuthReqRead;
        this.threedsV2AuthReqWrite = threedsV2AuthReqWrite;
        this.threedsV2EnrolledWrite = threedsV2EnrolledWrite;
        this.acsurl = acsurl;
        this.servletProxy = servletProxy;
        this.acsParerqWrite = acsParerqWrite;
        this.postilionAuthorise = postilionAuthorise;
        this.postilionRefund = postilionRefund;
        this.postilionCapture = postilionCapture;
        this.postilionReversal = postilionReversal;
        this.acquiringInstitutionCodePostilion = acquiringInstitutionCodePostilion;
        this.merchantId = merchantId;
        this.acquiringInstitutionCodeVisa = acquiringInstitutionCodeVisa;
        this.acquiringInstitutionCodeMastercard = acquiringInstitutionCodeMastercard;
        this.posDataCode = posDataCode;
        this.currencyCode = currencyCode;
        this.merchantCurrencyMapRead = merchantCurrencyMapRead;
        this.keystore = keystore;
        this.merchantCode = merchantCode;
        this.countryCode = countryCode;
        this.merchantType = merchantType;
        this.updateOrder = updateOrder;
        this.threeDSResultResponse = threeDSResultResponse;
        this.threeDSIssuerURL = threeDSIssuerURL;
        this.transactionDetailsWrite = transactionDetailsWrite;
        this.transactionDetailsRead = transactionDetailsRead;


        try {
            decryptor = new AESEncryptionDecryption();
            decryptedUserName = decryptor.decrypt(communicatorUserName, secret);
            decryptedPassword = decryptor.decrypt(communicatorPassword, secret);
            decryptedkeystorepasswordplatformpac = decryptor.decrypt(keystorepasswordplatformpac, secret);
        } catch (Exception ex) {
            mLogger.error("Exception with decryption credentials." + ex, new Throwable().getStackTrace()[0]);
        }
    }

    private String checkAmount(String amount) {

        if (amount.contains(".")) {
            Float floatAmount = new Float(amount) * 100;
            String centAmount = Integer.toString(floatAmount.intValue());
            return centAmount;
        }
        return amount;
    }

    private String checkExpiryDate(String datetime) {

        //Receive datetime as MMYYYY from Sabre
        //Need to return expiry date as YYMM
        if (datetime.length() > 4) {

            String monthString = datetime.substring(0, 2);
            String yearString = datetime.substring(4, 6);

            return yearString + monthString;

        } else if (datetime.length() == 4) {

            String monthString = datetime.substring(0, 2);
            String yearString = datetime.substring(2, 4);

            return yearString + monthString;
        }
        else if (datetime.length() == 3) {

            String monthString = "0"+datetime.substring(0, 1);
            String yearString = datetime.substring(1, 3);

            return yearString + monthString;
        }
        return datetime;
    }

    private byte[] concatBytes(byte[] xid, byte[] cavv) {
        byte[] result = new byte[xid.length + cavv.length];
        System.arraycopy(xid, 0, result, 0, xid.length);
        System.arraycopy(cavv, 0, result, xid.length, cavv.length);
        return result;
    }

    private String displayBytes(byte[] barray) {
        StringBuilder bytes = new StringBuilder();
        for (byte b : barray) {
            bytes.append(b).append(" ");
        }
        return bytes.toString();
    }

    private String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private String asciiToHex(String asciiStr) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString((int) ch));
        }

        return hex.toString();
    }

    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    
    /**
     * =========================================================================
     * GenerateTransactionID
     * -------------------------------------------------------------------------
     * @return 
     * =========================================================================
     */
    public String GenerateTransactionID() {  
        Date date = new Date();
        return date.getTime() + "";

    }
    

    private String AddressVerificationFormat(AddressType addressType) {

        String postalcode = "";
        String addressline1 = "";
        String addressline2 = "";
        String city = "";
        String state = "";
        String country = "";

        StringBuilder avd = new StringBuilder();

        if (addressType.getPostalCode() != null) {
            postalcode = addressType.getPostalCode();
            //Postilion: The alphanumeric postal/ZIP code (positions 1 - 9)
            if (postalcode.length() < 9) {
                int padding = 9 - addressType.getPostalCode().length();

                for (int i = 1; i <= padding; i++) {
                    postalcode = postalcode + " ";
                }
            }

        }

        if (addressType.getAddressLine1() != null) {
            addressline1 = addressType.getAddressLine1();
        }
        if (addressType.getAddressLine2() != null) {
            addressline2 = addressType.getAddressLine2();
        }
        if (addressType.getCityName() != null) {
            city = addressType.getCityName();
        }
        if (addressType.getStateProv() != null) {
            if (addressType.getStateProv().getName() != null) {
                state = addressType.getStateProv().getName();
            }
        }
        if (addressType.getCounty() != null) {
            if (addressType.getCountry().getName() != null) {
                country = addressType.getCounty();
            }
        }

        avd.append(postalcode);
        avd.append(addressline1).append(" ");
        avd.append(addressline2).append(" ");
        avd.append(city).append(" ");
        avd.append(state).append(" ");
        avd.append(country).append(" ");

        String avdStringOut = avd.toString();

        if (avdStringOut.length() < 29) {
            int advPadding = 29 - avdStringOut.length();

            for (int i = 1; i <= advPadding; i++) {
                avdStringOut = avdStringOut + " ";
            }
        } else {
            avdStringOut = avdStringOut.substring(0, 29);
        }

        //Postilion :The alphanumeric cardholder address (positions 10 - 29).
        return avdStringOut;
    }

    private String processResponseCode(String responseCode) {
        switch (responseCode) {
            case "00":
                return "Approval";
            case "01":
                return "Refer to card issuer";
            case "02":
                return "Refer to card issuer, special condition ";
            case "03":
                return "Invalid merchant ";
            case "04":
                return "Pick-up card ";
            case "05":
                return "Do not honor ";
            case "06":
                return "Error ";
            case "07":
                return "Pick-up card, special condition ";
            case "08":
                return "Honor with identification ";
            case "09":
                return "Request in progress ";
            case "10":
                return "Approved, partial ";
            case "11":
                return "Approved, VIP ";
            case "12":
                return "Invalid transaction ";
            case "13":
                return "Invalid amount ";
            case "14":
                return "Invalid card number ";
            case "15":
                return "No such issuer ";
            case "16":
                return "Approved, update track 3 ";
            case "17":
                return "Customer cancellation ";
            case "18":
                return "Customer dispute ";
            case "19":
                return "Re-enter transaction ";
            case "20":
                return "Invalid response ";
            case "21":
                return "No action taken ";
            case "22":
                return "Suspected malfunction ";
            case "23":
                return "Unacceptable transaction fee ";
            case "24":
                return "File update not supported ";
            case "25":
                return "Unable to locate record ";
            case "26":
                return "Duplicate record ";
            case "27":
                return "File update field edit error ";
            case "28":
                return "File update file locked ";
            case "29":
                return "File update failed ";
            case "30":
                return "Format error";
            case "31":
                return " Bank not supported ";
            case "32":
                return " Completed partially ";
            case "33":
                return " Expired card, pick-up ";
            case "34":
                return " Suspected fraud, pick-up ";
            case "35":
                return " Contact acquirer, pick-up ";
            case "36":
                return " Restricted card, pick-up ";
            case "37":
                return " Call acquirer security, pick-up ";
            case "38":
                return " PIN tries exceeded, pick-up ";
            case "39":
                return " No credit account ";
            case "40":
                return " Function not supported ";
            case "41":
                return " Lost card, pick-up ";
            case "42":
                return " No universal account ";
            case "43":
                return " Stolen card, pick-up ";
            case "44":
                return " No investment account ";
            case "45":
                return " Account closed ";
            case "46":
                return " Identification required ";
            case "47":
                return "Identification cross-check required ";
            case "48":
            case "49":
            case "50":
                return "Reserved for future Postilion use ";
            case "51":
                return "Not sufficient funds ";
            case "52":
                return "No check account ";
            case "53":
                return "No savings account ";
            case "54":
                return "Expired card ";
            case "55":
                return "Incorrect PIN ";
            case "56":
                return "No card record ";
            case "57":
                return "Transaction not permitted to cardholder ";
            case "58":
                return "Transaction not permitted on terminal ";
            case "59":
                return "Suspected fraud ";
            case "60":
                return "Contact acquirer ";
            case "61":
                return "Exceeds withdrawal limit ";
            case "62":
                return "Restricted card ";
            case "63":
                return "Security violation ";
            case "64":
                return "Original amount incorrect ";
            case "65":
                return "Exceeds withdrawal frequency ";
            case "66":
                return "Call acquirer security ";
            case "67":
                return "Hard capture ";
            case "68":
                return "Response received too late ";
            case "69":
                return "Advice received too late ";
            case "70":
            case "71":
            case "72":
            case "73":
            case "74":
                return "Reserved for future Postilion use ";
            case "75":
                return "PIN tries exceeded";
            case "76":
                return "Reserved for future Postilion use";
            case "77":
                return "Intervene, bank approval required";
            case "78":
                return "Intervene, bank approval required for partial amount";
            case "79":
            case "80":
            case "81":
            case "82":
            case "83":
            case "84":
            case "85":
            case "86":
            case "87":
            case "88":
            case "89":
                return "Reserved for client-specific use (declined)";
            case "90":
                return "Cut-off in progress";
            case "91":
                return "Issuer or switch inoperative";
            case "92":
                return "Routing error";
            case "93":
                return "Violation of law";
            case "94":
                return "Duplicate transaction";
            case "95":
                return "Reconcile error";
            case "96":
                return "System malfunction";
            case "97":
                return "Reserved for future Postilion use";
            case "98":
                return "Exceeds cash limit";
            case "99":
                return "Reserved for future Postilion use";
            default:
                return "Unknown";
        }
    }

    public String processAVS(String avs) {

        switch (avs) {
            case "A":
                return "F"; //A:Address matches, postal/ZIP code does not -> F:Address matched; postal code not matched
            case "E":
                return "E"; //E:Error                                     -> E:Postal code and address not checked
            case "N":
                return "J"; //Neither address nor postal/ZIP code matches -> J:Postal code and address not matched
            case "R":
                return "E"; //Retry                                       ->
            case "U":
                return "H"; //U:Unavailable                               -> H: Postal code and address not supplied 
            case "Y":
                return "A"; //Y:Address and postal/ZIP code matches       -> A:Postal code and address matched
            case "Z":
                return "C"; //Z:postal/ZIP code matches, address does not -> C:Postal code matched; address not matched 
            default:
                return "E";
        }

    }

    public String processCVC(String cvc) {

        switch (cvc) {
            case "M":
                return "A"; //M:CVV2 valid (match), CVV valid or not available       -> A: CVC Matched
            case "N":
                return "D"; //N:CVV2 invalid (non-match), CVV valid or not available -> D: CVC Not Match
            case "P":
                return "J"; //P:Unable to process CVV2, CVV valid or not available   -> B: CVC Not Supplied
            case "U":
                return "E"; //U:Issuer unregistered to process CVV2, CVV valid or not available -> C:CVC Not Checked
            case "Y":
                return "D"; //Y:CVV invalid                                          -> D: CVC Not Match
            default:
                return "C";
        }
    }

    public RoutingObj extractSubfieldsDE127_03(String routing) {

        String source = routing.substring(0, 12);
        String sink = routing.substring(12, 24);
        String sourceStan = routing.substring(24, 30);
        String sinkStan = routing.substring(30, 36);
        String totalsGroup = routing.substring(36, 48);

        RoutingObj routingObj = new RoutingObj(source, sink, sourceStan, sinkStan, totalsGroup);

        return routingObj;

    }
    
    /**
     * #########################################################################
     * # DE127.22 StructureData utility methods 
     * #########################################################################
     * The methods below are the StructureData utility methods.
     * Developed by Grant O'Reilly 2022-07-04
     * #########################################################################
     */    
    
    /**
     * =========================================================================
     * constructStruct
     * =========================================================================
     * @param value
     * @return 
     * =========================================================================
     */
    private String constructStruct(String value){
       StringBuilder struct = new StringBuilder(); 
       
       Integer valueLength = value.length();
       struct.append((valueLength+"").length())
             .append(valueLength)
             .append(value);
       
       return struct.toString();
    }
    /**
     * =========================================================================
     * constructStructuredDataDE127_22
     * =========================================================================
     * @param tdsVersion
     * @param tdsAuthMethod
     * @param dsTransId
     * @return 
     */
    private String constructStructuredDataDE127_22(String tdsVersion, 
                                                  String tdsAuthMethod, 
                                                  String dsTransId){
        String tdsVersionKey = "3D_SECURE_VERSION";
        String tdsAuthMethodKey = "3DS_AUTH_METHOD";
        String dsTransIdKey  = "DIRECTORY_SERVER_TRANSACTION_ID";

        StringBuilder structuredData = new StringBuilder();
        
        if (tdsAuthMethod.length()>1){
           tdsAuthMethod = tdsAuthMethod.substring(1, 2);
        }        
        
        structuredData.append(constructStruct(tdsVersionKey))
                      .append(constructStruct(tdsVersion))
                      .append(constructStruct(tdsAuthMethodKey))
                      .append(constructStruct(tdsAuthMethod))
                      .append(constructStruct(dsTransIdKey))
                      .append(constructStruct(dsTransId)        
        );
        
        return structuredData.toString();
    }
    /**
     * #########################################################################
     * # End of DE127.22 StructureData utility methods 
     * #########################################################################
     */
    
    
    /**
     * =========================================================================
     * PersistACS
     * =========================================================================
     * @param acsPareqElement 
     * =========================================================================
     */
    private void PersistACS(AcsPareqElement acsPareqElement) {
        getmLogger().info("Saving the ACS and PaReq to database", new Throwable().getStackTrace()[0]);
        getmLogger().info("Calling: "+this.getAcsParerqWrite()+" and writing "+acsPareqElement.toJSON(), new Throwable().getStackTrace()[0]);
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        sslComms.sendHttpPost(this.getAcsParerqWrite(), acsPareqElement.toJSON().getBytes());
    }

    private void PersistCapture(CaptureElement captureElement) {

        getmLogger().info("Saving capture data to database:", new Throwable().getStackTrace()[0]);
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        sslComms.sendHttpPost(this.getCaptureWrite(), captureElement.toJSON().getBytes());
    }

    private void PersistTransaction(String transactionId, String messageType, AuthoriseResponse postilionAuthRes, String url) {

        OriginalDataElement ode = new OriginalDataElement(transactionId,
                messageType,
                postilionAuthRes.getTransmissionDateTime(),
                postilionAuthRes.getSystemTraceAuditNumber(),
                postilionAuthRes.getAcquiringInstitutionCode(),
                "00000000000");

        Authorisation auth = new Authorisation(transactionId,
                postilionAuthRes.getMessageType(),
                postilionAuthRes.getPrimaryAccountNumber(),
                postilionAuthRes.getProcessingCode(),
                new Integer(checkAmount(postilionAuthRes.getTransactionAmount())),
                postilionAuthRes.getTransmissionDateTime(),
                postilionAuthRes.getSystemTraceAuditNumber(),
                postilionAuthRes.getLocalTranTime(),
                postilionAuthRes.getLocalTranDate(),
                postilionAuthRes.getAccountExpiryDate(),
                postilionAuthRes.getSettlementDate(),
                postilionAuthRes.getPosConditionCode(),
                postilionAuthRes.getAcquiringInstitutionCode(),
                postilionAuthRes.getRetrievalRefNumber(),
                postilionAuthRes.getAuthIdResponse(),
                postilionAuthRes.getTerminalId(),
                postilionAuthRes.getPosDataCode(),
                postilionAuthRes.getRoutingInformation());

        getmLogger().info("Saving original data element to database: " + ode.getDE90(), new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        sslComms.sendHttpPost(this.getOriginaldataWrite(), ode.toJSON().getBytes());

        getmLogger().info("Saving authorisation data to database: " + auth.getTransactionId(), new Throwable().getStackTrace()[0]);
        //sslComms.sendHttpPost(this.getAuthorisationWrite(), auth.toJSON().getBytes());
        sslComms.sendHttpPost(url, auth.toJSON().getBytes());

    }
    
    private AuthRS DeclineAuth(String orderNumber, String transactionId) {

        getmLogger().info("Decline!", new Throwable().getStackTrace()[0]);
        AuthRS response = new AuthRS();

        response.setVersion("1.0");
        response.setResponseCode("5");
        response.setDescription("Declined");
        response.setAVSResultCode("A");
        response.setCVCResultCode("D");
        response.setDebitCard(Boolean.FALSE);
        response.setOrderNumber(orderNumber);
        response.setTransactionID(transactionId);

        return response;

    }    

    public CaptureElement readCapture(String transactionId) {

        getmLogger().info("Read capture data element from database using transactionId= " + transactionId, new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());

        CaptureElement ce = new CaptureElement();
        ce.setTransactionId(transactionId);
        Result result = sslComms.sendHttpPost(this.getCaptureRead(), ce.toJSON().getBytes());
        getmLogger().info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        Gson gson = new Gson();
        return gson.fromJson(result.getData(), CaptureElement.class);
    }

    
    /**
     * #########################################################################
     * # Read financial using transaction id
     * @param transactionId
     * @return Financial
     * #########################################################################
    */
    public Financial readFinancial(String transactionId) {

        getmLogger().info("Read financial data element from database using transactionId= " + transactionId, new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());

        Financial financial = new Financial();
        financial.setTransactionId(transactionId);
        
        Result result = sslComms.sendHttpPost("https://ccpgw.testbsp.com.pg:9078/transaction/manager/database/financial/read/transactionid", financial.toJSON().getBytes());
        getmLogger().info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        
        Gson gson = new Gson();
        return gson.fromJson(result.getData(), Financial.class);
    }
    
    /**
     * #########################################################################
     * # Write financial to financial table
     * @param financial
     * @return Result
     * #########################################################################
    */
    public Result writeFinancial(Financial financial) {

        getmLogger().info("Writing financial data element to database for transactionId= " + financial.getTransactionId(), new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());

        Result result = sslComms.sendHttpPost(this.getAuthorisationWrite(), financial.toJSON().getBytes());
        
        getmLogger().info("Write Financial - status: " + result.getStatus() + "; comments: " + result.getComments(), new Throwable().getStackTrace()[0]);
        
        return result;
    }
    
    /**
     * #########################################################################
     * # Read Merchant currency map using merchant name and currency
     * @param merchantName
     * @param currency
     * @return MerchantCurrencyMap
     * #########################################################################
    */
    public MerchantCurrencyMap readMerchantCurrencyMap(String merchantName, String currency) {

        getmLogger().info("Read merchant currency map from database using merchantName= " + merchantName + " and currency= " + currency, new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());

        MerchantCurrencyMap map = new MerchantCurrencyMap();
        map.setMerchantName(merchantName);
        map.setCurrency(currency);
        
        Result result = sslComms.sendHttpPost(this.getMerchantCurrencyMapRead(), map.toJSON().getBytes());
        getmLogger().info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        
        Gson gson = new Gson();
        return gson.fromJson(result.getData(), MerchantCurrencyMap.class);
    }    
    
    
    private OriginalDataElement readOriginalDataElement(String stan, String transactionId) {

        getmLogger().info("Read original data element from database using STAN = " + stan, new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());

        OriginalDataElement ode = new OriginalDataElement();
        ode.setTransactionId(transactionId);
        ode.setSystemTraceAuditNumber(stan);
        Result result = sslComms.sendHttpPost(this.getOriginaldataRead(), ode.toJSON().getBytes());
        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        Gson gson = new Gson();
        return gson.fromJson(result.getData(), OriginalDataElement.class);
    }

    private Authorisation readAuthorisation(String transactionId) {
        getmLogger().info("Read authorisation from database using transactionId = " + transactionId, new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());

        Authorisation auth = new Authorisation();
        auth.setTransactionId(transactionId);
        Result result = sslComms.sendHttpPost(this.getAuthorisationRead(), auth.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        return gson.fromJson(result.getData(), Authorisation.class);
    }

    public AuthRSObject process3DSStepOneVersion1(String orderNumber, String transactionId, PaymentDetailType paymentType) {

        AuthRS response = new AuthRS();

        PaReqRequestObject paReq = new PaReqRequestObject();
        Cardholder cardHolder = new Cardholder();
        cardHolder.setPan(paymentType.getPaymentCard().getCardNumber());
        cardHolder.setExpiry(checkExpiryDate(paymentType.getPaymentCard().getExpireDate()));

        Purchase purchase = new Purchase();
        purchase.setExponent("2");
        purchase.setAmount(checkAmount(paymentType.getAmountDetail().getAmount().toString()));
        purchase.setCurrency(this.getMerchantCurrencyMap().getCode());

        Merchant merchant = new Merchant();
        //merchant.setAcquirerBin("123456"); //60130200000
        //merchant.setCountryCode("100");    //598 

        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
        getmLogger().info("Card scheme: " + cardType, new Throwable().getStackTrace()[0]);

        switch (cardType) {
            case "CA"://Mastercard
                getmLogger().info("Setting acquire bin for Mastercard.", new Throwable().getStackTrace()[0]);
                merchant.setAcquirerBin(this.getAcquiringInstitutionCodeMastercard());
                cardHolder.setScheme("Mastercard");
                break;
            default: //Visa
                getmLogger().info("Setting acquire bin for Visa.", new Throwable().getStackTrace()[0]);
                merchant.setAcquirerBin(this.getAcquiringInstitutionCodeVisa());
                cardHolder.setScheme("Visa");
                break;
        }

        merchant.setCountryCode(this.getCountryCode());

        //header.key.3dsorgid=3DS-Organization-ID
        //header.value.3dsorgid=a93e6455-3ffc-498d-9d64-84fa22dc7095
        //merchant.setId("a93e6455-3ffc-498d-9d64-84fa22dc7095"); //
        merchant.setId(this.getMerchantCode());//merchant.setId("merId");
        merchant.setName("Sabre");
        merchant.setUrl("https://www.sabre.com/");

        paReq.setCardholder(cardHolder);
        paReq.setPurchase(purchase);
        paReq.setMerchant(merchant);

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getThreedsCreateReq(), paReq.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        String resp = result.getData().replaceAll("message", "Message");
        resp = resp.replaceAll("veres", "VERes");
        resp = resp.replaceAll("ch", "CH");

        Gson gson = new Gson();
        PaReqResponseObject paResponse = gson.fromJson(resp, PaReqResponseObject.class);
        getmLogger().info("paResponse :" + paResponse.toJSON(), new Throwable().getStackTrace()[0]);

        // Part of the ENROLL = "N" Dev                
        List<Message> messageList = paResponse.getThreeDSecureVERes().getMessage();
        Message m = messageList.get(0);
        String enrolled = m.getVERes().getCH().getEnrolled();

        getmLogger().info("Enrolled :" + enrolled, new Throwable().getStackTrace()[0]);

        if (enrolled.equals("N")) {
            response.setOrderNumber(orderNumber);
            response.setTransactionID(transactionId);
        } else {
            response.setAVSResultCode("A");
            response.setCVCResultCode("A");
            response.setDebitCard(Boolean.FALSE);
            response.setDescription("Approved");
            response.setOrderNumber(orderNumber);
            response.setResponseCode("0");
            response.setTransactionID(transactionId);
            response.setVersion("");

            if (getServletProxy().toLowerCase().equals("true")) {
                getmLogger().info("Servlet Proxy used. ACSURL: " + this.getAcsurl());
                response.setRedirectURL(this.getAcsurl());
            } else {
                getmLogger().info("Servlet Proxy NOT used. ACSURL: " + paResponse.getSessionData().getAcsUrl());
                response.setRedirectURL(paResponse.getSessionData().getAcsUrl());
            }

            //3DS response
            response.setVersion("1.0");
            response.setResponseCode("1");
            response.setDescription("3DS Authentication Required");
            response.setOrderNumber(orderNumber);
            response.setTransactionID(transactionId);
            response.setDebitCard(Boolean.FALSE);

            AuthRS.T3DSResult t3ds = new AuthRS.T3DSResult();
            t3ds.setPARequest(paResponse.getPaReq());

            if (getServletProxy().toLowerCase().equals("true")) {
                getmLogger().info("Servlet Proxy used. Issuer URL: " + this.getAcsurl());
                t3ds.setIssuerURL(this.getAcsurl());
            } else {
                getmLogger().info("Servlet Proxy NOT used. Issuer URL: " + paResponse.getSessionData().getAcsUrl());
                t3ds.setIssuerURL(paResponse.getSessionData().getAcsUrl());
            }

            //header.key.3dsorgid=3DS-Organization-ID
            //header.value.3dsorgid=a93e6455-3ffc-498d-9d64-84fa22dc7095
            //t3ds.setMD("a93e6455-3ffc-498d-9d64-84fa22dc7095"); //t3ds.setMD("merId");  
            t3ds.setMD(this.getMerchantCode()); //t3ds.setMD("merId");

            this.PersistACS(new AcsPareqElement(paResponse.getSessionData().getAcsUrl(), paResponse.getPaReq()));

            response.setT3DSResult(t3ds);
        }

        return new AuthRSObject(response, enrolled);
    }
    
    public AuthRS process3DSStepTwoVersion1(String commandType,
                                    String orderNumber,
                                    String transactionId,
                                    PaymentDetailType paymentType) {

        PaResValidateRequestObject paResValidate = new PaResValidateRequestObject();

        paResValidate.setPaRes(paymentType.getPaymentCard().getT3DS().getPAResponse());

        getmLogger().info("PAResponse: " + paymentType.getPaymentCard().getT3DS().getPAResponse(), new Throwable().getStackTrace()[0]);

        SessionData sessionData = new SessionData();
        sessionData.setPaReqCreationTime("20131015 16:45:01");
        sessionData.setPan(paymentType.getPaymentCard().getCardNumber());
        sessionData.setAcsUrl("http://www.acs-url.com");
        sessionData.setMasterCardTokenized("false");

        Purchase2 purchase = new Purchase2();
        purchase.setXid(paymentType.getPaymentCard().getT3DS().getXID());
        purchase.setDate("20131015 16:45:00");
        purchase.setAmount(paymentType.getAmountDetail().getAmount().toString());
        purchase.setCurrency(this.getMerchantCurrencyMap().getCode());
        purchase.setExponent("2");
        purchase.setDesc("This is the description");

        Merchant2 merchant = new Merchant2();
        //merchant.setAcqBIN("123456");
        //merchant.setCountry("100");
        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
        getmLogger().info("Card scheme: " + cardType, new Throwable().getStackTrace()[0]);

        switch (cardType) {
            case "CA"://Mastercard
                getmLogger().info("Setting acquire bin for Mastercard.", new Throwable().getStackTrace()[0]);
                merchant.setAcqBIN(this.getAcquiringInstitutionCodeMastercard());
                sessionData.setScheme("Mastercard");
                break;
            default: //Visa
                getmLogger().info("Setting acquire bin for Visa.", new Throwable().getStackTrace()[0]);
                merchant.setAcqBIN(this.getAcquiringInstitutionCodeVisa());
                sessionData.setScheme("Visa");
                break;
        }

        merchant.setCountry(this.getCountryCode());
        //header.key.3dsorgid=3DS-Organization-ID
        //header.value.3dsorgid=a93e6455-3ffc-498d-9d64-84fa22dc7095

        //merchant.setMerID("a93e6455-3ffc-498d-9d64-84fa22dc7095"); //
        merchant.setMerID(this.getMerchantCode());//merchant.setMerID("merId");
        merchant.setName("Sabre");
        merchant.setUrl("https://www.sabre.com/");

        CH ch = new CH();
        ch.setAcctID("accountId");
        ch.setExpiry(paymentType.getPaymentCard().getExpireDate());

        PAReq paReq = new PAReq();
        paReq.setVersion("1.0.2");
        paReq.setMerchant(merchant);
        paReq.setPurchase(purchase);
        paReq.setCH(ch);

        Message message = new Message();
        message.setId("pa9d4eb548-db62-43b7-8cc8-8731ac202de2");
        message.setPAReq(paReq);

        ThreeDSecureType threedsType = new ThreeDSecureType();
        threedsType.getMessage().add(message);
        sessionData.setThreeDSecurePAReq(threedsType);
        paResValidate.setSessionData(sessionData);

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getThreedsValidatePaRes(), paResValidate.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();

        PaResValidationResponse paResValidResponse = gson.fromJson(result.getData(), PaResValidationResponse.class);
        getmLogger().info("Transaction Status " + paResValidResponse.getTransactionStatus(), new Throwable().getStackTrace()[0]);

//        switch(paResValidResponse.getTransactionStatus()){
//            case "Y": return postilionAuth(paResValidResponse, paymentType, orderNumber, transactionId);
//            case "N": 
//            default : return DeclineAuth(orderNumber, transactionId);  
//        }
        return postilionAuth(commandType, paResValidResponse, paymentType, orderNumber, transactionId);

    }
    
    /**
     * #########################################################################
     * # 3DS v2.x methods 
     * #########################################################################
     * The methods below are the 3DS v2.x methods.
     * Developed by Grant O'Reilly 2022-05-01
     * #########################################################################
     */

    /**
     * =========================================================================
     * process3DSv2xVersioningRequest
     * =========================================================================
     * @param cardholderAccountNumber
     * @param cardType
     * @return 
     * ========================================================================= 
     */
    private ThreeDSServerVersioningV3Response process3DSv2xVersioningRequest(String cardholderAccountNumber, String scheme){

        ThreeDSV2ServerVersioningRequest versionReq = new ThreeDSV2ServerVersioningRequest();
        
        getmLogger().info("Card scheme: " + scheme, new Throwable().getStackTrace()[0]);

        versionReq.setCardholderAccountNumber(cardholderAccountNumber);
        versionReq.setSchemeId(scheme);
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getThreedsV2Versioning(), versionReq.toJSON().getBytes());
        
        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        String resp = result.getData();

        Gson gson = new Gson();
        ThreeDSServerVersioningV3Response versionRes = gson.fromJson(resp, ThreeDSServerVersioningV3Response.class);
        
        getmLogger().info("Versioning Response :" + versionRes.toJSON(), new Throwable().getStackTrace()[0]); 

        return  versionRes;       
        
    }
    
    /**
     * =========================================================================
     * persistEnrolled
     * ========================================================================= 
     * @param threeDSServerVersioning
     * @param paymentType
     * @param scheme
     * @param threeDSServerTransID 
     * ========================================================================= 
     */
    private void persistEnrolled(ThreeDSServerVersioningV3Response threeDSServerVersioning,
                                 PaymentDetailType paymentType, 
                                 String scheme){
        

        Enrolled enrolled = new Enrolled(threeDSServerVersioning.getThreeDSServerTransID(),
                                         !threeDSServerVersioning.getIsCardFoundIn2xRanges()?"N":"Y", 
                                         paymentType.getPaymentCard().getCardNumber(),
                                         scheme);
                                         
                
        getmLogger().debug("Enrolled Object: "+enrolled.toJSON());
        getmLogger().debug("URL: "+this.getThreedsV2EnrolledWrite());
        
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        sslComms.sendHttpPost(this.getThreedsV2EnrolledWrite(), enrolled.toJSON().getBytes());        
    }
    
    /**
     * =========================================================================
     * getThreeDSMethodUrl
     * =========================================================================
     * @param versionRes
     * @return 
     */
    private URL getThreeDSMethodUrl(ThreeDSServerVersioningV3Response versionRes){
    
        //----------------------------------------------------------------------
        //Added for the Versioning V3 update.
        //Grant O'Reilly 2022-11-17 
        //----------------------------------------------------------------------
        //if (versionRes.getThreeDSMethodURL() != null)
        //    threeDSMethodUrl = versionRes.getThreeDSMethodURL();
        URL threeDSMethodUrl = null; 
        for (AcsProtocolVersionData acsProtocolVersionData : versionRes.getAcsProtocolVersions()){
              if (acsProtocolVersionData.getVersion().equals("2.1.0")){
                  threeDSMethodUrl = acsProtocolVersionData.getThreeDSMethodURL();
              }
              if (acsProtocolVersionData.getVersion().equals("2.2.0")){
                  if (threeDSMethodUrl == null){
                     threeDSMethodUrl = acsProtocolVersionData.getThreeDSMethodURL(); 
                  }
              }
        }        
        //---------------------------------------------------------------------- 
        return threeDSMethodUrl;
    }
    
    /**
     * =========================================================================
     * persistTransactionDetails
     * =========================================================================
     * @param threeDSServerVersioning
     * @param orderNumber
     * @param transactionId
     * @param paymentType
     * 
     */
    private void persistTransactionDetails(String threeDSServerTransID, 
                                           String orderNumber, 
                                           String transactionId, 
                                           PaymentDetailType paymentType,
                                           String secretKey){
        getmLogger().info("Persisting Transaction Details .........");
        
        String encryptedString = "";
        try {
            DataEncryption encrypt = new DataEncryption();
            
            PaymentDetails payDetail = new PaymentDetails(paymentType);
            
            encryptedString = encrypt.EncryptAES256(payDetail.toJSON());
        } catch (Exception ex) {
            getmLogger().error("Exception on encrypting payment details."+ex);
        }            
            
        TransactionFullDetails details = new TransactionFullDetails(threeDSServerTransID,
                                                                orderNumber,
                                                                transactionId,
                                                                secretKey, 
                                                                encryptedString);
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        sslComms.sendHttpPost(this.getTransactionDetailsWrite(), details.toJSON().getBytes());
    }
    
    /**
     * =========================================================================
     * persist3DSv2xAuthenticationRequest
     * =========================================================================
     * @param threeDSServerVersioning
     * @param paymentType
     * @param scheme
     * @param threeDSServerTransID 
     * ========================================================================= 
     */
    private void persist3DSv2xAuthenticationRequest(ThreeDSServerVersioningV3Response threeDSServerVersioning,
                                                      PaymentDetailType paymentType, 
                                                      String scheme,
                                                      String threeDSServerTransID){    
        
        ThreeDSServerAuthenticationRequest authReq = new ThreeDSServerAuthenticationRequest();
        
        ThreeDSV2xHandler threeDSV2xHandler = new ThreeDSV2xHandler();

        authReq.setPreferredProtocolVersion("2.1.0");
        authReq.setEnforcePreferredProtocolVersion(Boolean.FALSE);
        authReq.setDeviceChannel("02");
        authReq.setMessageCategory("01");
        
        //----------------------------------------------------------------------
        //Added for the Versioning V3 update.
        //Grant O'Reilly 2022-11-17 
        //----------------------------------------------------------------------
        URL threeDSMethodUrl = getThreeDSMethodUrl(threeDSServerVersioning);      
        //----------------------------------------------------------------------        
        
        //----------------------------------------------------------------------
        // This is the fix for the issue with White Label when ThreeDSMethodURL = null
        // Added 2022-11-04 by Grant O'Reilly
        if (threeDSMethodUrl == null){
            authReq.setThreeDSCompInd("U");
        }
        else {
            authReq.setThreeDSCompInd("Y");
        }
        //----------------------------------------------------------------------
        authReq.setThreeDSRequestor(threeDSV2xHandler.get3DSv2xRequestor());
        authReq.setThreeDSServerTransID(threeDSServerTransID);
        authReq.setThreeDSRequestorURL(threeDSMethodUrl);
        
        authReq.setCardholderAccount(threeDSV2xHandler.get3DSv2xCardholderAccount(paymentType.getPaymentCard().getCardNumber(),
                                                                                  checkExpiryDate(paymentType.getPaymentCard().getExpireDate()),
                                                                                  scheme));
        authReq.setCardholder(threeDSV2xHandler.get3DSv2xCardholder(paymentType.getPaymentCard().getCardHolderName()));
        
        
        getmLogger().debug("CurrencyCode: "+this.getCurrencyCode());
        
        authReq.setPurchase(threeDSV2xHandler.get3DSv2xPurchase(checkAmount(paymentType.getAmountDetail().getAmount().toString()), 
                                                                "598"));
        
        String acquirerBin = this.getAcquiringInstitutionCodeVisa();//"428280";
        String acquirerMerchantId = this.getMerchantId();//"Visa";
        
        if (scheme.equals("Mastercard")){
            acquirerBin = this.getAcquiringInstitutionCodeMastercard();
            //acquirerMerchantId = "Mastercard";
        }
        
        getmLogger().debug("acquirerBin: "+acquirerBin+" acquirerMerchantId:"+acquirerMerchantId);
        
        authReq.setAcquirer(threeDSV2xHandler.get3DSv2xAcquirer(acquirerBin,
                                                                acquirerMerchantId));
        
        authReq.setMerchant(threeDSV2xHandler.get3DSv2xMerchant("", // merchantConfigurationId
                                                                "4511",          //mcc 
                                                                "IBE SALES PGK",    //merchant name
                                                                "598")); 
        
        authReq.setBroadInfo(threeDSV2xHandler.get3DSv2xBroadInfo("TLS 1.x will be turned off starting summer 2019"));
        
        List<MessageExtensionAttribute> attributes = new ArrayList<>();
        attributes.add(threeDSV2xHandler.get3DSv2xMessageExtensionAttribute("id", "name"));
        authReq.setMessageExtension( attributes);
        
        authReq.setBrowserInformation(threeDSV2xHandler.get3DSv2xBrowser("192.168.1.11")); //IP address
        
        //authReq.setChallengeMessageExtension(List<MessageExtensionAttribute> challengeMessageExtension);
        //authReq.setDeviceRenderOptions(deviceRenderOptions);
        //authReq.setThreeRIInd(String threeRIInd);
        //authReq.setSdkInformation(Sdk sdkInformation); 
        
        getmLogger().debug("AuthReq: "+authReq.toJSON());
        
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        sslComms.sendHttpPost(this.getThreedsV2AuthReqWrite(), authReq.toJSON().getBytes());
        
    }
    
    /**
     * =========================================================================
     * process3DSStepOneVersion2x
     * =========================================================================
     * @param orderNumber
     * @param transactionId
     * @param paymentType
     * @return 
     * =========================================================================
     */
    public AuthRSObject process3DSStepOneVersion2x(String orderNumber, 
                                                   String transactionId, 
                                                   PaymentDetailType paymentType,
                                                   String secretkey) {

        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
        getmLogger().info("Card scheme: " + cardType, new Throwable().getStackTrace()[0]);
        String scheme = "Visa";  
        switch (cardType) {
            case "CA"://Mastercard
                getmLogger().info("Setting schemeId for Mastercard.", new Throwable().getStackTrace()[0]);
                scheme = "Mastercard";
                break;
            default: //Visa
                getmLogger().info("Setting schemeId for Visa.", new Throwable().getStackTrace()[0]);
                scheme = "Visa";
                break;
        }        
        
        ThreeDSServerVersioningV3Response versionRes = process3DSv2xVersioningRequest(paymentType.getPaymentCard().getCardNumber(), scheme);    
        
        
        //URL threeDSMethodUrl = null; 
        String threeDSMethodData = null;
        
        //----------------------------------------------------------------------
        //Added for the Versioning V3 update.
        //Grant O'Reilly 2022-11-17 
        //----------------------------------------------------------------------
        //if (versionRes.getThreeDSMethodURL() != null)
        //    threeDSMethodUrl = versionRes.getThreeDSMethodURL();
        URL threeDSMethodUrl = getThreeDSMethodUrl(versionRes);      
        //----------------------------------------------------------------------
        
        
        if (versionRes.getThreeDSMethodDataForm() != null){
         Map<String,String> map = versionRes.getThreeDSMethodDataForm();
         threeDSMethodData  = map.get("threeDSMethodData");
        }
        else{
             String stringToEncode = "{\"threeDSServerTransID\":\""+versionRes.getThreeDSServerTransID()+"\",\"threeDSMethodNotificationURL\":\""+this.getThreeDSIssuerURL()+"3dsmethodnotification\"}";
             threeDSMethodData  = Base64.getEncoder().encodeToString(stringToEncode.getBytes());
        }
        
        persistEnrolled(versionRes,paymentType,scheme);
        
        String enrolled = !versionRes.getIsCardFoundIn2xRanges()?"N":"Y";
        
        if (versionRes.getErrorDetails() != null){
            getmLogger().info("Error code : "+versionRes.getErrorDetails().getErrorCode());
            getmLogger().info("Error component : "+versionRes.getErrorDetails().getErrorComponent());
            getmLogger().info("Error description : "+versionRes.getErrorDetails().getErrorDescription());
        }

        AuthRS response = new AuthRS();        

        //3DS response
        response.setVersion("2.1.0");
        response.setResponseCode("1");
        response.setDescription("3DS Authentication Required");
        response.setOrderNumber(orderNumber);
        response.setTransactionID(transactionId);
        response.setDebitCard(Boolean.FALSE);

        AuthRS.T3DSResult t3ds = new AuthRS.T3DSResult();
        getmLogger().info("Setting IssuerURL to: "+this.getThreeDSIssuerURL()+"3dsredirectmechanism");
        t3ds.setIssuerURL(this.getThreeDSIssuerURL()+"3dsredirectmechanism");
        //t3ds.setTermURL(threeDSV2xAuthRes.getAcsURL().toString());
        t3ds.setPARequest(threeDSMethodData);
        
        getmLogger().info("Setting merchant data: "+this.getMerchantCurrencyMap().toJSON());
        
        t3ds.setMD(this.getMerchantCurrencyMap().getAutoGeneratedMid());
        
        this.persistTransactionDetails(versionRes.getThreeDSServerTransID(),
                                       orderNumber, 
                                       transactionId, 
                                       paymentType,
                                       secretkey);
        
        this.persist3DSv2xAuthenticationRequest(versionRes, 
                                                paymentType, 
                                                scheme, 
                                                versionRes.getThreeDSServerTransID());

        this.PersistACS(new AcsPareqElement(this.getThreeDSIssuerURL()+"3dsredirectmechanism",threeDSMethodData));

        response.setT3DSResult(t3ds);        

        return new AuthRSObject(response, enrolled);
    }

    /**
     * =========================================================================
     * process3DSStepTwoVersion2x
     * =========================================================================
     * @param orderNumber
     * @param transactionId
     * @param paymentType
     * @return 
     * =========================================================================
     */    
    public AuthRS process3DSStepTwoVersion2x(String commandType,
                                    String orderNumber,
                                    String transactionId,
                                    PaymentDetailType paymentType){
        
        
            
        AuthRS response = new AuthRS();
        
        
        if (paymentType.getPaymentCard() != null){
            getmLogger().debug("===========================================================================");
            getmLogger().debug(" PaymentDetailType->PaymentCard");
            getmLogger().debug("==========================================================================="); 
            getmLogger().debug("CardNumber: " +paymentType.getPaymentCard().getCardNumber());
            getmLogger().debug("CardHolderName: " +paymentType.getPaymentCard().getCardHolderName());
            getmLogger().debug("ExpiryDate: " +paymentType.getPaymentCard().getExpireDate());
            getmLogger().debug("CVC: " +paymentType.getPaymentCard().getCVC());
            getmLogger().debug("EffectiveDate: " +paymentType.getPaymentCard().getEffectiveDate());
            getmLogger().debug("IssueNumber: " +paymentType.getPaymentCard().getIssueNumber());
            getmLogger().debug("ReadyFor3DSVersion: " +paymentType.getPaymentCard().getReadyFor3DSVersion());
        }
        
        if (paymentType.getPaymentCard().getT3DS() != null){
            getmLogger().debug("===========================================================================");
            getmLogger().debug(" PaymentDetailType->PaymentCard->T3DS");
            getmLogger().debug("===========================================================================");        
            getmLogger().debug("PAResponse: " +paymentType.getPaymentCard().getT3DS().getPAResponse());
            getmLogger().debug("Authentication Results: " +paymentType.getPaymentCard().getT3DS().getAuthenticationResult());
            getmLogger().debug("CAVV: " +paymentType.getPaymentCard().getT3DS().getCAVV());
            getmLogger().debug("DirectoryServerTrxID: " +paymentType.getPaymentCard().getT3DS().getDirectoryServerTrxID());
            getmLogger().debug("CAVVAlgorithm: " +paymentType.getPaymentCard().getT3DS().getCAVVAlgorithm());
            getmLogger().debug("ECI: " +paymentType.getPaymentCard().getT3DS().getECI());
            getmLogger().debug("MD: " +paymentType.getPaymentCard().getT3DS().getMD());
            getmLogger().debug("XID: " +paymentType.getPaymentCard().getT3DS().getXID());
            getmLogger().debug("VerificationResult: " +paymentType.getPaymentCard().getT3DS().getVerificationResult());
            getmLogger().debug("Version: " +paymentType.getPaymentCard().getT3DS().getVersion());
        }

        if (paymentType.getAmountDetail() != null){
            getmLogger().debug("===========================================================================");
            getmLogger().debug(" PaymentDetailType->AmountDetail");
            getmLogger().debug("==========================================================================="); 

            getmLogger().debug("Amount: " +paymentType.getAmountDetail().getAmount());
            getmLogger().debug("CurrencyCode: " +paymentType.getAmountDetail().getCurrencyCode());
        }
        
        if (paymentType.getPaymentMethod() != null){
            getmLogger().debug("===========================================================================");
            getmLogger().debug(" PaymentDetailType->PaymentMethod");
            getmLogger().debug("==========================================================================="); 

            getmLogger().debug("PaymentMethodCode: " +paymentType.getPaymentMethod().getPaymentMethodCode());
        }        
        
        
        byte[] decodedBytes = Base64.getDecoder().decode(paymentType.getPaymentCard().getT3DS().getPAResponse());
        String decodedString = new String(decodedBytes);
        
        
        Gson gson = new Gson(); 
        PAResponseObject pares =  (PAResponseObject) gson.fromJson(decodedString, PAResponseObject.class);
        ThreeDSServerResultsResponse RRes = new ThreeDSServerResultsResponse ();
        RRes.setThreeDSServerTransID(pares.getThreeDSServerTransID());
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getThreeDSResultResponse(), RRes.toJSON().getBytes());
               
        ThreeDSServerResultsResponse threeDSServerResultsResponse = gson.fromJson(result.getData(),ThreeDSServerResultsResponse.class);
        
        if (threeDSServerResultsResponse != null){
            getmLogger().debug("===========================================================================");
            getmLogger().debug(" ThreeDSServerResultsResponse");
            getmLogger().debug("===========================================================================");        
            getmLogger().debug("ThreeDSServerTransID: " +threeDSServerResultsResponse.getThreeDSServerTransID());
            getmLogger().debug("ECI: " +threeDSServerResultsResponse.getEci());
            getmLogger().debug("TransStatus: " +threeDSServerResultsResponse.getTransStatus());
            getmLogger().debug("AuthenticationValue: " +threeDSServerResultsResponse.getAuthenticationValue());
            getmLogger().debug("DsTransID: " +threeDSServerResultsResponse.getResultsRequest().getDsTransID());
            getmLogger().debug("Result Response TransStatus: " +threeDSServerResultsResponse.getResultsRequest().getTransStatus());
            
        }
        
        return postilionAuth3DS2x(commandType, threeDSServerResultsResponse, paymentType, orderNumber, transactionId);

    }    
    
    

    /**
     * =========================================================================
     * postilionRefund
     * =========================================================================
     *
     * @param primaryAccountNumber
     * @param transactionAmount
     * @param accountExpiryDate
     * @param transactionId
     * @return
     * =========================================================================
     */
    public CancelOrRefundRS postilionRefund(String primaryAccountNumber,
            String transactionAmount,
            String accountExpiryDate,
            String transactionId,
            String merchantAccountCode,
            String currency) {

        getmLogger().info("Sending to Postilion Refund.", new Throwable().getStackTrace()[0]);
        CancelOrRefundRS response = new CancelOrRefundRS();

        CaptureElement captureElement = readCapture(transactionId);
        //MerchantCurrencyMap map = readMerchantId(merchantAccountCode, currency);

        AuthoriseRequest authObj = new AuthoriseRequest(primaryAccountNumber,
                "200000",
                checkAmount(transactionAmount),
                captureElement.getTransmissionDateTime(),
                captureElement.getSystemTraceAuditNumber(),
                captureElement.getLocalTranTime(),
                captureElement.getLocalTranDate(),
                accountExpiryDate, //checkExpiryDate(accountExpiryDate),
                this.getMerchantType(), //merchantType,            //DE18 
                "010", //posEntryMode             //DE22
                captureElement.getPosConditionCode(), //"00", //posConditionCode         //DE25  
                captureElement.getAcquiringInstitutionCode(),
                captureElement.getTerminalId(),
                this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42    // 30-Jul-2021 Mya
                this.getMerchantCurrencyMap().getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43  
                this.getMerchantCurrencyMap().getCode(), //"598", //currency <-- This won't work! Needs a curency code! //"598", //currencyCodeTransaction, //DE49    // 30-Jul-2021 Mya
                captureElement.getPosDataCode());           //posDataCode,             //DE123

        getmLogger().info("Refund Authorise Object: " + authObj.toJSON(), new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getPostilionRefund(), authObj.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);

        Integer responseCode = new Integer(postilionAuthRes.getResponseCode());
        String description = processResponseCode(postilionAuthRes.getResponseCode());

        getmLogger().info("ResponseCode :" + responseCode.toString(), new Throwable().getStackTrace()[0]);
        getmLogger().info("Description  :" + description, new Throwable().getStackTrace()[0]);
        getmLogger().info("Approval Id  :" + postilionAuthRes.getAuthIdResponse(), new Throwable().getStackTrace()[0]);

        response.setVersion("1");
        response.setResponseCode(responseCode.toString());
        response.setTransactionID(transactionId);
        response.setDescription(description);

        return response;
    }
    
    /**
     * =========================================================================
     * postilionJSONRefund
     * =========================================================================
     *
     * @param primaryAccountNumber
     * @param transactionAmount
     * @param accountExpiryDate
     * @param transactionId
     * @param merchantName
     * @param currency
     * @return
     * =========================================================================
     */
    public CancelOrRefundRS postilionJSONRefund(String primaryAccountNumber,
            String transactionAmount,
            String accountExpiryDate,
            String transactionId,
            String merchantName,
            String currency) {

        getmLogger().info("Sending to Postilion Refund.", new Throwable().getStackTrace()[0]);
        CancelOrRefundRS response = new CancelOrRefundRS();

        // Get financial with transaction id
        Financial financial = readFinancial(transactionId);
        
        // Currency mapping with Merchant name and currency
        MerchantCurrencyMap map = readMerchantCurrencyMap(merchantName, currency);
        
        Gson gson = new Gson();
        AuthoriseResponse postilionAuthRes;
        Financial newFinancial = new Financial();
        
        if (financial == null){
            
            //get current date time with Calendar()
            Calendar cal = Calendar.getInstance();
            
            DateFormat monthYrFormat = new SimpleDateFormat("MMyy");
            DateFormat hourMinSecFormat = new SimpleDateFormat("HHmmss");
            DateFormat txDateTimeFormat = new SimpleDateFormat("MMyyHHmmss");
            
            String sysTraceAuditNo = Integer.toString(generateRandomIntRange(100000, 999999));
            
            getmLogger().info("Random System Trace Audit Number generated= " + sysTraceAuditNo, new Throwable().getStackTrace()[0]);
            
            //saving ODE for Standalone Refund
            OriginalDataElement ode = new OriginalDataElement(transactionId,    //transactionId
                "0200",                                       //process code
                txDateTimeFormat.format(cal.getTime()),       //transmission date time
                sysTraceAuditNo,                              //system audit trace number ----unique 6 digits generated
                "60130200000",                                // acquiring institution code
                "00000000000");                               // forwarding institution id
            
            getmLogger().info("Saving original data element to database: " + ode.getDE90(), new Throwable().getStackTrace()[0]);

            SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
            sslComms.sendHttpPost(this.getOriginaldataWrite(), ode.toJSON().getBytes());
            
            AuthoriseRequest authObj = new AuthoriseRequest(primaryAccountNumber,       //card number entered on web portal
                    "200000",                                    //processing code
                    checkAmount(transactionAmount),              //transaction amount  -- entered on web portal
                    txDateTimeFormat.format(cal.getTime()),      //transmissiondatetime MMYYhhmmss  --current datetime
                    sysTraceAuditNo,                             // sys trace audit number
                    hourMinSecFormat.format(cal.getTime()),      //local tran time hhmmss      --current datetime
                    monthYrFormat.format(cal.getTime()),         //local tran date MMYY       --current datetime
                    accountExpiryDate,                           //expiryDate -- entered on web portal
                    this.getMerchantType(),                      //merchantType,            //DE18 
                    "010",                                       //posEntryMode             //DE22
                    "59",                                        //posConditionCode         //DE25  
                    "60130200000",                               // acquiring institution code
                    map.getAutoGeneratedTid(),                   // terminal id -- from currency mapping
                    map.getAutoGeneratedMid(),                   //merchantId,     -- from currency mapping     //DE42   
                    map.getCardAcceptorNameLocation(),           //cardAcceptorNameLocation -- from currency mapping     //DE43  
                    map.getCode(),                               //currencyCodeTransaction -- from currency mapping //DE49
                    "660550600001195");                          //posDataCode,             //DE123

            getmLogger().info("Standalone Refund Authorise Object: " + authObj.toJSON(), new Throwable().getStackTrace()[0]);

            SSLCommunicator ssl_comms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
            Result result = ssl_comms.sendHttpPost(this.getPostilionRefund(), authObj.toJSON().getBytes());
            
            getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
            
            postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);
            
        } else {
            
            getmLogger().info("Financial found for transaction id=: " + financial.getTransactionId(), new Throwable().getStackTrace()[0]);
            
            //saving ODE for Refund
            OriginalDataElement ode = new OriginalDataElement(transactionId,    //transactionId
                "0200",                                        //process code
                financial.getTransmissionDateTime(),           //transmission date time
                financial.getSystemTraceAuditNumber(),         //system audit trace number 
                financial.getAcquiringInstitutionCode(),       // acquiring institution code
                "00000000000");                                // forwarding institution id
            
            getmLogger().info("Saving original data element to database: " + ode.getDE90(), new Throwable().getStackTrace()[0]);

            SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
            sslComms.sendHttpPost(this.getOriginaldataWrite(), ode.toJSON().getBytes());
            

            AuthoriseRequest authObj = new AuthoriseRequest(primaryAccountNumber,
                    "200000",                                   //processing code
                    checkAmount(transactionAmount),             //transaction amount
                    financial.getTransmissionDateTime(),
                    financial.getSystemTraceAuditNumber(),
                    financial.getLocalTranTime(),
                    financial.getLocalTranDate(),
                    accountExpiryDate,                         // expiryDate,
                    this.getMerchantType(),                     //merchantType,            //DE18 
                    "010",                                      //posEntryMode             //DE22
                    financial.getPosConditionCode(),            //posConditionCode         //DE25  
                    financial.getAcquiringInstitutionCode(),
                    financial.getTerminalId(),
                    map.getAutoGeneratedMid(),                  //merchantId,               //DE42   
                    map.getCardAcceptorNameLocation(),          //cardAcceptorNameLocation  //DE43  
                    map.getCode(),                          //currencyCodeTransaction, //DE49
                    financial.getPosDataCode());           //posDataCode,             //DE123

            getmLogger().info("Refund Authorise Object: " + authObj.toJSON(), new Throwable().getStackTrace()[0]);

            SSLCommunicator ssl_Comms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
            Result result = ssl_Comms.sendHttpPost(this.getPostilionRefund(), authObj.toJSON().getBytes());

            getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

            postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);
            
        }
        
        getmLogger().info("Saving Refund financial for transactionId= " + transactionId + ", with AuthIdResponse= " + newFinancial.getAuthIdResponse(), new Throwable().getStackTrace()[0]);
        
        newFinancial.setTransactionId(transactionId);
        newFinancial.setMessageType(postilionAuthRes.getMerchantType());
        newFinancial.setPrimaryAccountNumber(postilionAuthRes.getPrimaryAccountNumber());
        newFinancial.setProcessingCode(postilionAuthRes.getProcessingCode());
        newFinancial.setAmount(new Integer(checkAmount(postilionAuthRes.getTransactionAmount())));
        newFinancial.setTransmissionDateTime(postilionAuthRes.getTransmissionDateTime());
        newFinancial.setSystemTraceAuditNumber(postilionAuthRes.getSystemTraceAuditNumber());   
        newFinancial.setLocalTranTime(postilionAuthRes.getLocalTranTime());
        newFinancial.setLocalTranDate(postilionAuthRes.getLocalTranDate());
        newFinancial.setExpiryDate(postilionAuthRes.getAccountExpiryDate());
        newFinancial.setSettlementDate(postilionAuthRes.getSettlementDate());             
        newFinancial.setPosConditionCode(postilionAuthRes.getPosConditionCode());
        newFinancial.setAcquiringInstitutionCode(postilionAuthRes.getAcquiringInstitutionCode());
        newFinancial.setRetrievalRef(postilionAuthRes.getRetrievalRefNumber());               
        newFinancial.setAuthIdResponse(postilionAuthRes.getAuthIdResponse());
        newFinancial.setTerminalId(postilionAuthRes.getTerminalId());
        newFinancial.setPosDataCode(postilionAuthRes.getPosDataCode());             
        newFinancial.setRouting(postilionAuthRes.getRoutingInformation());              

        writeFinancial(newFinancial);
        
        getmLogger().info("Refund financial for transactionId= " + transactionId + ", with AuthIdResponse= " + newFinancial.getAuthIdResponse(), new Throwable().getStackTrace()[0]);

        Integer responseCode = new Integer(postilionAuthRes.getResponseCode());
        String description = processResponseCode(postilionAuthRes.getResponseCode());
        
        getmLogger().info("ResponseCode :" + responseCode.toString(), new Throwable().getStackTrace()[0]);
        getmLogger().info("Description  :" + description, new Throwable().getStackTrace()[0]);
        getmLogger().info("Approval Id  :" + postilionAuthRes.getAuthIdResponse(), new Throwable().getStackTrace()[0]);

        response.setVersion("1");
        response.setResponseCode(responseCode.toString());
        response.setTransactionID(transactionId);
        response.setDescription(description);

        return response;
    }    

    /**
     * =========================================================================
     * postilionCapture
     * =========================================================================
     *
     * @param transactionAmount
     * @param transactionId
     * @param merchantAccountCode
     * @param currency
     * @return
     * =========================================================================
     */
    public CaptureRS postilionCapture(String transactionId,
            String transactionAmount, String merchantAccountCode, String currency) {

        Authorisation auth = readAuthorisation(transactionId);

        OriginalDataElement ode = readOriginalDataElement(auth.getSystemTraceAuditNumber(), transactionId);

        RoutingObj routingObj = extractSubfieldsDE127_03(auth.getRouting());

        getmLogger().info("Sending to Postilion Capture.", new Throwable().getStackTrace()[0]);

        CaptureRS response = new CaptureRS();

        //MerchantCurrencyMap map = readMerchantId(merchantAccountCode, currency);
        CaptureRequest captureReq = new CaptureRequest(auth.getPrimaryAccountNumber(), //primaryAccountNumber,  //DE2   
                "000000", //processingCode,        //DE3
                checkAmount(transactionAmount), //transactionAmount,       // DE4 
                auth.getSystemTraceAuditNumber(), //DE11
                auth.getExpiryDate(), //accountExpiryDate, YYMM  //DE14
                auth.getSettlementDate(), //dateSettlement           //DE15
                this.getMerchantType(),//"6300", //merchantType,            //DE18 
                "010", //posEntryMode             //DE22
                auth.getPosConditionCode(), //"00", //posConditionCode         //DE25 
                auth.getAcquiringInstitutionCode(),//acquiringInstitutionCode //DE32 
                auth.getRetrievalRef(), //retrievalRefString
                auth.getAuthIdResponse(),//authIdResponse   
                auth.getTerminalId(), //terminalId,               //DE41 
                this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42  // 30-Jul-2021 Mya
                this.getMerchantCurrencyMap().getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43 
                this.getMerchantCurrencyMap().getCode(), //"598", //currency <-- This won't work! Needs a curency code!currency, //"598", //currencyCodeTransaction,  //DE49 // 30-Jul-2021 Mya
                ode.getDE90(), // original data element    //DE90
                auth.getPosDataCode(), //posDataCode,              //DE123
                auth.getRetrievalRef(), //DE127.11 
                routingObj.getSinkNode() //DE127.26
        );

        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getPostilionCapture(), captureReq.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        CaptureResponse postilionCaptureRes = gson.fromJson(result.getData(), CaptureResponse.class);

        CaptureElement captureElement = new CaptureElement(transactionId,
                postilionCaptureRes.getMessageType(),
                postilionCaptureRes.getPrimaryAccountNumber(),
                postilionCaptureRes.getProcessingCode(),
                postilionCaptureRes.getTransactionAmount(),
                postilionCaptureRes.getTransmissionDateTime(),
                postilionCaptureRes.getSystemTraceAuditNumber(),
                postilionCaptureRes.getLocalTranTime(),
                postilionCaptureRes.getLocalTranDate(),
                postilionCaptureRes.getAccountExpiryDate(),
                postilionCaptureRes.getPosConditionCode(),
                postilionCaptureRes.getAcquiringInstitutionCode(),
                postilionCaptureRes.getRetrievalRefNumber(),
                postilionCaptureRes.getTerminalId(),
                postilionCaptureRes.getPosDataCode(),
                postilionCaptureRes.getRoutingInformation());

        PersistCapture(captureElement);

        Integer responseCode = new Integer(postilionCaptureRes.getResponseCode());
        String description = processResponseCode(postilionCaptureRes.getResponseCode());
        response.setVersion("1.0");
        response.setResponseCode(responseCode.toString());
        response.setTransactionID(transactionId);
        response.setDescription(description);

        return response;
    }

    /**
     * =========================================================================
     * postilionReversal
     * =========================================================================
     *
     * @param transactionId
     * @param transactionAmount
     * @param merchantAccountCode
     * @param currency
     * @return
     * =========================================================================
     */
    public CancelOrRefundRS postilionReversal(String transactionId,
            String transactionAmount,
            String merchantAccountCode,
            String currency) {

        getmLogger().info("Sending to Postilion Reversal.", new Throwable().getStackTrace()[0]);

        CancelOrRefundRS response = new CancelOrRefundRS();

        Authorisation auth = readAuthorisation(transactionId);

        OriginalDataElement ode = readOriginalDataElement(auth.getSystemTraceAuditNumber(), transactionId);

        getmLogger().info("Authorisation Object: " + auth.toJSON(), new Throwable().getStackTrace()[0]);
        getmLogger().info("OriginalDataElement Object: " + ode.toJSON(), new Throwable().getStackTrace()[0]);

        getmLogger().info("Routing: " + auth.getRouting(), new Throwable().getStackTrace()[0]);

        RoutingObj routingObj = extractSubfieldsDE127_03(auth.getRouting());

        getmLogger().info("Routing Object: " + routingObj.toJSON(), new Throwable().getStackTrace()[0]);

        //MerchantCurrencyMap map = readMerchantId(merchantAccountCode, currency);
        ReversalRequest reversalReq = new ReversalRequest(auth.getPrimaryAccountNumber(), //primaryAccountNumber,  //DE2   
                auth.getProcessingCode(), //processingCode,        //DE3
                checkAmount(auth.getAmount().toString()), //transactionAmount,       // DE4  
                auth.getTransmissionDateTime(),
                auth.getSystemTraceAuditNumber(),
                auth.getLocalTranTime(),
                auth.getLocalTranDate(),
                auth.getExpiryDate(), //accountExpiryDate, YYMM   //DE14
                auth.getSettlementDate(), //dateSettlement            //DE15
                this.getMerchantType(), //"6300", //merchantType,    4511         //DE18 
                "010", //posEntryMode              //DE22
                auth.getPosConditionCode(), //posConditionCode       //DE25    
                auth.getTerminalId(), //terminalId,               //DE41 
                this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,               //DE42   // 30-Jul-2021 Mya
                this.getMerchantCurrencyMap().getCardAcceptorNameLocation(),//cardAcceptorNameLocation,//cardAcceptorNameLocation, //DE43 
                this.getMerchantCurrencyMap().getCode(), //"598", //currency <-- This won't work! Needs a curency code!currency, //"598", //currencyCodeTransaction,  //DE49 // 30-Jul-2021 Mya
                "4000", //message reason code       //DE56
                ode.getDE90(), //original data element     //DE90
                auth.getPosDataCode(), //posDataCode,              //DE123
                auth.getRouting(), // Routing                  //DE127.03 
                auth.getRetrievalRef(), //orginal key               //DE127.11 
                routingObj.getSinkNode().trim() //original data      //DE127.26
        );

        getmLogger().info("ReversalRequest Object: " + reversalReq.toJSON(), new Throwable().getStackTrace()[0]);
        getmLogger().info("URL: " + this.getPostilionReversal(), new Throwable().getStackTrace()[0]);

        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getPostilionReversal(), reversalReq.toJSON().getBytes());
        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        ReversalResponse postilionReversalRes = gson.fromJson(result.getData(), ReversalResponse.class);

        Integer responseCode = new Integer(postilionReversalRes.getResponseCode());
        String description = processResponseCode(postilionReversalRes.getResponseCode());
        response.setVersion("1.0");
        response.setResponseCode(responseCode.toString());
        response.setTransactionID(transactionId);
        response.setDescription(description);

        return response;
    }

    /**
     * =========================================================================
     * processPosDataCode
     * =========================================================================
     * Grant O'Reilly(2021-08-05): PLEASE DO NOT CHANGE without speaking to me!
     * -------------------------------------------------------------------------
     *
     * @param cardType
     * @param transactionStatus
     * =========================================================================
     */
    private void processPosDataCode(String cardType, String transactionStatus) {

        String sTT = "95"; //Standard Terminal Type (sTT) was originally 90
        
        getmLogger().info("Initial PosDataCode: " + this.getPosDataCode(), new Throwable().getStackTrace()[0]);

        String subPosDataCode = this.getPosDataCode().substring(0, 13);
        
        switch (cardType) {
            case "CA"://Mastercard
                getmLogger().info("Processing PosDataCode for Mastercard.", new Throwable().getStackTrace()[0]);
                switch (transactionStatus.toUpperCase()) {
                    case "Y":
                        this.setPosDataCode(subPosDataCode + "92");
                        break;
                    case "N":
                    case "U":
                    case "R":    
                        this.setPosDataCode(subPosDataCode + sTT);
                        break;
                    case "A":
                        this.setPosDataCode(subPosDataCode + "91");
                        break;
                    default:
                        this.setPosDataCode(subPosDataCode + sTT);    
                }
                break;

            default: //Visa
                getmLogger().info("Processing PosDataCode for Visa card.", new Throwable().getStackTrace()[0]);
                switch (transactionStatus.toUpperCase()) {
                    case "Y":
                        this.setPosDataCode(subPosDataCode + "92");
                        break;
                    case "N":
                    case "R":    
                    case "ERROR":
                        this.setPosDataCode(subPosDataCode + sTT); //was "91" 2022-07-05
                        break;
                    case "U":
                        this.setPosDataCode(subPosDataCode + sTT);
                        break;
                    case "A":    
                    case "":
                        this.setPosDataCode(subPosDataCode + "91");
                        break;
                    case "BLANK":
                        this.setPosDataCode(subPosDataCode + sTT); //was "91" 2022-07-05
                        break;
                    case "BLANK2":
                        this.setPosDataCode(subPosDataCode + sTT);
                        break;
                    // The default here is usually "" for Visa originally it was set to 91
                    // but from  SIT21-1105 it was decided to make 90 - Grant O'Reilly 
                    // 2021-09-20    
                    //default: this.posDataCode = subPosDataCode + "91";
                    default:
                        this.setPosDataCode(subPosDataCode + sTT);
                }

        }
        getmLogger().info("PosDataCode: " + this.getPosDataCode(), new Throwable().getStackTrace()[0]);

    }

    /**
     * =========================================================================
     * processUCAFCollectionInd
     * =========================================================================
     * Grant O'Reilly(2021-08-05): PLEASE DO NOT CHANGE without speaking to me!
     * -------------------------------------------------------------------------
     * UCAF collection indicator
     * -------------------------------------------------------------------------
     * 0 = UCAF data collection is not supported at the merchant's web site. 1 =
     * UCAF data collection is supported by the merchant, but UCAF data was not
     * populated. 2 = UCAF data collection is supported by the merchant and the
     * UCAF data was populated.
     * -------------------------------------------------------------------------
     *
     * @param transactionStatus
     * @return
     * =========================================================================
     */
    private int processUCAFCollectionInd(String transactionStatus) {

        switch (transactionStatus.toUpperCase()) {
            case "Y":
                return 2;
            case "N":
            case "U":
                return 0;
            case "A":
                return 1;
            default:
                return 0;
        }
    }

    private String processUCAFPosition2(String transactionStatus) {
        switch (transactionStatus.toUpperCase()) {
            case "Y":
                return "j";
            case "A":
                return "h";
            case "N":
            case "U":
            default:
                return "";
        }
    }

    /**
     * =========================================================================
     * processSecureResult
     * =========================================================================
     * Grant O'Reilly(2021-08-12): PLEASE DO NOT CHANGE without speaking to me!
     * -------------------------------------------------------------------------
     *
     * @param transactionStatus
     * @return
     * =========================================================================
     */
    //private String processSecureResult(String transactionStatus, String cavv){
    private String processSecureResult(String transactionStatus) {

        switch (transactionStatus.toUpperCase()) {
            case "" :
            case " ":
            case "R":    
                return "";
            case "ERROR":
                return "0";
            case "ENROLL_N":    
                return "";
            case "N":
                return "1";
            case "Y":
                return "2";
            case "A":
                return "3";
            case "U":
                return "4";
            default:
                return "0";
        }
    }

    /**
     * =========================================================================
     * ThreeDSTransaction
     * =========================================================================
     *
     * @param paResValidResponse
     * @param paymentType
     * @param merchantAccountCode
     * @return
     * =========================================================================
     */
    private AuthoriseResponse ThreeDSTransaction(PaResValidationResponse paResValidResponse,
            PaymentDetailType paymentType) {

        getmLogger().info("Processing 3DS card.", new Throwable().getStackTrace()[0]);
        //byte[] decoded_xid = Base64.getDecoder().decode(paResValidResponse.getXid());
        byte[] decoded_xid = Base64.getDecoder().decode("");
        byte[] decoded_cavv = Base64.getDecoder().decode(paResValidResponse.getCavv());
        byte[] ds = concatBytes(decoded_xid, decoded_cavv);
        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
        String threeDSecureData = paResValidResponse.getXid() + paResValidResponse.getCavv();
        String transactionStatus = paResValidResponse.getTransactionStatus();
        String cavv = paResValidResponse.getCavv();
        String posConditionCode = "59";

        getmLogger().info("3DSecureData: " + threeDSecureData, new Throwable().getStackTrace()[0]);
        getmLogger().info("Decoded XiD length: " + String.valueOf(decoded_xid.length) + " Byte array: " + String.valueOf(displayBytes(decoded_xid)), new Throwable().getStackTrace()[0]);
        getmLogger().info("Decoded CAVV length: " + String.valueOf(decoded_cavv.length) + " Byte array: " + String.valueOf(displayBytes(decoded_cavv)), new Throwable().getStackTrace()[0]);
        getmLogger().info("3DSecureData length: " + String.valueOf(ds.length) + " Byte array: " + String.valueOf(displayBytes(ds)), new Throwable().getStackTrace()[0]);
        getmLogger().info("Payment method code: " + paymentType.getPaymentMethod().getPaymentMethodCode(), new Throwable().getStackTrace()[0]);

        String avd = AddressVerificationFormat(paymentType.getBillingAddress());

        getmLogger().info("AVD: " + avd, new Throwable().getStackTrace()[0]);

        AuthoriseRequest authObj;
        if (cardType.equals("CA")) { //This is Mastercard

            getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);

            int ucafCollectionInd = processUCAFCollectionInd(transactionStatus);

            String ucaf = ucafCollectionInd + paResValidResponse.getCavv();
            //------------------------------------------------------------------
            // Please DONOT remove this code 
            // Grant O'Reilly : This is incase Netcetera does not take care of 
            //                  the position 2 "j" or "h"
            //------------------------------------------------------------------
            //String position2 = processUCAFPosition2(transactionStatus);
            //String ucaf = ucafCollectionInd +position2+ paResValidResponse.getCavv(); //2 ->UCAF data collection is supported by the merchant and the UCAF data was populated. 
            //String ucaf = ucafCollectionInd + "JAmi21makAifmwqo2120cjq1AAA=";
            //------------------------------------------------------------------

            getmLogger().info("UCAF: " + ucaf, new Throwable().getStackTrace()[0]);

            posConditionCode = "06";
            getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);

            processPosDataCode(cardType, transactionStatus);
            getmLogger().info("ucaf Collection Ind : " + ucafCollectionInd, new Throwable().getStackTrace()[0]);
            getmLogger().info("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
            getmLogger().info("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);

            authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
                    "000000", //processingCode,          //DE3  
                    checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
                    checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
                    this.getMerchantType(), //"6300", //merchantType,            //DE18 
                    "010", //posEntryMode             //DE22
                    posConditionCode, //"00", //posConditionCode         //DE25
            getAcquiringInstitutionCodePostilion(), //acquiringInstitutionCode //DE32
                    this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
                    this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
                    this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
                    this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. ,//"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
            getPosDataCode(), //DE123 // 4-Aug-2021 Grant O'Reilly
                    paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
                    "", //DE127.15
                    "", //DE127.27
                    "", //AmericanExpressCardIdentifier, ////DE127.28 
                    null, //threeDSecureData,        //DE127.29
                    "", //threeDSecureResult,      //DE127.30
                    ucaf, // 4-Aug-2021 Grant O'Reilly
                    null); // 20-Oct-2021 Grant O'Reilly
        } else {

            getmLogger().info("Processing Visa card .....", new Throwable().getStackTrace()[0]);

            String secureResult = processSecureResult(transactionStatus);

            processPosDataCode(cardType, transactionStatus);

            getmLogger().info("Secure Result [DE127.30]: " + secureResult, new Throwable().getStackTrace()[0]);
            getmLogger().info("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
            getmLogger().info("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);

            posConditionCode = "59";
            getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);

            //------------------------------------------------------------------
            // Please DONOT remove this code 2021-11-24
            // Grant O'Reilly : This is an update due to issues on Visa
            // Need to replicate the method use for Mastyercard but include the 
            // 
            //------------------------------------------------------------------
            getmLogger().info("PaRes XiD: " + paResValidResponse.getXid());
            getmLogger().info("PaRes CAVV: " + paResValidResponse.getCavv());
            String hexValue = byteArrayToHex(ds);
            getmLogger().info("CAVV Hex: " + (hexValue));
            getmLogger().info("CAVV ASCII: " + (asciiToHex(hexValue)));

            byte[] dsBytes = hexStringToByteArray(asciiToHex(byteArrayToHex(ds)));

            getmLogger().info("CAVV Hex BYTES: " + Arrays.toString(dsBytes));

            authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
                    "000000", //processingCode,          //DE3  
                    checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
                    checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
                    this.getMerchantType(), //"6300", //merchantType,            //DE18 
                    "010", //posEntryMode             //DE22
                    posConditionCode, //"00", //posConditionCode         //DE25
            getAcquiringInstitutionCodePostilion(), //acquiringInstitutionCode //DE32
                    this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
                    this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
                    this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
                    this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. //"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
            getPosDataCode(), //DE123 // 4-Aug-2021 Grant O'Reilly
                    paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
                    avd, //DE127.15
                    "", //DE127.27
                    "", //AmericanExpressCardIdentifier, ////DE127.28 
                    //ds, //threeDSecureData,        //DE127.29
                    dsBytes, //threeDSecureData,     //DE127.29
                    secureResult,              //threeDSecureResult,      //DE127.30  //"2"  // 4-Aug-2021 Grant O'Reilly
                    null);  // structuredData //DE127.22  //4-July-2022  Grant O'Reilly
        }

        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getPostilionAuthorise(), authObj.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);

        return postilionAuthRes;
    }

    
    private byte[] checkBytes(byte[] dsBytes){
        if (dsBytes.length == 0)
            return null;
        else 
            return dsBytes;
    }
    /**
     * =========================================================================
     * ThreeDS2xTransaction
     * =========================================================================
     *
     * @param paResValidResponse
     * @param paymentType
     * @param merchantAccountCode
     * @return
     * =========================================================================
     */
    private AuthoriseResponse ThreeDS2xTransaction(ThreeDSServerResultsResponse threeDSServerResultsResponse, PaymentDetailType paymentType) {

        getmLogger().info("Processing 3DS card.", new Throwable().getStackTrace()[0]);

        byte[] decoded_xid = Base64.getDecoder().decode("");
        byte[] decoded_cavv = Base64.getDecoder().decode("");
        
        String authenticationValue = "";
        
        if (threeDSServerResultsResponse.getAuthenticationValue() != null){
            authenticationValue = threeDSServerResultsResponse.getAuthenticationValue();
            decoded_cavv = Base64.getDecoder().decode(threeDSServerResultsResponse.getAuthenticationValue());
        }
        byte[] ds = concatBytes(decoded_xid, decoded_cavv);
        
        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();
        String cavv = authenticationValue ;
        String threeDSecureData = "" + cavv;
        String transactionStatus = threeDSServerResultsResponse.getTransStatus();
        
        String posConditionCode = "59";

        getmLogger().info("3DSecureData: " + threeDSecureData, new Throwable().getStackTrace()[0]);
        getmLogger().info("Decoded XiD length: " + String.valueOf(decoded_xid.length) + " Byte array: " + String.valueOf(displayBytes(decoded_xid)), new Throwable().getStackTrace()[0]);
        getmLogger().info("Decoded CAVV length: " + String.valueOf(decoded_cavv.length) + " Byte array: " + String.valueOf(displayBytes(decoded_cavv)), new Throwable().getStackTrace()[0]);
        getmLogger().info("3DSecureData length: " + String.valueOf(ds.length) + " Byte array: " + String.valueOf(displayBytes(ds)), new Throwable().getStackTrace()[0]);
        getmLogger().info("Payment method code: " + paymentType.getPaymentMethod().getPaymentMethodCode(), new Throwable().getStackTrace()[0]);

        String avd = AddressVerificationFormat(paymentType.getBillingAddress());

        getmLogger().info("AVD: " + avd, new Throwable().getStackTrace()[0]);
        
        //----------------------------------------------------------------------
        // Begin Fix for the issue where AuthenticationMethod is null 
        // made by Grant O'Reilly 2022-10-14
        String authenticationType = "02";
        
        if (threeDSServerResultsResponse.getResultsRequest().getAuthenticationMethod() == null){
             getmLogger().info("authenticationMethod is null! Use the authenticationType");
             if (threeDSServerResultsResponse.getResultsRequest().getAuthenticationType() == null){
                 getmLogger().info("authenticationType is also null! Use default authenticationType = 02");
             }
             else{
                 authenticationType = threeDSServerResultsResponse.getResultsRequest().getAuthenticationType();
             }
        }
        else{
            authenticationType = threeDSServerResultsResponse.getResultsRequest().getAuthenticationMethod();
        }
        //End Fix for the issue where AuthenticationMethod   
        //----------------------------------------------------------------------
        
        String structuredData = constructStructuredDataDE127_22("2",
                                                                authenticationType,
                                                                threeDSServerResultsResponse.getResultsRequest().getDsTransID());
        getmLogger().info("Structured Data DE127.22: " + structuredData, new Throwable().getStackTrace()[0]);
        
        AuthoriseRequest authObj;
        if (cardType.equals("CA")) { //This is Mastercard

            getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);

            int ucafCollectionInd = processUCAFCollectionInd(transactionStatus);

            String ucaf = ucafCollectionInd + authenticationValue; //Use this for production
            getmLogger().info("UCAF for production: "+ucaf);
            
            //------------------------------------------------------------------
            // Please DONOT remove this code 
            // Grant O'Reilly : This is incase Netcetera does not take care of 
            //                  the position 2 "j" or "h"
            //------------------------------------------------------------------
            //String position2 = processUCAFPosition2(transactionStatus);
            //String ucaf = ucafCollectionInd +position2+  authenticationValue; //Use this for prev
            ////String ucaf = ucafCollectionInd +position2+ paResValidResponse.getCavv(); //2 ->UCAF data collection is supported by the merchant and the UCAF data was populated. 
            ////String ucaf = ucafCollectionInd + "JAmi21makAifmwqo2120cjq1AAA=";
            //------------------------------------------------------------------

            getmLogger().info("UCAF: " + ucaf, new Throwable().getStackTrace()[0]);

            posConditionCode = "06";
            getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);

            processPosDataCode(cardType, transactionStatus);
            getmLogger().info("ucaf Collection Ind : " + ucafCollectionInd, new Throwable().getStackTrace()[0]);
            getmLogger().info("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
            getmLogger().info("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);

            authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
                    "000000", //processingCode,          //DE3  
                    checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
                    checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
                    this.getMerchantType(), //"6300", //merchantType,            //DE18 
                    "010", //posEntryMode             //DE22
                    posConditionCode, //"00", //posConditionCode         //DE25
            getAcquiringInstitutionCodePostilion(), //acquiringInstitutionCode //DE32
                    this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
                    this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
                    this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
                    this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. ,//"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
            getPosDataCode(), //DE123 // 4-Aug-2021 Grant O'Reilly
                    paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
                    "", //DE127.15
                    "", //DE127.27
                    "", //AmericanExpressCardIdentifier, ////DE127.28 
                    null, //threeDSecureData,        //DE127.29
                    "", //threeDSecureResult,      //DE127.30
                    ucaf, // 4-Aug-2021 Grant O'Reilly
                    structuredData); // 20-Oct-2021 Grant O'Reilly
        } else {

            getmLogger().info("Processing Visa card .....", new Throwable().getStackTrace()[0]);

            String secureResult = processSecureResult(transactionStatus);

            processPosDataCode(cardType, transactionStatus);

            getmLogger().info("Secure Result [DE127.30]: " + secureResult, new Throwable().getStackTrace()[0]);
            getmLogger().info("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
            getmLogger().info("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);

            posConditionCode = "59";
            getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);

            //------------------------------------------------------------------
            // Please DONOT remove this code 2021-11-24
            // Grant O'Reilly : This is an update due to issues on Visa
            // Need to replicate the method use for Mastyercard but include the 
            // 
            //------------------------------------------------------------------
            //getmLogger().info("PaRes XiD: " + paResValidResponse.getXid());
            getmLogger().info("PaRes CAVV: " + cavv);
            String hexValue = byteArrayToHex(ds);
            getmLogger().info("CAVV Hex: " + (hexValue));
            getmLogger().info("CAVV ASCII: " + (asciiToHex(hexValue)));

            byte[] dsBytes = hexStringToByteArray(asciiToHex(byteArrayToHex(ds)));

            getmLogger().info("CAVV Hex BYTES: " + Arrays.toString(dsBytes));

            authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
                    "000000", //processingCode,          //DE3  
                    checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
                    checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
                    this.getMerchantType(), //"6300", //merchantType,            //DE18 
                    "010", //posEntryMode             //DE22
                    posConditionCode, //"00", //posConditionCode         //DE25
            getAcquiringInstitutionCodePostilion(), //acquiringInstitutionCode //DE32
                    this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
                    this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
                    this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
                    this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. //"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
            getPosDataCode(), //DE123 // 4-Aug-2021 Grant O'Reilly
                    paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
                    avd, //DE127.15
                    "", //DE127.27
                    "", //AmericanExpressCardIdentifier, ////DE127.28 
                    //ds, //threeDSecureData,        //DE127.29
                    checkBytes(dsBytes), //dsBytes, //threeDSecureData,     //DE127.29
                    secureResult, //threeDSecureResult,      //DE127.30  //"2"  // 4-Aug-2021 Grant O'Reilly
                    structuredData);        //structuredData //DE127.22  //4-July-2022  Grant O'Reilly
        }

        
        getmLogger().debug("Sending to : "+this.getPostilionAuthorise());
        getmLogger().debug("Auth to Postilion : "+authObj.toJSON());   
        
        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getPostilionAuthorise(), authObj.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);

        return postilionAuthRes;
    }    
    
    /**
     * =========================================================================
     * NonThreeDS2xTransaction
     * =========================================================================
     *
     * @param paResValidResponse
     * @param paymentType
     * @param merchantAccountCode
     * @return
     */
    private AuthoriseResponse NonThreeDS2xTransaction(PaymentDetailType paymentType, String issuetype) {
        

        
        
        getmLogger().info("Processing non-3DS2x card.", new Throwable().getStackTrace()[0]);

        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();

        String secureResult = "";
        int ucafCollectionInd = 0;
        String ucaf = ucafCollectionInd + "";
        String posConditionCode = "59";


        if (cardType.equals("CA")) { //This is Mastercard
            getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
            posConditionCode = "06";
            processPosDataCode(cardType, "U");
        } else {
            getmLogger().info("Processing Visa card .....", new Throwable().getStackTrace()[0]);
            switch(issuetype){
                case "ARES_ERROR" :
                                    processPosDataCode(cardType, "BLANK");
                                    secureResult = processSecureResult("ERROR");
                                    break;                    
                case "ENROLL_N"   : 
                                    processPosDataCode(cardType, "BLANK");
                                    secureResult = processSecureResult("ENROLL_N");
                                    break;
            }
            
            posConditionCode = "59";
            ucaf = null;
        }
        
        getmLogger().info("Incoming expiryDate:"+paymentType.getPaymentCard().getExpireDate());

        AuthoriseRequest authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
                "000000", //processingCode,          //DE3  
                checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
                checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
                this.getMerchantType(), //"6300", //merchantType,            //DE18 
                "010", //posEntryMode             //DE22
                posConditionCode, //"00", //posConditionCode         //DE25
        getAcquiringInstitutionCodePostilion(), //acquiringInstitutionCode //DE32
                this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
                this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
                this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
                this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. , //"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
        getPosDataCode(), //posDataCode,             //DE123
                paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
                "", //DE127.15
                "", //DE127.27
                "", //AmericanExpressCardIdentifier, ////DE127.28 
                null, //threeDSecureData,        //DE127.29
                secureResult, //threeDSecureResult,      //DE127.30
                ucaf,
                null);

        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getPostilionAuthorise(), authObj.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);

        return postilionAuthRes;
      
    }   
    
    /**
     * =========================================================================
     * NonThreeDSTransaction
     * =========================================================================
     *
     * @param paResValidResponse
     * @param paymentType
     * @param merchantAccountCode
     * @return
     */
    private AuthoriseResponse NonThreeDSTransaction(PaResValidationResponse paResValidResponse,
            PaymentDetailType paymentType) {
        getmLogger().info("Processing non-3DS card.", new Throwable().getStackTrace()[0]);

        String transactionStatus = paResValidResponse.getTransactionStatus();
        String cavv = paResValidResponse.getCavv();
        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();

        if (paResValidResponse.getXid() != null) {
            byte[] decoded_xid = Base64.getDecoder().decode(paResValidResponse.getXid());

            getmLogger().info("Decoded XiD length: " + String.valueOf(decoded_xid.length) + " Byte array: " + String.valueOf(displayBytes(decoded_xid)), new Throwable().getStackTrace()[0]);
        }

        String secureResult = "";
        int ucafCollectionInd = 0;
        String ucaf = ucafCollectionInd + "";
        String posConditionCode = "59";

        if (transactionStatus != null) {

            if (cardType.equals("CA")) { //This is Mastercard
                getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
                processPosDataCode(cardType, transactionStatus);
                posConditionCode = "06";
                getmLogger().info("ucaf Collection Ind : " + ucafCollectionInd, new Throwable().getStackTrace()[0]);
                getmLogger().info("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
                getmLogger().info("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);
                getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
            } else {

                getmLogger().info("Processing Visa card .....", new Throwable().getStackTrace()[0]);
                processPosDataCode(cardType, transactionStatus);
                secureResult = processSecureResult(transactionStatus);
                ucaf = null;
                posConditionCode = "59";
                getmLogger().info("Secure Result [DE127.30]: " + secureResult, new Throwable().getStackTrace()[0]);
                getmLogger().info("CAVV : " + cavv, new Throwable().getStackTrace()[0]);
                getmLogger().info("Transaction Status: " + transactionStatus, new Throwable().getStackTrace()[0]);
                getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
            }
        } else {
            if (cardType.equals("CA")) { //This is Mastercard
                getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
                posConditionCode = "06";
                processPosDataCode(cardType, "U");
            } else {
                getmLogger().info("Processing Visa card .....", new Throwable().getStackTrace()[0]);
                processPosDataCode(cardType, "BLANK");
                secureResult = processSecureResult("ERROR");
                posConditionCode = "59";
                ucaf = null;
            }
        }
        
        getmLogger().info("Incoming expiryDate:"+paymentType.getPaymentCard().getExpireDate());

        AuthoriseRequest authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
                "000000", //processingCode,          //DE3  
                checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
                checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
                this.getMerchantType(), //"6300", //merchantType,            //DE18 
                "010", //posEntryMode             //DE22
                posConditionCode, //"00", //posConditionCode         //DE25
        getAcquiringInstitutionCodePostilion(), //acquiringInstitutionCode //DE32
                this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
                this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
                this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
                this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. , //"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
        getPosDataCode(), //posDataCode,             //DE123
                paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
                "", //DE127.15
                "", //DE127.27
                "", //AmericanExpressCardIdentifier, ////DE127.28 
                null, //threeDSecureData,        //DE127.29
                secureResult, //threeDSecureResult,      //DE127.30
                ucaf,
                null);

        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getPostilionAuthorise(), authObj.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);

        return postilionAuthRes;
    }

    /**
     *
     * @param commandType
     * @param response
     * @param paymentType
     * @return
     */
    public AuthRS NonThreeDSTransaction(String commandType,
                                        AuthRS response, 
                                        PaymentDetailType paymentType) {
        //Visa will be CAVV resultcode blank and posdata 90, UCAF 0 and posdata 90
        getmLogger().info("Processing non-3DS card.", new Throwable().getStackTrace()[0]);
        String cardType = paymentType.getPaymentMethod().getPaymentMethodCode();

        String secureResult = "";
        int ucafCollectionInd = 0;
        String ucaf = ucafCollectionInd + "";
        String posConditionCode = "59";

        if (cardType.equals("CA")) { //This is Mastercard
            getmLogger().info("Processing Mastercard .....", new Throwable().getStackTrace()[0]);
            processPosDataCode(cardType, "N");
            posConditionCode = "06";
            getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
        } else {

            getmLogger().info("Processing Visa card .....");
            processPosDataCode(cardType, "BLANK2");
            posConditionCode = "59";
            getmLogger().info("pos Condition Code: " + posConditionCode, new Throwable().getStackTrace()[0]);
            ucaf = null;
        }

        getmLogger().info("Incoming expiryDate:"+paymentType.getPaymentCard().getExpireDate());
        
        AuthoriseRequest authObj = new AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
                "000000", //processingCode,          //DE3  
                checkAmount(paymentType.getAmountDetail().getAmount().toString()), //transactionAmount,       // DE4  
                checkExpiryDate(paymentType.getPaymentCard().getExpireDate()), //accountExpiryDate, YYMM  //DE14
                this.getMerchantType(), //"6300", //merchantType,            //DE18 
                "010", //posEntryMode             //DE22
                posConditionCode, //"00", //posConditionCode         //DE25
        getAcquiringInstitutionCodePostilion(), //acquiringInstitutionCode //DE32
                this.getMerchantCurrencyMap().getAutoGeneratedTid(), //terminalId, //terminalId,              //DE41 // 30-Jul-2021 Mya
                this.getMerchantCurrencyMap().getAutoGeneratedMid(), //merchantId, //merchantId,              //DE42 // 30-Jul-2021 Mya
                this.getMerchantCurrencyMap().getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43
                this.getMerchantCurrencyMap().getCode(), //"598",//paymentType.getAmountDetail().getCurrencyCode()// <--This will NOT work!, needs to be a currency code. ,//"598", //currencyCodeTransaction, //DE49 // 30-Jul-2021 Mya
        getPosDataCode(), //DE123 // 4-Aug-2021 Grant O'Reilly
                paymentType.getPaymentCard().getCVC(), //cvv2,                  //DE127.10
                "", //DE127.15
                "", //DE127.27
                "", //AmericanExpressCardIdentifier, ////DE127.28 
                null, //threeDSecureData,        //DE127.29
                secureResult, //threeDSecureResult,      //DE127.30
                ucaf, // 4-Aug-2021 Grant O'Reilly 
                null); // 20-Oct-2021 Grant O'Reilly 

        SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getPostilionAuthorise(), authObj.toJSON().getBytes());

        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        AuthoriseResponse postilionAuthRes = gson.fromJson(result.getData(), AuthoriseResponse.class);

        return processPostilionAuthRes(commandType, postilionAuthRes,
                null,
                response);
    }

    /**
     * =========================================================================
     * postilionAuth
     * =========================================================================
     *
     * @param paResValidResponse
     * @param paymentType
     * @param orderNumber
     * @param transactionId
     * @return
     * =========================================================================
     */
    private AuthRS postilionAuth(String commandType, PaResValidationResponse paResValidResponse,
            PaymentDetailType paymentType,
            String orderNumber,
            String transactionId) {

        getmLogger().info("Sending to Postilion Authorise.", new Throwable().getStackTrace()[0]);

        AuthoriseResponse postilionAuthRes;

        if (paResValidResponse.getCavv() != null) {
            postilionAuthRes = ThreeDSTransaction(paResValidResponse, paymentType);
        } else {
            postilionAuthRes = NonThreeDSTransaction(paResValidResponse, paymentType);
        }

        AuthRS response = new AuthRS();
        response.setOrderNumber(orderNumber);
        response.setTransactionID(transactionId);
        
        return processPostilionAuthRes(
                commandType,
                postilionAuthRes,
                paResValidResponse,
                response);
    }
    /**
     * =========================================================================
     * postilionAuth3DS2x
     * =========================================================================
     *
     * @param paResValidResponse
     * @param paymentType
     * @param orderNumber
     * @param transactionId
     * @return
     * =========================================================================
     */
    private AuthRS postilionAuth3DS2x(String commandType, 
                                      ThreeDSServerResultsResponse threeDSServerResultsResponse,
                                      PaymentDetailType paymentType,
                                      String orderNumber,
                                      String transactionId) {

        getmLogger().info("Sending to Postilion Authorise.", new Throwable().getStackTrace()[0]);

        AuthoriseResponse postilionAuthRes;

        if (threeDSServerResultsResponse != null) {
            if (threeDSServerResultsResponse.getResultsRequest().getTransStatus().equals("ENROLL_N")){
                postilionAuthRes = NonThreeDS2xTransaction(paymentType,"ENROLL_N");
            }
            else if(threeDSServerResultsResponse.getResultsRequest().getTransStatus().equals("ARES_ERROR")){
                postilionAuthRes = NonThreeDS2xTransaction(paymentType,"ARES_ERROR");
            } 
            else{
                postilionAuthRes = ThreeDS2xTransaction(threeDSServerResultsResponse, paymentType);
            }
        } else {
            postilionAuthRes = NonThreeDS2xTransaction(paymentType,"ENROLL_N");
        }

        AuthRS response = new AuthRS();
        response.setOrderNumber(orderNumber);
        response.setTransactionID(transactionId);
        
        return processPostilionAuthRes(
                commandType,
                postilionAuthRes,
                null,
                response);
    }
    /**
     *
     * @param postilionAuthRes
     * @return
     */
    private AuthRS processPostilionAuthRes(
            String commandType,
            AuthoriseResponse postilionAuthRes,
            PaResValidationResponse paResValidResponse,
            AuthRS response) {
            //String orderNumber,
            //String transactionId) {
        
        //AuthRS response = new AuthRS();

        Integer responseCode = new Integer(postilionAuthRes.getResponseCode());
        String description = processResponseCode(postilionAuthRes.getResponseCode());

        switch(commandType){
            case "0100" :
                            if ((responseCode == 0) || (responseCode == 91)) {
                                getmLogger().info("Persisting transaction as response is approved.", new Throwable().getStackTrace()[0]);
                                PersistTransaction(response.getTransactionID(), "0100", postilionAuthRes, this.getAuthorisationWrite());
                            }
                            break;
                
            case "0200" :   
                            if ((responseCode == 0)) { //not Sabre 91 response code  || (responseCode == 91)) {
                                getmLogger().info("Persisting transaction as response is approved.", new Throwable().getStackTrace()[0]);
                                PersistTransaction(response.getTransactionID(), "0200", postilionAuthRes, this.getAuthorisationWrite());
                            }                
        }
        


        String avs = "E";
        String cvc = "C";

        if (postilionAuthRes.getAddressVerificationResult() != null) {
            avs = processAVS(postilionAuthRes.getAddressVerificationResult());
        }
        if (postilionAuthRes.getThreeDSecureResult() != null) {
            cvc = processCVC(postilionAuthRes.getThreeDSecureResult());
        }

        getmLogger().info("ResponseCode :" + responseCode.toString(), new Throwable().getStackTrace()[0]);
        getmLogger().info("Description  :" + description, new Throwable().getStackTrace()[0]);
        getmLogger().info("Approval Id  :" + postilionAuthRes.getAuthIdResponse(), new Throwable().getStackTrace()[0]);
        getmLogger().info("AVS          :" + avs, new Throwable().getStackTrace()[0]);
        getmLogger().info("CVC          : ###(" + HashAString.hash(cvc) + ")", new Throwable().getStackTrace()[0]);

        if (responseCode != 0) {
            return DeclineAuth(response.getOrderNumber(), response.getTransactionID());
        }

        //3DS response
        response.setVersion("1.0");
        response.setResponseCode(responseCode.toString());
        response.setDescription(description);
        response.setApprovalCode(postilionAuthRes.getAuthIdResponse());
        response.setAVSResultCode(avs);
        response.setCVCResultCode(cvc); //response.setCVCResultCode("A");//response.setCVCResultCode(cvc);
        response.setDebitCard(Boolean.FALSE);
        //response.setOrderNumber(orderNumber);
        //response.setTransactionID(transactionId);
        response.setDebitCard(Boolean.FALSE);

        if (paResValidResponse != null) {
            AuthRS.T3DSResult t3ds = new AuthRS.T3DSResult();

            t3ds.setCAVV(paResValidResponse.getCavv()); //t3ds.setCAVV("ANANANAN");
            t3ds.setECI(paResValidResponse.getEci()); //t3ds.setECI("5");  // Electronic commerce indicator 

            response.setT3DSResult(t3ds);
        }
        
        if(commandType.equals("0200")){
            Order order = new Order();

            order.setOrder_id(response.getOrderNumber());
            order.setTransaction_ref(response.getTransactionID());
            order.setStatus("success");
            order.setStatus_value(responseCode.toString());
            order.setStatus_description(description);

            SSLCommunicator sslComms = new SSLCommunicator(getDecryptedUserName(), getDecryptedPassword(), this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
            Result result = sslComms.sendHttpPost(this.getUpdateOrder(), order.toJSON().getBytes());
        }
        

        return response;
    }
    
    protected int generateRandomIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }    

    /**
     * @return the originaldataWrite
     */
    public String getOriginaldataWrite() {
        return originaldataWrite;
    }

    /**
     * @return the originaldataRead
     */
    public String getOriginaldataRead() {
        return originaldataRead;
    }

    /**
     * @return the authorisationWrite
     */
    public String getAuthorisationWrite() {
        return authorisationWrite;
    }

    /**
     * @return the authorisationRead
     */
    public String getAuthorisationRead() {
        return authorisationRead;
    }

    /**
     * @return the threedsCreateReq
     */
    public String getThreedsCreateReq() {
        return threedsCreateReq;
    }

    /**
     * @return the threedsValidatePaRes
     */
    public String getThreedsValidatePaRes() {
        return threedsValidatePaRes;
    }

    /**
     * @return the postilionAuthorise
     */
    public String getPostilionAuthorise() {
        return postilionAuthorise;
    }

    /**
     * @return the postilionRefund
     */
    public String getPostilionRefund() {
        return postilionRefund;
    }

    /**
     * @return the postilionCapture
     */
    public String getPostilionCapture() {
        return postilionCapture;
    }

    /**
     * @return the postilionReversal
     */
    public String getPostilionReversal() {
        return postilionReversal;
    }

    /**
     * @return the captureWrite
     */
    public String getCaptureWrite() {
        return captureWrite;
    }

    /**
     * @return the captureRead
     */
    public String getCaptureRead() {
        return captureRead;
    }

    /**
     * @return the merchantCurrencyMapRead
     */
    public String getMerchantCurrencyMapRead() {
        return merchantCurrencyMapRead;
    }

    /**
     * @param merchantCurrencyMapRead the merchantCurrencyMapRead to set
     */
    public void setMerchantCurrencyMapRead(String merchantCurrencyMapRead) {
        this.merchantCurrencyMapRead = merchantCurrencyMapRead;
    }

    /**
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

     /**
     * ========================================================================= 
     * MerchantCurrencyMapSetup()
     * added 12-May-2022 by Grant O'Reilly
     * ========================================================================= 
      */
    private void MerchantCurrencyMapSetup(){
        getmLogger().debug("keystore: " + this.getKeystore());
        getmLogger().debug("keystorepassword: " + this.getDecryptedkeystorepasswordplatformpac());

        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystore(), this.getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(this.getMerchantCurrencyMapRead(), this.getMerchantCurrencyMap().toJSON().getBytes());

        Gson gson = new Gson();
        this.setMerchantCurrencyMap(gson.fromJson(result.getData(), MerchantCurrencyMap.class));

        String currCode = this.getMerchantCurrencyMap().getCode();
        if (currCode.length() < 3) {
            currCode = "0" + currCode;
            this.getMerchantCurrencyMap().setCode(currCode);
        }        
    }
    
    /**
     * ========================================================================= 
     * setMerchantCurrencyMap 
     * initially added 30-Jul-2021 by Grant O'Reilly
     * =========================================================================  
     * retrieve reference data from the database according to the currency and 
     * merchant account code sent by the client, in this case Sabre
     * @param merchantAccountCode
     * @param currency
     * =========================================================================  
     */
    public void setMerchantCurrencyMap(String merchantAccountCode, String currency) {
        getmLogger().info("Read MerchantCurrencyMap from database using merchantAccountCode and currency = " + merchantAccountCode + " and " + currency, new Throwable().getStackTrace()[0]);

        this.setMerchantCurrencyMap(new MerchantCurrencyMap());
        this.getMerchantCurrencyMap().setMerchantName(merchantAccountCode); //Added due to a mistake made in JSON Adapter by Mya - Needs to be fixed!
        this.getMerchantCurrencyMap().setMerchantAccountCode(merchantAccountCode);
        this.getMerchantCurrencyMap().setCurrency(currency);
        this.getMerchantCurrencyMap().setStationNumber(""); //No stationNumber set to ""
        
        MerchantCurrencyMapSetup();
    }

    /**
     * ========================================================================= 
     * setMerchantCurrencyMap 
     * overload setMerchantCurrencyMap for stationNumber 
     * added 12-May-2022 by Grant O'Reilly
     * =========================================================================  
     * retrieve reference data from the database according to the currency and 
     * merchant account code sent by the client, in this case Sabre
     * @param merchantAccountCode
     * @param currency
     * @param stationNumber  
     * =========================================================================  
     */    
    public void setMerchantCurrencyMap(String merchantAccountCode, String currency, String stationNumber) {
        getmLogger().info("Read MerchantCurrencyMap from database using merchantAccountCode, currency and stationNumber = " + merchantAccountCode + " and " + currency +" and "+stationNumber, new Throwable().getStackTrace()[0]);

        this.setMerchantCurrencyMap(new MerchantCurrencyMap());
        this.getMerchantCurrencyMap().setMerchantName(merchantAccountCode); //Added due to a mistake made in JSON Adapter by Mya - Needs to be fixed!
        this.getMerchantCurrencyMap().setMerchantAccountCode(merchantAccountCode);
        this.getMerchantCurrencyMap().setCurrency(currency);
        this.getMerchantCurrencyMap().setStationNumber(stationNumber);

        
        MerchantCurrencyMapSetup();
    }    
    
    
    /**
     * @return the acsurl
     */
    public String getAcsurl() {
        return acsurl;
    }

    /**
     * @param acsurl the acsurl to set
     */
    public void setAcsurl(String acsurl) {
        this.acsurl = acsurl;
    }

    /**
     * @return the acsParerqWrite
     */
    public String getAcsParerqWrite() {
        return acsParerqWrite;
    }

    /**
     * @param acsParerqWrite the acsParerqWrite to set
     */
    public void setAcsParerqWrite(String acsParerqWrite) {
        this.acsParerqWrite = acsParerqWrite;
    }

    /**
     * @param originaldataWrite the originaldataWrite to set
     */
    public void setOriginaldataWrite(String originaldataWrite) {
        this.originaldataWrite = originaldataWrite;
    }

    /**
     * @param originaldataRead the originaldataRead to set
     */
    public void setOriginaldataRead(String originaldataRead) {
        this.originaldataRead = originaldataRead;
    }

    /**
     * @param authorisationWrite the authorisationWrite to set
     */
    public void setAuthorisationWrite(String authorisationWrite) {
        this.authorisationWrite = authorisationWrite;
    }

    /**
     * @param authorisationRead the authorisationRead to set
     */
    public void setAuthorisationRead(String authorisationRead) {
        this.authorisationRead = authorisationRead;
    }

    /**
     * @param captureWrite the captureWrite to set
     */
    public void setCaptureWrite(String captureWrite) {
        this.captureWrite = captureWrite;
    }

    /**
     * @param captureRead the captureRead to set
     */
    public void setCaptureRead(String captureRead) {
        this.captureRead = captureRead;
    }

    /**
     * @param threedsCreateReq the threedsCreateReq to set
     */
    public void setThreedsCreateReq(String threedsCreateReq) {
        this.threedsCreateReq = threedsCreateReq;
    }

    /**
     * @param threedsValidatePaRes the threedsValidatePaRes to set
     */
    public void setThreedsValidatePaRes(String threedsValidatePaRes) {
        this.threedsValidatePaRes = threedsValidatePaRes;
    }

    /**
     * @return the servletProxy
     */
    public String getServletProxy() {
        return servletProxy;
    }

    /**
     * @param servletProxy the servletProxy to set
     */
    public void setServletProxy(String servletProxy) {
        this.servletProxy = servletProxy;
    }

    /**
     * @param postilionAuthorise the postilionAuthorise to set
     */
    public void setPostilionAuthorise(String postilionAuthorise) {
        this.postilionAuthorise = postilionAuthorise;
    }

    /**
     * @param postilionRefund the postilionRefund to set
     */
    public void setPostilionRefund(String postilionRefund) {
        this.postilionRefund = postilionRefund;
    }

    /**
     * @param postilionCapture the postilionCapture to set
     */
    public void setPostilionCapture(String postilionCapture) {
        this.postilionCapture = postilionCapture;
    }

    /**
     * @param postilionReversal the postilionReversal to set
     */
    public void setPostilionReversal(String postilionReversal) {
        this.postilionReversal = postilionReversal;
    }

    /**
     * @return the acquiringInstitutionCodePostilion
     */
    public String getAcquiringInstitutionCodePostilion() {
        return acquiringInstitutionCodePostilion;
    }

    /**
     * @param acquiringInstitutionCodePostilion the acquiringInstitutionCodePostilion to set
     */
    public void setAcquiringInstitutionCodePostilion(String acquiringInstitutionCodePostilion) {
        this.acquiringInstitutionCodePostilion = acquiringInstitutionCodePostilion;
    }

    /**
     * @return the acquiringInstitutionCodeVisa
     */
    public String getAcquiringInstitutionCodeVisa() {
        return acquiringInstitutionCodeVisa;
    }

    /**
     * @param acquiringInstitutionCodeVisa the acquiringInstitutionCodeVisa to set
     */
    public void setAcquiringInstitutionCodeVisa(String acquiringInstitutionCodeVisa) {
        this.acquiringInstitutionCodeVisa = acquiringInstitutionCodeVisa;
    }

    /**
     * @return the acquiringInstitutionCodeMastercard
     */
    public String getAcquiringInstitutionCodeMastercard() {
        return acquiringInstitutionCodeMastercard;
    }

    /**
     * @param acquiringInstitutionCodeMastercard the acquiringInstitutionCodeMastercard to set
     */
    public void setAcquiringInstitutionCodeMastercard(String acquiringInstitutionCodeMastercard) {
        this.acquiringInstitutionCodeMastercard = acquiringInstitutionCodeMastercard;
    }

    /**
     * @return the posDataCode
     */
    public String getPosDataCode() {
        return posDataCode;
    }

    /**
     * @param posDataCode the posDataCode to set
     */
    public void setPosDataCode(String posDataCode) {
        this.posDataCode = posDataCode;
    }

    /**
     * @return the decryptor
     */
    public AESEncryptionDecryption getDecryptor() {
        return decryptor;
    }

    /**
     * @param decryptor the decryptor to set
     */
    public void setDecryptor(AESEncryptionDecryption decryptor) {
        this.decryptor = decryptor;
    }

    /**
     * @return the decryptedUserName
     */
    public String getDecryptedUserName() {
        return decryptedUserName;
    }

    /**
     * @param decryptedUserName the decryptedUserName to set
     */
    public void setDecryptedUserName(String decryptedUserName) {
        this.decryptedUserName = decryptedUserName;
    }

    /**
     * @return the decryptedPassword
     */
    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    /**
     * @param decryptedPassword the decryptedPassword to set
     */
    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    /**
     * @return the merchantCurrencyMap
     */
    public MerchantCurrencyMap getMerchantCurrencyMap() {
        return merchantCurrencyMap;
    }

    /**
     * @param merchantCurrencyMap the merchantCurrencyMap to set
     */
    public void setMerchantCurrencyMap(MerchantCurrencyMap merchantCurrencyMap) {
        this.merchantCurrencyMap = merchantCurrencyMap;
    }

    /**
     * @return the keystore
     */
    public String getKeystore() {
        return keystore;
    }

    /**
     * @param keystore the keystore to set
     */
    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    /**
     * @return the decryptedkeystorepasswordplatformpac
     */
    public String getDecryptedkeystorepasswordplatformpac() {
        return decryptedkeystorepasswordplatformpac;
    }

    /**
     * @param decryptedkeystorepasswordplatformpac the decryptedkeystorepasswordplatformpac to set
     */
    public void setDecryptedkeystorepasswordplatformpac(String decryptedkeystorepasswordplatformpac) {
        this.decryptedkeystorepasswordplatformpac = decryptedkeystorepasswordplatformpac;
    }

    /**
     * @return the merchantCode
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * @param merchantCode the merchantCode to set
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the merchantType
     */
    public String getMerchantType() {
        return merchantType;
    }

    /**
     * @param merchantType the merchantType to set
     */
    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    /**
     * @return the mLogger
     */
    public LogWrapper getmLogger() {
        return mLogger;
    }

    /**
     * @param mLogger the mLogger to set
     */
    public void setmLogger(LogWrapper mLogger) {
        this.mLogger = mLogger;
    }

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

    /**
     * @return the updateOrder
     */
    public String getUpdateOrder() {
        return updateOrder;
    }

    /**
     * @param updateOrder the updateOrder to set
     */
    public void setUpdateOrder(String updateOrder) {
        this.updateOrder = updateOrder;
    }

    /**
     * @return the threedsV2Versioning
     */
    public String getThreedsV2Versioning() {
        return threedsV2Versioning;
    }

    /**
     * @param threedsV2Versioning the threedsV2Versioning to set
     */
    public void setThreedsV2Versioning(String threedsV2Versioning) {
        this.threedsV2Versioning = threedsV2Versioning;
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
     * @return the threedsV2Authorise
     */
    public String getThreedsV2Authorise() {
        return threedsV2Authorise;
    }

    /**
     * @param threedsV2Authorise the threedsV2Authorise to set
     */
    public void setThreedsV2Authorise(String threedsV2Authorise) {
        this.threedsV2Authorise = threedsV2Authorise;
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

    /**
     * @return the threedsV2EnrolledWrite
     */
    public String getThreedsV2EnrolledWrite() {
        return threedsV2EnrolledWrite;
    }

    /**
     * @param threedsV2EnrolledWrite the threedsV2EnrolledWrite to set
     */
    public void setThreedsV2EnrolledWrite(String threedsV2EnrolledWrite) {
        this.threedsV2EnrolledWrite = threedsV2EnrolledWrite;
    }

    /**
     * @return the threeDSIssuerURL
     */
    public String getThreeDSIssuerURL() {
        return threeDSIssuerURL;
    }

    /**
     * @param threeDSIssuerURL the threeDSIssuerURL to set
     */
    public void setThreeDSIssuerURL(String threeDSIssuerURL) {
        this.threeDSIssuerURL = threeDSIssuerURL;
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
