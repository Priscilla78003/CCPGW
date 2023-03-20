/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.microservice.security.access;

import com.google.gson.Gson;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.microservice.security.oauth2.OAuth2Parameters;
import com.truteq.microservice.security.oauth2.OAuth2Utils;
import com.truteq.microservice.security.token.TokenHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import javax.servlet.http.HttpSession;

/**
 *
 * @author <a href="mailto:grantboreilly@gmail.com"> Grant Blaise O'Reilly </a>
 */
public class MicroServiceHandler {

    private static final int ACCESS_DURATION = 300; //seconds
    //private static final org.apache.log4j.Logger mLogger = org.apache.log4j.Logger.getLogger(MicroServiceHandler.class);
    private LogWrapper mLogger = new LogWrapper(OAuth2Utils.class);

    HttpSession httpSession;
    String microserviceUrl;
    String keyCloakServerOpenIdConnectToken;
    String clientid;
    String clientSecret;
    String username;
    String password;

    public MicroServiceHandler() {

    }

    public MicroServiceHandler(HttpSession httpSession,
            String microserviceUrl,
            String keyCloakServerOpenIdConnectToken,
            String clientid,
            String clientSecret,
            String username,
            String password
    ) {
        this.httpSession = httpSession;
        this.microserviceUrl = microserviceUrl;
        this.keyCloakServerOpenIdConnectToken = keyCloakServerOpenIdConnectToken;
        this.clientid = clientid;
        this.clientSecret = clientSecret;
        this.username = username;
        this.password = password;
    }

    public String callMicroService() throws IOException {

        String token = (String) httpSession.getAttribute("access_token");
        String userName = (String) httpSession.getAttribute("user_name");

        if ((token == null) || (!userName.equals(this.username))) {
            TokenHandler tokenHandler = new TokenHandler(new OAuth2Parameters(this.clientid,
                    this.clientSecret,
                    this.username,
                    this.password,
                    keyCloakServerOpenIdConnectToken,
                    "password",
                    "openid"));
            System.out.println("clientid = " + this.clientid + "clientSecret = " + this.clientSecret + "username = " + this.username + "password = " + this.password + "keyCloakServerOpenIdConnectToken = " + this.keyCloakServerOpenIdConnectToken);
            tokenHandler.getTokens();

            httpSession.setAttribute("access_token", tokenHandler.getAccessToken());
            httpSession.setAttribute("start_time", Calendar.getInstance());
            httpSession.setAttribute("user_name", this.username);

            return this.Access(this.microserviceUrl, (String) httpSession.getAttribute("access_token"));
        } else {

            long duration = ((Calendar.getInstance().getTimeInMillis() - ((Calendar) httpSession.getAttribute("start_time")).getTimeInMillis())) / 1000;
            if (duration <= ACCESS_DURATION) {
                mLogger.info("Duration: " + duration);
                return this.Access(this.microserviceUrl, (String) httpSession.getAttribute("access_token"));
            } else {
                TokenHandler tokenHandler = new TokenHandler(new OAuth2Parameters(this.clientid,
                        this.clientSecret,
                        this.username,
                        this.password,
                        keyCloakServerOpenIdConnectToken,
                        "password",
                        "openid"));
                tokenHandler.getTokens();
                httpSession.setAttribute("access_token", tokenHandler.getAccessToken());
                httpSession.setAttribute("start_time", Calendar.getInstance());

                return this.Access(this.microserviceUrl, (String) httpSession.getAttribute("access_token"));
            }
        }
    }

    public String callMicroService(byte[] b) throws IOException {

        String token = (String) httpSession.getAttribute("access_token");
        String userName = (String) httpSession.getAttribute("user_name");

        if (token == null) {
            TokenHandler tokenHandler = new TokenHandler(new OAuth2Parameters(this.clientid,
                    this.clientSecret,
                    this.username,
                    this.password,
                    keyCloakServerOpenIdConnectToken,
                    "password",
                    "openid"));

            tokenHandler.getTokens();

            httpSession.setAttribute("access_token", tokenHandler.getAccessToken());
            httpSession.setAttribute("start_time", Calendar.getInstance());
            httpSession.setAttribute("user_name", this.username);

            return this.Access(this.microserviceUrl, (String) httpSession.getAttribute("access_token"), b);
        } else {
            long duration = ((Calendar.getInstance().getTimeInMillis() - ((Calendar) httpSession.getAttribute("start_time")).getTimeInMillis())) / 1000;

            if ((this.username.equals(userName)) && (duration <= ACCESS_DURATION)) {
                mLogger.info("Duration: " + duration);
                return this.Access(this.microserviceUrl, (String) httpSession.getAttribute("access_token"), b);
            } else {
                TokenHandler tokenHandler = new TokenHandler(new OAuth2Parameters(this.clientid,
                        this.clientSecret,
                        this.username,
                        this.password,
                        keyCloakServerOpenIdConnectToken,
                        "password",
                        "openid"));
                tokenHandler.getTokens();
                httpSession.setAttribute("access_token", tokenHandler.getAccessToken());
                httpSession.setAttribute("start_time", Calendar.getInstance());
                httpSession.setAttribute("user_name", this.username);

                return this.Access(this.microserviceUrl, (String) httpSession.getAttribute("access_token"), b);
            }
        }
    }

    public String Access(String addr, String token, byte[] b) throws MalformedURLException, IOException {
        StringBuilder response = new StringBuilder();

        URL obj = new URL(addr);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setReadTimeout(15 * 1000);
        con.setUseCaches(false);
        con.setAllowUserInteraction(false);
        con.setRequestProperty("Authorization", "Bearer " + token);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(b);//postparameter.toJSON().getBytes()
        os.flush();
        os.close();
        // For POST only - END  

        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            mLogger.info("Response:" + response.toString());
        } else {
            mLogger.info("POST request did not work.");
            return errorResponse(responseCode);
        }

        return response.toString();
    }

    public String Access(String addr, String token) throws MalformedURLException, IOException {
        URL url = new URL(addr);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

        // just want to do an HTTP GET here
        httpCon.setRequestMethod("GET");

        // uncomment this if you want to write output to this url
        //connection.setDoOutput(true);
        // give it 15 seconds to respond
        httpCon.setReadTimeout(15 * 1000);

        httpCon.setUseCaches(false);
        httpCon.setAllowUserInteraction(false);
        httpCon.setRequestProperty("Authorization", "Bearer " + token);
        //System.out.println(httpCon.getResponseCode());
        //System.out.println(httpCon.getResponseMessage());

        //Display what the GET request returns
        StringBuilder sb = new StringBuilder();
        int HttpResult = httpCon.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(httpCon.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
        } else {
            System.out.println(httpCon.getResponseMessage());
        }
        return sb.toString();
    }

    private String errorResponse(int responseCode) {
        switch (responseCode) {
            case 403:
                return "Could not fulfil the request! Access forbidden! HTTP error : " + responseCode;
        }
        return "Could not fulfil the request! Unknown error.";
    }

    public String getToken() throws IOException {

        TokenHandler tokenHandler = new TokenHandler(new OAuth2Parameters(this.clientid,
                this.clientSecret,
                this.username,
                this.password,
                keyCloakServerOpenIdConnectToken,
                "password",
                "openid"));
        tokenHandler.getTokens();
        httpSession.setAttribute("access_token", tokenHandler.getAccessToken());
        httpSession.setAttribute("start_time", Calendar.getInstance());

        return tokenHandler.getAccessToken();
    }
}
