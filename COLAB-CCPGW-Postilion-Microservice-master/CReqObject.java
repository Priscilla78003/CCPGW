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
public class CReqObject implements IChallenge{
    /**
     * {
     *   "threeDSMethodNotificationURL":"https://ccpgw.testbsp.com.pg/3dsmethodnotification",
     *   "threeDSServerTransID":"0ee9a8ed-b594-491a-9b0d-d360f9132a0d"
     * }
     */
 
    private String threeDSServerTransID;
    private String threeDSMethodNotificationURL;

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
     * @return the threeDSMethodNotificationURL
     */
    public String getThreeDSMethodNotificationURL() {
        return threeDSMethodNotificationURL;
    }

    /**
     * @param threeDSMethodNotificationURL the threeDSMethodNotificationURL to set
     */
    public void setThreeDSMethodNotificationURL(String threeDSMethodNotificationURL) {
        this.threeDSMethodNotificationURL = threeDSMethodNotificationURL;
    }
}
