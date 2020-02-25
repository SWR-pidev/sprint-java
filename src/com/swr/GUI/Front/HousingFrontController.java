/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.Housing;
import com.swr.Entite.Item;
import com.swr.Entite.Rating;
import com.swr.Service.HousingService;
import static com.swr.Service.HousingService.hs;
import com.swr.Service.RatingService;
import com.swr.Service.ServiceItem;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.MapView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * FXML Controller class
 *
 * @author moham
 */
public class HousingFrontController implements Initializable {

    @FXML
    private Label nbnotif;
    
    @FXML
    private Button LocationBtn;
    @FXML
    private Button itemsbtn;
    @FXML
    private Button subBtn;
    RatingService rser= RatingService.getInstance();
    HousingService hs= HousingService.getInstance();
    ObservableList<Housing> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<Housing> tableh;
    @FXML
    private TableColumn<?, ?> col_Name;
    @FXML
    private TableColumn<?, ?> col_Address;
    @FXML
    private TableColumn<?, ?> col_Type;
    @FXML
    private TableColumn<?, ?> col_Capacity;
    @FXML
    private TableColumn<?, ?> col_NbRes;
    @FXML
    private TableColumn<?,?> col_R;
    @FXML
    private org.controlsfx.control.Rating ratingSlider;
    @FXML
    private TextField tffeed;
    ObservableList<Item> oblistitem = FXCollections.observableArrayList();

    RatingService Rs= RatingService.getInstance();
    ServiceItem is= ServiceItem.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initList();
    }    

    private void initList() {
        
       
        try {
            oblist= (ObservableList<Housing>) hs.getAllHousingsByR();
            col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
            col_Capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
            col_NbRes.setCellValueFactory(new PropertyValueFactory<>("nbresidents"));
            col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_R.setCellValueFactory(new PropertyValueFactory<>("rating"));
            tableh.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(HousingFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void checkloc(ActionEvent event) {
        Housing h=tableh.getSelectionModel().getSelectedItem();
       String locc= h.getLocation().substring(1, (h.getLocation().length()-1));
        
        String[] loc= locc.split(",");
       
        double lat=Double.parseDouble(loc[0]);
        double lng=Double.parseDouble(loc[1]);
       
        
        MapViewOptions options = new MapViewOptions();
        options.importPlaces();
        com.teamdev.jxmaps.javafx.MapView.InitJavaFX();
        final MapView mapView = new MapView(options);

        mapView.setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                    final Map map = mapView.getMap();
                    map.setZoom(20.0);
                    GeocoderRequest request = new GeocoderRequest();
                    request.setLocation(new LatLng(lat,lng));
                    

                    mapView.getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());

                                final InfoWindow window = new InfoWindow(map);
                                window.setContent(h.getName());
                                window.open(map, marker);
                            }
                        }
                    });
                }
            }
        });

        Scene scene = new Scene(new BorderPane(mapView), 700, 500);

        Stage newWindow = new Stage();
                newWindow.setTitle("MAP");
                newWindow.setScene(scene);
 
                // Set position of second window, related to primary window.
                
 
                newWindow.show();
                newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
    @Override
    public void handle(WindowEvent event) {
       newWindow.close();
    }
});
    
    }


    @FXML
    private void submitR(ActionEvent event) throws SQLException {
        
        int rat= (int) ratingSlider.getRating();
        Rating r= new Rating(tableh.getSelectionModel().getSelectedItem().getIdh(), 0, tffeed.getText(),rat);
        Rs.addRating(r);
        initList();
        tffeed.setText("");
        ratingSlider.setRating(2);
        
    }

    @FXML
    private void redtodetails(MouseEvent event) throws IOException {
         if(event.getClickCount()==2){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("HousingDetails.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent,700, 700);
        HousingDetailsController hdc = loader.getController();
      
        String N= tableh.getSelectionModel().getSelectedItem().getName();
        String D= tableh.getSelectionModel().getSelectedItem().getDescription();
        
        String T= tableh.getSelectionModel().getSelectedItem().getType();
        
        String A= tableh.getSelectionModel().getSelectedItem().getAddress();
        hdc.setTaddress(A);
        hdc.setTname(N);
        hdc.setTdesc(D);
        hdc.setTtype(T);
        
 
        
        //This line gets the Stage information
         

        Stage newWindow = new Stage();
                newWindow.setTitle("Housing Details");
                newWindow.setScene(tableViewScene);
 
                // Set position of second window, related to primary window.
                /*
 
                
                */
                newWindow.show();}
        
        
    }

    @FXML
    private void redToitems(ActionEvent event) throws IOException, SQLException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Items.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent,800, 500);
        ItemsController Ic = loader.getController();
        
         oblistitem= is.getItemsOfHousing(tableh.getSelectionModel().getSelectedItem().getIdh());
            Ic.col_name.setCellValueFactory(new PropertyValueFactory<>("nameItem"));
            Ic.col_quan.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            Ic.col_stat.setCellValueFactory(new PropertyValueFactory<>("statusItem"));
            Ic.col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            Ic.txtlbl.setText(Ic.txtlbl.getText()+tableh.getSelectionModel().getSelectedItem().getName());
            
            
            Ic.itemTab.setItems(oblistitem);
      
        
        
 
        
        //This line gets the Stage information
         

        Stage newWindow = new Stage();
                newWindow.setTitle("Items");
                newWindow.setScene(tableViewScene);
                newWindow.showAndWait();
    }
   
    
}
