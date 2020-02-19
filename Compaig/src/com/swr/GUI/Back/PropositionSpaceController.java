/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import com.swr.Entite.Proposition;
import com.swr.Service.ServiceProposition;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Monta
 */
public class PropositionSpaceController implements Initializable {

    @FXML
    private Button btnhome;
    @FXML
    private Button btncmp;
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
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button addprop;
    @FXML
    private Button gotocmp;
    private TableColumn<?, ?> id_cmp;
    private TableColumn<?, ?> details;
    @FXML
    private TableView<Proposition> view;
 ObservableList<Proposition> oblist = FXCollections.observableArrayList();
    @FXML
    private Button btnOrder;
    @FXML
    private Button btnSignou;
    @FXML
    private TableColumn<?, ?> nom_cmp;
    @FXML
    private TableColumn<?, ?> desc_prop;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            // TODO
            initTable();
        
        
    }    
private void initTable()  {
        
      
         //   ObservableList<List<Proposition>> data = FXCollections.observableArrayList();
     //   List<Proposition> events = new ArrayList<>();
            ServiceProposition ser = new ServiceProposition();
        try {
            oblist = (ObservableList<Proposition>) ser.readAllProp();
           // oblist.forEach(( p)->{;});
          
            nom_cmp.setCellValueFactory(new PropertyValueFactory<>(""));
            desc_prop.setCellValueFactory(new PropertyValueFactory<>("details"));
            view.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(PropositionSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
  
        }
	
 @FXML
    private void home(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("BackHome.fxml"));
        Parent root = loader.load();
          
          btncmp.getScene().setRoot(root); 
    }
    @FXML
    private void compaign(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("CompaignSpace.fxml"));
        Parent root = loader.load();
          
          btncmp.getScene().setRoot(root);
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void addpropp(ActionEvent event) {
    }

    @FXML
    private void Gotocmp(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("CompaignSpace.fxml"));
        Parent root = loader.load();
          
          gotocmp.getScene().setRoot(root);
    }
    
}