/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.model.authenticate.authorise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author mho
 */
public class EncryptedCard {

    private String order_id;
    private String guid;
    private String publickey;
    private String payload;
    private String secretkey;

    public EncryptedCard() {
    }

    public EncryptedCard(String guid,
            String order_id,
            String publickey,
            String payload,
            String secretkey) {
        this();
        this.guid = guid;
        this.order_id = order_id;
        this.publickey = publickey;
        this.payload = payload;
        this.secretkey = secretkey;
        
    }
    
        public String toJSON(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    } 

    /**
     * @return the order_id
     */
    public String getOrder_id() {
        return order_id;
    }

    /**
     * @param order_id the order_id to set
     */
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return the publickey
     */
    public String getPublickey() {
        return publickey;
    }

    /**
     * @param publickey the publickey to set
     */
    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    /**
     * @return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * @return the secretkey
     */
    public String getSecretkey() {
        return secretkey;
    }

    /**
     * @param secretkey the secretkey to set
     */
    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }
        
        
}
