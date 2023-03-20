/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
public class TestClassName {
    
    static class MyClass{
    
        String name = "MyClass";
        public MyClass() {
        }
        public String getName(
        ){
          return name; 
        };
    }
    
    
    static class Myclass {
        String name = "Myclass";
        public Myclass(){
            
        }
        public String getName(
        ){
          return name; 
        };        
    }
    
    public static void main(String[] args) {
        MyClass m = new MyClass();
        Myclass m2 = new Myclass();
        
        System.out.println(m.getName());
        System.out.println(m2.getName());
    }
}
