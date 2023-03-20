/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.results.response;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ResultsRequest {
    private String threeDSServerTransID;
    private String acsTransID;
    private ACSRenderingType acsRenderingType;
    private String authenticationMethod;
    private String authenticationType;
    private String authenticationValue;
    private String dsTransID;
    private String eci;
    private String interactionCounte;
    private String messageCategory;
    private String messageType;
    private String messageVersion;
    private String transStatus;  
    
    public ResultsRequest(){
        
    }
    
    public ResultsRequest(String threeDSServerTransID,    
                          String acsTransID,    
                          ACSRenderingType acsRenderingType,    
                          String authenticationMethod,    
                          String authenticationType,    
                          String authenticationValue,    
                          String dsTransID,    
                          String eci,    
                          String interactionCounte,    
                          String messageCategory,    
                          String messageType,    
                          String messageVersion,    
                          String transStatus){
        this();
        this.threeDSServerTransID =threeDSServerTransID;
        this.acsTransID = acsTransID;
        this.acsRenderingType = acsRenderingType;
        this.authenticationMethod = authenticationMethod;
        this.authenticationType = authenticationType;
        this.authenticationValue = authenticationValue;
        this.dsTransID = dsTransID;
        this.eci = eci;
        this.interactionCounte = interactionCounte;
        this.messageCategory = messageCategory;
        this.messageType = messageType;
        this.messageVersion = messageVersion;
        this.transStatus = transStatus;        
    }    
    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }      
    
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
     * @return the acsRenderingType
     */
    public ACSRenderingType getAcsRenderingType() {
        return acsRenderingType;
    }

    /**
     * @param acsRenderingType the acsRenderingType to set
     */
    public void setAcsRenderingType(ACSRenderingType acsRenderingType) {
        this.acsRenderingType = acsRenderingType;
    }

    /**
     * @return the authenticationMethod
     */
    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    /**
     * @param authenticationMethod the authenticationMethod to set
     */
    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    /**
     * @return the authenticationType
     */
    public String getAuthenticationType() {
        return authenticationType;
    }

    /**
     * @param authenticationType the authenticationType to set
     */
    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    /**
     * @return the authenticationValue
     */
    public String getAuthenticationValue() {
        return authenticationValue;
    }

    /**
     * @param authenticationValue the authenticationValue to set
     */
    public void setAuthenticationValue(String authenticationValue) {
        this.authenticationValue = authenticationValue;
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
     * @return the eci
     */
    public String getEci() {
        return eci;
    }

    /**
     * @param eci the eci to set
     */
    public void setEci(String eci) {
        this.eci = eci;
    }

    /**
     * @return the interactionCounte
     */
    public String getInteractionCounte() {
        return interactionCounte;
    }

    /**
     * @param interactionCounte the interactionCounte to set
     */
    public void setInteractionCounte(String interactionCounte) {
        this.interactionCounte = interactionCounte;
    }

    /**
     * @return the messageCategory
     */
    public String getMessageCategory() {
        return messageCategory;
    }

    /**
     * @param messageCategory the messageCategory to set
     */
    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
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
}
