/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.swr.Entite.Compaign;
import com.swr.Entite.Donation;
import com.swr.Entite.User;
import com.swr.Service.ServiceCompaign;
import com.swr.Service.ServiceDonation;
import com.swr.Service.ServiceProposition;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class DonateController implements Initializable {
public  int id;
    @FXML
    private Label nbnotif;
    @FXML
    private Button dis;
    @FXML
    private JFXCheckBox month;
    @FXML
    private JFXTextArea mes;
    @FXML
    private JFXCheckBox fund;
    @FXML
    private Label ano;
    @FXML
    private JFXCheckBox anon;
    @FXML
    private ComboBox<?> com;
    @FXML
    private JFXListView<Label> recent;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnnn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
         // TODO
         recentdon();
     } catch (SQLException ex) {
         Logger.getLogger(DonateController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    

    @FXML
 private void donate(ActionEvent event) throws IOException, SQLException {
 //     Popup popup = new Popup();
//StripeController controller = new StripeController();
Stage s=new Stage();
FXMLLoader loader = new FXMLLoader(getClass().getResource("Stripe.fxml"));
Parent root = loader.load();
 StripeController des = loader.getController();

 
 
 int mo = 0;
      int an =0 ;
      int fun=0;
       Donation d=new Donation();
       Compaign c=new Compaign();
       Compaign c1=new Compaign();
       ServiceDonation ser= new ServiceDonation();
        ServiceCompaign serc=new ServiceCompaign();
       c1=serc.GetInfosCmp(id);
       c.setId_cmp(id);
        System.out.println("this the id of compaing"+id+"  ");
       
        if (month.isSelected())
        {       mo=1;
        }
        if (anon.isSelected())
        {       an=1;
        }
        if (fund.isSelected())
        {     
          
            fun=1;
        }
        
    /*    if (c1.getStillneeded()<Double.parseDouble(given.getText()))
        {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("THANK YOU !!!");
                alert.setContentText("but This is way too much we can't accept that");
                alert.showAndWait();  
        return;
        }
        if (Double.isNaN(Double.parseDouble(given.getText())))
        {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Carefull with the Amount !!!");
                alert.setContentText("Given Amount should Be double");
                alert.showAndWait();  
        return;
        }*/
        d.setAnnonym(an);
        d.setFunds(fun);
        d.setMonthly(mo);
        //d.setGiven(Double.parseDouble(given.getText()));
        d.setC(c);
        User u =new User();
        u.setId(3);
        d.setU(u);
        d.setMes(mes.getText().toString());
        d.setIdc(id);
 
  des.initvariable(id);
  des.d=d;
s.setScene(new Scene(root));
s.initModality(Modality.APPLICATION_MODAL);
s.initOwner(dis.getScene().getWindow());
s.setTitle("Stripe");
s.showAndWait();s.close();
//dis.getScene().setRoot(root);
s.close();


    }

    @FXML
    private void comb(ActionEvent event) {
    }

    public void setCom(ObservableList com) {
        this.com.setItems(com);
    }

    public void recentdon() throws SQLException
    {  
           
         
             ServiceDonation serd=new ServiceDonation();
          ObservableList<Donation> data =FXCollections.observableArrayList();
        data= (ObservableList<Donation>) serd.recentdon(id);
     
         for(int i=0 ; i<data.size() ; i++)
        {
               System.out.println("data "+data.get(i).toString());
            if (data.get(i).getAnnonym()==1)     
            {
                         
                 data.get(i).setNom("No One");
                 data.get(i).setPrenom("A User");
            }    
            Label lbl= new Label("Donor :"+data.get(i).getNom()+"  "+data.get(i).getPrenom()+"  donated  "+data.get(i).getGiven()+"\n"+"  ");
                    recent.getItems().add(lbl);     
        }
        recent.setExpanded(true);
        recent.depthProperty().set(1);
        recent.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       //  recent.setMouseTransparent( true );
      //  recent.setFocusTraversable( false );
       
    }

    @FXML
    private void homecliked(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("FrontHome.fxml"));
        Parent root = loader.load();
          
          btnhome.getScene().setRoot(root);
    }

   

    @FXML
    private void homm(ActionEvent event) throws IOException {
                             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("CompaignSpace.fxml"));
        Parent root = loader.load();
          
          btnhome.getScene().setRoot(root);
    }
    
    
    
    }
    

