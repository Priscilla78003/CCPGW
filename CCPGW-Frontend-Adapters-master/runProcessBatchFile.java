/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.frontend.adapter.sabre.api.batchprocessing;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class runProcessBatchFile {
        
        @Value("${file.name}")
        private static String fileName;
    
 	public static void main(String[] args) {
             ProcessBatchFile processBatch = new ProcessBatchFile();
             processBatch.readFile(fileName);
             processBatch.processData();
             processBatch.displayTransactions();
	}   
}
