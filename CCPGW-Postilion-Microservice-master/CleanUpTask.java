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
package com.truteq.ccpgw.communication.server.timer;

import com.truteq.ccpgw.communication.server.ServerContainer;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class CleanUpTask extends TimerTask{

    private final LogWrapper mLogger = new LogWrapper(CleanUpTask.class);
    private final String name;
    private final ServerContainer server;
    
    public CleanUpTask(ServerContainer server,String name){
        super();
        this.server = server;
        this.name = name;
    }

    @Override
    public void run() {
        this.server.performCleanUp();
        mLogger.info("[" + new Date() + "] " + name + ": task executed!");
    }
            
}
