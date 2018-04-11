/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.services;

import allforkids.yasmine.entities.ReclamationEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import allforkids.yasmine.utiles.DBconnection;

/**
 *
 * @author DELL
 */
public class ReclamationService {
  
      DBconnection I = DBconnection.getInstance();
    Connection C = DBconnection.getInstance().getConnection();
    
    
    public void EnvoiReclamation(ReclamationEntity  R){
    
          try {
              String requete = "INSERT INTO reclamation (idRec,objetRec,typeRec,etatRec,contenuRec,user,membreRec,mail) VALUES (?,?,?,?,?,?,?,?)";
              
              PreparedStatement st = C.prepareStatement(requete);
              
              st.setInt(1, R.getIdRec());
            
              st.setString(2, R.getObjetRec());//
              
              st.setString(3, R.getTypeRec());
              
              st.setInt(4, R.getEtatRec());
              st.setString(5, R.getContenuRec());//
              st.setInt(6, R.getUser());
              st.setString(7, R.getMembreRec());
              st.setString(8, R.getMail());//
              
              st.executeUpdate();
              System.out.println("reclamation envoyer");
               
          } catch (SQLException ex) {
              Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
          }
    
    }
    
    
    
    
    
    
}
