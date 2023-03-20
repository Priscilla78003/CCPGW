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
public class CardholderAccount {
 
  /**
   * Indicates the type of account. For example, for a multi-account card product. Accepted values are:
   *  01 -> Not applicable
   *  02 -> Credit
   *  03 -> Debit
   *  80 -> JCB specific value for Prepaid
   *  81-99 -> PS-specific value (dependent on the payment scheme type).
   *
   *  This is required if 3DS Requestor is asking Cardholder which Account Type they are using before making
   *  the purchase. This field is required in some markets. Otherwise, it is optional.
   */
  private String acctType;
  /**
   * Expiry date of the PAN or token supplied to the 3DS Requestor by the Cardholder. The field has 4 characters
   * in a format YYMM.
   *
   * The requirements of the presence of this field are DS specific.
   */
  private String cardExpiryDate;
  /**
   * This field contains additional information about the Cardholder’s account provided by the 3DS Requestor.
   *
   * The field is optional but recommended to include.
   *
   * This field contains the properties:
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
  private CardholderAccountInformation acctInfo;
  /**
   * Account number that will be used in the authorization request for payment transactions.
   * May be represented by PAN or token.
   *
   * This field is required.
   */
  private String acctNumber;
  /**
   * ID for the scheme to which the Cardholder's acctNumber belongs to. It will be used to identify the Scheme from
   * the 3DS Server configuration.
   *
   * This field is optional, but recommended to include.
   * It should be present when it is not one of the schemes for which scheme resolving regular expressions
   * are provided in the 3DS Server Configuration Properties. Additionally,
   * if the schemeId is present in the request and there are card ranges found by multiple schemes, the schemeId will be
   * used for proper resolving of the versioning data.
   */
  private String schemeId;
  /**
   * Additional information about the account optionally provided by the 3DS Requestor.
   *
   * This field is limited to 64 characters and it is optional to use.
   */
  private String acctID;
  /**
   * This field has a value of "true" if it indicates that the transaction was de-tokenized prior to being received by
   * the ACS. This data element will be populated by the system residing in the 3D Secure domain where the
   * de-tokenization occurs (i.e. the 3DS Server or the DS).
   *
   * The boolean value of true is the only valid response for this field when it is present.
   *
   * The field is required only if there is a de-tokenization of an Account Number.
   */
  private Boolean payTokenInd;

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
     * @return the acctInfo
     */
    public CardholderAccountInformation getAcctInfo() {
        return acctInfo;
    }

    /**
     * @param acctInfo the acctInfo to set
     */
    public void setAcctInfo(CardholderAccountInformation acctInfo) {
        this.acctInfo = acctInfo;
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
     * @return the schemeId
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * @param schemeId the schemeId to set
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    /**
     * @return the acctID
     */
    public String getAcctID() {
        return acctID;
    }

    /**
     * @param acctID the acctID to set
     */
    public void setAcctID(String acctID) {
        this.acctID = acctID;
    }

    /**
     * @return the payTokenInd
     */
    public Boolean getPayTokenInd() {
        return payTokenInd;
    }

    /**
     * @param payTokenInd the payTokenInd to set
     */
    public void setPayTokenInd(Boolean payTokenInd) {
        this.payTokenInd = payTokenInd;
    }
 
}