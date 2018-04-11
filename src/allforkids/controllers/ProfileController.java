/**
* @Project: AllForKids
* @Classe: ProfileController
* @Date: 4 avr. 2018
* @Time: 23:06:44
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.User;
import allforkids.services.UserService;
import allforkids.utils.MyConnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ProfileController implements Initializable{
    Connection cn = MyConnexion.getInstance().getConnection();
    UserService uss = new UserService();
    int idUser = uss.getId();
    User user = new User();
    
    @FXML
    private Label editProfileLabel;
    @FXML
    private ImageView profilPicture;
    @FXML
    private Label changePassLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label numeroLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label nomLabel;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            user = uss.getUser(idUser);
        } catch (SQLException | IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        String recupererRole = user.getRoles();
        String role = "";
        if(recupererRole.contains("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
            role = "ADMIN";
        }
        if(recupererRole.contains("a:0:{}")){
            role = "PARENT";
        }
        if(recupererRole.contains("a:1:{i:0;s:25:\"ROLE_RESPONSABLE_GARDERIE\";}")){
            role = "RESPONSABLE_GARDERIE";
        }
        if(recupererRole.contains("a:1:{i:0;s:21:\"ROLE_RESPONSABLE_CLUB\";}")){
            role = "RESPONSABLE_CLUB";
        }
        
        
        File file = new File("C:\\wamp64\\www\\AllForKids\\web\\image_user\\" + user.getImage().toString());
        Image image = new Image(file.toURI().toString(), 125, 125, false, false);
        
        Circle circle = new Circle(); 
        circle.setCenterX(75.0f); 
        circle.setCenterY(75.0f); 
        circle.setRadius(70.0f); 
        circle.setStrokeWidth(20); 
        profilPicture.setClip(circle);
        
        profilPicture.setImage(image);

        setProfile(image, user.getNom(), user.getPrenom(), role, String.valueOf(user.getDateNaissance()), 
                String.valueOf(user.getContact()), user.getEmail(), user.getAdresse());
        
    }
    

    @FXML
    private void goToEditProfileAction(MouseEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/EditProfile.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/allforkids/ressources/images/logo1.png")));
        stage.setTitle("AllForKids");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(editProfileLabel.getScene().getWindow());
        stage.show();
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(root);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    

    @FXML
    private void goToChangePassAction(MouseEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/ChangePass.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/allforkids/ressources/images/logo1.png")));
        stage.setTitle("AllForKids");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(editProfileLabel.getScene().getWindow());
        stage.show();
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(root);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    
    ////////////////////////////getters & setters///////////////////////
    public ImageView getProfilPicture() {
        return profilPicture;
    }

    public void setProfilPicture(Image profilPicture) {
        this.profilPicture.setImage(profilPicture);
    }

    public Label getRoleLabel() {
        return roleLabel;
    }

    public void setRoleLabel(String roleLabel) {
        this.roleLabel.setText(roleLabel);
    }

    public Label getBirthdayLabel() {
        return birthdayLabel;
    }

    public void setBirthdayLabel(String birthdayLabel) {
        this.birthdayLabel.setText(birthdayLabel);
    }

    public Label getAdresseLabel() {
        return adresseLabel;
    }

    public void setAdresseLabel(String adresseLabel) {
        this.adresseLabel.setText(adresseLabel);
    }

    public Label getNumeroLabel() {
        return numeroLabel;
    }

    public void setNumeroLabel(String numeroLabel) {
        this.numeroLabel.setText(numeroLabel);
    }

    public Label getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(String emailLabel) {
        this.emailLabel.setText(emailLabel);
    }

    public Label getPrenomLabel() {
        return prenomLabel;
    }

    public void setPrenomLabel(String prenomLabel) {
        this.prenomLabel.setText(prenomLabel);
    }

    public Label getNomLabel() {
        return nomLabel;
    }

    public void setNomLabel(String nomLabel) {
        this.nomLabel.setText(nomLabel);
    }

    public void setProfile(Image profilPicture, String nomLabel, String prenomLabel, String roleLabel, String birthdayLabel,  String numeroLabel, String emailLabel, String adresseLabel) {
        this.profilPicture.setImage(profilPicture);
        this.nomLabel.setText(nomLabel);
        this.prenomLabel.setText(prenomLabel);
        this.roleLabel.setText(roleLabel);
        this.birthdayLabel.setText(birthdayLabel);
        this.numeroLabel.setText(numeroLabel);
        this.emailLabel.setText(emailLabel);
        this.adresseLabel.setText(adresseLabel);
    }

}



/**
*@Lau82 © 2018
*/
