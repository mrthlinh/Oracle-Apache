
//ID = 10279885
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class Apache_class_41 {

    public static void main(String[] args) {

        FTPClient ftp = new FTPClient();
        int reply;

        try {

            ftp.connect("ip address");
            ftp.login("username","password");
            reply = ftp.getReplyCode();

            if(FTPReply.isPositiveCompletion(reply)){
                System.out.println("Connected Success");
            }else {
                System.out.println("Connection Failed");
                ftp.disconnect();
            }

            FileInputStream fis = null;
            String filename = "demo.txt";
            fis = new FileInputStream("C:\\demo.txt");
            System.out.println("Is file stored: "+ftp.storeFile(filename,fis));
            fis.close();
            ftp.disconnect();
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}