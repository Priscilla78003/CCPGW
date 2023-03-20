/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
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
import com.truteq.ccpgw.transaction.manager.model.MerchantOrder;
import com.truteq.ccpgw.transaction.manager.model.Order;
import com.truteq.ccpgw.transaction.manager.model.OrderList;
import com.truteq.datagenerics.GenericDatabaseDAO;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mho
 */
@RestController
@PropertySource("classpath:datamanager.properties")
@RequestMapping("/transaction/manager/database/")
public class OrderController {

    private LogWrapper mLogger = new LogWrapper(OrderController.class);

    @Autowired
    private GenericDatabaseDAO databaseDao;

    @PostMapping(path = "order/write")
    @CrossOrigin(origins = "*")
    private void writeOrderData(@RequestBody Order order) {

        Object[] parameters = {
            Integer.parseInt(order.getOrder_id()),
            order.getAmount(),
            order.getCustomer_first_name(),
            order.getCustomer_last_name(),
            order.getCustomer_email(),
            order.getDescription(),
            order.getCurrency(),
            order.getMerchant_key(),
            order.getCallback_url(),
            order.getCallback_key()
        };

        int[] paramterTypes = {
            Types.INTEGER,
            Types.DECIMAL,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR
        };

        databaseDao.insert("ccpgw.transaction.manager.write.merchant.order", parameters, paramterTypes);
    }

    @PostMapping(path = "order/read/byorderidandmerchantkey")
    @CrossOrigin(origins = "*")
    public String getOrderByOrderIdAndMerchantKey(@RequestBody Order order) {

        Object[] parameters = {
            order.getOrder_id(),
            order.getMerchant_key()
        };

        int[] parameterTypes = {
            Types.VARCHAR,
            Types.VARCHAR
        };

        List<Order> orders = databaseDao.selectBy("ccpgw.transaction.manager.read.merchant.orderbysecretkey", Order.class, parameters, parameterTypes);
        OrderList oList = new OrderList();
        oList.setOrderList(orders);

        mLogger.info("order id = " + order.getOrder_id());
        mLogger.info("merchant_key = " + order.getMerchant_key());
        mLogger.info("no of orders = " + orders.size());
        mLogger.info("returned data = " + toJSON(oList));

        return toJSON(oList);
    }

    @PostMapping("order/read/merchantorder")
    @CrossOrigin(origins = "*")
    public String getMerchantOrder(@RequestBody MerchantOrder mOrder) throws Exception {

        mLogger.info("Secret Key = " + mOrder.getSecretKey());
        mLogger.info("Order ID = " + mOrder.getOrder_id());

        Object[] parameters = {
            mOrder.getOrder_id(),
            mOrder.getSecretKey()
        };

        int[] parameterTypes = {
            Types.VARCHAR,
            Types.VARCHAR
        };

        List<MerchantOrder> merchantOrders = databaseDao.selectBy("ccpgw.transaction.manager.read.merchant.order", MerchantOrder.class, parameters, parameterTypes);
        MerchantOrder mOrderResult = null;
        if (merchantOrders.size() > 0) {
            mOrderResult = merchantOrders.get(0);
        }

        mLogger.info("merchantOrder.size() " + merchantOrders.size());
        mLogger.info("toJSON(mOrderResult) " + toJSON(mOrderResult));
        return toJSON(mOrderResult);
    }

    @PostMapping("order/update")
    @CrossOrigin(origins = "*")
    public void updateOrderStatus(@RequestBody Order order) throws Exception {

        //mLogger.info("Order id: " + order.getOrder_id() + "; Ref number: " = order.getTra)
        Object parameters[] = {
            order.getTransaction_ref(),
            order.getStatus_value(),
            order.getStatus(),
            order.getStatus_description(),
            order.getOrder_id()

        };

        int parameterTypes[] = {Types.VARCHAR,
            Types.INTEGER,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR
        };
        
        databaseDao.insert("ccpgw.transaction.manager.orders.update", parameters, parameterTypes);
    }

    private String toJSON(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }
}
