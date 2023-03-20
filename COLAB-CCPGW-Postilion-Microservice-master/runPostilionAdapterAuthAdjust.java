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
public class runPostilionAdapterAuthAdjust {
    
    private static final LogWrapper mLogger = new LogWrapper(runPostilionAdapterAuthAdjust.class);
    
    public static void main(String[] args) throws ServiceException, MessageException, ISOException {  
        
        PostilionAdapter postilionAdapter = new PostilionAdapter();
        postilionAdapter.start();
        postilionAdapter.doSignOnOff(eAuth.SIGNON);
        try {
                Thread.sleep(5000);
        } catch (InterruptedException ex) {
                mLogger.error("Exception on the thread sleep.", ex);
        }

        byte[] ds = "AADE3C7DBD99C3EC74A1DF224555555555555555".getBytes();

        byte byte1 = (byte)0b10000000;
        byte byte2 = (byte)0b01000000;
        byte byte3 = (byte)0b00000000;
        byte byte4 = (byte)0b00000000;
        byte byte5 = (byte)0b00000000;
        byte byte6 = (byte)0b00000000;
        byte byte7 = (byte)0b00000000;
        byte byte8 = (byte)0b00000000;

        byte[] bytesArr = {byte1,
                           byte2,
                           byte3,
                           byte4,
                           byte5,
                           byte6,
                           byte7,
                           byte8};

        AuthoriseObject authObj = new AuthoriseObject(  eAuth.AUTH_ADJ,
                                                        "",
                                                        "4896780109909073",//primaryAccountNumber,    //DE2                                                           
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
                                                        new Field127Object("",  //messageLengthIndicator,  //DE127
                                                                           bytesArr,  //bitmap,                  //DE127.1 
                                                                           "532", //cvv2,                  //DE127.10
                                                                           "",  //Address Verification Data//DE127.15   
                                                                           "",  //Card Verification Data//DE127.27
                                                                           "",  //AmericanExpressCardIdentifier, ////DE127.28 
                                                                           ds,  //threeDSecureData,        //DE127.29
                                                                           "2", //threeDSecureResult,      //DE127.30
                                                                           null,  //ucafData                 //DE127.32
                                                                           null)//field 22   
                                                    ); 
        
        postilionAdapter.doAuthorisation(authObj);
    }    
}
