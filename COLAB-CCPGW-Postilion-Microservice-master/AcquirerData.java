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
public class AcquirerData {
 
  /**
   * Acquiring institution identification code as assigned by the DS receiving the AReq message. This field is limited
   * to 11 characters. This field can be omitted if it there is a MerchantAcquirer already configured for 3DS Server,
   * referenced by the acquirerMerchantId.
   *
   * This field is required if no MerchantAcquirer is present for the acquirer BIN in the 3DS Server configuration and
   * for  for requests where messageCategory = 01 (PA). For requests where messageCategory=02 (NPA) it is optional.
   */
  private String acquirerBin;
  /**
   * Acquirer-assigned Merchant identifier. This may be the same value that is used in authorization requests sent on
   * behalf of the 3DS Requestor and is represented in ISO 8583 formatting requirements. The field is limited to
   * maximum 35 characters. Individual Directory Servers may impose specific format and character requirements on
   * the contents of this field.
   *
   * This field will be used to identify the Directory Server where the AReq will be sent and the
   * acquirerBin from the 3DS Server configuration. If no MerchantAcquirer configuration is present in the 3DS Server,
   * the DirectoryServer information will be resolved from the scheme to which the cardholder account belongs to.
   *
   * This field is required only if the merchantConfigurationId is not provided in the request or
   * the schemeId is not provided in the request.
   */
  private String acquirerMerchantId;

    /**
     * @return the acquirerBin
     */
    public String getAcquirerBin() {
        return acquirerBin;
    }

    /**
     * @param acquirerBin the acquirerBin to set
     */
    public void setAcquirerBin(String acquirerBin) {
        this.acquirerBin = acquirerBin;
    }

    /**
     * @return the acquirerMerchantId
     */
    public String getAcquirerMerchantId() {
        return acquirerMerchantId;
    }

    /**
     * @param acquirerMerchantId the acquirerMerchantId to set
     */
    public void setAcquirerMerchantId(String acquirerMerchantId) {
        this.acquirerMerchantId = acquirerMerchantId;
    }
}