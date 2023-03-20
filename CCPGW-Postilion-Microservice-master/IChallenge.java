/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.transaction.notification.microservice.threeds.controller;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public interface IChallenge {
    public String getThreeDSServerTransID();
    public void setThreeDSServerTransID(String threeDSServerTransID); 
    public String toJSON();
}
