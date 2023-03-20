/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.comms.serv.ws.test;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testMaskAccount {
    public static void main(String[] args) {
        String account = "4896780109909073";
        
        String mask_begin = account.substring(0, 3);
        String mask_end = account.substring(account.length()-3, account.length());
        
        int maskinglength = account.length()-(mask_begin.length()+mask_end.length());
        
        String mask = "";
        for (int i = 0; i < maskinglength; i++) mask = mask + "*";
        String maskedAccount = mask_begin+mask+mask_end;
        
        System.out.println(account);
        System.out.println(maskedAccount);
        
        
    }
}
