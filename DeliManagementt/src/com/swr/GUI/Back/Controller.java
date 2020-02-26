/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.email.durgesh.Email;
import com.swr.Entite.Delivery;
import com.swr.Entite.Deliveryman;
import com.swr.Services.ServiceDelivery;
import com.swr.Services.ServiceDman;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Controller implements Initializable {
ServiceDelivery fr = new ServiceDelivery();
    @FXML
    private Button btnOverview;
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
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button t;
    @FXML
    private Button btnOrder;
    @FXML
    private Button out;
    @FXML
    private TableView<Delivery> tab;
    @FXML
    private TableColumn<Delivery, String> date;
@FXML
    private TableColumn<?, String> item;

    ObservableList<Delivery> oblist=  FXCollections.observableArrayList();
//    private TableView<Delivery> table;
    
    @FXML
    private TextField btndate;
    @FXML
    private TextField btnitem;
    @FXML
    private Button adddbtn;
    private Button mdfbtn;
    @FXML
    private Button delbtn;
    @FXML
    private ComboBox<String> choose;
    @FXML
    private Button send;
    @FXML
    private TextField maill;
    ObservableList <String> arr =FXCollections.observableArrayList();
    @FXML
    private TableColumn<Delivery, Integer> col_id;
    @FXML
    private Button idassign;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inittab();
        ServiceDman d=new ServiceDman();
        arr=d.getalldelimen();
        choose.setItems(arr);
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
//        Notifications.create()
//                .title("You have a new notification : ")
//                .text("A new delivery was added")
//                .showWarning();
    }
    
@FXML
    private void ajouter(ActionEvent event) throws SQLException, MessagingException, UnsupportedEncodingException {
       
if(validate("Date", btndate.getText(), "[a-zA-Z]+") &&
               validate ("item", btnitem.getText(), "[a-zA-Z]+"));
               
        
                
        Delivery m1= new Delivery ( btndate.getText(), btnitem.getText());
       
       try {
            fr.ajouter1(m1);
        } catch (SQLException ex) {
            Logger.getLogger(DeliverymanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        inittab();
        adddbtn.setText("Created ");
        clearAll();
        
        Notifications.create()
                .title("You have a new notification : ")
                .text("A new delivery was added")
                .showWarning();
        
    }

    private void update(ActionEvent event) throws SQLException {
        Delivery m1;
        
        m1 = new Delivery(btndate.getText(),btnitem.getText());
        fr.update(m1);
        mdfbtn.setText("Updated ");
        inittab();
       clearAll();
        Notifications.create()
                .title("You have a new notification : ")
                .text("A delivery was updated")
                .showWarning();
    }
@FXML
    private void delete(ActionEvent event) throws SQLException {
       
        Delivery m1=tab.getSelectionModel().getSelectedItem();
        if (!m1.equals(null)) 
        fr.delete(m1);
//        delbtn.setText("Delete was Successful :) ");
        inittab();
        Notifications.create()
                .title("You have a new notification : ")
                .text("A delivery was deleted")
                .showWarning();
        
    }
@FXML
    private void SelectedCell() {
    Delivery m1=tab.getSelectionModel().getSelectedItem();
        
        btndate.setText(m1.getDateD());
        btnitem.setText((m1.getitem()));
      
    }

    private void editcell(ActionEvent event) {
        SelectedCell();
    }

    private void inittab() {
        
        try {
            oblist= (ObservableList<Delivery>) fr.readAll();
            
            date.setCellValueFactory(new PropertyValueFactory<>("DateD"));
            item.setCellValueFactory(new PropertyValueFactory<>("item"));
            
//            System.out.println(oblist);
            tab.setItems(oblist);
            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
	}


    @FXML
    private void Deliveryyy(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("BackHome.fxml"));
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void Dout(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Deliveryman.fxml"));
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
    
        btndate.setText("");
        btnitem.setText("");
    }

    @FXML
    private void send(ActionEvent event) throws MessagingException, UnsupportedEncodingException {
        Email email = new Email("montassar43","montassar007");
        email.setFrom("montassar43@gmail.com", "Solidarity With Refugees");
        email.setSubject("Get your password");
        email.setContent("<h1> mail :</h1>"+"mail here", "text/html");
        email.addRecipient(maill.getText());
        email.send();
    }

    @FXML
    private void affecter(ActionEvent event) {
     oblist=tab.getSelectionModel().getSelectedItems();
     
     ServiceDelivery s=new ServiceDelivery();
     s.assign(choose.getSelectionModel().getSelectedItem(), oblist.get(0).getIdDe());
     inittab();
        JOptionPane.showMessageDialog(null, choose.getSelectionModel().getSelectedItem() + " a été assigné à cette livraison son id est " + oblist.get(0).getIdDe(), "info", 1);
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

 
    
}
