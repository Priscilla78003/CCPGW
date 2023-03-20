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
package com.truteq.ccpgw.model.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AdapterHandlerObj {
    
    private String type;
    private String name;
    private String namespace;
    private String classname;
    private String transaction_type; 
    private String transaction_namespace;

    public AdapterHandlerObj(){
        
    }
    
    public AdapterHandlerObj(String type,
                             String name,
                             String namespace,
                             String classname,
                             String transaction_type,
                             String transaction_namespace){
        this();
        this.type = type;
        this.name = name;
        this.namespace = namespace;
        this.classname = classname;
        this.transaction_type = transaction_type;
        this.transaction_namespace = transaction_namespace;         
    }
    
    public String toJSON(){
        return this.toJSON("yyyy-MM-dd HH:mm:ss");
    }  

    public String toJSON(String format){
        Gson gson = new GsonBuilder().setDateFormat(format).create();
        return gson.toJson(this);
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
     * @return the namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * @param namespace the namespace to set
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * @return the classname
     */
    public String getClassname() {
        return classname;
    }

    /**
     * @param classname the classname to set
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * @return the transaction_type
     */
    public String getTransaction_type() {
        return transaction_type;
    }

    /**
     * @param transaction_type the transaction_type to set
     */
    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    /**
     * @return the transaction_namespace
     */
    public String getTransaction_namespace() {
        return transaction_namespace;
    }

    /**
     * @param transaction_namespace the transaction_namespace to set
     */
    public void setTransaction_namespace(String transaction_namespace) {
        this.transaction_namespace = transaction_namespace;
    }
}
