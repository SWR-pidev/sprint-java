/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Delivery;
import com.swr.Entite.Deliveryman;
import com.swr.Services.ServiceDelivery;
import com.swr.Services.ServiceDman;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Controller implements Initializable {
ServiceDelivery fr = new ServiceDelivery();
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
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button t;
    @FXML
    private Button btnOrder;
    @FXML
    private Button out;
    @FXML
    private TableView<Delivery> tab;
    @FXML
    private TableColumn<Delivery, String> deliv;
    @FXML
    private TableColumn<Delivery, String> delivman;
    @FXML
    private TableColumn<Delivery, String> date;
    @FXML
    private TableColumn<Delivery, String> item;
    @FXML
    private TableColumn<Delivery, String> dest;

    ObservableList<Delivery> oblist=  FXCollections.observableArrayList();
//    private TableView<Delivery> table;
    @FXML
    private TextField btnid;
    @FXML
    private TextField btndate;
    @FXML
    private TextField btnitem;
    @FXML
    private Button adddbtn;
    private Button mdfbtn;
    @FXML
    private Button delbtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inittab();
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
@FXML
    private void ajouter(ActionEvent event) throws SQLException {
       
        Delivery m1= new Delivery (Integer.parseInt(btnid.getText()), btndate.getText(), Integer.parseInt(btnitem.getText()));
       
       try {
            fr.ajouter1(m1);
        } catch (SQLException ex) {
            Logger.getLogger(DeliverymanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        inittab();
        adddbtn.setText("Created ");
        clearAll();
    }

    private void update(ActionEvent event) throws SQLException {
        Delivery m1;
        int id= Integer.parseInt(btnid.getText());
        m1 = new Delivery(id,btndate.getText(),Integer.parseInt(btnitem.getText()));
        fr.update(m1);
        mdfbtn.setText("Updated ");
        inittab();
       clearAll();
        
    }
@FXML
    private void delete(ActionEvent event) throws SQLException {
       
        Delivery m1=tab.getSelectionModel().getSelectedItem();
        if (!m1.equals(null)) 
        fr.delete(m1);
//        delbtn.setText("Delete was Successful :) ");
        inittab();
        
    }
@FXML
    private void SelectedCell() {
    Delivery m1=tab.getSelectionModel().getSelectedItem();
        btnid.setText(String.valueOf(m1.getIdDman()));
        btndate.setText(m1.getDateD());
        btnitem.setText(String.valueOf(m1.getIdItem()));
      
    }

    private void editcell(ActionEvent event) {
        SelectedCell();
    }

    private void inittab() {
        
        try {
            oblist= (ObservableList<Delivery>) fr.readAll();
            deliv.setCellValueFactory(new PropertyValueFactory<>("IdDe"));
            delivman.setCellValueFactory(new PropertyValueFactory<>("idDman"));
            date.setCellValueFactory(new PropertyValueFactory<>("DateD"));
            item.setCellValueFactory(new PropertyValueFactory<>("idItem"));
            
//            System.out.println(oblist);
            tab.setItems(oblist);
            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
	}


    @FXML
    private void Deliveryyy(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("BackHome.fxml"));
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void Dout(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Deliveryman.fxml"));
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    private void clear(ActionEvent event) {
        clearAll();
    }
    private void clearAll(){
    btnid.setText("");
        btndate.setText("");
        btnitem.setText("");
    }
}
