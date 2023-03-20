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
package com.truteq.ccpgw.communication.server;

import com.truteq.ccpgw.comms.server.model.CommsServerMessage;
import com.truteq.ccpgw.comms.server.model.Disconnect;
import com.truteq.ccpgw.comms.server.model.Initiate;
import com.truteq.ccpgw.comms.server.model.ICommands;
import com.truteq.ccpgw.comms.server.model.Terminate;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class ServerDispatcher extends Thread implements ICommands{

    private final LogWrapper mlogger = new LogWrapper(ServerDispatcher.class);

    private final ServerContainer server;
    boolean active;
    private final Node node;
    private String applicationName;
    private String description;
    
    public ServerDispatcher(ServerContainer server, Socket socket) {
        this.server = server;
        this.node = new Node(socket);
    }

    @Override
    public void run() {
        active = true;
        try {
            mlogger.info("Thread started with name:" + Thread.currentThread().getName(), new Throwable().getStackTrace()[0]);

            while (active) {
                Object obj = this.getNode().read();
                System.out.println(obj.toString());
                readMessage(obj);
            }
        } catch ( ClassNotFoundException | IOException ex) {
            mlogger.error("Exception: "+ex, new Throwable().getStackTrace()[0]);
        }
    }

    private void readMessage(Object obj) throws IOException {
        if (obj instanceof Terminate) {
            mlogger.info("REQUEST TO SHUTDOWN SERVER", new Throwable().getStackTrace()[0]);

            //Write back to the client notifying the client that the message was received
            this.getNode().write(new CommsServerMessage("info","String","Received Terminate instruction and shutting down."));

            active = false;
            this.getNode().shutDown();
            server.setShutdown(true);
            server.shutdown();
        }
        
        if (obj instanceof Disconnect) {
           Disconnect disconnect = (Disconnect) obj;
           mlogger.info("Disconnecting "+disconnect.getName(), new Throwable().getStackTrace()[0]);
           
           
           
           //Write back to the client notifying the client that the message was received
//           if (disconnect.getName().equals(this.getName()))
//               this.getNode().write(new CommsServerMessage("info","String","self"));
//           else
//               this.getNode().write(new CommsServerMessage("info","String","Disconnected "+disconnect.getName()+" on Comms Server."));
           
           server.disconnect(disconnect.getName());
           this.active = false;
        }
        
        if (obj instanceof Initiate){
            Initiate initiate = (Initiate) obj;
            mlogger.info("Initiating "+initiate.getName(), new Throwable().getStackTrace()[0]);
            this.setApplicationName(initiate.getName());
            this.setDescription(initiate.getDescription());
        } 
        
        if (obj instanceof CommsServerMessage) {
            CommsServerMessage m = (CommsServerMessage) obj;
            switch (m.getState()) {
                case CONNECTIONS:
                    this.getNode().write(this.server.getConnections());
                    break;
                case INFO:
                    this.getNode().write(this.server.getInfo());
                    break;
                case SIGNON:  
                    this.getNode().write(this.server.performSignOn());
                    break; 
                case SIGNOFF:  
                    this.getNode().write(this.server.performSignOff());
                    break; 
                case PING:  
                    this.getNode().write(this.server.performPing());
                    break;
                case AUTHORISE:  
                    this.getNode().write(this.server.performAuthorise(m.getMessageObj()));
                    break;
                case CAPTURE:  
                    this.getNode().write(this.server.performCapture(m.getMessageObj()));
                    break;
                case REVERSAL:  
                    this.getNode().write(this.server.performReversal(m.getMessageObj()));
                    break;
                case REFUND:  
                    this.getNode().write(this.server.performRefund(m.getMessageObj()));
                    break;
                case FINANCIAL:  
                    this.getNode().write(this.server.performFinancial(m.getMessageObj()));
                    break;
                case DEBIT:  
                    this.getNode().write(this.server.performDebit(m.getMessageObj()));
                    break;     
                case CREDIT:  
                    this.getNode().write(this.server.performCredit(m.getMessageObj()));
                    break;
                case TEST:  
                    this.getNode().write(this.server.performTest(m.getMessageObj()));
                    break;
                case CLIENTDISCONNECT:  
                    this.getNode().write(this.server.performClientDisconnect());
                    break;
                case CLIENTCONNECT:  
                    this.getNode().write(this.server.performClientConnect());
                    break;
                case CLIENTLISTENERREFRESH:  
                    this.getNode().write(this.server.performClientListenerRefresh());
                    break;                     
            }
        }
    }

    /**
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param applicationName the applicationName to set
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

}
