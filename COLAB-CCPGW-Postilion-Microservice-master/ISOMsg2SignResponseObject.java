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

import static com.truteq.ccpgw.adapter.postilion.requests.element.interfaces.Elements.*;
import com.truteq.ccpgw.adapter.postilion.requests.objects.SignResponseObject;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import org.jpos.iso.ISOComponent;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ISOMsg2SignResponseObject {
    
    private final LogWrapper mLogger = new LogWrapper(ISOMsg2SignResponseObject.class);
    
    private String messageType;            //DE0

    private String transmissionDateTime;   //DE7
    
    private String systemTraceAuditNumber; //DE11

    private String localTranTime;          //DE12

    private String localTranDate;          //DE13
    
    private String responseCode;           //DE39
    
    private String networkManInfoCode;     //DE70
    
    
    public ISOMsg2SignResponseObject(){
        
    }
    
    public SignResponseObject signResponseObject(ISOMsg isoMsg){
        
        try {
            getSignObject(isoMsg);
        } catch (ISOException ex) {
            mLogger.error("Exception: Error converting ISOMsg to ReversalObject" + ex, new Throwable().getStackTrace()[0]);
        }
        
        SignResponseObject signRespObj = new SignResponseObject(this.messageType,
                                                                this.transmissionDateTime,
                                                                this.systemTraceAuditNumber,
                                                                this.localTranTime,
                                                                this.localTranDate,
                                                                this.responseCode,
                                                                this.networkManInfoCode);
        return signRespObj;
        
    }    
    
    private void getSignObject(ISOMsg isoMsg) throws ISOException{
        
        for (int i=0; i<=isoMsg.getMaxField(); i++ ){
           ISOComponent compo = isoMsg.getComponent(i);
           if (compo instanceof ISOMsg){
             getSignObject((ISOMsg) compo);  
           }
           else{
           if(compo != null)
                 updateFields(compo.getFieldNumber(),(String)compo.getValue()); 
           }
        }
        
    }
    
    private void updateFields(int fieldnumber, Object value){
        
      this.setMessageType("0810");  
      switch(fieldnumber){
 
        case PRIMARY_BITMAP : break;                                            //DE1 
        case TRANSMISSION_DATE_TIME : this.setTransmissionDateTime((String)value); break;     //DE7
        case SYSTEM_TRACE_AUDIT_NUMBER : this.setSystemTraceAuditNumber((String)value); break;//DE11
        case LOCAL_TRAN_TIME : this.setLocalTranTime((String)value); break;             //DE12
        case LOCAL_TRAN_DATE : this.setLocalTranDate((String)value); break;             //DE13        
        case REPONSE_CODE : this.setResponseCode((String)value); break;                 //DE39 
        case NETWORK_MANAGEMENT_INFO_CODE : this.setNetworkManInfoCode((String)value); break; //DE70
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
     * @return the networkManInfoCode
     */
    public String getNetworkManInfoCode() {
        return networkManInfoCode;
    }

    /**
     * @param networkManInfoCode the networkManInfoCode to set
     */
    public void setNetworkManInfoCode(String networkManInfoCode) {
        this.networkManInfoCode = networkManInfoCode;
    }
    
}
