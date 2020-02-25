/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;


import com.jfoenix.controls.JFXListView;
import com.swr.Entite.Compaign;
import com.swr.Service.ServiceCompaign;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
/**
 * FXML Controller class
 *
 * @author Monta
 */
public class CompaignSpaceController implements Initializable {

    @FXML
    private Label nbnotif;
    @FXML
    private JFXListView<Label> myJFXlist;
 ServiceCompaign ser = new ServiceCompaign();
 Compaign c=new Compaign();
    @FXML
    private Button dis;
 
 
 /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
         ListCompains();
     } catch (SQLException ex) {
         Logger.getLogger(CompaignSpaceController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    
    
    public void ListCompains() throws SQLException
    {
         
        ObservableList<Compaign> data =FXCollections.observableArrayList();
        data= (ObservableList<Compaign>) ser.readAllCmp();
        
         for(int i=0 ; i<data.size() ; i++)
        {
            //System.out.println(Integer.toString(data.get(i).getId_evt()));
            Label lbl = new Label("                           "+data.get(i).getNamecmp());
            
            //Label lbl2 = new Label(Integer.toString(data.get(i).getId_evt()));
            try {
                Image image = new Image(new FileInputStream("C:\\wamp64\\www\\swr\\web\\uploads\\"+data.get(i).getUrl()));
                ImageView imgEvt = new ImageView(image);
                imgEvt.setFitHeight(182);
                imgEvt.setFitWidth(250);
                lbl.setGraphic(imgEvt);
                //lbl.setGraphic(new Button("Participer"));
                myJFXlist.getItems().add(lbl);
               // myJFXlist.getItems().add(lbl2);
                // myJFXlist.getItems().set(i, lbl2).setVisible(false);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CompaignSpaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        myJFXlist.setExpanded(true);
        myJFXlist.depthProperty().set(1);
        myJFXlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      
       
    }

    @FXML
    private void discmp(ActionEvent event) throws SQLException, IOException {
        if (myJFXlist.getSelectionModel().getSelectedItem()!=null)
        {  int selectedEvent =  myJFXlist.getSelectionModel().getSelectedIndex();
        
         ObservableList<Compaign> data =FXCollections.observableArrayList();
        data= (ObservableList<Compaign>) ser.readAllCmp();
        
        int id = data.get(selectedEvent).getId_cmp();
       c = ser.GetInfosCmp(id);
    System.out.println(c.toString());
         
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("LearnMoreCompaign.fxml"));
        Parent root = loader.load();
        ProgressBar pro = new ProgressBar();
        LearnMoreCompaignController des = loader.getController();
        des.setid(c.getId_cmp());
        des.setName(c.getNamecmp());
        des.setDetailcmp(c.getDesc());
        des.setraised(c.getRaised());
        des.setUrlEvent(c.getUrl());   
        des.setBudget(c.getTrgt());
        des.setStill(c.getStillneeded());
        des.setnbdon(c.getNbdon());
       double d=c.getRaised()/c.getTrgt();
      // des.progress=d;
       System.out.println("double d=");
        des.setPro(d);

     //   ser.incrementDon(c);
       dis.getScene().setRoot(root);
        
    } else if (myJFXlist.getSelectionModel().getSelectedItem()==null)
        {  Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Compaign");
                alert.setHeaderText(null);
                alert.setContentText("Please Select Compaign First ");
                alert.showAndWait();}
    }

   
    
}
