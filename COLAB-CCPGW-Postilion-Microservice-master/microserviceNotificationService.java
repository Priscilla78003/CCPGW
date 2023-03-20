/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Notification Manager Microservice : Notification Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  26-May-2022 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.notification.manager;

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
public class microserviceNotificationService {
	public static void main(String[] args) {
	  SpringApplication.run(microserviceNotificationService.class, args);           
	}      
}
