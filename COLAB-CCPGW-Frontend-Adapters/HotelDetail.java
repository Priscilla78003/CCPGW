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
package com.truteq.ccpgw.frontend.adapter.sabre.api.batchprocessing;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class HotelDetail {
    private String supplierName;

    private String supplierCode;

    private String serviceStartDate;

    private String serviceEndDate;

    private Integer numberofNights;

    private Integer roomRate;

    private Integer baseRate;

    private Integer totalAmount;

    private Integer fee1Amount;

    private Integer fee2Amount;

    private String firstName;

    private String lastName;

    private String lodgeFolioNumber;

    private String orderNumber;

    private String contactPhone;

    private String filler3; 

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the supplierCode
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * @param supplierCode the supplierCode to set
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * @return the serviceStartDate
     */
    public String getServiceStartDate() {
        return serviceStartDate;
    }

    /**
     * @param serviceStartDate the serviceStartDate to set
     */
    public void setServiceStartDate(String serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    /**
     * @return the serviceEndDate
     */
    public String getServiceEndDate() {
        return serviceEndDate;
    }

    /**
     * @param serviceEndDate the serviceEndDate to set
     */
    public void setServiceEndDate(String serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    /**
     * @return the numberofNights
     */
    public Integer getNumberofNights() {
        return numberofNights;
    }

    /**
     * @param numberofNights the numberofNights to set
     */
    public void setNumberofNights(Integer numberofNights) {
        this.numberofNights = numberofNights;
    }

    /**
     * @return the roomRate
     */
    public Integer getRoomRate() {
        return roomRate;
    }

    /**
     * @param roomRate the roomRate to set
     */
    public void setRoomRate(Integer roomRate) {
        this.roomRate = roomRate;
    }

    /**
     * @return the baseRate
     */
    public Integer getBaseRate() {
        return baseRate;
    }

    /**
     * @param baseRate the baseRate to set
     */
    public void setBaseRate(Integer baseRate) {
        this.baseRate = baseRate;
    }

    /**
     * @return the totalAmount
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the fee1Amount
     */
    public Integer getFee1Amount() {
        return fee1Amount;
    }

    /**
     * @param fee1Amount the fee1Amount to set
     */
    public void setFee1Amount(Integer fee1Amount) {
        this.fee1Amount = fee1Amount;
    }

    /**
     * @return the fee2Amount
     */
    public Integer getFee2Amount() {
        return fee2Amount;
    }

    /**
     * @param fee2Amount the fee2Amount to set
     */
    public void setFee2Amount(Integer fee2Amount) {
        this.fee2Amount = fee2Amount;
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
     * @return the lodgeFolioNumber
     */
    public String getLodgeFolioNumber() {
        return lodgeFolioNumber;
    }

    /**
     * @param lodgeFolioNumber the lodgeFolioNumber to set
     */
    public void setLodgeFolioNumber(String lodgeFolioNumber) {
        this.lodgeFolioNumber = lodgeFolioNumber;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the contactPhone
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone the contactPhone to set
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * @return the filler3
     */
    public String getFiller3() {
        return filler3;
    }

    /**
     * @param filler3 the filler3 to set
     */
    public void setFiller3(String filler3) {
        this.filler3 = filler3;
    }
}
