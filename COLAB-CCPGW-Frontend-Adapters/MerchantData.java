/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.frontend.adapter.json.database.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.payment.gateway.api.json.Merchant;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import com.truteq.ccpgw.transaction.manager.comms.Communicator;
import com.truteq.ccpgw.transaction.manager.merchant.portal.authenticate.authorise.EncryptionOnly;
import com.truteq.ccpgw.transaction.manager.merchant.portal.authenticate.authorise.EncryptionSession;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Users;
import com.truteq.general.util.AESEncryptionDecryption;
import com.truteq.ipgw.model.authenticate.authorise.SessionObj;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/ccpgwjsonadapter/")
public class MerchantData {

    private LogWrapper mLogger = new LogWrapper(MerchantData.class);

    @Value("${transaction.manager.certificate}")
    private String keystore;

    @Value("${transaction.manager.platformpac.keystore.password}")
    private String keystorepasswordplatformpac;

    @Value("${communicator.secret}")
    private String secret;

    @Value("${transaction.manager.db.read.merchant}")
    private String merchantRead;
    
    @Value("${transaction.manager.db.create.merchant.reactjs}")
    private String merchantCreateReactJS;
    
    @Value("${transaction.manager.db.merchant.user.auth}")
    private String merchantUserAuth;

    @Value("${transaction.manager.db.merchant.user.read.session}")
    private String merchantUserReadSession; 
     
    @Value("${transaction.manager.db.merchant.user.close.session}")
    private String merchantUserCloseSession;
    
    @Value("${transaction.manager.db.merchant.user.bank.account.list}")
    private String merchantUserBankAccountList;    
    
    @Value("${transaction.manager.db.merchant.user.profile}")
    private String merchantUserProfile;
    
    @Value("${transaction.manager.db.merchant.user.imagedata.read}")
    private String merchantReadImageData;
            
    @Value("${transaction.manager.db.merchant.user.imagedata.update}")
    private String merchantUpdateImageData;  

    @Value("${transaction.manager.db.merchant.user.profile.update}")
    private String merchantUserUpdateProfile;
    
    @Value("${transaction.manager.db.merchant.user.secretkey}")
    private String merchantSecretKey;
    
    @Value("${transaction.manager.db.merchant.user.order.list}")
    private String merchantOrderList;
    
    private AESEncryptionDecryption decryptor;
    private String decryptedkeystorepasswordplatformpac;

   private String toJSON(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }     
    
    
    @PostMapping("merchant/read")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_READ_MERCHANT')")
    public Merchant getMerchant(@RequestBody Merchant merchant) throws Exception {

        getmLogger().info("Retrieving merchant : secret key = " + merchant.getSecretkey(), new Throwable().getStackTrace()[0]);

        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        SSLCommunicator sslComms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(getMerchantRead(), merchant.toJSON().getBytes());
        getmLogger().info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        Gson gson = new Gson();
        getmLogger().debug("response: " + result.getData());
        return gson.fromJson(result.getData(), Merchant.class);
    }

    @PostMapping("merchant/createfrom/reactjs")
    @CrossOrigin(origins = "*")
    public Merchant createMerchantReactJS(@RequestBody EncryptionOnly encryptionObj) throws Exception {
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantCreateReactJS, encryptionObj.toJSON().getBytes());

