/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;


import com.swr.Entite.Donation;
import com.swr.Service.ServiceDonation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class DonationSpaceController implements Initializable {

    @FXML
    private Button btnhome;
    @FXML
    private Button btncmp;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnOrder;
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
    private Button btnSignou;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button addbtn;
    @FXML
    private ComboBox<?> orderby;
    @FXML
    private TableView<Donation> view;
    @FXML
    private TableColumn<?, ?> donNum;
    @FXML
    private TableColumn<?, ?> usrNum;
    @FXML
    private TableColumn<?, ?> cmpNum;
    @FXML
    private TableColumn<?, ?> descr;
    @FXML
    private TableColumn<?, ?> mon;
    @FXML
    private TableColumn<?, ?> fun;
    @FXML
    private TableColumn<?, ?> giv;
    @FXML
    private TableColumn<?, ?> ano;
    @FXML
    private TableColumn<?, ?> da;
    @FXML
    private TableColumn<?, ?> ti;
 ObservableList<Donation> oblist = FXCollections.observableArrayList();
 ServiceDonation ser = new ServiceDonation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void compaign(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void addcmp(ActionEvent event) {
    }
    private void initTable() throws SQLException  {
        
      
        //   ObservableList<List<Proposition>> data = FXCollections.observableArrayList();
        //   List<Proposition> events = new ArrayList<>();
        oblist = (ObservableList<Donation>) ser.readAllProp();
        donNum.setCellValueFactory(new PropertyValueFactory<>("namecmp"));
        usrNum.setCellValueFactory(new PropertyValueFactory<>("trgt"));
        cmpNum.setCellValueFactory(new PropertyValueFactory<>("raised"));
        descr.setCellValueFactory(new PropertyValueFactory<>("desc"));
        mon.setCellValueFactory(new PropertyValueFactory<>("url"));
        fun.setCellValueFactory(new PropertyValueFactory<>("nbdon"));
        giv.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
        ano.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
        da.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
        ti.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
        view.setItems(oblist);
            
  
        }
    
    
}
