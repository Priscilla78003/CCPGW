/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.googleauth.test;

import com.warrenstrange.googleauth.CredentialRepositoryMock;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
//import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base32;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestGoogleAuthforUser {
    
    String secret;
    String secretKey;
    long validationCode;
    List<Integer> scratchCodes;
    

    public TestGoogleAuthforUser(String secret){
        this.secret = secret;  
    }
       
    private void setupMockCredentialRepository(String key) {
        System.setProperty(
                CredentialRepositoryMock.MOCK_SECRET_KEY_NAME,
                key);
    }
    
    public void authoriseUser() {
        
         GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder gacb =
                new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                        .setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
                        .setWindowSize(5)
                        .setCodeDigits(6);
         GoogleAuthenticator ga = new GoogleAuthenticator(gacb.build());
        
         final GoogleAuthenticatorKey key = ga.createCredentials(this.secret);
         this.secretKey = key.getKey();
         
         Base32 codec = new Base32();
         byte[] decodedKey = codec.decode(this.secretKey);
         long timeWindow = new Date().getTime() / (TimeUnit.SECONDS.toMillis(30));
         this.validationCode = ga.calculateCode(decodedKey, timeWindow );
        
        setupMockCredentialRepository(this.secretKey); 
        boolean isCodeValid = ga.authorizeUser(this.secretKey , (int)this.validationCode);
        System.out.println("Check VALIDATION_CODE = " + isCodeValid);
    }    
    
    //Secret Key = ZC4XHL663WR4IMVX
    public boolean validate(int code){
        GoogleAuthenticator ga = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = ga.createCredentials(this.secret);
        System.out.println( "Key: "+ key.getKey());
        return ga.authorize("ZC4XHL663WR4IMVX", 367480);
    }
    
    public void Credentials(){
        GoogleAuthenticatorKey credentials = new GoogleAuthenticatorKey("ZC4XHL663WR4IMVX", 114785, new ArrayList<Integer>());
        //String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthURL("Acme", "alice@example.com", credentials);
    }
    
    public static void main(String[] args) {
        TestGoogleAuthforUser  test = new TestGoogleAuthforUser("gbo@truteq.com");
        test.Credentials();
        System.out.println( "Validate: "+ test.validate(679888));
        test.authoriseUser();
        
        
        
    }
}
