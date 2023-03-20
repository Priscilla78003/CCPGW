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

//https://howtodoinjava.com/java/multi-threading/java-thread-pool-executor-example/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testThreadPool {

    public class Task implements Runnable {
        private final String name;

        public Task(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void run() {
            try {
                Long duration = (long) (Math.random() * 10);
                System.out.println("Executing : " + name);
                TimeUnit.SECONDS.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }    
    
    private Map threadPool = new HashMap();

    private void submitTasks(ExecutorService executor)
            throws InterruptedException {
        Set<String> threadNames = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            int finalI = i;

            executor.execute(() -> {
                System.out.println(
                        "Running: " + finalI + " Thread: " + Thread
                                .currentThread().getName());

                threadNames
                        .add(Thread.currentThread().getName());
            });
        }
        Thread.sleep(2000);

        System.out.println();
        System.out.println(
                "# Threads: " + threadNames.size());
        System.out.println("Threads: " + threadNames);
    }

//    private void submitTask(ExecutorService executor) throws InterruptedException {
//
//    }

    public static void main(String[] args) throws InterruptedException {
        testThreadPool test = new testThreadPool();
        
        ExecutorService executor = Executors
          .newCachedThreadPool();
 
        test.submitTasks(executor);
    }

}
