/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Entite.Deliveryman;
import Entite.delivery;
import Service.ServiceDelivery;
import Service.ServiceDman;
import java.sql.*;
import java.util.List;

/**
 *
 * @author House
 */
public class test {
    
    public static void main(String[] args) {
        ServiceDman serD = new ServiceDman();
        ServiceDelivery dv=new ServiceDelivery();
        
        Deliveryman d = new Deliveryman("ljkhg","jyy",5,"hegeh");
        //Deliveryman n = new Deliveryman("Blam","test",9,"haha");
        delivery p = new delivery (1,122,"ha",111);
        delivery i = new delivery (3,133,"ai",44);
         delivery k = new delivery (3,133,"5 janvier",44);
        //Deliveryman b = new Deliveryman(8);
        //delivery h = new delivery (4);
        try {
            dv.ajouter1(i);
            dv.ajouter1(p);
            dv.ajouter1(k);
            //boolean testt= dv.delete(h);
          //boolean test2 = serD.delete(b);
            //serD.ajouter(t2);
            // boolean test=dv.update(i);
          // if (test)  System.out.print("upadate success"+test);
           
           Deliveryman fd = new Deliveryman(5,"hzha","Blam",8,"hegeh",232);
           boolean test=serD.update(fd);
           if (test)  System.out.print("upadate success"+test);
           // serD.ajouter1(d);
          // serD.delete(d);
         //dv.ajouter1(p);
        // dv.delete(p);
          // List<Deliveryman> list = serD.trilast();
           // System.out.println(list);
            List<delivery> list = dv.triDate();
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
