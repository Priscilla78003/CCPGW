/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

import java.math.BigInteger;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PurchaseData {
 
  /**
   * Indicates the maximum number of authorisations permitted for instalment payments. The field is limited to maximum
   * 3 characters and value shall be greater than 1.
   *
   * The fields is required if the Merchant and Cardholder have agreed to installment payments, i.e. if 3DS Requestor
   * Authentication Indicator = 03. Omitted if not an installment payment authentication.
   */
  private Integer purchaseInstalData;
  /**
   * Merchant's assessment of the level of fraud risk for the specific authentication for both the cardholder and the
   * authentication being conducted.
   *
   * The field is optional but strongly recommended to include.
   *
   * This object contains the following fields:
   *    shipIndicator -> Indicates shipping method chosen for the transaction. Merchants must choose the Shipping
   *                     Indicator code that most accurately describes the cardholder's specific transaction.
   *                     If one or more items are included in the sale, use the Shipping Indicator code for the physical
   *                     goods, or if all digital goods, use the code that describes the most expensive item.
   *                     Accepted values are:
   *                        01 -> Ship to cardholder's billing address
   *                        02 -> Ship to another verified address on file with merchant
   *                        03 -> Ship to address that is different than the cardholder's billing address
   *                        04 -> "Ship to Store" / Pick-up at local store (Store address shall be populated in shipping
   *                              address fields)
   *                        05 -> Digital goods (includes online services, electronic gift cards and redemption codes)
   *                        06 -> Travel and Event tickets, not shipped
   *                        07 -> Other (for example, Gaming, digital services not shipped, e-media subscriptions, etc.)
   *                        80-81 -> PS-specific value (dependent on the payment scheme type)
   *    deliveryTimeframe -> Indicates the merchandise delivery timeframe. Accepted values are:
   *                            01 -> Electronic Delivery
   *                            02 -> Same day shipping
   *                            03 -> Overnight shipping
   *                            04 -> Two-day or more shipping
   *    deliveryEmailAddress -> For electronic delivery, the email address to which the merchandise was delivered.
   *    reorderItemsInd -> Indicates whether the cardholder is reordering previously purchased merchandise.
   *                       Accepted values are:
   *                          01 -> First time ordered
   *                          02 -> Reordered
   *    preOrderPurchaseInd -> Indicates whether Cardholder is placing an order for merchandise with a future
   *                           availability or release date. Accepted values are:
   *                              01 -> Merchandise available
   *                              02 -> Future availability
   *    preOrderDate -> For a pre-ordered purchase, the expected date that the merchandise will be available.
   *                    Date format must be YYYYMMDD.
   *    giftCardAmount -> For prepaid or gift card purchase, the purchase amount total of prepaid or gift card(s) in
   *                      major units (for example, USD 123.45 is 123).
   *    giftCardCurr -> For prepaid or gift card purchase, the currency code of the card as defined in ISO 4217 except
   *                    955 - 964 and 999.
   *    giftCardCount -> For prepaid or gift card purchase, total count of individual prepaid or gift cards/codes
   *                     purchased. Field is limited to 2 characters.
   */
  private MerchantRiskIndicator merchantRiskIndicator;
  /**
   * Purchase amount in minor units of currency with all punctuation removed. When used in conjunction with the Purchase
   * Currentcy Exponent field, proper punctuation can be calculated. Example: If the purchase amount is USD 123.45,
   * element will contain the value 12345. The field is limited to maximum 48 characters.
   *
   * This field is required for 02 - NPA message category if 3DS Requestor Authentication Indicator = 02 or 03.
   */
  private BigInteger purchaseAmount;
  /**
   * Currency in which purchase amount is expressed. The value is limited to 3 numeric characters and is represented by
   * the ISO 4217 three-digit currency code, except 955-964 and 999.
   *
   * This field is required for requests where messageCategory = 01-PA and for 02-NPA if 3DS Requestor Authentication
   * Indicator = 02 or 03.
   */
  private String purchaseCurrency;
  /**
   * Minor units of currency as specified in the ISO 4217 currency exponent. The field is limited to 1 character and it
   * is required for 01-PA and for 02-NPA if 3DS Requestor Authentication Indicator = 02 or 03.
   *
   * Example: for currency USD the exponent should be 2, and for Yen the exponent should be 0.
   */
  private Integer purchaseExponent;
  /**
   * Date and time of the purchase, expressed in UTC. The field is limited to 14 characters,
   * formatted as YYYYMMDDHHMMSS.
   *
   * This field is required for 01-PA and for 02-NPA, if 3DS Requestor Authentication Indicator = 02 or 03.
   */

  private String purchaseDate;
  /**
   * Date after which no further authorizations shall be performed. This field is limited to 8 characters, and the
   * accepted format is YYYYMMDD.
   *
   * This field is required for 01-PA and for 02-NPA, if 3DS Requestor Authentication Indicator = 02 or 03.
   */

  private String recurringExpiry;
  /**
   * Indicates the minimum number of days between authorizations. The field is limited to maximum 4 characters.
   *
   * This field is required if 3DS Requestor Authentication Indicator = 02 or 03.
   */
  private Integer recurringFrequency;
  /**
   * Identifies the type of transaction being authenticated. The values are derived from ISO 8583. Accepted values are:
   *    01 -> Goods / Service purchase
   *    03 -> Check Acceptance
   *    10 -> Account Funding
   *    11 -> Quasi-Cash Transaction
   *    28 -> Prepaid activation and Loan
   *
   * This field is required in some markets. Otherwise, the field is optional.
   */
  private String transType;

    /**
     * @return the purchaseInstalData
     */
    public Integer getPurchaseInstalData() {
        return purchaseInstalData;
    }

    /**
     * @param purchaseInstalData the purchaseInstalData to set
     */
    public void setPurchaseInstalData(Integer purchaseInstalData) {
        this.purchaseInstalData = purchaseInstalData;
    }

    /**
     * @return the merchantRiskIndicator
     */
    public MerchantRiskIndicator getMerchantRiskIndicator() {
        return merchantRiskIndicator;
    }

    /**
     * @param merchantRiskIndicator the merchantRiskIndicator to set
     */
    public void setMerchantRiskIndicator(MerchantRiskIndicator merchantRiskIndicator) {
        this.merchantRiskIndicator = merchantRiskIndicator;
    }

    /**
     * @return the purchaseAmount
     */
    public BigInteger getPurchaseAmount() {
        return purchaseAmount;
    }

    /**
     * @param purchaseAmount the purchaseAmount to set
     */
    public void setPurchaseAmount(BigInteger purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    /**
     * @return the purchaseCurrency
     */
    public String getPurchaseCurrency() {
        return purchaseCurrency;
    }

    /**
     * @param purchaseCurrency the purchaseCurrency to set
     */
    public void setPurchaseCurrency(String purchaseCurrency) {
        this.purchaseCurrency = purchaseCurrency;
    }

    /**
     * @return the purchaseExponent
     */
    public Integer getPurchaseExponent() {
        return purchaseExponent;
    }

    /**
     * @param purchaseExponent the purchaseExponent to set
     */
    public void setPurchaseExponent(Integer purchaseExponent) {
        this.purchaseExponent = purchaseExponent;
    }

    /**
     * @return the purchaseDate
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the recurringExpiry
     */
    public String getRecurringExpiry() {
        return recurringExpiry;
    }

    /**
     * @param recurringExpiry the recurringExpiry to set
     */
    public void setRecurringExpiry(String recurringExpiry) {
        this.recurringExpiry = recurringExpiry;
    }

    /**
     * @return the recurringFrequency
     */
    public Integer getRecurringFrequency() {
        return recurringFrequency;
    }

    /**
     * @param recurringFrequency the recurringFrequency to set
     */
    public void setRecurringFrequency(Integer recurringFrequency) {
        this.recurringFrequency = recurringFrequency;
    }

    /**
     * @return the transType
     */
    public String getTransType() {
        return transType;
    }

    /**
     * @param transType the transType to set
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }
}