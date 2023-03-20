/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AuthReqData {
    private String threeDSServerTransID;
    private String preferredProtocolVersion;
    private String enforcePreferredProtocolVersion;
    private String deviceChannel;
    private String messageCategory;
    private String threeDSCompInd;
    private String threeDSRequestorURL;
    private String threeRIInd;
    private String messageExtension;
    private String challengeMessageExtension; 
    
    public AuthReqData(){
    }  

    public AuthReqData  (ThreeDSServerAuthenticationRequest authReq){
        this();
        this.threeDSServerTransID  = authReq.getThreeDSServerTransID();
        this.preferredProtocolVersion = authReq.getPreferredProtocolVersion();
        if (Objects.equals(authReq.getEnforcePreferredProtocolVersion(), Boolean.TRUE))
            this.enforcePreferredProtocolVersion = "true";
        else
            this.enforcePreferredProtocolVersion = "false";
        this.deviceChannel = authReq.getDeviceChannel();
        this.messageCategory = authReq.getMessageCategory();
        this.threeDSCompInd = authReq.getThreeDSCompInd();
        if (authReq.getThreeDSRequestorURL() != null)
           this.threeDSRequestorURL = authReq.getThreeDSRequestorURL().toString();
        if (authReq.getThreeRIInd()!= null)
           this.threeRIInd = authReq.getThreeRIInd();
        if (authReq.getMessageExtension()!= null)
           this.messageExtension =  MessageExtensionHandler(authReq.getMessageExtension());
        if (authReq.getChallengeMessageExtension() != null)
           this.challengeMessageExtension = MessageExtensionHandler(authReq.getChallengeMessageExtension());
    }
    
    private String MessageExtensionHandler(List<MessageExtensionAttribute> list){
        if (list != null){
            StringBuilder messageExtensionBuilder = new StringBuilder();
            int count = 0;
            for (MessageExtensionAttribute me : list ){
                count++;
                messageExtensionBuilder.append(me.getId())
                                .append(":")
                                .append(me.getName())
                                .append(":")
                                .append(me.getCriticalityIndicator())
                                .append(":")
                                .append(me.getData());
                if (count < list.size()){
                    messageExtensionBuilder.append(",");
                }
            }
            return messageExtensionBuilder.toString();
        }
        else return "";
        
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
     * @return the preferredProtocolVersion
     */
    public String getPreferredProtocolVersion() {
        return preferredProtocolVersion;
    }

    /**
     * @param preferredProtocolVersion the preferredProtocolVersion to set
     */
    public void setPreferredProtocolVersion(String preferredProtocolVersion) {
        this.preferredProtocolVersion = preferredProtocolVersion;
    }

    /**
     * @return the enforcePreferredProtocolVersion
     */
    public String getEnforcePreferredProtocolVersion() {
        return enforcePreferredProtocolVersion;
    }

    /**
     * @param enforcePreferredProtocolVersion the enforcePreferredProtocolVersion to set
     */
    public void setEnforcePreferredProtocolVersion(String enforcePreferredProtocolVersion) {
        this.enforcePreferredProtocolVersion = enforcePreferredProtocolVersion;
    }

    /**
     * @return the deviceChannel
     */
    public String getDeviceChannel() {
        return deviceChannel;
    }

    /**
     * @param deviceChannel the deviceChannel to set
     */
    public void setDeviceChannel(String deviceChannel) {
        this.deviceChannel = deviceChannel;
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
     * @return the threeDSCompInd
     */
    public String getThreeDSCompInd() {
        return threeDSCompInd;
    }

    /**
     * @param threeDSCompInd the threeDSCompInd to set
     */
    public void setThreeDSCompInd(String threeDSCompInd) {
        this.threeDSCompInd = threeDSCompInd;
    }

    /**
     * @return the threeDSRequestorURL
     */
    public String getThreeDSRequestorURL() {
        return threeDSRequestorURL;
    }

    /**
     * @param threeDSRequestorURL the threeDSRequestorURL to set
     */
    public void setThreeDSRequestorURL(String threeDSRequestorURL) {
        this.threeDSRequestorURL = threeDSRequestorURL;
    }

    /**
     * @return the threeRIInd
     */
    public String getThreeRIInd() {
        return threeRIInd;
    }

    /**
     * @param threeRIInd the threeRIInd to set
     */
    public void setThreeRIInd(String threeRIInd) {
        this.threeRIInd = threeRIInd;
    }

    /**
     * @return the messageExtension
     */
    public String getMessageExtension() {
        return messageExtension;
    }

    /**
     * @param messageExtension the messageExtension to set
     */
    public void setMessageExtension(String messageExtension) {
        this.messageExtension = messageExtension;
    }

    /**
     * @return the challengeMessageExtension
     */
    public String getChallengeMessageExtension() {
        return challengeMessageExtension;
    }

    /**
     * @param challengeMessageExtension the challengeMessageExtension to set
     */
    public void setChallengeMessageExtension(String challengeMessageExtension) {
        this.challengeMessageExtension = challengeMessageExtension;
    }
}
