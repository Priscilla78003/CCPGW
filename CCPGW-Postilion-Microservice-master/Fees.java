/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.merchant.portal.model;

/**
 *
 * @author mho
 */
public class Fees {
    private long id;
    private String name;
    private String description;
    private String feetype;
    private int feeCostPerTransaction;
    private int feePercentOfTransaction;
    private int max_fee;
    private int min_fee;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the feetype
     */
    public String getFeetype() {
        return feetype;
    }

    /**
     * @param feetype the feetype to set
     */
    public void setFeetype(String feetype) {
        this.feetype = feetype;
    }

    /**
     * @return the feeCostPerTransaction
     */
    public int getFeeCostPerTransaction() {
        return feeCostPerTransaction;
    }

    /**
     * @param feeCostPerTransaction the feeCostPerTransaction to set
     */
    public void setFeeCostPerTransaction(int feeCostPerTransaction) {
        this.feeCostPerTransaction = feeCostPerTransaction;
    }

    /**
     * @return the feePercentOfTransaction
     */
    public int getFeePercentOfTransaction() {
        return feePercentOfTransaction;
    }

    /**
     * @param feePercentOfTransaction the feePercentOfTransaction to set
     */
    public void setFeePercentOfTransaction(int feePercentOfTransaction) {
        this.feePercentOfTransaction = feePercentOfTransaction;
    }

    /**
     * @return the max_fee
     */
    public int getMax_fee() {
        return max_fee;
    }

    /**
     * @param max_fee the max_fee to set
     */
    public void setMax_fee(int max_fee) {
        this.max_fee = max_fee;
    }

    /**
     * @return the min_fee
     */
    public int getMin_fee() {
        return min_fee;
    }

    /**
     * @param min_fee the min_fee to set
     */
    public void setMin_fee(int min_fee) {
        this.min_fee = min_fee;
    }
}
