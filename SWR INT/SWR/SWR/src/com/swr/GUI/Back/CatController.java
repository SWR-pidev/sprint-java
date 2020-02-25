/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Categorie;
import com.swr.Entite.News;
import com.swr.Service.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
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
public class CatController implements Initializable {

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
    private TableView<Categorie> Catview;
    @FXML
    private TableColumn<?, ?> cl_tit;
    ServiceCategorie cat = new ServiceCategorie();
    ObservableList<Categorie> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField catt;
    @FXML
    private Button addbtn;
    @FXML
    private Button btndel;
    @FXML
    private TextField upcat;
    @FXML
    private Button upbtn;
    @FXML
    private Label labcat;
    @FXML
    private Label labcatup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
    }
    public void init()
    {
    try {
            oblist= (ObservableList<Categorie>) cat.affichecat() ;
            
            cl_tit.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
            Catview.setItems(oblist);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserbController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    private void ajoutcat(ActionEvent event) throws IOException {
    
        try {
           if(catt.getText().isEmpty())
           {JOptionPane.showMessageDialog(null,"please fill all the textfields"); }
           else{
           if(catt.getText().matches("^[a-zA-Z]+$") && catt.getText().length()>4)
             {
            if(cat.sercat(catt.getText())==0)
            {
             Categorie u = new Categorie(catt.getText());
            cat.ajouter(u);
            init();
            clearAll();
            labcat.setText("");
            }
            else{ JOptionPane.showMessageDialog(null,"Category name exists ");}
             }
           else{labcat.setText("**The structure of "+catt.getText()+" is not correct**");  }
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
     Categorie c=Catview.getSelectionModel().getSelectedItem();
        if (c==null) {
        JOptionPane.showMessageDialog(null,"There is nothing selected !");
        }
        else{cat.delete(c.getNomcat());
        init();}
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
       if(upcat.getText().isEmpty())
           {JOptionPane.showMessageDialog(null,"please fill all the textfields"); }
           else{
           if(upcat.getText().matches("^[a-zA-Z]+$") && upcat.getText().length()>4)
             {
            if(cat.sercat(upcat.getText())==0)
            {
        Categorie c=Catview.getSelectionModel().getSelectedItem();
        cat.update(c.getNomcat(),upcat.getText());
        
        init();
        clearAll();
            labcatup.setText("");}
            else{ JOptionPane.showMessageDialog(null,"Category name exists ");}
             }
           else{labcatup.setText("**The structure of "+upcat.getText()+" is not correct**");  }
           }
    }
    
    private void clear(ActionEvent event) {
        clearAll();
    }
    private void clearAll(){
    
        catt.setText("");
        upcat.setText("");
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
                FXMLLoader fxml=new FXMLLoader(getClass().getResource("/com/swr/gui/front/login.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
    }
}
    

