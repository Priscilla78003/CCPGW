/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.transaction.notification.microservice.threeds.controller;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AuthTransaction {
    private String order_id;
    private String responseCode;
    private String description;
    private String approvalCode;
    private String orderNumber;
    private String transactionID;
    private String avsresultCode;
    private String cvcresultCode;

    public AuthTransaction(){
        
    }
    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the approvalCode
     */
    public String getApprovalCode() {
        return approvalCode;
    }

    /**
     * @param approvalCode the approvalCode to set
     */
    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * @return the avsresultCode
     */
    public String getAvsresultCode() {
        return avsresultCode;
    }

    /**
     * @param avsresultCode the avsresultCode to set
     */
    public void setAvsresultCode(String avsresultCode) {
        this.avsresultCode = avsresultCode;
    }

    /**
     * @return the cvcresultCode
     */
    public String getCvcresultCode() {
        return cvcresultCode;
    }

    /**
     * @param cvcresultCode the cvcresultCode to set
     */
    public void setCvcresultCode(String cvcresultCode) {
        this.cvcresultCode = cvcresultCode;
    }
}
