/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.tests;

import allforkids.yassine.entities.evenement;
import allforkids.yassine.entities.reservation;
import allforkids.yassine.services.crudEvenement;
import allforkids.yassine.services.crudReservation;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class mainClass {
    public static void main(String[] args) {
        crudEvenement e=new crudEvenement();
        crudReservation r=new crudReservation();
        evenement ev=new evenement();
        reservation res=new reservation();
        
       
        ev.setEvenement_user(5);
        ev.setDate_debut(Date.valueOf(LocalDate.now()));
        ev.setDate_fin(Date.valueOf(LocalDate.now()));
        ev.setEmplacement("tunis");
      
        ev.setNom("aaaa");
        ev.setImage("seeee");
        ev.setDescription("aaaaa");
        res.setEvenement_reservation(3);
        res.setReservation_user(5);
        
    //  e.ajouterEvenement(ev);
    //   e.supprimerEvenement(115);
      //  System.out.println(e.afficherEvenement());  
      // e.modifierEvenement(ev, 111);
      //  System.out.println(e.rechercheEvenement("Festival des enfants"));  
      //  r.ajouterReservation(res);
      //  r.supprimerReservation(5,3);
         //  System.out.println(r.afficheMembre(4));
        
    }
}
