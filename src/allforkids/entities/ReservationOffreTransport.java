/**
* @Project: AllForKids
* @Classe: ReservationTransprt
* @Date: 14 mars 2018
* @Time: 14:55:40
*
* @author Lauris
*/


package allforkids.entities;

import java.util.Date;
import java.util.Objects;


public class ReservationOffreTransport {
    
    private Integer id;
    private Integer user;
    private Integer nombreEnfant ;
    private Date dateReservation;
    private Integer offre;

    public ReservationOffreTransport() {}

    public ReservationOffreTransport(Integer id) {
        this.id = id;
    }

    public ReservationOffreTransport(Integer id, Integer user, Integer nombreEnfant, Date dateReservation, Integer offre) {
        this.id = id;
        this.user = user;
        this.nombreEnfant = nombreEnfant;
        this.dateReservation = dateReservation;
        this.offre = offre;
    }

    public ReservationOffreTransport(Integer user, Integer nombreEnfant, Date dateReservation, Integer offre) {
        this.user = user;
        this.nombreEnfant = nombreEnfant;
        this.dateReservation = dateReservation;
        this.offre = offre;
    }

    public ReservationOffreTransport(Integer user, Integer offre) {
        this.user = user;
        this.offre = offre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getNombreEnfant() {
        return nombreEnfant;
    }

    public void setNombreEnfant(Integer nombreEnfant) {
        this.nombreEnfant = nombreEnfant;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Integer getOffre() {
        return offre;
    }

    public void setOffre(Integer offre) {
        this.offre = offre;
    }

    @Override
    public int hashCode() {
        return id*offre;
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
        final ReservationOffreTransport other = (ReservationOffreTransport) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReservationOffreTransport{" + "id=" + id + ", user=" + user + ", nombreEnfant=" + nombreEnfant + ", dateReservation=" + dateReservation + ", offre=" + offre + '}' + "\n";
    }
    
    
    
}



/**
*@Lau82 © 2018
*/
