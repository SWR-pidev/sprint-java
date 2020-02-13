
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Deliveryman;
import IService.Iservice;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import utils.Database;
import java.util.ArrayList;

/**
 *
 * @author House
 */
public class ServiceDman implements Iservice<Deliveryman> {

    private Connection con;
    private Statement ste;

    public ServiceDman() {
        con = Database.getInstance().getConnection();

    }

    @Override
    public void ajouter(Deliveryman t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`deliveryman` (`Fname`, `Lname` , `Pnum`, `mail`, `numD`) VALUES ('" + t.getFname() + "', '" + t.getLname() + "', '" + t.getmail()+ "', '" + t.getPnum() +"');";
        ste.executeUpdate(requeteInsert);
    }
    
    public void ajouter1(Deliveryman t ) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`deliveryman` (`Fname`,`Lname`,`Pnum`,`mail`) VALUES (?,?,?,?);");
    pre.setString(1, t.getFname());
    pre.setString(2, t.getLname());
    pre.setInt(3, t.getPnum());
    pre.setString(4, t.getmail());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Deliveryman t) throws SQLException {
  PreparedStatement pre=con.prepareStatement("DELETE FROM deliveryman WHERE idDman= ?");
       pre.setInt(1, t.getidDman());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }

    
    @Override
    public boolean update(Deliveryman t) throws SQLException {
 PreparedStatement pre=con.prepareStatement("update deliveryman set Fname = ?, Lname = ?, Pnum = ?, mail = ?, numD = ? WHERE idDman = ?");
   
    pre.setString(1, t.getFname());
    pre.setString(2, t.getLname());
    pre.setInt(3, t.getPnum());
    pre.setString(4, t.getmail());
    pre.setInt(5, t.getnumD());
    pre.setInt(6, t.getidDman());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }
    
    @Override
    public List<Deliveryman> readAll() throws SQLException {
    List<Deliveryman> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Deliveryman");
     while (rs.next()) {                
               int idDman=rs.getInt(1);
               String Fname=rs.getString("Fname");
               String Lname=rs.getString("Lname");
               String mail=rs.getString("mail");
               int idDel=rs.getInt("idDel");
               int Pnum=rs.getInt("Pnum");
               int numD=rs.getInt("numD");
              
     }
    return arr;
    }
 public List<Deliveryman> trifirst() throws SQLException {
    List<Deliveryman> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Deliveryman order by Fname");
     while (rs.next()) {                
               int idDman=rs.getInt(1);
               String Fname=rs.getString("Fname");
               String Lname=rs.getString("Lname");
               String mail=rs.getString("mail");
               int idDel=rs.getInt("idDel");
               int Pnum=rs.getInt("Pnum");
               int numD=rs.getInt("numD");
              
     }
    return arr;
    }
     public List<Deliveryman> trilast() throws SQLException {
    List<Deliveryman> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Deliveryman order by Lname");
     while (rs.next()) {                
               int idDman=rs.getInt(1);
               String Fname=rs.getString("Fname");
               String Lname=rs.getString("Lname");
               String mail=rs.getString("mail");
               int idDel=rs.getInt("idDel");
               int Pnum=rs.getInt("Pnum");
               int numD=rs.getInt("numD");
              
     }
    return arr;
    }
    
  
}
