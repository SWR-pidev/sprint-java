/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Back;


import com.swr.Entite.Categorie;
import com.swr.Entite.News;
import com.swr.Entite.User;
import com.swr.Service.ServiceCategorie;
import com.swr.Service.ServiceNews;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import rest.file.uploader.tn.FileUploader;

/**
 * FXML Controller class
 *
 * @author Asus X550V
 */

public class NewsController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnUser;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<News> Newview;
    @FXML
    private TableColumn<?, ?> cl_tit;
    @FXML
    private TableColumn<?, ?> cl_desc;
    @FXML
    private TableColumn<?, ?> cl_date;
    @FXML
    private TableColumn<?, ?> cl_cat;
    ServiceNews news = new ServiceNews();
    ServiceCategorie cat = new ServiceCategorie();
    ObservableList<News> oblist = FXCollections.observableArrayList();
    @FXML
    private Button btncat;
    @FXML
    private TextField titre;
    @FXML
    private TextField desc;
    @FXML
    private Button addbtn;
    @FXML
    private ComboBox<Categorie> nomcat;
    @FXML
    private Button delbtn;
    @FXML
    private TextField descup;
    @FXML
    private TextField titup;
    @FXML
    private ComboBox<Categorie> nomcat1;
    @FXML
    private PieChart chart;
    @FXML
    private Button browze;
    private String lien="";
    @FXML
    private ImageView image;
    private Image image1;
    @FXML
    private Button browzeup;
    @FXML
    private ImageView imageup;
    private Image image2;
    private String lienup="";
    @FXML
    private TableColumn<?, ?> cl_image;
    @FXML
    private Label labtit;
    @FXML
    private Label labtitup;
    AutoCompletionBinding<String> autoCompletionBinding;
    Set<String> suggestions=new HashSet<>();

    /**
     * Initializes the controller class.
     */
    
    public void rafraichir()
    {
          ResultSet stat=null;
      
        try {
            stat=news.satistique();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        chart.getData().clear();
        
        try {
            while(stat.next())
            {
                
                
                chart.getData().add(new PieChart.Data(stat.getString(1),stat.getDouble(2)));
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
     }
        
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        combo();
        combo1();
        this.rafraichir();
        autoCompletionBinding = TextFields.bindAutoCompletion(desc, suggestions);
        desc.setOnKeyPressed((KeyEvent ke) -> {
            switch(ke.getCode())
            {
                case ENTER:
                    autoCompletionLearnWord(desc.getText());
                    break;
                default:
                    break;
            }
        });
        }
private void autoCompletionLearnWord(String newWord)
{
suggestions.add(newWord);
if(autoCompletionBinding!=null)
{
autoCompletionBinding.dispose();
}
autoCompletionBinding = TextFields.bindAutoCompletion(desc,suggestions);
}

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void user(ActionEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("Userb.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
    }
    public void combo()
    {
        try {
            ObservableList<Categorie> list=cat.affichecat();
            nomcat.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void combo1()
    {
        try {
            ObservableList<Categorie> list=cat.affichecat();
            nomcat1.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void initTable() {
        
        try {
            oblist= (ObservableList<News>) news.readAll() ;
            cl_tit.setCellValueFactory(new PropertyValueFactory<>("titre"));
            cl_desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
            cl_date.setCellValueFactory(new PropertyValueFactory<>("datepub"));
            cl_cat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
            cl_image.setCellValueFactory(new PropertyValueFactory<>("image"));
            Newview.setItems(oblist);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserbController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

    @FXML
    private void categ(ActionEvent event) throws IOException {
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("Cat.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
        
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
            
        try {
            if(titre.getText().isEmpty() || desc.getText().isEmpty() || lien=="" || nomcat.getValue()==null){
            JOptionPane.showMessageDialog(null,"please fill all the textfields and choose a picture");
            }
            else{
             if(titre.getText().matches("^[a-zA-Z ]+$") && titre.getText().length()>5)
             {
                 if(news.searchtit(titre.getText())==0)
                 {
            FileUploader fu = new FileUploader("localhost/swr/web/");
            String fileNameInServer = fu.upload(lien);
            News u = new News(titre.getText(),desc.getText(),nomcat.getValue().getNomcat(),fileNameInServer);
            news.ajouter(u);
            initTable();
            clearAll();
            this.rafraichir();
            labtit.setText("");
                 }
                 else{ JOptionPane.showMessageDialog(null,"The title "+titre.getText()+" exist !"); }
             }
             else{labtit.setText("**The structure of "+titre.getText()+" is not correct**"); }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        image.setVisible(false);
    
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
    News c=Newview.getSelectionModel().getSelectedItem();
        if (c==null) 
        {
     JOptionPane.showMessageDialog(null,"There is nothing selected !");
        }
        else{ 
        news.delete(c.getTitre());
        initTable();
        this.rafraichir();
        clearAll();}
    }
    private void clear(ActionEvent event) {
        clearAll();
    }
    private void clearAll(){
    
        titre.setText("");
        desc.setText("");
        nomcat.setValue(null);
        titup.setText("");
        descup.setText("");
        nomcat1.setValue(null);
        image.setVisible(false);
        imageup.setVisible(false);
        }

    @FXML
    private void update(ActionEvent event) {
    
        try {
            News c=Newview.getSelectionModel().getSelectedItem();
            if(c==null)
            {JOptionPane.showMessageDialog(null,"There is nothing selected !");
            clearAll();}
            else{
            FileUploader fu = new FileUploader("localhost/swr/web/");
            String fileNameInServer;
            try {
            if(titup.getText().isEmpty() || descup.getText().isEmpty() || lienup=="" || nomcat1.getValue()==null){
            JOptionPane.showMessageDialog(null,"please fill all the textfields and choose a picture");
            }
            else{
             if(titup.getText().matches("^[a-zA-Z]+$") && titup.getText().length()>5)
             {
                 if(news.searchtit(titup.getText())==0)
                 {
                fileNameInServer = fu.upload(lienup);
                news.update(c.getTitre(), titup.getText(), descup.getText(), nomcat1.getValue().getNomcat(),fileNameInServer);
            initTable();
        clearAll();
        this.rafraichir();
        labtitup.setText("");
        }
                 else{ JOptionPane.showMessageDialog(null,"The title "+titup.getText()+" exist !"); }
             }
             else{labtitup.setText("**The structure of "+titup.getText()+" is not correct**"); }
            }
            } catch (ProtocolException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            } } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageup.setVisible(false);
        
    }
        private void upall(){
        News c=Newview.getSelectionModel().getSelectedItem();
        titup.setText(c.getTitre());
        descup.setText(c.getDesc());
        try {
            image2=new Image("file:///C:\\wamp64\\www\\swr\\web\\uploads\\"+news.search(c.getTitre()));
        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageup.setImage(image2);
        imageup.setVisible(true);
        
        }

    @FXML
    private void getImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpeg files (*.jpg)", "*.jpg","jpeg files (*.png)", "*.png","jpeg files (*.jpeg)", "*.jpeg");
              fileChooser.getExtensionFilters().add(extFilter);
             Window primaryStage = null;
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null) return ;
            this.lien=file.getPath();
        try {
            image1=new Image(file.getAbsoluteFile().toURL().toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            image.setImage(image1);
            image.setVisible(true);
            }

    @FXML
    private void browzeupp(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpeg files (*.jpg)", "*.jpg","jpeg files (*.png)", "*.png","jpeg files (*.jpeg)", "*.jpeg");
              fileChooser.getExtensionFilters().add(extFilter);
             Window primaryStage = null;
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file==null) return ;
            this.lienup=file.getPath();
        try {
            image2=new Image(file.getAbsoluteFile().toURL().toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            imageup.setImage(image2);
    }

    @FXML
    private void tabclick(MouseEvent event) {
        upall();
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
                FXMLLoader fxml=new FXMLLoader(getClass().getResource("/com/swr/gui/front/login.fxml"));
        Parent root=fxml.load();
        btnUser.getScene().setRoot(root);
    }


    
    
}
