/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event.Entite;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Eya
 */
public class Event {
    private int id_evt;
    private String name;
    private Date date_evt;
    private Time time_evt;
    private String location;
    private String details;
    private double budget;
    private String organizator;
    private String url_evt ;
    private int nbviews;
    private int nbsponsor;
    private int nbparticipant;
    private int nbreport;
    private int state_evt;
    private double stillneeded_evt;

    public Event(int id_evt, String name, Date date_evt, Time time_evt, String location, String details, double budget, String organizator, String url_evt, int nbviews, int nbsponsor, int nbparticipant, int nbreport, int state_evt, double stillneeded_evt) {
        this.id_evt = id_evt;
        this.name = name;
        this.date_evt = date_evt;
        this.time_evt = time_evt;
        this.location = location;
        this.details = details;
        this.budget = budget;
        this.organizator = organizator;
        this.url_evt = url_evt;
        this.nbviews = nbviews;
        this.nbsponsor = nbsponsor;
        this.nbparticipant = nbparticipant;
        this.nbreport = nbreport;
        this.state_evt = state_evt;
        this.stillneeded_evt = stillneeded_evt;
    }

    public Event(int id_evt, double budget, double stillneeded_evt) {
        this.id_evt = id_evt;
        this.budget = budget;
        this.stillneeded_evt = stillneeded_evt;
    }

   
    

    public Event(int id_evt) {
        this.id_evt = id_evt;
    }

    public Event(int id_evt, String name, Date date_evt, Time time_evt, String location, String details, double budget, String organizator, String url_evt) {
        this.id_evt = id_evt;
        this.name = name;
        this.date_evt = date_evt;
        this.time_evt = time_evt;
        this.location = location;
        this.details = details;
        this.budget = budget;
        this.organizator = organizator;
        this.url_evt = url_evt;
    }

    public Event(int id_evt, String name, Date date_evt, Time time_evt, String location, String details, double budget, String organizator, String url_evt, double stillneeded_evt) {
        this.id_evt = id_evt;
        this.name = name;
        this.date_evt = date_evt;
        this.time_evt = time_evt;
        this.location = location;
        this.details = details;
        this.budget = budget;
        this.organizator = organizator;
        this.url_evt = url_evt;
        this.stillneeded_evt = stillneeded_evt;
    }

    public Event() {
    }

    public Event(String name, Date date_evt, Time time_evt, String location, String details, double budget, String organizator, String url_evt) {
        this.name = name;
        this.date_evt = date_evt;
        this.time_evt = time_evt;
        this.location = location;
        this.details = details;
        this.budget = budget;
        this.organizator = organizator;
        this.url_evt = url_evt;
    }

    public int getId_evt() {
        return id_evt;
    }

    public void setId_evt(int id_evt) {
        this.id_evt = id_evt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_evt() {
        return date_evt;
    }

    public void setDate_evt(Date date_evt) {
        this.date_evt = date_evt;
    }

    public Time getTime_evt() {
        return time_evt;
    }

    public void setTime_evt(Time time_evt) {
        this.time_evt = time_evt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getOrganizator() {
        return organizator;
    }

    public void setOrganizator(String organizator) {
        this.organizator = organizator;
    }

    public String getUrl_evt() {
        return url_evt;
    }

    public void setUrl_evt(String url_evt) {
        this.url_evt = url_evt;
    }

    public int getNbviews() {
        return nbviews;
    }

    public void setNbviews(int nbviews) {
        this.nbviews = nbviews;
    }

    public int getNbsponsor() {
        return nbsponsor;
    }

    public void setNbsponsor(int nbsponsor) {
        this.nbsponsor = nbsponsor;
    }

    public int getNbparticipant() {
        return nbparticipant;
    }

    public void setNbparticipant(int nbparticipant) {
        this.nbparticipant = nbparticipant;
    }

    public int getNbreport() {
        return nbreport;
    }

    public void setNbreport(int nbreport) {
        this.nbreport = nbreport;
    }

    public int getState_evt() {
        return state_evt;
    }

    public void setState_evt(int state_evt) {
        this.state_evt = state_evt;
    }

    public double getStillneeded_evt() {
        return stillneeded_evt;
    }

    public void setStillneeded_evt(double stillneeded_evt) {
        this.stillneeded_evt = stillneeded_evt;
    }

    @Override
    public String toString() {
        return "Event{" + "id_evt=" + id_evt + ", name=" + name + ", date_evt=" + date_evt + ", time_evt=" + time_evt + ", location=" + location + ", details=" + details + ", budget=" + budget + ", organizator=" + organizator + ", url_evt=" + url_evt + ", nbviews=" + nbviews + ", nbsponsor=" + nbsponsor + ", nbparticipant=" + nbparticipant + ", nbreport=" + nbreport + ", state_evt=" + state_evt + ", stillneeded_evt=" + stillneeded_evt + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id_evt;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (this.id_evt != other.id_evt) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
