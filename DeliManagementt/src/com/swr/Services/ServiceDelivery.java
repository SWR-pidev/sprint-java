/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Services;


import com.swr.Iservice.Iservice;
import com.swr.Entite.Delivery;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Database;

/**
 *
 * @author House
 */
public class ServiceDelivery implements Iservice<Delivery> {

    private Connection con;
    private Statement ste;

    public ServiceDelivery() {
        con = Database.getInstance().getConnection();

    }

    @Override
    public void ajouter(Delivery p) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`Delivery` (`DateD`) VALUES (NULL, '" + p.getDateD() + "');";
        ste.executeUpdate(requeteInsert);
    }
    
   public void ajouter1(Delivery p ) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `delivery` (`idDman`, `DateD`, `item`) VALUES  (?,?,?);");
    pre.setInt(1, p.getIdDman());
    pre.setString(2, p.getDateD());
    pre.setString(3, p.getitem());
    
    pre.executeUpdate();
    }
            

    
    @Override
    public ObservableList<Delivery> readAll() throws SQLException {
        ObservableList arr =FXCollections.observableArrayList();
    
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Delivery ");
     while (rs.next()) {                
               int IdDe=rs.getInt(1);
               int idDman=rs.getInt("IdDman");
               String DateD=rs.getString("DateD");
               String item=rs.getString("item");
            Delivery h = new Delivery(IdDe, idDman, DateD, item);
            arr.add(h);
     }
    return arr;
    }
 public List<Delivery> triDate() throws SQLException {
    List<Delivery> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Delivery order by date ");
     while (rs.next()) {                
               int IdDe=rs.getInt(1);
               int idDman=rs.getInt("IdDman");
               String DateD=rs.getString("DateD");
               String item=rs.getString("item");
            
     }
    return arr;
    }

    
    @Override
    public boolean delete(Delivery t) throws SQLException {
  PreparedStatement pre=con.prepareStatement("DELETE FROM delivery WHERE idDe= ?");
       pre.setInt(1, t.getIdDe());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }

 @Override
    public boolean update(Delivery t) throws SQLException {
 PreparedStatement pre=con.prepareStatement("update delivery set idDman = ?, DateD = ?, item  = ?  WHERE idDe = ?");
   
   
    pre.setInt(1, t.getIdDman());
    pre.setString(2, t.getDateD());
     pre.setString(3, t.getitem());
     pre.setInt(4, t.getIdDe());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }
   public void assign (String Fname,int idDe ){
   
        try {
            PreparedStatement pre=con.prepareStatement("update delivery set Fname = ? where idDe="+idDe);
 
            pre.setString(1,Fname);
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDelivery.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   }
           
           
}
