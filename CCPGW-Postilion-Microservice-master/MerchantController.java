/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  10-Feb-2022 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.microservice.database.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.transaction.manager.merchant.portal.authenticate.authorise.EncryptionOnly;
import com.truteq.ccpgw.transaction.manager.merchant.portal.authenticate.authorise.EncryptionSession;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Customer;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.ImageData;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Order;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Profile;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.ReactJSMerchantObj;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.ReactJSProfileObj;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Result;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Users;
import com.truteq.ccpgw.transaction.manager.model.Merchant;
import com.truteq.crypto.CryptoUtil;
import com.truteq.datagenerics.GenericDatabaseDAO;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
@PropertySource("classpath:datamanager.properties")
@RequestMapping("/transaction/manager/database/")
public class MerchantController {

    private final LogWrapper mLogger = new LogWrapper(MerchantController.class);
    
    @Autowired
    private GenericDatabaseDAO databaseDao;
    
    @Value(value = "${keycloak.admin.server.url}")
    private String serverUrl;
    @Value(value = "${keycloak.admin.realm}")
    private String realm;
    @Value(value = "${keycloak.admin.client-id}")
    private String clientId;
    @Value(value = "${keycloak.admin.client-secret}")
    private String clientSecret;        

    @PostMapping("merchant/read")
    @CrossOrigin(origins = "*")
    public String getMerchant(@RequestBody Merchant merchantin) throws Exception {

        mLogger.debug("merchantin.getSecretkey() = " + merchantin.getSecretkey());
        
        Merchant merchant = (Merchant) databaseDao.findByName("ccpgw.transaction.manager.read.merchant", Merchant.class, merchantin.getSecretkey());
        
        mLogger.debug("merchant/read MerchantController toJSON(merchant) = " + toJSON(merchant));
        return toJSON(merchant);
    }

    @PostMapping("merchant/rsaprivatekey/read")
    @CrossOrigin(origins = "*")
    public String getMerchantPrivateKey(@RequestBody Merchant merchantin) throws Exception {
        Merchant merchant = (Merchant) databaseDao.findByName("ccpgw.transaction.manager.read.merchant.privatekey", Merchant.class, merchantin.getGuid());

        return toJSON(merchant);
    }

    private String toJSON(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }
    
