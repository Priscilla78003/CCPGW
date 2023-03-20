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
package com.truteq.ccpgw.communication.server.logging.wrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class LogWrapper {
    
    private final Logger logger;
    private boolean debug = false;
    private boolean warn  = false;
    private boolean error = false;
    private boolean info  = false;
    
    public LogWrapper(Class clazz){
        this.logger = LogManager.getLogger(clazz);
        loadProperties("config/log-wrapper.properties");
    }
    
    public final void loadProperties(String filename) {

//        this.logger.debug("Working Directory = " + System.getProperty("user.dir"));
        try (InputStream input = new FileInputStream(filename)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
//            this.logger.debug("Debug : " + prop.getProperty("debug"));
//            this.logger.debug("Error : " + prop.getProperty("error"));
//            this.logger.debug("Info  : " + prop.getProperty("info"));
//            this.logger.debug("Warn  : " + prop.getProperty("warn"));
            this.debug = Boolean.parseBoolean(prop.getProperty("debug"));
            this.error = Boolean.parseBoolean(prop.getProperty("error"));
            this.info = Boolean.parseBoolean(prop.getProperty("info"));
            this.warn = Boolean.parseBoolean(prop.getProperty("warn"));
            input.close();

        } catch (IOException ex) {
            this.logger.error("Error load properties file: " + ex.getMessage());
        }
    }    
    
    
    public void debug(String message){
        if (this.debug) {
            this.logger.debug(message);
        }       
    }
    
    public void debug(String message, StackTraceElement element){
        if (this.debug) {
            this.logger.debug(element.getMethodName()+":"+element.getLineNumber()+": "+message);
        }       
    }    
    
    
    public void info(String message){
        if (this.info) {
            this.logger.info(message);
        }        
    }

    public void info(String message, StackTraceElement element){
        if (this.info) {
            this.logger.info(element.getMethodName()+":"+element.getLineNumber()+": "+message);
        }        
    }       
    
    public void warn(String message){
        if (this.warn) {
            this.logger.warn(message);
        }       
    }  
    
    public void warn(String message, StackTraceElement element){
        if (this.warn) {
            this.logger.warn(element.getMethodName()+":"+element.getLineNumber()+": "+message);
        }      
    }       
    
    public void error(String message){
         if (this.debug) {
            this.logger.error(message);
        }       
    }
    
    public void error(String message, StackTraceElement element){
        if (this.error) {
            this.logger.error(element.getMethodName()+":"+element.getLineNumber()+": "+message);
        }      
    }       
    
    public void error(String message, Throwable ex){
         if (this.error) {
            this.logger.error(message, ex);
        }        
    }
    
    public void error(String message, Throwable ex, StackTraceElement element){
        if (this.error) {
            this.logger.error(element.getMethodName()+":"+element.getLineNumber()+": "+message, ex);
        }       
    }       
   
    
    /**
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }
    
    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
