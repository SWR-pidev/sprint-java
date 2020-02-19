/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.Entite.Housing;
import com.swr.Entite.Rating;
import com.swr.Service.HousingService;
import static com.swr.Service.HousingService.hs;
import com.swr.Service.RatingService;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class HousingFrontController implements Initializable {

    @FXML
    private Label nbnotif;
    
    @FXML
    private Button Detailsbtn;
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
    private Slider ratingSlider;
    @FXML
    private TextField tffeed;
    RatingService Rs= RatingService.getInstance();
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
        String[] loc= h.getLocation().split(",");
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
    
    }

    @FXML
    private void detailH(ActionEvent event)  throws IOException {
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("HousingDetails.fxml"));
        tableViewParent.setUserData(tableh.getSelectionModel().getSelectedItem());
        Scene tableViewScene = new Scene(tableViewParent,1050, 700);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    
    
    }

    @FXML
    private void submitR(ActionEvent event) throws SQLException {
        int rat= (int) ratingSlider.getValue();
        Rating r= new Rating(tableh.getSelectionModel().getSelectedItem().getIdh(), 0, tffeed.getText(),rat);
        Rs.addRating(r);
        initList();
        tffeed.setText("");
        ratingSlider.setValue(1);
    }
   
    
}
