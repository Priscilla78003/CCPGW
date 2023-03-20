/*
 * ***************************************************************
 * Truteq CAMEL Diameter Gateway version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 
 * ***************************************************************
 *  CAP-Gw
 *  SS7 CAP component for Truteq CAMEL Diameter Gateway project 
 *  Support: Grant O'Reilly gbo@truteq.com
 *  V01.00.00  11-Sep-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.comms.server.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class CommsServerMessage implements Serializable{
    
    private String state;
    private String type;
    private Object messageObj;

    public CommsServerMessage(String state, String type, Object messageObj){
        this.state = state;
        this.type = type;
        this.messageObj = messageObj;
    }
    
    public String toJson(){
       return (new Gson()).toJson(this); 
    }    
        
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the messageObj
     */
    public Object getMessageObj() {
        return messageObj;
    }

    /**
     * @param messageObj the messageObj to set
     */
    public void setMessageObj(Object messageObj) {
        this.messageObj = messageObj;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }
    
}