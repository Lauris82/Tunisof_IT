/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

import allforkids.entities.User;
import allforkids.services.UserService;
import allforkids.yasmine.services.ClubService;
import allforkids.yasmine.entities.ClubEntity;
import java.io.IOException;
import java.net.URL;
import java.util.Observer;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import allforkids.yasmine.validation.validation;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterClubController implements Initializable {
    UserService uss = new UserService();
    int idUser = uss.getId();
    User user = new User();

    @FXML
    private TextArea desclub;
    @FXML
    private Button ajouterclub;
    @FXML
    private TextField tel;
    @FXML
    private TextField nclub;
    @FXML
    private ComboBox<String> gouvchoice;

    ObservableList<String> Listgouvernerat = FXCollections.observableArrayList("tunis", "Nabeul", "bizert");
    @FXML
    private AnchorPane zone2;
    @FXML
    private Label idTel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gouvchoice.setValue("ariana");
        gouvchoice.setItems(Listgouvernerat);

    }

    @FXML
    private void ajouter(ActionEvent event) {

//        boolean isTitleEmpty= validation.validation.isNumber(tel, idTel, "erreur num tel");
boolean isTitleEmpty= validation.isNumber(tel);
if (isTitleEmpty ){
        try {
            ClubService CService = new ClubService();
//        ClubEntity cl=new ClubEntity(3, nclub.getText(), desclub.getText(), tel.getText(), gouv.getText());
//            ClubEntity c2 = new ClubEntity(3, nclub.getText(), desclub.getText(), tel.getText(), gouvchoice.getValue());
            ClubEntity c2 = new ClubEntity();
            c2.setClub_user(idUser);
            c2.setNom(nclub.getText());
            c2.setDescription(desclub.getText());
            c2.setNumTel(tel.getText());
            c2.setGouvernorat(gouvchoice.getValue());
            
            
            CService.AjouterClub(c2);

 FXMLLoader loader = new FXMLLoader(getClass().getResource("/allforkids/yasmine/gui/afficherClub.fxml"));

            Parent root;

            root = loader.load();
            
          

            AfficherClubController ClubController = loader.getController();

            ClubController.setNomC(nclub.getText());
            ClubController.setDescC(desclub.getText());
            ClubController.setTelC(tel.getText());
            ClubController.setGouvC(gouvchoice.getValue());

                        zone2.getChildren().setAll(root);
        
      

            

        } catch (IOException ex) {
            Logger.getLogger(AjouterClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }else{
    idTel.setText("erreur");
}
    }
}
