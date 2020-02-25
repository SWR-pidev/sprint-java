/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.Item;
import com.swr.Service.HousingService;
import com.swr.Service.ServiceItem;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class ItemsController implements Initializable {
ObservableList<Item> oblist = FXCollections.observableArrayList();
    ServiceItem is= ServiceItem.getInstance();
    HousingService hs= HousingService.getInstance();
   
    
    @FXML
    public TableColumn<?, ?> col_name;
    @FXML
    public TableColumn<?, ?> col_quan;
    @FXML
    public TableColumn<?, ?> col_stat;
    @FXML
    public TableView<Item> itemTab;
    @FXML
    public Label txtlbl;
    @FXML
    public TableColumn<?, ?> col_desc;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button ctnbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
    }    
    @FXML
    private void recherche(){
      
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Item> filteredData = new FilteredList<>(itemTab.getItems(), p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (item.getNameItem().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (item.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Item> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(itemTab.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        itemTab.setItems(sortedData);
    }

    @FXML
    private void redtogoodscreat(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GoodCreation.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent,500, 500);
        GoodCreationController Gc = loader.getController();
        Gc.comboH.setItems((ObservableList<String>) hs.getHousingNames());
        
        
        
        
//         = is.getItemsOfHousing(itemTab.getSelectionModel().getSelectedItem().getIdh());
//            Ic.col_name.setCellValueFactory(new PropertyValueFactory<>("nameItem"));
//            Ic.col_quan.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//            Ic.col_stat.setCellValueFactory(new PropertyValueFactory<>("statusItem"));
//            Ic.col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
//            Ic.txtlbl.setText(Ic.txtlbl.getText()+itemTab.getSelectionModel().getSelectedItem().getNameItem());
//            
//            
//            Ic.itemTab.setItems(oblistitem);
      
        
        
 
        
        //This line gets the Stage information
         

        Stage newWindow = new Stage();
                newWindow.setTitle("Good Creation");
                newWindow.setScene(tableViewScene);
                newWindow.showAndWait();
    }
   
}
