/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.swr.Service.ServiceSponsor;
//import com.itextpdf.text.Image;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import com.swr.Entite.Event;
import com.swr.Entite.Participation;
import com.swr.Entite.Sponsor;
import com.swr.Entite.User;
import com.swr.Service.ServiceParticipation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Eya
 */
public class DisplayEventSingleController implements Initializable {

    @FXML
    private Label nbnotif;
    @FXML
    private Button btnEvent;
    @FXML
    private Label nameEvent;
    @FXML
    private Label urlEvent;
    @FXML
    private Label dateEvent;
    @FXML
    private Label timeEvent;
    @FXML
    private VBox vboxSponsor;
    @FXML
    private Label lblBudget;
    @FXML
    private Label budget;
    @FXML
    private Label lblStill;
    @FXML
    private Label still;
    @FXML
    private Label location;
    @FXML
    private Label detailEvent;
    @FXML
    private Button btnSponsor;
    @FXML
    private Button btnParticipate;
    
    @FXML
    private TextArea txtState;

   Participation p = new Participation();
   ServiceParticipation serp = new ServiceParticipation();
   ServiceSponsor sers = new ServiceSponsor();
   
   /*************************** USER ****************************/
         User u = new User(22,"eya","rabeh");
    /************************ USER *********************/
         
   Event e1 = new Event();
    @FXML
    private Label lblParticipate;
    @FXML
    private Button btnprint;
    @FXML
    private JFXListView<Label> listSponsors;
    @FXML
    private Label lblsponsor;
    @FXML
    private JFXProgressBar pgBar;
    @FXML
    private Label lblProg;
    
    /**
     * Initializes the controller class.
     */
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

  
 @FXML
    private void GoToEvents(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DisplayEvents.fxml"));
        Parent root = loader.load();
          
          btnEvent.getScene().setRoot(root);
    }


    public void setNameEvent(String nameEvent) {
        this.nameEvent.setText(nameEvent) ;
    }

    public void setDetailEvent(String detailEvent) {
        this.detailEvent.setText(detailEvent);
    }

    public void setUrlEvent(String urlEvent) throws FileNotFoundException {
         javafx.scene.image.Image imagee = new Image(new FileInputStream("C:\\wamp64\\www\\swr\\web\\uploads\\"+urlEvent));
                ImageView imgEvt = new ImageView(imagee);
                imgEvt.setFitHeight(380);
                imgEvt.setFitWidth(320);
               
        this.urlEvent.setGraphic(imgEvt);
    }

    public void setDateEvent(LocalDate dateEvent) {
        String formattedDate = dateEvent.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        this.dateEvent.setText(formattedDate);
    }

    public void setTimeEvent(LocalTime timeEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String timeString = timeEvent.format(formatter); 
        this.timeEvent.setText(timeString);
    }

    public void setLocation(String location) {
        this.location.setText(location);
    }

   public void initdata(Event e) throws SQLException
{
    txtState.setText(Integer.toString(e.getState_evt()));
    e1.setDate_evt(e.getDate_evt());
    e1.setLocation(e.getLocation());
    e1.setName(e.getName());
    e1.setTime_evt(e.getTime_evt());
    
    
}

   
   public void checkEvent(Event e) throws SQLException
   {
       e1.setId_evt(e.getId_evt());
       if(txtState.getText().equals("0"))
       {
           btnParticipate.setVisible(false);
           p.setE(e);
           budget.setText(Double.toString(e.getBudget()));
           still.setText(Double.toString(e.getStillneeded_evt()));
           listSponsors.setVisible(false);
           btnprint.setVisible(false);
           lblsponsor.setVisible(false);
       }
       if(txtState.getText().equals("1"))
       {
           btnSponsor.setVisible(false);
           lblBudget.setVisible(false);
           lblStill.setVisible(false);
            btnprint.setVisible(false);
            pgBar.setVisible(false);
            lblProg.setVisible(false);
       }
      boolean what= serp.checkParticipation1(u.getIdu(), e.getId_evt());
      if(!what)
      {
          lblParticipate.setText("Participate");
      }else{
          lblParticipate.setText("Cancel Participation");
          btnprint.setVisible(true);
      }
   }

