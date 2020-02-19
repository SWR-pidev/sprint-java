/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Compaign;
import com.swr.Entite.Proposition;
import com.swr.Service.ServiceCompaign;
import com.swr.Service.ServiceProposition;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class CompaignSpaceController implements Initializable {
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
    private Button btnhome;
    @FXML
    private Button addbtn;
    @FXML
    private Button goprop;
    @FXML
    private Button btnOrder;
    @FXML
    private Button btnSignou;
    @FXML
    private TableView<Compaign> view;
    @FXML
    private TableColumn<?, ?> cmpname;
    @FXML
    private TableColumn<?, ?> trgt;
    @FXML
    private TableColumn<?, ?> raised;
    @FXML
    private TableColumn<?, ?> descr;
    @FXML
    private TableColumn<?, ?> lin;
    @FXML
    private TableColumn<?, ?> nbdon;
    @FXML
    private TableColumn<?, ?> still;
 ObservableList<Compaign> oblist = FXCollections.observableArrayList();
 
 
 
 ServiceCompaign ser = new ServiceCompaign();
 @FXML
    private  ComboBox<String> orderby;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Number Donors",
        "Compaign Name",
        "Almost Funded"
    ); 
        orderby.setItems(options);
        
     
    }    
private void initTable()  {
        
      
         //   ObservableList<List<Proposition>> data = FXCollections.observableArrayList();
     //   List<Proposition> events = new ArrayList<>();
            
        try {
            oblist = (ObservableList<Compaign>) ser.readAllCmp();
            
            cmpname.setCellValueFactory(new PropertyValueFactory<>("namecmp"));
            trgt.setCellValueFactory(new PropertyValueFactory<>("trgt"));
             raised.setCellValueFactory(new PropertyValueFactory<>("raised"));
              descr.setCellValueFactory(new PropertyValueFactory<>("desc"));
               lin.setCellValueFactory(new PropertyValueFactory<>("url"));
                  nbdon.setCellValueFactory(new PropertyValueFactory<>("nbdon"));
                  still.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
            view.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(PropositionSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
  
        }
    @FXML
    private void handleClicks(ActionEvent event) {
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
    private void addcmp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AddCompaign.fxml"));
        Parent root = loader.load();
          
          addbtn.getScene().setRoot(root);
    }

    @FXML
    private void GotoProp(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("PropositionSpace.fxml"));
        Parent root = loader.load();
          
          goprop.getScene().setRoot(root);
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        if (view.getSelectionModel().getSelectedItem()!=null)
        {
        
        Compaign e=view.getSelectionModel().getSelectedItem();
      
        ser.deleteCmp(e);
        initTable();}
        else {
              Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("please select an item first !!");
                alert.showAndWait();  
                }
        }
    

    @FXML
    private void update(ActionEvent event) throws IOException {
        if (view.getSelectionModel().getSelectedItem()!=null)
        {
            
          Compaign  selectedCmp = view.getSelectionModel().getSelectedItem();
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("UpdateCompaign.fxml"));
        Parent root = loader.load();
            UpadteCompaignController  eec = loader.getController();
         eec.setId(selectedCmp.getId_cmp());
        eec.setLink(selectedCmp.getUrl());
        eec.setName(selectedCmp.getNamecmp());
         eec.setTarget(Double.toString(selectedCmp.getTrgt()));
         eec.setDescription(selectedCmp.getDesc());
         
           btncmp.getScene().setRoot(root);
        }  
         else {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("please select an item first !!");
                alert.showAndWait();
         }
    }

    @FXML
    private void trie(ActionEvent event) throws SQLException {
        if(orderby.getSelectionModel().isSelected(0))
        {
            oblist = (ObservableList<Compaign>) ser.trinbdonCmp();      
        }
        
        if(orderby.getSelectionModel().isSelected(1))
        {

            oblist = (ObservableList<Compaign>) ser.triNomCmp();
          
        }
        if(orderby.getSelectionModel().isSelected(2)){
       
            oblist = (ObservableList<Compaign>) ser.triStillNeeded();
        }
            cmpname.setCellValueFactory(new PropertyValueFactory<>("namecmp"));
            trgt.setCellValueFactory(new PropertyValueFactory<>("trgt"));
             raised.setCellValueFactory(new PropertyValueFactory<>("raised"));
              descr.setCellValueFactory(new PropertyValueFactory<>("desc"));
               lin.setCellValueFactory(new PropertyValueFactory<>("url"));
                  nbdon.setCellValueFactory(new PropertyValueFactory<>("nbdon"));
                  still.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
            view.setItems(oblist);
    }

    @FXML
    private void search(KeyEvent event) {
             
        try {
            oblist = (ObservableList<Compaign>) ser.rechercheAvCmp(search.getText());
            
            cmpname.setCellValueFactory(new PropertyValueFactory<>("namecmp"));
            trgt.setCellValueFactory(new PropertyValueFactory<>("trgt"));
             raised.setCellValueFactory(new PropertyValueFactory<>("raised"));
              descr.setCellValueFactory(new PropertyValueFactory<>("desc"));
               lin.setCellValueFactory(new PropertyValueFactory<>("url"));
                  nbdon.setCellValueFactory(new PropertyValueFactory<>("nbdon"));
                  still.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
            view.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(PropositionSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}




    

