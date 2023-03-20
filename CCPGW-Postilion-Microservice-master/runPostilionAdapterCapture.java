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
import com.truteq.ccpgw.adapter.postilion.requests.objects.CaptureObject;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.protocol.MessageException;
import org.jpos.iso.ISOException;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class runPostilionAdapterCapture {

    private static final LogWrapper mLogger = new LogWrapper(runPostilionAdapterSignOnOff.class);

    public static void main(String[] args) throws ServiceException, MessageException, ISOException {

        PostilionAdapter postilionAdapter = new PostilionAdapter();

        postilionAdapter.start();

        postilionAdapter.doSignOnOff(eAuth.SIGNON);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            mLogger.error("Exception on the thread sleep.", ex);
        }
        
        CaptureObject captureObj = new CaptureObject(eAuth.CAPTURE,
                                            "",
                                            "4300060000000169",//primaryAccountNumber,     //DE2                                                           
                                            "", //processingCode,           //DE3  
                                            "000000012500", //transactionAmount,        //DE4  
                                            "1712", //accountExpiryDate, YYMM   //DE14
                                            "4511", //merchantType,             //DE18 
                                            "010", //posEntryMode              //DE22
                                            "59", //posConditionCode          //DE25 
                                            "70130200000", //acquiringInstitutionCode  //DE32
                                            "", //retrievalReferenceNumber, //DE37
                                            "", //authorizationIdResponse,  //DE38
                                            "401PGK05", //terminalId,               //DE41 
                                            "4001PGK00000005", //merchantId,               //DE42
                                            "BSP MERCHANT PGK       POM            PG", //cardAcceptorNameLocation, //DE43 
                                            "598", //currencyCodeTransaction,  //DE49
                                            "", //originalDataElements,     //DE90
                                            "660550600001190", //posDataCode,              //DE123 
                                            null
        );
        
        postilionAdapter.doCapture(captureObj);
    }

}
