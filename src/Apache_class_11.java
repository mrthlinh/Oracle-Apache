
//ID = 3146328
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;

public class Apache_class_11 {
	public void main() throws UnsupportedEncodingException{
		String url = "";
		HttpPut addDoc = new HttpPut(url);
		addDoc.addHeader("Content-Type", "multipart/related; boundary=\"END_OF_PART\"");
		String bodyString = "Test for multipart update";
		String titleString = "Title Test for multipart update";
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		StringBody title = new StringBody(titleString, "application/atom+xml",Charset.forName("UTF-8"));
		StringBody body = new StringBody(bodyString, "text/plain",Charset.forName("UTF-8"));
		entity.addPart("title", title);
		entity.addPart("body", body);
		addDoc.setEntity(entity);
	}
}
