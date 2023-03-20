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
public class AirDetail {
    private String conjunction;
         
    private String coupons;

    private String routingCity;

    private String airlineCodes;

    private String fareBasis;

    private String classofService;

    private String stopoverCodes;

    private String departureDate;

    private String flightNumber;

    private String flightTimes;

    private String filler3;
   
    
    /**
     * @return the conjunction
     */
    public String getConjunction() {
        return conjunction;
    }

    /**
     * @param conjunction the conjunction to set
     */
    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    /**
     * @return the coupons
     */
    public String getCoupons() {
        return coupons;
    }

    /**
     * @param coupons the coupons to set
     */
    public void setCoupons(String coupons) {
        this.coupons = coupons;
    }

    /**
     * @return the routingCity
     */
    public String getRoutingCity() {
        return routingCity;
    }

    /**
     * @param routingCity the routingCity to set
     */
    public void setRoutingCity(String routingCity) {
        this.routingCity = routingCity;
    }

    /**
     * @return the airlineCodes
     */
    public String getAirlineCodes() {
        return airlineCodes;
    }

    /**
     * @param airlineCodes the airlineCodes to set
     */
    public void setAirlineCodes(String airlineCodes) {
        this.airlineCodes = airlineCodes;
    }

    /**
     * @return the fareBasis
     */
    public String getFareBasis() {
        return fareBasis;
    }

    /**
     * @param fareBasis the fareBasis to set
     */
    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    /**
     * @return the classofService
     */
    public String getClassofService() {
        return classofService;
    }

    /**
     * @param classofService the classofService to set
     */
    public void setClassofService(String classofService) {
        this.classofService = classofService;
    }

    /**
     * @return the stopoverCodes
     */
    public String getStopoverCodes() {
        return stopoverCodes;
    }

    /**
     * @param stopoverCodes the stopoverCodes to set
     */
    public void setStopoverCodes(String stopoverCodes) {
        this.stopoverCodes = stopoverCodes;
    }

    /**
     * @return the departureDate
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @return the flightTimes
     */
    public String getFlightTimes() {
        return flightTimes;
    }

    /**
     * @param flightTimes the flightTimes to set
     */
    public void setFlightTimes(String flightTimes) {
        this.flightTimes = flightTimes;
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
