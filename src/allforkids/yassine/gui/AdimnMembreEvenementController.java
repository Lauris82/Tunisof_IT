/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.gui;

import allforkids.yassine.entities.User;
import allforkids.yassine.entities.reservation;
import allforkids.yassine.services.crudReservation;
import allforkids.yassine.services.userReservation;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.HyperlinkLabel;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdimnMembreEvenementController implements Initializable {
  @FXML
    private TableColumn<String, String> nom;
    @FXML
    private TableColumn<String, String> prenom;
    @FXML
    private TableColumn<String, String> dateR;
    @FXML
    private TableView<userReservation> tableReservation;
    @FXML
    private JFXButton retour;
    @FXML
    private HyperlinkLabel nbrMembre;
    @FXML
    private HyperlinkLabel dispo;
    @FXML
    private HyperlinkLabel palaceTotal;
    @FXML
    private TableColumn<userReservation, String> image;
    
    
   public void setNbrMembre(String nbrMembre) {
        this.nbrMembre.setText(nbrMembre);
    }

    public void setDispo(String dispo) {
        this.dispo.setText(dispo);
    }

    public void setPalaceTotal(String palaceTotal) {
        this.palaceTotal.setText(palaceTotal);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadMembres();
    }    
    
     private void loadMembres() {
       
       crudReservation cr=new crudReservation();
      
       List<reservation> lr =cr.afficheMembre(AdminAfficheEvenementController.idEvent);
        
          ObservableList<userReservation>   data= FXCollections.observableArrayList();
         List<String> ll=new ArrayList<>();
          for (reservation r: lr)
          {
              userReservation ur=new userReservation();
              User user=cr.infoUser(r.getReservation_user());
              ur.setNom(user.getNom());
              ur.setDate(r.getDate_reservation().toString());
              ur.setPrenom(user.getPrenom());
              ur.setImage(user.getImage());
            
               image.setCellValueFactory(new PropertyValueFactory<>("image"));
               dateR.setCellValueFactory(new PropertyValueFactory<>("date"));
               nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
             prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
             
               
             image.setCellFactory(tc -> {
       TableCell<userReservation, String> cell = new TableCell<userReservation, String>() {
             @Override
             protected void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  if (item == null || empty) {
                        setText(null);
                  } else {
                        ImageView imageview = new ImageView();
                        
                       File file = new File( "C:\\wamp64\\www\\AllForKids\\web\\image_user\\"+ item);
                        Image image1 = new Image(file.toURI().toString()); 
                        imageview.setImage(image1);
                       imageview.setFitWidth(50);
                        imageview.setFitHeight(50);
                        
                        StackPane stackPane = new StackPane(imageview);
                        setGraphic(stackPane);
                  }
             }
       };
       return cell;
});
      data.add(ur);       
      
               tableReservation.setItems(null);
      tableReservation.setItems(data);
     
       
       
    }
  
    }

    @FXML
    private void retourListEvenement(ActionEvent event) throws IOException {
          FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/GUI/HomeAdmin.fxml"));
            Parent root = loader.load();
            
             tableReservation.getScene().setRoot(root); 
    }
    
}
