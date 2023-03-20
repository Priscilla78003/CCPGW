/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Notification Manager Microservice : Notification Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  26-May-2022 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.notification.microservice.threeds.controller;

import com.google.gson.Gson;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerAuthenticationRequest;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerAuthenticationResponse;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerTransaction;
import com.truteq.ccpgw.threeds.v2.objects.results.response.ACSRenderingType;
import com.truteq.ccpgw.threeds.v2.objects.results.response.ResultsRequest;
import com.truteq.ccpgw.threeds.v2.objects.results.response.ResultsResponse;
import com.truteq.ccpgw.threeds.v2.objects.results.response.ThreeDSServerResultsResponse;
import com.truteq.ccpgw.threeds.v2.objects.versioning.Enrolled;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import com.truteq.general.util.AESEncryptionDecryption;
import java.util.Base64;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
@RestController
@RequestMapping("/notification/manager/3ds/v2/service/")
public class ThreeDSV2ResultsResponse {

    private LogWrapper mLogger = new LogWrapper(ThreeDSV2ResultsResponse.class);
    
    @Value(value = "${certificate.keystore}")
    private String keystoreNetcetera;
    @Value("${transaction.manager.certificate}")
    private String keystoreTransactionManager;    
    @Value("${transaction.manager.platformpac.keystore.password}")
    private String keystorepasswordplatformpac;   
     @Value("${communicator.secret}")
    private String secret;  
    @Value(value = "${transaction.manager.3ds.v2.service.authentication.request.read}")
    private String threedsV2AuthReqRead;
    @Value(value = "${transaction.manager.3ds.v2.authorise}")
    private String threedsV2Authorise;  
    @Value(value = "${certificate.keystore}")
    private String certLocation;
    
    @Value(value = "${transaction.manager.3ds.v2.service.tds.transaction.read}")
    private String threedsV2TransactionRead;
    @Value(value = "${transaction.manager.3ds.v2.service.tds.transaction.write}")
    private String threedsV2TransactionWrite;
    @Value(value = "${transaction.manager.3ds.v2.service.tds.transaction.update}")
    private String threedsV2TransactionUpdate;
    @Value(value = "${transaction.manager.3ds.v2.service.tds.result.response.write}")
    private String threedsV2ResultResponseWrite;
    @Value(value = "${transaction.manager.3ds.v2.service.tds.enrolled.read}")
    private String threedsV2EnrolledRead;    
    
    
    @PostMapping("result/response")
    @CrossOrigin(origins = "*")
    public Result getResultsResponse (@RequestBody ThreeDSServerResultsResponse RRes) {
        getmLogger().info("received 3DS v2.x Result Response for 3DS Server: " + RRes.toJSON());
        
        Result result = new Result(200,"Success",RRes.toJSON());

        getmLogger().info(result.getComments() + " " + result.getData());
        
        return result;

    }    
  
    @PostMapping("3dsresult/response")
    @CrossOrigin(origins = "*")
    public ThreeDSServerResultsResponse getThreeDSServerResultsResponse (@RequestBody ThreeDSServerResultsResponse RRes) {

        AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
        String decryptedkeystorepasswordplatformpac = decryptor.decrypt(getKeystorepasswordplatformpac(), secret);
        Result result = new Result(200,"Success",RRes.toJSON());

        getmLogger().info("\n");
        getmLogger().info("========================================================");
        getmLogger().info("Received 3DS v2.x Result Response from ACS Server");
        getmLogger().info("========================================================");
        getmLogger().info(result.getComments() + " " + result.getData());
        
        Gson gson = new Gson();

        ThreeDSServerResultsResponse threeDSResponse = gson.fromJson(result.getData(), ThreeDSServerResultsResponse.class);
        
        getmLogger().info("Persistiong Result Response to database");
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        sslComms.sendHttpPost(this.getThreedsV2ResultResponseWrite(),threeDSResponse.toJSON().getBytes());
        

        return threeDSResponse;

    }      
    
