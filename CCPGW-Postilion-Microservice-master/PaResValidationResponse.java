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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *           
 *             The PaResValidationResponse returned to the Merchant after Payer Authentication Process.
 *           
 *         
 * 
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://mpi.netcetera.com}SessionId"/>
 *         &lt;element name="cavv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cavvHex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eci" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xidHex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ThreeDSecurePARes" type="{}ThreeDSecureType" minOccurs="0"/>
 *         &lt;element name="Error" type="{http://mpi.netcetera.com/common}ErrorDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sessionId",
    "cavv",
    "cavvHex",
    "eci",
    "transactionStatus",
    "xid",
    "xidHex",
    "threeDSecurePARes",
    "error"
})
@XmlRootElement(name = "PaResValidationResponse", namespace = "http://mpi.netcetera.com")
public class PaResValidationResponse {

    @XmlElement(required = true)
    protected String sessionId;
    protected String cavv;
    protected String cavvHex;
    protected String eci;
    protected String transactionStatus;
    protected String xid;
    protected String xidHex;
    @XmlElement(name = "ThreeDSecurePARes")
    protected ThreeDSecureType ThreeDSecurePARes;
    @XmlElement(name = "Error")
    protected ErrorDetails Error;

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the cavv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCavv() {
        return cavv;
    }

    /**
     * Sets the value of the cavv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCavv(String value) {
        this.cavv = value;
    }

    /**
     * Gets the value of the cavvHex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCavvHex() {
        return cavvHex;
    }

    /**
     * Sets the value of the cavvHex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCavvHex(String value) {
        this.cavvHex = value;
    }

    /**
     * Gets the value of the eci property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEci() {
        return eci;
    }

    /**
     * Sets the value of the eci property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEci(String value) {
        this.eci = value;
    }

    /**
     * Gets the value of the transactionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * Sets the value of the transactionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionStatus(String value) {
        this.transactionStatus = value;
    }

    /**
     * Gets the value of the xid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXid() {
        return xid;
    }

    /**
     * Sets the value of the xid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXid(String value) {
        this.xid = value;
    }

    /**
     * Gets the value of the xidHex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXidHex() {
        return xidHex;
    }

    /**
     * Sets the value of the xidHex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXidHex(String value) {
        this.xidHex = value;
    }

    /**
     * Gets the value of the threeDSecurePARes property.
     * 
     * @return
     *     possible object is
     *     {@link ThreeDSecureType }
     *     
     */
    public ThreeDSecureType getThreeDSecurePARes() {
        return ThreeDSecurePARes;
    }

    /**
     * Sets the value of the threeDSecurePARes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ThreeDSecureType }
     *     
     */
    public void setThreeDSecurePARes(ThreeDSecureType value) {
        this.ThreeDSecurePARes = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorDetails }
     *     
     */
    public ErrorDetails getError() {
        return Error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorDetails }
     *     
     */
    public void setError(ErrorDetails value) {
        this.Error = value;
    }

}
