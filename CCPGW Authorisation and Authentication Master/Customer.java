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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Customer {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date dateofbirth;
    private String status;
    private String salutation;
    private double balance;
    
    private long ref_user_id; 
    private long ref_address_id; 
    
    private Users user;
    private Address address;  
    
    
//    private String pattern = "yyyy-MM-dd";
//    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getPattern());
    /**
     {
     "name" : "Grant"
     "lastName" : "O'Reilly"
     "email": "gbo@truteq.com"
     "phoneNumber": 426892715
     "status": "active"
     "balance":10000
     "card_id" : 1
     "address_id":1
     "store_id":1 
     }
     * 
          {
     "name" : "Francois",
     "lastName" : "Joubert",
     "email": "fj@truteq.com",
     "phoneNumber": 123456789,
     "status": "active",
     "balance":10,
     "card_id" : 1,
     "address_id":1,
     "store_id":1 
     }
     
     */
    
    public Customer(){    
    }
    
    public Customer(ReactJSCustomerObj reactjsCustomer){
        this.name = reactjsCustomer.getFirstName();
        this.lastName = reactjsCustomer.getLastName();
        this.email = reactjsCustomer.getEmail();
        this.phoneNumber = reactjsCustomer.getPhonenumber();
        this.salutation = reactjsCustomer.getSalutation();
        try {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            this.dateofbirth =  simpleDateFormat.parse(reactjsCustomer.getDateofbirth());
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.balance = 0;
        this.status = "active";
        boolean  marketcomms = reactjsCustomer.getMarketcomms().equals("true") ? true : false;
        boolean  totp = reactjsCustomer.getTotp().equals("true") ? true : false;
 
        this.user = new Users(reactjsCustomer.getEmail(),
                              reactjsCustomer.getPassword(),
                              reactjsCustomer.getSecretkey(),
                              reactjsCustomer.getRole(),
                              totp,
                              marketcomms
                              );
        
        this.address = new Address(reactjsCustomer.getAddress1(),
                                   reactjsCustomer.getAddress2(),
                                   "",
                                   Integer.parseInt(reactjsCustomer.getPostalcode()),
                                   reactjsCustomer.getState(),
                                   reactjsCustomer.getCity(),
                                   reactjsCustomer.getCountry());
    }
    
    public Customer(
                String name,
                String lastName,
                String email,
                String phoneNumber,
                Date dateofbirth,
                String status,
                double balance,
                Users user,
                Address address
                ){
        this();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateofbirth = dateofbirth;
        this.status = status;
        this.balance = balance;
        this.address = address;
        this.user = user; 
    }    
    
    public String toJSON(){
        return this.toJSON("yyyy-MM-dd HH:mm:ss");
    }  

    public String toJSON(String format){
        Gson gson = new GsonBuilder().setDateFormat(format).create();
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
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
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
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
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * @return the dateofbirth
     */
    public Date getDateofbirth() {
        return dateofbirth;
    }

    /**
     * @param dateofbirth the dateofbirth to set
     */
    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

//    /**
//     * @return the pattern
//     */
//    public String getPattern() {
//        return pattern;
//    }
//
//    /**
//     * @param pattern the pattern to set
//     */
//    public void setPattern(String pattern) {
//        this.pattern = pattern;
//    }

    /**
     * @return the ref_user_id
     */
    public long getRef_user_id() {
        return ref_user_id;
    }

    /**
     * @param ref_user_id the ref_user_id to set
     */
    public void setRef_user_id(long ref_user_id) {
        this.ref_user_id = ref_user_id;
    }

    /**
     * @return the ref_address_id
     */
    public long getRef_address_id() {
        return ref_address_id;
    }

    /**
     * @param ref_address_id the ref_address_id to set
     */
    public void setRef_address_id(long ref_address_id) {
        this.ref_address_id = ref_address_id;
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


}
