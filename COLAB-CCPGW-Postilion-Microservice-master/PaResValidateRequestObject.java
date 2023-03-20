/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.objects;

import com.google.gson.Gson;
import com.truteq.ccpgw.netcetera.model.SessionData;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PaResValidateRequestObject {
    
    private String paRes;
    private SessionData sessionData;

    
    public String toJSON(){
        Gson gson =new Gson();
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
     * @return the sessionData
     */
    public SessionData getSessionData() {
        return sessionData;
    }

    /**
     * @param sessionData the sessionData to set
     */
    public void setSessionData(SessionData sessionData) {
        this.sessionData = sessionData;
    }
    
}
    