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

import com.truteq.ccpgw.communication.server.controller.GatewayController;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class runController {

    public static void main(String[] args) {
        Thread thread = new Thread(new GatewayController());
        thread.start();
    }
    
}
