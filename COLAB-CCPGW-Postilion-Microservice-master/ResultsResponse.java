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
public class ResultsResponse {
   private String threeDSServerTransID;
   private String acsTransID;
   private String dsTransID;
   private String messageType;
   private String messageVersion;
   private String resultsStatus;
   
 
   public ResultsResponse( String threeDSServerTransID,
                           String acsTransID,
                           String dsTransID,
                           String messageType,
                           String messageVersion,
                           String resultsStatus){
        this();
        this.threeDSServerTransID = threeDSServerTransID;
        this.acsTransID = acsTransID;
        this.dsTransID = dsTransID;
        this.messageType = messageType;
        this.messageVersion = messageVersion;
        this.resultsStatus = resultsStatus;
   }   
   
   public ResultsResponse(){
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
     * @return the resultsStatus
     */
    public String getResultsStatus() {
        return resultsStatus;
    }

    /**
     * @param resultsStatus the resultsStatus to set
     */
    public void setResultsStatus(String resultsStatus) {
        this.resultsStatus = resultsStatus;
    }
}
