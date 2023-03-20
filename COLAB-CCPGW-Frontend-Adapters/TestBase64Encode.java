
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
public class TestBase64Encode {
    public static void main(String[] args) {
        //String threeDSMethodData = "eyJtZXNzYWdlVHlwZSI6IkNSZXMiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjkwMDA4NGQ5LWNkNzEtNGFkZS1iZGFmLTgwYjIzYWJlMDQ3MyIsImFjc1RyYW5zSUQiOiI5NDMwMWQwZi01MWRkLTRiN2YtOWRmNy01YzViZTE3MTU5MDciLCJjaGFsbGVuZ2VDb21wbGV0aW9uSW5kIjoiWSIsIm1lc3NhZ2VWZXJzaW9uIjoiMi4xLjAiLCJ0cmFuc1N0YXR1cyI6IlkifQ";

        
        String threeDSMethodData = "{\"messageType\":\"CRes\",\"threeDSServerTransID\":\"900084d9-cd71-4ade-bdaf-80b23abe0473\",\"acsTransID\":\"94301d0f-51dd-4b7f-9df7-5c5be1715907\",\"challengeCompletionInd\":\"Y\",\"messageVersion\":\"2.1.0\",\"transStatus\":\"Y\"}";
        byte[] encodedBytes = Base64.getEncoder().encode(threeDSMethodData.getBytes());
        String encodedString = new String(encodedBytes) ;
        System.out.println("should look like: eyJtZXNzYWdlVHlwZSI6IkNSZXMiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjkwMDA4NGQ5LWNkNzEtNGFkZS1iZGFmLTgwYjIzYWJlMDQ3MyIsImFjc1RyYW5zSUQiOiI5NDMwMWQwZi01MWRkLTRiN2YtOWRmNy01YzViZTE3MTU5MDciLCJjaGFsbGVuZ2VDb21wbGV0aW9uSW5kIjoiWSIsIm1lc3NhZ2VWZXJzaW9uIjoiMi4xLjAiLCJ0cmFuc1N0YXR1cyI6IlkifQ");
        System.out.println(encodedString);
    }    
}
