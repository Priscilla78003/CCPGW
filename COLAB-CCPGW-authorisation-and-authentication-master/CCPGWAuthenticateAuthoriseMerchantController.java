/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  08-Feb-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.authentication.authorisation.server.springoauth.controller;

import com.google.gson.Gson;
import com.truteq.ccpgw.authentication.authorisation.server.encryption.CredentialEncryption;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.model.authenticate.authorise.AuthResult;
import com.truteq.ccpgw.model.authenticate.authorise.AuthUserObj;
import com.truteq.ccpgw.model.authenticate.authorise.Credentials;
import com.truteq.ccpgw.model.authenticate.authorise.EncryptionOnly;
import com.truteq.ccpgw.model.authenticate.authorise.EncryptionSession;
import com.truteq.ccpgw.model.authenticate.authorise.SessionObj;
import com.truteq.ccpgw.model.authenticate.authorise.UserAccessKey;
import com.truteq.microservice.security.access.MicroServiceHandler;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Result;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Grant Blaise O'Reilly <gbo@truteq.com>  
 */


@RestController
public class CCPGWAuthenticateAuthoriseMerchantController extends GenericAuthenticationAuthorisationController {

    private final LogWrapper mLogger = new LogWrapper(CCPGWAuthenticateAuthorizeCardController.class);



    @Autowired
    private HttpSession httpSession;
    
    
    /**
     * =========================================================================
     * CreateMerchantForm
     *
     * @param encryptionObj
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("/keycloak/ccpgw/authenticate/authorise/merchant/createfrom/")
    @CrossOrigin(origins = "*")
    public String CreateMerchantForm(@RequestBody EncryptionOnly encryptionObj) throws IOException {

        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/merchant/createfrom/reactjs",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                "ccpgwuser",
                "ccpgwu53r");

        return msh.callMicroService(encryptionObj.toJSON().getBytes());
    } 
    
    /**
     * =========================================================================
     * UserAuthentication
     * @param userObj
     * @return
     * @throws IOException 
     * =========================================================================
     */
    @PostMapping("/keycloak/authenticate/authorise/user/auth")
    @CrossOrigin(origins = "*")
    public String UserAuthentication(@RequestBody AuthUserObj userObj) throws IOException, Exception{
        // Note if the service is a public service then the username/password for 
        // Keycloak must be set to guest/guest.
        Credentials cred = new Credentials("guest", "guest");
        
        String sessionId = UUID.randomUUID().toString();
        userObj.setSessionid(sessionId);

        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                                                          ccpgwJsonFrontEndAdapterServerHostname+"/merchant/user/auth",
                                                          keyCloakServerOpenIdConnectToken,
                                                          aaClientId,
                                                          aaClientSecret,
                                                          "ccpgwuser",
                                                          "ccpgwu53r");
        
        String input = userObj.toJSON();      
        String output = msh.callMicroService(input.getBytes());
        Gson gson = new Gson();
        Result result = gson.fromJson(output, Result.class);
        
        if ((result.getStatus() == 200)||(result.getStatus() == 201)){
           Credentials credentials = new Credentials(userObj.getUsername(), userObj.getPassword(), sessionId);
           CredentialEncryption encrypt = new CredentialEncryption();
           String encryptedString = encrypt.EncryptAES256(credentials.toJSON());
           
           AuthResult authResult = new AuthResult(result.getStatus(),result.getComments(),userObj.getUsername(),encryptedString);
           return authResult.toJSON();
        }
        
