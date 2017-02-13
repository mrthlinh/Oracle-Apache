
//ID = 6473024
import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Apache_class_24 {
	public static void main() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String proxyHost = "192.168.4.10";
		Integer proxyPort = 8080;

		HttpHost targetHost = new HttpHost("noaasis.noaa.gov", 80, "http");
		HttpGet httpget = new HttpGet("/ptbus/ptbus167");

		try {

		    HttpHost proxy = new HttpHost(proxyHost, proxyPort);
		    httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

		    System.out.println("executing request: " + httpget.getRequestLine());
		    System.out.println("via proxy: " + proxy);
		    System.out.println("to target: " + targetHost);

		    HttpResponse response = httpclient.execute(targetHost, httpget);
		    HttpEntity entity = response.getEntity();

		    System.out.println("----------------------------------------");
		    System.out.println(response.getStatusLine());
		    Header[] headers = response.getAllHeaders();
		    for (int i = 0; i<headers.length; i++) {
		        System.out.println(headers[i]);
		    }

		    System.out.println("----------------------------------------");
		    System.out.println(response.getStatusLine());
		    if (entity != null) {
		        System.out.println("Response content length: " + entity.getContentLength());
		    }
		    EntityUtils.consume(entity);

		}
		catch (IOException ex) {

		}
		finally {
		    // When HttpClient instance is no longer needed,
		    // shut down the connection manager to ensure
		    // immediate deallocation of all system resources
		    httpclient.getConnectionManager().shutdown();
		}
    }
}
