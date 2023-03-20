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
public class ACSRedirectObject {
    private String acsUrl;
    private String id;
    private String payload;
    
    public ACSRedirectObject(){
        
    }
    
    public ACSRedirectObject(String acsUrl,String id,String payload){
        this();
        this.acsUrl = acsUrl;
        this.payload = payload;
        this.id = id;
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }     
    
    /**
     * @return the acsUrl
     */
    public String getAcsUrl() {
        return acsUrl;
    }

    /**
     * @param acsUrl the acsUrl to set
     */
    public void setAcsUrl(String acsUrl) {
        this.acsUrl = acsUrl;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }
}
