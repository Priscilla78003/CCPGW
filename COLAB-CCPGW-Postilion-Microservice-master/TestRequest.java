/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.postilion.rest.microserv.transaction.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestRequest implements Serializable{
    private String name;
    private int delay;

    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }       
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }    
}
