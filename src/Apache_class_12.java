
//ID = 3263809
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.portlet.PortletFileUpload;

public class Apache_class_12 {
	private Map<String, Object> getMap( ActionRequest request ) {

	    HashMap<String, Object> parameters = new HashMap<String, Object>();
	    if ( request == null ) {
	        return parameters;
	    }

	    if ( request.getContentType() == null ) {
	        return parameters;
	    }

	    try {
	        if(PortletFileUpload.isMultipartContent(request)){
	            DiskFileItemFactory factory = new DiskFileItemFactory();
	            PortletFileUpload upload = new PortletFileUpload(factory);
	            List<FileItem> fileItems = upload.parseRequest(request);
//	            DiskFileItem fileItem;
	            for( FileItem fileItem : fileItems ) {
	                String name = fileItem.getFieldName();
	                //now set appropriate variable, populate hashtable
	                if( fileItem.isFormField() ) {
	                    String value = fileItem.getString( request.getCharacterEncoding() );
	                    if( parameters.get( name ) == null ) {
	                        String[] values = new String[1];
	                        values[0] = value;
	                        parameters.put( name, values );
	                    } else {
	                        Object prevobj = parameters.get( name );
	                        if( prevobj instanceof String[] ) {
	                            String[] prev = ( String[] ) prevobj;
	                            String[] newStr = new String[prev.length + 1];
	                            System.arraycopy(
	                                    prev, 0, newStr, 0,
	                                    prev.length
	                            );
	                            newStr[prev.length] = value;
	                            parameters.put( name, newStr );
	                        } else {
	                            //now what? I think this breaks the standard.
//	                            throw new EatMyHatException(
//	                                    "file and input field with same name?"
//	                            );
	                        }
	                    }
	                } else {
	                    // Yes, we don't return FileParameter[] for multiple files of same name.  AFAIK, that's not allowed.
//	                    FileParameter fp = new FileParameter(fileItem);
//	                    parameters.put( name, fp );
//	                    files.add( fp );
	                }
	            }
	        } else {
	            // Not multipart
//	            return toObjectMap(request.getParameterMap());
	        }
	    } catch (FileUploadException e) {
	        throw new RuntimeException(e);
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e);
	    }
	    return parameters;
	}
}
