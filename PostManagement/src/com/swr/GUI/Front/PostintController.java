/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.Comments;
import com.swr.Entite.Posts;
import com.swr.Service.ServiceComments;
import com.swr.Service.ServicePosts;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Soulah
 */
public class PostintController implements Initializable {
    @FXML
    private Label nbnotif;
    private TextArea contenupost;
    @FXML
    private Button addpost;
    
          ServicePosts sp = new ServicePosts();
         List<Posts> a;
          ServiceComments sc = new ServiceComments();
         List<Comments> b;
    @FXML
    private Button homebtn;
    @FXML
    private TextArea addposttxt;
    @FXML
    private TextArea addcmttxt;
    @FXML
    private Button addcmt;
         
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
        addposttxt.setPromptText("Have Something in mind? Please Share it with us"); 
        addposttxt.getParent().requestFocus();
        addcmttxt.setPromptText("Have Something to add? Make this community great"); 
        addcmttxt.getParent().requestFocus();
      
    }
    public void setResNom() {
       
       
        
        
    }
/*
    public void setContenupost() {
        //this.contenupost.setText(a.get(0).getContenuP());
         try {
           a=sp.readAll();
           this.contenupost.setText(a.get(0).getContenuP());
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
*/

    @FXML
    private void gotoo(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("FrontHome.fxml"));
            try {
                Parent root = loader.load();
                homebtn.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void ajouterpost(ActionEvent event) {
         String contenuP = addposttxt.getText();
   
            Posts p = new Posts(contenuP,4);
            
             try {
          sp.ajouter1(p);
          clear();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
    
    
    private void clear(){
    addposttxt.setText("");
    }

    @FXML
    private void ajoutercmt(ActionEvent event) {
          String contenuC = addcmttxt.getText();
   
            Comments c = new Comments(contenuC,4);
            
             try {
          sc.ajouter(c);
          clear();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }
    
}



