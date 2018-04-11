/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.gui;

import allforkids.yassine.entities.User;
import allforkids.yassine.entities.evenement;
import static allforkids.yassine.gui.AfficheDetailEvenementController.idEvenement;
import allforkids.yassine.services.crudEvenement;
import allforkids.yassine.services.crudReservation;
import allforkids.yassine.services.userReservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminAfficheEvenementController implements Initializable {
    @FXML
    private TableView<userReservation> table;
    @FXML
    private TableColumn<userReservation, String> image;
    @FXML
    private TableColumn<userReservation, String> nom;
 
    @FXML
    private TableColumn<userReservation,String> dateDebut;
    @FXML
    private TableColumn<userReservation, String> dateFin;
    @FXML
    private TableColumn<userReservation,String> etat;
    @FXML
    private JFXTextField recherche;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton membres;
private ObservableList <userReservation> data ;
    @FXML
    private TableColumn<userReservation, String> emplacement;
    @FXML
    private TableColumn<userReservation, String> umgUsr;
    @FXML
    private TableColumn<userReservation, String> nomUsr;
    public static int idEvent ;

    public static void setIdEvent(int idEvent) {
        AdminAfficheEvenementController.idEvent = idEvent;
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            type.getItems().addAll("Nom", "Emplacement", "Date debut");
        LoadEvenement();
        recherche();
      
    }    
     public void LoadEvenement ()
    {
       
        crudEvenement cr =new crudEvenement();
        crudReservation cr2=new crudReservation();
        List<evenement> l=cr.afficherTouteEvenement();
       data= FXCollections.observableArrayList();
     for (evenement e : l)
     {    

         if(e.getDate_fin().before(Date.valueOf(LocalDate.now())))
         {
             if(e.getEtat()==0)
              cr.supprimerEvenement(e.getId_evenement());

         }
     }
       List<evenement> l2=cr.afficherTouteEvenement();
         for (evenement e : l2)
     {
         userReservation ur=new userReservation();
         int idUser=e.getEvenement_user();
         User user=new User();
         user=cr2.infoUser(idUser);
         ur.setNomusr(user.getNom());
         ur.setPrenom(user.getPrenom());
         ur.setImage2(user.getImage());
         ur.setNom(e.getNom());
         ur.setDate(e.getDate_debut().toString());
         ur.setDateF(e.getDate_fin().toString());
         ur.setImage(e.getImage());
         ur.setEmplacement(e.getEmplacement());
         ur.setIdEvent(e.getId_evenement());
         if(e.getEtat()==0)
         {
             ur.setEtat("en cours");
         }else{
              ur.setEtat("supprimer"); 
         }
       nomUsr.setCellValueFactory(new PropertyValueFactory<>("nomusr"));  
        umgUsr.setCellValueFactory(new PropertyValueFactory<>("image2"));
         umgUsr.setCellFactory(tc -> {
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
     nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     
      emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
      dateDebut.setCellValueFactory(new PropertyValueFactory<>("date"));
      dateFin.setCellValueFactory(new PropertyValueFactory<>("dateF"));
      image.setCellValueFactory(new PropertyValueFactory<>("image"));
      etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                image.setCellFactory(tc -> {
       TableCell<userReservation, String> cell = new TableCell<userReservation, String>() {
             @Override
             protected void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  if (item == null || empty) {
                        setText(null);
                  } else {
                        ImageView imageview = new ImageView();
                 
                       File file = new File( "C:\\wamp64\\www\\AllForKids\\web\\image_evenement\\"+ item);
                        Image image1 = new Image(file.toURI().toString()); 
                        imageview.setImage(image1);
                       imageview.setFitWidth(100);
                        imageview.setFitHeight(60);
                        
                        StackPane stackPane = new StackPane(imageview);
                        setGraphic(stackPane);
                  }
             }
       };
       return cell;
});
                data.add(ur);
       table.setItems(null);
       table.setItems(data);
     }
    
}
      public void recherche()
    {
           crudEvenement cr=new crudEvenement();
         crudReservation cr2=new crudReservation();
          
        recherche.setOnKeyReleased(e -> {
            if (recherche.getText().equals("")) {
               LoadEvenement();
             
            } else if(type.getValue() !=null){
               table.setItems(null);
                data.clear();
           
 
 
       List<evenement> l2=cr.rechercheTouteEvenement(recherche.getText().toString(),type.getValue());
         for (evenement ev : l2)
     {
         
         userReservation ur=new userReservation();
         int idUser=ev.getEvenement_user();
         User user=new User();
         user=cr2.infoUser(idUser);
         ur.setNomusr(user.getNom());
         ur.setPrenom(user.getPrenom());
         ur.setImage2(user.getImage());
         ur.setNom(ev.getNom());
         ur.setDate(ev.getDate_debut().toString());
         ur.setDateF(ev.getDate_fin().toString());
         ur.setImage(ev.getImage());
         ur.setEmplacement(ev.getEmplacement());
         ur.setIdEvent(ev.getId_evenement());
         if(ev.getEtat()==0)
         {
             ur.setEtat("en cours");
         }else{
              ur.setEtat("supprimer"); 
         }
       nomUsr.setCellValueFactory(new PropertyValueFactory<>("nomusr"));  
        umgUsr.setCellValueFactory(new PropertyValueFactory<>("image2"));
         umgUsr.setCellFactory(tc -> {
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
     nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     
      emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
      dateDebut.setCellValueFactory(new PropertyValueFactory<>("date"));
      dateFin.setCellValueFactory(new PropertyValueFactory<>("dateF"));
      image.setCellValueFactory(new PropertyValueFactory<>("image"));
      etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                image.setCellFactory(tc -> {
       TableCell<userReservation, String> cell = new TableCell<userReservation, String>() {
             @Override
             protected void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  if (item == null || empty) {
                        setText(null);
                  } else {
                        ImageView imageview = new ImageView();
                 
                       File file = new File( "C:\\wamp64\\www\\AllForKids\\web\\image_evenement\\"+ item);
                        Image image1 = new Image(file.toURI().toString()); 
                        imageview.setImage(image1);
                       imageview.setFitWidth(100);
                        imageview.setFitHeight(60);
                        
                        StackPane stackPane = new StackPane(imageview);
                        setGraphic(stackPane);
                  }
             }
       };
       return cell;
});
                data.add(ur);
       table.setItems(null);
       table.setItems(data);
         
     }
            }
    });
    }


    @FXML
    private void VoirCalendrier(ActionEvent event) throws IOException {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/calendrier.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root);
            Stage s=new Stage();
            s.setScene(scene);
            s.show();
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) throws IOException {
        ObservableList<userReservation> selectedRows, Ev;
         Ev= table.getItems();
         selectedRows = table.getSelectionModel().getSelectedItems();
             crudEvenement croud=new crudEvenement();
                 if(!selectedRows.isEmpty())
            {
        for(userReservation u :selectedRows)
        {
         
            int id=u.getIdEvent();
          evenement evv=croud.detailEvenment(id);
          if(evv.getDate_debut().before(Date.valueOf(LocalDate.now().plusDays(2)))){
             JOptionPane.showMessageDialog(null,"Tu ne peut pas supprimer une evenement en cours ou avant 24h de son date dèbut",""
                + "Erreur",2);  
          } else {if(evv.getEtat() ==1)
      {
           JOptionPane.showMessageDialog(null,"Tu ne peut pas supprimer une evenement dèja supprimer",""
                + "Erreur",2);  }
       else{ croud.supprimerEvenement(id);
      
        Notifications.create().darkStyle().title("Success").text("Evenement supprimer ").position(Pos.BASELINE_RIGHT).showConfirm();
          LoadEvenement();
             
          }
        
            
                 }}}
    }

    @FXML
    private void modifierEvenement(ActionEvent event) {
              try{
                    ObservableList<userReservation> selectedRows, Ev;
         Ev= table.getItems();
                      selectedRows = table.getSelectionModel().getSelectedItems();
              crudEvenement croud=new crudEvenement();
          
               if(!selectedRows.isEmpty())
            {
                   for(userReservation u :selectedRows)
        {
            
        evenement evv=croud.detailEvenment(u.getIdEvent());
      if(evv.getDate_debut().before(Date.valueOf(LocalDate.now().plusDays(2))))
      {
             JOptionPane.showMessageDialog(null,"Tu ne peut pas modifier une evenement en cours ou avant 24h de son date dèbut",""
                + "Erreur",2);  
      }else{ if(evv.getEtat() ==1)
      {
              JOptionPane.showMessageDialog(null,"Tu ne peut pas modifier une evenement dèja supprimer",""
                + "Erreur",2);  
      }else{
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/ModifierEvenement.fxml"));
        Parent root = loader.load();
            ModifierEvenementController modif=loader.getController();
            modif.setNom(evv.getNom());
            modif.setDescription(evv.getDescription());
            modif.setEmplacement(evv.getEmplacement());
           
            modif.setFileName(evv.getImage());
         modif.setNbrPlaces(evv.getNbr_place());
             String dd=evv.getDate_debut().toString();
       String df=evv.getDate_fin().toString();
             	LocalDate ldd = LocalDate.parse(dd );
          	LocalDate ldf = LocalDate.parse(df );
     
           modif.getDateDebut().setValue(ldd);
  modif.getDateFin().setValue(ldf);
            modif.setIdev(u.getIdEvent());
             table.getScene().setRoot(root);
             File file = new File("C:\\wamp64\\www\\AllForKids\\web\\image_evenement\\"+evv.getImage().toString());

            Image image1 = new Image(file.toURI().toString());
             modif.image.setImage(image1);
      }
            }
            }}
     } catch (IOException ex) {
            Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherMembresEvenement(ActionEvent event) {
          try{
    
         ObservableList<userReservation> selectedRows, Ev;
         Ev= table.getItems();
                      selectedRows = table.getSelectionModel().getSelectedItems();
              crudEvenement croud=new crudEvenement();
          
               if(!selectedRows.isEmpty())
            {
                   for(userReservation u :selectedRows)
        {
            setIdEvent(u.getIdEvent());
               evenement evv=croud.detailEvenment(u.getIdEvent());
        if(evv.getEtat() ==1)
      {
              JOptionPane.showMessageDialog(null,"Tu ne peut pas voir les membres d'une evenement dèja supprimer",""
                + "Erreur",2);  
      }else{
          
             FXMLLoader loader1 =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/AdimnMembreEvenement.fxml"));
        Parent root1 = loader1.load();
        AdimnMembreEvenementController membre=loader1.getController();
               crudReservation croudd=new crudReservation();
               membre.setPalaceTotal(String.valueOf(evv.getNbr_place())+" "+"Places");
               membre.setNbrMembre(String.valueOf(croudd.nbrMembres(evv.getId_evenement()))+" "+"Membres");
             
               membre.setDispo(String.valueOf(evv.getNbr_place()-croudd.nbrMembres(evv.getId_evenement()))+" "+"Places");
          
//               table.getScene().setRoot(root1); 
                Stage stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setScene(scene);
                stage.show();
        }
    }

}
                 } catch (IOException ex) {
            Logger.getLogger(AdimnMembreEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
