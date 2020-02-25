/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXSpinner;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.ExternalAccountCollection;
import com.stripe.model.Token;
import com.swr.Entite.Compaign;
import com.swr.Entite.Donation;
import com.swr.Service.ServiceCompaign;
import com.swr.Service.ServiceDonation;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Monta
 */
public class StripeController implements Initializable {
    int id;
    @FXML
    private TextField cardNum;
    @FXML
    private TextField expY;
    @FXML
    private TextField expM;
    @FXML
    private TextField amount;
    @FXML
    private TextField cvc;
    @FXML
    private Button btnDonate;
    Donation d;
    @FXML
    private JFXSpinner dur;
    @FXML
    private Label thnk;
    @FXML
    private ImageView img;
  int amountInt=0;
  int expy=0;
  int expm=0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    dur.setVisible(false);
    img.setVisible(false);
    thnk.setVisible(false);
    }    

    @FXML
    private void Donate(ActionEvent event) throws StripeException, SQLException, IOException {
   String cardn=cardNum.getText();
    String exy=expY.getText();
     String exm=expM.getText();
    String cvcc=cvc.getText();
       String Amount=amount.getText();
    int given = 0;




 boolean cn = checkCardNumber(cardn);
        boolean exyy =checkExpYear(exy);
        boolean exmm = checkExpMonth(exm);
        boolean cvcC = checkCvc(cvcc);
        boolean amountt = checkAmount(Amount);
        
        if(!cn)
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stripe");
                alert.setHeaderText(null);
                alert.setContentText("Please make sure to insert your card Number correctly");
                alert.showAndWait();
        }
        else if(!exyy) {  
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                 alert.setTitle("Stripe");
                 alert.setHeaderText(null);
                 alert.setContentText("Please enter the exp year correctly");
                 alert.showAndWait();
                 
                 }
    
        else if(!exmm) {  
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stripe");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the exp month correctly");
                alert.showAndWait();
                 
                 }
       
        else if(!cvcC)
        {
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stripe");
                alert.setHeaderText(null);
                alert.setContentText("CVC needs to be 3 digits");
                alert.showAndWait();
        }
        else if(!amountt) {  
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stripe");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the amount");
                alert.showAndWait();
                 }      
        else{
        System.out.print("Check year"+checkYearMonth(expy, expm));
            if (!checkYearMonth(expy, expm)){
           Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stripe");
                alert.setHeaderText(null);
                alert.setContentText("Your card is not valid based on your exp year and month");
                alert.showAndWait();
                return;
           }



Compaign c1= new Compaign ();
        ServiceCompaign serc=  new ServiceCompaign();
        c1=serc.GetInfosCmp(id);





if (c1.getStillneeded()>amountInt)

{
    
    cardNum.setVisible(false);
         expY.setVisible(false);
         expM.setVisible(false);
         amount.setVisible(false);
         cvc.setVisible(false);
          btnDonate.setVisible(false);
     
      if (netIsAvailable())  {
        if (d.getFunds()==1 &&amountInt<2)
         {
          Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Donation");
                alert.setHeaderText(null);
                alert.setContentText("If  You Checked Funds Than Donation Must Be at Least 2$ :)");
                alert.showAndWait();
              
                return;
         }
          if (StripePayment(cardn, exm, exy, cvcc, amountInt))
        {
         ServiceDonation ser= new ServiceDonation();
         d.setGiven(amountInt);
         
            ser.Donate(d);
            dur.setVisible(true);
  Timeline t=new Timeline(
          new KeyFrame(
                  Duration.ZERO,
                  new KeyValue(dur.progressProperty(),0)
     ),
          new KeyFrame(
                  Duration.seconds(0.5),
                  new KeyValue(dur.progressProperty(),0.5)
          ),
          new KeyFrame(
                  Duration.seconds(3),
                  new KeyValue(dur.progressProperty(),1)
          )
          );
          t.setCycleCount(1);

t.play();
 t.setOnFinished( e ->{dur.setVisible(false) ; img.setVisible(true) ;  thnk.setVisible(true); });

// 

////////////*****************    UUUUUUUUUUUSSSSSSSSSSSSSSSEEEEEEEEEEEEERRRRRRRRR*************/////////
         
        } else {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Something Went Wrong");
                alert.showAndWait();
        }   
      }
      else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Check Your Connection");
                alert.showAndWait();
      }
}else {
Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You are So Generous");
                alert.setContentText("We can not take this much");
                alert.showAndWait();
}

