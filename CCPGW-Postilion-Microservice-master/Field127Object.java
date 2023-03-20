/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Postilion Adapter: POSTILION - Transaction Manager Adapter 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.adapter.postilion.requests.objects;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Field127Object {
    
    private String messageLengthIndicator;   //DE127
    
    private byte [] bitmap;                   //DE127.1
    
    private String switchKey;                //DE127.2
     
    private String routingInfo;              //DE127.3
            
    private String posData;                  //DE127.4
    
    private String cvv2;                     //DE127.10  
           
    private String additionalNodeData;       //DE127.9
    
    private String originalKey;              //DE127.11
            
    private String terminalOwner;            //DE127.12
            
    private String posGeoData;               //DE127.13
            
    private String sponsorBank;              //DE127.14
    
    private String addressVerificationData;  //DE127.15
            
    private String authoriserDataSettlement; //DE127.20
    
    private String structuredData;           //DE127.22
    
    private String originalNode;             //DE127.26
    
    private String cardVerificationData;     //DE127.27
    
    private String americanExpressCardIdentifier;//DE127.28
    
    private byte [] threeDSecureData;        //DE127.29
    
    private String threeDSecureResult;       //DE127.30 
    
    private byte [] ucafData;                 //DE127.32      
               
    private Field127Subfield22Object field22;
            
    public Field127Object(String messageLengthIndicator,  
                    byte [] bitmap,  
                    String cvv2,
                    String avd,
                    String cvd,
                    String AmericanExpressCardIdentifier,  
                    byte [] threeDSecureData,  
                    String threeDSecureResult,  
                    byte [] ucafData,
                    String structuredData){
        
        this.messageLengthIndicator = messageLengthIndicator;    //DE127
        this.bitmap = bitmap;                                    //DE127.1
        this.cvv2 = cvv2;                                        //DE127.10
        this.addressVerificationData = avd;                      //DE127.15
        this.cardVerificationData = cvd;                         //DE127.27
        this.americanExpressCardIdentifier = AmericanExpressCardIdentifier;//DE127.28
        this.threeDSecureData = threeDSecureData;                //DE127.29
        this.threeDSecureResult = threeDSecureResult;            //DE127.30 
        this.ucafData = ucafData;                                //DE127.32
        this.structuredData = structuredData;                    //DE127.22 
        
    }

//    public Field127Object(String messageLengthIndicator,  
//                    byte [] bitmap,  
//                    String cvv2,
//                    String avd,
//                    String cvd,
//                    String AmericanExpressCardIdentifier,  
//                    byte [] threeDSecureData,  
//                    String threeDSecureResult,  
//                    byte [] ucafData,
//                    Field127Subfield22Object field22){
//        
//        this.messageLengthIndicator = messageLengthIndicator;    //DE127
//        this.bitmap = bitmap;                                    //DE127.1
//        this.cvv2 = cvv2;                                        //DE127.10
//        this.addressVerificationData = avd;                      //DE127.15
//        this.cardVerificationData = cvd;                         //DE127.27
//        this.americanExpressCardIdentifier = AmericanExpressCardIdentifier;//DE127.28
//        this.threeDSecureData = threeDSecureData;                //DE127.29
//        this.threeDSecureResult = threeDSecureResult;            //DE127.30 
//        this.ucafData = ucafData;                                //DE127.32
//        this.field22 = field22;
//        
//    }    
    
    public Field127Object(String originalKey,  
                          String originalNode){
        
        this.originalKey  = originalKey;    //DE127.11
        this.originalNode = originalNode;   //DE127.26
        
    }    

    public Field127Object(String routingInfo,
                          String originalKey,  
                          String originalNode){
        
        this.routingInfo = routingInfo;
        this.originalKey  = originalKey;    //DE127.11
        this.originalNode = originalNode;   //DE127.26
        
    }     
    
    /**
     * @return the messageLengthIndicator
     */
    public String getMessageLengthIndicator() {
        return messageLengthIndicator;
    }

    /**
     * @param messageLengthIndicator the messageLengthIndicator to set
     */
    public void setMessageLengthIndicator(String messageLengthIndicator) {
        this.messageLengthIndicator = messageLengthIndicator;
    }

    /**
     * @return the bitmap
     */
    public byte [] getBitmap() {
        return bitmap;
    }

    /**
     * @param bitmap the bitmap to set
     */
    public void setBitmap(byte [] bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * @return the cvv2
     */
    public String getCvv2() {
        return cvv2;
    }

    /**
     * @param cvv2 the cvv2 to set
     */
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    /**
     * @return the americanExpressCardIdentifier
     */
    public String getAmericanExpressCardIdentifier() {
        return americanExpressCardIdentifier;
    }

    /**
     * @param americanExpressCardIdentifier the americanExpressCardIdentifier to set
     */
    public void setAmericanExpressCardIdentifier(String americanExpressCardIdentifier) {
        this.americanExpressCardIdentifier = americanExpressCardIdentifier;
    }

    /**
     * @return the threeDSecureData
     */
    public byte[] getThreeDSecureData() {
        return threeDSecureData;
    }

    /**
     * @param threeDSecureData the threeDSecureData to set
     */
    public void setThreeDSecureData(byte[] threeDSecureData) {
        this.threeDSecureData = threeDSecureData;
    }

    /**
     * @return the threeDSecureResult
     */
    public String getThreeDSecureResult() {
        return threeDSecureResult;
    }

    /**
     * @param threeDSecureResult the threeDSecureResult to set
     */
    public void setThreeDSecureResult(String threeDSecureResult) {
        this.threeDSecureResult = threeDSecureResult;
    }

    /**
     * @return the ucafData
     */
    public byte [] getUcafData() {
        return ucafData;
    }

    /**
     * @param ucafData the ucafData to set
     */
    public void setUcafData(byte [] ucafData) {
        this.ucafData = ucafData;
    }

    /**
     * @return the field22
     */
    public Field127Subfield22Object getField22() {
        return field22;
    }

    /**
     * @param field22 the field22 to set
     */
    public void setField22(Field127Subfield22Object field22) {
        this.field22 = field22;
    }

    /**
     * @return the addressVerificationData
     */
    public String getAddressVerificationData() {
        return addressVerificationData;
    }

    /**
     * @param addressVerificationData the addressVerificationData to set
     */
    public void setAddressVerificationData(String addressVerificationData) {
        this.addressVerificationData = addressVerificationData;
    }

    /**
     * @return the cardVerificationData
     */
    public String getCardVerificationData() {
        return cardVerificationData;
    }

    /**
     * @param cardVerificationData the cardVerificationData to set
     */
    public void setCardVerificationData(String cardVerificationData) {
        this.cardVerificationData = cardVerificationData;
    }

    /**
     * @return the originalNode
     */
    public String getOriginalNode() {
        return originalNode;
    }

    /**
     * @param originalNode the originalNode to set
     */
    public void setOriginalNode(String originalNode) {
        this.originalNode = originalNode;
    }

    /**
     * @return the originalKey
     */
    public String getOriginalKey() {
        return originalKey;
    }

    /**
     * @param originalKey the originalKey to set
     */
    public void setOriginalKey(String originalKey) {
        this.originalKey = originalKey;
    }

    /**
     * @return the routingInfo
     */
    public String getRoutingInfo() {
        return routingInfo;
    }

    /**
     * @param routingInfo the routingInfo to set
     */
    public void setRoutingInfo(String routingInfo) {
        this.routingInfo = routingInfo;
    }

    /**
     * @return the structuredData
     */
    public String getStructuredData() {
        return structuredData;
    }

    /**
     * @param structuredData the structuredData to set
     */
    public void setStructuredData(String structuredData) {
        this.structuredData = structuredData;
    }
    


    
}
