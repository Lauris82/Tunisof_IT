/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.gui;

import allforkids.yasmine.services.ClubService;
import allforkids.yasmine.entities.ClubEntity;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tray.animations.AnimationType;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SupprimerClubController implements Initializable {

    @FXML
    private TableColumn<ClubEntity, String> NameClub;
    @FXML
    private TableView<ClubEntity> ClubView;
    @FXML
    private Label Des;
    @FXML
    private Label Tel;
    @FXML
    private Label Emp;

    public static String D;
    public static String T;
    public static String E;
    public static String N;
    public static int I;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chargerClub();
          
        
    }

    public void chargerClub() {

        NameClub.setCellValueFactory(new PropertyValueFactory<>("nom"));

        ClubService CService = new ClubService();
        ArrayList arrayList = (ArrayList) CService.selectAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        ClubView.setItems(observableList);

        ClubView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecteClub(newValue));

    }

    private void selecteClub(ClubEntity c) {
        Des.setText(c.getDescription());

//    adresse.setText(c.getGouvernorat());
        Tel.setText(c.getNumTel());

        Emp.setText(c.getGouvernorat());
        N = c.getNom();
        I = c.getIdclub();

    }

}