        Gson gson = new Gson();
        getmLogger().debug("response: " + result.getData());
        return gson.fromJson(result.getData(), Merchant.class);
    }

    
    @PostMapping("merchant/user/auth")
    @CrossOrigin(origins = "*")
    public Result getMerchantUserAuth(@RequestBody Users user) throws Exception {
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantUserAuth, user.toJSON().getBytes());

        Gson gson = new Gson();
        getmLogger().debug("response: " + result.getData());
        return gson.fromJson(result.getData(), Result.class);
    }
   
    
    /**
     * =============================================================================
     * readSession
     * @param sessionObjIn
     * @return 
     * =============================================================================
     */
    @PostMapping("merchant/user/session/read")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_USER_SESSION_READ')")
    public Result readSession(@RequestBody SessionObj sessionObjIn){
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantUserReadSession, sessionObjIn.toJSON().getBytes());

        Gson gson = new Gson();
        getmLogger().debug("response: " + result.getData());
        return gson.fromJson(result.getData(), Result.class);        
       
    }
    /**
     * =============================================================================
     * closeSession
     * @param sessionObjIn
     * @return
     * =============================================================================
     */
    @PostMapping("merchant/user/session/close")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_USER_SESSION_CLOSE')")
    public Result closeSession(@RequestBody SessionObj sessionObjIn) {
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantUserCloseSession, sessionObjIn.toJSON().getBytes());

        Gson gson = new Gson();
        getmLogger().debug("response: " + result.getData());
        return gson.fromJson(result.getData(), Result.class);          
        
    }              
    /**
     * =========================================================================
     * ListBankAccountsForReactJS
     * 
     * @param user
     * @return 
     * =========================================================================
     */
    @PostMapping("merchant/user/bank/account/list/reactjs")
    @CrossOrigin(origins = "*")
    public String ListBankAccountsForReactJS(@RequestBody Users user) {
        
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantUserBankAccountList, user.toJSON().getBytes());

        Gson gson = new Gson();
        getmLogger().debug("response: " + result.getData());        

        return result.getData();
    }   

    /**=========================================================================
     * getMerchantProfile
     * @param user
     * @return 
     * =========================================================================
     */    
    @PostMapping("merchant/user/read/profile")
    @CrossOrigin(origins = "*")  
    public String getMerchantUserProfile(@RequestBody Users user){
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantUserProfile, user.toJSON().getBytes());
 

        Gson gson = new Gson();
        getmLogger().debug("response: " + result.getData());        

        return result.getData();
        
    }
    
    @PostMapping("merchant/user/profile/update")
    @CrossOrigin(origins = "*")  
    //public ResponseEntity updateMerchantProfile(@RequestBody ReactJSProfileObj profile){
    public String updateMerchantProfile(@RequestBody EncryptionSession encryptionObj){ 
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantUserUpdateProfile, encryptionObj.toJSON().getBytes());
 

        Gson gson = new Gson();
        getmLogger().debug("response: " + result.getData());        

        return result.getData();
        
        
    }

    /**=========================================================================
     * getMerchantImageData
     * @param user
     * @return 
     * =========================================================================
     */  
    @PostMapping("merchant/user/read/imagedata")
    @CrossOrigin(origins = "*")  
    public String getMerchantImageData(@RequestBody Users user){
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantReadImageData, user.toJSON().getBytes());

        getmLogger().debug("response: " + result.getData());        

        return result.getData();        
        
    }

    /**=========================================================================
     * updateMerchantImageData
     * @param encryptionObj
     * @return 
     * =========================================================================
     */     
    @PostMapping("merchant/user/update/imagedata")
    @CrossOrigin(origins = "*")  
    public String updateMerchantImageData(@RequestBody EncryptionSession encryptionObj){ 
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantUpdateImageData, encryptionObj.toJSON().getBytes());

        getmLogger().debug("response: " + result.getData());        

        return result.getData(); 
    }
    
    /**=========================================================================
    * getMerchantSecretKey
    * @param user
    * @return 
    * =========================================================================
    */    
    @PostMapping("merchant/user/read/secretkey")
    @CrossOrigin(origins = "*")  
    public String getMerchantSecretKey(@RequestBody Users user){

        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantSecretKey, user.toJSON().getBytes());

        getmLogger().debug("response: " + result.getData());        

        return result.getData(); 
    }
    
    /**=========================================================================
    * getOrderList
    * @param user
    * @return 
    * =========================================================================
    */      
    @PostMapping("merchant/user/order/list")
    @CrossOrigin(origins = "*")
    public String getOrderList(@RequestBody Users user) {
        
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        //SSLCommunicator comms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        
        Communicator comms = new Communicator();
        Result result = comms.sendHttpPost(merchantOrderList, user.toJSON().getBytes());

        getmLogger().debug("response: " + result.getData());        

        return result.getData();         

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
     * @return the keystorepasswordplatformpac
     */
    public String getKeystorepasswordplatformpac() {
        return keystorepasswordplatformpac;
    }

    /**
     * @param keystorepasswordplatformpac the keystorepasswordplatformpac to set
     */
    public void setKeystorepasswordplatformpac(String keystorepasswordplatformpac) {
        this.keystorepasswordplatformpac = keystorepasswordplatformpac;
    }

    /**
     * @return the secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret the secret to set
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @return the merchantRead
     */
    public String getMerchantRead() {
        return merchantRead;
    }

    /**
     * @param merchantRead the merchantRead to set
     */
    public void setMerchantRead(String merchantRead) {
        this.merchantRead = merchantRead;
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
     * @return the decryptedkeystorepasswordplatformpac
     */
    public String getDecryptedkeystorepasswordplatformpac() {
        return decryptedkeystorepasswordplatformpac;
    }

    /**
     * @param decryptedkeystorepasswordplatformpac the
     * decryptedkeystorepasswordplatformpac to set
     */
    public void setDecryptedkeystorepasswordplatformpac(String decryptedkeystorepasswordplatformpac) {
        this.decryptedkeystorepasswordplatformpac = decryptedkeystorepasswordplatformpac;
    }

}
