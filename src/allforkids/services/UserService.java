/**
* @Project: AllForKids
* @Classe: UserServce
* @Date: 14 mars 2018
* @Time: 00:02:42
*
* @author Lauris
*/


package allforkids.services;

import Entites.BCrypt;
import allforkids.entities.User;
import allforkids.utils.MyConnexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserService {
    
    Connection cn = MyConnexion.getInstance().getConnection();
    
    public static int id;
    private static int workload = 12;
    private byte image;
    
    public int getId(){
        return id;
    }

    public byte getImage() {
        return image;
    }

    public void setImage(byte image) {
        this.image = image;
    }
    
    
    public int login(String username, String password) {
        try {
            String requete = "SELECT * FROM `user` where `username`=? and enabled=1";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, username);
            ResultSet res = st.executeQuery();
            
            if(res.last()){
                if(checkPassword(password, res.getString("password"))){
                    id = res.getInt("id");
                    return 1;
                }
            }
            else{
                return 0;
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
        return 0;
    }
    
    
    public Boolean register(User user) throws SQLException, FileNotFoundException{

        String requete = "INSERT INTO user (username,username_canonical,email,email_canonical,enabled,password,roles,nom,prenom,date_naissance,adresse,contact,image) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st = cn.prepareStatement(requete);

        st.setString(1, user.getUsername());
        st.setString(2, user.getUsername());
        st.setString(3, user.getEmail());
        st.setString(4, user.getEmail());
        st.setString(5, "1");
        st.setString(6, user.getPassword());
        st.setString(7, user.getRoles());
        st.setString(8, user.getNom());
        st.setString(9, user.getPrenom());
        st.setString(10, String.valueOf(user.getDateNaissance()));
        st.setString(11, user.getAdresse());
        st.setString(12, String.valueOf(user.getContact()));
        st.setString(13, user.getImage());
        
        return st.executeUpdate() == 1;
    }
    
    
    public void updateUser(User user, int id) throws SQLException, FileNotFoundException{

        String requete = "UPDATE user SET username=?, username_canonical=?, nom=?, prenom=?, date_naissance=?, adresse=?, contact=?, image=? WHERE id=?";
                
        PreparedStatement st = cn.prepareStatement(requete);

        st.setString(1, user.getUsername());
        st.setString(2, user.getUsername());
        st.setString(3, user.getNom());
        st.setString(4, user.getPrenom());
        st.setString(5, String.valueOf(user.getDateNaissance()));
        st.setString(6, user.getAdresse());
        st.setString(7, String.valueOf(user.getContact()));
        st.setString(8, user.getImage());

        st.setString(9, String.valueOf(id));

        st.executeUpdate();
        
        System.out.println("User modifié");
    }
    
    
    public void changePassword(String password, int id) throws SQLException, FileNotFoundException{

        String requete = "UPDATE user SET password=? WHERE id=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, password);
        st.setString(2, String.valueOf(id));

        st.executeUpdate();
        
        System.out.println("Password modifié");
    }
    
    
    public void setNotificationValue(int id) throws SQLException, FileNotFoundException{

        String requete = "UPDATE user SET notification=? WHERE id=?";
        PreparedStatement st = cn.prepareStatement(requete);

        st.setString(1, String.valueOf(0));
        st.setString(2, String.valueOf(id));
        st.executeUpdate();
        
        System.out.println("Notification Modifiée");
    }
    
    
    public User getUser(int id) throws SQLException, FileNotFoundException, IOException {
        User user = new User();

        String requete = "select * from `user` where `id`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, String.valueOf(id));
        ResultSet res = st.executeQuery();

        while(res.next()){
            user.setId(res.getInt("id"));
            user.setUsername(res.getString("username"));
            user.setPassword(res.getString("password"));
            user.setNom(res.getString("nom"));
            user.setPrenom(res.getString("prenom"));
            user.setRoles(res.getString("roles"));
            user.setEmail(res.getString("email"));
            user.setAdresse(res.getString("adresse"));
            user.setDateNaissance(res.getDate("date_naissance").toLocalDate());
            user.setImage(res.getString("image"));
            user.setContact(res.getLong("contact"));
            user.setNotification(res.getInt("notification"));
        }

        return user;
    }
    
    
    public User getUserByEmail(String email) throws SQLException, FileNotFoundException, IOException {
        User user = new User();

        String requete = "select * from `user` where `email`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, email);
        ResultSet res = st.executeQuery();

        while(res.next()){
            user.setId(res.getInt("id"));
            user.setUsername(res.getString("username"));
            user.setPassword(res.getString("password"));
            user.setNom(res.getString("nom"));
            user.setPrenom(res.getString("prenom"));
            user.setRoles(res.getString("roles"));
            user.setEmail(res.getString("email"));
            user.setAdresse(res.getString("adresse"));
            user.setDateNaissance(res.getDate("date_naissance").toLocalDate());
            user.setImage(res.getString("image"));
            user.setContact(res.getLong("contact"));
            user.setNotification(res.getInt("notification"));
        }

        return user;
    }
   public int emailExiste(String email) {
        try {
            String requete = "SELECT * FROM `user` where `email`=?";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, email);
            ResultSet res = st.executeQuery();
            
            if(res.last()){
                return 1;
            }
            else{
                return 0;
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int verifierUsername(String username) throws SQLException {
        String requete = "SELECT * FROM `user` where `username`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, username);
        ResultSet res = st.executeQuery();
        
        if(res.last()){
            return 1;
        }
        else{ //no user with this username
            return 0;
        }
    }

    public int verifierEmail(String mail) throws SQLException {
        String requete = "SELECT * FROM `user` where `email`=?";
        PreparedStatement st = cn.prepareStatement(requete);
        st.setString(1, mail);
        ResultSet res = st.executeQuery();
        
        if(res.last()){
            return 1;
        }
        else{ //no user with this email
            return 0;
        }
    }

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        System.out.println(salt);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
        System.out.println(password_verified);

        return (password_verified);
    }

}




/**
*@Lau82 © 2018
*/
