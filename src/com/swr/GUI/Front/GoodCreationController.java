/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.Housing;
import com.swr.Entite.Item;
import com.swr.Entite.goods;
import com.swr.Service.HousingService;
import com.swr.Service.ServiceItem;
import com.swr.Service.Servicegoods;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class GoodCreationController implements Initializable {

    @FXML
    public ComboBox<String> comboH;
    @FXML
    public ComboBox<String> comboItems;
    @FXML
    public TextField tfQN;
    @FXML
    public TextField tfQC;
    @FXML
    private Button btnCnt;
    ServiceItem is= ServiceItem.getInstance();
    ObservableList<Item> oblist= FXCollections.observableArrayList();
    
    HousingService hs= HousingService.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void afterChoosingH() throws SQLException{
    Housing h = hs.getHousingByName(comboH.getSelectionModel().getSelectedItem());
    oblist = is.getItemsOfHousing(h.getIdh());
        
        
        List<String> Listnames = oblist.stream().map(Item::getNameItem)
                                   .collect(Collectors.toList());
        ObservableList<String> obnames = FXCollections.observableArrayList(Listnames);
        
        comboItems.setItems(obnames);
    
    }
    @FXML
     public void afterChoosingI() throws SQLException{
         int i= is.getItemByName(comboItems.getSelectionModel().getSelectedItem());
         
         tfQN.setText(String.valueOf(i));
         
         if (i<=0) {
         btnCnt.setDisable(true);
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Collected");
        alert.setContentText("This Item Is already Collected :) Thank You ");
        alert.setHeaderText(null);
        alert.showAndWait();
         }
         else{btnCnt.setDisable(false);}
    
    }

    @FXML
    private void CreateGood(ActionEvent event) throws SQLException {
        int idh = hs.getHousingByName(comboH.getSelectionModel().getSelectedItem()).getIdh();
        String name = comboItems.getSelectionModel().getSelectedItem();
        int User= 0;
        if(tfQC.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert");
        alert.setContentText("Please Insert a Valide Quantity");
        alert.setHeaderText(null);
        alert.showAndWait();
        }
        else {
        int q= Integer.parseInt(tfQC.getText());
        goods g= new goods(idh, User, name, q, "Waiting");
        Servicegoods gs= new Servicegoods();
        gs.ajouter1(g);
        is.Changeandtest(name, q);
        }
        
    }
}
