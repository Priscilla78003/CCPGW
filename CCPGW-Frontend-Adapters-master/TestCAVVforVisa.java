
import java.util.Arrays;
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
public class TestCAVVforVisa {
    
    private String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }   
    
    private byte[] concatBytes(byte[] xid, byte[] cavv) {
        byte[] result = new byte[xid.length + cavv.length];
        System.arraycopy(xid, 0, result, 0, xid.length);
        System.arraycopy(cavv, 0, result, xid.length, cavv.length);
        return result;
    }    
    
    private String asciiToHex(String asciiStr) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString((int) ch));
        }

        return hex.toString();
    }

    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }    
    
    public static void main(String[] args) {
        
        TestCAVVforVisa test = new TestCAVVforVisa();

        String CAVV_1 ="AAABBZkJGGYTGQEhKQkYEYYH8LM=";

        String XiD_1 = "VAynK7Du50mJQYhyEvyO3opp+vY=";
        
        
        byte[] decoded_xid = Base64.getDecoder().decode("");
        byte[] decoded_cavv = Base64.getDecoder().decode(CAVV_1);
        byte[] ds = test.concatBytes(decoded_xid, decoded_cavv);
        
                
        System.out.println("PaRes XiD:  " + XiD_1);
        System.out.println("PaRes CAVV: " + CAVV_1);
        String hexValue = test.byteArrayToHex(ds);
        System.out.println("Should be: 000001059909186613190121290918118607f0b3");
        System.out.println("CAVV Hex : " + (hexValue));
        System.out.println("Should be:  30303030303130353939303931383636313331393031323132393039313831313836303766306233");
        System.out.println("CAVV ASCII: " + (test.asciiToHex(hexValue)));

        byte[] dsBytes = test.hexStringToByteArray(test.asciiToHex(test.byteArrayToHex(ds)));
        
        System.out.println("Should be     : [48, 48, 48, 48, 48, 49, 48, 53, 57, 57, 48, 57, 49, 56, 54, 54, 49, 51, 49, 57, 48, 49, 50, 49, 50, 57, 48, 57, 49, 56, 49, 49, 56, 54, 48, 55, 102, 48, 98, 51]");
        System.out.println("CAVV Hex BYTES: " + Arrays.toString(dsBytes));     
        
        
        CAVV_1 ="AJkBAXgokzMolIYYNCiTAAAAAAA=";
        
        
        decoded_xid = Base64.getDecoder().decode("");
        decoded_cavv = Base64.getDecoder().decode(CAVV_1);
        ds = test.concatBytes(decoded_xid, decoded_cavv);
        
                
        System.out.println("PaRes XiD:  " + XiD_1);
        
        System.out.println("PaRes CAVV: " + CAVV_1);
        hexValue = test.byteArrayToHex(ds);
        System.out.println("Should be: 0099010178289333289486183428930000000000");
        System.out.println("CAVV Hex : " + (hexValue));
        System.out.println("Should be:  30303939303130313738323839333333323839343836313833343238393330303030303030303030");
        System.out.println("CAVV ASCII: " + (test.asciiToHex(hexValue)));

        dsBytes = test.hexStringToByteArray(test.asciiToHex(test.byteArrayToHex(ds)));
        
        System.out.println("Should be     : [48, 48, 57, 57, 48, 49, 48, 49, 55, 56, 50, 56, 57, 51, 51, 51, 50, 56, 57, 52, 56, 54, 49, 56, 51, 52, 50, 56, 57, 51, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48]");
        System.out.println("CAVV Hex BYTES: " + Arrays.toString(dsBytes));          
        
        
    }
}
