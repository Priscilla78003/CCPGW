/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Postilion Adapter: POSTILION - Transaction Manager Adapter 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */

package com.truteq.ccpgw.adapter.postilion.utils;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Sequence {
    
    private final String configFileName;
    private Integer currentCount = 0;
    
    
    private final LogWrapper mLogger = new LogWrapper(Sequence.class);
    
    public Sequence(String configFileName){
        this.configFileName = configFileName;
        start();
    }
    
    public void start(){
        LoadProperties();
    }
    
    public Integer next(){
        currentCount++;
        if (currentCount > 999999){
           currentCount = 0;  
        }
        SaveProperties();
        return currentCount;
    }
    
    public void stop(){
       SaveProperties(); 
    }
    
    
    
    private void LoadProperties(){
       this.mLogger.debug("Working Directory = " + System.getProperty("user.dir")); 
       try (InputStream input = new FileInputStream(this.configFileName)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.mLogger.debug("Sequence count: "+prop.getProperty("sequence.count"));
            
            this.currentCount =  Integer.parseInt(prop.getProperty("sequence.count"));
        } catch (IOException ex) {
            this.mLogger.error("Error load properties file: " + ex.getMessage());
        }        
    }
    
    private void SaveProperties(){
        try (OutputStream output = new FileOutputStream(this.configFileName)) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("sequence.count", getCurrentCount().toString());

            prop.store(output, null);

        } catch (IOException io) {
            this.mLogger.error("Error saving properties file: " + io.getMessage());
        }        
    }

    /**
     * @return the currentCount
     */
    public Integer getCurrentCount() {
        return currentCount;
    }
    
}
