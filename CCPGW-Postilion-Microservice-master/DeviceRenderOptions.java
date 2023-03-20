/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

import java.util.List;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class DeviceRenderOptions {
    private String sdkInterface;
    private List<String> sdkUiType;
    
    public DeviceRenderOptions(){
        
    }
    
    public DeviceRenderOptions(String sdkInterface,List<String> sdkUiType){
        this();
        this.sdkInterface = sdkInterface;
        this.sdkUiType = sdkUiType;
    }    
    /**
     * @return the sdkInterface
     */
    public String getSdkInterface() {
        return sdkInterface;
    }

    /**
     * @param sdkInterface the sdkInterface to set
     */
    public void setSdkInterface(String sdkInterface) {
        this.sdkInterface = sdkInterface;
    }

    /**
     * @return the sdkUiType
     */
    public List<String> getSdkUiType() {
        return sdkUiType;
    }

    /**
     * @param sdkUiType the sdkUiType to set
     */
    public void setSdkUiType(List<String> sdkUiType) {
        this.sdkUiType = sdkUiType;
    }
}
