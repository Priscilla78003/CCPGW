
import com.google.gson.Gson;
import com.truteq.ccpgw.payment.gateway.api.json.PaymentDetails;
import com.truteq.ccpgw.payment.gateway.api.json.TransactionFullDetails;
import com.truteq.ccpgw.payment.gateway.util.DataEncryption;
import java.math.BigDecimal;

/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testEncryption {
    public static void main(String args[]) throws Exception {
        DataEncryption encrypt = new DataEncryption();
        
        PaymentDetails payDetail = new PaymentDetails();
        
        payDetail.setName("Grant");
        payDetail.setNumber("4916994064252017");
        payDetail.setAmount(new BigDecimal("10"));
        payDetail.setCurrency("PGK");
        payDetail.setCvv("123");
        payDetail.setExpiryDate("12/26");
        payDetail.setParequest("");
        payDetail.setParesponse("eyJtZXNzYWdlVHlwZSI6IkNSZXMiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjliNmU0Y2Q1LWNkZDktNDljOS05ZGY4LTVhMTg5OGE3ZTkzNiIsImFjc1RyYW5zSUQiOiI5ZmVhOTkxNC0wMmFkLTQ2YjctYTUzMS0xMWNiNTM3ZTQ5N2EiLCJjaGFsbGVuZ2VDb21wbGV0aW9uSW5kIjoiWSIsIm1lc3NhZ2VWZXJzaW9uIjoiMi4xLjAiLCJ0cmFuc1N0YXR1cyI6IlkifQ");
        payDetail.setSessionid("");
        payDetail.setDatetime("");
        payDetail.setAddressLine1("19 Cumberland Way");
        payDetail.setAddressLine2("");
        payDetail.setCity("Buderim");
        payDetail.setStateProv("Queensland");
        payDetail.setCountryCode("61");
        payDetail.setCountryNam("AUS");
        payDetail.setPostalCode("4556");
    
        String encryptedString = encrypt.EncryptAES256(payDetail.toJSON());
        
        TransactionFullDetails details = new TransactionFullDetails("9b6e4cd5-cdd9-49c9-9df8-5a1898a7e936",
                                                            "1234",
                                                            "t1234",
                                                            "",
                                                            encryptedString);
        
        String decryptedString = encrypt.DecryptAES256(encryptedString);
        
        Gson gson = new Gson();
        PaymentDetails payDetail2 = gson.fromJson(decryptedString, PaymentDetails.class);
        
        System.out.println("JSON Object           : "+details.toJSON());
        System.out.println("Encrypted JSON Object : "+encryptedString);
        System.out.println("Decrypted JSON Object : "+decryptedString);
        
        System.out.println("PaymentDetail JSON Object : "+payDetail2.toJSON());
    }    
}
