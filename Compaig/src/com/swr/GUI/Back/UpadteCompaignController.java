/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.jfoenix.controls.JFXTextField;
import com.swr.Entite.Compaign;
import com.swr.Service.ServiceCompaign;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class UpadteCompaignController implements Initializable {

    @FXML
    private Button btnhome;
    @FXML
    private Button btncmp;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnOers;
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
    private Button btnSgnout;
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
    private Button link;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField target;
    @FXML
    private TextArea description;
    @FXML
    private JFXTextField linn;
String lien;
int id;

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void addnow(ActionEvent event) throws SQLException, IOException {
        
             String nom1 = name.getText();
       int nolet=0;
int spa=0;
       /* ServiceCompaign Ser1=new ServiceCompaign();
         ObservableList listcmp;
         listcmp=Ser1.NameExist();*/
        // System.out.println(listcmp);
        
     char[] chars = nom1.toCharArray();
        for (char c : chars) {
        if(!Character.isLetter(c)) {
        nolet++; 
        }
         if(c==' ') {
        spa++; 
        }
        }
        if (spa!=nolet)
        {Alert alerto = new Alert(Alert.AlertType.WARNING);
                alerto.setTitle("Warning !!!");
                alerto.setContentText("Careful with the name");
                alerto.showAndWait();
         return;}
        String trgtverif=target.getText();
char[] chars1 = trgtverif.toCharArray();
        for (char c : chars1) {
        if(!Character.isDigit(c)) {
           Alert alerto = new Alert(Alert.AlertType.WARNING);
                alerto.setTitle("Warning !!!");
                alerto.setContentText("Target Should be numbers only");
                alerto.showAndWait(); 
                 return;
        }}
       
        {
       
       
        }
        if (target.getText().isEmpty())
        {
        Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("Careful with the Target");
                alert.showAndWait();
                 return;
        } 
        
        
       
                 double  trgt = Double.parseDouble(target.getText());
                    String desc = description.getText();
                
                
      ServiceCompaign Ser =new ServiceCompaign ();
        if(lien==null)
        {lien=linn.getText();}
    Compaign C1=new Compaign (id,nom1,trgt,this.lien,desc);
    Ser.updateCmp(C1);
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Compaign Successfully updated");
                alert.showAndWait();
     FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("CompaignSpace.fxml"));
        Parent root = loader.load();
          
          btncmp.getScene().setRoot(root);
        
    }


    public void setLink(String link) {
        this.linn.setText(link);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setTarget(String target) {
        this.target.setText(target);
    }

    public void setDescription(String description) {
        this.description.setText(description);
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
            this.linn.setText(lien);
    }

    @FXML
    private void linn(ActionEvent event) {
    }

 

    
}
