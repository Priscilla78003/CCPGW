CREATE TABLE tds2_transaction(
    threeDSServerTransID VARCHAR(150) PRIMARY KEY,
    order_id VARCHAR(150),
    transaction_id VARCHAR(150),
    payload TEXT(1000)
);


CREATE TABLE tds2_enrolled(
   threeDSServerTransID VARCHAR(150) PRIMARY KEY,
   enrolled VARCHAR(2),
   acctNumber VARCHAR(25),
   schemeId VARCHAR(15)
)

CREATE TABLE tds2_rreq(
     threeDSServerTransID VARCHAR(150) PRIMARY KEY,
     acsTransID VARCHAR(150),
     acsRenderingType_acsInterface VARCHAR(10),
     acsRenderingType_acsUiTemplate VARCHAR(10),
     authenticationMethod VARCHAR(10),
     authenticationType VARCHAR(10),
     authenticationValue VARCHAR(150),
     dsTransID VARCHAR(150),
     eci VARCHAR(10),
     interactionCounter VARCHAR(10),
     messageCategory VARCHAR(10),
     messageType VARCHAR(10),
     messageVersion VARCHAR(10),
     transStatus VARCHAR(10)     
)

CREATE TABLE tds2_rres(
    threeDSServerTransID VARCHAR(150) PRIMARY KEY,
    acsTransID VARCHAR(150),
    dsTransID VARCHAR(150),
    messageType VARCHAR(10),
    messageVersion VARCHAR(10),
    resultsStatus VARCHAR(10)    
)

CREATE TABLE tds2_resultresponse(
     threeDSServerTransID VARCHAR(150) PRIMARY KEY,
     transStatus VARCHAR(10),
     authenticationValue VARCHAR(150),
     eci VARCHAR(10),
     CONSTRAINT tds2_rreq_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_rreq (threeDSServerTransID),
     CONSTRAINT tds2_rres_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_rres (threeDSServerTransID),     
)

CREATE TABLE tds2_transaction(
    threeDSServerTransID VARCHAR(150) PRIMARY KEY,
    md VARCHAR(100),
    termUrl VARCHAR(250),
    creq TEXT(500),
    cres TEXT(500)
);


CREATE TABLE tds2_requestor(
    threeDSServerTransID VARCHAR(150) PRIMARY KEY,
    threeDSRequestorAuthenticationInd  VARCHAR(5),
    threeDSReqAuthMethod  VARCHAR(5),
    threeDSReqAuthTimestamp VARCHAR(15),
    threeDSReqAuthData  VARCHAR(100), 
    threeDSRequestorChallengeInd  VARCHAR(5),
    threeDSReqPriorRef VARCHAR(150),
    threeDSReqPriorAuthMethod   VARCHAR(5),
    threeDSReqPriorAuthTimestamp   VARCHAR(15),
    threeDSReqPriorAuthData  VARCHAR(100),
    threeDSRequestorDecReqInd  VARCHAR(5),
    threeDSRequestorDecMaxTime INT       
);

CREATE TABLE tds2_cardholder(
  threeDSServerTransID VARCHAR(150) PRIMARY KEY,
  addrMatch VARCHAR(5),
  billAddrCity VARCHAR(50),
  billAddrCountry VARCHAR(50),
  billAddrLine1 VARCHAR(150),
  billAddrLine2 VARCHAR(150),
  billAddrLine3 VARCHAR(150),
  billAddrPostCode VARCHAR(10),
  billAddrState VARCHAR(50),
  email VARCHAR(100),
  homephonecc  VARCHAR(10),
  homephonesub VARCHAR(10),
  mobilephonecc VARCHAR(10),
  mobilephonesub VARCHAR(10),
  workphonecc VARCHAR(10),
  workphonesub VARCHAR(10),
  cardholderName VARCHAR(100),
  shipAddrCity VARCHAR(50),
  shipAddrCountry VARCHAR(50),
  shipAddrLine1 VARCHAR(150),
  shipAddrLine2 VARCHAR(150),
  shipAddrLine3 VARCHAR(150),
  shipAddrPostCode VARCHAR(10),
  shipAddrState VARCHAR(50)
);

CREATE TABLE tds2_cardholderaccount(
   threeDSServerTransID VARCHAR(150) PRIMARY KEY,
   acctType VARCHAR(5),
   cardExpiryDate VARCHAR(5),
   acctNumber VARCHAR(25),
   schemeId VARCHAR(15),
   acctID VARCHAR(25),
   payTokenInd VARCHAR(5),
   chAccAgeInd VARCHAR(5),
   chAccDate VARCHAR(15),
   chAccChangeInd VARCHAR(5),
   chAccChange VARCHAR(15),
   chAccPwChangeInd VARCHAR(5),
   chAccPwChange VARCHAR(15),
   shipAddressUsageInd VARCHAR(5),
   shipAddressUsage VARCHAR(15),
   txnActivityDay VARCHAR(5),
   txnActivityYear VARCHAR(5),
   provisionAttemptsDay VARCHAR(5),
   nbPurchaseAccount VARCHAR(5),
   suspiciousAccActivity VARCHAR(5),
   shipNameIndicator VARCHAR(5),
   paymentAccInd VARCHAR(5),
   paymentAccAge VARCHAR(15)  
);

