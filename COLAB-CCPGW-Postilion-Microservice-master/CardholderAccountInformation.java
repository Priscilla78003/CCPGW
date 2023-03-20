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
public class CardholderAccountInformation {
   /* This field contains the properties:
   *    chAccAgeInd -> Length of time that the cardholder has had the account with the 3DS Requestor.
   *                   Accepted values are:
   *                   01 -> No account
   *                   02 -> Created during this transaction
   *                   03 -> Less than 30 days
   *                   04 -> Between 30 and 60 days
   *                   05 -> More than 60 days
   *    chAccDate -> Date that the cardholder opened the account with the 3DS Requestor. Date format = YYYYMMDD.
   *    chAccChangeInd -> Length of time since the cardholder’s account information with the 3DS Requestor was
   *                      last changed. Includes Billing or Shipping address, new payment account, or new user(s) added.
   *                      Accepted values are:
   *                        01 -> Changed during this transaction
   *                        02 -> Less than 30 days
   *                        03 -> 30 - 60 days
   *                        04 -> More than 60 days
   *    chAccChange -> Date that the cardholder’s account with the 3DS Requestor was last changed. Including Billing
   *                   or Shipping address, new payment account, or new user(s) added. Date format = YYYYMMDD.
   *    chAccPwChangeInd -> Length of time since the cardholder’s account with the 3DS Requestor had a password change
   *                        or account reset. The accepted values are:
   *                          01 -> No change
   *                          02 -> Changed during this transaction
   *                          03 -> Less than 30 days
   *                          04 -> 30 - 60 days
   *                          05 -> More than 60 days
   *    chAccPwChange -> Date that cardholder’s account with the 3DS Requestor had a password change or account reset.
   *                     Date format must be YYYYMMDD.
   *    shipAddressUsageInd -> Indicates when the shipping address used for this transaction was first used with the
   *                           3DS Requestor. Accepted values are:
   *                              01 -> This transaction
   *                              02 -> Less than 30 days
   *                              03 -> 30 - 60 days
   *                              04 -> More than 60 days.
   *    shipAddressUsage -> Date when the shipping address used for this transaction was first used with the
   *                        3DS Requestor. Date format must be YYYYMMDD.
   *    txnActivityDay -> Number of transactions (successful and abandoned) for this cardholder account with the
   *                      3DS Requestor across all payment accounts in the previous 24 hours.
   *    txnActivityYear -> Number of transactions (successful and abandoned) for this cardholder account with the
   *                       3DS Requestor across all payment accounts in the previous year.
   *    provisionAttemptsDay -> Number of Add Card attempts in the last 24 hours.
   *    nbPurchaseAccount -> Number of purchases with this cardholder account during the previous six months.
   *    suspiciousAccActivity -> Indicates whether the 3DS Requestor has experienced suspicious activity
   *                              (including previous fraud) on the cardholder account. Accepted values are:
   *                                  01 -> No suspicious activity has been observed
   *                                  02 -> Suspicious activity has been observed
   *    shipNameIndicator -> Indicates if the Cardholder Name on the account is identical to the shipping Name used
   *                         for this transaction. Accepted values are:
   *                            01 -> Account Name identical to shipping Name
   *                            02 -> Account Name different than shipping Name
   *    paymentAccInd -> Indicates the length of time that the payment account was enrolled in the cardholder’s
   *                     account with the 3DS Requester. Accepted values are:
   *                        01 -> No account (guest check-out)
   *                        02 -> During this transaction
   *                        03 -> Less than 30 days
   *                        04 -> 30 - 60 days
   *                        05 -> More than 60 days
   *    paymentAccAge -> Date that the payment account was enrolled in the cardholder’s account with
   *                     the 3DS Requestor. Date format must be YYYYMMDD.
   */
    private String chAccAgeInd;
    private String chAccDate;
    private String chAccChangeInd;
    private String chAccChange;
    private String chAccPwChangeInd;
    private String chAccPwChange;
    private String shipAddressUsageInd;
    private String shipAddressUsage;
    private String txnActivityDay;
    private String txnActivityYear;
    private String provisionAttemptsDay;
    private String nbPurchaseAccount;
    private String suspiciousAccActivity;
    private String shipNameIndicator;
    private String paymentAccInd;
    private String paymentAccAge;  

    /**
     * @return the chAccAgeInd
     */
    public String getChAccAgeInd() {
        return chAccAgeInd;
    }

    /**
     * @param chAccAgeInd the chAccAgeInd to set
     */
    public void setChAccAgeInd(String chAccAgeInd) {
        this.chAccAgeInd = chAccAgeInd;
    }

    /**
     * @return the chAccDate
     */
    public String getChAccDate() {
        return chAccDate;
    }

    /**
     * @param chAccDate the chAccDate to set
     */
    public void setChAccDate(String chAccDate) {
        this.chAccDate = chAccDate;
    }

