/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Deliveryman;
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
public class DeliverymanController implements Initializable {
 ServiceDman s = new ServiceDman();
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnOr;
    @FXML
    private Button btnders;
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
    private Button btnSign;
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
    private TextField btiddman;
    @FXML
    private TextField btnfname;
    @FXML
    private TextField btnlname;
    @FXML
    private TextField btnpnum;
    @FXML
    private TextField btnmail;
    @FXML
    private TableColumn<Deliveryman, String> IDdelman;
    @FXML
    private TableColumn<Deliveryman, String> fname;
    @FXML
    private TableColumn<Deliveryman, String> lname;
    @FXML
    private TableColumn<Deliveryman, String> pnum;
    @FXML
    private TableColumn<Deliveryman, String> mail;
    @FXML
    private TableColumn<Deliveryman, String> ref;
    
ObservableList<Deliveryman> oblist=  FXCollections.observableArrayList();
    @FXML
    private TableView<Deliveryman> table;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
        Deliveryman d1= new Deliveryman(btnfname.getText(), btnlname.getText(), Integer.parseInt(btnpnum.getText()), btnmail.getText());
       
       try {
            s.ajouter1(d1);
        } catch (SQLException ex) {
            Logger.getLogger(DeliverymanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initTable();
//        addbtn.setText("Creation was Successful :) ");
        clearAll();
    }

    private void update(ActionEvent event) throws SQLException {
        Deliveryman d1;
        int id= Integer.parseInt(btiddman.getText());
        d1 = new Deliveryman(id,btnfname.getText(), btnlname.getText(), Integer.parseInt(btnpnum.getText()), btnmail.getText());
        s.update(d1);
        btnupdate.setText("Updated ");
        initTable();
       clearAll();
        
    }
 @FXML
    private void SelectedCell() {
    Deliveryman d1=table.getSelectionModel().getSelectedItem();
        btiddman.setText(String.valueOf(d1.getidDman()));
        btnfname.setText(d1.getFname());
        btnlname.setText(d1.getLname());
       
        btnpnum.setText(String.valueOf(d1.getPnum()));
        btnmail.setText(d1.getmail());
       
        
    }

    private void editcell(ActionEvent event) {
        SelectedCell();
    }

 @FXML
    private void delete(ActionEvent event) throws SQLException {
       
        Deliveryman h1=table.getSelectionModel().getSelectedItem();
        if (!h1.equals(null)) 
        s.delete(h1);
//        btndelete.setText("Delete was Successful :) ");
        initTable();
        
    }
      private void initTable() {
        
        try {
            oblist= (ObservableList<Deliveryman>) s.readAll();
            IDdelman.setCellValueFactory(new PropertyValueFactory<>("idDname"));
            fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));
            lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            pnum.setCellValueFactory(new PropertyValueFactory<>("Pnum"));
            mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            System.out.println(oblist);
            table.setItems(oblist);
            
        } catch (SQLException ex) {
            Logger.getLogger(DeliverymanController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

//      private void SelectedCell() {
//    Deliveryman h1=table.getSelectionModel().getSelectedItem();
//        IDdelman.setText(intValue(h1.getidDman()));
//        fname.setText(h1.getFname());
//        lname.setText(h1.getLname());
//        pnum.setText(h1.getPnum());
//        mail.setText(String.valueOf(h1.getmail()));
//        
//        
//    }
      
    @FXML
    
    private void redirectToDM(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("BackHome.fxml"));
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
    IDdelman.setText("");
        fname.setText("");
        lname.setText("");
        pnum.setText("");
        mail.setText("");}

    @FXML
    private void in(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DeliveryHome.fxml"));
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
       
    }
    
    
    

