
//ID = 10671494
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.protocol.HTTP;

public class Apache_class_42
{
    @SuppressWarnings("unused")
    public Apache_class_42()
    {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            String body="DataDataData";
            String bodyLength=new Integer(body.length()).toString();
            System.out.println(bodyLength);
//          StringEntity stringEntity=new StringEntity(body);

            URI uri=new URI("SOMEURL?Param1=1234&Param2=abcd");
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader("Test", "Test_Value");

//          httpPost.setEntity(stringEntity);

            StringEntity entity = new StringEntity(body, "text/xml",HTTP.DEFAULT_CONTENT_CHARSET);
            httpPost.setEntity(entity);

            RequestWrapper requestWrapper=new RequestWrapper(httpPost);
            requestWrapper.setMethod("POST");
            requestWrapper.setHeader("LuckyNumber", "77");
            requestWrapper.removeHeaders("Host");
            requestWrapper.setHeader("Host", "GOD_IS_A_DJ");
//          requestWrapper.setHeader("Content-Length",bodyLength);          
            HttpResponse response = httpclient.execute(requestWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}