  /*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2022 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.functional.tests;

import com.google.gson.Gson;
import com.google.common.reflect.TypeToken;
import com.truteq.ccpgw.threeds.v2.objects.versioning.AcsProtocolVersionData;
import java.lang.reflect.Type;
import com.truteq.ccpgw.threeds.v2.objects.versioning.ThreeDSServerVersioningV3Response;
import java.util.Collection;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testVersioningV3 {
    
    public static void main(String[] args) {
    String v3Versioning ="[{\"threeDSServerTransID\":\"c9ae1bd0-b359-497d-a792-96f7e9c277ba\",\"schemeId\":\"Mastercard\",\"acsProtocolVersions\":[{\"version\":\"2.1.0\",\"threeDSMethodURL\":\"https://3dss.extranet.netcetera.biz/3dss-demo/acs/3ds-method\"},{\"version\":\"2.2.0\",\"threeDSMethodURL\":\"https://3dss.extranet.netcetera.biz/3dss-demo/acs/3ds-method\"}],\"dsProtocolVersions\":[\"2.1.0\",\"2.2.0\"],\"threeDSMethodDataForm\":{\"threeDSMethodData\":\"eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImM5YWUxYmQwLWIzNTktNDk3ZC1hNzkyLTk2ZjdlOWMyNzdiYSJ9\"},\"threeDSMethodData\":{\"threeDSMethodNotificationURL\":\"https://ccpgw.testbsp.com.pg/3dsmethodnotification\",\"threeDSServerTransID\":\"c9ae1bd0-b359-497d-a792-96f7e9c277ba\"},\"errorDetails\":null,\"isCardFoundIn2xRanges\":true,\"directoryServerID\":\"directoryServerID\"}]";
          
//String v3Versioning ="[{\n" +
//"  \"threeDSServerTransID\":\"8a880dc0-d2d2-4067-bcb1-b08d1690b26e\",\n" +
//"  \"schemeId\":\"Visa\",\n" +
//"  \"acsProtocolVersions\":[\n" +
//"    {\n" +
//"      \"version\":\"2.1.0\",\n" +
//"      \"acsInfoInd\":[\n" +
//"        \"01\",\n" +
//"        \"02\"\n" +
//"      ],\n" +
//"      \"threeDSMethodURL\":\"http://www.acs.com/script/1\",\n" +
//"      \"supportedMsgExt\":[\n" +
//"        {\n" +
//"          \"id\":\"A000000802-001\",\n" +
//"          \"version\":\"2.0\"\n" +
//"        },\n" +
//"        {\n" +
//"          \"id\":\"A000000802-004\",\n" +
//"          \"version\":\"1.0\"\n" +
//"        }\n" +
//"      ]\n" +
//"    },\n" +
//"    {\n" +
//"      \"version\":\"2.2.0\",\n" +
//"      \"acsInfoInd\":[\n" +
//"        \"01\",\n" +
//"        \"02\",\n" +
//"        \"03\"\n" +
//"      ],\n" +
//"      \"threeDSMethodURL\":\"http://www.acs.com/script/2\",\n" +
//"      \"supportedMsgExt\":[\n" +
//"        {\n" +
//"          \"id\":\"A000000802-002\",\n" +
//"          \"version\":\"1.0\"\n" +
//"        \n" +
//"        },\n" +
//"        {\n" +
//"          \"id\":\"A000000802-003\",\n" +
//"          \"version\":\"2.0\"\n" +
//"        \n" +
//"        }\n" +
//"      ]\n" +
//"    }\n" +
//"  ],\n" +
//"  \"dsProtocolVersions\":[\n" +
//"    \"2.1.0\",\n" +
//"    \"2.2.0\",\n" +
//"    \"2.3.0\"\n" +
//"  ],\n" +
//"  \"threeDSMethodDataForm\":{\n" +
//"    \"threeDSMethodData\":\"eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cDovL3d3dy4zZHMuY29tL25vdGlmaWNhdGlvbi11cmwiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjhhODgwZGMwLWQyZDItNDA2Ny1iY2IxLWIwOGQxNjkwYjI2ZSJ9\"\n" +
//"  },\n" +
//"  \"threeDSMethodData\":{\n" +
//"    \"threeDSMethodNotificationURL\":\"http://www.3ds.com/notification-url\",\n" +
//"    \"threeDSServerTransID\":\"8a880dc0-d2d2-4067-bcb1-b08d1690b26e\"\n" +
//"  },\n" +
//"  \"errorDetails\":null,\n" +
//"  \"isCardFoundIn2xRanges\":true,\n" +
//"  \"directoryServerID\":\"A000000003\"\n" +
//"}]";          
          
          Gson gson = new Gson();
          //ThreeDSServerVersioningV3Response versionResponse = gson.fromJson(v3Versioning, ThreeDSServerVersioningV3Response.class);
          Type collectionType = new TypeToken<Collection<ThreeDSServerVersioningV3Response>>(){}.getType();
          Collection<ThreeDSServerVersioningV3Response> versionResponse = gson.fromJson(v3Versioning, collectionType);

          Object[] obj = versionResponse.toArray();
          ThreeDSServerVersioningV3Response tdsVersion = (ThreeDSServerVersioningV3Response)obj[0];
          System.out.println(tdsVersion.toJSON());
          
          for (AcsProtocolVersionData acsProtocolVersionData : tdsVersion.getAcsProtocolVersions()){
              System.out.println(acsProtocolVersionData.getVersion()+" "+acsProtocolVersionData.getThreeDSMethodURL());
          }
    }
}
