/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

import allforkids.yasmine.services.ClubService;
import allforkids.yasmine.entities.ClubEntity;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ListerClubController implements Initializable {

    @FXML
    private TableView<ClubEntity> tableViewClub;
    @FXML
    private TableColumn<ClubEntity, String> NomClub;

    @FXML
    private Label description;
    @FXML
    private Label tel;
    @FXML
    private Label Emp;
    @FXML
    private AnchorPane zoneDetail;

    public static String D;
    public static String T;
    public static String E;
    public static String N;
    public static int I;
    @FXML
    private AnchorPane zoneAn;
    @FXML
    private ChoiceBox<String> choiseBox;
    ObservableList<String> comboList = FXCollections.observableArrayList("Nom", "Emplacement");

    @FXML
    private TextField Recherche;
    @FXML
    private AnchorPane anTest;

//    private List<ClubEntity> listClub;
//    private ObservableList<ClubEntity> observableListClub;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadClub();
        choiseBox.setItems(comboList);

        Recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    filtrerClub((String) oldValue, (String) newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(ListerClubController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
//       tableViewClub.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->selectedClub(newValue));
//       tableViewClub.getSelectionModel().getSelectedItem()
    }

    void filtrerClub(String oldValue, String newValue) throws SQLException {

        ClubService ms = new ClubService();

        String choix = choiseBox.getValue();
        if (choix.equals("Nom")) {
            ObservableList<ClubEntity> filteredList = FXCollections.observableArrayList();
            if (Recherche.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
                tableViewClub.setItems((ObservableList<ClubEntity>) ms.selectAll());

            } else {
                newValue = newValue.toUpperCase();
                for (ClubEntity st : tableViewClub.getItems()) {

                    String filterNom = st.getNom();

                    if (filterNom.toUpperCase().contains(newValue)) {
                        filteredList.add(st);
                    }
                }

                tableViewClub.setItems(filteredList);
            }
        } else if (choix.equals("Emplacement")) {
            ObservableList<ClubEntity> filteredList = FXCollections.observableArrayList();
            if (Recherche.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
                tableViewClub.setItems((ObservableList<ClubEntity>) ms.selectAll());

            } else {
                newValue = newValue.toUpperCase();
                for (ClubEntity st : tableViewClub.getItems()) {

                    String filterAdress = st.getGouvernorat();

                    if (filterAdress.toUpperCase().contains(newValue)) {
                        filteredList.add(st);
                    }
                }
                tableViewClub.setItems(filteredList);
            }
        }

    }

    public void loadClub() {

        NomClub.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        DesClub.setCellValueFactory(new PropertyValueFactory<>("description"));
//        NumClub.setCellValueFactory(new PropertyValueFactory<>("numTel"));

        ClubService CService = new ClubService();
        ObservableList arrayList = (ObservableList) CService.selectAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        tableViewClub.setItems(observableList);

        tableViewClub.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedClub(newValue));

    }

    public void selectedClub(ClubEntity c) {
        description.setText(c.getDescription());

//    adresse.setText(c.getGouvernorat());
        tel.setText(c.getNumTel());

        Emp.setText(c.getGouvernorat());
        N = c.getNom();
        I = c.getIdclub();
//        System.out.println("club selected :" + c.getNom());
    }

    @FXML
    private void goModifier(ActionEvent event) {

        try {
            D = description.getText();
            T = tel.getText();
            E = Emp.getText();
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("modifierClub.fxml"));
            zoneAn.getChildren().setAll(a);
        } catch (IOException ex) {
            Logger.getLogger(ListerClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void goSupprimer(ActionEvent event) {

        ClubEntity c = tableViewClub.getSelectionModel().getSelectedItem();

        if (c == null) {
            Alert A = new Alert(Alert.AlertType.INFORMATION);
            A.setTitle("No club selected");
            A.setHeaderText(null);
            A.setContentText("Please select club for deletion.");
            A.showAndWait();
            return;

        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("DeletingClub");
        alert.setContentText("are you sure want to delete the club " + c.getNom() + "?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            ClubService CService = new ClubService();
            CService.supprimerClub(c.getIdclub());

            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("les Clubs");
            tray.setMessage("Club supprimer");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(2500));

            tray.setRectangleFill(Color.valueOf("#f78c37"));

            AnchorPane an;
            try {

                    an =(AnchorPane)FXMLLoader.load(getClass().getResource("listerClub.fxml"));
               
            zoneAn.getChildren().setAll(an);
//                Parent root = FXMLLoader.load(getClass().getResource("supprimerClub.fxml"));
//                Stage stage = new Stage();
//                Scene scene = new Scene(root);
//
//                stage.setScene(scene);
//                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(ListerClubController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
