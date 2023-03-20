/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Postilion Restful server : POSTILION - Restful Web service 
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
public class ReversalResponse implements Serializable{
    
    
    private String messageType;              //DE0

    private String transmissionDateTime;     //DE7
    
    private String systemTraceAuditNumber;   //DE11

    private String localTranTime;            //DE12

    private String localTranDate;            //DE13  

    private String primaryAccountNumber;     //DE2

    private String processingCode;           //DE3
    
    private String transactionAmount;        //DE4
    
    private String accountExpiryDate;        //DE14
    
    private String settlementDate;           //DE15
    
    private String merchantType;             //DE18
    
    private String posEntryMode;             //DE22
    
    private String posConditionCode;         //DE25
    
    private String transactionFeeAmount;     //DE28
    
    private String settlementFeeAmount;      //DE30
    
    private String acquiringInstitutionCode; //DE32
    
    private String retrievalRefNumber;       //DE37      
    
    private String authIdResponse;           //DE38
     
    private String responseCode;             //DE39
    
    private String terminalId;               //DE41
    
    private String merchantId;               //DE42
    
    private String cardAcceptorNameLocation; //DE43
    
    private String currencyCodeTransaction;  //DE49
    
    private String additionalAmounts;        //DE54
    
    private String replacementAmounts;       //DE95
    
    private String posDataCode;              //DE123
    
    private String switchKey;                //DE127.2
    
    private String routingInformation;       //DE127.03
    
    private String authorizationProfile;     //DE127.06
    
    private String originalkey;              //DE127.11

    
    public ReversalResponse(){
        
    }
    
    public ReversalResponse(ISOMsg isoMsg)throws ISOException{

        getCaptureObj(isoMsg, false);
        
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
        case LOCAL_TRAN_TIME : this.setLocalTranTime(getLocalTranTime()); break;     //DE12
        case LOCAL_TRAN_DATE : this.setLocalTranDate(getLocalTranDate()); break;     //DE13        
        case EXPIRATION_DATE : this.setAccountExpiryDate(value); break;         //DE14          
        case SETTLEMENT_DATE : this.setSettlementDate(value);break;             //DE15 
        case MERCHANT_TYPE : this.setMerchantType(value); break;                //DE18        
        case POS_ENTRY_MODE : this.setPosEntryMode( value); break;              //DE22 
        case POS_CONDITION_CODE : this.setPosConditionCode(value); break;       //DE25         
        case TRANSACTION_FEE : this.setTransactionFeeAmount(value); break;      //DE28 
        case TRANSACTION_PROCESSING_FEE : this.setTransactionFeeAmount(value); break;  //DE30
        case AQC_INSTITUDE_ID_CODE : this.setAcquiringInstitutionCode(value); break;   //DE32 
        case RETRIEVAL_REF_NUMBER : this.setRetrievalRefNumber(value); break;   //DE37        
        case AUTH_ID_CODE : break;                                              //DE38 
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
        case POS_DATA_CODE : this.setPosDataCode(value); break;                 //DE123        
        }
        
    }    
    
    private void updateSubFields(int fieldnumber, String value){
        
        switch(fieldnumber){
            
            case SWITCH_KEY_127 : this.setSwitchKey(value); break;
            
            case ROUTING_INFORMATION_127 : this.setRoutingInformation(value); break;

            case AUTHORIZATION_PROFILE_127 : this.setAuthorizationProfile(value); break; 
            
            case ORIGINAL_KEY_127 : this.setOriginalkey(value); break; 
            
        }
  
    }
    
    
    private void getCaptureObj(ISOMsg isoMsg, boolean subfield) throws ISOException{
        
        for (int i=0; i<=isoMsg.getMaxField(); i++ ){
           ISOComponent compo = isoMsg.getComponent(i);
           if (compo instanceof ISOMsg){
             getCaptureObj((ISOMsg) compo, true);  
           }
           else{
           if(compo != null)
              if (subfield) 
                 updateSubFields(compo.getFieldNumber(),(String)compo.getValue());
              else
                 updateFields(compo.getFieldNumber(),(String)compo.getValue()); 
           }
        }
        
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
     * @return the authIdResponse
     */
    public String getAuthIdResponse() {
        return authIdResponse;
    }

    /**
     * @param authIdResponse the authIdResponse to set
     */
    public void setAuthIdResponse(String authIdResponse) {
        this.authIdResponse = authIdResponse;
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
     * @return the replacementAmounts
     */
    public String getReplacementAmounts() {
        return replacementAmounts;
    }

    /**
     * @param replacementAmounts the replacementAmounts to set
     */
    public void setReplacementAmounts(String replacementAmounts) {
        this.replacementAmounts = replacementAmounts;
    }

    /**
     * @return the switchKey
     */
    public String getSwitchKey() {
        return switchKey;
    }

    /**
     * @param switchKey the switchKey to set
     */
    public void setSwitchKey(String switchKey) {
        this.switchKey = switchKey;
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
     * @return the originalkey
     */
    public String getOriginalkey() {
        return originalkey;
    }

    /**
     * @param originalkey the originalkey to set
     */
    public void setOriginalkey(String originalkey) {
        this.originalkey = originalkey;
    }
     

    
}
