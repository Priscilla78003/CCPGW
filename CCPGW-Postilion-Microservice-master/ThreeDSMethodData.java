/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.versioning;

import com.google.gson.Gson;
import java.net.URL;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ThreeDSMethodData {
    private URL threeDSMethodNotificationURL;
    private String threeDSServerTransID;
    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @return the threeDSMethodNotificationURL
     */
    public URL getThreeDSMethodNotificationURL() {
        return threeDSMethodNotificationURL;
    }

    /**
     * @param threeDSMethodNotificationURL the threeDSMethodNotificationURL to set
     */
    public void setThreeDSMethodNotificationURL(URL threeDSMethodNotificationURL) {
        this.threeDSMethodNotificationURL = threeDSMethodNotificationURL;
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
}
