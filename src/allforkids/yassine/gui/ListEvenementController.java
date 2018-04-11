/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.gui;

import allforkids.services.UserService;
import allforkids.yassine.entities.evenement;
import allforkids.yassine.entities.reservation;
import allforkids.yassine.services.crudEvenement;
import allforkids.yassine.services.crudReservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.sql.Date;

import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListEvenementController implements Initializable {
    
    UserService uss = new UserService();
    int idUser = UserService.id;
    
    @FXML
    private TableView<evenement> table;
    @FXML
    private TableColumn<evenement, String> nom;
    @FXML
    private TableColumn<evenement, Date> dateDebut;
  
    @FXML
    private TextField recherche;
 private ObservableList <evenement> data ;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private TableColumn<evenement, String> Emplacement;
 
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         type.getItems().addAll("Nom", "Emplacement", "Date debut");
        LoadEvenement();
        recherche();
      
    }
    public void LoadEvenement ()
    {
       
        crudEvenement cr =new crudEvenement();
        List<evenement> l=cr.afficherEvenement();
       data= FXCollections.observableArrayList();
     for (evenement e : l)
     {    

         if(e.getDate_fin().before(Date.valueOf(LocalDate.now())))
         {
              cr.supprimerEvenement(e.getId_evenement());

         }
     }
       List<evenement> l2=cr.afficherEvenement();
         for (evenement e : l2)
     {
         data.add(e);
     nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      Emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
      dateDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
      
       table.setItems(null);
       table.setItems(data);
     }
    
}

    @FXML
    private void afficheDetailEvenement(ActionEvent event)  {
         try {
              
           crudEvenement cr =new crudEvenement();
             crudReservation cr2=new crudReservation();
        ObservableList<evenement> selectedRows, Ev;
        Ev= table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/afficheDetailEvenement.fxml"));
        Parent root = loader.load();
            AfficheDetailEvenementController nav=loader.getController();
              if(!selectedRows.isEmpty())
            {
        for(evenement e :selectedRows)
        {
         
            int id=e.getId_evenement();
             nav.setIdEvenement(id);
            evenement evv=cr.detailEvenment(id);
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
            nav.image.setPreserveRatio(true);
    nav.setNomImage(evv.getImage().toString());
    
    int nbrplace =e.getNbr_place() - cr2.nbrMembres(e.getId_evenement());
   if(nbrplace !=0)
   {
       
           nav.setNbrPlaces("Oui");  
   }
   else{
       nav.setNbrPlaces("Non , Evenement complet");  
   }
      
          if(e.getEvenement_user()!= idUser)
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
              if(cr2.estReserver(idUser, id) ==false)
          {
              nav.setRes("Participer");
                  if(nbrplace==0)
             {
                 nav.res.setVisible(false);
              
          }}else{
              nav.setRes("Annuler Participation");
          }
        }
        
               table.getScene().setRoot(root);
            }
             } catch (IOException ex) {
            Logger.getLogger(AfficheDetailEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    

   
    }
    public void recherche()
    {
           crudEvenement cr=new crudEvenement();
         
          
        recherche.setOnKeyReleased(e -> {
            if (recherche.getText().equals("")) {
               LoadEvenement();
             
            } else if(type.getValue() !=null){
               table.setItems(null);
                data.clear();
    
       List<evenement> l2=cr.rechercheEvenement(recherche.getText().toString(),type.getValue());
         for (evenement ev : l2)
     {
         data.add(ev);
     nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
      dateDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
     
       table.setItems(data);
         
     }
            }
    });
    }

    @FXML
    private void ajouterEvenement(ActionEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/ajouterEvenement.fxml"));
        Parent root = loader.load();
         table.getScene().setRoot(root); 
    }

    @FXML
    private void VoirCalendrier(ActionEvent event) throws IOException {
          FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/calendrier.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root);
            Stage s=new Stage();
            s.setScene(scene);
            s.show();
            // table.getScene().setRoot(root); 
    }



    @FXML
    private void goToHome(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("allforkids/GUI/Home.fxml")); 
        Scene scene = new Scene(root);
        stage = (Stage) table.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }
 
   

}