/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  03-Feb-2020 
 * V01.00.01  07-Apr-2021 Mya Htike Oo - Merchant Fees
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.merchant.portal.model;

import java.sql.Timestamp;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class BankAccount {
    
    
   private long id; 
   private String name;
   private String cif;
   private String number;
   private Timestamp creation_date;
   private int bank_id;
   private int type_id;
   private int customer_id;   	
   private String type;	
   private String toAccountDescription;

   public BankAccount(){}
   
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
     * @return the creation_date
     */
    public Timestamp getCreation_date() {
        return creation_date;
    }

    /**
     * @param creation_date the creation_date to set
     */
    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    /**
     * @return the bank_id
     */
    public int getBank_id() {
        return bank_id;
    }

    /**
     * @param bank_id the bank_id to set
     */
    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    /**
     * @return the type_id
     */
    public int getType_id() {
        return type_id;
    }

    /**
     * @param type_id the type_id to set
     */
    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    /**
     * @return the customer_id
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
   
   	
    /**	
     * @return the type	
     */	
    public String getType() {	
        return type;	
    }	
    /**	
     * @param type the type to set	
     */	
    public void setType(String type) {	
        this.type = type;	
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
     * @return the toAccountDescription	
     */	
    public String getToAccountDescription() {	
        return toAccountDescription;	
    }	
    /**	
     * @param toAccountDescription the toAccountDescription to set	
     */	
    public void setToAccountDescription(String toAccountDescription) {	
        this.toAccountDescription = toAccountDescription;	
    }	
    
}
