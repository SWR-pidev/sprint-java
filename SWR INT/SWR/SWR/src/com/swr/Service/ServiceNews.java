/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;
import com.swr.Entite.News;
import com.swr.IService.IServiceNew;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.swr.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
/**
 *
 * @author Asus X550V
 */
public class ServiceNews implements IServiceNew<News> {
    
    private Connection con;
    private Statement ste;
    
    public ServiceNews()
    {
    con = DataBase.getInstance().getConnection();
    }
    
    @Override
    public void ajouter(News n) throws SQLException {
ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`news` (`idn`, `titre`, `description`, `datepub`, `nomcat`, `image`) VALUES (NULL, '" + n.getTitre() + "', '" + n.getDesc() + "',sysdate(),'" + n.getNomcat() +"','" +n.getImage()+"');";
        ste.executeUpdate(requeteInsert);    }

    @Override
    public boolean delete(String titre) throws SQLException {
PreparedStatement pre=con.prepareStatement("DELETE FROM news WHERE titre='"+titre+"' ;");
        pre.executeUpdate();
        return true;    }

    @Override
    public boolean update(String titre1, String titre, String description,String nomcat,String image) throws SQLException {
PreparedStatement pre=con.prepareStatement("UPDATE news SET titre= '" +titre+ "',description='"+description+"',nomcat= '"+nomcat+"',image= '"+image+"'WHERE titre='"+titre1+"' ;");           
pre.executeUpdate();
       
        return true;      }

    @Override
    public ObservableList<News> readAll() throws SQLException {
ObservableList oblist = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from news");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String desc=rs.getString("description");
               String datepub=rs.getString("datepub");
               String nomcat=rs.getString("nomcat");
               String image=rs.getString("image");
               News n=new News(id,titre,desc,datepub,nomcat);
     oblist.add(n);
     }
    return oblist;    }

    @Override
    public List<News> nouveaute() throws SQLException {
List<News> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `news` WHERE datepub between SUBDATE(sysdate(),3) and sysdate()");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String desc=rs.getString("description");
               String datepub=rs.getString("datepub");
               String nomcat=rs.getString("nomcat");
               News n=new News(id, titre,desc,datepub,nomcat);
     arr.add(n);
     }
    return arr;     }

    @Override
    public float stats(String nomcat) throws SQLException {
        
        float i=0,k=0;
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from news where nomcat='"+nomcat+"' ;");
        while (rs.next()) {
        i++; 
        }
        ResultSet rs1=ste.executeQuery("select * from news ;");
        while (rs1.next()) {
        k++; 
        }
        float s=((i/k)*100);
        return s;
    }

    @Override
    public ResultSet satistique() throws SQLException {
         //To change body of generated methods, choose Tools | Templates.ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select nomcat,count(*)total from news group by nomcat ");
    return rs;
    }
    @Override
    public ObservableList<News> list() throws SQLException {
    ObservableList oblist = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select titre from news");
     while (rs.next()) {                
               String titre=rs.getString("titre");
               News n=new News(titre);
     oblist.add(n);
     }
    return oblist; 
    }

    @Override
    public String search(String titre) throws SQLException {
        String im="";
 ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from news where titre='"+titre+"' ;");
    while(rs.next())
    {
    im=rs.getString("image");
    }
    return im;
    }
        @Override
    public String searchdesc(String titre) throws SQLException {
        String im="";
 ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from news where titre='"+titre+"' ;");
    while(rs.next())
    {
    im=rs.getString("description");
    }
    return im;
    }
            @Override
    public String searchdate(String titre) throws SQLException {
        String im="";
 ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from news where titre='"+titre+"' ;");
    while(rs.next())
    {
    im=rs.getString("datepub");
    }
    return im;
    }
    @Override
    public String searchnomcat(String titre) throws SQLException {
        String im="";
 ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from news where titre='"+titre+"' ;");
    while(rs.next())
    {
    im=rs.getString("nomcat");
    }
    return im;
    }
    public int searchtit(String titre) throws SQLException {
        int im=0;
 ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from news where titre='"+titre+"' ;");
    while(rs.next())
    {
    im++;
    }
    return im;
    }
    
    
    
}
