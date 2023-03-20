/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

import com.google.gson.Gson;
import com.truteq.ccpgw.threeds.v2.objects.versioning.Sdk;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ThreeDSServerAuthenticationRequest {
 
  /**
   * Specifies the preferred version of 3D Secure protocol to be utilized while executing 3D Secure authentication.
   * 3DS Server initiates an authentication request with the preferred version and if this version is not supported by
   * other 3D Secure components, it falls back to the next supported version(s) and continues authentication.
   *
   * If the preferred version is enforced by setting {@link #enforcePreferredProtocolVersion} flag, but this version
   * is not supported by one of the 3D Secure components, 3DS Server does not initiate an authentication and provides
   * corresponding error message to the customer.
   *
   * For application initiated transactions (deviceChannel = '01'), the preferred protocol version must be enforced
   * ({@link #enforcePreferredProtocolVersion} must be set to true).
   *
   * The accepted values are:
   *  2.1.0 -> prefer authentication with 2.1.0 version,
   *  2.2.0 -> prefer authentication with 2.2.0 version,
   *  latest -> prefer authentication with the latest version, the 3DS Server is certified for. 2.2.0 at this moment.
   *
   *  If no value is provided, EMV 3DS 2.1.0 version will be used by default.
   *  Available for supporting EMV 3DS 2.2.0 and later versions.
   */

  private String preferredProtocolVersion = "2.1.0";
  /**
   * Boolean flag that enforces preferred 3D Secure protocol version to be used in 3D Secure authentication.
   * The value should be set true to enforce preferred version. If value is false or not provided,
   * 3DS Server can fall back to next supported 3DS protocol version while initiating 3D Secure authentication.
   *
   * For application initiated transactions (deviceChannel = '01'), the preferred protocol version must be enforced.
   *
   * @see #preferredProtocolVersion
   * Available for supporting EMV 3DS 2.2.0 and later versions.
   */
  private Boolean enforcePreferredProtocolVersion = Boolean.FALSE;
  /**
   * Indicates the type of channel interface being used to initiate the transaction. The accepted values are:
   *
   *  01 -> App-based (APP)
   *  02 -> BrowserData (BRW)
  03 -> 3DS Requestor Initiated (3RI)
   *
   *  This is a required field.
   */
  private String deviceChannel;
  /**
   * Identifies the category of the message for a specific use case. The accepted values are:
   *
   *  01 -> PA
   *  02 -> NPA
   *  80 - 99 -> PS Specific Values (80 -> MasterCard Identity Check Insights;
   *                                 85 -> MasterCard Identity Check, Production Validation PA;
   *                                 86 -> MasterCard Identity Check, Production Validation NPA)
   *
   *  This is a required field.
   */
  private String messageCategory;
  /**
   * Indicates whether the 3DS Method successfully completed. The value is used only when deviceChannel = 02 (Browser).
   *
   * The accepted values are:
   *
   *  Y -> Successfully completed
   *  N -> Did not successfully complete
   *  U -> Unavailable - 3DS Method URL was not present in the PRes message data for the card range associated
                      with the CardholderData Account Number.

 If the 3DS Server handles the 3DS Method response, then this field is not required and it will be resolved
 internally. If there is no 3DS Method URL associated with the Directory Server to which the cardholder belongs,
 then this field is not required and 3DS Server will send U. Otherwise, this field should be sent.
   */
  private String threeDSCompInd;
  /**
   * Contains information for the 3DS Requestor. More details can be found at {@link ThreeDSRequestor}.
   */
  private ThreeDSRequestor threeDSRequestor;
  /**
   * Universally unique transaction identifier assigned by the 3DS Server to identify a single transaction.
   * This value has 36 characters in a format defined in IETF RFC 4122. In case 3DS Method is previously invoked,
   * the threeDSServerTransID should be sent. If not, 3DS Server will generate a new transaction identifier.
   */
  private String threeDSServerTransID;
  /**
   * Fully qualified URL of 3DS Requestor website or customer care site. This field is optional and it is recommended
   * to be configured in the configuration.
   */
  private URL threeDSRequestorURL;
  /**
   * Contains information for the Cardholder Account. More details can be found at {@link CardholderAccount}.
   */
  private CardholderAccount cardholderAccount;
  /**
   * Contains information for the Cardholder. More details can be found at {@link CardholderData}.
   *
   * This field is required unless market or regional mandate restricts sending this information.
   */
  private CardholderData cardholder;
  /**
   * Contains purchase information. More details can be found at {@link PurchaseData}.
   */
  private PurchaseData purchase;
  /**
   * Contains information for the Acquirer. More details can be found at {@link AcquirerData}.
   */
  private AcquirerData acquirer;
  /**
   * Contains merchant information. More details can be found at {@link MerchantData}.
   */
  private MerchantData merchant;
  /**
   * Unstructured information sent between the 3DS Server, the DS and the ACS.
   *
   * This field is not required to be filled by the Requestor and the requirements for the presence of this field
   * are DS specific.
   */ 
  private BroadInfo broadInfo;
  /**
   * Defines the SDK UI types that the device supports for displaying specific challenge user interfaces within the SDK.
   *
   * This field is required only when deviceChannel=01 (APP).
   *
   * Fields in this object:
   *    sdkInterface -> Specifies all of the SDK Interface types that the device supports for displaying specific
   *                    challenge user interfaces within the SDK. Accepted values are:
   *                      01 -> Native
   *                      02 -> HTML
   *                      03 -> Both
   *    sdkUiType -> Contains a list of all UI types that the device supports for displaying specific challenge user
   *                 interfaces within the SDK. Accepted values for each UI type are:
   *                    01 -> Text
   *                    02 -> Single select
   *                    03 -> Multi select
   *                    04 -> OOB
   *                    05 -> Html Other (valid only for HTML UI)
   *                 For Native UI SDK Interface accepted values are 01-04 and for HTML UI accepted values are 01-05.
   */
  private DeviceRenderOptions deviceRenderOptions;
  /**
   * Data necessary to support requirements not otherwise defined in the 3D Secure message are
   * carried in a Message Extension. This field is limited to 81.920 characters and it is used in the
   * Authentication Request.
   *
   * Requirements of this field are set by each Directory Server.
   *
   * The fields for each message extension attribute are:
   *    id -> A unique identifier for the extension. Payment System Registered Application Provider Identifier (RID) is
   *          required as prefix of the ID. The maximum length is 64 characters.
   *    name -> The name of the extension data set as defined by the extension owner. Maximum length is 64 characters.
   *    criticalityIndicator -> A boolean value indicating whether the recipient must understand the contents
   *                            of the extension to interpret the entire message.
   *    data -> The data carried in the extension. The maximum length is 8059 characters.
   */
  private List<MessageExtensionAttribute> messageExtension;
  /**
   * Data necessary to support requirements not otherwise defined in the 3D Secure message are
   * carried in a Message Extension. This field is limited to 81.920 characters and it is used in the
   * generating of the ChallengeRequest, if challenge is needed.
   *
   * Requirements of this field are set by each Directory Server.
   *
   * The fields for each message extension attribute are:
   *    id -> A unique identifier for the extension. Payment System Registered Application Provider Identifier (RID) is
   *          required as prefix of the ID. The maximum length is 64 characters.
   *    name -> The name of the extension data set as defined by the extension owner. Maximum length is 64 characters.
   *    criticalityIndicator -> A boolean value indicating whether the recipient must understand the contents
   *                            of the extension to interpret the entire message.
   *    data -> The data carried in the extension. The maximum length is 8059 characters.
   */
  private List<MessageExtensionAttribute> challengeMessageExtension;
  /**
   * Contains browser information. More details can be found at {@link BrowserData}.
   *
   * This field is required when deviceChannel=02 (BRW).
   */
  private BrowserData browserInformation;
  /**
   * Indicates the type of 3RI request. This data element provides additional information to the ACS to determine the
   * best approach for handling a 3RI request. The accepted values are:
   *  01 -> Recurring transaction
   *  02 -> Installment transaction
   *  03 -> Add card
   *  04 -> Maintain card information
   *  05 -> Account verification.
   *
   *  The next values are accepted as well if 3DS Server initiates authentication with EMV 3DS 2.2.0 version
   *  or greater (required protocol version can be set in
   *  {@link ThreeDSServerAuthenticationRequest#preferredProtocolVersion} field):
   *
   *  06 -> Split/delayed shipment
   *  07 -> Top-up
   *  08 -> Mail order
   *  09 -> Telephone order
   *  10 -> Whitelist status check
   *  11 -> Other payment
   *  12 -> Billing agreement.
   *
   *  Additionally, 80-99 can be used for PS-specific values, regardless of protocol version.
   *
   *  This field is required only when deviceChannel=03 (3RI) and messageCategory=02 (NPA).
   */
  private String threeRIInd;
  /**
  * Contains SDK information. More details can be found at {@link Sdk}.
   *
   * All fields in this object are required for App-based transactions.
  */
  private Sdk sdkInformation;
  
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }  

    /**
     * @return the preferredProtocolVersion
     */
    public String getPreferredProtocolVersion() {
        return preferredProtocolVersion;
    }

    /**
     * @param preferredProtocolVersion the preferredProtocolVersion to set
     */
    public void setPreferredProtocolVersion(String preferredProtocolVersion) {
        this.preferredProtocolVersion = preferredProtocolVersion;
    }

    /**
     * @return the enforcePreferredProtocolVersion
     */
    public Boolean getEnforcePreferredProtocolVersion() {
        return enforcePreferredProtocolVersion;
    }

    /**
     * @param enforcePreferredProtocolVersion the enforcePreferredProtocolVersion to set
     */
    public void setEnforcePreferredProtocolVersion(Boolean enforcePreferredProtocolVersion) {
        this.enforcePreferredProtocolVersion = enforcePreferredProtocolVersion;
    }

    /**
     * @return the deviceChannel
     */
    public String getDeviceChannel() {
        return deviceChannel;
    }

    /**
     * @param deviceChannel the deviceChannel to set
     */
    public void setDeviceChannel(String deviceChannel) {
        this.deviceChannel = deviceChannel;
    }

    /**
     * @return the messageCategory
     */
    public String getMessageCategory() {
        return messageCategory;
    }

    /**
     * @param messageCategory the messageCategory to set
     */
    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

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
     * @return the threeDSRequestor
     */
    public ThreeDSRequestor getThreeDSRequestor() {
        return threeDSRequestor;
    }

    /**
     * @param threeDSRequestor the threeDSRequestor to set
     */
    public void setThreeDSRequestor(ThreeDSRequestor threeDSRequestor) {
        this.threeDSRequestor = threeDSRequestor;
    }

    /**
     * @return the threeDSServerTransID
     */
    public String getThreeDSServerTransID() {
        return threeDSServerTransID;
    }

    /**
     * @param threeDSServerTransID the threeDSServerTransID to set
     */
    public void setThreeDSServerTransID(String threeDSServerTransID) {
        this.threeDSServerTransID = threeDSServerTransID;
    }

    /**
     * @return the threeDSRequestorURL
     */
    public URL getThreeDSRequestorURL() {
        return threeDSRequestorURL;
    }

    /**
     * @param threeDSRequestorURL the threeDSRequestorURL to set
     */
    public void setThreeDSRequestorURL(URL threeDSRequestorURL) {
        this.threeDSRequestorURL = threeDSRequestorURL;
    }

    /**
     * @return the cardholderAccount
     */
    public CardholderAccount getCardholderAccount() {
        return cardholderAccount;
    }

    /**
     * @param cardholderAccount the cardholderAccount to set
     */
    public void setCardholderAccount(CardholderAccount cardholderAccount) {
        this.cardholderAccount = cardholderAccount;
    }

    /**
     * @return the cardholder
     */
    public CardholderData getCardholder() {
        return cardholder;
    }

    /**
     * @param cardholder the cardholder to set
     */
    public void setCardholder(CardholderData cardholder) {
        this.cardholder = cardholder;
    }

    /**
     * @return the purchase
     */
    public PurchaseData getPurchase() {
        return purchase;
    }

    /**
     * @param purchase the purchase to set
     */
    public void setPurchase(PurchaseData purchase) {
        this.purchase = purchase;
    }

    /**
     * @return the acquirer
     */
    public AcquirerData getAcquirer() {
        return acquirer;
    }

    /**
     * @param acquirer the acquirer to set
     */
    public void setAcquirer(AcquirerData acquirer) {
        this.acquirer = acquirer;
    }

    /**
     * @return the merchant
     */
    public MerchantData getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(MerchantData merchant) {
        this.merchant = merchant;
    }

    /**
     * @return the broadInfo
     */
    public BroadInfo getBroadInfo() {
        return broadInfo;
    }

    /**
     * @param broadInfo the broadInfo to set
     */
    public void setBroadInfo(BroadInfo broadInfo) {
        this.broadInfo = broadInfo;
    }

    /**
     * @return the deviceRenderOptions
     */
    public DeviceRenderOptions getDeviceRenderOptions() {
        return deviceRenderOptions;
    }

    /**
     * @param deviceRenderOptions the deviceRenderOptions to set
     */
    public void setDeviceRenderOptions(DeviceRenderOptions deviceRenderOptions) {
        this.deviceRenderOptions = deviceRenderOptions;
    }

    /**
     * @return the messageExtension
     */
    public List<MessageExtensionAttribute> getMessageExtension() {
        return messageExtension;
    }

    /**
     * @param messageExtension the messageExtension to set
     */
    public void setMessageExtension(List<MessageExtensionAttribute> messageExtension) {
        this.messageExtension = messageExtension;
    }

    /**
     * @return the challengeMessageExtension
     */
    public List<MessageExtensionAttribute> getChallengeMessageExtension() {
        return challengeMessageExtension;
    }

    /**
     * @param challengeMessageExtension the challengeMessageExtension to set
     */
    public void setChallengeMessageExtension(List<MessageExtensionAttribute> challengeMessageExtension) {
        this.challengeMessageExtension = challengeMessageExtension;
    }

    /**
     * @return the browserInformation
     */
    public BrowserData getBrowserInformation() {
        return browserInformation;
    }

    /**
     * @param browserInformation the browserInformation to set
     */
    public void setBrowserInformation(BrowserData browserInformation) {
        this.browserInformation = browserInformation;
    }

    /**
     * @return the threeRIInd
     */
    public String getThreeRIInd() {
        return threeRIInd;
    }

    /**
     * @param threeRIInd the threeRIInd to set
     */
    public void setThreeRIInd(String threeRIInd) {
        this.threeRIInd = threeRIInd;
    }

    /**
     * @return the sdkInformation
     */
    public Sdk getSdkInformation() {
        return sdkInformation;
    }

    /**
     * @param sdkInformation the sdkInformation to set
     */
    public void setSdkInformation(Sdk sdkInformation) {
        this.sdkInformation = sdkInformation;
    }
  
}