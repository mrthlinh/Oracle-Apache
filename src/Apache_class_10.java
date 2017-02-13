
// ID = 3089109
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;


public class Apache_class_10 {

    public static final int CONNECTION_TIMEOUT=10000;
    public static final int SOCKET_TIMEOUT=15000;

    public String callPostWebService(String url,  String soapAction,   String envelope) throws Exception {
        final DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpParams params = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);

        HttpProtocolParams.setUseExpectContinue(httpClient.getParams(), true);

        // POST
        HttpPost httppost = new HttpPost(url);
        // add headers. set content type as XML
        httppost.setHeader("soapaction", soapAction);
        httppost.setHeader("Content-Type", "text/xml; charset=utf-8");

        String responseString=null;
        try {
            // the entity holds the request
            HttpEntity entity = new StringEntity(envelope);
            httppost.setEntity(entity);

            ResponseHandler<String> rh=new ResponseHandler<String>() {
                // invoked on response
                public String handleResponse(HttpResponse response)
                throws ClientProtocolException, IOException {
                    HttpEntity entity = response.getEntity();

                    StringBuffer out = new StringBuffer();
                                    // read the response as byte array
                    byte[] b = EntityUtils.toByteArray(entity);
                    // write the response byte array to a string buffer
                    out.append(new String(b, 0, b.length));        
                    return out.toString();
                }
            };
            responseString=httpClient.execute(httppost, rh); 
        } 
        catch (UnsupportedEncodingException uee) {
            throw new Exception(uee);

        }catch (ClientProtocolException cpe){

            throw new Exception(cpe);
        }catch (IOException ioe){
            throw new Exception(ioe);

        }finally{
            // close the connection
            httpClient.getConnectionManager().shutdown();
        }
        return responseString;
    }

}