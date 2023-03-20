/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * ***************************************************************
 */
package com.truteq.ccpgw.model.objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truteq.ccpgw.model.authenticate.authorise.EndUserAuthenticate;
import java.util.Date;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ReactJSEndUserObj {
    private String cif;
    private String shortName;
    private String msisdn;
    private String email;
    private boolean termsconditions;
    private String acceptedon;
    
    
    public ReactJSEndUserObj(){
        
    }
    
    public ReactJSEndUserObj(EndUserAuthenticate enduser){
        this();
        this.cif = enduser.getCif();
        this.shortName = enduser.getShortName();
        this.msisdn = enduser.getMsisdn();
        this.email = enduser.getEmail();
        this.termsconditions = enduser.isTermsconditions();
        this.acceptedon = enduser.getAcceptedon();
    }    
    
    public String toJSON(){
        return this.toJSON("yyyy-MM-dd HH:mm:ss");
    }  

    public String toJSON(String format){
        Gson gson = new GsonBuilder().setDateFormat(format).create();
        return gson.toJson(this);
    }   
    
    /**
     * @return the cif
     */
    public String getCif() {
        return cif;
    }

    /**
     * @param cif the cif to set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return the termsconditions
     */
    public boolean isTermsconditions() {
        return termsconditions;
    }

    /**
     * @param termsconditions the termsconditions to set
     */
    public void setTermsconditions(boolean termsconditions) {
        this.termsconditions = termsconditions;
    }

    /**
     * @return the acceptedon
     */
    public String getAcceptedon() {
        return acceptedon;
    }

    /**
     * @param acceptedon the acceptedon to set
     */
    public void setAcceptedon(String acceptedon) {
        this.acceptedon = acceptedon;
    }

}
