/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;

import com.swr.Entite.Sponsor;
import com.swr.IService.IServiceSponsor;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.Date;
import com.swr.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.swr.Entite.Event;
import com.swr.Service.ServiceEvent;
import com.swr.Entite.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Eya
 */
public class ServiceSponsor implements IServiceSponsor<Sponsor>{
private Connection con;
    private Statement ste;

    public ServiceSponsor() {
        con = DataBase.getInstance().getConnection();

    }
    @Override
    public void addSponsor(Sponsor t) throws SQLException {
          PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`sponsorship` ( `id_sponsor`,`id_evt`,`logo`,`given_sponsor`,`date_sponsor`) VALUES ( ?, ?,?,?, CURDATE());");
    pre.setInt(1, t.getU().getIdu());
    pre.setInt(2, t.getE().getId_evt());
    pre.setString(3, t.getLogo());
    pre.setDouble(4, t.getGiven());
    
     if(pre.executeUpdate()!=0) {
         Event e= new Event(t.getE().getId_evt());
    ServiceEvent ser = new ServiceEvent();
    ServiceSponsor sers=new ServiceSponsor();
    ser.updateNbSponsorEvent(e);
    Event e1=new Event();
   e1= ser.readBudget_StillneededEvents(e);
   
    e1.setStillneeded_evt(e1.getStillneeded_evt()-t.getGiven());
    ser.updateStillneededEvent(e1);
    if(e1.getStillneeded_evt()==0)
    {
        ser.updateStateEvent(e1);
    }
     };
        return;
    }

    @Override
    public List<Sponsor> readAllSponsors() throws SQLException {
      List<Sponsor> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from sponsorship");
     while (rs.next()) {                
               int idS=rs.getInt(1);
               User u = new User();
               u.setId(idS);
               int idE=rs.getInt(2);
               Event e = new Event();
               e.setId_evt(idE);
               String logo=rs.getString(3);
            Date date=rs.getDate(5);
              
               double gived=rs.getDouble(4);
              
                
               Sponsor s=new Sponsor(u, e, logo, gived,date);
     arr.add(s);
     }
    return arr;
    }

    @Override
    public double GivedSum(Sponsor t) throws SQLException {
        
         double sum=0;
         PreparedStatement pre=con.prepareStatement("select SUM(given_sponsor) as sum from sponsorship where id_evt =?");
           pre.setInt(1, t.getE().getId_evt());
          
          ResultSet rs=pre.executeQuery();
          if (rs.next()) {                
               
              
               sum=rs.getDouble("sum");
               
        
     }
    return sum;
    }

    @Override
    public List<Sponsor> TrierSponsorGiven() throws SQLException {
      List<Sponsor> arr=new ArrayList<>();
         ste=con.createStatement();
          ResultSet rs=ste.executeQuery("select * from sponsorship order by given_sponsor desc");
          while (rs.next()) {                
               int idU=rs.getInt(1);
                User u = new User();
               u.setId(idU);
               int idE=rs.getInt(2);
                Event e = new Event();
               e.setId_evt(idE);
                 Date date=rs.getDate(5);
               Double gived=rs.getDouble(4);
               String logo=rs.getString(3);
                
                Sponsor s=new Sponsor(u, e, logo,gived,date);
         arr.add(s);
     }
    return arr;
    }

