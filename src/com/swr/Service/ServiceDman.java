
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;


import com.swr.Entite.Deliveryman;
import com.swr.IService.Serviceee;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.swr.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author House
 */
public class ServiceDman implements Serviceee {

    private Connection con;
    private Statement ste;

    public ServiceDman() {
        con = DataBase.getInstance().getConnection();

    }

  
    public void ajouter(Deliveryman t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`deliveryman` (`Fname`, `Lname` , `Pnum`, `mail`, `numD`) VALUES ('" + t.getFname() + "', '" + t.getLname() + "', '" + t.getmail()+ "', '" + t.getPnum() +"');";
        ste.executeUpdate(requeteInsert);
    }
    
    public void ajouter1(Deliveryman t ) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`deliveryman` (`Fname`,`Lname`,`Pnum`,`mail`) VALUES (?,?,?,?);");
    pre.setString(1, t.getFname());
    pre.setString(2, t.getLname());
    pre.setInt(3, t.getPnum());
    pre.setString(4, t.getmail());
    pre.executeUpdate();
    }
            

    
    public boolean delete(Deliveryman t) throws SQLException {
  PreparedStatement pre=con.prepareStatement("DELETE FROM deliveryman WHERE idDman= ?");
       pre.setInt(1, t.getidDman());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }


    public boolean update(Deliveryman t) throws SQLException {
 PreparedStatement pre=con.prepareStatement("update deliveryman set Fname = ?, Lname = ?, Pnum = ?, mail = ?, numD = ? WHERE idDman = ?");
   
    pre.setString(1, t.getFname());
    pre.setString(2, t.getLname());
    pre.setInt(3, t.getPnum());
    pre.setString(4, t.getmail());
    pre.setInt(5, t.getnumD());
    pre.setInt(6, t.getidDman());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }
    
    @Override
    public ObservableList<Deliveryman> readAll() throws SQLException {
        ObservableList arr =FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Deliveryman");
     while (rs.next()) {                
               int idDman=rs.getInt(1);
               String Fname=rs.getString("Fname");
               String Lname=rs.getString("Lname");
               String mail=rs.getString("mail");
               int Pnum=rs.getInt("Pnum");
               int numD=rs.getInt("numD");
              Deliveryman f= new Deliveryman(Fname, Lname, Pnum, mail);
              arr.add(f);
              
     }
    return arr;
    }
 public List<Deliveryman> trifirst() throws SQLException {
    List<Deliveryman> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Deliveryman order by Fname");
     while (rs.next()) {                
               int idDman=rs.getInt(1);
               String Fname=rs.getString("Fname");
               String Lname=rs.getString("Lname");
               String mail=rs.getString("mail");
               int idDel=rs.getInt("idDel");
               int Pnum=rs.getInt("Pnum");
               int numD=rs.getInt("numD");
              
     }
    return arr;
    }
     public List<Deliveryman> trilast() throws SQLException {
    List<Deliveryman> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Deliveryman order by Lname");
     while (rs.next()) {                
               int idDman=rs.getInt(1);
               String Fname=rs.getString("Fname");
               String Lname=rs.getString("Lname");
               String mail=rs.getString("mail");
               int idDel=rs.getInt("idDel");
               int Pnum=rs.getInt("Pnum");
               int numD=rs.getInt("numD");
              
     }
    return arr;
    }
    
     public ObservableList<String> getalldelimen  () {
         ObservableList arr =FXCollections.observableArrayList();
        try {
            
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select Fname from Deliveryman");
            while (rs.next()) {
               
                String Fname=rs.getString("Fname");
                
                
                arr.add(Fname);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDman.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
     }
  
 public ResultSet getdeliveryman() throws SQLException {


        ResultSet rs = ste.executeQuery("SELECT COUNT(Fname)from deliveryman GROUP BY Fname");
        return rs;
  }
 
}
