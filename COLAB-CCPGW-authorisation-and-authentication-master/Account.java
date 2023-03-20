/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.model.adapter.plugins.icbs;

import java.util.Date;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Account {
    
 private String acctId;
 private String acctType;
 private Date expiryDate;
 private String year;
 private String month;
 private String day;
 private String cardolderFlag;

    /**
     * @return the acctId
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * @param acctId the acctId to set
     */
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    /**
     * @return the acctType
     */
    public String getAcctType() {
        return acctType;
    }

    /**
     * @param acctType the acctType to set
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    /**
     * @return the expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the cardolderFlag
     */
    public String getCardolderFlag() {
        return cardolderFlag;
    }

    /**
     * @param cardolderFlag the cardolderFlag to set
     */
    public void setCardolderFlag(String cardolderFlag) {
        this.cardolderFlag = cardolderFlag;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }
  
}
