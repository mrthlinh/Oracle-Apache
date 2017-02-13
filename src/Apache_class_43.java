
//ID = 10772358
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import android.content.Context;

//ID = 10772358
public class Apache_class_43 extends DefaultHttpClient {

    private Context context;

    public Apache_class_43(Context context) {

        this.context = context;
    }

    @SuppressWarnings("deprecation")
	@Override
    protected ClientConnectionManager createClientConnectionManager() {

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        registry.register(new Scheme("https", newSslSocketFactory(), 443));
        return new SingleClientConnManager(getParams(), registry);
    }

    private SSLSocketFactory newSslSocketFactory() {

        try {
            KeyStore trusted = KeyStore.getInstance("BKS");
            InputStream in = context.getResources().openRawResource(20);
            try {
                trusted.load(in, "changeit".toCharArray());
            }
            catch (CertificateException c) {
                System.out
                        .println("There was a certificate exception in myhttpclient!");
            }
            finally {

                in.close();
            }
            return new SSLSocketFactory(trusted);
            }
            catch (Exception e) {
                throw new AssertionError(e);
            }
    }
}