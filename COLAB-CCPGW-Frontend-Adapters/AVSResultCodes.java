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
package com.truteq.ccpgw.frontend.adapter.sabre.api.enums;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public enum AVSResultCodes {
        A, // Postal code and address matched
        B, // Postal code matched; address not checked
        C, //  Postal code matched; address not matched
        D, //  Address matched; postal code not checked
        E, //  Postal code and address not checked
        F, //  Address matched; postal code not matched
        G, //  Postal code not checked; address not matched
        H, //  Postal code and address not supplied
        I, //  Address not checked; postal code not matched
        J  //  Postal code and address not matched    
}
