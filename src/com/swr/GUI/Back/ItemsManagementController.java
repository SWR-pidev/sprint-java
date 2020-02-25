/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Housing;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class ItemsManagementController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private TableView<Item> Itemstable;
    @FXML
    private TableColumn<?, ?> col_Name;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private TableColumn<?, ?> col_Quantity;
    @FXML
    private TableColumn<?, ?> col_Status;
    @FXML
    private Label lblH;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfQuantity;
    
    @FXML
    private TextField tfStatus;
    @FXML
    private TextArea taDesc;
    @FXML
    private TextField tfidItem;
    @FXML
    private Label lblMsg;
    @FXML
    private Button newbtn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
    @FXML
    private TextField tfSearch;
    @FXML
    private Label lbItems;
    @FXML
    private Button clearbtn;
    ObservableList<Item> oblist = FXCollections.observableArrayList();
    ObservableList<String> nameslist = FXCollections.observableArrayList();
    
    ServiceItem is= ServiceItem.getInstance();
    @FXML
    private ComboBox<String> comboH;
    HousingService hs= HousingService.getInstance();
    @FXML
    private TableColumn<?, ?> col_Hname;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            col_Hname.setVisible(false);
            nameslist= (ObservableList<String>) hs.getHousingNames();
            comboH.setItems( nameslist);
            tfidItem.setVisible(false);
            inittable();
        } catch (SQLException ex) {
            Logger.getLogger(ItemsManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void redTohousing(MouseEvent event) throws IOException {
           Parent tableViewParent = FXMLLoader.load(getClass().getResource("HousingManagement.fxml"));
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    

    @FXML
    private void addnew(ActionEvent event) throws SQLException {
        Item i;
        String name= comboH.getSelectionModel().getSelectedItem();
        Housing test= hs.getHousingByName(name);
        i= new Item(test.getIdh(), tfName.getText(),taDesc.getText(), Integer.parseInt(tfQuantity.getText()), null, tfStatus.getText());
        is.addItem(i);
        inittable();
        lblMsg.setText("Creation was Successful :) ");
        clearAll();
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        Item i;
        int id = hs.getHousingByName(comboH.getSelectionModel().getSelectedItem()).getIdh();
        i= new Item(Integer.parseInt(tfidItem.getText()),id, tfName.getText(),taDesc.getText(), Integer.parseInt(tfQuantity.getText()), null, tfStatus.getText());
        is.updateItem(i);
        lblMsg.setText("Update was Successful :) ");
        inittable();
       clearAll();
        
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
         Item h1=Itemstable.getSelectionModel().getSelectedItem();
         is.deleteItem(h1);
        lblMsg.setText("Delete was Successful :) ");
        inittable();
        
    }

    @FXML
    private void clear(ActionEvent event) {
        tfidItem.setText("");
        tfName.setText("");
        taDesc.setText("");
        tfQuantity.setText("");
        tfStatus.setText("");
        
    }
    private void inittable() throws SQLException {
    
            
            oblist= is.getAllItems();
            is.EmptyItem();
            col_Name.setCellValueFactory(new PropertyValueFactory<>("nameItem"));
            col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            col_Status.setCellValueFactory(new PropertyValueFactory<>("statusItem"));
            col_Hname.setCellValueFactory(new PropertyValueFactory<>("name"));
            
            Itemstable.setItems(oblist);
       
    }
    private void SelectedCell() throws SQLException {
    Item I1=Itemstable.getSelectionModel().getSelectedItem();
        tfidItem.setVisible(false);
        tfidItem.setText(String.valueOf(I1.getIdItem()));
        tfName.setText(I1.getNameItem());
        taDesc.setText(I1.getDescription());
        tfQuantity.setText(String.valueOf(I1.getQuantity()));
        tfStatus.setText(I1.getStatusItem());
        String hname = hs.getHousingById(I1.getIdhouse()).getName();
        comboH.setValue(hname);
        
       
    }
    private void clearAll(){
         tfidItem.setText("");
        tfName.setText("");
        taDesc.setText("");
        tfQuantity.setText("");
        tfStatus.setText("");
        
    }

    @FXML
    private void editcell(MouseEvent event) throws SQLException {
         if (event.getClickCount()==2){
        SelectedCell();
        }
    }
    @FXML
    private void recherche(){
      
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Item> filteredData = new FilteredList<>(oblist, p -> true);
        
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
        sortedData.comparatorProperty().bind(Itemstable.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        Itemstable.setItems(sortedData);
    }
}
