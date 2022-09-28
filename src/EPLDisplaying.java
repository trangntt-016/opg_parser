import parser.CommonTypeParser;
import parser.EPLParser;
import paths.Paths;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is to show all the names and node paths that are associated with the ones in the `EPL.xsd`
 * This matches with the Specific policy in Ebx's permission  http://catou-ogappvw3x:9080/ebx-ui/ui/advanced/data/BEPMO/Decision?service=%40component&serviceMode=DATA_SET&serviceParams=eyJjb21wb25lbnROYW1lIjoiUGVybWlzc2lvblNlcnZpY2UiLCJtb2R1bGVQYXRoIjoiZWJ4L2FwcC9idWlsdGluL3NlcnZpY2UvZGF0YXNldC9wZXJtaXNzaW9uL1Blcm1pc3Npb25TZXJ2aWNlIn0%3D
 * This helps to search for the nodepaths so that later on can put into permissions.json
 * User needs to input the paths of CommonType.xsd and EPL.xsd. Those are for constructing the nodes and will be display on the console
 * 
 * @author Trang Nguyen
 * 
 * version: 1.0.0 - May 11 2022
 */
public class EPLDisplaying {
	public static void main(String[] args) throws IOException {
        LinkedHashMap<String, String>epl = getEPLParser();

	    for(Map.Entry<String, String>v : epl.entrySet()) {
	    	  System.out.println(v.getValue() + "------->" + v.getKey());
	    }
    }
	
	public static LinkedHashMap<String, String> getEPLParser(){
		File commonTypeFile = new File(Paths.FilePaths.commonTypeFile);
        
		File eplFile = new File(Paths.FilePaths.eplFile);

        // get the map of common type nodes to extract 
        LinkedHashMap<String, List<String[]>> conmmonType = new CommonTypeParser().parseCommonType(commonTypeFile);

        LinkedHashMap<String, String>epl = new EPLParser().parseEPL(eplFile, conmmonType);
        
        return epl;
	}



}
