/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;
import com.swr.Entite.Compaign;
import com.swr.Entite.Proposition;
import com.swr.IService.IServiceProposition;
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
 * 
 * 
 */


public class ServiceProposition implements IServiceProposition<Proposition> {
  private Connection con;
    private Statement ste;
public static ServiceProposition p=null;
     public ServiceProposition() {
        con = DataBase.getInstance().getConnection();
        
     } 
      public static ServiceProposition getInstance()
    {if(p==null)
        p=new ServiceProposition();
    return p;
    }     
    
    @Override
    public void ajouterProp(Proposition t) throws SQLException {
     PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`sugg` ( `id_sugg`, `id_cmp`, `detail`) VALUES ( ?, ?, ?);");
    pre.setInt(1, t.getId_prop());
    pre.setInt(2, t.getC().getId_cmp());
    pre.setString(3, t.getDetails()); 
    pre.executeUpdate();
    }

    @Override
    public boolean deleteProp(Proposition t) throws SQLException {
  PreparedStatement pre=con.prepareStatement("DELETE FROM sugg WHERE id_sugg= ?");
       pre.setInt(1, t.getId_prop());
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }

    @Override
    public boolean updateProp(Proposition t) throws SQLException {
 PreparedStatement pre=con.prepareStatement("update sugg set detail = ?  WHERE id_sugg = ?");
   
    pre.setString(1, t.getDetails());
    pre.setDouble(2, t.getId_prop());
   
      if ( pre.executeUpdate()!=0)
      
      {return true; }
      else
        return false;
    }

    @Override
    public ObservableList<Proposition> readAllProp() throws SQLException {
       ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from sugg");
     while (rs.next()) {  
        
                int idProp=rs.getInt(1);
                int idCmp=rs.getInt(2);
                String desc=rs.getString(3);
                Compaign c=new Compaign();
                c.setId_cmp(idCmp);
                ServiceCompaign sr=new ServiceCompaign();
                c=sr.GetInfosCmp(idCmp);
                Proposition p= new Proposition(idProp,c,desc);
                p.setNamecmp(c.getNamecmp());
          
     arr.add(p);
     }
    return arr; 
    }
    
       @Override
    public List<Proposition> triMaxDetail() throws SQLException {
       List<Proposition> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from sugg order by MAX(LEN(detail))");
     while (rs.next()) {  
        
                int idProp=rs.getInt(1);
                int idCmp=rs.getInt(2);
                String desc=rs.getString(3);
                
                Compaign c=new Compaign();
                c.setId_cmp(idCmp);
                Proposition p= new Proposition(idProp,c,desc);
          
     arr.add(p);
     }
    return arr; 
    }

    
       public ObservableList<String> PropByIdCmp(int id) throws SQLException {
       ObservableList arr= FXCollections.observableArrayList();
    ste=con.createStatement();
         PreparedStatement pre=con.prepareStatement("select detail from sugg where id_cmp= ?");
  pre.setInt(1, id);
   ResultSet rs=pre.executeQuery();
     while (rs.next()) { 
    
                String desc=rs.getString(1);
                      
     arr.add(desc);
     }
    return arr; 
    }
}
