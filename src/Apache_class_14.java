
// ID = 3677925
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Apache_class_14 {
	public void main(){
		POIFSReaderEvent event = null;
		try {
		     final POIFSFileSystem poifs = new POIFSFileSystem(event.getStream());
		     final DirectoryEntry dir = poifs.getRoot();
		     final DocumentEntry dsiEntry = (DocumentEntry)
		             dir.getEntry(DocumentSummaryInformation.DEFAULT_STREAM_NAME);

		     final DocumentInputStream dis = new DocumentInputStream(dsiEntry);
		     final PropertySet props = new PropertySet(dis);
		     dis.close();
		     final DocumentSummaryInformation dsi = new DocumentSummaryInformation(props);
		    }
		    catch (Exception ex) {
		        throw new RuntimeException
		            ("Cannot create POI SummaryInformation for event: " + event +
		              ", path:" + event.getPath() + 
		              ", name:" + event.getPath() +
		              ", cause:" + ex);
		    }
	}
}
