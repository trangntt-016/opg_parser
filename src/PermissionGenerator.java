import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import model.AccessRightsByProfile.AccessRights;
import model.AccessRightsByProfile.ValueAccessSpecific;
import parser.EPLParser;
import paths.Paths;
import utils.FileUtil;

/**
 * This class is to generate a new `Access_rights_by_profile_date.xml` by reading modified access on permissions.json
 * The output is placed in the same location with /src
 * 
 * @author Trang Nguyen
 * 
 * version: 1.0.0 - May 11 2022
 */
public class PermissionGenerator {
	private static final String _Default = "D";
	
    private static final Logger logger = Logger.getLogger(EPLParser.class.getName());
    
	public static void main(String[] args) throws IOException, JAXBException {
		LinkedHashMap<String, String>eplMap = EPLDisplaying.getEPLParser();
		
		// construct user requirements into a data structure
		Map<String, List<ValueAccessSpecific>>permissionJsonmap = FileUtil.parseJson(new File(Paths.FilePaths.permissionJSON));     
		
	    JAXBContext context = JAXBContext.newInstance(AccessRights.class);
	    
	    // parse AccessRights from All Profile.xml.
	    // this is used as a template to create the output
	    AccessRights accessTemplate = (AccessRights) context.createUnmarshaller()
	      .unmarshal(new FileReader(Paths.FilePaths.accessRightsByProfile));
	    
	    
	    // go through each jsonprofile to modify access
	    for(Map.Entry<String, List<ValueAccessSpecific>>jsonP : permissionJsonmap.entrySet()) {
	    	String jsonProfile = jsonP.getKey();
	    	List<ValueAccessSpecific>jsonValueAccessList = jsonP.getValue();
	    	// check if 'Access Rights From All Profile.xml' contains this profile
	    	if(accessTemplate.getAccessRightsTable().stream().anyMatch(a->a.getProfile().equals(jsonProfile))) {
	    		for(ValueAccessSpecific v : jsonValueAccessList) {
	    			// check if EPL.xsd contains this node
	    			if(eplMap.containsKey(v.getNodePath())) {
	    				String oldAccess = accessTemplate.getAccessFromProfileAndNodePath(jsonProfile, v.getNodePath());
	    				String newAccess = v.getValuesAccessSpecific();
	    				// if this profile doesnt contain this nodePath and newAccess isnt Default => add this node path + new access to the ValueAccessSpecifics
	    				if(oldAccess == null && !newAccess.equals(_Default)) {
	    					ValueAccessSpecific valueSpecific = new ValueAccessSpecific(v.getNodePath(), v.getValuesAccessSpecific());
	    					accessTemplate.getAccessRightsTable().stream().filter(a->a.getProfile().equals(jsonProfile)).findFirst().get().getValueAccess().addValueAccessSpecificToList(valueSpecific);
	    				}
	    				// if nodepath exists in the profile
	    				else if(oldAccess!=null) {
	    					if( newAccess.equals(_Default)) {
	    						accessTemplate.getAccessRightsTable().stream().filter(a->a.getProfile().equals(jsonProfile)).findFirst().get().getValueAccess().removeValueAccessSpecific(v);						
	    					}
	    					// otherwise, just replace the old access with new one
		    				else {
		    					accessTemplate.getAccessRightsTable().stream().filter(a->a.getProfile().equals(jsonProfile)).findFirst().get().getValueAccess().getValueAccessSpecificFromNodePath(v.getNodePath()).setValuesAccessSpecific(v.getValuesAccessSpecific());	    					
		    				}
	    				}			
	    				logger.info("Profile " + jsonProfile + " has been updated from " + oldAccess + " to " + newAccess + " with a nodepath: " + v.getNodePath());
	    				
	    			}
	    			else {
		    			logger.log(Level.SEVERE, "This nodepath " + v.getNodePath() + " is invalid");	    				
	    			}

	    		}
	    	}
	    	else {
	    		logger.log(Level.SEVERE, "Profile " + jsonProfile + " doesn't exist in `Access Rights From All Profile.xml`");
	    	}
	    	
	    }
	    
    	Marshaller mar= context.createMarshaller();
    	String timeStamp = new SimpleDateFormat("yyMMdd_HHmm").format(Calendar.getInstance().getTime());
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(accessTemplate, new File("./Access_rights_by_profile_" + timeStamp+".xml"));
	}
}
