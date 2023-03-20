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

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import com.truteq.ccpgw.transaction.manager.model.AcsPareqElement;
import com.truteq.datagenerics.GenericDatabaseDAO;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
@WebServlet (urlPatterns = "/transaction/acs/servlet/main")
public class ACSServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    GenericDatabaseDAO databaseDao;

    private final LogWrapper mLogger = new LogWrapper(ACSServlet.class);
    private String threedskey = "";
    private String threedsvalue = "";

    private void loadProperties(String filename) {

        mLogger.info("Loading properties for Servlet.");
        try (InputStream input = new FileInputStream(filename)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            this.threedskey = prop.getProperty("header.key.3dsorgid");
            this.threedsvalue = prop.getProperty("header.value.3dsorgid");

        } catch (IOException ex) {
            this.mLogger.error("Error load properties file: " + ex.getMessage(), new Throwable().getStackTrace()[0]);
        }
    }

    /**
     * =========================================================================
     * readOrder
     * -------------------------------------------------------------------------
     * Added by Grant O'Reilly 18 Oct 2021 for the JSON Front End Adapter
     * =========================================================================
     *
     * @param pareq_in
     * @return
     */
    private String readACS(String pareq_in) {
        AcsPareqElement acsPareqElement = (AcsPareqElement) databaseDao.findByName("ccpgw.api.read.acspareq", AcsPareqElement.class, pareq_in);

        return acsPareqElement.getAcsurl();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        loadProperties("config/application.properties");

        response.setContentType("text/html");
        response.addHeader(this.threedskey, this.threedsvalue);

        mLogger.info("Proxying the ACS request");

        String termUrl = request.getParameter("TermUrl"); //https://ipe.int.sabre.com/ipe/3DS?merchantReference=01511634874976537902&amp;merchantId=PX
                                                          //https://localhost:9078/transaction/manager/servlet/acs/response
        String paReq = request.getParameter("PaReq");

        
//        if (paReq == null) {
//                String termUrlAtt = (String) request.getAttribute("TermUrl");
//                String paReqAtt = (String) request.getAttribute("PaReq");
//                
//                termUrl = termUrlAtt;
//                paReq = paReqAtt;      
//        }        
        
        
        String acs = readACS(paReq); //https://3dss.extranet.netcetera.biz/3dss-demo/3ds1/acs/authenticate
        
        mLogger.debug("3DS key: "+this.threedskey);
        mLogger.debug("3DS value: "+this.threedsvalue);
        mLogger.debug("ACS Url: "+acs);
        mLogger.debug("TermUrl: "+termUrl);
        mLogger.debug("PaReq: "+ paReq);

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>var timer = setInterval(function(){document.getElementById('3DSecureform').submit();if(timer){clearInterval(timer)};}, 1);</script>");
        out.println("<body>");
        out.println("<form method=\"POST\" action=" + acs + " name=\"3DSecureform\" id=\"3DSecureform\" >");
        out.println("<input type=\"hidden\" name=\"MD\" value=\"merId\" /><input type=\"hidden\" name=\"PaReq\" value=\"" + paReq + "\" /><input type=\"hidden\" name=\"TermUrl\" value=" + termUrl + " />");
        out.println("<noscript>");
        out.println("<br/><br/>");
        out.println("<div style=\"text-align: center\">");
        out.println("<h1>Processing your Transaction </h1>");
        out.println("<p>Please click continue to continue the processing of your transaction.</p>");
        out.println("<input type=\"submit\" name=\"submit\" value=\"continue\" />");
        out.println("</div>");
        out.println("</noscript>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>  ");

    }
}
