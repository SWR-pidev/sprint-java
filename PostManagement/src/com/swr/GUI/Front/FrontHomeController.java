/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Soulah
 */
public class FrontHomeController implements Initializable {
    @FXML
    private Label nbnotif;
    @FXML
    private Button blogbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoo(ActionEvent event) throws IOException {
 FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Postsinterface.fxml"));
            try {
                Parent root = loader.load();
                blogbtn.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
    }
    
      /*   Stage stage = null;
        Parent myNewScene = null;
        if (event.getSource() == blogbtn){
        stage = (Stage) blogbtn.getScene().getWindow();
            myNewScene = FXMLLoader.load(PostintController.class.getResource("Postsinterface.fxml"));}
        
 Scene scene = new Scene(myNewScene,1050, 700);
        stage.setScene(scene);
        stage.setTitle("My New Scene");
        stage.show();*/
        
}
    
}