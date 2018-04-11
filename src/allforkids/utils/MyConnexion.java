/**
* @Project: AllForKids
* @Classe: MyConnexion
* @Date: 13 mars 2018
* @Time: 23:20:12
*
* @author Lauris
*/


package allforkids.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnexion {
    
    public Connection cnx;
    static MyConnexion instance;

    private MyConnexion() {

        try {
            String url = "jdbc:mysql://localhost:3306/allforkids";
            String login = "root";
            String pwd = "";

            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return cnx;
    }

    public static MyConnexion getInstance() {

        if (instance == null) {
            instance = new MyConnexion();
        }
        return instance;
    }

    public static void main(String[] args) {
    }
    
}



/**
*@Lau82 © 2018
*/
