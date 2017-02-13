
//ID = 10881047
import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class Apache_class_47 {

	    public static void main(String[] args) throws Exception {

	        URIBuilder builder = new URIBuilder();
	        builder.setScheme("http").setHost("api.stackexchange.com").setPath("/2.0/search")
	            .setParameter("site", "stackoverflow")
	            .setParameter("intitle" ,"workaround")
	            .setParameter("tagged","javascript");
	        URI uri = builder.build();

	         HttpClient httpclient = new DefaultHttpClient();
	         try {
	             HttpGet httpget1 = new HttpGet(uri);

	             System.out.println("executing request " + httpget1.getURI());
	             // Create a response handler
	             ResponseHandler<String> responseHandler = new BasicResponseHandler();
	             String responseBody = httpclient.execute(httpget1, responseHandler);
	             System.out.println("----------------------------------------");
	             System.out.println(responseBody);
	             System.out.println("----------------------------------------");

	         } finally {
	             httpclient.getConnectionManager().shutdown();
	         }
	    }
}
