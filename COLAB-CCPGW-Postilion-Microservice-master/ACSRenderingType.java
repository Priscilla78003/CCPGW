/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.results.response;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ACSRenderingType {
    private String acsInterface;
    private String acsUiTemplate; 

    public ACSRenderingType (String acsInterface,
                             String acsUiTemplate){
      this();  
      this.acsInterface = acsInterface;
      this.acsUiTemplate = acsUiTemplate;  
    }
    
    public ACSRenderingType (){

    }
    
    /**
     * @return the acsInterface
     */
    public String getAcsInterface() {
        return acsInterface;
    }

    /**
     * @param acsInterface the acsInterface to set
     */
    public void setAcsInterface(String acsInterface) {
        this.acsInterface = acsInterface;
    }

    /**
     * @return the acsUiTemplate
     */
    public String getAcsUiTemplate() {
        return acsUiTemplate;
    }

    /**
     * @param acsUiTemplate the acsUiTemplate to set
     */
    public void setAcsUiTemplate(String acsUiTemplate) {
        this.acsUiTemplate = acsUiTemplate;
    }
}
