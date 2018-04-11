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
public class reservation {
     public int id_reservation ;
 public int reservation_user;
 public int evenement_reservation;
 public Date date_reservation ;

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getReservation_user() {
        return reservation_user;
    }

    public void setReservation_user(int reservation_user) {
        this.reservation_user = reservation_user;
    }

    public int getEvenement_reservation() {
        return evenement_reservation;
    }

    public void setEvenement_reservation(int evenement_reservation) {
        this.evenement_reservation = evenement_reservation;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    @Override
    public String toString() {
        return "reservation{" + "id_reservation=" + id_reservation + ", reservation_user=" + reservation_user + ", evenement_reservation=" + evenement_reservation + ", date_reservation=" + date_reservation + '}';
    }

    public reservation() {
    }

    public reservation(int id_reservation, int reservation_user, int evenement_reservation, Date date_reservation) {
        this.id_reservation = id_reservation;
        this.reservation_user = reservation_user;
        this.evenement_reservation = evenement_reservation;
        this.date_reservation = date_reservation;
    }
     public reservation( int reservation_user, int evenement_reservation, Date date_reservation) {
     
        this.reservation_user = reservation_user;
        this.evenement_reservation = evenement_reservation;
        this.date_reservation = date_reservation;
    }

    public reservation(int reservation_user, int evenement_reservation) {
        this.reservation_user = reservation_user;
        this.evenement_reservation = evenement_reservation;
    }
 
}
