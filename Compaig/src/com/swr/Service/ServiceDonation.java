/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;
import com.swr.Entite.Donation;
import com.swr.Entite.Compaign;
import com.swr.Entite.User;
import com.swr.IService.IServiceDonation;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.swr.Utils.DataBase;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Monta
 */
public class ServiceDonation implements IServiceDonation<Donation> {

    private Connection con;
    private Statement ste;

     public ServiceDonation() {
        con = DataBase.getInstance().getConnection();
     } 
    
    
    
    @Override
    public void Donate(Donation t) throws SQLException {
     
        
     Compaign C3=new Compaign (t.getC().getId_cmp());
     ServiceCompaign Ser =new ServiceCompaign ();
     boolean WeCanDonate=Ser.GetRaised(C3);
     if (WeCanDonate)  
     {    
        PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`donation` ( `id_don`, `id_user`, `id_cmp`, `monthly`, `message`, `funds`, `given`, `annonym`, `dated`, `timed`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), NOW());");
        pre.setInt(1, t.getIdDon());
        pre.setInt(2, t.getU().getIdu());
        pre.setInt(3, t.getC().getId_cmp());
        pre.setInt(4, t.getMonthly());
        pre.setString(5, t.getMes());
        pre.setInt(6, t.getFunds());
        pre.setDouble(7, t.getGiven());
        pre.setInt(8, t.getAnnonym()); 
       // pre.setDate(9, (Date) t.getDateD()); 
       //pre.setTime(10, (Time) t.getTimeD()); 
        
      if (pre.executeUpdate()!=0)
        {
            //***************     ICI L 'incrementation De Nb Donneur *******************//
            Compaign C2=new Compaign (t.getC().getId_cmp()); 
             Ser.incrementDon(C2);
             PreparedStatement req=con.prepareStatement("update compaign set  raised = raised + ?  WHERE id_cmp = ?");  
             req.setInt(2, t.getC().getId_cmp());
             req.setDouble(1, t.getGiven());

             req.executeUpdate();          // update Raised
            PreparedStatement req1;  
      
            req1 = con.prepareStatement("update compaign set   stillneeded = target - raised  WHERE id_cmp = ?");  
            req1.setInt(1, t.getC().getId_cmp());
            req1.executeUpdate();    // update stillneeded 
         }  
     } } 
          
  

    @Override
    public boolean deleteProp(Donation t) throws SQLException {
       PreparedStatement pre=con.prepareStatement("DELETE FROM donation WHERE id_don = ?");
       pre.setInt(1, t.getIdDon());
        return pre.executeUpdate()!=0;
    
    }

    @Override
    public ObservableList<Donation> readAllProp() throws SQLException {
        ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from donation ");
     while (rs.next()) {  
         
               
          int id_don=rs.getInt(1);
           int id_u=rs.getInt(2);
            int id_cmp=rs.getInt(3);
             int month=rs.getInt(4);
             String ms=rs.getString(5);
             int funds=rs.getInt(6);
             Double given=rs.getDouble(7);
             int anony=rs.getInt(8);
             Date d=rs.getDate(9);
             Time t=rs.getTime(10);
             User u = null;
             u.setId(id_u);
                Compaign c=new Compaign();
                c.setId_cmp(id_cmp);
               Donation p=new Donation(id_don, u, c,month,ms,funds,given,anony,d,t);
     arr.add(p);
     }
    return arr;
    
    }
    
    
     @Override
    public ObservableList<Donation>  rechercheAvCmp(String  t) throws SQLException {

      ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from donation where id_don like '%"+t+"%' or message like '%"+t+"%' or given like '%"+t+"%' or dated like '%"+t+"%'" );
     while (rs.next()) {  
         //  Compaign C1=new Compaign (2,"Monta",45,23,"desc","url",3,4);
                int id_don=rs.getInt(1);
           int id_u=rs.getInt(2);
            int id_cmp=rs.getInt(3);
             int month=rs.getInt(4);
             String ms=rs.getString(5);
             int funds=rs.getInt(6);
             Double given=rs.getDouble(7);
             int anony=rs.getInt(8);
             Date d=rs.getDate(9);
             Time tim=rs.getTime(10);
             
                 User u = new User();
             u.setId(id_u);
                Compaign c=new Compaign();
                c.setId_cmp(id_cmp);
               //Donation p=new Donation(id_don, u, c,month,ms,funds,given,anony,d,t);
               Donation p=new Donation(id_don, u, c,month,ms,funds,given,anony,d,tim);
     arr.add(p);
     }
    return arr;
    
    }     

    @Override
    public ObservableList<Donation> triMaxMes() throws SQLException {
           ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from donation order by MAX(LEN(message)) ");
     while (rs.next()) {  
         
               
          int id_don=rs.getInt(1);
           int id_u=rs.getInt(2);
            int id_cmp=rs.getInt(3);
             int month=rs.getInt(4);
             String ms=rs.getString(5);
             int funds=rs.getInt(6);
             Double given=rs.getDouble(7);
             int anony=rs.getInt(8);
             Date d=rs.getDate(9);
             Time t=rs.getTime(10);
             User u = new User();
             u.setId(id_u);
                Compaign c=new Compaign();
                c.setId_cmp(id_cmp);
               Donation p=new Donation(id_don, u, c,month,ms,funds,given,anony,d,t); 
                
               
     arr.add(p);
     }
    return arr;
    
    }
    
    
        @Override
    public ObservableList<Donation> triMaxGiven() throws SQLException {
           ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from donation order by given desc ");
     while (rs.next()) {  
         
               
          int id_don=rs.getInt(1);
           int id_u=rs.getInt(2);
            int id_cmp=rs.getInt(3);
             int month=rs.getInt(4);
             String ms=rs.getString(5);
             int funds=rs.getInt(6);
             Double given=rs.getDouble(7);
             int anony=rs.getInt(8);
             Date d=rs.getDate(9);
             Time t=rs.getTime(10);
              User u =new User();
             u.setId(id_u);
                Compaign c=new Compaign();
                c.setId_cmp(id_cmp);
               Donation p=new Donation(id_don, u, c,month,ms,funds,given,anony,d,t);
                
              
     arr.add(p);
     }
    return arr;
    
    }

    @Override
    public ObservableList<Donation> MaxGiven() throws SQLException {
  ObservableList arr= FXCollections.observableArrayList();    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from donation order by MAX given  ");
     while (rs.next()) {  
         
               
          int id_don=rs.getInt(1);
           int id_u=rs.getInt(2);
            int id_cmp=rs.getInt(3);
             int month=rs.getInt(4);
             String ms=rs.getString(5);
             int funds=rs.getInt(6);
             Double given=rs.getDouble(7);
             int anony=rs.getInt(8);
             Date d=rs.getDate(9);
             Time t=rs.getTime(10);
              User u = null;
             u.setId(id_u);
                Compaign c=null;
                c.setId_cmp(id_cmp);
               Donation p=new Donation(id_don, u, c,month,ms,funds,given,anony,d,t);
                
               
     arr.add(p);
     }
    return arr;
    
    
    }
    
    
}
