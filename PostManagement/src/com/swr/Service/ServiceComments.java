/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;
import com.swr.Entite.Comments;
import com.swr.Entite.Posts;
import com.swr.IService.IService;
import com.swr.Service.ServicePosts;
import com.swr.Utils.DataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Soulah
 */
public class ServiceComments implements IService<Comments> {
    
     private final Connection con;
    private Statement ste;

 
    public ServiceComments() {
        con = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Comments p) throws SQLException {
         PreparedStatement pre=con.prepareStatement("INSERT INTO comments ( `idP`, `contenuC`, `dateC`, `reportC`, `iduser`) VALUES ( ?, ?, CURRENT_TIMESTAMP, ?, ?);");
    pre.setInt(1, p.getIdP());
    pre.setString(2, p.getContenuC());
    pre.setInt(3, p.getReportC());
    pre.setInt(4, p.getIduser());

    pre.executeUpdate();
    }

    @Override
    public boolean delete(Comments t) throws SQLException {
        
            PreparedStatement pre=con.prepareStatement("delete from comments where contenuC = '" + t.getContenuC() + "' ;");
            pre.executeUpdate();
            return true;    }

    @Override
    public boolean update(Comments t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE comments SET contenuC= '" + t.getContenuC() + "'  WHERE Iduser=1 ;");
            pre.executeUpdate();
            return true;
    }

    @Override
    public ObservableList<Comments> readAll() throws SQLException {
ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from comments");
     while (rs.next()) {                
               int idC=rs.getInt(1);
               String contenuC=rs.getString("contenuC");
               int idP=rs.getInt("idP");
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               Date dateC=rs.getDate("dateC");
               
               
               Comments  c=new Comments(idC, idP, contenuC, dateC, reportC,iduser);
     arr.add(c);
     }
    return arr;    }
    
     public  ObservableList<Comments> trie() throws SQLException{
        ObservableList fish= FXCollections.observableArrayList();

         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from comments order by idC desc;");
           while (rs.next()) {                
               int idC=rs.getInt(1);
               String contenuC=rs.getString("contenuC");
               int idP=rs.getInt("idP");
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               Date dateC=rs.getDate("dateC");
               
               Comments x=new Comments(idC, idP, contenuC, dateC, reportC,iduser);
     fish.add(x);
     }
        return fish;
    }
     
         public List<Comments> recherche(String aa) throws SQLException{
         List<Comments> fish=new ArrayList<>();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from comments where contenuC like '%" + aa + "%' ;");
           while (rs.next()) {                
                 int idC=rs.getInt(1);
               String contenuC=rs.getString("contenuC");
               int idP=rs.getInt("idP");
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               Date dateC=rs.getDate("dateC");
               
               
                Comments p=new Comments(idC, idP, contenuC, dateC, reportC,iduser);
     fish.add(p);
     }
        return fish;
    }
    

    
}
