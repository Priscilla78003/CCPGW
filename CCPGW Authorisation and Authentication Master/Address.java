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

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Address {
    private long id; 
    private String line1;
    private String line2;
    private String line3;
    private int postcode;
    private String state;
    private String city;
    private String countrycode;
    private long customer_id;    

    public Address(){
    }
    
    public Address(String line1, String line2,String line3,int postcode,String state,String city,String countrycode){
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.postcode = postcode;
        this.state = state;
        this.city = city;
        this.countrycode = countrycode;
    }
    
    /**
     * @return the line1
     */
    public String getLine1() {
        return line1;
    }

    /**
     * @param line1 the line1 to set
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * @return the line2
     */
    public String getLine2() {
        return line2;
    }

    /**
     * @param line2 the line2 to set
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * @return the line3
     */
    public String getLine3() {
        return line3;
    }

    /**
     * @param line3 the line3 to set
     */
    public void setLine3(String line3) {
        this.line3 = line3;
    }

    /**
     * @return the postcode
     */
    public int getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(int postcode) {
        this.postcode = postcode;
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
     * @return the countrycode
     */
    public String getCountrycode() {
        return countrycode;
    }

    /**
     * @param countrycode the countrycode to set
     */
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
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
}
