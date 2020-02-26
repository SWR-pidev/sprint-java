/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Entite;

/**
 *
 * @author Asus
 */
public class Delivery {
    private int IdDe ;
    private int idDman ;
    private String DateD ;
    private String item ;

    public Delivery(int IdDe, int idDman, String DateD, String item) {
        this.IdDe = IdDe;
        this.idDman = idDman;
        this.DateD = DateD;
        this.item = item;
    }

    public Delivery(int idDman, String DateD, String item) {
        this.idDman = idDman;
        this.DateD = DateD;
        this.item = item;
    }

    public Delivery(String DateD, String item) {
        this.DateD = DateD;
        this.item = item;
    }

    public int getIdDe() {
        return IdDe;
    }

    public void setIdDe(int IdDe) {
        this.IdDe = IdDe;
    }

    public int getIdDman() {
        return idDman;
    }

    public void setIdDman(int idDman) {
        this.idDman = idDman;
    }

    public String getDateD() {
        return DateD;
    }

    public void setDateD(String DateD) {
        this.DateD = DateD;
    }

    public String getitem() {
        return item;
    }

    public void setitem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Delivery{" + "IdDe=" + IdDe + ", idDman=" + idDman + ", DateD=" + DateD + ", item=" + item + '}';
    }

    

    

    
    
}
