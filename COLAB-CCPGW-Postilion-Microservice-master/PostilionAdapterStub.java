/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Communication server
 * ***************************************************************
 * Used to communicate with different adapters 
 * Support: Grant O'Reilly gbo@truteq.com OR grant@platformpac.com.pg
 * V01.00.00  29-Jun-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.communication.server.application.threads;

import com.truteq.ServiceException;
import com.truteq.ccpgw.adapter.postilion.enums.eAuth;
import com.truteq.ccpgw.adapter.postilion.executor.htm.PostilionExecutorAdapterHTM;
import com.truteq.ccpgw.adapter.postilion.requests.objects.AuthoriseObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.CaptureObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.Field127Object;
import com.truteq.ccpgw.adapter.postilion.requests.objects.RequestObject;
import com.truteq.ccpgw.adapter.postilion.requests.objects.ReversalObject;
import com.truteq.ccpgw.comms.server.model.CommsServerMessage;
import com.truteq.ccpgw.comms.server.model.ICommands;
import com.truteq.ccpgw.communication.server.listeners.PostilionListener;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.TestRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.TestResponse;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class PostilionAdapterStub implements ICommands {

    private final LogWrapper mLogger = new LogWrapper(PostilionAdapterStub.class);
    private final PostilionListener postilionListener;
    private final PostilionExecutorAdapterHTM postilionAdapter;

    public PostilionAdapterStub(PostilionListener postilionListener) {
        this.postilionListener = postilionListener;
        this.postilionAdapter = new PostilionExecutorAdapterHTM();
        try {
            this.postilionAdapter.start();
        } catch (ServiceException ex) {
            mLogger.error("Exception: error when trying to connect to Postilion." + ex, new Throwable().getStackTrace()[0]);
        }
        this.signon();
    }

    public void connect() {
        getPostilionListener().onConnect();
    }

    public void disconnect() {
        getPostilionListener().onDisconnect();
    }

    public CommsServerMessage clientDisconnect() {
        getPostilionListener().onClientDisconnect();
        mLogger.info("Disconnecting from Postilion.", new Throwable().getStackTrace()[0]);
        postilionAdapter.doClientDisconnect();
        return new CommsServerMessage(CLIENTDISCONNECT, "String", "Disconnecting from Postilion.");
    }

    public CommsServerMessage clientConnect() {
        getPostilionListener().onClientConnect();
        mLogger.info("Connecting to Postilion.", new Throwable().getStackTrace()[0]);
        postilionAdapter.doClientConnect();
        return new CommsServerMessage(CLIENTCONNECT, "String", "Connecting to Postilion Postilion.");
    }

    public CommsServerMessage clientListenerRefresh() {
        getPostilionListener().onClientListernerRefresh();
        mLogger.info("Refresh client listener.", new Throwable().getStackTrace()[0]);
        postilionAdapter.doClientListenerRefresh();
        return new CommsServerMessage(CLIENTLISTENERREFRESH, "String", "Refresh client listener.");
    }

    public CommsServerMessage signon() {
        getPostilionListener().onSignOn();
        mLogger.info("Signing ON to Postilion.", new Throwable().getStackTrace()[0]);
        postilionAdapter.doSignOnOff(eAuth.SIGNON);
        return new CommsServerMessage(SIGNON, "String", "Signed ON to Postilion.");
    }

    public CommsServerMessage signoff() {
        getPostilionListener().onSignOff();
        mLogger.info("Signing OFF from Postilion.", new Throwable().getStackTrace()[0]);
        postilionAdapter.doSignOnOff(eAuth.SIGNOFF);
        return new CommsServerMessage(SIGNOFF, "String", "Signing OFF from Postilion.");
    }

    public CommsServerMessage ping() {
        getPostilionListener().onPing();
        mLogger.info("Ping Postilion.", new Throwable().getStackTrace()[0]);
        postilionAdapter.doSignOnOff(eAuth.ECHO);
        return new CommsServerMessage(PING, "String", "Ping Postilion.");
    }

    public CommsServerMessage cleanup() {
        getPostilionListener().onCleanUp();
        mLogger.info("CleanUp.", new Throwable().getStackTrace()[0]);
        postilionAdapter.doCleanUp();
        return new CommsServerMessage(CLEANUP, "String", "Clean up.");
    }

    public CommsServerMessage checkconnection() {
        getPostilionListener().onCheckConnection();
        mLogger.info("Check Connection.", new Throwable().getStackTrace()[0]);
        postilionAdapter.doCheckConnection();
        return new CommsServerMessage(CHECKCONNECTION, "String", "Check Connection.");
    }

    public CommsServerMessage authorise(Object messageObj) {
        getPostilionListener().onAuthorise();

        if (messageObj instanceof AuthoriseRequest) {
            AuthoriseRequest authReq = (AuthoriseRequest) messageObj;

            mLogger.debug(authReq.toJSON(), new Throwable().getStackTrace()[0]);

            AuthoriseResponse resp = AuthorisationCommand(eAuth.AUTH, authReq);

            return new CommsServerMessage(AUTHORISE, "AuthoriseResponse", resp);
        } else {
            return new CommsServerMessage(ERROR, "String", "Message object is NOT type AuthoriseRequest");
        }
    }

    public CommsServerMessage capture(Object messageObj) {
        getPostilionListener().onCapture();
        if (messageObj instanceof CaptureRequest) {
            CaptureRequest captureReq = (CaptureRequest) messageObj;

            CaptureResponse resp = CaptureCommand(eAuth.CAPTURE, captureReq);

            return new CommsServerMessage(CAPTURE, "CaptureResponse", resp);
        } else {
            return new CommsServerMessage(ERROR, "String", "Message object is NOT type CaptureRequest");
        }
    }

    public CommsServerMessage reversal(Object messageObj) {
        getPostilionListener().onReversal();

        if (messageObj instanceof ReversalRequest) {
            ReversalRequest reversalReq = (ReversalRequest) messageObj;

            ReversalResponse resp = ReversalCommand(eAuth.REVERSAL, reversalReq);

            return new CommsServerMessage(REVERSAL, "ReversalResponse", resp);
        } else {
            return new CommsServerMessage(ERROR, "String", "Message object is NOT type ReversalRequest");
        }
    }

    public CommsServerMessage financial(Object messageObj) {
        getPostilionListener().onFinancial();
        if (messageObj instanceof AuthoriseRequest) {
            AuthoriseRequest authReq = (AuthoriseRequest) messageObj;

            AuthoriseResponse resp = AuthorisationCommand(eAuth.FINANCIAL, authReq);

            return new CommsServerMessage(FINANCIAL, "AuthoriseResponse", resp);
        } else {
            return new CommsServerMessage(ERROR, "String", "Message object is NOT type AuthoriseRequest");
        }
    }

    public CommsServerMessage refund(Object messageObj) {
        getPostilionListener().onRefund();
        if (messageObj instanceof AuthoriseRequest) {
            AuthoriseRequest authReq = (AuthoriseRequest) messageObj;

            AuthoriseResponse resp = AuthorisationCommand(eAuth.REFUND, authReq);

            return new CommsServerMessage(REFUND, "AuthoriseResponse", resp);
        } else {
            return new CommsServerMessage(ERROR, "String", "Message object is NOT type AuthoriseRequest");
        }
    }

    public CommsServerMessage debit(Object messageObj) {
        getPostilionListener().onDebit();
        if (messageObj instanceof CaptureRequest) {
            CaptureRequest captureReq = (CaptureRequest) messageObj;

            CaptureResponse resp = CaptureCommand(eAuth.DEBIT, captureReq);

            return new CommsServerMessage(DEBIT, "CaptureResponse", resp);
        } else {
            return new CommsServerMessage(ERROR, "String", "Message object is NOT type CaptureRequest");
        }
    }

    public CommsServerMessage credit(Object messageObj) {
        getPostilionListener().onCredit();
        if (messageObj instanceof CaptureRequest) {
            CaptureRequest captureReq = (CaptureRequest) messageObj;

            CaptureResponse resp = CaptureCommand(eAuth.CREDIT, captureReq);

            return new CommsServerMessage(CREDIT, "CaptureResponse", resp);
        } else {
            return new CommsServerMessage(ERROR, "String", "Message object is NOT type CaptureRequest");
        }
    }

    private byte[] concatBytes(byte[] xid, byte[] cavv) {
        byte[] result = new byte[xid.length + cavv.length];
        System.arraycopy(xid, 0, result, 0, xid.length);
        System.arraycopy(cavv, 0, result, xid.length, cavv.length);
        return result;
    }   

//    private String createStructure(String tag, String tagValue){
//        String taglength = Integer.toString(tag.length());
//        String taglengthIndicator = Integer.toString(taglength.length());
//        String tagValuelength = Integer.toString(tagValue.length());
//        String tagValuelengthIndicator = Integer.toString(tagValuelength.length()); 
//        
//        StringBuilder builder = new StringBuilder();
//        builder.append(taglengthIndicator)
//               .append(taglength)
//               .append(tag)
//               .append(tagValuelengthIndicator)
//               .append(tagValuelength)
//               .append(tagValue);
//        return builder.toString();
//    }
//    
////    private String frontPadding (String stringtopad, int length){
////        String paddedString = stringtopad;
////        
////        while (paddedString.length() < length){
////                paddedString = "0"+paddedString;
////        }       
////        
////        return paddedString; 
////    }
//    
//    private String generateDE127_22(){
//        
//        String tag1 = "3D_SECURE_VERSION";
//        String tag2 = "DIRECTORY_SERVER_TRANSACTION_ID";
//        String tag1Value = "1";
//        String tag2Value = "abcdefghijhlmnopqrstuvwxyz1234565789";
//       
//        String DE127_22_struct = createStructure(tag1, tag1Value)+createStructure(tag2, tag2Value);
//
//        //return frontPadding(Integer.toString(DE127_22_struct.length()),5)+DE127_22_struct;
//        return DE127_22_struct;
//    }      
    /**
     * =========================================================================
     * AuthorisationCommand [i.e. commands e.g.100, 120]
     * -------------------------------------------------------------------------
     *
     * @param type
     * @param authReq
     * @return
     * =========================================================================
     */
    private AuthoriseResponse AuthorisationCommand(eAuth type, AuthoriseRequest authReq) {
        AuthoriseObject authObj;
        try {
            if (type == eAuth.REFUND) {
                authObj = new AuthoriseObject(type,
                        authReq.getPrimaryAccountNumber(),
                        authReq.getProcessingCode(),
                        authReq.getTransactionAmount(),
                        authReq.getTransmissionDateTime(),
                        authReq.getSystemTraceAuditNumber(),
                        authReq.getLocalTranTime(),
                        authReq.getLocalTranDate(),
                        authReq.getAccountExpiryDate(),
                        authReq.getMerchantType(),
                        authReq.getPosEntryMode(),
                        authReq.getPosConditionCode(),
                        authReq.getAcquiringInstitutionCode(),
                        authReq.getTerminalId(),
                        authReq.getMerchantId(),
                        authReq.getCardAcceptorNameLocation(),
                        authReq.getCurrencyCodeTransaction(),
                        authReq.getPosDataCode(),
                        null);
            } else {

                byte[] ucaf = null;
                       
                if (authReq.getUcafData() != null) {
                    ucaf = authReq.getUcafData().getBytes();
                    //authReq.setStructuredData(generateDE127_22()); 
                }

                authObj = new AuthoriseObject(type,
                        "",
                        authReq.getPrimaryAccountNumber(),//primaryAccountNumber,    //DE2
                        "", //processingCode,          //DE3
                        authReq.getTransactionAmount(), //transactionAmount,       // DE4
                        authReq.getAccountExpiryDate(), //accountExpiryDate, YYMM  //DE14
                        authReq.getMerchantType(), //merchantType,            //DE18
                        authReq.getPosEntryMode(), //posEntryMode             //DE22
                        authReq.getPosConditionCode(), //posConditionCode         //DE25
                        authReq.getAcquiringInstitutionCode(), //acquiringInstitutionCode //DE32
                        authReq.getTerminalId(), //terminalId,              //DE41
                        authReq.getMerchantId(), //merchantId,              //DE42
                        authReq.getCardAcceptorNameLocation(), //cardAcceptorNameLocation, //DE43
                        authReq.getCurrencyCodeTransaction(), //currencyCodeTransaction, //DE49
                        authReq.getPosDataCode(), //posDataCode,             //DE123
                        new Field127Object("", //messageLengthIndicator,  //DE127
                                null, //bitmap,                  //DE127.1
                                authReq.getCvv2(), //cvv2,         //DE127.10
                                authReq.getAvd(), //avd           //DE127.15
                                authReq.getCvd(), //cvd           //DE127.27
                                authReq.getAmericanExpressCardIdentifier(), //AmericanExpressCardIdentifier, ////DE127.28
                                authReq.getThreeDSecureData(), //threeDSecureData,        //DE127.29
                                authReq.getThreeDSecureResult(), //threeDSecureResult,      //DE127.30
                                ucaf,//authReq.getUcafData(), //ucafData                 //DE127.32
                                authReq.getStructuredData())//field 22
                );
            }

            mLogger.debug(authObj.toJSON(), new Throwable().getStackTrace()[0]);

            ISOMsg isoMsg = this.processRequest(type, authObj);

            if (isoMsg != null) {
                postilionAdapter.printISOMessage(isoMsg);
            }

            AuthoriseResponse response = new AuthoriseResponse(isoMsg);

            return response;

        } catch (ISOException ex) {
            mLogger.error("Exception raised when initiating Postilion Authorisation command: " + ex, new Throwable().getStackTrace()[0]);
        }

        return null;
    }

    /**
     * =========================================================================
     * CaptureCommand [i.e. commands 220]
     * -------------------------------------------------------------------------
     *
     * @param type
     * @param authReq
     * @return
     * =========================================================================
     */
    private CaptureResponse CaptureCommand(eAuth type, CaptureRequest authReq) {

        try {
            CaptureObject captureObj = new CaptureObject(type,
                    authReq.getSystemTraceAuditNumber(),
                    authReq.getPrimaryAccountNumber(),//primaryAccountNumber,    //DE2
                    "", //processingCode,          //DE3
                    authReq.getTransactionAmount(), //transactionAmount,       // DE4
                    authReq.getAccountExpiryDate(), //accountExpiryDate, YYMM  //DE14
                    authReq.getMerchantType(), //merchantType,            //DE18
                    authReq.getPosEntryMode(), //posEntryMode             //DE22
                    authReq.getPosConditionCode(), //posConditionCode         //DE25
                    authReq.getAcquiringInstitutionCode(),//acquiringInstitutionCode  //DE32
                    authReq.getRetrievalRefString(), //retrievalReferenceNumber, //DE37
                    authReq.getAuthIdResponse(), //authorizationIdResponse,  //DE38                                                             
                    authReq.getTerminalId(), //terminalId,               //DE41
                    authReq.getMerchantId(), //merchantId,               //DE42
                    authReq.getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //DE43
                    authReq.getCurrencyCodeTransaction(), //currencyCodeTransaction,  //DE49
                    authReq.getOriginalDataElements(), //originalDataElements,     //DE90                    
                    authReq.getPosDataCode(), //posDataCode,              //DE123
                    new Field127Object(authReq.getOriginalKey(), //DE127.11
                            authReq.getOriginalNode()));  //DE127.26

            ISOMsg isoMsg = this.processRequest(type, captureObj);
            postilionAdapter.printISOMessage(isoMsg);

            CaptureResponse response = new CaptureResponse(isoMsg);

            return response;

        } catch (ISOException ex) {
            mLogger.error("Exception raised when initiating Postilion Capture command: " + ex, new Throwable().getStackTrace()[0]);
        }

        return null;
    }

    /**
     * =========================================================================
     * ReversalCommand [i.e. commands 420]
     * -------------------------------------------------------------------------
     *
     * @param type
     * @param authReq
     * @return
     * =========================================================================
     */
    private ReversalResponse ReversalCommand(eAuth type, ReversalRequest authReq) {

        try {

            Field127Object field127 = new Field127Object(authReq.getRoutingInfo(),
                    authReq.getOriginalKey(),
                    authReq.getOriginalNode());

            ReversalObject reversalObj = new ReversalObject(type,
                    authReq.getSystemTraceAuditNumber(),
                    authReq.getPrimaryAccountNumber(),//primaryAccountNumber,    //DE2
                    authReq.getProcessingCode(), //processingCode,          //DE3
                    authReq.getTransactionAmount(), //transactionAmount,       //DE4
                    authReq.getTransmissionDateTime(),
                    authReq.getLocalTranTime(),
                    authReq.getLocalTranDate(),
                    authReq.getAccountExpiryDate(), //accountExpiryDate, YYMM  //DE14
                    authReq.getDateSettlement(), //settleDate,              //DE15  
                    authReq.getMerchantType(),
                    authReq.getPosEntryMode(), //posEntryMode             //DE22
                    authReq.getPosConditionCode(),
                    authReq.getTerminalId(),
                    authReq.getMerchantId(),
                    authReq.getCardAcceptorNameLocation(),//cardAcceptorNameLocation, //DE43
                    authReq.getCurrencyCodeTransaction(), //currencyCodeTransaction,  //DE49
                    authReq.getMessageReasonCode(), //messageReasonCode,        //DE56
                    authReq.getOriginalDataElements(), //originalDataElements,     //DE90
                    authReq.getPosDataCode(),
                    field127);

            ISOMsg isoMsg = this.processRequest(type, reversalObj);
            postilionAdapter.printISOMessage(isoMsg);

            ReversalResponse response = new ReversalResponse(isoMsg);

            return response;

        } catch (ISOException ex) {
            mLogger.error("Exception raised when initiating Postilion Reversal command: " + ex, new Throwable().getStackTrace()[0]);
        }

        return null;
    }

    /**
     * =========================================================================
     * processRequest
     * -------------------------------------------------------------------------
     *
     * @param type
     * @param requestObj
     * @return
     * =========================================================================
     */
    private ISOMsg processRequest(eAuth type, RequestObject requestObj) {
        ISOMsg isoMsg = null;

        mLogger.info("Running authorisation request.", new Throwable().getStackTrace()[0]);

        try {
            switch (type) {
                case MOTO:
                case REFUND:
                case FINANCIAL:
                case AUTH_ADJ:
                case AUTH:
                    AuthoriseObject authObj = (AuthoriseObject) requestObj;
                    isoMsg = postilionAdapter.doAuthorisation(authObj);
                    break;
                case CAPTURE:
                case CREDIT:
                case DEBIT:
                    CaptureObject captureObj = (CaptureObject) requestObj;
                    isoMsg = postilionAdapter.doCapture(captureObj, true);
                    break;
                case REVERSAL:
                    ReversalObject reversalObj = (ReversalObject) requestObj;
                    isoMsg = postilionAdapter.doReversal(reversalObj);
                    break;

            }

        } catch (ISOException ex) {
            mLogger.error("Exception raised when requesting authorisation from Postilion : " + ex, new Throwable().getStackTrace()[0]);
        }

        return isoMsg;
    }

    /**
     * @return the postilionListener
     */
    public PostilionListener getPostilionListener() {
        return postilionListener;
    }

    public CommsServerMessage test(Object messageObj) {
        mLogger.info("Processing a test", new Throwable().getStackTrace()[0]);

        if (messageObj instanceof TestRequest) {
            TestRequest testReq = (TestRequest) messageObj;
            mLogger.info("Processing Test request: " + testReq.getName(), new Throwable().getStackTrace()[0]);
            mLogger.info("Waiting for " + testReq.getDelay() + " millisec", new Throwable().getStackTrace()[0]);

            try {
                Thread.sleep(testReq.getDelay());
            } catch (InterruptedException ex) {
                mLogger.error("Exception on Thread delay. " + ex, new Throwable().getStackTrace()[0]);
            }
            mLogger.info("Completed wait for " + testReq.getName(), new Throwable().getStackTrace()[0]);
            return new CommsServerMessage(TEST, "TestResponse", new TestResponse("Test completed successful for " + testReq.getName()));

        } else {
            return new CommsServerMessage(ERROR, "String", "Message object is NOT type TestRequest");
        }

    }
}