CREATE TABLE tds2_purchase(
    threeDSServerTransID VARCHAR(150) PRIMARY KEY,
    purchaseInstalData INT,
    purchaseAmount BIGINT,
    purchaseCurrency VARCHAR(5),
    ppurchaseExponent INT,
    purchaseDate VARCHAR(20),
    recurringExpiry VARCHAR(10),
    recurringFrequency INT,
    transType  VARCHAR(5),
    shipIndicator VARCHAR(5),
    deliveryTimeframe VARCHAR(5),
    deliveryEmailAddress VARCHAR(50),
    reorderItemsInd VARCHAR(5),
    preOrderPurchaseInd VARCHAR(5),
    preOrderDate VARCHAR(20),
    giftCardAmount INT,
    giftCardCurr  VARCHAR(5),
    giftCardCount INT
);

CREATE TABLE tds2_acquirer(
   threeDSServerTransID VARCHAR(150) PRIMARY KEY,
   acquirerBin VARCHAR(25),
   acquirerMerchantId VARCHAR(25)
);

CREATE TABLE tds2_merchant(
  threeDSServerTransID VARCHAR(150) PRIMARY KEY,
  merchantConfigurationId VARCHAR(25),
  mcc VARCHAR(15),
  merchantCountryCode VARCHAR(5),
  merchantName VARCHAR(50),
  notificationURL VARCHAR(50),
  threeDSRequestorId VARCHAR(100),
  threeDSRequestorName VARCHAR(100),
  whiteListStatus VARCHAR(5),
  resultsResponseNotificationUrl VARCHAR(50)
);

CREATE TABLE tds2_broad(
    threeDSServerTransID VARCHAR(150) PRIMARY KEY,
    message VARCHAR(250)
);

CREATE TABLE tds2_browser(
  threeDSServerTransID VARCHAR(150) PRIMARY KEY,
  browserAcceptHeader VARCHAR(50),
  browserIP VARCHAR(50),
  browserJavaEnabled VARCHAR(5),
  browserLanguage VARCHAR(5),
  browserColorDepth VARCHAR(5),
  browserScreenHeight INT,
  browserScreenWidth INT,
  browserTZ INT,
  browserUserAgent VARCHAR(250),
  challengeWindowSize VARCHAR(5),
  browserJavascriptEnabled VARCHAR(5)
);

CREATE TABLE tds2_sdk(
  threeDSServerTransID VARCHAR(150) PRIMARY KEY,
  sdkAppID VARCHAR(150),
  sdkEncData VARCHAR(150),
  sdkEphemPubKey VARCHAR(100),
  sdkMaxTimeout INT,
  sdkReferenceNumber VARCHAR(15),
  sdkTransID VARCHAR(150)
);

CREATE TABLE tds2_devicerender(
    threeDSServerTransID VARCHAR(150) PRIMARY KEY,
    sdkInterface VARCHAR(5),
    sdkUiType VARCHAR(50)
);


CREATE TABLE tds2_authreq(
    threeDSServerTransID VARCHAR(150) PRIMARY KEY,
    preferredProtocolVersion  VARCHAR(10),
    enforcePreferredProtocolVersion VARCHAR(5),
    deviceChannel VARCHAR(5),
    messageCategory  VARCHAR(10),
    threeDSCompInd  VARCHAR(5),
    threeDSRequestorURL VARCHAR(100),
    threeRIInd VARCHAR(5),
    messageExtension VARCHAR(150),
    challengeMessageExtension VARCHAR(150),
    CONSTRAINT tds2_requestor_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_requestor (threeDSServerTransID),
    CONSTRAINT tds2_cardholder_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_cardholder (threeDSServerTransID),
    CONSTRAINT tds2_cardholderaccount_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_cardholderaccount (threeDSServerTransID),
    CONSTRAINT tds2_purchase_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_purchase (threeDSServerTransID),
    CONSTRAINT tds2_merchant_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_merchant (threeDSServerTransID),
    CONSTRAINT tds2_browser_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_browser (threeDSServerTransID),
    CONSTRAINT tds2_sdk_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_sdk (threeDSServerTransID),
    CONSTRAINT tds2_devicerender_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_devicerender (threeDSServerTransID),
    CONSTRAINT tds2_broad_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_broad (threeDSServerTransID),
    CONSTRAINT tds2_acquirer_fk FOREIGN KEY (threeDSServerTransID) REFERENCES tds2_acquirer (threeDSServerTransID)
);


