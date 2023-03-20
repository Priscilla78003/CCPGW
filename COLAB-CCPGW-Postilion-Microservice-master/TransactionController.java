/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Postilion Async Restful server : POSTILION - Restful Web service  
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Aug-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.comms.server.ws.async.controller;

import com.truteq.ccpgw.comms.server.model.ICommands;
import static com.truteq.ccpgw.comms.server.model.ICommands.AUTHORISE;
import static com.truteq.ccpgw.comms.server.model.ICommands.CAPTURE;
import static com.truteq.ccpgw.comms.server.model.ICommands.CREDIT;
import static com.truteq.ccpgw.comms.server.model.ICommands.DEBIT;
import static com.truteq.ccpgw.comms.server.model.ICommands.FINANCIAL;
import static com.truteq.ccpgw.comms.server.model.ICommands.REFUND;
import com.truteq.ccpgw.comms.server.ws.async.services.AsyncService;
import com.truteq.ccpgw.comms.server.ws.model.TestRequestObj;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.TestRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.TestResponse;
import com.truteq.protocol.MessageException;
import java.util.concurrent.CompletableFuture;
import org.jpos.iso.ISOException;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/postilion/restful/service/comms/server/")
public class TransactionController implements ICommands{

    private final LogWrapper mLogger = new LogWrapper(TransactionController.class);
   
    @Autowired
    private AsyncService service;    
    
    /**
     * =========================================================================
     * Authorize
     * =========================================================================
     * @param authReq
     * @return
     * @throws com.truteq.protocol.MessageException
     * @throws org.jpos.iso.ISOException
     * @throws java.lang.InterruptedException
     * =========================================================================
     */
    /*==========================================================================
    Curl example call for Authorisation
    ============================================================================
    ----------------------------------------------------------------------------
    Visa Curl:
    ----------------------------------------------------------------------------
    curl -d '{
            "primaryAccountNumber":"4896780109909073",
            "processingCode":"000000",
            "transactionAmount":"000000012300",
            "accountExpiryDate":"2306",
            "merchantType":"6300",
            "posEntryMode":"010",
            "posConditionCode":"59",
            "acquiringInstitutionCode":"60130200000",
            "terminalId":"423PGK11",
            "merchantId":"4023PGK00000011",
            "cardAcceptorNameLocation":"PHA Health Assurance LiPOM            PG",
            "currencyCodeTransaction":"598",
            "posDataCode":"660550600001192",
            "cvv2":"532",
            "threeDSecureData" : "tgNQqfU1mMRgn3f8+8+Q9cR2zYc=VGhpcyBpcyBhIHRlc3QgYmFzZTY=",
            "threeDSecureResult":"2"
            }' -H 'Content-Type: application/json' --user CCPGW-Postilion-switch-admin:CCPGW_P0st1l10n_sw1tch_4dm1n_0421 http://10.160.12.81:9077/postilion/restful/service/comms/server/postilion/authorise 
    
        http://ipgw.testbsp.com.pg/postilionrestserver/postilion/restful/service/authorise 
        localhost:9077/postilion/restful/service/authorise 
    
    ----------------------------------------------------------------------------
    Mastercard Curl:
    ----------------------------------------------------------------------------
    curl -d '{
            "primaryAccountNumber":"5204230010000012",
            "processingCode":"000000",
            "transactionAmount":"000000012300",
            "accountExpiryDate":"2306",
            "merchantType":"6300",
            "posEntryMode":"010",
            "posConditionCode":"59",
            "acquiringInstitutionCode":"60130200000",
            "terminalId":"423PGK11",
            "merchantId":"4023PGK00000011",
            "cardAcceptorNameLocation":"PHA Health Assurance LiPOM            PG",
            "currencyCodeTransaction":"598",
            "posDataCode":"660550600001192",
            "cvv2":"123",
            "ucafData":"2VGhpcyBpcyBhIHRlc3QgYmFzZTY="
            }' -H 'Content-Type: application/json' --user CCPGW-Postilion-switch-admin:CCPGW_P0st1l10n_sw1tch_4dm1n_0421 http://10.160.12.81:9077/postilion/restful/service/comms/server/postilion/authorise
    
    */
    
