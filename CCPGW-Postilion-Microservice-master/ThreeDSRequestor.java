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
public class ThreeDSRequestor {
 
  /**
   * Indicates the type of Authentication request. This data element provides additional information to the ACS to
   * determine the best approach for handling an authentication request. This value is used for App-based and Browser
   * flows. The accepted values are:
   *
   *  01 -> Payment transaction
   *  02 -> Recurring transaction
   *  03 -> Installment transaction
   *  04 -> Add card
   *  05 -> Maintain card
   *  06 -> Cardholder verification as part of EMV token ID&V
   *  07 -> Billing agreement.
   *
   * Additionally, 80-99 can be used for PS-specific values, regardless of protocol version.
   *
   *  This is a required field.
   */
  private String threeDSRequestorAuthenticationInd;
  /**
   * Information about how the 3DS Requestor authenticated the cardholder before or during the transaction. This object
   * contains the following fields:
   *    threeDSReqAuthMethod -> Mechanism used by the Cardholder to authenticate to the 3DS Requestor. Accepted values
   *    are:
   *      01 -> No 3DS Requestor authentication occurred (i.e. cardholder "logged in" as guest)
   *      02 -> Login to the cardholder account at the 3DS Requestor system using 3DS Requestor's own credentials
   *      03 -> Login to the cardholder account at the 3DS Requestor system using federated ID
   *      04 -> Login to the cardholder account at the 3DS Requestor system using issuer credentials
   *      05 -> Login to the cardholder account at the 3DS Requestor system using third-party authentication
   *      06 -> Login to the cardholder account at the 3DS Requestor system using FIDO Authenticator.
   *
   *      The next values are accepted as well if 3DS Server initiates authentication with EMV 3DS 2.2.0 version
   *      or greater (required protocol version can be set in
   *      {@link ThreeDSServerAuthenticationRequest#preferredProtocolVersion} field):
   *      07 -> Login to the cardholder account at the 3DS Requestor system using FIDO Authenticator
   *            (FIDO assurance data signed).
   *      08 -> SRC Assurance Data.
   *
   *      Additionally, 80-99 can be used for PS-specific values, regardless of protocol version.
   *
   *    threeDSReqAuthTimestamp -> Date and time in UTC of the cardholder authentication. Field is limited to 12
   *                               characters and accepted format is YYYYMMDDHHMM
   *    threeDSReqAuthData -> Data that documents and supports a specific authentication process. In the current
   *                          version of the specification, this data element is not defined in detail, however the
   *                          intention is that for each 3DS Requestor Authentication Method, this field carry data
   *                          that the ACS can use to verify the authentication process. For example, if the 3DS
   *                          Requestor Authentication Method is:
   *                            03 -> then this element can carry information about the provider of the federated ID and
   *                                  related information
   *                            06 -> then this element can carry the FIDO attestation data (incl. the signature)
   *
   * This field is optional, but recommended to include.
   */
  private ThreeDSRequestorAuthenticationInformation threeDSRequestorAuthenticationInfo;
  /**
   * Indicates whether a challenge is requested for this transaction. For example: For 01-PA, a 3DS Requestor may have
   * concerns about the transaction, and request a challenge. For 02-NPA, a challenge may be necessary when adding a new
   * card to a wallet.
   *
   * This field is optional. The accepted values are:
   *
   *  01 -> No preference
   *  02 -> No challenge requested
   *  03 -> Challenge requested: 3DS Requestor Preference
   *  04 -> Challenge requested: Mandate.
   *
   *  The next values are accepted as well if 3DS Server initiates authentication with EMV 3DS 2.2.0 version
   *  or greater (required protocol version can be set in
   *  {@link ThreeDSServerAuthenticationRequest#preferredProtocolVersion} field):
   *
   *  05 -> No challenge requested (transactional risk analysis is already performed)
   *  06 -> No challenge requested (Data share only)
   *  07 -> No challenge requested (strong consumer authentication is already performed)
   *  08 -> No challenge requested (utilise whitelist exemption if no challenge required)
   *  09 -> Challenge requested (whitelist prompt requested if challenge required).
   *
   *  Additionally, 80-99 can be used for PS-specific values, regardless of protocol version.
   *
   * If the element is not provided, the expected action is that the ACS would interpret as 01 -> No preference.
   */
  private String threeDSRequestorChallengeInd;
  /**
   * This field contains information about how the 3DS Requestor authenticated the cardholder as part of a previous
   * 3DS transaction. This object contains the following fields:
   *    threeDSReqPriorRef -> This data element provides additional information to the ACS to determine the best
   *                          approach for handling a request. The field is limited to 36 characters containing
   *                          ACS Transaction ID for a prior authenticated transaction (for example, the first
   *                          recurring transaction that was authenticated with the cardholder).
   *    threeDSReqPriorAuthMethod -> Mechanism used by the Cardholder to previously authenticate to the 3DS Requestor.
   *                                 Accepted values for this field are:
   *                                    01 -> Frictionless authentication occurred by ACS
   *                                    02 -> Cardholder challenge occurred by ACS
   *                                    03 -> AVS verified
   *                                    04 -> Other issuer methods
   *                                    80-99 -> PS-specific value (dependent on the payment scheme type).
   *    threeDSReqPriorAuthTimestamp -> Date and time in UTC of the prior authentication. Accepted date format is
   *                                    YYYYMMDDHHMM.
   *    threeDSReqPriorAuthData -> Data that documents and supports a specific authentication process. In the current
   *                               version of the specification this data element is not defined in detail, however
   *                               the intention is that for each 3DS Requestor Authentication Method, this field carry
   *                               data that the ACS can use to verify the authentication process. In future versions
   *                               of the application, these details are expected to be included. Field is limited to
   *                               maximum 2048 characters.
   *
   * This field is optional, but recommended to include.
   */
  private ThreeDSRequestorPriorTransactionAuthenticationInformation threeDSRequestorPriorAuthenticationInfo;
  /**
   * Indicates whether the 3DS Requestor requests the ACS to utilise Decoupled Authentication and
   * agrees to utilise Decoupled Authentication if the ACS confirms its use. Accepted values are:
   *    Y -> Decoupled Authentication is supported and preferred if challenge is necessary.
   *    N -> Do not use Decoupled Authentication.
   *
   * The field is optional and if value is not present, the expected action is for the ACS to interpret as 'N'.
   * Available for supporting EMV 3DS 2.2.0 and later versions.
   */
  private String  threeDSRequestorDecReqInd;
  /**
   * Indicates the maximum amount of time that the 3DS Requestor will wait for an ACS to provide the results
   * of a Decoupled Authentication transaction (in minutes). Valid values are between 1 and 10080.
   *
   * The field is optional and if value is not present, the expected action is for the ACS to interpret it as
   * 10080 minutes (7 days).
   * Available for supporting EMV 3DS 2.2.0 and later versions.
   */
  private Integer threeDSRequestorDecMaxTime;

