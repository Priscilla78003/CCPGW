/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2022 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  15-Jun-2022 
 * ***************************************************************
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

import com.truteq.ccpgw.threeds.v2.objects.versioning.Sdk;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TDSAuthReqObject {

    private String threeDSServerTransID;
    private String preferredProtocolVersion;
    private String enforcePreferredProtocolVersion;
    private String deviceChannel;
    private String messageCategory;
    private String threeDSCompInd;
    private String threeDSRequestorURL;
    private String threeRIInd;
    private String messageExtension;
    private String challengeMessageExtension;
    private String threeDSRequestorAuthenticationInd;
    private String threeDSReqAuthMethod;
    private String threeDSReqAuthTimestamp;
    private String threeDSReqAuthData;
    private String threeDSRequestorChallengeInd;
    private String threeDSReqPriorRef;
    private String threeDSReqPriorAuthMethod;
    private String threeDSReqPriorAuthTimestamp;
    private String threeDSReqPriorAuthData;
    private String threeDSRequestorDecReqInd;
    private Integer threeDSRequestorDecMaxTime;
    private String addrMatch;
    private String billAddrCity;
    private String billAddrCountry;
    private String billAddrLine1;
    private String billAddrLine2;
    private String billAddrLine3;
    private String billAddrPostCode;
    private String billAddrState;
    private String email;
    private String homephonecc;
    private String homephonesub;
    private String mobilephonecc;
    private String mobilephonesub;
    private String workphonecc;
    private String workphonesub;
    private String cardholderName;
    private String shipAddrCity;
    private String shipAddrCountry;
    private String shipAddrLine1;
    private String shipAddrLine2;
    private String shipAddrLine3;
    private String shipAddrPostCode;
    private String shipAddrState;
    private String acctType;
    private String cardExpiryDate;
    private String acctNumber;
    private String schemeId;
    private String acctID;
    private String payTokenInd;
    private String chAccAgeInd;
    private String chAccDate;
    private String chAccChangeInd;
    private String chAccChange;
    private String chAccPwChangeInd;
    private String chAccPwChange;
    private String shipAddressUsageInd;
    private String shipAddressUsage;
    private String txnActivityDay;
    private String txnActivityYear;
    private String provisionAttemptsDay;
    private String nbPurchaseAccount;
    private String suspiciousAccActivity;
    private String shipNameIndicator;
    private String paymentAccInd;
    private String paymentAccAge;
    private Integer purchaseInstalData;
    private BigInteger purchaseAmount;
    private String purchaseCurrency;
    private Integer ppurchaseExponent;
    private String purchaseDate;
    private String recurringExpiry;
    private Integer recurringFrequency;
    private String transType;
    private String shipIndicator;
    private String deliveryTimeframe;
    private String deliveryEmailAddress;
    private String reorderItemsInd;
    private String preOrderPurchaseInd;
    private String preOrderDate;
    private Integer giftCardAmount;
    private String giftCardCurr;
    private Integer giftCardCount;
    private String merchantConfigurationId;
    private String mcc;
    private String merchantCountryCode;
    private String merchantName;
    private String notificationURL;
    private String threeDSRequestorId;
    private String threeDSRequestorName;
    private String whiteListStatus;
    private String browserAcceptHeader;
    private String browserIP;
    private String browserJavaEnabled;
    private String browserLanguage;
    private String browserColorDepth;
    private Integer browserScreenHeight;
    private Integer browserScreenWidth;
    private Integer browserTZ;
    private String browserUserAgent;
    private String challengeWindowSize;
    private String browserJavascriptEnabled;
    private String sdkAppID;
    private String sdkEncData;
    private String sdkEphemPubKey;
    private Integer sdkMaxTimeout;
    private String sdkReferenceNumber;
    private String sdkTransID;
    private String sdkInterface;
    private String sdkUiType;
    private String message;
    private String acquirerBin;
    private String acquirerMerchantId;

    public ThreeDSServerAuthenticationRequest toThreeDSServerAuthenticationRequest() {
        ThreeDSServerAuthenticationRequest authReq = new ThreeDSServerAuthenticationRequest();

        authReq.setThreeDSServerTransID(threeDSServerTransID);
        authReq.setPreferredProtocolVersion(preferredProtocolVersion);
        if (enforcePreferredProtocolVersion.toLowerCase().equals("true")) {
            authReq.setEnforcePreferredProtocolVersion(Boolean.TRUE);
        } else {
            authReq.setEnforcePreferredProtocolVersion(Boolean.FALSE);
        }
        authReq.setDeviceChannel(deviceChannel);
        authReq.setMessageCategory(messageCategory);
        authReq.setThreeDSCompInd(threeDSCompInd);
        try {
            authReq.setThreeDSRequestorURL(new URL(threeDSRequestorURL));
        } catch (MalformedURLException err) {

        }
        authReq.setThreeRIInd(threeRIInd);
        List<MessageExtensionAttribute> messageExtensionList = new ArrayList<>();
        MessageExtensionAttribute attr = new MessageExtensionAttribute();
        attr.setName(messageExtension);
        attr.setId("id");
        messageExtensionList.add(attr);
        authReq.setMessageExtension(messageExtensionList);
        authReq.setChallengeMessageExtension(null);

        
        authReq.setThreeDSRequestor(new ThreeDSRequestor());
        authReq.getThreeDSRequestor().setThreeDSRequestorAuthenticationInd(threeDSRequestorAuthenticationInd);
        authReq.getThreeDSRequestor().setThreeDSRequestorChallengeInd(threeDSRequestorChallengeInd);
        authReq.getThreeDSRequestor().setThreeDSRequestorDecReqInd(threeDSRequestorDecReqInd);
        authReq.getThreeDSRequestor().setThreeDSRequestorDecMaxTime(threeDSRequestorDecMaxTime);

        authReq.getThreeDSRequestor().setThreeDSRequestorAuthenticationInfo(new ThreeDSRequestorAuthenticationInformation());
        authReq.getThreeDSRequestor().getThreeDSRequestorAuthenticationInfo().setThreeDSReqAuthMethod(threeDSReqAuthMethod);
        authReq.getThreeDSRequestor().getThreeDSRequestorAuthenticationInfo().setThreeDSReqAuthTimestamp(threeDSReqAuthTimestamp);
        authReq.getThreeDSRequestor().getThreeDSRequestorAuthenticationInfo().setThreeDSReqAuthData(threeDSReqAuthData);  
        
        authReq.getThreeDSRequestor().setThreeDSRequestorPriorAuthenticationInfo(new ThreeDSRequestorPriorTransactionAuthenticationInformation());
        authReq.getThreeDSRequestor().getThreeDSRequestorPriorAuthenticationInfo().setThreeDSReqPriorRef(threeDSReqPriorRef);
        authReq.getThreeDSRequestor().getThreeDSRequestorPriorAuthenticationInfo().setThreeDSReqPriorAuthMethod(threeDSReqPriorAuthMethod);
        authReq.getThreeDSRequestor().getThreeDSRequestorPriorAuthenticationInfo().setThreeDSReqPriorAuthTimestamp(threeDSReqPriorAuthTimestamp);
        authReq.getThreeDSRequestor().getThreeDSRequestorPriorAuthenticationInfo().setThreeDSReqPriorAuthData(threeDSReqPriorAuthData);


        authReq.setCardholder(new CardholderData());
        authReq.getCardholder().setAddrMatch(addrMatch);
        authReq.getCardholder().setBillAddrCity(billAddrCity);
        authReq.getCardholder().setBillAddrCountry(billAddrCountry);
        authReq.getCardholder().setBillAddrLine1(billAddrLine1);
        authReq.getCardholder().setBillAddrLine2(billAddrLine2);
        authReq.getCardholder().setBillAddrLine3(billAddrLine3);
        authReq.getCardholder().setBillAddrPostCode(billAddrPostCode);
        authReq.getCardholder().setBillAddrState(billAddrState);
        authReq.getCardholder().setEmail(email);
        authReq.getCardholder().setHomePhone(new CardholderPhoneNumber(homephonecc, homephonesub));
        authReq.getCardholder().setMobilePhone(new CardholderPhoneNumber(mobilephonecc, mobilephonesub));
        authReq.getCardholder().setWorkPhone(new CardholderPhoneNumber(workphonecc, workphonesub));
        authReq.getCardholder().setCardholderName(cardholderName);
        authReq.getCardholder().setShipAddrCity(shipAddrCity);
        authReq.getCardholder().setShipAddrCountry(shipAddrCountry);
        authReq.getCardholder().setShipAddrLine1(shipAddrLine1);
        authReq.getCardholder().setShipAddrLine2(shipAddrLine2);
        authReq.getCardholder().setShipAddrLine3(shipAddrLine3);
        authReq.getCardholder().setShipAddrPostCode(shipAddrPostCode);
        authReq.getCardholder().setShipAddrState(shipAddrState);
        
        authReq.setCardholderAccount(new CardholderAccount());
        authReq.getCardholderAccount().setAcctType(acctType);
        authReq.getCardholderAccount().setCardExpiryDate(cardExpiryDate);
        authReq.getCardholderAccount().setAcctNumber(acctNumber);
        authReq.getCardholderAccount().setSchemeId(schemeId);
        authReq.getCardholderAccount().setAcctID(acctID);
        if (payTokenInd.toLowerCase().equals("true")) {
            authReq.getCardholderAccount().setPayTokenInd(Boolean.TRUE);
        } else {
            authReq.getCardholderAccount().setPayTokenInd(Boolean.FALSE);
        }
        authReq.getCardholderAccount().setAcctInfo(new CardholderAccountInformation());
        authReq.getCardholderAccount().getAcctInfo().setChAccAgeInd(chAccAgeInd);
        authReq.getCardholderAccount().getAcctInfo().setChAccDate(chAccDate);
        authReq.getCardholderAccount().getAcctInfo().setChAccChangeInd(chAccChangeInd);
        authReq.getCardholderAccount().getAcctInfo().setChAccChange(chAccChange);
        authReq.getCardholderAccount().getAcctInfo().setChAccPwChangeInd(chAccPwChangeInd);
        authReq.getCardholderAccount().getAcctInfo().setChAccPwChange(chAccPwChange);
        authReq.getCardholderAccount().getAcctInfo().setShipAddressUsageInd(shipAddressUsageInd);
        authReq.getCardholderAccount().getAcctInfo().setShipAddressUsage(shipAddressUsage);
        authReq.getCardholderAccount().getAcctInfo().setTxnActivityDay(txnActivityDay);
        authReq.getCardholderAccount().getAcctInfo().setTxnActivityYear(txnActivityYear);
        authReq.getCardholderAccount().getAcctInfo().setProvisionAttemptsDay(provisionAttemptsDay);
        authReq.getCardholderAccount().getAcctInfo().setNbPurchaseAccount(nbPurchaseAccount);
        authReq.getCardholderAccount().getAcctInfo().setSuspiciousAccActivity(suspiciousAccActivity);
        authReq.getCardholderAccount().getAcctInfo().setShipNameIndicator(shipNameIndicator);
        authReq.getCardholderAccount().getAcctInfo().setPaymentAccInd(paymentAccInd);
        authReq.getCardholderAccount().getAcctInfo().setPaymentAccAge(paymentAccAge);

        authReq.setPurchase(new PurchaseData());
        authReq.getPurchase().setPurchaseInstalData(purchaseInstalData);
        authReq.getPurchase().setPurchaseAmount(purchaseAmount);
        authReq.getPurchase().setPurchaseCurrency(purchaseCurrency);
        authReq.getPurchase().setPurchaseExponent(ppurchaseExponent);
        authReq.getPurchase().setPurchaseDate(purchaseDate);
        authReq.getPurchase().setRecurringExpiry(recurringExpiry);
        authReq.getPurchase().setRecurringFrequency(recurringFrequency);
        authReq.getPurchase().setTransType(transType);

        authReq.getPurchase().setMerchantRiskIndicator(new MerchantRiskIndicator());
        authReq.getPurchase().getMerchantRiskIndicator().setShipIndicator(shipIndicator);
        authReq.getPurchase().getMerchantRiskIndicator().setDeliveryTimeframe(deliveryTimeframe);
        authReq.getPurchase().getMerchantRiskIndicator().setDeliveryEmailAddress(deliveryEmailAddress);
        authReq.getPurchase().getMerchantRiskIndicator().setReorderItemsInd(reorderItemsInd);
        authReq.getPurchase().getMerchantRiskIndicator().setPreOrderPurchaseInd(preOrderPurchaseInd);
        authReq.getPurchase().getMerchantRiskIndicator().setPreOrderDate(preOrderDate);
        authReq.getPurchase().getMerchantRiskIndicator().setGiftCardAmount(giftCardAmount);
        authReq.getPurchase().getMerchantRiskIndicator().setGiftCardCurr(giftCardCurr);
        authReq.getPurchase().getMerchantRiskIndicator().setGiftCardCount(giftCardCount);
        
        
        authReq.setMerchant(new MerchantData());
        authReq.getMerchant().setMerchantConfigurationId(merchantConfigurationId);
        authReq.getMerchant().setMcc(mcc);
        authReq.getMerchant().setMerchantCountryCode(merchantCountryCode);
        authReq.getMerchant().setMerchantName(merchantName);
        try {
            authReq.getMerchant().setNotificationURL(new URL(notificationURL));
        } catch (MalformedURLException err) {

        }
        authReq.getMerchant().setThreeDSRequestorId(threeDSRequestorId);
        authReq.getMerchant().setThreeDSRequestorName(threeDSRequestorName);
        authReq.getMerchant().setWhiteListStatus(whiteListStatus);
        
        authReq.setBrowserInformation(new BrowserData());
        authReq.getBrowserInformation().setBrowserAcceptHeader(browserAcceptHeader);
        authReq.getBrowserInformation().setBrowserIP(browserIP);    
        if (browserJavaEnabled.toLowerCase().equals("true")) {
            authReq.getBrowserInformation().setBrowserJavaEnabled(Boolean.TRUE);
        } else {
            authReq.getBrowserInformation().setBrowserJavaEnabled(Boolean.FALSE);
        }        
        authReq.getBrowserInformation().setBrowserLanguage(browserLanguage);
        authReq.getBrowserInformation().setBrowserColorDepth(browserColorDepth);
        authReq.getBrowserInformation().setBrowserScreenHeight(browserScreenHeight);
        authReq.getBrowserInformation().setBrowserScreenWidth(browserScreenWidth);
        authReq.getBrowserInformation().setBrowserTZ(browserTZ);
        authReq.getBrowserInformation().setBrowserUserAgent(browserUserAgent);
        authReq.getBrowserInformation().setChallengeWindowSize(challengeWindowSize);
        if (browserJavascriptEnabled.toLowerCase().equals("true")) {
            authReq.getBrowserInformation().setBrowserJavaEnabled(Boolean.TRUE);
        } else {
            authReq.getBrowserInformation().setBrowserJavaEnabled(Boolean.FALSE);
        }        
        
        authReq.setSdkInformation(new Sdk());
        authReq.getSdkInformation().setSdkAppID(sdkAppID);
        authReq.getSdkInformation().setSdkEncData(sdkEncData);
        
        Map<String, Object> sdkEphemPubKeyMap = new HashMap<>();
        StringTokenizer st = new StringTokenizer(sdkEphemPubKey, ",");
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            StringTokenizer st2 = new StringTokenizer(token, ":");
            String[] tokens = new String[2];
            int count = 0;
            while (st2.hasMoreTokens()) {
                tokens[count] = st2.nextToken().trim();
                count++;
            }
            sdkEphemPubKeyMap.put(tokens[0], tokens[1]);
        }        
        
        authReq.getSdkInformation().setSdkEphemPubKey(sdkEphemPubKeyMap);
        authReq.getSdkInformation().setSdkMaxTimeout(sdkMaxTimeout);
        authReq.getSdkInformation().setSdkReferenceNumber(sdkReferenceNumber);
        authReq.getSdkInformation().setSdkTransID(sdkTransID);
        
//This was removed for PRODUCTION on 2022-10-12 by Grant O'Reilly 
// Reason: 
// The following is the error received from Visa.
// {"messageType":"Erro","messageVersion":"2.1.0","threeDSServerTransID":"b00b6616-d329-45a9-b071-e5e1e6d4665b",
//  "dsTransID":"265981f8-6211-4080-a63a-3c50c2e401e2","errorCode":"201","errorComponent":"D",
//  "errorDescription":"A message element required as defined in Table A.1 is missing from the message.",
//  "errorDetail":"deviceRenderOptions.sdkInterface","errorMessageType":"AReq"}
//
//please remove the whole deviceRenderOptions from your Authentication Request, this attribute is only needed for deviceChannel 01-APP (from mobile app)
        //authReq.setDeviceRenderOptions(new DeviceRenderOptions());
        //authReq.getDeviceRenderOptions().setSdkInterface(sdkInterface);

//        List<String> sdkUiTypeList = new ArrayList();
//        st = new StringTokenizer(sdkUiType, ",");
//        while (st.hasMoreTokens()) {
//            sdkUiTypeList.add(st.nextToken().trim());
//        }
        //authReq.getDeviceRenderOptions().setSdkUiType(sdkUiTypeList);
        
        authReq.setBroadInfo(new BroadInfo());
        authReq.getBroadInfo().setMessage(message);
     
        authReq.setAcquirer(new AcquirerData());
        authReq.getAcquirer().setAcquirerBin(acquirerBin);
        authReq.getAcquirer().setAcquirerMerchantId(acquirerMerchantId);
        return authReq;
    }

    /**
     * @return the threeDSServerTransID
     */
    public String getThreeDSServerTransID() {
        return threeDSServerTransID;
    }

    /**
     * @param threeDSServerTransID the threeDSServerTransID to set
     */
    public void setThreeDSServerTransID(String threeDSServerTransID) {
        this.threeDSServerTransID = threeDSServerTransID;
    }

    /**
     * @return the preferredProtocolVersion
     */
    public String getPreferredProtocolVersion() {
        return preferredProtocolVersion;
    }

    /**
     * @param preferredProtocolVersion the preferredProtocolVersion to set
     */
    public void setPreferredProtocolVersion(String preferredProtocolVersion) {
        this.preferredProtocolVersion = preferredProtocolVersion;
    }

    /**
     * @return the enforcePreferredProtocolVersion
     */
    public String getEnforcePreferredProtocolVersion() {
        return enforcePreferredProtocolVersion;
    }

    /**
     * @param enforcePreferredProtocolVersion the
     * enforcePreferredProtocolVersion to set
     */
    public void setEnforcePreferredProtocolVersion(String enforcePreferredProtocolVersion) {
        this.enforcePreferredProtocolVersion = enforcePreferredProtocolVersion;
    }

    /**
     * @return the deviceChannel
     */
    public String getDeviceChannel() {
        return deviceChannel;
    }

    /**
     * @param deviceChannel the deviceChannel to set
     */
    public void setDeviceChannel(String deviceChannel) {
        this.deviceChannel = deviceChannel;
    }

    /**
     * @return the messageCategory
     */
    public String getMessageCategory() {
        return messageCategory;
    }

    /**
     * @param messageCategory the messageCategory to set
     */
    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    /**
     * @return the threeDSCompInd
     */
    public String getThreeDSCompInd() {
        return threeDSCompInd;
    }

    /**
     * @param threeDSCompInd the threeDSCompInd to set
     */
    public void setThreeDSCompInd(String threeDSCompInd) {
        this.threeDSCompInd = threeDSCompInd;
    }

    /**
     * @return the threeDSRequestorURL
     */
    public String getThreeDSRequestorURL() {
        return threeDSRequestorURL;
    }

    /**
     * @param threeDSRequestorURL the threeDSRequestorURL to set
     */
    public void setThreeDSRequestorURL(String threeDSRequestorURL) {
        this.threeDSRequestorURL = threeDSRequestorURL;
    }

    /**
     * @return the threeRIInd
     */
    public String getThreeRIInd() {
        return threeRIInd;
    }

    /**
     * @param threeRIInd the threeRIInd to set
     */
    public void setThreeRIInd(String threeRIInd) {
        this.threeRIInd = threeRIInd;
    }

    /**
     * @return the messageExtension
     */
    public String getMessageExtension() {
        return messageExtension;
    }

    /**
     * @param messageExtension the messageExtension to set
     */
    public void setMessageExtension(String messageExtension) {
        this.messageExtension = messageExtension;
    }

    /**
     * @return the challengeMessageExtension
     */
    public String getChallengeMessageExtension() {
        return challengeMessageExtension;
    }

    /**
     * @param challengeMessageExtension the challengeMessageExtension to set
     */
    public void setChallengeMessageExtension(String challengeMessageExtension) {
        this.challengeMessageExtension = challengeMessageExtension;
    }

    /**
     * @return the threeDSRequestorAuthenticationInd
     */
    public String getThreeDSRequestorAuthenticationInd() {
        return threeDSRequestorAuthenticationInd;
    }

    /**
     * @param threeDSRequestorAuthenticationInd the
     * threeDSRequestorAuthenticationInd to set
     */
    public void setThreeDSRequestorAuthenticationInd(String threeDSRequestorAuthenticationInd) {
        this.threeDSRequestorAuthenticationInd = threeDSRequestorAuthenticationInd;
    }

    /**
     * @return the threeDSReqAuthMethod
     */
    public String getThreeDSReqAuthMethod() {
        return threeDSReqAuthMethod;
    }

    /**
     * @param threeDSReqAuthMethod the threeDSReqAuthMethod to set
     */
    public void setThreeDSReqAuthMethod(String threeDSReqAuthMethod) {
        this.threeDSReqAuthMethod = threeDSReqAuthMethod;
    }

    /**
     * @return the threeDSReqAuthTimestamp
     */
    public String getThreeDSReqAuthTimestamp() {
        return threeDSReqAuthTimestamp;
    }

    /**
     * @param threeDSReqAuthTimestamp the threeDSReqAuthTimestamp to set
     */
    public void setThreeDSReqAuthTimestamp(String threeDSReqAuthTimestamp) {
        this.threeDSReqAuthTimestamp = threeDSReqAuthTimestamp;
    }

    /**
     * @return the threeDSReqAuthData
     */
    public String getThreeDSReqAuthData() {
        return threeDSReqAuthData;
    }

    /**
     * @param threeDSReqAuthData the threeDSReqAuthData to set
     */
    public void setThreeDSReqAuthData(String threeDSReqAuthData) {
        this.threeDSReqAuthData = threeDSReqAuthData;
    }

    /**
     * @return the threeDSRequestorChallengeInd
     */
    public String getThreeDSRequestorChallengeInd() {
        return threeDSRequestorChallengeInd;
    }

    /**
     * @param threeDSRequestorChallengeInd the threeDSRequestorChallengeInd to
     * set
     */
    public void setThreeDSRequestorChallengeInd(String threeDSRequestorChallengeInd) {
        this.threeDSRequestorChallengeInd = threeDSRequestorChallengeInd;
    }

    /**
     * @return the threeDSReqPriorRef
     */
    public String getThreeDSReqPriorRef() {
        return threeDSReqPriorRef;
    }

    /**
     * @param threeDSReqPriorRef the threeDSReqPriorRef to set
     */
    public void setThreeDSReqPriorRef(String threeDSReqPriorRef) {
        this.threeDSReqPriorRef = threeDSReqPriorRef;
    }

    /**
     * @return the threeDSReqPriorAuthMethod
     */
    public String getThreeDSReqPriorAuthMethod() {
        return threeDSReqPriorAuthMethod;
    }

    /**
     * @param threeDSReqPriorAuthMethod the threeDSReqPriorAuthMethod to set
     */
    public void setThreeDSReqPriorAuthMethod(String threeDSReqPriorAuthMethod) {
        this.threeDSReqPriorAuthMethod = threeDSReqPriorAuthMethod;
    }

    /**
     * @return the threeDSReqPriorAuthTimestamp
     */
    public String getThreeDSReqPriorAuthTimestamp() {
        return threeDSReqPriorAuthTimestamp;
    }

    /**
     * @param threeDSReqPriorAuthTimestamp the threeDSReqPriorAuthTimestamp to
     * set
     */
    public void setThreeDSReqPriorAuthTimestamp(String threeDSReqPriorAuthTimestamp) {
        this.threeDSReqPriorAuthTimestamp = threeDSReqPriorAuthTimestamp;
    }

    /**
     * @return the threeDSReqPriorAuthData
     */
    public String getThreeDSReqPriorAuthData() {
        return threeDSReqPriorAuthData;
    }

    /**
     * @param threeDSReqPriorAuthData the threeDSReqPriorAuthData to set
     */
    public void setThreeDSReqPriorAuthData(String threeDSReqPriorAuthData) {
        this.threeDSReqPriorAuthData = threeDSReqPriorAuthData;
    }

    /**
     * @return the threeDSRequestorDecReqInd
     */
    public String getThreeDSRequestorDecReqInd() {
        return threeDSRequestorDecReqInd;
    }

    /**
     * @param threeDSRequestorDecReqInd the threeDSRequestorDecReqInd to set
     */
    public void setThreeDSRequestorDecReqInd(String threeDSRequestorDecReqInd) {
        this.threeDSRequestorDecReqInd = threeDSRequestorDecReqInd;
    }

    /**
     * @return the threeDSRequestorDecMaxTime
     */
    public Integer getThreeDSRequestorDecMaxTime() {
        return threeDSRequestorDecMaxTime;
    }

    /**
     * @param threeDSRequestorDecMaxTime the threeDSRequestorDecMaxTime to set
     */
    public void setThreeDSRequestorDecMaxTime(Integer threeDSRequestorDecMaxTime) {
        this.threeDSRequestorDecMaxTime = threeDSRequestorDecMaxTime;
    }

    /**
     * @return the addrMatch
     */
    public String getAddrMatch() {
        return addrMatch;
    }

    /**
     * @param addrMatch the addrMatch to set
     */
    public void setAddrMatch(String addrMatch) {
        this.addrMatch = addrMatch;
    }

    /**
     * @return the billAddrCity
     */
    public String getBillAddrCity() {
        return billAddrCity;
    }

    /**
     * @param billAddrCity the billAddrCity to set
     */
    public void setBillAddrCity(String billAddrCity) {
        this.billAddrCity = billAddrCity;
    }

    /**
     * @return the billAddrCountry
     */
    public String getBillAddrCountry() {
        return billAddrCountry;
    }

    /**
     * @param billAddrCountry the billAddrCountry to set
     */
    public void setBillAddrCountry(String billAddrCountry) {
        this.billAddrCountry = billAddrCountry;
    }

    /**
     * @return the billAddrLine1
     */
    public String getBillAddrLine1() {
        return billAddrLine1;
    }

    /**
     * @param billAddrLine1 the billAddrLine1 to set
     */
    public void setBillAddrLine1(String billAddrLine1) {
        this.billAddrLine1 = billAddrLine1;
    }

    /**
     * @return the billAddrLine2
     */
    public String getBillAddrLine2() {
        return billAddrLine2;
    }

    /**
     * @param billAddrLine2 the billAddrLine2 to set
     */
    public void setBillAddrLine2(String billAddrLine2) {
        this.billAddrLine2 = billAddrLine2;
    }

    /**
     * @return the billAddrLine3
     */
    public String getBillAddrLine3() {
        return billAddrLine3;
    }

    /**
     * @param billAddrLine3 the billAddrLine3 to set
     */
    public void setBillAddrLine3(String billAddrLine3) {
        this.billAddrLine3 = billAddrLine3;
    }

    /**
     * @return the billAddrPostCode
     */
    public String getBillAddrPostCode() {
        return billAddrPostCode;
    }

    /**
     * @param billAddrPostCode the billAddrPostCode to set
     */
    public void setBillAddrPostCode(String billAddrPostCode) {
        this.billAddrPostCode = billAddrPostCode;
    }

    /**
     * @return the billAddrState
     */
    public String getBillAddrState() {
        return billAddrState;
    }

    /**
     * @param billAddrState the billAddrState to set
     */
    public void setBillAddrState(String billAddrState) {
        this.billAddrState = billAddrState;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the homephonecc
     */
    public String getHomephonecc() {
        return homephonecc;
    }

    /**
     * @param homephonecc the homephonecc to set
     */
    public void setHomephonecc(String homephonecc) {
        this.homephonecc = homephonecc;
    }

    /**
     * @return the homephonesub
     */
    public String getHomephonesub() {
        return homephonesub;
    }

    /**
     * @param homephonesub the homephonesub to set
     */
    public void setHomephonesub(String homephonesub) {
        this.homephonesub = homephonesub;
    }

    /**
     * @return the mobilephonecc
     */
    public String getMobilephonecc() {
        return mobilephonecc;
    }

    /**
     * @param mobilephonecc the mobilephonecc to set
     */
    public void setMobilephonecc(String mobilephonecc) {
        this.mobilephonecc = mobilephonecc;
    }

    /**
     * @return the mobilephonesub
     */
    public String getMobilephonesub() {
        return mobilephonesub;
    }

    /**
     * @param mobilephonesub the mobilephonesub to set
     */
    public void setMobilephonesub(String mobilephonesub) {
        this.mobilephonesub = mobilephonesub;
    }

    /**
     * @return the workphonecc
     */
    public String getWorkphonecc() {
        return workphonecc;
    }

    /**
     * @param workphonecc the workphonecc to set
     */
    public void setWorkphonecc(String workphonecc) {
        this.workphonecc = workphonecc;
    }

    /**
     * @return the workphonesub
     */
    public String getWorkphonesub() {
        return workphonesub;
    }

    /**
     * @param workphonesub the workphonesub to set
     */
    public void setWorkphonesub(String workphonesub) {
        this.workphonesub = workphonesub;
    }

    /**
     * @return the cardholderName
     */
    public String getCardholderName() {
        return cardholderName;
    }

    /**
     * @param cardholderName the cardholderName to set
     */
    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    /**
     * @return the shipAddrCity
     */
    public String getShipAddrCity() {
        return shipAddrCity;
    }

    /**
     * @param shipAddrCity the shipAddrCity to set
     */
    public void setShipAddrCity(String shipAddrCity) {
        this.shipAddrCity = shipAddrCity;
    }

    /**
     * @return the shipAddrCountry
     */
    public String getShipAddrCountry() {
        return shipAddrCountry;
    }

    /**
     * @param shipAddrCountry the shipAddrCountry to set
     */
    public void setShipAddrCountry(String shipAddrCountry) {
        this.shipAddrCountry = shipAddrCountry;
    }

    /**
     * @return the shipAddrLine1
     */
    public String getShipAddrLine1() {
        return shipAddrLine1;
    }

    /**
     * @param shipAddrLine1 the shipAddrLine1 to set
     */
    public void setShipAddrLine1(String shipAddrLine1) {
        this.shipAddrLine1 = shipAddrLine1;
    }

    /**
     * @return the shipAddrLine2
     */
    public String getShipAddrLine2() {
        return shipAddrLine2;
    }

    /**
     * @param shipAddrLine2 the shipAddrLine2 to set
     */
    public void setShipAddrLine2(String shipAddrLine2) {
        this.shipAddrLine2 = shipAddrLine2;
    }

    /**
     * @return the shipAddrLine3
     */
    public String getShipAddrLine3() {
        return shipAddrLine3;
    }

    /**
     * @param shipAddrLine3 the shipAddrLine3 to set
     */
    public void setShipAddrLine3(String shipAddrLine3) {
        this.shipAddrLine3 = shipAddrLine3;
    }

    /**
     * @return the shipAddrPostCode
     */
    public String getShipAddrPostCode() {
        return shipAddrPostCode;
    }

    /**
     * @param shipAddrPostCode the shipAddrPostCode to set
     */
    public void setShipAddrPostCode(String shipAddrPostCode) {
        this.shipAddrPostCode = shipAddrPostCode;
    }

    /**
     * @return the shipAddrState
     */
    public String getShipAddrState() {
        return shipAddrState;
    }

    /**
     * @param shipAddrState the shipAddrState to set
     */
    public void setShipAddrState(String shipAddrState) {
        this.shipAddrState = shipAddrState;
    }

    /**
     * @return the acctType
     */
    public String getAcctType() {
        return acctType;
    }

    /**
     * @param acctType the acctType to set
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    /**
     * @return the cardExpiryDate
     */
    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    /**
     * @param cardExpiryDate the cardExpiryDate to set
     */
    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    /**
     * @return the acctNumber
     */
    public String getAcctNumber() {
        return acctNumber;
    }

    /**
     * @param acctNumber the acctNumber to set
     */
    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    /**
     * @return the schemeId
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * @param schemeId the schemeId to set
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    /**
     * @return the acctID
     */
    public String getAcctID() {
        return acctID;
    }

    /**
     * @param acctID the acctID to set
     */
    public void setAcctID(String acctID) {
        this.acctID = acctID;
    }

    /**
     * @return the payTokenInd
     */
    public String getPayTokenInd() {
        return payTokenInd;
    }

    /**
     * @param payTokenInd the payTokenInd to set
     */
    public void setPayTokenInd(String payTokenInd) {
        this.payTokenInd = payTokenInd;
    }

    /**
     * @return the chAccAgeInd
     */
    public String getChAccAgeInd() {
        return chAccAgeInd;
    }

    /**
     * @param chAccAgeInd the chAccAgeInd to set
     */
    public void setChAccAgeInd(String chAccAgeInd) {
        this.chAccAgeInd = chAccAgeInd;
    }

    /**
     * @return the chAccDate
     */
    public String getChAccDate() {
        return chAccDate;
    }

    /**
     * @param chAccDate the chAccDate to set
     */
    public void setChAccDate(String chAccDate) {
        this.chAccDate = chAccDate;
    }

    /**
     * @return the chAccChangeInd
     */
    public String getChAccChangeInd() {
        return chAccChangeInd;
    }

    /**
     * @param chAccChangeInd the chAccChangeInd to set
     */
    public void setChAccChangeInd(String chAccChangeInd) {
        this.chAccChangeInd = chAccChangeInd;
    }

    /**
     * @return the chAccChange
     */
    public String getChAccChange() {
        return chAccChange;
    }

    /**
     * @param chAccChange the chAccChange to set
     */
    public void setChAccChange(String chAccChange) {
        this.chAccChange = chAccChange;
    }

    /**
     * @return the chAccPwChangeInd
     */
    public String getChAccPwChangeInd() {
        return chAccPwChangeInd;
    }

    /**
     * @param chAccPwChangeInd the chAccPwChangeInd to set
     */
    public void setChAccPwChangeInd(String chAccPwChangeInd) {
        this.chAccPwChangeInd = chAccPwChangeInd;
    }

    /**
     * @return the chAccPwChange
     */
    public String getChAccPwChange() {
        return chAccPwChange;
    }

    /**
     * @param chAccPwChange the chAccPwChange to set
     */
    public void setChAccPwChange(String chAccPwChange) {
        this.chAccPwChange = chAccPwChange;
    }

    /**
     * @return the shipAddressUsageInd
     */
    public String getShipAddressUsageInd() {
        return shipAddressUsageInd;
    }

    /**
     * @param shipAddressUsageInd the shipAddressUsageInd to set
     */
    public void setShipAddressUsageInd(String shipAddressUsageInd) {
        this.shipAddressUsageInd = shipAddressUsageInd;
    }

    /**
     * @return the shipAddressUsage
     */
    public String getShipAddressUsage() {
        return shipAddressUsage;
    }

    /**
     * @param shipAddressUsage the shipAddressUsage to set
     */
    public void setShipAddressUsage(String shipAddressUsage) {
        this.shipAddressUsage = shipAddressUsage;
    }

    /**
     * @return the txnActivityDay
     */
    public String getTxnActivityDay() {
        return txnActivityDay;
    }

    /**
     * @param txnActivityDay the txnActivityDay to set
     */
    public void setTxnActivityDay(String txnActivityDay) {
        this.txnActivityDay = txnActivityDay;
    }

    /**
     * @return the txnActivityYear
     */
    public String getTxnActivityYear() {
        return txnActivityYear;
    }

    /**
     * @param txnActivityYear the txnActivityYear to set
     */
    public void setTxnActivityYear(String txnActivityYear) {
        this.txnActivityYear = txnActivityYear;
    }

    /**
     * @return the provisionAttemptsDay
     */
    public String getProvisionAttemptsDay() {
        return provisionAttemptsDay;
    }

    /**
     * @param provisionAttemptsDay the provisionAttemptsDay to set
     */
    public void setProvisionAttemptsDay(String provisionAttemptsDay) {
        this.provisionAttemptsDay = provisionAttemptsDay;
    }

    /**
     * @return the nbPurchaseAccount
     */
    public String getNbPurchaseAccount() {
        return nbPurchaseAccount;
    }

    /**
     * @param nbPurchaseAccount the nbPurchaseAccount to set
     */
    public void setNbPurchaseAccount(String nbPurchaseAccount) {
        this.nbPurchaseAccount = nbPurchaseAccount;
    }

    /**
     * @return the suspiciousAccActivity
     */
    public String getSuspiciousAccActivity() {
        return suspiciousAccActivity;
    }

    /**
     * @param suspiciousAccActivity the suspiciousAccActivity to set
     */
    public void setSuspiciousAccActivity(String suspiciousAccActivity) {
        this.suspiciousAccActivity = suspiciousAccActivity;
    }

    /**
     * @return the shipNameIndicator
     */
    public String getShipNameIndicator() {
        return shipNameIndicator;
    }

    /**
     * @param shipNameIndicator the shipNameIndicator to set
     */
    public void setShipNameIndicator(String shipNameIndicator) {
        this.shipNameIndicator = shipNameIndicator;
    }

    /**
     * @return the paymentAccInd
     */
    public String getPaymentAccInd() {
        return paymentAccInd;
    }

    /**
     * @param paymentAccInd the paymentAccInd to set
     */
    public void setPaymentAccInd(String paymentAccInd) {
        this.paymentAccInd = paymentAccInd;
    }

    /**
     * @return the paymentAccAge
     */
    public String getPaymentAccAge() {
        return paymentAccAge;
    }

    /**
     * @param paymentAccAge the paymentAccAge to set
     */
    public void setPaymentAccAge(String paymentAccAge) {
        this.paymentAccAge = paymentAccAge;
    }

    /**
     * @return the purchaseInstalData
     */
    public Integer getPurchaseInstalData() {
        return purchaseInstalData;
    }

    /**
     * @param purchaseInstalData the purchaseInstalData to set
     */
    public void setPurchaseInstalData(Integer purchaseInstalData) {
        this.purchaseInstalData = purchaseInstalData;
    }

    /**
     * @return the purchaseAmount
     */
    public BigInteger getPurchaseAmount() {
        return purchaseAmount;
    }

    /**
     * @param purchaseAmount the purchaseAmount to set
     */
    public void setPurchaseAmount(BigInteger purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    /**
     * @return the purchaseCurrency
     */
    public String getPurchaseCurrency() {
        return purchaseCurrency;
    }

    /**
     * @param purchaseCurrency the purchaseCurrency to set
     */
    public void setPurchaseCurrency(String purchaseCurrency) {
        this.purchaseCurrency = purchaseCurrency;
    }

    /**
     * @return the ppurchaseExponent
     */
    public Integer getPpurchaseExponent() {
        return ppurchaseExponent;
    }

    /**
     * @param ppurchaseExponent the ppurchaseExponent to set
     */
    public void setPpurchaseExponent(Integer ppurchaseExponent) {
        this.ppurchaseExponent = ppurchaseExponent;
    }

    /**
     * @return the purchaseDate
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the recurringExpiry
     */
    public String getRecurringExpiry() {
        return recurringExpiry;
    }

    /**
     * @param recurringExpiry the recurringExpiry to set
     */
    public void setRecurringExpiry(String recurringExpiry) {
        this.recurringExpiry = recurringExpiry;
    }

    /**
     * @return the recurringFrequency
     */
    public Integer getRecurringFrequency() {
        return recurringFrequency;
    }

    /**
     * @param recurringFrequency the recurringFrequency to set
     */
    public void setRecurringFrequency(Integer recurringFrequency) {
        this.recurringFrequency = recurringFrequency;
    }

    /**
     * @return the transType
     */
    public String getTransType() {
        return transType;
    }

    /**
     * @param transType the transType to set
     */
    public void setTransType(String transType) {
        this.transType = transType;
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

    /**
     * @return the merchantConfigurationId
     */
    public String getMerchantConfigurationId() {
        return merchantConfigurationId;
    }

    /**
     * @param merchantConfigurationId the merchantConfigurationId to set
     */
    public void setMerchantConfigurationId(String merchantConfigurationId) {
        this.merchantConfigurationId = merchantConfigurationId;
    }

    /**
     * @return the mcc
     */
    public String getMcc() {
        return mcc;
    }

    /**
     * @param mcc the mcc to set
     */
    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    /**
     * @return the merchantCountryCode
     */
    public String getMerchantCountryCode() {
        return merchantCountryCode;
    }

    /**
     * @param merchantCountryCode the merchantCountryCode to set
     */
    public void setMerchantCountryCode(String merchantCountryCode) {
        this.merchantCountryCode = merchantCountryCode;
    }

    /**
     * @return the merchantName
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @param merchantName the merchantName to set
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * @return the notificationURL
     */
    public String getNotificationURL() {
        return notificationURL;
    }

    /**
     * @param notificationURL the notificationURL to set
     */
    public void setNotificationURL(String notificationURL) {
        this.notificationURL = notificationURL;
    }

    /**
     * @return the threeDSRequestorId
     */
    public String getThreeDSRequestorId() {
        return threeDSRequestorId;
    }

    /**
     * @param threeDSRequestorId the threeDSRequestorId to set
     */
    public void setThreeDSRequestorId(String threeDSRequestorId) {
        this.threeDSRequestorId = threeDSRequestorId;
    }

    /**
     * @return the threeDSRequestorName
     */
    public String getThreeDSRequestorName() {
        return threeDSRequestorName;
    }

    /**
     * @param threeDSRequestorName the threeDSRequestorName to set
     */
    public void setThreeDSRequestorName(String threeDSRequestorName) {
        this.threeDSRequestorName = threeDSRequestorName;
    }

    /**
     * @return the whiteListStatus
     */
    public String getWhiteListStatus() {
        return whiteListStatus;
    }

    /**
     * @param whiteListStatus the whiteListStatus to set
     */
    public void setWhiteListStatus(String whiteListStatus) {
        this.whiteListStatus = whiteListStatus;
    }

    /**
     * @return the browserAcceptHeader
     */
    public String getBrowserAcceptHeader() {
        return browserAcceptHeader;
    }

    /**
     * @param browserAcceptHeader the browserAcceptHeader to set
     */
    public void setBrowserAcceptHeader(String browserAcceptHeader) {
        this.browserAcceptHeader = browserAcceptHeader;
    }

    /**
     * @return the browserIP
     */
    public String getBrowserIP() {
        return browserIP;
    }

    /**
     * @param browserIP the browserIP to set
     */
    public void setBrowserIP(String browserIP) {
        this.browserIP = browserIP;
    }

    /**
     * @return the browserJavaEnabled
     */
    public String getBrowserJavaEnabled() {
        return browserJavaEnabled;
    }

    /**
     * @param browserJavaEnabled the browserJavaEnabled to set
     */
    public void setBrowserJavaEnabled(String browserJavaEnabled) {
        this.browserJavaEnabled = browserJavaEnabled;
    }

    /**
     * @return the browserLanguage
     */
    public String getBrowserLanguage() {
        return browserLanguage;
    }

    /**
     * @param browserLanguage the browserLanguage to set
     */
    public void setBrowserLanguage(String browserLanguage) {
        this.browserLanguage = browserLanguage;
    }

    /**
     * @return the browserColorDepth
     */
    public String getBrowserColorDepth() {
        return browserColorDepth;
    }

    /**
     * @param browserColorDepth the browserColorDepth to set
     */
    public void setBrowserColorDepth(String browserColorDepth) {
        this.browserColorDepth = browserColorDepth;
    }

    /**
     * @return the browserScreenHeight
     */
    public Integer getBrowserScreenHeight() {
        return browserScreenHeight;
    }

    /**
     * @param browserScreenHeight the browserScreenHeight to set
     */
    public void setBrowserScreenHeight(Integer browserScreenHeight) {
        this.browserScreenHeight = browserScreenHeight;
    }

    /**
     * @return the browserScreenWidth
     */
    public Integer getBrowserScreenWidth() {
        return browserScreenWidth;
    }

    /**
     * @param browserScreenWidth the browserScreenWidth to set
     */
    public void setBrowserScreenWidth(Integer browserScreenWidth) {
        this.browserScreenWidth = browserScreenWidth;
    }

    /**
     * @return the browserTZ
     */
    public Integer getBrowserTZ() {
        return browserTZ;
    }

    /**
     * @param browserTZ the browserTZ to set
     */
    public void setBrowserTZ(Integer browserTZ) {
        this.browserTZ = browserTZ;
    }

    /**
     * @return the browserUserAgent
     */
    public String getBrowserUserAgent() {
        return browserUserAgent;
    }

    /**
     * @param browserUserAgent the browserUserAgent to set
     */
    public void setBrowserUserAgent(String browserUserAgent) {
        this.browserUserAgent = browserUserAgent;
    }

    /**
     * @return the challengeWindowSize
     */
    public String getChallengeWindowSize() {
        return challengeWindowSize;
    }

    /**
     * @param challengeWindowSize the challengeWindowSize to set
     */
    public void setChallengeWindowSize(String challengeWindowSize) {
        this.challengeWindowSize = challengeWindowSize;
    }

    /**
     * @return the browserJavascriptEnabled
     */
    public String getBrowserJavascriptEnabled() {
        return browserJavascriptEnabled;
    }

    /**
     * @param browserJavascriptEnabled the browserJavascriptEnabled to set
     */
    public void setBrowserJavascriptEnabled(String browserJavascriptEnabled) {
        this.browserJavascriptEnabled = browserJavascriptEnabled;
    }

    /**
     * @return the sdkAppID
     */
    public String getSdkAppID() {
        return sdkAppID;
    }

    /**
     * @param sdkAppID the sdkAppID to set
     */
    public void setSdkAppID(String sdkAppID) {
        this.sdkAppID = sdkAppID;
    }

    /**
     * @return the sdkEncData
     */
    public String getSdkEncData() {
        return sdkEncData;
    }

    /**
     * @param sdkEncData the sdkEncData to set
     */
    public void setSdkEncData(String sdkEncData) {
        this.sdkEncData = sdkEncData;
    }

    /**
     * @return the sdkEphemPubKey
     */
    public String getSdkEphemPubKey() {
        return sdkEphemPubKey;
    }

    /**
     * @param sdkEphemPubKey the sdkEphemPubKey to set
     */
    public void setSdkEphemPubKey(String sdkEphemPubKey) {
        this.sdkEphemPubKey = sdkEphemPubKey;
    }

    /**
     * @return the sdkMaxTimeout
     */
    public Integer getSdkMaxTimeout() {
        return sdkMaxTimeout;
    }

    /**
     * @param sdkMaxTimeout the sdkMaxTimeout to set
     */
    public void setSdkMaxTimeout(Integer sdkMaxTimeout) {
        this.sdkMaxTimeout = sdkMaxTimeout;
    }

    /**
     * @return the sdkReferenceNumber
     */
    public String getSdkReferenceNumber() {
        return sdkReferenceNumber;
    }

    /**
     * @param sdkReferenceNumber the sdkReferenceNumber to set
     */
    public void setSdkReferenceNumber(String sdkReferenceNumber) {
        this.sdkReferenceNumber = sdkReferenceNumber;
    }

    /**
     * @return the sdkTransID
     */
    public String getSdkTransID() {
        return sdkTransID;
    }

    /**
     * @param sdkTransID the sdkTransID to set
     */
    public void setSdkTransID(String sdkTransID) {
        this.sdkTransID = sdkTransID;
    }

    /**
     * @return the sdkInterface
     */
    public String getSdkInterface() {
        return sdkInterface;
    }

    /**
     * @param sdkInterface the sdkInterface to set
     */
    public void setSdkInterface(String sdkInterface) {
        this.sdkInterface = sdkInterface;
    }

    /**
     * @return the sdkUiType
     */
    public String getSdkUiType() {
        return sdkUiType;
    }

    /**
     * @param sdkUiType the sdkUiType to set
     */
    public void setSdkUiType(String sdkUiType) {
        this.sdkUiType = sdkUiType;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the acquirerBin
     */
    public String getAcquirerBin() {
        return acquirerBin;
    }

    /**
     * @param acquirerBin the acquirerBin to set
     */
    public void setAcquirerBin(String acquirerBin) {
        this.acquirerBin = acquirerBin;
    }

    /**
     * @return the acquirerMerchantId
     */
    public String getAcquirerMerchantId() {
        return acquirerMerchantId;
    }

    /**
     * @param acquirerMerchantId the acquirerMerchantId to set
     */
    public void setAcquirerMerchantId(String acquirerMerchantId) {
        this.acquirerMerchantId = acquirerMerchantId;
    }

}
