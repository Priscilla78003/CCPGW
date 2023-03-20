/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.versioning;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Enrolled {
    
    private String threeDSServerTransID;
    private String enrolled;
    private String acctNumber;
    private String schemeId;
    
    public Enrolled(){
        
    }
    
    public Enrolled(String threeDSServerTransID,
                    String enrolled,
                    String acctNumber,
                    String schemeId){
        this();
        this.threeDSServerTransID = threeDSServerTransID;
        this.enrolled = enrolled;
        this.acctNumber = acctNumber;
        this.schemeId = schemeId;
    }
    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }    

    /**
     * @return the threeDSServerTransID
     */
    public String getThreeDSServerTransID() {
        return threeDSServerTransID;
    }

    /**
     * @param threeDSServerTransID the threeDSServerTransID to set
     */
    public void setThreeDSServerTransID(String threeDSServerTransID) {
        this.threeDSServerTransID = threeDSServerTransID;
    }

    /**
     * @return the enrolled
     */
    public String getEnrolled() {
        return enrolled;
    }

    /**
     * @param enrolled the enrolled to set
     */
    public void setEnrolled(String enrolled) {
        this.enrolled = enrolled;
    }

    /**
     * @return the acctNumber
     */
    public String getAcctNumber() {
        return acctNumber;
    }

    /**
     * @param acctNumber the acctNumber to set
     */
    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    /**
     * @return the schemeId
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * @param schemeId the schemeId to set
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }
    
}
