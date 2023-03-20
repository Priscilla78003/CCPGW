/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.adapter.postilion.test;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

import java.io.InputStream;
import org.jpos.iso.ISOBinaryField;
import org.jpos.iso.ISOSource;
import org.jpos.iso.packager.NativePackager;
import org.springframework.beans.factory.annotation.Value;

public class PackISOMessage {
    
    
    @Value("${fields}")
    private String fields;
    
    
    public static void main(String[] args) {
        PackISOMessage iso = new PackISOMessage();
        try {
            String message = iso.buildISOMessage();
            System.out.printf("Message = %s", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildISOMessage() throws Exception {
        try {
            // Load package from resources directory.
            //GenericPackager packager = new GenericPackager("/home/grant/Truteq-Gogs-Server-Repo/IPGW-Adapters/ipgw-adapter-postilion-v2/src/main/resources/fields.xml");
            GenericPackager packager = new GenericPackager(fields);

            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);
//            isoMsg.setMTI("0800");
//
//            isoMsg.set(3, "000010");
//            isoMsg.set(4, "1500");
//            isoMsg.set(7, "1206041200");
//            isoMsg.set(11, "000001");
//            isoMsg.set(41, "12340001");
//            isoMsg.set(49, "840");

//        isoMsg.setMTI("0800");
//        isoMsg.set(7, "0413204438");
//        isoMsg.set(11, "11");
//        isoMsg.set(12, "204438"); 
//        isoMsg.set(13, "0413");
//        isoMsg.set(70, "002");

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
            
            byte[] ds = "AADE3C7DBD99C3EC74A1DF224555555555555555".getBytes();
                byte byte1 = (byte)0b00000000;
                byte byte2 = (byte)0b01000000;
                byte byte3 = (byte)0b00000000;
                byte byte4 = (byte)0b00001100;
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
            
            isoMsg.set("127.01",bytesArr);
            isoMsg.set("127.02","0200:000107:0508180236:907348967");
            isoMsg.set("127.03","2");
            isoMsg.set("127.10","532");
            isoMsg.set("127.29",ds);
            isoMsg.set("127.30","2");
            
String sms = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<Sms>\n" +
"  <AcqInstCountryCode>598</AcqInstCountryCode>\n" +
"  <AcqInstIdCode>428280</AcqInstIdCode>\n" +
"  <NwrkId>0002</NwrkId>\n" +
"  <ChbackReduct>   8   </ChbackReduct>\n" +
"  <ReimburseAttr>0</ReimburseAttr>\n" +
"  <SysTrace>581153</SysTrace>\n" +
"  <RetrievalRefNr>629214581153</RetrievalRefNr>\n" +
"  <MessageStatusFlags>000000</MessageStatusFlags>\n" +
"</Sms>";


String field22_key = "221ThirdPartyBillPayment";
// 221 : 2 length of length (21), 21 length of "ThirdPartyBillPayment"

String field22_value = "3125<ThirdPartyBillPayment><BillPaymentRequest><ReferenceId>1111111111</ReferenceId></BillPaymentRequest></ThirdPartyBillPayment>";
// 3125 : 3 length of (125), 125 length of 
String field22 = field22_key+field22_value;

isoMsg.set("127.22",field22);	


        //isoMsg.set("127.22",sms);

//           ISOMsg f127 = new ISOMsg(127);
//           isoMsg.setPackager(packager);
//           
//           byte[] ds = "AADE3C7DBD99C3EC74A1DF224555555555555555".getBytes();
//           
//           f127.set(2,"0200:000107:0508180236:907348967");
//           f127.set(3,"2");
//           f127.set(10,"532");
//           f127.set(29,ds);
//           f127.set(30,"2");       
//              
//           isoMsg.set(f127);
            

            printISOMessage(isoMsg);

            byte[] result = isoMsg.pack();
            return new String(result);
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }

    private void printISOMessage(ISOMsg isoMsg) {
        try {
            System.out.printf("MTI = %s%n", isoMsg.getMTI());
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                      System.out.printf("Field (%s) = %s%n", i, isoMsg.getString(i));
                }
            }
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
}