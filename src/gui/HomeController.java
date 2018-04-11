/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Lauris
 */
public class HomeController implements Initializable {

    @FXML
    private Button AjoutClubButton;
    @FXML
    private ImageView imageLogo;
    @FXML
    private AnchorPane zone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goAjout(ActionEvent event) {
    }

    @FXML
    private void goLister(ActionEvent event) {
    }

    @FXML
    private void goToHome(MouseEvent event) {
    }
    
}
