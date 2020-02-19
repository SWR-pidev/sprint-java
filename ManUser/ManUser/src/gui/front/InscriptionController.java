/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import Entite.User;
import Service.ServiceUser;
import Service.Servicebcrypt;
import Utils.staticvar;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private Button btnupp;
    @FXML
    private PasswordField pwd1;
    @FXML
    private TextField tel;
    @FXML
    private TextField country;
    @FXML
    private TextField username;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouteruser(ActionEvent event) {
    try {
            String mai = mail.getText();
            String tell = tel.getText();
            String usernam = username.getText();
            String nomm = nom.getText();
            String prenomm = prenom.getText();
            String pwd = pwd1.getText();
            String countryy = country.getText();
            ServiceUser user = new ServiceUser();
            String crypted = Servicebcrypt.hashpw(pwd, staticvar.SALT);
            User u = new User(nomm,prenomm,countryy,mai,crypted,tell,usernam,"user");
            user.ajouter(u);
            
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("login.fxml"));
            try {
                Parent root = loader.load();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void back(MouseEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        InscriptionController rc=fxml.getController();
    }
    
}
