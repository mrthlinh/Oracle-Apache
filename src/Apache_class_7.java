
//ID = 1910857
import java.io.StringWriter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
public class Apache_class_7 {


	public class Main
	{
	   /** Define a static logger variable so that it references the Logger
	    *  instance named "MyApp".
	    */
	    private Logger logger = Logger.getLogger(Main.class);


	    /**
	     * @param args the command line arguments
	     */
	    public void main(String[] args)
	    {

	        /* Set up a simple log4j configuration that logs on the console. */
	        BasicConfigurator.configure();


	        /* Check to see that a template path was passed on the command line. */
	        if (args.length != 1)
	        {
	            logger.fatal("You must pass the path to a template as a " +
	                    "command line argument.");
	            return;
	        }

	        /* Pull the template filename from the command line argument. */
	        String fileName = args[0];

	        try
	        {
	            Velocity.setProperty("resource.loader", "file");
	            Velocity.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
	            Velocity.setProperty("file.resource.loader.path", "/templates/");
	            Velocity.setProperty("file.resource.loader.cache", "false");
	            Velocity.setProperty("file.resource.loader.modificationCheckInterval", "0");

	            Velocity.init();
	        }
	        catch (Exception ex)
	        {
	            logger.fatal("Error initializing the Veolcity engine.", ex);
	            return;
	        }

	        boolean error = false;

	        /* Create an empty Velocity context */
	        VelocityContext context = new VelocityContext();

	        Template template = null;

	        try
	        {
	           template = Velocity.getTemplate(fileName);
	        }
	        catch( ResourceNotFoundException rnfe )
	        {
	           logger.error("Couldn't find the template to parse at this path: " +
	                   fileName + ".", rnfe);
	           error = true;
	        }
	        catch( ParseErrorException peex )
	        {
	            logger.error("Error parsing the template located at this path: " +
	                    fileName + ".", peex);
	            error = true;
	        }
	        catch( MethodInvocationException mie )
	        {
	            logger.error("Something invoked in the template (" + fileName +
	                    ") threw an Exception while parsing.", mie);
	            error = true;
	        }
	        catch( Exception e )
	        {
	            logger.error("An unexpected exception was thrown when attempting " +
	                    "to parse the template: " + fileName + ".", e);
	            error = true;
	        }

	        if (error)
	        {
	            return;
	        }

	        StringWriter sw = new StringWriter();
	        try
	        {
	            template.merge(context, sw);
	        } 
	        catch (ResourceNotFoundException rnfe)
	        {
	            logger.error("Couldn't find the template to merge at this path: " +
	                   fileName + ".", rnfe);
	            error = true;
	        } 
	        catch (ParseErrorException peex)
	        {
	            logger.error("Error parsing the template at this path during merge: " +
	                    fileName + ".", peex);
	            error = true;
	        } 
	        catch (MethodInvocationException mie)
	        {
	            logger.error("Something invoked in the template (" + fileName +
	                    ") threw an Exception while merging.", mie);
	            error = true;
	        } 
	        catch( Exception e )
	        {
	            logger.error("An unexpected exception was thrown when attempting " +
	                    "to merge the template: " + fileName + ".", e);
	            error = true;
	        }

	        if (!error)
	        {
	            logger.info("No syntax errors detected.");
	        }
	    }
	}
}
