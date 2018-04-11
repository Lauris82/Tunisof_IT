/**
* @Project: AllForKids
* @Classe: NotificationController
* @Date: 9 avr. 2018
* @Time: 23:22:20
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.Notification;
import allforkids.entities.User;
import allforkids.services.NotificationService;
import allforkids.services.UserService;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class NotificationController implements Initializable{

    UserService uss = new UserService();
    int id = uss.getId();
    User user = new User();
    User emetteur = new User();
    NotificationService notfs = new NotificationService();
    ObservableList<Notification> myList = FXCollections.observableArrayList();
    
    ObservableList<String> currentList = FXCollections.observableArrayList ();
    
    
    @FXML
    private JFXListView<String> notificationListView;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        notificationListView.setCellFactory(param -> new NotifiationCell());
        reloadString();
    
    }
    
    
    static class NotifiationCell extends JFXListCell<String>{
        
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        Pane pane = new Pane();
        
        
        Label lbNomEmetteur = new Label();
        Label lbSujet = new Label();
        Notification lastItem;
        
        
        public NotifiationCell(){
            super();

            vbox.getChildren().addAll(lbNomEmetteur, lbSujet);
            hbox.getChildren().addAll(vbox);

            VBox.setVgrow(pane, Priority.ALWAYS);
        }
    }
    
    
    private void reloadString(){
         try {
            user = uss.getUser(id);
        } catch (SQLException | IOException ex) {
            System.err.println(ex);
        }
        myList = notfs.listerNotification(id);
        
        for(Notification notf : myList){
            try {
                emetteur = uss.getUser(notf.getEmetteur());
            } catch (SQLException | IOException ex) {
                System.err.println(ex);
            }
            String info = "Notification de " +emetteur.getUsername()+ " le: "+notf.getDateNotification()+ "\n" +notf.getSujet();
            
            currentList.add(info);
        }
        
        notificationListView.setItems(currentList);
    }
    
}





/**
*@Lau82 © 2018
*/
