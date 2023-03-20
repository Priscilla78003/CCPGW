/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.microservice.security.oauth2;

/**
 *
 * @author <a href="mailto:grantboreilly@gmail.com"> Grant Blaise O'Reilly </a>
 */
public interface IOAuth2Constants {

	public static final String ACCESS_TOKEN = "access_token";
	public static final String REFRESH_TOKEN = "refresh_token";

	public static final String CONFIG_FILE_PATH = "com/ibm/oauth/Oauth2Client.config";
	public static final String RESOURCE_SERVER_URL = "resource_server_url";
	
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer";
	public static final String BASIC = "Basic";
	public static final String JSON_CONTENT = "application/json";
	public static final String XML_CONTENT = "application/xml";
	public static final String URL_ENCODED_CONTENT = "application/x-www-form-urlencoded";
	
	public static final int HTTP_OK = 200;
	public static final int HTTP_FORBIDDEN = 403;
	public static final int HTTP_UNAUTHORIZED = 401;   
        
        
// These parameter values are covered by OAuth2Parameters        
//      public static final String CLIENT_ID = "dep-employee-service";
//	public static final String CLIENT_SECRET = "d5107494-5214-4c34-ab6e-1043541b425b";
//      public static final String USERNAME = "grant";
//	public static final String PASSWORD = "admin";
//      public static final String AUTHENTICATION_SERVER_URL = "http://192.168.30.191:8080/auth/realms/dev/protocol/openid-connect/token";
//      public static final String GRANT_TYPE = "password";//"authorization_Code"; //"grant_type";
//	public static final String SCOPE = "openid";        
}
