/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.comms.serv.ws.test;
import com.truteq.general.util.AESEncryptionDecryption;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testDecryptPassword {
    public static void main(String[] args) {
        String userName = "oy8JRKqtFvKeBfPoYdRVJSZVFFRQLLMnJZai2UACYLo=";
        String password = "bHuAWRdSKYVEDu0UWAOEKAJO4bUD+pkcAs5gaBx433PYImoPGazHO/9GhZWh3VUz";
        String role = "IplKrKwDFoLs8k5O00Znmg==";
        
        String keypassword = "QkoMCqFLVDFZpgkdLzjTlA==";
        String keypassword2 = "1UhfTWQFcy0sitoOIveklw==";
        
        String toEncode = "platformpac";
        
        AESEncryptionDecryption decryptor;
        String decodedRole = null; 
        String decodedUserName = null; 
        String decodedPassword = null; 
        String decodedKeyPassword = null; 
        String decodedKeyPassword2 = null;
        
        String encryptStr = null;
        
        try {
            decryptor = new AESEncryptionDecryption();
            decodedRole = decryptor.decrypt(role, "adm1nttpp");
            decodedUserName = decryptor.decrypt(userName, "adm1nttpp");
            decodedPassword = decryptor.decrypt(password, "adm1nttpp");
            decodedKeyPassword = decryptor.decrypt(keypassword, "adm1nttpp");
            decodedKeyPassword2 = decryptor.decrypt(keypassword2, "adm1nttpp");
            encryptStr = decryptor.encrypt(toEncode, "adm1nttpp");

        } catch (Exception ex) {

        } 
        
        System.out.println("Role:"+decodedRole);
        System.out.println("Username:"+decodedUserName);
        System.out.println("Password:"+decodedPassword);
        System.out.println("KeyPassword:"+decodedKeyPassword);
        System.out.println("KeyPassword2:"+decodedKeyPassword2);
        System.out.println("Encrypt string:"+encryptStr);
    }
}
