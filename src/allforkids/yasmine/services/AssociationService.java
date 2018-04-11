/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.services;

import allforkids.yasmine.entities.AssociationEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import allforkids.yasmine.utiles.DBconnection;

/**
 *
 * @author DELL
 */
public class AssociationService {

    DBconnection I = DBconnection.getInstance();
    Connection C = DBconnection.getInstance().getConnection();

    public void AjouterClub(AssociationEntity association) {
        String requete = "INSERT INTO association (association_user,nom,description,num_tel,gouvernorat,municipalite,nomrue,coderue,lat,lng) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            //creation de requete
            PreparedStatement st = C.prepareStatement(requete);
            //les num de parametres ça commence avec 1 pas par 0
           // st.setInt(1, association.getId_aasociation());
            st.setInt(1, association.getAssociation_user());
            st.setString(2, association.getNom());
            st.setString(3, association.getDescription());
            st.setString(4, association.getNum_tel());
            st.setString(5, association.getGouvernorat());
            st.setString(6, association.getMunicipalite());
            st.setString(7, association.getNomrue());
            st.setString(8, association.getCoderue());
            st.setDouble(9, association.getLat());
            st.setDouble(10, association.getLng());
            //on a passer requete dans prepared statement donc on le met pas dans executequery
            st.executeUpdate();
            System.out.println("association ajouter avec succée!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<AssociationEntity> selectAll() {
        ObservableList<AssociationEntity> associations = FXCollections.observableArrayList();

        String req = "select * from association";
        try {
            Statement statement = C.createStatement();
            ResultSet resultSet = statement.executeQuery(req);

            while (resultSet.next()) {
                AssociationEntity c2 = new AssociationEntity();
                c2.setId_aasociation(resultSet.getInt(1));
                c2.setNom(resultSet.getString(3));
                c2.setDescription(resultSet.getString(4));
                c2.setNum_tel(resultSet.getString(5));
                c2.setGouvernorat(resultSet.getString(6));

                associations.add(c2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return associations;

    }

    
    public void supprimerAssociation(int id) {
        try {
            String requete= "delete from association where id_aasociation=?;";
            PreparedStatement st=C.prepareStatement(requete);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("association Supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
       public void ModifierAssociation(AssociationEntity association){
                String req="UPDATE association SET nom=?,description=?,num_tel=?,gouvernorat=? WHERE id_aasociation="+association.getId_aasociation()+";";

        try {
           PreparedStatement st=C.prepareStatement(req);
           st.setString(1, association.getNom());
           st.setString(2, association.getDescription());
           st.setString(3, association.getNum_tel());
           st.setString(4, association.getGouvernorat());
           
           st.executeUpdate();
            System.out.println(association.id_aasociation);
            System.out.println("association modifier!");
            
        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }
    
    
}
