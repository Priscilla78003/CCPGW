/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.transaction.manager.model;

import com.google.gson.Gson;

/**
 *
 * @author mho
 */
public class Financial {

    private String transactionId;

    private String messageType;              //DE0

    private String primaryAccountNumber;     //DE2

    private String processingCode;           //DE3

    private Integer amount;                  //DE4

    private String transmissionDateTime;     //DE7

    private String systemTraceAuditNumber;   //DE11

    private String localTranTime;            //DE12

    private String localTranDate;            //DE13

    private String expiryDate;               //DE14

    private String settlementDate;           //DE15

    private String posConditionCode;         //DE25

    private String acquiringInstitutionCode; //DE32

    private String retrievalRef;             //DE37

    private String authIdResponse;           //DE38

    private String terminalId;               //DE41 

    private String posDataCode;              //DE123

    private String routing;                  //DE127.03

    public Financial() {

    }

    public Financial(String transactionId,
            String messageType,
            String primaryAccountNumber,
            String processingCode,
            Integer amount,
            String transmissionDateTime,
            String systemTraceAuditNumber,
            String localTranTime,
            String localTranDate,
            String expiryDate,
            String settlementDate,
            String posConditionCode,
            String acquiringInstitutionCode,
            String retrievalRef,
            String authIdResponse,
            String terminalId,
            String posDataCode,
            String routing) {
        this();
        this.transactionId = transactionId;
        this.messageType = messageType;                       //DE0
        this.primaryAccountNumber = primaryAccountNumber;     //DE2
        this.processingCode = processingCode;                 //DE3        
        this.amount = amount;                                 //DE4
        this.transmissionDateTime = transmissionDateTime;     //DE7
        this.systemTraceAuditNumber = systemTraceAuditNumber; //DE11
        this.localTranTime = localTranTime;                   //DE12
        this.localTranDate = localTranDate;                   //DE13         
        this.expiryDate = expiryDate;                         //DE14
        this.settlementDate = settlementDate;                 //DE15
        this.posConditionCode = posConditionCode;             //DE25 
        this.acquiringInstitutionCode = acquiringInstitutionCode; //DE32
        this.authIdResponse = authIdResponse;                 //DE38
        this.retrievalRef = retrievalRef;                     //DE37
        this.terminalId = terminalId;                         //DE41 
        this.posDataCode = posDataCode;                       //DE123            
        this.routing = routing;                               //DE127.03
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
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
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
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
     * @return the settlementDate
     */
    public String getSettlementDate() {
        return settlementDate;
    }

    /**
     * @param settlementDate the settlementDate to set
     */
    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
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
     * @return the retrievalRef
     */
    public String getRetrievalRef() {
        return retrievalRef;
    }

    /**
     * @param retrievalRef the retrievalRef to set
     */
    public void setRetrievalRef(String retrievalRef) {
        this.retrievalRef = retrievalRef;
    }

    /**
     * @return the authIdResponse
     */
    public String getAuthIdResponse() {
        return authIdResponse;
    }

    /**
     * @param authIdResponse the authIdResponse to set
     */
    public void setAuthIdResponse(String authIdResponse) {
        this.authIdResponse = authIdResponse;
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
     * @return the routing
     */
    public String getRouting() {
        return routing;
    }

    /**
     * @param routing the routing to set
     */
    public void setRouting(String routing) {
        this.routing = routing;
    }
    
    
    
}
