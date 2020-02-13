/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import swr.Entites.Housing;
import swr.Entites.Rating;
import swr.Service.HousingService;
import swr.Service.RatingService;

/**
 *
 * @author mohamed
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            HousingService hser = new HousingService();
            Housing h1= new Housing("this is the name","this is description1", "this is address1"," this is location1", 100, 55, "CAMP1");
            Housing h2= new Housing("this is the name","this is description2", "this is address2"," this is location2", 100, 55, "CAMP2");
            Housing h3= new Housing("this is the name","this is description3", "this is address3"," this is location3", 100, 55, "CAMP3");
//              RatingService rser= new RatingService();
//              Rating r1= new Rating(2,6,"Hello MFS",4);
//              Rating r2= new Rating(1,3,"Hello MFS",5);
//              Rating r3= new Rating(2,2,"Hello MFS",1);
//              

           try { 
//               rser.addRating(r1);
//               rser.addRating(r2);
//               rser.addRating(r3);
//               System.out.println("Command Exectued");
//               rser.deleteRating(r2);
//               System.out.println("Delete Exectued");
               Housing h5= new Housing(2,"UPDATE","UPDATE", "UPDATE"," UPDATE", 666, 666, "UPDATE");
            hser.addHousing(h1);
            hser.addHousing(h2);
            hser.addHousing(h3);
            System.out.println("Exectued");
            List<Housing> listhousing=hser.getAllHousings();   
            System.out.println(listhousing);
            hser.updateHousing(h5);
            List<Housing> newlisthousing=hser.getAllHousings();   
               System.out.println("***********UPDATED***************");
            System.out.println(newlisthousing);
              

        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
