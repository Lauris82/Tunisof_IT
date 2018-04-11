/**
* @Project: AllForKids
* @Classe: FacebookService
* @Date: 7 avr. 2018
* @Time: 20:06:29
*
* @author Lauris
*/


package allforkids.services;

import allforkids.entities.Facebook_User;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import java.util.Arrays;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class FacebookService {

    //String appSecret = "3b9024816e8bd626955d268a48c1ecbb";
    String client_id = "568364336875744";
    String domain = "https://github.com/Lauris82";
    String accessToken = "EAAIE7KJnCOABADBTUoYpSx6XA5E1MqnwKyOZAobdMiiTcsW6JAOeJki8XZAZBEgyhaIJ9ZArmTFdO4dSyWIyqZAY9NmFml4AEQbENhZABFMSrx9NCG2rzgZCkRffXf3wur0UNZBuLnhk1mnuyYvMZBBwyKctlXv5sDoqgLxTuGHc8ZCwZDZD";

    String authUrl = "https://www.facebook.com/v2.12/dialog/oauth?client_id="+client_id+
            "&redirect_uri="+domain+"&state=st=state82allfor,kids=123456789"+
            "&scope=email,user_birthday,user_photos,user_hometown,user_location";
        
    public int isAccessToken = 0;

    public int getIsAccessToken() {
        return isAccessToken;
    }
    
    

    public void connectionFacebook(){
        
        System.setProperty("webdirver.gecko.driver", "geckodriver.exe");
        
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(380, 40));
        driver.manage().window().setSize(new Dimension(830, 740));
        
        driver.get(authUrl);
        
        while(true){
            if(!driver.getCurrentUrl().contains("facebook.com")){
//                String url = driver.getCurrentUrl();
//                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                
                isAccessToken = 1;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    System.err.println("Probleme");
                }
                driver.close();
            }
        }
    }
    
    public Facebook_User get_Profile_Info(String accessToken){
        
        Facebook_User fb_User = new Facebook_User();
        
        try {
            FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_6);

            JsonObject fetchObjectsResults = fbClient.fetchObjects(Arrays.asList("me"),
                    JsonObject.class, Parameter.with("fields","id,name,email,picture"));

            String temp = fetchObjectsResults.toString();

            fb_User.setId(temp.substring(temp.indexOf("\"id\":\"")+6, temp.indexOf("\",\"name\":\"")));
            fb_User.setName(temp.substring(temp.indexOf(",\"name\":\"")+9, temp.indexOf("\",\"email\"")));
            fb_User.setEmail(temp.substring(temp.indexOf(",\"email\":\"")+10, temp.indexOf("\",\"picture\":")));
            fb_User.setPicture(temp.substring(temp.indexOf("\"url\":\"")+7, temp.indexOf("\",\"width\"")));
        
        } catch (Exception e) {
            System.err.println("Pas possible");
        }
        
        return fb_User;
    }

    public String getAccessToken() {
        return accessToken;
    }


}




/**
*@Lau82 © 2018
*/
