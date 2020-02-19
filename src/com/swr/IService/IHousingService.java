/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.IService;

import java.sql.SQLException;
import java.util.List;
import com.swr.Entite.Housing;
import javafx.collections.ObservableList;

/**
 *
 * @author mohamed
 */
public interface IHousingService {
    void addHousing(Housing h) throws SQLException;
    boolean deleteHousing(Housing h) throws SQLException;
    boolean updateHousing(Housing h) throws SQLException;
    ObservableList<Housing> getAllHousings() throws SQLException;
    Housing getHousingById(int id) throws SQLException;
    Housing getHousingByName(String nom) throws SQLException;
    Housing getHousingByLocation(String loc) throws SQLException;
    
}
