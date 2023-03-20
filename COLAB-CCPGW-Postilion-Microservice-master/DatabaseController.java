/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021
 * V01.01.00  30-Jul-2021 Mya
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.microservice.database.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truteq.ccpgw.adapter.postilion.requests.objects.RepeatUpdateObj;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.transaction.manager.model.AcsPareqElement;
import com.truteq.ccpgw.transaction.manager.model.Authorisation;
import com.truteq.ccpgw.transaction.manager.model.CaptureElement;
import com.truteq.ccpgw.transaction.manager.model.MerchantCurrencyMap;
import com.truteq.ccpgw.transaction.manager.model.OriginalDataElement;
import com.truteq.ccpgw.transaction.manager.model.RepeatElement;
import com.truteq.ccpgw.transaction.manager.model.RepeatReadRequest;
import com.truteq.datagenerics.GenericDatabaseDAO;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@RestController
@PropertySource("classpath:datamanager.properties")
@RequestMapping("/transaction/manager/database/")
public class DatabaseController {
    
    private final LogWrapper mLogger = new LogWrapper(DatabaseController.class);
    
    @Value(value = "${certificate.keystore}")
    private String keystore;    
    
   private String toJSON(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }
    
    @Autowired
    GenericDatabaseDAO databaseDao;

   
    /**
     * =========================================================================
     * getOriginalData
     * ============================================================================
     * @param ode_in
     * @return
     * =========================================================================
     */
    @PostMapping("read/originaldata")
    @CrossOrigin(origins = "*")
    public String readOriginalData(@RequestBody OriginalDataElement ode_in) {

        //OriginalDataElement ode = (OriginalDataElement) databaseDao.findByName("ccpgw.api.read.originaldata", OriginalDataElement.class, ode_in.getSystemTraceAuditNumber());
        Object odeParameters[] = {ode_in.getSystemTraceAuditNumber(),ode_in.getTransactionId()};

        int odeParameterTypes[] = {Types.VARCHAR,Types.VARCHAR}; 
        
        List<OriginalDataElement> odeList = databaseDao.selectBy("ccpgw.api.read.originaldata", OriginalDataElement.class, odeParameters, odeParameterTypes );
 
        return toJSON(odeList.get(0));
    }   
    
    /**
     * =========================================================================
     * writeOriginalData
     * =========================================================================
     * @param ode_in
     */
    @PostMapping("write/originaldata")
    @CrossOrigin(origins = "*")
    public void writeOriginalData(@RequestBody OriginalDataElement ode_in) {

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
        
        databaseDao.insert("ccpgw.api.write.originaldata", odeParameters, odeParameterTypes);

    }      

    /**
     * =========================================================================
     *  getAuthorisation
     * ============================================================================
     * @param auth_in
     * @return
     * =========================================================================
     */
    @PostMapping("read/authorisation")
    @CrossOrigin(origins = "*")
    public String readAuthorisation(@RequestBody Authorisation auth_in) {

        Authorisation auth = (Authorisation) databaseDao.findByName("ccpgw.api.read.authorisation", Authorisation.class, auth_in.getTransactionId());

        return toJSON(auth);
    }
    
