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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class EditEventController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button t;
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
    private Button tt;
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
    private TextArea detailTxt;
    @FXML
    private JFXTextField budgetTxt;
    @FXML
    private JFXTextField orgaTxt;
    @FXML
    private Button btnUrl;
    @FXML
    private JFXTextField locationtxt;
    @FXML
    private JFXTextField urlTxt;
    @FXML
    private Button btnEditEvent;
    private String Imgurl;
    private int Id_evt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            if(file==null) return;
            else {this.Imgurl=file.getPath();
                  this.setUrlTxt(Imgurl);}
    }

    @FXML
    private void EditEvent(ActionEvent event) throws SQLException, IOException {
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the name with only letters!!");
                alert.showAndWait();
        }
          else if(dateTxt.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the date !!");
                alert.showAndWait();

        }
          else if(timeTxt.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the time !!");
                alert.showAndWait();

        } 
          else if(detail.isEmpty())
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the Description!!");
                alert.showAndWait();
        }
           else if( budgetTxt.getText().trim().equals("") || budgetTxt.getText().matches("^[ A-Za-z]+$"))
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the budget with only numbers !!");
                alert.showAndWait();
        }
           else if( !orga.matches("^[ A-Za-z]+$") || orga.trim().equals(""))
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the organizator name with only letters !!");
                alert.showAndWait();
        }
           else if (urlTxt.getText().isEmpty())
           {
               Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert an image!!");
                alert.showAndWait();
           }
     
           else if(location.isEmpty())
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("Make sure to insert the Description!!");
                alert.showAndWait();
        }
           else {
              
               
               if(Imgurl==null)
        {
            Imgurl=urlTxt.getText();
        }
               
                double  budget = Double.parseDouble(budgetTxt.getText());
               Time timee =  Time.valueOf( time );
                java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        ServiceEvent ser = new ServiceEvent();
        Event e = new Event(Id_evt,nom,sqlDate,timee,location,detail,budget,orga,this.Imgurl);
        
        ser.updateEvent(e);
        
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("EventSpace.fxml"));
        Parent root = loader.load();
          
          btnEvent.getScene().setRoot(root);}
    }

    public void setNameTxt(String nameTxt) {
        this.nameTxt.setText(nameTxt);
    }

    public void setDateTxt(LocalDate dateTxt) {
         //LocalDate localDate = LocalDate.parse(dateTxt);
        this.dateTxt.setValue(dateTxt);
    }

    public void setTimeTxt(LocalTime timeTxt) {
        //LocalTime localtime = LocalTime.parse(timeTxt);
        this.timeTxt.setValue(timeTxt);
    }

    public void setDetailTxt(String detailTxt) {
        this.detailTxt.setText(detailTxt);
    }

    public void setBudgetTxt(String budgetTxt) {
        this.budgetTxt.setText(budgetTxt);
    }

    public void setOrgaTxt(String orgaTxt) {
        this.orgaTxt.setText(orgaTxt);
    }

    public void setLocationtxt(String locationtxt) {
        this.locationtxt.setText(locationtxt);
    }

    public void setUrlTxt(String urlTxt) {
        this.urlTxt.setText(urlTxt);
    }

    public void setId_evt(int Id_evt) {
        this.Id_evt = Id_evt;
    }
    
}
