/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.payment.gateway.util;

import com.google.gson.Gson;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.payment.gateway.api.json.EncryptedCard;
import com.truteq.ccpgw.payment.gateway.api.json.Refund;
import com.truteq.ccpgw.transaction.manager.merchant.portal.authenticate.authorise.EncryptionSession;
import com.truteq.ccpgw.payment.gateway.api.soap.AuthRQ;
import java.util.Base64;
import org.apache.tomcat.util.buf.HexUtils;

/**
 *
 * @author mho
 */
public class Decryption {

    private final LogWrapper mLogger = new LogWrapper(Decryption.class);

    public AuthRQ decryptPayload(EncryptedCard encryptedPayload, String RsaPrivateKey) throws Exception {
        mLogger.debug("GUID: " + encryptedPayload.getGuid());
        mLogger.debug("Public Key: " + encryptedPayload.getPublickey());
        mLogger.debug("Payload: " + encryptedPayload.getPayload());

        CryptoUtil cryptoUtil = new CryptoUtil();

        String encAesKeyBase64 = encryptedPayload.getPublickey();
        mLogger.debug("RSA encrypted Aes Key Base64 [len=" + encAesKeyBase64.length() + "]: " + encAesKeyBase64 + "\n");
        //decode from Base64 format
        byte[] encAesKeyBytes = Base64.getDecoder().decode(encAesKeyBase64);

        mLogger.debug("RSA encrypted Aes Key [len=" + encAesKeyBytes.length + "]: \n");

        //decrypt AES key with private RSA key
        byte[] decryptedAesKeyHex = null;
        try {
            decryptedAesKeyHex = cryptoUtil.decryptWithPrivateRsaKey(encAesKeyBytes, RsaPrivateKey);
        } catch (Exception ex) {
            mLogger.error("Error decrypting AES key with private RSA key, Exception = " + ex);
        }
        mLogger.debug("Decrypted Aes Key [hex]: " + new String(decryptedAesKeyHex) + "\n ");

        byte[] decryptedAesKey = HexUtils.fromHexString(new String(decryptedAesKeyHex));
        mLogger.debug("Decrypted Aes Key [len=" + decryptedAesKey.length + "]: " + new String(decryptedAesKey) + "\n");
        //initialization vector - 1st 16 chars of userId
        byte[] iv = encryptedPayload.getGuid().substring(0, 16).getBytes();

        mLogger.debug("Encrypted transaction BASE64 [len=" + encryptedPayload.getPayload().length() + "]: " + encryptedPayload.getPayload() + "n");

        byte[] encTransBytes = Base64.getDecoder().decode(encryptedPayload.getPayload());

        //decrypt transaction payload with AES key
        byte[] decrypted = null;
        try {
            decrypted = cryptoUtil.decryptWithAes(encTransBytes, decryptedAesKey, iv);
            mLogger.info("Decrypted transaction: " + new String(decrypted));
        } catch (Exception ex) {
            mLogger.error("Error decrypting AES transaction payload with AES key, Exception = " + ex);
        }

        return convertToPojo(new String(decrypted));

    }

    private AuthRQ convertToPojo(String transactionData) {
        mLogger.info("Converting transaction data into TransactionalObject." + transactionData);
        Gson gson = new Gson();
        String jsonString = "{\"field1\": \"blah blah 1\",\"field2\": \"blah blah 2\"}";
        Temp tempObj = gson.fromJson(jsonString, Temp.class);
        mLogger.debug("field1 = " + tempObj.getField1());
        mLogger.debug("field2 = " + tempObj.getField2());
        AuthRQ transactionObj = gson.fromJson(transactionData, AuthRQ.class);
        mLogger.debug(transactionObj.toJSON());
        return transactionObj;
    }

    public Refund decryptRefundPayload(EncryptionSession encryptedPayload, String RsaPrivateKey) throws Exception {
        mLogger.debug("GUID: " + encryptedPayload.getGuid());
        mLogger.debug("Public Key: " + encryptedPayload.getKey());
        mLogger.debug("Payload: " + encryptedPayload.getEncryption());

        CryptoUtil cryptoUtil = new CryptoUtil();

        String encAesKeyBase64 = encryptedPayload.getKey();
        mLogger.debug("RSA encrypted Aes Key Base64 [len=" + encAesKeyBase64.length() + "]: " + encAesKeyBase64 + "\n");
        //decode from Base64 format
        byte[] encAesKeyBytes = Base64.getDecoder().decode(encAesKeyBase64);

        mLogger.debug("RSA encrypted Aes Key [len=" + encAesKeyBytes.length + "]: \n");

        //decrypt AES key with private RSA key
        byte[] decryptedAesKeyHex = null;
        try {
            decryptedAesKeyHex = cryptoUtil.decryptWithPrivateRsaKey(encAesKeyBytes, RsaPrivateKey);
        } catch (Exception ex) {
            mLogger.error("Error decrypting AES key with private RSA key, Exception = " + ex);
        }
        mLogger.debug("Decrypted Aes Key [hex]: " + new String(decryptedAesKeyHex) + "\n ");

        byte[] decryptedAesKey = HexUtils.fromHexString(new String(decryptedAesKeyHex));
        mLogger.debug("Decrypted Aes Key [len=" + decryptedAesKey.length + "]: " + new String(decryptedAesKey) + "\n");
        //initialization vector - 1st 16 chars of userId
        byte[] iv = encryptedPayload.getGuid().substring(0, 16).getBytes();

        mLogger.debug("Encrypted transaction BASE64 [len=" + encryptedPayload.getEncryption().length() + "]: " + encryptedPayload.getEncryption() + "n");

        byte[] encTransBytes = Base64.getDecoder().decode(encryptedPayload.getEncryption());

        //decrypt transaction payload with AES key
        byte[] decrypted = null;
        try {
            decrypted = cryptoUtil.decryptWithAes(encTransBytes, decryptedAesKey, iv);
            mLogger.info("Decrypted refund object: " + new String(decrypted));
        } catch (Exception ex) {
            mLogger.error("Error decrypting AES transaction payload with AES key, Exception = " + ex);
        }

        return convertToRefundPojo(new String(decrypted));

    }

    private Refund convertToRefundPojo(String transactionData) {

        mLogger.info("Converting refund data into Refund object." + transactionData);

        Gson gson = new Gson();
        Refund transactionObj = gson.fromJson(transactionData, Refund.class);

        mLogger.debug(transactionObj.toJSON());

        return transactionObj;
    }
}
