/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event.test;

import com.event.Entite.Event;
import com.event.Service.ServiceEvent;
import com.event.Entite.Participation;
import com.event.Service.ServiceParticipation;
import com.event.Entite.Sponsor;
import com.event.Service.ServiceSponsor;
import java.sql.*;
import java.util.List;
/**
 *
 * @author Eya
 */
public class Event_Management {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         ServiceEvent ser = new ServiceEvent();
         ServiceParticipation serp = new ServiceParticipation();
         ServiceSponsor sers = new ServiceSponsor();
         
         Event e1;
        e1 = new Event(6,"update",new Date(2020,2,8),new Time(503022),"whereever","wtv",1220,"eya","eya.jpg");
        Participation p = new Participation(1,12);
        Sponsor s = new Sponsor(22,11,"logo.jpg",200);
         Event e2;
        e2 = new Event(19,"update",new Date(2020,2,8),new Time(503022),"whereever","wtv",1220,"eya","eya.jpg",200);
        try {
//         
          ser.addEvent(e2);
            
         
          //serp.addParticipation(p);
            //serp.deleteParticipation(p);
          //sers.addSponsor(s);
          /* List<Participation> list = serp.readAllParticipation();
         System.out.println(list);*/
        //ser.addEvent(e2);
            //ser.updateStillneededEvent(e2);
            //ser.deleteEvent(e1);
        //sers.addSponsor(s);
           
        //List<Event> list = ser.ChercherEvent("eya");
         //System.out.println(list);
         // List<Event> list = ser.TrierEventDate();
        // System.out.println(list);
           List<Sponsor> list = sers.TrierSponsorDate();
          System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
