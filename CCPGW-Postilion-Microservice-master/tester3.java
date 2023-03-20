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

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.beans.factory.annotation.Value;

public class tester3 {

    @Value("${certificate.keystore}")
    private static String certKeystore;

    public static void main(String[] args) throws Exception {
        //System.setProperty("javax.net.ssl.trustStore", "/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/a93e6455-3ffc-498d-9d64-84fa22dc7095/mytruststore.jks");
        //System.setProperty("javax.net.ssl.trustStorePassword", "trustcerts");

        //System.setProperty("javax.net.ssl.keyStore", "/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/a93e6455-3ffc-498d-9d64-84fa22dc7095/mycerts.jks");
        //System.setProperty("javax.net.ssl.keyStorePassword", "mycerts");
        /*java.io.InputStreamReader mUTF8InputStream = null;
        java.io.BufferedReader mUTF8BuffReader = null;

        String payload
                = "{\n"
                + "  \"Cardholder\": {\n"
                + "    \"pan\": \"341502098634895\",\n"
                + "    \"expiry\": \"1612\"\n"
                + "  },\n"
                + "  \"Purchase\": {\n"
                + "    \"amount\": \"10000\",\n"
                + "    \"currency\": \"756\"\n"
                + "  },\n"
                + "  \"Merchant\": {\n"
                + "    \"acquirerBin\": \"123456\",\n"
                + "    \"countryCode\": \"100\",\n"
                + "    \"id\": \"merId\",\n"
                + "    \"name\": \"Merchant Name\",\n"
                + "    \"url\": \"http://url.net\"\n"
                + "  }\n"
                + "}";

        StringBuilder response = new StringBuilder();

        try {

            //File crtFile = new File("/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/a93e6455-3ffc-498d-9d64-84fa22dc7095/a93e6455-3ffc-498d-9d64-84fa22dc7095.crt");
            //Certificate certificate = CertificateFactory.getInstance("X.509").generateCertificate(new FileInputStream(crtFile));
            // Or if the crt-file is packaged into a jar file:
            //Certificate certificate = CertificateFactory.getInstance("X.509").generateCertificate(SSLCommunicator.class.getClassLoader().getResourceAsStream(certificatePath));
            //KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            //keyStore.load(null, null);
//        char[] keyStorePassword = "changeit".toCharArray();  
//        try(InputStream keyStoreData = new FileInputStream("/home/grant/Apps/jdk1.8.0_40/jre/lib/security/cacerts")){ 
//            keyStore.load(keyStoreData, keyStorePassword);  
//        }          
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            char[] keyStorePassword = "mycerts".toCharArray();
            try (InputStream keyStoreData = new FileInputStream(certKeystore)) {
                keyStore.load(keyStoreData, keyStorePassword);
            }
            //keyStore.setCertificateEntry("3dssprevca", certificate);

            //TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            //trustManagerFactory.init(keyStore);
            //SSLContext sslContext = SSLContext.getInstance("TLS");
            //sslContext.init(null, trustManagerFactory.getTrustManagers(), null);   
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, "mycerts".toCharArray())
                    //.loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()) //custom trust store
                    .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); //TODO        

            //System.setProperty("https.protocols", "TLSv1,TLSv1.2,TLSv1.1,SSLv3");
            URL obj = new URL("https://3dss.prev.netcetera-payment.ch/3ds-server/mpi/v1/createPaReqIfEnrolled");
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setSSLSocketFactory(sslcontext.getSocketFactory());

            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(payload.getBytes());//postparameter.toJSON().getBytes()
            os.flush();
            os.close();
            // For POST only - END            

            int responseCode = con.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Response:" + response.toString());
            } else {
                System.out.println("POST request did not work.");
            }

        } catch (MalformedURLException ex) {
            System.out.println("Exception the URL is malformed: " + ex);

        } catch (IOException ex) {
            System.out.println("Exception on the Http GET request : " + ex);
        }
*/
    }
}
