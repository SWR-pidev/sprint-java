/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swr.Repository;

import java.sql.SQLException;
import java.util.List;
import swr.Entites.Rating;

/**
 *
 * @author mohamed
 */
public interface  IRatingService {
    void addRating(Rating r) throws SQLException;
    boolean deleteRating(Rating r) throws SQLException;
    boolean updateRating(Rating r) throws SQLException;
    List<Rating> readAllRatings() throws SQLException;
    List<Rating> getAllRatingsOfHousing(int id) throws SQLException;
    long countRatings(int id) throws SQLException;
    double averageRating(int id) throws SQLException;
}
