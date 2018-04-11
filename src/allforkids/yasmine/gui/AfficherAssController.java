/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AfficherAssController implements Initializable {

    @FXML
    private Label nomA;
    @FXML
    private Label descA;
    @FXML
    private Label telA;
    @FXML
    private Label gouvA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setNomA(String nomA) {
        this.nomA.setText(nomA); 
    }

    public void setDescA(String descA) {
        this.descA.setText(descA); 
    }

    public void setTelA(String telA) {
        this.telA.setText(telA);
    }

    public void setGouvA(String gouvA) {
        this.gouvA.setText(gouvA);
    }
    
    
    
}
