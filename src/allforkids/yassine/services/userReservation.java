/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.services;

/**
 *
 * @author ASUS
 */
public class userReservation {
    public int idEvent ;
    public String date ;
 public  String nom;
public String prenom ;
public String image ;
public String etat ;
public String dateF ;
public String emplacement ;
public String nomusr ;

public String image2 ;
    public userReservation(String date, String nom, String image, String etat, String dateF, String emplacement , int idEvent,String nomusr, String image2) {
        this.date = date;
        this.nom = nom;
        this.image = image;
        this.etat = etat;
        this.dateF = dateF;
        this.emplacement = emplacement;
        this.idEvent=idEvent;
        this.nomusr=nomusr ;
        this.image2=image2 ;
    }



    public userReservation(String date, String nom, String prenom, String image) {
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }

    public String getNomusr() {
        return nomusr;
    }

    public void setNomusr(String nomusr) {
        this.nomusr = nomusr;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }
    public void setImage(String image) {
        this.image = image;
    }
 

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public userReservation() {
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

  
   
    
}
