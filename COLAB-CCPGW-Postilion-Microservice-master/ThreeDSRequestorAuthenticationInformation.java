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
public class ThreeDSRequestorAuthenticationInformation {
      private String threeDSReqAuthMethod;
      private String threeDSReqAuthTimestamp;
      private String threeDSReqAuthData; 

    /**
     * @return the threeDSReqAuthMethod
     */
    public String getThreeDSReqAuthMethod() {
        return threeDSReqAuthMethod;
    }

    /**
     * @param threeDSReqAuthMethod the threeDSReqAuthMethod to set
     */
    public void setThreeDSReqAuthMethod(String threeDSReqAuthMethod) {
        this.threeDSReqAuthMethod = threeDSReqAuthMethod;
    }

    /**
     * @return the threeDSReqAuthTimestamp
     */
    public String getThreeDSReqAuthTimestamp() {
        return threeDSReqAuthTimestamp;
    }

    /**
     * @param threeDSReqAuthTimestamp the threeDSReqAuthTimestamp to set
     */
    public void setThreeDSReqAuthTimestamp(String threeDSReqAuthTimestamp) {
        this.threeDSReqAuthTimestamp = threeDSReqAuthTimestamp;
    }

    /**
     * @return the threeDSReqAuthData
     */
    public String getThreeDSReqAuthData() {
        return threeDSReqAuthData;
    }

    /**
     * @param threeDSReqAuthData the threeDSReqAuthData to set
     */
    public void setThreeDSReqAuthData(String threeDSReqAuthData) {
        this.threeDSReqAuthData = threeDSReqAuthData;
    }
}
