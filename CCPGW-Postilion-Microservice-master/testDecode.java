/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.googleauth.test;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base32;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testDecode {
        
     private boolean compareVal(GoogleAuthenticator ga, String secretKey, long code, long timestamp, int window){
        // convert unix time into a 30 second "window" as specified by the
        // TOTP specification. Using Google's default interval of 30 seconds.
        final long timeWindow = timestamp / (TimeUnit.SECONDS.toMillis(30));

        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secretKey);
        // Calculating the verification code of the given key in each of the
        // time intervals and returning true if the provided code is equal to
        // one of them.
        for (int i = -((window - 1) / 2); i <= window / 2; ++i)
        {
            // Calculating the verification code for the current time interval.
            long hash = ga.calculateCode(decodedKey, timeWindow + i);
            System.out.println("hash is " + hash); 
            // Checking if the provided code is equal to the calculated one.
            if (hash == code)
            {
                // The verification code is valid.
                return true;
            }
        }
        return false;        
    }        
     
    public void authoriseUser() {
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder gacb =
                new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                        .setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
                        .setWindowSize(5)
                        .setCodeDigits(6);
        GoogleAuthenticator ga = new GoogleAuthenticator(gacb.build());
        
         final GoogleAuthenticatorKey key = ga.createCredentials("gbo@truteq.com");
         String secretkey = key.getKey();
         int verificationcode = key.getVerificationCode();
         System.out.println("Secret Key = " + secretkey );
         System.out.println(" verificationcode = " +  verificationcode );
         
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secretkey);
        long timeWindow = new Date().getTime() / (TimeUnit.SECONDS.toMillis(30));
        long hash = ga.calculateCode(decodedKey, timeWindow );
        System.out.println(" hash value= " +  hash );
        
        boolean isCodeValid1 = compareVal(ga, secretkey, hash, new Date().getTime() , 3);
        System.out.println("Check VALIDATION_CODE = " + isCodeValid1);

    }    
    
    //Secret Key = ZC4XHL663WR4IMVX
        public void authoriseUser2() {
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder gacb =
                new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                        .setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
                        .setWindowSize(5)
                        .setCodeDigits(6);
        GoogleAuthenticator ga = new GoogleAuthenticator(gacb.build());
        
         final GoogleAuthenticatorKey key = ga.createCredentials("grantboreilly@gmail.com");
         String secretkey = key.getKey();
         int verificationcode = key.getVerificationCode();
         System.out.println("Secret Key = " + secretkey );
         System.out.println(" verificationcode = " +  verificationcode );
         
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode("ZC4XHL663WR4IMVX");
        long timeWindow = new Date().getTime() / (TimeUnit.SECONDS.toMillis(30));
        long hash = ga.calculateCode(decodedKey, timeWindow);
        System.out.println(" TOTP value = " +  hash );
        
        boolean isCodeValid1 = compareVal(ga, secretkey, hash, new Date().getTime() , 3);
        System.out.println("Check VALIDATION_CODE = " + isCodeValid1);

    }    
         
        public static void main(String[] args) {
            testDecode test = new testDecode();
            //test.authoriseUser();
            test.authoriseUser2();
        }
        
}
