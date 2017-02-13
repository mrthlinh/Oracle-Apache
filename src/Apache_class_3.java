
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class Apache_class_3 {
	public static void a() throws ClientProtocolException, IOException {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		HttpClient httpclient = new DefaultHttpClient();
//		Parameter.add(new BasicNameValuePair("format", "json"));
//		params.add(new BasicNameValuePair("foo", bar));

		HttpPost httppost = new HttpPost();
		// this is how you set the body of the POST request
//		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		String responseBody = "";
		try {
		    // Create a response handler
		    ResponseHandler<String> responseHandler = new BasicResponseHandler();
		    responseBody = httpclient.execute(httppost, responseHandler);
		} catch(HttpResponseException e) {
		    String error = "unknown error";
		    if (e.getStatusCode() == 400) {
		        HttpUriRequest request = null;
				// TODO responseBody and e.detailMessage are null here, 
		        // even though packet sniffing may reveal a response like
		        // Transfer-Encoding: chunked
		        // Content-Type: application/json
		        //
		        // 42
		        // {"error": "You do not have permissions for this operation."}
		        HttpResponse response = httpclient.execute(request);
		        int statusCode = response.getStatusLine().getStatusCode();
		        HttpEntity entity = response.getEntity();
//		        responseBody = entity.getContent();

		        if (statusCode != 200) {
		            // responseBody will have the error response
		        }
		        }
		    // e.getMessage() is ""
		}
	}
}
