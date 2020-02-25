/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;
import com.swr.Entite.Comments;
import com.swr.Entite.Posts;
import com.swr.Entite.User;
import com.swr.IService.IService1;
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
public class ServiceComments implements IService1<Comments> {
    
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

    public boolean delete(int x) throws SQLException {
        
            PreparedStatement pre=con.prepareStatement("delete from comments where idC = '" + x + "' ;");
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
               Posts p=new Posts();
                 User u=new User();
               
               int idP=rs.getInt("idP");
               p.setIdP(idP);
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               
               Date dateC=rs.getDate("dateC");
               u.setId(iduser);
               
               Comments  c=new Comments(idC, p, contenuC, dateC, reportC,u);
     arr.add(c);
     }
    return arr;    }
    
     public  ObservableList<Comments> trie() throws SQLException{
        ObservableList fish= FXCollections.observableArrayList();

         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from comments order by idP desc;");
           while (rs.next()) {                
               int idC=rs.getInt(1);
               String contenuC=rs.getString("contenuC");
               Posts p=new Posts();
                User u=new User();
               
               int idP=rs.getInt("idP");
               p.setIdP(idP);
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               
               Date dateC=rs.getDate("dateC");
               u.setId(iduser);
               
               Comments x=new Comments(idC, p, contenuC, dateC, reportC,u);
     fish.add(x);
     }
        return fish;
    }
     
         public ObservableList<Comments> recherche(String aa) throws SQLException{
          ObservableList fish= FXCollections.observableArrayList();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from comments where contenuC like '%" + aa + "%' ;");
           while (rs.next()) {                
                 int idC=rs.getInt(1);
               String contenuC=rs.getString("contenuC");
               Posts x=new Posts();
               User u=new User();
               
               int idP=rs.getInt("idP");
               x.setIdP(idP);
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               
               Date dateC=rs.getDate("dateC");
               u.setId(iduser);
               
               
                Comments p=new Comments(idC, x, contenuC, dateC, reportC,u);
     fish.add(p);
     }
        return fish;
    }
    
         
         public  ObservableList<Comments> getpostscmt() throws SQLException{
        ObservableList fish= FXCollections.observableArrayList();

         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from comments inner join posts on  comments.idP=posts.id;");
           while (rs.next()) {                
               int idC=rs.getInt(1);
               String contenuC=rs.getString("contenuC");
               Posts p=new Posts();
               User u=new User();
               
               int idP=rs.getInt("idP");
               p.setIdP(idP);
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               
               Date dateC=rs.getDate("dateC");
               u.setId(iduser);
               
               Comments x=new Comments(idC, p, contenuC, dateC, reportC,u);
     fish.add(x);
     }
        return fish;
    }
         
            public  ObservableList<Comments> getpostscomments(int id) throws SQLException{
        ObservableList fish= FXCollections.observableArrayList();

         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from comments where idP= '" + id + "' ");
           while (rs.next()) {                
               int idC=rs.getInt(1);
               String contenuC=rs.getString("contenuC");
               Posts p=new Posts();
                User u=new User();
               
               int idP=rs.getInt("idP");
               p.setIdP(idP);
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               
               Date dateC=rs.getDate("dateC");
               u.setId(iduser);
               
               Comments x=new Comments(idC, p, contenuC, dateC, reportC,u);
     fish.add(x);
     }
        return fish;
    }

    @Override
    public boolean delete(Comments t) throws SQLException {
 PreparedStatement pre=con.prepareStatement("delete from comments where idC = '" + t.getIdC()+ "' ;");
            pre.executeUpdate();
            return true;      }
    
         public ObservableList<Integer> listidc() throws SQLException{
         ObservableList<Integer> fish=FXCollections.observableArrayList();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select idC from comments ;");
           while (rs.next()) {                
               int idC=rs.getInt(1);
           
               
               
            //   Posts x=new Posts(idP);
     fish.add(idC);
     }
        return fish;
         }
    
         public boolean deleteX(int x,int a) throws SQLException {
        
            PreparedStatement pre=con.prepareStatement("delete from comments where idC = '" + x + "' and iduser = '" + a + "'  ;");
            pre.executeUpdate();
            return true;    }
         
     public  Comments getcomment(String d,int z) throws SQLException{
        
               Comments x=new Comments();

         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from comments where contenuC= '" + d + "' and iduser= '" + z + "' limit 1 ");
           while (rs.next()) {                
               int idC=rs.getInt(1);
               String contenuC=rs.getString("contenuC");
               Posts p=new Posts();
                User u=new User();
               
               int idP=rs.getInt("idP");
               p.setIdP(idP);
               int reportC=rs.getInt("reportC");           
               int iduser=rs.getInt("iduser");
               
               Date dateC=rs.getDate("dateC");
               u.setId(iduser);
               
                x=new Comments(idC, p, contenuC, dateC, reportC,u);
    
     }
        return x;
    }
    
    
  public boolean Modify(String x,User u,int o) throws SQLException{
       PreparedStatement pre=con.prepareStatement("UPDATE comments SET contenuC= '" + x + "'  WHERE Iduser= '" + u.getIdu()+ "' and idC='" + o + "' ;");
            pre.executeUpdate();
            return true;
  }

    
}
