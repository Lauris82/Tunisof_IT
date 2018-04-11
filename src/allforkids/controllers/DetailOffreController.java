/**
* @Project: AllForKids
* @Classe: TypeOffreController
* @Date: 22 mars 2018
* @Time: 16:24:14
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.OffreTransport;
import allforkids.entities.User;
import allforkids.services.OffreTransportService;
import allforkids.services.ReservationOffreTransportService;
import allforkids.services.UserService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class DetailOffreController implements Initializable{
    
    OffreTransport offre = new OffreTransport();
    ReservationOffreTransportService rots = new ReservationOffreTransportService();
    OffreTransportService ots = new OffreTransportService();
    
    UserService uss = new UserService();
    User user = new User();
    int idUser = 0;
    int idOffre = 0;

    @FXML
    private Label userLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label destinationLabel;
    @FXML
    private Label dateDebutLabel;
    @FXML
    private Label dateFinLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView imageUser;
    @FXML
    private Label placeRestantLabel;
    @FXML
    private JFXButton modifierButton;
    @FXML
    private JFXButton reserverButton;
    @FXML
    private JFXButton supprimerButton;
    @FXML
    private Label annulerReservationLabel;
    
    
    
    
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
    
    
    @FXML
    public void supprimerOffreAction(ActionEvent event){
        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("allforkids/GUI/ListeOffre.fxml"));
        
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        ListeOffreController listeOffre = loader.getController();
        
        int id = listeOffre.getIdOffre();
        
        ots.deleteOffreTransport(id);
        
        Stage stage = new Stage();
        stage = (Stage) supprimerButton.getScene().getWindow();
        stage.close();
        
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Allforkids");
        al.setHeaderText("Votre offre a été supprimé avec succès. \nVeillez rafraichir la liste");
        al.show();
            
    }
    

    @FXML
    private void goToModifierAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/UpdateOffre.fxml"));
        Scene scene = new Scene(root);
        
        stage = (Stage) modifierButton.getScene().getWindow();
        stage.close();
        
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/allforkids/ressources/images/logo1.png")));
        stage.setTitle("AllForKids");
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
    private void reserverOffreAction(ActionEvent event) throws SQLException, IOException{
        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("allforkids/GUI/ListeOffre.fxml"));
        
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        ListeOffreController listeOffre = loader.getController();
        
        idOffre = listeOffre.getIdOffre();
        offre = ots.getOffreTransport(idOffre);
        idUser = uss.getId();
        user = uss.getUser(idUser);
            
        int dejaReserve = rots.dejaReserver(idUser, idOffre);
        
        if(dejaReserve == 1){
            Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
            alerte.setTitle("Allforkids");
            alerte.setHeaderText("Confirmation");
            alerte.setContentText("Vous avez déjà reservé cette offre.\nVoulez-vous réserver de nouveau ?");

            Button confirmButton = (Button) alerte.getDialogPane().lookupButton(ButtonType.OK);
            Button exitButton = (Button) alerte.getDialogPane().lookupButton(ButtonType.CANCEL);
            confirmButton.setText("Confirmer");
            exitButton.setText("Annuler");
            Optional<ButtonType> choice = alerte.showAndWait();

            if(ButtonType.OK.equals(choice.get())){
                rots.reserverOffreTransport(offre);
                
                Stage stage = new Stage();
                stage = (Stage) reserverButton.getScene().getWindow();
                stage.close();

                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Succes");
                al.setHeaderText("Votre réservation a été effectué. \n");
                al.show();
            }
            if(ButtonType.CANCEL.equals(choice.get())){
                alerte.close();
            }
        }else{
            rots.reserverOffreTransport(offre);
                
            Stage stage = new Stage();
            stage = (Stage) reserverButton.getScene().getWindow();
            stage.close();

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Succes");
            al.setHeaderText("Votre réservation a été effectué. \n");
            al.show();
//            System.err.println("Ok");
        }
        
    }

    @FXML
    private void annulerReservationAction(MouseEvent event) throws SQLException {
        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("allforkids/GUI/ListeOffre.fxml"));
        
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        ListeOffreController listeOffre = loader.getController();
        
        idOffre = listeOffre.getIdOffre();
        offre = ots.getOffreTransport(idOffre);
        idUser = uss.getId();
        

        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("Allforkids");
        alerte.setHeaderText("Confirmation");
        alerte.setContentText("Ceci annulera toutes vos \nréservations pour cette Offre.\nVoulez-vous continuer cette action ?");

        Button confirmButton = (Button) alerte.getDialogPane().lookupButton(ButtonType.OK);
        Button exitButton = (Button) alerte.getDialogPane().lookupButton(ButtonType.CANCEL);
        confirmButton.setText("Confirmer");
        exitButton.setText("Annuler");
        Optional<ButtonType> choice = alerte.showAndWait();

        if(ButtonType.OK.equals(choice.get())){
            rots.deleteReservation(idUser, idOffre);

            Stage stage = new Stage();
            stage = (Stage) reserverButton.getScene().getWindow();
            stage.close();

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Succes");
            al.setHeaderText("Votre réservation a été annulé. \n");
            al.show();
        }
        if(ButtonType.CANCEL.equals(choice.get())){
            alerte.close();
        }
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }

    
    
    public Label getAnnulerReservationLabel() {
        return annulerReservationLabel;
    }

    public void setAnnulerReservationLabel(Label annulerReservationLabel) {
        this.annulerReservationLabel = annulerReservationLabel;
    }
    
    public AnchorPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
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

    public JFXButton getModifierButton() {
        return modifierButton;
    }

    public void setModifierButton(JFXButton modifierButton) {
        this.modifierButton = modifierButton;
    }

    public JFXButton getReserverButton() {
        return reserverButton;
    }

    public void setReserverButton(JFXButton reserverButton) {
        this.reserverButton = reserverButton;
    }

    public JFXButton getSupprimerButton() {
        return supprimerButton;
    }

    public void setSupprimerButton(JFXButton supprimerButton) {
        this.supprimerButton = supprimerButton;
    }
}



/**
*@Lau82 © 2018
*/
