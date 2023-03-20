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

import com.truteq.ccpgw.adapter.postilion.enums.eAuth;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class CaptureObject  extends AuthoriseObject {
    
    private String retrievalReferenceNumber;
    private String authorizationIdResponse;
    private String originalDataElements;
    
    public CaptureObject(eAuth auth,
                            String sysTraceAuditNumber, 
                            String primaryAccountNumber, //DE2 
                            String processingCode,       //DE3
                            String transactionAmount,    //DE4
                            String accountExpiryDate,    //DE14
                            String merchantType,         //DE18        
                            String posEntryMode,         //DE22  
                            String posConditionCode,     //DE25 
                            String acquiringInstitutionCode, //DE32 
                            String retrievalReferenceNumber, //DE37
                            String authorizationIdResponse,  //DE38
                            String terminalId,           //DE41
                            String merchantId,           //DE42 
                            String cardAcceptorNameLocation, //DE43  
                            String currencyCode,         //DE49        
                            String originalDataElements, //DE90
                            String posDataCode,          //DE123
                            Field127Object field127){
        super(auth, 
              sysTraceAuditNumber, 
              primaryAccountNumber, 
              processingCode, 
              transactionAmount, 
              accountExpiryDate, 
              merchantType, 
              posEntryMode, 
              posConditionCode, 
              acquiringInstitutionCode, 
              terminalId, 
              merchantId, 
              cardAcceptorNameLocation, 
              currencyCode, 
              posDataCode, 
              field127);
        this.originalDataElements = originalDataElements;
        this.retrievalReferenceNumber = retrievalReferenceNumber;
        this.authorizationIdResponse = authorizationIdResponse;

    } 

    /**
     * @return the retrievalReferenceNumber
     */
    public String getRetrievalReferenceNumber() {
        return retrievalReferenceNumber;
    }

    /**
     * @param retrievalReferenceNumber the retrievalReferenceNumber to set
     */
    public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
        this.retrievalReferenceNumber = retrievalReferenceNumber;
    }

    /**
     * @return the authorizationIdResponse
     */
    public String getAuthorizationIdResponse() {
        return authorizationIdResponse;
    }

    /**
     * @param authorizationIdResponse the authorizationIdResponse to set
     */
    public void setAuthorizationIdResponse(String authorizationIdResponse) {
        this.authorizationIdResponse = authorizationIdResponse;
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
 
    
}