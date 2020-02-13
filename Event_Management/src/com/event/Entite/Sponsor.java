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
public class Sponsor {
    private int id_sponsor;
    private int id_evt;
    private String logo;
    private double given;
    private Date date_sponsor;

    public Sponsor(int id_sponsor, int id_evt, String logo, double given) {
        this.id_sponsor = id_sponsor;
        this.id_evt = id_evt;
        this.logo = logo;
        this.given = given;
    }




    public Sponsor(int id_sponsor, int id_evt, String logo, double given, Date date_sponsor) {
        this.id_sponsor = id_sponsor;
        this.id_evt = id_evt;
        this.logo = logo;
        this.given = given;
        this.date_sponsor = date_sponsor;
    }
    
     public int getId_sponsor() {
        return id_sponsor;
    }

    public void setId_sponsor(int id_sponsor) {
        this.id_sponsor = id_sponsor;
    }

    public int getId_evt() {
        return id_evt;
    }

    public void setId_evt(int id_evt) {
        this.id_evt = id_evt;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getGiven() {
        return given;
    }

    public void setGiven(double given) {
        this.given = given;
    }

    public Date getDate_sponsor() {
        return date_sponsor;
    }

    public void setDate_sponsor(Date date_sponsor) {
        this.date_sponsor = date_sponsor;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "id_sponsor=" + id_sponsor + ", id_evt=" + id_evt + ", logo=" + logo + ", given=" + given + ", date_sponsor=" + date_sponsor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id_sponsor;
        hash = 23 * hash + this.id_evt;
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
        final Sponsor other = (Sponsor) obj;
        if (this.id_sponsor != other.id_sponsor) {
            return false;
        }
        if (this.id_evt != other.id_evt) {
            return false;
        }
        return true;
    }
    
}
