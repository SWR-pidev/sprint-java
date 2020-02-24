/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Event;
import com.swr.Entite.Sponsor;
import com.swr.Service.ServiceSponsor;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class DisplaySponsorsController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnEvent;
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
    private Label lblId;
    @FXML
    private TableView<Sponsor> tableEvent;
    @FXML
    private TableColumn<?, ?> col_name;
    @FXML
    private TableColumn<?, ?> col_date;
    ServiceSponsor srs = new ServiceSponsor();
    Sponsor s = new Sponsor();
     ObservableList<Sponsor> data=FXCollections.observableArrayList();
     ObservableList<Sponsor> data1=FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> col_first;
    @FXML
    private TableColumn<?, ?> col_logo;
    @FXML
    private TableColumn<?, ?> col_gived;
    @FXML
    private TableView<Sponsor> tableSponsored;
    @FXML
    private TableColumn<?, ?> col_nameE;
    @FXML
    private TableColumn<?, ?> col_givedE;
    @FXML
    private Button btnSponsored;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        }
        
        
public void initdata(Event e) throws SQLException
{
    lblId.setText(Integer.toString(e.getId_evt()));    
}
public void inittab() throws SQLException
{
    int id=Integer.valueOf(lblId.getText());
    data=(ObservableList<Sponsor>) srs.GetSponsorByIdEvent(id);
    
     col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
      col_first.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       col_logo.setCellValueFactory(new PropertyValueFactory<>("logo"));
       col_gived.setCellValueFactory(new PropertyValueFactory<>("given"));
       col_date.setCellValueFactory(new PropertyValueFactory<>("date_sponsor"));
       tableEvent.setItems(data);
}
    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void DisplayEventSpace(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("EventSpace.fxml"));
        Parent root = loader.load();
          
          btnEvent.getScene().setRoot(root);
    }



    @FXML
    private void DisplayEventSponsored(ActionEvent event) throws SQLException {
        if (tableEvent.getSelectionModel().getSelectedItem() != null) {
            
        Sponsor selectedSponsor = tableEvent.getSelectionModel().getSelectedItem();
        
       data1= (ObservableList<Sponsor>)srs.SUE(selectedSponsor.getU().getIdu());
       // System.out.println("hey"+selectedSponsor.getU().getIdu()+"nameEvt :"+selectedSponsor.);
        col_nameE.setCellValueFactory(new PropertyValueFactory<>("nameEvt"));
        col_givedE.setCellValueFactory(new PropertyValueFactory<>("given"));
        tableSponsored.setItems(data1);
        }
        else 
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("please select a Sponsor first !!");
                alert.showAndWait();
        }
    }

   

    

    

    
    
}
