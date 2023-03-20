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
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class SignObject extends RequestObject {
    
    private LogWrapper mLogger = new LogWrapper(SignObject.class);
  
    private String networkManInfoCode;//DE70
    
    public SignObject(eAuth auth, String sysTraceAuditNumber){
        super(auth, sysTraceAuditNumber);
        
        switch(auth){
            case SIGNON  : 
                           setNetworkManInfoCode("001");
                           mLogger.debug("Signing On .....");
                           break;
            case SIGNOFF : 
                           setNetworkManInfoCode("002");
                           mLogger.debug("Signing Off .....");
                           break;  
            case ECHO    : 
                           setNetworkManInfoCode("301");
                           mLogger.debug("Echo request .....");
                           break;            
        }        
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
