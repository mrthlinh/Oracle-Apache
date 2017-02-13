
//ID = 10866697
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

public class Apache_class_45 {
	public static String sendToIP(String IP, List <NameValuePair> nvpss) throws ClientProtocolException, IOException
	{
	    DefaultHttpClient httpclient = new DefaultHttpClient();

	    HttpPost httpost = new HttpPost(IP);
	    List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	if(nvpss!=null)
	{ nvps=nvpss;}

	    httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
	    HttpResponse response = httpclient.execute(httpost);
	    String finalReturn = (inputStreamToString(response.getEntity().getContent()).toString());



	    // When HttpClient instance is no longer needed, 
	    // shut down the connection manager to ensure
	    // immediate deallocation of all system resources
	    httpclient.getConnectionManager().shutdown();
	    return finalReturn;

	}


	    private static StringBuilder inputStreamToString(InputStream is) {

	        String line = "";
	        StringBuilder total = new StringBuilder();

	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

	        try {
	            while ((line = rd.readLine()) != null) {
	                total.append(line);
	            }
	        } catch (Exception e) {
	        }

	        return total;

	    }
}
