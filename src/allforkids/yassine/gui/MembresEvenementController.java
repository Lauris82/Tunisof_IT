/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.gui;

import allforkids.services.UserService;
import allforkids.yassine.entities.User;
import allforkids.yassine.entities.evenement;
import allforkids.yassine.entities.reservation;
import allforkids.yassine.services.userReservation;
import allforkids.yassine.services.crudEvenement;
import allforkids.yassine.services.crudReservation;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import org.controlsfx.control.HyperlinkLabel;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MembresEvenementController implements Initializable {


    
    UserService uss = new UserService();
    int idUser = UserService.id;
  
    @FXML
    private TableColumn<String, String> nom;
    @FXML
    private TableColumn<String, String> prenom;
    @FXML
    private TableColumn<String, String> dateR;
    @FXML
    private TableView<userReservation> tableReservation;
 
    @FXML
    private TableColumn<String, String> image;
    @FXML
    private JFXButton retour;
    @FXML
    private HyperlinkLabel nbrMembre;
    @FXML
    private HyperlinkLabel dispo;


    public void setNbrMembre(String nbrMembre) {
        this.nbrMembre.setText(nbrMembre);
    }

    public void setDispo(String dispo) {
        this.dispo.setText(dispo);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      loadMembres();
    }  


    private void loadMembres() {
       
       crudReservation cr=new crudReservation();
      
       List<reservation> lr =cr.afficheMembre(AfficheDetailEvenementController.idEvenement);
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
               System.out.println(user.getNom());
                System.out.println(user.getImage());
               image.setCellValueFactory(new PropertyValueFactory<>("image"));
               dateR.setCellValueFactory(new PropertyValueFactory<>("date"));
               nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
             prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
             
               
             image.setCellFactory(tc -> {
       TableCell<String, String> cell = new TableCell<String, String>() {
             @Override
             protected void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  if (item == null || empty) {
                        setText(null);
                  } else {
                        ImageView imageview = new ImageView();
                        
                       File file = new File( "C:\\wamp64\\www\\AllForKids\\web\\image_user\\"+ item);
                       System.out.println(item);
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
    private void retourDetailEvenement(ActionEvent event) throws IOException {
        crudEvenement cr =new crudEvenement();
             crudReservation cr2=new crudReservation();
              FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/afficheDetailEvenement.fxml"));
        Parent root = loader.load();
            AfficheDetailEvenementController nav=loader.getController();
            List<reservation>  reservation=new ArrayList<>();
          evenement evv=cr.detailEvenment(AfficheDetailEvenementController.idEvenement);
          nav.setNom(evv.getNom());
            nav.setDateDebut(evv.getDate_debut().toString());
            nav.setDateFin(evv.getDate_fin().toString());
            nav.setDescription(evv.getDescription());
            nav.setEmplacement(evv.getEmplacement());
            File file = new File("C:\\wamp64\\www\\AllForKids\\web\\image_evenement\\"+evv.getImage().toString());

            Image image1 = new Image(file.toURI().toString());
            nav.image.setImage(image1);
            nav.image.setFitHeight(120);
            nav.image.setFitWidth(250);
                int nbrplace =evv.getNbr_place() - cr2.nbrMembres(evv.getId_evenement());
   if(nbrplace !=0)
   {
       
           nav.setNbrPlaces("Oui");  
   }
   else{
       nav.setNbrPlaces("Non , Evenement complet");  
   }
          reservation =cr2.afficheMembre(evv.getId_evenement());
       
          if(evv.getEvenement_user()==idUser)
          {
         nav.membres.setVisible(false);
         nav.res.setVisible(true);
               nav.modifier.setVisible(false);
            nav.supprimer.setVisible(false);
        }else{
              nav.res.setVisible(false);
               nav.membres.setVisible(true);
                   nav.modifier.setVisible(true);
            nav.supprimer.setVisible(true);
                }
                if(cr2.estReserver(idUser, AfficheDetailEvenementController.idEvenement) ==false)
          {
              nav.setRes("Participer");
                  if(nbrplace==0)
             {
                 nav.res.setVisible(false);
              
          }}else{
              nav.setRes("Annuler Participation");
          }
             tableReservation.getScene().setRoot(root); 
    }

  

}
