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
public interface IServiceNew<T> {
  void ajouter(T t) throws SQLException;
    boolean delete(String titre) throws SQLException;
    boolean update(String titre1,String titre, String description ,String nomcat) throws SQLException;
    List<T> readAll() throws SQLException;  
    List<T> nouveaute() throws SQLException;
    float stats(String nomcat) throws SQLException;
    
}
