
//ID = 7196630
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.util.TrustManagerUtils;

public class Apache_class_28 {
	public void outboundProcessFile(String inputFilePath, String outputFilePath) throws Exception {

		File inFile = new java.io.File(inputFilePath);
		String args[]=new String[2];

		boolean storeFile = false, binaryTransfer = false, error = false, listFiles = false, listNames = false, hidden = false;
		boolean mlst = false, mlsd = false;
		boolean lenient = true;
		boolean feat = true;;
		long keepAliveTimeout = 300000;
		int controlKeepAliveReplyTimeout = 300000;
		int minParams = 5; 
		String doCommand = "PROT P";//null
		String server = null, password = null, username = null, remote = null,local = null;
		int port = 990;//990
		int base = 0;
		for (base = 0; base < args.length; base++)     {
		    if (args[base].equals("-s")) {
		        storeFile = true;
		    }        else if (args[base].equals("-b")) {
		        binaryTransfer = true;
		    }        else if (args[base].equals("-c")) {
		        doCommand = args[++base];
		        minParams = 3;
		    }        else if (args[base].equals("-d")) {
		        mlsd = true;
		        minParams = 3;
		    }        else if (args[base].equals("-h")) {
		        hidden = true;
		    }        else if (args[base].equals("-k")) {
		        keepAliveTimeout = Long.parseLong(args[++base]);
		    }        else if (args[base].equals("-l")) {
		        listFiles = true;
		        minParams = 3;
		    }        else if (args[base].equals("-L")) {
		        lenient = true;
		    }        else if (args[base].equals("-n")) {
		        listNames = true;
		        minParams = 3;
		    }        else if (args[base].equals("-t")) {
		        mlst = true;
		        minParams = 3;
		    }        else if (args[base].equals("-w")) {
		        controlKeepAliveReplyTimeout = Integer.parseInt(args[++base]);
		    }        else if (args[base].equals("-sh")) {
		        server = args[++base];
		        String parts[] = server.split(":");
		        if (parts.length == 2){
		            server=parts[0];
		            port=Integer.parseInt(parts[1]);
		        }
		    }        else if (args[base].equals("-u")) {
		        username = args[++base];
		    }        else if (args[base].equals("-pw")) {
		        password = args[++base];
		    }        else if (args[base].equals("-rf")) {
		        remote = null;

		        if (args.length - base > 0) {
		            remote = args[++base];
		        }
		    }        else if (args[base].equals("-lf")) {
		        local = null;

		        if (args.length - base > 0) {
		            local = args[++base];
		        }
		    }        else {
		        break;
		    }
		}

		FTPClient ftp;
		    FTPSClient ftps = new FTPSClient(true);

		    ftp = ftps;
		    ftps.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
//		    ftp.setCopyStreamListener(createListener());

		if (keepAliveTimeout >= 0) {
		}
		if (controlKeepAliveReplyTimeout >= 0) {
		}

		// suppress login details
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

		try                                  {
		    int reply;
		    if (port > 0) {
		        ftp.connect(server, port);
		    } else {
		        ftp.connect(server);
		    }
		    System.out.println("Connected to " + server + " on "+ftp.getRemotePort());

		    // After connection attempt, you should check the reply code to verify
		    // success.
		    reply = ftp.getReplyCode();

		    if (!FTPReply.isPositiveCompletion(reply))           {
		        ftp.disconnect();
		        System.err.println("FTP server refused connection.");
		        System.exit(1);
		    }
		}
		catch (IOException e)         {
		    if (ftp.isConnected())         {
		        try               {
		            ftp.disconnect();
		        }
		        catch (IOException f)
		        {
		            // do nothing
		        }
		    }
		    System.err.println("Could not connect to server.");
		    e.printStackTrace();
		    System.exit(1);
		}

		__main:
		try
		{
		    String userid = null;//

		    if (!ftp.login(username, password))              {
		        ftp.logout();
		        error = true;
		        break __main;
		    }

		    System.out.println("Remote system is " + ftp.getSystemType());
		    if (feat)                                                        {
		        // boolean feature check
		        if (remote != null) { // See if the command is present
		            if (ftp.hasFeature(remote)) {
		                System.out.println("Has feature: "+remote);
		            } else {
		                if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
//		                        System.out.println("FEAT "+remote+" was not detected");
		                } else {
		                    System.out.println("Command failed: "+ftp.getReplyString());
		                }
		            }

		            // Strings feature check
		            String []features = ftp.featureValues(remote);
		            if (features != null) {
		                for(String f : features) {
		                    System.out.println("FEAT "+remote+"="+f+".");
		                }
		            } else {
		                if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
//		                        System.out.println("FEAT "+remote+" is not present");
		                } else {
		                    System.out.println("Command failed: "+ftp.getReplyString());
		                }
		            }
		        } else {
		            if (ftp.features()) {
//		                    Command listener has already printed the output
		            } else {
		                System.out.println("Failed: "+ftp.getReplyString());
		            }
		        }
		    }
		    ftp.doCommandAsStrings("PBSZ", "0");
		    ftp.doCommandAsStrings("PROT", "P");
		    if (binaryTransfer)
		        ftp.setFileType(FTP.ASCII_FILE_TYPE);//.BINARY_FILE_TYPE);

		    // Use passive mode as default because most of us are
		    // behind firewalls these days.

		        ftp.enterLocalPassiveMode();
		        ftp.doCommand("MLSD", "");

		    if (storeFile)          {
		        InputStream input;

		        input = new FileInputStream(local);
		        ftp.changeWorkingDirectory(remote);
		        ftp.storeFile(local.substring(local.lastIndexOf("/")+1), input);

		        input.close();
		    }        else if (listFiles)          {
		        if (lenient) {
		            FTPClientConfig config = new FTPClientConfig();
		            config.setLenientFutureDates(true);
		            ftp.configure(config );
		        }

		        for (FTPFile f : ftp.listFiles(remote)) {
		            System.out.println(f.getRawListing());
		            System.out.println(f.toFormattedString());
		        }
		    }         else if (mlsd)         {
		        for (FTPFile f : ftp.mlistDir(remote)) {
		            System.out.println(f.getRawListing());
		            System.out.println(f.toFormattedString());
		        }
		    }           else if (mlst)          {
		        FTPFile f = ftp.mlistFile(remote);
		        if (f != null){
		            System.out.println(f.toFormattedString());
		        }
		    }               else if (listNames)           {
		        for (String s : ftp.listNames(remote)) {
		            System.out.println(s);
		        }
		    }    else          {
		        OutputStream output;

		        output = new FileOutputStream(local);
		        ftp.changeWorkingDirectory(remote);
		        ftp.retrieveFile(local.substring(local.lastIndexOf("/")), output);

		        output.close();
		    }

		    ftp.noop(); // check that control connection is working OK
		    ftp.logout();
		}       catch (FTPConnectionClosedException e)       {
		    error = true;
		    System.err.println("Server closed connection.");
		    e.printStackTrace();
		}     catch (IOException e)        {
		    error = true;
		    e.printStackTrace();
		}     finally         {
		    if (ftp.isConnected())         {
		        try               {
		            ftp.disconnect();
		        }             catch (IOException f)             {
		            // do nothing
		        }
		    }
		}
		System.exit(error? -1:0);       
		   }
}
