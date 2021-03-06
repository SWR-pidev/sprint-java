/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.goods;
import com.swr.Services.Servicegoods;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class GoodsController implements Initializable {
Servicegoods i = new Servicegoods();
    @FXML
    private Label nbnotif;
    @FXML
    private TableColumn<goods, String> itemc;
    @FXML
    private TableColumn<goods, String> quantc;
    @FXML
    private TableColumn<goods, String> statc;
    ObservableList<goods> oblistt=  FXCollections.observableArrayList();
    @FXML
    private TableView<goods> tablegoods;
    private TextField btnitemm;
    private TextField btnstatus;
    private TextField btnquant;
    @FXML
    private ComboBox<String> chooses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    List<String> Ls= new ArrayList();
    Ls.add("waiting");
    Ls.add("delivered");
    chooses.setItems(FXCollections.observableArrayList(Ls));
    
        try {
        initTable();
    } catch (SQLException ex) {
        Logger.getLogger(GoodsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    

    private void Ajouteer(ActionEvent event) throws SQLException {
       goods g1= new goods(1,1,btnitemm.getText(),Integer.parseInt(btnquant.getText()), btnstatus.getText());
       
       i.ajouter1(g1);
        initTable();
//        addbtn.setText("Creation was Successful :) ");
        clearAll(); 
    }
    private void initTable() throws SQLException {
        
        oblistt= (ObservableList<goods>) i.readAll();
        itemc.setCellValueFactory(new PropertyValueFactory<>("item"));
        quantc.setCellValueFactory(new PropertyValueFactory<>("Qcollected"));
        statc.setCellValueFactory(new PropertyValueFactory<>("status"));
        tablegoods.setItems(oblistt);
	}
    
     private void clear(ActionEvent event) {
        clearAll();
    }
    private void clearAll(){
   
        itemc.setText("");
        quantc.setText("");
        statc.setText("");
        
    }

//    @FXML
@FXML
    private void choose(ActionEvent event) throws SQLException {
//        oblist=tablegoods.getSelectionModel().getSelectedItems();
//     
//     ServiceDelivery s=new ServiceDelivery();
//     s.assign(choose.getSelectionModel().getSelectedItem(), oblist.get(0).getIdDe());
//     inittab();
//        JOptionPane.showMessageDialog(null, choose.getSelectionModel().getSelectedItem() + " a été assigné à cette livraison son id est " + oblist.get(0).getIdDe(), "info", 1);
     
        oblistt= (ObservableList<goods>) i.readAll();
    List<goods>  L=  oblistt.stream().filter(e->e.getStatus().equals(chooses.getSelectionModel().getSelectedItem())).collect(Collectors.toList());
        ObservableList<goods> ob= FXCollections.observableArrayList(L);
        itemc.setCellValueFactory(new PropertyValueFactory<>("item"));
        quantc.setCellValueFactory(new PropertyValueFactory<>("Qcollected"));
        statc.setCellValueFactory(new PropertyValueFactory<>("status"));
        tablegoods.setItems(ob);
    
    }
}
