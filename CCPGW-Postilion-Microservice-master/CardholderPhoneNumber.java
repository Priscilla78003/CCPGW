/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class CardholderPhoneNumber {
    private String cc;
    private String subscriber;

    public CardholderPhoneNumber(){
        
    }
    
    public CardholderPhoneNumber(String cc, String subscriber){
        this();
        this.cc = cc;
        this.subscriber = subscriber;
    }
    /**
     * @return the cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @return the subscriber
     */
    public String getSubscriber() {
        return subscriber;
    }

    /**
     * @param subscriber the subscriber to set
     */
    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }
    
}
