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
package com.truteq.ccpgw.transaction.manager.merchant.portal.model;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ReactJSBankAccountObj extends BankAccount {
    
   private String bankname;
   private String typename; 
   private String currname; 
   private String abbrev;
   private String country;
   private String username;
    
    public ReactJSBankAccountObj() {}
    

    /**
     * @return the bankname
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * @param bankname the bankname to set
     */
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    /**
     * @return the typename
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename the typename to set
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * @return the currname
     */
    public String getCurrname() {
        return currname;
    }

    /**
     * @param currname the currname to set
     */
    public void setCurrname(String currname) {
        this.currname = currname;
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
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the abbrev
     */
    public String getAbbrev() {
        return abbrev;
    }

    /**
     * @param abbrev the abbrev to set
     */
    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }
    
    
    
}
