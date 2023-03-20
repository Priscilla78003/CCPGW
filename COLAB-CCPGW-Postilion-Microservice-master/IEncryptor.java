/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Dec-2019 
 * ***************************************************************
 */
package com.truteq.encryptor;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public interface IEncryptor {
    public String Encode (String stringToEncode)  throws Exception ;
    public String Decode (String stringToDecode)  throws Exception ;
}
