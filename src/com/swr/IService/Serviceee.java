/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.IService;


import java.sql.SQLException;
import java.util.List;
import com.swr.Entite.Deliveryman;
import javafx.collections.ObservableList;

/**
 *
 * @author House
 */
public interface Serviceee {
    void ajouter(Deliveryman d) throws SQLException;
    boolean delete(Deliveryman d) throws SQLException;
    boolean update(Deliveryman d) throws SQLException;
    ObservableList<Deliveryman> readAll() throws SQLException;
}

