/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.IService;
import com.swr.Entite.News;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author Asus X550V
 */
public interface IServiceNew<T> {
    public int searchtit(String titre) throws SQLException;
  void ajouter(T t) throws SQLException;
    boolean delete(String titre) throws SQLException;
    String search(String titre) throws SQLException;
    String searchdesc(String titre) throws SQLException;
    String searchdate(String titre) throws SQLException;
    String searchnomcat(String titre) throws SQLException;
    boolean update(String titre1,String titre, String description ,String nomcat,String image) throws SQLException;
    ObservableList<News> readAll() throws SQLException;  
    List<T> nouveaute() throws SQLException;
    float stats(String nomcat) throws SQLException;
    ResultSet satistique() throws SQLException;
    ObservableList<News> list() throws SQLException;
}
