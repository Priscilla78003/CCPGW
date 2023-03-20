{"threeDSServerTransID":"c8c85f72-ffed-4853-ace3-6ce418894170","transStatus":"Y","authenticationValue":"JAmi21makAifmwqo2120cjq1AAA\u003d","eci":"01","resultsRequest":{"threeDSServerTransID":"c8c85f72-ffed-4853-ace3-6ce418894170","acsTransID":"3be4b26f-cb9f-4428-9a6d-d898405cca1b","acsRenderingType":{"acsInterface":"01","acsUiTemplate":"01"},"authenticationMethod":"02","authenticationType":"02","authenticationValue":"JAmi21makAifmwqo2120cjq1AAA\u003d","dsTransID":"5a43b51f-cb8b-4ed9-8ee3-9cf0375fa0ce","eci":"01","messageCategory":"01","messageType":"RReq","messageVersion":"2.1.0","transStatus":"Y"},"resultsResponse":{"threeDSServerTransID":"c8c85f72-ffed-4853-ace3-6ce418894170","acsTransID":"3be4b26f-cb9f-4428-9a6d-d898405cca1b","dsTransID":"5a43b51f-cb8b-4ed9-8ee3-9cf0375fa0ce","messageType":"RRes","messageVersion":"2.1.0","resultsStatus":"01"}}


insert into tds2_rreq(     
     threeDSServerTransID,
     acsTransID,
     acsRenderingType_acsInterface,
     acsRenderingType_acsUiTemplate,
     authenticationMethod,
     authenticationType,
     authenticationValue,
     dsTransID,
     eci,
     interactionCounter,
     messageCategory,
     messageType,
     messageVersion,
     transStatus) values ('c8c85f72-ffed-4853-ace3-6ce418894170','3be4b26f-cb9f-4428-9a6d-d898405cca1b','01','01','02','02','JAmi21makAifmwqo2120cjq1AAA\u003d','5a43b51f-cb8b-4ed9-8ee3-9cf0375fa0ce','01','','01','RReq','2.1.0','Y')

insert into tds2_rres(    
    threeDSServerTransID,
    acsTransID,
    dsTransID,
    messageType,
    messageVersion,
    resultsStatus) values ('c8c85f72-ffed-4853-ace3-6ce418894170','3be4b26f-cb9f-4428-9a6d-d898405cca1b','5a43b51f-cb8b-4ed9-8ee3-9cf0375fa0ce','RRes','2.1.0','01')  


insert into tds2_resultresponse(
     threeDSServerTransID,
     transStatus,
     authenticationValue,
     eci) values ('c8c85f72-ffed-4853-ace3-6ce418894170','Y','JAmi21makAifmwqo2120cjq1AAA\u003d','01')


insert into tds2_requestor(
    threeDSServerTransID,
    threeDSRequestorAuthenticationInd,
    threeDSReqAuthMethod,
    threeDSReqAuthTimestamp,
    threeDSReqAuthData, 
    threeDSRequestorChallengeInd,
    threeDSReqPriorRef,
    threeDSReqPriorAuthMethod,
    threeDSReqPriorAuthTimestamp,
    threeDSReqPriorAuthData,
    threeDSRequestorDecReqInd,
    threeDSRequestorDecMaxTime) values ('1234567890','02','04','202206021122','threeDSReqAuthData','03','VOGXpZvTlCmBUyPnnZfmsGDKqxRsRwPovkAE','01','202206021122','threeDSReqPriorAuthData','N',10080);

insert into tds2_cardholder( threeDSServerTransID, addrMatch,
  billAddrCountry, billAddrLine1,billAddrLine2,billAddrLine3,billAddrPostCode,billAddrState,email,homephonecc,homephonesub,mobilephonecc,mobilephonesub,workphonecc,workphonesub,
  cardholderName,shipAddrCity,shipAddrCountry,shipAddrLine1,shipAddrLine2,shipAddrLine3,shipAddrPostCode,shipAddrState) values('1234567890','N','','','','','','','','1','123','1','123','1','123','Grant O\u0027Reilly','','','','','','','');    
  
insert into tds2_cardholderaccount(             
    threeDSServerTransID,
    acctType,
    cardExpiryDate,
    acctNumber,
    schemeId,
    acctID,
    payTokenInd,
    chAccAgeInd,
    chAccDate,
    chAccChangeInd,
    chAccChange,
    chAccPwChangeInd,
    chAccPwChange,
    shipAddressUsageInd,
    shipAddressUsage,
    txnActivityDay,
    txnActivityYear,
    provisionAttemptsDay,
    nbPurchaseAccount,
    suspiciousAccActivity,
    shipNameIndicator,
    paymentAccInd,
    paymentAccAge) values('1234567890','02','1812','4000001000000005','Visa','','true','04','0181220','03','20181220','04','20181220','03','20181220',1,1,1,1,'01','01','03','20181220');

   
insert into tds2_purchase (
    threeDSServerTransID,
    purchaseInstalData,
    purchaseAmount,
    purchaseCurrency,
    ppurchaseExponent,
    purchaseDate,
    recurringExpiry,
    recurringFrequency,
    transType,
    shipIndicator,
    deliveryTimeframe,
    deliveryEmailAddress,
    reorderItemsInd,
    preOrderPurchaseInd,
    preOrderDate,
    giftCardAmount,
    giftCardCurr,
    giftCardCount) values('1234567890',3,1,'978',1,'20181220173550','20181220',1,'01','01','02','netcetera@example.com','01','01','20181220',2,'978',1)
    

insert into tds2_merchant (threeDSServerTransID,
  merchantConfigurationId,
  mcc,
  merchantCountryCode,
  merchantName,
  notificationURL,
  threeDSRequestorId,
  threeDSRequestorName,
  whiteListStatus) values('1234567890','','4511','598','IBE SALES PGK','https://ccpgw.testbsp.com.pg/3dsmethodnotification','ds-assigned-requestor-id','ds-assigned-requestor-name','Y');
  
  
insert into tds2_browser (threeDSServerTransID,  
  browserAcceptHeader,
  browserIP,
  browserJavaEnabled,
  browserLanguage,
  browserColorDepth,
  browserScreenHeight,
  browserScreenWidth,
  browserTZ,
  browserUserAgent,
  challengeWindowSize,
  browserJavascriptEnabled) values('1234567890','application/json','192.168.1.11','true','en','8',1,1,1,'Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0','01','true');
  
insert into tds2_sdk (threeDSServerTransID,
  sdkAppID,
  sdkEncData,
  sdkEphemPubKey,
  sdkMaxTimeout,
  sdkReferenceNumber,
  sdkTransID) values('1234567890','90d9b629-e639-4188-82d8-09e5c508bc86','enc-data','kty:EC,crv:P-256,x:test,y:test',5,'ref-num','7f101033-df46-4f5c-9e96-9575c924e1e7');
  
insert into tds2_devicerender (threeDSServerTransID,sdkInterface,sdkUiType) values('1234567890','01','02,03');
    
insert into tds2_broad (threeDSServerTransID,message) values('1234567890','TLS 1.x will be turned off starting summer 2019');

insert into tds2_acquirer (threeDSServerTransID,acquirerBin,acquirerMerchantId) values('1234567890','428280','Visa');
  

insert into tds2_authreq( 
threeDSServerTransID,preferredProtocolVersion,enforcePreferredProtocolVersion,deviceChannel,messageCategory,threeDSCompInd,threeDSRequestorURL,threeRIInd) values('1234567890','2.1.0','false','02','01','Y','https://3dss.extranet.netcetera.biz/3dss-demo/acs/3ds-method','');
