/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Service.ServiceUser;
import com.email.durgesh.Email;
import com.teknikindustries.bulksms.SMS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */
public class ForgotController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private Button btn;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void forgotpass(ActionEvent event) throws SQLException, MessagingException, UnsupportedEncodingException, IOException {
    String mai = mail.getText();
    ServiceUser user = new ServiceUser();
    String a=user.forgotpasss(mai);
    if(user.getUser(mai)!="" && !mai.isEmpty())
    {   
			// Construct data
			String apiKey = "apikey=" + "jMj08sPgSDU-unVcErnJICPpq7lnSRJSx6AnsLzcSy";
			String message = "&message=" + "This your verification code: "+a;
			String sender = "&sender=" + "Solidarity with refugees";
			String numbers = "&numbers=" + "216"+user.getTel(mai);
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
        Email email = new Email("swr.pidev@gmail.com","SWR.pidev123");
        email.setFrom("swr.pidev@gmail.com", "Solidarity With Refugees");
        email.setSubject("Get your password");
        email.setContent("<h1>This is your verification code :</h1>"+a, "text/html");
        email.addRecipient(mai);
        email.send();
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("Confirmation.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        ConfirmationController rc=fxml.getController();
        rc.setLabmail(mai);
        rc.aff();
    }
    else{
    JOptionPane.showMessageDialog(null,mai+" doesn't existing, PLEASE SIGN UP");
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("inscription.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        InscriptionController rc=fxml.getController();
    }
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        mail.getScene().setRoot(root);
        LoginController rc=fxml.getController();
    }
    
}
