/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.frontend.adapter.json.database.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.payment.gateway.api.json.Order;
import com.truteq.ccpgw.payment.gateway.api.json.OrderList;
import com.truteq.ccpgw.payment.gateway.api.json.OrderObj;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import com.truteq.general.util.AESEncryptionDecryption;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import org.springframework.http.HttpHeaders;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@RestController
@RequestMapping("/ccpgwjsonadapter/")
public class OrderData {

    private LogWrapper mLogger = new LogWrapper(OrderData.class);

    @Value(value = "${ccpgw.payment.gateway.orderid}")
    private String ccpgwPaymentGatewayOrderid;

    @Value("${transaction.manager.certificate}")
    private String keystore;

    @Value("${transaction.manager.platformpac.keystore.password}")
    private String keystorepasswordplatformpac;

    @Value("${communicator.secret}")
    private String secret = "adm1nttpp";

    @Value("${transaction.manager.db.write.order}")
    private String writeOrder;

    @Value("${transaction.manager.db.read.order.by.merchantkey}")
    private String readOrderByMerchantkey;

    private AESEncryptionDecryption decryptor;
    private String decryptedkeystorepasswordplatformpac;

    @PostMapping(path = "order/payment")
    //@CrossOrigin(origins = "*")
    public String payForOrder(@RequestBody OrderObj orderObj) throws Exception {

        getmLogger().info("To process payment for order id = " + orderObj.getOrder_id());

        Order order = decryptOrderDetails(orderObj.getMerchant_key(), orderObj.getOrder_detail());
        order.setOrder_id(orderObj.getOrder_id());
        HttpHeaders headers = new HttpHeaders();

        setDecryptor(new AESEncryptionDecryption());
        setDecryptedkeystorepasswordplatformpac(getDecryptor().decrypt(getKeystorepasswordplatformpac(), getSecret()));
        SSLCommunicator sslComms = new SSLCommunicator(getKeystore(), getDecryptedkeystorepasswordplatformpac());
        getmLogger().info("why is merchant key null :  mOrder.getSecretKey()= " + order.getMerchant_key() + "orderObj.getMerchant_key()" + orderObj.getMerchant_key());
        Result result = sslComms.sendHttpPost(getReadOrderByMerchantkey(), order.toJSON().getBytes());
        getmLogger().info("Order duplicate check result:: " + result.getComments() + " " + result.getData(), new Throwable().getStackTrace()[0]);

        Gson gson = new Gson();
        OrderList duplicated = gson.fromJson(result.getData(), OrderList.class);
        if (duplicated == null || (duplicated != null && duplicated.getOrderList() == null)
                || (duplicated != null && duplicated.getOrderList() != null && duplicated.getOrderList().size() <= 0)) {

            Result result2 = sslComms.sendHttpPost(getWriteOrder(), order.toJSON().getBytes());
            getmLogger().info("Order duplicate check result2:: " + result2.getComments() + " " + result2.getData(), new Throwable().getStackTrace()[0]);
            URI theURI = URI.create(ccpgwPaymentGatewayOrderid + orderObj.getOrder_id() + "&merchant_key=" + orderObj.getMerchant_key());
            return theURI.toString();
        } else {

            URI theURI = URI.create(order.getCallback_url() + "&status=failed&reason=duplicate_order_id");
            return theURI.toString();
        }
    }

    private Order decryptOrderDetails(String merchant_key, String order_details) {
        String CIPHER_NAME = "AES/CBC/PKCS5PADDING";
        try {
            String key = merchant_key.substring(0, 16);
            String[] parts = order_details.split(":");

            IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] decodedEncryptedData = Base64.getDecoder().decode(parts[0]);

            byte[] original = cipher.doFinal(decodedEncryptedData);

            //return 
            String objectStr = new String(original);

            Gson gson = new Gson();
            Order orderObj = gson.fromJson(objectStr, Order.class);

            return orderObj;

        } catch (JsonSyntaxException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            getmLogger().error("Exception in decryptOrderDetails :: error :" + ex);
        } catch (InvalidAlgorithmParameterException ex) {
            getmLogger().error("Error in decrypting order details " + ex.getMessage());
        }

        return null;
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
     * @return the writeOrder
     */
    public String getWriteOrder() {
        return writeOrder;
    }

    /**
     * @param writeOrder the writeOrder to set
     */
    public void setWriteOrder(String writeOrder) {
        this.writeOrder = writeOrder;
    }

    /**
     * @return the readOrderByMerchantkey
     */
    public String getReadOrderByMerchantkey() {
        return readOrderByMerchantkey;
    }

    /**
     * @param readOrderByMerchantkey the readOrderByMerchantkey to set
     */
    public void setReadOrderByMerchantkey(String readOrderByMerchantkey) {
        this.readOrderByMerchantkey = readOrderByMerchantkey;
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
     * @param decryptedkeystorepasswordplatformpac the
     * decryptedkeystorepasswordplatformpac to set
     */
    public void setDecryptedkeystorepasswordplatformpac(String decryptedkeystorepasswordplatformpac) {
        this.decryptedkeystorepasswordplatformpac = decryptedkeystorepasswordplatformpac;
    }

}
