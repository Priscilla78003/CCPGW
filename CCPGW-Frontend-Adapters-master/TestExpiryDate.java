/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Postilion Restful server : POSTILION - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ipgw.test.postilion.comms;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestExpiryDate {
    
    private String checkExpiryDate(String datetime){
        
        //Receive datetime as MMYYYY from Sabre
        //Need to return expiry date as YYMM
        
        if (datetime.length()>4){
            
          String monthString = datetime.substring(0, 2);
          String yearString = datetime.substring(4, 6);

          return yearString+monthString;
            
        }
        else if (datetime.length()==4){
          
          String monthString = datetime.substring(0, 2);
          String yearString = datetime.substring(2, 4);

          return yearString+monthString;   
        }
        else if (datetime.length() == 3) {

            String monthString = "0"+datetime.substring(0, 1);
            String yearString = datetime.substring(1, 3);

            return yearString + monthString;
        }
        return datetime;
    }
    
    private String checkAmount(String amount){
        
        if(amount.contains(".")){
            Float floatAmount = new Float(amount)*100; 
            String centAmount =  Integer.toString(floatAmount.intValue());
            return centAmount;
        }
        return amount;
    }    
    
    private String reverseMonthYear(String datetime){
        String expiryDate = checkExpiryDate(datetime);
        String monthString = expiryDate.substring(0, 2);
        String yearString = expiryDate.substring(2, 4);
        return yearString+monthString; 
    }
    
    
    public static void main(String[] args) {
        String datetime = "062023";
        String datetime2 = "0623";
        String datetime3 = "623";
        
        TestExpiryDate test = new TestExpiryDate();
        
        System.out.println(datetime);
        System.out.println(test.checkExpiryDate(datetime));
        System.out.println(test.checkExpiryDate(datetime2));
        System.out.println(test.reverseMonthYear(datetime));
        System.out.println(test.checkExpiryDate(datetime3));
        
        System.out.println(test.checkAmount("10.02"));
    }
}
