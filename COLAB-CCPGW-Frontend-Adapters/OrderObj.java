/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.payment.gateway.api.json;

import com.google.gson.Gson;

/**
 *
 * @author mho
 */
public class OrderObj {

    private String order_id;
    private String merchant_key;
    private String order_detail;

    public OrderObj() {
    }

    public OrderObj(String order_id, String merchant_key, String order_detail) {

        this();
        this.order_id = order_id;
        this.merchant_key = merchant_key;
        this.order_detail = order_detail;
    }
    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
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
     * @return the order_detail
     */
    public String getOrder_detail() {
        return order_detail;
    }

    /**
     * @param order_detail the order_detail to set
     */
    public void setOrder_detail(String order_detail) {
        this.order_detail = order_detail;
    }

    
}
