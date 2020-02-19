/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Compaign;
import com.swr.Service.ServiceCompaign;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class AddCompaignController implements Initializable {

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
    private TextField name;
    @FXML
    private TextField target;
    @FXML
    private TextArea description;
    
    String lien;
    @FXML
    private Button link;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }    

    @FXML
    private void home(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("BackHome.fxml"));
        Parent root = loader.load();
          
          btnhome.getScene().setRoot(root); 
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
    private void addnow(ActionEvent event) throws SQLException, IOException {
       String nom1 = name.getText();
        char[] chars = nom1.toCharArray();
        for (char c : chars) {
        if(!Character.isLetter(c)) {
          Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("Careful with the name");
                alert.showAndWait();
        }}
           
        if (target.getText().equals(""))
        {
        Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("Careful with the Target");
                alert.showAndWait();
        }
        
       
                 double  trgt = Double.parseDouble(target.getText());
                    String desc = description.getText();
                
                
      ServiceCompaign Ser =new ServiceCompaign ();
    
    Compaign C1=new Compaign (nom1,trgt,desc,this.lien);
    Ser.ajouterCmp(C1);
    
    Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Compaign Successfully Added");
                alert.showAndWait();
     FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("CompaignSpace.fxml"));
        Parent root = loader.load();
          
          btncmp.getScene().setRoot(root);
    }

    @FXML
    private void navigate(ActionEvent event) {
              FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
              fileChooser.getExtensionFilters().add(extFilter);
             Window primaryStage = null;
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null) return ;
            this.lien=file.getPath();
       
    }

    
}