    /**
     * =========================================================================
     * writeOriginalData
     * =========================================================================
     * @param auth_in
     */
    @PostMapping("write/authorisation")
    @CrossOrigin(origins = "*")
    public void writeAuthorisation(@RequestBody Authorisation auth_in) {    
        
        Object authParameters[] = {
            
            auth_in.getTransactionId(),
            auth_in.getMessageType(),
            auth_in.getPrimaryAccountNumber(),
            auth_in.getProcessingCode(),
            auth_in.getAmount(),
            auth_in.getTransmissionDateTime(),
            auth_in.getSystemTraceAuditNumber(),
            auth_in.getLocalTranTime(),
            auth_in.getLocalTranDate(),
            auth_in.getExpiryDate(),
            auth_in.getSettlementDate(),
            auth_in.getPosConditionCode(),
            auth_in.getAcquiringInstitutionCode(),
            auth_in.getRetrievalRef(),
            auth_in.getTerminalId(),
            auth_in.getAuthIdResponse(),
            auth_in.getPosDataCode(),
            auth_in.getRouting()
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
        
        databaseDao.insert("ccpgw.api.write.authorisation", authParameters, authParameterTypes);

    }
    
    /**
     * =========================================================================
     *  readCaptured
     * ============================================================================
     * @param ce_in
     * @return
     * =========================================================================
     */
    @PostMapping("read/captured")
    @CrossOrigin(origins = "*")
    public String readCaptured(@RequestBody CaptureElement ce_in) {

        CaptureElement ce = (CaptureElement) databaseDao.findByName("ccpgw.api.read.capture", CaptureElement.class, ce_in.getTransactionId());

        return toJSON(ce);
    }
    
    /**
     * =========================================================================
     * writeCapturedData
     * =========================================================================
     * @param ce_in
     */
    @PostMapping("write/captured")
    @CrossOrigin(origins = "*")
    public void writeCapturedData(@RequestBody CaptureElement ce_in) {

        Object captureParameters[] = {
 
            ce_in.getTransactionId(),
            ce_in.getMessageType(),
            ce_in.getPrimaryAccountNumber(),
            ce_in.getProcessingCode(),
            ce_in.getTransactionAmount(),
            ce_in.getTransmissionDateTime(),
            ce_in.getSystemTraceAuditNumber(),
            ce_in.getLocalTranTime(),
            ce_in.getLocalTranDate(),
            ce_in.getExpiryDate(),
            ce_in.getPosConditionCode(),
            ce_in.getAcquiringInstitutionCode(),
            ce_in.getRetrievalRefNumber(),
            ce_in.getTerminalId(),
            ce_in.getPosDataCode(),
            ce_in.getRoutingInformation()
        };

        int captureParameterTypes[] = {
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
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR
        };        
        
        databaseDao.insert("ccpgw.api.write.capture", captureParameters, captureParameterTypes);

    }
    
     /**
     * =========================================================================
     *  readRepeat
     * ============================================================================
     * @param value_in
     * @return
     * =========================================================================
     */
    @PostMapping("read/repeat")
    @CrossOrigin(origins = "*")
    public String readRepeat(@RequestBody RepeatReadRequest value_in) {
        
        Object parameters[] = {
            value_in.getValue()
        };
        int parameterTypes[] = {
            Types.INTEGER
        };        
        
        List<RepeatElement> list = databaseDao.selectBy("ccpgw.api.read.repeat",RepeatElement.class, parameters, parameterTypes);

        return toJSON(list);
    }
    
    /**
     * =========================================================================
     *  upDateRepeat
     * ============================================================================
     * @param repeatUpdateObj
     * ============================================================================ 
     */
    @PostMapping("update/repeat")
    @CrossOrigin(origins = "*")
    public void updateRepeat(@RequestBody RepeatUpdateObj repeatUpdateObj) {
        
        Object repeatUpdateParameters[] = {
            repeatUpdateObj.getMti(),
            repeatUpdateObj.getStan()
        };
        int repeatUpdateParameterTypes[] = {
            Types.VARCHAR,
            Types.VARCHAR,
        };
        
        databaseDao.insert("ccpgw.api.update.repeat",repeatUpdateParameters, repeatUpdateParameterTypes);

    }
    
    /**
     * =========================================================================
     * writeRepeat
     * =========================================================================
     * @param re_in
     * =========================================================================
     */
    @PostMapping("write/repeat")
    @CrossOrigin(origins = "*")
    public void writeRepeat(@RequestBody RepeatElement re_in) {
   
        
        Object repeatParameters[] = {
            re_in.getSystemTraceAuditNumber(),
            re_in.getMessageType(),
            re_in.getPrimaryAccountNumber(),
            re_in.getProcessingCode(),
            re_in.getTransactionAmount(),
            re_in.getExpiryDate(),
            re_in.getSettlementDate (),
            re_in.getTransmissionDateTime(),
            re_in.getLocalTranTime(),
            re_in.getLocalTranDate(),
            re_in.getMerchantType(),        
            re_in.getPosEntryMode(), 
            re_in.getPosConditionCode(),
            re_in.getAcquiringInstitutionCode(),
            re_in.getRetrievalRefNumber(),
            re_in.getAuthorizationIdResponse(),
            re_in.getTerminalId(),
            re_in.getMerchantId(), 
            re_in.getCardAcceptorNameLocation(), 
            re_in.getCurrencyCode(),        
            re_in.getOriginalDataElements(),
            re_in.getMessageReasonCode (),
            re_in.getPosDataCode(),
            re_in.getSystemTraceAuditNumber(),
            re_in.getMessageType(),
            re_in.getPrimaryAccountNumber(),
        };

        int repeatParameterTypes[] = {
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
        
        databaseDao.insert("ccpgw.api.write.repeat", repeatParameters, repeatParameterTypes);

    }
    
    /**
     * =========================================================================
     *  writeAcsPaReq 
     * ------------------------------------------------------------------------- 
     * Added by Grant O'Reilly 25 Oct 2021 for the JSON Front End Adapter
     * =========================================================================  
     * @param acsPareq_in
     */
    @PostMapping("write/acspareq")
    @CrossOrigin(origins = "*")
    public void writeAcsPaReq(@RequestBody AcsPareqElement acsPareq_in) {

        Object orderParameters[] = {
            acsPareq_in.getAcsurl(),
            acsPareq_in.getPareq()
        };

        int orderParameterTypes[] = {
            Types.VARCHAR,
            Types.VARCHAR
        }; 
        
        databaseDao.insert("ccpgw.api.write.acspareq", orderParameters, orderParameterTypes);

    }    
    
    
    
//    /**
//     * =========================================================================
//     *  readOrder 
//     * ------------------------------------------------------------------------- 
//     * Added by Grant O'Reilly 18 Oct 2021 for the JSON Front End Adapter
//     * =========================================================================
//     * @param orderin 
//     * @return  
//     */
//    @PostMapping("read/order")
//    @CrossOrigin(origins = "*")
//    public String readOrder(@RequestBody Order orderin) {
//        Order order = (Order) databaseDao.findByName("ccpgw.api.read.order", Order.class, orderin.getOrder_id());
//        
//        return order.toJSON();
//
//    }

//    /**
//     * =========================================================================
//     *  writeOrderData 
//     * ------------------------------------------------------------------------- 
//     * Added by Grant O'Reilly 18 Oct 2021 for the JSON Front End Adapter
//     * =========================================================================
//     * @param orderin 
//     * @return  
//     */
//    @PostMapping("write/order")
//    @CrossOrigin(origins = "*")
//    public void writeOrder(@RequestBody Order orderin) {
//
//        Object orderParameters[] = {
//            orderin.getOrder_id(),
//            orderin.getAmount(),
//            orderin.getCustomer_first_name(),
//            orderin.getCustomer_last_name(),
//            orderin.getCustomer_email(),
//            orderin.getDescription(),
//            orderin.getCallback_url(),
//            orderin.getCallback_key(),
//            orderin.getMerchant_id(),
//            orderin.getCurrency_code()
//        };
//
//        int orderParameterTypes[] = {
//            Types.VARCHAR,
//            Types.INTEGER,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,
//            Types.VARCHAR,            
//            Types.INTEGER,
//            Types.INTEGER
//        }; 
//        
//        databaseDao.insert("ccpgw.api.write.order", orderParameters, orderParameterTypes);
//
//    }


    /**
     * =========================================================================
     * getMerchantId
     * ------------------------------------------------------------------------- 
     * Updated by Grant O'Reilly 11 July 2022 for station number issues
     * =========================================================================
     * 
     * @param merCurIn
     * @return  
     */
    @PostMapping("read/merchantId")
    @CrossOrigin(origins = "*")
    public String getMerchantId(@RequestBody MerchantCurrencyMap merCurIn){
        
        
        if (merCurIn.getStationNumber().equals("")){

            Object[] parameters =  {merCurIn.getMerchantAccountCode(),
                                    merCurIn.getCurrency()};

            int[] parameterTypes = {Types.VARCHAR, Types.VARCHAR};            
            
            List<MerchantCurrencyMap> map = databaseDao.selectBy("ccpgw.api.read.merchantcurencymap.nostationnumber", MerchantCurrencyMap.class, parameters, parameterTypes);

            return toJSON(map.get(0));
        }
        else{
            Object[] parameters =  {merCurIn.getMerchantAccountCode(),
                                    merCurIn.getCurrency(),
                                    merCurIn.getStationNumber() };

            int[] parameterTypes = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};            
            
            List<MerchantCurrencyMap> map = databaseDao.selectBy("ccpgw.api.read.merchantcurencymap", MerchantCurrencyMap.class, parameters, parameterTypes);

            return toJSON(map.get(0));           
        }
  
    }

    /**
     * =========================================================================
     * getMerchantId 26-Nov-2021 Mya
     * =========================================================================
     *
     * @param merCurIn
     * @return
     */
    @PostMapping("read/merchantcurrencymap/businessnameandcurrency")
    @CrossOrigin(origins = "*")
    public String getMerchantCurrencyMapByBusinessNameandCurrency(@RequestBody MerchantCurrencyMap merCurIn) {
        
        mLogger.debug("merCurIn.getMerchantName() in  getMerchantCurrencyMapByBusinessNameandCurrency " + merCurIn.getMerchantName());
        mLogger.debug("merCurIn.getCurrency() in  getMerchantCurrencyMapByBusinessNameandCurrency" + merCurIn.getCurrency());
        
        Object[] parameters = {merCurIn.getMerchantName(),
            merCurIn.getCurrency()};

        int[] parameterTypes = {Types.VARCHAR, Types.VARCHAR};

        List<MerchantCurrencyMap> map = databaseDao.selectBy("ccpgw.api.read.merchantcurencymap.bybusinessnameandcurrency", MerchantCurrencyMap.class, parameters, parameterTypes);

        
        return toJSON(map.get(0));
    }
    
    /**
     * =========================================================================
     * getVersion
     * =========================================================================
     * @return
     * =========================================================================
     */
    @GetMapping("version")
    public String getVersion() {

        return "CCPGW Transaction Manager Restful Web service. PlatformPAC(c) 2021 version 1.0.0.";
    }    
}
