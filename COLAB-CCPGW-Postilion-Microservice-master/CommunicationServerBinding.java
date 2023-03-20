/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Communication server
 * ***************************************************************
 * Used to communicate with different adapters 
 * Support: Grant O'Reilly gbo@truteq.com OR grant@platformpac.com.pg
 * V01.00.00  29-Jum-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.communication.server.binding;

import com.truteq.ccpgw.comms.server.model.CommsServerMessage;
import com.truteq.ccpgw.comms.server.model.Terminate;
import com.truteq.ccpgw.communication.server.Node;
import java.io.IOException;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class CommunicationServerBinding extends Node {
    
    private final int port;
    
    public CommunicationServerBinding(int port) {
        super(port);
        this.port = port;
    }
    
    public String sendMessage(CommsServerMessage message) throws IOException, ClassNotFoundException{
        write(message);
        Object obj = waitForResponse();
        CommsServerMessage mrec = (CommsServerMessage)obj;
        return mrec.toJson();        
    }
    
//    public Request sendMessage(Message message, String from) throws IOException, ClassNotFoundException{
//        write(message);
//        Object obj = waitForResponse();
//        Message mrec = (Message)obj;
//        return mrec.getRequest();        
//    }
    
     public void asktoShutDownServer() throws IOException, ClassNotFoundException{
         write(new Terminate());
    }    

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }
    
}

