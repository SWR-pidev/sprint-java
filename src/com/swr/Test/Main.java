/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Test;

import com.swr.Entite.Rating;
import com.swr.Entite.goods;
import com.swr.Service.HousingService;
import com.swr.Service.RatingService;
import com.swr.Service.ServiceItem;
import com.swr.Service.Servicegoods;
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
        ServiceItem is= ServiceItem.getInstance();
        try {
            is.Changeandtest("Water",150);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
