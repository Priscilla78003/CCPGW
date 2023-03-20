package com.truteq.security.keycloak.admin;

/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW API - Microservice for Truteq IPGW project 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  11-Jan-2021  
 * ***************************************************************
 */


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class KeyCloakAdmin {

    String serverUrl;
    String realm;
    String clientId;
    String clientSecret;
    Keycloak keycloak;
    RealmResource realmResource;
    UsersResource usersResource;
    
    public KeyCloakAdmin(  String serverUrl,
                    String realm,
                    String clientId,
                    String clientSecret){
         
        this.serverUrl = serverUrl;
        this.realm = realm;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        
       keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl) 
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username("ccpgwadmin")
                .password("ccpgwadmin")
                .build(); 
       KeyCloakRealm();
    }
    
    private void KeyCloakRealm(){
        // Get realm
        realmResource = keycloak.realm(realm);
        usersResource = realmResource.users();        
    }

    private UserRepresentation defineUser(String username,
            String firstname,
            String lastname,
            String email,
            boolean enabled) {
        // Define user
        UserRepresentation userRep = new UserRepresentation();
        userRep.setEnabled(enabled);
        userRep.setUsername(username);
        userRep.setFirstName(firstname);
        userRep.setLastName(lastname);
        userRep.setEmail(email);
        userRep.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));
        return userRep;
    }
    
    public String createUser(String username,
                           String firstname,
                           String lastname,
                           String email,
                           boolean enabled){
        UserRepresentation user = defineUser(username,firstname,lastname,email,enabled);
        
        // Create user (requires manage-users role)
        Response response = usersResource.create(user);
        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);

        System.out.printf("User created with userId: %s%n", userId); 
        return userId;
    }
    
    public void definePassword(String userId, String password){
        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);

        UserResource userResource = this.usersResource.get(userId);

        // Set password credential
        userResource.resetPassword(passwordCred);        
    }

    public void AssignClientRoles(String userId, String clientId, String role){
        // Get realm role "tester" (requires view-realm role)
        //RoleRepresentation testerRealmRole = realmResource.roles()//
        //        .get("tester").toRepresentation();
        // Assign realm role tester to user
        //userResource.roles().realmLevel() //
        //        .add(Arrays.asList(testerRealmRole));

        // Get client        
        UserResource userResource = this.usersResource.get(userId);
        ClientRepresentation app1Client = realmResource.clients()
                .findByClientId(clientId).get(0);

        // Get client level role (requires view-clients role)
        RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId())
                .roles().get(role).toRepresentation();
        // Assign client level role to user
        userResource.roles() //
                .clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));        
    }
    
    
    public boolean DoesUserExist(String username){
         List<UserRepresentation> list = this.usersResource.search(username);
         return list.stream().anyMatch(userRep -> (userRep.getUsername().equals(username.toLowerCase())));
    }

        // Send password reset E-Mail
        // VERIFY_EMAIL, UPDATE_PROFILE, CONFIGURE_TOTP, UPDATE_PASSWORD, TERMS_AND_CONDITIONS
//        usersRessource.get(userId).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));

        // Delete User
//        userResource.remove();    

}
