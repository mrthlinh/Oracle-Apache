
//ID = 7057342
import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class Apache_class_27 {
	public static void main(String[] args) throws Exception
	  {
	    String fileName = "test.avi";
	    File file = new File(fileName);

	    String serverResponse = null;
	    HttpParams params = new BasicHttpParams();
	    params.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, true);
	    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	    HttpClient client = new DefaultHttpClient(params);
	    HttpPut put = new HttpPut("http://localhost:8080/" + fileName);

	    FileEntity fileEntity = new FileEntity(file, "binary/octet-stream");
	    put.setEntity(fileEntity);   

	    HttpResponse response = client.execute(put);
	    HttpEntity entity = response.getEntity();
	    if (entity != null)
	    {
	      serverResponse = EntityUtils.toString(entity);
	      System.out.println(serverResponse);
	    }
	  }
}
