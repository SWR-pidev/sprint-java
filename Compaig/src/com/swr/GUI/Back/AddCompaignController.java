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
import java.io.IOException;
import java.net.MalformedURLException;
    import java.net.ProtocolException;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
    import rest.file.uploader.tn.FileUploader;
   
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
    
  public static  String lien=null;
    @FXML
    private Button link;
    public static String DataBaseLink;
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
private static boolean netIsAvailable() {
    try {
        final URL url = new URL("http://www.google.com");
        final URLConnection conn = url.openConnection();
        conn.connect();
        conn.getInputStream().close();
        return true;
    } catch (MalformedURLException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        return false;
       
    }  
}
    @FXML
    private void addnow(ActionEvent event) throws SQLException, IOException {
      if(netIsAvailable())  
      {
        
        int nolet=0;
  int spa=0;
     String nom1 = name.getText();
        ServiceCompaign Ser1=new ServiceCompaign();
         ObservableList listcmp;
         listcmp=Ser1.NameExist();
         System.out.println(listcmp);
         if (listcmp.contains(nom1))
         {
         Alert alerto = new Alert(AlertType.WARNING);
                alerto.setTitle("Warning !!!");
                alerto.setContentText("Compaign name already exists");
                alerto.showAndWait();
         return;
         }
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
        {Alert alerto = new Alert(AlertType.WARNING);
                alerto.setTitle("Warning !!!");
                alerto.setContentText("Careful with the name");
                alerto.showAndWait();
         return;}
        String trgtverif=target.getText();
char[] chars1 = trgtverif.toCharArray();
        for (char c : chars1) {
        if(!Character.isDigit(c)) {
           Alert alerto = new Alert(AlertType.WARNING);
                alerto.setTitle("Warning !!!");
                alerto.setContentText("Target Should be numbers only");
                alerto.showAndWait(); 
                 return;
        }}
       
        if (target.getText().isEmpty())
        {
        Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("Careful with the Target");
                alert.showAndWait();
                 return;
        }
        if (lien==null)
        {
         Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("Please Select an Image");
                alert.showAndWait();
                 return;
        }
                 double  trgt = Double.parseDouble(target.getText());
                    String desc = description.getText();              
      ServiceCompaign Ser =new ServiceCompaign ();
        FileUploader fu = new FileUploader("localhost/swr/web/"); 
   Compaign C1=new Compaign (nom1,trgt,fu.upload(lien),desc);
        try { 
            Ser.ajouterCmp(C1);
        } catch (SQLException ex) {
            Logger.getLogger(AddCompaignController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    }
    @FXML
    private void navigate(ActionEvent event) {
              FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
              fileChooser.getExtensionFilters().add(extFilter);
             Window primaryStage = null;
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null) {
            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Please Select An Image");
                alert.showAndWait();  
                return;
            } ;
            this.lien=file.getPath();
       
    }

    
}
