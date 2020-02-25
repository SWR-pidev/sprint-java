/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;


import com.swr.Entite.News;
import com.swr.Entite.User;
import com.swr.Service.ServiceNews;
import com.swr.Service.ServiceUser;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */
public class NewsfController implements Initializable {
    private Image image;
    ServiceNews news = new ServiceNews();
    ServiceUser user = new ServiceUser();
    ObservableList oblist = FXCollections.observableArrayList();
    @FXML
    private Label userlabel;
    @FXML
    private ImageView logout;
    @FXML
    private Label nbnotif;
    @FXML
    private Button profile;
    @FXML
    private ImageView btnprofile;
    @FXML
    private Button btnnews;
    @FXML
    private ListView<ObservableList<News>> List;
    @FXML
    private TextArea labdesc;
    @FXML
    private Label datelab;
    @FXML
    private Label catlab;
    @FXML
    private ImageView newim;
    @FXML
    private Label descfix;
    @FXML
    private Label datefix;
    @FXML
    private Label catfix;
    @FXML
    private ImageView improfil;
 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        cleaar();
    }
        public void setImage(Image image) {
       this.improfil.setImage(image);
    }
        private void initTable() {
       
        try {
           List.refresh();
            oblist= (ObservableList<News>) news.list() ;
            List.getItems().addAll(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(NewsfController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        private void cleaar()
        {
        descfix.setVisible(false);
        datefix.setVisible(false);
        catfix.setVisible(false);
        labdesc.setVisible(false);

        }
            private void upall(){
        News c=(News) List.getSelectionModel().getSelectedItem();
        try {
            labdesc.setText(news.searchdesc(c.getTitre()));
            datelab.setText(news.searchdate(c.getTitre()));
            catlab.setText(news.searchnomcat(c.getTitre()));
            image=new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+news.search(c.getTitre()));
            newim.setImage(image);
            descfix.setVisible(true);
            datefix.setVisible(true);
            catfix.setVisible(true);
            labdesc.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(NewsfController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
     public void setLabelUser(String userlabel) {
        this.userlabel.setText(userlabel);
    }    

    @FXML
    private void logout(MouseEvent event) {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root;
        try {
            root = fxml.load();
            logout.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(NewsfController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void account(ActionEvent event) {
        
         try {
             FXMLLoader fxml=new FXMLLoader(getClass().getResource("User.fxml"));
        Parent root=fxml.load();
        logout.getScene().setRoot(root);
        UserController rc=fxml.getController();
        rc.setLabelUser(userlabel.getText());
            rc.setImage(new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+user.getImageu(userlabel.getText())));
            User u = user.SerachUser(userlabel.getText());
            rc.setTNom(u.getNom());
            rc.setTPnom(u.getPrenom());
            rc.setTel(u.getTel());
            rc.setCount(u.getCountry());
            rc.setUser(userlabel.getText());
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewsfController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void newws(ActionEvent event) {
    }

    @FXML
    private void listclick(MouseEvent event) {
       upall();
    }
    
}
