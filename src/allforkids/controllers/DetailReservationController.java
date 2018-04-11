/**
* @Project: AllForKids
* @Classe: DetailReservationController
* @Date: 9 avr. 2018
* @Time: 16:16:24
*
* @author Lauris
*/


package allforkids.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class DetailReservationController implements Initializable{

    @FXML
    private Label descriptionLabel;
    @FXML
    private Label destinationLabel;
    @FXML
    private Label dateDebutLabel;
    @FXML
    private Label dateFinLabel;
    @FXML
    private Label placeRestantLabel;
    @FXML
    private Label userLabel;
    @FXML
    private ImageView imageUser;
    @FXML
    private Label prixLabel;
    @FXML
    private Label annulerReservationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    
    
    
    public void setOffreDetail(String user, String description, String destination, String dateDebut, String dateFin, String place, String prix, Image image){
        this.userLabel.setText(user);
        this.descriptionLabel.setText(description);
        this.destinationLabel.setText(destination);
        this.dateDebutLabel.setText(dateDebut);
        this.dateFinLabel.setText(dateFin);
        this.placeRestantLabel.setText(place);
        this.prixLabel.setText(prix);
        this.imageUser.setImage(image);
    }
    
    
    public Label getAnnulerReservationLabel() {
        return annulerReservationLabel;
    }

    public void setAnnulerReservationLabel(Label annulerReservationLabel) {
        this.annulerReservationLabel = annulerReservationLabel;
    }
    
    public Label getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(String userLabel) {
        this.userLabel.setText(userLabel);
    }

    public Label getDescriptionLabel() {
        return descriptionLabel;
    }

    public void setDescriptionLabel(String descriptionLabel) {
        this.descriptionLabel.setText(descriptionLabel);
    }

    public Label getDestinationLabel() {
        return destinationLabel;
    }

    public void setDestinationLabel(String destinationLabel) {
        this.destinationLabel.setText(destinationLabel);
    }

    public Label getDateDebutLabel() {
        return dateDebutLabel;
    }

    public void setDateDebutLabel(String dateDebutLabel) {
        this.dateDebutLabel.setText(dateDebutLabel);
    }

    public Label getDateFinLabel() {
        return dateFinLabel;
    }

    public void setDateFinLabel(String dateFinLabel) {
        this.dateFinLabel.setText(dateFinLabel);
    }

    public Label getPrixLabel() {
        return prixLabel;
    }

    public void setPrixLabel(String prixLabel) {
        this.prixLabel.setText(prixLabel);
    }

    public ImageView getImageUser() {
        return imageUser;
    }

    public void setImageUser(Image imageUser) {
        this.imageUser.setImage(imageUser);
    }

    public Label getPlaceRestantLabel() {
        return placeRestantLabel;
    }

    public void setPlaceRestantLabel(Label placeRestantLabel) {
        this.placeRestantLabel = placeRestantLabel;
    }

    
}



/**
*@Lau82 © 2018
*/
