/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2023 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.functional.tests;

import com.truteq.security.crypto.SCryptUtil;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PasswordGeneraterMD5 {

    public static void main(String[] args) {
        
     //T4p10c4D3l1ght -> $s0$41010$NKzOdms5AMcHJ9aV+hJA/A==$jbHugWAK71RFIYz2KovVpyCXv+bAbpZlrhxrGUvm1g4= 
     //T4p10c4D3l1ght -> $s0$41010$K98W9VzLw+Gna56gkJ7qrw==$mtdn1ceFCJrFQIc8wKwp5mgHS7UPOcve0P2xW7//b1E=
     String originalPassword = "T4p10c4D3l1ght";
        
        String generatedSecuredPasswordHash = SCryptUtil.scrypt(originalPassword, 16, 16, 16);

       //qw = $s0$41010$XG1CFSmy2y/Ku9DOTFnxpw==$mj+oSF+mza0M2+D0uwvrNeGM0BD9elQk4eJhaq+OBT0=  
        System.out.println(originalPassword+" -> "+generatedSecuredPasswordHash);
        
        //S0l4rP4yG0 -> $s0$41010$RjbNUpDwPZn1PiUV8s+ozw==$/OxQfYqvwkkUaxsd8can0oF5M4ysiU3SKcPJdfYemCg=
        String originalPassword2 = "S0l4rP4yG0";
        String generatedSecuredPasswordHash2 = SCryptUtil.scrypt(originalPassword2, 16, 16, 16);
        System.out.println(originalPassword2+" -> "+generatedSecuredPasswordHash2);
        
        String originalPassword3 = "qwerty";
        String generatedSecuredPasswordHash3 = SCryptUtil.scrypt(originalPassword3, 16, 16, 16);
        System.out.println(originalPassword3+" -> "+generatedSecuredPasswordHash3);
        
    }

}
