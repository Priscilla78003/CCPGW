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
package com.truteq.ccpgw.postilion.rest.microserv.transaction.model;

import com.google.gson.Gson;
import static com.truteq.ccpgw.adapter.postilion.requests.element.interfaces.Elements.*;
import java.io.Serializable;
import org.jpos.iso.ISOComponent;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

//<isomsg direction="incoming">
//  <!-- org.jpos.iso.packager.GenericPackager[/home/ipgw/sabre-postilion/adapter/config/fields127.xml] -->
//  <field id="0" value="0110"/>
//  <field id="2" value="4896780109909073"/>
//  <field id="3" value="002000"/>
//  <field id="4" value="000000000000"/>
//  <field id="7" value="0423094551"/>
//  <field id="11" value="000011"/>
//  <field id="12" value="094551"/>
//  <field id="13" value="0423"/>
//  <field id="14" value="2306"/>
//  <field id="15" value="0423"/>
//  <field id="18" value="6300"/>
//  <field id="22" value="010"/>
//  <field id="23" value="000"/>
//  <field id="25" value="59"/>
//  <field id="28" value="C00000000"/>
//  <field id="30" value="C00000000"/>
//  <field id="32" value="60130200000"/>
//  <field id="37" value="000011386639"/>
//  <field id="39" value="59"/>
//  <field id="41" value="423PGK11"/>
//  <field id="42" value="4023PGK00000011"/>
//  <field id="43" value="PHA Health Assurance LiPOM            PG"/>
//  <field id="49" value="598"/>
//  <field id="54" value="2053598C000000000000"/>
//  <field id="102" value="007012130550"/>
//  <field id="123" value="660550600001192"/>
//  <isomsg id="127">
//    <!-- org.jpos.iso.packager.GenericSubFieldPackager -->
//    <field id="3" value="PlatformPSrcICBSSnk2    000011000011VDEMVTotal  "/>
//    <field id="6" value="24"/>
//    <field id="20" value="20210423"/>
//    <field id="22"><![CDATA[225Postilion:ExtendedRspCode14P227218Postilion:MetaData299225Postilion:ExtendedRspCode111230Postilion:ExtendedResponseCode111226Postilion:ValidationResult111230Postilion:ExtendedResponseCode14P227226Postilion:ValidationResult3765<?xml version="1.0" encoding="UTF-8"?><ValidationResult><Result validator="PC_LOCATION">PASS</Result><Result validator="PC_PRODUCT">PASS</Result><Result validator="PC_CHALLENGE">PASS</Result><Result validator="PC_EXTERNAL_PROCESSING">PASS</Result><Result validator="PC_CVV_CVV2_DCVV">PASS</Result><Result validator="PC_PIN">SKIP</Result><Result validator="PC_3DS">3d_secure_data_unexpected</Result><Result validator="PC_VELOCITY">PASS</Result><Result validator="PC_PIN_INTEGRITY">SKIP</Result><Result validator="PC_CRYPTOGRAM">SKIP</Result><Result validator="PC_ATC">SKIP</Result><Result validator="PC_CVR">SKIP</Result><Result validator="PC_TVR">SKIP</Result><Result validator="PC_FLEET">PASS</Result><Result validator="PC_DECLINE">FAIL</Result></ValidationResult>]]></field>
//    <field id="30" value="M"/>
//  </isomsg>
//</isomsg>

public class AuthoriseResponse implements Serializable{
    
    private String messageType;              //DE0
    
    private String primaryAccountNumber;     //DE2

    private String processingCode;           //DE3
    
    private String transactionAmount;        //DE4

    private String transmissionDateTime;     //DE7
    
    private String systemTraceAuditNumber;   //DE11

    private String localTranTime;            //DE12

    private String localTranDate;            //DE13  

    private String accountExpiryDate;        //DE14
    
    private String settlementDate;           //DE15
    
    private String merchantType;             //DE18
    
    private String posEntryMode;             //DE22
    
    private String cardSequenceNumber;       //DE23 
    
    private String posConditionCode;         //DE25
    
    private String transactionFeeAmount;     //DE28
    
    private String settlementFeeAmount;      //DE30
    
    private String acquiringInstitutionCode; //DE32
    
    private String retrievalRefNumber;       //DE37
    
    private String authIdResponse;           //DE38  //Approval Code
    
    private String responseCode;             //DE39
    
    private String terminalId;               //DE41 
    
    private String merchantId;               //DE42
    
    private String cardAcceptorNameLocation; //DE43
    
    private String currencyCodeTransaction;  //DE49
    
    private String additionalAmounts;        //DE54
     
    private String accountId;                //DE102
    
    private String accountIdTo;              //DE103
    
