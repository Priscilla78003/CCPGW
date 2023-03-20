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

import com.google.gson.Gson;
import com.truteq.ccpgw.transaction.manager.comms.Communicator;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testAuthorisationRequest {
    
    public static void main(String[] args) {
        
          
          //echo -n | openssl s_client -connect ipgw.testbsp.com.pg:443 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > ~/ipgw.testbsp.com.pg.crt
          //keytool -import -v -trustcacerts -alias ipgw.testbsp.com.pg -file ~/ipgw.testbsp.com.pg.crt -keystore "$JAVA_HOME/jre/lib/security/cacerts" -keypass changeit -storepass changeit

          AuthoriseRequest authObj = new AuthoriseRequest(  "4896780109909073",//primaryAccountNumber,    //DE2                                                           
                                                            "000000",          //processingCode,          //DE3  
                                                            "000000012300",    //transactionAmount,       // DE4  
                                                            "2306",            //accountExpiryDate, YYMM  //DE14
                                                            "6300",            //merchantType,            //DE18 
                                                            "010",             //posEntryMode             //DE22
                                                            "59",              //posConditionCode         //DE25 
                                                            "60130200000",     //acquiringInstitutionCode //DE32 
                                                            "423PGK11",        //terminalId,              //DE41 
                                                            "4023PGK00000011", //merchantId,              //DE42
                                                            "PHA Health Assurance LiPOM            PG", //cardAcceptorNameLocation, //DE43 
                                                            "598",             //currencyCodeTransaction, //DE49
                                                            "660550600001192", //posDataCode,             //DE123
                                                            "532",             //cvv2,                  //DE127.10
                                                            "",                //avd                    //DE127.15 
                                                            "",                //cvd                    //DE127.27  
                                                            "",                //AmericanExpressCardIdentifier, ////DE127.28 
                                                            null,              //threeDSecureData,        //DE127.29
                                                            "2",               //threeDSecureResult,      //DE127.30
                                                            null,
                                                            null);         
        
        Communicator comms = new Communicator("CCPGW-Postilion-switch-admin","CCPGW_P0st1l10n_sw1tch_4dm1n_0421");
        
        //Result result = comms.sendHttpPost("http://localhost:9077/postilion/restful/service/authorise", authObj.toJSON().getBytes());
        Result result = comms.sendHttpPost("https://ipgw.testbsp.com.pg/postilion/restful/service/authorise", authObj.toJSON().getBytes());
        
        System.out.println(result.getComments()+" "+result.getData());
        
        Gson gson = new Gson();
        
        AuthoriseResponse response = gson.fromJson(result.getData(), AuthoriseResponse.class);
        
        System.out.println(response.toJSON());
        

    }
}
