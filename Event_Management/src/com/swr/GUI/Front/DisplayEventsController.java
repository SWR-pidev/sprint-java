/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.swr.Entite.Event;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import com.swr.Service.ServiceEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class DisplayEventsController implements Initializable {

 
 ServiceEvent ser = new ServiceEvent();
   Event e = new Event();
    @FXML
    private Label nbnotif;
    @FXML
    private Button btnEvent;
    @FXML
    private JFXListView<Label> myJFXlist;
    @FXML
    private JFXListView<Label> myJFXlistP;
    @FXML
    private Button btnLearnMore;
    @FXML
    private Button btnLearnMoreP;
    
   
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       btnLearnMore.disableProperty()          
        .bind(myJFXlist.getSelectionModel().selectedItemProperty().isNull());
     btnLearnMoreP.disableProperty()          
        .bind(myJFXlistP.getSelectionModel().selectedItemProperty().isNull());
   
     
     
     try {
         listEventSponsor();
         listEventP();
     } catch (SQLException ex) {
         Logger.getLogger(DisplayEventsController.class.getName()).log(Level.SEVERE, null, ex);
     }
    
     
       
        
    }


            
    public void listEventSponsor() throws SQLException
    {
         
        ObservableList<Event> data =FXCollections.observableArrayList();

         data= (ObservableList<Event>) ser.readSponsorEvent();
        
        
        
         for(int i=0 ; i<data.size() ; i++)
        {
           
            //System.out.println(Integer.toString(data.get(i).getId_evt()));
            Label lbl = new Label("       "+data.get(i).getName());
            //Label lbl2 = new Label(Integer.toString(data.get(i).getId_evt()));
            try {
                Image image = new Image(new FileInputStream("C:\\wamp64\\www\\swr\\web\\uploads\\"+data.get(i).getUrl_evt()));
                ImageView imgEvt = new ImageView(image);
                imgEvt.setFitHeight(100);
                imgEvt.setFitWidth(100);
                lbl.setGraphic(imgEvt);
                //lbl.setGraphic(new Button("Participer"));
                myJFXlist.getItems().add(lbl);
               // myJFXlist.getItems().add(lbl2);
                // myJFXlist.getItems().set(i, lbl2).setVisible(false);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayEventsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        myJFXlist.setExpanded(true);
        myJFXlist.depthProperty().set(1);
        myJFXlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      
       
    }

    @FXML
    private void GotoSingleEvent(ActionEvent event) throws SQLException, IOException {
      ObservableList<Event> dataS =FXCollections.observableArrayList();
     
       if( myJFXlist.getSelectionModel().getSelectedItems()!=null)
       {
         dataS= (ObservableList<Event>) ser.readSponsorEvent();
        int selectedEvent =  myJFXlist.getSelectionModel().getSelectedIndex();
        int idS = dataS.get(selectedEvent).getId_evt();
        e=ser.GetInfosById(idS);
             }
      
       
        System.out.println(e.toString());
         
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DisplayEventSingle.fxml"));
        Parent root = loader.load();
        
        DisplayEventSingleController des = loader.getController();
        
        des.setNameEvent(e.getName());
        des.setDetailEvent(e.getDetails());
        des.setLocation(e.getLocation());
        des.setUrlEvent(e.getUrl_evt());
        double raised=e.getBudget()-e.getStillneeded_evt();
        System.out.println("Prog bar should be:"+raised/e.getBudget());
        des.setPgBar(raised/e.getBudget());
       des.initdata(e);
       des.checkEvent(e);
       des.listSponsor(e);
       des.setE1(e);
          java.sql.Date date = (java.sql.Date) e.getDate_evt();
           LocalDate localdate = date.toLocalDate();
        des.setDateEvent(localdate);
        
        java.sql.Time sqlTime = e.getTime_evt() ;
              LocalTime localTime = sqlTime.toLocalTime();
              des.setTimeEvent(localTime);
        ser.updateNbViewsEvent(e);
       btnEvent.getScene().setRoot(root);
        
    }

    private void GoToEvents(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DisplayEvents.fxml"));
        Parent root = loader.load();
          
          btnEvent.getScene().setRoot(root);
    }

   
     public void listEventP() throws SQLException
     {
          ObservableList<Event> data =FXCollections.observableArrayList();
        
        data= (ObservableList<Event>) ser.readParticipateEvent();
        
         for(int i=0 ; i<data.size() ; i++)
        {
           
            //System.out.println(Integer.toString(data.get(i).getId_evt()));
            Label lbl = new Label("       "+data.get(i).getName());
            //Label lbl2 = new Label(Integer.toString(data.get(i).getId_evt()));
            try {
                Image image = new Image(new FileInputStream("C:\\wamp64\\www\\swr\\web\\uploads\\"+data.get(i).getUrl_evt()));
                ImageView imgEvt = new ImageView(image);
                imgEvt.setFitHeight(100);
                imgEvt.setFitWidth(100);
                lbl.setGraphic(imgEvt);
                //lbl.setGraphic(new Button("Participer"));
                myJFXlistP.getItems().add(lbl);
               // myJFXlist.getItems().add(lbl2);
                // myJFXlist.getItems().set(i, lbl2).setVisible(false);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayEventsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        myJFXlistP.setExpanded(true);
        myJFXlistP.depthProperty().set(1);
        myJFXlistP.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
         
      
     }

    @FXML
    private void GotoSingleEventP(ActionEvent event) throws SQLException, IOException {
        ObservableList<Event> dataP =FXCollections.observableArrayList();
        
         if(myJFXlistP.getSelectionModel().getSelectedItems()!=null)
        { dataP= (ObservableList<Event>) ser.readParticipateEvent();
         int selectedEventP =  myJFXlistP.getSelectionModel().getSelectedIndex();
         int idP = dataP.get(selectedEventP).getId_evt();
         e=ser.GetInfosById(idP);
        }
         System.out.println(e.toString());
         
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DisplayEventSingle.fxml"));
        Parent root = loader.load();
        
        DisplayEventSingleController des = loader.getController();
      
        des.setNameEvent(e.getName());
        des.setDetailEvent(e.getDetails());
        des.setLocation(e.getLocation());
        String hh=e.getUrl_evt();
        des.setUrlEvent(hh);
         
       des.initdata(e);
       des.checkEvent(e);
       des.listSponsor(e);
       des.setE1(e);
          java.sql.Date date = (java.sql.Date) e.getDate_evt();
           LocalDate localdate = date.toLocalDate();
        des.setDateEvent(localdate);
        
        java.sql.Time sqlTime = e.getTime_evt() ;
              LocalTime localTime = sqlTime.toLocalTime();
              des.setTimeEvent(localTime);
        ser.updateNbViewsEvent(e);
       btnEvent.getScene().setRoot(root);
    }
    
}
