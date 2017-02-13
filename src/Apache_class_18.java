
//ID = 5098348
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class Apache_class_18 {
	public void main() throws ParseException, IOException{
		DefaultHttpClient httpclient = new DefaultHttpClient();
	    try {
	        // try to get the home page
	        HttpGet httpget = new HttpGet("http://<host>/<root>/home.action");
	        HttpResponse httpClientResponse = httpclient.execute(httpget);
	        HttpEntity entity = httpClientResponse.getEntity();

	        // check status and close entity stream
	        EntityUtils.consume(entity);

	        // check cookies
	        System.out.println("Initial set of cookies:");
	        @SuppressWarnings("deprecation")
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
//	        printCookies(cookies);

	        /***  Login ***/
	        HttpPost httppost = new HttpPost("http://<host>/<root>/j_spring_security_check");

	        // Prepare post parameters
	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();

	        nvps.add(new BasicNameValuePair("j_username", "1"));
	        nvps.add(new BasicNameValuePair("j_password", "2"));
	        httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

	        httpClientResponse = httpclient.execute(httppost);

	        // copy response headers and determine redirect location
	        Header[] allHeaders = httpClientResponse.getAllHeaders();
	        System.out.println("Headers: ");
	        String location = "";
	        
	        for (Header header : allHeaders) {
	            System.out.println(header);
	            if("location".equalsIgnoreCase(header.getName())) location = header.getValue();
	            HttpServletResponse response = null;
				response.addHeader(header.getName(), header.getValue());
	        }

	        // check response body
	        entity = httpClientResponse.getEntity();
	        System.out.println("Response content: " + httpClientResponse.getStatusLine());
	        System.out.println(EntityUtils.toString(entity)); // always empty
	        EntityUtils.consume(entity);

	        // check cookies
	        System.out.println("Post logon cookies:");
	        cookies = httpclient.getCookieStore().getCookies();
//	        printCookies(cookies);

	        // populate redirect information in response
	        System.out.println("Redirecting to: " );
	        HttpServletResponse response = null;
	        response.setStatus(httpClientResponse.getStatusLine().getStatusCode()); // 302

	        // test if server-side get works for home page at this point (it does)
	        httpget = new HttpGet(location);
	        httpClientResponse = httpclient.execute(httpget);
	        entity = httpClientResponse.getEntity();

	        // print response body (all home content is loaded)
	        System.out.println("home get: " + httpClientResponse.getStatusLine());
	        System.out.println("Response content: " + httpClientResponse.getStatusLine());
	        System.out.println(EntityUtils.toString(entity));
	        EntityUtils.consume(entity);

	    } finally {
	        httpclient.getConnectionManager().shutdown();
	    }
	}
}
