/**
* @Project: AllForKids
* @Classe: RegisterController
* @Date: 13 mars 2018
* @Time: 20:29:27
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.User;
import allforkids.services.UserService;
import allforkids.utils.MyConnexion;
import com.jfoenix.controls.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class RegisterController implements Initializable{
    
    public String fileName = "";
    Connection cn = MyConnexion.getInstance().getConnection();
    String Admin = "ADMIN";
    String Parent = "PARENT";
    String Responsable_Garderie = "RESPONSABLE_GARDERIE";
    String Responsable_Club = "RESPONSABLE_CLUB";

    @FXML
    private AnchorPane rootpane;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton registerButton;
    @FXML
    private JFXPasswordField rpassword;
    @FXML
    private JFXComboBox<String> roles;
    @FXML
    private JFXDatePicker birthday;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField addresse;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXButton loginButton;
    @FXML
    private Label labImage;
    @FXML
    private Button imageButton;
    @FXML
    private JFXTextField contact;
    
    
    
    @FXML
    private void goToLoginAction(ActionEvent event){
 
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/Login.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }   
    }
    
    public String uploadImage(File file) throws IOException{
        BufferedOutputStream streamWeb = null;
        BufferedOutputStream streamJava = null;
        
        String pathWeb = "C:\\wamp64\\www\\AllForKids\\web\\image_user";
        String pathJava = "C:\\Users\\Lauris\\Desktop\\3A18\\PIDEV\\Java\\AllForKids\\src\\allforkids\\ressources\\images";
        String localPath = "localhost:8080/";
        
        fileName = file.getName();
        fileName = fileName.replace(" ", "_");
        
        java.nio.file.Path pa = file.toPath();
        byte[] bytes = Files.readAllBytes((java.nio.file.Path) pa);
        
        File dirWeb = new File(pathWeb);
        File dirJava = new File(pathJava);
        if(!dirWeb.exists()){
            dirJava.mkdirs();
        }
        
        File serverFileWeb = new File(dirWeb.getAbsolutePath() + File.separator + fileName);
        File serverFileJava = new File(dirJava.getAbsolutePath() + File.separator + fileName);
        
        streamWeb = new BufferedOutputStream(new FileOutputStream(serverFileWeb));
        streamJava = new BufferedOutputStream(new FileOutputStream(serverFileJava));
        streamWeb.write(bytes);
        streamWeb.close();
        streamJava.write(bytes);
        streamJava.close();
        
        return localPath+"/"+fileName;
    }
    
   
    @FXML
    private void chooseFileAction(ActionEvent actionEvent) throws MalformedURLException, IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
         
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null){
            uploadImage(file);
            labImage.setText(file.getAbsolutePath());
            String imageFile = file.toURI().toURL().toString();
            System.out.println(imageFile);
        }
    }
    
    
    @FXML
    private void resgiterAction(ActionEvent event) throws SQLException, FileNotFoundException, IOException{
        String recupererRole = roles.getValue();
        String role = "";
        if(recupererRole.contains("ADMIN")){
            role = "a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
        }
        if(recupererRole.contains("PARENT")){
            role =  "a:0:{}";
        }
        if(recupererRole.contains("RESPONSABLE_GARDERIE")){
            role = "a:1:{i:0;s:25:\"ROLE_RESPONSABLE_GARDERIE\";}";
        }
        if(recupererRole.contains("RESPONSABLE_CLUB")){
            role = "a:1:{i:0;s:21:\"ROLE_RESPONSABLE_CLUB\";}";
        }
        long num = 0;
        
        UserService uss = new UserService();
        boolean longExiste = isLong(contact);
        if(longExiste == true){
            num = Long.valueOf(contact.getText());
        }
        
        Alert al = new Alert(Alert.AlertType.ERROR);
        
        if(roles.getValue() == null){
            al.setContentText("Veuillez choisir un role");
            al.showAndWait();
        }
        else{
            if(username.getText().isEmpty() || uss.verifierUsername(username.getText()) != 0){
                al.setContentText("Veuillez saisir un username valide");
                al.showAndWait();
            }else{
                if(password.getText().isEmpty()){
                    al.setContentText("Veuillez saisir un mot de passe valide");
                    al.showAndWait();
                }else{
                    if(rpassword.getText().isEmpty()){
                        al.setContentText("Veuillez saisir le meme mot de passe");
                        al.showAndWait();
                    }else{
                        if(email.getText().isEmpty() || uss.verifierEmail(email.getText()) != 0 || !email.getText().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{1,4}$")){
                            al.setContentText("Veuillez saisir une adresse mail valide");
                            al.showAndWait();
                        }else{
                            if(birthday.getValue().isAfter(LocalDate.now())){
                                al.setContentText("Veuillez saisir une date de naissance valide");
                                al.showAndWait();
                            }else{
                                if(nom.getText().isEmpty()){
                                    al.setContentText("Veuillez saisir votre nom");
                                    al.showAndWait();
                                }else{
                                    if(prenom.getText().isEmpty()){
                                        al.setContentText("Veuillez saisir prenom");
                                        al.showAndWait();
                                    }else{
                                        if(addresse.getText().isEmpty()){
                                            al.setContentText("Veuillez saisir une adresse valide");
                                            al.showAndWait();
                                        }else{
                                            if(labImage.getText().isEmpty()){
                                                al.setContentText("Veuillez choisir une image");
                                                al.showAndWait();
                                            }else{
                                                if(contact.getText().isEmpty() || contact.getText().length() != 8 ){
                                                    al.setContentText("Veuillez saisir un contact valide");
                                                    al.showAndWait();
                                                }else{
                                                    User regs = new User(username.getText(), username.getText(), email.getText(), email.getText(), true, UserService.hashPassword(password.getText()), 
                                                            role, nom.getText(), prenom.getText(), birthday.getValue(), addresse.getText(), fileName, num);

                                                    uss.register(regs);

                                                    System.out.println("Compte Creer");

                                                    Stage stage = new Stage();
                                                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/Login.fxml"));
                                                    Scene scene = new Scene(root);
                                                    stage = (Stage) loginButton.getScene().getWindow();
                                                    stage.close();
                                                    stage.setScene(scene);
                                                    stage.show();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        birthday.setValue(LocalDate.now());
        
        ObservableList<String> options = FXCollections.observableArrayList(
                Admin,
                Parent,
                Responsable_Garderie,
                Responsable_Club
        );
        roles.setItems(options);
        roles.getSelectionModel().select(1);
        
    }
    
    
    
    @FXML
    private void colorEmail(KeyEvent event) throws SQLException {
        UserService ss = new UserService();
        System.out.println(ss.verifierEmail(email.getText()));
        if( ss.verifierEmail(email.getText()) == 1 || !email.getText().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-z]{1,4}$") ){
            email.setStyle("-fx-background-color: #de7f21");
        }
        else{
            email.setStyle("-fx-background-color: #81BF27");
        }
    }

    @FXML
    private void colorUsername(KeyEvent event) throws SQLException {
        UserService ss = new UserService();
        if( ss.verifierUsername(username.getText()) == 1 ){
            username.setStyle("-fx-background-color: #de7f21");
        }
        else{
            username.setStyle("-fx-background-color: #81BF27");
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
    private void numberOnly(KeyEvent event) {
        String s = new String();
        if (contact.getText().matches("[0-9]*")){
         s = contact.getText();
        }
        else{
            contact.setText(s);
            contact.positionCaret(s.length());
        }
    }


    private boolean isLong(TextField input){
        try{
            long age = Long.parseLong(input.getText());
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}



/**
*@Lau82 © 2018
*/
