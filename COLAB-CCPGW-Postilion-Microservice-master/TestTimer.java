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
package com.truteq.ccpgw.test.util;

import com.truteq.ccpgw.communication.server.timer.ScheduledTask;
import java.util.Timer;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestTimer {
    
    
    public static void main(String[] args){
      ScheduledTask t1 = new ScheduledTask(null,"Task 1");
      ScheduledTask t2 = new ScheduledTask(null,"Task 2");
      
      Timer t = new Timer();
      
      t.schedule(t1, 0,10000); //  executes for every 10 seconds
      t.schedule(t2, 0, 20000); // executes for every 20 seconds        
    }
}
