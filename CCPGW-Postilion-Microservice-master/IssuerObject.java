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
public class IssuerObject {
    
    private String md;   
    private String pareq;
    private String termUrl;
    
    public IssuerObject(){
    }
    
    public IssuerObject(String md, String pareq, String termUrl){
        this();
        this.md = md;
        this.pareq = pareq;
        this.termUrl = termUrl;
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }    
    
    /**
     * @return the md
     */
    public String getMd() {
        return md;
    }

    /**
     * @param md the md to set
     */
    public void setMd(String md) {
        this.md = md;
    }

    /**
     * @return the pareq
     */
    public String getPareq() {
        return pareq;
    }

    /**
     * @param pareq the pareq to set
     */
    public void setPareq(String pareq) {
        this.pareq = pareq;
    }

    /**
     * @return the termUrl
     */
    public String getTermUrl() {
        return termUrl;
    }

    /**
     * @param termUrl the termUrl to set
     */
    public void setTermUrl(String termUrl) {
        this.termUrl = termUrl;
    }
}
