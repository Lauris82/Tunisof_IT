/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

import allforkids.yasmine.entities.AssociationEntity;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ListerAssController implements Initializable {

    @FXML
    private AnchorPane anTest;
    @FXML
    private TableView<AssociationEntity> tableViewAss;
    @FXML
    private TableColumn<AssociationEntity, String> Association;
    @FXML
    private AnchorPane zoneDetailA;
    @FXML
    private Label description;
    @FXML
    private Label tel;
    @FXML
    private Label Emp;
    @FXML
    private ChoiceBox<String> choiseBox;
    ObservableList<String> comboList = FXCollections.observableArrayList("Nom", "Emplacement");
    @FXML
    private TextField Recherche;

    public static String N;
    public static int I;
    
     public static String D;
    public static String T;
    public static String E;
    
    @FXML
    private AnchorPane zone;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         loadAss();
        choiseBox.setItems(comboList);

        Recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    filtrerAss((String) oldValue, (String) newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(ListerAssController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
    }    
    
        void filtrerAss(String oldValue, String newValue) throws SQLException {

        AssociationService ms = new AssociationService();

        String choix = choiseBox.getValue();
        if (choix.equals("Nom")) {
            ObservableList<AssociationEntity> filteredList = FXCollections.observableArrayList();
            if (Recherche.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
                tableViewAss.setItems((ObservableList<AssociationEntity>) ms.selectAll());

            } else {
                newValue = newValue.toUpperCase();
                for (AssociationEntity st : tableViewAss.getItems()) {

                    String filterNom = st.getNom();

                    if (filterNom.toUpperCase().contains(newValue)) {
                        filteredList.add(st);
                    }
                }

                tableViewAss.setItems(filteredList);
            }
        } else if (choix.equals("Emplacement")) {
            ObservableList<AssociationEntity> filteredList = FXCollections.observableArrayList();
            if (Recherche.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
                tableViewAss.setItems((ObservableList<AssociationEntity>) ms.selectAll());

            } else {
                newValue = newValue.toUpperCase();
                for (AssociationEntity st : tableViewAss.getItems()) {

                    String filterAdress = st.getGouvernorat();

                    if (filterAdress.toUpperCase().contains(newValue)) {
                        filteredList.add(st);
                    }
                }
                tableViewAss.setItems(filteredList);
            }
        }

    }
        
        
public void loadAss() {

        Association.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        DesClub.setCellValueFactory(new PropertyValueFactory<>("description"));
//        NumClub.setCellValueFactory(new PropertyValueFactory<>("numTel"));

        AssociationService CService = new AssociationService();
        ObservableList arrayList = (ObservableList) CService.selectAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        tableViewAss.setItems(observableList);

        tableViewAss.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedAss(newValue));

    }

    public void selectedAss(AssociationEntity c) {
        description.setText(c.getDescription());

//    adresse.setText(c.getGouvernorat());
        tel.setText(c.getNum_tel());

        Emp.setText(c.getGouvernorat());
        N = c.getNom();
        I = c.getId_aasociation();
//        System.out.println("club selected :" + c.getNom());
    }        

    @FXML
    private void goSuppA(ActionEvent event) {
        
        
        AssociationEntity c = tableViewAss.getSelectionModel().getSelectedItem();

        if (c == null) {
            Alert A = new Alert(Alert.AlertType.INFORMATION);
            A.setTitle("No association selected");
            A.setHeaderText(null);
            A.setContentText("Please select association for deletion.");
            A.showAndWait();
            return;

        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("suppression association");
        alert.setContentText("voulez-vous supprimer " + c.getNom() + "?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            AssociationService CService = new AssociationService();
            CService.supprimerAssociation(c.getId_aasociation());
            System.out.println(c.getId_aasociation());
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("Association");
            tray.setMessage("Association supprimer");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(2500));

            tray.setRectangleFill(Color.valueOf("#f78c37"));

            AnchorPane an;
            try {

                    an =(AnchorPane)FXMLLoader.load(getClass().getResource("listerAss.fxml"));
               
            anTest.getChildren().setAll(an);
//                Parent root = FXMLLoader.load(getClass().getResource("supprimerClub.fxml"));
//                Stage stage = new Stage();
//                Scene scene = new Scene(root);
//
//                stage.setScene(scene);
//                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(ListerAssController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        
        
        
    }

    @FXML
    private void goModifierA(ActionEvent event) {
        try {
            D = description.getText();
            T = tel.getText();
            E = Emp.getText();
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("modifierAss.fxml"));
            zone.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(ListerAssController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
