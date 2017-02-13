
//ID = 10916282
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.io.CopyStreamException;

public class Apache_class_49 {
	public void main() throws IOException{
		FTPClient ftp = new FTPClient();

        ftp.setRemoteVerificationEnabled(false);
        InetAddress ip = null;
		ftp.connect(ip);

        if (ftp.isConnected()) {
            ftp.login("****", "****");
            ftp.enterLocalPassiveMode();
            FTPFile[] files = ftp.listFiles("xml");
            for (FTPFile file : files) {
                boolean first = file.getName().startsWith("Firstfile");
                boolean second = file.getName().startsWith("Secondfile");

                if (first || second) {
                    String grammarHome = null;
					File clientFile = new File(grammarHome + file.getName());
                    clientFile.createNewFile();
                    OutputStream stream = new FileOutputStream(clientFile);

                    try{
                    ftp.retrieveFile("xml/" + file.getName(), stream);
                    }catch(CopyStreamException e){
                        e.getIOException().printStackTrace();
                        throw e;
                    }

                    stream.close();

                    if (first) {
                        files[0] = file;
                    }
                    else if (second) {
                        files[1] = file;
                    }
                }

            }

        }
	}
}
