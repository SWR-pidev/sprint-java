/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Service.ServiceUser;
import com.swr.Service.Servicebcrypt;
import com.swr.Utils.staticvar;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */
public class NewpassController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private Button btnConf;
    @FXML
    private ImageView back;
    @FXML
    private Label labmail;
    ServiceUser us = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setLabmail(String a)
    {
    this.labmail.setText(a);
    }
    @FXML
    private void confirmation(ActionEvent event) throws IOException {
        try {
            String crypted = Servicebcrypt.hashpw(code.getText(), staticvar.SALT);
            us.updatepass(labmail.getText(),crypted);
            us.resetiter(labmail.getText());
            JOptionPane.showMessageDialog(null,"Your password has been modified");
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root=fxml.load();
            code.getScene().setRoot(root);
        } catch (SQLException ex) {
            Logger.getLogger(NewpassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(MouseEvent event) {
    }
    
}
