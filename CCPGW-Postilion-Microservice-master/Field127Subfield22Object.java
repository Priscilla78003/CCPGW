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
package com.truteq.ccpgw.adapter.postilion.requests.objects;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Field127Subfield22Object {

    private final String currencyCodeBill;         //127.22.051_CURRENCY_CODE_BILL
    
    private final String convRateCardHolderBill;   //127.22.010_CONV_RATE_CARDHOLDER_BILL
    
    private final String amountCardHolderBill;     //127.22.006_AMOUNT_CARDHOLDER_BILL
    
    private final String originPosEntryMode;       //127.22.OriginalPosEntryMode
       
    private final String Sms;                      //DE127.22.Sms          
            
    private final String termAppIsoRecIndicator;   //DE127.22.TermApp.ISO:ReconciliationIndicator
            
    private final String postilionMetaData;        //DE127.22.Postilion:MetaData         
    
    private final String postilionActiveActiveData;//DE127.22.Postilion:ActiveActiveData
    
    
    /**
     * =========================================================================
     * Constructor
     * =========================================================================
     * @param currencyCodeBill
     * @param convRateCardHolderBill
     * @param amountCardHolderBill
     * @param originPosEntryMode
     * @param Sms
     * @param termAppIsoRecIndicator
     * @param postilionMetaData
     * @param postilionActiveActiveData 
     * =========================================================================
     */
    public Field127Subfield22Object(String currencyCodeBill,
                                    String convRateCardHolderBill,
                                    String amountCardHolderBill,
                                    String originPosEntryMode,
                                    String Sms,
                                    String termAppIsoRecIndicator,
                                    String postilionMetaData,
                                    String postilionActiveActiveData){
    this.currencyCodeBill = currencyCodeBill;
    
    this.convRateCardHolderBill = convRateCardHolderBill;
    
    this.amountCardHolderBill = amountCardHolderBill;
    
    this.originPosEntryMode = originPosEntryMode;
       
    this.Sms = Sms;  
            
    this.termAppIsoRecIndicator = termAppIsoRecIndicator;
            
    this.postilionMetaData = postilionMetaData;      
    
    this.postilionActiveActiveData= postilionActiveActiveData;    
    }
    
    
    private String createField22KeyValuePair(String key, String value ){
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

    /**
     * @return the currencyCodeBill
     */
    public String getCurrencyCodeBill() {
        return createField22KeyValuePair("051_CURRENCY_CODE_BILL", currencyCodeBill);
    }

    /**
     * @return the convRateCardHolderBill
     */
    public String getConvRateCardHolderBill() {
        return createField22KeyValuePair("010_CONV_RATE_CARDHOLDER_BILL",convRateCardHolderBill);
    }

    /**
     * @return the amountCardHolderBill
     */
    public String getAmountCardHolderBill() {
        return createField22KeyValuePair("006_AMOUNT_CARDHOLDER_BILL",amountCardHolderBill);
    }

    /**
     * @return the originPosEntryMode
     */
    public String getOriginPosEntryMode() {
        return createField22KeyValuePair("OriginalPosEntryMode",originPosEntryMode);
    }

    /**
     * @return the Sms
     */
    public String getSms() {
        return createField22KeyValuePair("Sms",Sms);
    }

    /**
     * @return the termAppIsoRecIndicator
     */
    public String getTermAppIsoRecIndicator() {
        return createField22KeyValuePair("TermApp.ISO:ReconciliationIndicator",termAppIsoRecIndicator);
    }

    /**
     * @return the postilionMetaData
     */
    public String getPostilionMetaData() {
        return createField22KeyValuePair("Postilion:MetaData",postilionMetaData);
    }

    /**
     * @return the postilionActiveActiveData
     */
    public String getPostilionActiveActiveData() {
        return createField22KeyValuePair("Postilion:ActiveActiveData",postilionActiveActiveData);
    }


}
