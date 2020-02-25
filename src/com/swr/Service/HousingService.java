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
import com.swr.Entite.Housing;
import com.swr.IService.IHousingService;
import com.swr.Utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mohamed
 */
public class HousingService implements IHousingService {

    
    private Connection con;
    private Statement ste;
    private PreparedStatement pste;
    public  static HousingService hs;
    
    public static HousingService getInstance()
    {if(hs==null)
        hs=new HousingService();
    return hs;
    }
    
    public HousingService() {
         con = DataBase.getInstance().getConnection();
         
    }
    
    @Override
     public void addHousing(Housing h) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`housing` (`idh`,`name`,`description`, `address`, `location`, `capacity`, `nbresidents`, `type`) VALUES (NULL, '"+ h.getName()+ "', '" + h.getDescription() + "', '" + h.getAddress() + "', '"+ h.getLocation() + "', '"+ h.getCapacity() + "', '"+ h.getNbresidents() + "', '" + h.getType() + "');";
        ste.executeUpdate(requeteInsert);
    }
    @Override
    public ObservableList<Housing> getAllHousings() throws SQLException {
    ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from housing");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String name= rs.getString("name");
               String description=rs.getString("description");
               String address=rs.getString("address");
               String location=rs.getString(5);
               int capacity=rs.getInt("capacity");
               int nbresidents=rs.getInt("nbresidents");
               String type=rs.getString("type");
               Housing p=new Housing(id,name,description,address, location,capacity,nbresidents,type) ;
     arr.add(p);
     }
    return arr;
    }

    public boolean existeHousing(Housing h)  throws SQLException {
    ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from housing where `idh`="+h.getIdh()+";");
        return rs.isBeforeFirst();
    }
    
    @Override
    public boolean deleteHousing(Housing h) throws SQLException {
        
        ste= con.createStatement();
        String deleteRequest ="DELETE FROM `housing` WHERE `idh`="+h.getIdh()+";";
        int rows= ste.executeUpdate(deleteRequest);
            return rows != 0;
        
    }

    @Override
    public boolean updateHousing(Housing h) throws SQLException {
       
        pste= con.prepareStatement("UPDATE `housing` SET `idh`=?,`name`=?,`description`=?,`address`=?,`location`=?,`capacity`=?,`nbresidents`=?,`type`=? WHERE `idh`=?;");
            pste.setInt(1,h.getIdh());
            pste.setString(2, h.getName());
            pste.setString(3, h.getDescription());
            pste.setString(4, h.getAddress());
            pste.setString(5, h.getLocation());
            pste.setInt(6, h.getCapacity());
            pste.setInt(7, h.getNbresidents());
            pste.setString(8, h.getType());
            pste.setInt(9,h.getIdh());
            int rows= pste.executeUpdate();
            return rows != 0;
    }

    @Override
    public Housing getHousingById(int id) throws SQLException {
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from `housing`  where `idh`='"+id+"';");
        
        while(rs.first()){
        int idh=rs.getInt(1);
               String name= rs.getString(2);
               String description=rs.getString("description");
               String address=rs.getString("address");
               String location=rs.getString(5);
               int capacity=rs.getInt("capacity");
               int nbresidents=rs.getInt("nbresidents");
               String type=rs.getString("type");
               
        Housing h= new Housing(idh,name,description,address, location,capacity,nbresidents,type);
        return h;
        }
        return null;

        
    }

    @Override
    public Housing getHousingByName(String nom) throws SQLException {
       ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from `housing`  where `name`='"+nom+"';");
        
        while(rs.first()){
        int idh=rs.getInt(1);
               String name= rs.getString(2);
               String description=rs.getString("description");
               String address=rs.getString("address");
               String location=rs.getString(5);
               int capacity=rs.getInt("capacity");
               int nbresidents=rs.getInt("nbresidents");
               String type=rs.getString("type");
               
        Housing h= new Housing(idh,name,description,address, location,capacity,nbresidents,type);
        return h;
        }
        return null;

        
    }

    @Override
    public Housing getHousingByLocation(String loc) throws SQLException {
         pste=con.prepareStatement("Select * from housing where location=? ");
        pste.setString(1, loc);
        ResultSet rs= pste.executeQuery();
        if (rs.isBeforeFirst()) {
            System.out.println("*********** Housing doesn't Exist !**********");
            return null;}
        else {
        int idh=rs.getInt(1);
               String name= rs.getString(2);
               String description=rs.getString("description");
               String address=rs.getString("address");
               String location=rs.getString(5);
               int capacity=rs.getInt("capacity");
               int nbresidents=rs.getInt("nbresidents");
               String type=rs.getString("type");
        Housing h= new Housing(idh,name,description,address, location,capacity,nbresidents,type);
        return h;
        }
    }
    public ObservableList<Housing> getAllHousingsByR() throws SQLException {
    ObservableList arr= FXCollections.observableArrayList();
    RatingService Rserv= RatingService.getInstance();
    ste=con.createStatement();
    
    
    ResultSet rs=ste.executeQuery("select * from housing");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String name= rs.getString("name");
               String description=rs.getString("description");
               String address=rs.getString("address");
               String location=rs.getString(5);
               int capacity=rs.getInt("capacity");
               int nbresidents=rs.getInt("nbresidents");
               String type=rs.getString("type");
               double rating= Rserv.averageRating(rs.getInt("idh"));
               Housing p=new Housing(id,name,description,address, location,capacity,nbresidents,type,rating) ;
     arr.add(p);
     }
    return arr;
    }
    public Housing getHousingWR(int id) throws SQLException {
        RatingService Rserv= RatingService.getInstance();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from `housing`  where `idh`="+id+";");
        
        while(rs.first()){
        int idh=rs.getInt(1);
               String name= rs.getString(2);
               String description=rs.getString("description");
               String address=rs.getString("address");
               String location=rs.getString(5);
               int capacity=rs.getInt("capacity");
               int nbresidents=rs.getInt("nbresidents");
               String type=rs.getString("type");
               double r= Rserv.averageRating(id);
        Housing h= new Housing(idh,name,description,address, location,capacity,nbresidents,type,r);
        return h;
        }
        return null;
    }

    @Override
    public ObservableList<String> getHousingNames() throws SQLException {
        ObservableList names= FXCollections.observableArrayList();
        ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select name from housing");
     while (rs.next()) {  
         String name= rs.getString("name");
         names.add(name);
     }  
     return names;
    }
}
