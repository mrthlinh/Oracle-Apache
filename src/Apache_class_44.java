
//ID = 10817970
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.ParagraphProperties;

public class Apache_class_44 {
	public static void main(String[] args) {
	    try {
	          HWPFDocument templateFile = new HWPFDocument(new FileInputStream("D:\\POI\\testPOIin.doc"));
	          HWPFDocument blankFile = new HWPFDocument(new FileInputStream("D:\\POI\\blank.doc"));

	        ParagraphProperties pp = templateFile.getRange().getParagraph(4).cloneProperties();
//	        blankFile.getRange().insertAfter(pp, 0);
	        OutputStream out = new FileOutputStream("D:\\POI\\testPOIout.doc");
	        blankFile.write(out);

	      } catch (FileNotFoundException fnfe) {
	          // TODO: Add catch code
	          fnfe.printStackTrace();
	      } catch (Exception ioe) {
	          // TODO: Add catch code
	          ioe.printStackTrace();
	      }
	  }
}
