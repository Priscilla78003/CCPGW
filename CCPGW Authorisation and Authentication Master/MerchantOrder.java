/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.model.authenticate.authorise;

import com.google.gson.Gson;

/**
 *
 * @author mho
 */
public class MerchantOrder {

    private String transaction_id;
    private String order_id;
    private String amount;
    private String currency_code;
    private String currency;
    private String guid;
    private String businessName;
    private String businessNumber;
    private String rsaPublicKey;
    private String rsaPrivateKey;
    private String secretKey;
    private String successurl;
    private String failurl;
    private String cancelurl;
    private String callback_url;

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @return the transaction_id
     */
    public String getTransaction_id() {
        return transaction_id;
    }

    /**
     * @param transaction_id the transaction_id to set
     */
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    /**
     * @return the order_id
     */
    public String getOrder_id() {
        return order_id;
    }

    /**
     * @param order_id the order_id to set
     */
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the currency_code
     */
    public String getCurrency_code() {
        return currency_code;
    }

    /**
     * @param currency_code the currency_code to set
     */
    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
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
     * @return the businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName the businessName to set
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the businessNumber
     */
    public String getBusinessNumber() {
        return businessNumber;
    }

    /**
     * @param businessNumber the businessNumber to set
     */
    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    /**
     * @return the rsaPublicKey
     */
    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    /**
     * @param rsaPublicKey the rsaPublicKey to set
     */
    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    /**
     * @return the rsaPrivateKey
     */
    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    /**
     * @param rsaPrivateKey the rsaPrivateKey to set
     */
    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    /**
     * @return the secretKey
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * @param secretKey the secretKey to set
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * @return the successurl
     */
    public String getSuccessurl() {
        return successurl;
    }

    /**
     * @param successurl the successurl to set
     */
    public void setSuccessurl(String successurl) {
        this.successurl = successurl;
    }

    /**
     * @return the failurl
     */
    public String getFailurl() {
        return failurl;
    }

    /**
     * @param failurl the failurl to set
     */
    public void setFailurl(String failurl) {
        this.failurl = failurl;
    }

    /**
     * @return the cancelurl
     */
    public String getCancelurl() {
        return cancelurl;
    }

    /**
     * @param cancelurl the cancelurl to set
     */
    public void setCancelurl(String cancelurl) {
        this.cancelurl = cancelurl;
    }

    /**
     * @return the callback_url
     */
    public String getCallback_url() {
        return callback_url;
    }

    /**
     * @param callback_url the callback_url to set
     */
    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }
    
    
}
