/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ipgw.test.postilion.comms;

import java.util.Arrays;
import java.util.Base64;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testXidCavv {

    private byte[] concatBytes(byte[] xid, byte[] cavv) {
        byte[] result = new byte[xid.length + cavv.length];
        System.arraycopy(xid, 0, result, 0, xid.length);
        System.arraycopy(cavv, 0, result, xid.length, cavv.length);
        return result;
    }

    private String displayBytes(byte[] barray) {
        StringBuilder bytes = new StringBuilder();
        for (byte b : barray) {
            bytes.append(b).append(" ");
        }
        return bytes.toString();
    }

    public static void main(String[] args) {

        //String cavv = "VGhpcyBpcyBhIHRlc3QgYmFzZTY=";
        // String xid = "NzUb558CF7moiLeGiy8mSdXEY+s=";  
        String cavv = "VGhpcyBpcyBhIHRlc3QgYmFzZTY=";
        String xid = "yM1c59o0ku/603M4UIGWv9bmonQ=";

        testXidCavv test = new testXidCavv();

        byte[] decoded_xid = Base64.getDecoder().decode(xid);
        byte[] decoded_cavv = Base64.getDecoder().decode(cavv);
        byte[] ds = test.concatBytes(decoded_xid, decoded_cavv);
        String threeDSecureData = xid + cavv;

        //System.out.println("37 35 1B E7 9F 02 17 B9 A8 88 B7 86 8B 2F 26 49 D5 C4 63 EB 54 68 69 73 20 69 73 20 61 20 74 65 73 74 20 62 61 73 65 36");
        System.out.println("32 56 47 68 70 63 79 42 70 63 79 42 68 49 48 52 6C 63 33 51 67 59 6D 46 7A 5A 54 59 3D 00 00 00 00");
        System.out.println("3DSecureData: " + threeDSecureData);
        System.out.println("Decoded XiD length: " + String.valueOf(decoded_xid.length) + " Byte array: " + String.valueOf(test.displayBytes(decoded_xid)));
        System.out.println("Decoded CAVV length: " + String.valueOf(decoded_cavv.length) + " Byte array: " + String.valueOf(test.displayBytes(decoded_cavv)));
        System.out.println("3DSecureData length: " + String.valueOf(ds.length) + " Byte array: " + String.valueOf(test.displayBytes(ds)));

//32 6A 49 7A 69 36 32 63 7A 6C 71 30 68 43 42 45 48 4F 44 56 31 41 47 59 41 41 41 41 3D
//32 56 47 68 70 63 79 42 70 63 79 42 68 49 48 52 6C 63 33 51 67 59 6D 46 7A 5A 54 59 3D
        
        byte[] ucaf = test.concatBytes("2".getBytes(), decoded_cavv);
        
        int PADDING_SIZE = 33 - ucaf.length;
        byte[] padding = new byte[PADDING_SIZE ];
        
        byte[] ucaf2 = test.concatBytes(ucaf, padding);
         
        System.out.println(Arrays.toString(ucaf));
        System.out.println(Arrays.toString(ucaf2)); 
        
        byte[] ucaf3 = Base64.getEncoder().encode(decoded_cavv);
        String ucaf3String = Base64.getEncoder().encodeToString(decoded_cavv);
        System.out.println(String.valueOf(test.displayBytes(ucaf3)));
        System.out.println(ucaf3String);
        
        byte[] ucaf4 = Base64.getEncoder().encode(ucaf);
        String ucaf4String = Base64.getEncoder().encodeToString(ucaf4);
        System.out.println(ucaf4String);
        
        
        byte[] ucaf5 = test.concatBytes("2".getBytes(), cavv.getBytes());
        System.out.println(Arrays.toString(ucaf5));
        
        System.out.println(new Integer(2).byteValue());
        
        byte[] val = new byte[]{2};
        byte[] ucaf6 = test.concatBytes(val, cavv.getBytes());
        int PADDING_SIZE2 = 33 - ucaf6.length;
        byte[] padding2 = new byte[PADDING_SIZE2];
        byte[] ucaf7 = test.concatBytes(ucaf6, padding2);
        System.out.println(Arrays.toString(ucaf7));
        
        //byte[] testarr = new byte[] {}; //32, 56, 47, 68, 70, 63, 79, 42, 70, 63, 79, 42, 68, 49, 48, 52, 6C, 63, 33, 51, 67, 59, 6D, 46, 7A, 5A, 54, 59, 3D, 00, 00, 00, 00};
    }
}
