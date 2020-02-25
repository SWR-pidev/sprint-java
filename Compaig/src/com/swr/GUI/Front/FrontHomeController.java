/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.Compaign;
import com.swr.Service.ServiceCompaign;
import com.swr.Service.ServiceProposition;
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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class FrontHomeController implements Initializable {
    @FXML
    private Label nbnotif;
    @FXML
    private Button don;
    @FXML
    private Label cmpname;
    @FXML
    private Label still;
    @FXML
    private Button dond;
Compaign c=new Compaign();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ServiceCompaign ser = new ServiceCompaign();

 Compaign data =new Compaign();
        try {
            data=  ser.AlmostFundedCmp();
            if (data.getStillneeded()!=0)
            { this.cmpname.setText(data.getNamecmp());
         this.still.setText(Double.toString(data.getStillneeded())+" $");
         c.setId_cmp(data.getId_cmp());}
        } catch (SQLException ex) {
            Logger.getLogger(FrontHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void donate(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("CompaignSpace.fxml"));
        Parent root = loader.load();
          
          don.getScene().setRoot(root);
    }

    @FXML
    private void donatenow(ActionEvent event) throws IOException, SQLException {
        
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Donate.fxml"));
        Parent root = loader.load();
         DonateController des = loader.getController();
         
          ObservableList<String> options = FXCollections.observableArrayList() ; 
        ServiceProposition sera=new ServiceProposition();
        int idd=c.getId_cmp();
        options=sera.PropByIdCmp(idd);
        System.out.println("id compaign from hom  "+idd);
         System.out.println(options);
        des.setCom(options);
         
         
        des.id=idd; 
        des.recentdon();
          dond.getScene().setRoot(root);
    }
    
}
