
//ID = 9631527
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

public class Apache_class_35 {

    public static final int BUFFER_MAX = 2048;

    public static void untar(String fileName, String targetPath) throws IOException {
        File tarArchiveFile = new File(fileName);
        BufferedOutputStream dest = null;
        FileInputStream tarArchiveStream = new FileInputStream(tarArchiveFile);
        TarArchiveInputStream tis = new TarArchiveInputStream(new BufferedInputStream(tarArchiveStream));
        TarArchiveEntry entry = null;
        try {
            while ((entry = tis.getNextTarEntry()) != null) {
                int count;
                File outputFile = new File(targetPath, entry.getName());

                if (entry.isDirectory()) { // entry is a directory
                    if (!outputFile.exists()) {
                        outputFile.mkdirs();
                    }
                } else { // entry is a file
                    byte[] data = new byte[BUFFER_MAX];
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    dest = new BufferedOutputStream(fos, BUFFER_MAX);
                    while ((count = tis.read(data, 0, BUFFER_MAX)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                    dest.close();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (dest != null) {
                dest.flush();
                dest.close();
            }
            tis.close();
        }
    }
}