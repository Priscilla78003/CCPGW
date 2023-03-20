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
package com.truteq.ccpgw.adapter.postilion.requests.element.interfaces;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public interface Elements {

    public static final int MESSAGE_TYPE = 0;

    public static final int PRIMARY_BITMAP = 1;               //DE1 

    public static final int PRIMARY_ACCOUNT_NUMBER = 2;       //DE2

    public static final int PROCESSING_CODE = 3;              //DE3        

    public static final int AMOUNT = 4;                       //DE4

    public static final int TRANSMISSION_DATE_TIME = 7;       //DE7

    public static final int SYSTEM_TRACE_AUDIT_NUMBER = 11;   //DE11

    public static final int LOCAL_TRAN_TIME = 12;             //DE12

    public static final int LOCAL_TRAN_DATE = 13;             //DE13        

    public static final int EXPIRATION_DATE = 14;             //DE14          

    public static final int SETTLEMENT_DATE = 15;             //DE15 

    public static final int MERCHANT_TYPE = 18;               //DE18        

    public static final int POS_ENTRY_MODE = 22;              //DE22 

    public static final int CARD_SEQ_NUMBER = 23;             //DE23 

    public static final int POS_CONDITION_CODE = 25;          //DE25         

    public static final int TRANSACTION_FEE = 28;             //DE28 

    public static final int TRANSACTION_PROCESSING_FEE = 30;  //DE30

    public static final int AQC_INSTITUDE_ID_CODE = 32;       //DE32 

    public static final int FF_INSTITUTION_ID_CODE = 33;      //DE33

    public static final int RETRIEVAL_REF_NUMBER = 37;        //DE37        

    public static final int AUTH_ID_CODE = 38;                //DE38 

    public static final int REPONSE_CODE = 39;                //DE39 

    public static final int CARD_ACCEPT_TERMINAL_ID = 41;     //DE41  

    public static final int CARD_ACCEPT_ID_CODE = 42;         //DE42 

    public static final int CARD_ACCEPT_NAME_LOC = 43;        //DE43          

    public static final int CURRENCY_CODE = 49;               //DE49 

    public static final int ADDITIONAL_AMOUNTS = 54;          //DE54
    
    public static final int MESSAGE_REASON_CODE = 56;         //DE56
    
    public static final int NETWORK_MANAGEMENT_INFO_CODE = 70;//DE70
    
    public static final int ORIGINAL_DATA_ELEMENTS = 90;      //DE90
    
    public static final int REPLACEMENT_AMOUNTS = 95;         //DE95

    public static final int RCV_INSTITUTION_ID_CODE = 100;    //DE100

    public static final int ACCOUNT_IDENTIFICATION = 102;     //DE102
    
    public static final int ACCOUNT_IDENTIFICATION_TO = 103;     //DE103 

    public static final int POS_DATA_CODE = 123;              //DE123
    
    public static final int FIELD127MLI = 127;                //DE127
    
    public static final String BITMAP = "127.001";                    //DE127.01 
    
    public static final String SWITCH_KEY = "127.002";                //DE127.02
    public static final int SWITCH_KEY_127 = 2;    
    
    public static final String ROUTING_INFORMATION = "127.003";       //DE127.03
    public static final int ROUTING_INFORMATION_127 = 3;
    
    public static final String AUTHORIZATION_PROFILE = "127.006";     //DE127.06
    public static final int AUTHORIZATION_PROFILE_127 = 6;

    public static final String CVV2 = "127.010";                      //DE127.10
    public static final int CVV2_127 = 10;
    
    public static final String ORIGINAL_KEY = "127.011";              //DE127.11
    public static final int ORIGINAL_KEY_127 = 11; 
    
    public static final String ADDRESS_VERFICATION_DATA = "127.015";  //DE127.15
    public static final int ADDRESS_VERFICATION_DATA_127 = 15;          
    
    public static final String ADDRESS_VERFICATION_RESULT = "127.016";//DE127.16
    public static final int ADDRESS_VERFICATION_RESULT_127 = 16;      
    
    public static final String AUTHORIZER_DATE_SETTLEMENT="127.020";  //DE127.20
    public static final int AUTHORIZER_DATE_SETTLEMENT_127 = 20;
    
    public static final String STRUCTURED_DATA="127.022";             //DE127.22
    public static final int STRUCTURED_DATA_127 = 22;
    
    public static final String ORIGINAL_NODE = "127.026";             //DE127.26
    public static final int ORIGINAL_NODE_127 = 26;     
    
    public static final String CARD_VERFICATION_RESULT = "127.027";   //DE127.27
    public static final int CARD_VERFICATION_RESULT_127 = 27;     
    
    public static final String THREED_SECURE_DATA = "127.29";         //DE127.29
    public static final int THREED_SECURE_DATA_127 = 29;
    
    public static final String THREED_SECURE_RESULT = "127.30";       //DE127.30
    public static final int THREED_SECURE_RESULT_127 = 30;
    
    public static final String UCAF_DATA = "127.37";                  //DE127.37
    public static final int UCAF_DATA_127 = 37;
    


}
