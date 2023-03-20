/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.microservice.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@ComponentScan(basePackages = {"com.truteq"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class microserviceTransactionManager {
	public static void main(String[] args) {
	  SpringApplication.run(microserviceTransactionManager.class, args);           
	}    
}
