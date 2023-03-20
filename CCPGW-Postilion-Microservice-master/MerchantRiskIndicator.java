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
public class MerchantRiskIndicator {
    private String shipIndicator;
    private String deliveryTimeframe;
    private String deliveryEmailAddress;
    private String reorderItemsInd;
    private String preOrderPurchaseInd;
    private String preOrderDate;
    private Integer giftCardAmount;
    private String giftCardCurr;
    private Integer giftCardCount;

    
    public MerchantRiskIndicator(){
        
    }
    
    public MerchantRiskIndicator(String shipIndicator,
                                    String deliveryTimeframe,
                                    String deliveryEmailAddress,
                                    String reorderItemsInd,
                                    String preOrderPurchaseInd,
                                    String preOrderDate,
                                    Integer giftCardAmount,
                                    String giftCardCurr,
                                    Integer giftCardCount){
        this();
        this.shipIndicator = shipIndicator;
        this.deliveryTimeframe = deliveryTimeframe;
        this.deliveryEmailAddress = deliveryEmailAddress;
        this.reorderItemsInd = reorderItemsInd;
        this.preOrderPurchaseInd = preOrderPurchaseInd;
        this.preOrderDate = preOrderDate;
        this.giftCardAmount = giftCardAmount;
        this.giftCardCurr = giftCardCurr;
        this.giftCardCount = giftCardCount;
    }    
    
    /**
     * @return the shipIndicator
     */
    public String getShipIndicator() {
        return shipIndicator;
    }

    /**
     * @param shipIndicator the shipIndicator to set
     */
    public void setShipIndicator(String shipIndicator) {
        this.shipIndicator = shipIndicator;
    }

    /**
     * @return the deliveryTimeframe
     */
    public String getDeliveryTimeframe() {
        return deliveryTimeframe;
    }

    /**
     * @param deliveryTimeframe the deliveryTimeframe to set
     */
    public void setDeliveryTimeframe(String deliveryTimeframe) {
        this.deliveryTimeframe = deliveryTimeframe;
    }

    /**
     * @return the deliveryEmailAddress
     */
    public String getDeliveryEmailAddress() {
        return deliveryEmailAddress;
    }

    /**
     * @param deliveryEmailAddress the deliveryEmailAddress to set
     */
    public void setDeliveryEmailAddress(String deliveryEmailAddress) {
        this.deliveryEmailAddress = deliveryEmailAddress;
    }

    /**
     * @return the reorderItemsInd
     */
    public String getReorderItemsInd() {
        return reorderItemsInd;
    }

    /**
     * @param reorderItemsInd the reorderItemsInd to set
     */
    public void setReorderItemsInd(String reorderItemsInd) {
        this.reorderItemsInd = reorderItemsInd;
    }

    /**
     * @return the preOrderPurchaseInd
     */
    public String getPreOrderPurchaseInd() {
        return preOrderPurchaseInd;
    }

    /**
     * @param preOrderPurchaseInd the preOrderPurchaseInd to set
     */
    public void setPreOrderPurchaseInd(String preOrderPurchaseInd) {
        this.preOrderPurchaseInd = preOrderPurchaseInd;
    }

    /**
     * @return the preOrderDate
     */
    public String getPreOrderDate() {
        return preOrderDate;
    }

    /**
     * @param preOrderDate the preOrderDate to set
     */
    public void setPreOrderDate(String preOrderDate) {
        this.preOrderDate = preOrderDate;
    }

    /**
     * @return the giftCardAmount
     */
    public Integer getGiftCardAmount() {
        return giftCardAmount;
    }

    /**
     * @param giftCardAmount the giftCardAmount to set
     */
    public void setGiftCardAmount(Integer giftCardAmount) {
        this.giftCardAmount = giftCardAmount;
    }

    /**
     * @return the giftCardCurr
     */
    public String getGiftCardCurr() {
        return giftCardCurr;
    }

    /**
     * @param giftCardCurr the giftCardCurr to set
     */
    public void setGiftCardCurr(String giftCardCurr) {
        this.giftCardCurr = giftCardCurr;
    }

    /**
     * @return the giftCardCount
     */
    public Integer getGiftCardCount() {
        return giftCardCount;
    }

    /**
     * @param giftCardCount the giftCardCount to set
     */
    public void setGiftCardCount(Integer giftCardCount) {
        this.giftCardCount = giftCardCount;
    }
}
