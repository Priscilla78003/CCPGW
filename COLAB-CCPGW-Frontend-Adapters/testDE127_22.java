/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testDE127_22 {
    
    private String constructStruct(String value){
       StringBuilder struct = new StringBuilder(); 
       
       Integer valueLength = value.length();
       struct.append((valueLength+"").length())
             .append(valueLength)
             .append(value);
       
       return struct.toString();
    }
    
    public String constructStructuredDataDE127_22(String tdsVersion, 
                                                  String tdsAuthMethod, 
                                                  String dsTransId){
        String tdsVersionKey = "3D_SECURE_VERSION";
        String tdsAuthMethodKey = "3DS_AUTH_METHOD";
        String dsTransIdKey  = "DIRECTORY_SERVER_TRANSACTION_ID";

        StringBuilder structuredData = new StringBuilder();
        
        if (tdsAuthMethod.length()>1){
           tdsAuthMethod = tdsAuthMethod.substring(1, 2);
        }        
        
        structuredData.append(constructStruct(tdsVersionKey))
                      .append(constructStruct(tdsVersion))
                      .append(constructStruct(tdsAuthMethodKey))
                      .append(constructStruct(tdsAuthMethod))
                      .append(constructStruct(dsTransIdKey))
                      .append(constructStruct(dsTransId)        
        );
        
        return structuredData.toString();
    }
    
    
    public static void main(String[] args) {
        String tdsVersion =  "2";
        String tdsAuthMethod =  "02";
        String dsTransId =  "f25084f0-5b16-4c0a-ae5d-b24808a95e4b";
        
        testDE127_22 test = new testDE127_22();
        
        String structuredData = test.constructStructuredDataDE127_22(tdsVersion, tdsAuthMethod, dsTransId);
        System.out.println("000962173D_SECURE_VERSION111231DIRECTORY_SERVER_TRANSACTION_ID236abcdefghijhlmnopqrstuvwxyz1234565789");
         System.out.println("2173D_SECURE_VERSION111231DIRECTORY_SERVER_TRANSACTION_ID236abcdefghijhlmnopqrstuvwxyz1234565789".length());
        System.out.println(structuredData);
    }
}
