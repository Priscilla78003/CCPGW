//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.26 at 04:22:56 PM AEST 
//


package com.truteq.ccpgw.payment.gateway.api.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="T3DS_Result" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RedirectHTML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="CAVV" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="CAVV_ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="PA_Request" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="IssuerURL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="TermURL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="ECI" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="MD" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="PA_ResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="VE_ResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="AuthenticationResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="DirectoryServerTrxID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="ThreeDSServerTransID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="LiabilityShiftInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RedirectURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardReaderResult" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Tokens" type="{}TokensType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ListOfReferenceNbrs" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ReferenceNbr" type="{}ReferenceNbrType" maxOccurs="99"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ResponseCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ApprovalCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CVC_ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AVS_ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MandateID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DebitCard" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "t3DSResult",
    "redirectURL",
    "cardReaderResult",
    "listOfReferenceNbrs"
})
@XmlRootElement(name = "AuthRS")
public class AuthRS {

    @XmlElement(name = "T3DS_Result")
    protected AuthRS.T3DSResult t3DSResult;
    @XmlElement(name = "RedirectURL")
    protected String redirectURL;
    @XmlElement(name = "CardReaderResult")
    protected AuthRS.CardReaderResult cardReaderResult;
    @XmlElement(name = "ListOfReferenceNbrs")
    protected AuthRS.ListOfReferenceNbrs listOfReferenceNbrs;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "ResponseCode", required = true)
    protected String responseCode;
    @XmlAttribute(name = "Description")
    protected String description;
    @XmlAttribute(name = "ApprovalCode")
    protected String approvalCode;
    @XmlAttribute(name = "CVC_ResultCode")
    protected String cvcResultCode;
    @XmlAttribute(name = "AVS_ResultCode")
    protected String avsResultCode;
    @XmlAttribute(name = "OrderNumber")
    protected String orderNumber;
    @XmlAttribute(name = "TransactionID")
    protected String transactionID;
    @XmlAttribute(name = "MandateID")
    protected String mandateID;
    @XmlAttribute(name = "DebitCard")
    protected Boolean debitCard;
    @XmlAttribute(name = "SessionID")
    private String sessionid;

    /**
     * Gets the value of the t3DSResult property.
     * 
     * @return
     *     possible object is
     *     {@link AuthRS.T3DSResult }
     *     
     */
    public AuthRS.T3DSResult getT3DSResult() {
        return t3DSResult;
    }

    /**
     * Sets the value of the t3DSResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthRS.T3DSResult }
     *     
     */
    public void setT3DSResult(AuthRS.T3DSResult value) {
        this.t3DSResult = value;
    }

    /**
     * Gets the value of the redirectURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedirectURL() {
        return redirectURL;
    }

    /**
     * Sets the value of the redirectURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedirectURL(String value) {
        this.redirectURL = value;
    }

    /**
     * Gets the value of the cardReaderResult property.
     * 
     * @return
     *     possible object is
     *     {@link AuthRS.CardReaderResult }
     *     
     */
    public AuthRS.CardReaderResult getCardReaderResult() {
        return cardReaderResult;
    }

    /**
     * Sets the value of the cardReaderResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthRS.CardReaderResult }
     *     
     */
    public void setCardReaderResult(AuthRS.CardReaderResult value) {
        this.cardReaderResult = value;
    }

    /**
     * Gets the value of the listOfReferenceNbrs property.
     * 
     * @return
     *     possible object is
     *     {@link AuthRS.ListOfReferenceNbrs }
     *     
     */
    public AuthRS.ListOfReferenceNbrs getListOfReferenceNbrs() {
        return listOfReferenceNbrs;
    }

    /**
     * Sets the value of the listOfReferenceNbrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthRS.ListOfReferenceNbrs }
     *     
     */
    public void setListOfReferenceNbrs(AuthRS.ListOfReferenceNbrs value) {
        this.listOfReferenceNbrs = value;
    }

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
     * Gets the value of the responseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseCode(String value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the approvalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovalCode() {
        return approvalCode;
    }

    /**
     * Sets the value of the approvalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovalCode(String value) {
        this.approvalCode = value;
    }

    /**
     * Gets the value of the cvcResultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCVCResultCode() {
        return cvcResultCode;
    }

    /**
     * Sets the value of the cvcResultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCVCResultCode(String value) {
        this.cvcResultCode = value;
    }

    /**
     * Gets the value of the avsResultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAVSResultCode() {
        return avsResultCode;
    }

    /**
     * Sets the value of the avsResultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAVSResultCode(String value) {
        this.avsResultCode = value;
    }

    /**
     * Gets the value of the orderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
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
     * Gets the value of the debitCard property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDebitCard() {
        return debitCard;
    }

    /**
     * Sets the value of the debitCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDebitCard(Boolean value) {
        this.debitCard = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Tokens" type="{}TokensType" minOccurs="0"/>
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
        "tokens"
    })
    public static class CardReaderResult {

        @XmlElement(name = "Tokens")
        protected TokensType tokens;

        /**
         * Gets the value of the tokens property.
         * 
         * @return
         *     possible object is
         *     {@link TokensType }
         *     
         */
        public TokensType getTokens() {
            return tokens;
        }

        /**
         * Sets the value of the tokens property.
         * 
         * @param value
         *     allowed object is
         *     {@link TokensType }
         *     
         */
        public void setTokens(TokensType value) {
            this.tokens = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ReferenceNbr" type="{}ReferenceNbrType" maxOccurs="99"/>
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
        "referenceNbr"
    })
    public static class ListOfReferenceNbrs {

        @XmlElement(name = "ReferenceNbr", required = true)
        protected List<ReferenceNbrType> referenceNbr;

        /**
         * Gets the value of the referenceNbr property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the referenceNbr property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReferenceNbr().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReferenceNbrType }
         * 
         * 
         */
        public List<ReferenceNbrType> getReferenceNbr() {
            if (referenceNbr == null) {
                referenceNbr = new ArrayList<ReferenceNbrType>();
            }
            return this.referenceNbr;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="RedirectHTML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="CAVV" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="CAVV_ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="PA_Request" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="IssuerURL" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="TermURL" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="ECI" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="MD" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="PA_ResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="VE_ResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="AuthenticationResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="DirectoryServerTrxID" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="ThreeDSServerTransID" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="LiabilityShiftInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "redirectHTML"
    })
    public static class T3DSResult {

        @XmlElement(name = "RedirectHTML")
        protected String redirectHTML;
        @XmlAttribute(name = "CAVV")
        protected String cavv;
        @XmlAttribute(name = "CAVV_ResultCode")
        protected String cavvResultCode;
        @XmlAttribute(name = "PA_Request")
        protected String paRequest;
        @XmlAttribute(name = "IssuerURL")
        protected String issuerURL;
        @XmlAttribute(name = "TermURL")
        protected String termURL;
        @XmlAttribute(name = "ECI")
        protected String eci;
        @XmlAttribute(name = "MD")
        protected String md;
        @XmlAttribute(name = "PA_ResponseCode")
        protected String paResponseCode;
        @XmlAttribute(name = "VE_ResponseCode")
        protected String veResponseCode;
        @XmlAttribute(name = "AuthenticationResponseCode")
        protected String authenticationResponseCode;
        @XmlAttribute(name = "DirectoryServerTrxID")
        protected String directoryServerTrxID;
        @XmlAttribute(name = "ThreeDSServerTransID")
        protected String threeDSServerTransID;
        @XmlAttribute(name = "LiabilityShiftInd")
        protected Boolean liabilityShiftInd;
        @XmlAttribute(name = "Version")
        protected String version;

        /**
         * Gets the value of the redirectHTML property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRedirectHTML() {
            return redirectHTML;
        }

        /**
         * Sets the value of the redirectHTML property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRedirectHTML(String value) {
            this.redirectHTML = value;
        }

        /**
         * Gets the value of the cavv property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCAVV() {
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
        public void setCAVV(String value) {
            this.cavv = value;
        }

        /**
         * Gets the value of the cavvResultCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCAVVResultCode() {
            return cavvResultCode;
        }

        /**
         * Sets the value of the cavvResultCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCAVVResultCode(String value) {
            this.cavvResultCode = value;
        }

        /**
         * Gets the value of the paRequest property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPARequest() {
            return paRequest;
        }

        /**
         * Sets the value of the paRequest property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPARequest(String value) {
            this.paRequest = value;
        }

        /**
         * Gets the value of the issuerURL property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIssuerURL() {
            return issuerURL;
        }

        /**
         * Sets the value of the issuerURL property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIssuerURL(String value) {
            this.issuerURL = value;
        }

        /**
         * Gets the value of the termURL property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTermURL() {
            return termURL;
        }

        /**
         * Sets the value of the termURL property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTermURL(String value) {
            this.termURL = value;
        }

        /**
         * Gets the value of the eci property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getECI() {
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
        public void setECI(String value) {
            this.eci = value;
        }

        /**
         * Gets the value of the md property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMD() {
            return md;
        }

        /**
         * Sets the value of the md property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMD(String value) {
            this.md = value;
        }

        /**
         * Gets the value of the paResponseCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPAResponseCode() {
            return paResponseCode;
        }

        /**
         * Sets the value of the paResponseCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPAResponseCode(String value) {
            this.paResponseCode = value;
        }

        /**
         * Gets the value of the veResponseCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVEResponseCode() {
            return veResponseCode;
        }

        /**
         * Sets the value of the veResponseCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVEResponseCode(String value) {
            this.veResponseCode = value;
        }

        /**
         * Gets the value of the authenticationResponseCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthenticationResponseCode() {
            return authenticationResponseCode;
        }

        /**
         * Sets the value of the authenticationResponseCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthenticationResponseCode(String value) {
            this.authenticationResponseCode = value;
        }

        /**
         * Gets the value of the directoryServerTrxID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDirectoryServerTrxID() {
            return directoryServerTrxID;
        }

        /**
         * Sets the value of the directoryServerTrxID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDirectoryServerTrxID(String value) {
            this.directoryServerTrxID = value;
        }

        /**
         * Gets the value of the threeDSServerTransID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getThreeDSServerTransID() {
            return threeDSServerTransID;
        }

        /**
         * Sets the value of the threeDSServerTransID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setThreeDSServerTransID(String value) {
            this.threeDSServerTransID = value;
        }

        /**
         * Gets the value of the liabilityShiftInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isLiabilityShiftInd() {
            return liabilityShiftInd;
        }

        /**
         * Sets the value of the liabilityShiftInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setLiabilityShiftInd(Boolean value) {
            this.liabilityShiftInd = value;
        }

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

    }

    /**
     * @return the sessionid
     */
    public String getSessionid() {
        return sessionid;
    }

    /**
     * @param sessionid the sessionid to set
     */
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

}
