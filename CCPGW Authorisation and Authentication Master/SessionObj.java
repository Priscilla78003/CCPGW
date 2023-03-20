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
package com.truteq.ccpgw.model.authenticate.authorise;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class SessionObj {
    
    //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String cif;
    private String sessionid;
    private String datetimestring;
    
    public SessionObj(){
        
    }
    
    public SessionObj(String cif){
        this();
        this.cif = cif;
    }

    public SessionObj(String cif, String sessionid){
        this(cif);
        this.sessionid = sessionid;
    }    
    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }       
    
//    public Date getDate() throws ParseException{
//        return formatter.parse(this.datetimestring);
//    }
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
     * @return the datetimestring
     */
    public String getDatetimestring() {
        return datetimestring;
    }

    /**
     * @param datetimestring the datetimestring to set
     */
    public void setDatetimestring(String datetimestring) {
        this.datetimestring = datetimestring;
    }
    
}
