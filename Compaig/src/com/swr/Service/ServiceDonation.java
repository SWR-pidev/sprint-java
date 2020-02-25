/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;
import com.swr.Entite.Donation;
import com.swr.Entite.Compaign;
import com.swr.Entite.User;
import com.swr.GUI.Front.DonateController;
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
     System.out.println("We can Donate "+WeCanDonate);
     if (WeCanDonate)  
     {    
        PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`donation` ( `id_user`, `id_cmp`, `monthly`, `message`, `funds`, `given`, `annonym`, `dated`, `timed`) VALUES ( ?, ?, ?, ?, ?, ?, ?, CURDATE(), NOW());");
        //pre.setInt(1, t.getIdDon());
        pre.setInt(1, t.getU().getIdu());
        pre.setInt(2, t.getIdc());
        pre.setInt(3, t.getMonthly());
        pre.setString(4, t.getMes());
        pre.setInt(5, t.getFunds());
        pre.setDouble(6, t.getGiven());
        pre.setInt(7, t.getAnnonym()); 
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
     } 
    } 
          
  

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
             User u = new User();
             u.setId(id_u);
                Compaign c=new Compaign();
                 
                
                c.setId_cmp(id_cmp);
               Donation p=new Donation(id_don, u, c,month,ms,funds,given,anony,d,t);
               p.setIdc(id_cmp);
               p.setIdu(id_u);
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
    
    public ObservableList<Donation> jointure(int id) throws SQLException {
        ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select u.nom,u.prenom,d.given from user u INNER JOIN donation d on u.idu=d.id_user where d.id_cmp='"+id+"';");
     while (rs.next()) {  
 
             String ms1=rs.getString(1);
              String ms2=rs.getString(2);
                 Double given=rs.getDouble(3);   
               Donation d=new Donation(ms2,ms1,given);
   
     arr.add(d);
     }
    return arr;
    
    }

public ObservableList<Donation> recentdon(int id) throws SQLException {
        ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select u.nom,u.prenom,d.given,d.annonym,d.message from user u INNER JOIN donation d on u.idu=d.id_user where d.id_cmp='"+id+"'order by d.dated,d.timed ;");
     while (rs.next()) {  
 
             String ms1=rs.getString(1);
              String ms2=rs.getString(2);
                 Double given=rs.getDouble(3);   
                 int ano=rs.getInt(4);
                 String ms=rs.getString(5);
               Donation d=new Donation(ms2,ms1,given,ano,ms);
   
     arr.add(d);
     }
    return arr;
    
    }

public int totalcmp() throws SQLException {
  int ms1 = 0;       
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select  COUNT(*) FROM compaign; ");
     while (rs.next()) {  
           ms1=rs.getInt(1);
     }
      
    return ms1;
    
    }

public int statcmp() throws SQLException {
  int ms1 = 0;       
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select  COUNT(*) FROM compaign where nbdon>3; ");
     while (rs.next()) {  
           ms1=rs.getInt(1);
     }
      
    return ms1;
    
    }
 
}
