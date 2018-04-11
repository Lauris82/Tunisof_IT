/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.gui;

import allforkids.entities.User;
import allforkids.services.UserService;
import allforkids.yassine.entities.evenement;
import allforkids.yassine.entities.reservation;
import allforkids.yassine.services.crudEvenement;
import allforkids.yassine.services.crudReservation;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.print.DocFlavor;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierEvenementController implements Initializable {
    
    UserService uss = new UserService();
    int idUser = UserService.id;
    User user = new User();
    
    @FXML
    private TextField nom;
    @FXML
    private TextArea description;
    @FXML
    private TextField emplacement;
    @FXML
    private DatePicker dateFin;
    @FXML
    private DatePicker dateDebut;
public int idev ;
    @FXML
    public ImageView image;
public String fileName ;
    @FXML
    private Button retour;
    @FXML
    private JFXTextField nbrPlaces;

    public void setNbrPlaces(int nbrPlaces) {
        
        this.nbrPlaces.setText(String.valueOf(nbrPlaces));
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setIdev(int idev) {
        this.idev = idev;
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setEmplacement(String emplacement) {
        this.emplacement.setText(emplacement);
    }

    public void setDateFin(DatePicker dateFin) {
       
        
        this.dateFin.setValue(LocalDate.now());
    }

    public void setDateDebut(DatePicker dateDebut) {
        this.dateDebut =dateDebut;
    }

    public DatePicker getDateDebut() {
        return dateDebut;
    }

    public DatePicker getDateFin() {
        return dateFin;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        try {
            user = uss.getUser(idUser);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ModifierEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
public  String upload(File file) throws  IOException {
     BufferedOutputStream stream = null;
        BufferedOutputStream stream2 = null;
        String globalPath="C:\\wamp64\\www\\AllForKids\\web\\image_evenement";
        String globalPath2="C:\\Users\\Lauris\\Desktop\\3A18\\PIDEV\\Java\\AllForKids\\src\\imageEvenement";
        String localPath="localhost:8080/";
       fileName = file.getName();
        fileName=fileName.replace(" ", "_");
        try {
            Path p = file.toPath();
            
            byte[] bytes = Files.readAllBytes(p);
    
            File dir = new File(globalPath);
            File dir2 = new File(globalPath2);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()+File.separator + fileName);
            File serverFile2 = new File(dir2.getAbsolutePath()+File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream2 = new BufferedOutputStream(new FileOutputStream(serverFile2));
            stream.write(bytes);
            stream.close();
            stream2.write(bytes);
            stream2.close();
            return localPath+"/"+fileName;
        } catch (IOException ex) {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        
        }
      }
    @FXML
    private void choosefile(ActionEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("file", "*.jpg", "*.png"));
		File selectedfile = fileChooser.showOpenDialog(null);
		if (selectedfile != null) {
			
			upload(selectedfile);
                        System.out.println("    "+upload(selectedfile));
			Image image = new Image(new FileInputStream(selectedfile));
			this.image.setImage(image);
		}
    }
    @FXML
    private void modifierEvenement(ActionEvent event) throws ParseException, IOException, SQLException {
        
         if(nom.getText().equals(""))
         
         {
           JOptionPane.showMessageDialog(null,"il faut remplir le champ nom",""
                + "Erreur",2);  
         }else {if(description.getText().equals(""))
         
         {
           JOptionPane.showMessageDialog(null,"il faut remplir le champ description",""
                + "Erreur",2);  
         }else{ if(emplacement.getText().equals(""))
         
         {
           JOptionPane.showMessageDialog(null,"il faut remplir le champ emplacement",""
                + "Erreur",2);  
         }else {if(dateDebut.getValue()==null)
         
         {
           JOptionPane.showMessageDialog(null,"il faut remplir le champ date debut",""
                + "Erreur",2);  
         }else {if(dateFin.getValue()==null)
         
         {
           JOptionPane.showMessageDialog(null,"il faut remplir le champ date fin",""
                + "Erreur",2);  
         }
      
         else{ 
              String DATE_PATTERN = "yyyy/MM/dd";
          
        DateTimeFormatter DATE_FORMATTER
                = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String date1 = DATE_FORMATTER.format(dateDebut.getValue());
         String date2 = DATE_FORMATTER.format(dateFin.getValue());

       
        
       
        java.util.Date utilDate=new java.util.Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");
        utilDate=formatter.parse(date1);
        java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
        
         
        java.util.Date utilDate2=new java.util.Date();
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy/MM/dd");
        utilDate=formatter.parse(date2);
        java.sql.Date sqlDate2=new java.sql.Date(utilDate.getTime());
         if(sqlDate.after(sqlDate2))
         
         {
           JOptionPane.showMessageDialog(null,"il faut choisissez la date debut avant la date fin",""
                + "Erreur",2);  
         }
             else{ if(sqlDate2.before(Date.valueOf(LocalDate.now())))
         
         {
           JOptionPane.showMessageDialog(null,"il faut choisissez la date dfin après la date actuel",""
                + "Erreur",2);  
         }    else{ if(sqlDate.before(Date.valueOf(LocalDate.now())))
         
         {
           JOptionPane.showMessageDialog(null,"il faut choisissez la date debut après la date actuel",""
                + "Erreur",2);  
         }  else {if(fileName==null)
         
         {
           JOptionPane.showMessageDialog(null,"il faut choisissez une image",""
                + "Erreur",2);  
         }
          else{
     
        
        
        
        crudEvenement crud=new crudEvenement();
        evenement ev=new evenement();
        ev.setNom(nom.getText().toString());
        ev.setDescription(description.getText().toString());
        ev.setDate_debut(sqlDate);
        ev.setDate_fin(sqlDate2);
        ev.setEmplacement(emplacement.getText().toString());
        ev.setImage(fileName);
       ev.setNbr_place(Integer.valueOf(nbrPlaces.getText()));
        crud.modifierEvenement(ev, idev);
                Notifications.create().darkStyle().title("Success").text("Evenement modifier ").position(Pos.BASELINE_RIGHT).showConfirm();
                user = uss.getUser(idUser);
                if(user.getRoles().contains("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
                    FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/GUI/HomeAdmin.fxml"));
                    Parent root = loader.load();

                     nom.getScene().setRoot(root);
                }
              else{
                       FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/ListEvenement.fxml"));
                       Parent root = loader.load();
                         nom.getScene().setRoot(root); 
                      }
         }}}}}}}}}
    }

    @FXML
    private void retourDetailEvenement(ActionEvent event) throws IOException {
        if(user.getRoles().contains("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))
        {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/GUI/HomeAdmin.fxml"));
            Parent root = loader.load();
            
             nom.getScene().setRoot(root);
        }else{
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
      int nbrplace =evv.getNbr_place() - cr2.nbrMembres(evv.getId_evenement());
   if(nbrplace !=0)
   {
       
           nav.setNbrPlaces("Oui");  
   }
   else{
       nav.setNbrPlaces("Non , Evenement complet");  
   }
          reservation =cr2.afficheMembre(evv.getId_evenement());
     
          if(evv.getEvenement_user()!= idUser)
          {
                nav.membres.setVisible(false);
         nav.res.setVisible(true);
               nav.modifier.setVisible(false);
            nav.supprimer.setVisible(false);
              if(nbrplace ==0)
              {
                 nav.res.setVisible(false); 
              }
       
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
             nom.getScene().setRoot(root); 
        }
    }


  


 

    
}
