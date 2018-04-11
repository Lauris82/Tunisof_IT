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
public class AfficherClubController implements Initializable {

    @FXML
    private Label nomC;
    @FXML
    private Label descC;
    @FXML
    private Label telC;
    @FXML
    private Label gouvC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    public void setNomC(String nom) {
        this.nomC.setText(nom); 
    }

    public void setDescC(String desc) {
        this.descC.setText(desc); 
    }

    public void setTelC(String tel) {
        this.telC.setText(tel);
    }

    public void setGouvC(String gouv) {
        this.gouvC.setText(gouv);
    }
    
}