    /**
     * @return the threeDSRequestorAuthenticationInd
     */
    public String getThreeDSRequestorAuthenticationInd() {
        return threeDSRequestorAuthenticationInd;
    }

    /**
     * @param threeDSRequestorAuthenticationInd the threeDSRequestorAuthenticationInd to set
     */
    public void setThreeDSRequestorAuthenticationInd(String threeDSRequestorAuthenticationInd) {
        this.threeDSRequestorAuthenticationInd = threeDSRequestorAuthenticationInd;
    }

    /**
     * @return the threeDSRequestorAuthenticationInfo
     */
    public ThreeDSRequestorAuthenticationInformation getThreeDSRequestorAuthenticationInfo() {
        return threeDSRequestorAuthenticationInfo;
    }

    /**
     * @param threeDSRequestorAuthenticationInfo the threeDSRequestorAuthenticationInfo to set
     */
    public void setThreeDSRequestorAuthenticationInfo(ThreeDSRequestorAuthenticationInformation threeDSRequestorAuthenticationInfo) {
        this.threeDSRequestorAuthenticationInfo = threeDSRequestorAuthenticationInfo;
    }

    /**
     * @return the threeDSRequestorChallengeInd
     */
    public String getThreeDSRequestorChallengeInd() {
        return threeDSRequestorChallengeInd;
    }

    /**
     * @param threeDSRequestorChallengeInd the threeDSRequestorChallengeInd to set
     */
    public void setThreeDSRequestorChallengeInd(String threeDSRequestorChallengeInd) {
        this.threeDSRequestorChallengeInd = threeDSRequestorChallengeInd;
    }

    /**
     * @return the threeDSRequestorPriorAuthenticationInfo
     */
    public ThreeDSRequestorPriorTransactionAuthenticationInformation getThreeDSRequestorPriorAuthenticationInfo() {
        return threeDSRequestorPriorAuthenticationInfo;
    }

    /**
     * @param threeDSRequestorPriorAuthenticationInfo the threeDSRequestorPriorAuthenticationInfo to set
     */
    public void setThreeDSRequestorPriorAuthenticationInfo(ThreeDSRequestorPriorTransactionAuthenticationInformation threeDSRequestorPriorAuthenticationInfo) {
        this.threeDSRequestorPriorAuthenticationInfo = threeDSRequestorPriorAuthenticationInfo;
    }

    /**
     * @return the threeDSRequestorDecReqInd
     */
    public String getThreeDSRequestorDecReqInd() {
        return threeDSRequestorDecReqInd;
    }

    /**
     * @param threeDSRequestorDecReqInd the threeDSRequestorDecReqInd to set
     */
    public void setThreeDSRequestorDecReqInd(String threeDSRequestorDecReqInd) {
        this.threeDSRequestorDecReqInd = threeDSRequestorDecReqInd;
    }

    /**
     * @return the threeDSRequestorDecMaxTime
     */
    public Integer getThreeDSRequestorDecMaxTime() {
        return threeDSRequestorDecMaxTime;
    }

    /**
     * @param threeDSRequestorDecMaxTime the threeDSRequestorDecMaxTime to set
     */
    public void setThreeDSRequestorDecMaxTime(Integer threeDSRequestorDecMaxTime) {
        this.threeDSRequestorDecMaxTime = threeDSRequestorDecMaxTime;
    }
}