/**
* @Project: AllForKids
* @Classe: ForgetPasswordController
* @Date: 10 avr. 2018
* @Time: 13:47:30
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.User;
import allforkids.services.SmsService;
import allforkids.services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class ForgetPasswordController implements Initializable{

    Alert al = new Alert(Alert.AlertType.ERROR);
    private String verifCode;
    private long userNum;
    int isEmail = 0;
    UserService uss = new UserService();
    int idUser = uss.getId();
    User user;
    
    @FXML
    private Label numeroLabel;
    @FXML
    private JFXButton sendCodeButton;
    @FXML
    private JFXTextField codeTextField;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField rpassword;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXTextField emailTextField;
    @FXML
    private JFXButton verifierEmailButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendCodeButton.setVisible(false);
        numeroLabel.setVisible(false);
        password.setVisible(false);
        rpassword.setVisible(false);
        saveButton.setVisible(false);
        codeTextField.setVisible(false);
    }
    
    
    @FXML
    private void verifierEmailAction(ActionEvent event){
        if(emailTextField.getText().isEmpty()){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("Remplissez le champ Email. \n");
            al.show();
        }else{
            isEmail = uss.emailExiste(emailTextField.getText());
            System.out.println(isEmail);
        }
        
        if(isEmail == 1){
            numeroLabel.setVisible(true);
            sendCodeButton.setVisible(true);
            try {
                user = uss.getUserByEmail(emailTextField.getText());
            } catch (SQLException | IOException ex) {
                System.err.println("Cet Email n'existe pas dans notre base");
            }
            userNum = user.getContact();
            String num = Long.toString(userNum);
            System.out.println(userNum);
            numeroLabel.setText("Recevoir le code de vérification: +216****"+ num.substring(4, 8));
        }else{
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText("Cet Email n'existe pas dans notre base. \n");
            al.show();
        }
    }

    @FXML
    private void envoyerCodeAction(ActionEvent event){
        codeTextField.setVisible(true);
        
        SmsService sms = new SmsService();
        verifCode = makeVerifCode();
        String verifMessage = "\nMessage de AllForKids\nCode de vérification: " + verifCode + "\nMerci.";
       
        System.out.println(userNum);
        System.out.println(verifCode);
        
        sms.createSMS(userNum, verifMessage);
    }
    

    @FXML
    private void showPassFields(KeyEvent event) throws SQLException {
        if(codeTextField.getText().equals(verifCode)){
            password.setVisible(true);
            rpassword.setVisible(true);
            saveButton.setVisible(true);
        }
    }

    
    @FXML
    private void checkPass(KeyEvent event) {
        if( (password.getText() == null ? rpassword.getText() == null : password.getText().equals(rpassword.getText())) ){
            rpassword.setStyle("-fx-background-color: #81BF27");
        }
        else{
            rpassword.setStyle("-fx-background-color: #de7f21");
        }
    }

    
    @FXML
    private void setNewPasswordAction(ActionEvent event) throws SQLException, FileNotFoundException {
        if(password.getText().isEmpty()){
            al.setContentText("Veuillez saisir le nouveau mot de passe");
            al.showAndWait();
        }else{
            if(rpassword.getText().isEmpty() || !rpassword.getText().equals(password.getText())){
                al.setContentText("Veuillez retaper le nouveau mot de passe");
                al.showAndWait();
            }else{
                uss.changePassword(UserService.hashPassword(rpassword.getText()), user.getId());

                Stage stage = new Stage();
                stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
                Alert succes = new Alert(Alert.AlertType.INFORMATION);
                succes.setContentText("Mot de Pass modifié avec succes");
            }
        }
    }
    

    public String makeVerifCode() {
        String randomString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        verifCode = "";
        for (int i = 0; i < 8; i++) {
            int n = rand.nextInt(randomString.length());
            verifCode += randomString.charAt(n);
        }
        return verifCode;
    }
    
}



/**
*@Lau82 © 2018
*/