create view vw_authreq as
(SELECT a.threeDSServerTransID,a.preferredProtocolVersion,a.enforcePreferredProtocolVersion,a.deviceChannel,a.messageCategory,a.threeDSCompInd,a.threeDSRequestorURL,a.threeRIInd,a.messageExtension,a.challengeMessageExtension, 
b.threeDSRequestorAuthenticationInd,b.threeDSReqAuthMethod,b.threeDSReqAuthTimestamp,b.threeDSReqAuthData,b.threeDSRequestorChallengeInd,b.threeDSReqPriorRef,b.threeDSReqPriorAuthMethod,b.threeDSReqPriorAuthTimestamp,b.threeDSReqPriorAuthData,b.threeDSRequestorDecReqInd,b.threeDSRequestorDecMaxTime,
c.addrMatch,c.billAddrCity,c.billAddrCountry,c.billAddrLine1,c.billAddrLine2,c.billAddrLine3,c.billAddrPostCode,c.billAddrState,c.email,c.homephonecc,c.homephonesub,c.mobilephonecc,c.mobilephonesub,c.workphonecc,c.workphonesub,c.cardholderName,c.shipAddrCity,c.shipAddrCountry,c.shipAddrLine1,c.shipAddrLine2,c.shipAddrLine3,c.shipAddrPostCode,c.shipAddrState,
d.acctType,d.cardExpiryDate,d.acctNumber,d.schemeId,d.acctID,d.payTokenInd,d.chAccAgeInd,d.chAccDate,d.chAccChangeInd,d.chAccChange,d.chAccPwChangeInd,d.chAccPwChange,d.shipAddressUsageInd,d.shipAddressUsage,d.txnActivityDay,d.txnActivityYear,d.provisionAttemptsDay,d.nbPurchaseAccount,d.suspiciousAccActivity,d.shipNameIndicator,d.paymentAccInd,d.paymentAccAge,
e.purchaseInstalData,e.purchaseAmount,e.purchaseCurrency,e.ppurchaseExponent,e.purchaseDate,e.recurringExpiry,e.recurringFrequency,e.transType,e.shipIndicator,e.deliveryTimeframe,e.deliveryEmailAddress,e.reorderItemsInd,e.preOrderPurchaseInd,e.preOrderDate,e.giftCardAmount,e.giftCardCurr,e.giftCardCount,
f.merchantConfigurationId,f.mcc,f.merchantCountryCode,f. merchantName,f.notificationURL,f.threeDSRequestorId,f.threeDSRequestorName,f.whiteListStatus,
g.browserAcceptHeader,g.browserIP,g.browserJavaEnabled,g.browserLanguage,g.browserColorDepth,g.browserScreenHeight,g.browserScreenWidth,g.browserTZ,g.browserUserAgent,g.challengeWindowSize,g.browserJavascriptEnabled,
h.sdkAppID,h.sdkEncData,h.sdkEphemPubKey,h.sdkMaxTimeout,h.sdkReferenceNumber,h.sdkTransID,
i.sdkInterface,i.sdkUiType,
j.message,
k.acquirerBin,k.acquirerMerchantId
FROM tds2_authreq a
INNER JOIN tds2_requestor b on a.threeDSServerTransID = b.threeDSServerTransID
INNER JOIN tds2_cardholder c on a.threeDSServerTransID = c.threeDSServerTransID
INNER JOIN tds2_cardholderaccount d on a.threeDSServerTransID = d.threeDSServerTransID
INNER JOIN tds2_purchase e on a.threeDSServerTransID = e.threeDSServerTransID
INNER JOIN tds2_merchant f on a.threeDSServerTransID = f.threeDSServerTransID
INNER JOIN tds2_browser g on a.threeDSServerTransID = g.threeDSServerTransID
INNER JOIN tds2_sdk h on a.threeDSServerTransID = h.threeDSServerTransID
INNER JOIN tds2_devicerender i on a.threeDSServerTransID = i.threeDSServerTransID
INNER JOIN tds2_broad j on a.threeDSServerTransID = j.threeDSServerTransID
INNER JOIN tds2_acquirer k on a.threeDSServerTransID = k.threeDSServerTransID
WHERE a.threeDSServerTransID = '1234567890') 

create view vw_resultresponse as
(select a.threeDSServerTransID,a.transStatus,a.authenticationValue,a.eci,b.acsTransID,
     b.acsRenderingType_acsInterface,
     b.acsRenderingType_acsUiTemplate,
     b.authenticationMethod,
     b.authenticationType,
     b.authenticationValue as req_authenticationValue,
     b.dsTransID as req_dsTransID,
     b.eci as req_eci,
     b.interactionCounter,
     b.messageCategory,
     b.messageType as req_messageType,
     b.messageVersion as req_Version,
     b.transStatus as req_transStatus,
     c.acsTransID as res_acsTransID,
     c.dsTransID as res_dsTransID,
     c.messageType as res_messageType,
     c.messageVersion as res_messageVersion,
     c.resultsStatus 
from tds2_resultresponse a
inner join tds2_rreq b on a.threeDSServerTransID = b.threeDSServerTransID
inner join tds2_rres c on a.threeDSServerTransID = c.threeDSServerTransID)                        
