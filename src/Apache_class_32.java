
//ID = 8398259
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class Apache_class_32 {

    public static void main(String[] args)throws IOException {
        String fileName = "C:\\Test.docx";
        InputStream fis = new FileInputStream(fileName);
        XWPFDocument document = new XWPFDocument(fis);
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (int x=0; x<paragraphs.size();x++)
        {
            XWPFParagraph paragraph = paragraphs.get(x);
            System.out.println(paragraph.getParagraphText());
        }
        List<XWPFTable> tables = document.getTables();
        for (int x=0; x<tables.size();x++)
        {
            XWPFTable table = tables.get(x);
            List<XWPFTableRow> tableRows = table.getRows();
            tableRows.remove(x);
            for (int r=0; r<tableRows.size();r++)
            {
                System.out.println("Row "+ (r+1)+ ":");
                XWPFTableRow tableRow = tableRows.get(r);
                List<XWPFTableCell> tableCells = tableRow.getTableCells();
                for (int c=0; c<tableCells.size();c++)
                {
                    System.out.print("Column "+ (c+1)+ ": ");
                    XWPFTableCell tableCell = tableCells.get(c);
                    //tableCell.setText("TAE");
                    String tableCellVal = tableCell.getText();
                    if ((c+1)==2){
                        if (tableCellVal!=null){
                            if (tableCellVal.length()>0){
                                 char c1 = tableCellVal.charAt(0);
                                 String s2 = "-TEST";
                                 char c2 = s2.charAt(0);
                                 String test = tableCell.getText().replace(tableCellVal,s2);
                                 tableCell.setText(test);
                            }else{
                                //tableCell.setText("NULL");
                            }
                        }
                    }
                    System.out.println("tableCell.getText(" + (c) + "):" + tableCellVal);
                }
            }
            System.out.println("\n");
        }
        OutputStream out = new FileOutputStream(fileName);
        document.write(out);
        out.close();
    }
}
