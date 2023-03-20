/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.merchant.portal.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Result {
    private int status;
    private String comments;
    private String data;

    public Result(int status, String comments, String data){
        this.data = data;
        this.status = status; 
        this.comments = comments;
    }
          
    
    public Result(int status, String comments){
       this(status,comments,"");
    }
        
    
    public Result(int status){
       this(status,"");
    }
    
    public String toJSON(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }      
    
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }
    
    
}
