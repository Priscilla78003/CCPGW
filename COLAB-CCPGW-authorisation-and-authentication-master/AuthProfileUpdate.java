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

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AuthProfileUpdate {
   private String username;
   private String key; 
   private Integer merchantId;
   private Boolean marketcomms; 
   private Boolean totp;
   private String secretkey;
   private String name;
   private String last_name;
   private String email;
   private String phone_number;
   private Date dob;
   private String streetNumber;
   private String streetNameline1;
   private String streetNameline2;
   private String state;
   private String city;
   private String country;     

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
     * @return the marketcomms
     */
    public Boolean getMarketcomms() {
        return marketcomms;
    }

    /**
     * @param marketcomms the marketcomms to set
     */
    public void setMarketcomms(Boolean marketcomms) {
        this.marketcomms = marketcomms;
    }

    /**
     * @return the totp
     */
    public Boolean getTotp() {
        return totp;
    }

    /**
     * @param totp the totp to set
     */
    public void setTotp(Boolean totp) {
        this.totp = totp;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
     * @return the phone_number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * @param phone_number the phone_number to set
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the streetNumber
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * @param streetNumber the streetNumber to set
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * @return the streetNameline1
     */
    public String getStreetNameline1() {
        return streetNameline1;
    }

    /**
     * @param streetNameline1 the streetNameline1 to set
     */
    public void setStreetNameline1(String streetNameline1) {
        this.streetNameline1 = streetNameline1;
    }

    /**
     * @return the streetNameline2
     */
    public String getStreetNameline2() {
        return streetNameline2;
    }

    /**
     * @param streetNameline2 the streetNameline2 to set
     */
    public void setStreetNameline2(String streetNameline2) {
        this.streetNameline2 = streetNameline2;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
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
     * @return the merchantId
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
}
