
//ID = 9715364
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class Apache_class_37 {
	public void main() throws ClientProtocolException, IOException{
		HttpParams params = new BasicHttpParams();

        // Set the timeout in milliseconds until a connection is established.
        // The default value is zero, that means the timeout is not used. 
        int timeoutConnection = 60000;
        HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);

        params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
                HttpVersion.HTTP_1_1);

        // Set the default socket timeout (SO_TIMEOUT) 
        // in milliseconds which is the timeout for waiting for data.
        int timeoutSocket = 60000;
        HttpConnectionParams.setSoTimeout(params, timeoutSocket);

        DefaultHttpClient httpclient = new DefaultHttpClient(params);
HttpUriRequest httppost = null;
CloseableHttpResponse response = httpclient.execute(httppost);
    byte[] buffer = new byte[1024];
    int bufferLength = 0; 
    InputStream inputStream = null;
    int totalSize = 0;
    while ((bufferLength = inputStream.read(buffer)) > 0) {
        totalSize += bufferLength;
//      onProgressTitleUpdate("Calculating size...");
    }
	}
}
