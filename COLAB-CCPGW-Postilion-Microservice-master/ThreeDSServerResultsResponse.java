/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.results.response;

import com.google.gson.Gson;
import com.truteq.ccpgw.netcetera.model.ErrorDetails;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ThreeDSServerResultsResponse {
  /**
   * Universally unique transaction identifier assigned by the 3DS Server to identify a single transaction. It has
   * the same value as the authentication request. This value has 36 characters in a format defined in IETF RFC 4122.
   */
  private String threeDSServerTransID;
  /**
   * Indicates whether a transaction qualifies as an authenticated transaction.
   *
   * The accepted values are:
   *
   *  Y -> Authentication / Account verification successful
   *  N -> Not authenticated / Account not verified; Transaction denied
   *  U -> Authentication / Account verification could not be performed; technical or other problem
   *  C -> In order to complete the authentication, a challenge is required
   *  R -> Authentication / Account verification Rejected. Issuer is rejecting authentication/verification
   *       and request that authorization not be attempted
   *  A -> Attempts processing performed; Not authenticated / verified, but a proof of attempt
   *       authentication / verification is provided
   *
   *  The following values are also accepted if the 3DS Server has initiated authentication with EMV 3DS 2.2.0 version
   *  or greater:
   *
   *  D -> In order to complete the authentication, a challenge is required. Decoupled Authentication confirmed.
   *  I -> Informational Only; 3DS Requestor challenge preference acknowledged.
   *
   */
  private String transStatus;
  /**
   * Payment System-specific value provided as part of the ACS registration for each supported DS. Authentication Value
   * may be used to provide proof of authentication.
   */
  private String authenticationValue;
  /**
   * Payment System-specific value provided by the ACS to indicate the results of the attempt to authenticate the
   * Cardholder.
   */
  private  String eci;
  /**
   * The received Results Request from the Directory Server.
   */
  private ResultsRequest resultsRequest;
  /**
   * The sent Results Response to the Directory Server.
   */
  private ResultsResponse resultsResponse;
  /**
   * Object containing error details if any errors occurred.
   */
  private ErrorDetails errorDetails;
  
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
     * @return the transStatus
     */
    public String getTransStatus() {
        return transStatus;
    }

    /**
     * @param transStatus the transStatus to set
     */
    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    /**
     * @return the authenticationValue
     */
    public String getAuthenticationValue() {
        return authenticationValue;
    }

    /**
     * @param authenticationValue the authenticationValue to set
     */
    public void setAuthenticationValue(String authenticationValue) {
        this.authenticationValue = authenticationValue;
    }

    /**
     * @return the eci
     */
    public String getEci() {
        return eci;
    }

    /**
     * @param eci the eci to set
     */
    public void setEci(String eci) {
        this.eci = eci;
    }

    /**
     * @return the resultsRequest
     */
    public ResultsRequest getResultsRequest() {
        return resultsRequest;
    }

    /**
     * @param resultsRequest the resultsRequest to set
     */
    public void setResultsRequest(ResultsRequest resultsRequest) {
        this.resultsRequest = resultsRequest;
    }

    /**
     * @return the resultsResponse
     */
    public ResultsResponse getResultsResponse() {
        return resultsResponse;
    }

    /**
     * @param resultsResponse the resultsResponse to set
     */
    public void setResultsResponse(ResultsResponse resultsResponse) {
        this.resultsResponse = resultsResponse;
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
  
}