/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class AuthenticationRequest {
    private String threeDSCompInd;
    private String threeDSRequestorID;
    private String threeDSRequestorName;
    private String threeDSRequestorURL;
    private String acquirerBIN;
    private String acquirerMerchantID;
    private String addrMatch;
    private String cardExpiryDate;
    private String acctNumber;
    private String billAddrCity;
    private String billAddrCountry;
    private String billAddrLine1;
    private String billAddrLine2;
    private String billAddrLine3;
    private String billAddrPostCode;
    private String billAddrState;
    private String email;
    private CardholderPhoneNumber homePhone;
    private CardholderPhoneNumber mobilePhone;

    /**
     * @return the threeDSCompInd
     */
    public String getThreeDSCompInd() {
        return threeDSCompInd;
    }

    /**
     * @param threeDSCompInd the threeDSCompInd to set
     */
    public void setThreeDSCompInd(String threeDSCompInd) {
        this.threeDSCompInd = threeDSCompInd;
    }

    /**
     * @return the threeDSRequestorID
     */
    public String getThreeDSRequestorID() {
        return threeDSRequestorID;
    }

    /**
     * @param threeDSRequestorID the threeDSRequestorID to set
     */
    public void setThreeDSRequestorID(String threeDSRequestorID) {
        this.threeDSRequestorID = threeDSRequestorID;
    }

    /**
     * @return the threeDSRequestorName
     */
    public String getThreeDSRequestorName() {
        return threeDSRequestorName;
    }

    /**
     * @param threeDSRequestorName the threeDSRequestorName to set
     */
    public void setThreeDSRequestorName(String threeDSRequestorName) {
        this.threeDSRequestorName = threeDSRequestorName;
    }

    /**
     * @return the threeDSRequestorURL
     */
    public String getThreeDSRequestorURL() {
        return threeDSRequestorURL;
    }

    /**
     * @param threeDSRequestorURL the threeDSRequestorURL to set
     */
    public void setThreeDSRequestorURL(String threeDSRequestorURL) {
        this.threeDSRequestorURL = threeDSRequestorURL;
    }

    /**
     * @return the acquirerBIN
     */
    public String getAcquirerBIN() {
        return acquirerBIN;
    }

    /**
     * @param acquirerBIN the acquirerBIN to set
     */
    public void setAcquirerBIN(String acquirerBIN) {
        this.acquirerBIN = acquirerBIN;
    }

    /**
     * @return the acquirerMerchantID
     */
    public String getAcquirerMerchantID() {
        return acquirerMerchantID;
    }

    /**
     * @param acquirerMerchantID the acquirerMerchantID to set
     */
    public void setAcquirerMerchantID(String acquirerMerchantID) {
        this.acquirerMerchantID = acquirerMerchantID;
    }

    /**
     * @return the addrMatch
     */
    public String getAddrMatch() {
        return addrMatch;
    }

    /**
     * @param addrMatch the addrMatch to set
     */
    public void setAddrMatch(String addrMatch) {
        this.addrMatch = addrMatch;
    }

    /**
     * @return the cardExpiryDate
     */
    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    /**
     * @param cardExpiryDate the cardExpiryDate to set
     */
    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    /**
     * @return the acctNumber
     */
    public String getAcctNumber() {
        return acctNumber;
    }

    /**
     * @param acctNumber the acctNumber to set
     */
    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    /**
     * @return the billAddrCity
     */
    public String getBillAddrCity() {
        return billAddrCity;
    }

    /**
     * @param billAddrCity the billAddrCity to set
     */
    public void setBillAddrCity(String billAddrCity) {
        this.billAddrCity = billAddrCity;
    }

    /**
     * @return the billAddrCountry
     */
    public String getBillAddrCountry() {
        return billAddrCountry;
    }

    /**
     * @param billAddrCountry the billAddrCountry to set
     */
    public void setBillAddrCountry(String billAddrCountry) {
        this.billAddrCountry = billAddrCountry;
    }

    /**
     * @return the billAddrLine1
     */
    public String getBillAddrLine1() {
        return billAddrLine1;
    }

    /**
     * @param billAddrLine1 the billAddrLine1 to set
     */
    public void setBillAddrLine1(String billAddrLine1) {
        this.billAddrLine1 = billAddrLine1;
    }

    /**
     * @return the billAddrLine2
     */
    public String getBillAddrLine2() {
        return billAddrLine2;
    }

    /**
     * @param billAddrLine2 the billAddrLine2 to set
     */
    public void setBillAddrLine2(String billAddrLine2) {
        this.billAddrLine2 = billAddrLine2;
    }

    /**
     * @return the billAddrLine3
     */
    public String getBillAddrLine3() {
        return billAddrLine3;
    }

    /**
     * @param billAddrLine3 the billAddrLine3 to set
     */
    public void setBillAddrLine3(String billAddrLine3) {
        this.billAddrLine3 = billAddrLine3;
    }

    /**
     * @return the billAddrPostCode
     */
    public String getBillAddrPostCode() {
        return billAddrPostCode;
    }

    /**
     * @param billAddrPostCode the billAddrPostCode to set
     */
    public void setBillAddrPostCode(String billAddrPostCode) {
        this.billAddrPostCode = billAddrPostCode;
    }

    /**
     * @return the billAddrState
     */
    public String getBillAddrState() {
        return billAddrState;
    }

    /**
     * @param billAddrState the billAddrState to set
     */
    public void setBillAddrState(String billAddrState) {
        this.billAddrState = billAddrState;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the homePhone
     */
    public CardholderPhoneNumber getHomePhone() {
        return homePhone;
    }

    /**
     * @param homePhone the homePhone to set
     */
    public void setHomePhone(CardholderPhoneNumber homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * @return the mobilePhone
     */
    public CardholderPhoneNumber getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(CardholderPhoneNumber mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
