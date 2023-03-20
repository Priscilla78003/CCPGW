/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AuthenticationResponse {
    private String threeDSServerTransID;
    private String acsTransID;
    private String acsReferenceNumber;
    private String acsOperatorID;
    private String dsReferenceNumber;
    private String dsTransID;
    private String sdkTransID;
    private String transStatus;
    private String acsChallengeMandated;
    private String messageType;
    private String messageVersion;       

    /**
     * @return the threeDSServerTransID
     */
    public String getThreeDSServerTransID() {
        return threeDSServerTransID;
    }

    /**
     * @param threeDSServerTransID the threeDSServerTransID to set
     */
    public void setThreeDSServerTransID(String threeDSServerTransID) {
        this.threeDSServerTransID = threeDSServerTransID;
    }

    /**
     * @return the acsTransID
     */
    public String getAcsTransID() {
        return acsTransID;
    }

    /**
     * @param acsTransID the acsTransID to set
     */
    public void setAcsTransID(String acsTransID) {
        this.acsTransID = acsTransID;
    }

    /**
     * @return the acsReferenceNumber
     */
    public String getAcsReferenceNumber() {
        return acsReferenceNumber;
    }

    /**
     * @param acsReferenceNumber the acsReferenceNumber to set
     */
    public void setAcsReferenceNumber(String acsReferenceNumber) {
        this.acsReferenceNumber = acsReferenceNumber;
    }

    /**
     * @return the acsOperatorID
     */
    public String getAcsOperatorID() {
        return acsOperatorID;
    }

    /**
     * @param acsOperatorID the acsOperatorID to set
     */
    public void setAcsOperatorID(String acsOperatorID) {
        this.acsOperatorID = acsOperatorID;
    }

    /**
     * @return the dsReferenceNumber
     */
    public String getDsReferenceNumber() {
        return dsReferenceNumber;
    }

    /**
     * @param dsReferenceNumber the dsReferenceNumber to set
     */
    public void setDsReferenceNumber(String dsReferenceNumber) {
        this.dsReferenceNumber = dsReferenceNumber;
    }

    /**
     * @return the dsTransID
     */
    public String getDsTransID() {
        return dsTransID;
    }

    /**
     * @param dsTransID the dsTransID to set
     */
    public void setDsTransID(String dsTransID) {
        this.dsTransID = dsTransID;
    }

    /**
     * @return the sdkTransID
     */
    public String getSdkTransID() {
        return sdkTransID;
    }

    /**
     * @param sdkTransID the sdkTransID to set
     */
    public void setSdkTransID(String sdkTransID) {
        this.sdkTransID = sdkTransID;
    }

    /**
     * @return the transStatus
     */
    public String getTransStatus() {
        return transStatus;
    }

    /**
     * @param transStatus the transStatus to set
     */
    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    /**
     * @return the acsChallengeMandated
     */
    public String getAcsChallengeMandated() {
        return acsChallengeMandated;
    }

    /**
     * @param acsChallengeMandated the acsChallengeMandated to set
     */
    public void setAcsChallengeMandated(String acsChallengeMandated) {
        this.acsChallengeMandated = acsChallengeMandated;
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
     * @return the messageVersion
     */
    public String getMessageVersion() {
        return messageVersion;
    }

    /**
     * @param messageVersion the messageVersion to set
     */
    public void setMessageVersion(String messageVersion) {
        this.messageVersion = messageVersion;
    }
}
