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
public class Compaign {
    private int id_cmp; 
    private String namecmp; 
    private double raised;
    private double trgt;
    private String url;
    private int nbdon;
    private double stillneeded; 
    
    public Compaign(int id_cmp, String namecmp) {
        this.id_cmp = id_cmp;
        this.namecmp = namecmp;
    }

    public Compaign(int id_cmp, String namecmp, double trgt, String url, String desc) {
        this.id_cmp = id_cmp;
        this.namecmp = namecmp;
        this.trgt = trgt;
        this.url = url;
        this.desc = desc;
    }

    
    public Compaign(String namecmp, double raised, double trgt, String url, int nbdon, double stillneeded, String desc) {
        this.namecmp = namecmp;
        this.raised = raised;
        this.trgt = trgt;
        this.url = url;
        this.nbdon = nbdon;
        this.stillneeded = stillneeded;
        this.desc = desc;
    }
    
    private String desc;

    public Compaign(int id_cmp) {
        this.id_cmp = id_cmp;
    }

    public Compaign() {
    }

    public Compaign(String namecmp, double trgt, String url, String desc) {
        this.namecmp = namecmp;
        this.trgt = trgt;
        this.url = url;
        this.desc = desc;
    }

    
    

    public int getId_cmp() {
        return id_cmp;
    }

    public void setId_cmp(int id_cmp) {
        this.id_cmp = id_cmp;
    }

    public String getNamecmp() {
        return namecmp;
    }

    public void setNamecmp(String namecmp) {
        this.namecmp = namecmp;
    }

    public double getRaised() {
        return raised;
    }

    public void setRaised(double raised) {
        this.raised = raised;
    }

    public double getTrgt() {
        return trgt;
    }

    public void setTrgt(double trgt) {
        this.trgt = trgt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNbdon() {
        return nbdon;
    }

    public void setNbdon(int nbdon) {
        this.nbdon = nbdon;
    }

    public double getStillneeded() {
        return stillneeded;
    }

    public void setStillneeded(double stillneeded) {
        this.stillneeded = stillneeded;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id_cmp;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compaign other = (Compaign) obj;
        if (this.id_cmp != other.id_cmp) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Compaign{" + "id_cmp=" + id_cmp + ", namecmp=" + namecmp + ", raised=" + raised + ", trgt=" + trgt + ", desc=" + desc + ", url=" + url + ", nbdon=" + nbdon + ", stillneeded=" + stillneeded + '}';
    }

    public Compaign(int id_cmp, String namecmp, double raised, double trgt, String desc, String url, int nbdon, double stillneeded) {
        this.id_cmp = id_cmp;
        this.namecmp = namecmp;
        this.raised = raised;
        this.trgt = trgt;
        this.desc = desc;
        this.url = url;
        this.nbdon = nbdon;
        this.stillneeded = stillneeded;
    }
}
