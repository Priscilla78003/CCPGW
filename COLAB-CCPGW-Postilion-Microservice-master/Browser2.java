//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 01:49:42 PM AEST 
//


package com.truteq.ccpgw.netcetera.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         
 *           Browser information.
 *         
 *       
 * 
 * <p>Java class for Browser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Browser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deviceCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Browser", propOrder = {
    "deviceCategory",
    "accept",
    "userAgent"
})
public class Browser2 {

    protected String deviceCategory;
    protected String accept;
    protected String userAgent;

    /**
     * Gets the value of the deviceCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceCategory() {
        return deviceCategory;
    }

    /**
     * Sets the value of the deviceCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceCategory(String value) {
        this.deviceCategory = value;
    }

    /**
     * Gets the value of the accept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccept() {
        return accept;
    }

    /**
     * Sets the value of the accept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccept(String value) {
        this.accept = value;
    }

    /**
     * Gets the value of the userAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the value of the userAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAgent(String value) {
        this.userAgent = value;
    }

}