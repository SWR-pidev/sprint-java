/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;

import com.swr.Entite.goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.swr.Utils.DataBase;

/**
 *
 * @author Asus
 */
public class Servicegoods  {
    private Connection con;
    private Statement ste;

    public Servicegoods() {
        con = DataBase.getInstance().getConnection();

    }
public void ajouter1(goods g ) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`goods` (`idG`,`idH`,`idUser`,`item`,`Qcollected`,`status`) VALUES (NULL,?,?,?,?,?);");
    pre.setInt(1, g.getIdH());
    pre.setInt(2, g.getIdUser());
    pre.setString(3, g.getItem());
    pre.setInt(4, g.getQcollected());
    pre.setString(5, g.getStatus());
    pre.executeUpdate();
    }
    
    public ObservableList<goods> readAll() throws SQLException {
        ObservableList arr =FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from goods");
     while (rs.next()) {                
               int idG=rs.getInt(1);
               int idH=rs.getInt("idH");
               int idUser=rs.getInt("idUser");
                String item=rs.getString("item");
                int Qcollected=rs.getInt("Qcollected");
                String status=rs.getString("status");
                
              goods g= new goods(idH,idUser,item, Qcollected, status);
              arr.add(g);
              
     }
    return arr;
    }

    
    public void ajouter(Object t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean delete(Object t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean update(Object t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
