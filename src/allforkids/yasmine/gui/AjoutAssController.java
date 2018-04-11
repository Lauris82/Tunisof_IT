/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

import allforkids.entities.User;
import allforkids.services.UserService;
import allforkids.yasmine.entities.AssociationEntity;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import allforkids.yasmine.services.AssociationService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjoutAssController implements Initializable {
    UserService uss = new UserService();
    int idUser = uss.getId();
    User user = new User();

    @FXML
    private TextArea desclub;
    @FXML
    private TextField tel;
    @FXML
    private Button ajouterAssociation;
    @FXML
    private TextField nclub;
    @FXML
    private ComboBox<String> gouvchoice;
    
        ObservableList<String> Listgouvernerat = FXCollections.observableArrayList("tunis", "Nabeul", "bizert");
    @FXML
    private AnchorPane zone2A;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         gouvchoice.setValue("ariana");
        gouvchoice.setItems(Listgouvernerat);
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
        
      
            AssociationService AService = new AssociationService();
            
            AssociationEntity c2 = new AssociationEntity();
            System.out.println(idUser);
            c2.setAssociation_user(idUser);
            c2.setNom(nclub.getText());
            c2.setDescription(desclub.getText());
            c2.setNum_tel(tel.getText());
            c2.setGouvernorat(gouvchoice.getValue());
            
            
            AService.AjouterClub(c2);
            System.out.println("asso ajouter");
            
             TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("Associations");
            tray.setMessage("association ajouter");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(2500));

            tray.setRectangleFill(Color.valueOf("#f78c37"));
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allforkids/yasmine/gui/afficherAss.fxml"));

            Parent root;

            root = loader.load();
            

            AfficherAssController AssController = loader.getController();

            AssController.setNomA(nclub.getText());
            AssController.setDescA(desclub.getText());
            AssController.setTelA(tel.getText());
            AssController.setTelA(gouvchoice.getValue());

                        zone2A.getChildren().setAll(root);
            
    }
    
}
