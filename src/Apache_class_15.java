
//ID = 4017138
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Apache_class_15 {
	public void main(){
		String url = "http://rads.stackoverflow.com/amzn/click/05961580";
		 GetMethod get = new GetMethod(url);
		 HttpMethodRetryHandler httpHandler = new DefaultHttpMethodRetryHandler(1, false);
		 get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, httpHandler );
		 get.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		 HttpConnectionManager connectionManager = new SimpleHttpConnectionManager();
		 HttpClient client = new HttpClient( connectionManager );
		 client.getParams().setParameter("http.useragent", "FIREFOX");
		 String line;
		 StringBuilder stringBuilder = new StringBuilder();
		 String toStreamBody = null;
		 String toStringBody = null;
		 try {
		  int statusCode = client.executeMethod(get);
		  if( statusCode != HttpStatus.SC_OK ){
		   System.err.println("Internet Status: " + HttpStatus.getStatusText(statusCode) );
		   System.err.println("While getting page: " + url );
		  }
		 //toString
		  toStringBody = get.getResponseBodyAsString();
		 //toStream
		  InputStreamReader isr = new InputStreamReader(get.getResponseBodyAsStream());
		  BufferedReader rd = new BufferedReader(isr);
		  while ((line = rd.readLine()) != null) {
		  stringBuilder.append(line);
		  }
		 } catch (java.io.IOException ex) {
		  System.out.println( "Failed to get page: " + url);
		 } finally {
		  get.releaseConnection();
		 }       
		 toStreamBody = stringBuilder.toString();
	}
}