    private String posDataCode;              //DE123
      
    private String routingInformation;       //DE127.03
    
    private String authorizationProfile;     //DE127.06
    
    private String addressVerificationResult;//DE127.16 //AVS 
    
    private String authorizerDateSettlement; //DE127.20  
    
    private String structuredData;           //DE127.22
    
    private String cardVerificationResult;   //DE127.27 //CVC
    
    private String threeDSecureResult;       //DE127.30 
    
    public AuthoriseResponse(){
        
    }
    
    public AuthoriseResponse(ISOMsg isoMsg) throws ISOException{

        getAuthoriseObj(isoMsg, false);

    }
    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }    
    
    
    private void updateFields(int fieldnumber, String value){
        
      switch(fieldnumber){
            
        case MESSAGE_TYPE : this.setMessageType(value); break;                  //DE0  
        case PRIMARY_BITMAP : break;                                            //DE1 
        case PRIMARY_ACCOUNT_NUMBER : this.setPrimaryAccountNumber(value);break;//DE2
        case PROCESSING_CODE : this.setProcessingCode(value); break;            //DE3        
        case AMOUNT : this.setTransactionAmount(value);                         //DE4
        case TRANSMISSION_DATE_TIME : this.setTransmissionDateTime(value); break;     //DE7
        case SYSTEM_TRACE_AUDIT_NUMBER : this.setSystemTraceAuditNumber(value); break;//DE11
        case LOCAL_TRAN_TIME : this.setLocalTranTime(value); break;             //DE12
        case LOCAL_TRAN_DATE : this.setLocalTranDate(value); break;             //DE13        
        case EXPIRATION_DATE : this.setAccountExpiryDate(value); break;         //DE14          
        case SETTLEMENT_DATE : this.setSettlementDate(value);break;             //DE15 
        case MERCHANT_TYPE : this.setMerchantType(value); break;                //DE18        
        case POS_ENTRY_MODE : this.setPosEntryMode( value); break;              //DE22 
        case CARD_SEQ_NUMBER : this.setCardSequenceNumber(value); break;        //DE23 
        case POS_CONDITION_CODE : this.setPosConditionCode(value); break;       //DE25         
        case TRANSACTION_FEE : this.setTransactionFeeAmount(value); break;      //DE28 
        case TRANSACTION_PROCESSING_FEE : this.setTransactionFeeAmount(value); break;  //DE30
        case AQC_INSTITUDE_ID_CODE : this.setAcquiringInstitutionCode(value); break;   //DE32 
        case FF_INSTITUTION_ID_CODE : break;                                    //DE33
        case RETRIEVAL_REF_NUMBER : this.setRetrievalRefNumber(value); break;   //DE37        
        case AUTH_ID_CODE : this.setAuthIdResponse(value);break;                //DE38 
        case REPONSE_CODE : this.setResponseCode(value); break;                 //DE39 
        case CARD_ACCEPT_TERMINAL_ID : this.setTerminalId(value); break;        //DE41  
        case CARD_ACCEPT_ID_CODE : this.setMerchantId(value); break;            //DE42 
        case CARD_ACCEPT_NAME_LOC : this.setCardAcceptorNameLocation(value); break;    //DE43          
        case CURRENCY_CODE : this.setCurrencyCodeTransaction(value);break;      //DE49 
        case ADDITIONAL_AMOUNTS : this.setAdditionalAmounts(value); break;      //DE54
        case MESSAGE_REASON_CODE : break;                                       //DE56
        case NETWORK_MANAGEMENT_INFO_CODE : break;                              //DE70
        case ORIGINAL_DATA_ELEMENTS : break;                                    //DE90
        case RCV_INSTITUTION_ID_CODE : break;                                   //DE100
        case ACCOUNT_IDENTIFICATION : this.setAccountId(value); break;          //DE102 
        case ACCOUNT_IDENTIFICATION_TO : this.setAccountIdTo(value); break;     //DE103
        case POS_DATA_CODE : this.setPosDataCode(value); break;                 //DE123
      }        
    }    
    
    private void updateSubFields(int fieldnumber, String value){
        
        switch(fieldnumber){

            case ROUTING_INFORMATION_127 : this.setRoutingInformation(value); break;

            case AUTHORIZATION_PROFILE_127 : this.setAuthorizationProfile(value); break;

            case AUTHORIZER_DATE_SETTLEMENT_127 : this.setAuthorizerDateSettlement(value); break;

            case STRUCTURED_DATA_127 : this.setStructuredData(value); break;

            case THREED_SECURE_RESULT_127 : this.setThreeDSecureResult(value); break;
            
            case ADDRESS_VERFICATION_RESULT_127 : this.setAddressVerificationResult(value); break;
            
            case CARD_VERFICATION_RESULT_127 : this.setCardVerificationResult(value); break;

        }
  
    }
    
    
    private void getAuthoriseObj(ISOMsg isoMsg, boolean subfield) throws ISOException{
        
        for (int i=0; i<=isoMsg.getMaxField(); i++ ){
           ISOComponent compo = isoMsg.getComponent(i);
           if (compo instanceof ISOMsg){
             getAuthoriseObj((ISOMsg) compo, true);  
           }
           else{
           if(compo != null)
              if (subfield){ 
                 if (compo.getValue() instanceof byte[]){ 
                    updateSubFields(compo.getFieldNumber(),new String((byte[])compo.getValue()));
                 }
                 else
                    updateSubFields(compo.getFieldNumber(),(String)compo.getValue());  
              }
              else
                 updateFields(compo.getFieldNumber(),(String)compo.getValue()); 
           }
        }
        
    } 

    /**
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the primaryAccountNumber
     */
    public String getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }

    /**
     * @param primaryAccountNumber the primaryAccountNumber to set
     */
    public void setPrimaryAccountNumber(String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
    }

    /**
     * @return the processingCode
     */
    public String getProcessingCode() {
        return processingCode;
    }

    /**
     * @param processingCode the processingCode to set
     */
    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    /**
     * @return the transactionAmount
     */
    public String getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * @param transactionAmount the transactionAmount to set
     */
    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * @return the transmissionDateTime
     */
    public String getTransmissionDateTime() {
        return transmissionDateTime;
    }

    /**
     * @param transmissionDateTime the transmissionDateTime to set
     */
    public void setTransmissionDateTime(String transmissionDateTime) {
        this.transmissionDateTime = transmissionDateTime;
    }

    /**
     * @return the systemTraceAuditNumber
     */
    public String getSystemTraceAuditNumber() {
        return systemTraceAuditNumber;
    }

    /**
     * @param systemTraceAuditNumber the systemTraceAuditNumber to set
     */
    public void setSystemTraceAuditNumber(String systemTraceAuditNumber) {
        this.systemTraceAuditNumber = systemTraceAuditNumber;
    }

    /**
     * @return the localTranTime
     */
    public String getLocalTranTime() {
        return localTranTime;
    }

    /**
     * @param localTranTime the localTranTime to set
     */
    public void setLocalTranTime(String localTranTime) {
        this.localTranTime = localTranTime;
    }

    /**
     * @return the localTranDate
     */
    public String getLocalTranDate() {
        return localTranDate;
    }

    /**
     * @param localTranDate the localTranDate to set
     */
    public void setLocalTranDate(String localTranDate) {
        this.localTranDate = localTranDate;
    }

    /**
     * @return the accountExpiryDate
     */
    public String getAccountExpiryDate() {
        return accountExpiryDate;
    }

    /**
     * @param accountExpiryDate the accountExpiryDate to set
     */
    public void setAccountExpiryDate(String accountExpiryDate) {
        this.accountExpiryDate = accountExpiryDate;
    }

    /**
     * @return the settlementDate
     */
    public String getSettlementDate() {
        return settlementDate;
    }

    /**
     * @param settlementDate the settlementDate to set
     */
    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    /**
     * @return the merchantType
     */
    public String getMerchantType() {
        return merchantType;
    }

    /**
     * @param merchantType the merchantType to set
     */
    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    /**
     * @return the posEntryMode
     */
    public String getPosEntryMode() {
        return posEntryMode;
    }

    /**
     * @param posEntryMode the posEntryMode to set
     */
    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    /**
     * @return the cardSequenceNumber
     */
    public String getCardSequenceNumber() {
        return cardSequenceNumber;
    }

    /**
     * @param cardSequenceNumber the cardSequenceNumber to set
     */
    public void setCardSequenceNumber(String cardSequenceNumber) {
        this.cardSequenceNumber = cardSequenceNumber;
    }

    /**
     * @return the posConditionCode
     */
    public String getPosConditionCode() {
        return posConditionCode;
    }

    /**
     * @param posConditionCode the posConditionCode to set
     */
    public void setPosConditionCode(String posConditionCode) {
        this.posConditionCode = posConditionCode;
    }

    /**
     * @return the transactionFeeAmount
     */
    public String getTransactionFeeAmount() {
        return transactionFeeAmount;
    }

    /**
     * @param transactionFeeAmount the transactionFeeAmount to set
     */
    public void setTransactionFeeAmount(String transactionFeeAmount) {
        this.transactionFeeAmount = transactionFeeAmount;
    }

    /**
     * @return the settlementFeeAmount
     */
    public String getSettlementFeeAmount() {
        return settlementFeeAmount;
    }

    /**
     * @param settlementFeeAmount the settlementFeeAmount to set
     */
    public void setSettlementFeeAmount(String settlementFeeAmount) {
        this.settlementFeeAmount = settlementFeeAmount;
    }

    /**
     * @return the acquiringInstitutionCode
     */
    public String getAcquiringInstitutionCode() {
        return acquiringInstitutionCode;
    }

    /**
     * @param acquiringInstitutionCode the acquiringInstitutionCode to set
     */
    public void setAcquiringInstitutionCode(String acquiringInstitutionCode) {
        this.acquiringInstitutionCode = acquiringInstitutionCode;
    }

    /**
     * @return the retrievalRefNumber
     */
    public String getRetrievalRefNumber() {
        return retrievalRefNumber;
    }

    /**
     * @param retrievalRefNumber the retrievalRefNumber to set
     */
    public void setRetrievalRefNumber(String retrievalRefNumber) {
        this.retrievalRefNumber = retrievalRefNumber;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the terminalId
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * @param terminalId the terminalId to set
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * @return the merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return the cardAcceptorNameLocation
     */
    public String getCardAcceptorNameLocation() {
        return cardAcceptorNameLocation;
    }

    /**
     * @param cardAcceptorNameLocation the cardAcceptorNameLocation to set
     */
    public void setCardAcceptorNameLocation(String cardAcceptorNameLocation) {
        this.cardAcceptorNameLocation = cardAcceptorNameLocation;
    }

    /**
     * @return the currencyCodeTransaction
     */
    public String getCurrencyCodeTransaction() {
        return currencyCodeTransaction;
    }

    /**
     * @param currencyCodeTransaction the currencyCodeTransaction to set
     */
    public void setCurrencyCodeTransaction(String currencyCodeTransaction) {
        this.currencyCodeTransaction = currencyCodeTransaction;
    }

    /**
     * @return the additionalAmounts
     */
    public String getAdditionalAmounts() {
        return additionalAmounts;
    }

    /**
     * @param additionalAmounts the additionalAmounts to set
     */
    public void setAdditionalAmounts(String additionalAmounts) {
        this.additionalAmounts = additionalAmounts;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the posDataCode
     */
    public String getPosDataCode() {
        return posDataCode;
    }

    /**
     * @param posDataCode the posDataCode to set
     */
    public void setPosDataCode(String posDataCode) {
        this.posDataCode = posDataCode;
    }

    /**
     * @return the routingInformation
     */
    public String getRoutingInformation() {
        return routingInformation;
    }

    /**
     * @param routingInformation the routingInformation to set
     */
    public void setRoutingInformation(String routingInformation) {
        this.routingInformation = routingInformation;
    }

    /**
     * @return the authorizationProfile
     */
    public String getAuthorizationProfile() {
        return authorizationProfile;
    }

    /**
     * @param authorizationProfile the authorizationProfile to set
     */
    public void setAuthorizationProfile(String authorizationProfile) {
        this.authorizationProfile = authorizationProfile;
    }

    /**
     * @return the authorizerDateSettlement
     */
    public String getAuthorizerDateSettlement() {
        return authorizerDateSettlement;
    }

    /**
     * @param authorizerDateSettlement the authorizerDateSettlement to set
     */
    public void setAuthorizerDateSettlement(String authorizerDateSettlement) {
        this.authorizerDateSettlement = authorizerDateSettlement;
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
     * @return the AuthIdResponse
     */
    public String getAuthIdResponse() {
        return authIdResponse;
    }

    /**
     * @param AuthIdResponse the AuthIdResponse to set
     */
    public void setAuthIdResponse(String AuthIdResponse) {
        this.authIdResponse = AuthIdResponse;
    }

    /**
     * @return the addressVerificationResult
     */
    public String getAddressVerificationResult() {
        return addressVerificationResult;
    }

    /**
     * @param addressVerificationResult the addressVerificationResult to set
     */
    public void setAddressVerificationResult(String addressVerificationResult) {
        this.addressVerificationResult = addressVerificationResult;
    }

    /**
     * @return the cardVerificationResult
     */
    public String getCardVerificationResult() {
        return cardVerificationResult;
    }

    /**
     * @param cardVerificationResult the cardVerificationResult to set
     */
    public void setCardVerificationResult(String cardVerificationResult) {
        this.cardVerificationResult = cardVerificationResult;
    }

    /**
     * @return the accountIdTo
     */
    public String getAccountIdTo() {
        return accountIdTo;
    }

    /**
     * @param accountIdTo the accountIdTo to set
     */
    public void setAccountIdTo(String accountIdTo) {
        this.accountIdTo = accountIdTo;
    }
}