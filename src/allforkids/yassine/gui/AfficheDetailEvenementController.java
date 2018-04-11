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
import allforkids.yassine.services.partage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficheDetailEvenementController implements Initializable {
    
    
    UserService uss = new UserService();
    int idUser = UserService.id;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label dateDebut;
    @FXML
    private Label dateFin;
    @FXML
    private Label emplacement;
    @FXML
    private Button retour;
    public Button res;
public static int idEvenement ;
    @FXML
    public Button supprimer;
    @FXML
    public Button modifier;
    public Button membres;
    @FXML
    private Button fb;
    @FXML
    public  ImageView image;
    public String nomImage ;
    @FXML
    private Label nbrPlaces;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    

    public void setNbrPlaces(String nbrPlaces) {
        this.nbrPlaces.setText(nbrPlaces);
    }



    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }


  
    
public void setRes(String res)
{
    this.res.setText(res);
}
    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut.setText(dateDebut);
    }

    public void setDateFin(String dateFin) {
        this.dateFin.setText(dateFin);
    }

    public void setEmplacement(String emplacement) {
        this.emplacement.setText(emplacement);
    }

    @FXML
    private void retourListEvenement(ActionEvent event) throws IOException {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/ListEvenement.fxml"));
            Parent root = loader.load();
             nom.getScene().setRoot(root); 
             
    }

    @FXML
    private void reservationEvenement(ActionEvent event) {
  crudReservation cr=new crudReservation();

          if(res.getText() =="Annuler Participation")
          {
                reservation r =new reservation();
             res.setText("Participer");

           cr.supprimerReservation(idUser, idEvenement);
        
                                Notifications.create().darkStyle().title("Success").text("participation annuler ").position(Pos.BASELINE_RIGHT).showConfirm();

          }else{
                reservation r =new reservation();
                r.setEvenement_reservation(idEvenement);
             r.setReservation_user(idUser);
             cr.ajouterReservation(r);
                                  Notifications.create().darkStyle().title("Success").text("participation ajouter ").position(Pos.BASELINE_RIGHT).showConfirm();
              res.setText("Annuler Participation");
          }
          
    }

    @FXML
    private void afficherMembresEvenement(ActionEvent event)  {
        try{
            crudEvenement crud=new crudEvenement();
            evenement e =crud.detailEvenment(idEvenement);
        FXMLLoader loader1 =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/MembresEvenement.fxml"));
        Parent root1 = loader1.load();
            MembresEvenementController membre=loader1.getController();
               crudReservation croud=new crudReservation();
               
               membre.setNbrMembre(String.valueOf(croud.nbrMembres(idEvenement))+" "+"Membres");
              membre.setDispo(String.valueOf(e.getNbr_place()-croud.nbrMembres(idEvenement))+" "+"Places");
           description.getScene().setRoot(root1); 
          
       } catch (IOException ex) {
            Logger.getLogger(MembresEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }  
         
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) throws IOException {
        crudEvenement croud=new crudEvenement();
          evenement evv=croud.detailEvenment(idEvenement);
          if(evv.getDate_debut().before(Date.valueOf(LocalDate.now().plusDays(2))))
             JOptionPane.showMessageDialog(null,"Tu ne peut pas supprimer une evenement en cours ou avant 24h de son date dèbut",""
                + "Erreur",2);  
          else{
        croud.supprimerEvenement(idEvenement);
      
        Notifications.create().darkStyle().title("Success").text("Evenement supprimer ").position(Pos.BASELINE_RIGHT).showConfirm();
           FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/ListEvenement.fxml"));
            Parent root = loader.load();
            
             nom.getScene().setRoot(root); 
             
          }
    }

    @FXML
    private void modifierEvenement(ActionEvent event) {
        try{
              crudEvenement croud=new crudEvenement();
          evenement evv=croud.detailEvenment(idEvenement);
      if(evv.getDate_debut().before(Date.valueOf(LocalDate.now().plusDays(2))))
             JOptionPane.showMessageDialog(null,"Tu ne peut pas modifier une evenement en cours ou avant 24h de son date dèbut",""
                + "Erreur",2);  
          else{
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/ModifierEvenement.fxml"));
        Parent root = loader.load();
            ModifierEvenementController modif=loader.getController();
            modif.setNom(nom.getText());
            modif.setDescription(description.getText());
            modif.setEmplacement(emplacement.getText());
            modif.setIdev(idEvenement);
            modif.setFileName(evv.getImage());
         modif.setNbrPlaces(evv.getNbr_place());
             String dd=dateDebut.getText();
       String df=dateFin.getText();
             	LocalDate ldd = LocalDate.parse(dd );
          	LocalDate ldf = LocalDate.parse(df );
     
           modif.getDateDebut().setValue(ldd);
  modif.getDateFin().setValue(ldf);
            
             nom.getScene().setRoot(root);
             modif.image.setImage(image.getImage());
      }
     } catch (IOException ex) {
            Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
}

    @FXML
    private void partage(ActionEvent event) {
        partage p=new partage();
        p.partager(nom.getText().toString(),description.getText().toString(),dateDebut.getText().toString(),dateFin.getText().toString(),nomImage);
      
    }
}