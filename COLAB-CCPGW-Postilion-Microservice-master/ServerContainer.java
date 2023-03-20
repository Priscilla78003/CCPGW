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
import com.truteq.ccpgw.communication.server.application.threads.PostilionAdapterThread;
import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.communication.server.timer.CheckConnectionTask;
import com.truteq.ccpgw.communication.server.timer.CleanUpTask;
import com.truteq.ccpgw.communication.server.timer.ScheduledTask;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Timer;


/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public final class ServerContainer {
    
    private final LogWrapper mlogger = new LogWrapper(ServerContainer.class);

    private ServerSocket serverSocket;
    private final String type;
    private final int port;
    private final String name;
    private final String version;
    private final int heartBeatPeriod;
    private boolean shutdown;
    private final HashMap clients;
    private InetAddress machineAddress; // This is the InetAddress of the machine on which the server is running.
    private int appCount = 0; 
    
    private PostilionAdapterThread postilionAdapterThread;
    private ScheduledTask heartBeatTask = new ScheduledTask(this,"Heart beat echo");
    private CleanUpTask cleanUpTask = new CleanUpTask(this,"CleanUp");
    private CheckConnectionTask checkConnectionTask = new CheckConnectionTask(this,"Check connection.");
    private Timer timer;


    public ServerContainer(String configFileName) {
        this.shutdown = false;
        this.clients = new HashMap<>();
        try {
            setMachineAddress(InetAddress.getLocalHost());
        } catch (UnknownHostException ex) {
        }          
        Properties props = loadProperties(configFileName);
        this.name = props.getProperty("servername").trim();
        this.version = props.getProperty("version").trim();
        this.port  = Integer.parseInt(props.getProperty("port"));
        this.type = props.getProperty("type").trim();
        this.heartBeatPeriod = Integer.parseInt(props.getProperty("heartbeat"));
    }
    
    private Properties loadProperties(String fileName) {
        System.out.println(System.getProperty("user.dir"));
        
        Properties props = new Properties();
        try {        
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
                                                          File.separator+
                                                          "config"+
                                                          File.separator+fileName);
                props.load(fis);
                fis.close();
        }catch (IOException  ex) {
            mlogger.error("Exception:Error when reading config.properties. "+ ex, new Throwable().getStackTrace()[0]);
        }
        
        return props;     

    }     

    public void start() throws IOException {
        
        mlogger.info("Initiating main thread with "+this.type, new Throwable().getStackTrace()[0]);
        startMainThread();       
        
        mlogger.info("Starting the socket server at port:" + port, new Throwable().getStackTrace()[0]);
        serverSocket = new ServerSocket(port);

        while (!isShutdown()) {
            mlogger.info("Waiting for clients...", new Throwable().getStackTrace()[0]);
            try {
                //Listen for clients. Block till one connects
                Socket socket = serverSocket.accept();
                if (!isShutdown()) {
                    //This is very important! Donot create anything further once a 
                    //Shutdown has been detected.
                    mlogger.info("The following client has connected:" + socket.getInetAddress().getCanonicalHostName()
                            + " with IP address: " + convertAddress(socket.getInetAddress().getAddress()), new Throwable().getStackTrace()[0]);
                    //A client has connected to this server. Send welcome message
                    Thread applicationAdapter = new ServerDispatcher(this, socket);
                    this.appCount++;
                    applicationAdapter.setName("Application-Adapter-"+appCount);
                    clients.put(applicationAdapter.getName(),applicationAdapter);
                    ((ServerDispatcher) clients.get(applicationAdapter.getName())).start();
                }
            } catch (SocketException ex) {
                mlogger.error("Server Socket terminated! Exception on start: " + ex, new Throwable().getStackTrace()[0]);
                System.exit(0);
            }
        }
    }
     
    public CommsServerMessage performAuthorise(Object messageObj){
        return postilionAdapterThread.getStub().authorise(messageObj);
    }
    
    public CommsServerMessage performCapture(Object messageObj){
        return postilionAdapterThread.getStub().capture(messageObj);
    }

    public CommsServerMessage performReversal(Object messageObj){
        return postilionAdapterThread.getStub().reversal(messageObj);
    }

    public CommsServerMessage performFinancial(Object messageObj){
        return postilionAdapterThread.getStub().financial(messageObj);
    }

    public CommsServerMessage performRefund(Object messageObj){
        return postilionAdapterThread.getStub().refund(messageObj);
    }    
    
    public CommsServerMessage performDebit(Object messageObj){
        return postilionAdapterThread.getStub().debit(messageObj);
    } 

    public CommsServerMessage performCredit(Object messageObj){
        return postilionAdapterThread.getStub().credit(messageObj);
    }     
    
    public CommsServerMessage performSignOn(){
        return postilionAdapterThread.getStub().signon();
    }

    public CommsServerMessage performPing(){
        return postilionAdapterThread.getStub().ping();
    } 

    public CommsServerMessage performSignOff(){
        return postilionAdapterThread.getStub().signoff();
    }          
    
    public CommsServerMessage performCleanUp(){
        return postilionAdapterThread.getStub().cleanup();
    }
    
    public CommsServerMessage performCheckConnection(){
        return postilionAdapterThread.getStub().checkconnection();
    }
    
    public CommsServerMessage performTest(Object messageObj){
        return postilionAdapterThread.getStub().test(messageObj);
    }    
    
    public CommsServerMessage performClientDisconnect(){
        return postilionAdapterThread.getStub().clientDisconnect();
    }  

    public CommsServerMessage performClientConnect(){
        return postilionAdapterThread.getStub().clientConnect();
    }  

    public CommsServerMessage performClientListenerRefresh(){
        return postilionAdapterThread.getStub().clientListenerRefresh();
    }  

    
    public List getConnections(){
        
        List connections = new ArrayList<>();
        Iterator i = clients.keySet().iterator();
        while (i.hasNext()){
            String adapter = (String)i.next();
            ServerDispatcher dispatcher = (ServerDispatcher)clients.get(adapter);
            connections.add(adapter+" | "+dispatcher.getApplicationName()+" | "+dispatcher.getDescription());
        }      
        return connections;        
    }
    
    public void disconnect(String name){

        Iterator i = clients.keySet().iterator();
        String adapterName = "";
        ServerDispatcher dispatcherToDisconnect = null;
        while (i.hasNext()){
            String adapter = (String)i.next();
            ServerDispatcher dispatcher = (ServerDispatcher)clients.get(adapter);
            if (dispatcher.getApplicationName().equals(name)){
                adapterName = adapter;
                dispatcherToDisconnect = dispatcher;
                break;
            }   
        }
        if (dispatcherToDisconnect != null){
            try{
                dispatcherToDisconnect.getNode().shutDown();
                clients.remove(adapterName);
            } catch (IOException ex) {
                mlogger.error("Exception when disconnecting dispatcher: "+ex, new Throwable().getStackTrace()[0]);
            }
        }        
        
//        try {
//            ServerDispatcher dispatcher = (ServerDispatcher)clients.get(name);
//            dispatcher.getNode().shutDown();
//            clients.remove(name);
//        } catch (IOException ex) {
//            mlogger.error("Exception when disconnecting dispatcher: "+ex);
//        }
    }
    
    public String getInfo(){
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("Hostname: ").append(getMachineAddress()).append("\n");
        strBuild.append("Name    : ").append(name).append("\n");
        strBuild.append("Version : ").append(version).append("\n");
        return strBuild.toString();
    }
    
    private void startMainThread(){
      
       Thread postilionAdapter = new Thread(postilionAdapterThread = new PostilionAdapterThread());
       postilionAdapter.start();

       timer = new Timer();
       timer.schedule(heartBeatTask, 10000, this.heartBeatPeriod); 
       timer.schedule(cleanUpTask, 60000, 300000);
       timer.schedule(checkConnectionTask, 60000, 120000);
              
    }

    private String convertAddress(byte[] address) {
        String addr = "";
        int count = 0;
        for (byte b : address) {
            if (count == 0) {
                addr = addr + b;
            } else {
                addr = addr + "." + b;
            }
            count++;
        }
        return addr;
    }

    public void shutdown() {
        
        try {
            serverSocket.close();
        } catch (SocketException ex) {
        } catch (IOException ex) {
            mlogger.error("Exception when shutting down server socket: "+ex, new Throwable().getStackTrace()[0]);
        }
    }

    /**
     * @return the shutdown
     */
    public boolean isShutdown() {
        return shutdown;
    }

    /**
     * @param shutdown the shutdown to set
     */
    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    /**
     * @return the clients
     */
    public HashMap getClients() {
        return clients;
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

}
