/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.Service;
import com.swr.Entite.Likes;
import com.swr.Entite.Posts;
import com.swr.Entite.User;
import com.swr.IService.IService1;
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
public class ServiceLikes implements IService1<Likes>  {
     private final Connection con;
    private Statement ste;
     User u =new User();
   Posts p=new Posts();

 
    public ServiceLikes() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Likes l) throws SQLException {
  
   PreparedStatement pre=con.prepareStatement("INSERT INTO `swr`.`likes` ( `idP`, `iduser`) VALUES ( ?, ?);");
    pre.setInt(1, l.getIdP());
    pre.setInt(2, l.u.getIdu());
    

    pre.executeUpdate();
        
    }

    @Override
    public boolean delete(Likes t) throws SQLException {

            PreparedStatement pre=con.prepareStatement("delete from like where iduser = '" +t.u.getIdu() + "' and idP = '" +t.p.getIdP()+ "'  ;");
            pre.executeUpdate();
            return true;    }

    @Override
    public boolean update(Likes t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Likes> readAll() throws SQLException {
         return null;
    
    }
    
    public boolean checkstatus(int a,int x) throws SQLException{
            List<Likes> arr= new ArrayList<>();
            int z=0;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from likes where iduser = '" +a + "' and idP = '" + x + "' ");
     while (rs.next()) { 
         z++;
         
     }   
     if(z!=0) return true;
     else return false;
    }
    
}
