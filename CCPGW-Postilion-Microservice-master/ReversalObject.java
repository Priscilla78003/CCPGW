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
public class ReversalObject extends AuthoriseObject {
    
    private String settleDate;
    private String messageReasonCode;
    private String originalDataElements;
    
    public ReversalObject(eAuth auth,
                            String sysTraceAuditNumber, 
                            String primaryAccountNumber, //DE2 
                            String processingCode,       //DE3
                            String transactionAmount,    //DE4
                            String transmissionDateTime,
                            String localTranTime,
                            String localTranDate,
                            String accountExpiryDate,    //DE14
                            String settleDate,           //DE15
                            String merchantType,
                            String posEntryMode,         //DE22  
                            String posConditionCode,     //DE25 
                            String terminalId,           //DE41
                            String merchantId,
                            //String cardAcceptorIdCode,   //DE42 
                            String cardAcceptorNameLocation, //DE43  
                            String currencyCode,         //DE49        
                            String messageReasonCode,    //DE56
                            String originalDataElements, //DE90
                            String posdatacode,          //DE123
                            Field127Object field127){

        
        super(auth, 
              primaryAccountNumber, 
              processingCode, 
              transactionAmount,
              transmissionDateTime,
              sysTraceAuditNumber,
              localTranTime,
              localTranDate,
              accountExpiryDate, 
              merchantType, 
              posEntryMode, 
              posConditionCode, 
              "", 
              terminalId, 
              merchantId, 
              cardAcceptorNameLocation, 
              currencyCode, 
              posdatacode, 
              field127);

        this.settleDate = settleDate;
        this.messageReasonCode = messageReasonCode;
        this.originalDataElements = originalDataElements;          
    }

    /**
     * @return the settleDate
     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * @param settleDate the settleDate to set
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
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
}
