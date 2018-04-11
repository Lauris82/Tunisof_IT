/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.services;

import allforkids.yasmine.entities.ClubEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import allforkids.yasmine.utiles.DBconnection;

/**
 *
 * @author DELL
 */
public class ClubService {
    
    DBconnection I=DBconnection.getInstance();
    Connection C=DBconnection.getInstance().getConnection();
    
    
    public void AjouterClub(ClubEntity club){
  String requete="INSERT INTO club (idclub,club_user,nom,description,numTel,gouvernorat,municipalite,nomrue,coderue,lat,lng) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    
     try {
            //creation de requete
            PreparedStatement st=C.prepareStatement(requete);
            //les num de parametres ça commence avec 1 pas par 0
            st.setInt(1, club.getIdclub());
            st.setInt(2, club.getClub_user());
            st.setString(3, club.getNom());
            st.setString(4, club.getDescription());
            st.setString(5, club.getNumTel());
            st.setString(6, club.getGouvernorat());
            st.setString(7, club.getMunicipalite());
            st.setString(8, club.getNomrue());
            st.setString(9, club.getCoderue());
            st.setDouble(10, club.getLat());
            st.setDouble(11, club.getLng());
     //on a passer requete dans prepared statement donc on le met pas dans executequery
            st.executeUpdate();
            System.out.println("club ajouter avec succée!");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
 
    
    public ObservableList<ClubEntity> selectAll(){
        ObservableList<ClubEntity> clubs=FXCollections.observableArrayList();

       String req="select * from club";
        try {
            Statement statement=C.createStatement();
            ResultSet resultSet =statement.executeQuery(req);
            
            while(resultSet.next()){
//                ClubEntity c=new ClubEntity(resultSet.getInt(1), resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
                 ClubEntity c2 = new ClubEntity();
            c2.setIdclub(resultSet.getInt(1));
            c2.setNom(resultSet.getString(3));
            c2.setDescription(resultSet.getString(4));
            c2.setNumTel(resultSet.getString(5));
            c2.setGouvernorat(resultSet.getString(6));
                
                clubs.add(c2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clubs;
        
    }
    
    public void ModifierClub(ClubEntity club){
                String req="UPDATE club SET nom=?,description=?,numTel=?,gouvernorat=? WHERE idclub="+club.getIdclub()+";";

        try {
           PreparedStatement st=C.prepareStatement(req);
           st.setString(1, club.getNom());
           st.setString(2, club.getDescription());
           st.setString(3, club.getNumTel());
           st.setString(4, club.getGouvernorat());
           
           st.executeUpdate();
            System.out.println(club.getIdclub());
            System.out.println("club modifier!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }

    public void supprimerClub(int id) {
        try {
            String requete= "delete from club where idclub=?;";
            PreparedStatement st=C.prepareStatement(requete);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Club Supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
