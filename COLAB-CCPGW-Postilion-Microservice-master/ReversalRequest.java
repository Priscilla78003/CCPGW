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
public class ReversalRequest implements Serializable{
    
    private String primaryAccountNumber;     //DE2

    private String processingCode;           //DE3 
    
    private String transactionAmount;        //DE4
    
    private String transmissionDateTime;     //DE7
    
    private String systemTraceAuditNumber;   //DE11

    private String localTranTime;            //DE12

    private String localTranDate;            //DE13  
    
    private String accountExpiryDate;        //DE14
    
    private String dateSettlement;           //DE15
    
    private String merchantType;             //DE18
    
    private String posEntryMode;             //DE22
    
    private String posConditionCode;         //DE25
    
    private String transactionFee;           //DE28
    
    private String settlementFee;            //DE29
    
    private String transactionProcessingFee; //DE30
     
    private String acquiringInstitutionId;   //DE32
    
    private String track2Data;               //DE35
    
    private String retrievalRefercence;      //DE37
    
    private String authorizationIdResp;      //DE38
    
    private String responseCode;             //DE39
    
    private String serviceRestrictionCode;   //DE40
    
    private String terminalId;               //DE41
    
    private String merchantId;               //DE42
    
    private String cardAcceptorNameLocation; //DE43
    
    private String currencyCodeTransaction;  //DE49
    
    private String additionalAmounts;        //DE54
    
    private String messageReasonCode;        //DE56
    
    private String originalDataElements;     //DE90
    
    private String replacementAmounts;       //DE95
    
    private String accountFrom;              //DE102 
            
    private String accountTo;                //DE103         
    
    private String posDataCode;              //DE123
    
    private String routingInfo;              //DE127.03
    
    private String originalKey;              //DE127.11
    
    private String originalNode;             //DE127.26     

  
    public ReversalRequest(){
        
    }
    
//    public ReversalRequest(
//                            String primaryAccountNumber,  
//                            String processingCode,  
//                            String transactionAmount, 
//                            String transmissionDateTime,
//                            String systemTraceAuditNumber,
//                            String localTranTime,
//                            String localTranDate,
//                            String accountExpiryDate,
//                            String dateSettlement,
//                            String merchantType,  
//                            String posEntryMode,  
//                            String posConditionCode,  
//                            String terminalId,  
//                            String merchantId,  
//                            String cardAcceptorNameLocation,  
//                            String currencyCodeTransaction, 
//                            String messageReasonCode,
//                            String originalDataElements,
//                            String posDataCode,
//                            String acquiringInstitutionId,
//                            String routingInfo,
//                            String originalKey,
//                            String originalNode){
//     this();
//
//        this.primaryAccountNumber = primaryAccountNumber;    //DE2
//        this.processingCode = processingCode;                //DE3
//        this.transactionAmount = transactionAmount;          //DE4 
//        
//        this.transmissionDateTime = transmissionDateTime;
//        this.systemTraceAuditNumber = systemTraceAuditNumber;
//        this.localTranTime = localTranTime;
//        this.localTranDate = localTranDate;
//        
//        this.accountExpiryDate = accountExpiryDate;          //DE14
//        this.dateSettlement = dateSettlement;                //DE15 
//        this.merchantType = merchantType;                    //DE18
//        this.posEntryMode = posEntryMode;                    //DE22
//        this.posConditionCode = posConditionCode;                //DE25 
//        this.terminalId = terminalId;                            //DE41
//        this.merchantId = merchantId;                            //DE42
//        this.cardAcceptorNameLocation = cardAcceptorNameLocation;//DE43
//        this.currencyCodeTransaction = currencyCodeTransaction;  //DE49
//        this.messageReasonCode = messageReasonCode;              //DE56
//        this.originalDataElements = originalDataElements;        //DE90
//        this.posDataCode = posDataCode;                          //DE123
//        this.originalKey = originalKey;                          //DE127.11 
//        this.originalNode = originalNode;                        //DE127.26 
//        this.routingInfo = routingInfo;                          //DE127.03
//    }    
    
