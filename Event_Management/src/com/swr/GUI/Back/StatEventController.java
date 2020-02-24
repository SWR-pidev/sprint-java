/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Utils.DataBase;
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
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class StatEventController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnnOrders;
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
    private Button btnSignount;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private PieChart pieviews;
    @FXML
    private BarChart<String, Integer> barpartici;
private Connection con;
    private Statement ste;
    ObservableList<PieChart.Data> data2 = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> dataS = FXCollections.observableArrayList();
    @FXML
    private PieChart pieviewsS;
    @FXML
    private BarChart<String, Integer> barorga;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(StatEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pieviews.setData(data2);
        pieviews.setTitle("Events per Number of Views");
        pieviews.setLegendSide(Side.BOTTOM);
        pieviews.setLabelsVisible(true);
        try {
            loadBar();
        } catch (SQLException ex) {
            Logger.getLogger(StatEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        barpartici.setTitle("Events per number of participations");
        try {
            loadDataS();
        } catch (SQLException ex) {
            Logger.getLogger(StatEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
         pieviewsS.setData(dataS);
        pieviewsS.setTitle("Events Sponsor per Number of Views");
        pieviewsS.setLegendSide(Side.BOTTOM);
        pieviewsS.setLabelsVisible(true);
        
        try {
            loadBarOrga();
        } catch (SQLException ex) {
            Logger.getLogger(StatEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
         barorga.setTitle("Organizator per number of participations");
    }    

     
    public void loadData() throws SQLException
    {
         con = DataBase.getInstance().getConnection();
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select name_evt,nbviews_evt from event where state_evt=1");
            while (rs.next()) {
                data2.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
            }
        
    }
    public void loadBar() throws SQLException
    {
        con = DataBase.getInstance().getConnection();
            ste=con.createStatement();
            XYChart.Series<String,Integer> series= new XYChart.Series<>();
            ResultSet rs=ste.executeQuery("select name_evt,nbparticipant_evt from event where state_evt=1");
            while (rs.next()) {
               series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
            }
            barpartici.getData().add(series);
    }
    public void loadDataS() throws SQLException
    {
         con = DataBase.getInstance().getConnection();
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select name_evt,nbviews_evt from event where state_evt=0 or ISNULL(state_evt)");
            while (rs.next()) {
                dataS.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
            }
        
    }
    public void loadBarOrga() throws SQLException
    {
        con = DataBase.getInstance().getConnection();
            ste=con.createStatement();
            XYChart.Series<String,Integer> series= new XYChart.Series<>();
            ResultSet rs=ste.executeQuery("select organizator_evt,SUM(nbparticipant_evt) from event where state_evt=1 GROUP BY organizator_evt ");
            while (rs.next()) {
               series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
            }
            barorga.getData().add(series);
    }
    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void DisplayEventSpace(ActionEvent event) {
    }
    
}
