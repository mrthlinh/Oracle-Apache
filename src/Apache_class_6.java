
//ID = 1560230
import org.apache.directory.server.core.DefaultDirectoryService;
import org.apache.directory.server.core.api.partition.Partition;
import org.apache.directory.server.ldap.LdapServer;
import org.apache.directory.server.protocol.shared.transport.TcpTransport;

public class Apache_class_6 {
	public void startDirectoryService() throws Exception {
	    DefaultDirectoryService service = new DefaultDirectoryService();
	    service.getChangeLog().setEnabled( false );

//	    Partition apachePartition = addPartition("apache", "dc=apache,dc=org");

	    LdapServer ldapService = new LdapServer();
	    ldapService.setTransports(new TcpTransport(389));
	    ldapService.setDirectoryService(service);

	    service.startup();
	    ldapService.start();

	    Partition apachePartition = null;
		service.getAdminSession().lookup( apachePartition.getSuffixDn() );
	}
}
