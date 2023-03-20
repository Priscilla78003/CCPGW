/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.payment.gateway.api.json;

import com.google.gson.Gson;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentDetailType;
import java.math.BigDecimal;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PaymentDetails {
    private String name;
    private String number;
    private BigDecimal  amount;
    private String currency;
    private String cvv;
    private String expiryDate;
    private String parequest;
    private String paresponse;
    private String sessionid;
    private String datetime;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String stateProv;
    private String countryCode;
    private String countryNam;
    private String postalCode;
    private String paymentMethodCode;

    public PaymentDetails(){
        
    }
    
    public PaymentDetails(PaymentDetailType paymentType){
            this();
            this.setNumber(paymentType.getPaymentCard().getCardNumber());
            this.setName(paymentType.getPaymentCard().getCardHolderName());
            this.setExpiryDate(paymentType.getPaymentCard().getExpireDate());
            this.setAmount(paymentType.getAmountDetail().getAmount());
            this.setCurrency(paymentType.getAmountDetail().getCurrencyCode());
            this.setCvv(paymentType.getPaymentCard().getCVC());
            this.setAddressLine1(paymentType.getBillingAddress().getAddressLine1());
            this.setAddressLine2(paymentType.getBillingAddress().getAddressLine2());
            this.setCity(paymentType.getBillingAddress().getCityName());
            this.setStateProv(paymentType.getBillingAddress().getStateProv().toString());
            this.setCountryNam(paymentType.getBillingAddress().getCountry().getName());
            this.setCountryCode(paymentType.getBillingAddress().getCountry().getCode());
            this.setPostalCode(paymentType.getBillingAddress().getPostalCode());
            this.setPaymentMethodCode(paymentType.getPaymentMethod().getPaymentMethodCode());
    }
    
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
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
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
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
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
     * @return the stateProv
     */
    public String getStateProv() {
        return stateProv;
    }

    /**
     * @param stateProv the stateProv to set
     */
    public void setStateProv(String stateProv) {
        this.stateProv = stateProv;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the countryNam
     */
    public String getCountryNam() {
        return countryNam;
    }

    /**
     * @param countryNam the countryNam to set
     */
    public void setCountryNam(String countryNam) {
        this.countryNam = countryNam;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the expiryDate
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the paymentMethodCode
     */
    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    /**
     * @param paymentMethodCode the paymentMethodCode to set
     */
    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }
    
}
