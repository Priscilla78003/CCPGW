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
public enum CVCResultCodes {
    A, //CVC Matched
    B, //CVC Not Supplied
    C, //CVC Not Checked
    D  //CVC Not Matched
}
