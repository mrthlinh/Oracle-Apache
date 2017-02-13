

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;

import android.os.Environment;

public class Apache_class_31 {
	public static void getResponseBodyForServerData(final HttpEntity entity) throws IOException, ParseException {

        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        InputStream instream = entity.getContent();
        InputStreamReader inputStreamReader = new InputStreamReader(instream, "UNICODE") ;

        if (instream == null) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/foo/Response.txt");
        if (!file.exists()) {
            file.createNewFile();
        }


        BufferedReader bufferedReader = new BufferedReader(inputStreamReader) ;
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UNICODE")) ;



        String singleLine = null ;

        while((singleLine = bufferedReader.readLine()) != null) {
            bufferedWriter.write(singleLine) ;
        }

        bufferedWriter.flush() ;
        bufferedWriter.close() ;
	}
	public static void getResponseBodyForServerData1(final HttpEntity entity) throws IOException, ParseException {

        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        InputStream instream = entity.getContent();

        if (instream == null) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/foo/Response.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream out = new FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = instream.read(buf)) > 0)
            out.write(buf, 0, len);
        out.flush();
        out.close();

    }
}
