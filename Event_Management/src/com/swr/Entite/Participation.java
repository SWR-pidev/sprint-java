/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Entite;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Eya
 */
public class Participation {
    private User u;
    private Event e;
    private Date participation_date;

    public Participation(User u, Event e, Date participation_date) {
        this.u=u;
        this.e = e;
        this.participation_date = participation_date;
    }

    public Participation() {
    }

    public Participation(User u, Event e) {
        this.u = u;
        this.e = e;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Event getE() {
        return e;
    }

    public void setE(Event e) {
        this.e = e;
    }

    public Date getParticipation_date() {
        return participation_date;
    }

    public void setParticipation_date(Date participation_date) {
        this.participation_date = participation_date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.u);
        hash = 53 * hash + Objects.hashCode(this.e);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Participation other = (Participation) obj;
        if (!Objects.equals(this.u, other.u)) {
            return false;
        }
        if (!Objects.equals(this.e, other.e)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Participation{" + "u=" + u + ", e=" + e + ", participation_date=" + participation_date + '}';
    }

 
    
    
    
}
