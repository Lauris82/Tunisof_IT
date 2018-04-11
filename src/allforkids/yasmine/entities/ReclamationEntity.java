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
public class ReclamationEntity {
 public int idRec;
 public String objetRec;
 public String typeRec;
 public int etatRec;
 public String contenuRec;
 public int user;
 public String membreRec;
 public String mail;

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getObjetRec() {
        return objetRec;
    }

    public void setObjetRec(String objetRec) {
        this.objetRec = objetRec;
    }

    public String getTypeRec() {
        return typeRec;
    }

    public void setTypeRec(String typeRec) {
        this.typeRec = typeRec;
    }

    public int getEtatRec() {
        return etatRec;
    }

    public void setEtatRec(int etatRec) {
        this.etatRec = etatRec;
    }

    public String getContenuRec() {
        return contenuRec;
    }

    public void setContenuRec(String contenuRec) {
        this.contenuRec = contenuRec;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getMembreRec() {
        return membreRec;
    }

    public void setMembreRec(String membreRec) {
        this.membreRec = membreRec;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ReclamationEntity() {
    }

    public ReclamationEntity(int idRec, String objetRec, String typeRec, int etatRec, String contenuRec, int user, String membreRec, String mail) {
        this.idRec = idRec;
        this.objetRec = objetRec;
        this.typeRec = typeRec;
        this.etatRec = etatRec;
        this.contenuRec = contenuRec;
        this.user = user;
        this.membreRec = membreRec;
        this.mail = mail;
    
    }
   
 
 
 
    
}
