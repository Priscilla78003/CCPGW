/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.model.authenticate.authorise;

import java.util.List;

/**
 *
 * @author mho
 */
public class TokensType {
    
    private List<TokensType.Token> token;
    
    public static class Token {

        private String value;
        private String name;

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
        
        
    }

    /**
     * @return the token
     */
    public List<TokensType.Token> getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(List<TokensType.Token> token) {
        this.token = token;
    }
}
