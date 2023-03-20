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
package com.truteq.ccpgw.adapter.postilion.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ISOMessagePackageTest {

    @Value("${isopack}")
    private String fields;
    
    public static void main(String[] args) {
        ISOMessagePackageTest isoMessage = new ISOMessagePackageTest();
        try {
            String message = isoMessage.buildISOMessage();
            System.out.printf("Message = %s", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private String buildISOMessage() throws Exception {
        try {
            // Load package from resources directory.
            GenericPackager packager = new GenericPackager("/home/grant/Truteq-Gogs-Server-Repo/IPGW-Adapters/ipgw-adapter-postilion-v2/src/main/resources/fields.xml");
            //GenericPackager packager = new GenericPackager(fields);

            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);

            isoMsg.setMTI("0100");
            isoMsg.set(2,"4896780109909073");
            isoMsg.set(3,"000000");
            isoMsg.set(4,"000000012300");
            isoMsg.set(7,"0414201559");
            isoMsg.set(11,"000011");
            isoMsg.set(12,"201559");
            isoMsg.set(13,"0414");
            isoMsg.set(14,"2108");
            isoMsg.set(18,"5399");
            isoMsg.set(22,"010");
            isoMsg.set(25,"00");
            isoMsg.set(41,"423PGK11");
            isoMsg.set(42,"4023PGK00000011");
            isoMsg.set(43,"PHA Health Assurance LiPOM            PG");
            isoMsg.set(49,"598");
            isoMsg.set(123,"660550600001190");
            
                byte byte1 = (byte)0b00000000;
                byte byte2 = (byte)0b01000000;
                byte byte3 = (byte)0b00000000;
                byte byte4 = (byte)0b00000000;
                byte byte5 = (byte)0b00000000;
                byte byte6 = (byte)0b00000000;
                byte byte7 = (byte)0b00000000;
                byte byte8 = (byte)0b00000000;

                byte[] bytesArr = {byte1,
                                   byte2,
                                   byte3,
                                   byte4,
                                   byte5,
                                   byte6,
                                   byte7,
                                   byte8};
            
            //isoMsg.set("127.01",bytesArr);
            //isoMsg.set("127.10","532");
            
            ISOMsg f127 = new ISOMsg(127);
            f127.setPackager(packager);
            
            f127.set(1,bytesArr);
            f127.set(10, "532");
            isoMsg.set(f127);
            
            isoMsg.dump(System.out, "");
            
            printUnpackedISOMessage(isoMsg);

            byte[] result = isoMsg.pack();
            
            printPackedISOMessage(result);
            
            System.out.println(ISOUtil.hexdump(result));
            
            System.out.println("Max fields: "+isoMsg.getMaxField());
            
             System.out.println("STAN: "+isoMsg.getComponent(11).getValue());
            
            return new String(result);
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }

    private void printUnpackedISOMessage(ISOMsg isoMsg) {
        try {
            System.out.printf("MTI = %s%n", isoMsg.getMTI());
            
            System.out.println("127.001 : " + isoMsg.getString("127.001"));
            System.out.println("127.010 : " + isoMsg.getString("127.010"));            
            
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                      System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
                }
            }
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
    
    private void printPackedISOMessage(byte[] packedIso){
        String isoMessage = "";
        for (int i = 0; i < packedIso.length; i++) {
  
            isoMessage += (char) packedIso[i];
        }
        System.out.println(" Packed ISO8385 Message = '"+isoMessage+"'");        
    }

//    private void printISOHexMessage(GenericPackager packager, byte [] hexmsg){
//        try {
//            //byte[] bmsg = ISOUtil.hex2byte(hexmsg);
//            ISOMsg m = new ISOMsg();
//            // set packager, change ISO87BPackager for the matching one.
//            m.setPackager(packager);
//            //unpack the message using the packager
//            m.unpack(hexmsg);
//            
//            System.out.println(ISOUtil.byte2hex(bmsg));
//            //dump the message to standar output
//            m.dump(System.out, "");
//            byte b[] = m.pack();
//            m.unpack(b); 
//            System.out.println(ISOUtil.hexdump(b));
//        } catch (ISOException ex) {
//            Logger.getLogger(ISOMessagePackageTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
}
