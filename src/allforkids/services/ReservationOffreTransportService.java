/**
* @Project: AllForKids
* @Classe: ReservationTransportService
* @Date: 14 mars 2018
* @Time: 20:35:26
*
* @author Lauris
*/


package allforkids.services;

import allforkids.entities.OffreTransport;
import allforkids.entities.ReservationOffreTransport;
import allforkids.entities.User;
import allforkids.utils.MyConnexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ReservationOffreTransportService {
    
    Connection cn = MyConnexion.getInstance().getConnection();
    
    
    public boolean reserverOffreTransport(OffreTransport o) throws SQLException, IOException{
        
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = localDate.format(formatter);

        UserService uss = new UserService();
        int idU = uss.getId();
        User user = uss.getUser(idU);
        User userN = uss.getUser(o.getUser());
        NotificationService nos = new NotificationService();
        
        boolean existe = true;
        //existe = dejaReserver(user.getId(), o.getId());
        
        if(existe == true){
            
            String req1 = "UPDATE `offre_transport` SET place_restant=? WHERE id=?";
            PreparedStatement st1 = cn.prepareStatement(req1);
            st1.setString(1, String.valueOf(o.getPlaceRestant()-1));
            st1.setString(2, String.valueOf(o.getId()));
            st1.executeUpdate();
            
            
            String requete = "INSERT INTO  `reservation_transport` (user,nombreEnfants,date_reservation,offreTransport) VALUES (?,?,?,?) ";
            PreparedStatement st = cn.prepareStatement(requete);
            
            st.setString(1, String.valueOf(user.getId()));
            st.setString(2, String.valueOf(1));
            st.setString(3, String.valueOf(today));
            st.setString(4, String.valueOf(o.getId()));
            
            String sujet = "Votre offre: "+o.getDescription()+" a été réservé par "+user.getNom();
            nos.createNotification(idU, o.getUser(), sujet);
            
            String req12 = "UPDATE `user` SET notification=? WHERE id=?";
            PreparedStatement st12 = cn.prepareStatement(req12);
            st12.setString(1, String.valueOf(userN.getNotification()+1));
            st12.setString(2, String.valueOf(userN.getId()));
            st12.executeUpdate();
            
            System.out.println("Reservation effectuee");
            
            return st.executeUpdate() == 1;
        }
        return false;
    }
    
    
    public boolean  annulerReservation(int user, int offre) throws SQLException{
        
        String requete = "UPDATE `reservation_transport` SET etat=? where `user`=? and `offreTransport`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        
        st.setInt(1, 0);
        st.setInt(2, user);
        st.setInt(3, offre);
        st.executeUpdate();

        System.err.println("Reservation annulée");
        return false;
    }
    
    
    public void deleteReservation(int user, int offre){
        try {
            String requete = "DELETE FROM `reservation_transport` where `user`=? and `offreTransport`=?";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setInt(1, user);
            st.setInt(2, offre);
            st.executeUpdate();
            
            System.err.println("Reservation supprimée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void updateEtat(ReservationOffreTransport rots) throws SQLException{
        String requete = "UPDATE `reservation_transport` set etat=? where id=?";
        PreparedStatement st = cn.prepareStatement(requete);
        
        st.setInt(1, +1);
        st.setInt(2, rots.getId());
        st.executeUpdate();
        
        System.out.println("OffreTransport modifiée");
    }
    
    
    public int reserverExiste(int user, int offre) throws SQLException{
        
        String requete = "select * from `reservation_transport` where `user`=? and `offreTransport`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        
        st.setString(1, String.valueOf(user));
        st.setString(2, String.valueOf(offre));
        ResultSet res = st.executeQuery();
        
        if(res.last()){
            return 1;
        }else{
            return 0;
        }
    }
    
    
    public int dejaReserver(int user, int offre) throws SQLException{
        
        String requete = "select * from `reservation_transport` where `user`=? and `offreTransport`=? and `etat`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        
        st.setString(1, String.valueOf(user));
        st.setString(2, String.valueOf(offre));
        st.setInt(3, 1);
        ResultSet res = st.executeQuery();
        
        if(res.last()){
            return 1;
        }else{
            return 0;
        }
    }
    
    
    public ObservableList<ReservationOffreTransport> listerReservationOffreTransport(int idUser){
        ObservableList<ReservationOffreTransport> myList = FXCollections.observableArrayList();
        
        try {
            
            String requete = "SELECT * FROM reservation_transport where `user`=? order by id desc";
            PreparedStatement st = cn.prepareStatement(requete);

            st.setString(1, String.valueOf(idUser));
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                ReservationOffreTransport o = new ReservationOffreTransport();
                o.setId(rs.getInt(1));
                o.setUser(rs.getInt(2));
                o.setNombreEnfant(rs.getInt(3));
                o.setDateReservation(rs.getDate(4));
                o.setOffre(rs.getInt(5));
                
                myList.add(o);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }
    
    
    public ReservationOffreTransport getReservationOffreTransport(int id) throws SQLException {
       
        ReservationOffreTransport offre = new ReservationOffreTransport();

        String requete = "select * from `reservation_transport` where `id`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, String.valueOf(id));
        ResultSet res = st.executeQuery();

        while(res.next()){
            System.out.println(res.getString("description"));
            offre.setId(res.getInt("id"));
            offre.setUser(res.getInt("user"));
            offre.setNombreEnfant(res.getInt("nombreEnfants"));
            offre.setDateReservation(res.getDate("date_reservation"));
            offre.setOffre(res.getInt("offreTransport"));
        }

        return offre;
    }
    
}



/**
*@Lau82 © 2018
*/
