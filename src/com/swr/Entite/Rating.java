/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Entite;

/**
 *
 * @author mohamed
 */
public class Rating {

    private int idR;
    private int idH;
    private int iduser;
    private String feedback;
    private int rating;
    
    public Rating(int idR, int idH, int iduser, String feedback, int rating) {
        this.idR = idR;
        this.idH = idH;
        this.iduser = iduser;
        this.feedback = feedback;
        this.rating = rating;
    }

    public Rating(int idH, int iduser, String feedback, int rating) {
        this.idH = idH;
        this.iduser = iduser;
        this.feedback = feedback;
        this.rating = rating;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getidH() {
        return idH;
    }

    public void setidH(int idH) {
        this.idH = idH;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" + "idR=" + idR + ", idH=" + idH + ", iduser=" + iduser + ", feedback=" + feedback + ", rating=" + rating + '}';
    }
    
    
}
