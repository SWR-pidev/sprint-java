/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Comments;
import com.swr.Entite.Posts;
import com.swr.Service.ServiceComments;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Soulah
 */
public class CommentController  implements Initializable {
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
    private TextField searchbar;
    @FXML
    private TableView<Comments> cmtTable;
    
    
    @FXML
    private TableColumn<?, ?> col_ContenuC;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?, ?> col_reportc;
    @FXML
    private TableColumn<?, ?> col_user;
    @FXML
    private Button deleteCbtn;
    @FXML
    private Button cmtbtn;
    @FXML
    private Button statsbtn;
    ServiceComments sc= new ServiceComments();
    
    ObservableList<Comments> oblist = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> Sortboxc;
    @FXML
    private Button searchbtn;
    
    public void initialize(URL url, ResourceBundle rb) {
     initTable();
      Sortboxc.setItems(FXCollections.observableArrayList("Idp","Default"));
     Sortboxc.setValue("Default");
    
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
          String x= searchbar.getText();
         try {
            oblist= (ObservableList<Comments>) sc.recherche(x);
            col_ContenuC.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
            col_reportc.setCellValueFactory(new PropertyValueFactory<>("reportC"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("iduser"));
            cmtTable.setItems(oblist);
            
        } catch (SQLException ex) {
                  System.out.println(ex.getMessage());        }
    }

    @FXML
    private void deleteccmt(ActionEvent event) throws SQLException {
        Comments p1=cmtTable.getSelectionModel().getSelectedItem();
        if (!p1.equals(null)){ 
        sc.delete(p1);
        initTable();}
    }

    @FXML
    private void gotoComnts(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Postsgui.fxml"));
            try {
                Parent root = loader.load();
                cmtbtn.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
    }
    }
     private void initTable() {
        
        try {
            oblist= (ObservableList<Comments>) sc.readAll();
          
            col_ContenuC.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
            col_reportc.setCellValueFactory(new PropertyValueFactory<>("reportC"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("iduser"));
            cmtTable.setItems(oblist);
            
        } catch (SQLException ex) {
                  System.out.println(ex.getMessage());        }
	}
     
       private void Trie() {
        
        try {
            oblist= (ObservableList<Comments>) sc.trie();
          
            col_ContenuC.setCellValueFactory(new PropertyValueFactory<>("contenuC"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
            col_reportc.setCellValueFactory(new PropertyValueFactory<>("reportC"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("iduser"));
            cmtTable.setItems(oblist);
            
        } catch (SQLException ex) {
                  System.out.println(ex.getMessage());        }
	}

    @FXML
    private void gotoStats(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Stats.fxml"));
            try {
                Parent root = loader.load();
                cmtbtn.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void trier(ActionEvent event) {
           if(Sortboxc.getValue()=="Idp")
     {
        
         
         Trie();
     }
         
         else if(Sortboxc.getValue()=="Default")
     {
         
         
         initTable();
     }
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
                           FXMLLoader fxml=new FXMLLoader(getClass().getResource("/com/swr/gui/front/login.fxml"));
        Parent root=fxml.load();
        cmtbtn.getScene().setRoot(root);
    }
    
}
