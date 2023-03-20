/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.objects;

import com.google.gson.Gson;
import com.truteq.ccpgw.netcetera.model.ErrorDetails;
import com.truteq.ccpgw.netcetera.model.ThreeDSecureType;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PaResValidateResponseObject {
    
     private ThreeDSecureType threeDSecurePARes;
     private ErrorDetails error;   

    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }        

    /**
     * @return the threeDSecurePARes
     */
    public ThreeDSecureType getThreeDSecurePARes() {
        return threeDSecurePARes;
    }

    /**
     * @param threeDSecurePARes the threeDSecurePARes to set
     */
    public void setThreeDSecurePARes(ThreeDSecureType threeDSecurePARes) {
        this.threeDSecurePARes = threeDSecurePARes;
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
