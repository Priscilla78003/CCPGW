/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.versioning;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ErrorDetails {
    private String threeDSServerTransID;
    private String errorCode;
    private String errorComponent;
    private String errorDescription;
    private String errorDetail;
    private String acsTransID;
    private String dsTransID;
    private String sdkTransID;


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
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorComponent
     */
    public String getErrorComponent() {
        return errorComponent;
    }

    /**
     * @param errorComponent the errorComponent to set
     */
    public void setErrorComponent(String errorComponent) {
        this.errorComponent = errorComponent;
    }

    /**
     * @return the errorDescription
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * @param errorDescription the errorDescription to set
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    /**
     * @return the errorDetail
     */
    public String getErrorDetail() {
        return errorDetail;
    }

    /**
     * @param errorDetail the errorDetail to set
     */
    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
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
}
