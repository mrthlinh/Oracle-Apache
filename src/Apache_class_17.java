
//ID = 5054523
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Apache_class_17 {
	public ArrayList <Object> sendObject( Object o )
	{
	    try
	    {
//	        URL servletUrl = new URL( this.getCodeBase(),  ); // "http://localhost:8080/" );
	        URL servletUrl = new URL("Updater");
	        URLConnection conn = servletUrl.openConnection();

	        conn.setDoInput( true );
	        conn.setDoOutput( true );
	        conn.setUseCaches( false );
	        conn.setRequestProperty( "Content-Type", "application/x-java-serialized-object" );

	        OutputStream out = conn.getOutputStream();
	        ObjectOutputStream objOut = new ObjectOutputStream( out );
	        objOut.writeObject( o ); 
	        objOut.flush(); 
	        objOut.close();

	        // receive result from servlet
	        System.out.println( "Result from applet.getCodeBase()=");
	     // The following line is where I get the exception thrown
	        InputStream instr = conn.getInputStream();              
	        ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
	        ArrayList <Object> result = (ArrayList <Object>) inputFromServlet.readObject();
	        inputFromServlet.close();
	        instr.close();

	        System.out.println( "Contents of output is " );
	        for (int i=0; i<result.size(); i++)
	        {
	            System.out.println( "" + result.get(i) );
	        }

	        return result;
	    }
	    catch ( IOException e )
	    {
	        System.out.println( "In sendObject(): " + e );
	    }
	    catch ( Exception e )
	    {
	        System.out.println( "In sendObject(): " + e );
	    }

	    return null;
	}
}
