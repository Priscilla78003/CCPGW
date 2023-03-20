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

import com.truteq.ccpgw.model.authenticate.authorise.EndUserAuthenticate;
import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ReactJSPinObj {
    private Result result;
    private String cif;
    private String pin;
    private String shortname;
    private String msisdn;
    private String sessionid;
    private String datetime;

    public ReactJSPinObj(){
        
    }
    
    public ReactJSPinObj(Result result){
        this();
        this.result = result;
    }     
    
    public ReactJSPinObj(String cif, String pin){
        this();
        this.cif = cif;
        this.pin = pin;
    }     
    
    public ReactJSPinObj(String cif, String pin, String sessionid){
        this(cif,pin);
        this.sessionid = sessionid;
    }   
    
    public ReactJSPinObj(EndUserAuthenticate endUser){
        this(endUser.getCif(), endUser.getPin());
    }
    
    public ReactJSPinObj(EndUserAuthenticate endUser, String sessionid){
        this(endUser.getCif(), endUser.getPin(), sessionid);
    }    
    
    public String toJSON(){
        Gson gson =new Gson();
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
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @return the shortname
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * @param shortname the shortname to set
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
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
     * @return the sessionid
     */
    public String getSessionid() {
        return sessionid;
    }

    /**
     * @param sessionid the sessionid to set
     */
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }
}
