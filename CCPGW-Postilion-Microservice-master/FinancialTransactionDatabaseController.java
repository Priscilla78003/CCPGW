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
import com.truteq.ccpgw.transaction.manager.model.Financial;
import com.truteq.ccpgw.transaction.manager.model.OriginalDataElement_Financial;
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
public class FinancialTransactionDatabaseController {

    @Autowired
    private GenericDatabaseDAO databaseDao;

    @PostMapping("financial/originaldata/write")
    @CrossOrigin(origins = "*")
    public void writeOriginalData(@RequestBody OriginalDataElement_Financial ode_in) {

        Object odeParameters[] = {
            ode_in.getTransactionId(),
            ode_in.getMessageType(),
            ode_in.getTransmissionDateTime(),
            ode_in.getSystemTraceAuditNumber(),
            ode_in.getAcquiringInstitutionCode(),
            ode_in.getForwardingInstitudeId()
        };

        int odeParameterTypes[] = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR
        };

        databaseDao.insert("ccpgw.transaction.manager.originaldata.financial", odeParameters, odeParameterTypes);

    }
    
    /**
     * =========================================================================
     *  readFinancialByTransactionId
     * =========================================================================
     * @param fin_in
     * @return
     * =========================================================================
     * This method was developed by Amy Rombuk
     * 2022-08-03
     */
    @PostMapping("financial/read/transactionid")
    @CrossOrigin(origins = "*")
    public String readFinancialByTransactionId(@RequestBody Financial fin_in) {

        Financial fin = (Financial) databaseDao.findByName("ccpgw.transaction.mamager.read.financial.by.transactionid", Financial.class, fin_in.getTransactionId());

        return toJSON(fin);
    }     
    
    /**
     * =========================================================================
     *  readFinancial
     * ============================================================================
     * @param fin_in
     * @return
     * =========================================================================
     */
    @PostMapping("financial/read")
    @CrossOrigin(origins = "*")
    public String readFinancial(@RequestBody Financial fin_in) {

        Financial auth = (Financial) databaseDao.findByName("ccpgw.transaction.manager.read.financial", Financial.class, fin_in.getTransactionId());

        return toJSON(auth);
    }    

    
    /**
     * =========================================================================
     * writeFinancial
     * =========================================================================
     * @param fin_in
     */    
    @PostMapping("financial/write")
    @CrossOrigin(origins = "*")
    public void writeFinancial(@RequestBody Financial fin_in) {

        Object authParameters[] = {
            fin_in.getTransactionId(),
            fin_in.getMessageType(),
            fin_in.getPrimaryAccountNumber(),
            fin_in.getProcessingCode(),
            fin_in.getAmount(),
            fin_in.getTransmissionDateTime(),
            fin_in.getSystemTraceAuditNumber(),
            fin_in.getLocalTranTime(),
            fin_in.getLocalTranDate(),
            fin_in.getExpiryDate(),
            fin_in.getSettlementDate(),
            fin_in.getPosConditionCode(),
            fin_in.getAcquiringInstitutionCode(),
            fin_in.getRetrievalRef(),
            fin_in.getTerminalId(),
            fin_in.getAuthIdResponse(),
            fin_in.getPosDataCode(),
            fin_in.getRouting()
        };

        int authParameterTypes[] = {
            Types.VARCHAR,
            Types.VARCHAR,
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
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR

        };

        databaseDao.insert("ccpgw.transaction.manager.write.financial", authParameters, authParameterTypes);

    }

    private String toJSON(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }
}
