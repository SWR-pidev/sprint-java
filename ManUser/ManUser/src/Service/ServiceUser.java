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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
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
        String requeteInsert = "INSERT INTO `swr`.`user` (`idu`, `nom`, `prenom`, `country`, `email`, `password`, `tel`, `username`, `roles`, `dateins`) VALUES (NULL, '" + u.getNom() + "', '" + u.getPrenom() + "', '" + u.getCountry() + "', '" + u.getMail() + "', '" + u.getPwd() + "', '" + u.getTel() + "', '" + u.getUsername() + "', '" + u.getRoles() +"',sysdate());";
        ste.executeUpdate(requeteInsert);
        JOptionPane.showMessageDialog(null,"User ajouté avec succées");
    }

    @Override
    public boolean delete(String nom) throws SQLException {
    
        PreparedStatement pre=con.prepareStatement("DELETE FROM user WHERE username='"+nom+"' ;");
        pre.executeUpdate();
        JOptionPane.showMessageDialog(null,"User supprimé avec succées");
        return true;
    }
    
    public boolean update(User u,String id) throws SQLException {
PreparedStatement pre=con.prepareStatement("UPDATE user SET nom= '" +u.getNom()+ "',prenom='"+u.getPrenom()+"',country='"+u.getCountry()+"',email='"+u.getMail()+"',password='"+u.getPwd()+"',tel='"+u.getTel()+"',username='"+u.getUsername()+"' WHERE id='"+id+"' ;");
JOptionPane.showMessageDialog(null,"User modifié avec succées");
            pre.executeUpdate();
       
        return true;    }

    @Override
    public ObservableList<User> readAll() throws SQLException {
    ObservableList oblist = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               String country=rs.getString("country");
               String mail=rs.getString("email");
               String pwd=rs.getString("password");
               String tel=rs.getString(7);
               String username=rs.getString("username");
               String roles=rs.getString("roles");
               String dateins=rs.getString("dateins");
               User u=new User(nom, prenom, country,mail,pwd,tel,username,roles,dateins);
     oblist.add(u);
     }
    return oblist;
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
               String tel=rs.getString(7);
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
               String tel=rs.getString(7);
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
               String tel=rs.getString(7);
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

    @Override
    public int auth(String mail, String pwd) throws SQLException {
        int i=0,k=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM user WHERE email='"+mail+"' ;");
        while (rs.next()) {
            String crypted = rs.getString("password");
                if (Servicebcrypt.checkpw(pwd, crypted)) {
                i++;    
                }
            
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               String country=rs.getString("country");
               String tel=rs.getString(7);
               String username=rs.getString("username");
               String roles=rs.getString("roles");
               if("user".equals(roles))
               {k++;}
     }
        if(i==1 && k==1)
        {
        return 1;
        }
        else if(i==1 && k==0)
        {
        return 2;
        }
        else{
        return 0;
        }
    }

    @Override
    public String forgotpasss(String mail) throws SQLException {
    String a="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE email='"+mail+"' ;");
    while (rs.next()) { 
    a=rs.getString("password");
    }
    return a;
    }

    public boolean update(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modify(User u, String id) throws SQLException {
    PreparedStatement pre=con.prepareStatement("UPDATE user SET nom= '" +u.getNom()+ "',prenom='"+u.getPrenom()+"',country='"+u.getCountry()+"',password='"+u.getPwd()+"',tel='"+u.getTel()+"' WHERE username='"+id+"' ;");
    JOptionPane.showMessageDialog(null,"User modifié avec succées");
    pre.executeUpdate();
       
        return true;      }

    @Override
    public String getUser(String mail) throws SQLException {
    String a="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE email='"+mail+"' ;");
    while (rs.next()) { 
    a=rs.getString("username");
    }
    return a;
    }

    @Override
    public User SerachUser(String username) throws SQLException {
    
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE username='"+username+"' ;");
    while (rs.next()) { 
    String a=rs.getString("username");
    String nom=rs.getString("nom");
    String prenom=rs.getString(3);
    String country=rs.getString("country");
    String tel=rs.getString(7);
    String pwd=rs.getString("password");
    User u= new User(nom,prenom,country,pwd,tel);
    return u;
    }
    return null;
    }

  


   

    
    
    
}
