

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

public class Apache_class_8 {
	
			public class HTTPrequestHelper {

			private final ResponseHandler<String> responseHandler;
			private final String CLASSTAG = HTTPrequestHelper.class.getSimpleName();
			private DefaultHttpClient client;
			public void main (){

			    HttpParams params = new BasicHttpParams();      
			      params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			      params.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, HTTP.UTF_8);
			      ///params.setParameter(CoreProtocolPNames.USER_AGENT, "Android-x");      
			      params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
			      params.setParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false);

			      SchemeRegistry schemeRegistry = new SchemeRegistry();
			      schemeRegistry.register(
			               new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

			      schemeRegistry.register(
			               new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));



			      ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);

			      client = new DefaultHttpClient(cm,params);   

			}



			public HTTPrequestHelper(ResponseHandler<String> responseHandler) {
			this.responseHandler = responseHandler;
			}

			public void performrequest(String url, String para)
			{

			    HttpPost post = new HttpPost(url);

			    StringEntity parameters;
			    try {

			        parameters = new StringEntity(para);

			        post.setEntity(parameters);
			    } catch (UnsupportedEncodingException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }

			    BasicHttpResponse errorResponse =
			        new BasicHttpResponse(
			        new ProtocolVersion("HTTP_ERROR", 1, 1),
			        500, "ERROR");

			    try {

			        client.execute(post, this.responseHandler);
			        }
			    catch (Exception e) {
			        errorResponse.setReasonPhrase(e.getMessage());
			    try {
			        this.responseHandler.handleResponse(errorResponse);
			        }
			    catch (Exception ex) {
//			             Log.e( "ouch", "!!! IOException " + ex.getMessage() );
			        }
			    }


			}
			}
}
