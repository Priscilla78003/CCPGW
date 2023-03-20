/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.adapter.postilion.requests.objects;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class RepeatUpdateObj {
    
    private String mti;
    private String stan;
    
    public RepeatUpdateObj(){
        
    }
    
    public RepeatUpdateObj(String mti, String stan){
        this();
        this.mti = mti;
        this.stan = stan;
    }

    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }     
    
    /**
     * @return the mti
     */
    public String getMti() {
        return mti;
    }

    /**
     * @param mti the mti to set
     */
    public void setMti(String mti) {
        this.mti = mti;
    }

    /**
     * @return the stan
     */
    public String getStan() {
        return stan;
    }

    /**
     * @param stan the stan to set
     */
    public void setStan(String stan) {
        this.stan = stan;
    }
        
}
