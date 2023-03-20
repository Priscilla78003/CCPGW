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
package com.truteq.ccpgw.transaction.manager.comms;

import com.google.gson.Gson;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Communicator {

    private final LogWrapper mLogger = new LogWrapper(Communicator.class);
    
    private String username = "";
    private String password = "";
    private String idKey = "";
    private String idValue = "";

    
    public Communicator(){

    }
    
    public Communicator(String username, String password){
        this.username = username;
        this.password = password;
    }    
     
    public Communicator(String username, String password, String idKey, String idValue){
        this(username,password);
        this.idKey = idKey;
        this.idValue = idValue;   
    }  
    
    public Communicator(boolean debug, String idKey, String idValue) {
        this.idKey = idKey;
        this.idValue = idValue;
    }
    
    private String getAuthHeaderValue(){
        String auth = this.username + ":" + this.password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8)); 
        String authHeaderValue = "Basic " + new String(encodedAuth);
        return authHeaderValue;
    }
    
    public Result sendHttpPost(String url, byte[] b) {  //, Merchant postparameter
        StringBuilder response = new StringBuilder();
        try {
            
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            
            if ((!this.idKey.equals(""))&&(!this.idValue.equals(""))){
               //con.setRequestProperty("3DS-Organization-ID", "cd9cf664-4fd1-4acb-bb49-1453c087b7fa");
               mLogger.info("Setting Header ID: "+idKey+" = "+idValue, new Throwable().getStackTrace()[0]);
               con.setRequestProperty(idKey, idValue);
            }            
            
            if ((!this.username.equals(""))&&(!this.password.equals(""))){
               con.setRequestProperty("Authorization", getAuthHeaderValue());
            }
            
            
            
            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(b);//postparameter.toJSON().getBytes()
            os.flush();
            os.close();
            // For POST only - END            
            
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                mLogger.debug("Response:"+response.toString(), new Throwable().getStackTrace()[0]);
            } else {
                mLogger.info("POST request did not work.", new Throwable().getStackTrace()[0]);
            }
            
            con.disconnect();
            
            
        } catch (MalformedURLException ex) {
             mLogger.error("Exception the URL is malformed: "+ex, new Throwable().getStackTrace()[0]);
             
        } catch (IOException ex) {
            mLogger.error("Exception on the Http GET request : "+ex, new Throwable().getStackTrace()[0]);
        }

        Result result = processResponse(response.toString());
        return result;
    }
    
    public Result sendHttpsPost(String url, byte[] b, String keystore) {
        
        StringBuilder response = new StringBuilder();
        try {
            KeyStore keyStore  = KeyStore.getInstance("PKCS12"); 
            
            char[] keyStorePassword = "Pl@tf0rmp@c".toCharArray();
            try(InputStream keyStoreData = new FileInputStream(keystore)){ 
                keyStore.load(keyStoreData, keyStorePassword);  
            } catch (NoSuchAlgorithmException | CertificateException ex) {
                mLogger.error("Certificate exception with loading key store: "+ex, new Throwable().getStackTrace()[0]);
            }

            SSLContext sslContext = SSLContexts.custom()
                //.loadKeyMaterial(keyStore, "mycerts".toCharArray())
                .loadKeyMaterial(keyStore, "Pl@tf0rmp@c".toCharArray())    
                .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[] {"SSL","TLSv1.3","TLSv1.2"}, //new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.STRICT_HOSTNAME_VERIFIER);            

            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setSSLSocketFactory(sslContext.getSocketFactory());
            
//            con.setHostnameVerifier(new HostnameVerifier() {
//
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//                });             
            
  
            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(b);//postparameter.toJSON().getBytes()
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
                mLogger.debug("Response:"+response.toString(), new Throwable().getStackTrace()[0]);                    
            } else {
                mLogger.info("POST request did not work.", new Throwable().getStackTrace()[0]);
            }
             
            con.disconnect();
            
        } catch (MalformedURLException ex) {
             mLogger.error("Exception the URL is malformed: "+ex, new Throwable().getStackTrace()[0]);   
        } catch (IOException ex) {
            mLogger.error("Exception on the Http GET request : "+ex, new Throwable().getStackTrace()[0]);
        } catch (KeyStoreException ex) {
            mLogger.error("Error reading keystore: "+ex, new Throwable().getStackTrace()[0]);
        } catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
//        StringBuilder response = new StringBuilder();
//        try {
//            
//            URL obj = new URL(url);
//            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Accept", "application/json");
//            con.setRequestProperty("Content-Type", "application/json");
//            
//            if ((!this.idKey.equals(""))&&(!this.idValue.equals(""))){
//               //con.setRequestProperty("3DS-Organization-ID", "cd9cf664-4fd1-4acb-bb49-1453c087b7fa");
//               con.setRequestProperty(idKey, idValue);
//            }            
//            
//            if ((!this.username.equals(""))&&(!this.password.equals(""))){
//               con.setRequestProperty("Authorization", getAuthHeaderValue());
//            }
//            
//            con.setHostnameVerifier(new HostnameVerifier() {
//
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//                });            
//            
//            // For POST only - START
//            con.setDoOutput(true);
//            OutputStream os = con.getOutputStream();
//            os.write(b);//postparameter.toJSON().getBytes()
//            os.flush();
//            os.close();
//            // For POST only - END            
//            
//            int responseCode = con.getResponseCode();
//
//            if (responseCode == HttpsURLConnection.HTTP_OK) { // success
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        con.getInputStream()));
//                String inputLine;
//                
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//                mLogger.info("Response:"+response.toString(), new Throwable().getStackTrace()[0]);
//            } else {
//                mLogger.info("POST request did not work.", new Throwable().getStackTrace()[0]);
//            }
//            
//            con.disconnect();
//            
//            
//        } catch (MalformedURLException ex) {
//             mLogger.error("Exception the URL is malformed: "+ex, new Throwable().getStackTrace()[0]);
//             
//        } catch (IOException ex) {
//            mLogger.error("Exception on the Http GET request : "+ex, new Throwable().getStackTrace()[0]);
//        }

        Result result = processResponse(response.toString());
        return result;
    }    
    
    private Result processResponse(String resp){
        
        if (resp.contains("Access forbidden!")){
            return new  Result(403, "Error", resp);
        }
        else{
            Gson gson = new Gson(); 
            Result result = new Result(200,"Success",resp);
            return result;
        }  
    }
    
}