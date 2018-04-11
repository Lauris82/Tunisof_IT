/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.services;
import com.restfb.BinaryAttachment;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import net.bytebuddy.dynamic.TargetType;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 *
 * @author ASUS
 */
public class partage {
     public void partager(String nom,String description , String dateD,String Datef,String image){
    

        
          String domain="https://www.google.fr/";
          //domain="https://google.fr/";
         String appId="349160732241016";
         String appSecret="db068ddd2fc20481f4bdb6775f581a71";
         String authURL="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain
                 +"&scope=ads_management,publish_actions";
         
//         System.setProperty("webdriver.chrome.driver", "api/chromedriver_win32/chromedriver.exe");
//         WebDriver driver= new ChromeDriver();
        System.setProperty("webdirver.gecko.driver", "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        
        
         driver.get(authURL);
         String accessToken="EAAE9j1FqLHgBAOOaBooHARMAwdVOUz890Cmy3rtRmN3XS6vj2yMSRUqGImCdY5unX8VoMBP9g8YZAhA7Vt98Rxd6rsAcGI0cZAavKupTZB9hFzP2fMg8BnIl940GDyWmywCfIGIKEZCdKkZCyAxfG0pZBHJD0DDDmADOB9s8pOpQZDZD" ;
         
         boolean ok=true;
         while(ok)
         {
             if ( (! driver.getCurrentUrl().contains("facebook.com")) && (driver.getCurrentUrl()!=authURL) )
             {
                 String url =driver.getCurrentUrl();
                // accessToken =url.replaceAll(".*#access_token=(.+)&.*", "$1");
                 System.out.println(accessToken);
                
                 ok=false;
              }
             
         }
         
         System.out.println("act:"+accessToken);
         driver.quit();
         FacebookClient fbClient = new DefaultFacebookClient(accessToken);
              User me = fbClient.fetchObject("me", User.class);
              System.out.println(me.getUsername());
              FacebookType publishPhotoResponse = fbClient.publish("me/photos", FacebookType.class,
  BinaryAttachment.with(image, getClass().getResourceAsStream("/imageEvenement/"+image)),
  Parameter.with("message", "Evenement"+" "+ nom+" "+"a"+" "+description+" "+"Du"+" "+dateD+" "+"jusqua "+" "+Datef));
//            
//              FacebookType publishMessageResponse =
//            fbClient.publish("me/feed", FacebookType.class,
//                    com.restfb.Parameter.with("message","Evenement"));
//          
//
//              System.out.println("Published message ID: " + publishMessageResponse.getId());

    /**
     *
     */
    
        
    
            
    }
}
