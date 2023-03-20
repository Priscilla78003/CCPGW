/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * V01.00.01  06-Apr-2021 Mya Htike Oo
 * ***************************************************************
 */
package com.truteq.ccpgw.model.objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truteq.ccpgw.crypto.CryptoUtil;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Merchant {

    private long id;
    private String guid;

    private String businessname;
    private String businessnumber;
    private String rsapublickey;
    private String rsaprivatekey;
    private String secretkey;
    private long customer_id;

    private String cardacceptorname;
    private String phone_number;
    private boolean non3D;
    private String currency;
    private boolean refund;
    private String notifEmail;
    private String notifURL;
    private String failURL;
    private String cancelURL;
    private String successURL;
    private String integrate;

//    private MerchantDocuments documents;
//    
//    private Customer customer;     
    private static String current_date() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());

    }

    private void generateSecretKey() {
        //This generates the secret key for the use with Woocommerce

        String today = current_date();
        String base = this.getGuid() + today;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            this.setSecretkey(hexString.toString());

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

    }

    private void generateGUID() {
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        this.setGuid(uuid.toString());
    }

    private void generateRSAKeyPair() {
        CryptoUtil cryptoUtil = new CryptoUtil();
        KeyPair keyPair = null;
        try {
            keyPair = cryptoUtil.generateRsaKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Merchant.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (keyPair != null) {
            byte[] publicKey = keyPair.getPublic().getEncoded();
            byte[] privateKey = keyPair.getPrivate().getEncoded();

            //encoding keys to Base64 text format so that we can send public key via REST API
            this.setRsapublickey(new String(Base64.getEncoder().encode(publicKey)));
            this.setRsaprivatekey(new String(Base64.getEncoder().encode(privateKey)));
        }
    }

    public String toJSON() {
        return this.toJSON("yyyy-MM-dd HH:mm:ss");
    }

    public String toJSON(String format) {
        Gson gson = new GsonBuilder().setDateFormat(format).create();
        return gson.toJson(this);
    }

//    private long id;
//    private String guid;
//    
//    private String businessName;
//    private String businessNumber;
//    private String rsaPublicKey;
//    private String rsaPrivateKey;
//    private String secretKey;
//    private long customer_id;   
//    
//    private String cardAcceptorName;
//    private String businessPhoneNumber;
//    private boolean non3D;
//    private String currency;
//    private boolean refund;
//    private String notifEmail;
//    private String notifURL;
//    private String failURL;
//    private String cancelURL;
//    private String successURL;
//    private String integrate;  
//    
//    private MerchantDocuments documents;
//    
//    private Customer customer;     
//    
//    public Merchant(){ 
//    }
//    
//    public Merchant(ReactJSMerchantObj reactjsMerchant){
//        Customer cust = new Customer();
//        cust.setName(reactjsMerchant.getFirstName());
//        cust.setLastName(reactjsMerchant.getLastName());
//        cust.setEmail(reactjsMerchant.getEmail());
//        cust.setPhoneNumber(reactjsMerchant.getPhoneNumber());
//        cust.setSalutation(reactjsMerchant.getSalutation());
//        try {
//            String pattern = "yyyy-MM-dd";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);    
//            cust.setDateofbirth(simpleDateFormat.parse(reactjsMerchant.getDateofbirth()));
//        } catch (ParseException ex) {
//            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        cust.setBalance(0);
//        cust.setStatus("active");
//      
//        boolean  marketcomms = reactjsMerchant.getMarketcomms().equals("true") ? true : false;
//        boolean  totp = reactjsMerchant.getTotp().equals("true") ? true : false;
// 
//        cust.setUser(new Users(reactjsMerchant.getEmail(),
//                               reactjsMerchant.getPassword(),
//                               reactjsMerchant.getSecretkey(),
//                               reactjsMerchant.getRole(),
//                               totp,
//                               marketcomms
//                              ));
//        
//        cust.setAddress(new Address(reactjsMerchant.getAddress1(),
//                                   reactjsMerchant.getAddress2(),
//                                   "",
//                                   Integer.parseInt(reactjsMerchant.getPostalcode()),
//                                   reactjsMerchant.getState(),
//                                   reactjsMerchant.getCity(),
//                                   reactjsMerchant.getCountry()));
//        
//        this.businessName = reactjsMerchant.getBusinessName();
//        this.businessNumber = reactjsMerchant.getBusinessNumber();
//        this.customer = cust;
//        
//        this.cardAcceptorName = reactjsMerchant.getCardAcceptorName();
//        this.businessPhoneNumber = reactjsMerchant.getBusinessPhoneNumber();
//        this.non3D = reactjsMerchant.getNon3D().equals("true") ? true : false;
//        this.refund = reactjsMerchant.getRefund().equals("true") ? true : false;
//        this.notifEmail = reactjsMerchant.getNotifEmail();
//        this.notifURL = reactjsMerchant.getNotifURL();
//        this.failURL = reactjsMerchant.getFailURL();
//        this.cancelURL = reactjsMerchant.getCancelURL();
//        this.successURL = reactjsMerchant.getSuccessURL();
//        this.integrate = reactjsMerchant.getIntegrate();
//        this.currency = reactjsMerchant.getCurrency();
//        
//        MerchantDocuments mDocs = new MerchantDocuments();
//        mDocs.setDocument1(reactjsMerchant.getDocument1());
//        mDocs.setDocument2(reactjsMerchant.getDocument2());
//        mDocs.setDocument3(reactjsMerchant.getDocument3());
//        mDocs.setDocument1_1(reactjsMerchant.getDocument1_1());
//        mDocs.setDocument2_1(reactjsMerchant.getDocument2_1());
//        mDocs.setDocument3_1(reactjsMerchant.getDocument3_1());
//        mDocs.setDocument3_2(reactjsMerchant.getDocument3_2()); 
//        mDocs.setDocument4(reactjsMerchant.getDocument4());     
//        mDocs.setDocument_signature(reactjsMerchant.getDocument_signature()); // Mya 06-Apr-2021 document_signature value assignment
//        this.documents = mDocs;
//
//        
//        generateGUID();
//        generateRSAKeyPair();
//        generateSecretKey();
//              
//    }    
//    
//    public Merchant(
//                String businessName,
//                String businessNumber,
//                Customer customer
//                ){
//        this.businessName = businessName;
//        this.businessNumber = businessNumber;
//        this.customer = customer;
//        
//        generateGUID();
//        generateRSAKeyPair();
//        generateSecretKey();
//    }  
//    
//    private static String current_date() {
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//
//        //get current date time with Calendar()
//        Calendar cal = Calendar.getInstance();
//        
//        return dateFormat.format(cal.getTime());
//
//    }
//    
//    private void generateSecretKey(){
//        //This generates the secret key for the use with Woocommerce
//        
//        String today = current_date();
//        String base = this.getGuid() + today;
//        
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(base.getBytes("UTF-8"));
//            StringBuilder hexString = new StringBuilder();
//
//            for (int i = 0; i < hash.length; i++) {
//                String hex = Integer.toHexString(0xff & hash[i]);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//
//            this.setSecretKey(hexString.toString());
//
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
//            throw new RuntimeException(ex);
//        }        
//        
//    }
//    
//    private void generateGUID(){
//        // Creating a random UUID (Universally unique identifier).
//        UUID uuid = UUID.randomUUID();
//        this.setGuid(uuid.toString());
//    }
//    
//       
//    private void generateRSAKeyPair(){
//        CryptoUtil cryptoUtil = new CryptoUtil();
//        KeyPair keyPair = null;
//        try {
//            keyPair = cryptoUtil.generateRsaKeyPair();
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(Merchant.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (keyPair != null){
//            byte[] publicKey = keyPair.getPublic().getEncoded();
//            byte[] privateKey = keyPair.getPrivate().getEncoded();
//            
//            //encoding keys to Base64 text format so that we can send public key via REST API
//            this.setRsaPublicKey(new String(Base64.getEncoder().encode(publicKey)));
//            this.setRsaPrivateKey(new String(Base64.getEncoder().encode(privateKey))); 
//        }
//    }
//
//
//    
//    public String toJSON(){
//        return this.toJSON("yyyy-MM-dd HH:mm:ss");
//    }  
//
//    public String toJSON(String format){
//        Gson gson = new GsonBuilder().setDateFormat(format).create();
//        return gson.toJson(this);
//    } 
//
//    
//    /**
//     * @return the guid
//     */
//    public String getGuid() {
//        return guid;
//    }
//
//    /**
//     * @param guid the guid to set
//     */
//    public void setGuid(String guid) {
//        this.guid = guid;
//    }
//
//    /**
//     * @return the rsaPublicKey
//     */
//    public String getRsaPublicKey() {
//        return rsaPublicKey;
//    }
//
//    /**
//     * @param rsaPublicKey the rsaPublicKey to set
//     */
//    public void setRsaPublicKey(String rsaPublicKey) {
//        this.rsaPublicKey = rsaPublicKey;
//    }
//
//    /**
//     * @return the rsaPrivateKey
//     */
//    public String getRsaPrivateKey() {
//        return rsaPrivateKey;
//    }
//
//    /**
//     * @param rsaPrivateKey the rsaPrivateKey to set
//     */
//    public void setRsaPrivateKey(String rsaPrivateKey) {
//        this.rsaPrivateKey = rsaPrivateKey;
//    }
//
//    /**
//     * @return the id
//     */
//    public long getId() {
//        return id;
//    }
//
//    /**
//     * @param id the id to set
//     */
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    /**
//     * @return the customer
//     */
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    /**
//     * @param customer the customer to set
//     */
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    /**
//     * @return the businessName
//     */
//    public String getBusinessName() {
//        return businessName;
//    }
//
//    /**
//     * @param businessName the businessName to set
//     */
//    public void setBusinessName(String businessName) {
//        this.businessName = businessName;
//    }
//
//    /**
//     * @return the businessNumber
//     */
//    public String getBusinessNumber() {
//        return businessNumber;
//    }
//
//    /**
//     * @param businessNumber the businessNumber to set
//     */
//    public void setBusinessNumber(String businessNumber) {
//        this.businessNumber = businessNumber;
//    }
//
//    /**
//     * @return the customer_id
//     */
//    public long getCustomer_id() {
//        return customer_id;
//    }
//
//    /**
//     * @param customer_id the customer_id to set
//     */
//    public void setCustomer_id(long customer_id) {
//        this.customer_id = customer_id;
//    }
//
//    /**
//     * @return the secretKey
//     */
//    public String getSecretKey() {
//        return secretKey;
//    }
//
//    /**
//     * @param secretKey the secretKey to set
//     */
//    public void setSecretKey(String secretKey) {
//        this.secretKey = secretKey;
//    }
//
//    /**
//     * @return the cardAcceptorName
//     */
//    public String getCardAcceptorName() {
//        return cardAcceptorName;
//    }
//
//    /**
//     * @param cardAcceptorName the cardAcceptorName to set
//     */
//    public void setCardAcceptorName(String cardAcceptorName) {
//        this.cardAcceptorName = cardAcceptorName;
//    }
//
//    /**
//     * @return the businessPhoneNumber
//     */
//    public String getBusinessPhoneNumber() {
//        return businessPhoneNumber;
//    }
//
//    /**
//     * @param businessPhoneNumber the businessPhoneNumber to set
//     */
//    public void setBusinessPhoneNumber(String businessPhoneNumber) {
//        this.businessPhoneNumber = businessPhoneNumber;
//    }
//
//    /**
//     * @return the notifEmail
//     */
//    public String getNotifEmail() {
//        return notifEmail;
//    }
//
//    /**
//     * @param notifEmail the notifEmail to set
//     */
//    public void setNotifEmail(String notifEmail) {
//        this.notifEmail = notifEmail;
//    }
//
//    /**
//     * @return the notifURL
//     */
//    public String getNotifURL() {
//        return notifURL;
//    }
//
//    /**
//     * @param notifURL the notifURL to set
//     */
//    public void setNotifURL(String notifURL) {
//        this.notifURL = notifURL;
//    }
//
//    /**
//     * @return the failURL
//     */
//    public String getFailURL() {
//        return failURL;
//    }
//
//    /**
//     * @param failURL the failURL to set
//     */
//    public void setFailURL(String failURL) {
//        this.failURL = failURL;
//    }
//
//    /**
//     * @return the cancelURL
//     */
//    public String getCancelURL() {
//        return cancelURL;
//    }
//
//    /**
//     * @param cancelURL the cancelURL to set
//     */
//    public void setCancelURL(String cancelURL) {
//        this.cancelURL = cancelURL;
//    }
//
//    /**
//     * @return the successURL
//     */
//    public String getSuccessURL() {
//        return successURL;
//    }
//
//    /**
//     * @param successURL the successURL to set
//     */
//    public void setSuccessURL(String successURL) {
//        this.successURL = successURL;
//    }
//
//    /**
//     * @return the integrate
//     */
//    public String getIntegrate() {
//        return integrate;
//    }
//
//    /**
//     * @param integrate the integrate to set
//     */
//    public void setIntegrate(String integrate) {
//        this.integrate = integrate;
//    }
//
//    /**
//     * @return the currency
//     */
//    public String getCurrency() {
//        return currency;
//    }
//
//    /**
//     * @param currency the currency to set
//     */
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    /**
//     * @return the documents
//     */
//    public MerchantDocuments getDocuments() {
//        return documents;
//    }
//
//    /**
//     * @param documents the documents to set
//     */
//    public void setDocuments(MerchantDocuments documents) {
//        this.documents = documents;
//    }
//
//    /**
//     * @return the non3D
//     */
//    public boolean isNon3D() {
//        return non3D;
//    }
//
//    /**
//     * @param non3D the non3D to set
//     */
//    public void setNon3D(boolean non3D) {
//        this.non3D = non3D;
//    }
//
//    /**
//     * @return the refund
//     */
//    public boolean isRefund() {
//        return refund;
//    }
//
//    /**
//     * @param refund the refund to set
//     */
//    public void setRefund(boolean refund) {
//        this.refund = refund;
//    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return the businessname
     */
    public String getBusinessname() {
        return businessname;
    }

    /**
     * @param businessname the businessname to set
     */
    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    /**
     * @return the businessnumber
     */
    public String getBusinessnumber() {
        return businessnumber;
    }

    /**
     * @param businessnumber the businessnumber to set
     */
    public void setBusinessnumber(String businessnumber) {
        this.businessnumber = businessnumber;
    }

    /**
     * @return the rsapublickey
     */
    public String getRsapublickey() {
        return rsapublickey;
    }

    /**
     * @param rsapublickey the rsapublickey to set
     */
    public void setRsapublickey(String rsapublickey) {
        this.rsapublickey = rsapublickey;
    }

    /**
     * @return the rsaprivatekey
     */
    public String getRsaprivatekey() {
        return rsaprivatekey;
    }

    /**
     * @param rsaprivatekey the rsaprivatekey to set
     */
    public void setRsaprivatekey(String rsaprivatekey) {
        this.rsaprivatekey = rsaprivatekey;
    }

    /**
     * @return the secretkey
     */
    public String getSecretkey() {
        return secretkey;
    }

    /**
     * @param secretkey the secretkey to set
     */
    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    /**
     * @return the customer_id
     */
    public long getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @return the cardacceptorname
     */
    public String getCardacceptorname() {
        return cardacceptorname;
    }

    /**
     * @param cardacceptorname the cardacceptorname to set
     */
    public void setCardacceptorname(String cardacceptorname) {
        this.cardacceptorname = cardacceptorname;
    }

    /**
     * @return the phone_number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * @param phone_number the phone_number to set
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * @return the non3D
     */
    public boolean isNon3D() {
        return non3D;
    }

    /**
     * @param non3D the non3D to set
     */
    public void setNon3D(boolean non3D) {
        this.non3D = non3D;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the refund
     */
    public boolean isRefund() {
        return refund;
    }

    /**
     * @param refund the refund to set
     */
    public void setRefund(boolean refund) {
        this.refund = refund;
    }

    /**
     * @return the notifEmail
     */
    public String getNotifEmail() {
        return notifEmail;
    }

    /**
     * @param notifEmail the notifEmail to set
     */
    public void setNotifEmail(String notifEmail) {
        this.notifEmail = notifEmail;
    }

    /**
     * @return the notifURL
     */
    public String getNotifURL() {
        return notifURL;
    }

    /**
     * @param notifURL the notifURL to set
     */
    public void setNotifURL(String notifURL) {
        this.notifURL = notifURL;
    }

    /**
     * @return the failURL
     */
    public String getFailURL() {
        return failURL;
    }

    /**
     * @param failURL the failURL to set
     */
    public void setFailURL(String failURL) {
        this.failURL = failURL;
    }

    /**
     * @return the cancelURL
     */
    public String getCancelURL() {
        return cancelURL;
    }

    /**
     * @param cancelURL the cancelURL to set
     */
    public void setCancelURL(String cancelURL) {
        this.cancelURL = cancelURL;
    }

    /**
     * @return the successURL
     */
    public String getSuccessURL() {
        return successURL;
    }

    /**
     * @param successURL the successURL to set
     */
    public void setSuccessURL(String successURL) {
        this.successURL = successURL;
    }

    /**
     * @return the integrate
     */
    public String getIntegrate() {
        return integrate;
    }

    /**
     * @param integrate the integrate to set
     */
    public void setIntegrate(String integrate) {
        this.integrate = integrate;
    }
}
