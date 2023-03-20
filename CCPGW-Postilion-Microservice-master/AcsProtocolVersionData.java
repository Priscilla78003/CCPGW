/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.versioning;

import com.google.gson.Gson;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AcsProtocolVersionData {
    
    private String version; 
    private URL threeDSMethodURL;
    private List<String> acsInfoInd;
    private List<SupportedMsgExt> supportedMsgExt;

        public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }


    /**
     * @return the acsInfoInd
     */
    public List<String> getAcsInfoInd() {
        return acsInfoInd;
    }

    /**
     * @param acsInfoInd the acsInfoInd to set
     */
    public void setAcsInfoInd(List<String> acsInfoInd) {
        this.acsInfoInd = acsInfoInd;
    }

    /**
     * @return the supportedMsgExt
     */
    public List<SupportedMsgExt> getSupportedMsgExt() {
        return supportedMsgExt;
    }

    /**
     * @param supportedMsgExt the supportedMsgExt to set
     */
    public void setSupportedMsgExt(List<SupportedMsgExt> supportedMsgExt) {
        this.supportedMsgExt = supportedMsgExt;
    }

    /**
     * @return the threeDSMethodURL
     */
    public URL getThreeDSMethodURL() {
        return threeDSMethodURL;
    }

    /**
     * @param threeDSMethodURL the threeDSMethodURL to set
     */
    public void setThreeDSMethodURL(URL threeDSMethodURL) {
        this.threeDSMethodURL = threeDSMethodURL;
    }
    
    
}
