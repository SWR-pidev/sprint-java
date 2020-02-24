/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Entite;

import java.sql.Date;

/**
 *
 * @author Eya
 */
public class Sponsor {
    private User u;
    private Event e;
    private String logo;
    private double given;
    private Date date_sponsor;
    String nom;
    String prenom;
    String nameEvt;
    public Sponsor() {
    }

    public Sponsor(User u, Event e, String logo, double given) {
        this.u = u;
        this.e = e;
        this.logo = logo;
        this.given = given;
    }

    public Sponsor(String logo, double given) {
        this.logo = logo;
        this.given = given;
    }

    public Sponsor(User u, Event e, String logo, double given, Date date_sponsor) {
        this.u = u;
        this.e = e;
        this.logo = logo;
        this.given = given;
        this.date_sponsor = date_sponsor;
    }

    public Sponsor(double given, String nom, String prenom, String nameEvt) {
        this.given = given;
        this.nom = nom;
        this.prenom = prenom;
        this.nameEvt = nameEvt;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNameEvt() {
        return nameEvt;
    }

    public void setNameEvt(String nameEvt) {
        this.nameEvt = nameEvt;
    }

   
    
}
