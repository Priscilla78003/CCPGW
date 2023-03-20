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
public class ThreeDSRequestorPriorTransactionAuthenticationInformation {
    private String threeDSReqPriorRef;
    private String threeDSReqPriorAuthMethod;
    private String threeDSReqPriorAuthTimestamp;
    private String threeDSReqPriorAuthData;   

    /**
     * @return the threeDSReqPriorRef
     */
    public String getThreeDSReqPriorRef() {
        return threeDSReqPriorRef;
    }

    /**
     * @param threeDSReqPriorRef the threeDSReqPriorRef to set
     */
    public void setThreeDSReqPriorRef(String threeDSReqPriorRef) {
        this.threeDSReqPriorRef = threeDSReqPriorRef;
    }

    /**
     * @return the threeDSReqPriorAuthMethod
     */
    public String getThreeDSReqPriorAuthMethod() {
        return threeDSReqPriorAuthMethod;
    }

    /**
     * @param threeDSReqPriorAuthMethod the threeDSReqPriorAuthMethod to set
     */
    public void setThreeDSReqPriorAuthMethod(String threeDSReqPriorAuthMethod) {
        this.threeDSReqPriorAuthMethod = threeDSReqPriorAuthMethod;
    }

    /**
     * @return the threeDSReqPriorAuthTimestamp
     */
    public String getThreeDSReqPriorAuthTimestamp() {
        return threeDSReqPriorAuthTimestamp;
    }

    /**
     * @param threeDSReqPriorAuthTimestamp the threeDSReqPriorAuthTimestamp to set
     */
    public void setThreeDSReqPriorAuthTimestamp(String threeDSReqPriorAuthTimestamp) {
        this.threeDSReqPriorAuthTimestamp = threeDSReqPriorAuthTimestamp;
    }

    /**
     * @return the threeDSReqPriorAuthData
     */
    public String getThreeDSReqPriorAuthData() {
        return threeDSReqPriorAuthData;
    }

    /**
     * @param threeDSReqPriorAuthData the threeDSReqPriorAuthData to set
     */
    public void setThreeDSReqPriorAuthData(String threeDSReqPriorAuthData) {
        this.threeDSReqPriorAuthData = threeDSReqPriorAuthData;
    }
}
