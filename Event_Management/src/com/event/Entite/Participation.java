/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event.Entite;

import java.sql.Date;

/**
 *
 * @author Eya
 */
public class Participation {
    private int id_user;
    private int id_evt;
    private Date participation_date;

    public Participation(int id_user, int id_evt, Date participation_date) {
        this.id_user = id_user;
        this.id_evt = id_evt;
        this.participation_date = participation_date;
    }

    public Participation(int id_user, int id_evt) {
        this.id_user = id_user;
        this.id_evt = id_evt;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_evt() {
        return id_evt;
    }

    public void setId_evt(int id_evt) {
        this.id_evt = id_evt;
    }

    public Date getParticipation_date() {
        return participation_date;
    }

    public void setParticipation_date(Date participation_date) {
        this.participation_date = participation_date;
    }

    @Override
    public String toString() {
        return "Participation{" + "id_user=" + id_user + ", id_evt=" + id_evt + ", participation_date=" + participation_date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id_user;
        hash = 61 * hash + this.id_evt;
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
        final Participation other = (Participation) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_evt != other.id_evt) {
            return false;
        }
        return true;
    }

    
    
    
}
