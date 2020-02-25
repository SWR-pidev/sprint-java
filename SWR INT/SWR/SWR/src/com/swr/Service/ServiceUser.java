/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;
import com.swr.Entite.User;
import com.swr.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.swr.Utils.DataBase;
import java.util.ArrayList;
import java.util.Random;
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
    public User listuser(String us) throws SQLException{
          User x=new User();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from user where username='" + us + "' ;");
           while (rs.next()) {                
               int idu=rs.getInt(1);
                
               String prenom=rs.getString("prenom");
               String nom=rs.getString("nom");
               
               
               x=new User(idu,nom,prenom);
 
     }
        return x;
    }

    @Override
    public void ajouter(User u) throws SQLException {
        Random rand = new Random();
            int alea=rand.nextInt(200000 - 10 + 1) + 200000;
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`user` (`idu`, `nom`, `prenom`, `country`, `email`, `password`, `tel`, `username`, `roles`, `dateins`, `radom`, `image`) VALUES (NULL, '" + u.getNom() + "', '" + u.getPrenom() + "', '" + u.getCountry() + "', '" + u.getMail() + "', '" + u.getPwd() + "', '" + u.getTel() + "', '" + u.getUsername() + "', '" + u.getRoles() +"',sysdate(),'"+alea+"', '"+ u.getImage()+"');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(String nom) throws SQLException {
    
        PreparedStatement pre=con.prepareStatement("DELETE FROM user WHERE username='"+nom+"' ;");
        pre.executeUpdate();
     
        return true;
    }
    @Override
    public boolean deletemail(String nom) throws SQLException {
    
        PreparedStatement pre=con.prepareStatement("DELETE FROM user WHERE email='"+nom+"' ;");
        pre.executeUpdate();
        
        return true;
    }
    
    public boolean update(User u,String id) throws SQLException {
PreparedStatement pre=con.prepareStatement("UPDATE user SET nom= '" +u.getNom()+ "',prenom='"+u.getPrenom()+"',country='"+u.getCountry()+"',email='"+u.getMail()+"',password='"+u.getPwd()+"',tel='"+u.getTel()+"',username='"+u.getUsername()+"' WHERE id='"+id+"' ;");
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
               String image=rs.getString("image");
               User u=new User(nom, prenom, country,mail,pwd,tel,username,roles,dateins,image);
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
    public ObservableList<User> rechercheentredate(String d1, String d2) throws SQLException {
    ObservableList<User> arr=FXCollections.observableArrayList();
    ste=con.createStatement();
    String sql="select * from user";
    if((!d1.isEmpty())&&(!d2.isEmpty()))
    {
        sql="select * from user where dateins between '" + d1 + "' AND '" + d2 + "'";
    }
   
    ResultSet rs=ste.executeQuery(sql);
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
    ResultSet rs=ste.executeQuery("SELECT radom FROM `user` WHERE email='"+mail+"' ;");
    while (rs.next()) { 
    a=rs.getString("radom");
    }
    return a;
    }

    public boolean update(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modify(User u, String id) throws SQLException {
    PreparedStatement pre=con.prepareStatement("UPDATE user SET nom= '" +u.getNom()+ "',prenom='"+u.getPrenom()+"',country='"+u.getCountry()+"',password='"+u.getPwd()+"',tel='"+u.getTel()+"',image='"+u.getImage()+"' WHERE username='"+id+"' ;");
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
    public String getaUser(String mail) throws SQLException {
    String a="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE email='"+mail+"' AND roles='admin' ;");
    while (rs.next()) { 
    a=rs.getString("username");
    }
    return a;
    }
        public String getImage(String mail) throws SQLException {
    String a="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE email='"+mail+"' ;");
    while (rs.next()) { 
    a=rs.getString("image");
    }
    return a;
    }
                public String getImageu(String mail) throws SQLException {
    String a="";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE username='"+mail+"' ;");
    while (rs.next()) { 
    a=rs.getString("image");
    }
    return a;
    }
  public int getTel(String tel) throws SQLException {
    int a=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE tel='"+tel+"' ;");
    while (rs.next()) { 
    a++;
    }
    return a;
    }
    public int getUs(String tel) throws SQLException {
    int a=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE username='"+tel+"' ;");
    while (rs.next()) { 
    a++;
    }
    return a;
    }
    public int getma(String tel) throws SQLException {
    int a=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE email='"+tel+"' ;");
    while (rs.next()) { 
    a++;
    }
    return a;
    }
    
    @Override
    public int affichiter(String mail) throws SQLException {
       
ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user WHERE email='"+mail+"';");
    if(rs.next())
    {
    return rs.getInt("iteration");
    }
    else{
    return rs.getInt("iteration");
    }
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
    String image=rs.getString("image");
    
    User u= new User(nom,prenom,country,pwd,tel,image);
    return u;
    }
    return null;
    }

    @Override
    public int preauth(String mail, String pwd) throws SQLException {
int i=0;
int h=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM user WHERE email='"+mail+"'AND password='"+pwd+"' ;");
        while (rs.next()) {
              int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               String country=rs.getString("country");
               String tel=rs.getString(7);
               String username=rs.getString("username");
               String roles=rs.getString("roles");
               if("user".equals(roles))
               {i++;}
               if("admin".equals(roles))
               {h++;}
     }
        if(i==1)
        {
        return 1;
        }
        else if(h==1)
        {return 2;}
        else{
        return 0;
        }
    }

    @Override
    public int getCode(String code) throws SQLException {
        int i=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE radom='"+code+"' ;");
    while (rs.next()) { 
    i++;
    }
    if(i!=0)
    {
    return i=1;
    }
    else
    {
    return 0;
    }
    }

    @Override
    public void updateiter(String mail) throws SQLException {
PreparedStatement pre=con.prepareStatement("UPDATE user SET iteration=iteration-1 WHERE email='"+mail+"' ;");
pre.executeUpdate();
    }
    @Override
    public void updatepass(String mail,String pass) throws SQLException {
PreparedStatement pre=con.prepareStatement("UPDATE user SET password='"+pass+"' WHERE email='"+mail+"' ;");
pre.executeUpdate();
    }
    @Override
    public void resetiter(String mail) throws SQLException {
PreparedStatement pre=con.prepareStatement("UPDATE user SET iteration=3 WHERE email='"+mail+"' ;");
pre.executeUpdate();
    }
        @Override
    public ResultSet barchart() throws SQLException {
ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT dateins , count(*) day from user group by dateins ;");
    return rs;
    }

    

  


   

    
    
    
}
