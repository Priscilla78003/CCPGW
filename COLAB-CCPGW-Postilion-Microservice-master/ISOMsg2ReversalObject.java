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
package com.truteq.ccpgw.adapter.postilion;

import com.truteq.ccpgw.adapter.postilion.enums.eAuth;
import com.truteq.ccpgw.adapter.postilion.requests.objects.ReversalObject;
import org.jpos.iso.ISOComponent;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import static com.truteq.ccpgw.adapter.postilion.requests.element.interfaces.Elements.*;
import com.truteq.ccpgw.adapter.postilion.requests.objects.Field127Object;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ISOMsg2ReversalObject {
    
    private final LogWrapper mLogger = new LogWrapper(ISOMsg2ReversalObject.class);
    
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
    
    private String messageReasonCode;        //DE56
    
    private String originalDataElements;     //DE90
     
    private String accountId;                //DE102
    
    private String accountIdTo;              //DE103
    
    private String posDataCode;              //DE123
      
    private String routingInformation;       //DE127.03
    
    private String authorizationProfile;     //DE127.06
    
    private String originalKey;              //DE127.11
    
    private String addressVerificationResult;//DE127.16 //AVS 
    
    private String authorizerDateSettlement; //DE127.20  
    
    private String structuredData;           //DE127.22
    
    private String originalNode;             //DE127.26  
    
    private String cardVerificationResult;   //DE127.27 //CVC
    
    private String threeDSecureResult;       //DE127.30     
    
    
    public ISOMsg2ReversalObject(){
        
    }
    
    
    public ReversalObject getReversalObject(ISOMsg isoMsg){
        
        try {
            getAuthoriseObj(isoMsg,false);
        } catch (ISOException ex) {
            mLogger.error("Exception: Error converting ISOMsg to ReversalObject" + ex, new Throwable().getStackTrace()[0]);
        }
        Field127Object field127 = new Field127Object(this.getRoutingInformation(),
                                                     this.getOriginalKey(),  
                                                     this.getOriginalNode());
        
        ReversalObject revObj = new ReversalObject(eAuth.REVERSAL,
                                                   this.getSystemTraceAuditNumber(), 
                                                   this.getPrimaryAccountNumber(), //DE2 
                            this.getProcessingCode(),       //DE3
                            this.getTransactionAmount(), //Integer.toString(Integer.parseInt(this.getTransactionAmount())),//this.getTransactionAmount(),    //DE4
                            this.getTransmissionDateTime(),
                            this.getLocalTranTime(),
                            this.getLocalTranDate(),
                            this.getAccountExpiryDate(),    //DE14
                            this.getSettlementDate(),           //DE15
                            this.getMerchantType(),
                            this.getPosEntryMode(),         //DE22  
                            this.getPosConditionCode(),     //DE25 
                            this.getTerminalId(),           //DE41
                            this.getMerchantId(),
                            this.getCardAcceptorNameLocation(), //DE43  
                            this.getCurrencyCodeTransaction(),  //DE49        
                            this.getMessageReasonCode(),    //DE56
                            this.getOriginalDataElements(), //DE90
                            this.getPosDataCode(),          //DE123
                            field127);
        return revObj;
    }
    
    private void getAuthoriseObj(ISOMsg isoMsg, boolean subfield) throws ISOException{
        
        for (int i=0; i<=isoMsg.getMaxField(); i++ ){
           ISOComponent compo = isoMsg.getComponent(i);
           if (compo instanceof ISOMsg){
             getAuthoriseObj((ISOMsg) compo, true);  
           }
           else{
           if(compo != null)
              if (subfield) 
                 updateSubFields(compo.getFieldNumber(),compo.getValue());
              else
                 updateFields(compo.getFieldNumber(),compo.getValue()); 
           }
        }
        
    }
    
    private void updateFields(int fieldnumber, Object value){
        
      switch(fieldnumber){
            
        case MESSAGE_TYPE : this.setMessageType((String)value); break;                  //DE0  
        case PRIMARY_BITMAP : break;                                            //DE1 
        case PRIMARY_ACCOUNT_NUMBER : this.setPrimaryAccountNumber((String)value);break;//DE2
        case PROCESSING_CODE : this.setProcessingCode((String)value); break;            //DE3        
        case AMOUNT : this.setTransactionAmount((String)value);                         //DE4
        case TRANSMISSION_DATE_TIME : this.setTransmissionDateTime((String)value); break;     //DE7
        case SYSTEM_TRACE_AUDIT_NUMBER : this.setSystemTraceAuditNumber((String)value); break;//DE11
        case LOCAL_TRAN_TIME : this.setLocalTranTime((String)value); break;             //DE12
        case LOCAL_TRAN_DATE : this.setLocalTranDate((String)value); break;             //DE13        
        case EXPIRATION_DATE : this.setAccountExpiryDate((String)value); break;         //DE14          
        case SETTLEMENT_DATE : this.setSettlementDate((String)value);break;             //DE15 
        case MERCHANT_TYPE : this.setMerchantType((String)value); break;                //DE18        
        case POS_ENTRY_MODE : this.setPosEntryMode( (String)value); break;              //DE22 
        case CARD_SEQ_NUMBER : this.setCardSequenceNumber((String)value); break;        //DE23 
        case POS_CONDITION_CODE : this.setPosConditionCode((String)value); break;       //DE25         
        case TRANSACTION_FEE : this.setTransactionFeeAmount((String)value); break;      //DE28 
        case TRANSACTION_PROCESSING_FEE : this.setTransactionFeeAmount((String)value); break;  //DE30
        case AQC_INSTITUDE_ID_CODE : this.setAcquiringInstitutionCode((String)value); break;   //DE32 
        case FF_INSTITUTION_ID_CODE : break;                                    //DE33
        case RETRIEVAL_REF_NUMBER : this.setRetrievalRefNumber((String)value); break;   //DE37        
        case AUTH_ID_CODE : this.setAuthIdResponse((String)value);break;                //DE38 
        case REPONSE_CODE : this.setResponseCode((String)value); break;                 //DE39 
        case CARD_ACCEPT_TERMINAL_ID : this.setTerminalId((String)value); break;        //DE41  
        case CARD_ACCEPT_ID_CODE : this.setMerchantId((String)value); break;            //DE42 
        case CARD_ACCEPT_NAME_LOC : this.setCardAcceptorNameLocation((String)value); break;    //DE43          
        case CURRENCY_CODE : this.setCurrencyCodeTransaction((String)value);break;      //DE49 
        case ADDITIONAL_AMOUNTS : this.setAdditionalAmounts((String)value); break;      //DE54
        case MESSAGE_REASON_CODE : break;                                       //DE56
        case NETWORK_MANAGEMENT_INFO_CODE : break;                              //DE70
        case ORIGINAL_DATA_ELEMENTS : this.setOriginalDataElements((String)value); break; //DE90
        case RCV_INSTITUTION_ID_CODE : break;                                   //DE100
        case ACCOUNT_IDENTIFICATION : this.setAccountId((String)value); break;          //DE102 
        case ACCOUNT_IDENTIFICATION_TO : this.setAccountIdTo((String)value); break;     //DE103
        case POS_DATA_CODE : this.setPosDataCode((String)value); break;                 //DE123
      }        
    }    
    
    private void updateSubFields(int fieldnumber, Object value){
        
        switch(fieldnumber){

            case ROUTING_INFORMATION_127 : this.setRoutingInformation((String)value); break;
            
            case ORIGINAL_KEY_127: this.setOriginalKey((String)value); break;
                    
            case ORIGINAL_NODE_127: this.setOriginalNode((String)value); break;
           
            case AUTHORIZATION_PROFILE_127 : this.setAuthorizationProfile((String)value); break;

            case AUTHORIZER_DATE_SETTLEMENT_127 : this.setAuthorizerDateSettlement((String)value); break;

            case STRUCTURED_DATA_127 : this.setStructuredData((String)value); break;

            case THREED_SECURE_RESULT_127 : this.setThreeDSecureResult((String)value); break;
            
            case ADDRESS_VERFICATION_RESULT_127 : this.setAddressVerificationResult((String)value); break;
            
            case CARD_VERFICATION_RESULT_127 : this.setCardVerificationResult((String)value); break;

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
     * @return the messageReasonCode
     */
    public String getMessageReasonCode() {
        return messageReasonCode;
    }

    /**
     * @param messageReasonCode the messageReasonCode to set
     */
    public void setMessageReasonCode(String messageReasonCode) {
        this.messageReasonCode = messageReasonCode;
    }

    /**
     * @return the originalDataElements
     */
    public String getOriginalDataElements() {
        return originalDataElements;
    }

    /**
     * @param originalDataElements the originalDataElements to set
     */
    public void setOriginalDataElements(String originalDataElements) {
        this.originalDataElements = originalDataElements;
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
}
