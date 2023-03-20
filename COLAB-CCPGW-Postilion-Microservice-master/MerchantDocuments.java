/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * V01.00.01  06-Apr-2021 Mya Htike Oo
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.merchant.portal.model;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class MerchantDocuments {
    private long id;
    private String document1;
    private String document2;
    private String document3;
    private String document1_1;
    private String document2_1;
    private String document3_1;
    private String document3_2;    
    private String document4; 
    private String document_signature; // Mya 06-Apr-2021 document_signature new field and getters and setters

    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
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
