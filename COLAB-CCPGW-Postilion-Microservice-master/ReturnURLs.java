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
public class ReturnURLs {
    

    private String successURL;
    private String failureURL;   

    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    
    /**
     * @return the successURL
     */
    public String getSuccessURL() {
        return successURL;
    }

    /**
     * @param successURL the successURL to set
     */
    public void setSuccessURL(String successURL) {
        this.successURL = successURL;
    }

    /**
     * @return the failureURL
     */
    public String getFailureURL() {
        return failureURL;
    }

    /**
     * @param failureURL the failureURL to set
     */
    public void setFailureURL(String failureURL) {
        this.failureURL = failureURL;
    }
    
    
    
    
}
