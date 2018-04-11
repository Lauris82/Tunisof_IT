/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class evenement {
 public int id_evenement ;
 public int evenement_user;
 public String nom ;
   public String description;
    public Date date_debut ;
    public Date date_fin ;
    public String emplacement ;
    public String image ;
    public int etat ;
public int nbr_place ;

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getEvenement_user() {
        return evenement_user;
    }

    public void setEvenement_user(int evenement_user) {
        this.evenement_user = evenement_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "evenement{" + "id_evenement=" + id_evenement + ", evenement_user=" + evenement_user + ", nom=" + nom + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", emplacement=" + emplacement + ", image=" + image + ", etat=" + etat + '}';
    }

    public evenement() {
    }

    public evenement(int evenement_user, String nom, String description, Date date_debut, Date date_fin, String emplacement, String image, int nbr_place) {
        this.evenement_user = evenement_user;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.emplacement = emplacement;
        this.image = image;
     
        this.nbr_place = nbr_place;
    }

    public evenement(int id_evenement, int evenement_user, String nom, String description, Date date_debut, Date date_fin, String emplacement, String image, int etat, int nbr_place) {
        this.id_evenement = id_evenement;
        this.evenement_user = evenement_user;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.emplacement = emplacement;
        this.image = image;
        this.etat = etat;
        this.nbr_place = nbr_place;
    }

  

    
    
    
  
}
