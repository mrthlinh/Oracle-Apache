
//ID = 6025567
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

public class Apache_class_22 {
	public static void main(String[] args) throws Exception {
	    DefaultHttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet("https://campus.chipfalls.k12.wi.us/campus/portal/chippewa_falls.jsp");
	    httpget.addHeader("Referer", "http://cfsd.chipfalls.k12.wi.us//high/");
	    HttpResponse response = httpclient.execute(httpget);
	    HttpEntity entity = response.getEntity();

	    System.out.println("Login form get: " + response.getStatusLine());
	    if (entity != null) {
	        InputStream input = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        String ln = "";
	        while((ln = reader.readLine()) != null) {
	            System.out.println("During Get - " + ln);
	        }
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
	}
}