    private String maskAccount(String account){
        String mask_begin = account.substring(0, 3);
        String mask_end = account.substring(account.length()-3, account.length());
        
        int maskinglength = account.length()-(mask_begin.length()+mask_end.length());
        
        String mask = "";
        for (int i = 0; i < maskinglength; i++) mask = mask + "*";
        return  mask_begin+mask+mask_end;        
    }
    
    @PostMapping("postilion/authorise")
    @CrossOrigin(origins = "*")
    public CompletableFuture<AuthoriseResponse> Authorise(@RequestBody AuthoriseRequest authReq) throws MessageException, ISOException, InterruptedException {
        
        mLogger.debug("received Authorisation request for account: " + maskAccount(authReq.getPrimaryAccountNumber()));
        CompletableFuture<AuthoriseResponse> auth = service.getAuthorisationCommand(AUTHORISE,authReq);
        return auth;
    
    }

    /**
     * =========================================================================
     * Capture
     * ========================================================================= 
     * @param captureReq
     * @return
     * @throws MessageException
     * @throws ISOException 
     * @throws java.lang.InterruptedException
     * =========================================================================
     */
    /*==========================================================================
    Curl example call for Capture
    ============================================================================
    curl -d '{
            "primaryAccountNumber":"4123440000000200",
            "processingCode":"",
            "transactionAmount":"000000001000",
            "accountExpiryDate":"2212",
            "dateSettlement": "",
            "merchantType":"4511",
            "posEntryMode":"010",
            "posConditionCode":"59",
            "acquiringInstitutionCode":"70130200000",
            "retrievalRefString":"000825076283",
            "authIdResponse":"007805",    
            "terminalId":"401PGK05",
            "merchantId":"4001PGK00000005",
            "cardAcceptorNameLocation":"BSP MERCHANT PGK       POM            PG",
            "currencyCodeTransaction":"598",
            "originalDataElements":"010006090511300000236013020000000000000000",
            "posDataCode":"660550600001190",
            "originalKey":"000011396944",
            "originalNode":"VISASnk"
            }' -H 'Content-Type: application/json' --user CCPGW-Postilion-switch-admin:CCPGW_P0st1l10n_sw1tch_4dm1n_0421  http://10.160.12.81:9077/postilion/restful/service/comms/server/postilion/capture
    
            http://ipgw.testbsp.com.pg/postilionrestserver/postilion/restful/service/capture 
    ==========================================================================*/       
    
    @PostMapping("postilion/capture")
    @CrossOrigin(origins = "*")
    public CompletableFuture<CaptureResponse> Capture(@RequestBody CaptureRequest captureReq) throws MessageException, ISOException, InterruptedException {

        mLogger.info("received Capture request");
        CompletableFuture<CaptureResponse> capture = service.getCaptureCommand(CAPTURE, captureReq);
        return capture;
                
    }    
    
    /**
     * =========================================================================
     * Reversal
     * ========================================================================= 
     * @param reversalReq
     * @return
     * @throws MessageException
     * @throws ISOException 
     * =========================================================================
     */
    /*==========================================================================
    Curl example call for Reversal
    ============================================================================
    curl -d '{
            "primaryAccountNumber":"4300060000000169",
            "processingCode":"",
            "transactionAmount":"000000045000",
            "accountExpiryDate":"1712",
            "dateSettlement": "1014",
            "merchantType":"",  
            "posEntryMode":"010",
            "posConditionCode":"59",
            "terminalId":"401PGK05",
            "merchantId":"4001PGK00000005",
            "cardAcceptorNameLocation":"BSP MERCHANT PGK       POM            PG",
            "currencyCodeTransaction":"598",
            "messageReasonCode":"4021",   
            "originalDataElements":"020000159310181415316013020000000000000000"
            }' -H 'Content-Type: application/json' --user CCPGW-Postilion-switch-admin:CCPGW_P0st1l10n_sw1tch_4dm1n_0421  http://10.160.12.81:9077/postilion/restful/service/reversal
    
            http://ipgw.testbsp.com.pg/postilionrestserver/postilion/restful/service/reversal
    
    
    ==========================================================================*/      

