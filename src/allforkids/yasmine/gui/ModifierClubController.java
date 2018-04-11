/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

import allforkids.yasmine.services.ClubService;
import allforkids.yasmine.entities.ClubEntity;

//import static gui.ListerClubController.I;
//import static gui.ListerClubController.N;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierClubController implements Initializable {

    @FXML
    private TextArea desclub;
    @FXML
    private TextField tel;
    @FXML
    private TextField nclub;
    @FXML
    private ComboBox<String> gouvchoice;

    ObservableList<String> Listgouvernerat = FXCollections.observableArrayList("tunis", "Nabeul", "bizert");
    @FXML
    private Button modifierclub;
    @FXML
    private AnchorPane zoneModif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        desclub.setText(ListerClubController.D);
        tel.setText(ListerClubController.T);
        nclub.setText(ListerClubController.N);
        gouvchoice.setValue(ListerClubController.E);
        gouvchoice.setItems(Listgouvernerat);
    }

    public void setDesclub(String desc) {
        this.desclub.setText(desc);
    }

    public void setTel(String tel) {
        this.tel.setText(tel);
    }

    public void setNclub(String nom) {
        this.nclub.setText(nom);
    }

//    public void setGouvchoice(ComboBox<?> gouvchoice) {
//        this.gouvchoice.setText(gouv);
//    }String gouv
//      public void setGouvchoice(String gouv) {
//        this.gouvchoice.setText(gouv);
//    }
    @FXML
    private void Modifier(ActionEvent event) {
        try {
            ClubService CService = new ClubService();
//            ClubEntity c2 = new ClubEntity(ListerClubController.I,nclub.getText(), desclub.getText(), tel.getText(), gouvchoice.getValue());

            ClubEntity c2 = new ClubEntity();
            c2.setIdclub(ListerClubController.I);
            c2.setNom(nclub.getText());
            c2.setDescription(desclub.getText());
            c2.setNumTel(tel.getText());
            c2.setGouvernorat(gouvchoice.getValue());

            CService.ModifierClub(c2);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherClub.fxml"));

            Parent root;

            root = loader.load();

            AfficherClubController ClubController = loader.getController();

            ClubController.setNomC(nclub.getText());
            ClubController.setDescC(desclub.getText());
            ClubController.setTelC(tel.getText());
            ClubController.setGouvC(gouvchoice.getValue());

            zoneModif.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ModifierClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
//     public  void loadClubM(){
//    
//      NomClub.setCellValueFactory(new PropertyValueFactory<>("nom"));
////        DesClub.setCellValueFactory(new PropertyValueFactory<>("description"));
////        NumClub.setCellValueFactory(new PropertyValueFactory<>("numTel"));
//
//        ClubService CService = new ClubService();
//        ArrayList arrayList = (ArrayList) CService.selectAll();
//        ObservableList observableList = FXCollections.observableArrayList(arrayList);
//        tableViewClub.setItems(observableList);
//
//    
//    }
}
