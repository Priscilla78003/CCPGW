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
public class CarDetail {
    
    private String supplierName;

    private String supplierCode;

    private String serviceStartDate;

    private String serviceEndDate;

    private String vehicleClass;

    private String distance;

    private String mileage;

    private Integer baseAmount;

    private Integer totalAmount;

    private Integer fee1Amount;

    private Integer fee2Amount;

    private String orderNumber;
        
    private String rentalAgreement;

    private String pickupLocation;

    private String pickupCity;

    private String pickupRegion;

    private String pickupCountry;

    private String pickupDate;

    private String dropOffCity;

    private String dropOffRegion;

    private String dropOffState;

    private String dropOffCountry;

    private String dropOffDate;

    private String rentersFirstName;

    private String rentersLastName;

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
     * @return the vehicleClass
     */
    public String getVehicleClass() {
        return vehicleClass;
    }

    /**
     * @param vehicleClass the vehicleClass to set
     */
    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    /**
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * @return the mileage
     */
    public String getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the baseAmount
     */
    public Integer getBaseAmount() {
        return baseAmount;
    }

    /**
     * @param baseAmount the baseAmount to set
     */
    public void setBaseAmount(Integer baseAmount) {
        this.baseAmount = baseAmount;
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
     * @return the rentalAgreement
     */
    public String getRentalAgreement() {
        return rentalAgreement;
    }

    /**
     * @param rentalAgreement the rentalAgreement to set
     */
    public void setRentalAgreement(String rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    /**
     * @return the pickupLocation
     */
    public String getPickupLocation() {
        return pickupLocation;
    }

    /**
     * @param pickupLocation the pickupLocation to set
     */
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    /**
     * @return the pickupCity
     */
    public String getPickupCity() {
        return pickupCity;
    }

    /**
     * @param pickupCity the pickupCity to set
     */
    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    /**
     * @return the pickupRegion
     */
    public String getPickupRegion() {
        return pickupRegion;
    }

    /**
     * @param pickupRegion the pickupRegion to set
     */
    public void setPickupRegion(String pickupRegion) {
        this.pickupRegion = pickupRegion;
    }

    /**
     * @return the pickupCountry
     */
    public String getPickupCountry() {
        return pickupCountry;
    }

    /**
     * @param pickupCountry the pickupCountry to set
     */
    public void setPickupCountry(String pickupCountry) {
        this.pickupCountry = pickupCountry;
    }

    /**
     * @return the pickupDate
     */
    public String getPickupDate() {
        return pickupDate;
    }

    /**
     * @param pickupDate the pickupDate to set
     */
    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    /**
     * @return the dropOffCity
     */
    public String getDropOffCity() {
        return dropOffCity;
    }

    /**
     * @param dropOffCity the dropOffCity to set
     */
    public void setDropOffCity(String dropOffCity) {
        this.dropOffCity = dropOffCity;
    }

    /**
     * @return the dropOffRegion
     */
    public String getDropOffRegion() {
        return dropOffRegion;
    }

    /**
     * @param dropOffRegion the dropOffRegion to set
     */
    public void setDropOffRegion(String dropOffRegion) {
        this.dropOffRegion = dropOffRegion;
    }

    /**
     * @return the dropOffState
     */
    public String getDropOffState() {
        return dropOffState;
    }

    /**
     * @param dropOffState the dropOffState to set
     */
    public void setDropOffState(String dropOffState) {
        this.dropOffState = dropOffState;
    }

    /**
     * @return the dropOffCountry
     */
    public String getDropOffCountry() {
        return dropOffCountry;
    }

    /**
     * @param dropOffCountry the dropOffCountry to set
     */
    public void setDropOffCountry(String dropOffCountry) {
        this.dropOffCountry = dropOffCountry;
    }

    /**
     * @return the dropOffDate
     */
    public String getDropOffDate() {
        return dropOffDate;
    }

    /**
     * @param dropOffDate the dropOffDate to set
     */
    public void setDropOffDate(String dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    /**
     * @return the rentersFirstName
     */
    public String getRentersFirstName() {
        return rentersFirstName;
    }

    /**
     * @param rentersFirstName the rentersFirstName to set
     */
    public void setRentersFirstName(String rentersFirstName) {
        this.rentersFirstName = rentersFirstName;
    }

    /**
     * @return the rentersLastName
     */
    public String getRentersLastName() {
        return rentersLastName;
    }

    /**
     * @param rentersLastName the rentersLastName to set
     */
    public void setRentersLastName(String rentersLastName) {
        this.rentersLastName = rentersLastName;
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