    @PostMapping("3dsnotification/response")
    @CrossOrigin(origins = "*")
    public String getThreeDSServerNotification (@RequestParam MultiValueMap<String,String> paramMap){
        
        AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
        String decryptedkeystorepasswordplatformpac = decryptor.decrypt(getKeystorepasswordplatformpac(), secret);

        getmLogger().info("\n");
        getmLogger().info("========================================================");
        getmLogger().info("Received 3DS v2.x Notification from Netcetera Server");
        getmLogger().info("========================================================");
        Map<String, String> map = paramMap.toSingleValueMap();
        
        for (String key : map.keySet()) {
            String value = map.get(key);
            getmLogger().info("KeyValuePair :"+key+" "+value);          
        }  

        String cres  = map.get("cres");
        
        CResObject creqObj = (CResObject)DecodeAndFindValue(cres, CTypeEnum.CRES);
        
        ThreeDSServerTransaction tdsTransaction = new ThreeDSServerTransaction(creqObj.getThreeDSServerTransID(),
                                                                               null,
                                                                               null,
                                                                               null,
                                                                               cres);
        
        getmLogger().info("Updating ThreeDSServerTransaction :"+tdsTransaction.toJSON());
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        sslComms.sendHttpPost(this.getThreedsV2TransactionUpdate(),tdsTransaction.toJSON().getBytes());
        
        ThreeDSServerTransaction threeDSServerTransaction = getThreeDSServerTransactionFromDatabase(decryptedkeystorepasswordplatformpac,
                                                                                                    tdsTransaction);
        
        
        String payload = "PaRes="+threeDSServerTransaction.getCres()+"&MD="+threeDSServerTransaction.getMd();
        getmLogger().info("Redirecting back to: "+threeDSServerTransaction.getTermUrl()+" with payload = "+payload);
        SSLCommunicator sslComms2 = new SSLCommunicator("","");
        Result result = sslComms2.sendHttpPostNoCert(threeDSServerTransaction.getTermUrl(), payload.getBytes(), "application/x-www-form-urlencoded");
        
        return result.getData();

    }
         
    private ThreeDSServerTransaction getThreeDSServerTransactionFromDatabase(String decryptedkeystorepasswordplatformpac,
                                                                             ThreeDSServerTransaction tdsTransaction){
        getmLogger().info("Getting ThreeDSServerTransaction from database.");
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);

