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
package com.truteq.ccpgw.comms.server.model;

import java.io.Serializable;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Initiate implements Serializable {
    
    private final String name;
    private final String description;
    
    public Initiate(String name,String description){
        this.name = name;
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }    

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
