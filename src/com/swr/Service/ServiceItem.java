/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.swr.Entite.Item;
import com.swr.IService.IServiceItem;
import com.swr.Utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mohamed
 */
public class ServiceItem implements IServiceItem{

    public static ServiceItem si;
    private Connection con;
    private Statement ste;
    private PreparedStatement pste;
    
    public ServiceItem() {
        con = DataBase.getInstance().getConnection();
    }
    public static ServiceItem getInstance()
    {if(si==null)
        si=new ServiceItem();
    return si;
    }
    
    

    @Override
    public void addItem(Item i) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `items`(`idItem`, `idh`, `name`, `description`, `quantity`, `expdate`, `status`) VALUES (NULL, '" + i.getIdhouse() + "', '" + i.getNameItem() + "', '"+ i.getDescription() + "', '" + i.getQuantity() + "', '"+ i.getExpDate() + "', '"+ i.getStatusItem()+ "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean deleteItem(Item i) throws SQLException {
        ste= con.createStatement();
        String deleteRequest ="DELETE FROM `items` WHERE `idItem`="+i.getIdItem()+";";
        int rows= ste.executeUpdate(deleteRequest);
            return rows != 0;
    }

    @Override
    public boolean updateItem(Item i) throws SQLException {
        pste= con.prepareStatement("UPDATE `items` SET `idh`=?,`name`=?,`description`=?,`quantity`=?,`expdate`=?,`status`=? WHERE `idItem`=?;");
            pste.setInt(1,i.getIdhouse());
            pste.setString(2, i.getNameItem());
            pste.setString(3, i.getDescription());
            pste.setInt(4, i.getQuantity());
            pste.setString(5, i.getExpDate());
            pste.setString(6, i.getStatusItem());
            pste.setInt(7, i.getIdItem());
            int test= pste.executeUpdate();
            return test != 0;
    }

    @Override
    public ObservableList<Item> getAllItems() throws SQLException {
        ObservableList arr=FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from items");
     while (rs.next()) {       
          int idItem= rs.getInt(1);
          int idh= rs.getInt(2);
           String nameItem= rs.getString(3);
            String description= rs.getString(4);
          int Quantity=  rs.getInt(5);
           String expDate= rs.getString(6);
           String statusItem= rs.getString(7);
      Item p=new Item(idItem,idh,nameItem,description,Quantity ,expDate,statusItem) ;
     arr.add(p);
     }
    return arr;
    }

    @Override
    public ObservableList<Item> getItemsOfHousing(int id) throws SQLException {
            ObservableList<Item> arr=FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from items where idh="+id+";");
     while (rs.next()) {       
          int idItem= rs.getInt(1);
          int idh= rs.getInt(2);
           String nameItem= rs.getString(3);
            String description= rs.getString(4);
          int Quantity=  rs.getInt(5);
           String expDate= rs.getString(6);
           String statusItem= rs.getString(7);
      Item p=new Item(idItem,idh,nameItem,description,Quantity ,expDate,statusItem) ;
     arr.add(p);
     }
    return arr;
    }

    @Override
    public Item getItemById(int id) throws SQLException {
        pste=con.prepareStatement("Select * from items where idItem=? ");
        pste.setInt(1, id);
        ResultSet rs= pste.executeQuery();
     
        while(rs.first()) {
               int idItem=rs.getInt(1);
               int idhouse= rs.getInt("idh");
               String nameItem=rs.getString("name");
               String description=rs.getString("description");
               int Quantity=rs.getInt("Quantity");
               String expDate=rs.getString("capacity");
               String statusItem=rs.getString("nbresidents");
               
        Item i= new Item(idItem,idhouse,nameItem,description, Quantity ,expDate,statusItem);
        return i;
            }
        return null;
    }

    @Override
    public int getItemByName(String n) throws SQLException {
    pste=con.prepareStatement("Select Quantity from items where name=? ");
        pste.setString(1,n );
        ResultSet rs= pste.executeQuery();
        while(rs.first()) {
               
               int Quantity=rs.getInt("Quantity");
               
               
        
        return Quantity;
            }
        return 0;
    }
   

    @Override
    public void EmptyItem() throws SQLException {
     pste=con.prepareStatement("Select * from items where quantity=0 ");
     ResultSet rs= pste.executeQuery();
      while(rs.next()) {
               int idItem=rs.getInt(1);
               int idhouse= rs.getInt("idh");
               String nameItem=rs.getString("name");
               String description=rs.getString("description");
               int Quantity=rs.getInt("quantity");
               String expDate=rs.getString("expdate");
               String statusItem=rs.getString("status");
            Item i= new Item(idItem,idhouse,nameItem,description, Quantity ,expDate,"Collected");
           
            updateItem(i);
     }
        
    }
    
    public void Changeandtest(String name,int q) throws SQLException {
     pste=con.prepareStatement("Select * from items where name='"+name+"';");
     ResultSet rs= pste.executeQuery();
     if(rs.first()){
               int idItem=rs.getInt(1);
               
               int idhouse= rs.getInt("idh");
               String nameItem=rs.getString("name");
               String description=rs.getString("description");
               int Quantity=rs.getInt("quantity");
               String expDate=rs.getString("expdate");
               String statusItem=rs.getString("status");
               if (Quantity<=q){
               Quantity=0;
               statusItem="Collected";
                   System.out.println("im here Collecting");
               }
               else {Quantity=Quantity-q;
               }
               
                Item i= new Item(idItem,idhouse,nameItem,description, Quantity ,expDate,statusItem);
                updateItem(i);
     }
     
    }
    //Update Item 
    
    
}
