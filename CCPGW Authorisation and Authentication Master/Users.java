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

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Users {
    private long id;
    private String guid;
    private String username;
    private String password;
    private String sessionid;
    private String secretkey;
    private String role;
    private boolean totp;
    private boolean marketcomms;
    private long customer_id;  

    public Users(){
        
    }
    
    public Users(String username,
                String password,
                String secretkey,
                String role,
                boolean totp,
                boolean marketcomms){
            this.username = username;
            this.password = password;
            this.secretkey = secretkey;
            this.role = role;
            this.totp =totp;
            this.marketcomms = marketcomms;
    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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

    /**
     * @return the customer_id
     */
    public long getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @return the marketcomms
     */
    public boolean isMarketcomms() {
        return marketcomms;
    }

    /**
     * @param marketcomms the marketcomms to set
     */
    public void setMarketcomms(boolean marketcomms) {
        this.marketcomms = marketcomms;
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
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the totp
     */
    public boolean isTotp() {
        return totp;
    }

    /**
     * @param totp the totp to set
     */
    public void setTotp(boolean totp) {
        this.totp = totp;
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
}
