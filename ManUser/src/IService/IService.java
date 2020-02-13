/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Asus X550V
 */
public interface IService<T> {
    void ajouter(T t) throws SQLException;
    boolean delete(String nom) throws SQLException;
    boolean update(String nom1,String mail,String pwd) throws SQLException;
    List<T> readAll() throws SQLException;
    List<T> rechercheavance(String n) throws SQLException;
    List<T> tri() throws SQLException;
    List<T> rechercheentredate(String d1 , String d2) throws SQLException;
    int moyenne() throws SQLException;
}
