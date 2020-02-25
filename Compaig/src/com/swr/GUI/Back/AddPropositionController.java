/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.swr.Entite.Compaign;
import com.swr.Entite.Proposition;
import com.swr.Service.ServiceCompaign;
import com.swr.Service.ServiceProposition;
import java.io.IOException;
import java.net.URL;
import java.security.Provider;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class AddPropositionController implements Initializable {

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
    private Button addcmp;
    @FXML
    private TextArea description;
    @FXML
    private JFXComboBox<String> comb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String>  names;
        ServiceCompaign s=new ServiceCompaign();
        try {
            names=s.readAllCmpnames();
             comb.setItems(names);
        } catch (SQLException ex) {
            Logger.getLogger(AddPropositionController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void addnow(ActionEvent event) throws SQLException {
        boolean MyComboBoxEmpty = comb.getSelectionModel().isEmpty();
      // System.out.println(comb.getSelectionModel().getSelectedItem().toString());
        if (!MyComboBoxEmpty)
        {
             ObservableList<Integer>  ids;
            ServiceProposition s=new ServiceProposition();
            ServiceCompaign Sc=new ServiceCompaign();
            
            
            ObservableList<Integer>  listId=Sc.GetIdByName();
           int  index=comb.getSelectionModel().getSelectedIndex();
           
           int id =listId.get(index);
            
            Compaign c=new Compaign();
         //   c.setNamecmp(comb.getSelectionModel().getSelectedItem().toString());
            c.setId_cmp(id);
            Proposition p=new Proposition(c,description.getText());
            s.ajouterProp(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success !!!");
                alert.setContentText("Proposition Added !!");
                alert.showAndWait();  
        }
        else 
        {
         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Select Compaign Name First !!");
                alert.showAndWait();  
        }
    }

    @FXML
    private void combo(ActionEvent event) {
    }
    
}
