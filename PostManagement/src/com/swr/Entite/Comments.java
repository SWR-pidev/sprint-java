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
    int idC,idP,reportC,iduser;
    String contenuC;
    Date dateC;

    public Comments(String contenuC,int iduser) {
        this.iduser = iduser;
        this.contenuC = contenuC;
    }

    public Comments(int idC, int idP, String contenuC, Date dateC, int reportC, int iduser) {
        this.idC = idC;
        this.idP = idP;
        this.reportC = reportC;
        this.iduser = iduser;
        this.contenuC = contenuC;
        this.dateC = dateC;
    }

    public Comments(int idP, String contenuC, Date dateC, int reportC, int iduser) {
        this.idP = idP;
        this.reportC = reportC;
        this.iduser = iduser;
        this.contenuC = contenuC;
        this.dateC = dateC;
    }
    
    

    public Comments(int idP,String contenuC, int reportC, int iduser) {
        this.idC = idC;
        this.idP = idP;
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

    public void setIdP(int idP) {
        this.idP = idP;
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

    public int getIdC() {
        return idC;
    }

    public int getIdP() {
        return idP;
    }

    public int getReportC() {
        return reportC;
    }

    public int getIduser() {
        return iduser;
    }

    public String getContenuC() {
        return contenuC;
    }

    @Override
    public String toString() {
        return "Comments{" + "idC=" + idC + ", idP=" + idP + ", reportC=" + reportC + ", iduser=" + iduser + ", contenuC=" + contenuC + '}';
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
