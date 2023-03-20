/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.transaction.manager.model;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Order {

    private String order_id;
    private Integer amount;
    private String merchant_id;
    private String customer_first_name;
    private String customer_last_name;
    private String customer_email;
    private String currency_code;
    private String description;
    private String callback_url;
    private String callback_key;

    private String merchant_key;
    private String currency;

    private String transaction_ref;
    private String status_value;
    private String status;
    private String status_description;

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
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return the customer_first_name
     */
    public String getCustomer_first_name() {
        return customer_first_name;
    }

    /**
     * @param customer_first_name the customer_first_name to set
     */
    public void setCustomer_first_name(String customer_first_name) {
        this.customer_first_name = customer_first_name;
    }

    /**
     * @return the customer_last_name
     */
    public String getCustomer_last_name() {
        return customer_last_name;
    }

    /**
     * @param customer_last_name the customer_last_name to set
     */
    public void setCustomer_last_name(String customer_last_name) {
        this.customer_last_name = customer_last_name;
    }

    /**
     * @return the customer_email
     */
    public String getCustomer_email() {
        return customer_email;
    }

    /**
     * @param customer_email the customer_email to set
     */
    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
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
     * @return the merchant_id
     */
    public String getMerchant_id() {
        return merchant_id;
    }

    /**
     * @param merchant_id the merchant_id to set
     */
    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    /**
     * @return the currency_code
     */
    public String getCurrency_code() {
        return currency_code;
    }

    /**
     * @param currency_code the currency_code to set
     */
    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
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
     * @return the status_value
     */
    public String getStatus_value() {
        return status_value;
    }

    /**
     * @param status_value the status_value to set
     */
    public void setStatus_value(String status_value) {
        this.status_value = status_value;
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
     * @return the status_description
     */
    public String getStatus_description() {
        return status_description;
    }

    /**
     * @param status_description the status_description to set
     */
    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

    /**
     * @return the transaction_ref
     */
    public String getTransaction_ref() {
        return transaction_ref;
    }

    /**
     * @param transaction_ref the transaction_ref to set
     */
    public void setTransaction_ref(String transaction_ref) {
        this.transaction_ref = transaction_ref;
    }
    
}
