
import com.google.gson.Gson;
import com.truteq.ccpgw.payment.gateway.api.json.PaymentDetails;
import com.truteq.ccpgw.payment.gateway.util.DataEncryption;
import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testDecodeTransactionDetailsPayload {
    public static void main(String args[]) throws Exception {
        
        String payload = //"37oPe/CX+98+vLQ82rumBeAxToJxH7ahbV3fqf7vF3vTL011A9ZlT+9pUz9uR8GQHmPZVowUYq8FwRLVyjm4B4xma5SEANMRtEOw5ONvW+K4RUiiJHqx6xM5evL9aPVSdp7MleIdkHB0H3AlIDYvWmITJZQtAVrC4WQ5iySSsVMWDpNzUju2FDyPLmxfJf5cl0u9f125zwTIkLsUjlsReDJ8n4/tMewF+g68yMnDWzY7nuywlWlEisfyX5s/AxgpDCEU21uCTW/3vhP1Ow1Ja3715l/xyj6D7NMvyX33l7kKQDu2Bny+l5RliEWmLJQGZdmtvKoN+Nyg7NolrNhv4pEYugQLRip48oHNtSqd00srlppy/KFU3eaJyA6bL8HV3NEmSO9TRp5HUkQQJkrmyteLEp061k2bA07TUw4d7ZbFjENcPfEn4DxafYS1tf+8atjf8P9J/GwBZMzkcsXyRw==";
        //"K09GjF4S6be1WaezzD5t9u5IPJwqnvvavSY0rqehiDeNsvNhv1NoWiUBRwSy+HDGpPMPUw7f3XKNJNC0Gkvt9H8AY5TSXy8Y3EaAmcJkmNDHXLu4mzMDqMxnImRa/6YpWfNQUhEEH86UeQnHYFPapzB5Cz/OdVb5gYJM/mqV87aeO+yc1sUTC0fTvQca9W+tuSnjLBazKqblXhs5MkO/6z+mSOwNB4A35v+N/Xd/7vwK/01c7ixBVpjkbDLz0Kq1+37ger35HRmyt/3osMla8QszCbilvkkv9cCLkq6kXaTcIxeKCn9nwOX5FoFCxGj3wT0xsg7TeAjIwLHVHcHnz/geAAh5JbBgou06ce1iBne+xOQyOHlXMyvOGmDCuADzDKX7JuTqPc00cazfxPcBMDxnfJN7OQ8dNWEuUQ2d5nK42JzOL19VxbuM0NkQGJb8YkH5Z2sqQHYerrk7hOn4pg==";
        "732VYDM3bDGuIzz9kgAyvpJKztIS8flWGpM51JmavLOyKUDkgT4ukqBy6764cIQG+svBQA7Uxqpqno7ts8+0omHL52kfte7s0s4UzyXzDL5zcs2kdAmW9PB76KhxusTaI7xZcWJmGnXeQkJmRKvOLYlobKgY8V7CdXBaP7a7yP4kW1l/B11oMjA7ksO7j21JrhZCdKPdfzshvzFPOLxdcBD0fnan72HtsxVWbxVWzCbpWXlW9znjj/Ml/02kyrQmv2NZMwr3jI9x/nMQRtduDvSmBwWbFAVVhgDmaCkIVzGm3Bkike7IoKaebajjxKc2k3Ee3QEQqk4hta8wuPvht695cxuCEN1XtIJBAdrwdiF2oYGnqtUNLb4EBUe9EYSCGKY+G8hgyT0A51rO2jyAUychBO2GPfj9bZY/G4WzepsdEwF+ZdIJLKtqQ38X0LqzNHoLU41ylqWeczo+6KpVHG4+CGF0Ne+b/VWnThbjTKrhqw9jx0jQ1GIf4QaU3bUI";
                DataEncryption encrypt = new DataEncryption();
        
        String decryptedString = encrypt.DecryptAES256(payload);
        
        Gson gson = new Gson();
        PaymentDetails payDetail = gson.fromJson(decryptedString, PaymentDetails.class);
       
        
        System.out.println("PaymentDetail JSON Object : "+payDetail.toJSON());
    }     
}
