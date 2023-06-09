//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.26 at 04:22:56 PM AEST 
//


package com.truteq.ccpgw.payment.gateway.api.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Issuer" type="{}IssuerType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{}UserNameGroup"/>
 *       &lt;attribute name="AccountType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AccountNbr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AccountHolderName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ExpireDate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BusinessIdentifierCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MandateID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SortCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CountryCode" type="{}ISO3166" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountDetailType", propOrder = {
    "issuer"
})
public class AccountDetailType {

    @XmlElement(name = "Issuer")
    protected IssuerType issuer;
    @XmlAttribute(name = "AccountType")
    protected String accountType;
    @XmlAttribute(name = "AccountNbr")
    protected String accountNbr;
    @XmlAttribute(name = "AccountHolderName")
    protected String accountHolderName;
    @XmlAttribute(name = "ExpireDate")
    protected String expireDate;
    @XmlAttribute(name = "BusinessIdentifierCode")
    protected String businessIdentifierCode;
    @XmlAttribute(name = "MandateID")
    protected String mandateID;
    @XmlAttribute(name = "SortCode")
    protected String sortCode;
    @XmlAttribute(name = "CountryCode")
    protected String countryCode;
    @XmlAttribute(name = "UserName")
    protected String userName;
    @XmlAttribute(name = "Password")
    protected String password;

    /**
     * Gets the value of the issuer property.
     * 
     * @return
     *     possible object is
     *     {@link IssuerType }
     *     
     */
    public IssuerType getIssuer() {
        return issuer;
    }

    /**
     * Sets the value of the issuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link IssuerType }
     *     
     */
    public void setIssuer(IssuerType value) {
        this.issuer = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the accountNbr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNbr() {
        return accountNbr;
    }

    /**
     * Sets the value of the accountNbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNbr(String value) {
        this.accountNbr = value;
    }

    /**
     * Gets the value of the accountHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Sets the value of the accountHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountHolderName(String value) {
        this.accountHolderName = value;
    }

    /**
     * Gets the value of the expireDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the value of the expireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpireDate(String value) {
        this.expireDate = value;
    }

    /**
     * Gets the value of the businessIdentifierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessIdentifierCode() {
        return businessIdentifierCode;
    }

    /**
     * Sets the value of the businessIdentifierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessIdentifierCode(String value) {
        this.businessIdentifierCode = value;
    }

    /**
     * Gets the value of the mandateID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMandateID() {
        return mandateID;
    }

    /**
     * Sets the value of the mandateID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMandateID(String value) {
        this.mandateID = value;
    }

    /**
     * Gets the value of the sortCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortCode() {
        return sortCode;
    }

    /**
     * Sets the value of the sortCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortCode(String value) {
        this.sortCode = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

}
