
import com.google.gson.Gson;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerAuthenticationRequest;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerTransaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testURLEncode {
    public static void main(String[] args) {
        String threeDSMethodData = "eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjQzOTJkNTIxLTMzMTYtNDFkMC1hZTZiLTA3MmY0MWQxYjk4YiIsInRocmVlRFNNZXRob2ROb3RpZmljYXRpb25VUkwiOiJodHRwczovL2NjcGd3LmJzcHBheS5jb20ucGcvM2RzbWV0aG9kbm90aWZpY2F0aW9uIn0=";
        String threeDSServerTransID ="4392d521-3316-41d0-ae6b-072f41d1b98b";
        String md = "4001PGK00000005";
        String termUrl = "https://ipe-pmt.prod.sabre.com/ipe/3DS?merchantReference\u003d01551666822542035342\u0026merchantId\u003dPX";
        
        ThreeDSServerTransaction tdsTransaction = new ThreeDSServerTransaction(threeDSServerTransID,
                                                                               md,
                                                                               termUrl ,
                                                                               threeDSMethodData,
                                                                               null);
        System.out.println("threeDSMethodData : "+threeDSMethodData);
        System.out.println("Persisting ThreeDSServerTransaction : "+tdsTransaction.toJSON());
        
        
        String result = "{\"preferredProtocolVersion\":\"2.1.0\",\"enforcePreferredProtocolVersion\":false,\"deviceChannel\":\"02\",\"messageCategory\":\"01\",\"threeDSCompInd\":\"Y\",\"threeDSRequestor\":{\"threeDSRequestorAuthenticationInd\":\"02\",\"threeDSRequestorAuthenticationInfo\":{\"threeDSReqAuthMethod\":\"04\",\"threeDSReqAuthTimestamp\":\"202210270815\",\"threeDSReqAuthData\":\"threeDSReqAuthData\"},\"threeDSRequestorChallengeInd\":\"03\",\"threeDSRequestorPriorAuthenticationInfo\":{\"threeDSReqPriorRef\":null,\"threeDSReqPriorAuthMethod\":\"01\",\"threeDSReqPriorAuthTimestamp\":\"202210270815\",\"threeDSReqPriorAuthData\":\"threeDSReqPriorAuthData\"},\"threeDSRequestorDecReqInd\":\"N\",\"threeDSRequestorDecMaxTime\":10080},\"threeDSServerTransID\":\"4392d521-3316-41d0-ae6b-072f41d1b98b\",\"threeDSRequestorURL\":null,\"cardholderAccount\":{\"acctType\":\"02\",\"cardExpiryDate\":\"2503\",\"acctInfo\":{\"chAccAgeInd\":\"04\",\"chAccDate\":\"20181220\",\"chAccChangeInd\":\"03\",\"chAccChange\":\"20181220\",\"chAccPwChangeInd\":\"04\",\"chAccPwChange\":\"20181220\",\"shipAddressUsageInd\":\"03\",\"shipAddressUsage\":\"20181220\",\"txnActivityDay\":\"1\",\"txnActivityYear\":\"1\",\"provisionAttemptsDay\":\"1\",\"nbPurchaseAccount\":\"1\",\"suspiciousAccActivity\":\"01\",\"shipNameIndicator\":\"01\",\"paymentAccInd\":\"03\",\"paymentAccAge\":\"20181220\"},\"acctNumber\":\"4715722001633491\",\"schemeId\":\"Visa\",\"acctID\":\"\",\"payTokenInd\":true},\"cardholder\":{\"addrMatch\":\"N\",\"billAddrCity\":\"\",\"billAddrCountry\":\"\",\"billAddrLine1\":\"\",\"billAddrLine2\":\"\",\"billAddrLine3\":\"\",\"billAddrPostCode\":\"\",\"billAddrState\":\"\",\"email\":\"\",\"homePhone\":{\"cc\":\"1\",\"subscriber\":\"123\"},\"mobilePhone\":{\"cc\":\"1\",\"subscriber\":\"123\"},\"workPhone\":{\"cc\":\"1\",\"subscriber\":\"123\"},\"cardholderName\":\"Rishan Gounder\",\"shipAddrCity\":\"\",\"shipAddrCountry\":\"\",\"shipAddrLine1\":\"\",\"shipAddrLine2\":\"\",\"shipAddrLine3\":\"\",\"shipAddrPostCode\":\"\",\"shipAddrState\":\"\"},\"purchase\":{\"purchaseInstalData\":3,\"merchantRiskIndicator\":{\"shipIndicator\":\"\",\"deliveryTimeframe\":\"\",\"deliveryEmailAddress\":\"\",\"reorderItemsInd\":\"\",\"preOrderPurchaseInd\":\"\",\"preOrderDate\":\"\",\"giftCardAmount\":0,\"giftCardCurr\":\"\",\"giftCardCount\":0},\"purchaseAmount\":75840,\"purchaseCurrency\":\"598\",\"purchaseExponent\":2,\"purchaseDate\":\"20221027081544\",\"recurringExpiry\":\"20221027\",\"recurringFrequency\":1,\"transType\":\"01\"},\"acquirer\":{\"acquirerBin\":\"428280\",\"acquirerMerchantId\":\"4001PGK00000005\"},\"merchant\":{\"merchantConfigurationId\":\"\",\"mcc\":\"4511\",\"merchantCountryCode\":\"598\",\"merchantName\":\"IBE SALES PGK\",\"notificationURL\":null,\"threeDSRequestorId\":\"ds-assigned-requestor-id\",\"threeDSRequestorName\":\"ds-assigned-requestor-name\",\"whiteListStatus\":\"Y\",\"resultsResponseNotificationUrl\":null},\"broadInfo\":{\"message\":\"TLS 1.x will be turned off starting summer 2019\"},\"messageExtension\":[{\"name\":\"id:name:false:com.truteq.ccpgw.threeds.v2.objects.authentication.Data@1d2021a8\",\"id\":\"id\",\"criticalityIndicator\":null,\"data\":null}],\"challengeMessageExtension\":null,\"browserInformation\":{\"browserAcceptHeader\":\"application/json\",\"browserIP\":\"192.168.1.11\",\"browserJavaEnabled\":true,\"browserLanguage\":\"en\",\"browserColorDepth\":\"8\",\"browserScreenHeight\":1,\"browserScreenWidth\":1,\"browserTZ\":1,\"browserUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0\",\"challengeWindowSize\":\"01\",\"browserJavascriptEnabled\":null},\"threeRIInd\":null,\"sdkInformation\":{\"sdkAppID\":\"\",\"sdkEncData\":\"\",\"sdkEphemPubKey\":{\"crv=P-256\":null,\"x=test\":null,\"{kty=EC\":null,\"y=test}\":null},\"sdkMaxTimeout\":0,\"sdkReferenceNumber\":\"\",\"sdkTransID\":\"\"}}";
        Gson gson = new Gson();
        ThreeDSServerAuthenticationRequest AuthReq = gson.fromJson(result,ThreeDSServerAuthenticationRequest.class);
        
        System.out.println("The ThreeDSRequestorURL (ACS): "+AuthReq.getThreeDSRequestorURL().toString());
        System.out.println(AuthReq.toJSON());
        
    }
}
