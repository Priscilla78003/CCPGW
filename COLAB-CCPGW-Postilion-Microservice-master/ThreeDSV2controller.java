/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2022 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.microservice.threeds.controller;

import com.truteq.ccpgw.threeds.v2.objects.authentication.TDSAuthReqObject;
import com.google.gson.Gson;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.threeds.v2.objects.authentication.AcquirerData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.AuthReqData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.BroadInfo;
import com.truteq.ccpgw.threeds.v2.objects.authentication.BrowserData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.CardholderAccount;
import com.truteq.ccpgw.threeds.v2.objects.authentication.CardholderData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.DeviceRenderOptions;
import com.truteq.ccpgw.threeds.v2.objects.authentication.MerchantData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.MerchantRiskIndicator;
import com.truteq.ccpgw.threeds.v2.objects.authentication.PurchaseData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSRequestor;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerAuthenticationRequest;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerAuthenticationResponse;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerTransaction;
import com.truteq.ccpgw.threeds.v2.objects.versioning.Enrolled;
import com.truteq.ccpgw.threeds.v2.objects.versioning.Sdk;
import com.truteq.ccpgw.threeds.v2.objects.versioning.ThreeDSServerVersioningResponse;
import com.truteq.ccpgw.threeds.v2.objects.versioning.ThreeDSV2ServerVersioningRequest;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import com.truteq.datagenerics.GenericDatabaseDAO;
import com.truteq.general.util.AESEncryptionDecryption;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@RestController
@RequestMapping("/transaction/manager/3ds/v2/service/")
public class ThreeDSV2controller {
    
    @Autowired
    private GenericDatabaseDAO databaseDao;

    @Value(value = "${certificate.keystore}")
    private String keystore;
    @Value(value = "${netcetera.keystore.password}")
    private String encryptedkeystorepassword;
    @Value(value = "${spring.encryption.secret}")        
    private String secret;
    @Value(value = "${netcetera.3ds.v2.versioning}")
    private String netceteraVersioning;
    @Value(value = "${netcetera.3ds.v2.authorise}")
    private String netceteraAuthorise;
    
    private final LogWrapper mLogger = new LogWrapper(ThreeDSV2controller.class);

    @PostMapping("versioning")
    @CrossOrigin(origins = "*")
    public ThreeDSServerVersioningResponse Versioning (@RequestBody ThreeDSV2ServerVersioningRequest versionReq) {
        mLogger.info("received 3DS v2.x Versioning Request: " + versionReq.toJSON());

       String keystorepassword = "";
        try {
            AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
            keystorepassword = decryptor.decrypt(encryptedkeystorepassword, secret);         
        } catch (Exception ex) {
            mLogger.error("Exception with decryption credentials." + ex, new Throwable().getStackTrace()[0]);
        }
        
        //SSLCommunicator sslComms = new SSLCommunicator("3dss-preview-tech-user","W7Ldsmy6fTW3qrP",keystore,keystorepassword);
        SSLCommunicator sslComms = new SSLCommunicator(keystore,keystorepassword);
        Result result = sslComms.sendHttpPost(netceteraVersioning, versionReq.toJSON().getBytes());
        
        mLogger.info(result.getComments() + " " + result.getData());

        Gson gson = new Gson();

        ThreeDSServerVersioningResponse versionResponse = gson.fromJson(result.getData(), ThreeDSServerVersioningResponse.class);

        return versionResponse;

    }

    @PostMapping("authentication")
    @CrossOrigin(origins = "*")
    public ThreeDSServerAuthenticationResponse Authentication (@RequestBody ThreeDSServerAuthenticationRequest authReq) {
        mLogger.info("received 3DS v2.x Authentication Request: " + authReq.toJSON());

       String keystorepassword = "";
        try {
            AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
            keystorepassword = decryptor.decrypt(encryptedkeystorepassword, secret);         
        } catch (Exception ex) {
            mLogger.error("Exception with decryption credentials." + ex, new Throwable().getStackTrace()[0]);
        }
        
        SSLCommunicator sslComms = new SSLCommunicator(keystore,keystorepassword);
        Result result = sslComms.sendHttpPost(netceteraAuthorise, authReq.toJSON().getBytes());
        
        mLogger.info(result.getComments() + " " + result.getData());

        Gson gson = new Gson();

        ThreeDSServerAuthenticationResponse authResponse = gson.fromJson(result.getData(), ThreeDSServerAuthenticationResponse.class);

        return authResponse;

    }
    
    
    @PostMapping("transaction/read")
    @CrossOrigin(origins = "*")
    public  ThreeDSServerTransaction Read3DSv2Transaction (@RequestBody ThreeDSServerTransaction tdsTransaction) {
        
        mLogger.info("Read 3DS v2.x transaction: " + tdsTransaction.toJSON());
        
        ThreeDSServerTransaction tdsTransactionDb = (ThreeDSServerTransaction) databaseDao.findByName("ccpgw.transaction.manager.read.tds.transaction", ThreeDSServerTransaction.class, tdsTransaction.getThreeDSServerTransID());
        
        return tdsTransactionDb;

    }     
    
