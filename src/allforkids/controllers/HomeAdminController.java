/**
* @Project: AllForKids
* @Classe: HomeAdminController
* @Date: 11 avr. 2018
* @Time: 16:07:51
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class HomeAdminController implements Initializable{
    
    AnchorPane home;

    @FXML
    private JFXButton homeButton;
    @FXML
    private JFXButton garderieButton;
    @FXML
    private JFXButton clubButton;
    @FXML
    private JFXButton eventButton;
    @FXML
    private JFXButton offreButton;
    @FXML
    private JFXButton produitButton;
    @FXML
    private AnchorPane contentMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void goToHome(ActionEvent event) {
    }

    @FXML
    private void showBackEvenement(ActionEvent event) {
        try {
            home = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/yassine/gui/AdminAfficheEvenement.fxml"));
            
            setNode(home);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    private void setNode(Node node){
        contentMenu.getChildren().clear();
        contentMenu.getChildren().add(node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void goToOffreTransport(ActionEvent event) {
        try {
            home = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/ListeOffreBack.fxml"));
            
            setNode(home);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}



/**
*@Lau82 © 2018
*/