        return output;
    }    
    

    /**
     * =========================================================================
     * GetBankAccountList
     *
     * @param request
     * @param response
     * @param userAuthObj
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("/keycloak/authenticate/authorise/bank/account/list")
    @CrossOrigin(origins = "*")
    public String GetBankAccountList(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AuthResult userAuthObj) throws IOException, Exception {

        return ValidateAndCallMicroService(request,
                response,
                userAuthObj.getUsername(),
                userAuthObj.getKey(),
                "/merchant/user/bank/account/list/reactjs",
                "{ \"username\":\"" + userAuthObj.getUsername() + "\"}");

    }
    /**
     * =========================================================================
     * CloseSession
     *
     * @param request
     * @param response
     * @param accesskey
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("keycloak/authenticate/authorise/user/session/close")
    @CrossOrigin(origins = "*")
    public String CloseSession(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody UserAccessKey accesskey) throws IOException, Exception {

        if (accesskey.getKey() == null) {
            return "No valid key supplied!";
        }
        Credentials cred = getDecryptedCredentials(accesskey.getKey());

        HttpSession httpSession = request.getSession();
        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/merchant/user/session/close",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                cred.getUsername().toLowerCase(), //"ccpgwuser"
                cred.getPassword());       //"ccpgwu53r"           
                
        SessionObj sessionObj = new SessionObj(cred.getUsername());

        String rsp = msh.callMicroService(sessionObj.toJSON().getBytes());

        return rsp;

    }

    /**
     * =========================================================================
     * ReadSession
     *
     * @param request
     * @param response
     * @param cred
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("keycloak/authenticate/authorise/user/session/read")
    @CrossOrigin(origins = "*")
    public String ReadSession(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Credentials cred) throws IOException, Exception {

        HttpSession httpSession = request.getSession();
        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                ccpgwJsonFrontEndAdapterServerHostname + "/merchant/user/session/read",
                keyCloakServerOpenIdConnectToken,
                aaClientId,
                aaClientSecret,
                cred.getUsername().toLowerCase(),
                cred.getPassword());

        SessionObj sessionObj = new SessionObj(cred.getUsername(), cred.getSessionid());

        String rsp = msh.callMicroService(sessionObj.toJSON().getBytes());

        return rsp;

    }
    
    
    /**
     * =========================================================================
     * ValidateAndCallMicroService :
     * -------------------------------------------------------------------------
     * This is the core utility method used to access a protected restful web
     * service.
     * -------------------------------------------------------------------------
     *
     * @param request
     * @param response
     * @param cif
     * @param key
     * @param url
     * @param input
     * @return
     * @throws Exception
     * =========================================================================
     */
    String ValidateAndCallMicroService(HttpServletRequest request,
            HttpServletResponse response,
            String username,
            String key,
            String url,
            String input) throws Exception {

        Credentials cred = getDecryptedCredentials(key);

        Result result = getResultFromString(ReadSession(request, response, cred));

        switch (result.getStatus()) {
            case 200:
                SessionObj sessionObj = getSessionObjFromString(result.getData());

                if (sessionObj.getSessionid().equals(cred.getSessionid())) {
                    HttpSession httpSession = request.getSession();

                    mLogger.info("Session Id: " + httpSession.getId());

                    if (!username.equals(cred.getUsername())) {
                        return "Violation detected! The username does not match the embedded session username.";
                    } else {
                        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
                                ccpgwJsonFrontEndAdapterServerHostname + url,
                                keyCloakServerOpenIdConnectToken,
                                aaClientId,
                                aaClientSecret,
                                cred.getUsername().toLowerCase(),
                                cred.getPassword());


                        String rsp = msh.callMicroService(input.getBytes());
                        return rsp;
                    }
                } else {
                    return "Session id does not match!";
                }
            case 600:
                return result.getData();
            default:
                return "Unknown error when retrieving session id.";
        }

    }


    /**
     * =========================================================================
     * GetMerchantProfile
     *
     * @param request
     * @param response
     * @param userAuthObj
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("/keycloak/authenticate/authorise/merchant/read/profile")
    @CrossOrigin(origins = "*")
    public String GetMerchantProfile(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AuthResult userAuthObj) throws IOException, Exception {

        return ValidateAndCallMicroService(request,
                response,
                userAuthObj.getUsername(),
                userAuthObj.getKey(),
                "/merchant/user/read/profile",
                "{ \"username\":\"" + userAuthObj.getUsername() + "\"}");
    }

    /**
     * =========================================================================
     * LoadImageData
     *
     * @param request
     * @param response
     * @param userAuthObj
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("/keycloak/authenticate/authorise/merchant/read/imagedata")
    @CrossOrigin(origins = "*")
    public String LoadImageData(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AuthResult userAuthObj) throws IOException, Exception {

        return ValidateAndCallMicroService(request,
                response,
                userAuthObj.getUsername(),
                userAuthObj.getKey(),
                "/merchant/user/read/imagedata",
                "{ \"username\":\"" + userAuthObj.getUsername() + "\"}");

    }

    /**
     * =========================================================================
     * UpdateImageData
     *
     * @param request
     * @param response
     * @param encryptSession
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("/keycloak/authenticate/authorise/merchant/update/imagedata")
    @CrossOrigin(origins = "*")
    public String UpdateImageData(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody EncryptionSession encryptSession) throws IOException, Exception {
        //@RequestBody AuthImageData authImageData) throws IOException, Exception{

        return ValidateAndCallMicroService(request,
                response,
                encryptSession.getUsername(),
                encryptSession.getSessionkey(),
                "/merchant/user/update/imagedata",
                encryptSession.toJSON());
    }

    /**
     * =========================================================================
     * UpdateProfileChanges
     *
     * @param request
     * @param response
     * @param encryptSession
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("/keycloak/authenticate/authorise/merchant/profile/update")
    @CrossOrigin(origins = "*")
    public String UpdateProfileChanges(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody EncryptionSession encryptSession) throws IOException, Exception {

        return ValidateAndCallMicroService(request,
                response,
                encryptSession.getUsername(),
                encryptSession.getSessionkey(),
                "/merchant/user/profile/update",
                encryptSession.toJSON());

    }


    /**
     * =========================================================================
     * GetMerchantSecretKey
     *
     * @param request
     * @param response
     * @param userAuthObj
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("/keycloak/authenticate/authorise/merchant/read/secretkey")
    @CrossOrigin(origins = "*")
    public String GetMerchantSecretKey(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AuthResult userAuthObj) throws IOException, Exception {

        return ValidateAndCallMicroService(request,
                response,
                userAuthObj.getUsername(),
                userAuthObj.getKey(),
                "/merchant/user/read/secretkey",
                "{ \"username\":\"" + userAuthObj.getUsername() + "\"}");

    } 
    
    /**
     * =========================================================================
     * OrderList
     *
     * @param request
     * @param response
     * @param userAuthObj
     * @return
     * @throws IOException
     * =========================================================================
     */
    @PostMapping("/keycloak/authenticate/authorise/merchant/order/list")
    @CrossOrigin(origins = "*")
    public String OrderList(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AuthResult userAuthObj) throws IOException, Exception {

        return ValidateAndCallMicroService(request,
                response,
                userAuthObj.getUsername(),
                userAuthObj.getKey(),
                "/merchant/user/order/list",
                "{ \"username\":\"" + userAuthObj.getUsername() + "\"}");

    }    

}


