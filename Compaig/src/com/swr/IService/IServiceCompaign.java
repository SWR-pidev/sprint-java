/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.IService;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Monta
 */
public interface IServiceCompaign <T> {
    void ajouterCmp(T t) throws SQLException;
    boolean deleteCmp(T t) throws SQLException;
    boolean updateCmp(T t) throws SQLException;
    boolean incrementDon(T t) throws SQLException;
    List<T> readAllCmp() throws SQLException;
    List<T> triNomCmp() throws SQLException;
    List<T> trinbdonCmp() throws SQLException;
    List<T> triStillNeeded() throws SQLException;
    List<T> rechercheAvCmp(String t) throws SQLException;
    boolean GetRaised(T t) throws SQLException;
}
