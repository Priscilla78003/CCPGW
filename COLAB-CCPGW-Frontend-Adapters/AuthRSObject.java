/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.payment.gateway.model;

import com.google.gson.Gson;
import com.truteq.ccpgw.payment.gateway.api.soap.AuthRS;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AuthRSObject {
    private AuthRS authrs;
    private String enrollment;
    
    public AuthRSObject(){
    
    }

    public AuthRSObject(AuthRS authrs,String enrollment){
       this();
       this.authrs = authrs;
       this.enrollment = enrollment;
    }    
      
    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    /**
     * @return the authrs
     */
    public AuthRS getAuthrs() {
        return authrs;
    }

    /**
     * @param authrs the authrs to set
     */
    public void setAuthrs(AuthRS authrs) {
        this.authrs = authrs;
    }

    /**
     * @return the enrollment
     */
    public String getEnrollment() {
        return enrollment;
    }

    /**
     * @param enrollment the enrollment to set
     */
    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    
}
