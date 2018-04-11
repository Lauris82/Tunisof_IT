/**
* @Project: AllForKids
* @Classe: UpdateOffreController
* @Date: 24 mars 2018
* @Time: 14:31:48
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.OffreTransport;
import allforkids.services.OffreTransportService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class UpdateOffreController implements Initializable{
    
    OffreTransportService ots = new OffreTransportService();
    OffreTransport offre = new OffreTransport();

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton annulerButton;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField destination;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private JFXTextField nbrePlace;

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("allforkids/GUI/ListeOffre.fxml"));
        
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        ListeOffreController listeOffre = loader.getController();
        
        int id = listeOffre.getIdOffre();
        
        try {
            offre = ots.getOffreTransport(id);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        description.setText(offre.getDescription());
        destination.setText(offre.getDestination());
        datedebut.setValue(offre.getDateDebut());
        dateFin.setValue(offre.getDateFin());
        nbrePlace.setText(String.valueOf(offre.getNombrePlace()));
        prix.setText(String.valueOf(offre.getPrix()));
        
    }
    
    
    @FXML
    public void annulerAction(ActionEvent event){
        Stage stage = new Stage();
        stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }

    
    @FXML
    private void updateOffreAction(ActionEvent event) {
        
        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("allforkids/GUI/ListeOffre.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        ListeOffreController listeOffre = loader.getController();
        int idO = listeOffre.getIdOffre();
        
        
        int nbreP = 0;
        double prx = 0;
        
        boolean intExiste = isInt(nbrePlace);
        boolean doubleExiste = isDouble(prix);
        
        String desc = description.getText();
        String dest = destination.getText();
        LocalDate ddt = datedebut.getValue();
        LocalDate dfn = dateFin.getValue();
        
        if(intExiste == true){
            nbreP = Integer.valueOf(nbrePlace.getText());
        }
        if(doubleExiste == true){
            prx = Double.valueOf(prix.getText());  
        }
        
        offre = new OffreTransport(desc, dest, ddt, dfn, nbreP, nbreP, prx);
       
        if(description.getText() != null && destination.getText() != null && datedebut.getValue() != null && 
                dateFin.getValue() != null && nbrePlace.getText() != null && prix.getText() != null && nbreP > 0 && prx > 0){
            
            ots.updateOffreTransport(offre, idO);
            
            Stage stage = new Stage();
            stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Succes");
            al.setHeaderText("Votre offre a été modifié. \nVeillez rafraichir la liste");
            al.show();
            
        }
        else{
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("Check the information you have entered. \n");
            al.show();
        }
    }
    

    private boolean isInt(TextField input){
        try{
            int age = Integer.parseInt(input.getText());
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    private boolean isDouble(TextField input){
        try{
            double age = Double.parseDouble(input.getText());
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
}



/**
*@Lau82 © 2018
*/
