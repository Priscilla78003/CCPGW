/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Microservices suite: ACS Servlet Manager 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  26-Oct-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.acs.servlet.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@ComponentScan(basePackages = {"com.truteq"})
@ServletComponentScan("com.truteq.ccpgw.acs.servlet.servlets")
@PropertySource("classpath:datamanager.properties")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class microserviceAcsServletManager {
	public static void main(String[] args) {
            
	  SpringApplication.run(microserviceAcsServletManager.class, args);
      
	}    

}
