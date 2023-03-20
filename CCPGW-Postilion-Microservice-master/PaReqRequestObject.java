/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.objects;

import com.google.gson.Gson;
import com.truteq.ccpgw.netcetera.model.Browser;
import com.truteq.ccpgw.netcetera.model.Cardholder;
import com.truteq.ccpgw.netcetera.model.Merchant;
import com.truteq.ccpgw.netcetera.model.Purchase;
import java.util.List;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PaReqRequestObject {
    

    private Cardholder cardholder;
    private Purchase purchase;
    private Merchant merchant;
    private Browser browser;
    private List<Object> extension;

    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }

    /**
     * @return the cardholder
     */
    public Cardholder getCardholder() {
        return cardholder;
    }

    /**
     * @param cardholder the cardholder to set
     */
    public void setCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;
    }

    /**
     * @return the purchase
     */
    public Purchase getPurchase() {
        return purchase;
    }

    /**
     * @param purchase the purchase to set
     */
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    /**
     * @return the merchant
     */
    public Merchant getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    /**
     * @return the browser
     */
    public Browser getBrowser() {
        return browser;
    }

    /**
     * @param browser the browser to set
     */
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    /**
     * @return the extension
     */
    public List<Object> getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(List<Object> extension) {
        this.extension = extension;
    }
}