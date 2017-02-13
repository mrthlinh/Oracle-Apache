
//ID = 7362719
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * @author Kevin Kowalewski
 *
 */
public class Apache_class_29 {

	public class DownloadClient {
	DefaultHttpClient httpClient;
	HttpGet httpGet;
	HttpResponse httpResponse;
	HttpEntity httpEntity;
	InputStream httpInputStream;

	private final String LOG_TAG = DownloadClient.class.getName();

	public DownloadClient(){
	    httpClient = new DefaultHttpClient();
	    httpGet = new HttpGet("http://cachefly.cachefly.net/100mb.test");
	    for (Header header : httpGet.getAllHeaders()){
	        Log.d(LOG_TAG, "--> Header: " + header);
	    }

	    Log.d(LOG_TAG, "--> Header size is: " + httpGet.getAllHeaders().length);
	    try {
	        httpResponse = httpClient.execute(httpGet);
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    httpEntity = httpResponse.getEntity();
	    try {
	        httpInputStream = httpEntity.getContent();
	    } catch (IllegalStateException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    Log.d(LOG_TAG, "--> StatusLine: " + httpResponse.getStatusLine());
	}

	public void read(){
	    byte[] readBuffer = new byte[32];
	    try {
	        while (httpInputStream.read(readBuffer) != -1){
	            try{Thread.sleep(100);}catch(Exception e){}
	            //Log.d(LOG_TAG,"--> Read Bytes: " + new String(readBuffer));
	        };
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

	public void shutdown(){
	    httpGet.abort();
	    httpClient.getConnectionManager().shutdown();
	}
}
}
