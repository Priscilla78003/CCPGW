/*
 * ***************************************************************
 * Truteq Credit Card Payment Gateway (CCPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * CCPGW Microservices suite: ACS Servlet Manager 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  26-Oct-2021 
 * ***************************************************************
 */
package com.truteq.ccpgw.acs.servlet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
@WebServlet(urlPatterns = "/transaction/acs/servlet/response")
public class ACSResponseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        out.println("<h3>Hello from ACS!</h3>");
        
        Enumeration parameternames = request.getParameterNames();
        while(parameternames.hasMoreElements()){
            String name = (String)parameternames.nextElement();
            out.println(name);
            out.println(request.getParameter(name));
        }
       
    }

}
