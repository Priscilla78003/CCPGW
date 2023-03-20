/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.frontend.adapter.json.database.controller;

import com.google.gson.Gson;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.payment.gateway.api.json.TempCard;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import com.truteq.general.util.AESEncryptionDecryption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@RestController
@RequestMapping("/ccpgwjsonadapter/")
public class CardData {

    private LogWrapper mLogger = new LogWrapper(CardData.class);

    @Value("${transaction.manager.certificate}")
    private String keystore;

    @Value("${transaction.manager.platformpac.keystore.password}")
    private String keystorepasswordplatformpac;

    @Value("${communicator.secret}")
    private String secret;

    @Value("${transaction.manager.db.read.tempcard}")
    private String tempcardRead;

    @Value("${transaction.manager.db.delete.tempcard}")
    private String tempcardDelete;

    private AESEncryptionDecryption decryptor;
    private String decryptedkeystorepasswordplatformpac;


    @PostMapping(path = "tempcard/read", consumes = "application/json")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_READ_TEMPCARD')")
    public TempCard getCard(@RequestBody TempCard theCard) {

        getmLogger().info("Retrieving card data to database: sessionid = " + theCard.getSessionid(), new Throwable().getStackTrace()[0]);
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        SSLCommunicator sslComms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        Result result = sslComms.sendHttpPost(getTempcardRead(), theCard.toJSON().getBytes());
        getmLogger().info(result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);
        Gson gson = new Gson();
        TempCard tCard = gson.fromJson(result.getData(), TempCard.class);
        getmLogger().debug("payment method code in getCard CardData :: " + tCard.getPayment_method_code());
        return tCard;
    }

    @PostMapping(path = "tempcard/delete", consumes = "application/json")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_DELETE_TEMPCARD')")
    public void deleteCard(@RequestBody TempCard theCard) {
        getmLogger().info("Deleting card data from database: sessionid = " + theCard.getSessionid(), new Throwable().getStackTrace()[0]);
        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        SSLCommunicator sslComms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        sslComms.sendHttpPost(getTempcardDelete(), theCard.toJSON().getBytes());
    }

    /**
     * @return the mLogger
     */
    public LogWrapper getmLogger() {
        return mLogger;
    }

    /**
     * @param mLogger the mLogger to set
     */
    public void setmLogger(LogWrapper mLogger) {
        this.mLogger = mLogger;
    }

    /**
     * @return the keystore
     */
    public String getKeystore() {
        return keystore;
    }

    /**
     * @param keystore the keystore to set
     */
    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    /**
     * @return the keystorepasswordplatformpac
     */
    public String getKeystorepasswordplatformpac() {
        return keystorepasswordplatformpac;
    }

    /**
     * @param keystorepasswordplatformpac the keystorepasswordplatformpac to set
     */
    public void setKeystorepasswordplatformpac(String keystorepasswordplatformpac) {
        this.keystorepasswordplatformpac = keystorepasswordplatformpac;
    }

    /**
     * @return the secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret the secret to set
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @return the tempcardRead
     */
    public String getTempcardRead() {
        return tempcardRead;
    }

    /**
     * @param tempcardRead the tempcardRead to set
     */
    public void setTempcardRead(String tempcardRead) {
        this.tempcardRead = tempcardRead;
    }

    /**
     * @return the tempcardDelete
     */
    public String getTempcardDelete() {
        return tempcardDelete;
    }

    /**
     * @param tempcardDelete the tempcardDelete to set
     */
    public void setTempcardDelete(String tempcardDelete) {
        this.tempcardDelete = tempcardDelete;
    }

    /**
     * @return the decryptor
     */
    public AESEncryptionDecryption getDecryptor() {
        return decryptor;
    }

    /**
     * @param decryptor the decryptor to set
     */
    public void setDecryptor(AESEncryptionDecryption decryptor) {
        this.decryptor = decryptor;
    }

    /**
     * @return the decryptedkeystorepasswordplatformpac
     */
    public String getDecryptedkeystorepasswordplatformpac() {
        return decryptedkeystorepasswordplatformpac;
    }

    /**
     * @param decryptedkeystorepasswordplatformpac the decryptedkeystorepasswordplatformpac to set
     */
    public void setDecryptedkeystorepasswordplatformpac(String decryptedkeystorepasswordplatformpac) {
        this.decryptedkeystorepasswordplatformpac = decryptedkeystorepasswordplatformpac;
    }

    
    
}
