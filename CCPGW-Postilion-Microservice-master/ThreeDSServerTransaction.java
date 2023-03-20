/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.threeds.v2.objects.authentication;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ThreeDSServerTransaction {
   
    private String threeDSServerTransID;
    private String md;
    private String termUrl;
    private String creq;
    private String cres;
    
    public ThreeDSServerTransaction(){
        
    }
    
    public ThreeDSServerTransaction(String threeDSServerTransID,
                                    String md,
                                    String termUrl,
                                    String creq,
                                    String cres){
        this();
        this.threeDSServerTransID = threeDSServerTransID;
        this.md = md;
        this.termUrl = termUrl;
        this.creq = creq;
        this.cres = cres;
    }
        
    
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
// If one wants to remove the Html Escaping
//    public String toJSON() {
//        GsonBuilder gsonbuilder = new GsonBuilder();
//        gsonbuilder.disableHtmlEscaping();
//        Gson gson = gsonbuilder.create();
//        return gson.toJson(this);
//    }     
    
    public ThreeDSServerTransaction update(ThreeDSServerTransaction tdsTransaction){
        if (tdsTransaction.getMd()!=null){
            if (!tdsTransaction.getMd().equals("")){
              this.md = tdsTransaction.getMd();
            }
        }    

        if (tdsTransaction.getTermUrl()!=null){
            if (!tdsTransaction.getTermUrl().equals("")){
              this.termUrl = tdsTransaction.getTermUrl();
            }
        }         
        
        if (tdsTransaction.getCreq()!=null){
            if (!tdsTransaction.getCreq().equals("")){
              this.creq = tdsTransaction.getCreq();
            }
        }
        
        if (tdsTransaction.getCres()!=null){
            if (!tdsTransaction.getCres().equals("")){
              this.cres = tdsTransaction.getCres();
            }
        }    
        
        return this;
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
     * @return the creq
     */
    public String getCreq() {
        return creq;
    }

    /**
     * @param creq the creq to set
     */
    public void setCreq(String creq) {
        this.creq = creq;
    }

    /**
     * @return the cres
     */
    public String getCres() {
        return cres;
    }

    /**
     * @param cres the cres to set
     */
    public void setCres(String cres) {
        this.cres = cres;
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
     * @return the termUrl
     */
    public String getTermUrl() {
        return termUrl;
    }

    /**
     * @param termUrl the termUrl to set
     */
    public void setTermUrl(String termUrl) {
        this.termUrl = termUrl;
    }
    
}
