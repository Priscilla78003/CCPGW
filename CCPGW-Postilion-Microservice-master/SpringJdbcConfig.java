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
package com.truteq.ccpgw.transaction.manager.microservice.config;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.general.util.AESEncryptionDecryption;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author mho
 */
@Configuration
@ComponentScan(basePackages = {"com.truteq"})
public class SpringJdbcConfig {
    
    private String classname;
    private String url;
    private String userName;
    private String password;
    private String secret;

    private final LogWrapper mLogger = new LogWrapper(SpringJdbcConfig.class);
    private AESEncryptionDecryption decryptor;
    private static String decodedUserName;
    private static String decodedPassword;

    protected SpringJdbcConfig() {
        
        loadProperties("config/application.properties");

        try {
            decryptor = new AESEncryptionDecryption();
            decodedUserName = decryptor.decrypt(userName,secret);
            decodedPassword = decryptor.decrypt(password,secret);

        } catch (Exception ex) {
            mLogger.error("Exception in decrypting credentials." + ex);
        }
    } 
    
    public final void loadProperties(String filename) {

        this.mLogger.debug("Working Directory = " + System.getProperty("user.dir"), new Throwable().getStackTrace()[0]);
        try (InputStream input = new FileInputStream(filename)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.mLogger.debug("Username: " + prop.getProperty("spring.database.username"), new Throwable().getStackTrace()[0]);

            this.classname = prop.getProperty("spring.datasource.driver.class.name");
            this.url = prop.getProperty("spring.datasource.url");
            this.userName = prop.getProperty("spring.database.username");
            this.password = prop.getProperty("spring.database.password");
            this.secret = prop.getProperty("spring.encryption.secret");

        } catch (IOException ex) {
            this.mLogger.error("Error load properties file: " + ex.getMessage(), new Throwable().getStackTrace()[0]);
        }
    }   
    
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName(classname);
        dataSource.setUrl(url);

        mLogger.debug(decodedUserName+" "+decodedPassword);

        dataSource.setUsername(decodedUserName);
        dataSource.setPassword(decodedPassword);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
