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
public class TDSResultResponseObject {
   
private String threeDSServerTransID;
private String transStatus;
private String authenticationValue;
private String eci;
private String acsTransID;
private String acsRenderingType_acsInterface;
private String acsRenderingType_acsUiTemplate;
private String authenticationMethod;
private String authenticationType;
private String req_authenticationValue;
private String req_dsTransID;
private String req_eci;
private String interactionCounter;
private String messageCategory;
private String req_messageType;
private String req_Version;
private String req_transStatus;
private String res_acsTransID;
private String res_dsTransID;
private String res_messageType;
private String res_messageVersion;
private String resultsStatus;


    public ThreeDSServerResultsResponse toThreeDSServerResultsResponse(){
        ThreeDSServerResultsResponse tdsServerResultsResponse = new ThreeDSServerResultsResponse();
        tdsServerResultsResponse.setThreeDSServerTransID(threeDSServerTransID);
        tdsServerResultsResponse.setTransStatus(transStatus);
        tdsServerResultsResponse.setAuthenticationValue(authenticationValue);
        tdsServerResultsResponse.setEci(eci);
        
        ResultsRequest rreq = new ResultsRequest(threeDSServerTransID,    
                          acsTransID,    
                          new ACSRenderingType(acsRenderingType_acsInterface,
                                               acsRenderingType_acsUiTemplate),    
                          authenticationMethod,    
                          authenticationType,    
                          req_authenticationValue,    
                          req_dsTransID,    
                          req_eci,    
                          interactionCounter,    
                          messageCategory,    
                          req_messageType,    
                          req_Version,    
                          req_transStatus);
        
        ResultsResponse rres = new ResultsResponse(threeDSServerTransID,
                           res_acsTransID,
                           res_dsTransID,
                           res_messageType,
                           res_messageVersion,
                           resultsStatus);      
        tdsServerResultsResponse.setResultsRequest(rreq);
        tdsServerResultsResponse.setResultsResponse(rres);
        return tdsServerResultsResponse;
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
     * @return the acsRenderingType_acsInterface
     */
    public String getAcsRenderingType_acsInterface() {
        return acsRenderingType_acsInterface;
    }

    /**
     * @param acsRenderingType_acsInterface the acsRenderingType_acsInterface to set
     */
    public void setAcsRenderingType_acsInterface(String acsRenderingType_acsInterface) {
        this.acsRenderingType_acsInterface = acsRenderingType_acsInterface;
    }

    /**
     * @return the acsRenderingType_acsUiTemplate
     */
    public String getAcsRenderingType_acsUiTemplate() {
        return acsRenderingType_acsUiTemplate;
    }

    /**
     * @param acsRenderingType_acsUiTemplate the acsRenderingType_acsUiTemplate to set
     */
    public void setAcsRenderingType_acsUiTemplate(String acsRenderingType_acsUiTemplate) {
        this.acsRenderingType_acsUiTemplate = acsRenderingType_acsUiTemplate;
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
     * @return the req_authenticationValue
     */
    public String getReq_authenticationValue() {
        return req_authenticationValue;
    }

    /**
     * @param req_authenticationValue the req_authenticationValue to set
     */
    public void setReq_authenticationValue(String req_authenticationValue) {
        this.req_authenticationValue = req_authenticationValue;
    }

    /**
     * @return the req_dsTransID
     */
    public String getReq_dsTransID() {
        return req_dsTransID;
    }

    /**
     * @param req_dsTransID the req_dsTransID to set
     */
    public void setReq_dsTransID(String req_dsTransID) {
        this.req_dsTransID = req_dsTransID;
    }

    /**
     * @return the req_eci
     */
    public String getReq_eci() {
        return req_eci;
    }

    /**
     * @param req_eci the req_eci to set
     */
    public void setReq_eci(String req_eci) {
        this.req_eci = req_eci;
    }

    /**
     * @return the interactionCounter
     */
    public String getInteractionCounter() {
        return interactionCounter;
    }

    /**
     * @param interactionCounter the interactionCounter to set
     */
    public void setInteractionCounter(String interactionCounter) {
        this.interactionCounter = interactionCounter;
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
     * @return the req_messageType
     */
    public String getReq_messageType() {
        return req_messageType;
    }

    /**
     * @param req_messageType the req_messageType to set
     */
    public void setReq_messageType(String req_messageType) {
        this.req_messageType = req_messageType;
    }

    /**
     * @return the req_Version
     */
    public String getReq_Version() {
        return req_Version;
    }

    /**
     * @param req_Version the req_Version to set
     */
    public void setReq_Version(String req_Version) {
        this.req_Version = req_Version;
    }

    /**
     * @return the req_transStatus
     */
    public String getReq_transStatus() {
        return req_transStatus;
    }

    /**
     * @param req_transStatus the req_transStatus to set
     */
    public void setReq_transStatus(String req_transStatus) {
        this.req_transStatus = req_transStatus;
    }

    /**
     * @return the res_acsTransID
     */
    public String getRes_acsTransID() {
        return res_acsTransID;
    }

    /**
     * @param res_acsTransID the res_acsTransID to set
     */
    public void setRes_acsTransID(String res_acsTransID) {
        this.res_acsTransID = res_acsTransID;
    }

    /**
     * @return the res_dsTransID
     */
    public String getRes_dsTransID() {
        return res_dsTransID;
    }

    /**
     * @param res_dsTransID the res_dsTransID to set
     */
    public void setRes_dsTransID(String res_dsTransID) {
        this.res_dsTransID = res_dsTransID;
    }

    /**
     * @return the res_messageType
     */
    public String getRes_messageType() {
        return res_messageType;
    }

    /**
     * @param res_messageType the res_messageType to set
     */
    public void setRes_messageType(String res_messageType) {
        this.res_messageType = res_messageType;
    }

    /**
     * @return the res_messageVersion
     */
    public String getRes_messageVersion() {
        return res_messageVersion;
    }

    /**
     * @param res_messageVersion the res_messageVersion to set
     */
    public void setRes_messageVersion(String res_messageVersion) {
        this.res_messageVersion = res_messageVersion;
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
