/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * V01.00.01  06-Apr-2020 Mya Htike Oo
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.merchant.portal.model;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ReactJSMerchantObj {

   private String businessName;
   private String cardAcceptorName;
   private String businessNumber;
   private String businessPhoneNumber;
   private String address1;
   private String address2;
   private String city;
   private String state;
   private String postalcode;
   private String country;
   private String non3D;
   private String currency;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;
   private String salutation;
   private String dateofbirth;
   private String refund;
   private String role;
   private String password;
   private String confirmpassword;
   private String totp;
   private String secretkey;
   private String notifEmail;
   private String notifURL;
   private String failURL;
   private String cancelURL;
   private String successURL;
   private String integrate;
   private String marketcomms;
   private String document1;
   private String document2;
   private String document3;
   private String document1_1;
   private String document2_1;
   private String document3_1;
   private String document3_2;    
   private String document4;    
   private String document_signature; // Mya 06-Apr-2021 document_signature new field and getters and setters

    /**
     * @return the businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName the businessName to set
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the cardAcceptorName
     */
    public String getCardAcceptorName() {
        return cardAcceptorName;
    }

    /**
     * @param cardAcceptorName the cardAcceptorName to set
     */
    public void setCardAcceptorName(String cardAcceptorName) {
        this.cardAcceptorName = cardAcceptorName;
    }

    /**
     * @return the businessNumber
     */
    public String getBusinessNumber() {
        return businessNumber;
    }

    /**
     * @param businessNumber the businessNumber to set
     */
    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    /**
     * @return the businessPhoneNumber
     */
    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    /**
     * @param businessPhoneNumber the businessPhoneNumber to set
     */
    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
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
     * @return the postalcode
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * @param postalcode the postalcode to set
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
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
     * @return the non3D
     */
    public String getNon3D() {
        return non3D;
    }

    /**
     * @param non3D the non3D to set
     */
    public void setNon3D(String non3D) {
        this.non3D = non3D;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the salutation
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * @param salutation the salutation to set
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    /**
     * @return the refund
     */
    public String getRefund() {
        return refund;
    }

    /**
     * @param refund the refund to set
     */
    public void setRefund(String refund) {
        this.refund = refund;
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
     * @return the confirmpassword
     */
    public String getConfirmpassword() {
        return confirmpassword;
    }

    /**
     * @param confirmpassword the confirmpassword to set
     */
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    /**
     * @return the totp
     */
    public String getTotp() {
        return totp;
    }

    /**
     * @param totp the totp to set
     */
    public void setTotp(String totp) {
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
     * @return the notifEmail
     */
    public String getNotifEmail() {
        return notifEmail;
    }

    /**
     * @param notifEmail the notifEmail to set
     */
    public void setNotifEmail(String notifEmail) {
        this.notifEmail = notifEmail;
    }

    /**
     * @return the notifURL
     */
    public String getNotifURL() {
        return notifURL;
    }

    /**
     * @param notifURL the notifURL to set
     */
    public void setNotifURL(String notifURL) {
        this.notifURL = notifURL;
    }

    /**
     * @return the failURL
     */
    public String getFailURL() {
        return failURL;
    }

    /**
     * @param failURL the failURL to set
     */
    public void setFailURL(String failURL) {
        this.failURL = failURL;
    }

    /**
     * @return the cancelURL
     */
    public String getCancelURL() {
        return cancelURL;
    }

    /**
     * @param cancelURL the cancelURL to set
     */
    public void setCancelURL(String cancelURL) {
        this.cancelURL = cancelURL;
    }

    /**
     * @return the successURL
     */
    public String getSuccessURL() {
        return successURL;
    }

    /**
     * @param successURL the successURL to set
     */
    public void setSuccessURL(String successURL) {
        this.successURL = successURL;
    }

    /**
     * @return the integrate
     */
    public String getIntegrate() {
        return integrate;
    }

    /**
     * @param integrate the integrate to set
     */
    public void setIntegrate(String integrate) {
        this.integrate = integrate;
    }

    /**
     * @return the marketcomms
     */
    public String getMarketcomms() {
        return marketcomms;
    }

    /**
     * @param marketcomms the marketcomms to set
     */
    public void setMarketcomms(String marketcomms) {
        this.marketcomms = marketcomms;
    }

    /**
     * @return the document1
     */
    public String getDocument1() {
        return document1;
    }

    /**
     * @param document1 the document1 to set
     */
    public void setDocument1(String document1) {
        this.document1 = document1;
    }

    /**
     * @return the document2
     */
    public String getDocument2() {
        return document2;
    }

    /**
     * @param document2 the document2 to set
     */
    public void setDocument2(String document2) {
        this.document2 = document2;
    }

    /**
     * @return the document3
     */
    public String getDocument3() {
        return document3;
    }

    /**
     * @param document3 the document3 to set
     */
    public void setDocument3(String document3) {
        this.document3 = document3;
    }

    /**
     * @return the dateofbirth
     */
    public String getDateofbirth() {
        return dateofbirth;
    }

    /**
     * @param dateofbirth the dateofbirth to set
     */
    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    /**
     * @return the document1_1
     */
    public String getDocument1_1() {
        return document1_1;
    }

    /**
     * @param document1_1 the document1_1 to set
     */
    public void setDocument1_1(String document1_1) {
        this.document1_1 = document1_1;
    }

    /**
     * @return the document2_1
     */
    public String getDocument2_1() {
        return document2_1;
    }

    /**
     * @param document2_1 the document2_1 to set
     */
    public void setDocument2_1(String document2_1) {
        this.document2_1 = document2_1;
    }

    /**
     * @return the document3_1
     */
    public String getDocument3_1() {
        return document3_1;
    }

    /**
     * @param document3_1 the document3_1 to set
     */
    public void setDocument3_1(String document3_1) {
        this.document3_1 = document3_1;
    }

    /**
     * @return the document3_2
     */
    public String getDocument3_2() {
        return document3_2;
    }

    /**
     * @param document3_2 the document3_2 to set
     */
    public void setDocument3_2(String document3_2) {
        this.document3_2 = document3_2;
    }

    /**
     * @return the document4
     */
    public String getDocument4() {
        return document4;
    }

    /**
     * @param document4 the document4 to set
     */
    public void setDocument4(String document4) {
        this.document4 = document4;
    }

    /**
     * @return the document_signature
     */
    public String getDocument_signature() {
        return document_signature;
    }

    /**
     * @param document_signature the document_signature to set
     */
    public void setDocument_signature(String document_signature) {
        this.document_signature = document_signature;
    }
    
    
    
}