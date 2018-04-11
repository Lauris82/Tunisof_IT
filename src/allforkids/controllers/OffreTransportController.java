/**
* @Project: AllForKids
* @Classe: OffreTransportController
* @Date: 22 mars 2018
* @Time: 14:30:48
*
* @author Lauris
*/


package allforkids.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class OffreTransportController implements Initializable{

    AnchorPane home;
    @FXML
    private JFXButton homeButton;
    @FXML
    private AnchorPane content;
    
    
    
    
    @FXML
    private void goToHome(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/Home.fxml")); 
        Scene scene = new Scene(root);
        stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    private void setNode(Node node){
        content.getChildren().clear();
        content.getChildren().add(node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    private void createPageListe() {
        try {
            home = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/ListeOffre.fxml"));
            
            setNode(home);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createPageListe();
    }

}



/**
*@Lau82 © 2018
*/
