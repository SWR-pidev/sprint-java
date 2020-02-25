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
public class Comments {
    int idC,reportC,iduser;
    Posts c;
    String contenuC;
    Date dateC;
    User u;

    public Comments() {
    }

    
    public Comments(String contenuC,Posts c,User u) {
        this.u = u;
        this.contenuC = contenuC;
        this.c=c;
    }

    public Comments(int idC,Posts p, String contenuC, Date dateC, int reportC, User u) {
        this.idC = idC;
        this.c=p;
        this.reportC = reportC;
        this.u = u;
        this.contenuC = contenuC;
        this.dateC = dateC;
    }

    public Comments(Posts c, String contenuC, Date dateC, int reportC, int iduser) {
        this.c = c;
        this.reportC = reportC;
        this.iduser = iduser;
        this.contenuC = contenuC;
        this.dateC = dateC;
    }
    
    

    public Comments(Posts c,String contenuC, int reportC, int iduser) {
        this.idC = idC;
        this.c = c;
        this.reportC = reportC;
        this.iduser = iduser;
        this.contenuC = contenuC;
        this.dateC = dateC;
    }
    
    

    public Date getDateC() {
        return dateC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setIdP(Posts c) {
        this.c = c;
    }

    public void setReportC(int reportC) {
        this.reportC = reportC;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public void setContenuC(String contenuC) {
        this.contenuC = contenuC;
    }

    public void setDateC(Date dateC) {
        this.dateC = dateC;
    }

    public void setC(Posts c) {
        this.c = c;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    
    public Posts getC() {
        return c;
    }

    public User getU() {
        return u;
    }  

    public int getIdC() {
        return idC;
    }

    public int getIdP() {
        return c.getIdP();
    }

    public int getReportC() {
        return reportC;
    }

    public int getIduser() {
        return  u.getIdu();
    }

    public String getContenuC() {
        return contenuC;
    }

    @Override
    public String toString() {
        return "Comments{" + "idC=" + idC + ", idP=" + c.getIdP() + ", reportC=" + reportC + ", iduser=" + u.getIdu() + ", contenuC=" + contenuC + '}';
    }
    
    

    /**
     *
     * @param idC
     * @param idP
     * @param contenuC
     * @param reportC
     * @param iduser
     */
   
    
}