        /**
     * =========================================================================
     * @param key
     * @param guid
     * @param encryptedValue
     * @return 
     * =========================================================================
     */
    private String decryptPayload(String key, String guid, String encryptedValue) {

        CryptoUtil cryptoUtil = new CryptoUtil();
        
        String RsaPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALktsEvZmUBcMs2fxvifrBin1m5EAiuTvTGNYXTlTciUD1hZeHkpilVKHx/GmRnZOFgtzbLnffikSn/Yo/eeiZxQ+0LvTokqwYNI8j2ryp1eYjze7afQDhDdig8EfjnjAOz9NU75EY8fWZ//hOBpOoIHFVLZJaE07OzeVOl2BlNHAgMBAAECgYBK5aLXmuQ0NtZJeqVUa+iEdGXzZUhnbbLo9uEDEbe7N79ZIZ7ripSr2HXsOTw1SzlR2PzIrn6x8Wk8elgfUB0hzx/EBo9aPVNHpsk8xV/qB1L5meq+aBspYtANeYuAhwbAu33WWmeKeA7mIEIq+Ol8oRemzQ4QRpUQYIffs9uSuQJBAOAjkge7wbAjt8cOka3jzCQiIkzJKPkcEYGxCjjhNq9wWqhdHfRn6bvWQXptruIAcrqHzjfaIla+CJu2U/am4CUCQQDTgFgd9mvC/yzPMRB0zVr5jXa1I6H+3YIsneGOztE1GP2gpK2aEf2MmolcSI/iaVvLB2uSIigp0eqINhp1naP7AkBBTxwF1NejE0VBYyVfnbil7qw/431k2KDhQUzRNg5RIFPKKxfHV1/rs4pwufTzNV44q8mSzKSk0cqAVKgyfn1hAkBNBuUQpHpCniR3oOrZXyDO2hJtjfillY6fKTDIjdHrgJp+Mvt2rS6mUnHMRjEAyFZB7wXiRsZR1L2RtgKU60CZAkAm4C3GKIQ7qTczOq+jnFtgzBHqEb9KcJ2LJ8eOztmmvbNE2PfTffoufw4pKkktfER7I/N/YzsLmmAHN6FXBFea";

        String encAesKeyBase64 = key;
        //decode from Base64 format
        byte[] encAesKeyBytes = Base64.getDecoder().decode(encAesKeyBase64);
        //decrypt AES key with private RSA key
        byte[] decryptedAesKeyHex = null;
        try {
            decryptedAesKeyHex = cryptoUtil.decryptWithPrivateRsaKey(encAesKeyBytes, RsaPrivateKey);
        } catch (Exception ex) {
            mLogger.error("Error decrypting AES key with private RSA key, Exception = " + ex);
        }

        byte[] decryptedAesKey = HexUtils.fromHexString(new String(decryptedAesKeyHex));

        //initialization vector - 1st 16 chars of userId
        byte[] iv = guid.substring(0, 16).getBytes();

        byte[] encTransBytes = Base64.getDecoder().decode(encryptedValue);

        //decrypt transaction payload with AES key
        byte[] decrypted = null;
        try {
            decrypted = cryptoUtil.decryptWithAes(encTransBytes, decryptedAesKey, iv);
            mLogger.info("Decrypted transaction: " + new String(decrypted));
        } catch (Exception ex) {
            mLogger.error("Error decrypting AES transaction payload with AES key, Exception = " + ex);
        }
        
        return new String(decrypted);
        
    }
    /**
     * =========================================================================
     * decryptPayloadToMerchant
     * @param key
     * @param guid
     * @param encryptedValue
     * @return 
     * =========================================================================
     */
    private ReactJSMerchantObj decryptPayloadToMerchant(String key, String guid, String encryptedValue){
        
        String transactionData = decryptPayload(key,guid,encryptedValue);
        Gson gson = new Gson();
        ReactJSMerchantObj transactionObj = gson.fromJson(transactionData, ReactJSMerchantObj.class);
        return transactionObj;        
    }

    /**
     * =========================================================================
     * decryptPayloadToProfile
     * @param key
     * @param guid
     * @param encryptedValue
     * @return
     * =========================================================================
     */
    private ReactJSProfileObj decryptPayloadToProfile(String key, String guid, String encryptedValue){
        
        String transactionData = decryptPayload(key,guid,encryptedValue);
        Gson gson = new Gson();
        ReactJSProfileObj transactionObj = gson.fromJson(transactionData, ReactJSProfileObj.class);
        return transactionObj;       
    }
    /**
     * =========================================================================
     * decryptPayloadToImageData
     * @param key
     * @param guid
     * @param encryptedValue
     * @return 
     * =========================================================================
     */
    private ImageData decryptPayloadToImageData(String key, String guid, String encryptedValue){
        String transactionData = decryptPayload(key,guid,encryptedValue);
        Gson gson = new Gson();
        ImageData imageData = gson.fromJson(transactionData, ImageData.class);
        return imageData ;           
    }    
    
    
    /**=========================================================================
     * @param merchant
     * @return
     * =========================================================================
    */
    @PostMapping(path = "merchant/create", consumes = "application/json")
    @CrossOrigin(origins = "*")
    public ResponseEntity CreateMerchant(@RequestBody com.truteq.ccpgw.transaction.manager.merchant.portal.model.Merchant merchant) {
        
        ObjectManagerUtil objManager = new ObjectManagerUtil(databaseDao);
        
        objManager.setServerUrl(this.serverUrl);
        objManager.setRealm(this.realm);
        objManager.setClientId(this.clientId);
        objManager.setClientSecret(this.clientSecret);
        
        
        HttpHeaders headers = new HttpHeaders();
        
        com.truteq.ccpgw.transaction.manager.merchant.portal.model.Merchant db_merchant = objManager.createMerchant(merchant);
        
        return ResponseEntity.ok().headers(headers).body(new Result(200, "Successful!", "Created merchant: "+ db_merchant.getBusinessName()));
    } 
    /**=========================================================================
     * ReactJSCreateMerchant
     * @param encryptionObj
     * @return 
     * =========================================================================
     */
    @PostMapping(path = "merchant/createfrom/reactjs", consumes = "application/json")
    @CrossOrigin(origins = "*")
    public ResponseEntity ReactJSCreateMerchant(@RequestBody EncryptionOnly encryptionObj) {
        
        ReactJSMerchantObj reactjsMerchant = decryptPayloadToMerchant(encryptionObj.getKey(),encryptionObj.getGuid(), encryptionObj.getEncryption());
        
        return CreateMerchant(new com.truteq.ccpgw.transaction.manager.merchant.portal.model.Merchant(reactjsMerchant));
    }
    
