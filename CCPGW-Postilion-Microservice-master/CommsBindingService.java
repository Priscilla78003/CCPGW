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
package com.truteq.ccpgw.comms.server.ws.async.services;

import com.truteq.ccpgw.comms.server.ws.async.bindings.CommunicationServerBinding;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@Service
public class CommsBindingService {
    
    
    private final LogWrapper mLogger = new LogWrapper(CommsBindingService.class);
    
    private String commsserverHostname;
    
    public CommsBindingService(@Value("${comms.server}") String commsserverHostname){
        this.commsserverHostname = commsserverHostname;
    } 

    /**
     * @param name
     * @return the binding
     */
    public CommunicationServerBinding getBinding(String name) {
        mLogger.info("Connecting to Comms Server ... "+this.getCommsserverHostname());
        return new CommunicationServerBinding(this.getCommsserverHostname(),16011, name);
    }

    /**
     * @return the commsserverHostname
     */
    public String getCommsserverHostname() {
        return commsserverHostname;
    }

    /**
     * @param commsserverHostname the commsserverHostname to set
     */
    public void setCommsserverHostname(String commsserverHostname) {
        this.commsserverHostname = commsserverHostname;
    }
       
    
}
