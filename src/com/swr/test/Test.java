
package com.swr.test;


import com.swr.Entite.Deliveryman;
import com.swr.Services.ServiceDelivery;
import com.swr.Services.ServiceDman;
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
        
        Deliveryman f = new Deliveryman("n","h",122,"t");
        Deliveryman t = new Deliveryman (10,"n","h",3,"e");
        try {
//         
             //ser.delete(t);
            // ser.ajouter1(f);
            
            List<Deliveryman> list = ser.readAll();
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