    /**=========================================================================
     * getMerchantProfile
     * @param user
     * @return 
     * =========================================================================
     */    
    @PostMapping("merchant/read/profile")
    @CrossOrigin(origins = "*")  
    public ResponseEntity getMerchantProfile(@RequestBody Users user){

        Profile profile =  (Profile) databaseDao.findByName("ipgw.api.list.merchant.profile", Profile.class, user.getUsername());   

        HttpHeaders headers = new HttpHeaders();
        
        return ResponseEntity.ok().headers(headers).body(toJSON(profile));
    }

    /**=========================================================================
     * getMerchantImageData
     * @param u
     * @return 
     * =========================================================================
     */  
    @PostMapping("merchant/read/imagedata")
    @CrossOrigin(origins = "*")  
    public ResponseEntity getMerchantImageData(@RequestBody Users u){

        ImageData imgData =  (ImageData)databaseDao.selectImageData("ipgw.api.merchant.imagedata", ImageData.class, u.getUsername());   

        HttpHeaders headers = new HttpHeaders();
        
        return ResponseEntity.ok().headers(headers).body(toJSON(imgData));
    }
 
    
    /**=========================================================================
     * updateMerchantImageData
     * @param encryptionObj
     * @return 
     * =========================================================================
     */     
    @PostMapping("merchant/update/imagedata")
    @CrossOrigin(origins = "*")  
    //public ResponseEntity updateMerchantImageData(@RequestBody ImageData imgData){
    public ResponseEntity updateMerchantImageData(@RequestBody EncryptionSession encryptionObj){
        
        ImageData imgData = decryptPayloadToImageData(encryptionObj.getKey(),encryptionObj.getGuid(), encryptionObj.getEncryption());
        
        Object merchantParameters[] = { 
                                          
                                          imgData.getBusinessName(),
                                          imgData.getBusinessNumber(),
                                          imgData.getId()
        };
        int merchantParameterTypes[] = {
                                      
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.INTEGER
        };         

        databaseDao.insert("ipgw.api.merchant.update", merchantParameters, merchantParameterTypes);          
        
        Object imageDataParameters[] = { 
                                          
                                          imgData.getDescription(),
                                          imgData.getLogo(),
                                          imgData.getId()
        };
        int imageDataParameterTypes[] = {
                                     
                                     Types.VARCHAR,
                                     Types.CLOB,
                                     Types.INTEGER
        };         

        databaseDao.insert("ipgw.api.merchant.update.imagedata", imageDataParameters, imageDataParameterTypes);  

        HttpHeaders headers = new HttpHeaders();
        
        Result result = new Result(200, "Successful!", "Merchant "+imgData.getBusinessName()+" updated.");
        
        return ResponseEntity.ok().headers(headers).body(result.toJSON());  
    }   
    
    
    /**=========================================================================
     * updateMerchantProfile
     * @param encryptionObj
     * @return 
     * =========================================================================
     */  
    @PostMapping("merchant/profile/update")
    @CrossOrigin(origins = "*")  
    //public ResponseEntity updateMerchantProfile(@RequestBody ReactJSProfileObj profile){
    public ResponseEntity updateMerchantProfile(@RequestBody EncryptionSession encryptionObj){ 
        
        ReactJSProfileObj profile = decryptPayloadToProfile(encryptionObj.getKey(),encryptionObj.getGuid(), encryptionObj.getEncryption());
        
        
        Customer cust = (Customer) databaseDao.findByName("ipgw.api.read.customer.from.user", Customer.class, profile.getUsername());
        
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
        
        String dob = sformat.format(profile.getDateofbirth());
        
        Object customerParameters[] = { 
                                          profile.getName(),
                                          profile.getLast_name(),
                                          profile.getEmail(),
                                          dob,
                                          cust.getId()
        };
        int customerParameterTypes[] = {
                                      
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.INTEGER
        };    
        
       databaseDao.insert("ipgw.api.merchant.profile.customer.update", customerParameters, customerParameterTypes); 
       
       Object totpParameters[] = { profile.getSecretkey(),
                                   profile.getTotp(),
                                   profile.getMarketcomms(),
                                   profile.getUsername()};
       
       int totpParameterTypes[] = { 
                                     Types.VARCHAR, 
                                     Types.BOOLEAN,
                                     Types.BOOLEAN,
                                     Types.VARCHAR};
       
       databaseDao.insert("ipgw.api.merchant.update.user.totp",totpParameters,totpParameterTypes);
       
       
        Object addressParameters[] = {
                                          profile.getStreetNumber(),
                                          profile.getStreetNameline1(),
                                          profile.getStreetNameline2(),
                                          profile.getState(),
                                          profile.getCity(),
                                          profile.getCountry(),
                                          cust.getId()
        };
        int addressParameterTypes[] = {
                                     Types.VARCHAR,  
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.INTEGER
        };         
       
       databaseDao.insert("ipgw.api.merchant.profile.address.update", addressParameters, addressParameterTypes); 
       
       HttpHeaders headers = new HttpHeaders();
        
        Result result = new Result(200, "Successful!", "Merchant updated.");
        
        return ResponseEntity.ok().headers(headers).body(result.toJSON());         
    }
    
