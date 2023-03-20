/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.frontend.adapter.sabre.api.batchprocessing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class BillingFileTransaction {

    private String transactionType;

    private String documentType;

    private String documentNumber;

    private String issueDateStr;

    private String issueTimeStr;

    private String pnrLocator;

    private Integer sequenceNumber;

    private Integer sequenceCount;

    private String creditCardType;

    private String creditCardNumber;

    private String expireDateStr;

    private String passengerName;

    private String merchantNumber;

    private Integer stationNumber;

    private String posCity;

    private String posState;

    private String posCountry;

    private String posZipCode;

    private String channelId;

    private String internetIndicator;

    private String airLineCode;

    private String dbaName;

    private String authCode;

    private String transactionId;

    private String payPlan;

    private Integer authAmount;

    private String authDecimalPosition;

    private String authCurrency;

    private String docAmount;

    private String docDecimalPosition;

    private String docCurrency;

    private Integer billedAmount;

    private String billedDecimalPosition;

    private String billedCurrency;

    private Integer terminalId;

    private String eciIndicator;

    private String authCharacteristicIndicator;

    private String avsResultCode;

    private String validationCode;
    
    private String authSourceCode;
    
    private String authResponseCode;
    
    private String threeDSIndicator;
    
    private String masterCard2ndLevelIndicator;
    
    private String cvv2PresenceId;
    
    private String cvv2ResultCode;

    private String cavvResult;

    private String cavvResultCode;

    private String entryMode;

    private String posEntryMode;

    private String posIndicator;

    private String posDataCode;

    private String emvChipData;

    private String SerialNumber;

    private String InvoiceNumber;

    private String SystemTraceAuditNumber;

    private String ProductID;

    private String SupplierCode;

    private String AuthDate;

    private String AuthTime;

    private String TaxesFees;

    private String TokenizedCreditCard;

    private String ApplePayIndicator;

    private String TransactionDataConditionCode;

    private String TransactionIntegrityClass;

    private String Filler1;

    private String DCCIndicator;

    private String DCCType;

    private String DCCRate;

    private Integer DCCAmount;

    private Integer DCCDecimalPosition;

    private Integer DCCCurrency;

    private String DCCOfferDate;

    private String DCCOfferTime;

    private String DCCTimeZone;

    private String CustomerReferenceNumber;

    private String PSPReferenceNumber;

    private String MandateID;

    private String DirectoryServerTransactionID;

    private String Filler2;

    private String WorkingStorage;
    
    private String DocumentTypeData;
    
    private AirDetail airDetail;

    public String toJSON(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }      
        
    
    /**
     * @return the transactionType
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * @param documentNumber the documentNumber to set
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * @return the issueDateStr
     */
    public String getIssueDateStr() {
        return issueDateStr;
    }

    /**
     * @param issueDateStr the issueDateStr to set
     */
    public void setIssueDateStr(String issueDateStr) {
        this.issueDateStr = issueDateStr;
    }

    /**
     * @return the issueTimeStr
     */
    public String getIssueTimeStr() {
        return issueTimeStr;
    }

    /**
     * @param issueTimeStr the issueTimeStr to set
     */
    public void setIssueTimeStr(String issueTimeStr) {
        this.issueTimeStr = issueTimeStr;
    }

    /**
     * @return the pnrLocator
     */
    public String getPnrLocator() {
        return pnrLocator;
    }

    /**
     * @param pnrLocator the pnrLocator to set
     */
    public void setPnrLocator(String pnrLocator) {
        this.pnrLocator = pnrLocator;
    }

    /**
     * @return the sequenceNumber
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @param sequenceNumber the sequenceNumber to set
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @return the sequenceCount
     */
    public Integer getSequenceCount() {
        return sequenceCount;
    }

    /**
     * @param sequenceCount the sequenceCount to set
     */
    public void setSequenceCount(Integer sequenceCount) {
        this.sequenceCount = sequenceCount;
    }

    /**
     * @return the creditCardType
     */
    public String getCreditCardType() {
        return creditCardType;
    }

    /**
     * @param creditCardType the creditCardType to set
     */
    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @param creditCardNumber the creditCardNumber to set
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * @return the expireDateStr
     */
    public String getExpireDateStr() {
        return expireDateStr;
    }

    /**
     * @param expireDateStr the expireDateStr to set
     */
    public void setExpireDateStr(String expireDateStr) {
        this.expireDateStr = expireDateStr;
    }

    /**
     * @return the passengerName
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * @param passengerName the passengerName to set
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * @return the merchantNumber
     */
    public String getMerchantNumber() {
        return merchantNumber;
    }

    /**
     * @param merchantNumber the merchantNumber to set
     */
    public void setMerchantNumber(String merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    /**
     * @return the stationNumber
     */
    public Integer getStationNumber() {
        return stationNumber;
    }

    /**
     * @param stationNumber the stationNumber to set
     */
    public void setStationNumber(Integer stationNumber) {
        this.stationNumber = stationNumber;
    }

    /**
     * @return the posCity
     */
    public String getPosCity() {
        return posCity;
    }

    /**
     * @param posCity the posCity to set
     */
    public void setPosCity(String posCity) {
        this.posCity = posCity;
    }

    /**
     * @return the posState
     */
    public String getPosState() {
        return posState;
    }

    /**
     * @param posState the posState to set
     */
    public void setPosState(String posState) {
        this.posState = posState;
    }

    /**
     * @return the posCountry
     */
    public String getPosCountry() {
        return posCountry;
    }

    /**
     * @param posCountry the posCountry to set
     */
    public void setPosCountry(String posCountry) {
        this.posCountry = posCountry;
    }

    /**
     * @return the posZipCode
     */
    public String getPosZipCode() {
        return posZipCode;
    }

    /**
     * @param posZipCode the posZipCode to set
     */
    public void setPosZipCode(String posZipCode) {
        this.posZipCode = posZipCode;
    }

    /**
     * @return the channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * @param channelId the channelId to set
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * @return the internetIndicator
     */
    public String getInternetIndicator() {
        return internetIndicator;
    }

    /**
     * @param internetIndicator the internetIndicator to set
     */
    public void setInternetIndicator(String internetIndicator) {
        this.internetIndicator = internetIndicator;
    }

    /**
     * @return the airLineCode
     */
    public String getAirLineCode() {
        return airLineCode;
    }

    /**
     * @param airLineCode the airLineCode to set
     */
    public void setAirLineCode(String airLineCode) {
        this.airLineCode = airLineCode;
    }

    /**
     * @return the dbaName
     */
    public String getDbaName() {
        return dbaName;
    }

    /**
     * @param dbaName the dbaName to set
     */
    public void setDbaName(String dbaName) {
        this.dbaName = dbaName;
    }

    /**
     * @return the authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * @param authCode the authCode to set
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the payPlan
     */
    public String getPayPlan() {
        return payPlan;
    }

    /**
     * @param payPlan the payPlan to set
     */
    public void setPayPlan(String payPlan) {
        this.payPlan = payPlan;
    }

    /**
     * @return the authAmount
     */
    public Integer getAuthAmount() {
        return authAmount;
    }

    /**
     * @param authAmount the authAmount to set
     */
    public void setAuthAmount(Integer authAmount) {
        this.authAmount = authAmount;
    }

    /**
     * @return the authDecimalPosition
     */
    public String getAuthDecimalPosition() {
        return authDecimalPosition;
    }

    /**
     * @param authDecimalPosition the authDecimalPosition to set
     */
    public void setAuthDecimalPosition(String authDecimalPosition) {
        this.authDecimalPosition = authDecimalPosition;
    }

    /**
     * @return the authCurrency
     */
    public String getAuthCurrency() {
        return authCurrency;
    }

    /**
     * @param authCurrency the authCurrency to set
     */
    public void setAuthCurrency(String authCurrency) {
        this.authCurrency = authCurrency;
    }

    /**
     * @return the docAmount
     */
    public String getDocAmount() {
        return docAmount;
    }

    /**
     * @param docAmount the docAmount to set
     */
    public void setDocAmount(String docAmount) {
        this.docAmount = docAmount;
    }

    /**
     * @return the docDecimalPosition
     */
    public String getDocDecimalPosition() {
        return docDecimalPosition;
    }

    /**
     * @param docDecimalPosition the docDecimalPosition to set
     */
    public void setDocDecimalPosition(String docDecimalPosition) {
        this.docDecimalPosition = docDecimalPosition;
    }

    /**
     * @return the docCurrency
     */
    public String getDocCurrency() {
        return docCurrency;
    }

    /**
     * @param docCurrency the docCurrency to set
     */
    public void setDocCurrency(String docCurrency) {
        this.docCurrency = docCurrency;
    }

    /**
     * @return the billedAmount
     */
    public Integer getBilledAmount() {
        return billedAmount;
    }

    /**
     * @param billedAmount the billedAmount to set
     */
    public void setBilledAmount(Integer billedAmount) {
        this.billedAmount = billedAmount;
    }

    /**
     * @return the billedDecimalPosition
     */
    public String getBilledDecimalPosition() {
        return billedDecimalPosition;
    }

    /**
     * @param billedDecimalPosition the billedDecimalPosition to set
     */
    public void setBilledDecimalPosition(String billedDecimalPosition) {
        this.billedDecimalPosition = billedDecimalPosition;
    }

    /**
     * @return the billedCurrency
     */
    public String getBilledCurrency() {
        return billedCurrency;
    }

    /**
     * @param billedCurrency the billedCurrency to set
     */
    public void setBilledCurrency(String billedCurrency) {
        this.billedCurrency = billedCurrency;
    }

    /**
     * @return the terminalId
     */
    public Integer getTerminalId() {
        return terminalId;
    }

    /**
     * @param terminalId the terminalId to set
     */
    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * @return the eciIndicator
     */
    public String getEciIndicator() {
        return eciIndicator;
    }

    /**
     * @param eciIndicator the eciIndicator to set
     */
    public void setEciIndicator(String eciIndicator) {
        this.eciIndicator = eciIndicator;
    }

    /**
     * @return the authCharacteristicIndicator
     */
    public String getAuthCharacteristicIndicator() {
        return authCharacteristicIndicator;
    }

    /**
     * @param authCharacteristicIndicator the authCharacteristicIndicator to set
     */
    public void setAuthCharacteristicIndicator(String authCharacteristicIndicator) {
        this.authCharacteristicIndicator = authCharacteristicIndicator;
    }

    /**
     * @return the avsResultCode
     */
    public String getAvsResultCode() {
        return avsResultCode;
    }

    /**
     * @param avsResultCode the avsResultCode to set
     */
    public void setAvsResultCode(String avsResultCode) {
        this.avsResultCode = avsResultCode;
    }

    /**
     * @return the validationCode
     */
    public String getValidationCode() {
        return validationCode;
    }

    /**
     * @param validationCode the validationCode to set
     */
    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    /**
     * @return the authSourceCode
     */
    public String getAuthSourceCode() {
        return authSourceCode;
    }

    /**
     * @param authSourceCode the authSourceCode to set
     */
    public void setAuthSourceCode(String authSourceCode) {
        this.authSourceCode = authSourceCode;
    }

    /**
     * @return the authResponseCode
     */
    public String getAuthResponseCode() {
        return authResponseCode;
    }

    /**
     * @param authResponseCode the authResponseCode to set
     */
    public void setAuthResponseCode(String authResponseCode) {
        this.authResponseCode = authResponseCode;
    }

    /**
     * @return the threeDSIndicator
     */
    public String getThreeDSIndicator() {
        return threeDSIndicator;
    }

    /**
     * @param threeDSIndicator the threeDSIndicator to set
     */
    public void setThreeDSIndicator(String threeDSIndicator) {
        this.threeDSIndicator = threeDSIndicator;
    }

    /**
     * @return the masterCard2ndLevelIndicator
     */
    public String getMasterCard2ndLevelIndicator() {
        return masterCard2ndLevelIndicator;
    }

    /**
     * @param masterCard2ndLevelIndicator the masterCard2ndLevelIndicator to set
     */
    public void setMasterCard2ndLevelIndicator(String masterCard2ndLevelIndicator) {
        this.masterCard2ndLevelIndicator = masterCard2ndLevelIndicator;
    }

    /**
     * @return the cvv2PresenceId
     */
    public String getCvv2PresenceId() {
        return cvv2PresenceId;
    }

    /**
     * @param cvv2PresenceId the cvv2PresenceId to set
     */
    public void setCvv2PresenceId(String cvv2PresenceId) {
        this.cvv2PresenceId = cvv2PresenceId;
    }

    /**
     * @return the cvv2ResultCode
     */
    public String getCvv2ResultCode() {
        return cvv2ResultCode;
    }

    /**
     * @param cvv2ResultCode the cvv2ResultCode to set
     */
    public void setCvv2ResultCode(String cvv2ResultCode) {
        this.cvv2ResultCode = cvv2ResultCode;
    }

    /**
     * @return the cavvResult
     */
    public String getCavvResult() {
        return cavvResult;
    }

    /**
     * @param cavvResult the cavvResult to set
     */
    public void setCavvResult(String cavvResult) {
        this.cavvResult = cavvResult;
    }

    /**
     * @return the cavvResultCode
     */
    public String getCavvResultCode() {
        return cavvResultCode;
    }

    /**
     * @param cavvResultCode the cavvResultCode to set
     */
    public void setCavvResultCode(String cavvResultCode) {
        this.cavvResultCode = cavvResultCode;
    }

    /**
     * @return the entryMode
     */
    public String getEntryMode() {
        return entryMode;
    }

    /**
     * @param entryMode the entryMode to set
     */
    public void setEntryMode(String entryMode) {
        this.entryMode = entryMode;
    }

    /**
     * @return the posEntryMode
     */
    public String getPosEntryMode() {
        return posEntryMode;
    }

    /**
     * @param posEntryMode the posEntryMode to set
     */
    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    /**
     * @return the posIndicator
     */
    public String getPosIndicator() {
        return posIndicator;
    }

    /**
     * @param posIndicator the posIndicator to set
     */
    public void setPosIndicator(String posIndicator) {
        this.posIndicator = posIndicator;
    }

    /**
     * @return the posDataCode
     */
    public String getPosDataCode() {
        return posDataCode;
    }

    /**
     * @param posDataCode the posDataCode to set
     */
    public void setPosDataCode(String posDataCode) {
        this.posDataCode = posDataCode;
    }

    /**
     * @return the emvChipData
     */
    public String getEmvChipData() {
        return emvChipData;
    }

    /**
     * @param emvChipData the emvChipData to set
     */
    public void setEmvChipData(String emvChipData) {
        this.emvChipData = emvChipData;
    }

    /**
     * @return the SerialNumber
     */
    public String getSerialNumber() {
        return SerialNumber;
    }

    /**
     * @param SerialNumber the SerialNumber to set
     */
    public void setSerialNumber(String SerialNumber) {
        this.SerialNumber = SerialNumber;
    }

    /**
     * @return the InvoiceNumber
     */
    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    /**
     * @param InvoiceNumber the InvoiceNumber to set
     */
    public void setInvoiceNumber(String InvoiceNumber) {
        this.InvoiceNumber = InvoiceNumber;
    }

    /**
     * @return the SystemTraceAuditNumber
     */
    public String getSystemTraceAuditNumber() {
        return SystemTraceAuditNumber;
    }

    /**
     * @param SystemTraceAuditNumber the SystemTraceAuditNumber to set
     */
    public void setSystemTraceAuditNumber(String SystemTraceAuditNumber) {
        this.SystemTraceAuditNumber = SystemTraceAuditNumber;
    }

    /**
     * @return the ProductID
     */
    public String getProductID() {
        return ProductID;
    }

    /**
     * @param ProductID the ProductID to set
     */
    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    /**
     * @return the SupplierCode
     */
    public String getSupplierCode() {
        return SupplierCode;
    }

    /**
     * @param SupplierCode the SupplierCode to set
     */
    public void setSupplierCode(String SupplierCode) {
        this.SupplierCode = SupplierCode;
    }

    /**
     * @return the AuthDate
     */
    public String getAuthDate() {
        return AuthDate;
    }

    /**
     * @param AuthDate the AuthDate to set
     */
    public void setAuthDate(String AuthDate) {
        this.AuthDate = AuthDate;
    }

    /**
     * @return the AuthTime
     */
    public String getAuthTime() {
        return AuthTime;
    }

    /**
     * @param AuthTime the AuthTime to set
     */
    public void setAuthTime(String AuthTime) {
        this.AuthTime = AuthTime;
    }

    /**
     * @return the TaxesFees
     */
    public String getTaxesFees() {
        return TaxesFees;
    }

    /**
     * @param TaxesFees the TaxesFees to set
     */
    public void setTaxesFees(String TaxesFees) {
        this.TaxesFees = TaxesFees;
    }

    /**
     * @return the TokenizedCreditCard
     */
    public String getTokenizedCreditCard() {
        return TokenizedCreditCard;
    }

    /**
     * @param TokenizedCreditCard the TokenizedCreditCard to set
     */
    public void setTokenizedCreditCard(String TokenizedCreditCard) {
        this.TokenizedCreditCard = TokenizedCreditCard;
    }

    /**
     * @return the ApplePayIndicator
     */
    public String getApplePayIndicator() {
        return ApplePayIndicator;
    }

    /**
     * @param ApplePayIndicator the ApplePayIndicator to set
     */
    public void setApplePayIndicator(String ApplePayIndicator) {
        this.ApplePayIndicator = ApplePayIndicator;
    }

    /**
     * @return the TransactionDataConditionCode
     */
    public String getTransactionDataConditionCode() {
        return TransactionDataConditionCode;
    }

    /**
     * @param TransactionDataConditionCode the TransactionDataConditionCode to set
     */
    public void setTransactionDataConditionCode(String TransactionDataConditionCode) {
        this.TransactionDataConditionCode = TransactionDataConditionCode;
    }

    /**
     * @return the TransactionIntegrityClass
     */
    public String getTransactionIntegrityClass() {
        return TransactionIntegrityClass;
    }

    /**
     * @param TransactionIntegrityClass the TransactionIntegrityClass to set
     */
    public void setTransactionIntegrityClass(String TransactionIntegrityClass) {
        this.TransactionIntegrityClass = TransactionIntegrityClass;
    }

    /**
     * @return the Filler1
     */
    public String getFiller1() {
        return Filler1;
    }

    /**
     * @param Filler1 the Filler1 to set
     */
    public void setFiller1(String Filler1) {
        this.Filler1 = Filler1;
    }

    /**
     * @return the DCCIndicator
     */
    public String getDCCIndicator() {
        return DCCIndicator;
    }

    /**
     * @param DCCIndicator the DCCIndicator to set
     */
    public void setDCCIndicator(String DCCIndicator) {
        this.DCCIndicator = DCCIndicator;
    }

    /**
     * @return the DCCType
     */
    public String getDCCType() {
        return DCCType;
    }

    /**
     * @param DCCType the DCCType to set
     */
    public void setDCCType(String DCCType) {
        this.DCCType = DCCType;
    }

    /**
     * @return the DCCRate
     */
    public String getDCCRate() {
        return DCCRate;
    }

    /**
     * @param DCCRate the DCCRate to set
     */
    public void setDCCRate(String DCCRate) {
        this.DCCRate = DCCRate;
    }

    /**
     * @return the DCCAmount
     */
    public Integer getDCCAmount() {
        return DCCAmount;
    }

    /**
     * @param DCCAmount the DCCAmount to set
     */
    public void setDCCAmount(Integer DCCAmount) {
        this.DCCAmount = DCCAmount;
    }

    /**
     * @return the DCCDecimalPosition
     */
    public Integer getDCCDecimalPosition() {
        return DCCDecimalPosition;
    }

    /**
     * @param DCCDecimalPosition the DCCDecimalPosition to set
     */
    public void setDCCDecimalPosition(Integer DCCDecimalPosition) {
        this.DCCDecimalPosition = DCCDecimalPosition;
    }

    /**
     * @return the DCCCurrency
     */
    public Integer getDCCCurrency() {
        return DCCCurrency;
    }

    /**
     * @param DCCCurrency the DCCCurrency to set
     */
    public void setDCCCurrency(Integer DCCCurrency) {
        this.DCCCurrency = DCCCurrency;
    }

    /**
     * @return the DCCOfferDate
     */
    public String getDCCOfferDate() {
        return DCCOfferDate;
    }

    /**
     * @param DCCOfferDate the DCCOfferDate to set
     */
    public void setDCCOfferDate(String DCCOfferDate) {
        this.DCCOfferDate = DCCOfferDate;
    }

    /**
     * @return the DCCOfferTime
     */
    public String getDCCOfferTime() {
        return DCCOfferTime;
    }

    /**
     * @param DCCOfferTime the DCCOfferTime to set
     */
    public void setDCCOfferTime(String DCCOfferTime) {
        this.DCCOfferTime = DCCOfferTime;
    }

    /**
     * @return the DCCTimeZone
     */
    public String getDCCTimeZone() {
        return DCCTimeZone;
    }

    /**
     * @param DCCTimeZone the DCCTimeZone to set
     */
    public void setDCCTimeZone(String DCCTimeZone) {
        this.DCCTimeZone = DCCTimeZone;
    }

    /**
     * @return the CustomerReferenceNumber
     */
    public String getCustomerReferenceNumber() {
        return CustomerReferenceNumber;
    }

    /**
     * @param CustomerReferenceNumber the CustomerReferenceNumber to set
     */
    public void setCustomerReferenceNumber(String CustomerReferenceNumber) {
        this.CustomerReferenceNumber = CustomerReferenceNumber;
    }

    /**
     * @return the PSPReferenceNumber
     */
    public String getPSPReferenceNumber() {
        return PSPReferenceNumber;
    }

    /**
     * @param PSPReferenceNumber the PSPReferenceNumber to set
     */
    public void setPSPReferenceNumber(String PSPReferenceNumber) {
        this.PSPReferenceNumber = PSPReferenceNumber;
    }

    /**
     * @return the MandateID
     */
    public String getMandateID() {
        return MandateID;
    }

    /**
     * @param MandateID the MandateID to set
     */
    public void setMandateID(String MandateID) {
        this.MandateID = MandateID;
    }

    /**
     * @return the DirectoryServerTransactionID
     */
    public String getDirectoryServerTransactionID() {
        return DirectoryServerTransactionID;
    }

    /**
     * @param DirectoryServerTransactionID the DirectoryServerTransactionID to set
     */
    public void setDirectoryServerTransactionID(String DirectoryServerTransactionID) {
        this.DirectoryServerTransactionID = DirectoryServerTransactionID;
    }

    /**
     * @return the Filler2
     */
    public String getFiller2() {
        return Filler2;
    }

    /**
     * @param Filler2 the Filler2 to set
     */
    public void setFiller2(String Filler2) {
        this.Filler2 = Filler2;
    }

    /**
     * @return the WorkingStorage
     */
    public String getWorkingStorage() {
        return WorkingStorage;
    }

    /**
     * @param WorkingStorage the WorkingStorage to set
     */
    public void setWorkingStorage(String WorkingStorage) {
        this.WorkingStorage = WorkingStorage;
    }

    /**
     * @return the DocumentTypeData
     */
    public String getDocumentTypeData() {
        return DocumentTypeData;
    }

    /**
     * @param DocumentTypeData the DocumentTypeData to set
     */
    public void setDocumentTypeData(String DocumentTypeData) {
        this.DocumentTypeData = DocumentTypeData;
    }

    /**
     * @return the airDetail
     */
    public AirDetail getAirDetail() {
        return airDetail;
    }

    /**
     * @param airDetail the airDetail to set
     */
    public void setAirDetail(AirDetail airDetail) {
        this.airDetail = airDetail;
    }

}
