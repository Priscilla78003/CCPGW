package com.truteq.ccpgw.payment.gateway.api.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Amy Rombuk
 */
public class Refund {
    
    private String primaryAccountNumber;
    private String transactionAmount;
    private String accountExpiryDate;
    private String transactionId;
    private String merchantName;
    private String currency;
    private String orderId;

    public Refund() {
    }

    public Refund(String primaryAccountNumber,
            String transactionAmount,
            String accountExpiryDate,
            String transactionId,
            String merchantName,
            String currency,
            String orderId) {
        this();
        this.primaryAccountNumber = primaryAccountNumber;
        this.transactionAmount = transactionAmount;
        this.accountExpiryDate = accountExpiryDate;
        this.transactionId = transactionId;
        this.merchantName = merchantName;
        this.currency = currency;
        this.orderId = orderId;
        
    }
    
        public String toJSON(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    } 

    /**
     * @return the primaryAccountNumber
     */
    public String getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }

    /**
     * @param primaryAccountNumber the primaryAccountNumber to set
     */
    public void setPrimaryAccountNumber(String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
    }

    /**
     * @return the transactionAmount
     */
    public String getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * @param transactionAmount the transactionAmount to set
     */
    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * @return the accountExpiryDate
     */
    public String getAccountExpiryDate() {
        return accountExpiryDate;
    }

    /**
     * @param accountExpiryDate the accountExpiryDate to set
     */
    public void setAccountExpiryDate(String accountExpiryDate) {
        this.accountExpiryDate = accountExpiryDate;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    /**
     * @return the merchantName
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @param merchantName the merchantName to set
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}