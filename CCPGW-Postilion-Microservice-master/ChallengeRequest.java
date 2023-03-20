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
public class ChallengeRequest {
    private String threeDSServerTransID;
    private String acsTransID;
    private String messageType;
    private String messageVersion;
    private String challengeWindowSize;
    private MessageExtensionAttribute messageExtension;    

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
     * @return the challengeWindowSize
     */
    public String getChallengeWindowSize() {
        return challengeWindowSize;
    }

    /**
     * @param challengeWindowSize the challengeWindowSize to set
     */
    public void setChallengeWindowSize(String challengeWindowSize) {
        this.challengeWindowSize = challengeWindowSize;
    }

    /**
     * @return the messageExtension
     */
    public MessageExtensionAttribute getMessageExtension() {
        return messageExtension;
    }

    /**
     * @param messageExtension the messageExtension to set
     */
    public void setMessageExtension(MessageExtensionAttribute messageExtension) {
        this.messageExtension = messageExtension;
    }
}