    @FXML
    private void Sponsor(ActionEvent event) throws IOException {
        Stage s=new Stage();
FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupSponsor.fxml"));
Parent root = loader.load();
 PopupSponsorController cs = loader.getController();
 cs.initData(e1);
 s.setScene(new Scene(root));
s.initModality(Modality.APPLICATION_MODAL);
s.initOwner(btnEvent.getScene().getWindow());
s.setTitle("Stripe");
s.showAndWait();
//btnEvent.getScene().setRoot(root);
s.close();
        
    }

    @FXML
    private void AddParticipation(ActionEvent event) throws SQLException {
        p.setU(u);
        p.setE(e1);
       boolean what= serp.checkParticipation1(u.getIdu(), e1.getId_evt());
      if(!what )
      { serp.addParticipation(p);
      
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Participate");
                alert.setHeaderText(null);
                alert.setContentText("Registration in progress");
                alert.showAndWait();
      lblParticipate.setText("Cancel Participation");
       btnprint.setVisible(true);
      }
     else
      {
          serp.deleteParticipation(p);
          
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Participate");
                alert.setHeaderText(null);
                alert.setContentText("Canceling Registration in progress");
                alert.showAndWait();
                lblParticipate.setText("Participate");
                 btnprint.setVisible(false);
      }
       
    }

    public void setE1(Event e1) {
        this.e1 = e1;
    }

    @FXML
    private void PrintMe(ActionEvent event) throws DocumentException, FileNotFoundException, BadElementException, IOException, SQLException {
      
        Format formatter =  new SimpleDateFormat("dd MMMM yyyy");  
String s = formatter.format(e1.getDate_evt());

DateFormat format = new SimpleDateFormat("HH:mm");
String s1=format.format(e1.getTime_evt());
String s2 = Integer.toString(u.getIdu());
int nb= sers.CountSponsor(e1.getId_evt());
ObservableList<String> data =FXCollections.observableArrayList();
data= (ObservableList<String>) sers.GetLogoSponsor(e1.getId_evt());
        serp.ToPDF(e1.getName(),s,s1,e1.getLocation(),"C:/Users/Eya/Desktop/invitation.pdf",s2,nb,data);
          try {

		if ((new File("C:/Users/Eya/Desktop/invitation.pdf")).exists()) {

			Process p = Runtime
			   .getRuntime()
			   .exec("rundll32 url.dll,FileProtocolHandler C:/Users/Eya/Desktop/invitation.pdf");
			p.waitFor();
				
		} else {

			System.out.println("File is not exists");

		}

		System.out.println("Done");

  	  } catch (Exception ex) {
		ex.printStackTrace();
	  }

	}
    
     public void listSponsor(Event e) throws SQLException, FileNotFoundException
    {
         
        ObservableList<Sponsor> data =FXCollections.observableArrayList();
        data= (ObservableList<Sponsor>) sers.GetSponsor(e.getId_evt());
        
         for(int i=0 ; i<data.size() ; i++)
        {
            //System.out.println(Integer.toString(data.get(i).getId_evt()));
            Label lbl = new Label();
            //Label lbl2 = new Label(Integer.toString(data.get(i).getId_evt()));
            Image image = new Image(new FileInputStream("C:\\wamp64\\www\\swr\\web\\uploads\\"+data.get(i).getLogo()));
            ImageView imgEvt = new ImageView(image);
            imgEvt.setFitHeight(50);
            imgEvt.setFitWidth(50);
            lbl.setGraphic(imgEvt);
            //lbl.setGraphic(new Button("Participer"));
            listSponsors.getItems().add(lbl);
            // myJFXlist.getItems().add(lbl2);
            // myJFXlist.getItems().set(i, lbl2).setVisible(false);
            
        }
        listSponsors.setExpanded(true);
        listSponsors.depthProperty().set(1);
        listSponsors.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listSponsors.setMouseTransparent( true );
        listSponsors.setFocusTraversable( false );
       
    }

    public void setPgBar(double pgBar) {
        this.pgBar.setProgress(pgBar);
    }
     
    }
     
    
    
