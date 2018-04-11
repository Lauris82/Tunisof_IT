/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class myConnexion {
    static myConnexion instance ;
    public Connection cnx ;
    private myConnexion(){
        
        try{
            
            String url="jdbc:mysql://localhost:3306/allforkids";
            String login="root";
            String pwd="";
            cnx=DriverManager.getConnection(url,login,pwd);
            System.out.println("connection ètablie");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
    public static myConnexion getInstance(){
        
        if(instance==null){
            instance=new myConnexion();
        }
        return instance;
        
        
    }
    public Connection getConnection(){
        return cnx ;
    }
}
