/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Housing;
import com.swr.Entite.Item;
import com.swr.Service.ServiceItem;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private TableColumn<?, ?> col_idH;
    @FXML
    private TableColumn<?, ?> col_Name;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private TableColumn<?, ?> col_Quantity;
    @FXML
    private TableColumn<?, ?> col_Exp;
    @FXML
    private TableColumn<?, ?> col_Status;
    @FXML
    private Label lblH;
    @FXML
    private TextField tfidh;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfQuantity;
    @FXML
    private TextField tfExpdate;
    @FXML
    private TextField tfStatus;
    @FXML
    private TextArea taDesc;
    @FXML
    private TextField tfidItem;
    @FXML
    private Label lblMsg;
    @FXML
    private Button editbtn;
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
    ServiceItem is= ServiceItem.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
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
    private void editcell(ActionEvent event) {
        SelectedCell();
    }

    @FXML
    private void addnew(ActionEvent event) throws SQLException {
        Item i;
        i= new Item(Integer.parseInt(tfidh.getText()), tfName.getText(),taDesc.getText(), Integer.parseInt(tfQuantity.getText()), tfExpdate.getText(), tfStatus.getText());
        is.addItem(i);
        inittable();
        lblMsg.setText("Creation was Successful :) ");
        clearAll();
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        Item i;
        i= new Item(Integer.parseInt(tfidItem.getText()),Integer.parseInt(tfidh.getText()), tfName.getText(),taDesc.getText(), Integer.parseInt(tfQuantity.getText()), tfExpdate.getText(), tfStatus.getText());
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
        tfExpdate.setText("");
        tfQuantity.setText("");
        tfStatus.setText("");
        tfidh.setText("");
        
    }
    private void inittable() throws SQLException {
    
        
            oblist= is.getAllItems();
            is.deleteEmptyItem();
            col_Name.setCellValueFactory(new PropertyValueFactory<>("nameItem"));
            col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            col_Status.setCellValueFactory(new PropertyValueFactory<>("statusItem"));
            col_idH.setCellValueFactory(new PropertyValueFactory<>("idhouse"));
            col_Exp.setCellValueFactory(new PropertyValueFactory<>("expDate"));
            
            Itemstable.setItems(oblist);
       
    }
    private void SelectedCell() {
    Item I1=Itemstable.getSelectionModel().getSelectedItem();
        tfidItem.setVisible(false);
        tfidItem.setText(String.valueOf(I1.getIdItem()));
        tfName.setText(I1.getNameItem());
        taDesc.setText(I1.getDescription());
        tfExpdate.setText(I1.getExpDate());
        tfQuantity.setText(String.valueOf(I1.getQuantity()));
        tfStatus.setText(I1.getStatusItem());
        tfidh.setText(String.valueOf(I1.getIdhouse()));
       
    }
    private void clearAll(){
         tfidItem.setText("");
        tfName.setText("");
        taDesc.setText("");
        tfExpdate.setText("");
        tfQuantity.setText("");
        tfStatus.setText("");
        tfidh.setText("");
    }
}