    @PostMapping("transaction/write")
    @CrossOrigin(origins = "*")
    public void Persist3DSv2Transaction(@RequestBody ThreeDSServerTransaction tdsTransaction) {
        mLogger.info("Persisting 3DS v2.x transaction: " + tdsTransaction.toJSON());

        Object parameters[] = {
            tdsTransaction.getThreeDSServerTransID(),
            tdsTransaction.getMd(),
            tdsTransaction.getTermUrl(),
            tdsTransaction.getCreq(),
            tdsTransaction.getCres()
        };

        int parameterTypes[] = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.LONGVARCHAR,
            Types.LONGVARCHAR
        };
        
        databaseDao.insert("ccpgw.transaction.manager.write.tds.transaction", parameters, parameterTypes);   
    }
    
    @PostMapping("transaction/update")
    @CrossOrigin(origins = "*")
    public void Update3DSv2Transaction(@RequestBody ThreeDSServerTransaction tdsTransaction) {
        
        ThreeDSServerTransaction tdsTransactionDb = Read3DSv2Transaction(tdsTransaction);
        
        mLogger.info("Updatinging 3DS v2.x transaction: " + tdsTransaction.toJSON());
        
        mLogger.info("tdsTransactionDb before update: " + tdsTransactionDb.toJSON());
        
        tdsTransactionDb.update(tdsTransaction);
        
         mLogger.info("tdsTransactionDb after update: " + tdsTransactionDb.toJSON());

        Object parameters[] = {
            tdsTransactionDb.getMd(),
            tdsTransactionDb.getTermUrl(),
            tdsTransactionDb.getCreq(),
            tdsTransactionDb.getCres(),
            tdsTransactionDb.getThreeDSServerTransID()
        };

        int parameterTypes[] = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.LONGVARCHAR,
            Types.LONGVARCHAR,
            Types.VARCHAR
        };
        
        databaseDao.insert("ccpgw.transaction.manager.update.tds.transaction", parameters, parameterTypes);   
    }    
    
    
    @PostMapping("authentication/request/read")
    @CrossOrigin(origins = "*")
    public  ThreeDSServerAuthenticationRequest ReadAuthentication (@RequestBody ThreeDSServerAuthenticationRequest authReq) {
        mLogger.info("Read 3DS v2.x Authentication Request: " + authReq.toJSON());

        mLogger.debug("ThreeDSServerTransID = " + authReq.getThreeDSServerTransID());
        
        TDSAuthReqObject authreqObj = (TDSAuthReqObject) databaseDao.findByName("ccpgw.transaction.manager.read.authreq", TDSAuthReqObject.class, authReq.getThreeDSServerTransID());
        
        ThreeDSServerAuthenticationRequest authreq = authreqObj.toThreeDSServerAuthenticationRequest();
        
        mLogger.debug("Authoirisationj Request: " + authReq.toJSON());
        
        return authreq;

    }    
        
    
    
    @PostMapping("authentication/request/write")
    @CrossOrigin(origins = "*")
    public void PersistAuthentication (@RequestBody ThreeDSServerAuthenticationRequest authReq) {
        mLogger.info("Persisting 3DS v2.x Authentication Request: " + authReq.toJSON());
        
        persistRequestor(authReq.getThreeDSServerTransID(),authReq.getThreeDSRequestor()); 
        persistCardHolder(authReq.getThreeDSServerTransID(),authReq.getCardholder());
        persistCardholderAccount(authReq.getThreeDSServerTransID(),authReq.getCardholderAccount()); 
        persistPurchase(authReq.getThreeDSServerTransID(),authReq.getPurchase());
        persistMerchant(authReq.getThreeDSServerTransID(), authReq.getMerchant());
        persistBrowser(authReq.getThreeDSServerTransID(), authReq.getBrowserInformation());
        persistSdk(authReq.getThreeDSServerTransID(), authReq.getSdkInformation());
        persistDeviceRender(authReq.getThreeDSServerTransID(), authReq.getDeviceRenderOptions()); 
        persistBroadInfo(authReq.getThreeDSServerTransID(), authReq.getBroadInfo());
        persistAcquirer(authReq.getThreeDSServerTransID(), authReq.getAcquirer());
          
        AuthReqData authReqData = new AuthReqData(authReq);
        persistAuthReq(authReq.getThreeDSServerTransID(),authReqData);
    }
    
    @PostMapping("enrolled/read")
    @CrossOrigin(origins = "*")
    public   Enrolled ReadEnrolled (@RequestBody Enrolled enrolled) {
        mLogger.info("Read 3DS v2.x Enrolled Request: " + enrolled.toJSON());

        mLogger.debug("ThreeDSServerTransID = " + enrolled.getThreeDSServerTransID());
        
        Enrolled enrolledObj = (Enrolled) databaseDao.findByName("ccpgw.transaction.manager.read.enrolled", Enrolled.class, enrolled.getThreeDSServerTransID());
        
        mLogger.debug("Enrolled Object: " + enrolledObj.toJSON());
        
        return enrolledObj;

    }   
    
    @PostMapping("enrolled/write")
    @CrossOrigin(origins = "*")
    public void PersistEnrolled (@RequestBody Enrolled enrolled) {
        mLogger.info("Persisting 3DS v2.x Enrolled Object: " + enrolled.toJSON());
        
        Object parameters[] = { 
                                enrolled.getThreeDSServerTransID(),
                                enrolled.getEnrolled(),
                                enrolled.getAcctNumber(),
                                enrolled.getSchemeId()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.enrolled", parameters, parameterTypes); 
    }    
    
     /**
     * =========================================================================
     * persistMerchant
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistAuthReq(String index, AuthReqData authReq) {
        mLogger.info("Persisting 3DS v2.x AuthReqData.");
               
        Object parameters[] = { 
                                index,
                                authReq.getPreferredProtocolVersion(),
                                authReq.getEnforcePreferredProtocolVersion(),
                                authReq.getDeviceChannel(),
                                authReq.getMessageCategory(),
                                authReq.getThreeDSCompInd(),
                                authReq.getThreeDSRequestorURL(),
                                authReq.getThreeRIInd(),
                                authReq.getMessageExtension(),
                                authReq.getChallengeMessageExtension()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.authreq", parameters, parameterTypes);    
        
    }     
        
     /**
     * =========================================================================
     * persistCardholderAccount
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistCardholderAccount(String index, CardholderAccount cardholderAccount) {
        mLogger.info("Persisting 3DS v2.x CardholderAccount.");
               
        Object parameters[] = { 
                                index,
                                cardholderAccount.getAcctType(),
                                cardholderAccount.getCardExpiryDate(),
                                cardholderAccount.getAcctNumber(),
                                cardholderAccount.getSchemeId(),
                                cardholderAccount.getAcctID(),
                                cardholderAccount.getPayTokenInd(),
                                cardholderAccount.getAcctInfo().getChAccAgeInd(),
                                cardholderAccount.getAcctInfo().getChAccDate(),
                                cardholderAccount.getAcctInfo().getChAccChangeInd(),
                                cardholderAccount.getAcctInfo().getChAccChange(),
                                cardholderAccount.getAcctInfo().getChAccPwChangeInd(),
                                cardholderAccount.getAcctInfo().getChAccPwChange(),
                                cardholderAccount.getAcctInfo().getShipAddressUsageInd(),
                                cardholderAccount.getAcctInfo().getShipAddressUsage(),
                                cardholderAccount.getAcctInfo().getTxnActivityDay(),
                                cardholderAccount.getAcctInfo().getTxnActivityYear(),
                                cardholderAccount.getAcctInfo().getProvisionAttemptsDay(),
                                cardholderAccount.getAcctInfo().getNbPurchaseAccount(),
                                cardholderAccount.getAcctInfo().getSuspiciousAccActivity(),
                                cardholderAccount.getAcctInfo().getShipNameIndicator(),
                                cardholderAccount.getAcctInfo().getPaymentAccInd(),
                                cardholderAccount.getAcctInfo().getPaymentAccAge()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2cardholderaccount", parameters, parameterTypes);    
        
    }    
    
     /**
     * =========================================================================
     * persistPurchase
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistPurchase(String index,PurchaseData purchase) {
        mLogger.info("Persisting 3DS v2.x Purchase.");
        
        if (purchase.getMerchantRiskIndicator() == null){
            purchase.setMerchantRiskIndicator(new MerchantRiskIndicator("","","","","","",0,"",0));
        }
               
        Object parameters[] = { 
                                index,
                                purchase.getPurchaseInstalData(),
                                purchase.getPurchaseAmount(),
                                purchase.getPurchaseCurrency(),
                                purchase.getPurchaseExponent(),
                                purchase.getPurchaseDate(),
                                purchase.getRecurringExpiry(),
                                purchase.getRecurringFrequency(),
                                purchase.getTransType(),
                                purchase.getMerchantRiskIndicator().getShipIndicator(),
                                purchase.getMerchantRiskIndicator().getDeliveryTimeframe(),
                                purchase.getMerchantRiskIndicator().getDeliveryEmailAddress(),
                                purchase.getMerchantRiskIndicator().getReorderItemsInd(),
                                purchase.getMerchantRiskIndicator().getPreOrderPurchaseInd(),
                                purchase.getMerchantRiskIndicator().getPreOrderDate(),
                                purchase.getMerchantRiskIndicator().getGiftCardAmount(),
                                purchase.getMerchantRiskIndicator().getGiftCardCurr(),
                                purchase.getMerchantRiskIndicator().getGiftCardCount()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.INTEGER,
                                     Types.BIGINT, 
                                     Types.VARCHAR,
                                     Types.INTEGER, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.INTEGER,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.INTEGER,
                                     Types.VARCHAR, 
                                     Types.INTEGER
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2purchase", parameters, parameterTypes);    
        
    }     

     /**
     * =========================================================================
     * persistMerchant
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistMerchant(String index, MerchantData merchant) {
        mLogger.info("Persisting 3DS v2.x Merchant.");
           
        String notificationURL = "";
        if (merchant.getNotificationURL() != null){
           notificationURL = merchant.getNotificationURL().toString();
        } 
        Object parameters[] = { 
                                index,
                                merchant.getMerchantConfigurationId(),
                                merchant.getMcc(),
                                merchant.getMerchantCountryCode(),
                                merchant.getMerchantName(),
                                notificationURL,
                                merchant.getThreeDSRequestorId(),
                                merchant.getThreeDSRequestorName(),
                                merchant.getWhiteListStatus(),
                                merchant.getResultsResponseNotificationUrl()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2merchant", parameters, parameterTypes);    
        
    }     
  
     /**
     * =========================================================================
     * persistBrowser
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistBrowser(String index, BrowserData browser) {
        mLogger.info("Persisting 3DS v2.x Browser.");
               
        Object parameters[] = { 
                                index,
                                browser.getBrowserAcceptHeader(),
                                browser.getBrowserIP(),   
                                browser.getBrowserJavaEnabled(),     
                                browser.getBrowserLanguage(),
                                browser.getBrowserColorDepth(),
                                browser.getBrowserScreenHeight(),
                                browser.getBrowserScreenWidth(),
                                browser.getBrowserTZ(),
                                browser.getBrowserUserAgent(),
                                browser.getChallengeWindowSize(),
                                browser.getBrowserJavaEnabled()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.INTEGER,
                                     Types.INTEGER,
                                     Types.INTEGER,
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2browser", parameters, parameterTypes);    
        
    }     
  
     /**
     * =========================================================================
     * persistSdk
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistSdk(String index, Sdk sdk) {
        mLogger.info("Persisting 3DS v2.x Sdk.");
        
        if (sdk == null){
           Map<String, Object> map = new HashMap<>();
           map.put("kty","EC");
           map.put("crv","P-256");
           map.put("x","test");
           map.put("y","test");
           sdk = new Sdk("","",0,"","",map);
        }
               
        Object parameters[] = { 
                                index,
                                sdk.getSdkAppID(),
                                sdk.getSdkEncData(),
                                sdk.getSdkEphemPubKey(),
                                sdk.getSdkMaxTimeout(),
                                sdk.getSdkReferenceNumber(),
                                sdk.getSdkTransID()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.INTEGER,
                                     Types.VARCHAR, 
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2sdk", parameters, parameterTypes);    
        
    }
    
     /**
     * =========================================================================
     * persistDeviceRender
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistDeviceRender(String index, DeviceRenderOptions deviceRender) {
        mLogger.info("Persisting 3DS v2.x DeviceRenderOptions.");
        
        if (deviceRender == null){
            List<String> list = new ArrayList<>();
            list.add("02");
            list.add("03");
            deviceRender = new DeviceRenderOptions("",list);
        }
               
        StringBuilder sdkUiType = new StringBuilder();
        List<String> list = deviceRender.getSdkUiType();
        for (int i = 0; i< list.size(); i++){
            sdkUiType.append(list.get(i));
            if (i<list.size()-1)
                sdkUiType.append(",");     
        }
        
        Object parameters[] = { 
                                index,
                                deviceRender.getSdkInterface(),
                                sdkUiType.toString()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2devicerender", parameters, parameterTypes);    
        
    }

     /**
     * =========================================================================
     * persistBroadInfo
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistBroadInfo(String index, BroadInfo broadInfo) {
        mLogger.info("Persisting 3DS v2.x BroadInfo.");
               
        Object parameters[] = { 
                                index,
                                broadInfo.getMessage()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2broadionfo", parameters, parameterTypes);    
    }   
    
        
      /**
     * =========================================================================
     * persistAcquirer
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistAcquirer(String index, AcquirerData acquirer) {
        mLogger.info("Persisting 3DS v2.x AcquirerData.");
               
        Object parameters[] = { 
                                index,
                                acquirer.getAcquirerBin(),
                                acquirer.getAcquirerMerchantId()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2acquirer", parameters, parameterTypes);    
        
    }   

     /**
     * =========================================================================
     * persistCardHolder
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistCardHolder(String index, CardholderData cardholder) {
        mLogger.info("Persisting 3DS v2.x CardholderData.");
               
        Object parameters[] = { 
                                index,
                                cardholder.getAddrMatch(),
                                cardholder.getBillAddrCity(),
                                cardholder.getBillAddrCountry(),
                                cardholder.getBillAddrLine1(),
                                cardholder.getBillAddrLine2(),
                                cardholder.getBillAddrLine3(),
                                cardholder.getBillAddrPostCode(),
                                cardholder.getBillAddrState(),
                                cardholder.getEmail(),
                                cardholder.getHomePhone().getCc(),
                                cardholder.getHomePhone().getSubscriber(),
                                cardholder.getMobilePhone().getCc(),
                                cardholder.getMobilePhone().getSubscriber(),
                                cardholder.getWorkPhone().getCc(),
                                cardholder.getWorkPhone().getSubscriber(),
                                cardholder.getCardholderName(),
                                cardholder.getShipAddrCity(),
                                cardholder.getShipAddrCountry(),
                                cardholder.getShipAddrLine1(),
                                cardholder.getShipAddrLine2(),
                                cardholder.getShipAddrLine3(),
                                cardholder.getShipAddrPostCode(),
                                cardholder.getShipAddrState(),
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2cardholder", parameters, parameterTypes);    
        
    }  
    
    /**
     * =========================================================================
     * persistRequestor
     * =========================================================================
     * @param index
     * @param requestor 
     * =========================================================================
     */
    private void persistRequestor(String index, ThreeDSRequestor requestor){
        mLogger.info("Persisting 3DS v2.x ThreeDSRequestor.");
               
        Object parameters[] = { 
                                index,
                                requestor.getThreeDSRequestorAuthenticationInd(),
                                requestor.getThreeDSRequestorAuthenticationInfo().getThreeDSReqAuthMethod(),
                                requestor.getThreeDSRequestorAuthenticationInfo().getThreeDSReqAuthTimestamp(),
                                requestor.getThreeDSRequestorAuthenticationInfo().getThreeDSReqAuthData(),
                                requestor.getThreeDSRequestorChallengeInd(),
                                
                                requestor.getThreeDSRequestorPriorAuthenticationInfo().getThreeDSReqPriorRef(),
                                requestor.getThreeDSRequestorPriorAuthenticationInfo().getThreeDSReqPriorAuthMethod(),
                                requestor.getThreeDSRequestorPriorAuthenticationInfo().getThreeDSReqPriorAuthTimestamp(),
                                requestor.getThreeDSRequestorPriorAuthenticationInfo().getThreeDSReqPriorAuthData(),
                                
                                requestor.getThreeDSRequestorDecReqInd(),
                                requestor.getThreeDSRequestorDecMaxTime()
        };
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.INTEGER
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.tds2requestor", parameters, parameterTypes);    
        
    }      
}
