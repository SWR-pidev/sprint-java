/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event.Service;

import com.event.Entite.Participation;
import com.event.GUI.IServiceParticipation;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.Date;
import com.event.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.event.Entite.Event;
import com.event.Service.ServiceEvent;
/**
 *
 * @author Eya
 */
public class ServiceParticipation implements IServiceParticipation<Participation> {
 private Connection con;
    private Statement ste;

    public ServiceParticipation() {
        con = DataBase.getInstance().getConnection();

    }
    @Override
    public void addParticipation(Participation t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`participation` ( `id_user`, `id_evt`, `date_participation`) VALUES ( ?, ?, CURDATE());");
    pre.setInt(1, t.getId_user());
    pre.setInt(2, t.getId_evt());
    if(pre.executeUpdate()!=0) {
         Event e= new Event(t.getId_evt());
    ServiceEvent ser = new ServiceEvent();
    ser.updateSNbparticipantEvent(e);
     };
        return;
    }

    @Override
    public boolean deleteParticipation(Participation t) throws SQLException {
         PreparedStatement pre=con.prepareStatement("DELETE FROM `swr`.`participation`  WHERE id_evt =? AND id_user=?");
         pre.setInt(1, t.getId_evt());
         pre.setInt(2, t.getId_user());
        if(pre.executeUpdate()!=0) {
            Event e = new Event(t.getId_evt());
            ServiceEvent ser = new ServiceEvent();
            ser.decrementNbparticipantEvent(e);
            return true;
        }
        
        
        return false;
    }

    @Override
    public List<Participation> readAllParticipation() throws SQLException {
     List<Participation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from participation");
     while (rs.next()) {                
               int idU=rs.getInt(1);
               int idE=rs.getInt(2);
            Date date=rs.getDate(3);
              
               
                
               Participation p=new Participation(idU,idE,date);
     arr.add(p);
     }
    return arr;
    }

  
    
}
