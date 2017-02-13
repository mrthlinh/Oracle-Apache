

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Apache_class_2 {
	public static int getPicIndex(HSSFWorkbook wb){
	    int index = -1;
	    try {
	        byte[] picData = null;
	        File pic = new File( "C:\\pdf\\logo.jpg" );
	        long length = pic.length(  );
	        picData = new byte[ ( int ) length ];
	        FileInputStream picIn = new FileInputStream( pic );
	        picIn.read( picData );
	        index = wb.addPicture( picData, HSSFWorkbook.PICTURE_TYPE_JPEG );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  catch (Exception e) {
	        e.printStackTrace();
	    } 	    
	    return index;
	}
}
