/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2022 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Transaction Manager Microservice : Transaction Manager - Restful Web service 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  14-Apr-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.transaction.manager.microservice.threeds.controller;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.threeds.v2.objects.results.response.ResultsRequest;
import com.truteq.ccpgw.threeds.v2.objects.results.response.ResultsResponse;
import com.truteq.ccpgw.threeds.v2.objects.results.response.TDSResultResponseObject;
import com.truteq.ccpgw.threeds.v2.objects.results.response.ThreeDSServerResultsResponse;
import com.truteq.datagenerics.GenericDatabaseDAO;
import java.sql.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
@RestController
@RequestMapping("/transaction/manager/3ds/v2/result/response/")
public class ThreeDSV2RResultsResponse {

    @Autowired
    private GenericDatabaseDAO databaseDao;
    private final LogWrapper mLogger = new LogWrapper(ThreeDSV2RResultsResponse.class);
    
    /**
     * =========================================================================
     * getResultsResponse
     * =========================================================================
     * @param RRes
     * @return 
     * =========================================================================
     */
    @PostMapping("read")
    @CrossOrigin(origins = "*")
    public ThreeDSServerResultsResponse getResultsResponse (@RequestBody ThreeDSServerResultsResponse RRes) {
        mLogger.info("Reading 3DS v2.x Result Response from databse for 3DSServerTransID :" + RRes.getThreeDSServerTransID());
        
        TDSResultResponseObject resultresObj = (TDSResultResponseObject) databaseDao.findByName("ccpgw.transaction.manager.read.result.response", TDSResultResponseObject.class, RRes.getThreeDSServerTransID());
        
        mLogger.debug("3DS Result Response Object: " + resultresObj.toJSON());
        
        return resultresObj.toThreeDSServerResultsResponse();

    }    
  
    /**
     * =========================================================================
     * persistThreeDSServerResultsResponse
     * =========================================================================
     * @param RRes 
     * =========================================================================
     */
    @PostMapping("write")
    @CrossOrigin(origins = "*")
    public void persistThreeDSServerResultsResponse (@RequestBody ThreeDSServerResultsResponse RRes) {
        mLogger.info("Persisting 3DS v2.x Result Response: " + RRes.toJSON());
        
        
        persistRreq(RRes.getThreeDSServerTransID(),RRes.getResultsRequest()); 
        persistRres(RRes.getThreeDSServerTransID(),RRes.getResultsResponse());
        persistResultResponse(RRes.getThreeDSServerTransID(),RRes);       
        
    }
    
    /**
     * =========================================================================
     * persistResultResponse
     * =========================================================================
     * @param threeDSServerTransID
     * @param rres 
     * =========================================================================
     */
     private void persistResultResponse(String threeDSServerTransID,  ThreeDSServerResultsResponse rres){
         Object parameters[] = { 
                                threeDSServerTransID,
                                rres.getTransStatus(),
                                rres.getAuthenticationValue(),
                                rres.getEci()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.result.response", parameters, parameterTypes);            
    } 
    
    /**
     * =========================================================================
     * persistRres
     * =========================================================================
     * @param threeDSServerTransID
     * @param rres 
     * ========================================================================= 
     */
    private void persistRres(String threeDSServerTransID, ResultsResponse rres){
         Object parameters[] = { 
                                threeDSServerTransID,
                                rres.getAcsTransID(),
                                rres.getDsTransID(),
                                rres.getMessageType(),
                                rres.getMessageVersion(),
                                rres.getResultsStatus()
        };
                
        int parameterTypes[] = {
                                     Types.VARCHAR, 
                                     Types.VARCHAR,
                                     Types.VARCHAR,
                                     Types.VARCHAR, 
                                     Types.VARCHAR, 
                                     Types.VARCHAR
        };         

        databaseDao.insert("ccpgw.transaction.manager.write.rres", parameters, parameterTypes);            
    }    
    
    /**
     * =========================================================================
     * persistRreq
     * =========================================================================
     * @param threeDSServerTransID
     * @param rreq 
     * =========================================================================
     */
    private void persistRreq(String threeDSServerTransID, ResultsRequest rreq){
         Object parameters[] = { 
                                threeDSServerTransID,
                                rreq.getAcsTransID(),
                                rreq.getAcsRenderingType().getAcsInterface(),
                                rreq.getAcsRenderingType().getAcsUiTemplate(),
                                rreq.getAuthenticationMethod(),
                                rreq.getAuthenticationType(),
                                rreq.getAuthenticationValue(),
                                rreq.getDsTransID(),
                                rreq.getEci(),
                                rreq.getInteractionCounte(),
                                rreq.getMessageCategory(),
                                rreq.getMessageType(),
                                rreq.getMessageVersion(),
                                rreq.getTransStatus()
        };
                
        int parameterTypes[] = {
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

        databaseDao.insert("ccpgw.transaction.manager.write.rreq", parameters, parameterTypes);            
    }
    
}
