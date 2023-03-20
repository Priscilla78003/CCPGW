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
public class BrowserData {
 
  /**
   * Exact content of the HTTP accept headers as sent to the 3DS Requestor from the Cardholder's browser.
   * This field is limited to maximum 2048 characters  and if the total length exceeds the limit, the 3DS Server
   * truncates the excess portion.
   *
   * This field is required for requests where deviceChannel=02 (BRW).
   */
  private String browserAcceptHeader;
  /**
   * IP address of the browser as returned by the HTTP headers to the 3DS Requestor. The field is limited to maximum 45
   * characters and the accepted values are as following:
   *      - IPv4 address is represented in the dotted decimal format of 4 sets of decimal numbers separated by dots. The
   *        decimal number in each and every set is in the range 0 - 255. Example: 1.12.123.255
   *      - IPv6 adress is represented as eight groups of four hexadecimal digits, each group representing 16 bits (two
   *        octets). The groups are separated by colons (:). Example: 2011:0db8:85a3:0101:0101:8a2e:0370:7334
   *
   * This field is required for requests when deviceChannel = 02 (BRW) where regionally acceptable.
   */
  private String browserIP;
  /**
   * Boolean that represents the ability of the cardholder browser to execute Java. Value is returned from the
   * navigator.javaEnabled property.
   *
   * Depending on the message version, the field is required for requests:
   * - with message version = 2.1.0 and deviceChannel = 02 (BRW).
   * - with message version = 2.2.0 and deviceChannel = 02 (BRW) and browserJavascriptEnabled = true.
   */
  private Boolean browserJavaEnabled;
  /**
   * Value representing the browser language as defined in IETF BCP47. The value is limited to 1-8 characters. If the
   * value exceeds 8 characters, it will be truncated to a semantically valid value, if possible. The value is
   * returned from navigator.language property.
   *
   * This field is required for requests where deviceChannel = 02 (BRW).
   */
  private String browserLanguage;
  /**
   * Value representing the bit depth of the colour palette for displaying images, in bits per pixel. Obtained from
   * Cardholder browser using the screen.colorDepth property. The field is limited to 1-2 characters.
   *
   * Accepted values are:
   *  1 -> 1 bit
   *  4 -> 4 bits
   *  8 -> 8 bits
   *  15 -> 15 bits
   *  16 -> 16 bits
   *  24 -> 24 bits
   *  32 -> 32 bits
   *  48 -> 48 bits
   *  If the value is not in the accepted values, it will be resolved to the first accepted value lower from the one
   *  provided.
   *
   * Depending on the message version, the field is required for requests:
   * - with message version = 2.1.0 and deviceChannel = 02 (BRW).
   * - with message version = 2.2.0 and deviceChannel = 02 (BRW) and browserJavascriptEnabled = true.
   */
  private String browserColorDepth;
  /**
   * Total height of the Cardholder's screen in pixels. Value is returned from the screen.height property. The value is
   * limited to 1-6 characters.
   *
   * Depending on the message version, the field is required for requests:
   * - with message version = 2.1.0 and deviceChannel = 02 (BRW).
   * - with message version = 2.2.0 and deviceChannel = 02 (BRW) and browserJavascriptEnabled = true.
   */
  private Integer browserScreenHeight;
  /**
   * Total width of the Cardholder's screen in pixels. Value is returned from the screen.width property. The value is
   * limited to 1-6 characters.
   *
   * Depending on the message version, the field is required for requests:
   * - with message version = 2.1.0 and deviceChannel = 02 (BRW).
   * - with message version = 2.2.0 and deviceChannel = 02 (BRW) and browserJavascriptEnabled = true.
   */
  private Integer browserScreenWidth;
  /**
   * Time difference between UTC time and the Cardholder browser local time, in minutes. The field is limited to 1-5
   * characters where the vauyes is returned from the getTimezoneOffset() method.
   *
   * Depending on the message version, the field is required for requests:
   * - with message version = 2.1.0 and deviceChannel = 02 (BRW).
   * - with message version = 2.2.0 and deviceChannel = 02 (BRW) and browserJavascriptEnabled = true.
   */
  private Integer browserTZ;
  /**
   * Exact content of the HTTP user-agent header. The field is limited to maximum 2048 caracters. If the total length of
   * the User-Agent sent by the browser exceeds 2048 characters, the 3DS Server truncates the excess portion.
   *
   * This field is required for requests where deviceChannel = 02 (BRW).
   */
  private String browserUserAgent;
  /**
   * Dimensions of the challenge window that has been displayed to the Cardholder. The ACS shall reply with content
   * that is formatted to appropriately render in this window to provide the best possible user experience.
   *
   * Preconfigured sizes are width X height in pixels of the window displayed in the Cardholder browser window. This is
   * used only to prepare the CReq request and it is not part of the AReq flow. If not present it will be omitted.
   *
   * However, when sending the Challenge Request, this field is required when deviceChannel = 02 (BRW).
   *
   * Accepted values are:
   *  01 -> 250 x 400
   *  02 -> 390 x 400
   *  03 -> 500 x 600
   *  04 -> 600 x 400
   *  05 -> Full screen
   */
  private String challengeWindowSize;
  /**
   * Boolean that represents the ability of the cardholder browser to execute JavaScript.
   *
   * This field is required for requests where deviceChannel = 02 (BRW).
   * Available for supporting EMV 3DS 2.2.0 and later versions.
   */
  private Boolean browserJavascriptEnabled;

