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

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testVISA_CAVV {

    public static byte[] concatBytes(byte[] xid, byte[] cavv) {
        byte[] result = new byte[xid.length + cavv.length];
        System.arraycopy(xid, 0, result, 0, xid.length);
        System.arraycopy(cavv, 0, result, xid.length, cavv.length);
        return result;
    }

    private static String binaryToHex(String binary) {
        int decimalValue = 0;
        int length = binary.length() - 1;
        for (int i = 0; i < binary.length(); i++) {
            decimalValue += Integer.parseInt(binary.charAt(i) + "") * Math.pow(2, length);
            length--;
        }
        return decimalToHex(decimalValue);
    }

    private static String decimalToHex(int decimal) {
        String hex = "";
        while (decimal != 0) {
            int hexValue = decimal % 16;
            hex = toHexChar(hexValue) + hex;
            decimal = decimal / 16;
        }
        return hex;
    }

    private static char toHexChar(int hexValue) {
        if (hexValue <= 9 && hexValue >= 0) {
            return (char) (hexValue + '0');
        } else {
            return (char) (hexValue - 10 + 'A');
        }
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static String hexToAscii(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(n / 2);
        for (int i = 0; i < n; i += 2) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            sb.append((char) ((hexToInt(a) << 4) | hexToInt(b)));
        }
        return sb.toString();
    }

    private static int hexToInt(char ch) {
        if ('a' <= ch && ch <= 'f') {
            return ch - 'a' + 10;
        }
        if ('A' <= ch && ch <= 'F') {
            return ch - 'A' + 10;
        }
        if ('0' <= ch && ch <= '9') {
            return ch - '0';
        }
        throw new IllegalArgumentException(String.valueOf(ch));
    }

    public static String convertStringToHex(String str) {

        // display in uppercase
        //char[] chars = Hex.encodeHex(str.getBytes(StandardCharsets.UTF_8), false);

        // display in lowercase, default
        char[] chars = Hex.encodeHex(str.getBytes(StandardCharsets.UTF_8));

        return String.valueOf(chars);
    }

    public static String convertHexToString(String hex) {

        String result = "";
        try {
            byte[] bytes = Hex.decodeHex(hex);
            result = new String(bytes, StandardCharsets.UTF_8);
        } catch (DecoderException e) {
            throw new IllegalArgumentException("Invalid Hex format!");
        }
        return result;
    } 
    
    private static String asciiToHex(String asciiStr) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString((int) ch));
        }

        return hex.toString();
    }    
    
    public static void main(String[] args) {
        
        //String XiD = "zfV1lzLGhIMuM3BRN3Zm8QOhQgI=";
        //String CAVV = "jIzi62czlq0hCBEHODV1AGYAAAA=";
        //String XiD = "KlQiIpNlqA/qEqh/978Z7Rj9keY=";
        //String CAVV =  "BwABCYV3ImhTUCSZlnci";//"BwABCYV3ImhTUCSZlnciAAAAAAA=";
        
        String XiD = "sWdE0rdPRkcFJgvV5xPNoMH2OSg=";
        String CAVV = "AAABBJYik5k5dkSFgiKTEGpE7lg=";
        
        
        byte[] decoded_xid = Base64.getDecoder().decode(XiD);
        byte[] decoded_cavv = Base64.getDecoder().decode(CAVV);
        byte[] ds = concatBytes(decoded_xid, decoded_cavv);
        System.out.println(Arrays.toString(ds));
         
        String hexValue = byteArrayToHex(ds);
        System.out.println(hexValue);
        
        String ascii = hexToAscii(hexValue);
        System.out.println(ascii);


        ds = concatBytes(XiD.getBytes(), CAVV.getBytes());
        System.out.println(Arrays.toString(ds));
         
        hexValue = byteArrayToHex(ds);
        System.out.println(hexValue);
        
        hexValue = byteArrayToHex(decoded_xid);
        System.out.println(hexValue);
        
        hexValue = byteArrayToHex(decoded_cavv);
        System.out.println(hexValue);        
        
        hexValue = byteArrayToHex(XiD.getBytes());
        System.out.println(hexValue);
        
        hexValue = byteArrayToHex(CAVV.getBytes());
        System.out.println(hexValue);          
        
        ascii = hexToAscii(hexValue);
        System.out.println(ascii);
        
        
        hexValue = convertStringToHex(CAVV);
        System.out.println(hexValue);
        
        
        hexValue = asciiToHex(CAVV);
        System.out.println(hexValue);
        //------------------------------------------------------------------------------
        // For Doku
        //------------------------------------------------------------------------------
        //[Fixed  b      40 040] 127.029 *[30303030303130393833323139393134323239343138373339343231393931313637376661656238] 
        //If we convert  30303030303130393833323139393134323239343138373339343231393931313637376661656238 to ASCII we get: 00000109832199142294187394219911677faeb8
        //String ascii = hexToAscii("30303030303130393833323139393134323239343138373339343231393931313637376661656238");
        //System.out.println(ascii);
        
        //System.out.println(byteArrayToHex("00000109832199142294187394219911677faeb8".getBytes()));        
        
        
//        byte[] dsBytes = concatBytes(XiD.getBytes(),CAVV.getBytes());
//        
//        System.out.println(Arrays.toString(CAVV.getBytes()));
//        
//        String hexValue = byteArrayToHex(dsBytes);
//        System.out.println(hexValue);
//        
//        
//        System.out.println(convertStringToHex(XiD+CAVV));
//        
//        String ascii = hexToAscii(convertStringToHex(XiD+CAVV));
//        System.out.println(ascii);
        
        
        //ascii = hexToAscii("625A627442693970714F365A51754D596B4D6273482B327A6448553D6A497A693632637A6C713068434245484F445631414759414141413D");
        //System.out.println(ascii);        
        
        /*
        String XiD = "Rv7V69Pl4rvDLV8IRtkLjMT0z1M=";
        String CAVV = "AAABBmhZmQAAAAAzYVmZAAAAAAA=";

        //String XiD = "mz7eJhE+yej1RoW/LUmxk4B+y4I=";
        //String CAVV = "jIzi62czlq0hCBEHODV1AGYAAAA=";
        //String Old3dsData = "[-101, 62, -34, 38, 17, 62, -55, -24, -11, 70, -123, -65, 45, 73, -79, -109, -128, 126, -53, -126, -116, -116, -30, -21, 103, 51, -106, -83, 33, 8, 17, 7, 56, 53, 117, 0, 102, 0, 0, 0]";
        //String New3dsData = "[109, 122, 55, 101, 74, 104, 69, 43, 121, 101, 106, 49, 82, 111, 87, 47, 76, 85, 109, 120, 107, 52, 66, 43, 121, 52, 73, 61, 106, 73, 122, 105, 54, 50, 99, 122, 108, 113, 48, 104, 67, 66, 69, 72, 79, 68, 86, 49, 65, 71, 89, 65, 65, 65, 65, 61]";
        //00F00000E00000900806000000D0010000D00000
        //00000101219044714031825255904410670ba11b
//-PaRes XiD: mz7eJhE+yej1RoW/LUmxk4B+y4I=
//-PaRes CAVV: jIzi62czlq0hCBEHODV1AGYAAAA=
//-Old 3dsData: [-101, 62, -34, 38, 17, 62, -55, -24, -11, 70, -123, -65, 45, 73, -79, -109, -128, 126, -53, -126, -116, -116, -30, -21, 103, 51, -106, -83, 33, 8, 17, 7, 56, 53, 117, 0, 102, 0, 0, 0]
//-New 3dsData: [109, 122, 55, 101, 74, 104, 69, 43, 121, 101, 106, 49, 82, 111, 87, 47, 76, 85, 109, 120, 107, 52, 66, 43, 121, 52, 73, 61, 106, 73, 122, 105, 54, 50, 99, 122, 108, 113, 48, 104, 67, 66, 69, 72, 79, 68, 86, 49, 65, 71, 89, 65, 65, 65, 65, 61]
//"6D7A37654A68452B79656A31526F572F4C556D786B34422B7934493D6A497A693632637A6C713068434245484F445631414759414141413D"
//B681429630AC951D2F1095185E9E5F937080DE250000010176006869043397400500680000000000
//9b3ede26113ec9e8f54685bf2d49b193807ecb828c8ce2eb673396ad210811073835750066000000        
//3DSecureData: Rv7V69Pl4rvDLV8IRtkLjMT0z1M=AAABBmhZmQAAAAAzYVmZAAAAAAA=
//XiD length: 20 Byte array: 70 -2 -43 -21 -45 -27 -30 -69 -61 45 95 8 70 -39 11 -116 -60 -12 -49 83 
//CAVV length: 20 Byte array: 0 0 1 6 104 89 -103 0 0 0 0 51 97 89 -103 0 0 0 0 0 
//DSecureData length: 40 Byte array: 70 -2 -43 -21 -45 -27 -30 -69 -61 45 95 8 70 -39 11 -116 -60 -12 -49 83 0 0 1 6 104 89 -103 0 0 0 0 51 97 89 -103 0 0 0 0 0 
//CAVV : AAABBmhZmQAAAAAzYVmZAAAAAAA=

        System.out.println(Arrays.toString((XiD + CAVV).getBytes()));
        System.out.println(XiD + CAVV);

        byte[] decoded_xid = Base64.getDecoder().decode(XiD);
        byte[] decoded_cavv = Base64.getDecoder().decode(CAVV);

        byte[] ds = concatBytes(decoded_xid, decoded_cavv);

        System.out.println(Arrays.toString(decoded_xid));
        System.out.println(Arrays.toString(decoded_cavv));
        System.out.println(Arrays.toString(ds));

        byte[] encoded_cavv = Base64.getEncoder().encode(ds);
        System.out.println(Arrays.toString(encoded_cavv));

        String hexValue = byteArrayToHex(ds);
        System.out.println(hexValue);

        byte[] dsBytes = concatBytes(XiD.getBytes(), CAVV.getBytes());
        hexValue = byteArrayToHex(dsBytes);
        System.out.println(hexValue);
        
        //------------------------------------------------------------------------------
        // For Mastercard
        //------------------------------------------------------------------------------
        //[LLVAR  b    ..33 029] 127.032 *[326A4546372B7270567036546943424A6E6E6B6F73424145414141413D]
        //If we convert  326A4546372B7270567036546943424A6E6E6B6F73424145414141413D  to  ASCII we get 2jEF7+rpVp6TiCBJnnkosBAEAAAA=        
        String ascii = hexToAscii("326A4546372B7270567036546943424A6E6E6B6F73424145414141413D");
        System.out.println(ascii);
        
        System.out.println(byteArrayToHex("2jEF7+rpVp6TiCBJnnkosBAEAAAA=".getBytes()));
        
        //------------------------------------------------------------------------------
        // For Doku
        //------------------------------------------------------------------------------
        //[Fixed  b      40 040] 127.029 *[30303030303130393833323139393134323239343138373339343231393931313637376661656238] 
        //If we convert  30303030303130393833323139393134323239343138373339343231393931313637376661656238 to ASCII we get: 00000109832199142294187394219911677faeb8
        ascii = hexToAscii("30303030303130393833323139393134323239343138373339343231393931313637376661656238");
        System.out.println(ascii);
        
        System.out.println(byteArrayToHex("00000109832199142294187394219911677faeb8".getBytes()));
        
        //------------------------------------------------------------------------------
        // For PlatformPAC
        //------------------------------------------------------------------------------  
        
        System.out.println(byteArrayToHex(XiD.getBytes()));
        System.out.println(byteArrayToHex(CAVV.getBytes()));
        
        System.out.println(byteArrayToHex(Base64.getDecoder().decode(XiD)));
        System.out.println(byteArrayToHex(Base64.getDecoder().decode(CAVV)));
        
        System.out.println(byteArrayToHex(concatBytes(Base64.getDecoder().decode(XiD),Base64.getDecoder().decode(CAVV))));
        
        ascii = hexToAscii("0000010668599900000000336159990000000000");
        System.out.println(ascii);
        
        System.out.println(byteArrayToHex(XiD.getBytes()));
        
        ascii = hexToAscii("7A6656316C7A4C4768494D754D3342524E335A6D38514F685167493D6A497A693632637A6C713068434245484F445631414759414141413D");
        System.out.println(ascii);
        
        */
               
    }
}
