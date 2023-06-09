/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.adapter.postilion.test;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
//<isomsg>
//  <!-- org.jpos.iso.packager.ISO87BPackager -->
//  <field id="0" value="0800"/>
//  <field id="3" value="000000"/>
//  <field id="11" value="000001"/>
//  <field id="41" value="29110001"/>
//</isomsg>
//0000(0000)  30 38 30 30 a2 38 00 00  00 00 00 00 04 00 00 00   0800.8..........
//
//0016(0010)  00 00 00 00 30 30 30 30  30 30 30 34 31 33 31 34   ....000000041314
//
//0032(0020)  31 38 30 37 30 30 30 30  31 30 31 34 31 38 30 37   1807000010141807
//
//0048(0030)  30 34 31 33 30 30 31                               0413001
//<isomsg>
//  <field id="0" value="0800"/>
//  <field id="1" value="0020000000000000"/>
//  <field id="7" value="0413204438"/>
//  <field id="11" value="000010"/>
//  <field id="12" value="204438"/>
//  <field id="13" value="0413"/>
//  <field id="70" value="001"/>
//</isomsg>
public class ParseISOMsg {

    @Value("${fields}")
    private static String fields;

    public static void main(String[] args) throws ISOException {

        //GenericPackager packager = new GenericPackager("/home/grant/Truteq-Gogs-Server-Repo/IPGW-Adapters/ipgw-adapter-postilion-v2/src/main/resources/fields.xml");
        GenericPackager packager = new GenericPackager(fields);

        //String hexmsg ="0100F23C448000E080000000000000000022164896780109909073000000000000012300041420155900001120155904142108539901000423PGK114023PGK00000011PHA Health Assurance LiPOM            PG598015660550600001190000134`@     320200:000107:0508180236:9073489672                                               532AADE3C7DBD99C3EC74A1DF2245555555555555552";
//        String hexmsg ="0100F23C448000E080000000000000000022164896780109909073000000000000012300041420155900001120155904142108539901000423PGK114023PGK00000011PHA Health Assurance LiPOM            PG598015660550600001190000503`@    320200:000107:0508180236:9073489672                                               53200364<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//"<Sms>\n" +
//"  <AcqInstCountryCode>598</AcqInstCountryCode>\n" +
//"  <AcqInstIdCode>428280</AcqInstIdCode>\n" +
//"  <NwrkId>0002</NwrkId>\n" +
//"  <ChbackReduct>   8   </ChbackReduct>\n" +
//"  <ReimburseAttr>0</ReimburseAttr>\n" +
//"  <SysTrace>581153</SysTrace>\n" +
//"  <RetrievalRefNr>629214581153</RetrievalRefNr>\n" +
//"  <MessageStatusFlags>000000</MessageStatusFlags>\n" +
//"</Sms>AADE3C7DBD99C3EC74A1DF2245555555555555552";
//        
//String hexmsg ="0100F23C448000E080000000000000000022164896780109909073000000000000012300041420155900001120155904142108539901000423PGK114023PGK00000011PHA Health Assurance LiPOM            PG598015660550600001190000503`@    320200:000107:0508180236:9073489672                                               53200364<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//"<Sms>\n" +
//"  <AcqInstCountryCode>598</AcqInstCountryCode>\n" +
//"  <AcqInstIdCode>428280</AcqInstIdCode>\n" +
//"  <NwrkId>0002</NwrkId>\n" +
//"  <ChbackReduct>   8   </ChbackReduct>\n" +
//"  <ReimburseAttr>0</ReimburseAttr>\n" +
//"  <SysTrace>581153</SysTrace>\n" +
//"  <RetrievalRefNr>629214581153</RetrievalRefNr>\n" +
//"  <MessageStatusFlags>000000</MessageStatusFlags>\n" +
//"</Sms>AADE3C7DBD99C3EC74A1DF2245555555555555552";
//String hexmsg ="0100F23C448000E080000000000000000022164896780109909073000000000000012300041420155900001120155904142108539901000423PGK114023PGK00000011PHA Health Assurance LiPOM            PG598015660550600001190000134`@     320200:000107:0508180236:9073489672                                               532AADE3C7DBD99C3EC74A1DF2245555555555555552";
        String hexmsg = "0100F23C448000E080000000000000000022164896780109909073000000000000012300041420155900001120155904142108539901000423PGK114023PGK00000011PHA Health Assurance LiPOM            PG598015660550600001190000292`@    320200:000107:0508180236:9073489672                                               53200153221ThirdPartyBillPayment3125<ThirdPartyBillPayment><BillPaymentRequest><ReferenceId>1111111111</ReferenceId></BillPaymentRequest></ThirdPartyBillPayment>AADE3C7DBD99C3EC74A1DF2245555555555555552";
        byte[] bmsg = ISOUtil.hex2byte(hexmsg);
        ISOMsg m = new ISOMsg();
        // set packager, change ISO87BPackager for the matching one.
        m.setPackager(packager);
        //unpack the message using the packager
        m.unpack(hexmsg.getBytes());

        System.out.println(ISOUtil.byte2hex(bmsg));
        //dump the message to standar output
        m.dump(System.out, "");
        byte b[] = m.pack();
        m.unpack(b);
        System.out.println(ISOUtil.hexdump(b));

//        ISOMsg m2 = new ISOMsg();
//        m2.setMTI("0800");
//        m2.set(7, "0413204438");
//        m2.set(11, "10");
//        m2.set(12, "204438"); 
//        m2.set(13, "0413");
//        m2.set(70, "001");
//        m2.setPackager(packager);
//        byte b[] = m2.pack();
//        String s = new String(b);  
//        System.out.println(s);
//
//        System.out.println(ISOUtil.hexString(b));
//        
//        m2.unpack(b);
//        System.out.println(ISOUtil.hexdump(b));
//        m2.dump(System.out,"");        
    }
}
