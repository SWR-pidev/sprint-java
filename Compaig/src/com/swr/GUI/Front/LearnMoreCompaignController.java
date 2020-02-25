/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import com.swr.Service.ServiceCompaign;
import com.swr.Service.ServiceProposition;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
/**
 * FXML Controller class
 *
 * @author Monta
 */
public class LearnMoreCompaignController implements Initializable {

    @FXML
    private Label nbnotif;
    @FXML
    private Button btnEvent;
    @FXML
    private Label name;
    @FXML
    private Label urlEvent;
    @FXML
    private Label nameEvent;
    private Label date;
    private Label time;
    @FXML
    private VBox vboxSponsor;
    @FXML
    private Label lblBudget;
    @FXML
    private Label budget;

    @FXML
    private Label still;
    @FXML
    private Label detailcmp;
    @FXML
    private Button btnDonate;
    @FXML
    private Label nbdon;
    @FXML
    private Label lblBudget1;
    @FXML
    private Label raised;
    @FXML
    private Label id;
    @FXML
    private Button prnt;
    @FXML
    private ProgressBar pro;
   public        double progress;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOp
       // prog=0.5;
       // double target= Double.parseDouble(budget.getText().toString());
            //  double rais= Double.parseDouble(raised.getText().toString());

        //System.out.println("Target "+target);
       //  System.out.println("raised "+rais);
       
    }    

    @FXML
    private void GoToEvents(ActionEvent event) {
    }

    public void setName(String name) {
        this.name.setText(name); 
    }
    public void setUrlEvent(String urlEvent) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("C:\\wamp64\\www\\swr\\web\\uploads\\"+urlEvent));
                ImageView imgEvt = new ImageView(image);
                imgEvt.setFitHeight(380);
                imgEvt.setFitWidth(320);
               
        this.urlEvent.setGraphic(imgEvt); 
    }

  public void setraised(double budget) {
        this.raised.setText(Double.toString(budget));
    }

    public void setTarget(double budget) {
        this.budget.setText(Double.toString(budget));
    }

    public void setStill(double still) {
        this.still.setText(Double.toString(still));
    }
 public void setid(int still) {
        this.id.setText(Integer.toString(still));
    }
 public void setDetailcmp(String detailcmp) {
        this.detailcmp.setText(detailcmp);
    }
     public void setnbdon(int detailcmp) {
        this.nbdon.setText(Integer.toString(detailcmp));
    }

    public void setPro(double pro) {
        this.pro.setProgress(pro);
    }

    public void setprovalue(double prog) {
        this.progress = prog;
    }

    public void setBudget(double budget) {
        this.budget.setText(Double.toString(budget));
    }

    
    @FXML
    private void donate(ActionEvent event) throws IOException, SQLException {
        
        
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Donate.fxml"));
        Parent root = loader.load();
         DonateController des = loader.getController();
         
          ObservableList<String> options = FXCollections.observableArrayList() ; 
        ServiceProposition sera=new ServiceProposition();
        int idd=Integer.parseInt(id.getText().trim()); 
        options=sera.PropByIdCmp(idd);
        System.out.println("id compaign Go here  "+id);
         System.out.println(options);
        des.setCom(options);
         
         
        des.id=Integer.parseInt(id.getText().trim()); 
        des.recentdon();
          btnDonate.getScene().setRoot(root);
          
    }

    @FXML
    private void print(ActionEvent event) throws DocumentException, FileNotFoundException, IOException {
        String s="We Are ";
        ServiceCompaign ser1=new ServiceCompaign();
        
        ser1.ToPDF(name.getText().toString(),"C:/Users/Monta/Desktop/testPd.pdf",this.detailcmp.getText());
          try {

		if ((new File("C:/Users/Monta/Desktop/testPd.pdf")).exists()) {

			Process p = Runtime
			   .getRuntime()
			   .exec("rundll32 url.dll,FileProtocolHandler C:/Users/Monta/Desktop/testPd.pdf");
			p.waitFor();
				
		} else {

			System.out.println("File is not exists");

		}

		System.out.println("Done");

  	  } catch (Exception ex) {
		ex.printStackTrace();
	  }

	}
    }
    
    

