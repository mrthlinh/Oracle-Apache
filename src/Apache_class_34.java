

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class Apache_class_34 {
	public void write(int row, int column, String label) {
	    try {
	    	FileInputStream inp = new FileInputStream("aaa");
	        Workbook wb = WorkbookFactory.create(inp);
	        Sheet sheet = wb.getSheetAt(0);
	        Row sheetRow = sheet.getRow(row);
	        Cell cell = sheetRow.getCell(column);
	        //String cellContents = cell.getStringCellValue();
	        //        //Modify the cellContents here
	        //        // Write the output to a file
	        if (cell == null) {
	            cell = sheetRow.createCell(column);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	        }
	        cell.setCellType(Cell.CELL_TYPE_STRING);
	        cell.setCellValue(label);
	        FileOutputStream fileOut = new FileOutputStream("aaa");
	        wb.write(fileOut);
	        fileOut.close();
	    } catch (IOException ex) {
//	        Logger.getLogger(ExcelManipulator.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (InvalidFormatException ex) {
//	        Logger.getLogger(ExcelManipulator.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
}