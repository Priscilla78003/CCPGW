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
package com.truteq.ccpgw.adapter.postilion.enums;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public enum eAuth {
    SIGNON,
    SIGNOFF,
    ECHO,
    AUTH,
    AUTH_ADJ,
    FINANCIAL,
    REVERSAL,
    REFUND,
    CAPTURE,
    CREDIT,
    DEBIT,
    MOTO,
    REPEATREVERSAL,
    REPEATCAPTURE,
}
