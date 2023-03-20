/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Postilion Adapter: POSTILION - Transaction Manager Adapter 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.adapter.postilion.requests.objects;

import com.google.gson.Gson;
import com.truteq.ccpgw.adapter.postilion.enums.eAuth;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AuthoriseObject extends RequestObject {
    
    
    private String primaryAccountNumber;     //DE2

    private String processingCode;           //DE3
    
    private String transactionAmount;        // DE4
    
    private String accountExpiryDate;        //DE14
    
    private String merchantType;             //DE18
    
    private String posEntryMode;             //DE22
    
    private String posConditionCode;         //DE25
    
    private String acquiringInstitutionCode; //DE32
    
    private String terminalId;               //DE41
    
    private String merchantId;               //DE42
    
    private String cardAcceptorNameLocation; //DE43
    
    private String currencyCodeTransaction;  //DE49
    
    private String posDataCode;              //DE123
    
    private Field127Object field127;
    
    public AuthoriseObject( eAuth auth,
                            String sysTraceAuditNumber, 
                            String primaryAccountNumber,  
                            String processingCode,  
                            String transactionAmount,    
                            String accountExpiryDate,  
                            String merchantType,  
                            String posEntryMode,  
                            String posConditionCode,  
                            String acquiringInstitutionCode,  
                            String terminalId,  
                            String merchantId,  
                            String cardAcceptorNameLocation,  
                            String currencyCodeTransaction,  
                            String posDataCode,
                            Field127Object field127){
    
    super(auth, sysTraceAuditNumber);
    
    this.primaryAccountNumber = primaryAccountNumber;    //DE2
    this.processingCode = processingCode;                //DE3
    this.transactionAmount = transactionAmount;          //DE4    
    this.accountExpiryDate = accountExpiryDate;          //DE14
    this.merchantType = merchantType;                    //DE18
    this.posEntryMode = posEntryMode;                    //DE22
    this.posConditionCode = posConditionCode;                //DE25
    this.acquiringInstitutionCode = acquiringInstitutionCode;//DE32
    this.terminalId = terminalId;                            //DE41
    this.merchantId = merchantId;                            //DE42
    this.cardAcceptorNameLocation = cardAcceptorNameLocation;//DE43
    this.currencyCodeTransaction = currencyCodeTransaction;  //DE49
    this.posDataCode = posDataCode;                          //DE123
    this.field127 = field127;   
    }  
    
    public AuthoriseObject( eAuth auth, 
                            String primaryAccountNumber,  
                            String processingCode,  
                            String transactionAmount,
                            String transmissionDateTime,
                            String systemTraceAuditNumber,
                            String localTranTime,
                            String localTranDate,
                            String accountExpiryDate,  
                            String merchantType,  
                            String posEntryMode,  
                            String posConditionCode,  
                            String acquiringInstitutionCode,  
                            String terminalId,  
                            String merchantId,  
                            String cardAcceptorNameLocation,  
                            String currencyCodeTransaction,  
                            String posDataCode,
                            Field127Object field127){
    
    super(auth, systemTraceAuditNumber,transmissionDateTime,localTranTime,localTranDate);
    
    this.primaryAccountNumber = primaryAccountNumber;    //DE2
    this.processingCode = processingCode;                //DE3
    this.transactionAmount = transactionAmount;          //DE4    
    this.accountExpiryDate = accountExpiryDate;          //DE14
    this.merchantType = merchantType;                    //DE18
    this.posEntryMode = posEntryMode;                    //DE22
    this.posConditionCode = posConditionCode;                //DE25
    this.acquiringInstitutionCode = acquiringInstitutionCode;//DE32
    this.terminalId = terminalId;                            //DE41
    this.merchantId = merchantId;                            //DE42
    this.cardAcceptorNameLocation = cardAcceptorNameLocation;//DE43
    this.currencyCodeTransaction = currencyCodeTransaction;  //DE49
    this.posDataCode = posDataCode;                          //DE123
    this.field127 = field127;
    }
        
    public String toJSON(){
        Gson gson =new Gson();
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
     * @return the processingCode
     */
    public String getProcessingCode() {
        return processingCode;
    }

    /**
     * @param processingCode the processingCode to set
     */
    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
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
     * @return the posEntryMode
     */
    public String getPosEntryMode() {
        return posEntryMode;
    }

    /**
     * @param posEntryMode the posEntryMode to set
     */
    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    /**
     * @return the posConditionCode
     */
    public String getPosConditionCode() {
        return posConditionCode;
    }

    /**
     * @param posConditionCode the posConditionCode to set
     */
    public void setPosConditionCode(String posConditionCode) {
        this.posConditionCode = posConditionCode;
    }

    /**
     * @return the acquiringInstitutionCode
     */
    public String getAcquiringInstitutionCode() {
        return acquiringInstitutionCode;
    }

    /**
     * @param acquiringInstitutionCode the acquiringInstitutionCode to set
     */
    public void setAcquiringInstitutionCode(String acquiringInstitutionCode) {
        this.acquiringInstitutionCode = acquiringInstitutionCode;
    }

    /**
     * @return the terminalId
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * @param terminalId the terminalId to set
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
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
     * @return the cardAcceptorNameLocation
     */
    public String getCardAcceptorNameLocation() {
        return cardAcceptorNameLocation;
    }

    /**
     * @param cardAcceptorNameLocation the cardAcceptorNameLocation to set
     */
    public void setCardAcceptorNameLocation(String cardAcceptorNameLocation) {
        this.cardAcceptorNameLocation = cardAcceptorNameLocation;
    }

    /**
     * @return the currencyCodeTransaction
     */
    public String getCurrencyCodeTransaction() {
        return currencyCodeTransaction;
    }

    /**
     * @param currencyCodeTransaction the currencyCodeTransaction to set
     */
    public void setCurrencyCodeTransaction(String currencyCodeTransaction) {
        this.currencyCodeTransaction = currencyCodeTransaction;
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
     * @return the field127
     */
    public Field127Object getField127() {
        return field127;
    }

    /**
     * @param field127 the field127 to set
     */
    public void setField127(Field127Object field127) {
        this.field127 = field127;
    }
    
}
