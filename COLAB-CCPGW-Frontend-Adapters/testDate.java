
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class testDate {
  public static void main(String[] args) {

      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      
      DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmm");

      Date date = new Date();
      System.out.println(dateFormat2.format(date));    // 2021/03/22 16:37:15

      // new Date() actually calls this new Date(long date)
      Date date2 = new Date(System.currentTimeMillis());
      System.out.println(dateFormat.format(date));    // 2021/03/22 16:37:15

  }    
}
