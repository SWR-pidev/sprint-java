/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Compaign;
import com.swr.Entite.Donation;
import com.swr.Entite.Proposition;
import com.swr.Service.ServiceCompaign;
import com.swr.Service.ServiceDonation;
import com.swr.Service.ServiceProposition;
import com.swr.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author Monta
 */
public class CompaignSpaceController implements Initializable {
    @FXML
    private Button btncmp;
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
    private Button btnhome;
    @FXML
    private Button addbtn;
    @FXML
    private Button goprop;
    @FXML
    private Button btnOrder;
    @FXML
    private Button btnSignou;
    @FXML
    private TableView<Compaign> view;
    @FXML
    private TableColumn<?, ?> cmpname;
    @FXML
    private TableColumn<?, ?> trgt;
    @FXML
    private TableColumn<?, ?> raised;
    @FXML
    private TableColumn<?, ?> descr;
    @FXML
    private TableColumn<?, ?> lin;
    @FXML
    private TableColumn<?, ?> nbdon;
    @FXML
    private TableColumn<?, ?> still;
 ObservableList<Compaign> oblist = FXCollections.observableArrayList();
 
 
 
 ServiceCompaign ser = new ServiceCompaign();
 @FXML
    private  ComboBox<String> orderby;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TextField search;
    @FXML
    private Button ch;
    @FXML
    private PieChart chart;
    @FXML
    private PieChart pie2;
 private Connection con;
    private Statement ste;
    ObservableList<PieChart.Data>list1=FXCollections.observableArrayList();
       ObservableList<PieChart.Data>list2=FXCollections.observableArrayList();
    @FXML
    private Button donation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(CompaignSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Number Donors",
        "Compaign Name",
        "Almost Funded","Funded"
    ); 
        orderby.setItems(options);
 
        chart.getData().clear();
    
               // chart.getData().add(new PipieChart.Data(ms1,ms2));
     try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(CompaignSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        chart.setData(list1);
        chart.setTitle("Number Of Donors Per Compaign");
      
    
               // chart.getData().add(new PipieChart.Data(ms1,ms2));
     try {
            loadData2();
        } catch (SQLException ex) {
            Logger.getLogger(CompaignSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pie2.setData(list2);
        pie2.setTitle("Remainings Per Compaign");
     
    }    
private void initTable() throws SQLException  {
        
      
         //   ObservableList<List<Proposition>> data = FXCollections.observableArrayList();
     //   List<Proposition> events = new ArrayList<>();
            
        try {
            oblist = (ObservableList<Compaign>) ser.readAllCmp();
            
            cmpname.setCellValueFactory(new PropertyValueFactory<>("namecmp"));
            raised.setCellValueFactory(new PropertyValueFactory<>("trgt"));
             trgt.setCellValueFactory(new PropertyValueFactory<>("raised"));
              descr.setCellValueFactory(new PropertyValueFactory<>("desc"));
               lin.setCellValueFactory(new PropertyValueFactory<>("url"));
                  nbdon.setCellValueFactory(new PropertyValueFactory<>("nbdon"));
                  still.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
            view.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(PropositionSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
     
  
        }
    @FXML
    private void handleClicks(ActionEvent event) {
    }
 @FXML
    private void home(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("BackHome.fxml"));
        Parent root = loader.load();
          
          btncmp.getScene().setRoot(root); 
    }
    @FXML
    private void compaign(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("CompaignSpace.fxml"));
        Parent root = loader.load();
          
          btncmp.getScene().setRoot(root);
    }

    @FXML
    private void addcmp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AddCompaign.fxml"));
        Parent root = loader.load();
          
          addbtn.getScene().setRoot(root);
    }

    @FXML
    private void GotoProp(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("PropositionSpace.fxml"));
        Parent root = loader.load();
          
          goprop.getScene().setRoot(root);
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        if (view.getSelectionModel().getSelectedItem()!=null)
        {
        
        Compaign e=view.getSelectionModel().getSelectedItem();
      
        ser.deleteCmp(e);
        initTable();}
        else {
              Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("please select an item first !!");
                alert.showAndWait();  
                }
        }
    

