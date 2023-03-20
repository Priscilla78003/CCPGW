/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.versioning;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ThreeDSServerVersioningV3Response {

  /**
   * Constant defining the name of the field in the 3DS Method form containing the encoded 3DS Method Data.
   */
  private static String THREE_DS_METHOD_DATA_FORM_FIELD_NAME = "threeDSMethodData";

  /**
   * Universally unique transaction identifier assigned by the 3DS Server to identify a single transaction.
   */
  private String threeDSServerTransID;

  /**
   * The Id of the scheme.
   */
  private String schemeId;

  /**
   * Array of objects containing the list of protocol versions supported by the ACS for the card range, with their
   * associated ACS Information Indicator, the 3DS Method URL and the list of Supported Message Extension.
   * • Version
   * • ACS Information Indicator
   * • 3DS Method URL
   * • Supported Message Extension
   */
  private List<AcsProtocolVersionData> acsProtocolVersions;

  /**
   * Contains the list of active protocol versions supported by the DS.
   */
  private List<String> dsProtocolVersions;

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
   * Boolean indicating whether a card is found within the card ranges.
   */
  private Boolean isCardFoundIn2xRanges;

  /**
   * Registered Application Provider Identifier (RID) that is unique to the Payment System. RIDs are defined by the
   * ISO 7816-5 standard. The Directory Server ID is a hex value encoded as a 10-character text.
   * For example, 0x'A000000003' is encoded as 'A000000003'.
   */
  private String directoryServerID;
  
  
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
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
     * @return the acsProtocolVersions
     */
    public List<AcsProtocolVersionData> getAcsProtocolVersions() {
        return acsProtocolVersions;
    }

    /**
     * @param acsProtocolVersions the acsProtocolVersions to set
     */
    public void setAcsProtocolVersions(List<AcsProtocolVersionData> acsProtocolVersions) {
        this.acsProtocolVersions = acsProtocolVersions;
    }

    /**
     * @return the dsProtocolVersions
     */
    public List<String> getDsProtocolVersions() {
        return dsProtocolVersions;
    }

    /**
     * @param dsProtocolVersions the dsProtocolVersions to set
     */
    public void setDsProtocolVersions(List<String> dsProtocolVersions) {
        this.dsProtocolVersions = dsProtocolVersions;
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
     * @return the directoryServerID
     */
    public String getDirectoryServerID() {
        return directoryServerID;
    }

    /**
     * @param directoryServerID the directoryServerID to set
     */
    public void setDirectoryServerID(String directoryServerID) {
        this.directoryServerID = directoryServerID;
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

}
