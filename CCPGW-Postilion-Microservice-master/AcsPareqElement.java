/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.transaction.manager.model;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AcsPareqElement {
    
    private String acsurl;
    private String pareq;
    
    public AcsPareqElement(){
        
    }
    
    public AcsPareqElement(String acsurl,
                           String pareq){
      this();
      this.acsurl = acsurl;
      this.pareq = pareq;
    } 

    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
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
     * @return the acsurl
     */
    public String getAcsurl() {
        return acsurl;
    }

    /**
     * @param acsurl the acsurl to set
     */
    public void setAcsurl(String acsurl) {
        this.acsurl = acsurl;
    }
    
    
}
