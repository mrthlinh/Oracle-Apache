
//ID = 9664023
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class Apache_class_36 {
	public void main() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, KeyManagementException, UnrecoverableKeyException{
		HttpClient httpClient = null;


	        HttpParams httpParameters = new BasicHttpParams();

	        KeyStore rootca = KeyStore.getInstance(KeyStore.getDefaultType());
	        rootca.load(new FileInputStream("server.jks"), "bara".toCharArray());

	        KeyStore mycert = KeyStore.getInstance("JKS");
	        mycert.load(new FileInputStream("client.jks"), "bara".toCharArray());

	        SSLSocketFactory sockfact = new SSLSocketFactory(mycert, "bara", rootca);
	        sockfact.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); 
	        SchemeRegistry registry = new SchemeRegistry();
	        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	        registry.register(new Scheme("https", sockfact, 443));
	        httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(httpParameters, registry), httpParameters);

	        HttpGet get = new HttpGet("https://mycomputer.mynetwork/test");
	        HttpResponse response = httpClient.execute(get);

	        System.out.println(response.getStatusLine());

	}
}
