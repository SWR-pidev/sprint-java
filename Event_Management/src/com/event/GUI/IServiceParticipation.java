/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.event.GUI;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Eya
 * @param <T>
 */
public interface IServiceParticipation<T> {
    
     void addParticipation(T t) throws SQLException;
     boolean deleteParticipation(T t) throws SQLException;
      List<T> readAllParticipation() throws SQLException;
}
