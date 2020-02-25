/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.User;
import com.swr.Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */
public class HomepageController implements Initializable {
    ServiceUser user = new ServiceUser();

    @FXML
    private Label nbnotif;
    @FXML
    private ImageView logout;
    @FXML
    private Button btnprofile;
    @FXML
    private Label userlabel;
    @FXML
    private Button btnnews;
    private Image image;
    @FXML
    private ImageView improfil;
    @FXML
    private Button blogbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void logout(MouseEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        logout.getScene().setRoot(root);
        LoginController rc=fxml.getController();
    }
    public void setLabelUser(String userlabel) {
        this.userlabel.setText(userlabel);
    }
    public void setImage(Image image) {
       this.improfil.setImage(image);
    }

    @FXML
    private void account(ActionEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("User.fxml"));
        Parent root=fxml.load();
        logout.getScene().setRoot(root);
        UserController rc=fxml.getController();
        rc.setLabelUser(userlabel.getText());
         try {
            rc.setImage(new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+user.getImageu(userlabel.getText())));
            User u = user.SerachUser(userlabel.getText());
            rc.setTNom(u.getNom());
            rc.setTPnom(u.getPrenom());
            rc.setTel(u.getTel());
            rc.setCount(u.getCountry());
            rc.setUser(userlabel.getText());
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void newws(ActionEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("Newsf.fxml"));
        Parent root=fxml.load();
        logout.getScene().setRoot(root);
        NewsfController rc=fxml.getController();
        rc.setLabelUser(userlabel.getText());
        try {
            rc.setImage(new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+user.getImageu(userlabel.getText())));
        } catch (SQLException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotoblog(ActionEvent event) throws IOException {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("PostsInterface.fxml"));
        Parent root=fxml.load();
        logout.getScene().setRoot(root);
        PostintController rc=fxml.getController();
                rc.setLabelUser(userlabel.getText());
        try {
            rc.setImage(new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+user.getImageu(userlabel.getText())));
        } catch (SQLException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
