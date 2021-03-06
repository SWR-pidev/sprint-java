/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Posts;
import com.swr.Service.ServicePosts;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Soulah
 */
public class postsController implements Initializable {
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
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<Posts> PostsTable;
    @FXML
    private TableColumn<?, ?> col_Contenu;
    @FXML
    private TableColumn<?, ?> col_cmt;
    @FXML
    private TableColumn<?, ?> col_views;
    @FXML
    private TableColumn<?, ?> col_report;
    @FXML
    private TableColumn<?, ?> col_like;
    @FXML
    private TableColumn<?, ?> col_user;
    
    ServicePosts p= new ServicePosts();
    
    ObservableList<Posts> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField searchbar;
    @FXML
    private Button deletebtn;
    @FXML
    private Button cmtbtn;
    @FXML
    private Button statsbtn;
    @FXML
    private ChoiceBox<?> choiceboxSort;


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
    
    private void initTable() {
        
        try {
            oblist= (ObservableList<Posts>) p.readAll();
          //  col_id.setCellValueFactory(new PropertyValueFactory<>("idP"));
            col_Contenu.setCellValueFactory(new PropertyValueFactory<>("contenuP"));
            col_cmt.setCellValueFactory(new PropertyValueFactory<>("nbcmt"));
            col_views.setCellValueFactory(new PropertyValueFactory<>("views"));
            col_report.setCellValueFactory(new PropertyValueFactory<>("report"));
            col_like.setCellValueFactory(new PropertyValueFactory<>("likes"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("iduser"));
            PostsTable.setItems(oblist);
            
        } catch (SQLException ex) {
                  System.out.println(ex.getMessage());        }
	}

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void deletePost(ActionEvent event) throws SQLException {
        
        Posts p1=PostsTable.getSelectionModel().getSelectedItem();
        if (!p1.equals(null)){ 
        p.delete(p1);
        initTable();}

    }

    @FXML
    private void gotoComnts(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ComntBack.fxml"));
            try {
                Parent root = loader.load();
                cmtbtn.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void gotoStats(ActionEvent event) {
    }

    
}
