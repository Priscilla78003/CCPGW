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
public class AuthResponse {

    private AuthResponse.T3DSResult t3DSResult;
    private String redirectURL;
    private AuthResponse.CardReaderResult cardReaderResult;
    private AuthResponse.ListOfReferenceNbrs listOfReferenceNbrs;
    private String version;
    private String responseCode;
    private String description;
    private String approvalCode;
    private String cvcResultCode;
    private String avsResultCode;
    private String orderNumber;
    private String transactionID;
    private String mandateID;
    private Boolean debitCard;
    private String sessionId;

    
    public static class ListOfReferenceNbrs {
        private List<ReferenceNbrType> referenceNbr;

        /**
         * @return the referenceNbr
         */
        public List<ReferenceNbrType> getReferenceNbr() {
            return referenceNbr;
        }

        /**
         * @param referenceNbr the referenceNbr to set
         */
        public void setReferenceNbr(List<ReferenceNbrType> referenceNbr) {
            this.referenceNbr = referenceNbr;
        }
   }
    
    public static class CardReaderResult {
        private TokensType tokens;

        /**
         * @return the tokens
         */
        public TokensType getTokens() {
            return tokens;
        }

        /**
         * @param tokens the tokens to set
         */
        public void setTokens(TokensType tokens) {
            this.tokens = tokens;
        }
        
        
    }
    
    public static class T3DSResult {

        private String redirectHTML;
        private String cavv;
        private String cavvResultCode;
        private String paRequest;
        private String issuerURL;
        private String termURL;
        private String eci;
        private String md;
        private String paResponseCode;
        private String veResponseCode;
        private String authenticationResponseCode;
        private String directoryServerTrxID;
        private String threeDSServerTransID;
        private Boolean liabilityShiftInd;
        private String version;

        /**
         * @return the redirectHTML
         */
        public String getRedirectHTML() {
            return redirectHTML;
        }

        /**
         * @param redirectHTML the redirectHTML to set
         */
        public void setRedirectHTML(String redirectHTML) {
            this.redirectHTML = redirectHTML;
        }

        /**
         * @return the cavv
         */
        public String getCavv() {
            return cavv;
        }

        /**
         * @param cavv the cavv to set
         */
        public void setCavv(String cavv) {
            this.cavv = cavv;
        }

        /**
         * @return the cavvResultCode
         */
        public String getCavvResultCode() {
            return cavvResultCode;
        }

        /**
         * @param cavvResultCode the cavvResultCode to set
         */
        public void setCavvResultCode(String cavvResultCode) {
            this.cavvResultCode = cavvResultCode;
        }

        /**
         * @return the paRequest
         */
        public String getPaRequest() {
            return paRequest;
        }

        /**
         * @param paRequest the paRequest to set
         */
        public void setPaRequest(String paRequest) {
            this.paRequest = paRequest;
        }

        /**
         * @return the issuerURL
         */
        public String getIssuerURL() {
            return issuerURL;
        }

        /**
         * @param issuerURL the issuerURL to set
         */
        public void setIssuerURL(String issuerURL) {
            this.issuerURL = issuerURL;
        }

        /**
         * @return the termURL
         */
        public String getTermURL() {
            return termURL;
        }

        /**
         * @param termURL the termURL to set
         */
        public void setTermURL(String termURL) {
            this.termURL = termURL;
        }

        /**
         * @return the eci
         */
        public String getEci() {
            return eci;
        }

        /**
         * @param eci the eci to set
         */
        public void setEci(String eci) {
            this.eci = eci;
        }

        /**
         * @return the md
         */
        public String getMd() {
            return md;
        }

        /**
         * @param md the md to set
         */
        public void setMd(String md) {
            this.md = md;
        }

        /**
         * @return the paResponseCode
         */
        public String getPaResponseCode() {
            return paResponseCode;
        }

        /**
         * @param paResponseCode the paResponseCode to set
         */
        public void setPaResponseCode(String paResponseCode) {
            this.paResponseCode = paResponseCode;
        }

        /**
         * @return the veResponseCode
         */
        public String getVeResponseCode() {
            return veResponseCode;
        }

        /**
         * @param veResponseCode the veResponseCode to set
         */
        public void setVeResponseCode(String veResponseCode) {
            this.veResponseCode = veResponseCode;
        }

