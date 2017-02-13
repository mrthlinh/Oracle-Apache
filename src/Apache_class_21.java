
//ID = 5898154
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class Apache_class_21 {
	private String authenticate()  throws Exception
    {
        // Create a new HttpClient and Post Header
           DefaultHttpClient httpclient = new DefaultHttpClient();
           HttpPost httppost = new HttpPost("http://mySite/login_form");

           HttpResponse response = null;
           BufferedReader in = null;
           String resultContent = null;

           try
           {
               // Add data
               List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
               nameValuePairs.add(new BasicNameValuePair("came_from", ""));
               nameValuePairs.add(new BasicNameValuePair("form.submitted", "1"));
               nameValuePairs.add(new BasicNameValuePair("js_enabled", "0"));
               nameValuePairs.add(new BasicNameValuePair("cookies_enabled", ""));
               nameValuePairs.add(new BasicNameValuePair("login_name", ""));
               nameValuePairs.add(new BasicNameValuePair("pwd_empty", "0"));
               nameValuePairs.add(new BasicNameValuePair("name", "username"));
               nameValuePairs.add(new BasicNameValuePair("password", "password"));

               httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Create a local instance of cookie store
                CookieStore cookieStore = new BasicCookieStore();
                // Create local HTTP context
                HttpContext localContext = new BasicHttpContext();
                // Bind custom cookie store to the local context
                localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
               // Execute HTTP Post Request
               response = httpclient.execute(httppost,localContext);

               in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
               StringBuffer sb = new StringBuffer("");
               String line = "";
               String NL = System.getProperty("line.separator");
               while ((line = in.readLine()) != null)
               {
                 sb.append(line + NL);
               }
               in.close();
               resultContent = sb.toString();
//               Log.i("mytag","result :"+resultContent);
//
//               cookies = new java.util.ArrayList();
//               cookies = cookieStore.getCookies();

           }
           catch (ClientProtocolException e)
           {
//               Log.i("mytag","Client protocol exception");
           }
           catch (IOException e)
           {
//               Log.i("mytag","IOException");
           }
           catch(Exception e)
           {
//               Log.i("mytag","Exception");
//               Log.i("mytag",e.toString());
           }


        return resultContent;

    }
}
