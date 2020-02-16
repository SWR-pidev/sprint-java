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
    pre.setInt(1, t.getId_sponsor());
    pre.setInt(2, t.getId_evt());
    pre.setString(3, t.getLogo());
    pre.setDouble(4, t.getGiven());
    
     if(pre.executeUpdate()!=0) {
         Event e= new Event(t.getId_evt());
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
               int idE=rs.getInt(2);
               String logo=rs.getString(3);
            Date date=rs.getDate(5);
              
               double gived=rs.getDouble(4);
              
                
               Sponsor s=new Sponsor(idS, idE, logo, gived,date);
     arr.add(s);
     }
    return arr;
    }

    @Override
    public double GivedSum(Sponsor t) throws SQLException {
        
         double sum=0;
         PreparedStatement pre=con.prepareStatement("select SUM(given_sponsor) as sum from sponsorship where id_evt =?");
           pre.setInt(1, t.getId_evt());
          
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
               int idE=rs.getInt(2);
                 Date date=rs.getDate(5);
               Double gived=rs.getDouble(4);
               String logo=rs.getString(3);
                
                Sponsor s=new Sponsor(idU, idE, logo,gived,date);
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
               int idE=rs.getInt(2);
           Date date=rs.getDate(5);
              Double gived=rs.getDouble(4); 
                 String logo=rs.getString(3);
               Sponsor s=new Sponsor(idU, idE, logo,gived,date);
         arr.add(s);
     }
    return arr;
    }

   
    

   

   
    
    
}
