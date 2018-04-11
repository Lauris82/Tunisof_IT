/**
* @Project: AllForKids
* @Classe: ListeReservationController
* @Date: 30 mars 2018
* @Time: 01:20:54
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.OffreTransport;
import allforkids.entities.ReservationOffreTransport;
import allforkids.entities.User;
import allforkids.services.OffreTransportService;
import allforkids.services.ReservationOffreTransportService;
import allforkids.services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ListeReservationController implements Initializable{

    UserService uss = new UserService();
    int idUser = uss.getId();
    User user = new User();
    User offreur = new User();
    ReservationOffreTransportService rots = new ReservationOffreTransportService();
    ReservationOffreTransport rot = new ReservationOffreTransport();
    OffreTransportService ots = new OffreTransportService();
    OffreTransport offre = new OffreTransport();
    ObservableList<ReservationOffreTransport> myList = FXCollections.observableArrayList();
    ObservableList<String> currentList = FXCollections.observableArrayList ();
        
    @FXML
    private JFXListView<String> reservationListView;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        reservationListView.setCellFactory(param -> new ReservationCell());
        reload();
    }
    
    static class ReservationCell extends JFXListCell<String>{
        
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        HBox buttonContainer = new HBox();
        Pane pane = new Pane();
        
        
        JFXButton annuleResButton = new JFXButton("Annuler reservation");
        JFXButton detailButton = new JFXButton("Voir l'offre");
        Label sujetLab = new Label();
        
        public ReservationCell(){
            super();
            annuleResButton.setVisible(true);
            annuleResButton.setStyle("fx-background-color: #FFF");
            detailButton.setStyle("fx-background-color: #CDCDCD");
            detailButton.setVisible(true);
            buttonContainer.getChildren().addAll(annuleResButton, detailButton);
            vbox.getChildren().addAll(sujetLab, buttonContainer);
            hbox.getChildren().addAll(vbox);
            
//            VBox.setVgrow(pane, Priority.ALWAYS);
        }
    }
    
    
    public void reload(){
        try {
            user = uss.getUser(idUser);
        } catch (SQLException | IOException ex) {
            System.err.println(ex);
        }
        myList = rots.listerReservationOffreTransport(idUser);
        
        for(ReservationOffreTransport res : myList){
            try {
                offre = ots.getOffreTransport(res.getOffre());
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            String info = "Vous avez reservez l'offre: \"" +offre.getDescription()+ "\" \nDestination: \""+offre.getDestination()+ "\" le " +res.getDateReservation();
            
            currentList.add(info);
        }
        reservationListView.setItems(currentList);
    }
    
}



/**
*@Lau82 © 2018
*/