//    btnDonate.getScene().getWindow().hide();
    }
    }
    public void initvariable(int idi)
    {
        this.id=idi;
    }
    
    private static boolean netIsAvailable() {
    try {
        final URL url = new URL("http://www.google.com");
        final URLConnection conn = url.openConnection();
        conn.connect();
        conn.getInputStream().close();
        return true;
    } catch (MalformedURLException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        return false;
       
    }  
}
public boolean StripePayment (String cn ,String em ,String ey,String cvc,int d ) throws StripeException
{
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	
    	//add stripe key
    	Stripe.apiKey ="sk_test_Y0jQ7oWwtJt7b1c1xHJaN4Kg00OReUT5ee";
    	
    	//change customer id
        Customer newCustomer = Customer.retrieve("cus_GmTdcLIX8mMjCZ");
        //ExternalAccountCollection allCardDetails = newCustomer.getSources();
        
        Map < String, Object> cardParam = new HashMap< String, Object> ();
        cardParam.put("number", cn);
        cardParam.put("exp_month", em);
        cardParam.put("exp_year", ey);
        cardParam.put("cvc", cvc);
        
        Map < String, Object> tokenParam = new HashMap< String, Object> ();
        tokenParam.put("card", cardParam);
        
        Token token =Token.create(tokenParam);
        Boolean cardIsNotExist =true;
        
        	 Map < String, Object> source = new HashMap< String, Object> ();
             source.put("source",token.getId());
             
             newCustomer.getSources().create(source);
             System.out.println("card created");
       
       
       System.out.println( gson.toJson(newCustomer));
      
        Map<String, Object> params = new HashMap<>();
		params.put("amount", d*100);
		params.put("currency", "usd");
		params.put("customer", "cus_GmTdcLIX8mMjCZ");

		Charge charge = Charge.create(params);
		System.out.println(charge);
                return true;
}
   public boolean checkYearMonth(int year , int month)
    {
        
         Date date = new Date();
         LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         int monthNow = localDate.getMonthValue(); 
         int yearNow = localDate.getYear();
        if(year < yearNow )
        {
            return false;
        }
        else if(year==yearNow)
        {
            if(month<1 || month>12)
            {
                return false;
            }
            else if(month < monthNow)
            {
                return false;               
            }
            else if(month==monthNow)
            {
                return false;
            }
            else
            {
                return true;
            }
    }
        else 
        {
            return true;
        }
}

    
     public boolean checkCardNumber(String cardn)
    {
        /* if(cardn.isEmpty() || cardn.length()!=16 || !cardn.matches(".*[^0-9].*"))
        {
            
                return false;
        }*/
        if (cardn.length()!=16){return false;}
         char[] chars = cardn.toCharArray();
        for (char c : chars) {
        if(!Character.isDigit(c)) {
        return false;
        } }
     return true;
    }
       public boolean checkExpYear(String exy)
    {
         if (exy.length()!=4){return false;}
         char[] chars = exy.toCharArray();
        for (char c : chars) {
        if(!Character.isDigit(c)) {
        return false;
        } }
        expy = Integer.parseInt(exy);
         return true;
    }
         public boolean checkExpMonth(String exm)
    {
           if (exm.length()!=2){return false;}
         char[] chars = exm.toCharArray();
        for (char c : chars) {
        if(!Character.isDigit(c)) {
        return false;
        } }
        expm = Integer.parseInt(exm);
         return true;
    }
         
public boolean checkCvc(String cvcc)
    {
          if (cvcc.length()!=3){return false;}
         char[] chars = cvcc.toCharArray();
        for (char c : chars) {
        if(!Character.isDigit(c)) {
        return false;
        } }
          return true;
    }

public boolean checkAmount(String Amount)
    {
             if (Amount.length()==0){return false;}
         char[] chars = Amount.toCharArray();
        for (char c : chars) {
        if(!Character.isDigit(c)) {
        return false;
        } }
        amountInt = Integer.parseInt(Amount);
          return true;
    }  
}
