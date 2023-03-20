/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Postilion Restful server : POSTILION - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.postilion.rest.microserv.transaction.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
  public class AuthoriseRequest implements Serializable{

    private String transmissionDateTime;     //DE7
    
    private String systemTraceAuditNumber;   //DE11

    private String localTranTime;            //DE12

    private String localTranDate;            //DE13  

    private String primaryAccountNumber;     //DE2

    private String processingCode;           //DE3
    
    private String transactionAmount;        //DE4
    
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
    
    private String cvv2;                     //DE127.10
    
    private String avd;                      //DE127.15
    
    private String cvd;                      //DE127.27 

    private String americanExpressCardIdentifier;//DE127.28
    
    private byte [] threeDSecureData;        //DE127.29
    
    private String threeDSecureResult;       //DE127.30 

    private String ucafData;                 //DE127.32

    private String structuredData;           //DE127.22     
    
    public AuthoriseRequest(){
        
    }
    
    /*
    AuthoriseRequest(paymentType.getPaymentCard().getCardNumber(),//primaryAccountNumber,    //DE2                                                           
                                                        "000000",          //processingCode,          //DE3  
                                                        checkAmount(paymentType.getAmountDetail().getAmount().toString()),    //transactionAmount,       // DE4  
                                                        checkExpiryDate(paymentType.getPaymentCard().getExpireDate()),            //accountExpiryDate, YYMM  //DE14
                                                        "6300",            //merchantType,            //DE18 
                                                        "010",             //posEntryMode             //DE22
                                                        "00",              //posConditionCode         //DE25 
                                                        acquiringInstitutionCode,     //acquiringInstitutionCode //DE32 
                                                        terminalId,        //terminalId,              //DE41 
                                                        merchantId,        //merchantId,              //DE42  
                                                        cardAcceptorNameLocation, //cardAcceptorNameLocation,//DE43 
                                                        "598",             //currencyCodeTransaction, //DE49
                                                        posDataCode, //posDataCode,             //DE123
                                                        paymentType.getPaymentCard().getCVC(),        //cvv2,                  //DE127.10
                                                        avd,                                          //DE127.15
                                                        "",                                           //DE127.27
                                                        "",                //AmericanExpressCardIdentifier, ////DE127.28 
                                                        ds,                //threeDSecureData,        //DE127.29
                                                        "2",               //threeDSecureResult,      //DE127.30
                                                        null);  
        }
    */
    
    
    public AuthoriseRequest(
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
                            String cvv2,
                            String avd,
                            String cvd,
                            String americanExpressCardIdentifier,
                            byte [] threeDSecureData,
                            String threeDSecureResult,
                            String ucafData,
                            String structuredData){
     this();

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
        
        this.cvv2 = cvv2;
        this.avd = avd;
        this.cvd = cvd;
        this.americanExpressCardIdentifier = americanExpressCardIdentifier;
        this.threeDSecureData = threeDSecureData;
        this.threeDSecureResult = threeDSecureResult;
        this.ucafData = ucafData;
        this.structuredData = structuredData;
    }
    
    public AuthoriseRequest(
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
                            String cvv2,
                            String avd,
                            String cvd,
                            String americanExpressCardIdentifier,
                            byte [] threeDSecureData,
                            String threeDSecureResult,
                            String structuredData){
     this();

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
        
        this.cvv2 = cvv2;
        this.avd = avd;
        this.cvd = cvd;
        this.americanExpressCardIdentifier = americanExpressCardIdentifier;
        this.threeDSecureData = threeDSecureData;
        this.threeDSecureResult = threeDSecureResult;
        this.structuredData = structuredData;
    }    
    
    
    public AuthoriseRequest(
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
                            String posDataCode){
     this();
     
        this.primaryAccountNumber = primaryAccountNumber;    //DE2
        this.processingCode = processingCode;                //DE3
        this.transactionAmount = transactionAmount;          //DE4 
        this.transmissionDateTime = transmissionDateTime;    //DE7
        this.systemTraceAuditNumber = systemTraceAuditNumber;//DE11
        this.localTranTime = localTranTime;                  //DE12
        this.localTranDate = localTranDate;                  //DE13 
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
    }       
    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }    
    

    /**
     * @return the transmissionDateTime
     */
    public String getTransmissionDateTime() {
        return transmissionDateTime;
    }

    /**
     * @param transmissionDateTime the transmissionDateTime to set
     */
    public void setTransmissionDateTime(String transmissionDateTime) {
        this.transmissionDateTime = transmissionDateTime;
    }

    /**
     * @return the systemTraceAuditNumber
     */
    public String getSystemTraceAuditNumber() {
        return systemTraceAuditNumber;
    }

    /**
     * @param systemTraceAuditNumber the systemTraceAuditNumber to set
     */
    public void setSystemTraceAuditNumber(String systemTraceAuditNumber) {
        this.systemTraceAuditNumber = systemTraceAuditNumber;
    }

    /**
     * @return the localTranTime
     */
    public String getLocalTranTime() {
        return localTranTime;
    }

    /**
     * @param localTranTime the localTranTime to set
     */
    public void setLocalTranTime(String localTranTime) {
        this.localTranTime = localTranTime;
    }

    /**
     * @return the localTranDate
     */
    public String getLocalTranDate() {
        return localTranDate;
    }

    /**
     * @param localTranDate the localTranDate to set
     */
    public void setLocalTranDate(String localTranDate) {
        this.localTranDate = localTranDate;
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
     * @return the cvv2
     */
    public String getCvv2() {
        return cvv2;
    }

    /**
     * @param cvv2 the cvv2 to set
     */
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    /**
     * @return the americanExpressCardIdentifier
     */
    public String getAmericanExpressCardIdentifier() {
        return americanExpressCardIdentifier;
    }

    /**
     * @param americanExpressCardIdentifier the americanExpressCardIdentifier to set
     */
    public void setAmericanExpressCardIdentifier(String americanExpressCardIdentifier) {
        this.americanExpressCardIdentifier = americanExpressCardIdentifier;
    }

    /**
     * @return the threeDSecureData
     */
    public byte[] getThreeDSecureData() {
        return threeDSecureData;
    }

    /**
     * @param threeDSecureData the threeDSecureData to set
     */
    public void setThreeDSecureData(byte[] threeDSecureData) {
        this.threeDSecureData = threeDSecureData;
    }

    /**
     * @return the threeDSecureResult
     */
    public String getThreeDSecureResult() {
        return threeDSecureResult;
    }

    /**
     * @param threeDSecureResult the threeDSecureResult to set
     */
    public void setThreeDSecureResult(String threeDSecureResult) {
        this.threeDSecureResult = threeDSecureResult;
    }

//    /**
//     * @return the ucafData
//     */
//    public byte[] getUcafData() {
//        return ucafData;
//    }
//
//    /**
//     * @param ucafData the ucafData to set
//     */
//    public void setUcafData(byte[] ucafData) {
//        this.ucafData = ucafData;
//    }
    /**
     * @return the ucafData
     */
    public String getUcafData() {
        return ucafData;
    }

    /**
     * @param ucafData the ucafData to set
     */
    public void setUcafData(String ucafData) {
        this.ucafData = ucafData;
    }   
    
    /**
     * @return the avd
     */
    public String getAvd() {
        return avd;
    }

    /**
     * @param avd the avd to set
     */
    public void setAvd(String avd) {
        this.avd = avd;
    }

    /**
     * @return the cvd
     */
    public String getCvd() {
        return cvd;
    }

    /**
     * @param cvd the cvd to set
     */
    public void setCvd(String cvd) {
        this.cvd = cvd;
    }

    /**
     * @return the structuredData
     */
    public String getStructuredData() {
        return structuredData;
    }

    /**
     * @param structuredData the structuredData to set
     */
    public void setStructuredData(String structuredData) {
        this.structuredData = structuredData;
    }
    
}
