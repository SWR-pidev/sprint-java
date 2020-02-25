/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Entite;

import java.sql.Date;

/**
 *
 * @author Soulah
 */
public class Posts {
    private int idP,nbcmt,views,likes,report,iduser;
    private String contenuP;
    Date dateP;
    User u;

   
    
    /************ constructors **********/
    
    
    
    public Posts() {
    }  

    public Posts(int idP) {
        this.idP = idP;
    }

    public Posts(String contenuP, User u) {
        this.idP = idP;
        this.nbcmt = nbcmt;
        this.views = views;
        this.likes = likes;
        this.report = report;
        this.contenuP = contenuP;
        this.u = u;
        this.dateP = dateP;
    }  

    public Posts(int idP, String contenuP, int nbcmt, int views, int likes, int report, User u,Date dateP) {
        this.idP = idP;
        this.nbcmt = nbcmt;
        this.views = views;
        this.likes = likes;
        this.report = report;
        this.contenuP = contenuP;
        this.dateP = dateP;
        this.u = u;
    }

    public Posts(int idP, String contenuP, int nbcmt, int views, int likes, int report, int iduser) {
        this.idP = idP;
        this.nbcmt = nbcmt;
        this.views = views;
        this.likes = likes;
        this.report = report;
        this.iduser = iduser;
        this.contenuP = contenuP;
        this.dateP = dateP;
    }  
    
    public Posts(String contenuP, int nbcmt, int views, int likes, int report, int iduser) {
        this.nbcmt = nbcmt;
        this.views = views;
        this.likes = likes;
        this.report = report;
        this.iduser = iduser;
        this.contenuP = contenuP;
        this.dateP = dateP;
    }

    public Posts(int idP,String contenuP, int nbcmt, int views, int likes, int report, int iduser, Date dateP) {
        this.nbcmt = nbcmt;
        this.views = views;
        this.likes = likes;
        this.report = report;
        this.iduser = iduser;
        this.contenuP = contenuP;
        this.dateP = dateP;
    }
    
    
/**********  setters   ************/
    public void setIdP(int idP) {
        this.idP = idP;
    }
    
    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }

    public void setNbcmt(int nbcmt) {
        this.nbcmt = nbcmt;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public void setIduser(User u) {
        this.u = u;
    }

    public void setContenuP(String contenuP) {
        this.contenuP = contenuP;
    }
    
    
    /********** getters ************/

    public int getIdP() {
        return idP;
    }
    
    public Date getDateP() {
        return dateP;
    }

    public int getNbcmt() {
        return nbcmt;
    }

    public int getViews() {
        return views;
    }

    public int getLikes() {
        return likes;
    }

    public int getReport() {
        return report;
    }

    public int getIduser() {
        return u.getIdu();
    }

    public String getContenuP() {
        return contenuP;
    }
    /************** To String *****************/
    
     @Override
    public String toString() {
        return "Posts{" + "idP=" + idP + ", nbcmt=" + nbcmt + ", views=" + views + ", likes=" + likes + ", report=" + report + ", iduser=" + u.getIdu() + ", contenuP=" + contenuP + ", Date=" + dateP +'}';
    }
    

   
}
