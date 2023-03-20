/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.payment.gateway.api.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truteq.ccpgw.payment.gateway.util.CryptoUtil;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mho
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
    
    private void generateSecretKey(){
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
    
    private void generateGUID(){
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        this.setGuid(uuid.toString());
    }
    
       
    private void generateRSAKeyPair(){
        CryptoUtil cryptoUtil = new CryptoUtil();
        KeyPair keyPair = null;
        try {
            keyPair = cryptoUtil.generateRsaKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Merchant.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (keyPair != null){
            byte[] publicKey = keyPair.getPublic().getEncoded();
            byte[] privateKey = keyPair.getPrivate().getEncoded();
            
            //encoding keys to Base64 text format so that we can send public key via REST API
            this.setRsapublickey(new String(Base64.getEncoder().encode(publicKey)));
            this.setRsaprivatekey(new String(Base64.getEncoder().encode(privateKey))); 
        }
    }


    
    public String toJSON(){
        return this.toJSON("yyyy-MM-dd HH:mm:ss");
    }  

    public String toJSON(String format){
        Gson gson = new GsonBuilder().setDateFormat(format).create();
        return gson.toJson(this);
    } 

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
