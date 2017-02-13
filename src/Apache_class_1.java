import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
;

public class Apache_class_1 {
	public void searchAndReplace(String inputFilename, String outputFilename,
            HashMap<String, String> replacements) {
    File outputFile = null;
    File inputFile = null;
    FileInputStream fileIStream = null;
    FileOutputStream fileOStream = null;
    BufferedInputStream bufIStream = null;
    BufferedOutputStream bufOStream = null;
    POIFSFileSystem fileSystem = null;
    HWPFDocument document = null;
    Range docRange = null;
    Paragraph paragraph = null;
    CharacterRun charRun = null;
    Set<String> keySet = null;
    Iterator<String> keySetIterator = null;
    int numParagraphs = 0;
    int numCharRuns = 0;
    String text = null;
    String key = null;
    String value = null;
        try {
            // Create an instance of the POIFSFileSystem class and
            // attach it to the Word document using an InputStream.
            inputFile = new File(inputFilename);
            fileIStream = new FileInputStream(inputFile);
            bufIStream = new BufferedInputStream(fileIStream);
            fileSystem = new POIFSFileSystem(bufIStream);
            document = new HWPFDocument(fileSystem);
//            docRange = document.getRange();
//            numParagraphs = docRange.numParagraphs();
            keySet = replacements.keySet();
            for (int i = 0; i < numParagraphs; i++) {
//                paragraph = docRange.getParagraph(i);
                text = paragraph.text();
                numCharRuns = paragraph.numCharacterRuns();
                for (int j = 0; j < numCharRuns; j++) {
                    charRun = paragraph.getCharacterRun(j);
                    text = charRun.text();
                    System.out.println("Character Run text: " + text);
                    keySetIterator = keySet.iterator();
                    while (keySetIterator.hasNext()) {
                        key = keySetIterator.next();
                        if (text.contains(key)) {
                            value = replacements.get(key);
                            charRun.replaceText(key, value);
//                            docRange = document.getRange();
//                            paragraph = docRange.getParagraph(i);
                            charRun = paragraph.getCharacterRun(j);
                            text = charRun.text();
                        }
                    }
                }
            }
            bufIStream.close();
            bufIStream = null;
            outputFile = new File(outputFilename);
            fileOStream = new FileOutputStream(outputFile);
            bufOStream = new BufferedOutputStream(fileOStream);
            document.write(bufOStream);
        } catch (Exception ex) {
            System.out.println("Caught an: " + ex.getClass().getName());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Stacktrace follows.............");
            ex.printStackTrace(System.out);
        }
}
}
