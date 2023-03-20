/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Postilion Adapter: POSTILION - Transaction Manager Adapter 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.adapter.postilion.requests;

import java.time.Instant;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ResponseMessage {
    
    private String stan;
    private boolean waiting = true;
    private String mti;
    private ISOMsg isoMessage;
    private Instant startTime;

    public ResponseMessage(){
        this.startTime = Instant.now();
    }
    
    public ResponseMessage(String stan, String mti){
        this();
        this.waiting = true;
        this.stan = stan;
        this.mti = mti;
        this.isoMessage = null;
    }
    
    public ResponseMessage(String stan, ISOMsg isoMessage){
        this();
        this.waiting = true;
        this.stan = stan;
        this.isoMessage = isoMessage;
    }
    
    public ResponseMessage(String stan, ISOMsg isoMessage, boolean waiting){
        this();
        this.waiting = waiting;
        this.stan = stan;
        this.isoMessage = isoMessage;
    }    
    
    public ResponseMessage clone(ResponseMessage responseMessage){
        return new ResponseMessage(responseMessage.getStan(),
                                   responseMessage.getIsoMessage(),
                                   responseMessage.isWaiting());
    }
    
    /**
     * @return the stan
     */
    public String getStan() {
        return stan;
    }

    /**
     * @param stan the stan to set
     */
    public void setStan(String stan) {
        this.stan = stan;
    }

    /**
     * @return the waiting
     */
    public boolean isWaiting() {
        return waiting;
    }

    /**
     * @param waiting the waiting to set
     */
    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    /**
     * @return the isoMessage
     */
    public ISOMsg getIsoMessage() {
        return isoMessage;
    }

    /**
     * @param isoMessage the isoMessage to set
     */
    public void setIsoMessage(ISOMsg isoMessage) {
        this.isoMessage = isoMessage;
    }

    /**
     * @return the startTime
     */
    public Instant getStartTime() {
        return startTime;
    }

    /**
     * @return the mti
     */
    public String getMti() {
        return mti;
    }

    /**
     * @param mti the mti to set
     */
    public void setMti(String mti) {
        this.mti = mti;
    }
    
}
