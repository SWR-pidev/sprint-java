/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.swr.Entite.Event;
import com.swr.Entite.Sponsor;
import com.swr.Entite.User;
import com.swr.Service.ServiceEvent;
import com.swr.Service.ServiceSponsor;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import rest.file.uploader.tn.FileUploader;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class PopupSponsorController implements Initializable {

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
    private Button btnSponsor;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button browse;
    @FXML
    private Label lblupload;
    @FXML
    private ImageView DoneImg;
    @FXML
    private Label lblThanks;
    Event e1 = new Event();
    Sponsor s = new Sponsor();
    
    /*************************** USER ****************************/
         User u = new User(22,"eya","rabeh");
    /************************ USER *********************/
         
    String ImgUrl="";
    ServiceSponsor sers=new ServiceSponsor();
    @FXML
    private Button btnNext;
    @FXML
    private TextField textLogo;
    int expy =0;
    int expm=0;
   int amountInt=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              btnNext.disableProperty()          
        .bind(Bindings.isEmpty(textLogo.textProperty())); 
             
    }    
    
    public void initData(Event e)
    {
        e1.setId_evt(e.getId_evt());
        System.out.println("FUCKING ID "+e.getId_evt());
        DoneImg.setVisible(false);
        cvc.setVisible(false);
        cardNum.setVisible(false);
        expM.setVisible(false);
        expY.setVisible(false);
        amount.setVisible(false);
        lblThanks.setVisible(false);
        btnSponsor.setVisible(false);
    }

    @FXML
    private void browseLogo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
              fileChooser.getExtensionFilters().add(extFilter);
             Window primaryStage = null;
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null) { return;}
            this.ImgUrl=file.getPath(); 
            textLogo.setText(ImgUrl);
    }

    @FXML
    private void nextStep(ActionEvent event) {
        cvc.setVisible(true);
        cardNum.setVisible(true);
        expM.setVisible(true);
        expY.setVisible(true);
        amount.setVisible(true);
        btnSponsor.setVisible(true);
        browse.setVisible(false);
        lblupload.setVisible(false);
        btnNext.setVisible(false);
        
    }
    
    @FXML
    private void AddSponsor(ActionEvent event) throws SQLException, StripeException, ProtocolException, IOException {
        String cardn=cardNum.getText();
        String exm=expM.getText();
        String exy=expY.getText();
        String cvcc=cvc.getText();
        String Amount=amount.getText();
        
       
       
       
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
            
            Event e2=new Event();
            ServiceEvent sere=new ServiceEvent();
            e2=sere.GetInfosById(e1.getId_evt());
              System.out.print("amount int "+amountInt);
            if (e2.getStillneeded_evt()<=amountInt){
          
              Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stripe");
                alert.setHeaderText(null);
                alert.setContentText("You're very generous but we can't accept more than we need"+"\n"+"Please check our still needed");
                alert.showAndWait();
                return;
              
          }
        
            
            
           
        
            boolean checkConnection= netIsAvailable();
            if(checkConnection)
            {boolean YouCanPay = StripeSponsor(cardn, exm, exy, cvcc, amountInt);
            if(YouCanPay)
            {
                
                s.setE(e1);
                s.setGiven(amountInt);
                        ServiceEvent ser = new ServiceEvent();      
         FileUploader fu = new FileUploader("localhost:8080/swr/web/");
                s.setLogo(fu.upload(ImgUrl));
                s.setU(u);
                sers.addSponsor(s);
                cvc.setVisible(false);
        cardNum.setVisible(false);
        expM.setVisible(false);
        expY.setVisible(false);
        amount.setVisible(false);
        btnSponsor.setVisible(false);
        lblThanks.setVisible(true);
        DoneImg.setVisible(true);
            }
            else 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stripe");
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong with Stripe"+"\n"+"Please try again");
                alert.showAndWait();
            }
            }
            else 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stripe");
                alert.setHeaderText(null);
                alert.setContentText("Please connect to your internet and try again");
                alert.showAndWait();
            }
        
        }
        
        
    }
    
    public boolean StripeSponsor (String cardn ,String exm ,String exy,String cvc,int amount ) throws StripeException
{
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	
    	//add stripe key
    	Stripe.apiKey ="sk_test_GOj6mm19M1VpwpaNLeshf7oS00WqcmAb9q";
    	
    	//change customer id
        Customer newCustomer = Customer.retrieve("cus_GmvVBQowW3TZ26");
        //ExternalAccountCollection allCardDetails = newCustomer.getSources();
        
        Map < String, Object> cardParam = new HashMap< String, Object> ();
        cardParam.put("number", cardn);
        cardParam.put("exp_month", exm);
        cardParam.put("exp_year", exy);
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
		params.put("amount", amount*100);
		params.put("currency", "usd");
		params.put("customer", "cus_GmvVBQowW3TZ26");

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
 
