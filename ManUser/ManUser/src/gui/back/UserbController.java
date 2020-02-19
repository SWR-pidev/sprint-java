/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import Entite.User;
import Service.ServiceUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */
public class UserbController implements Initializable {

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
    private Button btnSignout;
    @FXML
    private Button btnUser;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<User> Userview;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_prenom;
    @FXML
    private TableColumn<?, ?> col_count;
    @FXML
    private TableColumn<?, ?> col_mail;
    @FXML
    private TableColumn<?, ?> col_tel;
    @FXML
    private TableColumn<?, ?> col_us;
    @FXML
    private TableColumn<?, ?> col_role;
    @FXML
    private TableColumn<?, ?> col_date;
    ObservableList<User> oblist = FXCollections.observableArrayList();
    ServiceUser user = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void user(ActionEvent event) {
    }
    private void initTable() {
        
        try {
            oblist= (ObservableList<User>) user.readAll() ;
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            col_count.setCellValueFactory(new PropertyValueFactory<>("country"));
            col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            col_us.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateins"));
            Userview.setItems(oblist);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserbController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
    
}
