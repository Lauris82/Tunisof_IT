/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

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
import allforkids.yasmine.services.AssociationService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierAssController implements Initializable {

    @FXML
    private TextArea desAss;
    @FXML
    private TextField tel;
    @FXML
    private Button modifierAss;
    @FXML
    private ComboBox<String> gouvchoice;
    @FXML
    private TextField nAss;

    ObservableList<String> Listgouvernerat = FXCollections.observableArrayList("tunis", "Nabeul", "bizert");
    @FXML
    private AnchorPane zoneModifA;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        desAss.setText(ListerAssController.D);
        tel.setText(ListerAssController.T);
        nAss.setText(ListerAssController.N);
        gouvchoice.setValue(ListerAssController.E);
        gouvchoice.setItems(Listgouvernerat);
        
    }    

    public void setDesAss(String desAss) {
        this.desAss.setText(desAss);
    }

    public void setTel(String tel) {
        this.tel.setText(tel);
    }

    public void setnAss(String nAss) {
        this.nAss.setText(nAss);
    }
    
    

    @FXML
    private void modifierA(ActionEvent event) {
        
           try {
            AssociationService CService = new AssociationService();

            AssociationEntity c2 = new AssociationEntity();
            c2.setId_aasociation(ListerAssController.I);
            c2.setNom(nAss.getText());
            c2.setDescription(desAss.getText());
            c2.setNum_tel(tel.getText());
            c2.setGouvernorat(gouvchoice.getValue());

            CService.ModifierAssociation(c2);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherAss.fxml"));

            Parent root;

            root = loader.load();

            AfficherAssController AssController = loader.getController();

            AssController.setNomA(nAss.getText());
            AssController.setDescA(desAss.getText());
            AssController.setTelA(tel.getText());
            AssController.setGouvA(gouvchoice.getValue());

            zoneModifA.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ModifierAssController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }
    
}
