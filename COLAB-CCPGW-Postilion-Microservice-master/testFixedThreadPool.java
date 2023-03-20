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
package com.truteq.ccpgw.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testFixedThreadPool {
  
    public static class Task implements Runnable 
    {
        private String name;

        public Task(String name) 
        {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() 
        {
            try
            {
                Long duration = (long) (Math.random() * 10);
                System.out.println("Doing a task during : " + name);
                TimeUnit.SECONDS.sleep(duration);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) 
    {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) 
        {
            Task task = new Task("Task " + i);
            Task task2 = new Task("Task2 " + i);
            System.out.println("A new task has been added : " + task.getName());
            executor.execute(task);
            System.out.println("A new task2 has been added : " + task2.getName());
            executor.execute(task2);
        }
        System.out.println("Maximum threads inside pool " + executor.getMaximumPoolSize());
        executor.shutdown();
    }  
    
}
