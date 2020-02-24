/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;

/**
 *
 * @author Eya
 */
import com.email.durgesh.Email;
import com.swr.Entite.Event;
import com.swr.IService.IServiceEvent;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.Date;
import com.swr.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceEvent implements IServiceEvent<Event>{

     private Connection con;
    private Statement ste;

    public ServiceEvent() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void addEvent(Event t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`event` ( `name_evt`, `date_evt`, `time_evt`,`location_evt`, `details_evt`, `budget_evt`,`organizator_evt`,`url_evt`,`stillneeded_evt`) VALUES ( ?, ?, ?,?, ?, ?,?, ?,?);");
    pre.setString(1, t.getName());
    pre.setDate(2, (Date) t.getDate_evt());
    pre.setTime(3, t.getTime_evt());
    pre.setString(4, t.getLocation());
    pre.setString(5, t.getDetails());
    pre.setDouble(6, t.getBudget());
    pre.setString(7, t.getOrganizator());
    pre.setString(8, t.getUrl_evt());
    pre.setDouble(9, t.getBudget());
    
    pre.executeUpdate();
    try {
        Email email = new Email("swr.pidev@gmail.com","SWR.pidev123");
        email.setFrom("swr.pidev@gmail.com", "Solidarity With Refugees");
        email.setSubject("New event");
        email.setContent("<h1> Join Us </h1>", "text/html");
        email.addRecipient("eya.rabeh@esprit.tn");
        email.send();
    
         } catch (Exception e) 
             
             { e.printStackTrace();}		 			
    System.out.println("Mail Sended");               			    
    }

    @Override
    public boolean deleteEvent(Event t) throws SQLException {
         PreparedStatement pre=con.prepareStatement("DELETE FROM `swr`.`event`  WHERE id_evt =?");
         pre.setInt(1, t.getId_evt());
        if(pre.executeUpdate()!=0) return true;
        return false;
    }

    @Override
    public boolean updateEvent(Event t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `swr`.`event` SET name_evt=?,date_evt=?,time_evt=?,location_evt=?,details_evt=?,budget_evt=?,organizator_evt=?,url_evt=? WHERE id_evt =?");
           pre.setString(1, t.getName());
           pre.setDate(2, (Date) t.getDate_evt());
           pre.setTime(3, t.getTime_evt());
           pre.setString(4, t.getLocation());
           pre.setString(5, t.getDetails());
           pre.setDouble(6, t.getBudget());
           pre.setString(7, t.getOrganizator());
           pre.setString(8, t.getUrl_evt()); 
           pre.setInt(9, t.getId_evt());
        if(pre.executeUpdate()!=0) return true;
        return false;
    }

    @Override
    public ObservableList<Event> readAllEvents() throws SQLException {
        ObservableList<Event> arr= FXCollections.observableArrayList();

    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from event");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("name_evt");
            java.util.Date date=rs.getDate(3);
              Time time = rs.getTime(4);
               String location=rs.getString(5);
               String detail=rs.getString(6);
               double budget=rs.getDouble(7);
               String orga=rs.getString(8);
               String url=rs.getString(9);
                int nbv=rs.getInt(10);
                 int nbp=rs.getInt(12);
                 int nbs=rs.getInt(11);
                int nbr=rs.getInt(13);
                int state=rs.getInt(14);
                double still=rs.getDouble(15);
               Event e=new Event(id, nom, date, time,location,detail,budget,orga,url,nbv,nbs,nbp,nbr,state,still);
     arr.add(e);
     }
    return arr;
    }

    @Override
    public boolean updateStateEvent(Event t) throws SQLException {
          PreparedStatement pre=con.prepareStatement("UPDATE `swr`.`event` SET 	state_evt=1 WHERE id_evt =?");
        
           pre.setInt(1, t.getId_evt());
        if(pre.executeUpdate()!=0) return true;
        return false;
    }

    @Override
    public boolean updateSNbparticipantEvent(Event t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `swr`.`event` SET nbparticipant_evt=nbparticipant_evt+1 WHERE id_evt =?");
        
           pre.setInt(1, t.getId_evt());
           if(pre.executeUpdate()!=0) return true;
        return false;
    }

    @Override
    public boolean updateNbViewsEvent(Event t) throws SQLException {
          PreparedStatement pre=con.prepareStatement("UPDATE `swr`.`event` SET nbviews_evt=nbviews_evt+1 WHERE id_evt =?");
        
           pre.setInt(1, t.getId_evt());
           if(pre.executeUpdate()!=0) return true;
        return false;
    }

    @Override
    public boolean updateNbSponsorEvent(Event t) throws SQLException {
           PreparedStatement pre=con.prepareStatement("UPDATE `swr`.`event` SET nbsponsor_evt=nbsponsor_evt+1 WHERE id_evt =?");
        
           pre.setInt(1, t.getId_evt());
           if(pre.executeUpdate()!=0) return true;
        return false;
    }

    @Override
    public boolean updateNbreportEvent(Event t) throws SQLException {
          PreparedStatement pre=con.prepareStatement("UPDATE `swr`.`event` SET nbreport_evt=nbreport_evt+1 WHERE id_evt =?");
        
           pre.setInt(1, t.getId_evt());
           if(pre.executeUpdate()!=0) return true;
        return false;
    }

    @Override
    public ObservableList<Event> TrierEventNbparticipant() throws SQLException {
         ObservableList<Event> arr= FXCollections.observableArrayList();
         ste=con.createStatement();
          ResultSet rs=ste.executeQuery("select * from event order by nbparticipant_evt desc");
          while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("name_evt");
            java.util.Date date=rs.getDate(3);
              Time time = rs.getTime(4);
               String location=rs.getString(5);
               String detail=rs.getString(6);
               double budget=rs.getDouble(7);
               String orga=rs.getString(8);
               String url=rs.getString(9);
                int nbv=rs.getInt(10);
                 int nbp=rs.getInt(12);
                 int nbs=rs.getInt(11);
                int nbr=rs.getInt(13);
                int state=rs.getInt(14);
                double still=rs.getDouble(15);
               Event e=new Event(id, nom, date, time,location,detail,budget,orga,url,nbv,nbs,nbp,nbr,state,still);
               
         arr.add(e);
     }
    return arr;
    }

    @Override
    public ObservableList<Event> TrierEventNbviews() throws SQLException {
      ObservableList<Event> arr=FXCollections.observableArrayList();
         ste=con.createStatement();
          ResultSet rs=ste.executeQuery("select * from event order by nbviews_evt desc");
          while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("name_evt");
            java.util.Date date=rs.getDate(3);
              Time time = rs.getTime(4);
               String location=rs.getString(5);
               String detail=rs.getString(6);
               double budget=rs.getDouble(7);
               String orga=rs.getString(8);
               String url=rs.getString(9);
              int nbv=rs.getInt(10);
                 int nbp=rs.getInt(12);
                 int nbs=rs.getInt(11);
                int nbr=rs.getInt(13);
                int state=rs.getInt(14);
                double still=rs.getDouble(15);
               Event e=new Event(id, nom, date, time,location,detail,budget,orga,url,nbv,nbs,nbp,nbr,state,still);
         arr.add(e);
     }
    return arr;
    }

    @Override
    public ObservableList<Event> TrierEventNbsponsor() throws SQLException {
       ObservableList<Event> arr=FXCollections.observableArrayList();
         ste=con.createStatement();
          ResultSet rs=ste.executeQuery("select * from event order by nbsponsor_evt desc");
          while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("name_evt");
            java.util.Date date=rs.getDate(3);
              Time time = rs.getTime(4);
               String location=rs.getString(5);
               String detail=rs.getString(6);
               double budget=rs.getDouble(7);
               String orga=rs.getString(8);
               String url=rs.getString(9);
                int nbv=rs.getInt(10);
                 int nbp=rs.getInt(12);
                 int nbs=rs.getInt(11);
                int nbr=rs.getInt(13);
                int state=rs.getInt(14);
                double still=rs.getDouble(15);
               Event e=new Event(id, nom, date, time,location,detail,budget,orga,url,nbv,nbs,nbp,nbr,state,still);
         arr.add(e);
     }
    return arr;
    }

    @Override
    public ObservableList<Event> ChercherEvent(String s) throws SQLException {
         ObservableList<Event> arr=FXCollections.observableArrayList();
         
         PreparedStatement pre=con.prepareStatement("select * from event where name_evt LIKE '%' ? '%' OR location_evt LIKE '%' ? '%' OR organizator_evt LIKE '%' ? '%'");
           pre.setString(1, s);
           pre.setString(2, s);
           pre.setString(3, s);
          ResultSet rs=pre.executeQuery();
          while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("name_evt");
            java.util.Date date=rs.getDate(3);
              Time time = rs.getTime(4);
               String location=rs.getString(5);
               String detail=rs.getString(6);
               double budget=rs.getDouble(7);
               String orga=rs.getString(8);
               String url=rs.getString(9);
                int nbv=rs.getInt(10);
                 int nbp=rs.getInt(12);
                 int nbs=rs.getInt(11);
                int nbr=rs.getInt(13);
                int state=rs.getInt(14);
                double still=rs.getDouble(15);
               Event e=new Event(id, nom, date, time,location,detail,budget,orga,url,nbv,nbs,nbp,nbr,state,still);
         arr.add(e);
     }
    return arr;
    }

    @Override
    public boolean updateStillneededEvent(Event t) throws SQLException {
     PreparedStatement pre=con.prepareStatement("UPDATE `swr`.`event` SET stillneeded_evt=? WHERE id_evt =?");
          
           pre.setDouble(1, t.getStillneeded_evt());
         
           pre.setInt(2, t.getId_evt());
        if(pre.executeUpdate()!=0) return true;
        return false;
    }

    @Override
    public Event readBudget_StillneededEvents(Event t) throws SQLException {
     
         Event e=new Event(t.getId_evt());
         PreparedStatement pre=con.prepareStatement("select * from event where id_evt =?");
           pre.setInt(1, t.getId_evt());
          
          ResultSet rs=pre.executeQuery();
          if (rs.next()) {                
               int id=rs.getInt(1);
              
               double budget=rs.getDouble(7);
                double still=rs.getDouble(15);
                
               e.setBudget(budget);
               e.setStillneeded_evt(still);
               
        
     }
    return e;
    }

    @Override
    public ObservableList<Event> TrierEventDate() throws SQLException {
          ObservableList<Event> arr=FXCollections.observableArrayList();
         ste=con.createStatement();
          ResultSet rs=ste.executeQuery("select * from event order by date_evt , time_evt ");
          while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("name_evt");
            java.util.Date date=rs.getDate(3);
              Time time = rs.getTime(4);
               String location=rs.getString(5);
               String detail=rs.getString(6);
               double budget=rs.getDouble(7);
               String orga=rs.getString(8);
               String url=rs.getString(9);
                 int nbv=rs.getInt(10);
                 int nbp=rs.getInt(12);
                 int nbs=rs.getInt(11);
                int nbr=rs.getInt(13);
                int state=rs.getInt(14);
                double still=rs.getDouble(15);
               Event e=new Event(id, nom, date, time,location,detail,budget,orga,url,nbv,nbs,nbp,nbr,state,still);
         arr.add(e);
     }
    return arr;
    }

    @Override
    public boolean decrementNbparticipantEvent(Event t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE `swr`.`event` SET nbparticipant_evt=nbparticipant_evt-1 WHERE id_evt =?");
        
           pre.setInt(1, t.getId_evt());
           if(pre.executeUpdate()!=0) return true;
        return false;
    }
    
    public Event GetInfosById(int id) throws SQLException
    {
         Event e = new Event();
         PreparedStatement pre=con.prepareStatement("select * from event where id_evt =?");
           pre.setInt(1,id);
          
          ResultSet rs=pre.executeQuery();
          if (rs.next()) {                
                
              String nom=rs.getString("name_evt");
            java.util.Date date=rs.getDate(3);
              Time time = rs.getTime(4);
               String location=rs.getString(5);
               String detail=rs.getString(6);
               double budget=rs.getDouble(7);
               String orga=rs.getString(8);
               String url=rs.getString(9);
                 int nbv=rs.getInt(10);
                 int nbp=rs.getInt(12);
                 int nbs=rs.getInt(11);
                int nbr=rs.getInt(13);
                int state=rs.getInt(14);
                double still=rs.getDouble(15);
              
                e.setName(nom);
                e.setBudget(budget);
                e.setDate_evt(date);
                e.setDetails(detail);
                e.setLocation(location);
                e.setId_evt(id);
               e.setOrganizator(orga);
               e.setStillneeded_evt(still);
               e.setUrl_evt(url);
               e.setTime_evt(time);
               e.setState_evt(state);
        
     }
           
    return e;
    }
    
    public ObservableList<Event> readSponsorEvent() throws SQLException {
        ObservableList<Event> arr= FXCollections.observableArrayList();

    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from event where state_evt is null or state_evt=0");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("name_evt");
            java.util.Date date=rs.getDate(3);
              Time time = rs.getTime(4);
               String location=rs.getString(5);
               String detail=rs.getString(6);
               double budget=rs.getDouble(7);
               String orga=rs.getString(8);
               String url=rs.getString(9);
                int nbv=rs.getInt(10);
                 int nbp=rs.getInt(12);
                 int nbs=rs.getInt(11);
                int nbr=rs.getInt(13);
                int state=rs.getInt(14);
                double still=rs.getDouble(15);
               Event e=new Event(id, nom, date, time,location,detail,budget,orga,url,nbv,nbs,nbp,nbr,state,still);
     arr.add(e);
     }
    return arr;
    }
    
    public ObservableList<Event> readParticipateEvent() throws SQLException {
        ObservableList<Event> arr= FXCollections.observableArrayList();

    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from event where state_evt=1");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("name_evt");
            java.util.Date date=rs.getDate(3);
              Time time = rs.getTime(4);
               String location=rs.getString(5);
               String detail=rs.getString(6);
               double budget=rs.getDouble(7);
               String orga=rs.getString(8);
               String url=rs.getString(9);
                int nbv=rs.getInt(10);
                 int nbp=rs.getInt(12);
                 int nbs=rs.getInt(11);
                int nbr=rs.getInt(13);
                int state=rs.getInt(14);
                double still=rs.getDouble(15);
               Event e=new Event(id, nom, date, time,location,detail,budget,orga,url,nbv,nbs,nbp,nbr,state,still);
     arr.add(e);
     }
    return arr;
    }
    
    
}
