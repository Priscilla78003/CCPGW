/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Postilion Adapter: POSTILION - Transaction Manager Adapter 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.adapter.postilion.test;

import com.truteq.ServiceException;
import com.truteq.ccpgw.adapter.postilion.PostilionAdapter;
import com.truteq.ccpgw.adapter.postilion.enums.eAuth;
import com.truteq.ccpgw.adapter.postilion.requests.objects.AuthoriseObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.Field127Object;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.protocol.MessageException;
import org.jpos.iso.ISOException;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class runPostilionAdapterMOTO {
    private static final LogWrapper mLogger = new LogWrapper(runPostilionAdapterMOTO.class);
    
    public static void main(String[] args) throws ServiceException, MessageException, ISOException {  
        
        PostilionAdapter postilionAdapter = new PostilionAdapter();
        postilionAdapter.start();
        postilionAdapter.doSignOnOff(eAuth.SIGNON);
        try {
                Thread.sleep(5000);
        } catch (InterruptedException ex) {
                mLogger.error("Exception on the thread sleep.", ex);
        }
        AuthoriseObject authObj = new AuthoriseObject(  eAuth.MOTO,
                                                        "",
                                                        "4300060000000169",//primaryAccountNumber,    //DE2                                                           
                                                        "",                //processingCode,          //DE3  
                                                        "000000012500",    //transactionAmount,       //DE4  
                                                        "1712",            //accountExpiryDate, YYMM  //DE14
                                                        "4511",            //merchantType,            //DE18 
                                                        "010",             //posEntryMode             //DE22
                                                        "",                //posConditionCode         //DE25 
                                                        "70130200000",     //acquiringInstitutionCode //DE32 
                                                        "401PGK05",        //terminalId,              //DE41 
                                                        "4001PGK00000005", //merchantId,              //DE42
                                                        "BSP MERCHANT PGK       POM            PG", //cardAcceptorNameLocation, //DE43 
                                                        "598",             //currencyCodeTransaction, //DE49
                                                        "660550600001190", //posDataCode,             //DE123 
                                                        new Field127Object("",      //messageLengthIndicator,   //DE127
                                                                            null,   //bitmap,                   //DE127.1 
                                                                            "246",  //cvv2,                     //DE127.10
                                                                            "",  //Address Verification Data//DE127.15
                                                                            "",  //Card Verification Data//DE127.27
                                                                            "", //AmericanExpressCardIdentifier,//DE127.28 
                                                                            null, //threeDSecureData,         //DE127.29
                                                                            "",   //threeDSecureResult,       //DE127.30
                                                                            null,   //ucafData                  //DE127.32
                                                                            null) //field 22
                                                        );          
        postilionAdapter.doAuthorisation(authObj);

    }     
}