    @Override
    public List<Sponsor> TrierSponsorDate() throws SQLException {
       List<Sponsor> arr=new ArrayList<>();
         ste=con.createStatement();
          ResultSet rs=ste.executeQuery("select * from sponsorship order by date_sponsor desc");
          while (rs.next()) {                
               int idU=rs.getInt(1);
               User u = new User();
               u.setId(idU);
               int idE=rs.getInt(2);
                Event e = new Event();
               e.setId_evt(idE);
           Date date=rs.getDate(5);
              Double gived=rs.getDouble(4); 
                 String logo=rs.getString(3);
               Sponsor s=new Sponsor(u, e, logo,gived,date);
         arr.add(s);
     }
    return arr;
    }

   
    public ObservableList<Sponsor> GetSponsorByIdEvent(int id) throws SQLException
    {
        ObservableList<Sponsor> arr=FXCollections.observableArrayList();
         Event e = new Event();
          User u=new User();
          String n,p;
         PreparedStatement pre=con.prepareStatement("select * from sponsorship where id_evt =?");
           pre.setInt(1, id);
          
          ResultSet rs=pre.executeQuery();
          while (rs.next()) {                
               int idU=rs.getInt(1);
               int idE=rs.getInt(2);
               String logo=rs.getString(3);
               Double gived=rs.getDouble(4);
               java.util.Date date=rs.getDate(5);
               
                u=this.GetUbyId(idU);
                u.setId(idU);
          Sponsor s=new Sponsor(u, e, logo,gived, (Date) date);
               s.setNom(u.getNom());
               s.setPrenom(u.getPrenom());
               
        arr.add(s);
     }
    return arr;
    }

   
public User GetUbyId(int id) throws SQLException
{
    PreparedStatement pre1=con.prepareStatement("select * from user where idu =?");
           pre1.setInt(1, id);
          User u =new User();
          ResultSet rs1=pre1.executeQuery();
          while (rs1.next()){
              String nom=rs1.getString(2);
              String prenom=rs1.getString(3);
              
             
              u.setNom(nom);
              u.setPrenom(prenom);
              
              
              
          } 
          return u;
}

public ObservableList<Sponsor> SUE (int ids) throws SQLException
{
     ObservableList<Sponsor> arr=FXCollections.observableArrayList();
      PreparedStatement pre=con.prepareStatement("select e.name_evt,s.given_sponsor,u.nom,u.prenom from user u INNER JOIN sponsorship s on u.idu = s.id_sponsor INNER JOIN event e on s.id_evt = e.id_evt where s.id_sponsor=?");
       pre.setInt(1, ids);
         ResultSet rs=pre.executeQuery();
          while (rs.next()) {
              String nameEvt = rs.getString(1);
              String nom =rs.getString(3);
              Double given = rs.getDouble(2);
              String prenom = rs.getString(4);
              
              Sponsor s=new Sponsor(given, nom, prenom,nameEvt);
              
               arr.add(s);
          }
          return arr;
}
   
public  ObservableList<Sponsor> GetSponsor(int id) throws SQLException
{
     ObservableList<Sponsor> arr=FXCollections.observableArrayList();
      PreparedStatement pre=con.prepareStatement("select * from sponsorship where id_evt=? order by given_sponsor desc");
       pre.setInt(1, id);
         ResultSet rs=pre.executeQuery();
          while (rs.next()) {
              
              String logo =rs.getString(3);
              Double gived = rs.getDouble(4);
              
              Sponsor s=new Sponsor(logo, gived);
              
               arr.add(s);
          }
          return arr;
}
    
    public  int CountSponsor(int id) throws SQLException
{
     int i=0;
      PreparedStatement pre=con.prepareStatement("select COUNT(*) from sponsorship where id_evt=?");
       pre.setInt(1, id);
         ResultSet rs=pre.executeQuery();
          while (rs.next()) {
              i=rs.getInt(1);
              
          }
          return i;
}
public  ObservableList<String> GetLogoSponsor(int id) throws SQLException
{
     ObservableList<String> arr=FXCollections.observableArrayList();
      PreparedStatement pre=con.prepareStatement("select logo from sponsorship where id_evt=?");
       pre.setInt(1, id);
         ResultSet rs=pre.executeQuery();
          while (rs.next()) {
              
              String logo =rs.getString(1);
              
              
              
              
               arr.add(logo);
          }
          return arr;
}

 public  boolean CheckAmount(int id,int a) throws SQLException
{
     int i=0;
      PreparedStatement pre=con.prepareStatement("select stillneeded_evt from event where id_evt=?");
       pre.setInt(1, id);
         ResultSet rs=pre.executeQuery();
          while (rs.next()) {
              i=rs.getInt(1);
              
          }
          if(i>=a)
          {   System.out.println("i============="+i);
              return true;}
          else return false;
}

}

 