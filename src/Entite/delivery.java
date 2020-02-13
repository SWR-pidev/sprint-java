/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Asus
 */
public class delivery {
    private int idDe;
    private int idDman;
    private String DateD;
    private int idItem;

    public delivery(int idDe, int idDman, String DateD, int idItem) {
        this.idDe = idDe;
        this.idDman = idDman;
        this.DateD = DateD;
        this.idItem = idItem;
    }

    public delivery(String DateD, int idItem) {
        this.DateD = DateD;
        this.idItem = idItem;
    }

    public delivery(int idDe, int idDman) {
        this.idDe = idDe;
        this.idDman = idDman;
    }

    public delivery(int idDe) {
        this.idDe = idDe;
    }

    public delivery(int idDman, String DateD, int idItem) {
        this.idDman = idDman;
        this.DateD = DateD;
        this.idItem = idItem;
    }

    public int getIdDe() {
        return idDe;
    }

    public void setIdDe(int idDe) {
        this.idDe = idDe;
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

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    @Override
    public String toString() {
        return "delivery{" + "idDe=" + idDe + ", idDman=" + idDman + ", DateD=" + DateD + ", idItem=" + idItem + '}';
    }
    
    
    
}