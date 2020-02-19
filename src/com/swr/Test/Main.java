/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Test;

import com.swr.Entite.Rating;
import com.swr.Service.HousingService;
import com.swr.Service.RatingService;
import com.swr.Service.ServiceItem;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moham
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//            RatingService rs= new RatingService();
            HousingService hs= HousingService.getInstance();
        try {
            System.out.println(hs.getHousingWR(9));
            System.out.println(hs.getHousingByName("test housing"));
            System.out.println(hs.getAllHousingsByR());
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
          ServiceItem is= new ServiceItem();
//            Rating r1= new Rating(1, 2, "This a rating Test", 3);
//            Rating r2= new Rating(1, 2, "This a rating Test", 5);
//            Rating r3= new Rating(1, 2, "This a rating Test", 3);
//            Rating r5= new Rating(3,3, 2, "This is an update ", 3);
//
//            
//            rs.addRating(r1);
//            rs.addRating(r2);
//            rs.addRating(r3);
//            rs.deleteRating(r2);
//            rs.updateRating(r5);
//            List l=rs.readAllRatings();
//            System.out.println(l);
//            List lh=rs.getAllRatingsOfHousing(2); 
//            System.out.println(lh);
//            System.out.println("**************************");
//            double ratingavg= rs.averageRating(2);
//            System.out.println("This is the average"+ratingavg);

    }
    
}
