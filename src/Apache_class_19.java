
//ID = 5172076
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Apache_class_19 {
	public void main() throws IOException{
		InputStream inp = new FileInputStream("workbook.xls");
		HSSFWorkbook wb = new HSSFWorkbook(inp);

		String text="";
		Sheet sheet1 = wb.getSheetAt(0);

		for (Row row : sheet1) {
		    for (Cell cell : row) {
		        // Do something here
		        ExcelExtractor extractor = new ExcelExtractor(wb);
		        text=extractor.getText();
		        System.out.print("text="+text);
		        }
		    }
		sheet1.shiftRows(0,sheet1.getLastRowNum(),1);
	}
}
