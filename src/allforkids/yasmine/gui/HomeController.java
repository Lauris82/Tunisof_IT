/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class HomeController implements Initializable {

    
    @FXML
    private Button AjoutClubButton;
    @FXML
    private AnchorPane zone;
    @FXML
    private ImageView imageLogo;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goAjout(ActionEvent event) {
        try {
            
            AnchorPane a=(AnchorPane) FXMLLoader.load(getClass().getResource("ajouterClub.fxml"));
            zone.getChildren().setAll(a);
        
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goLister(ActionEvent event) {

        try {
            AnchorPane a=(AnchorPane) FXMLLoader.load(getClass().getResource("listerClub.fxml"));
            zone.getChildren().setAll(a);
        
        
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void goRecherche(ActionEvent event) {
        try {
            AnchorPane a=(AnchorPane) FXMLLoader.load(getClass().getResource("RechercherClub.fxml"));
            zone.getChildren().setAll(a);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void goToHome(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/Home.fxml")); 
        Scene scene = new Scene(root);
        stage = (Stage) zone.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
