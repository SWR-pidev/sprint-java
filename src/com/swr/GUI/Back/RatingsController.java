/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;

import com.swr.Entite.Rating;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class RatingsController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    public Label txtlbl;
    @FXML
    public TableView<Rating> tableR;
    @FXML
    public TableColumn<?, ?> col_R;
    @FXML
    public TableColumn<?, ?> col_feed;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void recherche(KeyEvent event) {
    }
    
}
