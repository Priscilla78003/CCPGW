/*
 * ***************************************************************
 * Truteq CAMEL Diameter Gateway version 1.0.0
 * ***************************************************************
 * Copyright (c) 2020 Truteq Australia 
 * ***************************************************************
 *  CAP-Gw
 *  SS7 CAP component for Truteq CAMEL Diameter Gateway project 
 *  Support: Grant O'Reilly gbo@truteq.com
 *  V01.00.00  11-Sep-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.comms.server.model;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public interface ICommands {
    
        public final static String UNKNOWN  = "unknown";
	public final static String EXIT     = "exit";
        public final static String SHUTDOWN = "shutdown";
	public final static String HELP     = "help";        
        public final static String SEND     = "send";
        
        
//        public final static String START    = "start";
//        public final static String REGISTER    = "register";
//        public final static String REGISTERCONTROL    = "registercontrol";
//        public final static String DEREGISTER    = "deregister";
//        public final static String LIST    = "list";
//        public final static String GET    = "get";
//        public final static String LOG    = "log";
//        public final static String CLIENTS    = "clients";
//        public final static String WAITING    = "waiting";
//        public final static String STARTPROCESS    = "startpro";
        public final static String WHOAMI    = "whoami";
//        public final static String COMPLETE    = "complete";
//        public final static String PROCESS    = "process";
//        public final static String BUSY    = "busy";
//        public final static String IDLE    = "idle";
//        public final static String RECEIVED    = "received";
//        
//        public final static String SSF    = "ssf";
//        public final static String SCF    = "scf";
        public final static String CONNECTIONS    = "connections";
//        
        public final static String INFO   = "info";
        
        public final static String ERROR      = "error";
        public final static String SUCCESS    = "success";
        public final static String CONNECT    = "connect";
        public final static String DISCONNECT = "disconnect";
        public final static String SIGNON     = "signon";
        public final static String SIGNOFF    = "signoff";
        public final static String PING       = "ping";
        public final static String CAPTURE    = "capture";
        public final static String AUTHORISE  = "authorise";
        public final static String REVERSAL   = "reversal";
        public final static String REFUND     = "refund";
        public final static String FINANCIAL  = "financial";
        public final static String DEBIT      = "debit";
        public final static String CREDIT     = "credit";
        public final static String CLEANUP    = "cleanup";
        public final static String CHECKCONNECTION = "checkconnection";
        public final static String TEST       = "test";
        public final static String CLIENTDISCONNECT = "clientdisconnect";
        public final static String CLIENTCONNECT = "clientconnect";
        public final static String CLIENTLISTENERREFRESH = "clientlistenerrefresh";
        
        
        
        //======================================================================
        //gsmSSF commands
        //======================================================================     
        //----------------------------------------------------------------------        
        //Initial Detect Point  [IDP]:
        //----------------------------------------------------------------------        
        // This operation is used by the gsmSSF to 
        //start a CAMEL service
        //----------------------------------------------------------------------        
        public final static String IDP   = "idp";
        
        
        //----------------------------------------------------------------------        
        //CloseDialog [CD] : 
        //----------------------------------------------------------------------        
        // Simply close the dialog
        //----------------------------------------------------------------------        
        public final static String CD   = "cd";
        
        
        //----------------------------------------------------------------------        
        //AssistRequestInstructions [ARI] : 
        //----------------------------------------------------------------------        
        // This operation is used by the assisting gsmSSF or the intelligent 
        // peripheral to establish an assisting dialogue for user interaction
        //----------------------------------------------------------------------        
        public final static String ARI   = "ari";
        
        //----------------------------------------------------------------------        
        //ApplyChargeReport [ACR] :
        //----------------------------------------------------------------------        
        // This operation contains the result of the on-line call duration instruction
        //----------------------------------------------------------------------        
        public final static String ACR   = "acr";
        
        //----------------------------------------------------------------------        
        //EventReportBCSM [ERB] :  
        //----------------------------------------------------------------------        
        // This operation is used by the gsmSSF to inform the gsmSCF about the 
        // occurrence of an event
        //----------------------------------------------------------------------        
        public final static String ERB   = "erb";

        //======================================================================
        //gsmSCF commands
        //======================================================================
        //----------------------------------------------------------------------        
        //ApplyCharging [ACH] : 
        //----------------------------------------------------------------------        
        //This operation is used to instruct the gsmSSF to 
        // apply on-line call duration control. It may be used for an outgoing 
        //call or during user interaction
        //----------------------------------------------------------------------
        public final static String ACH   = "ach";
        //----------------------------------------------------------------------       
        //Cancel [CAN] : 
        //---------------------------------------------------------------------- 
        //This operation has a dual purpose:
        //  (1) It may be used to disarm armed detection points and to cancel 
        //      requests for reports. It is normally used when a CAMEL service 
        //      wants to terminate the relationship
        //  (2) It may be used to prevent or stop the execution of a user 
        //      interaction operation, which was previously sent to the gsmSRF 
        //      or to the intelligent peripheral
        //----------------------------------------------------------------------         
        public final static String CAN   = "can";
        //----------------------------------------------------------------------   
        //Connect [CON] :
        //---------------------------------------------------------------------- 
        // This operation is used to instruct the gsmSSF to continue call 
        // establishment with modified information. This operation may also be 
        // used to generate a follow-on call
        //---------------------------------------------------------------------- 
        public final static String CON   = "con";
        
        //----------------------------------------------------------------------         
        //Continue [CUE] :  
        //----------------------------------------------------------------------         
        // This operation is used to instruct the gsmSSF to continue call processing 
        // at the DP where call processing was suspended
        //---------------------------------------------------------------------- 
        public final static String CUE   = "cue";
        
        
        //----------------------------------------------------------------------         
        //ReleaseCall [RC] :
        //----------------------------------------------------------------------         
        // This operation is used by the gsmSCF to release a call
        //----------------------------------------------------------------------         
        public final static String RC   = "rc";
        
        //---------------------------------------------------------------------- 
        //RequestReportBCSMEvent [RRB] : 
        //----------------------------------------------------------------------         
        // This operation may be used by the gsmSCF to arm or disarm detection 
        // points in the BCSM [Basic Call State Model]
        //---------------------------------------------------------------------- 
        public final static String RRB   = "rrb"; 
        
        //---------------------------------------------------------------------- 
        //ConnectToResourse [CTR] : 
        //----------------------------------------------------------------------         
        // This operation is used to instruct the gsmSSF or assisting gsmSSF to 
        // connect the call to a specialized resource, for user interaction
        //----------------------------------------------------------------------
        public final static String CTR   = "ctr";
        
       
        //----------------------------------------------------------------------        
        //ActivityTest [AT] : 
        //----------------------------------------------------------------------         
        // This operation is used between gsmSCF and gsmSSF, assisting gsmSSF or 
        // intelligent peripheral. It is used to check for the existence of the 
        // CAMEL relationship
        //---------------------------------------------------------------------- 
        public final static String AT   = "at";

        
        //---------------------------------------------------------------------- 
        //PromptAndCollectUserInformation [PC] :
        //----------------------------------------------------------------------         
        // A CAMEL service may use this operation to instruct the gsmSRF or 
        // intelligent peripheral to play an announcement and to collect digits 
        // from the user
        //---------------------------------------------------------------------- 
        public final static String PC  = "pc";        

        
        //---------------------------------------------------------------------- 
        //FurnishChargingInformation [FCI] : 
        //----------------------------------------------------------------------         
        // The gsmSCF may use this operation to place service-specific data in 
        // the CDR for the call    
        //----------------------------------------------------------------------
        public final static String FCI  = "fci";
}