    /**
     * @return the chAccChangeInd
     */
    public String getChAccChangeInd() {
        return chAccChangeInd;
    }

    /**
     * @param chAccChangeInd the chAccChangeInd to set
     */
    public void setChAccChangeInd(String chAccChangeInd) {
        this.chAccChangeInd = chAccChangeInd;
    }

    /**
     * @return the chAccChange
     */
    public String getChAccChange() {
        return chAccChange;
    }

    /**
     * @param chAccChange the chAccChange to set
     */
    public void setChAccChange(String chAccChange) {
        this.chAccChange = chAccChange;
    }

    /**
     * @return the chAccPwChangeInd
     */
    public String getChAccPwChangeInd() {
        return chAccPwChangeInd;
    }

    /**
     * @param chAccPwChangeInd the chAccPwChangeInd to set
     */
    public void setChAccPwChangeInd(String chAccPwChangeInd) {
        this.chAccPwChangeInd = chAccPwChangeInd;
    }

    /**
     * @return the chAccPwChange
     */
    public String getChAccPwChange() {
        return chAccPwChange;
    }

    /**
     * @param chAccPwChange the chAccPwChange to set
     */
    public void setChAccPwChange(String chAccPwChange) {
        this.chAccPwChange = chAccPwChange;
    }

    /**
     * @return the shipAddressUsageInd
     */
    public String getShipAddressUsageInd() {
        return shipAddressUsageInd;
    }

    /**
     * @param shipAddressUsageInd the shipAddressUsageInd to set
     */
    public void setShipAddressUsageInd(String shipAddressUsageInd) {
        this.shipAddressUsageInd = shipAddressUsageInd;
    }

    /**
     * @return the shipAddressUsage
     */
    public String getShipAddressUsage() {
        return shipAddressUsage;
    }

    /**
     * @param shipAddressUsage the shipAddressUsage to set
     */
    public void setShipAddressUsage(String shipAddressUsage) {
        this.shipAddressUsage = shipAddressUsage;
    }

    /**
     * @return the txnActivityDay
     */
    public String getTxnActivityDay() {
        return txnActivityDay;
    }

    /**
     * @param txnActivityDay the txnActivityDay to set
     */
    public void setTxnActivityDay(String txnActivityDay) {
        this.txnActivityDay = txnActivityDay;
    }

    /**
     * @return the txnActivityYear
     */
    public String getTxnActivityYear() {
        return txnActivityYear;
    }

    /**
     * @param txnActivityYear the txnActivityYear to set
     */
    public void setTxnActivityYear(String txnActivityYear) {
        this.txnActivityYear = txnActivityYear;
    }

    /**
     * @return the provisionAttemptsDay
     */
    public String getProvisionAttemptsDay() {
        return provisionAttemptsDay;
    }

    /**
     * @param provisionAttemptsDay the provisionAttemptsDay to set
     */
    public void setProvisionAttemptsDay(String provisionAttemptsDay) {
        this.provisionAttemptsDay = provisionAttemptsDay;
    }

    /**
     * @return the nbPurchaseAccount
     */
    public String getNbPurchaseAccount() {
        return nbPurchaseAccount;
    }

    /**
     * @param nbPurchaseAccount the nbPurchaseAccount to set
     */
    public void setNbPurchaseAccount(String nbPurchaseAccount) {
        this.nbPurchaseAccount = nbPurchaseAccount;
    }

    /**
     * @return the suspiciousAccActivity
     */
    public String getSuspiciousAccActivity() {
        return suspiciousAccActivity;
    }

    /**
     * @param suspiciousAccActivity the suspiciousAccActivity to set
     */
    public void setSuspiciousAccActivity(String suspiciousAccActivity) {
        this.suspiciousAccActivity = suspiciousAccActivity;
    }

    /**
     * @return the shipNameIndicator
     */
    public String getShipNameIndicator() {
        return shipNameIndicator;
    }

    /**
     * @param shipNameIndicator the shipNameIndicator to set
     */
    public void setShipNameIndicator(String shipNameIndicator) {
        this.shipNameIndicator = shipNameIndicator;
    }

    /**
     * @return the paymentAccInd
     */
    public String getPaymentAccInd() {
        return paymentAccInd;
    }

    /**
     * @param paymentAccInd the paymentAccInd to set
     */
    public void setPaymentAccInd(String paymentAccInd) {
        this.paymentAccInd = paymentAccInd;
    }

    /**
     * @return the paymentAccAge
     */
    public String getPaymentAccAge() {
        return paymentAccAge;
    }

    /**
     * @param paymentAccAge the paymentAccAge to set
     */
    public void setPaymentAccAge(String paymentAccAge) {
        this.paymentAccAge = paymentAccAge;
    }
}
