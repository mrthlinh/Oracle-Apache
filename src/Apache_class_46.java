
//ID = 10986661
import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod; 


public class Apache_class_46{
public static void main (String []args)throws IOException{
    HttpClient client = new HttpClient();  
    client.getParams().setParameter("username", "SomeUSER");  
    client.getParams().setParameter("password", "GF@QT#$WE");  

    GetMethod method = new GetMethod("https://online.investools.com/authentication/auth.iedu");  
        try{  
              client.executeMethod(method);  
              Cookie[] cookies = client.getState().getCookies();  
              for (int i = 0; i < cookies.length; i++) {  
                Cookie cookie = cookies[i];  
                System.err.println(  
                  "Cookie: " + cookie.getName() +  
                  ", Value: " + cookie.getValue() +  
                  ", IsPersistent?: " + cookie.isPersistent() +  
                  ", Expiry Date: " + cookie.getExpiryDate() +  
                  ", Comment: " + cookie.getComment());  
                }  
              client.executeMethod(method);  
        } 
        catch(Exception e) {  
          System.err.println(e);  
        } 
        finally {  
          method.releaseConnection();  
        }
}
}
