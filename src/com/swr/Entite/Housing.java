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
public class Housing {

   
    private int idh;
    private String name;
    private String description;
    private String address;
    private String location;
    private int capacity;
    private int nbresidents;
    private String  type;
    private double rating;

    public Housing(int idh, String name, String description, String address, String location, int capacity, int nbresidents, String type, double rating) {
        this.idh = idh;
        this.name = name;
        this.description = description;
        this.address = address;
        this.location = location;
        this.capacity = capacity;
        this.nbresidents = nbresidents;
        this.type = type;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Housing{" + "idh=" + idh + ", name=" + name + ", description=" + description + ", address=" + address + ", location=" + location + ", capacity=" + capacity + ", nbresidents=" + nbresidents + ", type=" + type + ", rating=" + rating + '}';
    }
    
  
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public Housing(int idh,String name, String description, String address, String location, int capacity, int nbresidents, String type) {
        this.idh = idh;
        this.name= name;
        this.description = description;
        this.address = address;
        this.location = location;
        this.capacity = capacity;
        this.nbresidents = nbresidents;
        this.type = type;
    }

  

    public boolean houseexiste(int id){
    return this.idh==id;
    }
    
    public Housing(String name,String description, String address, String location, int capacity, int nbresidents, String type) {
        this.name= name;
        this.description = description;
        this.address = address;
        this.location = location;
        this.capacity = capacity;
        this.nbresidents = nbresidents;
        this.type = type;
    }

    public int getIdh() {
        return idh;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNbresidents() {
        return nbresidents;
    }

    public void setNbresidents(int nbresidents) {
        this.nbresidents = nbresidents;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
