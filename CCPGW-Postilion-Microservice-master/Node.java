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

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class Node {
    
    private final LogWrapper mlogger = new LogWrapper(Node.class);
 
    private String hostname;
    
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;    

    public Node(int port){
        
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
             mlogger.error("Exception Node Constructor: "+ ex, new Throwable().getStackTrace()[0]);
        }        
        
        
        try {
            this.socket = new Socket(getHostname(),port);
            initialise();
        } catch (IOException ex) {
             mlogger.error("Exception Node Constructor: "+ ex, new Throwable().getStackTrace()[0]);
        }
        
    }    

    public Node(Socket socket){
        this.socket = socket;
        try {
            initialise();
        } catch (IOException ex) {
            mlogger.error("Exception Node Constructor: "+ ex, new Throwable().getStackTrace()[0]);
        }
    }    
    
    private void initialise() throws IOException{
    //--------------------------------------------------------------------------
    // NB:
    // ~~~
    // You need to create the ObjectOutputStream before the ObjectInputStream 
    // at both sides of the connection(!). When the ObjectInputStream is created,
    // it tries to read the object stream header from the InputStream. So if 
    // the ObjectOutputStream on the other side hasn't been created yet there is 
    // no object stream header to read, and it will block indefinitely.

    //Or phrased differently:
    //~~~~~~~~~~~~~~~~~~~~~~
    // If both sides first construct the ObjectInputStream, both will block 
    // trying to read the object stream header, which won't be written until 
    // the ObjectOutputStream has been created (on the other side of the line); 
    // which will never happen because both sides are blocked in the constructor
    // of ObjectInputStream.        
    //--------------------------------------------------------------------------        
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

    }
    
    public Object waitForResponse() throws IOException, ClassNotFoundException{
         Object obj = null;
         while (obj == null)
             obj = read();
         return obj;
    }
    
    public void shutDown() throws IOException{
            this.ois.close();
            this.oos.close();
    }

    public Object read()throws IOException, ClassNotFoundException{
        Object obj = ois.readObject();
        return obj;
    }
    
    public void write(Object obj) throws IOException{
        oos.writeObject(obj);
    }        

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
    
}
