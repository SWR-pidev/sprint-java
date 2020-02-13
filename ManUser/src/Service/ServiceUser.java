/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entite.User;
import IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Asus X550V
 */
public class ServiceUser implements IService<User> {
    private Connection con;
    private Statement ste;
    
    public ServiceUser()
    {
    con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(User u) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`user` (`idu`, `nom`, `prenom`, `country`, `email`, `password`, `tel`, `username`, `roles`, `dateins`) VALUES (NULL, '" + u.getNom() + "', '" + u.getPrenom() + "', '" + u.getCountry() + "', '" + u.getMail() + "', '" + u.getPwd() + "', '" + u.getTel() + "', '" + u.getUsername() + "', '" + u.getRoles() +"', '" + u.getDateins() + "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(String nom) throws SQLException {
    
        PreparedStatement pre=con.prepareStatement("DELETE FROM user WHERE nom='"+nom+"' ;");
        pre.executeUpdate();
        return true;
    }

    @Override
    public boolean update(String n1,String ma,String pw) throws SQLException {
PreparedStatement pre=con.prepareStatement("UPDATE user SET email= '" +ma+ "',password='"+pw+"' WHERE nom='"+n1+"' ;");
            pre.executeUpdate();
       
        return true;    }

    @Override
    public List<User> readAll() throws SQLException {
    List<User> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               String country=rs.getString("country");
               String mail=rs.getString("email");
               String pwd=rs.getString("password");
               int tel=rs.getInt(7);
               String username=rs.getString("username");
               String roles=rs.getString("roles");
               String dateins=rs.getString("dateins");
               User u=new User(id, nom, prenom, country,mail,pwd,tel,username,roles,dateins);
     arr.add(u);
     }
    return arr;
    }
    
    @Override
    public List<User> rechercheavance(String n) throws SQLException
    {
    List<User> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user where username like '%" + n + "%' ;");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               String country=rs.getString("country");
               String mail=rs.getString("email");
               String pwd=rs.getString("password");
               int tel=rs.getInt(7);
               String username=rs.getString("username");
               String roles=rs.getString("roles");
               String dateins=rs.getString("dateins");
               User u=new User(id, nom, prenom, country,mail,pwd,tel,username,roles,dateins);
     arr.add(u);
     }
    return arr;
    }

    @Override
    public List<User> tri() throws SQLException {
     List<User> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user ORDER BY country asc");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               String country=rs.getString("country");
               String mail=rs.getString("email");
               String pwd=rs.getString("password");
               int tel=rs.getInt(7);
               String username=rs.getString("username");
               String roles=rs.getString("roles");
               String dateins=rs.getString("dateins");
               User u=new User(id, nom, prenom, country,mail,pwd,tel,username,roles,dateins);
     arr.add(u);
     }
    return arr;
    }

    @Override
    public List<User> rechercheentredate(String d1, String d2) throws SQLException {
    List<User> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user where dateins between '" + d1 + "' AND '" + d2 + "' ;");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               String country=rs.getString("country");
               String mail=rs.getString("email");
               String pwd=rs.getString("password");
               int tel=rs.getInt(7);
               String username=rs.getString("username");
               String roles=rs.getString("roles");
               String dateins=rs.getString("dateins");
               User u=new User(id, nom, prenom, country,mail,pwd,tel,username,roles,dateins);
     arr.add(u);
     }
    return arr;
    }

    @Override
    public int moyenne() throws SQLException {
    ste=con.createStatement();
    int i=0;
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE DATEDIFF(dateins,sysdate())=0");
     while (rs.next()) { 
         i++;
     }
    return i;
    }
    
    
}
