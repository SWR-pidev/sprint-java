/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Entite;

/**
 *
 * @author Soulah
 */
public class Likes {
   public  User u;
  public   Posts p;

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdP() {
        return idP;
    }
  int idP;

    public Likes(User u, int idP) {
        this.u = u;
        this.idP = idP;
    }
  
  

    public Likes(User u, Posts p) {
        this.u = u;
        this.p = p;
    }

    public User getU() {
        return u;
    }

    public Posts getP() {
        return p;
    }

    public void setU(User u) {
        this.u = u;
    }

    public void setP(Posts p) {
        this.p = p;
    }
    
    
    
}
