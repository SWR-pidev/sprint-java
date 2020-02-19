/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Housing;
import com.swr.Service.HousingService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class HousingManagementController implements Initializable {

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
    private Pane pnlCustomer;
    @FXML
    private TableView<Housing> Htable;
    @FXML
    private Button newbtn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
   
    private HousingService hs;
    @FXML
    private TextField tfLocation;
    @FXML
    private TextArea taDesc;
    @FXML
    private Label lblMsg;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfNbRes;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfCapacity;
    @FXML
    private TableColumn<?, ?> col_Name;
    @FXML
    private TableColumn<?, ?> col_Desc;
    @FXML
    private TableColumn<?, ?> col_Type;
    @FXML
    private TableColumn<?, ?> col_Address;
    @FXML
    private TableColumn<?, ?> col_Capacity;
    @FXML
    private TableColumn<?, ?> col_NbRes;
    @FXML
    private TableColumn<?, ?> col_Location;
    ObservableList<Housing> oblist = FXCollections.observableArrayList();
    @FXML
    private Button editbtn;
    @FXML
    private TextField tfIdh;
    @FXML
    private TextField tfSearch;
    @FXML
    private Label lbItems;
    @FXML
    private Button clearbtn;

    public HousingManagementController() {
        this.hs = HousingService.getInstance();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
        Housing h1;
        if(validate("Name", tfName.getText(), "[a-zA-Z]+") && 
             
               validate("Address", tfAddress.getText(), "[a-zA-Z]+") &&
                validate("Capacity", tfCapacity.getText(), "[0-9]+") && 
                validate("Residents", tfNbRes.getText(), "[0-9]+") && 
                 validate("Location", tfLocation.getText(), "([0-9][0-9][.])([0-9]+[,])([0-9][0-9][.])([0-9]+)") &&
                  validate("Type", tfType.getText(), "[a-zA-Z]+") )  {
        
        int t1= Integer.parseInt(tfCapacity.getText());
        int t2= Integer.parseInt(tfNbRes.getText());
       
        
        h1 = new Housing(tfName.getText(),taDesc.getText(),tfAddress.getText(),tfLocation.getText(),t1,t2,tfType.getText());
        try {
            hs.addHousing(h1);
        } catch (SQLException ex) {
            Logger.getLogger(HousingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initTable();
        lblMsg.setText("Creation was Successful :) ");
        clearAll();}
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        Housing h1;
        int id= Integer.parseInt(tfIdh.getText());
        int t1= Integer.parseInt(tfCapacity.getText());
        int t2= Integer.parseInt(tfNbRes.getText());
        h1 = new Housing(id,tfName.getText(),taDesc.getText(),tfAddress.getText(),tfLocation.getText(),t1,t2,tfType.getText());
        hs.updateHousing(h1);
        lblMsg.setText("Update was Successful :) ");
        initTable();
       clearAll();
        
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
       
        Housing h1=Htable.getSelectionModel().getSelectedItem();
        if (!h1.equals(null)) 
        hs.deleteHousing(h1);
        lblMsg.setText("Delete was Successful :) ");
        initTable();
        
    }
    private void initTable() {
        
        try {
            oblist= (ObservableList<Housing>) hs.getAllHousings();
            col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_Desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
            col_Location.setCellValueFactory(new PropertyValueFactory<>("location"));
            col_Capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
            col_NbRes.setCellValueFactory(new PropertyValueFactory<>("nbresidents"));
            col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            Htable.setItems(oblist);
            
        } catch (SQLException ex) {
            Logger.getLogger(HousingManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
    private void SelectedCell() {
    Housing h1=Htable.getSelectionModel().getSelectedItem();
        tfIdh.setText(String.valueOf(h1.getIdh()));
        tfName.setText(h1.getName());
        taDesc.setText(h1.getDescription());
        tfAddress.setText(h1.getAddress());
        tfCapacity.setText(String.valueOf(h1.getCapacity()));
        tfLocation.setText(h1.getLocation());
        tfNbRes.setText(String.valueOf(h1.getNbresidents()));
        tfType.setText(h1.getType());
        
    }

    @FXML
    private void editcell(ActionEvent event) {
        SelectedCell();
    }

    @FXML
    private void redToItems(MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ItemsManagement.fxml"));
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void clear(ActionEvent event) {
        clearAll();
    }
    private void clearAll(){
    tfIdh.setText("");
        tfName.setText("");
        taDesc.setText("");
        tfAddress.setText("");
        tfCapacity.setText("");
        tfLocation.setText("");
        tfNbRes.setText("");
        tfType.setText("");}
    
    
    @FXML
      private void recherche(){
      
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Housing> filteredData = new FilteredList<>(oblist, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(housing -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (housing.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (housing.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Housing> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(Htable.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        Htable.setItems(sortedData);
    }
      private boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
		Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		}        
    }
	
	private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
        	validationAlert(field, true);            
            return false;            
        }
    }	
	
	private void validationAlert(String field, boolean empty){
	Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	} 
}
