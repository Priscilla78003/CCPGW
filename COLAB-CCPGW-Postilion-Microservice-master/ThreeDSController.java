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
package com.truteq.ccpgw.transaction.manager.microservice.threeds.controller;

import com.google.gson.Gson;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.netcetera.model.PaReqCreationRequest;
import com.truteq.ccpgw.netcetera.model.PaReqCreationResponse;
import com.truteq.ccpgw.netcetera.model.PaResValidationRequest;
import com.truteq.ccpgw.netcetera.model.PaResValidationResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import com.truteq.general.util.AESEncryptionDecryption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
@RestController
@RequestMapping("/transaction/manager/3ds/v1/service/")
public class ThreeDSController {

    @Value(value = "${certificate.keystore}")
    private String keystore;
    @Value(value = "${netcetera.keystore.password}")
    private String encryptedkeystorepassword;
    @Value(value = "${spring.encryption.secret}")        
    private String secret;
    @Value(value = "${netcetera.create.pareq}")
    private String netceteraCreatePareq;
    @Value(value = "${netcetera.validate.paresponse}")
    private String netceteraValidatePares;
    @Value(value = "${debug}")
    private boolean debug;

    private final LogWrapper mLogger = new LogWrapper(ThreeDSController.class);

    /**
     * =========================================================================
     * CreatePaReqIfEnrolled
     * =========================================================================
     *
     * @param paReq
     * @return
     */
    /*==========================================================================
    Curl example call for CreatePaReqIfEnrolled
    ============================================================================
    curl -d '{
            "cardholder": {
               "pan": "341502098634895",
               "expiry": "1612"
             },
             "purchase": {
               "amount": "10000",
               "currency": "756"
             },
             "merchant": {
               "acquirerBin": "123456",
               "countryCode": "100",
               "id": "merId",
               "name": "Merchant Name",
               "url": "http://url.net"
             }
            }' -H 'Content-Type: application/json' http://10.160.12.81:9078/transaction/manager/3ds/v1/service/createPaReqIfEnrolled
    
    Test Server: http://localhost:9078/transaction/manager/3ds/v1/service/createPaReqIfEnrolled
    =======================================================================*/
    @PostMapping("createPaReqIfEnrolled")
    @CrossOrigin(origins = "*")
    public PaReqCreationResponse CreatePaReqIfEnrolled(@RequestBody PaReqCreationRequest paReq) {
        mLogger.info("received PA Request for merchant: " + paReq.getMerchant().getName());

        //Communicator comms = new Communicator("3dss-preview-tech-user","W7Ldsmy6fTW3qrP","3DS-Organization-ID","cd9cf664-4fd1-4acb-bb49-1453c087b7fa");
        //Result result = comms.sendHttpPost("https://3dss-preview-server.extranet.netcetera.biz/mpi/v1/createPaReqIfEnrolled", paReq.toJSON().getBytes());
        //Result result = comms.sendHttpPost("https://3dss.prev.netcetera-payment.ch/3ds-server/mpi/v1/createPaReqIfEnrolled", paReq.toJSON().getBytes());
        //SSLCommunicator sslComms = new SSLCommunicator("/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/a93e6455-3ffc-498d-9d64-84fa22dc7095/mycerts.p12");
        //Result result = sslComms.sendHttpPost("https://3dss.prev.netcetera-payment.ch/3ds-server/mpi/v1/createPaReqIfEnrolled", paReq.toJSON().getBytes());        
        String keystorepassword = "";
        try {
            AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
            keystorepassword = decryptor.decrypt(encryptedkeystorepassword, secret);         
        } catch (Exception ex) {
            mLogger.error("Exception with decryption credentials." + ex, new Throwable().getStackTrace()[0]);
        }
        
        SSLCommunicator sslComms = new SSLCommunicator(keystore,keystorepassword);
        Result result = sslComms.sendHttpPost(netceteraCreatePareq, paReq.toJSON().getBytes());
        
        mLogger.info(result.getComments() + " " + result.getData());

        Gson gson = new Gson();

        PaReqCreationResponse paResponse = gson.fromJson(result.getData(), PaReqCreationResponse.class);

        return paResponse;

    }

