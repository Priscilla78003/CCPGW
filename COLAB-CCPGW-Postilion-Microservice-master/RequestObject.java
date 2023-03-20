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

public abstract class RequestObject {
    
    private eAuth auth; 
    
    private String messageType;            //DE0

    private String transmissionDateTime;   //DE7
    
    private String systemTraceAuditNumber; //DE11

    private String localTranTime;          //DE12

    private String localTranDate;          //DE13    

    public RequestObject(eAuth auth, String sysTraceAuditNumber){
        this.auth = auth;
        
        switch(auth){
            
            case SIGNON   :
            case SIGNOFF  :  
            case ECHO     :  this.setMessageType("0800");
                             break;
                             
            case AUTH     :  this.setMessageType("0100");
                             break;
                             
            case AUTH_ADJ :  this.setMessageType("0120");
                             break;
                             
            case FINANCIAL:
            case REFUND   :
            case MOTO     :    
                             this.setMessageType("0200");
                             break;
                             
            case CREDIT   :   
            case DEBIT    :                 
            case CAPTURE  :
                             this.setMessageType("0220");
                             break;  
                             
            case REVERSAL :  this.setMessageType("0420");
                             break;                
        }         
        
        long vCurrentTime = System.currentTimeMillis();
        setSystemTraceAuditNumber(zeroFill(sysTraceAuditNumber, 6));
        setTransmissionDateTime(new java.text.SimpleDateFormat("MMddHHmmss").format(vCurrentTime));//MMDDhhmmss
        setLocalTranDate(new java.text.SimpleDateFormat("MMdd").format(vCurrentTime));             //MMDD
        setLocalTranTime(new java.text.SimpleDateFormat("HHmmss").format(vCurrentTime));           //hhmmss        
    }
    
    public RequestObject(eAuth auth, 
                         String sysTraceAuditNumber,
                         String transmissionDateTime,
                         String localTranTime,
                         String localTranDate
                         ){
        this.auth = auth;
        
        switch(auth){
            
            case SIGNON   :
            case SIGNOFF  :  
            case ECHO     :  this.setMessageType("0800");
                             break;
                             
            case AUTH     :  this.setMessageType("0100");
                             break;
                             
            case AUTH_ADJ :  this.setMessageType("0120");
                             break;
                             
            case FINANCIAL:
            case REFUND   :
            case MOTO     :    
                             this.setMessageType("0200");
                             break;
                             
            case CREDIT   :   
            case DEBIT    :                 
            case CAPTURE  :
                             this.setMessageType("0220");
                             break;  
                             
            case REVERSAL :  this.setMessageType("0420");
                             break;                
        }         
        
        this.systemTraceAuditNumber = sysTraceAuditNumber;
        this.transmissionDateTime = transmissionDateTime;
        this.localTranTime = localTranTime;
        this.localTranDate = localTranDate;     
    }    
    
    
    private String zeroFill (String aValue, int aMaxFieldSize) {

              if (aValue == null || aValue.isEmpty()) {

                      StringBuilder vBuilder = new StringBuilder ();

                      for (int vIndx = 0 ; vIndx < aMaxFieldSize; vIndx++) {

                              vBuilder.append("0");
                      }

                      return vBuilder.toString();

              } else if (aValue.length() == aMaxFieldSize) {
                      return aValue;
              } else if (aValue.length() < aMaxFieldSize) {
                      StringBuilder vBuilder = new StringBuilder ();

                      for (int vIndx = 0 ; vIndx < (aMaxFieldSize - aValue.length()); vIndx++) {

                              vBuilder.append("0");
                      }

                      return vBuilder.append(aValue).toString();
              } else {
                      return aValue.substring(0, aMaxFieldSize);
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
     * @return the systemTraceAuditNumber
     */
    public String getSystemTraceAuditNumber() {
        
        while (systemTraceAuditNumber.length() < 6){
           systemTraceAuditNumber = "0"+systemTraceAuditNumber; 
        }
        
        return systemTraceAuditNumber;
    }

    /**
     * @param systemTraceAuditNumber the systemTraceAuditNumber to set
     */
    public void setSystemTraceAuditNumber(String systemTraceAuditNumber) {
        this.systemTraceAuditNumber = systemTraceAuditNumber;
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
     * @return the auth
     */
    public eAuth getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(eAuth auth) {
        this.auth = auth;
    }
    
}
