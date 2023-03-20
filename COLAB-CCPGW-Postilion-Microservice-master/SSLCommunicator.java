/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  18-June-2021 
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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class SSLCommunicator {
    
    private final LogWrapper mLogger = new LogWrapper(SSLCommunicator.class);
    
    private final String keystore;
    private final String keystorePassword;

    private String username = "";
    private String password = "";    
    
    public SSLCommunicator(String keystore, String keystorePassword){
        this.keystore = keystore;
        this.keystorePassword = keystorePassword;
    }
    
    public SSLCommunicator(String username, String password, String keystore, String keystorePassword){
        this(keystore,keystorePassword);
        this.username = username;
        this.password = password;        
    }
    
    private String getAuthHeaderValue(){
        String auth = this.username + ":" + this.password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8)); 
        String authHeaderValue = "Basic " + new String(encodedAuth);
        return authHeaderValue;
    }    
    

    public Result sendHttpPost(String url, byte[] b) {
        StringBuilder response = new StringBuilder();
        try {
            KeyStore keyStore  = KeyStore.getInstance("PKCS12"); 
            char[] keyStorePassword = this.keystorePassword.toCharArray();
            try(InputStream keyStoreData = new FileInputStream(this.keystore)){ 
                keyStore.load(keyStoreData, keyStorePassword);  
            } catch (NoSuchAlgorithmException | CertificateException ex) {
                mLogger.error("Certificate exception with loading key store: "+ex, new Throwable().getStackTrace()[0]);
            }

            SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, this.keystorePassword.toCharArray()) 
                .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[] {"TLSv1.3","TLSv1.2"}, //new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);            
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            if ((!this.username.equals(""))&&(!this.password.equals(""))){
               con.setRequestProperty("Authorization", getAuthHeaderValue());
            }            
                        
            con.setSSLSocketFactory(sslContext.getSocketFactory());
  
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
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            mLogger.error("Error reading keystore: "+ex, new Throwable().getStackTrace()[0]);
        }

        Result result = processResponse(response.toString());
        return result;
    }
    
    public Result sendHttpPost(String url, byte[] b, String type) {
        StringBuilder response = new StringBuilder();
        try {
            KeyStore keyStore  = KeyStore.getInstance("PKCS12"); 
            char[] keyStorePassword = this.keystorePassword.toCharArray();
            try(InputStream keyStoreData = new FileInputStream(this.keystore)){ 
                keyStore.load(keyStoreData, keyStorePassword);  
            } catch (NoSuchAlgorithmException | CertificateException ex) {
                mLogger.error("Certificate exception with loading key store: "+ex, new Throwable().getStackTrace()[0]);
            }

            SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, this.keystorePassword.toCharArray()) 
                .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[] {"TLSv1.3","TLSv1.2"}, //new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);            
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Content-Type", type); //"application/x-www-form-urlencoded"
            
            if ((!this.username.equals(""))&&(!this.password.equals(""))){
               con.setRequestProperty("Authorization", getAuthHeaderValue());
            }            
                        
            con.setSSLSocketFactory(sslContext.getSocketFactory());
  
            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(b);//postparameter.toJSON().getBytes()
            os.flush();
            os.close();
            // For POST only - END            
            
            int responseCode = con.getResponseCode();

            mLogger.debug("Response Code:"+responseCode , new Throwable().getStackTrace()[0]);  
            
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
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            mLogger.error("Error reading keystore: "+ex, new Throwable().getStackTrace()[0]);
        }

        Result result = processResponse(response.toString());
        return result;
    }    
    
    public Result sendHttpPostNoCert(String url, byte[] b, String type) {
        StringBuilder response = new StringBuilder();
        try {
            SSLContext sslContext = SSLContexts.custom()
                .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[] {"TLSv1.3","TLSv1.2"}, //new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);            
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Content-Type", type); //"application/x-www-form-urlencoded"
            
            if ((!this.username.equals(""))&&(!this.password.equals(""))){
               con.setRequestProperty("Authorization", getAuthHeaderValue());
            }            
                        
            con.setSSLSocketFactory(sslContext.getSocketFactory());
  
            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(b);//postparameter.toJSON().getBytes()
            os.flush();
            os.close();
            // For POST only - END            
            
            int responseCode = con.getResponseCode();

            mLogger.debug("Response Code:"+responseCode , new Throwable().getStackTrace()[0]);  
            
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
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            mLogger.error("Error reading keystore: "+ex, new Throwable().getStackTrace()[0]);
        }

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
