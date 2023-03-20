/*
 * ***************************************************************
 * Truteq CAMEL Diameter Gateway version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 
 * ***************************************************************
 *  CAP-Gw
 *  SS7 CAP component for Truteq CAMEL Diameter Gateway project 
 *  Support: Grant O'Reilly gbo@truteq.com
 *  V01.00.00  11-Sep-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.communication.server.main;

import com.truteq.ccpgw.communication.server.ServerContainer;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class runCommunicationServer {
    
    public static void main(String[] argv) throws Exception {

        ServerContainer server = new ServerContainer("comm-server.properties");
        server.start();
        
    }       
    
}
