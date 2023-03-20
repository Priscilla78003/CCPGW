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
package com.truteq.ipgw.test.postilion.comms;

import java.util.Base64;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testThreeDSSecureData {

    public String displayBytes(byte[] barray) {
        StringBuilder bytes = new StringBuilder();
        for (byte b : barray) {
            bytes.append(b).append(" ");
        }
        return bytes.toString();
    }

    public byte[] concatBytes(byte[] xid, byte[] cavv) {
        byte[] result = new byte[xid.length + cavv.length];
        System.arraycopy(xid, 0, result, 0, xid.length);
        System.arraycopy(cavv, 0, result, xid.length, cavv.length);
        return result;
    }
    

    public static void main(String[] args) {

        testThreeDSSecureData test = new testThreeDSSecureData();

        String cavv = "VGhpcyBpcyBhIHRlc3QgYmFzZTY=";
        String xid = "Sn2heGdQX6sGtnpzCqkwVBV9Vzk=";

        byte[] decoded_cavv = Base64.getDecoder().decode(cavv);
        System.out.println(decoded_cavv + " " + decoded_cavv.length);
        System.out.println(test.displayBytes(decoded_cavv));

        byte[] decoded_xid = Base64.getDecoder().decode(xid);
        System.out.println(decoded_xid + " " + decoded_xid.length);
        System.out.println(test.displayBytes(decoded_xid));

        byte[] result = test.concatBytes(decoded_xid, decoded_cavv);
        System.out.println(result + " " + result.length);
        System.out.println(test.displayBytes(result));

        byte[] xid_from = new byte[20];
        byte[] cavv_from = new byte[20];
        System.arraycopy(result, 0, xid_from, 0, xid_from.length);
        System.arraycopy(result, xid_from.length, cavv_from, 0, cavv_from.length);

        System.out.println(xid_from + " " + xid_from.length);
        System.out.println(test.displayBytes(xid_from));
        System.out.println(cavv_from + " " + cavv_from.length);
        System.out.println(test.displayBytes(cavv_from));

    }
}
