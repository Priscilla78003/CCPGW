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
public class ThreeDSV2ServerVersioningRequest {

    /**
     * Universally unique transaction identifier to identify a single
     * transaction. If the value does not exist or it is not a valid UUID, the
     * 3DS Server will generate a new transaction ID. This value has 36
     * characters in a format defined in IETF RFC 4122.
     */
    private String threeDSServerTransID;
    /**
     * The cardholder account number. Mandatory field.
     */
    private String cardholderAccountNumber;
    /**
     * The id of the scheme. Optional field. If not sent, the id of the scheme
     * will be resolved from the cardholder account number.
     */
    private String schemeId;

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
     * @return the cardholderAccountNumber
     */
    public String getCardholderAccountNumber() {
        return cardholderAccountNumber;
    }

    /**
     * @param cardholderAccountNumber the cardholderAccountNumber to set
     */
    public void setCardholderAccountNumber(String cardholderAccountNumber) {
        this.cardholderAccountNumber = cardholderAccountNumber;
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
