/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.transaction.notification.microservice.threeds.controller;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AResObject {
    private String messageType;
    private String threeDSServerTransID;
    private String acsTransID;
    private String challengeCompletionInd;
    private String messageVersion;
    private String transStatus;

    public AResObject(String messageType,
                      String threeDSServerTransID,
                      String acsTransID,
                      String challengeCompletionInd,
                      String messageVersion,
                      String transStatus){
        this();
        this.messageType = messageType;
        this.threeDSServerTransID = threeDSServerTransID;
        this.acsTransID = acsTransID;
        this.challengeCompletionInd = challengeCompletionInd;
        this.messageVersion = messageVersion;
        this.transStatus = transStatus;     
    }    

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    public AResObject(){
        
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
     * @return the challengeCompletionInd
     */
    public String getChallengeCompletionInd() {
        return challengeCompletionInd;
    }

    /**
     * @param challengeCompletionInd the challengeCompletionInd to set
     */
    public void setChallengeCompletionInd(String challengeCompletionInd) {
        this.challengeCompletionInd = challengeCompletionInd;
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
