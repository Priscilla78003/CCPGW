/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 2.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPPGW Front end Adapter: PaymentGatewayHandler
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  26-May-2022 
 * ***************************************************************
 */
package com.truteq.ccpgw.payment.gateway.adapter.threeDs.v2.x;

import com.truteq.ccpgw.threeds.v2.objects.authentication.AcquirerData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.BroadInfo;
import com.truteq.ccpgw.threeds.v2.objects.authentication.BrowserData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.CardholderAccount;
import com.truteq.ccpgw.threeds.v2.objects.authentication.CardholderAccountInformation;
import com.truteq.ccpgw.threeds.v2.objects.authentication.CardholderData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.CardholderPhoneNumber;
import com.truteq.ccpgw.threeds.v2.objects.authentication.Data;
import com.truteq.ccpgw.threeds.v2.objects.authentication.MerchantData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.MerchantRiskIndicator;
import com.truteq.ccpgw.threeds.v2.objects.authentication.MessageExtensionAttribute;
import com.truteq.ccpgw.threeds.v2.objects.authentication.PurchaseData;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSRequestor;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSRequestorAuthenticationInformation;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSRequestorPriorTransactionAuthenticationInformation;
import java.math.BigInteger;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ThreeDSV2xHandler {
    
    
    public ThreeDSV2xHandler(){
        
    }
    
    private String getDateString(Date date, String pattern){

      DateFormat dateFormat = new SimpleDateFormat(pattern); //"yyyyMMddHHmm"

      return dateFormat.format(date);
      
    }
        
    
   /**
     * =========================================================================
     * get3DSv2xRequestor
     * =========================================================================
     * @param cardholderAccountNumber
     * @param cardType
     * @return 
     * ========================================================================= 
     */
    public ThreeDSRequestor get3DSv2xRequestor(){
        
        ThreeDSRequestorAuthenticationInformation threeDSRequestorAuthenticationInfo = new ThreeDSRequestorAuthenticationInformation();
        threeDSRequestorAuthenticationInfo.setThreeDSReqAuthData("threeDSReqAuthData");
        threeDSRequestorAuthenticationInfo.setThreeDSReqAuthMethod("04");
        threeDSRequestorAuthenticationInfo.setThreeDSReqAuthTimestamp(getDateString(new Date(),"yyyyMMddHHmm"));
        
        ThreeDSRequestorPriorTransactionAuthenticationInformation threeDSRequestorPriorAuthenticationInfo = new ThreeDSRequestorPriorTransactionAuthenticationInformation();
        threeDSRequestorPriorAuthenticationInfo.setThreeDSReqPriorAuthData("threeDSReqPriorAuthData");
        threeDSRequestorPriorAuthenticationInfo.setThreeDSReqPriorAuthMethod("01");
        threeDSRequestorPriorAuthenticationInfo.setThreeDSReqPriorAuthTimestamp(getDateString(new Date(),"yyyyMMddHHmm"));
        //threeDSRequestorPriorAuthenticationInfo.setThreeDSReqPriorRef("VOGXpZvTlCmBUyPnnZfmsGDKqxRsRwPovkAE");        
        
        
        ThreeDSRequestor threeDSRequestor = new ThreeDSRequestor();
        threeDSRequestor.setThreeDSRequestorAuthenticationInd("02");
        threeDSRequestor.setThreeDSRequestorAuthenticationInfo(threeDSRequestorAuthenticationInfo);
        threeDSRequestor.setThreeDSRequestorChallengeInd("03");
        threeDSRequestor.setThreeDSRequestorDecMaxTime(10080);
        threeDSRequestor.setThreeDSRequestorDecReqInd("N");
        threeDSRequestor.setThreeDSRequestorPriorAuthenticationInfo(threeDSRequestorPriorAuthenticationInfo); 
        
        return threeDSRequestor;
    }
    
    /**
     * =========================================================================
     * get3DSv2xCardholderAccount
     * =========================================================================
     * @param acctNumber
     * @param cardExpiryDate
     * @param schemeId
     * @return 
     * =========================================================================
     */
    public CardholderAccount get3DSv2xCardholderAccount(String acctNumber,
                                                         String cardExpiryDate,
                                                         String schemeId){
        
        CardholderAccountInformation acctInfo = new CardholderAccountInformation();
        acctInfo.setChAccAgeInd("04");
        acctInfo.setChAccDate("20181220");
        acctInfo.setChAccChangeInd("03");
        acctInfo.setChAccChange("20181220");
        acctInfo.setChAccPwChangeInd("04");
        acctInfo.setChAccPwChange("20181220");
        acctInfo.setShipAddressUsageInd("03");
        acctInfo.setShipAddressUsage("20181220");
        acctInfo.setTxnActivityDay("1");
        acctInfo.setTxnActivityYear("1");
        acctInfo.setProvisionAttemptsDay("1");
        acctInfo.setNbPurchaseAccount("1");
        acctInfo.setSuspiciousAccActivity("01");
        acctInfo.setShipNameIndicator("01");
        acctInfo.setPaymentAccInd("03");
        acctInfo.setPaymentAccAge("20181220");
   
        CardholderAccount cardholderAccount = new CardholderAccount();
        cardholderAccount.setAcctType("02");
        cardholderAccount.setCardExpiryDate(cardExpiryDate);
        cardholderAccount.setAcctInfo(acctInfo);
        cardholderAccount.setAcctNumber(acctNumber);
        cardholderAccount.setSchemeId(schemeId);
        cardholderAccount.setAcctID(""); //This field is optional
        cardholderAccount.setPayTokenInd(Boolean.TRUE);

        return cardholderAccount;
    }
    /**
     * =========================================================================
     * get3DSv2xCardholder
     * =========================================================================
     * @param acctNumber
     * @param cardExpiryDate
     * @param schemeId
     * @return 
     * =========================================================================
     */
    public CardholderData get3DSv2xCardholder(String cardholderName){
        
        CardholderData cardholderData = new CardholderData();
        cardholderData.setAddrMatch("N");
        cardholderData.setBillAddrCity("");
        cardholderData.setBillAddrCountry("");
        cardholderData.setBillAddrLine1("");
        cardholderData.setBillAddrLine2("");
        cardholderData.setBillAddrLine3("");
        cardholderData.setBillAddrPostCode("");
        cardholderData.setBillAddrState("");
        cardholderData.setEmail("");
        cardholderData.setHomePhone(new CardholderPhoneNumber("1","123"));
        cardholderData.setMobilePhone(new CardholderPhoneNumber("1","123"));
        cardholderData.setWorkPhone(new CardholderPhoneNumber("1","123"));
        cardholderData.setCardholderName(cardholderName);
        cardholderData.setShipAddrCity("");
        cardholderData.setShipAddrCountry("");
        cardholderData.setShipAddrLine1("");
        cardholderData.setShipAddrLine2("");
        cardholderData.setShipAddrLine3("");
        cardholderData.setShipAddrPostCode("");
        cardholderData.setShipAddrState("");

        return cardholderData;
        
    }    
    /**
     * =========================================================================
     * get3DSv2xPurchase
     * =========================================================================
     * @param purchaseAmount
     * @param purchaseCurrency
     * @return 
     * =========================================================================
     */    
    public PurchaseData get3DSv2xPurchase(String purchaseAmount,
                                          String purchaseCurrency){
        
        MerchantRiskIndicator merchantRiskIndicator = new MerchantRiskIndicator();
        merchantRiskIndicator.setShipIndicator("01");
        merchantRiskIndicator.setDeliveryTimeframe("02");
        merchantRiskIndicator.setDeliveryEmailAddress("");
        merchantRiskIndicator.setReorderItemsInd("01");
        merchantRiskIndicator.setPreOrderPurchaseInd("01");
        merchantRiskIndicator.setPreOrderDate(getDateString(new Date(),"yyyyMMddHHmm")) ;
        merchantRiskIndicator.setGiftCardAmount(2);
        merchantRiskIndicator.setGiftCardCurr(purchaseCurrency);
        merchantRiskIndicator.setGiftCardCount(1);
        PurchaseData purchaseData = new PurchaseData();
        purchaseData.setPurchaseInstalData(3);
        purchaseData.setPurchaseAmount(new BigInteger(purchaseAmount));
        purchaseData.setPurchaseCurrency(purchaseCurrency);
        purchaseData.setPurchaseExponent(2);
        purchaseData.setPurchaseDate(getDateString(new Date(),"yyyyMMddHHmmss"));
        purchaseData.setRecurringExpiry(getDateString(new Date(),"yyyyMMdd"));
        purchaseData.setRecurringFrequency(1);
        purchaseData.setTransType("01");
        return purchaseData;
    }    
    
    public MerchantData get3DSv2xMerchant(String merchantConfigurationId,
                                           String mcc,
                                           String merchantName,
                                           String merchantCountryCode,
                                           URL resultsResponseNotificationUrl,
                                           URL notificationURL){
        MerchantData merchantData = new MerchantData();
        merchantData.setMerchantConfigurationId(merchantConfigurationId);
        merchantData.setMcc(mcc);
        merchantData.setMerchantCountryCode(merchantCountryCode);
        merchantData.setMerchantName(merchantName);
        merchantData.setNotificationURL(notificationURL);
        merchantData.setThreeDSRequestorId("ds-assigned-requestor-id");
        merchantData.setThreeDSRequestorName("ds-assigned-requestor-name");
        merchantData.setWhiteListStatus("Y");
        merchantData.setResultsResponseNotificationUrl(resultsResponseNotificationUrl);
        return merchantData;
    }
    
    public MerchantData get3DSv2xMerchant(String merchantConfigurationId,
                                           String mcc,
                                           String merchantName,
                                           String merchantCountryCode){
        MerchantData merchantData = new MerchantData();
        merchantData.setMerchantConfigurationId(merchantConfigurationId);
        merchantData.setMcc(mcc);
        merchantData.setMerchantCountryCode(merchantCountryCode);
        merchantData.setMerchantName(merchantName);
        merchantData.setThreeDSRequestorId("ds-assigned-requestor-id");
        merchantData.setThreeDSRequestorName("ds-assigned-requestor-name");
        merchantData.setWhiteListStatus("Y");
        return merchantData;
    }    
    
    /**
     * =========================================================================
     *  get3DSv2xAcquirer
     * =========================================================================
     * @param acquirerBin
     * @param acquirerMerchantId
     * @return 
     * =========================================================================
     */   
    public AcquirerData get3DSv2xAcquirer(String acquirerBin, String acquirerMerchantId){
        AcquirerData acquirerData = new AcquirerData();
        acquirerData.setAcquirerBin(acquirerBin);
        acquirerData.setAcquirerMerchantId(acquirerMerchantId);
        return acquirerData;
    }   

    /**
     * 
     * @param id
     * @param name
     * @return 
     */
    public MessageExtensionAttribute get3DSv2xMessageExtensionAttribute(String id, String name){
        Data data = new Data();
        data.setValueOne("value1");
        data.setValueTwo("value2");
        
        MessageExtensionAttribute messageExtensionAttribute = new MessageExtensionAttribute();
        messageExtensionAttribute.setName(name);
        messageExtensionAttribute.setId(id);
        messageExtensionAttribute.setCriticalityIndicator(Boolean.FALSE);
        messageExtensionAttribute.setData(data);
        return messageExtensionAttribute;
    }       
    /**
     * 
     * @param browserIP
     * @return 
     */
    public BrowserData get3DSv2xBrowser(String browserIP){
        BrowserData browserData = new BrowserData();
        browserData.setBrowserAcceptHeader("application/json");
        browserData.setBrowserIP(browserIP);
        browserData.setBrowserJavaEnabled(Boolean.TRUE);
        browserData.setBrowserLanguage("en");
        browserData.setBrowserColorDepth("8");
        browserData.setBrowserScreenHeight(1);
        browserData.setBrowserScreenWidth(1);
        browserData.setBrowserTZ(1);
        browserData.setBrowserUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
        browserData.setChallengeWindowSize("01");
        browserData.setBrowserJavascriptEnabled(Boolean.TRUE);   
        return browserData;
    }         
    /**
     * 
     * @param message
     * @return 
     */
    public BroadInfo get3DSv2xBroadInfo(String message){
        BroadInfo broadInfo = new BroadInfo();
        broadInfo.setMessage(message);
        return broadInfo;
    }         
}
