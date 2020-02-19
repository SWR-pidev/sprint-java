/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Entite;
import java.sql.Time;
import java.util.Date;
import com.swr.Entite.User;
/**
 *
 * @author Monta
 */
public class Donation {
    private int idDon ;
    private User u;
    private Compaign c;
    private int idc=c.getId_cmp();
    private int idu=u.getIdu();
    private int monthly;
    private String mes;
    private int funds ;
    private double given;
    private int Annonym;
    private Date d;
    private Time t;
    public Donation(int idDon, User u, Compaign c, int monthly, String mes, int funds, double given, int Annonym) {
        this.idDon = idDon;
        this.u = u;
        this.c = c;
        this.monthly = monthly;
        this.mes = mes;
        this.funds = funds;
        this.given = given;
        this.Annonym = Annonym;
    }

    public Donation(User u, Compaign c, int monthly, String mes, int funds, double given, int Annonym) {
        this.u = u;
        this.c = c;
        this.monthly = monthly;
        this.mes = mes;
        this.funds = funds;
        this.given = given;
        this.Annonym = Annonym;
    }

    public int getIdDon() {
        return idDon;
    }

    public void setIdDon(int idDon) {
        this.idDon = idDon;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Compaign getC() {
        return c;
    }

    public void setC(Compaign c) {
        this.c = c;
    }

    public int getMonthly() {
        return monthly;
    }

    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public double getGiven() {
        return given;
    }

    public void setGiven(double given) {
        this.given = given;
    }

    public int getAnnonym() {
        return Annonym;
    }

    public void setAnnonym(int Annonym) {
        this.Annonym = Annonym;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public Time getT() {
        return t;
    }

    public void setT(Time t) {
        this.t = t;
    }

    public Donation(int idDon, User u, Compaign c, int monthly, String mes, int funds, double given, int Annonym, Date d, Time t) {
        this.idDon = idDon;
        this.u = u;
        this.c = c;
        this.monthly = monthly;
        this.mes = mes;
        this.funds = funds;
        this.given = given;
        this.Annonym = Annonym;
        this.d = d;
        this.t = t;
    }
    
}
