/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.User;
import com.swr.Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

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
    private DatePicker date1;
    private DatePicker date2;
    private LineChart<String, Number> LineChart;
    private NumberAxis y;
    private CategoryAxis x;
    @FXML
    private Label moylab;
    @FXML
    private BarChart<?, ?> chart_bar;
    @FXML
    private TextField sear;
    @FXML
    private Button deletebtn;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        try {
            this.moy();
            this.rafraichir();
            this.Rechercher();
        } catch (SQLException ex) {
            Logger.getLogger(UserbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rafraichir()
    {
          ResultSet stat = null;
           XYChart.Series dataSeries1 = new XYChart.Series();
        
        try {
            stat= user.barchart();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        try {
            while(stat.next())
            {
                  dataSeries1.getData().add(new XYChart.Data(stat.getString(1),stat.getInt(2)));
              
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        chart_bar.getData().clear();
        chart_bar.getData().add(dataSeries1);
     }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("News.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
    }

    @FXML
    private void user(ActionEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("Userb.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
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
   private void Rechercher(){
         FilteredList<User>filteredData=new FilteredList<>(oblist,b -> true);
            sear.setOnKeyReleased(e->{
        sear.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super User>)User -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (User.getPrenom().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (User.getUsername().toLowerCase().contains(lowerCaseFilter)) {
					return true; }
                                else if (User.getTel().toLowerCase().contains(lowerCaseFilter)) {
					return true; }
				//else if (Reclamation.getIdR().contains(newValue)){
				   // return true;}
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<User>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(Userview.comparatorProperty());
        Userview.setItems(soretedData);
            });
     }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
    User c=Userview.getSelectionModel().getSelectedItem();
        if (c==null)
        {
      JOptionPane.showMessageDialog(null,"There is nothing selected !");
        }
        else{
  
          user.delete(c.getUsername());
        initTable();
        this.moy();
        this.rafraichir();

        }
    }
    private void moy() throws SQLException
    {
    int i=user.moyenne();
    moylab.setText(""+i);
    }

    private void rechercher(ActionEvent event) {
        LocalDate date11=date1.getValue();
        LocalDate date22=date2.getValue();
        String chainedate1="";
        String chainedate2="";
        if(date11 != null)
        {
            chainedate1=date11.toString();
        }
         if(date22 != null)
        {
            chainedate2=date22.toString();
        }
       
        try {
            oblist= (ObservableList<User>) user.rechercheentredate(chainedate1, chainedate2) ;
        
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            col_count.setCellValueFactory(new PropertyValueFactory<>("country"));
            col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            col_us.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("dateins"));
            Userview.getItems().clear();
            Userview.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(UserbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
                FXMLLoader fxml=new FXMLLoader(getClass().getResource("/com/swr/gui/front/login.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
    }
    
}
