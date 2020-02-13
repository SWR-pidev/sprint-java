/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entite.Categorie;
import IService.IServiceCat;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Asus X550V
 */
public class ServiceCategorie implements IServiceCat<Categorie> {
    private Connection con;
    private Statement ste;
    
    public ServiceCategorie()
    {
    con = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Categorie c) throws SQLException {
    ste = con.createStatement();
        String requeteInsert = "INSERT INTO `swr`.`categorie` (`idc`, `nomcat`) VALUES (NULL, '" + c.getNomcat()+ "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(String nom) throws SQLException {
PreparedStatement pre=con.prepareStatement("DELETE FROM categorie WHERE nomcat='"+nom+"' ;");
        pre.executeUpdate();
        return true;    }

    @Override
    public boolean update(String nom1,String nom2) throws SQLException {
PreparedStatement pre=con.prepareStatement("UPDATE categorie SET nomcat= '" +nom2+"' WHERE nomcat='"+nom1+"' ;");
            pre.executeUpdate();
       
        return true;
    }

    @Override
    public List<Categorie> readAll() throws SQLException {
List<Categorie> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from categorie");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nomcat=rs.getString("nomcat");
               Categorie c=new Categorie(id, nomcat);
     arr.add(c);
     }
    return arr;
    }


    
}
