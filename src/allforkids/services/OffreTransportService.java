/**
* @Project: AllForKids
* @Classe: OffreTransportService
* @Date: 14 mars 2018
* @Time: 20:34:54
*
* @author Lauris
*/


package allforkids.services;

import allforkids.entities.OffreTransport;
import allforkids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class OffreTransportService {
    
    Connection cn = MyConnexion.getInstance().getConnection();
    
    public void addOffreTransport(OffreTransport o){
        try {
            
            String requete = "INSERT INTO offre_transport (user,description,destination,date_debut,date_fin,nombre_place,place_restant,prix) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            
            st.setString(1, String.valueOf(o.getUser()));
            st.setString(2, o.getDescription());
            st.setString(3, o.getDestination());
            st.setString(4, String.valueOf(o.getDateDebut()));
            st.setString(5, String.valueOf(o.getDateFin()));
            st.setString(6, String.valueOf(o.getNombrePlace()));
            st.setString(7, String.valueOf(o.getPlaceRestant()));
            st.setString(8, String.valueOf(o.getPrix()));
            
            st.executeUpdate();
            
            System.out.println("Offre Transport ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    public List<OffreTransport> listerOffreTransport(){
        List<OffreTransport> myList = new ArrayList<>();
        
        try {
            
            String requete = "SELECT * FROM offre_transport";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                OffreTransport o = new OffreTransport();
                o.setId(rs.getInt(1));
                o.setUser(rs.getInt(2));
                o.setDescription(rs.getString(3));
                o.setDestination(rs.getString("destination"));
                o.setDateDebut(rs.getDate("date_debut").toLocalDate());
                o.setDateFin(rs.getDate("date_fin").toLocalDate());
                o.setNombrePlace(rs.getInt("nombre_place"));
                o.setPlaceRestant(rs.getInt("place_restant"));
                o.setPrix(rs.getDouble("prix"));
                
                myList.add(o);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }
    
    
    public ObservableList<OffreTransport>  searchOffreTransport(String destination) throws SQLException{
        ObservableList<OffreTransport> myList = FXCollections.observableArrayList();

        String requete = "select * from `offre_transport` where `destination`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, String.valueOf(destination));
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            OffreTransport o = new OffreTransport();
            o.setId(rs.getInt(1));
            o.setUser(rs.getInt(2));
            o.setDescription(rs.getString(3));
            o.setDestination(rs.getString("destination"));
            o.setDateDebut(rs.getDate("date_debut").toLocalDate());
            o.setDateFin(rs.getDate("date_fin").toLocalDate());
            o.setNombrePlace(rs.getInt("nombre_place"));
            o.setPlaceRestant(rs.getInt("place_restant"));
            o.setPrix(rs.getDouble("prix"));

            myList.add(o);
        }
        return myList;
    }
    
    
    public ObservableList<OffreTransport>  searchOffreTransportByDateDebut(LocalDate dateDebut) throws SQLException{
        ObservableList<OffreTransport> myList = FXCollections.observableArrayList();

        String requete = "select * from `offre_transport` where `date_debut`>=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, String.valueOf(dateDebut));
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            OffreTransport o = new OffreTransport();
            o.setId(rs.getInt(1));
            o.setUser(rs.getInt(2));
            o.setDescription(rs.getString(3));
            o.setDestination(rs.getString("destination"));
            o.setDateDebut(rs.getDate("date_debut").toLocalDate());
            o.setDateFin(rs.getDate("date_fin").toLocalDate());
            o.setNombrePlace(rs.getInt("nombre_place"));
            o.setPlaceRestant(rs.getInt("place_restant"));
            o.setPrix(rs.getDouble("prix"));

            myList.add(o);
        }
        return myList;
    }
    
    
    public ObservableList<OffreTransport>  searchOffreTransportByDateFin(LocalDate dateFin) throws SQLException{
        ObservableList<OffreTransport> myList = FXCollections.observableArrayList();

        String requete = "select * from `offre_transport` where `date_fin`<=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, String.valueOf(dateFin));
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            OffreTransport o = new OffreTransport();
            o.setId(rs.getInt(1));
            o.setUser(rs.getInt(2));
            o.setDescription(rs.getString(3));
            o.setDestination(rs.getString("destination"));
            o.setDateDebut(rs.getDate("date_debut").toLocalDate());
            o.setDateFin(rs.getDate("date_fin").toLocalDate());
            o.setNombrePlace(rs.getInt("nombre_place"));
            o.setPlaceRestant(rs.getInt("place_restant"));
            o.setPrix(rs.getDouble("prix"));

            myList.add(o);
        }
        return myList;
    }
    
    
    public ObservableList<OffreTransport>  searchOffreTransportByPrix(double prix) throws SQLException{
        ObservableList<OffreTransport> myList = FXCollections.observableArrayList();

        String requete = "select * from `offre_transport` where `prix`<=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, String.valueOf(prix));
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            OffreTransport o = new OffreTransport();
            o.setId(rs.getInt(1));
            o.setUser(rs.getInt(2));
            o.setDescription(rs.getString(3));
            o.setDestination(rs.getString("destination"));
            o.setDateDebut(rs.getDate("date_debut").toLocalDate());
            o.setDateFin(rs.getDate("date_fin").toLocalDate());
            o.setNombrePlace(rs.getInt("nombre_place"));
            o.setPlaceRestant(rs.getInt("place_restant"));
            o.setPrix(rs.getDouble("prix"));

            myList.add(o);
        }
        return myList;
    }
    
    
    public void updateOffreTransport(OffreTransport o, int id){
        try {
            String requete = "UPDATE offre_transport set description=?, destination=?, date_debut=?, date_fin=?, nombre_place=?, place_restant=?, prix=? where id=?";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, o.getDescription());
            st.setString(2, o.getDestination());
            st.setString(3, String.valueOf(o.getDateDebut()));
            st.setString(4, String.valueOf(o.getDateFin()));
            st.setString(5, String.valueOf(o.getNombrePlace()));
            st.setString(6, String.valueOf(o.getPlaceRestant()));
            st.setString(7, String.valueOf(o.getPrix()));
            st.setInt(8, id);
            st.executeUpdate();
            
            System.out.println("OffreTransport modifiée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    public void deleteOffreTransport(int id){
        try {
            String requete = "DELETE FROM offre_transport where id=?";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setInt(1, id);
            st.executeUpdate();
            
            System.err.println("Personne supprimée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public OffreTransport getOffreTransport(int id) throws SQLException {
        OffreTransport offre = new OffreTransport();

        String requete = "select * from `offre_transport` where `id`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, String.valueOf(id));
        ResultSet res = st.executeQuery();

        while(res.next()){
            System.out.println(res.getString("description"));
            offre.setId(res.getInt("id"));
            offre.setUser(res.getInt("user"));
            offre.setDescription(res.getString("description"));
            offre.setDestination(res.getString("destination"));
            offre.setDateDebut(res.getDate("date_debut").toLocalDate());
            offre.setDateFin(res.getDate("date_fin").toLocalDate());
            offre.setNombrePlace(res.getInt("nombre_place"));
            offre.setPlaceRestant(res.getInt("place_restant"));
            offre.setPrix(res.getDouble("prix"));
        }

        return offre;
    }

}



/**
*@Lau82 © 2018
*/
