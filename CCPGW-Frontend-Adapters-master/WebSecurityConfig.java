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
package com.truteq.ccpgw.frontend.adapter.sabre.api.config;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.frontend.adapter.sabre.api.endpoints.AuthRQEndpoint;
import com.truteq.general.util.AESEncryptionDecryption;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private String userName;
    private String password;
    private String role;
    private String secret;    
    
 
    private final LogWrapper mLogger = new LogWrapper(WebSecurityConfig.class);

    private AESEncryptionDecryption decryptor;
    private String decodedRole;
    private String decodedUserName;
    private String decodedPassword;
    
    protected WebSecurityConfig() {

        loadProperties("config/application.properties");
        
        try {
            decryptor = new AESEncryptionDecryption();
            decodedRole = decryptor.decrypt(role, secret);
            decodedUserName = decryptor.decrypt(userName, secret);
            decodedPassword = decryptor.decrypt(password, secret);
           
        } catch (Exception ex) {
            mLogger.error("Exception in decrypting credentials."+ ex, new Throwable().getStackTrace()[0]);
        }
    }  
    
    public final void loadProperties(String filename) {

        this.mLogger.debug("Working Directory = " + System.getProperty("user.dir"), new Throwable().getStackTrace()[0]);
        try (InputStream input = new FileInputStream(filename)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.mLogger.debug("Username: " + prop.getProperty("soap.ws.user.name"), new Throwable().getStackTrace()[0]);

            this.userName = prop.getProperty("soap.ws.user.name");
            this.password = prop.getProperty("soap.ws.password");
            this.role = prop.getProperty("soap.ws.role");
            this.secret = prop.getProperty("communicator.secret");

        } catch (IOException ex) {
            this.mLogger.error("Error load properties file: " + ex.getMessage(), new Throwable().getStackTrace()[0]);
        }
    }    
    
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            //.antMatchers("/sabre/SupplierPaymentServicesDetailsWsdl.wsdl").permitAll()
            .anyRequest().hasRole(decodedRole)
            .and()
            .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(decodedUserName)
                .password(passwordEncoder().encode(decodedPassword))
                .roles(decodedRole);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }    

}