    public ReversalRequest(
                            String primaryAccountNumber,  
                            String processingCode,  
                            String transactionAmount, 
                            String transmissionDateTime,
                            String systemTraceAuditNumber,
                            String localTranTime,
                            String localTranDate,
                            String accountExpiryDate,
                            String dateSettlement,
                            String merchantType,  
                            String posEntryMode,  
                            String posConditionCode,  
                            String terminalId,  
                            String merchantId,  
                            String cardAcceptorNameLocation,  
                            String currencyCodeTransaction, 
                            String messageReasonCode,
                            String originalDataElements,
                            String posDataCode,
                            String routingInfo,
                            String originalKey,
                            String originalData){
     this();

        this.primaryAccountNumber = primaryAccountNumber;    //DE2
        this.processingCode = processingCode;                //DE3
        this.transactionAmount = transactionAmount;          //DE4 
        
        this.transmissionDateTime = transmissionDateTime;
        this.systemTraceAuditNumber = systemTraceAuditNumber;
        this.localTranTime = localTranTime;
        this.localTranDate = localTranDate;
        
        this.accountExpiryDate = accountExpiryDate;          //DE14
        this.dateSettlement = dateSettlement;                //DE15 
        this.merchantType = merchantType;                    //DE18
        this.posEntryMode = posEntryMode;                    //DE22
        this.posConditionCode = posConditionCode;                //DE25 
        this.terminalId = terminalId;                            //DE41
        this.merchantId = merchantId;                            //DE42
        this.cardAcceptorNameLocation = cardAcceptorNameLocation;//DE43
        this.currencyCodeTransaction = currencyCodeTransaction;  //DE49
        this.messageReasonCode = messageReasonCode;              //DE56
        this.originalDataElements = originalDataElements;        //DE90
        this.posDataCode = posDataCode;                          //DE123
        this.originalKey = originalKey;                          //DE127.11 
        this.originalNode = originalData;                        //DE127.26
        this.routingInfo = routingInfo;                          //DE127.03        
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
     * @return the dateSettlement
     */
    public String getDateSettlement() {
        return dateSettlement;
    }

    /**
     * @param dateSettlement the dateSettlement to set
     */
    public void setDateSettlement(String dateSettlement) {
        this.dateSettlement = dateSettlement;
    }

    /**
     * @return the messageReasonCode
     */
    public String getMessageReasonCode() {
        return messageReasonCode;
    }

    /**
     * @param messageReasonCode the messageReasonCode to set
     */
    public void setMessageReasonCode(String messageReasonCode) {
        this.messageReasonCode = messageReasonCode;
    }

    /**
     * @return the originalDataElements
     */
    public String getOriginalDataElements() {
        return originalDataElements;
    }

    /**
     * @param originalDataElements the originalDataElements to set
     */
    public void setOriginalDataElements(String originalDataElements) {
        this.originalDataElements = originalDataElements;
    }

    /**
     * @return the originalKey
     */
    public String getOriginalKey() {
        return originalKey;
    }

    /**
     * @param originalKey the originalKey to set
     */
    public void setOriginalKey(String originalKey) {
        this.originalKey = originalKey;
    }

    /**
     * @return the originalNode
     */
    public String getOriginalNode() {
        return originalNode;
    }

    /**
     * @param originalNode the originalNode to set
     */
    public void setOriginalNode(String originalNode) {
        this.originalNode = originalNode;
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
     * @return the acquiringInstitutionId
     */
    public String getAcquiringInstitutionId() {
        return acquiringInstitutionId;
    }

    /**
     * @param acquiringInstitutionId the acquiringInstitutionId to set
     */
    public void setAcquiringInstitutionId(String acquiringInstitutionId) {
        this.acquiringInstitutionId = acquiringInstitutionId;
    }

    /**
     * @return the routingInfo
     */
    public String getRoutingInfo() {
        return routingInfo;
    }

    /**
     * @param routingInfo the routingInfo to set
     */
    public void setRoutingInfo(String routingInfo) {
        this.routingInfo = routingInfo;
    }
}

