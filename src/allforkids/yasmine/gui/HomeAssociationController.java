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
public class HomeAssociationController implements Initializable {

    @FXML
    private Button AjoutAss;
    @FXML
    private AnchorPane zoneA;
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
    private void goListerAss(ActionEvent event) {
      try {
            AnchorPane a=(AnchorPane) FXMLLoader.load(getClass().getResource("listerAss.fxml"));
            zoneA.getChildren().setAll(a);
        
        
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void goAjouterA(ActionEvent event) {
        try {
            AnchorPane a=(AnchorPane) FXMLLoader.load(getClass().getResource("AjoutAss.fxml"));
            zoneA.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(HomeAssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToHome(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/Home.fxml")); 
        Scene scene = new Scene(root);
        stage = (Stage) zoneA.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }

}
