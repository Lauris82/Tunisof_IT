/**
* @Project: AllForKids
* @Classe: AllForKids
* @Date: 13 mars 2018
* @Time: 19:16:32
*
* @author Lauris
*/


package allforkids;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class AllForKids extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        
//        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/allforkids/ressources/images/logo1.png")));
        stage.setTitle("AllForKids");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}



/**
*@Lau82 © 2018
*/