/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.model.adapter.plugins.icbs;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AliasName {
  
    private String lastName;
    private String firstName;
    private String middleName;
    private String titlePrefix;
    private String nameSuffix;  

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the titlePrefix
     */
    public String getTitlePrefix() {
        return titlePrefix;
    }

    /**
     * @param titlePrefix the titlePrefix to set
     */
    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    /**
     * @return the nameSuffix
     */
    public String getNameSuffix() {
        return nameSuffix;
    }

    /**
     * @param nameSuffix the nameSuffix to set
     */
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }


    
}
