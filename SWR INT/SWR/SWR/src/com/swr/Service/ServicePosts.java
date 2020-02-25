/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;


//import com.email.durgesh.Email;
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
    public class ServicePosts implements IService1<Posts> {

    private final Connection con;
    private Statement ste;

    public ServicePosts() {
        con = DataBase.getInstance().getConnection();

    }

  /*  @Override
    public void ajouter(Posts t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`posts` (`idP`, `contenuP`, `nbcmt`, `views`, `likes`, `report`, `iduser`) VALUES (NULL, '" + t.getContenuP() + "', '" +t. getNbcmt() + "', '" + t.getViews() + "', '" + t.getLikes() + "', '" + t.getReport() + "', '" + t.getIduser() + "');";
        ste.executeUpdate(requeteInsert);
    }*/
    public void ajouter1(Posts p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`posts` ( `contenuP`, `nbcmt`, `views`, `likes`, `report`, `iduser`, `dateP`) VALUES ( ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP);");
    pre.setString(1, p.getContenuP());
    pre.setInt(2, p.getNbcmt());
    pre.setInt(3, p.getViews());
    pre.setInt(4, p.getLikes());
    pre.setInt(5, p.getReport());
    pre.setInt(6, p.getIduser());

    pre.executeUpdate();
    }
            
    
    @Override
    public boolean delete(Posts p) throws SQLException {
         /*ste = con.createStatement();
        String requeteInsert = "delete from posts where contenuP = alo " ;
        ste.executeUpdate(requeteInsert);*/
          PreparedStatement pre=con.prepareStatement("delete from posts where contenuP = '" + p.getContenuP() + "' ;");
            pre.executeUpdate();
            return true;
    }
     public boolean delete1(int a) throws SQLException {
        
          PreparedStatement pre=con.prepareStatement("delete from posts where idP = '" + a + "' ;");
            pre.executeUpdate();
            return true;
    }

    public boolean update(int x,String a) throws SQLException {
       /* String sql = "UPDATE posts SET contenuP=? WHERE Iduser=2";
 
       PreparedStatement statement = con.prepareStatement(sql);
       statement.setString(1, a);*/
        PreparedStatement pre=con.prepareStatement("UPDATE posts SET contenuP= '" + a + "'  WHERE idP= '" + x + "'  ;");
            pre.executeUpdate();
       
        return true;
    }

   @Override
    public ObservableList<Posts> readAll() throws SQLException {
ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from posts");
     while (rs.next()) {                
               int idP=rs.getInt("idP");
               String contenuP=rs.getString("contenuP");
               User u=new User();
               int nbcmt=rs.getInt("nbcmt");
               int views=rs.getInt("views");
               int likes=rs.getInt("likes");
               int report=rs.getInt("report");
               int iduser=rs.getInt("iduser");
             u.setId(iduser);
               Date dateP=rs.getDate("dateP");
               
               
               Posts p=new Posts(idP, contenuP, nbcmt, views, likes, report,u,dateP);
     arr.add(p);
     }
    return arr;
    }
        public ObservableList<Posts> list(int id) throws SQLException {
ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from posts where idP= '" + id + "'");
     while (rs.next()) {                
               int idP=rs.getInt("idP");
               String contenuP=rs.getString("contenuP");
               User u=new User();
               int nbcmt=rs.getInt("nbcmt");
               int views=rs.getInt("views");
               int likes=rs.getInt("likes");
               int report=rs.getInt("report");
               int iduser=rs.getInt("iduser");
             u.setId(iduser);
               Date dateP=rs.getDate("dateP");
               
               
               Posts p=new Posts(idP, contenuP, nbcmt, views, likes, report,u,dateP);
     arr.add(p);
     }
    return arr;
    }

    @Override
    public void ajouter(Posts t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Posts t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ObservableList<Posts> recherche(String aa) throws SQLException{
         ObservableList<Posts> fish=FXCollections.observableArrayList();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from posts where contenuP like '%" + aa + "%' ;");
           while (rs.next()) {     
                User u=new User();
               int idP=rs.getInt(1);
               String contenuP=rs.getString("contenuP");
               int nbcmt=rs.getInt("nbcmt");
               int views=rs.getInt("views");
               int likes=rs.getInt("likes");
               int report=rs.getInt("report");
               int iduser=rs.getInt("iduser");
               u.setId(iduser);
               Date dateP=rs.getDate("dateP");
               
               
               Posts p=new Posts(idP, contenuP, nbcmt, views, likes, report,u,dateP);
     fish.add(p);
     }
        return fish;
    }
    
    public ObservableList<Posts> trieln() throws SQLException{
         ObservableList<Posts> fish=FXCollections.observableArrayList();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from posts order by length(contenuP) desc;");
           while (rs.next()) {    
                User u=new User();
               int idP=rs.getInt(1);
               String contenuP=rs.getString("contenuP");
               int nbcmt=rs.getInt("nbcmt");
               int views=rs.getInt("views");
               int likes=rs.getInt("likes");
               int report=rs.getInt("report");
               int iduser=rs.getInt("iduser");
               u.setId(iduser);
               Date dateP=rs.getDate("dateP");
               
               
               Posts x=new Posts(idP, contenuP, nbcmt, views, likes, report,u,dateP);
     fish.add(x);
     }
        return fish;
    }
    
    
     public ObservableList<Integer> listid() throws SQLException{
         ObservableList<Integer> fish=FXCollections.observableArrayList();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select idP from posts ;");
           while (rs.next()) {                
               int idP=rs.getInt(1);
           
               
               
            //   Posts x=new Posts(idP);
     fish.add(idP);
     }
        return fish;
    }
     
    public boolean like(int x) throws SQLException {
  
        PreparedStatement pre=con.prepareStatement("UPDATE posts SET likes= likes + 1  WHERE idP='" + x + "' ;");
            pre.executeUpdate();
       
        return true;
    }
     public boolean views(int x) throws SQLException {
       
        PreparedStatement pre=con.prepareStatement("UPDATE posts SET views= views + 1   WHERE idP='" + x + "'  ;");
            pre.executeUpdate();
       
        return true;
    }
   /*  
         public void sendmail(){
    try {
        Email email = new Email("skalah77@gmail.com","01010202s");
        email.setFrom("skalah77@gmail.com", "Solidarity With Refugees");
        email.setSubject("this is for testing");
        email.setContent("<h1>You have your first comments on your post</h1>", "text/html");
        email.addRecipient("salahddine.benhamida@esprit.tn");
        email.send();
    
         } catch (Exception e) 
             
             { e.printStackTrace();}		  
    System.out.println("Mail Sended"); 
    }*/
   
         public int countid() throws SQLException
         {
              int x=0;
             ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from posts where views < 10;");
         while (rs.next()) {
             x++;
         }
         return x;
         }
   
    /**
     *
     * @return
     * @throws SQLException
     */
    public int countgt0() throws SQLException{
          int x=0;
              List<Integer> a=new ArrayList<>();
             ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from posts where views > 10 ;");
         while (rs.next()) {
            // int count=rs.getInt("count");
             
            x++;
         }
         return x;
         }
     public int countAll() throws SQLException{
          int x=0;
              List<Integer> a=new ArrayList<>();
             ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from posts ;");
         while (rs.next()) {
            // int count=rs.getInt("count");
             
            x++;
         }
         return x;
         }
     
       public User listuser(int id) throws SQLException{
          User x=new User();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from user where idu='" + id + "' ;");
           while (rs.next()) {                
               int idu=rs.getInt(1);
                
               String prenom=rs.getString("prenom");
               String nom=rs.getString("nom");
               
               
               x=new User(idu,nom,prenom);
 
     }
        return x;
    }
       
        public boolean deleteX(int x,int a) throws SQLException {
        
            PreparedStatement pre=con.prepareStatement("delete from posts where idP = '" + x + "' and iduser = '" + a + "'  ;");
            pre.executeUpdate();
            return true;    }
        

         public ObservableList<Posts> slctd(int a,int z) throws SQLException{
         ObservableList<Posts> fish=FXCollections.observableArrayList();
         ste=con.createStatement();
        ResultSet rs=ste.executeQuery("Select * from posts where idP = '" + a + "' and iduser='" + z + "' ;");
           while (rs.next()) {    
                User u=new User();
               int idP=rs.getInt(1);
               String contenuP=rs.getString("contenuP");
               int nbcmt=rs.getInt("nbcmt");
               int views=rs.getInt("views");
               int likes=rs.getInt("likes");
               int report=rs.getInt("report");
               int iduser=rs.getInt("iduser");
               u.setId(iduser);
               Date dateP=rs.getDate("dateP");
               
               
               Posts x=new Posts(idP, contenuP, nbcmt, views, likes, report,u,dateP);
     fish.add(x);
     }
        return fish;
    }
         
             public boolean Modifiy(Posts p,int x) throws SQLException {
        
             PreparedStatement pre=con.prepareStatement("UPDATE posts SET contenuP= '" + p.getContenuP() + "'  WHERE idP= '" + x + "' and iduser= '" + p.getIduser() + "' ;");
               pre.executeUpdate();
       
        return true;
    

        }
              public boolean nbcmtPlus(int x) throws SQLException {
       
        PreparedStatement pre=con.prepareStatement("UPDATE posts SET nbcmt= nbcmt + 1   WHERE idP='" + x + "'  ;");
            pre.executeUpdate();
       
        return true;
    }
      public boolean nbcmtDown(int x) throws SQLException {
       
        PreparedStatement pre=con.prepareStatement("UPDATE posts SET nbcmt= nbcmt - 1   WHERE idP='" + x + "'  ;");
            pre.executeUpdate();
       
        return true;
    }

  
}
    

