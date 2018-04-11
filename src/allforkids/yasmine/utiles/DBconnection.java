/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yasmine.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class DBconnection {
    //declaration de la vble d'instance
    public static DBconnection instance;
    public Connection cnx;
    
    
    //rendre le constructeur private
     private DBconnection () {
        String Url = "jdbc:mysql://localhost:3306/allforkids";
        String login = "root";
        String pwd = "";

        try {

            cnx = DriverManager.getConnection(Url, login, pwd);
            System.out.println("Connection de BD établie!!!!!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
     
     //creation de la methode get instance
     public static DBconnection getInstance(){
     
         if (instance==null){
             instance=new DBconnection ();
         }
         return instance;
         
     }
     
     public Connection getConnection(){
     return cnx;
     }
    
}
