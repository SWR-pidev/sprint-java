/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;


import com.swr.Entite.Donation;
import com.swr.Service.ServiceDonation;
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
import javafx.scene.Parent;
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
        try {
            // TODO
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(DonationSpaceController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void handleClicks(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("BackHome.fxml"));
        Parent root = loader.load();
          
          btncmp.getScene().setRoot(root);
    }

    private void initTable() throws SQLException  {
        
      
        //   ObservableList<List<Proposition>> data = FXCollections.observableArrayList();
        //   List<Proposition> events = new ArrayList<>();
        oblist = (ObservableList<Donation>) ser.readAllProp();
        donNum.setCellValueFactory(new PropertyValueFactory<>("idDon"));
        usrNum.setCellValueFactory(new PropertyValueFactory<>("idu"));
        cmpNum.setCellValueFactory(new PropertyValueFactory<>("idc"));
        descr.setCellValueFactory(new PropertyValueFactory<>("mes"));
        mon.setCellValueFactory(new PropertyValueFactory<>("monthly"));
        fun.setCellValueFactory(new PropertyValueFactory<>("funds"));
        giv.setCellValueFactory(new PropertyValueFactory<>("given"));
        ano.setCellValueFactory(new PropertyValueFactory<>("Annonym"));
        da.setCellValueFactory(new PropertyValueFactory<>("d"));
        ti.setCellValueFactory(new PropertyValueFactory<>("t"));
        view.setItems(oblist);
            
  
        }
    
    
}
