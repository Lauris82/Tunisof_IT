/**
* @Project: AllForKids
* @Classe: OffreTransport
* @Date: 14 mars 2018
* @Time: 14:54:12
*
* @author Lauris
*/


package allforkids.entities;

import java.time.LocalDate;
import java.util.Objects;


public class OffreTransport {
    
    private Integer id;
    private Integer user;
    private String description ;
    private String destination;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer nombrePlace;
    private Integer placeRestant;
    private Double prix;

    public OffreTransport() {}

    public OffreTransport(Integer id) {
        this.id = id;
    }

    public OffreTransport(Integer id, Integer user, String description, String destination, LocalDate dateDebut, LocalDate dateFin, Integer nombrePlace, Integer placeRestant, Double prix) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.destination = destination;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePlace = nombrePlace;
        this.placeRestant = placeRestant;
        this.prix = prix;
    }

    public OffreTransport(Integer user, String description, String destination, LocalDate dateDebut, LocalDate dateFin, Integer nombrePlace, Integer placeRestant, Double prix) {
        this.user = user;
        this.description = description;
        this.destination = destination;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePlace = nombrePlace;
        this.placeRestant = placeRestant;
        this.prix = prix;
    }

    public OffreTransport(String description, String destination, LocalDate dateDebut, LocalDate dateFin, Integer nombrePlace, Integer placeRestant, Double prix) {
        this.description = description;
        this.destination = destination;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePlace = nombrePlace;
        this.placeRestant = placeRestant;
        this.prix = prix;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(Integer nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    public Integer getPlaceRestant() {
        return placeRestant;
    }

    public void setPlaceRestant(Integer placeRestant) {
        this.placeRestant = placeRestant;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        return id*description.hashCode();
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
        final OffreTransport other = (OffreTransport) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "OffreTransport{" + "id=" + id + ", user=" + user + ", description=" + description + ", destination=" + destination + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nombrePlace=" + nombrePlace + ", placeRestant=" + placeRestant + ", prix=" + prix + '}' + "\n";
    }
    
}



/**
*@Lau82 © 2018
*/
