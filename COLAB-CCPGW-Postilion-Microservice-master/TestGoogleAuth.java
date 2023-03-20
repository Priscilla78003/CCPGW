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
import com.warrenstrange.googleauth.KeyRepresentation;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestGoogleAuth {
    
    String secret;
    int validatecode;
    List<Integer> scratchCodes;
    
    // Change this to the saved secret from the running the above test.
    @SuppressWarnings("SpellCheckingInspection")
    private static final String SECRET_KEY = "ZC4XHL663WR4IMVX";
    private static final int VALIDATION_CODE = 192415;

    public static void setupMockCredentialRepository() {
        System.setProperty(
                CredentialRepositoryMock.MOCK_SECRET_KEY_NAME,
                SECRET_KEY);
    }

    private static byte[] hexStr2Bytes(String hex) {
        // Adding one byte to get the right conversion
        // Values starting with "0" can be converted
        byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();

        // Copy all the REAL bytes, not the "first"
        byte[] ret = new byte[bArray.length - 1];
        System.arraycopy(bArray, 1, ret, 0, ret.length);

        return ret;
    }

    public void rfc6238TestVectors() {
        // See RFC 6238, p. 14
        final String rfc6238TestKey = "3132333435363738393031323334353637383930";
        final byte[] key = hexStr2Bytes(rfc6238TestKey);
        final long testTime[] = {59L, 1111111109L, 1111111111L, 1234567890L, 2000000000L, 20000000000L};
        final long testResults[] = {94287082, 7081804, 14050471, 89005924, 69279037, 65353130};
        final long timeStepSizeInSeconds = 30;

        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder cb = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder();
        cb.setCodeDigits(8).setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(timeStepSizeInSeconds));
        GoogleAuthenticator ga = new GoogleAuthenticator(cb.build());

        for (int i = 0; i < testTime.length; ++i) {
            System.out.println(ga.calculateCode(key, testTime[i] / timeStepSizeInSeconds)+" = "+ testResults[i]);
        }
    }

    public void createCredentials() {
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder gacb =
                new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                        .setKeyRepresentation(KeyRepresentation.BASE64);
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator(gacb.build());

        final GoogleAuthenticatorKey key =
                googleAuthenticator.createCredentials();
        final String secret = key.getKey();
        final List<Integer> scratchCodes = key.getScratchCodes();

        //String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthURL("TRUPAY", "gbo@truteq.com", key);

        //System.out.println("Please register (otpauth uri): " + otpAuthURL);
        System.out.println("Base64-encoded secret key is " + secret);

        for (Integer i : scratchCodes) {
            if (!googleAuthenticator.validateScratchCode(i)) {
                throw new IllegalArgumentException("An invalid code has been " +
                        "generated: this is an application bug.");
            }
            System.out.println("Scratch code: " + i);
        }
    }

    public void createCredentialsForUser() {
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

        final GoogleAuthenticatorKey key =
                googleAuthenticator.createCredentials("gbo@truteq.com");
        final String secret = key.getKey();
        final List<Integer> scratchCodes = key.getScratchCodes();

        //String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthURL("TRUPAY", "gbo@truteq.com", key);

        //System.out.println("Please register (otpauth uri): " + otpAuthURL);
        System.out.println("Secret key is " + secret);

        for (Integer i : scratchCodes) {
            if (!googleAuthenticator.validateScratchCode(i)) {
                throw new IllegalArgumentException("An invalid code has been " +
                        "generated: this is an application bug.");
            }
            System.out.println("Scratch code: " + i);
        }
    }

    public void authorise() {
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder gacb =
                new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                        .setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
                        .setWindowSize(5);
        GoogleAuthenticator ga = new GoogleAuthenticator(gacb.build());

        boolean isCodeValid = ga.authorize(SECRET_KEY, VALIDATION_CODE);

        System.out.println("Check VALIDATION_CODE = " + isCodeValid);
    }


    public void authoriseUser() {
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder gacb =
                new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                        .setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
                        .setWindowSize(5)
                        .setCodeDigits(6);
        GoogleAuthenticator ga = new GoogleAuthenticator(gacb.build());

        boolean isCodeValid = ga.authorizeUser("gbo@truteq.com", VALIDATION_CODE);

        System.out.println("Check VALIDATION_CODE = " + isCodeValid);
    } 
    
    
    
    public static void main(String[] args) {
        TestGoogleAuth test = new TestGoogleAuth ();
        TestGoogleAuth.setupMockCredentialRepository();
        //test.rfc6238TestVectors();
        test.createCredentials();
        test.createCredentialsForUser();
        test.authorise();
        test.authoriseUser();
        
    }
}
