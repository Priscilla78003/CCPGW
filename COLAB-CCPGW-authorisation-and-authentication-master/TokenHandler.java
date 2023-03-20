/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.microservice.security.token;

import com.truteq.microservice.security.oauth2.IOAuth2Constants;
import com.truteq.microservice.security.oauth2.OAuth2Parameters;
import com.truteq.microservice.security.oauth2.OAuth2Utils;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author <a href="mailto:grantboreilly@gmail.com"> Grant Blaise O'Reilly </a>
 */
public class TokenHandler implements IOAuth2Constants{
    
    private final OAuth2Utils oauth2Utils;
    private Map<String, String> tokenMap = null;
    private String accessToken = "";
    private String refreshToken = "";
    OAuth2Parameters oath2Parameters = null;
    
    public TokenHandler(OAuth2Parameters oath2Parameters){
        this.oath2Parameters = oath2Parameters;
        oauth2Utils = new OAuth2Utils();
    }
    
    /**
     * @return the oauth2Utils
     */
    public OAuth2Utils getOauth2Utils() {
        return oauth2Utils;
    }

    public void getTokens() throws IOException{
    	tokenMap = this.getOauth2Utils().getAccessToken(oath2Parameters);
        
        String tempAccessToken = getTokenMap().get(ACCESS_TOKEN);
        if (getOauth2Utils().isValid(tempAccessToken))
           accessToken = tempAccessToken;
        String tempRefreshToken = getTokenMap().get(REFRESH_TOKEN);
        if (getOauth2Utils().isValid(tempRefreshToken))
           refreshToken = tempRefreshToken;
    }
    
    /**
     * @return the tokenMap
     */
    public Map<String, String> getTokenMap() {
        return tokenMap;
    }

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }
      
}
