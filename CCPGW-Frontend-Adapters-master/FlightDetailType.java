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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Flight detail
 * 
 * <p>Java class for FlightDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FlightDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AirlineCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperatingAirlineCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FlightNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClassOfService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DepartureInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="DepartureAirport" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="DepartureDateTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ArrivalInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="ArrivalAirport" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="ArrivalDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="FareBasisCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TicketDesignator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ReturnFlightInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ConnectingFlightInd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlightDetailType", propOrder = {
    "airlineCode",
    "operatingAirlineCode",
    "flightNumber",
    "classOfService",
    "departureInfo",
    "arrivalInfo"
})
public class FlightDetailType {

    @XmlElement(name = "AirlineCode")
    protected String airlineCode;
    @XmlElement(name = "OperatingAirlineCode")
    protected String operatingAirlineCode;
    @XmlElement(name = "FlightNumber")
    protected String flightNumber;
    @XmlElement(name = "ClassOfService")
    protected String classOfService;
    @XmlElement(name = "DepartureInfo")
    protected FlightDetailType.DepartureInfo departureInfo;
    @XmlElement(name = "ArrivalInfo")
    protected FlightDetailType.ArrivalInfo arrivalInfo;
    @XmlAttribute(name = "FareBasisCode")
    protected String fareBasisCode;
    @XmlAttribute(name = "TicketDesignator")
    protected String ticketDesignator;
    @XmlAttribute(name = "ReturnFlightInd")
    protected Boolean returnFlightInd;
    @XmlAttribute(name = "ConnectingFlightInd")
    protected String connectingFlightInd;

    /**
     * Gets the value of the airlineCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * Sets the value of the airlineCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirlineCode(String value) {
        this.airlineCode = value;
    }

    /**
     * Gets the value of the operatingAirlineCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingAirlineCode() {
        return operatingAirlineCode;
    }

    /**
     * Sets the value of the operatingAirlineCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingAirlineCode(String value) {
        this.operatingAirlineCode = value;
    }

    /**
     * Gets the value of the flightNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the value of the flightNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlightNumber(String value) {
        this.flightNumber = value;
    }

    /**
     * Gets the value of the classOfService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassOfService() {
        return classOfService;
    }

    /**
     * Sets the value of the classOfService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassOfService(String value) {
        this.classOfService = value;
    }

    /**
     * Gets the value of the departureInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FlightDetailType.DepartureInfo }
     *     
     */
    public FlightDetailType.DepartureInfo getDepartureInfo() {
        return departureInfo;
    }

    /**
     * Sets the value of the departureInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightDetailType.DepartureInfo }
     *     
     */
    public void setDepartureInfo(FlightDetailType.DepartureInfo value) {
        this.departureInfo = value;
    }

    /**
     * Gets the value of the arrivalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FlightDetailType.ArrivalInfo }
     *     
     */
    public FlightDetailType.ArrivalInfo getArrivalInfo() {
        return arrivalInfo;
    }

    /**
     * Sets the value of the arrivalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightDetailType.ArrivalInfo }
     *     
     */
    public void setArrivalInfo(FlightDetailType.ArrivalInfo value) {
        this.arrivalInfo = value;
    }

    /**
     * Gets the value of the fareBasisCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFareBasisCode() {
        return fareBasisCode;
    }

    /**
     * Sets the value of the fareBasisCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFareBasisCode(String value) {
        this.fareBasisCode = value;
    }

    /**
     * Gets the value of the ticketDesignator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketDesignator() {
        return ticketDesignator;
    }

    /**
     * Sets the value of the ticketDesignator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketDesignator(String value) {
        this.ticketDesignator = value;
    }

    /**
     * Gets the value of the returnFlightInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnFlightInd() {
        return returnFlightInd;
    }

    /**
     * Sets the value of the returnFlightInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnFlightInd(Boolean value) {
        this.returnFlightInd = value;
    }

    /**
     * Gets the value of the connectingFlightInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectingFlightInd() {
        return connectingFlightInd;
    }

    /**
     * Sets the value of the connectingFlightInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectingFlightInd(String value) {
        this.connectingFlightInd = value;
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
     *       &lt;attribute name="ArrivalAirport" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="ArrivalDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ArrivalInfo {

        @XmlAttribute(name = "ArrivalAirport", required = true)
        protected String arrivalAirport;
        @XmlAttribute(name = "ArrivalDateTime")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar arrivalDateTime;

        /**
         * Gets the value of the arrivalAirport property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getArrivalAirport() {
            return arrivalAirport;
        }

        /**
         * Sets the value of the arrivalAirport property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setArrivalAirport(String value) {
            this.arrivalAirport = value;
        }

        /**
         * Gets the value of the arrivalDateTime property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getArrivalDateTime() {
            return arrivalDateTime;
        }

        /**
         * Sets the value of the arrivalDateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setArrivalDateTime(XMLGregorianCalendar value) {
            this.arrivalDateTime = value;
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
     *       &lt;attribute name="DepartureAirport" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="DepartureDateTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class DepartureInfo {

        @XmlAttribute(name = "DepartureAirport", required = true)
        protected String departureAirport;
        @XmlAttribute(name = "DepartureDateTime", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar departureDateTime;

        /**
         * Gets the value of the departureAirport property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDepartureAirport() {
            return departureAirport;
        }

        /**
         * Sets the value of the departureAirport property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDepartureAirport(String value) {
            this.departureAirport = value;
        }

        /**
         * Gets the value of the departureDateTime property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDepartureDateTime() {
            return departureDateTime;
        }

        /**
         * Sets the value of the departureDateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDepartureDateTime(XMLGregorianCalendar value) {
            this.departureDateTime = value;
        }

    }

}