//    /**
//     * =========================================================================
//     * TransactionList
//     *
//     * @param request
//     * @param response
//     * @param userAuthObj
//     * @return
//     * @throws IOException
//     * =========================================================================
//     */
//    @PostMapping("/keycloak/authenticate/authorise/merchant/transaction/list")
//    @CrossOrigin(origins = "*")
//    public String TransactionList(HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestBody AuthResult userAuthObj) throws IOException, Exception {
//
//        return ValidateAndCallMicroService(request,
//                response,
//                userAuthObj.getUsername(),
//                userAuthObj.getKey(),
//                "/ipgw/api/transaction/list",
//                "{ \"username\":\"" + userAuthObj.getUsername() + "\"}");
//
//    }
//

//
//    /**
//     * =========================================================================
//     * NotificationsList
//     *
//     * @param request
//     * @param response
//     * @param userAuthObj
//     * @return
//     * @throws IOException
//     * =========================================================================
//     */
//    @PostMapping("/keycloak/authenticate/authorise/merchant/notifications/list")
//    @CrossOrigin(origins = "*")
//    public String NotificationsList(HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestBody AuthResult userAuthObj) throws IOException, Exception {
//
//        return ValidateAndCallMicroService(request,
//                response,
//                userAuthObj.getUsername(),
//                userAuthObj.getKey(),
//                "/ipgw/api/merchant/list/notifications",
//                "{ \"username\":\"" + userAuthObj.getUsername() + "\"}");
//
//    }
//
//    /**
//     * =========================================================================
//     * BusinessProfile
//     *
//     * @param request
//     * @param response
//     * @param userAuthObj
//     * @return
//     * @throws IOException
//     * =========================================================================
//     */
//    @PostMapping("/keycloak/authenticate/authorise/merchant/read/business/profile")
//    @CrossOrigin(origins = "*")
//    public String BusinessProfile(HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestBody AuthResult userAuthObj) throws IOException, Exception {
//
//        return ValidateAndCallMicroService(request,
//                response,
//                userAuthObj.getUsername(),
//                userAuthObj.getKey(),
//                "/ipgw/api/merchant/read/business/profile",
//                "{ \"username\":\"" + userAuthObj.getUsername() + "\"}");
//
//    }
//
//    @PostMapping("/keycloak/authenticate/authorise/merchant/read")
//    @CrossOrigin(origins = "*")
//    public String getMerchant(@RequestBody Merchant merchant) throws IOException {
//
//        MicroServiceHandler msh = new MicroServiceHandler(httpSession,
//                ccpgwJsonFrontEndAdapterServerHostname + "order/read/merchantorder",
//                keyCloakServerOpenIdConnectToken,
//                "ccpgw-auth-service",
//                "4317340f-72d9-42b0-b0e0-46eb85b4a109",
//                "ccpgwuser",
//                "ccpgwu53r");
//
//        return msh.callMicroService(merchant.toJSON().getBytes());
//    }
//}
