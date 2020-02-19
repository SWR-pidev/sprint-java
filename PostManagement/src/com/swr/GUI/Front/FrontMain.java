/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.GUI.Front;

import com.swr.GUI.Front.FrontHomeController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/**
 *
 * @author Monta
 */
public class FrontMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {


        Button btn = new Button();
        btn.setText("Say 'Hello World"
                + "'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Solidarity !!!");
        });
        Parent p=new Parent() {
};
        Parent x=new Parent() {
};
        p=FXMLLoader.load(FrontHomeController.class.getResource("FrontHome.fxml"));
            x=FXMLLoader.load(PostintController.class.getResource("Postsinterface.fxml"));

       /* StackPane root = new StackPane();
        root.getChildren().add(btn);*/
        
        Scene scene = new Scene(p, 1050, 700);
        
        primaryStage.setTitle("Solidarity With Refugees!!!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
