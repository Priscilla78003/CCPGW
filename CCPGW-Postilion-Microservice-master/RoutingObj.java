/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Postilion Restful server : POSTILION - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.postilion.rest.microserv.transaction.model;

import com.google.gson.Gson;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class RoutingObj {
    
    private String sourceNode;
    private String sinkNode;
    private String sourceNodeStan;
    private String sinkNodeStan;
    private String totalsGroup;

    public RoutingObj(){
    }
    
    public RoutingObj(String sourceNode,
                      String sinkNode,
                      String sourceNodeStan,
                      String sinkNodeStan,
                      String totalsGroup){
        this();
        this.sourceNode = sourceNode;
        this.sinkNode = sinkNode;
        this.sourceNodeStan = sourceNodeStan;
        this.sinkNodeStan = sinkNodeStan;
        this.totalsGroup = totalsGroup;        
    }
    
    public String toJSON(){
        Gson gson =new Gson();
        return gson.toJson(this);
    }    
        
    
    /**
     * @return the sourceNode
     */
    public String getSourceNode() {
        return sourceNode;
    }

    /**
     * @param sourceNode the sourceNode to set
     */
    public void setSourceNode(String sourceNode) {
        this.sourceNode = sourceNode;
    }

    /**
     * @return the sinkNode
     */
    public String getSinkNode() {
        return sinkNode;
    }

    /**
     * @param sinkNode the sinkNode to set
     */
    public void setSinkNode(String sinkNode) {
        this.sinkNode = sinkNode;
    }

    /**
     * @return the sourceNodeStan
     */
    public String getSourceNodeStan() {
        return sourceNodeStan;
    }

    /**
     * @param sourceNodeStan the sourceNodeStan to set
     */
    public void setSourceNodeStan(String sourceNodeStan) {
        this.sourceNodeStan = sourceNodeStan;
    }

    /**
     * @return the sinkNodeStan
     */
    public String getSinkNodeStan() {
        return sinkNodeStan;
    }

    /**
     * @param sinkNodeStan the sinkNodeStan to set
     */
    public void setSinkNodeStan(String sinkNodeStan) {
        this.sinkNodeStan = sinkNodeStan;
    }

    /**
     * @return the totalsGroup
     */
    public String getTotalsGroup() {
        return totalsGroup;
    }

    /**
     * @param totalsGroup the totalsGroup to set
     */
    public void setTotalsGroup(String totalsGroup) {
        this.totalsGroup = totalsGroup;
    }
}
