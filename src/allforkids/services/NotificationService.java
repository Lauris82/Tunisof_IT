/**
* @Project: AllForKids
* @Classe: NotificationService
* @Date: 2 avr. 2018
* @Time: 19:32:54
*
* @author Lauris
*/


package allforkids.services;

import allforkids.entities.Notification;
import allforkids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class NotificationService {
    
    Connection cn = MyConnexion.getInstance().getConnection();
    
    public void createNotification(int emetteur, int destinataire, String sujet){
        
        try {
            LocalDate localDate = LocalDate.now();//For reference
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String today = localDate.format(formatter);
            
            String requete = "INSERT INTO `notification` (emetteur,destinataire,dateNotification,sujet) VALUES (?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            
            st.setString(1, String.valueOf(emetteur));
            st.setString(2, String.valueOf(destinataire));
            st.setString(3, String.valueOf(today));
            st.setString(4, sujet);
            System.out.println("Notification ajoutée");
            
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    public ObservableList<Notification> listerNotification(int idUser){
        ObservableList<Notification> myList = FXCollections.observableArrayList();
        
        try {
            
            String requete = "SELECT * FROM `notification` where `destinataire`=? ORDER BY id DESC";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, String.valueOf(idUser));
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Notification notf = new Notification();
                
                notf.setId(rs.getInt("id"));
                notf.setEmetteur(rs.getInt("emetteur"));
                notf.setDestinataire(rs.getInt("destinataire"));
                notf.setDateNotification(rs.getDate("dateNotification").toLocalDate());
                notf.setSujet(rs.getString("sujet"));
                
                myList.add(notf);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return myList;
    }
    
}



/**
*@Lau82 © 2018
*/
