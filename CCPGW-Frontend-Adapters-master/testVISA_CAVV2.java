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

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testVISA_CAVV2 {
    
    //https://mpi.netcetera.com/doc/current/schema/api-3ds.html (search cavv)
    //Cardholder authentication verification value. It is a 28 byte value (Base64 encoded 20 byte cavv)
    //Transaction identifier. It is a 28 byte value (Base64 encoded 20 byte xid).
 
    public static byte[] concatBytes(byte[] xid, byte[] cavv) {
        byte[] result = new byte[xid.length + cavv.length];
        System.arraycopy(xid, 0, result, 0, xid.length);
        System.arraycopy(cavv, 0, result, xid.length, cavv.length);
        return result;
    }

    public static ArrayList<byte[]> splitByteArray(byte[] input) {

        ByteBuffer bb = ByteBuffer.wrap(input);

        byte[] xid = new byte[20];
        byte[] cavv = new byte[20];
        
        System.arraycopy(input, 0, xid, 0, xid.length);
        System.arraycopy(input, xid.length, cavv, 0, cavv.length);
        
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(xid);
        list.add(cavv);
        
        return list;

    }    
    
    public static String displayBytes(byte[] barray) {
        StringBuilder bytes = new StringBuilder();
        for (byte b : barray) {
            bytes.append(b).append(" ");
        }
        return bytes.toString();
    }
    
    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    private static String asciiToHex(String asciiStr) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString((int) ch));
        }

        return hex.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }    
    
    public static void main(String[] args) {
        String XiD = "";
        String CAVV ="";
        
        //16:06:43
        //3DSecureData:8cxGKc9bzwJFJIqEBdg50aw4bTY=AAABASGQRHFAMYJSVZBEEGcLoRs=
        XiD = "8cxGKc9bzwJFJIqEBdg50aw4bTY=";
        CAVV = "AAABASGQRHFAMYJSVZBEEGcLoRs=";
        
        //16:09:18
        //3DSecureData: 1sgK1Hj5Vpxf4h4XnBkav9sJ8lk=AAABBEBFM5A0ljd2EUUzEGcLoRs= 
        XiD = "1sgK1Hj5Vpxf4h4XnBkav9sJ8lk=";
        CAVV = "AAABBEBFM5A0ljd2EUUzEGcLoRs=";     
        
        //2021-11-19 16:14:40 
        //3DSecureData: CwUcZRhn55WfM31DJvWMXywe74Y=AAABCCZgZQQwcphBmGBlEGcLoRs=        
         XiD = "CwUcZRhn55WfM31DJvWMXywe74Y=";
         CAVV = "AAABCCZgZQQwcphBmGBlEGcLoRs=";        
        
        byte[] decoded_xid = Base64.getDecoder().decode(XiD);
        byte[] decoded_cavv = Base64.getDecoder().decode(CAVV);
        
        //System.out.println(displayBytes(decoded_cavv));
        
        String hexValue = byteArrayToHex(decoded_cavv);
        System.out.println("CAVV: "+hexValue);
        
        hexValue = byteArrayToHex(decoded_xid);
        System.out.println("XiD: "+hexValue);        
       
        String output = Base64.getEncoder().encodeToString(decoded_cavv);
        System.out.println(output);

//        XiD = "8cxGKc9bzwJFJIqEBdg50aw4bTY=";
//        //XiD = "00000000000000000000";
//        CAVV = "AAABASGQRHFAMYJSVZBEEGcLoRs=";
//        
//        decoded_xid = Base64.getDecoder().decode(XiD);
//        decoded_cavv = Base64.getDecoder().decode(CAVV);
//        
        byte[] ds = concatBytes(decoded_xid, decoded_cavv);
        System.out.println(Arrays.toString(ds));
        hexValue = byteArrayToHex(ds);
        System.out.println(hexValue);
        
        
        byte[] ds2 = hexStringToByteArray(hexValue);
        System.out.println(Arrays.toString(ds2));
        
        ArrayList<byte[]> list = splitByteArray(ds2);
        byte[]xid = list.get(0);
        byte[]cavv = list.get(1);
        hexValue = byteArrayToHex(cavv);
        System.out.println("CAVV: "+hexValue); 
        output = Base64.getEncoder().encodeToString(cavv);
        System.out.println(output);
        
        //System.out.println("Decoded XiD length: " + String.valueOf(decoded_xid.length) + " Byte array: " + String.valueOf(displayBytes(decoded_xid)));
        
        String val = "8C8CE2EB673396AD210811073835750066000000";
        System.out.println(asciiToHex(val));
        System.out.println(Arrays.toString(hexStringToByteArray(asciiToHex(val))));
        

    }    
}
