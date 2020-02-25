/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class BackHomeController implements Initializable {
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
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button btncmp;
    @FXML
    private Button donations;
 private Connection con;
    private Statement ste;
     ObservableList<PieChart.Data>list1=FXCollections.observableArrayList();
    @FXML
    private Label totaldon;
    @FXML
    private PieChart pie;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(BackHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pie.setData(list1);
        pie.setTitle("Compaigns where Donors Checked Keep Us Alive");
        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DonationSpace.fxml"));
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
     public void loadData() throws SQLException
    {
         con = DataBase.getInstance().getConnection();
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select c.name_cmp , d.funds from compaign c INNER JOIN donation d on c.id_cmp=d.id_cmp ;");
            while (rs.next()) {
                list1.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
            }
}




}
