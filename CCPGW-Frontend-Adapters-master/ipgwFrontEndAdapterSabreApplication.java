/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  23-Mar-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.frontend.adapter.sabre.api.main;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.truteq"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
                                  UserDetailsServiceAutoConfiguration.class})
public class ipgwFrontEndAdapterSabreApplication
{
	public static void main(String[] args) {
	    SpringApplication.run(ipgwFrontEndAdapterSabreApplication.class, args);
	}

}
