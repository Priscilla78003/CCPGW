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
package com.truteq.ccpgw.transaction.manager.model;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class CaptureElement {
    
    private String transactionId;
    
    private String messageType;              //DE0
    
    private String primaryAccountNumber;     //DE2 

    private String processingCode;           //DE3
    
    private String transactionAmount;        //DE4

    private String transmissionDateTime;     //DE7
    
    private String systemTraceAuditNumber;   //DE11

    private String localTranTime;            //DE12

    private String localTranDate;            //DE13
    
    private String expiryDate;               //DE14
    
    private String posConditionCode;         //DE25
    
    private String acquiringInstitutionCode; //DE32
    
    private String retrievalRefNumber;             //DE37
    
    private String terminalId;               //DE41 
    
    private String posDataCode;              //DE123
      
    private String routingInformation;                  //DE127.03

    
    public CaptureElement(){
        
    }
    
    public CaptureElement(
            String transactionId,
            String messageType,
            String primaryAccountNumber,
            String processingCode,
            String transactionAmount,
            String transmissionDateTime,
            String systemTraceAuditNumber,
            String localTranTime,
            String localTranDate,
            String expiryDate,
            String posConditionCode,
            String acquiringInstitutionCode,
            String retrievalRefNumber,
            String terminalId,
            String posDataCode, 
            String routingInformation){

            this();
            this.transactionId =transactionId;
            this.messageType = messageType;                       //DE0
            this.primaryAccountNumber = primaryAccountNumber;     //DE2
            this.processingCode = processingCode;                 //DE3
            this.transactionAmount = transactionAmount;           //DE4
            this.transmissionDateTime = transmissionDateTime;     //DE7
            this.systemTraceAuditNumber = systemTraceAuditNumber; //DE11
            this.localTranTime = localTranTime;                   //DE12
            this.localTranDate = localTranDate;                   //DE13  
            this.expiryDate = expiryDate;                         //DE14 
            this.posConditionCode = posConditionCode;             //DE25
            this.acquiringInstitutionCode = acquiringInstitutionCode; //DE32
            this.retrievalRefNumber = retrievalRefNumber;         //DE37
            this.terminalId = terminalId;                         //DE41 
            this.posDataCode = posDataCode;                       //DE123
            this.routingInformation= routingInformation;
        
    }
    
    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }     

    /**
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
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
     * @return the expiryDate
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the retrievalRefNumber
     */
    public String getRetrievalRefNumber() {
        return retrievalRefNumber;
    }

    /**
     * @param retrievalRefNumber the retrievalRefNumber to set
     */
    public void setRetrievalRefNumber(String retrievalRefNumber) {
        this.retrievalRefNumber = retrievalRefNumber;
    }

    /**
     * @return the routingInformation
     */
    public String getRoutingInformation() {
        return routingInformation;
    }

    /**
     * @param routingInformation the routingInformation to set
     */
    public void setRoutingInformation(String routingInformation) {
        this.routingInformation = routingInformation;
    }
    
}
