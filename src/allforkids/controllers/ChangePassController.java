/**
* @Project: AllForKids
* @Classe: ChangePassController
* @Date: 4 avr. 2018
* @Time: 23:04:48
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.User;
import allforkids.services.UserService;
import allforkids.utils.MyConnexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class ChangePassController implements Initializable{
    
    Connection cn = MyConnexion.getInstance().getConnection();
    UserService uss = new UserService();
    int idUser = uss.getId();
    User user = new User();

    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField rpassword;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton annulerButton;
    @FXML
    private JFXPasswordField currentPass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            user = uss.getUser(idUser);
        } catch (SQLException | IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    @FXML
    private void changePassword(ActionEvent event) throws SQLException, FileNotFoundException{
        String hashPass = user.getPassword();
        boolean verifPass = UserService.checkPassword(currentPass.getText(), hashPass);
        
        Alert al = new Alert(Alert.AlertType.ERROR);
        if(currentPass.getText().isEmpty()){
            al.setContentText("Veuillez saisir votre ancien mot de passe");
            al.showAndWait();
        }else{
            if(password.getText().isEmpty()){
                al.setContentText("Veuillez saisir le nouveau mot de passe");
                al.showAndWait();
            }else{
                if(rpassword.getText().isEmpty() || !rpassword.getText().equals(password.getText())){
                    al.setContentText("Veuillez retaper le nouveau mot de passe");
                    al.showAndWait();
                }
            }
        }
        
        if(verifPass){
            uss.changePassword(UserService.hashPassword(rpassword.getText()), user.getId());
            
            Stage stage = new Stage();
            stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            Alert succes = new Alert(Alert.AlertType.INFORMATION);
            succes.setContentText("Mot de Pass modifié avec succes");
        }
        else{
            al.setContentText("L'ancien mot de passe saisi n'est pas correct\nRessaisissez le à nouveau");
            al.showAndWait();
        }
        
        
    }

    
    @FXML
    public void annulerAction(ActionEvent event){
        Stage stage = new Stage();
        stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }
    
}



/**
*@Lau82 © 2018
*/
