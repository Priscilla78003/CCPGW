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
package com.truteq.ccpgw.adapter.postilion.tests;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.BitSet;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Bitwise {
    
    public static int bitSetToInt(BitSet bitSet) {
        int bitInteger = 0;

        for (int i = 0; i < 32; i++){
            if (bitSet.get(i)) {
                bitInteger |= (1 << i);
            }
        }
        return bitInteger;
    }
    
  static String bitset2Hex(final BitSet bitset, final int minLength) {

    final StringBuilder result = new StringBuilder();
    for (int bytenum = 0; bytenum < minLength / 2; bytenum++) {
      byte v = 0;
      for (int bit = 0, mask = 0x80; mask >= 0x01; bit++, mask /= 2) {
        if (bitset.get((bytenum * 8) + bit)) {
          v |= mask;
        }
      }
      result.append(String.format("%02X", v));
    }

    return result.toString();
  }
  
    static byte[] bitset2bin(final BitSet bitSet, final int length) {

    final byte[] result = new byte[length];
    for (int bytenum = length - 1; bytenum >= 0; bytenum--) {
      result[bytenum] = 0;
      for (int bit = 0, mask = 0x80; mask >= 0x01; bit++, mask /= 2) {
        if (bitSet.get((bytenum * 8) + bit)) {
          result[bytenum] |= mask;
        }
      }
    }
    return result;
  }
 
  static String bytesToHex(byte[] bytes){
      String [] arr = new String[bytes.length];
      for (int i = 0; i < bytes.length; i++) {
          arr[i] = arr[i] = String.format("%02x", Byte.parseByte(bytes[i]+""));
      }    
      return java.util.Arrays.toString(arr);
  }  
    
  static int fromByteArray(byte[] bytes) {
     return ByteBuffer.wrap(bytes).getInt();
  }
 
  static byte[] toByteArray(int value) {
    return new byte[] { 
        (byte)(value >> 24),
        (byte)(value >> 16),
        (byte)(value >> 8),
        (byte)value};
}
  
static String toBinary( byte[] bytes )
{
    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
    return sb.toString();
}  

static byte[] getByteByString(String byteString){
    return new BigInteger(byteString, 2).toByteArray();
}
  
    
//A message will contain at least one bitmap, called the Primary Bitmap which indicates which of Data Elements 1 to 64 are present. A secondary bitmap may also be present, generally as data element one and indicates which of data elements 65 to 128 are present. Similarly, a tertiary, or third, bitmap can be used to indicate the presence or absence of fields 129 to 192, although these data elements are rarely used.
//The bitmap may be transmitted as 8 bytes of binary data, or as 16 hexadecimal characters 0-9, A-F in the ASCII or EBCDIC character sets.
//A field is present only when the specific bit in the bitmap is true. For example, byte '82x is binary '1000 0010' which means fields 1 and 7 are present in the message and fields 2, 3, 4, 5, 6, and 8 are not present.
//Examples -----
//Bitmap	Defines presence of
//4210001102C04804	Fields 2, 7, 12, 28, 32, 39, 41, 42, 50, 53, 62
//7234054128C28805	Fields 2, 3, 4, 7, 11, 12, 14, 22, 24, 26, 32, 35, 37, 41, 42, 47, 49, 53, 62, 64
//8000000000000001	Fields 1, 64
//0000000000000003
//(secondary bitmap)	Fields 127, 128
//Explanation of Bitmap (8 BYTE Primary Bitmap = 64 Bit) field 4210001102C04804
//BYTE1: 0100 0010 = 42x (counting from the left, the second and seventh bits are 1, indicating that fields 2 and 7 are present)
//BYTE2: 0001 0000 = 10x (field 12 is present)
//BYTE3: 0000 0000 = 00x (no fields present)
//BYTE4: 0001 0001 = 11x (fields 28 and 32 are present)
//BYTE5: 0000 0010 = 02x (field 39 is present)
//BYTE6: 1100 0000 = C0x (fields 41 and 42 are present)
//BYTE7: 0100 1000 = 48x (fields 50 and 53 are present)
//BYTE8: 0000 0100 = 04x (field 62 is present)
//Fields present in the above variable length message record:
//2-7-12-28-32-39-41-42-50-53-62  
//  
    public static void main(String[] args) {
//        BitSet bits = new BitSet();
//        bits.set(2,true);
//        bits.set(7,true);
//        bits.set(12,true);
////        bits.set(0, false);
////        bits.set(1, true);
////        bits.set(2, false);
////        bits.set(3, false);
////        bits.set(4, false);
////        bits.set(5, false);
////        bits.set(6, false);
////        bits.set(7, false);
////        bits.set(8, false);
//        System.out.println(bitSetToInt(bits));
//        
//        System.out.println(bitset2Hex(bits,64));
//        
//        System.out.println(Arrays.toString(bitset2bin(bits,4)));
//        
//        System.out.println(fromByteArray(bitset2bin(bits,8)));
//        
//        System.err.println(Arrays.toString(toByteArray(2097158)));
//        
//        
//        byte[] bytes = {66,16,0,0,0,0,0,0};
//        System.out.println(fromByteArray(bytes));
//        
//        System.out.println(bytesToHex(bytes));
        System.out.println("===================================================");
        System.out.println("Test 1");
        System.out.println("===================================================");
        byte byte1 = (byte)0b01000010;
        byte byte2 = (byte)0b00010000;
        byte byte3 = (byte)0b00000000;
        byte byte4 = (byte)0b00010001;
        byte byte5 = (byte)0b00000010;
        byte byte6 = (byte)0b11000000;
        byte byte7 = (byte)0b01001000;
        byte byte8 = (byte)0b00000100;
        
        byte[] bytesArr = {byte1,
                           byte2,
                           byte3,
                           byte4,
                           byte5,
                           byte6,
                           byte7,
                           byte8};
        
        System.out.println(Arrays.toString(bytesArr));
        System.out.println(bytesToHex(bytesArr));
        System.out.println(toBinary(bytesArr));
        System.out.println(Arrays.toString(getByteByString(toBinary(bytesArr))));
        
         System.out.println("===================================================");
        System.out.println("Test 2");
        System.out.println("===================================================");
        byte1 = (byte)0b01110010;
        byte2 = (byte)0b00110100;
        byte3 = (byte)0b00000101;
        byte4 = (byte)0b00010001;
        byte5 = (byte)0b01000001;
        byte6 = (byte)0b00101000;
        byte7 = (byte)0b11000000;
        byte8 = (byte)0b10001000;
        byte byte9 = (byte)0b00000101;
        
        byte[] bytesArr2 = {byte1,
                           byte2,
                           byte3,
                           byte4,
                           byte5,
                           byte6,
                           byte7,
                           byte8,
                           byte9};        
     
        System.out.println(Arrays.toString(bytesArr2));
        System.out.println(bytesToHex(bytesArr2));       

         System.out.println("===================================================");
        System.out.println("Test 3");
        System.out.println("===================================================");
        byte1 = (byte)0b00000000;
        byte2 = (byte)0b01000000;
        byte3 = (byte)0b00000000;
        byte4 = (byte)0b00001100;
        byte5 = (byte)0b00000000;
        byte6 = (byte)0b00000000;
        byte7 = (byte)0b00000000;
        byte8 = (byte)0b00000000;
        byte9 = (byte)0b00000000;
        
        byte[] bytesArr3 = {byte1,
                           byte2,
                           byte3,
                           byte4,
                           byte5,
                           byte6,
                           byte7,
                           byte8,
                           byte9};        
     
        System.out.println(Arrays.toString(bytesArr3));
        System.out.println(bytesToHex(bytesArr3)); 
        
         System.out.println("===================================================");
        System.out.println("Test 4");
        System.out.println("===================================================");
        byte1 = (byte)0b01110000;
        byte2 = (byte)0b10011100;
        byte3 = (byte)0b00010100;
        byte4 = (byte)0b00000000;
        byte5 = (byte)0b00000000;
        byte6 = (byte)0b00000000;
        byte7 = (byte)0b00000000;
        byte8 = (byte)0b00000000;
        byte9 = (byte)0b00000000;
        
        byte[] bytesArr4 = {byte1,
                           byte2,
                           byte3,
                           byte4,
                           byte5,
                           byte6,
                           byte7,
                           byte8,
                           byte9};        
     
        System.out.println(Arrays.toString(bytesArr4));
        System.out.println(bytesToHex(bytesArr4)); 
        
         System.out.println("===================================================");
        System.out.println("Test 5");
        System.out.println("===================================================");
        byte1 = (byte)0b01100100;
        byte2 = (byte)0b00000000;
        byte3 = (byte)0b00000000;
        byte4 = (byte)0b00000000;
        byte5 = (byte)0b00000000;
        byte6 = (byte)0b00000000;
        byte7 = (byte)0b00000000;
        byte8 = (byte)0b00000000;
        byte9 = (byte)0b00000000;
        
        byte[] bytesArr5 = {byte1,
                           byte2,
                           byte3,
                           byte4,
                           byte5,
                           byte6,
                           byte7,
                           byte8,
                           byte9};   
         System.out.println(Arrays.toString(bytesArr5));
        System.out.println(bytesToHex(bytesArr5)); 
        
    }
}
