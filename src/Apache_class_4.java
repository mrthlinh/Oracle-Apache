
// ID: 1536896
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
public class Apache_class_4 {
	public static void main(String[] args) {
	    final WebClient webClient = new WebClient();
	    try {
	        HtmlPage loginPage = webClient.getPage("https://www.google.com/accounts/ServiceLogin?service=orkut&hl=en-US&rm=false&continue=http%3A%2F%2Fwww.orkut.com%2FRedirLogin%3Fmsg%3D0%26page%3Dhttp%253A%252F%252Fwww.orkut.co.in%252FHome.aspx&cd=IN&passive=true&skipvpage=true&sendvemail=false");
	        System.out.println(loginPage.getTextContent());
	        List<HtmlForm> forms = loginPage.getForms();
	        HtmlForm loginForm = forms.get(0);
	        HtmlInput username = loginForm.getInputByName("Email");
	        HtmlInput password = loginForm.getInputByName("Passwd");
	        HtmlInput submit = loginForm.getInputByName("signIn");
	        username.setNodeValue("username");
	        password.setNodeValue("password");
	        HtmlPage homePage = submit.click();
	        Thread.sleep(10 * 1000);
	        System.out.println(homePage.getTextContent());
	    }catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}
}
