/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.Housing;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author moham
 */
public class HousingDetailsController implements Initializable {
    
    
   
    

    public void setTdesc(String Tdesc) {
        this.Tdesc.setText(Tdesc);
    }

    public void setTname(String Tname) {
        this.Tname.setText(Tname) ;
    }

    public void setTaddress(String Taddress) {
        this.Taddress.setText(Taddress);
    }

    public void setTtype(String Ttype) {
        this.Ttype.setText(Ttype);
    }

    public void setTcapacity(String Tcapacity) {
        this.Tcapacity.setText(Tcapacity);
    }

    public void setTRes(String TRes) {
        this.TRes.setText(TRes);
    }
    @FXML
    private Text Tdesc;
    @FXML
    private Text Tname;
    @FXML
    private Text Taddress;
    @FXML
    private Text Ttype;
    @FXML
    private Text Tcapacity;
    @FXML
    private Text TRes;
   

  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    

    
}
