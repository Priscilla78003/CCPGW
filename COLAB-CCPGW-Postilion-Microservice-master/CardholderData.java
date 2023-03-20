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
public class CardholderData {
 
  /**
   * Indicates whether the Cardholder Shipping Address and Cardholder Billing Address are the same. Accepted values:
   *  Y -> Shipping Address matches Billing Address
   *  N -> Shipping Address does not match Billing Address
   *
   *  If the field is not set and the shipping and billing addresses are the same, the 3DS Server will set the value to
   *  Y. Otherwise, the value will not be changed.
   *
   * This field is optional.
   */
  private String addrMatch;
  /**
   * The city of the Cardholder billing address associated with the card used for this purchase. This field is limited
   * to maximum of 50 characters.
   *
   * This field is required unless market or regional mandate restricts sending this information.
   */
  private String billAddrCity;
  /**
   * The country of the Cardholder billing address associated with the card used for this purchase.
   * This field is limited to 3 characters. This value shall be the ISO 3166-1 numeric country code, except values
   * from range 901 - 999 which are reserved by ISO.
   *
   * The field is required if Cardholder Billing Address State is present and unless market or regional mandate
   * restricts sending this information.
   */
  private String billAddrCountry;
  /**
   * First line of the street address or equivalent local portion of the Cardholder billing address associated with
   * the card use for this purchase. This field is limited to maximum 50 characters.
   *
   * This field is required unless market or regional mandate restricts sending this information.
   */
  private String billAddrLine1;
  /**
   * Second line of the street address or equivalent local portion of the Cardholder billing address associated with
   * the card use for this purchase. This field is limited to maximum 50 characters.
   *
   * This field is required unless market or regional mandate restricts sending this information.
   */
  private String billAddrLine2;
  /**
   * Third line of the street address or equivalent local portion of the Cardholder billing address associated with
   * the card use for this purchase. This field is limited to maximum 50 characters.
   *
   * This field is required unless market or regional mandate restricts sending this information.
   */
  private String billAddrLine3;
  /**
   * ZIP or other postal code of the Cardholder billing address associated with the card used for this purchase.
   * This field is limited to maximum 16 characters.
   *
   * This field is required unless market or regional mandate restricts sending this information.
   */
  private String billAddrPostCode;
  /**
   * The state or province of the Cardholder billing address associated with the card used for this purchase.
   * This field is limited to 3 characters. The value should be the country subtivision code defined in ISO 3166-2.
   *
   * This field is required unless State is not applicable for this country and unless market or regional mandate
   * restricts sending this information.
   */
  private String billAddrState;
  /**
   * The email address associated with the account that is either entered by the Cardholder, or is on file with
   * the 3DS Requestor. This field is limited to maximum 256 characters and shall meet requirements of Section 3.4 of
   * IETF RFC 5322.
   *
   * This field is required unless market or regional mandate restricts sending this information.
   */
  private String email;
  /**
   * The home phone provided by the Cardholder. The object contains the following fields:
   *    cc -> Country Code of the phone, limited to 1-3 characters
   *    subscriber -> subscriber section of the number, limited to maximum 15 characters.
   *
   * Refer to ITU-E.164 for additional information on format and length.
   *
   * This field is required if available, unless market or regional mandate restricts sending this information.
   */
  private CardholderPhoneNumber homePhone;
  /**
   * The mobile phone provided by the Cardholder. The object contains the following fields:
   *    cc -> Country Code of the phone, limited to 1-3 characters
   *    subscriber -> subscriber section of the number, limited to maximum 15 characters.
   *
   * Refer to ITU-E.164 for additional information on format and length.
   *
   * This field is required if available, unless market or regional mandate restricts sending this information.
   */
  private CardholderPhoneNumber mobilePhone;
  /**
   * The work phone provided by the Cardholder. The object contains the following fields:
   *    cc -> Country Code of the phone, limited to 1-3 characters
   *    subscriber -> subscriber section of the number, limited to maximum 15 characters.
   *
   * Refer to ITU-E.164 for additional information on format and length.
   *
   *
   * This field is required if available, unless market or regional mandate restricts sending this information.
   */
  private CardholderPhoneNumber workPhone;
  /**
   * Name of the Cardholder. This field is limited to maximum of 45 characters.
   *
   * This field is required unless market or regional mandate restricts sending this information.
   */
  private String cardholderName;
  /**
   * City portion of the shipping address requested by the Cardholder.
   *
   * This field is required unless shipping information is the same as billing information, or market or regional
   * mandate restricts sending this information.
   */
  private String shipAddrCity;
  /**
   * Country of the shipping address requested by the Cardholder. This field is limited to 3 characters.  This value
   * shall be the ISO 3166-1 numeric country code, except values from range 901 - 999 which are reserved by ISO.
   *
   * This field is required if Cardholder Shipping Address State is present and if shipping information are not the same
   * as billing information. This field can be omitted if market or regional mandate restricts sending this information.
   */
  private String shipAddrCountry;
  /**
   * First line of the street address or equivalent local portion of the shipping address associated with
   * the card use for this purchase. This field is limited to maximum 50 characters.
   *
   * This field is required unless shipping information is the same as billing information, or market or regional
   * mandate restricts sending this information.
   */
  private String shipAddrLine1;
  /**
   * Second line of the street address or equivalent local portion of the shipping address associated with
   * the card use for this purchase. This field is limited to maximum 50 characters.
   *
   * This field is required unless shipping information is the same as billing information, or market or regional
   * mandate restricts sending this information.
  */
  private String shipAddrLine2;
  /**
   * Third line of the street address or equivalent local portion of the shipping address associated with
   * the card use for this purchase. This field is limited to maximum 50 characters.
   *
   * This field is required unless shipping information is the same as billing information, or market or regional
   * mandate restricts sending this information.
   */
  private String shipAddrLine3;
  /**
   * ZIP or other postal code of the shipping address associated with the card used for this purchase.
   * This field is limited to maximum 16 characters.
   *
   * This field is required unless shipping information is the same as billing information, or market or regional
   * mandate restricts sending this information.
   */
  private String shipAddrPostCode;
  /**
   * The state or province of the shipping address associated with the card used for this purchase.
   * This field is limited to 3 characters. The value should be the country subdivision code defined in ISO 3166-2.
   *
   * This field is required unless shipping information is the same as billing information, or State is not applicable
   * for this country, or market or regional mandate restricts sending this information.
   */
  private String shipAddrState;

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

