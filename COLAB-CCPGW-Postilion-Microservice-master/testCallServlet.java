/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.functional.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testCallServlet {
    
    public void CallServlet(String urlString) throws MalformedURLException, IOException{
        
        URL aURL = new URL(urlString);  
        URLConnection aConnection = aURL.openConnection();  
        aConnection.setDoInput(true);  
        aConnection.setDoOutput(true);  
        aConnection.setUseCaches(false);  
        aConnection.setDefaultUseCaches(false);  
        aConnection.setRequestProperty("Content-Type", "text/xml");
        
    }
    
    public static void main(String[] args) throws IOException {
        testCallServlet test = new testCallServlet();
        test.CallServlet("https://localhost:9087/transaction/manager/servlet/acs");
    }
}
