/**
* @Project: AllForKids
* @Classe: AddOffreController
* @Date: 23 mars 2018
* @Time: 14:25:03
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.OffreTransport;
import allforkids.services.OffreTransportService;
import allforkids.services.UserService;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddOffreController implements Initializable{

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton ajouterButton;
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

    
    @FXML
    public void addOffreAction(ActionEvent event) throws SQLException, IOException{
        int nbreP = 0;
        double prx = 0;
        
        UserService uss = new UserService();
        OffreTransportService ots = new OffreTransportService();
        
        int id = uss.getId();
        
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
        
        OffreTransport offre = new OffreTransport(id, desc, dest, ddt, dfn, nbreP, nbreP, prx);
        
       
        if(description.getText() != null && destination.getText() != null && datedebut.getValue() != null && 
                dateFin.getValue() != null && nbrePlace.getText() != null && prix.getText() != null && nbreP > 0 && prx > 0){
            
            ots.addOffreTransport(offre);
            
            Stage stage = new Stage();
            stage = (Stage) ajouterButton.getScene().getWindow();
            stage.close();
            
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Succes");
            al.setHeaderText("Votre offre est enregistrée. \nVeillez rafraichir la liste");
            al.show();
            
        }
        else{
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("Check the information you have entered. \n");
            al.show();
        }
    }
    
    
    @FXML
    public void annulerAction(ActionEvent event){
        Stage stage = new Stage();
        stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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