    /**
     * @return the workPhone
     */
    public CardholderPhoneNumber getWorkPhone() {
        return workPhone;
    }

    /**
     * @param workPhone the workPhone to set
     */
    public void setWorkPhone(CardholderPhoneNumber workPhone) {
        this.workPhone = workPhone;
    }

    /**
     * @return the cardholderName
     */
    public String getCardholderName() {
        return cardholderName;
    }

    /**
     * @param cardholderName the cardholderName to set
     */
    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    /**
     * @return the shipAddrCity
     */
    public String getShipAddrCity() {
        return shipAddrCity;
    }

    /**
     * @param shipAddrCity the shipAddrCity to set
     */
    public void setShipAddrCity(String shipAddrCity) {
        this.shipAddrCity = shipAddrCity;
    }

    /**
     * @return the shipAddrCountry
     */
    public String getShipAddrCountry() {
        return shipAddrCountry;
    }

    /**
     * @param shipAddrCountry the shipAddrCountry to set
     */
    public void setShipAddrCountry(String shipAddrCountry) {
        this.shipAddrCountry = shipAddrCountry;
    }

    /**
     * @return the shipAddrLine1
     */
    public String getShipAddrLine1() {
        return shipAddrLine1;
    }

    /**
     * @param shipAddrLine1 the shipAddrLine1 to set
     */
    public void setShipAddrLine1(String shipAddrLine1) {
        this.shipAddrLine1 = shipAddrLine1;
    }

    /**
     * @return the shipAddrLine2
     */
    public String getShipAddrLine2() {
        return shipAddrLine2;
    }

    /**
     * @param shipAddrLine2 the shipAddrLine2 to set
     */
    public void setShipAddrLine2(String shipAddrLine2) {
        this.shipAddrLine2 = shipAddrLine2;
    }

    /**
     * @return the shipAddrLine3
     */
    public String getShipAddrLine3() {
        return shipAddrLine3;
    }

    /**
     * @param shipAddrLine3 the shipAddrLine3 to set
     */
    public void setShipAddrLine3(String shipAddrLine3) {
        this.shipAddrLine3 = shipAddrLine3;
    }

    /**
     * @return the shipAddrPostCode
     */
    public String getShipAddrPostCode() {
        return shipAddrPostCode;
    }

    /**
     * @param shipAddrPostCode the shipAddrPostCode to set
     */
    public void setShipAddrPostCode(String shipAddrPostCode) {
        this.shipAddrPostCode = shipAddrPostCode;
    }

    /**
     * @return the shipAddrState
     */
    public String getShipAddrState() {
        return shipAddrState;
    }

    /**
     * @param shipAddrState the shipAddrState to set
     */
    public void setShipAddrState(String shipAddrState) {
        this.shipAddrState = shipAddrState;
    }
}