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
public interface IServiceCat<T> {
    
    void ajouter(T t) throws SQLException;
    boolean delete(String nom) throws SQLException;
    boolean update(String nom1,String nom2) throws SQLException;
    List<T> readAll() throws SQLException;
    
}
