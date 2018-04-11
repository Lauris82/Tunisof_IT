/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.entities;

/**
 *
 * @author DELL
 */
public class ClubEntity {
    
public int idclub;
public int club_user;
public String nom;
public String description;
public String numTel;
public String gouvernorat;
public String municipalite;
public String nomrue;
public String coderue;
public float lat;
public float lng;

    public ClubEntity(String nom) {
    this.nom = nom;
    }

//    public ClubEntity(String nom,String string, String string0, String string1) {
//        this.nom = nom;
//        this.description = string;
//        this.numTel = string0;
//        this.gouvernorat = string1;
//    }
//    
//    
//        public ClubEntity(int idclub1, String nom1, String description1, String numTel1, String gouvernorat1) {
//      
//        this.idclub = idclub1;
//        this.nom = nom1;
//        this.description = description1;
//        this.numTel = numTel1;
//        this.gouvernorat = gouvernorat1;
//      
//    }
    
             public ClubEntity(int idclub1,int club_user, String nom, String description, String numTel, String gouvernorat) {
        this.idclub = idclub1;
        this.club_user = club_user;
        this.nom = nom;
        this.description = description;
        this.numTel = numTel;
        this.gouvernorat = gouvernorat;
      
    }
             
             
        
    

    public int getIdclub() {
        return idclub;
    }

//    public void setIdclub(int idclub) {
//        this.idclub = idclub;
//    }

    public int getClub_user() {
        return club_user;
    }

    public void setClub_user(int club_user) {
        this.club_user = club_user;
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

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getMunicipalite() {
        return municipalite;
    }

    public void setMunicipalite(String municipalite) {
        this.municipalite = municipalite;
    }

    public String getNomrue() {
        return nomrue;
    }

    public void setNomrue(String nomrue) {
        this.nomrue = nomrue;
    }

    public String getCoderue() {
        return coderue;
    }

    public void setCoderue(String coderue) {
        this.coderue = coderue;
    }

    public void setIdclub(int idclub) {
        this.idclub = idclub;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

     public ClubEntity() {
    }
    
    public ClubEntity(int idclub, int club_user, String nom, String description, String numTel, String gouvernorat, String municipalite, String nomrue, String coderue, float lat, float lng) {
        this.idclub = idclub;
        this.club_user = club_user;
        this.nom = nom;
        this.description = description;
        this.numTel = numTel;
        this.gouvernorat = gouvernorat;
        this.municipalite = municipalite;
        this.nomrue = nomrue;
        this.coderue = coderue;
        this.lat = lat;
        this.lng = lng;
    }

 
      
    
    
    
    @Override
    public String toString() {
        return "ClubEntity{" + "idclub=" + idclub + ", club_user=" + club_user + ", nom=" + nom + ", description=" + description + ", numTel=" + numTel + ", gouvernorat=" + gouvernorat + ", municipalite=" + municipalite + ", nomrue=" + nomrue + ", coderue=" + coderue + ", lat=" + lat + ", lng=" + lng + '}';
    }

   
    
    




}
