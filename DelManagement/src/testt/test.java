
package testt;

import Entite.Deliveryman;
import Service.ServiceDman;
import Entite.Deliveryman;
import Service.ServiceDelivery;
import Service.ServiceDman;
import utils.Database;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class test {
    
    public static void main(String[] args) {
        ServiceDman ser = new ServiceDman();
        ServiceDelivery dv = new ServiceDelivery();
        
        //Deliveryman f = new Deliveryman("n","h",122,"t");
        Deliveryman e = new Deliveryman (4,"Nermine","Manai",45,"mail");
        try {
            
           // ser.ajouter1(f);
            ser.update(e);
            List<Deliveryman> list = ser.readAll();
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
