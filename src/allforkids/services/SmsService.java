/**
* @Project: AllForKids
* @Classe: SmsService
* @Date: 7 avr. 2018
* @Time: 12:37:24
*
* @author Lauris
*/


package allforkids.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;




public class SmsService {
    
    public static final String ACCOUNT_SID = "AC0f227d9c4a9a228d8a2430a7767f4aed";
    public static final String AUTH_TOKEN = "0e33992e66248d1659550148f8f1f37c";
  
    
    public void createSMS(long number, String sujet){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
        Message message = Message.creator(new PhoneNumber("+216"+number), new PhoneNumber("(928) 325-2398"), sujet).create();
        
        System.out.println(message.getSid());
    }

}



/**
*@Lau82 © 2018
*/
