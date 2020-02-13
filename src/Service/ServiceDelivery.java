/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.delivery;
import Iservice.Iservice;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import utils.Database;

/**
 *
 * @author House
 */
public class ServiceDelivery implements Iservice<delivery> {

    private Connection con;
    private Statement ste;

    public ServiceDelivery() {
        con = Database.getInstance().getConnection();

    }

    public void ajouter(delivery p) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`Delivery` (`DateD`) VALUES (NULL, '" + p.getDateD() + "');";
        ste.executeUpdate(requeteInsert);
    }
    
   public void ajouter1(delivery p ) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `delivery` (`idDman`, `DateD`, `idItem`) VALUES  (?,?,?);");
    pre.setInt(1, p.getIdDman());
    pre.setString(2, p.getDateD());
     pre.setInt(3, p.getIdItem());
    
    pre.executeUpdate();
    }
            

    
    public List<delivery> readAll() throws SQLException {
    List<delivery> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Delivery");
     while (rs.next()) {                
               int idDe=rs.getInt(1);
               int idDman=rs.getInt("IdDman");
               String DateD=rs.getString("DateD");
               int idItem=rs.getInt("IdItem");
            
     }
    return arr;
    }
 public List<delivery> triDate() throws SQLException {
    List<delivery> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Delivery order by date ");
     while (rs.next()) {                
               int idDe=rs.getInt(1);
               int idDman=rs.getInt("IdDman");
               String DateD=rs.getString("DateD");
               int idItem=rs.getInt("IdItem");
            
     }
    return arr;
    }

    
    public boolean delete(delivery t) throws SQLException {
  PreparedStatement pre=con.prepareStatement("DELETE FROM delivery WHERE idDe= ?");
       pre.setInt(1, t.getIdDe());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }

 @Override
    public boolean update(delivery t) throws SQLException {
 PreparedStatement pre=con.prepareStatement("update delivery set idDman = ?, DateD = ?, idItem  = ?  WHERE idDe = ?");
   
   
    pre.setInt(1, t.getIdDman());
    pre.setString(2, t.getDateD());
     pre.setInt(3, t.getIdItem());
     pre.setInt(4, t.getIdDe());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }
   
}