        /**
         * @return the authenticationResponseCode
         */
        public String getAuthenticationResponseCode() {
            return authenticationResponseCode;
        }

        /**
         * @param authenticationResponseCode the authenticationResponseCode to set
         */
        public void setAuthenticationResponseCode(String authenticationResponseCode) {
            this.authenticationResponseCode = authenticationResponseCode;
        }

        /**
         * @return the directoryServerTrxID
         */
        public String getDirectoryServerTrxID() {
            return directoryServerTrxID;
        }

        /**
         * @param directoryServerTrxID the directoryServerTrxID to set
         */
        public void setDirectoryServerTrxID(String directoryServerTrxID) {
            this.directoryServerTrxID = directoryServerTrxID;
        }

        /**
         * @return the threeDSServerTransID
         */
        public String getThreeDSServerTransID() {
            return threeDSServerTransID;
        }

        /**
         * @param threeDSServerTransID the threeDSServerTransID to set
         */
        public void setThreeDSServerTransID(String threeDSServerTransID) {
            this.threeDSServerTransID = threeDSServerTransID;
        }

        /**
         * @return the liabilityShiftInd
         */
        public Boolean getLiabilityShiftInd() {
            return liabilityShiftInd;
        }

        /**
         * @param liabilityShiftInd the liabilityShiftInd to set
         */
        public void setLiabilityShiftInd(Boolean liabilityShiftInd) {
            this.liabilityShiftInd = liabilityShiftInd;
        }

        /**
         * @return the version
         */
        public String getVersion() {
            return version;
        }

        /**
         * @param version the version to set
         */
        public void setVersion(String version) {
            this.version = version;
        }
    }

    /**
     * @return the t3DSResult
     */
    public AuthResponse.T3DSResult getT3DSResult() {
        return t3DSResult;
    }

    /**
     * @param t3DSResult the t3DSResult to set
     */
    public void setT3DSResult(AuthResponse.T3DSResult t3DSResult) {
        this.t3DSResult = t3DSResult;
    }

    /**
     * @return the redirectURL
     */
    public String getRedirectURL() {
        return redirectURL;
    }

    /**
     * @param redirectURL the redirectURL to set
     */
    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    /**
     * @return the cardReaderResult
     */
    public AuthResponse.CardReaderResult getCardReaderResult() {
        return cardReaderResult;
    }

    /**
     * @param cardReaderResult the cardReaderResult to set
     */
    public void setCardReaderResult(AuthResponse.CardReaderResult cardReaderResult) {
        this.cardReaderResult = cardReaderResult;
    }

    /**
     * @return the listOfReferenceNbrs
     */
    public AuthResponse.ListOfReferenceNbrs getListOfReferenceNbrs() {
        return listOfReferenceNbrs;
    }

    /**
     * @param listOfReferenceNbrs the listOfReferenceNbrs to set
     */
    public void setListOfReferenceNbrs(AuthResponse.ListOfReferenceNbrs listOfReferenceNbrs) {
        this.listOfReferenceNbrs = listOfReferenceNbrs;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the approvalCode
     */
    public String getApprovalCode() {
        return approvalCode;
    }

    /**
     * @param approvalCode the approvalCode to set
     */
    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    /**
     * @return the cvcResultCode
     */
    public String getCvcResultCode() {
        return cvcResultCode;
    }

    /**
     * @param cvcResultCode the cvcResultCode to set
     */
    public void setCvcResultCode(String cvcResultCode) {
        this.cvcResultCode = cvcResultCode;
    }

    /**
     * @return the avsResultCode
     */
    public String getAvsResultCode() {
        return avsResultCode;
    }

    /**
     * @param avsResultCode the avsResultCode to set
     */
    public void setAvsResultCode(String avsResultCode) {
        this.avsResultCode = avsResultCode;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * @return the mandateID
     */
    public String getMandateID() {
        return mandateID;
    }

    /**
     * @param mandateID the mandateID to set
     */
    public void setMandateID(String mandateID) {
        this.mandateID = mandateID;
    }

    /**
     * @return the debitCard
     */
    public Boolean getDebitCard() {
        return debitCard;
    }

    /**
     * @param debitCard the debitCard to set
     */
    public void setDebitCard(Boolean debitCard) {
        this.debitCard = debitCard;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    

}
