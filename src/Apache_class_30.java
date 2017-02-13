

import java.io.BufferedReader;
//ID = 8090449
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class Apache_class_30{
	public class EasySSLSocketFactory implements SchemeSocketFactory
	{
	     private SSLContext sslcontext = null;

	     private SSLContext createEasySSLContext() throws IOException {
	             try {
	                     SSLContext context = SSLContext.getInstance("TLS");
	                     return context;
	             } catch (Exception e) {
	                     throw new IOException(e.getMessage());
	             }
	     }


	    private SSLContext getSSLContext() throws IOException {
	             if (this.sslcontext == null) {
	                     this.sslcontext = createEasySSLContext();
	             }
	             return this.sslcontext;
	     }

	     /**
	      * @see org.apache.http.conn.scheme.SchemeSocketFactory#isSecure(java.net.Socket)
	      */
	     public boolean isSecure(Socket socket) throws IllegalArgumentException {
	             return true;
	     }

	     // -------------------------------------------------------------------
	     // javadoc in org.apache.http.conn.scheme.SocketFactory says :
	     // Both Object.equals() and Object.hashCode() must be overridden
	     // for the correct operation of some connection managers
	     // -------------------------------------------------------------------

	     public boolean equals(Object obj) {
	             return ((obj != null) && obj.getClass().equals(
	                             EasySSLSocketFactory.class));
	     }

	     public int hashCode() {
	             return EasySSLSocketFactory.class.hashCode();
	     }

	    //this method is modified
	    @Override
	    public Socket connectSocket(Socket sock, InetSocketAddress remoteAddress,
	            InetSocketAddress localAddress, HttpParams params) throws IOException,
	            UnknownHostException, ConnectTimeoutException {

	        int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
	        int soTimeout = HttpConnectionParams.getSoTimeout(params);
	        SSLSocket sslsock = (SSLSocket) ((sock != null) ? sock : createSocket(params));
	        if (localAddress != null) {
	            // we need to bind explicitly
	            sslsock.bind(localAddress);
	    }

	    sslsock.connect(remoteAddress, connTimeout);
	    sslsock.setSoTimeout(soTimeout);
	    return sslsock;
	    }

	    //this method is modified
	    @Override
	    public Socket createSocket(HttpParams arg0) throws IOException {
	         return getSSLContext().getSocketFactory().createSocket();
	    }

	}
	public class MyHttpClient extends DefaultHttpClient {

	    /** The time it takes for our client to timeout */
	    public static final int HTTP_TIMEOUT = 30 * 1000; // milliseconds
	    public static final int SOCKET_TIMEOUT = 50 * 1000; // milliseconds


	    public MyHttpClient() {
	    }

	    @Override
	    protected ClientConnectionManager createClientConnectionManager() {

	        SchemeRegistry registry = new SchemeRegistry();
	        registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
	        // Register for port 443 our SSLSocketFactory to the ConnectionManager
	        registry.register(new Scheme("https", 443, new EasySSLSocketFactory()));

	        //setting the httpparams
	        HttpParams params = new BasicHttpParams();
	        //params.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 1);
	        //params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(1));
	        params.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);
	        //HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(params, "utf8");

	        return new SingleClientConnManager(registry);
	    }

	}
	public void newclass(){
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        String userid="";
		postParameters.add(new BasicNameValuePair("userid", userid));
        String password="";
		postParameters.add(new BasicNameValuePair("password", password));
        String newresponse = null;
        BufferedReader in = null;
        try{
             DefaultHttpClient client = new MyHttpClient();
             try {        
                    HttpPost req = new HttpPost("aaaa");
                    UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
                    req.setEntity(formEntity);
                    HttpResponse resp = client.execute(req);
                    in = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    String NL = System.getProperty("line.separator");
                    while ((line = in.readLine()) != null) {
                        sb.append(line + NL);
                    }
                    in.close();
                    newresponse = sb.toString();

                    }catch(Exception e){
//                        LOGGER.error("Exception", e);
                    }finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
//                                LOGGER.error("IOException: ",e);
                            }
                        }
                    }
        } catch(Exception e){
//            LOGGER.debug("Connection Exception: ",e);
        }
	}

}
