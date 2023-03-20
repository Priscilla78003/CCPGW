/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.transaction.manager.model;

import com.google.gson.Gson;
import com.truteq.ccpgw.adapter.postilion.enums.eAuth;
import com.truteq.ccpgw.adapter.postilion.requests.objects.CaptureObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.ReversalObject;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class RepeatElement {

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

    private String settlementDate;

    private String posConditionCode;         //DE25

    private String acquiringInstitutionCode; //DE32

    private String retrievalRefNumber;       //DE37

    private String terminalId;               //DE41 

    private String posDataCode;              //DE123

    private String routingInformation;       //DE127.03    

    private String merchantType;

    private String posEntryMode;

    private String authorizationIdResponse;

    private String merchantId;

    private String cardAcceptorNameLocation;

    private String currencyCode;

    private String originalDataElements;

    private String messageReasonCode;

    public RepeatElement() {

    }

    public RepeatElement(CaptureObject captureObj) {
        this();

        this.setSystemTraceAuditNumber(captureObj.getSystemTraceAuditNumber());
        this.setMessageType(captureObj.getMessageType());
        this.setPrimaryAccountNumber(captureObj.getPrimaryAccountNumber());
        this.setProcessingCode(captureObj.getProcessingCode());
        this.setTransactionAmount(captureObj.getTransactionAmount());
        this.setExpiryDate(captureObj.getAccountExpiryDate());
        this.setMerchantType(captureObj.getMerchantType());
        this.setPosEntryMode(captureObj.getPosEntryMode());
        this.setPosConditionCode(captureObj.getPosConditionCode());
        this.setAcquiringInstitutionCode(captureObj.getAcquiringInstitutionCode());
        this.setRetrievalRefNumber(captureObj.getRetrievalReferenceNumber());
        this.setAuthorizationIdResponse(captureObj.getAuthorizationIdResponse());
        this.setTerminalId(captureObj.getTerminalId());
        this.setMerchantId(captureObj.getMerchantId());
        this.setCardAcceptorNameLocation(captureObj.getCardAcceptorNameLocation());
        this.setCurrencyCode(captureObj.getCurrencyCodeTransaction());
        this.setOriginalDataElements(captureObj.getOriginalDataElements());
        this.setPosDataCode(captureObj.getPosDataCode());
    }

    public RepeatElement(ReversalObject revObj) {
        this();

        this.setSystemTraceAuditNumber(revObj.getSystemTraceAuditNumber());
        this.setMessageType(revObj.getMessageType());
        this.setPrimaryAccountNumber(revObj.getPrimaryAccountNumber());
        this.setProcessingCode(revObj.getProcessingCode());
        this.setTransactionAmount(revObj.getTransactionAmount());
        this.setExpiryDate(revObj.getAccountExpiryDate());
        this.setSettlementDate(revObj.getSettleDate());
        this.setMerchantType(revObj.getMerchantType());
        this.setPosEntryMode(revObj.getPosEntryMode());
        this.setPosConditionCode(revObj.getPosConditionCode());
        this.setAcquiringInstitutionCode(revObj.getAcquiringInstitutionCode());
        this.setTerminalId(revObj.getTerminalId());
        this.setMerchantId(revObj.getMerchantId());
        this.setCardAcceptorNameLocation(revObj.getCardAcceptorNameLocation());
        this.setCurrencyCode(revObj.getCurrencyCodeTransaction());
        this.setOriginalDataElements(revObj.getOriginalDataElements());
        this.setPosDataCode(revObj.getPosDataCode());

        this.setTransmissionDateTime(revObj.getTransmissionDateTime());
        this.setLocalTranDate(revObj.getLocalTranDate());
        this.setLocalTranTime(revObj.getLocalTranTime());
        this.setMessageReasonCode(revObj.getMessageReasonCode());

    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    public CaptureObject getCaptureObject(eAuth auth){

       return new CaptureObject(auth,
                                this.getSystemTraceAuditNumber(), 
                                this.getPrimaryAccountNumber(), //DE2 
                                this.getProcessingCode(),       //DE3
                                this.getTransactionAmount(),    //DE4
                                this.getExpiryDate(),           //DE14
                                this.getMerchantType(),         //DE18        
                                this.getPosEntryMode(),         //DE22  
                            this.getPosConditionCode(),         //DE25 
                            this.getAcquiringInstitutionCode(), //DE32 
                            this.getRetrievalRefNumber(),       //DE37
                            this.getAuthorizationIdResponse(),  //DE38
                            this.getTerminalId(),               //DE41
                            this.getMerchantId(),               //DE42 
                            this.getCardAcceptorNameLocation(), //DE43  
                            this.getCurrencyCode(),             //DE49        
                            this.getOriginalDataElements(),     //DE90
                            this.getPosDataCode(),              //DE123
                            null);      
        
    }
    
    public ReversalObject getReversalObject(eAuth auth){
        return new ReversalObject(auth,
                                this.getSystemTraceAuditNumber(), 
                                this.getPrimaryAccountNumber(),     //DE2 
                                this.getProcessingCode(),           //DE3
                                this.getTransactionAmount(),        //DE4
                                this.getTransmissionDateTime(),
                                this.getLocalTranTime(),
                                this.getLocalTranDate(),
                                this.getExpiryDate(),               //DE14
                                this.getSettlementDate(),           //DE15
                                this.getMerchantType(),
                                this.getPosEntryMode(),             //DE22  
                                this.getPosConditionCode(),         //DE25 
                                this.getTerminalId(),               //DE41
                                this.getMerchantId(),               //DE42 
                                this.getCardAcceptorNameLocation(), //DE43   
                                this.getCurrencyCode(),             //DE49        
                                this.getMessageReasonCode(),        //DE56
                                this.getOriginalDataElements(),     //DE90
                                this.getPosDataCode(),              //DE123
                                null);
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
     * @return the authorizationIdResponse
     */
    public String getAuthorizationIdResponse() {
        return authorizationIdResponse;
    }

    /**
     * @param authorizationIdResponse the authorizationIdResponse to set
     */
    public void setAuthorizationIdResponse(String authorizationIdResponse) {
        this.authorizationIdResponse = authorizationIdResponse;
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
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

}
