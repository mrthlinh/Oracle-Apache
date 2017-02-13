
//ID = 9761503
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class Apache_class_39 {
	public class FTPManager
	{
	FTPClient ftp;
	String user;
	String pass;
	String host;
	String dir;
	boolean bin;
	boolean active;

	public boolean isConnected()
	{
	    return ftp.isConnected();
	}
	public int getRetnerValue()
	{
	    return ftp.getReplyCode();
	}
	public boolean isGood(int i)
	{
	   return FTPReply.isPositiveCompletion(i);
	}
	public FTPManager(String _host, String account, String password, String inital_dir, boolean active, boolean binary)
	{
	    dir = inital_dir;

	    user = account;
	    pass = password;

	    bin = binary;

	    host = _host;

	    ftp = new FTPClient();

	    try
	    {
	        ftp.connect(_host);
	        int reply = ftp.getReplyCode();
	        if(!FTPReply.isPositiveCompletion(reply))
	        {
	            ftp.disconnect();
	            return;
	        }
	    }
	    catch(IOException e)
	    {
	        e.printStackTrace();
	    }
	}
	public void setupConnection()
	{
	    try
	    {
	        if(!ftp.login(user, pass))
	        {
	            ftp.logout();
	            return;
	        }

	        if(active)
	        {
	            ftp.enterLocalActiveMode();
	        }
	        else
	        {
	            ftp.enterLocalPassiveMode();
	        }

	        ftp.setFileType(bin ? FTP.BINARY_FILE_TYPE : FTP.ASCII_FILE_TYPE);
	    }
	    catch(IOException e)
	    {
	        e.printStackTrace();
	        System.exit(0);
	    }
	}
	public FTPFile[] filesIn(String remote)
	{
	    try
	    {
	        return ftp.listFiles(remote);
	    }
	    catch (IOException ex)
	    {
	        return new FTPFile[]{};
	    }
	}

	public String getInitalDirectory() throws IOException
	{
	    return ftp.printWorkingDirectory();
	}

	public FTPFile[] foldersIn(String remote)
	{
	    try
	    {

	        return remote.equalsIgnoreCase(".") ? ftp.listDirectories() : ftp.listDirectories(remote);
	    }
	    catch (IOException ex)
	    {
	        return null;
	    }
	}
	public void download(String remote, String local)
	{
	    File target = new File(local);
	    File output = null;

	    if(target.isDirectory())
	    {
	        String[] parts = remote.split(File.pathSeparator);

	        output = new File(local,parts[parts.length - 1]);
	    }
	    else
	    {
	        output = new File(local);
	    }
	    try
	    {
	        if(!output.exists())
	        {
	            output.mkdirs();
	            output.createNewFile();
	        }
	        ftp.retrieveFile(remote, new FileOutputStream(output));
	    }
	    catch(IOException ex)
	    {
	        Logger.getLogger(FTPManager.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	public void upload(String local, String remote)
	{
	    File input = new File(local);

	    if(!input.exists())
	    {
	        return;
	    }
	    try
	    {
	        FileInputStream in = new FileInputStream(input);
	        ftp.storeFile(remote, in);
	    }
	    catch (IOException ex)
	    {
	        Logger.getLogger(FTPManager.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	}
}
