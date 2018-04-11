/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.services;

import allforkids.yassine.entities.evenement;
import allforkids.yassine.utils.myConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class crudEvenement {
    public ResultSet rs ;
            Connection cnx = myConnexion.getInstance().getConnection();

    
    public void ajouterEvenement(evenement ev){
      
        String req = "INSERT  INTO `evenement`"
                + " ( `evenement_user`, `nom`, `description`, `date_debut`, `date_fin`, `emplacement`, `image`, `etat`, `nbr_place`) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
       try{
            PreparedStatement statement=cnx.prepareStatement(req);
          
            statement.setInt(1, ev.getEvenement_user());
            statement.setString(2, ev.getNom());
            statement.setString(3, ev.getDescription());
            statement.setDate(4, ev.getDate_debut());
            statement.setDate(5, ev.getDate_fin());
            statement.setString(6, ev.getEmplacement());
            statement.setString(7, ev.getImage());
            statement.setInt(8, 0);
            statement.setInt(9, ev.getNbr_place());
            System.out.println("evenement ajouter");
            statement.executeUpdate();
       }catch(SQLException ex)
       {
           ex.printStackTrace();
       }
    }
    public void supprimerEvenement(int id)
    {
        String req="UPDATE `evenement` SET `etat`=1 WHERE `id_evenement`="+id;
        try{
        PreparedStatement statement=cnx.prepareStatement(req);
        statement.executeUpdate();
         System.out.println("evenement supprimer");
        }catch(SQLException ex)
       {
           ex.printStackTrace();
       }
    }
    public List<evenement> afficherEvenement ()
    {
        List<evenement> l=new ArrayList<>();
        String req="SELECT * FROM evenement where etat=0 ORDER BY date_debut";
        try{
            Statement st=cnx.createStatement();
            rs=st.executeQuery(req);
        while(rs.next())
        {
           
          
            evenement ev= new evenement(); 
        
            ev.setId_evenement(rs.getInt(1));
          ev.setEvenement_user(rs.getInt(2));
          ev.setNom(rs.getString(3));
          ev.setDescription(rs.getString(4));
          ev.setDate_debut(rs.getDate(5));
          ev.setDate_fin(rs.getDate(6));
          ev.setEmplacement(rs.getString(7));
          ev.setImage(rs.getString(8));
          ev.setEtat(rs.getInt(9));
          ev.setNbr_place(rs.getInt(10));
            l.add(ev);
        }
        }catch(SQLException ex)
       {
           ex.printStackTrace();
       }
        
        return l ;
    }
     public List<evenement> afficherTouteEvenement ()
    {
        List<evenement> l=new ArrayList<>();
        String req="SELECT * FROM evenement ORDER BY date_debut";
        try{
            Statement st=cnx.createStatement();
            rs=st.executeQuery(req);
        while(rs.next())
        {
           
          
            evenement ev= new evenement(); 
        
            ev.setId_evenement(rs.getInt(1));
          ev.setEvenement_user(rs.getInt(2));
          ev.setNom(rs.getString(3));
          ev.setDescription(rs.getString(4));
          ev.setDate_debut(rs.getDate(5));
          ev.setDate_fin(rs.getDate(6));
          ev.setEmplacement(rs.getString(7));
          ev.setImage(rs.getString(8));
          ev.setEtat(rs.getInt(9));
          ev.setNbr_place(rs.getInt(10));
            l.add(ev);
        }
        }catch(SQLException ex)
       {
           ex.printStackTrace();
       }
        
        return l ;
    }
    public void modifierEvenement(evenement ev,int id){
        String req="UPDATE `evenement` SET `nom`=?,`description`=?,`date_debut`=?,`date_fin`=?,`emplacement`=? ,`image`=? , `nbr_place` =? WHERE id_evenement= ?";
  try{
      PreparedStatement statement=cnx.prepareStatement(req);
     
            
            
            statement.setString(1, ev.getNom());
            statement.setString(2, ev.getDescription());
            statement.setDate(3, ev.getDate_debut());
            statement.setDate(4, ev.getDate_fin());
            statement.setString(5, ev.getEmplacement());
            statement.setString(6, ev.getImage());
          statement.setInt(7, ev.getNbr_place());
            statement.setInt(8, id);
             
            System.out.println("evenement modifier");
            statement.executeUpdate();
  }catch(SQLException ex){
      ex.printStackTrace();
  }
    }
    public List<evenement> rechercheEvenement(String valeur,String critere)
    {
        critere=critere.replace(" ", "_");
        
        System.out.println(critere);
        List<evenement> l =new ArrayList<>();
        String req="select * from evenement where "+critere+" LIKE '%" + valeur + "%'"
                + "  and etat=0 order by date_debut";
         try{
             Statement s =cnx.createStatement();
             ResultSet rs=   s.executeQuery(req);
             while(rs.next())
             {
                 evenement ev = new evenement();
                     ev.setId_evenement(rs.getInt(1));
          ev.setEvenement_user(rs.getInt(2));
          ev.setNom(rs.getString(3));
          ev.setDescription(rs.getString(4));
          ev.setDate_debut(rs.getDate(5));
          ev.setDate_fin(rs.getDate(6));
          ev.setEmplacement(rs.getString(7));
          ev.setEtat(rs.getInt(9));
            l.add(ev);
             }
         }catch(SQLException ex){
      ex.printStackTrace();
  }
                 
        return l ;
    }
     public List<evenement> rechercheTouteEvenement(String valeur,String critere)
    {
        critere=critere.replace(" ", "_");
        
        System.out.println(critere);
        List<evenement> l =new ArrayList<>();
        String req="select * from evenement where "+critere+" LIKE '%" + valeur + "%'"
                + "  order by date_debut";
         try{
             Statement s =cnx.createStatement();
             ResultSet rs=   s.executeQuery(req);
             while(rs.next())
             {
                 evenement ev = new evenement();
                     ev.setId_evenement(rs.getInt(1));
          ev.setEvenement_user(rs.getInt(2));
          ev.setNom(rs.getString(3));
          ev.setDescription(rs.getString(4));
          ev.setDate_debut(rs.getDate(5));
          ev.setDate_fin(rs.getDate(6));
          ev.setEmplacement(rs.getString(7));
          ev.setImage(rs.getString(8));
          ev.setEtat(rs.getInt(9));
            l.add(ev);
             }
         }catch(SQLException ex){
      ex.printStackTrace();
  }
                 
        return l ;
    }
    
    public evenement detailEvenment(int id)
    {
         evenement ev = new evenement();
        String req="select * from evenement where `id_evenement`="+id;
          try{
            Statement st=cnx.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
             {
                
                     ev.setId_evenement(rs.getInt(1));
          ev.setEvenement_user(rs.getInt(2));
          ev.setNom(rs.getString(3));
          ev.setDescription(rs.getString(4));
          ev.setDate_debut(rs.getDate(5));
          ev.setDate_fin(rs.getDate(6));
          ev.setEmplacement(rs.getString(7));
          ev.setImage(rs.getString(8));
          ev.setEtat(rs.getInt(9));
           ev.setNbr_place(rs.getInt(10));
          
             }
             }catch(SQLException ex){
      ex.printStackTrace();
    }
    return ev ;      
}
}
