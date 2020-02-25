/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.IService;
import com.swr.Entite.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author Asus X550V
 */
public interface IService<T> {
    public User listuser(String us) throws SQLException;
    void ajouter(T t) throws SQLException;
    boolean delete(String nom) throws SQLException;
    boolean update(T t,String id) throws SQLException;
    void updateiter(String k) throws SQLException;
    int affichiter(String a) throws SQLException;
    boolean modify(T t,String id) throws SQLException;
    ObservableList<User> readAll() throws SQLException;
    List<T> rechercheavance(String n) throws SQLException;
    List<T> tri() throws SQLException;
    public String getaUser(String mail) throws SQLException;
    public void updatepass(String mail,String pass) throws SQLException;
    ObservableList<T> rechercheentredate(String d1 , String d2) throws SQLException;
    int moyenne() throws SQLException;
    int auth(String mail,String pwd) throws SQLException;
    int preauth(String mail,String pwd) throws SQLException;
    String forgotpasss(String mail) throws SQLException;
    String getUser(String mail) throws SQLException;
    String getImage(String mail) throws SQLException;
    public String getImageu(String mail) throws SQLException;
    public void resetiter(String mail) throws SQLException;
    public boolean deletemail(String nom) throws SQLException;
    int getCode(String code) throws SQLException;
    User SerachUser(String username) throws SQLException;
    public int getma(String tel) throws SQLException;
    public int getUs(String tel) throws SQLException;
    public int getTel(String tel) throws SQLException;
    public ResultSet barchart() throws SQLException;
}
