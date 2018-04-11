/**
* @Project: AllForKids
* @Classe: Notification
* @Date: 2 avr. 2018
* @Time: 19:21:18
*
* @author Lauris
*/


package allforkids.entities;

import java.time.LocalDate;
import java.util.Objects;


public class Notification {
    
    private Integer id;
    private Integer emetteur;
    private Integer destinataire ;
    private Integer reservation;
    private LocalDate dateNotification;
    private String sujet;

    public Notification(){}

    public Notification(Integer emetteur, Integer destinataire, Integer reservation, LocalDate dateNotification, String sujet) {
        this.emetteur = emetteur;
        this.destinataire = destinataire;
        this.reservation = reservation;
        this.dateNotification = dateNotification;
        this.sujet = sujet;
    }

    public Notification(Integer id, Integer emetteur, Integer destinataire, Integer reservation, LocalDate dateNotification, String sujet) {
        this.id = id;
        this.emetteur = emetteur;
        this.destinataire = destinataire;
        this.reservation = reservation;
        this.dateNotification = dateNotification;
        this.sujet = sujet;
    }

    public Notification(Integer emetteur, Integer destinataire, LocalDate dateNotification, String sujet) {
        this.emetteur = emetteur;
        this.destinataire = destinataire;
        this.dateNotification = dateNotification;
        this.sujet = sujet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(Integer emetteur) {
        this.emetteur = emetteur;
    }

    public Integer getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Integer destinataire) {
        this.destinataire = destinataire;
    }

    public Integer getReservation() {
        return reservation;
    }

    public void setReservation(Integer reservation) {
        this.reservation = reservation;
    }

    public LocalDate getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(LocalDate dateNotification) {
        this.dateNotification = dateNotification;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    @Override
    public int hashCode() {
        return id*emetteur.hashCode()*reservation.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Notification other = (Notification) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.destinataire, other.destinataire)) {
            return false;
        }
        if (!Objects.equals(this.reservation, other.reservation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", emetteur=" + emetteur + ", destinataire=" + destinataire + ", reservation=" + reservation + ", dateNotification=" + dateNotification + ", sujet=" + sujet + '}';
    }
}



/**
*@Lau82 © 2018
*/
