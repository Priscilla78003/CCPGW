
import com.google.gson.Gson;
import com.truteq.ccpgw.payment.gateway.adapter.threeDs.v2.x.ThreeDSV2xHandler;
import com.truteq.ccpgw.payment.gateway.api.soap.PaymentDetailType;
import com.truteq.ccpgw.postilion.rest.microserv.transaction.model.Result;
import com.truteq.ccpgw.threeds.v2.objects.authentication.MessageExtensionAttribute;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerAuthenticationRequest;
import com.truteq.ccpgw.threeds.v2.objects.authentication.ThreeDSServerAuthenticationResponse;
import com.truteq.ccpgw.threeds.v2.objects.versioning.ThreeDSServerVersioningResponse;
import com.truteq.ccpgw.threeds.v2.objects.versioning.ThreeDSV2ServerVersioningRequest;
import com.truteq.ccpgw.transaction.manager.comms.Communicator;
import com.truteq.ccpgw.transaction.manager.comms.SSLCommunicator;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class test3DSv2xCall {
    
    public ThreeDSServerVersioningResponse process3DSv2xVersioningRequest(String cardholderAccountNumber, String scheme){

        ThreeDSV2ServerVersioningRequest versionReq = new ThreeDSV2ServerVersioningRequest();
        
        System.out.println("Card scheme: " + scheme);

        versionReq.setCardholderAccountNumber(cardholderAccountNumber);
        versionReq.setSchemeId(scheme);
        Communicator Comms = new Communicator();
        Result result = Comms.sendHttpPost("http://localhost:9078/transaction/manager/3ds/v2/service/versioning", versionReq.toJSON().getBytes());
        
        System.out.println(result.getComments() + " " + result.getData());

        String resp = result.getData();

        Gson gson = new Gson();
        ThreeDSServerVersioningResponse versionRes = gson.fromJson(resp, ThreeDSServerVersioningResponse.class);
        System.out.println("Versioning Response :" + versionRes.toJSON()); 

        return  versionRes;       
        
    }    
    

    public ThreeDSServerAuthenticationResponse process3DSv2xAuthenticationRequest(){    
        
        ThreeDSServerAuthenticationRequest authReq = new ThreeDSServerAuthenticationRequest();
        
        ThreeDSV2xHandler threeDSV2xHandler = new ThreeDSV2xHandler();

        //authReq.setPreferredProtocolVersion("2.1.0");
        //authReq.setEnforcePreferredProtocolVersion(Boolean.FALSE);
        authReq.setDeviceChannel("02");
        authReq.setMessageCategory("01");
        authReq.setThreeDSCompInd("Y");
        authReq.setThreeDSRequestor(threeDSV2xHandler.get3DSv2xRequestor());

        
        authReq.setCardholderAccount(threeDSV2xHandler.get3DSv2xCardholderAccount("4916994064252017",
                                                                                  "1812",
                                                                                  "Visa"));
        authReq.setCardholder(threeDSV2xHandler.get3DSv2xCardholder("John Doe"));
        
        authReq.setPurchase(threeDSV2xHandler.get3DSv2xPurchase("1",
                                                                "598"));
        
        authReq.setAcquirer(threeDSV2xHandler.get3DSv2xAcquirer("428280",   //acquirerBin
                                                                "Visa")); //acquirerMerchantId
        
        authReq.setMerchant(threeDSV2xHandler.get3DSv2xMerchant("", // merchantConfigurationId
                                                                "4511",          //mcc 
                                                                "IBE SALES PGK",    //merchant name
                                                                "598")); 
        
        authReq.setBroadInfo(threeDSV2xHandler.get3DSv2xBroadInfo("TLS 1.x will be turned off starting summer 2019"));
        
        List<MessageExtensionAttribute> attributes = new ArrayList<>();
        attributes.add(threeDSV2xHandler.get3DSv2xMessageExtensionAttribute("id", "name"));
        authReq.setMessageExtension( attributes);
        
        authReq.setBrowserInformation(threeDSV2xHandler.get3DSv2xBrowser("192.168.1.11")); //IP address
        
        //authReq.setChallengeMessageExtension(List<MessageExtensionAttribute> challengeMessageExtension);
        //authReq.setDeviceRenderOptions(deviceRenderOptions);
        //authReq.setThreeRIInd(String threeRIInd);
        //authReq.setSdkInformation(Sdk sdkInformation);   
        
        System.out.println(authReq.toJSON());
        
        Communicator Comms = new Communicator();
        Result result = Comms.sendHttpPost("http://localhost:9078/transaction/manager/3ds/v2/service/authentication", authReq.toJSON().getBytes());
        
        System.out.println(result.getComments() + " " + result.getData());

        String resp = result.getData();

        Gson gson = new Gson();
        ThreeDSServerAuthenticationResponse authRes = gson.fromJson(resp, ThreeDSServerAuthenticationResponse.class);
        System.out.println("Authentication Response :" + authRes.toJSON()); 

        return authRes;
    }
    
    public static void main(String[] args) {
        test3DSv2xCall test = new test3DSv2xCall();
        //ThreeDSServerVersioningResponse versionRes = test.process3DSv2xVersioningRequest("4916994064252017", "Visa");

        ThreeDSServerAuthenticationResponse threeDSV2xAuthRes = test.process3DSv2xAuthenticationRequest();

    }
}
