/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;
import com.email.durgesh.Email;
import com.swr.Entite.Compaign;
import com.swr.IService.IServiceCompaign;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.swr.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Monta
 */
public class ServiceCompaign implements IServiceCompaign<Compaign>{
 
    private Connection con;
    private Statement ste;

    
     public ServiceCompaign() {
        con = DataBase.getInstance().getConnection();
     }   

    public void SendMail (){

try {
        Email email = new Email("montassar43@gmail.com","montassar007");
        email.setFrom("montassar43@gmail.com", "Solidarity With Refugees");
        email.setSubject("this is for testing");
        email.setContent("<h1> Mail Recevied </h1><h2> Mail Recevied </h2>", "text/html");
        email.addRecipient("montassar.rebai@esprit.tn");
        email.send();
    
         } catch (Exception e)
             
             { e.printStackTrace();}
    System.out.println("Mail Sended");
} 
     
    @Override
    public void ajouterCmp(Compaign t) throws SQLException {
   //   List<String> LIemails=new ArrayList<>();
        
    PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`compaign` ( `id_cmp`, `name_cmp`, `target`, `raised`, `descrip`, `urlimg`, `nbdon`, `stillneeded`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);");
    pre.setInt(1, t.getId_cmp());
    pre.setString(2, t.getNamecmp());
    pre.setDouble(3, t.getTrgt());
    pre.setDouble(4, t.getTrgt());
    pre.setString(5, t.getDesc());
    pre.setString(6, t.getUrl());
    pre.setInt(7, t.getNbdon());
    pre.setDouble(8, t.getStillneeded()); 
    pre.executeUpdate();
    ste=con.createStatement();
   /*  ResultSet rs=ste.executeQuery("select email from user ");
      while (rs.next()) {  
      String mail=rs.getString(1);
     LIemails.add(mail); }*/
     SendMail();
    }

    @Override
    public boolean deleteCmp(Compaign t) throws SQLException {
             PreparedStatement pre=con.prepareStatement("DELETE FROM compaign WHERE id_cmp = ?");
       pre.setInt(1, t.getId_cmp());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }

    @Override
    public boolean updateCmp(Compaign t) throws SQLException {
     
    PreparedStatement pre=con.prepareStatement("update compaign set name_cmp = ? ,target = ? , raised = ? , descrip = ? , urlimg = ? , nbdon = ? , stillneeded = ?  WHERE id_cmp = ?");
   
    pre.setString(1, t.getNamecmp());
    pre.setDouble(2, t.getTrgt());
    pre.setDouble(3, t.getRaised());
    pre.setString(4, t.getDesc());
    pre.setString(5, t.getUrl());
    pre.setInt(6, t.getNbdon());
    pre.setDouble(7, t.getStillneeded()); 
    pre.setInt(8, t.getId_cmp());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }

    @Override
    public ObservableList<Compaign> readAllCmp() throws SQLException {

       ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from compaign");
     while (rs.next()) {  
         //  Compaign C1=new Compaign (2,"Monta",45,23,"desc","url",3,4);
                int id=rs.getInt(1);
                String nom=rs.getString(2);
                Double trg=rs.getDouble(3);
                Double raised=rs.getDouble(4); 
                String desc=rs.getString(5);
                String url=rs.getString(6);
                 int nbd=rs.getInt(7);
               Double needed=rs.getDouble(8);   
               Compaign p=new Compaign(id, nom, trg, raised,desc,url,nbd,needed);
     arr.add(p);
     }
    return arr;
    
    }
    
    
    @Override
    public ObservableList<Compaign>  triNomCmp() throws SQLException {

       ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from compaign order by name_cmp");
     while (rs.next()) {  
         //  Compaign C1=new Compaign (2,"Monta",45,23,"desc","url",3,4);
                int id=rs.getInt(1);
                String nom=rs.getString(2);
                Double trg=rs.getDouble(3);
                Double raised=rs.getDouble(4); 
                String desc=rs.getString(5);
                String url=rs.getString(6);
                 int nbd=rs.getInt(7);
               Double needed=rs.getDouble(8);   
               Compaign p=new Compaign(id, nom, trg, raised,desc,url,nbd,needed);
     arr.add(p);
     }
    return arr;
    
    }
    
 @Override
    public ObservableList<Compaign>  trinbdonCmp() throws SQLException {

     ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from compaign order by nbdon desc");
     while (rs.next()) {  
         //  Compaign C1=new Compaign (2,"Monta",45,23,"desc","url",3,4);
                int id=rs.getInt(1);
                String nom=rs.getString(2);
                Double trg=rs.getDouble(3);
                Double raised=rs.getDouble(4); 
                String desc=rs.getString(5);
                String url=rs.getString(6);
                 int nbd=rs.getInt(7);
               Double needed=rs.getDouble(8);   
               Compaign p=new Compaign(id, nom, trg, raised,desc,url,nbd,needed);
     arr.add(p);
     }
    return arr;
    
    }    
    
     @Override
    public ObservableList<Compaign>  triStillNeeded() throws SQLException {

      ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from compaign order by stillneeded desc");
     while (rs.next()) {  
         //  Compaign C1=new Compaign (2,"Monta",45,23,"desc","url",3,4);
                int id=rs.getInt(1);
                String nom=rs.getString(2);
                Double trg=rs.getDouble(3);
                Double raised=rs.getDouble(4); 
                String desc=rs.getString(5);
                String url=rs.getString(6);
                 int nbd=rs.getInt(7);
               Double needed=rs.getDouble(8);   
               Compaign p=new Compaign(id, nom, trg, raised,desc,url,nbd,needed);
     arr.add(p);
     }
    return arr;
    
    }    
    
    
    
    @Override
    public ObservableList<Compaign>  rechercheAvCmp(String  t) throws SQLException {

        ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from compaign where name_cmp like '%"+t+"%' or descrip like '%"+t+"%'" );
     while (rs.next()) {  
         //  Compaign C1=new Compaign (2,"Monta",45,23,"desc","url",3,4);
                int id=rs.getInt(1);
                String nom=rs.getString(2);
                Double trg=rs.getDouble(3);
                Double raised=rs.getDouble(4); 
                String desc=rs.getString(5);
                String url=rs.getString(6);
                 int nbd=rs.getInt(7);
               Double needed=rs.getDouble(8);   
               Compaign p=new Compaign(id, nom, trg, raised,desc,url,nbd,needed);
     arr.add(p);
     }
    return arr;
    
    }     
    
     @Override
     public boolean  GetRaised(Compaign t) throws SQLException {

      List<Compaign> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select raised,target from compaign where name_cmp ='"+t.getId_cmp()+"'" );
     while (rs.next()) {  
         //  Compaign C1=new Compaign (2,"Monta",45,23,"desc","url",3,4);
               
                Double trg=rs.getDouble(2);
                Double raised=rs.getDouble(1); 
               
               //Compaign p=new Compaign(id, nom, trg, raised,desc,url,nbd,needed);
     //arr.add(p);
                if (trg>raised)
                    return true;
     }
    
    return false; 
    }     
       
    
 @Override
    public boolean incrementDon(Compaign t) throws SQLException {
     
    PreparedStatement pre=con.prepareStatement("update compaign set   nbdon = nbdon+1  WHERE id_cmp = ?");
   
    pre.setInt(1,t.getId_cmp());
   
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }    
    
    
    
}
