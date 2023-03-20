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
import com.truteq.ccpgw.adapter.postilion.requests.objects.SignResponseObject;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class SignResponse extends PostilionRequest{

    private final LogWrapper mLogger = new LogWrapper(SignResponse.class);
    private final SignResponseObject signRespObj;  
    
    public SignResponse(ISOPackager vISOPackager, SignResponseObject signRespObj) {
        super(vISOPackager); 
        this.signRespObj = signRespObj;
    }

    @Override
    public ISOMsg getUnpacked() throws com.truteq.protocol.MessageException {

        try {
            ISOMsg vISOMsg = new ISOMsg();
            vISOMsg.setPackager(this.getvISOPackager());
            vISOMsg.setMTI(this.getSignRespObj().getMessageType());
            vISOMsg.set(Elements.TRANSMISSION_DATE_TIME,this.getSignRespObj().getTransmissionDateTime());
            vISOMsg.set(Elements.SYSTEM_TRACE_AUDIT_NUMBER,this.getSignRespObj().getSystemTraceAuditNumber());
            vISOMsg.set(Elements.LOCAL_TRAN_DATE,this.getSignRespObj().getLocalTranDate());
            vISOMsg.set(Elements.LOCAL_TRAN_TIME,this.getSignRespObj().getLocalTranTime());
            vISOMsg.set(Elements.REPONSE_CODE, this.getSignRespObj().getResponseCode());
            vISOMsg.set(Elements.NETWORK_MANAGEMENT_INFO_CODE,this.getSignRespObj().getNetworkManInfoCode());
 
            return vISOMsg;

        } catch (ISOException vException) {
            mLogger.error(vException.getMessage(), vException, new Throwable().getStackTrace()[0]);
            throw new com.truteq.protocol.MessageException(vException.getMessage());
        }

    }    

    /**
     * @return the signRespObj
     */
    public SignResponseObject getSignRespObj() {
        return signRespObj;
    }
    
}
