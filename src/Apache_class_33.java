

import java.io.File;

import org.apache.commons.io.FileUtils;

class MoveFile {

    public static void main(String[] args) {
        MoveFile myobj = new MoveFile();
        myobj.moveTheFile();
    }

    public void moveTheFile () {
        try {
            File destDir = new File("C:\\Folder1\\temp2");
            File srcFile = new File("C:\\Folder1\\temp\\card.png");
            FileUtils.copyFileToDirectory(srcFile, destDir);
        } catch(Exception e) {
        }
    }

}