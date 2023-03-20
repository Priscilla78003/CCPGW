/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

import java.net.URL;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class MerchantData {
 
  /**
   * ID of the merchant. This value will be used to find merchant information from the configuration. From the merchant
   * configuration the 3DS Server can fill the other values (mcc, merchantCountryCode and merchantName), if provided.
   *
   * This field can be left out if merchant information are provided in the request.
   */
  private String merchantConfigurationId;
  /**
   * Merchant Category Code. This is the DS-specific code describing the Merchant's type of business, product or
   * service. The field is limited to 4 characters. The value correlates to the Merchant Category Code as defined
   * by each Payment System or DS.
   *
   * If not present in the request it will be filled from the merchant configuration referenced by
   * the merchantConfigurationId.
   *
   * This field is required for messageCategory=01 (PA) and optional, but strongly recommended for 02 (NPA).
   */
  private String mcc;
  /**
   * Country code for the merchant. This value correlates to the Merchant Country Code as defined by each Payment
   * System or DS. The field is limited to 3 characters accepting ISO 3166-1 format, except 901-999.
   *
   * If not present in the request it will be filled from the merchant configuration referenced by
   * the merchantConfigurationId.
   *
   * This field is required for messageCategory=01 (PA) and optional, but strongly recommended for 02 (NPA).
   */
  private String merchantCountryCode;
  /**
   * Merchant name assigned by the Acquirer or Payment System. This field is limited to maximum 40 characters, and it
   * is the same name used in the authorisation message as defined in ISO 8583.
   *
   * If not present in the request it will be filled from the merchant configuration referenced by
   * the merchantConfigurationId.
   *
   * This field is required for messageCategory=01 (PA) and optional, but strongly recommended for 02 (NPA).
   */
  private String merchantName;
  /**
   * Fully qualified URL of the merchant that receives the CRes message or Error Message.
   * Incorrect formatting will result in a failure to deliver the notification of the final CRes message. This field is
   * limited to 256 characters.
   *
   * This field should be present if the merchant will receive the final CRes message and the device channel is BROWSER.
   * If not present in the request it will be filled from the notificationURL configured in the XML or
   * database configuration.
   */
  private URL notificationURL;
  /**
   * Directory Server assigned 3DS Requestor identifier. Each DS will provide a unique ID to each 3DS Requestor on an
   * individual basis.
   *
   * This field is optional and if value is not present it will be taken from the resolved
   * Directory Server configuration.
   */
  private String threeDSRequestorId;
  /**
   * Directory Server assigned 3DS Requestor name. Each DS will provide a unique name to each 3DS Requestor on an
   * individual basis.
   *
   * This field is optional and if value is not present it will be taken from the resolved
   * Directory Server configuration.
   */
  private String threeDSRequestorName;
  /**
   * Set whitelisting status of the merchant. Accepted values are:
   *    Y -> 3DS Requestor is whitelisted by cardholder
   *    N -> 3DS Requestor is not whitelisted by cardholder
   *    E -> Not eligible as determined by issuer
   *    P -> Pending confirmation by cardholder
   *    R -> Cardholder rejected
   *    U -> Whitelist status unknown, unavailable, or does not apply.
   *
   * The field is optional and if value is not present, the whitelist remains unchanged.
   * Available for supporting EMV 3DS 2.2.0 and later versions.
   */
  private String whiteListStatus;
 
  /**
   * Fully qualified URL of the merchant that receives the RRes message or Error Message.
   * Incorrect formatting will result in a failure to deliver the notification of the final RRes message. This field
   * is limited to 256 characters.
   *
   * This field is not mandatory and could be present if the Results Response (in case of a challenge transaction)
   * should be sent to a dynamic URL different from the one present in the configuration, only if dynamic provision
   * of the Results Response notification URL is allowed per the license.
   *
   * If not present in the request it will be filled from the notificationURL configured in the XML or database
   * configuration.
   */
  private URL resultsResponseNotificationUrl;

    /**
     * @return the merchantConfigurationId
     */
    public String getMerchantConfigurationId() {
        return merchantConfigurationId;
    }

    /**
     * @param merchantConfigurationId the merchantConfigurationId to set
     */
    public void setMerchantConfigurationId(String merchantConfigurationId) {
        this.merchantConfigurationId = merchantConfigurationId;
    }

    /**
     * @return the mcc
     */
    public String getMcc() {
        return mcc;
    }

    /**
     * @param mcc the mcc to set
     */
    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    /**
     * @return the merchantCountryCode
     */
    public String getMerchantCountryCode() {
        return merchantCountryCode;
    }

    /**
     * @param merchantCountryCode the merchantCountryCode to set
     */
    public void setMerchantCountryCode(String merchantCountryCode) {
        this.merchantCountryCode = merchantCountryCode;
    }

    /**
     * @return the merchantName
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @param merchantName the merchantName to set
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * @return the notificationURL
     */
    public URL getNotificationURL() {
        return notificationURL;
    }

    /**
     * @param notificationURL the notificationURL to set
     */
    public void setNotificationURL(URL notificationURL) {
        this.notificationURL = notificationURL;
    }

    /**
     * @return the threeDSRequestorId
     */
    public String getThreeDSRequestorId() {
        return threeDSRequestorId;
    }

    /**
     * @param threeDSRequestorId the threeDSRequestorId to set
     */
    public void setThreeDSRequestorId(String threeDSRequestorId) {
        this.threeDSRequestorId = threeDSRequestorId;
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
     * @return the whiteListStatus
     */
    public String getWhiteListStatus() {
        return whiteListStatus;
    }

    /**
     * @param whiteListStatus the whiteListStatus to set
     */
    public void setWhiteListStatus(String whiteListStatus) {
        this.whiteListStatus = whiteListStatus;
    }

    /**
     * @return the resultsResponseNotificationUrl
     */
    public URL getResultsResponseNotificationUrl() {
        return resultsResponseNotificationUrl;
    }

    /**
     * @param resultsResponseNotificationUrl the resultsResponseNotificationUrl to set
     */
    public void setResultsResponseNotificationUrl(URL resultsResponseNotificationUrl) {
        this.resultsResponseNotificationUrl = resultsResponseNotificationUrl;
    }
}