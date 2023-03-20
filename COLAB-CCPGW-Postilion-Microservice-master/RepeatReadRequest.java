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
public class RepeatReadRequest {
    private Integer value;
    
    public RepeatReadRequest(){
        
    }
    
    public RepeatReadRequest(Integer value){
        this();
        this.value = value;
    }    
    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }    

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }
}
