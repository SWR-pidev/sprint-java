
package com.swr.test;


import com.swr.Entite.Deliveryman;
import com.swr.Entite.goods;
import com.swr.Services.ServiceDelivery;
import com.swr.Services.ServiceDman;
import com.swr.Services.Servicegoods;

import utils.Database;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class Test {
    
    public static void main(String[] args) {
        ServiceDman ser = new ServiceDman();
        ServiceDelivery dv = new ServiceDelivery();
        Servicegoods sg= new Servicegoods();
         goods g1= new goods(1,1,"malfouf",1000,"Waiting");
         goods g2= new goods(1,1,"Makloub chawarma",1000,"Waiting");
         
         
        try {
            sg.ajouter1(g1);
            sg.ajouter1(g2);
           
         System.out.println(sg.readAll());
            
//        Deliveryman f = new Deliveryman("n","h",122,"t");
//        Deliveryman t = new Deliveryman (10,"n","h",3,"e");
   
        
//         
             //ser.delete(t);
            // ser.ajouter1(f);
            
           // List<goods> list = sg.readAll();
            //List<Deliveryman> list = ser.readAll();
           //System.out.println(list);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
  
        }
    
    }
}
