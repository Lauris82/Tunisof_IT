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
public class AssociationEntity {
    
public int id_aasociation;
public int association_user	;
public String nom;
public String description;
public String num_tel;
public String gouvernorat;
public String municipalite;
public String nomrue;
public String coderue;
public float lat;
public float lng;

    public AssociationEntity() {
    }

    public AssociationEntity(int id_aasociation, int association_user, String nom, String description, String num_tel, String gouvernorat, String municipalite, String nomrue, String coderue, float lat, float lng) {
        this.id_aasociation = id_aasociation;
        this.association_user = association_user;
        this.nom = nom;
        this.description = description;
        this.num_tel = num_tel;
        this.gouvernorat = gouvernorat;
        this.municipalite = municipalite;
        this.nomrue = nomrue;
        this.coderue = coderue;
        this.lat = lat;
        this.lng = lng;
    }


        public AssociationEntity(int id_aasociation, int association_user, String nom, String description, String num_tel, String gouvernorat) {
        this.id_aasociation = id_aasociation;
        this.association_user = association_user;
        this.nom = nom;
        this.description = description;
        this.num_tel = num_tel;
        this.gouvernorat = gouvernorat;
      
    }
        
        
        public AssociationEntity(String nom) {
    this.nom = nom;
    }

    public int getId_aasociation() {
        return id_aasociation;
    }

    public void setId_aasociation(int id_aasociation) {
        this.id_aasociation = id_aasociation;
    }

    public int getAssociation_user() {
        return association_user;
    }

    public void setAssociation_user(int association_user) {
        this.association_user = association_user;
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

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
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

        
        
        
    
    
}
