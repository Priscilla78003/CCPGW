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
package com.truteq.ccpgw.transaction.manager.microservice.database.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.ReactJSBankAccountObj;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Result;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Users;
import com.truteq.datagenerics.GenericDatabaseDAO;
import com.truteq.ipgw.model.authenticate.authorise.SessionObj;
import com.truteq.ipgw.model.objects.ReactJSTotpObj;
import com.truteq.security.crypto.SCryptUtil;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
@RestController
@PropertySource("classpath:datamanager.properties")
@RequestMapping("/transaction/manager/database/")

public class UserController {
    
    private final LogWrapper mLogger = new LogWrapper(UserController.class);

    @Autowired
    GenericDatabaseDAO databaseDao;
    
   private String toJSON(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }    
    
    
    /**
     * =============================================================================
     * readSession
     * @param sessionObjIn
     * @return 
     * =============================================================================
     */
    @PostMapping("merchant/user/session/read")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_USER_SESSION_READ')")
    public ResponseEntity readSession(@RequestBody SessionObj sessionObjIn){

        HttpHeaders headers = new HttpHeaders();
        SessionObj sessionObj = (SessionObj) databaseDao.findByName("ipgw.api.user.session.read", SessionObj.class, sessionObjIn.getCif());
        
        if (sessionObj == null){
            Result result = new Result(600, "Error!", "No matching session active.");
            return ResponseEntity.ok().headers(headers).body(result.toJSON());
        }
        
        sessionObj.setCif(sessionObjIn.getCif());  
        Result result = new Result(200, "Successful!", sessionObj.toJSON());
        return ResponseEntity.ok().headers(headers).body(result.toJSON());
       
    }
    /**
     * =============================================================================
     * closeSession
     * @param sessionObjIn
     * @return
     * =============================================================================
     */
    @PostMapping("merchant/user/session/close")
    @CrossOrigin(origins = "*")
    @PreAuthorize("hasAnyAuthority('ROLE_USER_SESSION_CLOSE')")
    public ResponseEntity closeSession(@RequestBody SessionObj sessionObjIn) {
        
        Object sessionParameters[] = {sessionObjIn.getCif()};
        
        int sessionParameterTypes[] = {Types.VARCHAR}; 
        
        databaseDao.delete("ipgw.api.user.session.close", sessionParameters, sessionParameterTypes);        
        
        Result result = new Result(200, "Successful!", "Session for CIF: "+sessionObjIn.getCif()+" has been removeded.");
        
        HttpHeaders headers = new HttpHeaders();
        
        return ResponseEntity.ok().headers(headers).body(result.toJSON());
    }
       
    /**
     * =========================================================================
     * ListBankAccountsForReactJS
     * 
     * @param u
     * @return 
     * =========================================================================
     */
    @PostMapping("merchant/user/bank/account/list/reactjs")
    @CrossOrigin(origins = "*")
    public String ListBankAccountsForReactJS(@RequestBody Users u) {

        Users user = (Users) databaseDao.findByName("ipgw.api.read.user", Users.class, u.getUsername());

        Object bankaccParameters[] = {user.getCustomer_id()};

        int bankaccParameterTypes[] = {Types.BIGINT};        
        
        List<ReactJSBankAccountObj> listOfCustomers = databaseDao.selectBy("ipgw.api.list.bank.accounts.reactjs", ReactJSBankAccountObj.class, bankaccParameters, bankaccParameterTypes );
        
        listOfCustomers.forEach((bankaccount)->{ 
            bankaccount.setUsername(user.getUsername());
        });

        return toJSON(listOfCustomers);
    }  
    
