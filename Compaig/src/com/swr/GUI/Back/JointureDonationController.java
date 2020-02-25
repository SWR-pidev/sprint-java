/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Compaign;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class JointureDonationController implements Initializable {
int idcmp;
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
    private TextField search;
    @FXML
    private TableView<Donation> view;
    @FXML
    private TableColumn<?, ?> first;
    @FXML
    private TableColumn<?, ?> last;
    @FXML
    private TableColumn<?, ?> giv;
 ObservableList<Donation> oblist = FXCollections.observableArrayList();
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
    private void search(KeyEvent event) {
    }
    public void initTable()  {
        ServiceDonation ser=new ServiceDonation();
      
         //   ObservableList<List<Proposition>> data = FXCollections.observableArrayList();
     //   List<Proposition> events = new ArrayList<>();
            
        try {
            oblist = (ObservableList<Donation>) ser.jointure(idcmp);
            
            first.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            last.setCellValueFactory(new PropertyValueFactory<>("nom"));
             giv.setCellValueFactory(new PropertyValueFactory<>("given"));
              
            view.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(PropositionSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
  
        }
}
