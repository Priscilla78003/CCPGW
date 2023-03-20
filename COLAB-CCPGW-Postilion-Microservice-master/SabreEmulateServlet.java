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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@WebServlet(urlPatterns = "/transaction/acs/servlet/sabre/emulate")
public class SabreEmulateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //String action = "https://ccpgw.testbsp.com.pg/transaction/acs/servlet/main";
        //String termUrl = "http://ccpgw.testbsp.com.pg/transaction/acs/servlet/response";        
        
        String action = "https://ipgw.testbsp.com.pg/transaction/acs/servlet/main";
        String termUrl = "http://ipgw.testbsp.com.pg/transaction/acs/servlet/response";
        
        //String termUrl = "https://ipe.cert.havail.sabre.com/ipe/3DS?merchantReference=01811634045528783038&amp;merchantId=PX";
        //String action = "http://localhost:9079/transaction/acs/servlet/main";
        //String termUrl = "http://localhost:9079/transaction/acs/servlet/response";

        //String value = "eJxVUcFy2jAQ/RWP72Ylg1xg1sqkSQkcyDCUHHIU0iZ4imWQRQz9+krUNOlF896u9Hb1Ht6d633yQa6tGlumfMDShKxuTGXfy/RlM8vGadJ6ZY3aN5bK9EJteidxs3NEjz9JnxxJXFLbqndKKlOmB2XI8OEbbbM3wUbZSGxFti3IZBP9jZtA+ZZPUomr+zUdJfazZRg9yBFuNGg6vVPWS1T6+H3xLHk+HIkCoadYk1s8yngahL8ErapJLhv9C+EKUTcn691FcsYQbgRPbi933h/aKUDXdYM6vBjopgaE2EL4HL46RdQGqXNlpMuXv32xFqDrzcMxbz4qXeRzsjDuSoR4A43yJHOWc87ZMGFsytlUTBCudVR13EGKuE6P8RBH3H9pfC1gcNiFRC5STMbhCzeGdD6EQMKNYNo/jPC578M8Wqd98OWJj8UrH61+PM+X66f1QvjZcdZFK6/tqFUFX/IhK65ikSBEAehTgj7igP6L/g+KFr3C";
        String value = "eJxVUl1TqzAQ/SsM7zQJFAqdJU61fo2jtlbneh/TsLaMJdAQpP33Jv24evN0zmZzdvds4GJXbbwv1G1Zq9xnA+p7qGRdlGqV+2+vN0Hqe60RqhCbWmHu77H1Lzi8rjXidIGy08jhEdtWrNAri9xvhBhKSaNEBCMxioKhXKZBJmkYIH6IOPmIkyyNfA6zyQtuOZxqc1t6EAI5U6up5Voow0HI7eX9E4+jiKUMyIlChfp+yoeUstntAz2eGMgxDEpUyBdiqRHIAYOsO2X0nsdZCuRMoNMbvjamaceE9H0/aN2TgawrAsTdAflpZNY51FqtXVnwmuiXfVhv/3z170WynGp8/5uI6SKqrnMgLgMKYZCHNGSM0cxj4ZgmYzYCcoiDqFwTPKUptVMdCTSuxuT3ze8IWL+13c95ijMD3DV2PTbDWvgPA/np+OrOGSmN9WbbqUn4ps086zZL8Zw2SWRGq/ndpP/Mnb2HJKdYWoPCmEYHSUeAOBly2hw5rd2i/77DN8e8w6o=";
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<script>var timer = setInterval(function(){document.getElementById('3DSecureform').submit();if(timer){clearInterval(timer)};}, 1);</script>");
        out.println("<body>");
        out.println("<form method=\"POST\" action="+action+" name=\"3DSecureform\" id=\"3DSecureform\" >");
        out.println("<input type=\"hidden\" name=\"MD\" value=\"merId\" /><input type=\"hidden\" name=\"PaReq\" value="+value+" /><input type=\"hidden\" name=\"TermUrl\" value="+termUrl+" />");
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
/*
         

<html>

   <script>var timer = setInterval(function(){document.getElementById('3DSecureform').submit();if(timer){clearInterval(timer)};}, 1);</script>

   <body>

      <form method="POST" action=https://3dss.extranet.netcetera.biz/3dss-demo/3ds1/acs/authenticate name="3DSecureform" id="3DSecureform" >

         <input type="hidden" name="MD" value="merId" /><input type="hidden" name="PaReq" value="eJxVUttu4jAQ/ZUo78GxcyFBE1fQixatWkEplbpvTjKFoMRJ7VCgX1+bhnb7Ys2ZGZ8zPmO4Oja1845KV63MXDryXQdl0ZaV3GTu+unOS1xH90KWom4lZu4JtXvF4WmrEG9WWOwVcrhHrcUGnarM3E6wPE7zMoq9NMSxF47T0Msx9r0ywSBiRRSmQeFyWEwf8Y3DoM2N9IgBuUDDqYqtkD0HUbzN5g+csiCMYiADhAbV/IbbswTyBUCKBvlK5AqBnGMo2r3s1YlT3wdyAbBXNd/2facnhBwOh5G2V0ZF2xAgtgbkR36xt5E2XMeq5LP6+mX8uMT1v+fl6/R2s/7Y/cWFjpL4NgNiO6AUPXLmM0p9yhwaTAI2oRTIOQ+isUPwIB3HZqIBQWdFpr9K/6fA+KzMXk48ShPzjgsCPHZmLabDWPcdA/mZ+fqPNbDojTuvB9muOjG7v3vZsaTezWWun5fW0HPZclXGHBbQLzILgFgCMuyKDIs20a8P8AmUib/u" /><input type="hidden" name="TermUrl" value=https://ipe.cert.havail.sabre.com/ipe/3DS?merchantReference=01811634045528783038&amp;merchantId=PX />

         <noscript>

            <br/><br/>

            <div style="text-align: center">

               <h1>Processing your Transaction </h1>

               <p>Please click continue to continue the processing of your transaction.</p>

               <input type="submit" name="submit" value="continue" />

            </div>

         </noscript>

      </form>

   </body>

</html>
        
*/ 