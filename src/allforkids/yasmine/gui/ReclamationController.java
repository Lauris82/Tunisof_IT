/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

import allforkids.entities.User;
import allforkids.services.UserService;
import allforkids.yasmine.services.ReclamationService;
import allforkids.yasmine.entities.ReclamationEntity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ReclamationController implements Initializable {
    UserService uss = new UserService();
    int idUser = uss.getId();
    User user = new User();

    @FXML
    private AnchorPane reclamation;
    @FXML
    private TextArea contenu;
    @FXML
    private TextField objet;
    @FXML
    private TextField mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerRec(ActionEvent event) {
    
             ReclamationService RService = new ReclamationService();
          
             ReclamationEntity  R = new ReclamationEntity();
        
              R.setObjetRec(objet.getText());//
         
              R.setEtatRec(0);
              R.setContenuRec(contenu.getText());//
              R.setUser(idUser);
              R.setMail(mail.getText());//
          
            
            RService.EnvoiReclamation(R);
        
        
        
        
    }
    
}
