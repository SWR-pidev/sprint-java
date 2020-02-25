/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class ConfirmationController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private Button btnConf;
    @FXML
    private ImageView back;
    @FXML
    private Label iterlab;
    @FXML
    private Label labmail;
    
    ServiceUser us = new ServiceUser();
    @FXML
    private Label iterlab1;
    @FXML
    private Label iterlab11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void aff() throws SQLException
    {
    try {
            iterlab.setText(""+us.affichiter(labmail.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void setLabmail(String a)
    {
    this.labmail.setText(a);
    }

    @FXML
    private void confirmation(ActionEvent event) throws IOException {
        try {
            if((us.getCode(code.getText())==0) && (us.affichiter(labmail.getText())!=1))
            {
            us.updateiter(labmail.getText());
            aff();
            }
            else if((us.getCode(code.getText())==0) && (us.affichiter(labmail.getText())==1)) {
            us.deletemail(labmail.getText());
            JOptionPane.showMessageDialog(null,"Your account has been deleted,Please Sign Up");
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("inscription.fxml"));
            Parent root=fxml.load();
            code.getScene().setRoot(root);
        
            }
            else if((us.getCode(code.getText())!=0) && (us.affichiter(labmail.getText())!=0))
            {
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("Newpass.fxml"));
            Parent root=fxml.load();
            code.getScene().setRoot(root);
            NewpassController rc=fxml.getController();
            rc.setLabmail(labmail.getText());
                    }
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        code.getScene().setRoot(root);
        LoginController rc=fxml.getController();
    }
    
}
