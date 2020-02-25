/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.User;
import com.swr.Service.ServiceUser;
import com.swr.Service.Servicebcrypt;
import com.swr.Utils.staticvar;
import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import rest.file.uploader.tn.FileUploader;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */
public class UserController implements Initializable {

    @FXML
    private ImageView logout;
    @FXML
    private Label nbnotif;
    @FXML
    private TextField nom;
    @FXML
    private TextField pwd;
    @FXML
    private TextField tel;
    @FXML
    private TextField country;
    @FXML
    private TextField prenom;
    @FXML
    private Button btnmod;
    @FXML
    private Button btndel;
    @FXML
    private TextField btnuser;
    @FXML
    private Label userlabel;
    @FXML
    private ImageView improfil;
    private Image image;
    @FXML
    private Button neew;
    ServiceUser user = new ServiceUser();
    @FXML
    private Button browzeup;
    private String lienup="";
    @FXML
    private Label labpwd;
    @FXML
    private Label labtel;
    @FXML
    private Label labcountry;
    @FXML
    private Label labname;
    @FXML
    private Label labname1;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            
       }
    public void setImage(Image image) {
       this.improfil.setImage(image);
    }
    
    public void setLabelUser(String userlabel) {
        this.userlabel.setText(userlabel);
    }
    public void setTNom(String nom) {
        this.nom.setText(nom);
    } 
    public void setTPnom(String prenom) {
        this.prenom.setText(prenom);
    }
    public void setTel(String tel) {
        this.tel.setText(tel);
    }
    public void setCount(String country) {
        this.country.setText(country);
    }
    public void setUser(String btnuser) {
        this.btnuser.setText(btnuser);
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        logout.getScene().setRoot(root);
        LoginController rc=fxml.getController();
    }

    @FXML
    private void modify(ActionEvent event) throws SQLException, IOException {
    
            String tell = tel.getText();
            String nomm = nom.getText();
            String prenomm = prenom.getText();
            String pwd1 = pwd.getText();
            String countryy = country.getText();
            FileUploader fu = new FileUploader("localhost/swr/web/");
            String fileNameInServer;
            if(lienup=="" || tell.isEmpty() || nomm.isEmpty() || prenomm.isEmpty() || pwd1.isEmpty() || countryy.isEmpty()){
            JOptionPane.showMessageDialog(null,"please fill all the textfields and choose a picture");
            }
            else{
            if(nomm.matches("^[a-zA-Z]+$")){
            if(prenomm.matches("^[a-zA-Z]+$"))
            {
              if(pwd1.length()>8)
              {
               if(countryy.matches("^[a-zA-Z]+$"))
               {

                    if(tell.matches("^[0-9]+$") && tell.length()==8)
                    {

                     if(user.getTel(tell)==0)
                 {
            fileNameInServer = fu.upload(lienup);
            String crypted = Servicebcrypt.hashpw(pwd1, staticvar.SALT);
            User u = new User(nomm,prenomm,countryy,crypted,tell,fileNameInServer);
            user.modify(u,btnuser.getText());
            this.setImage(new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+user.getImageu(userlabel.getText())));
            
            }else {
                JOptionPane.showMessageDialog(null,"Number phone: "+tell+" exist !");
             }
                 }
                    
                    else{
              labtel.setText("**The structure of "+tell+" is not correct**");
              }
                    
                }
                else{
              labcountry.setText("**The structure of "+countryy+" is not correct**");
              }
               }
              else{
              labpwd.setText("**your password must exceed 8 characters**");
              }
               
              }
              else{
              labname.setText("**The structure of "+prenomm+" is not correct**");
              }
            }
             else{
             labname1.setText("**The structure of "+nomm+" is not correct**");
             }
            }
            }
            

    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
     
            String name = btnuser.getText();
            ServiceUser user = new ServiceUser();
            user.delete(name);
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        logout.getScene().setRoot(root);
        LoginController rc=fxml.getController();
    }

    @FXML
    private void neww(ActionEvent event) {
        
        try {
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("Newsf.fxml"));
        Parent root=fxml.load();
        logout.getScene().setRoot(root);
        NewsfController rc=fxml.getController();
        rc.setLabelUser(userlabel.getText());
            rc.setImage(new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+user.getImageu(userlabel.getText())));
        } catch (SQLException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void imagelien(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpeg files (*.jpg)", "*.jpg","jpeg files (*.png)", "*.png","jpeg files (*.jpeg)", "*.jpeg");
              fileChooser.getExtensionFilters().add(extFilter);
             Window primaryStage = null;
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null) return ;
            this.lienup=file.getPath();
    }
    }
    

