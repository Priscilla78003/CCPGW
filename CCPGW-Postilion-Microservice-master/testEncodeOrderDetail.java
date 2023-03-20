
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testEncodeOrderDetail {


        public String Encrypt(String stringToEncode, String merchant_key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        
        String keyString = merchant_key.substring(0, 32);
        String iv =  merchant_key.substring(0, 16);       
        
        Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        SecretKeySpec secretKey = new SecretKeySpec(keyString.getBytes(), "AES");
        
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
        
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        
        String url =  Base64.getEncoder().encodeToString(encryptCipher.doFinal(stringToEncode.getBytes()));
        
        return URLEncoder.encode(url);
            
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{

       String merchant_key = "dc3c62ee4d349a6cda7edd11e5972ec2f16e7e5b4444826c774e59d0af3b0574"; 

       String callback_url = "http://gurgle:8081/checkout/order-received/2161/?key=wc_order_BO5cfw061buTI";
       
       testEncodeOrderDetail test = new testEncodeOrderDetail();
       //&amp;from=ccpgw&amp;trx=NWVCN17rdV%2Bvp%2BJRmZFLG8UUk%2Fh8sI10jY6FJl4wV3Jirj%2FRiT2xC%2BmX2Gt3WTNl
       String  qStringPlusRef = "&ref=" + "1675313151198" + "&status=success";
       
       String encoded = test.Encrypt(qStringPlusRef,merchant_key);
      
       String encryptedURL = URI.create(callback_url+ "&from=ccpgw&trx="+encoded).toString();
       
       System.out.println(encryptedURL);
      
       System.out.println("Working: http://gurgle:8081/checkout/order-received/2161/?key=wc_order_BO5cfw061buTI&from=ccpgw&trx=VnQNHC5JnR01OQ%2Bcm9fFnVReHnUPHzVxfxWVRrQ11irNLHeBhqDYV2YlN7ceM7B9");
        
//      if (wcOrderKeyPos > -1) {
//        String wc_order_key = oldUrl.substring(wcOrderKeyPos).trim();
//
//        String encryptedURL = oldUrl.replace(qString, "") + "?"+ wc_order_key + "&from=ccpgw&trx="+encoded;
//        
//        System.out.println(encryptedURL);
//
//      }
    }     
    
    
//        public String Encrypt(String stringToEncode, String merchant_key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
//        
//        String keyString = merchant_key.substring(0, 32);
//        String ivString =  merchant_key.substring(0, 16);       
//        
//        Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//
//        byte[] key = keyString.getBytes();
//        //MessageDigest sha = MessageDigest.getInstance("SHA-1");
//        //key = sha.digest(key);
//        //key = Arrays.copyOf(key, 16); 
//        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
//        
//        byte[] iv = ivString.getBytes();
//        IvParameterSpec ivspec = new IvParameterSpec(iv);
//        
//        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
//        
//        String url =  Base64.getEncoder().encodeToString(encryptCipher.doFinal(stringToEncode.getBytes()));
//        
//        return URLEncoder.encode(url);
//            
//    }
//    
//    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
//
//       String merchant_key = "dc3c62ee4d349a6cda7edd11e5972ec2f16e7e5b4444826c774e59d0af3b0574"; 
//
//       String callback_url = "http://gurgle:8081/checkout/order-received/2161/?key=wc_order_BO5cfw061buTI";
//       
//       testEncodeOrderDetail test = new testEncodeOrderDetail();
//       
//       
////       int index = oldUrl.indexOf("?");
////       int wcOrderKeyPos = oldUrl.indexOf("key=wc_order_");
//        
//       //String  qString = oldUrl.substring(index, oldUrl.length()).trim();
//       //String  qStringPlusRef = oldUrl.substring(index, oldUrl.length()).trim() + "&ref=" + "1675313151198" + "&status=success";
//       String  qStringPlusRef = "&ref=" + "1675313151198" + "&status=success";
//       
//      String encoded = test.Encrypt(qStringPlusRef,merchant_key);
//      
//      String encryptedURL = URI.create(callback_url+ "&from=ccpgw&trx="+encoded).toString();
//      System.out.println(encryptedURL);
//      
//      System.out.println("Working: http://gurgle:8081/checkout/order-received/2161/?key=wc_order_BO5cfw061buTI&from=ccpgw&trx=VnQNHC5JnR01OQ%2Bcm9fFnVReHnUPHzVxfxWVRrQ11irNLHeBhqDYV2YlN7ceM7B9");
//        
////      if (wcOrderKeyPos > -1) {
////        String wc_order_key = oldUrl.substring(wcOrderKeyPos).trim();
////
////        String encryptedURL = oldUrl.replace(qString, "") + "?"+ wc_order_key + "&from=ccpgw&trx="+encoded;
////        
////        System.out.println(encryptedURL);
////
////      }
//    } 
    
    
//    public String Encrypt(String stringToEncode, String merchant_key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
//        
//        String key = merchant_key.substring(0, 32);
//        String ivString =  merchant_key.substring(0, 16);       
//        
//        Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        //byte[] key = keyString.getBytes();
//        //MessageDigest sha = MessageDigest.getInstance("SHA-1");
//        //key = sha.digest(key);
//        //key = Arrays.copyOf(key, 16); 
//        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
//        
//        byte[] iv = ivString.getBytes("UTF-8");
//        IvParameterSpec ivspec = new IvParameterSpec(iv);
//        
//        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
//        
//        return Base64.getEncoder().encodeToString(encryptCipher.doFinal(stringToEncode.getBytes("UTF-8")));
//    }
//    
//    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
//
//       String merchant_key = "dc3c62ee4d349a6cda7edd11e5972ec2f16e7e5b4444826c774e59d0af3b0574"; 
//       String oldUrl = "http://gurgle:8081/checkout/order-received/2161/?key=wc_order_BO5cfw061buTI&status=success";
//       
//       testEncodeOrderDetail test = new testEncodeOrderDetail();
//       
//       
//       int index = oldUrl.indexOf("?");
//       int wcOrderKeyPos = oldUrl.indexOf("key=wc_order_");
//        
//       String  qString = oldUrl.substring(index, oldUrl.length()).trim();
//       String  qStringPlusRef = oldUrl.substring(index, oldUrl.length()).trim() + "&ref=" + "1675313151198";
//       
//      String encoded = test.Encrypt(qStringPlusRef,merchant_key);
//        
//      if (wcOrderKeyPos > -1) {
//        String wc_order_key = oldUrl.substring(wcOrderKeyPos).trim();
//
//        String encryptedURL = oldUrl.replace(qString, "") + "?"+ wc_order_key + "&from=ccpgw&trx="+encoded;
//        
//        System.out.println(encryptedURL);
//
//      }
//    }  

}
