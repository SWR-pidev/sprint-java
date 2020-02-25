/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.User;
import com.swr.Service.ServiceUser;
import com.swr.GUI.Back.HomeController;
import com.swr.GUI.Front.HomepageController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */
public class LoginController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button btnin;
    @FXML
    private Button btnup;
    @FXML
    private Label btnfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void passe(ActionEvent event) throws IOException {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("inscription.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        InscriptionController rc=fxml.getController();
    }

    @FXML
    private void signin(ActionEvent event) throws SQLException, IOException {
   
            String mai = mail.getText();
            String pwd1 = pwd.getText();
            ServiceUser user = new ServiceUser();
            int k=user.preauth(mai, pwd1);
            String u=user.getUser(mai);
            if(k==1)
            {
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        HomepageController rc=fxml.getController();
        rc.setLabelUser(u);
        rc.setImage(new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+user.getImage(mai)));
            }
            else if (k==2)
            {
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("/com/swr/GUI/Back/Home.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        HomeController rc=fxml.getController();
            }
            else{
            int i=user.auth(mai, pwd1);
            if(i==1)
            {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        HomepageController rc=fxml.getController();
        rc.setLabelUser(u);
        rc.setImage(new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+user.getImage(mai)));
            }
            else if (i==2)
            {
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("/com/swr/GUI/Back/Home.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        HomeController rc=fxml.getController();
            }
            else{
            JOptionPane.showMessageDialog(null,"User doesn't exist !!");
            }}
            
            
    }

    @FXML
    private void forgot(MouseEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("Forgot.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        ForgotController rc=fxml.getController();
    }
      
    
}
