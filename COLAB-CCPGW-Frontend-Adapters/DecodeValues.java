/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.frontend.adapter.json;

import com.truteq.general.util.AESEncryptionDecryption;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class DecodeValues {
    
//json.rest.user.name=mJCe6Moyg3z+zBtSbi4+BSC5tnCvRkTJZNhPA4zqQSk=
//json.rest.password=5AoAMJwFe0xT6LE6et/weQOZfftlCbt9sE/3AdiFruU=
//json.rest.role=IplKrKwDFoLs8k5O00Znmg==    
    

    
    public static void main(String[] args) {
           AESEncryptionDecryption decryptor = new  AESEncryptionDecryption();
           System.out.println(decryptor.decrypt("mJCe6Moyg3z+zBtSbi4+BSC5tnCvRkTJZNhPA4zqQSk=","adm1nttpp"));
           System.out.println(decryptor.decrypt("5AoAMJwFe0xT6LE6et/weQOZfftlCbt9sE/3AdiFruU=","adm1nttpp"));
           System.out.println(decryptor.decrypt("IplKrKwDFoLs8k5O00Znmg==","adm1nttpp"));
    }
}
