/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.versioning;

import com.google.gson.Gson;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ThreeDSServerVersioningResponse {
  /**
   * Constant defining the name of the field in the 3DS Method form containing the encoded 3DS Method Data.
   */
  private static String THREE_DS_METHOD_DATA_FORM_FIELD_NAME = "threeDSMethodData";
 
  /**
   * Universally unique transaction identifier assigned by the 3DS Server to identify a single transaction.
   */
  private String threeDSServerTransID;
  /**
   * The earliest (i.e. oldest) active protocol version that is supported by the ACS, retrieved from the card range
   * data repository.
   */
  private String acsStartProtocolVersion;
  /**
   * The most recent active protocol version that is supported for the ACS URL, retrieved from the card range
   * data repository.
   */
  private String acsEndProtocolVersion;
  /**
   * The earliest (i.e. oldest) active protocol version that is supported by the DS, retrieved from the card range
   * data repository, or optionally from the Directory Server repository in case this info is not present for the
   * particular card range.
   */
  private String dsStartProtocolVersion;
  /**
   * The most recent active protocol version that is supported for the DS, retrieved from the card range
   * data repository, or optionally from the Directory Server repository in case this info is not present for the
   * particular card range.
   */
  private String dsEndProtocolVersion;
  /**
   * The highest (most recent) active protocol version supported by the 3DS Server, DS and ACS. This value is obtained
   * as the highest common divisor between the highest 3DS Server, DS and ACS supported protocol version, whereby
   * the DS and ACS highest (#dsEndProtocolVersion and #acsEndProtocolVersion) supported protocol version are
   * retrieved from the card range data repository.
   *
   * If the highest DS and ACS protocol versions cannot be retrieved this field will be null.
   *
   * If present, it is recommended that this value be utilised in the subsequent authentication request.
   */
  private String highestCommonSupportedProtocolVersion;
  /**
   * The ACS Information Indicator provides additional information to the Requestor. The element lists
   * all applicable values for the card range. This information is available only if the Directory Server
   * provided the information.
   *
   * Possible values are:
   *   01 - Authentication Available at ACS
   *   02 - Attempts Supported by ACS or DS
   *   03 - Decoupled Authentication Supported
   *   04 - Whitelisting Supported
   *   80-99 - PS-specific value (these values are dependent on the payment scheme type)
   *
   *   Example: "acsInfoInd": ["01","02","80","81"]
   */
  private List<String> acsInfoInd;
  /**
   * The ACS URL that will be used by the 3DS Method, retrieved from the card range data repository.
   */
  private URL threeDSMethodURL;
  /**
   * Map containing a key 'threeDSMethodData' and value encoded ThreeDSMethodData JSON object (containing 3DS Server
   * Transaction ID and 3DS Method Notification URL). This map shall be utilised when communicating to the ACS 3DS
   * Method URL. In case 3DS Method URL is not stored in the card range data repository for the particular card
   * range, this field will be null.
   */
  private Map<String, String> threeDSMethodDataForm;
  /**
   * Object containing the 3DS Method Data (3DS Server Transaction ID and 3DS Method Notification URL).
   * It's placed as a supplement to the threeDSMethodDataForm. In case 3DS Method URL is not stored in the card range
   * data repository for the particular card range, this field will be null.
   */
  private ThreeDSMethodData threeDSMethodData;
  /**
   * Object suggesting purpose of not existence of some fields in the returned output (Invalid cardholder account
   * number passed, not available card range data, not available 3DS Method URL for cardholder account number,
   * failure in encoding/serialization of the 3DS Method data).
   */  
  private ErrorDetails errorDetails;
   /**
   * Boolean indicating whether a card is found within the card ranges. Present only for API v2.
   */
  private Boolean isCardFoundIn2xRanges;  


  
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @return the THREE_DS_METHOD_DATA_FORM_FIELD_NAME
     */
    public static String getTHREE_DS_METHOD_DATA_FORM_FIELD_NAME() {
        return THREE_DS_METHOD_DATA_FORM_FIELD_NAME;
    }

    /**
     * @param aTHREE_DS_METHOD_DATA_FORM_FIELD_NAME the THREE_DS_METHOD_DATA_FORM_FIELD_NAME to set
     */
    public static void setTHREE_DS_METHOD_DATA_FORM_FIELD_NAME(String aTHREE_DS_METHOD_DATA_FORM_FIELD_NAME) {
        THREE_DS_METHOD_DATA_FORM_FIELD_NAME = aTHREE_DS_METHOD_DATA_FORM_FIELD_NAME;
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
     * @return the acsStartProtocolVersion
     */
    public String getAcsStartProtocolVersion() {
        return acsStartProtocolVersion;
    }

    /**
     * @param acsStartProtocolVersion the acsStartProtocolVersion to set
     */
    public void setAcsStartProtocolVersion(String acsStartProtocolVersion) {
        this.acsStartProtocolVersion = acsStartProtocolVersion;
    }

    /**
     * @return the acsEndProtocolVersion
     */
    public String getAcsEndProtocolVersion() {
        return acsEndProtocolVersion;
    }

    /**
     * @param acsEndProtocolVersion the acsEndProtocolVersion to set
     */
    public void setAcsEndProtocolVersion(String acsEndProtocolVersion) {
        this.acsEndProtocolVersion = acsEndProtocolVersion;
    }

    /**
     * @return the dsStartProtocolVersion
     */
    public String getDsStartProtocolVersion() {
        return dsStartProtocolVersion;
    }

    /**
     * @param dsStartProtocolVersion the dsStartProtocolVersion to set
     */
    public void setDsStartProtocolVersion(String dsStartProtocolVersion) {
        this.dsStartProtocolVersion = dsStartProtocolVersion;
    }

    /**
     * @return the dsEndProtocolVersion
     */
    public String getDsEndProtocolVersion() {
        return dsEndProtocolVersion;
    }

    /**
     * @param dsEndProtocolVersion the dsEndProtocolVersion to set
     */
    public void setDsEndProtocolVersion(String dsEndProtocolVersion) {
        this.dsEndProtocolVersion = dsEndProtocolVersion;
    }

    /**
     * @return the highestCommonSupportedProtocolVersion
     */
    public String getHighestCommonSupportedProtocolVersion() {
        return highestCommonSupportedProtocolVersion;
    }

    /**
     * @param highestCommonSupportedProtocolVersion the highestCommonSupportedProtocolVersion to set
     */
    public void setHighestCommonSupportedProtocolVersion(String highestCommonSupportedProtocolVersion) {
        this.highestCommonSupportedProtocolVersion = highestCommonSupportedProtocolVersion;
    }

    /**
     * @return the acsInfoInd
     */
    public List<String> getAcsInfoInd() {
        return acsInfoInd;
    }

    /**
     * @param acsInfoInd the acsInfoInd to set
     */
    public void setAcsInfoInd(List<String> acsInfoInd) {
        this.acsInfoInd = acsInfoInd;
    }

    /**
     * @return the threeDSMethodURL
     */
    public URL getThreeDSMethodURL() {
        return threeDSMethodURL;
    }

    /**
     * @param threeDSMethodURL the threeDSMethodURL to set
     */
    public void setThreeDSMethodURL(URL threeDSMethodURL) {
        this.threeDSMethodURL = threeDSMethodURL;
    }

    /**
     * @return the threeDSMethodDataForm
     */
    public Map<String, String> getThreeDSMethodDataForm() {
        return threeDSMethodDataForm;
    }

    /**
     * @param threeDSMethodDataForm the threeDSMethodDataForm to set
     */
    public void setThreeDSMethodDataForm(Map<String, String> threeDSMethodDataForm) {
        this.threeDSMethodDataForm = threeDSMethodDataForm;
    }

    /**
     * @return the threeDSMethodData
     */
    public ThreeDSMethodData getThreeDSMethodData() {
        return threeDSMethodData;
    }

    /**
     * @param threeDSMethodData the threeDSMethodData to set
     */
    public void setThreeDSMethodData(ThreeDSMethodData threeDSMethodData) {
        this.threeDSMethodData = threeDSMethodData;
    }

    /**
     * @return the errorDetails
     */
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    /**
     * @param errorDetails the errorDetails to set
     */
    public void setErrorDetails(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    /**
     * @return the isCardFoundIn2xRanges
     */
    public Boolean getIsCardFoundIn2xRanges() {
        return isCardFoundIn2xRanges;
    }

    /**
     * @param isCardFoundIn2xRanges the isCardFoundIn2xRanges to set
     */
    public void setIsCardFoundIn2xRanges(Boolean isCardFoundIn2xRanges) {
        this.isCardFoundIn2xRanges = isCardFoundIn2xRanges;
    }
  /**
   * Boolean indicating whether a card is found within the card ranges. Present only for API v2.
   */
  //@JsonInclude(value = JsonInclude.Include.NON_NULL)
}