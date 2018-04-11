/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.gui;

import allforkids.services.UserService;
import allforkids.yassine.entities.evenement;
import allforkids.yassine.services.crudEvenement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterEvenementController implements Initializable {
    UserService uss = new UserService();
    int idUser = UserService.id;
    
    @FXML
    private TextField nom;
    @FXML
    private JFXTextArea description;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField emplacement;
 //   private TextField image;
    @FXML
    private Button file;
    @FXML
    private ImageView im;
public String fileName ;
    @FXML
    private JFXButton retour;
    
    @FXML
    private JFXTextField nbrPlaces;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void retourListEvenement(ActionEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/ListEvenement.fxml"));
            Parent root = loader.load();
            
             nom.getScene().setRoot(root); 
    }
 public  String upload(File file) throws  IOException {
        BufferedOutputStream stream = null;
        BufferedOutputStream stream2 = null;
        String globalPath="C:\\wamp64\\www\\AllForKids\\web\\image_evenement";
        String globalPath2="C:\\Users\\ASUS\\Desktop\\AllForKids\\src\\imageEvenement";
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
        
        }}
    @FXML
    private void choosefile(ActionEvent event) throws MalformedURLException, IOException {
         
        FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("file", "*.jpg", "*.png"));
		File selectedfile = fileChooser.showOpenDialog(null);
		if (selectedfile != null) {
			
			upload(selectedfile);
                        System.out.println("    "+upload(selectedfile));
			Image image = new Image(new FileInputStream(selectedfile));
			im.setImage(image);
                    
		}
    
}
    @FXML
    private void ajouterEvenement(ActionEvent event) throws ParseException, IOException {
          
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
         }else {if(nbrPlaces.getText().equals(""))
         
         {
           JOptionPane.showMessageDialog(null,"il faut remplir le champ nombre de places",""
                + "Erreur",2);  
         }
//           else {if(Integer.parseInt(nbrPlaces.getText().toString()) )
//         {
//             JOptionPane.showMessageDialog(null,"le nombre de places doit ètre un entier",""
//                + "Erreur",2);  
//         
//          
//         }
         else {if(date_debut.getValue()==null)
         
         {
           JOptionPane.showMessageDialog(null,"il faut remplir le champ date debut",""
                + "Erreur",2);  
         }else {if(date_fin.getValue()==null)
         
         {
           JOptionPane.showMessageDialog(null,"il faut remplir le champ date fin",""
                + "Erreur",2);  
         }
      
         else{ 
              String DATE_PATTERN = "yyyy/MM/dd";
          
        DateTimeFormatter DATE_FORMATTER
                = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String date1 = DATE_FORMATTER.format(date_debut.getValue());
         String date2 = DATE_FORMATTER.format(date_fin.getValue());

       
        
       
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
     
        evenement ev = new evenement(idUser,nom.getText(),description.getText(),sqlDate,sqlDate2,emplacement.getText(),fileName,Integer.valueOf(nbrPlaces.getText()));
        crudEvenement cr =new crudEvenement();
        cr.ajouterEvenement(ev);

        Notifications.create().darkStyle().title("Success").text("Evenement Crèèer avec succèes").position(Pos.BASELINE_RIGHT).showConfirm();
       
         FXMLLoader loader =  new FXMLLoader(getClass().getResource("/allforkids/yassine/gui/ListEvenement.fxml"));
            Parent root = loader.load();
              nom.getScene().setRoot(root);
             }}}}}}}}}}
    }

    @FXML
    private void verifInteger(KeyEvent event) {
        TextField t=(TextField) event.getSource();
        if(!event.getCharacter().matches("[0-9]"))
        {
            event.consume();
        }
    }
    
}