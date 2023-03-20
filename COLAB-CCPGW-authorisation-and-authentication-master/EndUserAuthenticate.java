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

import java.util.Date;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class EndUserAuthenticate {
    private String cif;
    private String pin;
    private String shortName;
    private String msisdn;
    private String email;
    private boolean termsconditions;
    private String acceptedon;    
    private String username;
    private String password;
    private String key;

    public EndUserAuthenticate(){
        
    }   
    
    public EndUserAuthenticate(String cif,
                               String pin,
                               String shortName,
                               String msisdn){
        this();
        this.cif = cif;
        this.pin = pin;
        this.shortName = shortName;
        this.msisdn = msisdn;

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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
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
