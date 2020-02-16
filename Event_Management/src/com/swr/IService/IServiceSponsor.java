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
 * @author Eya
 */
public interface IServiceSponsor<T> {
     void addSponsor(T t) throws SQLException;
      List<T> readAllSponsors() throws SQLException;
     double GivedSum(T t) throws SQLException;
      List<T> TrierSponsorGiven() throws SQLException;
       List<T> TrierSponsorDate() throws SQLException;
     
}
