/**
* @Project: AllForKids
* @Classe: EditProfileController
* @Date: 4 avr. 2018
* @Time: 23:03:04
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.User;
import allforkids.services.UserService;
import allforkids.utils.MyConnexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class EditProfileController implements Initializable{
    
    public String fileName = "";
    Connection cn = MyConnexion.getInstance().getConnection();
    UserService uss = new UserService();
    int idUser = uss.getId();
    User user = new User();

    @FXML
    private JFXButton saveButton;
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
    private JFXButton annulerButton;
    @FXML
    private Button imageButton;
    @FXML
    private Label labImage;
    @FXML
    private JFXTextField contact;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            user = uss.getUser(idUser);
        } catch (SQLException | IOException ex) {
            System.err.println(ex.getMessage());
        }
        username.setText(user.getUsername());
        contact.setText(String.valueOf(user.getContact()));
        birthday.setValue(user.getDateNaissance());
        nom.setText(user.getNom());
        prenom.setText(user.getPrenom());
        addresse.setText(user.getAdresse());
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
    private void updateUser(ActionEvent event) throws SQLException, FileNotFoundException, IOException{
        long num = 0;
        
        boolean longExiste = isLong(contact);
        if(longExiste == true){
            num = Long.valueOf(contact.getText());
        }
        
        Alert al = new Alert(Alert.AlertType.ERROR);

        if(username.getText().isEmpty() || uss.verifierUsername(username.getText()) != 0){
            al.setContentText("Veuillez saisir un username valide");
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
                                    User regs = new User(username.getText(), username.getText(), nom.getText(), prenom.getText(), birthday.getValue(), addresse.getText(), num, fileName);

                                    uss.updateUser(regs, user.getId());

                                    System.out.println("Informations mis à jour");

                                    Stage stage = new Stage();
                                    stage = (Stage) saveButton.getScene().getWindow();
                                    stage.close();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    @FXML
    public void annulerAction(ActionEvent event){
        Stage stage = new Stage();
        stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }
    

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
