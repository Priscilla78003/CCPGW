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
package com.truteq.ccpgw.adapter.postilion.tests;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Field22KeyValueTest{
   
    public String createField22KeyValuePair(String key, String value ){
        //----------------------------------------------------------------------
        // 221 : 2 length of length (21), 21 length of "ThirdPartyBillPayment"
        // "221ThirdPartyBillPayment";
        //----------------------------------------------------------------------
        StringBuilder field22=  new StringBuilder();
        field22.append(getDigitCount(key)).append(key.length()).append(key);
        //----------------------------------------------------------------------
        // 3125 : 3 length of (125), 125 length of 
        // "221ThirdPartyBillPayment";
        //"3125<ThirdPartyBillPayment><BillPaymentRequest><ReferenceId>1111111111</ReferenceId></BillPaymentRequest></ThirdPartyBillPayment>";
        //----------------------------------------------------------------------
        field22.append(getDigitCount(value)).append(value.length()).append(value); 
        
        return field22.toString();        
    }
            
    private int getDigitCount(String key){

        if ((key.length() > 0)&&(key.length() <= 9))            return 1;
        if ((key.length() > 9)&&(key.length() < 100))           return 2;
        if ((key.length() >= 100)&&(key.length() < 1000))       return 3;
        if ((key.length() >= 1000)&&(key.length() < 10000))     return 4;
        if ((key.length() >= 10000)&&(key.length() < 100000))   return 5;
        if ((key.length() >= 100000)&&(key.length() < 1000000)) return 6;
        return 0;
    }    
    
    
    public static void main(String[] args) {
        String key = "ThirdPartyBillPayment";
        String value = "<ThirdPartyBillPayment><BillPaymentRequest><ReferenceId>1111111111</ReferenceId></BillPaymentRequest></ThirdPartyBillPayment>";
        
        Field22KeyValueTest test = new Field22KeyValueTest();
        
        String output = test.createField22KeyValuePair(key, value);
        
        System.out.println(output);
        
        key = "Sms";
        value = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Sms><AcqInstCountryCode>826</AcqInstCountryCode><AcqInstIdCode>492911</AcqInstIdCode><FwdInstIdCode>492900</FwdInstIdCode><NwrkId>0002</NwrkId><TranId>307144494870343</TranId><SysTrace>370888</SysTrace><RetrievalRefNr>714414370888</RetrievalRefNr><MessageStatusFlags>4C8410</MessageStatusFlags></Sms>";
        output = test.createField22KeyValuePair(key, value);
        
        System.out.println(output);
        
    }
}
