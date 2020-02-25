/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Entite;

/**
 *
 * @author Monta
 */
public class Proposition {
    private int id_prop;
    private Compaign c;
    public String namecmp;
    private String details; 

    public Proposition(int id_prop, Compaign c, String details) {
        this.id_prop = id_prop;
        this.c = c;
        this.details = details;
    }

    public Proposition(Compaign c, String details) {
        this.c = c;
        this.details = details;
    }

    public Proposition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_prop() {
        return id_prop;
    }

    public void setId_prop(int id_prop) {
        this.id_prop = id_prop;
    }

    public Compaign getC() {
        return c;
    }

    public void setC(Compaign c) {
        this.c = c;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Proposition{" + "id_prop=" + id_prop + ", c=" + c + ", details=" + details + '}';
    }
    

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNamecmp() {
        return namecmp;
    }

    public void setNamecmp(String namecmp) {
        this.namecmp = namecmp;
    }

    public Proposition(int id_prop, String details) {
        this.id_prop = id_prop;
        this.details = details;
    }

    public Proposition(int id_prop) {
        this.id_prop = id_prop;
    }

   
}