    /**
     * =========================================================================
     * ValidatePaRes
     * =========================================================================
     *
     * @param paResValidReq
     * @return
     */
    /*==========================================================================
    Curl example call for ValidatePaRes
    ============================================================================
    curl -d '{
                "paRes" : "4j6h89g4kd92ks2==",
                "sessionData" : {
                  "threeDSecurePAReq" : {
                    "message" : [ {
                      "pAReq" : {
                        "version" : "1.0.2",
                        "merchant" : {
                          "acqBIN" : "123456",
                          "merID" : "merId",
                          "name" : "the merchant name",
                          "country" : "756",
                          "url" : "http://groovy-merchant.com"
                        },
                        "purchase" : {
                          "xid" : "aXql9ldTUg7OHzBSHJax",
                          "date" : "20131015 16:45:00",
                          "amount" : "725",
                          "currency" : "756",
                          "exponent" : "2",
                          "desc" : "This is the description"
                        },
                        "cH" : {
                          "acctID" : "accountId",
                          "expiry" : "1612"
                        }
                      },
                      "id" : "pa9d4eb548-db62-43b7-8cc8-8731ac202de2"
                    } ]
                  },
                  "paReqCreationTime" : "20131015 16:45:01",
                  "pan" : "0000000000005432",
                  "scheme" : "Visa",
                  "acsUrl" : "http://www.acs-url.com",
                  "masterCardTokenized" : "false"
                }
            }' -H 'Content-Type: application/json' http://localhost:9078/transaction/manager/3ds/v1/service/validatePaRes
    
    Test Server: http://10.160.12.81:9078/transaction/manager/3ds/v1/service/validatePaRes
    =======================================================================*/
    @PostMapping("validatePaRes")
    @CrossOrigin(origins = "*")
    public PaResValidationResponse ValidatePaRes(@RequestBody PaResValidationRequest paResValidReq) {
        mLogger.info("received PA Validate Request from ACS URL" + paResValidReq.getSessionData().getAcsUrl());

        //Communicator comms = new Communicator("3dss-preview-tech-user","W7Ldsmy6fTW3qrP","3DS-Organization-ID","cd9cf664-4fd1-4acb-bb49-1453c087b7fa");
        //Result result = comms.sendHttpPost("https://3dss-preview-server.extranet.netcetera.biz/mpi/v1/validatePaRes", paResValidReq.toJSON().getBytes());
        //Result result = comms.sendHttpPost("https://3dss.prev.netcetera-payment.ch/3ds-server/mpi/v1/validatePaRes", paResValidReq.toJSON().getBytes());
//        SSLCommunicator sslComms = new SSLCommunicator("/home/grant/Truteq-Gogs-Server-Repo/CCPGW-Postilion-Microservice/ccpgw-transaction-manager-microservice/certificates/a93e6455-3ffc-498d-9d64-84fa22dc7095/mycerts.p12");
//        Result result = sslComms.sendHttpPost("https://3dss.prev.netcetera-payment.ch/3ds-server/mpi/v1/validatePaRes", paResValidReq.toJSON().getBytes());  
        
        String keystorepassword = "";
        try {
            AESEncryptionDecryption decryptor = new AESEncryptionDecryption();
            keystorepassword = decryptor.decrypt(encryptedkeystorepassword, secret);         
        } catch (Exception ex) {
            mLogger.error("Exception with decryption credentials." + ex, new Throwable().getStackTrace()[0]);
        }
        
        SSLCommunicator sslComms = new SSLCommunicator(keystore,keystorepassword);
        Result result = sslComms.sendHttpPost(netceteraValidatePares, paResValidReq.toJSON().getBytes());
        
        mLogger.info(result.getComments() + " " + result.getData());

        Gson gson = new Gson();

        PaResValidationResponse paResValidResponse = gson.fromJson(result.getData(), PaResValidationResponse.class);

        return paResValidResponse;

    }

}
