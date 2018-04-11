/**
* @Project: AllForKids
* @Classe: LoginController
* @Date: 13 mars 2018
* @Time: 20:09:01
*
* @author Lauris
*/


package allforkids.controllers;

import allforkids.entities.Facebook_User;
import allforkids.entities.User;
import allforkids.services.FacebookService;
import allforkids.services.UserService;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LoginController implements Initializable{
    FacebookService fbs = new FacebookService();
    
    Timeline timeline;
    
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private ImageView imageView;
    @FXML
    private FontAwesomeIconView registerIcon;
    @FXML
    private Label forgetPasswordLabel;
    
    
    @FXML
    private void closeAction(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void goToRegisterAction(MouseEvent event){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/Register.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    @FXML
    private void loginAction(ActionEvent event) throws SQLException, IOException{
        progressBar.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(2));
        
        UserService uService = new UserService();
        int logged = uService.login(username.getText(), password.getText());
        
        pt.setOnFinished(ev -> {
            if(logged == 1){
                try {
                    User user = uService.getUser(UserService.id);
                    System.out.println(user.getRoles());
                    
                    if(user.getRoles().contains("ROLE_ADMIN")){
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/HomeAdmin.fxml"));
                        Scene scene = new Scene(root);
                        stage = (Stage) loginButton.getScene().getWindow();
                        stage.close();
                        stage.setScene(scene);
                        stage.show();
                    }
                    else{
                        FXMLLoader loader  = new FXMLLoader();
                        loader.setLocation(getClass().getClassLoader().getResource("allforkids/GUI/Home.fxml"));
                        try {
                            loader.load();
                        } catch (IOException ex) {
                            System.err.println(ex.getMessage());
                        }
                        HomeController hcontrol = loader.getController();
                        File file = new File("C:\\wamp64\\www\\AllForKids\\web\\image_user\\" + user.getImage().toString());
                        Image image = new Image(file.toURI().toString(), 125, 125, false, false);
                        
                        hcontrol.setUsernameLab(user.getNom());
                        hcontrol.setProfilPicture(image);
                        if(user.getNotification() > 0){
                            hcontrol.setNotificationLabel(String.valueOf(user.getNotification()));
                            hcontrol.getNotificationLabel().setTextFill(Color.RED);
                        }
                        
                        Parent pa = loader.getRoot();
                        Stage stage = new Stage();
                        stage = (Stage) loginButton.getScene().getWindow();
                        stage.setScene(new Scene(pa));
                        
                    }
                } catch (SQLException | IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            else{
                progressBar.setVisible(false);
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Error");
                al.setHeaderText("Check the information you have entered. \n");
                al.show();
            }
        });
        
        pt.play();
    }
  
    
    @FXML
    public void connectionFacebookAction(ActionEvent event) throws InterruptedException, IOException, SQLException{
        
        String accessToken = fbs.getAccessToken();
        Facebook_User fb_user = new Facebook_User();
        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("allforkids/GUI/Home.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        HomeController hcontrol = loader.getController();
        
        String client_id = "568364336875744";
        String domain = "https://github.com/Lauris82";
        String authUrl = "https://www.facebook.com/v2.12/dialog/oauth?client_id="+client_id+
                "&redirect_uri="+domain+"&state=st=state82allfor,kids=123456789"+
                "&scope=email,user_birthday,user_photos,user_hometown,user_location";

        
        System.setProperty("webdirver.gecko.driver", "geckodriver.exe");
        
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(380, 40));
        driver.manage().window().setSize(new Dimension(830, 740));
        
        driver.get(authUrl);
        
        while(true){
            if(!driver.getCurrentUrl().contains("facebook.com")){
                fb_user = fbs.get_Profile_Info(accessToken);
                driver.close();
                
                File file = new File(fb_user.getPicture());
                Image image = new Image(file.toURI().toString(), 125, 125, false, false);
        
                hcontrol.setUsernameLab(fb_user.getName());
                System.out.println(fb_user.getName());
                hcontrol.setProfilPicture(image);
                
                Parent pa = loader.getRoot();
                Stage stage = new Stage();
                stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(pa));
            }
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setVisible(false);
        
        Image img = new Image(getClass().getResourceAsStream("/allforkids/ressources/images/garde1.jpg"), 1043, 735, false, false);
        Image img1 = new Image(getClass().getResourceAsStream("/allforkids/ressources/images/garde2.jpg"), 1043, 735, false, false);
        Image img2 = new Image(getClass().getResourceAsStream("/allforkids/ressources/images/garde3.jpg"), 1043, 735, false, false);
        Image img3 = new Image(getClass().getResourceAsStream("/allforkids/ressources/images/garde4.jpg"), 1043, 735, false, false);
        
        imageView.setPreserveRatio(true);
        
        KeyFrame keyFrame1On = new KeyFrame(Duration.seconds(0), new KeyValue(imageView.imageProperty(), img));
        KeyFrame startFade1Out = new KeyFrame(Duration.seconds(3), new KeyValue(imageView.opacityProperty(), 1.0));
        KeyFrame endFade1Out = new KeyFrame(Duration.seconds(3.5), new KeyValue(imageView.opacityProperty(), 0.0));
        
        KeyFrame keyFrame2On = new KeyFrame(Duration.seconds(3.5), new KeyValue(imageView.imageProperty(), img1));
        KeyFrame endFade2In = new KeyFrame(Duration.seconds(4), new KeyValue(imageView.opacityProperty(), 1.0));
        KeyFrame startFade2Out = new KeyFrame(Duration.seconds(7), new KeyValue(imageView.opacityProperty(), 1.0));
        KeyFrame endFade2Out = new KeyFrame(Duration.seconds(7.5), new KeyValue(imageView.opacityProperty(), 0.0));
        
        KeyFrame keyFrame3On = new KeyFrame(Duration.seconds(7.5), new KeyValue(imageView.imageProperty(), img2));
        KeyFrame endFade3In = new KeyFrame(Duration.seconds(8), new KeyValue(imageView.opacityProperty(), 1.0));
        KeyFrame startFade3Out = new KeyFrame(Duration.seconds(11), new KeyValue(imageView.opacityProperty(), 1.0));
        KeyFrame endFade3Out = new KeyFrame(Duration.seconds(11.5), new KeyValue(imageView.opacityProperty(), 0.0));
        
        KeyFrame keyFrame4On = new KeyFrame(Duration.seconds(11.5), new KeyValue(imageView.imageProperty(), img3));
        KeyFrame endFade4In = new KeyFrame(Duration.seconds(12), new KeyValue(imageView.opacityProperty(), 1.0));
        KeyFrame startFade4Out = new KeyFrame(Duration.seconds(15), new KeyValue(imageView.opacityProperty(), 1.0));
        KeyFrame endFade4Out = new KeyFrame(Duration.seconds(15.5), new KeyValue(imageView.opacityProperty(), 0.0));
        
        timeline = new Timeline(keyFrame1On, startFade1Out, endFade1Out, keyFrame2On, endFade2In, startFade2Out, endFade2Out,
                keyFrame3On, endFade3In, startFade3Out, endFade3Out, keyFrame4On, endFade4In, startFade4Out, endFade4Out);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void goToForgetPassword(MouseEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/ForgetPassword.fxml"));
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/allforkids/ressources/images/logo1.png")));
            stage.setTitle("AllForKids");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(forgetPasswordLabel.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}



/**
*@Lau82 © 2018
*/
