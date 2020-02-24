/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Event;
import com.swr.Service.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class EventSpaceController implements Initializable {
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
    private TableView<Event> tableEvent;
    @FXML
    private  TableColumn<?,? > col_name;
    @FXML
    private  TableColumn<?, ?> col_date;
    @FXML
    private  TableColumn<?, ?> col_time;
    @FXML
    private  TableColumn<?, ?> col_location;
    @FXML
    private  TableColumn<?, ?> col_detail;
    @FXML
    private  TableColumn<?, ?> col_budget;
    @FXML
    private  TableColumn<?, ?> col_orga;
    @FXML
    private  TableColumn<?, ?> col_img;
    @FXML
    private  TableColumn<?, ?> col_nbv;
    @FXML
    private  TableColumn<?, ?> col_nbs;
    @FXML
    private  TableColumn<?, ?> col_nbp;
    @FXML
    private  TableColumn<?, ?> col_nbr;
    @FXML
    private  TableColumn<?, ?> col_state;
    @FXML
    private  TableColumn<?, ?> col_still;
    
ObservableList<Event> data = FXCollections.observableArrayList();

ServiceEvent ser = new ServiceEvent();
    @FXML
    private ComboBox<String> orderby;
    @FXML
    private TextField searchBar;
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button btnDeletEvent;
    @FXML
    private Button btnSponsor;
    @FXML
    private Button btnnOrders;
    @FXML
    private Button btnSignount;
    @FXML
    private Button btnStat;
   
    
    
    public EventSpaceController() {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll("Number of Participations","Number of views","Number of sponsors","Date & Time");
        orderby.setItems(data);
        Refresh();
        
    }    
private void Refresh()
{
      try {
     data= (ObservableList<Event>) ser.readAllEvents();
    col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date_evt"));
            col_time.setCellValueFactory(new PropertyValueFactory<>("time_evt"));
            col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
            col_detail.setCellValueFactory(new PropertyValueFactory<>("details"));
            col_budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
            col_orga.setCellValueFactory(new PropertyValueFactory<>("organizator"));
            col_img.setCellValueFactory(new PropertyValueFactory<>("url_evt"));
            col_nbv.setCellValueFactory(new PropertyValueFactory<>("nbviews"));
            col_nbs.setCellValueFactory(new PropertyValueFactory<>("nbsponsor"));
            col_nbp.setCellValueFactory(new PropertyValueFactory<>("nbparticipant"));
            col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbreport"));
            col_state.setCellValueFactory(new PropertyValueFactory<>("state_evt"));
            col_still.setCellValueFactory(new PropertyValueFactory<>("stillneeded_evt"));
            } catch (SQLException ex) {
            Logger.getLogger(EventSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            tableEvent.setItems(data);
}
    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void DisplayEventSpace(ActionEvent event) {
    }

    @FXML
    private void AddEventSpace(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AddEventSpace.fxml"));
        Parent root = loader.load();
          
          btnEvent.getScene().setRoot(root);
    }

    @FXML
    private void DeleteEvent(ActionEvent event) throws SQLException {
         if (tableEvent.getSelectionModel().getSelectedItem()!=null)  
         { Event e=tableEvent.getSelectionModel().getSelectedItem();
       
        ser.deleteEvent(e);
        Refresh();}
         else
         {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("please select an item first !!");
                alert.showAndWait();
         }
    }

    @FXML
    private void EditEventSpace(ActionEvent event) throws IOException {
         if (tableEvent.getSelectionModel().getSelectedItem() != null) {
        Event selectedEvent = tableEvent.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("EditEventSpace.fxml"));
        Parent root = loader.load();
            EditEventController eec = loader.getController();
            eec.setNameTxt(selectedEvent.getName());
            eec.setBudgetTxt(Double.toString(selectedEvent.getBudget()));
            
             java.sql.Date date = (java.sql.Date) selectedEvent.getDate_evt();
           LocalDate localdate = date.toLocalDate();
            eec.setDateTxt(localdate);
            
            eec.setDetailTxt(selectedEvent.getDetails());
            eec.setLocationtxt(selectedEvent.getLocation());
            eec.setOrgaTxt(selectedEvent.getOrganizator());
            
            
             java.sql.Time sqlTime = selectedEvent.getTime_evt() ;
              LocalTime localTime = sqlTime.toLocalTime();
            eec.setTimeTxt(localTime);
            
            eec.setUrlTxt(selectedEvent.getUrl_evt());
            eec.setId_evt(selectedEvent.getId_evt());
          btnEvent.getScene().setRoot(root);
    }
         else {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("please select an item first !!");
                alert.showAndWait();
         }
         
        
    }

    @FXML
    private void TrierEvents(ActionEvent event) throws SQLException {
        if(orderby.getSelectionModel().isSelected(0))
        
            { data= (ObservableList<Event>) ser.TrierEventNbparticipant();}
          
    if(orderby.getSelectionModel().isSelected(1))
   
         { data= (ObservableList<Event>) ser.TrierEventNbviews(); }
    
    if(orderby.getSelectionModel().isSelected(2))
    
        { data= (ObservableList<Event>) ser.TrierEventNbsponsor(); }
  
    
    
     if(orderby.getSelectionModel().isSelected(3))
   
         { data= (ObservableList<Event>) ser.TrierEventDate();  }
    
   
     col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date_evt"));
            col_time.setCellValueFactory(new PropertyValueFactory<>("time_evt"));
            col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
            col_detail.setCellValueFactory(new PropertyValueFactory<>("details"));
            col_budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
            col_orga.setCellValueFactory(new PropertyValueFactory<>("organizator"));
            col_img.setCellValueFactory(new PropertyValueFactory<>("url_evt"));
            col_nbv.setCellValueFactory(new PropertyValueFactory<>("nbviews"));
            col_nbs.setCellValueFactory(new PropertyValueFactory<>("nbsponsor"));
            col_nbp.setCellValueFactory(new PropertyValueFactory<>("nbparticipant"));
            col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbreport"));
            col_state.setCellValueFactory(new PropertyValueFactory<>("state_evt"));
            col_still.setCellValueFactory(new PropertyValueFactory<>("stillneeded_evt"));
    tableEvent.setItems(data);
    }

    @FXML
    private void SearchEvent(KeyEvent event) throws SQLException {
        data= (ObservableList<Event>) ser.ChercherEvent(searchBar.getText());
         col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date_evt"));
            col_time.setCellValueFactory(new PropertyValueFactory<>("time_evt"));
            col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
            col_detail.setCellValueFactory(new PropertyValueFactory<>("details"));
            col_budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
            col_orga.setCellValueFactory(new PropertyValueFactory<>("organizator"));
            col_img.setCellValueFactory(new PropertyValueFactory<>("url_evt"));
            col_nbv.setCellValueFactory(new PropertyValueFactory<>("nbviews"));
            col_nbs.setCellValueFactory(new PropertyValueFactory<>("nbsponsor"));
            col_nbp.setCellValueFactory(new PropertyValueFactory<>("nbparticipant"));
            col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbreport"));
            col_state.setCellValueFactory(new PropertyValueFactory<>("state_evt"));
            col_still.setCellValueFactory(new PropertyValueFactory<>("stillneeded_evt"));
    tableEvent.setItems(data);
    }

    @FXML
    private void DisplaySponsors(ActionEvent event) throws IOException, SQLException {
        if (tableEvent.getSelectionModel().getSelectedItem() != null) {
            
        Event selectedEvent = tableEvent.getSelectionModel().getSelectedItem();
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DisplaySponsors.fxml"));
        Parent root = loader.load();
          DisplaySponsorsController dsc = loader.getController();
         dsc.initdata(selectedEvent);
         dsc.inittab();
          btnEvent.getScene().setRoot(root);
        }
        else 
        {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText(null);
                alert.setContentText("please select an item first !!");
                alert.showAndWait();
        }
    }

    @FXML
    private void GoToStat(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("StatEvent.fxml"));
        Parent root = loader.load();
          StatEventController dsc = loader.getController();
         
          btnEvent.getScene().setRoot(root);
    }
}

