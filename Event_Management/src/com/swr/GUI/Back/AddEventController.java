/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.swr.Entite.Event;
import com.swr.Service.ServiceEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import rest.file.uploader.tn.FileUploader;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class AddEventController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnEvent;
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
    private JFXTextField nameTxt;
    @FXML
    private JFXDatePicker dateTxt;
    @FXML
    private JFXTimePicker timeTxt;
    @FXML
    private JFXTextField locationtxt;
    @FXML
    private JFXTextField budgetTxt;
    @FXML
    private JFXTextField orgaTxt;
    @FXML
    private Button btnUrl;
    @FXML
    private TextArea detailTxt;
    
    String Imgurl="";
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button t;
    @FXML
    private Button tt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       /* Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("Careful !!");
                alert.showAndWait();*/
       dateTxt.setDayCellFactory((DatePicker picker) -> {
           return new DateCell() {
               public void updateItem(LocalDate date, boolean empty) {
                   super.updateItem(date, empty);
                   LocalDate today = LocalDate.now();
                   LocalDate weeklater =today.plusWeeks(1);
                   setDisable( empty || date.compareTo(today) < 0 || date.equals(today) || date.isBefore(weeklater));
               }
           }; });
       
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void DisplayEventSpace(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("EventSpace.fxml"));
        Parent root = loader.load();
          
          btnEvent.getScene().setRoot(root);
    
    }

    @FXML
    private void ImageUrl(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
              fileChooser.getExtensionFilters().add(extFilter);
             Window primaryStage = null;
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null) { return;}
            this.Imgurl=file.getPath();  
    }

    @FXML
    private void AddEvent(ActionEvent event) throws SQLException, IOException {
        
        ZoneId defaultZoneId = ZoneId.systemDefault();
        String nom = nameTxt.getText();
        LocalDate date = dateTxt.getValue();
        //Date datee = (Date) Date.from(date.atStartOfDay(defaultZoneId).toInstant());
      
        LocalTime time=timeTxt.getValue();
        String location = locationtxt.getText();
        
        String orga = orgaTxt.getText();
        String detail=detailTxt.getText();
        
       if(nom.isEmpty() || !nom.matches("^[ A-Za-z]+$") )
        {
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the name with only letters!!");
                alert.showAndWait();
        }
        else if(dateTxt.getValue()==null)
        {
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the date !!");
                alert.showAndWait();

        }
       else if(timeTxt.getValue()==null)
        {
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the time !!");
                alert.showAndWait();

        }
         else if(detail.isEmpty())
        {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the Description!!");
                alert.showAndWait();
        }
        else if(budgetTxt.getText().matches(".*[^0-9].*")||  budgetTxt.getText().trim().equals(""))
        {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the budget with only numbers !!");
                alert.showAndWait();
        }else if( !orga.matches("^[ A-Za-z]+$") || orga.trim().equals(""))
        {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the organizator name with only letters !!");
                alert.showAndWait();
        }
          else if(this.Imgurl.isEmpty())
        {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert a picture!!");
                alert.showAndWait();
        }
       
         else if(location.isEmpty())
        {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the Description!!");
                alert.showAndWait();
        }
       
       
        else{
              java.sql.Date sqlDate = java.sql.Date.valueOf(date);
              Time timee =  Time.valueOf( time );
        double  budget = Double.parseDouble(budgetTxt.getText());
        ServiceEvent ser = new ServiceEvent();      
         FileUploader fu = new FileUploader("localhost:8080/swr/web/");
         
           System.out.println("hedhi heya "+fu.upload(Imgurl));
        Event e = new Event(nom,sqlDate,timee,location,detail,budget,orga,fu.upload(Imgurl));
        
        ser.addEvent(e);
        
         Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Add Event");
                alert.setHeaderText(null);
                alert.setContentText("Add Event in progress");
                alert.showAndWait();
                
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("EventSpace.fxml"));
        Parent root = loader.load();
          
          btnEvent.getScene().setRoot(root);
        
    }}
    
    
        
    
}
