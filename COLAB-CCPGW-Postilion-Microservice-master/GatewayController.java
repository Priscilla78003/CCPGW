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
package com.truteq.ccpgw.communication.server.controller;

import com.truteq.ccpgw.comms.server.model.ICommands;
import com.google.gson.Gson;
import com.truteq.ccpgw.comms.server.model.CommsServerMessage;
import com.truteq.ccpgw.comms.server.model.Disconnect;
import com.truteq.ccpgw.communication.server.Node;
import com.truteq.ccpgw.comms.server.model.Initiate;
import com.truteq.ccpgw.comms.server.model.Terminate;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.AuthoriseResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureRequest;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.CaptureResponse;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.ReversalRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public final class GatewayController implements Runnable, ICommands {  //case CONNECT     :  break;

    private boolean complete = false;
    private PrintWriter ps;
    private Writer os;
    private Reader is;
    private StringBuffer input;
    private Node commsNode;
    private Properties prop;   // This is the properties of the machine on which the server is running.
    private InetAddress machineAddress; // This is the InetAddress of the machine on which the server is running.    

    //public GatewayController(int port){
    public GatewayController() {
        //super(port);
        is = new InputStreamReader(System.in);
        os = new PrintWriter(System.out, false);
        ps = new PrintWriter(getOs(), false);
        input = new StringBuffer();
        setProp(System.getProperties());
        try {
            setMachineAddress(InetAddress.getLocalHost());
        } catch (Exception ex) {
        }
    }

    @Override
    public void run() {
        displayHeader();
        while (!isComplete()) {
            try {
                parse(true);
            } catch (IOException | ClassNotFoundException ex) {

            }
        }
    }

    private List<String> tokenise(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
        List<String> tokens = new ArrayList<>();
        while (stringTokenizer.hasMoreElements()) {
            tokens.add(stringTokenizer.nextElement().toString().trim());
        }
        return tokens;
    }

    private synchronized void parse(boolean prompt) throws IOException, ClassNotFoundException {
        char buffer[] = new char[1];
        if (prompt) {
            getPs().print("CommServ> ");
            getPs().flush();
        }
        //////////////////////////////////////////////////////////////////////////////
        //Below - This is a character by character parser. The while loop will read
        //        each character and append it to the input stringBuffer until a
        //        return ('\n') has been found.
        /////////////////////////////////////////////////////////////////////////////
        //while (buffer[0] != '\n') {
        while (buffer[0] != ';') {
            try {
                getIs().read(buffer, 0, 1);
            } catch (IOException e) {

            }
            getInput().append(buffer[0]);
        }

        //////////////////////////////////////////////////////////////////////////////
        //Below -  the stringBuffer input has to be trimed, to dispose of the return
        //         character. We substring input form 0 to input.length()-2. We subtract
        //         two as we compensate for the '\' and 'n' characters.
        /////////////////////////////////////////////////////////////////////////////
        String commands = "";
        String payload = "";

        if ((getInput().toString().contains("{"))
                && (getInput().toString().contains("}"))) {
            commands = getInput().substring(0, getInput().toString().indexOf("{")).trim();
            payload = getInput().substring(getInput().toString().indexOf("{"), getInput().toString().indexOf("}") + 1);
        } else {
            commands = getInput().substring(0, getInput().length() - 1);
        }

        List<String> tokens = tokenise(commands);

        String command = tokens.get(0);

        String operation = "";
        int port = 0;

        if (tokens.size() == 2) {
            operation = tokens.get(1);
        }
        if (tokens.size() == 3) {
            operation = tokens.get(1);
            port = Integer.parseInt(tokens.get(2));
        }

        switch (command) {
            case EXIT:
                setComplete(true);
                break;

            case WHOAMI:
                System.out.println("Controller : " + getMachineAddress());
                System.out.println("User : " + getProp().getProperty("user.name"));
                break;

            case HELP:
                Help();
                break;

            case SEND:
                Controller(operation, payload);
                break;

            case CONNECT:
                commsNode = new Node(16011);
                commsNode.write(new Initiate("GatewayController", "Gateway Controller App used to administer the Comms Server."));
                break;

            case DISCONNECT:
                Object obj = null;
                if (commsNode == null) {
                    System.out.println("No CommsServ connection!");
                } else {
                    if (operation.equals("")) {
                        System.out.println("No parameter! Disconnect [operation];");
                    } else {
                        commsNode.write(new Disconnect(operation));
                        obj = commsNode.waitForResponse();
                    }
                }

                if (obj != null) {
                    CommsServerMessage mRsp = (CommsServerMessage) obj;
                    if (mRsp.getMessageObj() instanceof String) {
                        if (mRsp.getMessageObj().toString().equals("self")) {
                            commsNode = null;
                        } else {
                            System.out.println(mRsp.getMessageObj().toString());
                        }
                    }
                }
                break;

            case SHUTDOWN:
                obj = null;
                if (commsNode == null) {
                    System.out.println("No CommsServ connection!");
                } else {
                    commsNode.write(new Terminate());
                    obj = commsNode.waitForResponse();
                }

                if (obj != null) {
                    CommsServerMessage mRsp = (CommsServerMessage) obj;
                    if (mRsp.getMessageObj() instanceof String) {
                        System.out.println(mRsp.getMessageObj().toString());
                    }
                }
                break;
            case INFO:
                obj = null;
                if (commsNode == null) {
                    System.out.println("No CommsServ connection!");
                } else {
                    obj = nodeWrite(commsNode, INFO, "String", null);
                }

                if (obj != null) {
                    System.out.println(obj.toString());
                } else {
                    System.out.println("Exception : Issue with the server node response on INFO.");
                }
                break;

            case CONNECTIONS:
                obj = null;
                if (commsNode == null) {
                    System.out.println("No CommsServ connection!");
                } else {
                    obj = nodeWrite(commsNode, CONNECTIONS, "String", null);
                }

                if (obj != null) {
                    ArrayList agents = (ArrayList) obj;
                    for (Object agent : agents) {
                        System.out.println(agent.toString());
                    }
                } else {
                    System.out.println("Exception : Issue with the server node response on CONNECTIONS.");
                }
                break;

        }

        //////////////////////////////////////////////////////////////////////////////
        //Below -  clear the stringBuffer
        /////////////////////////////////////////////////////////////////////////////
        getInput().delete(0, getInput().length());
    }

    private Object nodeWrite(Node node, String state, String objType, Object payload) throws IOException, ClassNotFoundException {
        node.write(new CommsServerMessage(state, objType, payload));
        Object obj = node.waitForResponse();
        return obj;
    }

    private Object serverRequest(String type, String payload) throws IOException, ClassNotFoundException {
        if (commsNode == null) {
            System.out.println("No CommsServ connection!");
        } else {
            Gson gson = new Gson();
            switch (type) {
                case AUTHORISE:
                    AuthoriseRequest authReq = gson.fromJson(payload, AuthoriseRequest.class);
                    return nodeWrite(commsNode, AUTHORISE, "AuthoriseRequest", authReq);

                case CAPTURE:
                    CaptureRequest capReq = gson.fromJson(payload, CaptureRequest.class);
                    return nodeWrite(commsNode, CAPTURE, "CaptureRequest", capReq);
                    
                case REVERSAL:
                    ReversalRequest revReq = gson.fromJson(payload, ReversalRequest.class);
                    return nodeWrite(commsNode, REVERSAL, "ReversalRequest", revReq);

                case REFUND:
                    authReq = gson.fromJson(payload, AuthoriseRequest.class);
                    return nodeWrite(commsNode, REFUND, "AuthoriseRequest", authReq);

                case FINANCIAL:
                    authReq = gson.fromJson(payload, AuthoriseRequest.class);
                    return nodeWrite(commsNode, FINANCIAL, "AuthoriseRequest", authReq);                    

                case CREDIT:
                    capReq = gson.fromJson(payload, CaptureRequest.class);
                    return nodeWrite(commsNode, CREDIT, "CaptureRequest", capReq);

                case DEBIT:
                    capReq = gson.fromJson(payload, CaptureRequest.class);
                    return nodeWrite(commsNode, DEBIT, "CaptureRequest", capReq);
                                 
                case SIGNON:
                    return nodeWrite(commsNode, SIGNON,"String","");
                    
                case SIGNOFF:
                    return nodeWrite(commsNode, SIGNOFF,"String","");

                case PING:
                    return nodeWrite(commsNode, PING,"String","");

                case CLIENTDISCONNECT:
                    return nodeWrite(commsNode, CLIENTDISCONNECT, "String", "");

                case CLIENTCONNECT:
                    return nodeWrite(commsNode, CLIENTCONNECT, "String", "");

                case CLIENTLISTENERREFRESH:
                    return nodeWrite(commsNode, CLIENTLISTENERREFRESH, "String", "");                    
            }
        }
        return null;
    }

    private void serverResponse(Object obj) {
        if (obj != null) {
            if (obj instanceof CommsServerMessage) {
                CommsServerMessage response = (CommsServerMessage) obj;
                System.out.println(response.getState());
                System.out.println(response.getType());
                switch(response.getState()){
                  case AUTHORISE:
                  case REFUND   :
                  case FINANCIAL:    
                           AuthoriseResponse authResp = (AuthoriseResponse) response.getMessageObj();
                           System.out.println(authResp.toJSON());
                           break;
                  case CAPTURE:
                  case DEBIT  :
                  case CREDIT :
                           CaptureResponse capResp = (CaptureResponse) response.getMessageObj();
                           System.out.println(capResp.toJSON());
                           break;
                  case SIGNON :
                  case SIGNOFF:    
                  case PING   : 
                           if (response.getMessageObj() instanceof String) {
                              System.out.println(response.getMessageObj().toString());
                           }
                           break;
                }

            } else {
                System.out.println("The response message was not a CommsServerMessage object.");
            }
        } else {
            System.out.println("Exception : Issue with the server node response.");
        }
    }

    private void Controller(String operation, String payload) throws IOException, ClassNotFoundException {

        switch (operation) {

            case AUTHORISE:
                serverResponse(serverRequest(AUTHORISE,payload));
                break;

            case CAPTURE:
                serverResponse(serverRequest(CAPTURE,payload));
                break;

            case REVERSAL:
                serverResponse(serverRequest(REVERSAL,payload));
                break;

            case REFUND:
                serverResponse(serverRequest(REFUND,payload));
                break;
                
            case FINANCIAL:
                serverResponse(serverRequest(FINANCIAL,payload));
                break;                

            case DEBIT:
                serverResponse(serverRequest(DEBIT,payload));
                break;

            case CREDIT:
                serverResponse(serverRequest(CREDIT,payload));
                break;

            case PING:
                serverResponse(serverRequest(PING,payload));
                break;

            case SIGNON:
                serverResponse(serverRequest(SIGNON,payload));
                break;

            case SIGNOFF:
                serverResponse(serverRequest(SIGNOFF,payload));
                break;
                
            case CLIENTCONNECT:
                serverResponse(serverRequest(CLIENTCONNECT,payload));
                break;

            case CLIENTDISCONNECT:
                serverResponse(serverRequest(CLIENTDISCONNECT,payload));
                break;  

            case CLIENTLISTENERREFRESH:
                serverResponse(serverRequest(CLIENTLISTENERREFRESH,payload));
                break;                  

        }
    }

    /*
    send authorise {
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
        };
    
    send authorise {
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
            "threeDSecureResult":"2"
        };    
     */
    private void Authorisation() {

    }

    private void displayLogo() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("@@@@@@@@@@@   @@@@@@@     ");
        System.out.println("@@@@@@@@@@@   @@@@@@@@@   ");
        System.out.println("@@@@@@@@@@@   @@@@@@@@@@@ ");
        System.out.println("@@@@@@@@@@@   @@@@@@@@@@@@");
        System.out.println("@@@@@             @@@@@@@@");
        System.out.println("@@@@@  oooooooo     @@@@@@");
        System.out.println("@@@@@  ooooooooooo   @@@@@");
        System.out.println("@@@@@  oooooooooooo  @@@@@");
        System.out.println("@@@@@  oooooooooooo  @@@@@");
        System.out.println("@@@@@   ooooooooooo  @@@@@");
        System.out.println("@@@@@@     oooooooo  @@@@@");
        System.out.println("@@@@@@@              @@@@@");
        System.out.println("@@@@@@@@@@@   @@@@@@@@@@@@");
        System.out.println(" @@@@@@@@@@   @@@@@@@@@@@@");
        System.out.println("  @@@@@@@@@   @@@@@@@@@@@@");
        System.out.println("    @@@@@@@   @@@@@@@@@@@@");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    private void displayHeader() {

        displayLogo();

        System.out.println("=======================================================================");
        System.out.println("CCPGW Comms Server Controller version 1.0.0");
        System.out.println("Copyright (c) 2020 Truteq Australia");
        System.out.println("=======================================================================");
        System.out.println("Machine : " + getMachineAddress());
        System.out.println("Username: " + getProp().getProperty("user.name"));
        System.out.println("=======================================================================");
    }

    private void Help() {
        System.out.println("========================================================================");
        System.out.println("Processing Client help");
        System.out.println("========================================================================");
        System.out.println(EXIT + " - Quits and exits the Controller application.");
        System.out.println(WHOAMI + " - Information on the xCoreServer, Controller's host and user.");
        System.out.println(HELP + " - Display a quick help document.");
        System.out.println(CONNECT + " - Connect to the CommsServ server.");
        System.out.println(DISCONNECT + " - Disconnects applications e.g.disconnect Application-Adapter-1;");
        System.out.println(INFO + " - Information on CommsServ.");
        System.out.println(CONNECTIONS + " - All connections to CommServ.");
        System.out.println(PING + " - Ping the server.");
        System.out.println(SHUTDOWN + "- WARNING! - Shuts the CommsServer down.");
        System.out.println(SEND + " Sends a command and payload : send [command] [payload] e.g. send signon");
        System.out.println("========================================================================");
    }

    /**
     * @return the ps
     */
    public PrintWriter getPs() {
        return ps;
    }

    /**
     * @param ps the ps to set
     */
    public void setPs(PrintWriter ps) {
        this.ps = ps;
    }

    /**
     * @return the os
     */
    public Writer getOs() {
        return os;
    }

    /**
     * @param os the os to set
     */
    public void setOs(Writer os) {
        this.os = os;
    }

    /**
     * @return the is
     */
    public Reader getIs() {
        return is;
    }

    /**
     * @param is the is to set
     */
    public void setIs(Reader is) {
        this.is = is;
    }

    /**
     * @return the input
     */
    public StringBuffer getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(StringBuffer input) {
        this.input = input;
    }

    /**
     * @return the prop
     */
    public Properties getProp() {
        return prop;
    }

    /**
     * @param prop the prop to set
     */
    public void setProp(Properties prop) {
        this.prop = prop;
    }

    /**
     * @return the machineAddress
     */
    public InetAddress getMachineAddress() {
        return machineAddress;
    }

    /**
     * @param machineAddress the machineAddress to set
     */
    public void setMachineAddress(InetAddress machineAddress) {
        this.machineAddress = machineAddress;
    }

    /**
     * @return the complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @param complete the complete to set
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
