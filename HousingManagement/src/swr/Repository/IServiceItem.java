/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swr.Repository;

import java.sql.SQLException;
import java.util.List;
import swr.Entites.Item;

/**
 *
 * @author mohamed
 */
public interface IServiceItem {
    void addItem(Item i) throws SQLException;
    boolean deleteItem(Item i) throws SQLException;
    boolean updateItem(Item i) throws SQLException;
    List<Item> getAllItems() throws SQLException;
    List<Item> getItemsOfHousing(int id) throws SQLException;
    Item getItemById(int id) throws SQLException;
    Item getItemByName(String n) throws SQLException;
    void deleteEmptyItem() throws SQLException;
}
