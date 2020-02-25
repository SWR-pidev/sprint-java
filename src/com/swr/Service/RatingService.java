/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.swr.Entite.Rating;
import com.swr.IService.IRatingService;
import com.swr.Utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mohamed
 */
public class RatingService implements IRatingService {
    private Connection con;
    private Statement ste;
    private PreparedStatement pste;
    public static RatingService rs;
    public RatingService() {
         con = DataBase.getInstance().getConnection();
        
    }
    public static RatingService getInstance()
    {if(rs==null)
        rs=new RatingService();
    return rs;
    }

     public boolean existeRating(Rating r)  throws SQLException {
    ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from ratings where `idh`="+r.getIdR()+";");
        return rs.isBeforeFirst();
    }
    
    @Override
    public void addRating(Rating r) throws SQLException {
         ste = con.createStatement();
        String requeteInsert = "INSERT INTO `ratings` (`idR`,`idh`, `idUser`, `feedback`, `rating`) VALUES (NULL, '" + r.getidH() + "', '" + r.getIduser() + "', '"+ r.getFeedback() + "', '"+ r.getRating() + "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean deleteRating(Rating r) throws SQLException {
        if (existeRating(r)) return false;
        else {
        ste= con.createStatement();
        String deleteRequest ="DELETE FROM `ratings` WHERE `idR`="+r.getIdR()+";";
        ste.executeUpdate(deleteRequest);
        return true;
        }
    }

    @Override
    public boolean updateRating(Rating r) throws SQLException {
        
        
        pste= con.prepareStatement("Update `ratings` SET `idh`=?, `idUser`=?, `feedback`=?, `rating`=? where idR=?");
        pste.setInt(1, r.getidH());
        pste.setInt(2, r.getIduser());
        pste.setString(3, r.getFeedback());
        pste.setInt(4, r.getRating());
        pste.setInt(5, r.getIdR());
        int rows=pste.executeUpdate();
        return rows!=0;
        
    }

    @Override
    public List<Rating> readAllRatings() throws SQLException {
 List<Rating> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from ratings");
     while (rs.next()) {                
               int idR=rs.getInt(1);
               int idh=rs.getInt(2);
               int iduser=rs.getInt(3);
               String feedback=rs.getString("feedback");
               int rating=rs.getInt("rating");
               Rating p=new Rating(idR,idh,iduser, feedback,rating) ;
     arr.add(p);
     }
  return arr;

  }

    @Override
    public ObservableList<Rating> getAllRatingsOfHousing(int id) throws SQLException {
        ObservableList<Rating> arr=FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from ratings where `idh`="+id+";");
    
     while (rs.next()) {                
               int idR=rs.getInt(1);
               int idh=rs.getInt(2);
               int iduser=rs.getInt(3);
               String feedback=rs.getString("feedback");
               int rating=rs.getInt("rating");
               Rating p=new Rating(idR,idh,iduser, feedback,rating) ;
     arr.add(p);
     }
  return arr;
    }

    @Override
    public long countRatings(int id) throws SQLException {
        List<Rating> cont = getAllRatingsOfHousing(id);
        if(cont.isEmpty()) return -1;
        else return cont.stream().count();
        
    }

    @Override
    public double averageRating(int id) throws SQLException {
        List<Rating> cont = getAllRatingsOfHousing(id);
        if (cont.isEmpty()) return 0;
        else return cont.stream().mapToInt(e->e.getRating()).average().getAsDouble();
    }
    
    
}