    /**=========================================================================
    * getMerchantSecretKey
    * @param user
    * @return 
    * =========================================================================
    */    
    @PostMapping("merchant/read/secretkey")
    @CrossOrigin(origins = "*")  
    public ResponseEntity getMerchantSecretKey(@RequestBody Users user){

        Merchant merchant =  (Merchant)databaseDao.findByName("ipgw.api.read.merchant.secretkey", Merchant.class, user.getUsername());   

        HttpHeaders headers = new HttpHeaders();
        
        return ResponseEntity.ok().headers(headers).body(new Result(200, "Successful!", merchant.getSecretkey()));
    }
    
    /**=========================================================================
    * getOrderList
    * @param user
    * @return 
    * =========================================================================
    */      
    @PostMapping("merchant/order/list")
    @CrossOrigin(origins = "*")
    public ResponseEntity getOrderList(@RequestBody Users user) {

        Object orderParameters[] = { user.getUsername() };

        int orderParameterTypes[] = {Types.VARCHAR};

        List<Order> listOfOrders = databaseDao.selectBy("ipgw.api.list.orders", Order.class,  orderParameters,  orderParameterTypes);

        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.ok().headers(headers).body(toJSON(listOfOrders));

    } 
    
    /**=========================================================================
    * getMerchantDetails
    * @param user
    * @return 
    * ==========================================================================
    * This method was developed by Amy Rombuk
    * 2022-08-03
    */      
    @PostMapping("merchant/details")
    @CrossOrigin(origins = "*")
    public ResponseEntity getMerchantDetails(@RequestBody Users user) {

        Merchant merchDetails =  (Merchant) databaseDao.findByName("ccpgw.transaction.manager.read.merchant.by.username", Merchant.class, user.getUsername());   

        HttpHeaders headers = new HttpHeaders();
        
        return ResponseEntity.ok().headers(headers).body(toJSON(merchDetails));

    }    
}
