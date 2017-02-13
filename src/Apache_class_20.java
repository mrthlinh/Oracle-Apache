
//ID = 5832713
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
/**
 *
 */
@WebServlet(description = "Gets the book's barcode with a form", urlPatterns = { "/FormWebServlet" })
public class Apache_class_20 extends HttpServlet {

    /** */
    private static final long serialVersionUID = 6008315960327824633L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *  HttpServletResponse response)
     */
    protected void doGet(final HttpServletRequest request, 
            final HttpServletResponse response)
    throws IOException, ServletException {
        final String BAR_CODE = request.getParameter("barCode");

        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();

        if (BAR_CODE != null) {
            HttpClient client = new DefaultHttpClient();
//            final String ADDRESS = ServletUtilities.getHttpAddress(BAR_CODE);
            String ADDRESS ="";
            out.println("ADDRESS = \"" + ADDRESS + '\"');
            HttpGet get = new HttpGet(ADDRESS);

            HttpResponse httpResponse = null;

// Removed commented code that will use these objects
        }
    }
}