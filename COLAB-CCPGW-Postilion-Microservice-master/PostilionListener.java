/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Communication server
 * ***************************************************************
 * Used to communicate with different adapters 
 * Support: Grant O'Reilly gbo@truteq.com OR grant@platformpac.com.pg
 * V01.00.00  29-Jum-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.communication.server.listeners;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public interface PostilionListener {
    
    public void onConnect();
    public void onDisconnect();
    public void onSignOn();
    public void onSignOff();
    public void onPing();
    public void onAuthorise();
    public void onCapture();
    public void onReversal();
    public void onDebit();
    public void onCredit();
    public void onFinancial();
    public void onRefund();
    public void onCleanUp();
    public void onCheckConnection();
    public void onClientDisconnect();
    public void onClientConnect();
    public void onClientListernerRefresh();
}
