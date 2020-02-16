/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.test;

import com.swr.Entite.Event;
import com.swr.Service.ServiceEvent;
import com.swr.Entite.Participation;
import com.swr.Service.ServiceParticipation;
import com.swr.Entite.Sponsor;
import com.swr.Service.ServiceSponsor;
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
         Participation p1 = new Participation(1,12);
        Sponsor s = new Sponsor(22,11,"logo.jpg",200);
         Event e2;
        e2 = new Event(19,"update",new Date(2020,2,8),new Time(503022),"whereever","wtv",1220,"eya","eya.jpg",200);
          Event e3;
        e3 = new Event("testing",new Date(2020,2,8),new Time(503022),"whereever","wtv",1220,"eya","eya.jpg");
         Event e4;
        e4 = new Event("testing2222",new Date(2020,2,8),new Time(503022),"whereever","wtv",1220,"eya","eya.jpg");
        try {
//         
          ser.addEvent(e3);
            //ser.updateEvent(e3);
         //ser.deleteEvent(e4);
         /* List<Event> list = ser.readAllEvents();
         System.out.println(list);*/
          serp.addParticipation(p);
           serp.addParticipation(p1);
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
           /*List<Sponsor> list = sers.TrierSponsorDate();
          System.out.println(list);*/
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
