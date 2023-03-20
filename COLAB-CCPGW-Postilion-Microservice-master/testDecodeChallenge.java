
import com.google.gson.Gson;
import com.truteq.ccpgw.transaction.notification.microservice.threeds.controller.CReqObject;
import com.truteq.ccpgw.transaction.notification.microservice.threeds.controller.CResObject;
import com.truteq.ccpgw.transaction.notification.microservice.threeds.controller.CTypeEnum;
import com.truteq.ccpgw.transaction.notification.microservice.threeds.controller.IChallenge;
import java.util.Base64;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testDecodeChallenge {
    
    
    private IChallenge DecodeAndFindValue(String encodedString, CTypeEnum type){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        
        Gson gson = new Gson();
        switch (type){
            case CREQ :   return gson.fromJson(decodedString,CReqObject.class);
            case CRES :   return gson.fromJson(decodedString,CResObject.class);  
            default   :   return null;  
        }
          
    }    
    
    public static void main(String[] args) {
        String creq = "eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImI1ZDNmYzJjLWM4NjEtNGJiNS1iODAxLTZlYmY3Y2RmZDdjMyJ9";
                      //"eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImI1ZDNmYzJjLWM4NjEtNGJiNS1iODAxLTZlYmY3Y2RmZDdjMyIsInRocmVlRFNNZXRob2ROb3RpZmljYXRpb25VUkwiOiJodHRwczovL2NjcGd3LnRlc3Ric3AuY29tLnBnLzNkc21ldGhvZG5vdGlmaWNhdGlvbiJ9" ;        
                      // eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImI1ZDNmYzJjLWM4NjEtNGJiNS1iODAxLTZlYmY3Y2RmZDdjMyIsInRocmVlRFNNZXRob2ROb3RpZmljYXRpb25VUkwiOiJodHRwczovL2NjcGd3LnRlc3Ric3AuY29tLnBnLzNkc21ldGhvZG5vdGlmaWNhdGlvbiJ9
                      //"eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjBlZTlhOGVkLWI1OTQtNDkxYS05YjBkLWQzNjBmOTEzMmEwZCJ9";
        String cres = "eyJtZXNzYWdlVHlwZSI6IkNSZXMiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjBlZTlhOGVkLWI1OTQtNDkxYS05YjBkLWQzNjBmOTEzMmEwZCIsImFjc1RyYW5zSUQiOiJlODIxM2EyNC02ZTRiLTQyNWItOWFkNC04YTA4NjZkZjAwMTciLCJjaGFsbGVuZ2VDb21wbGV0aW9uSW5kIjoiWSIsIm1lc3NhZ2VWZXJzaW9uIjoiMi4xLjAiLCJ0cmFuc1N0YXR1cyI6IlkifQ";  
        
        
        String threeDSServerTransID = "b5d3fc2c-c861-4bb5-b801-6ebf7cdfd7c3";
        String stringToEncode = "{\"threeDSServerTransID\":\""+threeDSServerTransID+"\",\"threeDSMethodNotificationURL\":\"https://ccpgw.testbsp.com.pg/3dsmethodnotification\"}";
        String encoded = Base64.getEncoder().encodeToString(stringToEncode.getBytes());
        
        testDecodeChallenge test = new testDecodeChallenge();
        CReqObject creqObj = (CReqObject)test.DecodeAndFindValue(creq, CTypeEnum.CREQ);
        
        CResObject cresObj = (CResObject)test.DecodeAndFindValue(cres, CTypeEnum.CRES);
        
        System.out.println(creqObj.toJSON());
        
        System.out.println(cresObj.toJSON());
        
        System.out.println(encoded);
        
    }
}
