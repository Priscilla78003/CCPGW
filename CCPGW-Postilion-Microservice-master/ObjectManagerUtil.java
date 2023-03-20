/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 2019
 * ***************************************************************
 * CCPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  07-Feb-2021  
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.microservice.database.controller;

import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Address;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Customer;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Fees;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Merchant;
import com.truteq.ccpgw.transaction.manager.merchant.portal.model.Users;
import com.truteq.datagenerics.GenericDatabaseDAO;
import com.truteq.security.crypto.SCryptUtil;
import com.truteq.security.keycloak.admin.KeyCloakAdmin;
import java.sql.Types;
import java.util.Date;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ObjectManagerUtil {
    
    private final GenericDatabaseDAO databaseDao;
    
    private String serverUrl;
    private String realm;
    private String clientId;
    private String clientSecret;    
    
    public ObjectManagerUtil(GenericDatabaseDAO databaseDao) {
        this.databaseDao = databaseDao;
    }
    
    public Merchant updateMerchant(Merchant merchant){
        Users user = (Users) databaseDao.findById("ipgw.api.query.user.by.customer_id", Users.class, merchant.getCustomer_id());
        return merchant;
    }   
    /**=========================================================================
     * createCustomer is used to create the customer object in the database
     * 
     * @param customer This is the customer object that will be written to the DB
     * @return newCustomer, the customer returned from the DB with an database id
     * =========================================================================
     */    
    public Customer createCustomer( Customer customer ){
        
        if (customer.getDateofbirth() == null){
           customer.setDateofbirth(new Date());
        }
                
        Object customerParameters[] = {customer.getName(),
            customer.getLastName(),
            customer.getEmail(),
            customer.getPhoneNumber(),
            customer.getDateofbirth(),
            customer.getStatus(),
            customer.getBalance(),
            customer.getSalutation()
        };
        
        // define SQL types of the arguments
        int customerParameterTypes[] = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
        databaseDao.insert("ipgw.api.create.customer", customerParameters, customerParameterTypes);
        
        Customer newCustomerOnlyId = (Customer) databaseDao.findByName("ipgw.api.read.customerid", Customer.class, customer.getEmail());
        customer.setId(newCustomerOnlyId.getId());
        
        Users user = customer.getUser();
        Address address = customer.getAddress();
        user.setCustomer_id(newCustomerOnlyId.getId());
        address.setCustomer_id(newCustomerOnlyId.getId());
        
        String password = user.getPassword();
        
        Users newUser = createUser(user);
        Address newAddress = createAddress(address);
        
        createKeyCloakUser(newUser.getUsername(),
                           password,
                           customer.getName(),
                           customer.getLastName(),
                           customer.getEmail());
        
        customer.setRef_user_id(newUser.getId());
        customer.setRef_address_id(newAddress.getId());
        
        updateCustomer(customer);        
        
        return customer;       
    }
    
    /**=========================================================================
     * createMerchant is used to create the merchant object in the database
     * @param merchant This is the merchant object that will be written to the DB
     * @return the new merchant object
     * =========================================================================
     */
    public Merchant createMerchant(Merchant merchant){
        Customer customer = createCustomer(merchant.getCustomer());
        merchant.setCustomer_id(customer.getId());
                
        //Mya - 06.Apr.2021 added default fee_id (retrieved from db by name 'FREE-FEE' and new document_signature fields.		
        //Fees fees = (Fees)databaseDao.findByName("ipgw.api.merchant.get.default.fee", Fees.class, "FREE-FEE");
        
        Object merchantParameters[] = { merchant.getGuid(),
                                        merchant.getBusinessName(),
                                        merchant.getBusinessNumber(),
                                        merchant.getRsaPublicKey(),
                                        merchant.getRsaPrivateKey(),
                                        merchant.getSecretKey(),
                                        merchant.getCustomer_id(),
                                        merchant.getCardAcceptorName(),
                                        merchant.getBusinessPhoneNumber(),
                                        merchant.isNon3D(),
                                        merchant.isRefund(),
                                        merchant.getNotifEmail(),
                                        merchant.getNotifURL(),
                                        merchant.getFailURL(),
                                        merchant.getCancelURL(),
                                        merchant.getSuccessURL(),
                                        merchant.getIntegrate(),
                                        merchant.getCurrency(),
                                        0
                                        //fees.getId()
        };
        int merchantParameterTypes[] = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT,
                                        Types.VARCHAR, Types.VARCHAR, Types.BOOLEAN, Types.BOOLEAN, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, 
                                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT };        
        
        databaseDao.insert("ipgw.api.create.merchant", merchantParameters, merchantParameterTypes);
        
        Merchant newMerchantOnlyId = (Merchant) databaseDao.findByName("ipgw.api.read.merchantid", Merchant.class, merchant.getGuid());
        Object imageDataParameters[] = { 
                                          
                                          merchant.getDocuments().getDocument1(),
                                          merchant.getDocuments().getDocument2(),
                                          merchant.getDocuments().getDocument3(),
                                          merchant.getDocuments().getDocument1_1(),
                                          merchant.getDocuments().getDocument2_1(),
                                          merchant.getDocuments().getDocument3_1(),
                                          merchant.getDocuments().getDocument3_2(),
                                          newMerchantOnlyId.getId(),	
                                          merchant.getDocuments().getDocument_signature ()
        };
        int imageDataParameterTypes[] = {
                                     Types.CLOB,
                                     Types.CLOB,
                                     Types.CLOB,
                                     Types.CLOB,
                                     Types.CLOB,
                                     Types.CLOB,  
                                     Types.CLOB,
                                     Types.INTEGER,	
                                     Types.CLOB
        };          
        databaseDao.insert("ipgw.api.merchant.insert.documents",imageDataParameters,imageDataParameterTypes);
        
        //Insert the logo
        Object imgDataParameters[] = { newMerchantOnlyId.getId(), //Note this must be set to the merchant's id 
                                       merchant.getBusinessName()+" "+merchant.getBusinessNumber(),
                                       merchant.getDocuments().getDocument4() };
        
        // define SQL types of the arguments
        int imgDataParameterTypes[] = {Types.INTEGER,
                                       Types.VARCHAR,
                                       Types.CLOB
                                     };
        
        databaseDao.insert("ipgw.api.merchant.insert.imagedata", imgDataParameters, imgDataParameterTypes);          
        

        return merchant;          
    }
    /**=========================================================================
     * createKeyCloakUser
     *
     * @param username
     * @param password
     * @param firstname
     * @param lastname
     * @param email 
     * =========================================================================             
     */
    private void createKeyCloakUser(String username,
                                    String password,
                                    String firstname,
                                    String lastname,
                                    String email){
        
        KeyCloakAdmin keycloak = new KeyCloakAdmin(this.serverUrl,   //"http://localhost:8181/auth",
                                                   this.realm,       //"PlatformPAC",
                                                   this.clientId,    //"enduser-validate-otp-service",
                                                   this.clientSecret); //"5e997a57-5064-473a-94c1-1a887265a2ea");
        if (!keycloak.DoesUserExist(username.toLowerCase())){
        
          String userId = keycloak.createUser(username,firstname, lastname, email, true);
        
          keycloak.definePassword(userId,password);
        
          keycloak.AssignClientRoles(userId,"ccpgw-auth-service","USER_SESSION_READ");
          keycloak.AssignClientRoles(userId,"ccpgw-auth-service","USER_SESSION_CLOSE");
        }
        
    }
    /**=========================================================================
     * createUser
     * @param user 
     * @return 
     * =========================================================================
     */
    public Users createUser(Users user){
        String originalPassword = user.getPassword();
        String generatedSecuredPasswordHash = SCryptUtil.scrypt(originalPassword, 16, 16, 16);
        user.setPassword(generatedSecuredPasswordHash);
        
        Object userParameters[] = { user.getUsername(),
                                    user.getPassword(),
                                    user.getSecretkey(),
                                    user.isMarketcomms(),
                                    user.getCustomer_id(),
                                    user.getRole(),
                                    user.isTotp()
        };
        int userParameterTypes[] = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BOOLEAN, Types.BIGINT, Types.VARCHAR, Types.BOOLEAN};
        databaseDao.insert("ipgw.api.create.user", userParameters, userParameterTypes);  
        
        Users newUser = (Users) databaseDao.findById("ipgw.api.query.user.by.customer_id", Users.class, user.getCustomer_id());
        return newUser;
    }
    /**=========================================================================
     * createAddress
     * @param address
     * @return 
     * =========================================================================
     */
    public Address createAddress(Address address){
        Object addressParameters[] = {address.getLine1(),
            address.getLine2(),
            address.getLine3(),
            address.getPostcode(),
            address.getState(),
            address.getCity(),
            address.getCountrycode(),
            address.getCustomer_id()
        };
        int addressParameterTypes[] = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT};
        databaseDao.insert("ipgw.api.create.address", addressParameters, addressParameterTypes); 
        
        Address newAddress = (Address) databaseDao.findById("ipgw.api.query.address.by.customerid", Address.class, address.getCustomer_id());
        return newAddress;        
    }
    
    public void updateCustomer(Customer customer){
         
        Object customerParameters[] = {customer.getRef_user_id(),
                                       customer.getRef_address_id(),
                                       customer.getId()
        };
        
        // define SQL types of the arguments
        int customerParameterTypes[] = {Types.BIGINT, Types.BIGINT, Types.BIGINT };
        databaseDao.insert(""
                + "ipgw.api.update.customer", customerParameters, customerParameterTypes);
    }    
    
    public Customer updateCustomerObject(Customer customer){
         
        Users user = (Users) databaseDao.findById("ipgw.api.query.user.by.id", Users.class, customer.getRef_user_id()); 
        Address address = (Address) databaseDao.findById("ipgw.api.query.address.by.id", Address.class, customer.getRef_address_id()); 
         
        customer.setUser(user);
        customer.setAddress(address);
        
        return customer;
    }
     
    public Merchant updateMerchantObject(Merchant merchant){
         
        Customer customer = (Customer) databaseDao.findById("ipgw.api.read.customer", Customer.class, merchant.getCustomer_id()); 

        Customer updatedCustomer = updateCustomerObject(customer);
        
        merchant.setCustomer(updatedCustomer);
        
        return merchant;
    }
    
    public Customer readCustomer(Users user){
        Customer customer = (Customer) databaseDao.findById("ipgw.api.read.customer", Customer.class, user.getCustomer_id());
        Address address = (Address) databaseDao.findById("ipgw.api.query.address.by.id", Address.class, customer.getRef_address_id());
        
        customer.setUser(user);
        customer.setAddress(address);
        
        return customer;
    } 

    /**
     * @return the serverUrl
     */
    public String getServerUrl() {
        return serverUrl;
    }

    /**
     * @param serverUrl the serverUrl to set
     */
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * @return the realm
     */
    public String getRealm() {
        return realm;
    }

    /**
     * @param realm the realm to set
     */
    public void setRealm(String realm) {
        this.realm = realm;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param clientSecret the clientSecret to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
