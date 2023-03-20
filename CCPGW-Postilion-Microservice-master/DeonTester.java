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
package com.truteq.ccpgw.transaction.manager.microservice.manager;

import java.net.URL;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class DeonTester {
  public static void main(String[] args) throws Exception {
    //System.setProperty("javax.net.ssl.trustStore", "/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/a93e6455-3ffc-498d-9d64-84fa22dc7095/mytruststore.jks");
    System.setProperty("javax.net.ssl.trustStorePassword", "trustcerts");

    //System.setProperty("javax.net.ssl.keyStore", "/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/a93e6455-3ffc-498d-9d64-84fa22dc7095/mycerts.jks");
    System.setProperty("javax.net.ssl.keyStorePassword", "netcetera");

    java.io.InputStreamReader mUTF8InputStream = null;
    java.io.BufferedReader mUTF8BuffReader = null;

    URL url = new URL("https://3dss.prev.netcetera-payment.ch/3ds-server/mpi/v1/createPaReqIfEnrolled");
    java.io.InputStream urlInput = url.openStream();
    mUTF8InputStream = new java.io.InputStreamReader(urlInput);
    mUTF8BuffReader = new java.io.BufferedReader(mUTF8InputStream);
    java.lang.String vLine = mUTF8BuffReader.readLine();
    while (vLine != null) {
      System.out.println(vLine);
      vLine = mUTF8BuffReader.readLine();
    }
  }
}
