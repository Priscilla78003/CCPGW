/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.objects;

import com.google.gson.Gson;
import com.truteq.ccpgw.netcetera.model.ErrorDetails;
import com.truteq.ccpgw.netcetera.model.SessionData;
import com.truteq.ccpgw.netcetera.model.ThreeDSecureType;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PaReqResponseObject {

    private String sessionId;
    private SessionData sessionData;
    private String paReq;
    private ThreeDSecureType threeDSecureVEReq;
    private ThreeDSecureType threeDSecureVERes;
    private ErrorDetails error;  
    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }    

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    /**
     * @return the paReq
     */
    public String getPaReq() {
        return paReq;
    }

    /**
     * @param paReq the paReq to set
     */
    public void setPaReq(String paReq) {
        this.paReq = paReq;
    }

    /**
     * @return the threeDSecureVEReq
     */
    public ThreeDSecureType getThreeDSecureVEReq() {
        return threeDSecureVEReq;
    }

    /**
     * @param threeDSecureVEReq the threeDSecureVEReq to set
     */
    public void setThreeDSecureVEReq(ThreeDSecureType threeDSecureVEReq) {
        this.threeDSecureVEReq = threeDSecureVEReq;
    }

    /**
     * @return the threeDSecureVERes
     */
    public ThreeDSecureType getThreeDSecureVERes() {
        return threeDSecureVERes;
    }

    /**
     * @param threeDSecureVERes the threeDSecureVERes to set
     */
    public void setThreeDSecureVERes(ThreeDSecureType threeDSecureVERes) {
        this.threeDSecureVERes = threeDSecureVERes;
    }

    /**
     * @return the error
     */
    public ErrorDetails getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(ErrorDetails error) {
        this.error = error;
    }
}
