/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.model.authenticate.authorise;

import com.google.gson.Gson;
/**
 *
 * @author mho
 */
public class TempCard {

    private String name;
    private String number;
    private String cvv;
    private String expiry_month;
    private String expiry_year;
    private String parequest;
    private String paresponse;
    private String sessionid;
    private String order_id;
    private String merchant_key;
    private String datetime;
    private String createdate;
    private String callback_key;
    private String callback_url;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private String country;
    private String postal_code;
    private String payment_method_code;

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
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
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the cvv
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the expiry_month
     */
    public String getExpiry_month() {
        return expiry_month;
    }

    /**
     * @param expiry_month the expiry_month to set
     */
    public void setExpiry_month(String expiry_month) {
        this.expiry_month = expiry_month;
    }

    /**
     * @return the expiry_year
     */
    public String getExpiry_year() {
        return expiry_year;
    }

    /**
     * @param expiry_year the expiry_year to set
     */
    public void setExpiry_year(String expiry_year) {
        this.expiry_year = expiry_year;
    }

    /**
     * @return the parequest
     */
    public String getParequest() {
        return parequest;
    }

    /**
     * @param parequest the parequest to set
     */
    public void setParequest(String parequest) {
        this.parequest = parequest;
    }

    /**
     * @return the paresponse
     */
    public String getParesponse() {
        return paresponse;
    }

    /**
     * @param paresponse the paresponse to set
     */
    public void setParesponse(String paresponse) {
        this.paresponse = paresponse;
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
     * @return the merchant_key
     */
    public String getMerchant_key() {
        return merchant_key;
    }

    /**
     * @param merchant_key the merchant_key to set
     */
    public void setMerchant_key(String merchant_key) {
        this.merchant_key = merchant_key;
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
     * @return the createdate
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate the createdate to set
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    /**
     * @return the callback_key
     */
    public String getCallback_key() {
        return callback_key;
    }

    /**
     * @param callback_key the callback_key to set
     */
    public void setCallback_key(String callback_key) {
        this.callback_key = callback_key;
    }

    /**
     * @return the callback_url
     */
    public String getCallback_url() {
        return callback_url;
    }

    /**
     * @param callback_url the callback_url to set
     */
    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    /**
     * @return the addressline1
     */
    public String getAddressline1() {
        return addressline1;
    }

    /**
     * @param addressline1 the addressline1 to set
     */
    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    /**
     * @return the addressline2
     */
    public String getAddressline2() {
        return addressline2;
    }

    /**
     * @param addressline2 the addressline2 to set
     */
    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
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
     * @return the postal_code
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * @param postal_code the postal_code to set
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
    
    /**
     * @return the payment_method_code
     */
    public String getPayment_method_code() {
        return payment_method_code;
    }

    /**
     * @param payment_method_code the payment_method_code to set
     */
    public void setPayment_method_code(String payment_method_code) {
        this.payment_method_code = payment_method_code;
    }    
}
