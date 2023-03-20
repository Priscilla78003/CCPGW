/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ipgw.test.postilion.comms;

import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.RoutingObj;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestExtractSubfieldDE127_03 {
    
    public RoutingObj extractSubfieldsDE127_03(String routing){

        String source = routing.substring(0, 12);
        String sink = routing.substring(12, 24);
        String sourceStan = routing.substring(24, 30);
        String sinkStan = routing.substring(30, 36);
        String totalsGroup = routing.substring(36, 48);
        
        RoutingObj routingObj = new RoutingObj(source,sink,sourceStan,sinkStan,totalsGroup);
        
        return routingObj;
        
    }    
    
    public static void main(String[] args) {
        TestExtractSubfieldDE127_03  test = new TestExtractSubfieldDE127_03 ();
        
        //RoutingObj routingObj = test.extractSubfieldsDE127_03("PlatformPSrcVISASnk     000024505948VISATotals              ");
        //RoutingObj routingObj = test.extractSubfieldsDE127_03("PlatformPSrcVISASnk     000189506059VISATotals  ");
        RoutingObj routingObj = test.extractSubfieldsDE127_03("HyprcmAppSrcFCUBSPOSSnk 000285000285PacificCardT");
        System.out.println(routingObj.toJSON());
    }
    
}
