/**
* @Project: AllForKids
* @Classe: TypeHomeController
* @Date: 20 mars 2018
* @Time: 18:46:39
*
* @author Lauris
*/


package allforkids.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TypeHomeController implements Initializable{

    @FXML
    private AnchorPane garderieAnchorPane;
    @FXML
    private AnchorPane eventAnchorPane;
    @FXML
    private AnchorPane transportAnchorPane;
    @FXML
    private AnchorPane clubAnchorPane;
    @FXML
    private AnchorPane produitAnchorPane;
    @FXML
    private AnchorPane associationAnchorPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }
    
    @FXML
    private void goToOffreTransport(MouseEvent event) throws IOException{
        appuyer(transportAnchorPane);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/OffreTransport.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) transportAnchorPane.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void goToEvenement(MouseEvent event) throws IOException{
        appuyer(eventAnchorPane);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/allforkids/yassine/gui/ListEvenement.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) transportAnchorPane.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void goToGarderie(MouseEvent event) throws IOException{
        appuyer(garderieAnchorPane);
    }
    
    @FXML
    private void goToClub(MouseEvent event) throws IOException{
        appuyer(clubAnchorPane);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/allforkids/yasmine/gui/home.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) transportAnchorPane.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToAssociation(MouseEvent event) throws IOException {
        appuyer(associationAnchorPane);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/allforkids/yasmine/gui/homeAssociation.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) transportAnchorPane.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void goToProduit(MouseEvent event) throws IOException{
        appuyer(produitAnchorPane);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/forkids/gui/Home.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) transportAnchorPane.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }
    
    
    public void appuyer(Node node){
        Bounds boundsInScreen = node.localToScreen(node.getBoundsInLocal());
        double posY = boundsInScreen.getHeight()+2;
        node.setTranslateY(posY);
    }

}



/**
*@Lau82 © 2018
*/