    @FXML
    private void update(ActionEvent event) throws IOException {
        if (view.getSelectionModel().getSelectedItem()!=null)
        {
            
          Compaign  selectedCmp = view.getSelectionModel().getSelectedItem();
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("UpdateCompaign.fxml"));
        Parent root = loader.load();
            UpadteCompaignController  eec = loader.getController();
         eec.setId(selectedCmp.getId_cmp());
        eec.setLink(selectedCmp.getUrl());
        eec.setName(selectedCmp.getNamecmp());
         eec.setTarget(Double.toString(selectedCmp.getTrgt()));
         eec.setDescription(selectedCmp.getDesc());
         
           btncmp.getScene().setRoot(root);
        }  
         else {
             Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setContentText("please select an item first !!");
                alert.showAndWait();
         }
    }

    @FXML
    private void trie(ActionEvent event) throws SQLException {
        if(orderby.getSelectionModel().isSelected(0))
        {
            oblist = (ObservableList<Compaign>) ser.trinbdonCmp();      
        }
        
        if(orderby.getSelectionModel().isSelected(1))
        {

            oblist = (ObservableList<Compaign>) ser.triNomCmp();
          
        }
        if(orderby.getSelectionModel().isSelected(2)){
       
            oblist = (ObservableList<Compaign>) ser.triStillNeeded();
        }
        if(orderby.getSelectionModel().isSelected(3)){
       
            oblist = (ObservableList<Compaign>) ser.FundedCmp();
            
        }
        if(orderby.getSelectionModel().isSelected(4)){
       
            oblist = (ObservableList<Compaign>) ser.AlmostFundedCmp();
            
        }
             cmpname.setCellValueFactory(new PropertyValueFactory<>("namecmp"));
            raised.setCellValueFactory(new PropertyValueFactory<>("trgt"));
             trgt.setCellValueFactory(new PropertyValueFactory<>("raised"));
              descr.setCellValueFactory(new PropertyValueFactory<>("desc"));
               lin.setCellValueFactory(new PropertyValueFactory<>("url"));
                  nbdon.setCellValueFactory(new PropertyValueFactory<>("nbdon"));
                  still.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
            view.setItems(oblist);
    }

    @FXML
    private void search(KeyEvent event) {
             
        try {
            oblist = (ObservableList<Compaign>) ser.rechercheAvCmp(search.getText());
            
            cmpname.setCellValueFactory(new PropertyValueFactory<>("namecmp"));
            trgt.setCellValueFactory(new PropertyValueFactory<>("trgt"));
             raised.setCellValueFactory(new PropertyValueFactory<>("raised"));
              descr.setCellValueFactory(new PropertyValueFactory<>("desc"));
               lin.setCellValueFactory(new PropertyValueFactory<>("url"));
                  nbdon.setCellValueFactory(new PropertyValueFactory<>("nbdon"));
                  still.setCellValueFactory(new PropertyValueFactory<>("stillneeded"));
            view.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(PropositionSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void check(ActionEvent event) throws IOException {
        if (view.getSelectionModel().getSelectedItem()!=null)
        {
        int d= view.getSelectionModel().getSelectedItem().getId_cmp();
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("JointureDonation.fxml"));
        Parent root = loader.load();
          JointureDonationController jo=loader.getController();
          jo.idcmp=d;
          jo.initTable();
          goprop.getScene().setRoot(root);
        }
    }
 public void loadData() throws SQLException
    {
         con = DataBase.getInstance().getConnection();
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select name_cmp,nbdon from compaign ");
            while (rs.next()) {
                list1.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
            }
        
    }
    public void loadData2() throws SQLException
    {
         con = DataBase.getInstance().getConnection();
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select name_cmp,stillneeded from compaign ");
            while (rs.next()) {
                list2.add(new PieChart.Data(rs.getString(1), rs.getDouble(2)));
            }
        
    }

    @FXML
    private void donationclick(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DonationSpace.fxml"));
        Parent root = loader.load();
          
          goprop.getScene().setRoot(root);
    }
    
}




    

