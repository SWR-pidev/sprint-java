/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Service.ServicePosts;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Soulah
 */
public class StatsController implements Initializable {
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
    private TextField searchbar;
    @FXML
    private Button cmtbtn;
    @FXML
    private Button statsbtn;
    @FXML
    private PieChart piechart;
    @FXML
    private BarChart<?, ?> barchart;

    @FXML
    private void handleClicks(ActionEvent event) {
    }
     public void initialize(URL url, ResourceBundle rb) {
         stat();
    

    }    
     
     private void stat(){
         
       ServicePosts serP = new ServicePosts();
       
        ObservableList<PieChart.Data> pieChartData;
        DecimalFormat df = new DecimalFormat("#.##");
        double x = 0;
        double y = 0;
        double a = 0;
        try {
            x=(double) serP.countid();
            System.out.println(x);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            y=(double) serP.countgt0();
            System.out.println(y);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            a=(double) serP.countAll();
            System.out.println(a);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        x=(x/a)*100.00;
        y=(y/a)*100.00;
        System.out.println(x);
        System.out.println(y);
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Posts where Views =0 "+df.format(x)+"%", x),
                new PieChart.Data("Posts where Views >0 "+df.format(y)+"%", y));
       piechart.setData(pieChartData);

     }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void gotoComnts(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ComntBack.fxml"));
            try {
                Parent root = loader.load();
                cmtbtn.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void gotoPosts(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Postsgui.fxml"));
            try {
                Parent root = loader.load();
                cmtbtn.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
                       FXMLLoader fxml=new FXMLLoader(getClass().getResource("/com/swr/gui/front/login.fxml"));
        Parent root=fxml.load();
        cmtbtn.getScene().setRoot(root);
    }
    
}