        Result result = sslComms.sendHttpPost(this.getThreedsV2TransactionRead(),tdsTransaction.toJSON().getBytes());
        getmLogger().info("3DS v2.x transaction from database :"+result.getData());
        Gson gson = new Gson();
        return  gson.fromJson(result.getData(), ThreeDSServerTransaction.class);
    }
   
    private ThreeDSServerAuthenticationRequest getAuthReqFromDatabase(String decryptedkeystorepasswordplatformpac,
                                          ThreeDSServerTransactionIndex tdsTrans){
        getmLogger().info("Getting AuthReq from database.");
        
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        Result result = sslComms.sendHttpPost(this.getThreedsV2AuthReqRead(), tdsTrans.toJSON().getBytes());
        getmLogger().info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]); 
        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]); 
        
        Gson gson = new Gson();
        ThreeDSServerAuthenticationRequest AuthReq = gson.fromJson(result.getData(),ThreeDSServerAuthenticationRequest.class);
        
        AuthReq.setSdkInformation(null);
        AuthReq.setMessageExtension(null);
        AuthReq.setChallengeMessageExtension(null);
        
        return AuthReq;
    }
    
    private Enrolled getThreeDSEnrolledFromDatabase(String decryptedkeystorepasswordplatformpac, ThreeDSServerTransactionIndex tdsTrans){
        getmLogger().info("Getting Enrolled from database.");
        
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        Result result = sslComms.sendHttpPost(this.getThreedsV2EnrolledRead(), tdsTrans.toJSON().getBytes()); 
        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        
        Gson gson = new Gson();
        Enrolled enrolledResponse = gson.fromJson(result.getData(), Enrolled.class);
        
        return enrolledResponse;        
    }
    
    private ThreeDSServerAuthenticationResponse runThreeDSAuthorise(String decryptedkeystorepasswordplatformpac,
                                                                    ThreeDSServerAuthenticationRequest AuthReq){
        getmLogger().info("Running 3DS Authorisation.");
        
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        Result result = sslComms.sendHttpPost(this.getThreedsV2Authorise(), AuthReq.toJSON().getBytes());
        getmLogger().debug(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]); 
        
        Gson gson = new Gson();
        ThreeDSServerAuthenticationResponse authResponse = gson.fromJson(result.getData(), ThreeDSServerAuthenticationResponse.class);
        
        return authResponse;
    }
    
    /**
     * =========================================================================
     * Challenge
     * =========================================================================
     * @param authRes
     * @return 
     * ========================================================================= 
     */ 
    private String Challenge(ThreeDSServerAuthenticationResponse authRes){
        String payload = "creq="+authRes.getBase64EncodedChallengeRequest();

        SSLCommunicator sslComms = new SSLCommunicator(this.getCertLocation(),"mycerts");
        Result result = sslComms.sendHttpPost(authRes.getAcsURL().toString(), payload.getBytes(),"application/x-www-form-urlencoded");
        getmLogger().info(result.getComments()+" "+result.getData());
        
        String challengeForm = result.getData().replaceFirst("action=\"challenge/otp\"","action=\"https://3dss.extranet.netcetera.biz/3dss-demo/acs/challenge/otp\"");
        
        getmLogger().info("Updated form: "+challengeForm);
        
        return challengeForm;         
    }
    
 
    
    /**
     * =========================================================================
     * Enrolled
     * =========================================================================
     * @param authRes
     * @return 
     * ========================================================================= 
     */ 
    private String Enrolled(ThreeDSServerAuthenticationResponse authRes){
        
        AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
        String decryptedkeystorepasswordplatformpac = decryptor.decrypt(getKeystorepasswordplatformpac(), secret);
        
        ThreeDSServerResultsResponse threeDSResponse = new ThreeDSServerResultsResponse();
        threeDSResponse.setThreeDSServerTransID(authRes.getThreeDSServerTransID());
        threeDSResponse.setAuthenticationValue(authRes.getAuthenticationValue());
        threeDSResponse.setEci(authRes.getEci());
        threeDSResponse.setTransStatus(authRes.getTransStatus());
        
        ResultsRequest rreq = new ResultsRequest(authRes.getThreeDSServerTransID(),    
                          authRes.getAuthenticationResponse().getAcsTransID(),    
                          new ACSRenderingType("01","01"),
                          "02",    
                          "02",    
                          authRes.getAuthenticationValue(),    
                          authRes.getAuthenticationResponse().getDsTransID(),    
                          authRes.getEci(),
                          "",    
                          "01",    
                          "RReq",    
                          "2.1.0",    
                          authRes.getTransStatus());
                          
                
        ResultsResponse rres = new ResultsResponse(authRes.getThreeDSServerTransID(), 
                           authRes.getAuthenticationResponse().getAcsTransID(),
                           authRes.getAuthenticationResponse().getDsTransID(), 
                           "RRes",
                           "2.1.0",
                           "01");        

        threeDSResponse.setResultsRequest(rreq);
        threeDSResponse.setResultsResponse(rres);
        
        getmLogger().info("Persisting Result Response to database");
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        sslComms.sendHttpPost(this.getThreedsV2ResultResponseWrite(),threeDSResponse.toJSON().getBytes());           
        
        AResObject aresObj = new AResObject(authRes.getAuthenticationResponse().getMessageType(),
                      authRes.getThreeDSServerTransID(),
                      authRes.getAuthenticationResponse().getAcsTransID(),
                      "Y",
                      authRes.getAuthenticationResponse().getMessageVersion(),
                      authRes.getTransStatus());
        
        byte[] encodedBytes = Base64.getEncoder().encode(aresObj.toJSON().getBytes());
        String encodedString = new String(encodedBytes);
        
        ThreeDSServerTransaction tdsTransaction = new ThreeDSServerTransaction(authRes.getThreeDSServerTransID(),
                                                                               null,
                                                                               null,
                                                                               null,
                                                                               encodedString);
        
        getmLogger().info("Updating ThreeDSServerTransaction :"+tdsTransaction.toJSON());
        SSLCommunicator sslComms2 = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        sslComms2.sendHttpPost(this.getThreedsV2TransactionUpdate(),tdsTransaction.toJSON().getBytes());
        
        ThreeDSServerTransaction threeDSServerTransaction = getThreeDSServerTransactionFromDatabase(decryptedkeystorepasswordplatformpac,
                                                                                                    tdsTransaction);
        
        
        String payload = "PaRes="+threeDSServerTransaction.getCres()+"&MD="+threeDSServerTransaction.getMd();
        getmLogger().info("Redirecting back to: "+threeDSServerTransaction.getTermUrl()+" with payload = "+payload);
        SSLCommunicator sslComms3 = new SSLCommunicator("","");
        Result result = sslComms3.sendHttpPostNoCert(threeDSServerTransaction.getTermUrl(), payload.getBytes(), "application/x-www-form-urlencoded");
        
        return result.getData();       
    }    
     /**
     * =========================================================================
     * NoTransactionStatusIssueType
     * =========================================================================
     * @param issueType
     * @param authRes
     * @return 
     * ========================================================================= 
     */    
    private String NoTransactionStatusIssueType(String issueType, ThreeDSServerAuthenticationResponse authRes){
        AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
        String decryptedkeystorepasswordplatformpac = decryptor.decrypt(getKeystorepasswordplatformpac(), secret);
        
        ThreeDSServerResultsResponse threeDSResponse = new ThreeDSServerResultsResponse();
        threeDSResponse.setThreeDSServerTransID(authRes.getThreeDSServerTransID());
        
        ResultsRequest rreq = new ResultsRequest(authRes.getThreeDSServerTransID(),    
                          "",    
                          new ACSRenderingType("01","01"),
                          "02",    
                          "02",    
                          "",    
                          "",    
                          "",
                          "",    
                          "01",    
                          "RReq",    
                          "2.1.0",    
                          issueType);
                          
                
        ResultsResponse rres = new ResultsResponse(authRes.getThreeDSServerTransID(), 
                           "",
                           "", 
                           "RRes",
                           "2.1.0",
                           "01");        

        threeDSResponse.setResultsRequest(rreq);
        threeDSResponse.setResultsResponse(rres);
        
        getmLogger().info("Persisting Result Response to database");
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        sslComms.sendHttpPost(this.getThreedsV2ResultResponseWrite(),threeDSResponse.toJSON().getBytes());           
        
        AResObject aresObj = new AResObject("",
                      authRes.getThreeDSServerTransID(),
                      "",
                      "N",
                      "",
                      issueType);
        
        byte[] encodedBytes = Base64.getEncoder().encode(aresObj.toJSON().getBytes());
        String encodedString = new String(encodedBytes);
        
        ThreeDSServerTransaction tdsTransaction = new ThreeDSServerTransaction(authRes.getThreeDSServerTransID(),
                                                                               null,
                                                                               null,
                                                                               null,
                                                                               encodedString);
        
        getmLogger().info("Updating ThreeDSServerTransaction :"+tdsTransaction.toJSON());
        SSLCommunicator sslComms2 = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        sslComms2.sendHttpPost(this.getThreedsV2TransactionUpdate(),tdsTransaction.toJSON().getBytes());
        
        ThreeDSServerTransaction threeDSServerTransaction = getThreeDSServerTransactionFromDatabase(decryptedkeystorepasswordplatformpac,
                                                                                                    tdsTransaction);
        
        
        String payload = "PaRes="+threeDSServerTransaction.getCres()+"&MD="+threeDSServerTransaction.getMd();
        getmLogger().info("Redirecting back to: "+threeDSServerTransaction.getTermUrl()+" with payload = "+payload);
        SSLCommunicator sslComms3 = new SSLCommunicator("","");
        Result result = sslComms3.sendHttpPostNoCert(threeDSServerTransaction.getTermUrl(), payload.getBytes(), "application/x-www-form-urlencoded");
        
        return result.getData();           
    }
    
    
    /**
     * =========================================================================
     * NonEnrolled
     * =========================================================================
     * @param authRes
     * @return 
     * ========================================================================= 
     */ 
    private String NonEnrolled(ThreeDSServerAuthenticationResponse authRes){
        getmLogger().info("Processing Non-Enrolled card");
        return NoTransactionStatusIssueType("ENROLL_N",authRes);
    }    
    
    /**
     * =========================================================================
     * AResError
     * =========================================================================
     * @param authRes
     * @return 
     * ========================================================================= 
     */ 
    private String AResError(ThreeDSServerAuthenticationResponse authRes){
        getmLogger().info("Processing ARes ERROR on Enrolled card");
        return NoTransactionStatusIssueType("ARES_ERROR",authRes);
    }     
    
    /**
     * =========================================================================
     * ThreeDSTransactionStatus
     * =========================================================================
     * @param authRes
     * @return 
     * ========================================================================= 
     */     
    private String ThreeDSTransactionStatus(ThreeDSServerAuthenticationResponse authRes,
                                            Enrolled enrolled){
        
        if (enrolled.getEnrolled().equals("N")){
            return NonEnrolled(authRes);
        }        
        
        if (authRes.getTransStatus() == null){
            if (enrolled.getEnrolled().equals("Y")){
                return AResError(authRes);
            }
            
        }
        
        switch(authRes.getTransStatus()){
            case "Y":
            case "A":
            case "N": 
            case "U": 
            case "R":     
                      switch(authRes.getTransStatus()){
                        case "N": 
                                getmLogger().info("Not authenticated / Account not verified; Transaction denied: "+authRes.getTransStatus());
                                break;
                        case "U": 
                                getmLogger().info("Authentication / Account verification could not be performed; technical or other problem: "+authRes.getTransStatus()); 
                                break;
                        case "R": 
                                getmLogger().info("Authentication / Account verification Rejected. Issuer is rejecting authentication/verification and request that authorization not be attempted: "+authRes.getTransStatus());
                                break;
                        case "A":
                                getmLogger().info("Attempts processing performed; Not authenticated / verified, but a proof of attempt authentication / verification is provided: "+authRes.getTransStatus()); 
                                break;
                        case "Y":
                                getmLogger().info("Authentication / Account verification successful: "+authRes.getTransStatus());
                                break;
                      }
                      return Enrolled(authRes);

            case "C": 
                      getmLogger().info("In order to complete the authentication, a challenge is required: "+authRes.getTransStatus()); 
                      return Challenge(authRes); 

            default : return null;
        }
        
    }  
    /**
     * =========================================================================
     * AuthenticationRequest
     * =========================================================================
     * @param tdsTrans
     * @return 
     * ========================================================================= 
     */ 
    private String AuthenticationRequest(ThreeDSServerTransactionIndex tdsTrans){
        
        AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
        String decryptedkeystorepasswordplatformpac = decryptor.decrypt(getKeystorepasswordplatformpac(), secret);
        
        ThreeDSServerAuthenticationRequest AuthReq = getAuthReqFromDatabase(decryptedkeystorepasswordplatformpac,
                                                   tdsTrans);
        
        Enrolled enrolled = getThreeDSEnrolledFromDatabase(decryptedkeystorepasswordplatformpac,
                                                   tdsTrans);
        getmLogger().info("Enrolled Response :" + enrolled.toJSON(), new Throwable().getStackTrace()[0]); 

        ThreeDSServerAuthenticationResponse authRes = runThreeDSAuthorise(decryptedkeystorepasswordplatformpac,
                                                                          AuthReq);
                                                                      
        getmLogger().info("Authentication Response :" + authRes.toJSON(), new Throwable().getStackTrace()[0]); 
        
        return ThreeDSTransactionStatus(authRes,enrolled);         
    }
    
    /**
     * =========================================================================
     * DecodeAndFindValue
     * =========================================================================
     * @param encodedString
     * @param type
     * @return 
     * ========================================================================= 
     */  
    private IChallenge DecodeAndFindValue(String encodedString, CTypeEnum type){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        
        Gson gson = new Gson();
        switch (type){
            case CREQ :   return gson.fromJson(decodedString,CReqObject.class);
            case CRES :   return gson.fromJson(decodedString,CResObject.class);  
            default   :   return null;  
        }
    } 
    
    /**
     * 
     * @param paramMap
     * @return 
     */
    @PostMapping(path = "3dsmethoddata/response",
                 consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CrossOrigin(origins = "*")
    public String getThreeDSServerMethodNotify (@RequestParam MultiValueMap<String,String> paramMap) {

        //Result result = new Result(200,"Success",methodData.toJSON());

        getmLogger().info("\n");
        getmLogger().info("========================================================");
        getmLogger().info("Received 3DS v2.x Method Data response from Netcetera Server");
        getmLogger().info("========================================================");
        
        Map<String, String> map = paramMap.toSingleValueMap();
        
        String threeDSMethodData = "";
        
        for (String key : map.keySet()) {
            String value = map.get(key);
            getmLogger().info("KeyValuePair :"+key+" "+value);
            if (key.equals("threeDSMethodData")){
               threeDSMethodData = value;
            }            
        }  

        getmLogger().info("threeDSMethodData :"+threeDSMethodData);
        byte[] decodedBytes = Base64.getDecoder().decode(threeDSMethodData);
        String decodedThreeDSMethodData = new String(decodedBytes);
        getmLogger().info("Decoded ThreeDSMethodData"+decodedThreeDSMethodData);
        
        Gson gson = new Gson();
        ThreeDSServerTransactionIndex tdsTrans = gson.fromJson(decodedThreeDSMethodData, ThreeDSServerTransactionIndex.class);
        getmLogger().info("threeDSServerTransID: "+tdsTrans.getThreeDSServerTransID());
        
        return AuthenticationRequest(tdsTrans);
    }      
    
    
    @PostMapping(path = "sabre/redirect/mechanism",
                 consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CrossOrigin(origins = "*")
    public String getSabreRedirectMechanism (@RequestParam MultiValueMap<String,String> paramMap) {
        
        AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
        String decryptedkeystorepasswordplatformpac = decryptor.decrypt(getKeystorepasswordplatformpac(), secret);
        
        getmLogger().info("\n");
        getmLogger().info("========================================================");
        getmLogger().info("Received Issuer call from Sabre ..........");
        getmLogger().info("========================================================");
        
        Map<String, String> map = paramMap.toSingleValueMap();
        
        for (String key : map.keySet()) {
            String value = map.get(key);
            getmLogger().info("KeyValuePair :"+key+" "+value);
        }
        
        String threeDSMethodData  = map.get("PaReq");
        String md  = map.get("MD");
        String termUrl  = map.get("TermUrl");
        
        CReqObject creqObj = (CReqObject)DecodeAndFindValue(threeDSMethodData, CTypeEnum.CREQ);
        
        ThreeDSServerTransaction tdsTransaction = new ThreeDSServerTransaction(creqObj.getThreeDSServerTransID(),
                                                                               md,
                                                                               termUrl ,
                                                                               threeDSMethodData,
                                                                               null);
        
        getmLogger().info("Persisting ThreeDSServerTransaction : "+tdsTransaction.toJSON());
        getmLogger().info("SSLCommunicator: "+this.getKeystoreTransactionManager()+"::"+decryptedkeystorepasswordplatformpac+"::"+this.getThreedsV2TransactionWrite());
        
        SSLCommunicator sslComms = new SSLCommunicator(this.getKeystoreTransactionManager(), decryptedkeystorepasswordplatformpac);
        Result result = sslComms.sendHttpPost(this.getThreedsV2TransactionWrite(),tdsTransaction.toJSON().getBytes());
        
        getmLogger().info("Persisted ThreeDSServerTransaction to database");
        
        String payload = "threeDSMethodData="+threeDSMethodData;

        SSLCommunicator sslComms2 = new SSLCommunicator(getCertLocation(),"mycerts");
        Result result2 = sslComms2.sendHttpPost("https://3dss.extranet.netcetera.biz/3dss-demo/acs/3ds-method", payload.getBytes(),"application/x-www-form-urlencoded");
        getmLogger().info(result.getComments()+" "+result2.getData());
        
        return result2.getData();

    }     
    
    
    @GetMapping("test/redirect/mechanism")
    @CrossOrigin(origins = "*")
    public String testRedirectMechanism () {
        
        getmLogger().info("\n");
        getmLogger().info("========================================================");
        getmLogger().info("Test: Received Issuer call from Sabre ..........");
        getmLogger().info("========================================================");
        

        String payload = "threeDSMethodData=eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImViMmI2YzY5LWNjOGMtNGYwZC05NTM4LTA1OTFjODMzYzJkYSJ9";

        SSLCommunicator sslComms = new SSLCommunicator(getCertLocation(),"mycerts");
        Result result = sslComms.sendHttpPost("https://3dss.extranet.netcetera.biz/3dss-demo/acs/3ds-method", payload.getBytes(),"application/x-www-form-urlencoded");
        getmLogger().info(result.getComments()+" "+result.getData());
        
        return result.getData();

    }     

    /**
     * @return the mLogger
     */
    public LogWrapper getmLogger() {
        return mLogger;
    }

    /**
     * @param mLogger the mLogger to set
     */
    public void setmLogger(LogWrapper mLogger) {
        this.mLogger = mLogger;
    }

    /**
     * @return the keystoreNetcetera
     */
    public String getKeystoreNetcetera() {
        return keystoreNetcetera;
    }

    /**
     * @param keystoreNetcetera the keystoreNetcetera to set
     */
    public void setKeystoreNetcetera(String keystoreNetcetera) {
        this.keystoreNetcetera = keystoreNetcetera;
    }

    /**
     * @return the keystoreTransactionManager
     */
    public String getKeystoreTransactionManager() {
        return keystoreTransactionManager;
    }

    /**
     * @param keystoreTransactionManager the keystoreTransactionManager to set
     */
    public void setKeystoreTransactionManager(String keystoreTransactionManager) {
        this.keystoreTransactionManager = keystoreTransactionManager;
    }

    /**
     * @return the keystorepasswordplatformpac
     */
    public String getKeystorepasswordplatformpac() {
        return keystorepasswordplatformpac;
    }

    /**
     * @param keystorepasswordplatformpac the keystorepasswordplatformpac to set
     */
    public void setKeystorepasswordplatformpac(String keystorepasswordplatformpac) {
        this.keystorepasswordplatformpac = keystorepasswordplatformpac;
    }

    /**
     * @return the certLocation
     */
    public String getCertLocation() {
        return certLocation;
    }

    /**
     * @param certLocation the certLocation to set
     */
    public void setCertLocation(String certLocation) {
        this.certLocation = certLocation;
    }

    /**
     * @return the threedsV2Authorise
     */
    public String getThreedsV2Authorise() {
        return threedsV2Authorise;
    }

    /**
     * @param threedsV2Authorise the threedsV2Authorise to set
     */
    public void setThreedsV2Authorise(String threedsV2Authorise) {
        this.threedsV2Authorise = threedsV2Authorise;
    }

    /**
     * @return the threedsV2AuthReqRead
     */
    public String getThreedsV2AuthReqRead() {
        return threedsV2AuthReqRead;
    }

    /**
     * @param threedsV2AuthReqRead the threedsV2AuthReqRead to set
     */
    public void setThreedsV2AuthReqRead(String threedsV2AuthReqRead) {
        this.threedsV2AuthReqRead = threedsV2AuthReqRead;
    }

    /**
     * @return the threedsV2TransactionRead
     */
    public String getThreedsV2TransactionRead() {
        return threedsV2TransactionRead;
    }

    /**
     * @param threedsV2TransactionRead the threedsV2TransactionRead to set
     */
    public void setThreedsV2TransactionRead(String threedsV2TransactionRead) {
        this.threedsV2TransactionRead = threedsV2TransactionRead;
    }

    /**
     * @return the threedsV2TransactionWrite
     */
    public String getThreedsV2TransactionWrite() {
        return threedsV2TransactionWrite;
    }

    /**
     * @param threedsV2TransactionWrite the threedsV2TransactionWrite to set
     */
    public void setThreedsV2TransactionWrite(String threedsV2TransactionWrite) {
        this.threedsV2TransactionWrite = threedsV2TransactionWrite;
    }

    /**
     * @return the threedsV2TransactionUpdate
     */
    public String getThreedsV2TransactionUpdate() {
        return threedsV2TransactionUpdate;
    }

    /**
     * @param threedsV2TransactionUpdate the threedsV2TransactionUpdate to set
     */
    public void setThreedsV2TransactionUpdate(String threedsV2TransactionUpdate) {
        this.threedsV2TransactionUpdate = threedsV2TransactionUpdate;
    }

    /**
     * @return the threedsV2ResultResponseWrite
     */
    public String getThreedsV2ResultResponseWrite() {
        return threedsV2ResultResponseWrite;
    }

    /**
     * @param threedsV2ResultResponseWrite the threedsV2ResultResponseWrite to set
     */
    public void setThreedsV2ResultResponseWrite(String threedsV2ResultResponseWrite) {
        this.threedsV2ResultResponseWrite = threedsV2ResultResponseWrite;
    }

    /**
     * @return the threedsV2EnrolledRead
     */
    public String getThreedsV2EnrolledRead() {
        return threedsV2EnrolledRead;
    }

    /**
     * @param threedsV2EnrolledRead the threedsV2EnrolledRead to set
     */
    public void setThreedsV2EnrolledRead(String threedsV2EnrolledRead) {
        this.threedsV2EnrolledRead = threedsV2EnrolledRead;
    }
    
    
}