    /**
     * @return the browserAcceptHeader
     */
    public String getBrowserAcceptHeader() {
        return browserAcceptHeader;
    }

    /**
     * @param browserAcceptHeader the browserAcceptHeader to set
     */
    public void setBrowserAcceptHeader(String browserAcceptHeader) {
        this.browserAcceptHeader = browserAcceptHeader;
    }

    /**
     * @return the browserIP
     */
    public String getBrowserIP() {
        return browserIP;
    }

    /**
     * @param browserIP the browserIP to set
     */
    public void setBrowserIP(String browserIP) {
        this.browserIP = browserIP;
    }

    /**
     * @return the browserJavaEnabled
     */
    public Boolean getBrowserJavaEnabled() {
        return browserJavaEnabled;
    }

    /**
     * @param browserJavaEnabled the browserJavaEnabled to set
     */
    public void setBrowserJavaEnabled(Boolean browserJavaEnabled) {
        this.browserJavaEnabled = browserJavaEnabled;
    }

    /**
     * @return the browserLanguage
     */
    public String getBrowserLanguage() {
        return browserLanguage;
    }

    /**
     * @param browserLanguage the browserLanguage to set
     */
    public void setBrowserLanguage(String browserLanguage) {
        this.browserLanguage = browserLanguage;
    }

    /**
     * @return the browserColorDepth
     */
    public String getBrowserColorDepth() {
        return browserColorDepth;
    }

    /**
     * @param browserColorDepth the browserColorDepth to set
     */
    public void setBrowserColorDepth(String browserColorDepth) {
        this.browserColorDepth = browserColorDepth;
    }

    /**
     * @return the browserScreenHeight
     */
    public Integer getBrowserScreenHeight() {
        return browserScreenHeight;
    }

    /**
     * @param browserScreenHeight the browserScreenHeight to set
     */
    public void setBrowserScreenHeight(Integer browserScreenHeight) {
        this.browserScreenHeight = browserScreenHeight;
    }

    /**
     * @return the browserScreenWidth
     */
    public Integer getBrowserScreenWidth() {
        return browserScreenWidth;
    }

    /**
     * @param browserScreenWidth the browserScreenWidth to set
     */
    public void setBrowserScreenWidth(Integer browserScreenWidth) {
        this.browserScreenWidth = browserScreenWidth;
    }

    /**
     * @return the browserTZ
     */
    public Integer getBrowserTZ() {
        return browserTZ;
    }

    /**
     * @param browserTZ the browserTZ to set
     */
    public void setBrowserTZ(Integer browserTZ) {
        this.browserTZ = browserTZ;
    }

    /**
     * @return the browserUserAgent
     */
    public String getBrowserUserAgent() {
        return browserUserAgent;
    }

    /**
     * @param browserUserAgent the browserUserAgent to set
     */
    public void setBrowserUserAgent(String browserUserAgent) {
        this.browserUserAgent = browserUserAgent;
    }

    /**
     * @return the challengeWindowSize
     */
    public String getChallengeWindowSize() {
        return challengeWindowSize;
    }

    /**
     * @param challengeWindowSize the challengeWindowSize to set
     */
    public void setChallengeWindowSize(String challengeWindowSize) {
        this.challengeWindowSize = challengeWindowSize;
    }

    /**
     * @return the browserJavascriptEnabled
     */
    public Boolean getBrowserJavascriptEnabled() {
        return browserJavascriptEnabled;
    }

    /**
     * @param browserJavascriptEnabled the browserJavascriptEnabled to set
     */
    public void setBrowserJavascriptEnabled(Boolean browserJavascriptEnabled) {
        this.browserJavascriptEnabled = browserJavascriptEnabled;
    }
}