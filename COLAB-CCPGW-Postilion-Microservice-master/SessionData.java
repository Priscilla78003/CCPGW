//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 01:49:42 PM AEST 
//


package com.truteq.ccpgw.netcetera.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *          
 *           Element containing all the session data, needed by the MPI when the session is disabled.
 *           It is used in the validation of PARes. 
 *              
 *       
 * 
 * <p>Java class for SessionData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SessionData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ThreeDSecurePAReq" type="{}ThreeDSecureType" minOccurs="0"/>
 *         &lt;element name="paReqCreationTime" type="{http://mpi.netcetera.com}DateTime" minOccurs="0"/>
 *         &lt;element name="pan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scheme" type="{http://mpi.netcetera.com}SchemeId" minOccurs="0"/>
 *         &lt;element name="acsUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="masterCardTokenized" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SessionData", namespace = "http://mpi.netcetera.com", propOrder = {
    "threeDSecurePAReq",
    "paReqCreationTime",
    "pan",
    "scheme",
    "acsUrl",
    "masterCardTokenized"
})
public class SessionData {

    @XmlElement(name = "ThreeDSecurePAReq")
    protected ThreeDSecureType ThreeDSecurePAReq;
    protected String paReqCreationTime;
    protected String pan;
    protected String scheme;
    protected String acsUrl;
    protected String masterCardTokenized;

    /**
     * Gets the value of the threeDSecurePAReq property.
     * 
     * @return
     *     possible object is
     *     {@link ThreeDSecureType }
     *     
     */
    public ThreeDSecureType getThreeDSecurePAReq() {
        return ThreeDSecurePAReq;
    }

    /**
     * Sets the value of the threeDSecurePAReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link ThreeDSecureType }
     *     
     */
    public void setThreeDSecurePAReq(ThreeDSecureType value) {
        this.ThreeDSecurePAReq = value;
    }

    /**
     * Gets the value of the paReqCreationTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaReqCreationTime() {
        return paReqCreationTime;
    }

    /**
     * Sets the value of the paReqCreationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaReqCreationTime(String value) {
        this.paReqCreationTime = value;
    }

    /**
     * Gets the value of the pan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPan() {
        return pan;
    }

    /**
     * Sets the value of the pan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPan(String value) {
        this.pan = value;
    }

    /**
     * Gets the value of the scheme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * Sets the value of the scheme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheme(String value) {
        this.scheme = value;
    }

    /**
     * Gets the value of the acsUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcsUrl() {
        return acsUrl;
    }

    /**
     * Sets the value of the acsUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcsUrl(String value) {
        this.acsUrl = value;
    }

    /**
     * Gets the value of the masterCardTokenized property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterCardTokenized() {
        return masterCardTokenized;
    }

    /**
     * Sets the value of the masterCardTokenized property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterCardTokenized(String value) {
        this.masterCardTokenized = value;
    }

}
