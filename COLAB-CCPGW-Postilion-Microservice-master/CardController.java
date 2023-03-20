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
import com.truteq.ccpgw.transaction.manager.model.TempCard;
import com.truteq.datagenerics.GenericDatabaseDAO;
import java.sql.Types;
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
public class CardController {

    private final LogWrapper mLogger = new LogWrapper(CardController.class);
    
    @Autowired
    private GenericDatabaseDAO databaseDao;

    @PostMapping("tempcard/write")
    @CrossOrigin(origins = "*")
    public void saveCard(@RequestBody TempCard card) {

        Object[] parameters = {
            card.getName(),
            card.getNumber(),
            card.getCvv(),
            card.getExpiry_month(),
            card.getExpiry_year(),
            card.getParequest(),
            card.getSessionid(),
            card.getOrder_id(),
            card.getMerchant_key(),
            card.getAddressline1(),
            card.getAddressline2(),
            card.getCity(),
            card.getState(),
            card.getCountry(),
            card.getPostal_code(),
            card.getPayment_method_code()
        };

        int[] paramterTypes = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.INTEGER,
            Types.INTEGER,
            Types.INTEGER,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.INTEGER,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR
        };

        databaseDao.insert("ccpgw.transaction.manager.write.tempcard", parameters, paramterTypes);

    }

    @PostMapping("tempcard/read")
    @CrossOrigin(origins = "*")
    public String retrieveCard(@RequestBody TempCard card) {

        TempCard theCard = (TempCard) databaseDao.findByName("ccpgw.transaction.manager.read.tempcard.by.sessionid", TempCard.class, card.getSessionid());
        mLogger.debug("payment method code in CardControllder retreiveCard: " + theCard.getPayment_method_code());
        return toJSON(theCard);
    }

    @PostMapping("tempcard/delete")
    @CrossOrigin(origins = "*")
    public void deleteCard(@RequestBody TempCard card) {

        Object[] parameters = {
            card.getSessionid()
        };

        int[] parameterTypes = {
            Types.VARCHAR
        };
        databaseDao.delete("ccpgw.transaction.manager.delete.tempcard", parameters, parameterTypes);
    }

    private String toJSON(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }
}
