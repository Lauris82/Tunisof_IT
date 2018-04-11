/**
* @Project: AllForKids
* @Classe: TestOffreTransport
* @Date: 14 mars 2018
* @Time: 20:53:02
*
* @author Lauris
*/


package allforkids.Tests;

import allforkids.entities.OffreTransport;
import allforkids.services.OffreTransportService;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TestOffreTransport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, SQLException {
        
        int year = 2003;
        int month = 12;
        int day = 12;

        String date = year + "/" + month + "/" + day;
        java.util.Date utilDate = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        utilDate = formatter.parse(date);
        
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        OffreTransportService ots = new OffreTransportService();

//        OffreTransport o1 = new OffreTransport(3, "JDBC", "Esprit", sqlDate, sqlDate, 5, 4, 15.0);
//        OffreTransport o = new OffreTransport("JDBC", "Esprit", sqlDate, sqlDate, 5, 4, 15.0);

//        ots.addOffreTransport(o);
//        ots.deleteOffreTransport(4);
//        ots.updateOffreTransport(o, 3);
//        System.out.println(ots.listerOffreTransport());
        OffreTransport Osearch = new OffreTransport();
//        Osearch = ots.searchOffreTransport("oo");
        System.out.println(ots.searchOffreTransport("Esprit"));
       
    }

}



/**
*@Lau82 © 2018
*/
