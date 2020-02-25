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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnUser;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button btnpost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("News.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
        
    }

    @FXML
    private void user(ActionEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("Userb.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
        
    }

    private void news(ActionEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("News.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
       
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("/com/swr/gui/front/login.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
    }

    @FXML
    private void gotopost(ActionEvent event) throws IOException {
                FXMLLoader fxml=new FXMLLoader(getClass().getResource("Postsgui.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
    }
    
}
