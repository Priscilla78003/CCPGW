/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2023 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.model.authenticate.authorise;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PaResNotify {
    
    private String paRes;
    private String md;    
    
    public PaResNotify(){
         
    }
    
    public PaResNotify(String paRes, String md){
       this();
       this.paRes = paRes;
       this.md = md;
    }
    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @return the paRes
     */
    public String getPaRes() {
        return paRes;
    }

    /**
     * @param paRes the paRes to set
     */
    public void setPaRes(String paRes) {
        this.paRes = paRes;
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
    
}
