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
public interface ResponseCodes {
    static final int Authorized_Captured_Received = 0;
    static final int Redirection_required = 1;
    static final int Referral = 2;
    static final int Hold_card = 4;
    static final int Declined_refused =5;
    static final int Invalid_transaction =12;
    static final int Invalid_account = 13;
    static final int Invalid_amount = 14;
    static final int Invalid_card_issuer = 15;
    static final int Acquirer_error = 20;
    static final int Reversal_not_processed_missing_Auth = 21;
    static final int Transaction_ID_not_found_or_invalid = 22;
    static final int Card_expired = 33;
    static final int Fraud_suspicion = 34;
    static final int Lost_card = 41;
    static final int Stolen_card_pick_up = 43;
    static final int Limit_exceeded = 51;
    static final int Invalid_security_code = 55;
    static final int Card_blocked =76;
    static final int Rejected_by_card_issuer = 85;
    static final int Card_issuer_temporarily_unreachable = 91;
    static final int Card_type_not_processed_by_acquirer = 92;
    static final int Payment_Method_not_found = 100;
    static final int Collect_additional_data = 200;
    static final int Pending_payment = 300;
}
