//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 01:49:42 PM AEST 
//


package com.truteq.ccpgw.netcetera.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *           
 *             Verify Enrollment Request.
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
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Merchant" type="{}Merchant"/>
 *         &lt;element name="Browser" type="{}Browser" minOccurs="0"/>
 *         &lt;element name="Extension" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
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
    "version",
    "pan",
    "merchant",
    "browser",
    "extension"
})
@XmlRootElement(name = "VEReq")
public class VEReq {

    @XmlElement(required = true)
    protected String version;
    @XmlElement(required = true)
    protected String pan;
    @XmlElement(name = "Merchant", required = true)
    protected Merchant2 Merchant;
    @XmlElement(name = "Browser")
    protected Browser2 Browser;
    @XmlElement(name = "Extension")
    protected List<Object> Extension;

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
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
     * Gets the value of the merchant property.
     * 
     * @return
     *     possible object is
     *     {@link Merchant2 }
     *     
     */
    public Merchant2 getMerchant() {
        return Merchant;
    }

    /**
     * Sets the value of the merchant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Merchant2 }
     *     
     */
    public void setMerchant(Merchant2 value) {
        this.Merchant = value;
    }

    /**
     * Gets the value of the browser property.
     * 
     * @return
     *     possible object is
     *     {@link Browser2 }
     *     
     */
    public Browser2 getBrowser() {
        return Browser;
    }

    /**
     * Sets the value of the browser property.
     * 
     * @param value
     *     allowed object is
     *     {@link Browser2 }
     *     
     */
    public void setBrowser(Browser2 value) {
        this.Browser = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getExtension() {
        if (Extension == null) {
            Extension = new ArrayList<Object>();
        }
        return this.Extension;
    }

}