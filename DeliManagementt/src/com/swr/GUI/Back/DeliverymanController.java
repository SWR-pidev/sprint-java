/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Deliveryman;
import com.swr.Services.ServiceDman;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DeliverymanController implements Initializable {
 ServiceDman s = new ServiceDman();
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnOr;
    @FXML
    private Button btnders;
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
    private Button btnSign;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button addbtn;
//    private TextField btiddman;
//    @FXML
 @FXML
    private TextField btnfname;
    @FXML
    private TextField btnlname;
    @FXML
    private TextField btnpnum;
    @FXML
    private TextField btnmail;
//    private TableColumn<Deliveryman, String> IDdelman;
//    @FXML
 @FXML
    private TableColumn<Deliveryman, String> fname;
    @FXML
    private TableColumn<Deliveryman, String> lname;
    @FXML
    private TableColumn<Deliveryman, String> pnum;
    @FXML
    private TableColumn<Deliveryman, String> mail;
    
ObservableList<Deliveryman> oblist=  FXCollections.observableArrayList();


    @FXML
    private TableView<Deliveryman> table;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField enter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rechercher();
        initTable();
//rafraichir();
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
//        Notifications.create()
//                .title("You have a new notification : ")
//                .text("A new deliveryman was added")
//                .showWarning();
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
       if(validate("Fname", btnfname.getText(), "[a-zA-Z]+") &&
               validate ("Lname", btnlname.getText(), "[a-zA-Z]+")&&
               validate ("Pnum",btnpnum.getText(), "[0-9]+"));
               {int t1 = Integer.parseInt(btnpnum.getText()); 
        
        Deliveryman d1= new Deliveryman(btnfname.getText(), btnlname.getText(), Integer.parseInt(btnpnum.getText()), btnmail.getText());
       
       try {
            s.ajouter1(d1);
        } catch (SQLException ex) {
            Logger.getLogger(DeliverymanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initTable();
//        addbtn.setText("Creation was Successful :) ");
        clearAll();
        Notifications.create()
                .title("You have a new notification : ")
                .text("A new delivery was added")
                .showWarning();
    }
    }
    private void update(ActionEvent event) throws SQLException {
        Deliveryman d1;
        
        d1 = new Deliveryman(btnfname.getText(), btnlname.getText(), Integer.parseInt(btnpnum.getText()), btnmail.getText());
        s.update(d1);
        btnupdate.setText("Updated ");
        initTable();
       clearAll();
       Notifications.create()
                .title("You have a new notification : ")
                .text("A delivery was updated")
                .showWarning();
        
    }
 @FXML
    private void SelectedCell() {
    Deliveryman d1=table.getSelectionModel().getSelectedItem();
        
        btnfname.setText(d1.getFname());
        btnlname.setText(d1.getLname());
       
        btnpnum.setText(String.valueOf(d1.getPnum()));
        btnmail.setText(d1.getmail());
       
        
    }

    private void editcell(ActionEvent event) {
        SelectedCell();
    }

 @FXML
    private void delete(ActionEvent event) throws SQLException {
       
        Deliveryman h1=table.getSelectionModel().getSelectedItem();
        if (!h1.equals(null)) 
        s.delete(h1);
//        btndelete.setText("Delete was Successful :) ");
        initTable();
        Notifications.create()
                .title("You have a new notification : ")
                .text("A new delivery was deleted")
                .showWarning();
    }
      private void initTable() {
        
        try {
            oblist= (ObservableList<Deliveryman>) s.readAll();
            
            fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));
            lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            pnum.setCellValueFactory(new PropertyValueFactory<>("Pnum"));
            mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            System.out.println(oblist);
            table.setItems(oblist);
            
        } catch (SQLException ex) {
            Logger.getLogger(DeliverymanController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

//      private void SelectedCell() {
//    Deliveryman h1=table.getSelectionModel().getSelectedItem();
//        IDdelman.setText(intValue(h1.getidDman()));
//        fname.setText(h1.getFname());
//        lname.setText(h1.getLname());
//        pnum.setText(h1.getPnum());
//        mail.setText(String.valueOf(h1.getmail()));
//        
//        
//    }
      
    @FXML
    
    private void redirectToDM(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("BackHome.fxml"));
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    private void clear(ActionEvent event) {
        clearAll();
    }
    private void clearAll(){
   
        fname.setText("");
        lname.setText("");
        pnum.setText("");
        mail.setText("");}

    @FXML
    private void in(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DeliveryHome.fxml"));
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
       
    
    @FXML
    private void Rechercher(){
         FilteredList<Deliveryman>filteredData=new FilteredList<>(oblist,b -> true);
            enter.setOnKeyReleased(e->{
        enter.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super Deliveryman>)Deliveryman -> {
				// If filter text is empty, display all persons.
								
	if (newValue == null || newValue.isEmpty()) {
		return true;
	}
				
	// Compare first name and last name of every person with filter text.
	String lowerCaseFilter = newValue.toLowerCase();
				
	if (Deliveryman.getFname().toLowerCase().contains(lowerCaseFilter) ) {
        return true; // Filter matches first name.
	} else if (Deliveryman.getLname().toLowerCase().contains(lowerCaseFilter)) {
	return true; }
//       else if (Deliveryman.().toLowerCase().contains(lowerCaseFilter)) {
//	return true; }
				      
	return false; // Does not match.
	});
		});
        SortedList<Deliveryman>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(soretedData);
            });
     }
    
    public boolean validate (String field, String value, String pattern) {
    if (value.isEmpty()) {
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(value);
    
    if(m.find() && m.group().equals(value)) {
    return true;
    } else {
    validationAlert(field, false);
    return false; }
    }else {
        validationAlert(field, true);
        return false;}
    
    }
    
    private boolean emptyValidation(String field, boolean empty) {
    if (!empty) {
    return true;
    }else {
    validationAlert(field, true);
    return false; 
    }
    
    }
    
    private void validationAlert (String field, boolean empty){
    Alert alert= new Alert(AlertType.WARNING);
    alert.setTitle("Notice");
    alert.setHeaderText(null);
    if(field.equals("Role")) alert.setContentText ("Please Select"+ field);
            else {
     if (empty) alert.setContentText("Please Enter " +field);
     else alert.setContentText("Please Enter Valid"+field);
    }
    alert.showAndWait();
    }
//     public void rafraichir() {
//        ResultSet stat = null;
//        try {
//            stat = sp.getdeliveryman();
//        } catch (SQLException ex) {
//            Logger.getLogger(DeliverymanController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        chart.getData().clear();
//
//        try {
//            while (stat.next()) {
//                chart.getData().add(new PieChart.Data(stat.getString(1), stat.getDouble(2)));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DeliverymanController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    private void sees(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("piechart.fxml"));
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }


    
    }
    
    
    
    
    
    
    
    

