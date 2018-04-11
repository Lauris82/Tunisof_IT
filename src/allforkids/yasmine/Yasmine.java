/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class Yasmine extends Application {
    
//    @Override
//    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    
        @Override
    public void start(Stage primaryStage) {
    
            try {
//                Parent root = FXMLLoader.load(getClass().getResource("/gui/ajouterClub.fxml"));
                Parent root = FXMLLoader.load(getClass().getResource("/gui/home.fxml"));
                Parent root2 = FXMLLoader.load(getClass().getResource("/gui/homeAssociation.fxml"));
                Parent root3 = FXMLLoader.load(getClass().getResource("/gui/reclamation.fxml"));

                Scene scene = new Scene(root);
                
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(Yasmine.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    
//    public static void showAjoutClub() {
//        
//            try {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(Yasmine.class.getResource("/gui/ajouterClub.fxml"));
//                AnchorPane  mainlayout= loader.load();
//            } catch (IOException ex) {
//                Logger.getLogger(Yasmine.class.getName()).log(Level.SEVERE, null, ex);
//            }
//                
//    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
