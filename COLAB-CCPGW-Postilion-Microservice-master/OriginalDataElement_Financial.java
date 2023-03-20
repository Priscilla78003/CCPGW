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
public class OriginalDataElement_Financial {
    
    private String transactionId;
    
    private String messageType;              //DE0

    private String transmissionDateTime;     //DE7
    
    private String systemTraceAuditNumber;   //DE11
    
    private String acquiringInstitutionCode; //DE32
    
    private String forwardingInstitudeId;    //DE33
    
    
    public OriginalDataElement_Financial(){
        
    }

    public OriginalDataElement_Financial(String transactionId,
                                String messageType,
                                String transmissionDateTime,
                                String systemTraceAuditNumber,
                                String acquiringInstitutionCode,
                                String forwardingInstitudeId){
        this();
        this.transactionId = transactionId;
        //DE0 + DE11 + DE7+ DE32 +DE33
        this.messageType = messageType;                          //DE0
        this.systemTraceAuditNumber = systemTraceAuditNumber;    //DE11
        this.transmissionDateTime = transmissionDateTime;        //DE7
        this.acquiringInstitutionCode = acquiringInstitutionCode;//DE32
        this.forwardingInstitudeId = forwardingInstitudeId;      //DE33  
    }
    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }

    public String getDE90(){
        StringBuilder de90 = new StringBuilder();
        de90.append(this.messageType)
            .append(this.systemTraceAuditNumber)    
            .append(this.transmissionDateTime)
            .append(this.acquiringInstitutionCode)
            .append(this.forwardingInstitudeId);
        
        return de90.toString();
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
     * @return the forwardingInstitudeId
     */
    public String getForwardingInstitudeId() {
        return forwardingInstitudeId;
    }

    /**
     * @param forwardingInstitudeId the forwardingInstitudeId to set
     */
    public void setForwardingInstitudeId(String forwardingInstitudeId) {
        this.forwardingInstitudeId = forwardingInstitudeId;
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
    
}