    /**
     * =============================================================================
     *
     * @param user
     * @return
     * =============================================================================
     */
    @PostMapping("merchant/user/auth")
    @CrossOrigin(origins = "*")
    public ResponseEntity UserAuthenticate(@RequestBody Users u) {
        Result result = null;
        HttpHeaders headers = new HttpHeaders();
        
        Users user = (Users) databaseDao.findByName("ipgw.api.read.user", Users.class, u.getUsername());
       
        if (user == null){
            result = new Result(401, "Failure!", "Username "+u.getUsername()+" does NOT exist!");
            return ResponseEntity.ok().headers(headers).body(result.toJSON());
        }
        
        boolean matched = SCryptUtil.check(u.getPassword(), user.getPassword());
        

        if (matched) {
            
            user.setSessionid(u.getSessionid());
            
            Object userSessionParameters[] = { 
                                              user.getSessionid(),
                                              user.getUsername(),
                                              user.getSessionid()
            };
            int userSessionParameterTypes[] = {
                                         Types.VARCHAR, 
                                         Types.VARCHAR,
                                         Types.VARCHAR
            };             
            
            databaseDao.insert("ipgw.api.generate.merchant.sessionid", userSessionParameters, userSessionParameterTypes);  
            
            if (user.isTotp()){ 
               result = new Result(201, "Successful!", "User: "+user.getUsername()+" connected."  );
            }
            else{
               result = new Result(200, "Successful!", "User: "+user.getUsername()+" connected."  );
            }            

        } else {
            result = new Result(401, "Failure!", "Login credentials incorrect!");
        }
        
        return ResponseEntity.ok().headers(headers).body(result.toJSON());
    }

    /**
     * =============================================================================
     *
     * @param totp
     * @return
     * =============================================================================
     */
    @PostMapping("user/google/auth/totp")
    @CrossOrigin(origins = "*")
    public ResponseEntity GoogleTOTPAuth(@RequestBody ReactJSTotpObj totp) {

        Result result = null;
        HttpHeaders headers = new HttpHeaders();

        Users user = (Users) databaseDao.findByName("ipgw.api.read.user", Users.class, totp.getKey());

        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder gacb
                = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                        .setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
                        .setWindowSize(5)
                        .setCodeDigits(6);
        GoogleAuthenticator ga = new GoogleAuthenticator(gacb.build());

        Base32 codec = new Base32();
        mLogger.debug("User Secret Key ="+user.getSecretkey());
        byte[] decodedKey = codec.decode(user.getSecretkey());
        long timeWindow = new Date().getTime() / (TimeUnit.SECONDS.toMillis(30));
        //long hash = ga.calculateCode(decodedKey, timeWindow);
        
        mLogger.debug("TOTP ="+totp.getCode());
        int window =30;
        mLogger.debug("Window ="+window);
        
        boolean matched = false;
        for (int i = -((window - 1) / 2); i <= window / 2; ++i)
        {
            // Calculating the verification code for the current time interval.
            long hash = ga.calculateCode(decodedKey, timeWindow + i);
            mLogger.debug("Hash Key ="+hash); 
            
            if (hash == totp.getCode()) {
                matched = true;
            }
        }    
        
        if(matched){            
            result = new Result(200, "Successful!");
        } else {
            result = new Result(401, "Failure", "TOTP code is incorrect.");
        }  


        return ResponseEntity.ok().headers(headers).body(result.toJSON());
    }

    /**
     * =============================================================================
     *
     * @param totp
     * @return
     * =============================================================================
     */
    @PostMapping("user/google/auth/generatekey")
    @CrossOrigin(origins = "*")
    public ResponseEntity GenerateGoogleTOTPSecretKey(@RequestBody ReactJSTotpObj totp) {
        Result result = null;
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder gacb
                = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                        .setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
                        .setWindowSize(5)
                        .setCodeDigits(6);
        GoogleAuthenticator ga = new GoogleAuthenticator(gacb.build());

        final GoogleAuthenticatorKey key = ga.createCredentials(totp.getKey());//("grantboreilly@gmail.com");
        String secretkey = key.getKey();
        result = new Result(200, "Successful!",secretkey);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(result.toJSON());
    }
}
