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
package com.truteq.ccpgw.frontend.adapter.sabre.api.batchprocessing;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ProcessBatchFile {
    
    private final LogWrapper mLogger = new LogWrapper(ProcessBatchFile.class); 
    
    List<StringBuilder> lines = new ArrayList<>();
    List<BillingFileTransaction> transactions = new ArrayList<>();
    
    
    public ProcessBatchFile(){
        
    }

    public void readFile(String filename) {
        
        try {
            File file = new File(filename);             //creates a new file instance  
            FileReader fr = new FileReader(file);       //reads the file  
            BufferedReader br = new BufferedReader(fr); //creates a buffering character input stream   
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length()>1){
                    StringBuilder sb = new StringBuilder();     //constructs a string buffer with no characters 
                    sb.append(line);                        //appends line to string buffer  
                    sb.append("\n");                        //line feed
                    lines.add(sb);
                }
            }
            br.close();
            fr.close();                                //closes the stream and release the resources
            
 
        } catch (IOException e) {
             mLogger.error("Exception reading file " + filename + ". Fault: " + e);
        }
    }
    
    private String extractTransactionType(StringBuilder sb){
        switch (sb.charAt(0)){
            case 'S': return "Sale";
            case 'R': return "Refund";   
        }
        return "Unknown";        
    }
    
    private String extractDocumentType(StringBuilder sb){
        switch (sb.charAt(1)){
            case 'A': return "Air";
            case 'M': return "Miscellaneous";
            case 'C': return "Car";  
            case 'H': return "Hotel";  
        }
        return "Unknown";        
    }
    
    public void processData(){
        
        for (StringBuilder sb : lines){
            BillingFileTransaction bft = new BillingFileTransaction();
            bft.setTransactionType(extractTransactionType(sb));
            bft.setDocumentType(extractDocumentType(sb));
            bft.setDocumentNumber(sb.substring(2,17));
            bft.setIssueDateStr(sb.substring(18,24));
            bft.setIssueTimeStr(sb.substring(24,30));
            bft.setPnrLocator(sb.substring(30,37));
            bft.setSequenceNumber(Integer.parseInt(sb.substring(38,39)));
            bft.setSequenceCount(Integer.parseInt(sb.substring(39,42)));
            bft.setCreditCardType(sb.substring(42,44));
            bft.setCreditCardNumber(sb.substring(44,69));
            bft.setExpireDateStr(sb.substring(69,73));
            bft.setPassengerName(sb.substring(73,103));      
            bft.setMerchantNumber(sb.substring(103,123));
            bft.setStationNumber(Integer.parseInt(sb.substring(123,131)));
            bft.setPosCity(sb.substring(131,144));
            bft.setPosState(sb.substring(144,147));
            bft.setPosCountry(sb.substring(147,150));
            bft.setPosZipCode(sb.substring(150,160));
            bft.setChannelId(sb.substring(160,163));
            bft.setInternetIndicator(sb.substring(163,164));
            bft.setAirLineCode(sb.substring(164,167));
            bft.setDbaName(sb.substring(167,189));
            bft.setAuthCode(sb.substring(189,197));
            bft.setTransactionId(sb.substring(197,212));
            bft.setPayPlan(sb.substring(212,215));
            bft.setAuthAmount(Integer.parseInt(sb.substring(215,230)));
            bft.setAuthDecimalPosition(sb.substring(230,231));
            bft.setAuthCurrency(sb.substring(231,234));
            bft.setDocAmount(sb.substring(234,249));
            bft.setDocDecimalPosition(sb.substring(249,250));
            bft.setDocCurrency(sb.substring(250,253));
            bft.setBilledAmount(Integer.parseInt(sb.substring(253,268)));
            bft.setBilledDecimalPosition(sb.substring(268,269));
            bft.setBilledCurrency(sb.substring(269,272));
            bft.setTerminalId(sb.substring(272,280).trim().length() == 0 ? -1 : Integer.parseInt(sb.substring(272,280)));
            bft.setEciIndicator(sb.substring(280,282));
            bft.setAuthCharacteristicIndicator(sb.substring(282,283));
            bft.setAvsResultCode(sb.substring(283,284));
            bft.setValidationCode(sb.substring(284,288));
            bft.setAuthSourceCode(sb.substring(288,289));
            bft.setAuthResponseCode(sb.substring(289,295));
            bft.setThreeDSIndicator(sb.substring(295,296));    
            bft.setMasterCard2ndLevelIndicator(sb.substring(296,299));
            bft.setCvv2PresenceId(sb.substring(299,300));
            bft.setCvv2ResultCode(sb.substring(300,301));
            bft.setCavvResult(sb.substring(301,329));
            bft.setCavvResultCode(sb.substring(329,330));
            bft.setEntryMode(sb.substring(330,332));
            bft.setPosEntryMode(sb.substring(332,333));
            bft.setPosIndicator(sb.substring(333,334));
            bft.setPosDataCode(sb.substring(334,346));
            bft.setEmvChipData(sb.substring(346,604));
            bft.setSerialNumber(sb.substring(604,613));
            bft.setInvoiceNumber(sb.substring(613,621));
            bft.setSystemTraceAuditNumber(sb.substring(621,627));
            bft.setProductID(sb.substring(627,629));
            bft.setSupplierCode(sb.substring(629,633));
            bft.setAuthDate(sb.substring(633,638));
            bft.setAuthTime(sb.substring(638,644));
            bft.setTaxesFees(sb.substring(644,804));
            bft.setTokenizedCreditCard(sb.substring(804,805));
            bft.setApplePayIndicator(sb.substring(805,806));
            bft.setTransactionDataConditionCode(sb.substring(806,808));
            bft.setTransactionIntegrityClass(sb.substring(808,810));
            bft.setFiller1(sb.substring(810,821));
            bft.setDCCIndicator(sb.substring(821,822));
            bft.setDCCType(sb.substring(822,823));
            bft.setDCCRate(sb.substring(823,838));
            bft.setDCCAmount(Integer.parseInt(sb.substring(838,853)));
            bft.setDCCDecimalPosition(Integer.parseInt(sb.substring(853,854)));
            bft.setDCCCurrency(sb.substring(854,857).trim().length() == 0 ? -1 : Integer.parseInt(sb.substring(854,857)));
            bft.setDCCOfferDate(sb.substring(857,865));
            bft.setDCCOfferTime(sb.substring(865,871));
            bft.setDCCTimeZone(sb.substring(871,874));
            bft.setCustomerReferenceNumber(sb.substring(874,901));
            bft.setPSPReferenceNumber(sb.substring(901,965));
            bft.setMandateID(sb.substring(965,975));
            bft.setDirectoryServerTransactionID(sb.substring(976,1015));
            bft.setFiller2(sb.substring(1015,1032));
            bft.setWorkingStorage(sb.substring(1032,1062));
            
            AirDetail airDetail = new AirDetail();
            
            if (bft.getTransactionType().equals("Sale")){    
                if (sb.length() > 1065 ) airDetail.setConjunction(sb.substring(1063,1065));
                if (sb.length() > 1069 ) airDetail.setCoupons(sb.substring(1065,1069));
                if (sb.length() > 1084 ) airDetail.setRoutingCity(sb.substring(1069,1084));
                if (sb.length() > 1096 ) airDetail.setAirlineCodes(sb.substring(1084,1096));
                if (sb.length() > 1128 ) airDetail.setFareBasis(sb.substring(1096,1128));
                if (sb.length() > 1136 ) airDetail.setClassofService(sb.substring(1128,1136));
                if (sb.length() > 1140 ) airDetail.setStopoverCodes(sb.substring(1136,1140));
                if (sb.length() > 1164 ) airDetail.setDepartureDate(sb.substring(1140,1164));
                if (sb.length() > 1184 ) airDetail.setFlightNumber(sb.substring(1164,1184));
                if (sb.length() > 1204 ) airDetail.setFlightTimes(sb.substring(1184,1204));    
            }
            else if (bft.getTransactionType().equals("Refund")){
                if (sb.length() > 1065 )  airDetail.setConjunction(sb.substring(1063,1065));
                if (sb.length() > 1069 )  airDetail.setCoupons(sb.substring(1065,1069));
                if (sb.length() >= 1073 ) airDetail.setRoutingCity(sb.substring(1069,1073));
        }
            bft.setAirDetail(airDetail);
            
            transactions.add(bft);
        }

    }
    
    public void displayTransactions(){
        for (BillingFileTransaction bft : transactions)
            mLogger.info(bft.toJSON());
    }
  
    
}
