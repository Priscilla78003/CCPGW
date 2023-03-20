/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

import com.google.gson.Gson;
import com.truteq.ccpgw.netcetera.model.ErrorDetails;
import java.net.URL;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ThreeDSServerAuthenticationResponse {
 
  /**
   * Universally unique transaction identifier assigned by the 3DS Server to identify a single transaction. It has
   * the same value as the corresponding received authentication request. This value has 36 characters in a format
   * defined in IETF RFC 4122.
   */
  private String threeDSServerTransID;
  /**
   * Fully qualified URL of the ACS in case the authentication response message indicates that further
   * Cardholder interaction is required to complete the authentication.
   */
  private URL acsURL;
  /**
   * Indicates whether a transaction qualifies as an authenticated transaction. The accepted values are:
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
  private String eci;
  /**
   * Indication of whether a challenge is required for the transaction to be authorised due to local/regional
   * mandates or other variable. The accepted values are:
   *
   *  Y -> Challenge is mandated
   *  N -> Challenge is not mandated
   */
  private String acsChallengeMandated;
  /**
   * The sent Authentication Request to the Directory Server.
   */
  private AuthenticationRequest authenticationRequest;
  /**
   * The received Authentication Response from the Directory Server.
   */
  private AuthenticationResponse authenticationResponse;
  /**
   * Date and time of the purchase, expressed in UTC. The field is limited to 14 characters, formatted as
   * YYYYMMDDHHMMSS.
   */

  private String purchaseDate;
  /**
   * Object containing error details if any errors occurred.
   */
  private ErrorDetails errorDetails;
  /**
   * Challenge Request object in case the authentication response message indicates that further Cardholder
   * interaction is required to complete the authentication.
   */
  private ChallengeRequest challengeRequest;
  /**
   * Base64-encoded Challenge Request object in case the authentication response message indicates that further
   * Cardholder interaction is required to complete the authentication.
   */
  private String base64EncodedChallengeRequest;

  
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
     * @return the acsURL
     */
    public URL getAcsURL() {
        return acsURL;
    }

    /**
     * @param acsURL the acsURL to set
     */
    public void setAcsURL(URL acsURL) {
        this.acsURL = acsURL;
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
     * @return the acsChallengeMandated
     */
    public String getAcsChallengeMandated() {
        return acsChallengeMandated;
    }

    /**
     * @param acsChallengeMandated the acsChallengeMandated to set
     */
    public void setAcsChallengeMandated(String acsChallengeMandated) {
        this.acsChallengeMandated = acsChallengeMandated;
    }

    /**
     * @return the authenticationRequest
     */
    public AuthenticationRequest getAuthenticationRequest() {
        return authenticationRequest;
    }

    /**
     * @param authenticationRequest the authenticationRequest to set
     */
    public void setAuthenticationRequest(AuthenticationRequest authenticationRequest) {
        this.authenticationRequest = authenticationRequest;
    }

    /**
     * @return the authenticationResponse
     */
    public AuthenticationResponse getAuthenticationResponse() {
        return authenticationResponse;
    }

    /**
     * @param authenticationResponse the authenticationResponse to set
     */
    public void setAuthenticationResponse(AuthenticationResponse authenticationResponse) {
        this.authenticationResponse = authenticationResponse;
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
     * @return the challengeRequest
     */
    public ChallengeRequest getChallengeRequest() {
        return challengeRequest;
    }

    /**
     * @param challengeRequest the challengeRequest to set
     */
    public void setChallengeRequest(ChallengeRequest challengeRequest) {
        this.challengeRequest = challengeRequest;
    }

    /**
     * @return the base64EncodedChallengeRequest
     */
    public String getBase64EncodedChallengeRequest() {
        return base64EncodedChallengeRequest;
    }

    /**
     * @param base64EncodedChallengeRequest the base64EncodedChallengeRequest to set
     */
    public void setBase64EncodedChallengeRequest(String base64EncodedChallengeRequest) {
        this.base64EncodedChallengeRequest = base64EncodedChallengeRequest;
    }
}