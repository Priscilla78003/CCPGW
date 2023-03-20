
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
public class testBase64Decode {
    public static void main(String[] args) {
        String threeDSMethodData = "eyJtZXNzYWdlVHlwZSI6IkFSZXMiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImZhOGE1OGViLWI0NzQtNDZlZi05MzdjLTc1NDdmNzAxZTI5MCIsImFjc1RyYW5zSUQiOiI0MDU0MzNkNS1kZGVjLTQ1MmMtYjhmYy1jMmNiMDc4Yzg5ZmEiLCJjaGFsbGVuZ2VDb21wbGV0aW9uSW5kIjoiWSIsIm1lc3NhZ2VWZXJzaW9uIjoiMi4xLjAiLCJ0cmFuc1N0YXR1cyI6IlkifQ=="; 
                                  //"eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImEwMDlhMTU3LTg1NTQtNDYwNS04NTVkLWU4M2ZhNjVkNDllOSJ9";
                                   //"eyJtZXNzYWdlVHlwZSI6IkNSZXMiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjkwMDA4NGQ5LWNkNzEtNGFkZS1iZGFmLTgwYjIzYWJlMDQ3MyIsImFjc1RyYW5zSUQiOiI5NDMwMWQwZi01MWRkLTRiN2YtOWRmNy01YzViZTE3MTU5MDciLCJjaGFsbGVuZ2VDb21wbGV0aW9uSW5kIjoiWSIsIm1lc3NhZ2VWZXJzaW9uIjoiMi4xLjAiLCJ0cmFuc1N0YXR1cyI6IlkifQ==";

                                   //"eyJtZXNzYWdlVHlwZSI6IkNSZXMiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjAxY2NkNGRhLTcxM2EtNDFkYS05YWVmLTg0YjdhNzAyOWI1YSIsImFjc1RyYW5zSUQiOiJjMjRlZDliYi1kNjlhLTRhMGEtOGFiOC05ZTgwZTQ4Yzk5MTgiLCJjaGFsbGVuZ2VDb21wbGV0aW9uSW5kIjoiWSIsIm1lc3NhZ2VWZXJzaW9uIjoiMi4xLjAiLCJ0cmFuc1N0YXR1cyI6IlkifQ";
                                   //"eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjBlZTlhOGVkLWI1OTQtNDkxYS05YjBkLWQzNjBmOTEzMmEwZCJ9";

                                   //"eyJtZXNzYWdlVHlwZSI6IkNSZXMiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjBlZTlhOGVkLWI1OTQtNDkxYS05YjBkLWQzNjBmOTEzMmEwZCIsImFjc1RyYW5zSUQiOiJlODIxM2EyNC02ZTRiLTQyNWItOWFkNC04YTA4NjZkZjAwMTciLCJjaGFsbGVuZ2VDb21wbGV0aW9uSW5kIjoiWSIsIm1lc3NhZ2VWZXJzaW9uIjoiMi4xLjAiLCJ0cmFuc1N0YXR1cyI6IlkifQ";
                                   //"eyJtZXNzYWdlVHlwZSI6IkNSZXEiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjI5M2FhNDUyLTNkN2ItNGI4Zi04YmJiLWZlNWZiMDVkNzllMyIsImFjc1RyYW5zSUQiOiI4ZjNkODQyNy1iMDA0LTQyY2MtYTc3YS1iYTgwMjBlYjUxMDMiLCJjaGFsbGVuZ2VXaW5kb3dTaXplIjoiMDEiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIn0";
                                   //"eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImViZjMxNDNkLTQ1ZDQtNDUxZS05YTgzLWUyOGI2ZmNlM2E5NiJ9";
                                   // "eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImE2M2I1ZjEzLTc3ZDYtNDI1Ny05ZDg3LTY2MGUxMTY1YTFiNyJ9";
                                   //"eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjExM2RhMDViLTRjYzktNDM1Ny04YmYzLWM0NzU2ZDNkZjJhNSJ9";
                                   //"eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImViMmI2YzY5LWNjOGMtNGYwZC05NTM4LTA1OTFjODMzYzJkYSJ9";
                                      //eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImViMmI2YzY5LWNjOGMtNGYwZC05NTM4LTA1OTFjODMzYzJkYSJ9
                                      //eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjM4YmI1NDQ4LTZlYTctNGYzNC1iZGY4LTg3NTY2NTAxZDQ0ZSJ9
                                      //eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjM4YmI1NDQ4LTZlYTctNGYzNC1iZGY4LTg3NTY2NTAxZDQ0ZSJ9        
                                   //"eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjM4YmI1NDQ4LTZlYTctNGYzNC1iZGY4LTg3NTY2NTAxZDQ0ZSJ9";
                                   // eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9jY3Bndy50ZXN0YnNwLmNvbS5wZy8zZHNtZXRob2Rub3RpZmljYXRpb24iLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6IjU3ZDRjM2E2LTA5NmYtNGIzMy1iNTYwLTlhNmU4Yjk4OGVmOSJ9
        //System.out.println("This should decode to {\"threeDSMethodNotificationURL\":\"https://ccpgw.testbsp.com.pg/3dsmethodnotification\",\"threeDSServerTransID\":\"6816abd7-3c7c-4d59-8fbd-e0663d25941f\"}");
        
        byte[] decodedBytes = Base64.getDecoder().decode(threeDSMethodData);
        String decodedString = new String(decodedBytes);
        System.out.println(decodedString);
    }
}