    @PostMapping("postilion/reversal")
    @CrossOrigin(origins = "*")
    public CompletableFuture<ReversalResponse> Reversal(@RequestBody ReversalRequest reversalReq) throws MessageException, ISOException {
      
        mLogger.info("received Reversal request");
        CompletableFuture<ReversalResponse> reversal = service.getReversalCommand(REVERSAL, reversalReq);
        return reversal;             
                
    } 

    /**
     * =========================================================================
     * FinancialTransaction
     * ========================================================================= 
     * @param authReq
     * @return
     * @throws MessageException
     * @throws ISOException 
     * @throws java.lang.InterruptedException
     * =========================================================================
     */
    /*==========================================================================
    Curl example call for FinancialTransaction
    ============================================================================
    curl -d '{
            "primaryAccountNumber":"4300060000000169",
            "processingCode":"000000",
            "transactionAmount":"000000012500",
            "accountExpiryDate":"1712",
            "merchantType":"4511",
            "posEntryMode":"010",
            "posConditionCode":"59",
            "acquiringInstitutionCode":"70130200000",
            "terminalId":"401PGK05",
            "merchantId":"4001PGK00000005",
            "cardAcceptorNameLocation":"BSP MERCHANT PGK       POM            PG",
            "currencyCodeTransaction":"598",
            "posDataCode":"660550600001190",
            "cvv2":"246"
            }' -H 'Content-Type: application/json' --user CCPGW-Postilion-switch-admin:CCPGW_P0st1l10n_sw1tch_4dm1n_0421  http://10.160.12.81:9077/postilion/restful/service/financial
            
            http://ipgw.testbsp.com.pg/postilionrestserver/postilion/restful/service/financial
            localhost:9077/postilion/restful/service/financial
       =======================================================================*/
    
    @PostMapping("postilion/financial")
    @CrossOrigin(origins = "*")
    public CompletableFuture<AuthoriseResponse> FinancialTransaction(@RequestBody AuthoriseRequest authReq) throws MessageException, ISOException, InterruptedException {
      
        mLogger.info("received financial request.");
        CompletableFuture<AuthoriseResponse> auth = service.getAuthorisationCommand(FINANCIAL,authReq);
        return auth;
                
    }  
    
    /**
     * =========================================================================
     * Refund
     * ========================================================================= 
     * @param authReq
     * @return
     * @throws MessageException
     * @throws ISOException =
     * @throws java.lang.InterruptedException
     * =========================================================================
     */
    /*==========================================================================
    Curl example call for Refund
    ============================================================================
    curl -d '{
            "primaryAccountNumber":"4300060000000169",
            "processingCode":"000000",
            "transactionAmount":"000000012500",
            "accountExpiryDate":"1712",
            "merchantType":"4511",
            "posEntryMode":"010",
            "posConditionCode":"59",
            "acquiringInstitutionCode":"70130200000",
            "terminalId":"401PGK05",
            "merchantId":"4001PGK00000005",
            "cardAcceptorNameLocation":"BSP MERCHANT PGK       POM            PG",
            "currencyCodeTransaction":"598",
            "posDataCode":"660550600001190",
            "cvv2":"246"
            }' -H 'Content-Type: application/json' --user CCPGW-Postilion-switch-admin:CCPGW_P0st1l10n_sw1tch_4dm1n_0421  http://10.160.12.81:9077/postilion/restful/service/refund
    
        http://ipgw.testbsp.com.pg/postilionrestserver/postilion/restful/service/refund 
       =======================================================================*/
    
    @PostMapping("postilion/refund")
    @CrossOrigin(origins = "*")
    public CompletableFuture<AuthoriseResponse> Refund(@RequestBody AuthoriseRequest authReq) throws MessageException, ISOException, InterruptedException {
        
        mLogger.info("received refund request.");
        CompletableFuture<AuthoriseResponse> auth = service.getAuthorisationCommand(REFUND,authReq);
        return auth;
       
    }
    
