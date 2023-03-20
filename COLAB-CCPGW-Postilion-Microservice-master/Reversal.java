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
import com.truteq.ccpgw.adapter.postilion.requests.objects.ReversalObject;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.protocol.MessageException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Reversal extends PostilionRequest{
    
    private final LogWrapper mLogger = new LogWrapper(Reversal.class);
    private final ReversalObject reversalObj;

    public Reversal (ISOPackager vISOPackager, ReversalObject reversalObj) {
        super(vISOPackager);
        this.reversalObj = reversalObj;
        
    }      
    

    @Override
    public ISOMsg getUnpacked() throws MessageException {
        try {
            ISOMsg vISOMsg = new ISOMsg();
            vISOMsg.setPackager(this.getvISOPackager());
            vISOMsg.setMTI(this.getReversalObj().getMessageType());
            
            vISOMsg.set(Elements.PRIMARY_ACCOUNT_NUMBER,this.getReversalObj().getPrimaryAccountNumber());    //DE2
            vISOMsg.set(Elements.PROCESSING_CODE,this.getReversalObj().getProcessingCode());                 //DE3  
            vISOMsg.set(Elements.AMOUNT,this.getReversalObj().getTransactionAmount());                       //DE4
            vISOMsg.set(Elements.TRANSMISSION_DATE_TIME,this.getReversalObj().getTransmissionDateTime());    //DE7
            vISOMsg.set(Elements.SYSTEM_TRACE_AUDIT_NUMBER,this.getReversalObj().getSystemTraceAuditNumber()); //DE11
            vISOMsg.set(Elements.LOCAL_TRAN_DATE,this.getReversalObj().getLocalTranDate());                  //DE12 
            vISOMsg.set(Elements.LOCAL_TRAN_TIME,this.getReversalObj().getLocalTranTime());                  //DE13
            vISOMsg.set(Elements.EXPIRATION_DATE,this.getReversalObj().getAccountExpiryDate());              //DE14
            vISOMsg.set(Elements.SETTLEMENT_DATE,this.getReversalObj().getSettleDate());                     //DE15
            vISOMsg.set(Elements.MERCHANT_TYPE,this.getReversalObj().getMerchantType());                     //DE18
            vISOMsg.set(Elements.POS_ENTRY_MODE,this.getReversalObj().getPosEntryMode());                    //DE22
            vISOMsg.set(Elements.POS_CONDITION_CODE,this.getReversalObj().getPosConditionCode());            //DE25
            vISOMsg.set(Elements.CARD_ACCEPT_TERMINAL_ID,this.getReversalObj().getTerminalId());             //DE41
            vISOMsg.set(Elements.CARD_ACCEPT_ID_CODE,this.getReversalObj().getMerchantId());                 //DE42
            vISOMsg.set(Elements.CARD_ACCEPT_NAME_LOC,this.getReversalObj().getCardAcceptorNameLocation());  //DE43
            vISOMsg.set(Elements.CURRENCY_CODE,this.getReversalObj().getCurrencyCodeTransaction());          //DE49
            vISOMsg.set(Elements.MESSAGE_REASON_CODE,this.getReversalObj().getMessageReasonCode());          //DE56
            vISOMsg.set(Elements.ORIGINAL_DATA_ELEMENTS,this.getReversalObj().getOriginalDataElements());    //DE90
            vISOMsg.set(Elements.POS_DATA_CODE,this.getReversalObj().getPosDataCode());                      //DE123
            
            if (this.getReversalObj().getField127() != null){
                ISOMsg f127 = new ISOMsg(127);
                f127.setPackager(this.getvISOPackager());
                if (this.getReversalObj().getField127().getRoutingInfo()!=null)
                    f127.set(3,  this.getReversalObj().getField127().getRoutingInfo());
                if (this.getReversalObj().getField127().getOriginalKey()!=null)
                    f127.set(11, this.getReversalObj().getField127().getOriginalKey());
                if (this.getReversalObj().getField127().getOriginalNode()!=null)
                    f127.set(26, this.getReversalObj().getField127().getOriginalNode());
                vISOMsg.set(f127);
            }

            return vISOMsg;

        } catch (ISOException vException) {
            mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
            throw new com.truteq.protocol.MessageException(vException.getMessage());
        }
    }
    
    public ISOMsg generateReversalResponse(String responseCode){
        try {
            ISOMsg vISOMsg = new ISOMsg();
            vISOMsg.setPackager(this.getvISOPackager());
            vISOMsg.setMTI("PP0430");
            
            vISOMsg.set(Elements.PROCESSING_CODE,this.getReversalObj().getProcessingCode());                 //DE3  
            vISOMsg.set(Elements.AMOUNT,this.getReversalObj().getTransactionAmount());                       //DE4
            vISOMsg.set(Elements.TRANSMISSION_DATE_TIME,this.getReversalObj().getTransmissionDateTime());    //DE7
            vISOMsg.set(Elements.SYSTEM_TRACE_AUDIT_NUMBER,this.getReversalObj().getSystemTraceAuditNumber()); //DE11
            vISOMsg.set(Elements.LOCAL_TRAN_DATE,this.getReversalObj().getLocalTranDate());                  //DE12 
            vISOMsg.set(Elements.LOCAL_TRAN_TIME,this.getReversalObj().getLocalTranTime());                  //DE13
            vISOMsg.set(Elements.EXPIRATION_DATE,this.getReversalObj().getAccountExpiryDate());              //DE14
            vISOMsg.set(Elements.SETTLEMENT_DATE,this.getReversalObj().getSettleDate());                     //DE15
            vISOMsg.set(Elements.MERCHANT_TYPE,this.getReversalObj().getMerchantType());                     //DE18
            vISOMsg.set(Elements.POS_ENTRY_MODE,this.getReversalObj().getPosEntryMode());                    //DE22
            vISOMsg.set(Elements.POS_CONDITION_CODE,this.getReversalObj().getPosConditionCode());            //DE25
            vISOMsg.set(Elements.REPONSE_CODE,responseCode);                                                 //DE39                                                //DE39 
            vISOMsg.set(Elements.CARD_ACCEPT_TERMINAL_ID,this.getReversalObj().getTerminalId());             //DE41
            vISOMsg.set(Elements.CARD_ACCEPT_ID_CODE,this.getReversalObj().getMerchantId());                 //DE42
            vISOMsg.set(Elements.CARD_ACCEPT_NAME_LOC,this.getReversalObj().getCardAcceptorNameLocation());  //DE43
            vISOMsg.set(Elements.CURRENCY_CODE,this.getReversalObj().getCurrencyCodeTransaction());          //DE49
            vISOMsg.set(Elements.MESSAGE_REASON_CODE,this.getReversalObj().getMessageReasonCode());          //DE56
            vISOMsg.set(Elements.ORIGINAL_DATA_ELEMENTS,this.getReversalObj().getOriginalDataElements());    //DE90
            vISOMsg.set(Elements.POS_DATA_CODE,this.getReversalObj().getPosDataCode());                      //DE123
            
            if (this.getReversalObj().getField127() != null){
                ISOMsg f127 = new ISOMsg(127);
                f127.setPackager(this.getvISOPackager());
                if (this.getReversalObj().getField127().getRoutingInfo()!=null)
                    f127.set(3,  this.getReversalObj().getField127().getRoutingInfo());
                if (this.getReversalObj().getField127().getOriginalKey()!=null)
                    f127.set(11, this.getReversalObj().getField127().getOriginalKey());
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
    public ReversalObject getReversalObj() {
        return reversalObj;
    }

    
}
