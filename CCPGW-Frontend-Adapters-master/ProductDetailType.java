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
import javax.xml.bind.annotation.XmlType;


/**
 * Product detail
 * 
 * <p>Java class for ProductDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProductDetailItems" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ProductDetailItem" type="{}ProductDetailItemType" maxOccurs="99" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="ProductSummaryIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductDetailType", propOrder = {
    "productDetailItems"
})
public class ProductDetailType {

    @XmlElement(name = "ProductDetailItems")
    protected ProductDetailType.ProductDetailItems productDetailItems;
    @XmlAttribute(name = "ProductSummaryIndicator")
    protected String productSummaryIndicator;

    /**
     * Gets the value of the productDetailItems property.
     * 
     * @return
     *     possible object is
     *     {@link ProductDetailType.ProductDetailItems }
     *     
     */
    public ProductDetailType.ProductDetailItems getProductDetailItems() {
        return productDetailItems;
    }

    /**
     * Sets the value of the productDetailItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductDetailType.ProductDetailItems }
     *     
     */
    public void setProductDetailItems(ProductDetailType.ProductDetailItems value) {
        this.productDetailItems = value;
    }

    /**
     * Gets the value of the productSummaryIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductSummaryIndicator() {
        return productSummaryIndicator;
    }

    /**
     * Sets the value of the productSummaryIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductSummaryIndicator(String value) {
        this.productSummaryIndicator = value;
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
     *         &lt;element name="ProductDetailItem" type="{}ProductDetailItemType" maxOccurs="99" minOccurs="0"/>
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
        "productDetailItem"
    })
    public static class ProductDetailItems {

        @XmlElement(name = "ProductDetailItem")
        protected List<ProductDetailItemType> productDetailItem;

        /**
         * Gets the value of the productDetailItem property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the productDetailItem property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProductDetailItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProductDetailItemType }
         * 
         * 
         */
        public List<ProductDetailItemType> getProductDetailItem() {
            if (productDetailItem == null) {
                productDetailItem = new ArrayList<ProductDetailItemType>();
            }
            return this.productDetailItem;
        }

    }

}
