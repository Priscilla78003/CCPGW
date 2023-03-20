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
package com.truteq.ccpgw.adapter.postilion.requests;

import com.truteq.ccpgw.adapter.postilion.requests.element.interfaces.Elements;
import com.truteq.ccpgw.adapter.postilion.requests.objects.CaptureObject;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.protocol.MessageException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Capture extends PostilionRequest{

    private final LogWrapper mLogger = new LogWrapper(Capture.class);
    private final CaptureObject captureObj;

    public Capture (ISOPackager vISOPackager, CaptureObject captureObj) {
        super(vISOPackager);
        this.captureObj = captureObj;   
    } 
    
    
    @Override
    public ISOMsg getUnpacked() throws MessageException {
        try {
            ISOMsg vISOMsg = new ISOMsg();
            vISOMsg.setPackager(this.getvISOPackager());
            vISOMsg.setMTI(this.getCaptureObj().getMessageType());
            
            vISOMsg.set(Elements.PRIMARY_ACCOUNT_NUMBER,this.getCaptureObj().getPrimaryAccountNumber());    //DE2
            vISOMsg.set(Elements.PROCESSING_CODE,this.getCaptureObj().getProcessingCode());                 //DE3  
            vISOMsg.set(Elements.AMOUNT,this.getCaptureObj().getTransactionAmount());                       //DE4
            
            vISOMsg.set(Elements.TRANSMISSION_DATE_TIME,this.getCaptureObj().getTransmissionDateTime());    //DE7
            vISOMsg.set(Elements.SYSTEM_TRACE_AUDIT_NUMBER,this.getCaptureObj().getSystemTraceAuditNumber()); //DE11
            vISOMsg.set(Elements.LOCAL_TRAN_DATE,this.getCaptureObj().getLocalTranDate());                  //DE12 
            vISOMsg.set(Elements.LOCAL_TRAN_TIME,this.getCaptureObj().getLocalTranTime());                  //DE13
            vISOMsg.set(Elements.EXPIRATION_DATE,this.getCaptureObj().getAccountExpiryDate());              //DE14
            vISOMsg.set(Elements.MERCHANT_TYPE ,this.getCaptureObj().getMerchantType());                    //DE18
            vISOMsg.set(Elements.POS_ENTRY_MODE,this.getCaptureObj().getPosEntryMode());                    //DE22
            vISOMsg.set(Elements.POS_CONDITION_CODE,this.getCaptureObj().getPosConditionCode());            //DE25
            vISOMsg.set(Elements.AQC_INSTITUDE_ID_CODE,this.getCaptureObj().getAcquiringInstitutionCode()); //DE32
            
            vISOMsg.set(Elements.RETRIEVAL_REF_NUMBER,this.getCaptureObj().getRetrievalReferenceNumber());  //DE37
            vISOMsg.set(Elements.AUTH_ID_CODE,this.getCaptureObj().getAuthorizationIdResponse());           //DE38
            
            vISOMsg.set(Elements.CARD_ACCEPT_TERMINAL_ID,this.getCaptureObj().getTerminalId());             //DE41
            vISOMsg.set(Elements.CARD_ACCEPT_ID_CODE,this.getCaptureObj().getMerchantId());                 //DE42
            vISOMsg.set(Elements.CARD_ACCEPT_NAME_LOC,this.getCaptureObj().getCardAcceptorNameLocation());  //DE43
            vISOMsg.set(Elements.CURRENCY_CODE,this.getCaptureObj().getCurrencyCodeTransaction());          //DE49
            vISOMsg.set(Elements.ORIGINAL_DATA_ELEMENTS,this.getCaptureObj().getOriginalDataElements());    //DE90
            vISOMsg.set(Elements.POS_DATA_CODE,this.getCaptureObj().getPosDataCode());                      //DE123
 
            if (this.getCaptureObj().getField127() != null){
                ISOMsg f127 = new ISOMsg(127);
                f127.setPackager(this.getvISOPackager());
                if (this.getCaptureObj().getField127().getOriginalKey()!=null)
                    f127.set(11, this.getCaptureObj().getField127().getOriginalKey());
                if (this.getCaptureObj().getField127().getOriginalNode()!=null)
                    f127.set(26, this.getCaptureObj().getField127().getOriginalNode());
                vISOMsg.set(f127);
            }            
            

            return vISOMsg;

        } catch (ISOException vException) {
            mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
            throw new com.truteq.protocol.MessageException(vException.getMessage());
        }
    }
    
    public ISOMsg generateCaptureResponse(String responseCode){ 
         try {
            ISOMsg vISOMsg = new ISOMsg();
            vISOMsg.setPackager(this.getvISOPackager());
            vISOMsg.setMTI("PP0230");
            
            vISOMsg.set(Elements.PRIMARY_ACCOUNT_NUMBER,this.getCaptureObj().getPrimaryAccountNumber());    //DE2
            vISOMsg.set(Elements.PROCESSING_CODE,this.getCaptureObj().getProcessingCode());                 //DE3  
            vISOMsg.set(Elements.AMOUNT,this.getCaptureObj().getTransactionAmount());                       //DE4
            
            vISOMsg.set(Elements.TRANSMISSION_DATE_TIME,this.getCaptureObj().getTransmissionDateTime());    //DE7
            vISOMsg.set(Elements.SYSTEM_TRACE_AUDIT_NUMBER,this.getCaptureObj().getSystemTraceAuditNumber()); //DE11
            vISOMsg.set(Elements.LOCAL_TRAN_DATE,this.getCaptureObj().getLocalTranDate());                  //DE12 
            vISOMsg.set(Elements.LOCAL_TRAN_TIME,this.getCaptureObj().getLocalTranTime());                  //DE13
            vISOMsg.set(Elements.EXPIRATION_DATE,this.getCaptureObj().getAccountExpiryDate());              //DE14
            vISOMsg.set(Elements.MERCHANT_TYPE ,this.getCaptureObj().getMerchantType());                    //DE18
            vISOMsg.set(Elements.POS_ENTRY_MODE,this.getCaptureObj().getPosEntryMode());                    //DE22
            vISOMsg.set(Elements.POS_CONDITION_CODE,this.getCaptureObj().getPosConditionCode());            //DE25
            vISOMsg.set(Elements.AQC_INSTITUDE_ID_CODE,this.getCaptureObj().getAcquiringInstitutionCode()); //DE32
            
            vISOMsg.set(Elements.RETRIEVAL_REF_NUMBER,this.getCaptureObj().getRetrievalReferenceNumber());  //DE37
            vISOMsg.set(Elements.AUTH_ID_CODE,this.getCaptureObj().getAuthorizationIdResponse());           //DE38
            vISOMsg.set(Elements.REPONSE_CODE,responseCode);                                                //DE39
            vISOMsg.set(Elements.CARD_ACCEPT_TERMINAL_ID,this.getCaptureObj().getTerminalId());             //DE41
            vISOMsg.set(Elements.CARD_ACCEPT_ID_CODE,this.getCaptureObj().getMerchantId());                 //DE42
            vISOMsg.set(Elements.CARD_ACCEPT_NAME_LOC,this.getCaptureObj().getCardAcceptorNameLocation());  //DE43
            vISOMsg.set(Elements.CURRENCY_CODE,this.getCaptureObj().getCurrencyCodeTransaction());          //DE49
            vISOMsg.set(Elements.ORIGINAL_DATA_ELEMENTS,this.getCaptureObj().getOriginalDataElements());    //DE90
            vISOMsg.set(Elements.POS_DATA_CODE,this.getCaptureObj().getPosDataCode());                      //DE123
 
            if (this.getCaptureObj().getField127() != null){
                ISOMsg f127 = new ISOMsg(127);
                f127.setPackager(this.getvISOPackager());
                if (this.getCaptureObj().getField127().getOriginalKey()!=null)
                    f127.set(11, this.getCaptureObj().getField127().getOriginalKey());
                vISOMsg.set(f127);
            }            
            

            return vISOMsg;

        } catch (ISOException vException) {
            mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
        }
        return null;
    }   
    

    /**
     * @return the reversalObj
     */
    public CaptureObject getCaptureObj() {
        return captureObj;
    }
    
}
