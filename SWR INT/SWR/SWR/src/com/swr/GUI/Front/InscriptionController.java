/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;
import java.util.regex.*;
import com.swr.Entite.User;
import com.swr.Service.ServiceUser;
import com.swr.Service.Servicebcrypt;
import com.swr.Utils.staticvar;
import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import rest.file.uploader.tn.FileUploader;

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
    private String lienup="";
    @FXML
    private Label labtel;
    @FXML
    private Label labmail;
    @FXML
    private Label labcountry;
    @FXML
    private Label labpwd;
    @FXML
    private Label labusername;
    @FXML
    private Label labname1;
    @FXML
    private Label labname;
    @FXML
    private Button browse;
    @FXML
    private Button cam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouteruser(ActionEvent event) throws SQLException {
            String mai = mail.getText();
            String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(mai);
            String tell = tel.getText();
            String usernam = username.getText();
            String nomm = nom.getText();
            String prenomm = prenom.getText();
            String pwd = pwd1.getText();
            String countryy = country.getText();
            ServiceUser user = new ServiceUser();
            FileUploader fu = new FileUploader("localhost/swr/web/");
            String fileNameInServer;
        try {
            if(lienup=="" || tell.isEmpty() || usernam.isEmpty() || nomm.isEmpty() || prenomm.isEmpty() || pwd.isEmpty() || countryy.isEmpty()){
            JOptionPane.showMessageDialog(null,"please fill all the textfields and choose a picture");
            }
            else{
            if(controler.matches()){
             if(nomm.matches("^[a-zA-Z]+$")){
            if(prenomm.matches("^[a-zA-Z]+$"))
            {
              if(pwd.length()>8)
              {
               if(countryy.matches("^[a-zA-Z]+$"))
               {
                if(usernam.matches("^[a-zA-Z]+[a-zA-Z0-9]+$") && usernam.length()>6)
                {
                    if(tell.matches("^[0-9]+$") && tell.length()==8)
                    {
             if(user.getUs(usernam)==0)
             {
                 if(user.getma(mai)==0)
                 {
                     if(user.getTel(tell)==0)
                 {
            fileNameInServer = fu.upload(lienup);
                        String crypted = Servicebcrypt.hashpw(pwd, staticvar.SALT);
            User u = new User(nomm,prenomm,countryy,mai,crypted,tell,usernam,"user",fileNameInServer);
            user.ajouter(u);
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        LoginController rc=fxml.getController();
             
                 }
                     else {
                JOptionPane.showMessageDialog(null,"Number phone: "+tell+" exist !");
             }
                 }
                 else {
                JOptionPane.showMessageDialog(null,"E-mail: "+mai+" exist !");
             }
             }
             else {
                JOptionPane.showMessageDialog(null,"Username: "+usernam+" exist !");
             }
                    }
                    else{
              labtel.setText("**The structure of "+tell+" is not correct**");
              }
                    
                }
                else{
              labusername.setText("**The structure of "+usernam+" is not correct**");
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
            labname1.setText("**The structure of "+prenomm+" is not correct**");
            }
             }
             else{
             labname.setText("**The structure of "+nomm+" is not correct**");
             }
            }
            else{
            labmail.setText("**The structure of "+mai+" is not correct**");
                    }}
            
        } catch (ProtocolException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void back(MouseEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        LoginController rc=fxml.getController();
    }

    @FXML
    private void browze(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpeg files (*.jpg)", "*.jpg","jpeg files (*.png)", "*.png","jpeg files (*.jpeg)", "*.jpeg");
              fileChooser.getExtensionFilters().add(extFilter);
             Window primaryStage = null;
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null) return ;
            this.lienup=file.getPath();
            cam.setVisible(false);
    }

    @FXML
    private void webcam(ActionEvent event) {
    Webcam webcam = Webcam.getDefault();
		webcam.open();
Random rand = new Random();
            int alea=rand.nextInt(200000 - 10 + 1) + 200000;
		// get image
		BufferedImage image = webcam.getImage();

        try {
            // save image to PNG file
            File f=new File("C:\\wamp64\\www\\swr\\web\\uploads\\"+alea+".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
           this.lienup=f.getAbsolutePath();
            System.out.println(this.lienup);
            webcam.close();
            browse.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
