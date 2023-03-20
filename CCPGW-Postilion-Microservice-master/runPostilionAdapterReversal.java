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
import com.truteq.ccpgw.adapter.postilion.requests.objects.ReversalObject;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.protocol.MessageException;
import org.jpos.iso.ISOException;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class runPostilionAdapterReversal {
    private static final LogWrapper mLogger = new LogWrapper(runPostilionAdapterReversal.class);
    
    public static void main(String[] args) throws ServiceException, MessageException, ISOException {  
        
        PostilionAdapter postilionAdapter = new PostilionAdapter();
        postilionAdapter.start();
        postilionAdapter.doSignOnOff(eAuth.SIGNON);
        try {
                Thread.sleep(5000);
        } catch (InterruptedException ex) {
                mLogger.error("Exception on the thread sleep.", ex);
        }

        ReversalObject reversalObj = new ReversalObject(eAuth.REVERSAL,
                                                        "",
                                                        "4300060000000169", //primaryAccountNumber,    //DE2                                                           
                                                        "000000", //processingCode,          //DE3  
                                                        "000000045000",//transactionAmount,  // DE4 
                                                        "",
                                                        "",
                                                        "",
                                                        "1712", //accountExpiryDate, YYMM    //DE14
                                                        "1014", // settleDate,               //DE15 
                                                        "", 
                                                        "010", //posEntryMode                //DE22
                                                        "59",  //posConditionCode            //DE25 
                                                        "401PGK05", //terminalId,            //DE41 
                                                        "4001PGK00000005",//merchantId,      //DE42
                                                        "BSP MERCHANT PGK       POM            PG", //cardAcceptorNameLocation, //DE43 
                                                        "598", //currencyCodeTransaction,    //DE49
                                                        "4021", //messageReasonCode,         //DE59
                                                        "020000159310181415316013020000000000000000",//originalDataElements,     //DE90 
                                                        "",
                                                        null                      
                                                        );
        
        
        postilionAdapter.doReversal(reversalObj);
    }    
}
