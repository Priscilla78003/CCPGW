/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.adapter.postilion.requests.objects;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class SignResponseObject{
    
    private String messageType;            //DE0

    private String transmissionDateTime;   //DE7
    
    private String systemTraceAuditNumber; //DE11

    private String localTranTime;          //DE12

    private String localTranDate;          //DE13
    
    private String responseCode;           //DE39
    
    private String networkManInfoCode;     //DE70

    
    
    public SignResponseObject(String messageType,
                              String transmissionDateTime,
                              String systemTraceAuditNumber,
                              String localTranTime,
                              String localTranDate,
                              String responseCode,
                              String networkManInfoCode){
        this.messageType = messageType;
        this.transmissionDateTime = transmissionDateTime;
        this.systemTraceAuditNumber = systemTraceAuditNumber;
        this.localTranDate = localTranDate;
        this.localTranTime = localTranTime;
        this.responseCode = responseCode;
        this.networkManInfoCode = networkManInfoCode;        
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
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the networkManInfoCode
     */
    public String getNetworkManInfoCode() {
        return networkManInfoCode;
    }

    /**
     * @param networkManInfoCode the networkManInfoCode to set
     */
    public void setNetworkManInfoCode(String networkManInfoCode) {
        this.networkManInfoCode = networkManInfoCode;
    }
    
}
