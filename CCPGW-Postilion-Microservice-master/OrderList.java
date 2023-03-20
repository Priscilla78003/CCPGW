/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.transaction.manager.model;

import java.util.List;

/**
 *
 * @author mho
 */
public class OrderList {
    
    private List<Order> orderList;

    /**
     * @return the orderList
     */
    public List<Order> getOrderList() {
        return orderList;
    }

    /**
     * @param orderList the orderList to set
     */
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
    
    
}