   /**
     * =========================================================================
     * Credit
     * ========================================================================= 
     * @param captureReq
     * @return
     * @throws MessageException
     * @throws ISOException 
     * =========================================================================
     */
    /*==========================================================================
    Curl example call for Credit
    ============================================================================
    curl -d '{
            "primaryAccountNumber":"4300060000000169",
            "processingCode":"",
            "transactionAmount":"000000012500",
            "accountExpiryDate":"1712",
            "dateSettlement": "",
            "merchantType":"4511",
            "posEntryMode":"010",
            "posConditionCode":"59",
            "acquiringInstitutionCode":"70130200000",
            "retrievalRefString":"000825076283",
            "authIdResponse":"007805",    
            "terminalId":"401PGK05",
            "merchantId":"4001PGK00000005",
            "cardAcceptorNameLocation":"BSP MERCHANT PGK       POM            PG",
            "currencyCodeTransaction":"598",
            "originalDataElements":"010006090511300000236013020000000000000000", 
            "posDataCode":"660550600001190"
            }' -H 'Content-Type: application/json' --user CCPGW-Postilion-switch-admin:CCPGW_P0st1l10n_sw1tch_4dm1n_0421  http://10.160.12.81:9077/postilion/restful/service/credit
    
            http://ipgw.testbsp.com.pg/postilionrestserver/postilion/restful/service/credit

    ==========================================================================*/      
    @PostMapping("postilion/credit")
    @CrossOrigin(origins = "*")
    public CompletableFuture<CaptureResponse> Credit(@RequestBody CaptureRequest captureReq) throws MessageException, ISOException {
      
        mLogger.info("received credit request");
        CompletableFuture<CaptureResponse> capture = service.getCaptureCommand(CREDIT, captureReq);
        return capture;        
                
    }
    /**
     * =========================================================================
     * Debit
     * ========================================================================= 
     * @param captureReq
     * @return
     * @throws MessageException
     * @throws ISOException 
     * =========================================================================
     */
    /*==========================================================================
    Curl example call for Debit
    ============================================================================
    curl -d '{
            "primaryAccountNumber":"4300060000000169",
            "processingCode":"",
            "transactionAmount":"000000012500",
            "accountExpiryDate":"1712",
            "dateSettlement": "",
            "merchantType":"4511",
            "posEntryMode":"010",
            "posConditionCode":"59",
            "acquiringInstitutionCode":"70130200000",
            "retrievalRefString":"000825076283",
            "authIdResponse":"007805",    
            "terminalId":"401PGK05",
            "merchantId":"4001PGK00000005",
            "cardAcceptorNameLocation":"BSP MERCHANT PGK       POM            PG",
            "currencyCodeTransaction":"598",
            "posDataCode":"660550600001190"
            }' -H 'Content-Type: application/json' --user CCPGW-Postilion-switch-admin:CCPGW_P0st1l10n_sw1tch_4dm1n_0421  http://10.160.12.81:9077/postilion/restful/service/debit
    
            http://ipgw.testbsp.com.pg/postilionrestserver/postilion/restful/service/debit

    ==========================================================================*/      
    @PostMapping("postilion/debit")
    @CrossOrigin(origins = "*")
    public CompletableFuture<CaptureResponse> Debit(@RequestBody CaptureRequest captureReq) throws MessageException, ISOException {
      
      
        mLogger.info("received debit request");
        CompletableFuture<CaptureResponse> capture = service.getCaptureCommand(DEBIT, captureReq);
        return capture;         
                
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

        return "CCPGW Asnychronous Comms Server Restful Web service. PlatformPAC(c) 2021 version 1.0.0.";
    }
    
    
    @PostMapping("postilion/test")
    @CrossOrigin(origins = "*")     
    public CompletableFuture<String> getTest(@RequestBody TestRequestObj reqObj) throws InterruptedException {
        
        mLogger.info("Controller Authorise started");
        
        CompletableFuture<String> auth = service.getTest(reqObj.getName(),reqObj.getDelay());
        
        return auth;
    }   
 
    
    
    @PostMapping("postilion/commserv")
    @CrossOrigin(origins = "*")
    public CompletableFuture<TestResponse> testCommServ(@RequestBody TestRequest authReq) throws MessageException, ISOException, InterruptedException {
        
        mLogger.info("received Authorisation request for account: " + authReq.getName());
        CompletableFuture<TestResponse> auth = service.getCommServTest(TEST,authReq);
        return auth; 
    
    }    
    
    
}
