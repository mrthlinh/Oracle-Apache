
//ID = 5027309
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class Apache_class_16 {

        public static void main(String[] args) throws ClientProtocolException, IOException {
        HttpGet request = new HttpGet("http://tool.keepmeapi.com/echo");

        HttpContext HTTP_CONTEXT = new BasicHttpContext();
        HTTP_CONTEXT.setAttribute(CoreProtocolPNames.USER_AGENT, "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
        request.setHeader("Referer", "http://www.google.com");

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request, HTTP_CONTEXT);

        if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() >= 400) {
            throw new IOException("Got bad response, error code = " + response.getStatusLine().getStatusCode());
        }

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            System.out.println(EntityUtils.toString(entity));
        }
    }

}