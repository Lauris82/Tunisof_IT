/**
* @Project: AllForKids
* @Classe: TestReservationOffreTransport
* @Date: 15 mars 2018
* @Time: 01:29:57
*
* @author Lauris
*/


package allforkids.Tests;

import allforkids.entities.OffreTransport;
import allforkids.entities.ReservationOffreTransport;
import allforkids.services.NotificationService;
import allforkids.services.OffreTransportService;
import allforkids.services.ReservationOffreTransportService;
import allforkids.services.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestReservationOffreTransport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
        
//        int year = 2003;
//        int month = 12;
//        int day = 12;
//
//        String date = year + "/" + month + "/" + day;
//        java.util.Date utilDate = new java.util.Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        utilDate = formatter.parse(date);
//        
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//
////        OffreTransport o1 = new OffreTransport(3, "JDBC", "Esprit", sqlDate, sqlDate, 5, 4, 15.0);
//        OffreTransport o = new OffreTransport("JDBC", "Esprit", sqlDate, sqlDate, 5, 4, 15.0);
//
//        try {
//            ReservationOffreTransportService rots = new ReservationOffreTransportService();
//            UserService uss = new UserService();
//            
//            uss.login("yassine", "yassine");
//            
//            OffreTransport offre = new OffreTransport();
//            offre.setId(3);
//            rots.reserverOffreTransport(offre);
//            
////            ReservationOffreTransport rot = new ReservationOffreTransport(3, 3);
////            rot.setId(4);
////            rots.annulerReservation(rot);
//            
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
////        try {
////            System.out.println(rots.dejaReserver(3, 1));
////        } catch (SQLException ex) {
////            System.err.println(ex.getMessage());
////        }

//
//        OffreTransportService ots = new OffreTransportService();
//        List<OffreTransport> myList = ots.listerOffreTransport();
//        
//            System.out.println(myList.toString());

            NotificationService nots = new NotificationService();
            nots.createNotification(3, 7, "ok");
        
    }

}



/**
*@Lau82 © 2018
*/
