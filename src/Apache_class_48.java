
//ID = 10906747
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class Apache_class_48 {
	public void main(){
		try{
	        DefaultHttpClient httpclient = new DefaultHttpClient();

	        HttpGet httpget = new HttpGet("http://www.myspace.com/auth/form");

	        HttpResponse response = httpclient.execute(httpget);
	        HttpEntity entity = response.getEntity();

	        System.out.println("Login form get: " + response.getStatusLine());
	        if (entity != null) {
	            entity.consumeContent();
	        }
	        System.out.println("Initial set of cookies:");
	        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
	        if (cookies.isEmpty()) {
	            System.out.println("None");
	        } else {
	            for (int i = 0; i < cookies.size(); i++) {
	                System.out.println("- " + cookies.get(i).toString());
	            }
	        }

	        HttpPost httpost = new HttpPost("http://www.myspace.com/auth/form");

	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	        nvps.add(new BasicNameValuePair("email", "someEmail"));
	        nvps.add(new BasicNameValuePair("password", "somePassword"));

	        httpost.setEntity((HttpEntity) new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

	        response = httpclient.execute(httpost);
	        entity = response.getEntity();
	        //System.out.println("Double check we've got right page " + EntityUtils.toString(entity));

	        System.out.println("Login form get: " + response.getStatusLine());
	        if (entity != null) {
	            entity.consumeContent();
	        }

	        System.out.println("Post logon cookies:");
	        cookies = httpclient.getCookieStore().getCookies();
	        if (cookies.isEmpty()) {
	            System.out.println("None");
	        } else {
	            for (int i = 0; i < cookies.size(); i++) {
	                System.out.println("- " + cookies.get(i).toString());
	            }
	        }
	        httpclient.getConnectionManager().shutdown();
	    }
	    catch(Exception e){e.printStackTrace();}
	}
}
