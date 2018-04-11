/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.services;

import allforkids.yassine.entities.User;
import allforkids.yassine.entities.reservation;
import allforkids.yassine.utils.myConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class crudReservation {
    Connection cnx=myConnexion.getInstance().getConnection();
    public void ajouterReservation(reservation res)
    {
        try{
        String req="INSERT INTO `reservation`( `reservation_user`, `evenement_reservation`, `date_reservation`) VALUES (?,?,?)";
        PreparedStatement statemnt =cnx.prepareStatement(req);
        statemnt.setInt(1,res.getReservation_user());
        statemnt.setInt(2,res.getEvenement_reservation() );
        statemnt.setDate(3, Date.valueOf(LocalDate.now()));
        statemnt.executeUpdate();
            System.out.println("reservation ajouter");
        }catch(SQLException ex)
       {
           ex.printStackTrace();
       }
    }
    public void supprimerReservation(int idUsr , int idEv)
    {
        String req="DELETE FROM `reservation` WHERE reservation_user=? and evenement_reservation=?";
        try{
        PreparedStatement statement=cnx.prepareStatement(req);
        statement.setInt(1, idUsr);
          statement.setInt(2, idEv);
        statement.executeUpdate();
         System.out.println("reservation supprimer");
        }catch(SQLException ex)
       {
           ex.printStackTrace();
       }
    }
    public List<reservation> afficheMembre(int idEv)
    {
        List<reservation> l=new ArrayList<reservation>();
        String req="SELECT * FROM `reservation` WHERE evenement_reservation="+idEv;
        try{ 
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while (rs.next())
            {
                reservation r=new reservation();
                r.setReservation_user(rs.getInt(2));
                r.setDate_reservation(rs.getDate(4));
                l.add(r);
            }
        }catch(SQLException ex)
       {
           ex.printStackTrace();
       }
        return l;
    }
    public boolean estReserver(int idu ,int ide)
    {
          List<reservation> l=new ArrayList<reservation>();
       boolean existe=false  ;
       String req="SELECT * FROM `reservation` WHERE evenement_reservation= "+ide+" and reservation_user= "+idu;
    
try{ 
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while (rs.next())
            {
                reservation r=new reservation();
                r.setDate_reservation(rs.getDate(4));
                l.add(r);
            }
          
}catch(SQLException ex)
       {
           ex.printStackTrace();
       }
 if(l.isEmpty()==true)
     return false ;
 else return true ;
}
   public User infoUser(int id)
   {
       User user=new User();
    
       String req="SELECT * FROM `user` WHERE id= "+id;
       try{ 
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while (rs.next())
            {
                   
                user.setNom(rs.getString(13));
                user.setPrenom(rs.getString(14));
                user.setImage(rs.getString("image"));
            }
          
}catch(SQLException ex)
       {
           ex.printStackTrace();
       }
       return user ;
   }
   public int nbrMembres (int ide )
   {
       int count =0 ;
     String req="SELECT COUNT(*) FROM `reservation` WHERE evenement_reservation= "+ide;
 try{ 
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
    count = rs.getInt(1);
    }
            }catch(SQLException ex)
       {
           ex.printStackTrace();
       }
 return count ;
   }
}