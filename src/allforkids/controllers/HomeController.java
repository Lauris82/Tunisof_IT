/**
* @Project: AllForKids
* @Classe: HomeController
* @Date: 19 mars 2018
* @Time: 19:16:35
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.User;
import allforkids.services.UserService;
import allforkids.utils.MyConnexion;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


public class HomeController implements Initializable{
        
    AnchorPane home;
    
    @FXML
    private AnchorPane homeContent;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton notificationButton;
    @FXML
    private JFXButton reservationButton;
    @FXML
    private JFXButton reclamationButton;
    @FXML
    private JFXButton parametreButton;
    @FXML
    private JFXButton logoutButton;
    @FXML
    private ImageView profilPicture;
    @FXML
    private Label usernameLab;
    @FXML
    private ImageView menuDown;
    @FXML
    private ImageView menuUp;
    @FXML
    private AnchorPane menuAnchorPane;
    @FXML
    private Label notificationLabel;
    @FXML
    private JFXButton profileButton;

    
    Connection cn = MyConnexion.getInstance().getConnection();
    UserService uss = new UserService();
    int id = uss.getId();
    User user;
    public HomeController() throws SQLException, IOException {
        this.user = uss.getUser(id);
    }
    
    
    @FXML
    private void logoutAction(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/Login.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    private void goToProfileAction(ActionEvent event){
        try {
            menuDown.setVisible(true);
            menuAnchorPane.setVisible(false);
            home = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/Profile.fxml"));
            setNode(home);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } 
    }
    
    
    @FXML
    private void goToNotificationAction(ActionEvent event) throws SQLException{
        try {
            menuDown.setVisible(true);
            menuAnchorPane.setVisible(false);
            uss.setNotificationValue(id);
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/ListeNotification.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/allforkids/ressources/images/logo1.png")));
            stage.setTitle("AllForKids");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(reservationButton.getScene().getWindow());
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } 
    }
    
    
    
    @FXML
    private void goToReservationAction(ActionEvent event){
        try {
            menuDown.setVisible(true);
            menuAnchorPane.setVisible(false);
            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/ListeReservation.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/allforkids/ressources/images/logo1.png")));
            stage.setTitle("AllForKids");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(reservationButton.getScene().getWindow());
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } 
    }
    
    
    @FXML
    private void actualizeHomeAction(ActionEvent event){
        try {
            home = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/TypeHome.fxml"));
            setNode(home);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } 
    }
    
    
    @FXML
    private void showMenuContentAction(MouseEvent event){
        menuDown.setVisible(false);
        menuAnchorPane.setVisible(true);
        menuAnchorPane.toFront();
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(menuAnchorPane);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    
    @FXML
    private void hideMenuContentAction(MouseEvent event){
        menuDown.setVisible(true);
        menuAnchorPane.setVisible(false);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(menuAnchorPane);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        menuAnchorPane.setVisible(false);
        createPage();

        Circle circle = new Circle(); 
        circle.setCenterX(75.0f); 
        circle.setCenterY(75.0f); 
        circle.setRadius(55.0f); 
        circle.setStrokeWidth(20); 
        profilPicture.setClip(circle);
        
        if(user.getNotification() > 0){
            Image img = new Image(getClass().getResourceAsStream("/allforkids/ressources/images/Notification.png"));
            Notifications notificationBuilder = Notifications.create()
                    .title("Notification")
                    .text("Vous avez de nouvelles notification")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();

            notificationBuilder.show();
            playSound();
        }
        
    }
    
    private void setNode(Node node){
        homeContent.getChildren().clear();
        homeContent.getChildren().add(node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    private void createPage() {
        try {
            home = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/TypeHome.fxml"));
            
            setNode(home);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void playSound(){
        AudioClip sound = new AudioClip(this.getClass().getResource("/allforkids/ressources/images/iphone.mp3").toString());
        sound.play();
    }
    
    public AnchorPane getHomeContent() {
        return homeContent;
    }

    public void setHomeContent(AnchorPane homeContent) {
        this.homeContent = homeContent;
    }

    public AnchorPane getMenuAnchorPane() {
        return menuAnchorPane;
    }

    public ImageView getProfilPicture() {
        return profilPicture;
    }

    public void setProfilPicture(Image profilPicture) {
        this.profilPicture.setImage(profilPicture);
    }

    public Label getUsernameLab() {
        return usernameLab;
    }

    public void setUsernameLab(String usernameLab) {
        this.usernameLab.setText(usernameLab);
    }

    public Label getNotificationLabel() {
        return notificationLabel;
    }

    public void setNotificationLabel(String notificationLabel) {
        this.notificationLabel.setText(notificationLabel);
    }
    
}



/**
*@Lau82 © 2018
*/
