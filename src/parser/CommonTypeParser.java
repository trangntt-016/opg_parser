package parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dom.DOM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.FileUtil;
import utils.NodeUtil;
import utils.StringUtil;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * To construct a nodePath in AccessRightsByProfile.xml: <nodePath>/root/bundle/BaseAudit/createUser</nodePath> 
 * There are two parts that are linked together: /root/bundle/BaseAudit in EPL.xsd and Base/Audit/createUser in CommonType.xsd
 * To be more specific:
 * 				In EPL.xsd, <xs:element name="BaseAudit" type="BaseAudit" minOccurs="1" maxOccurs="1">
 * 				In CommonType.xsd, BaseAudit contains 04 <xs:element> nodes: "createUser", "createTimestamp", "lastUpdateUser", "lastUpdateTimestamp"
 * This class is to parse CommonType.xsd which contains all of the types that is associated with some nodes in the EPL.xsd.
 * We need to parse Common Types so that later on we can link them with the nodes in EPL.xsd that has custom types (e.g BaseAudit, EmployeeAudit, etc.) 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonTypeParser {
	private String FILE_EXTENSION = "xsd";
	
	
	private static final Logger logger = Logger.getLogger(CommonTypeParser.class.getName());

	/**
	 * Parse the Common Types from CommonTypes.xsd
	 *
	 *
	 *
	 * @param File file : the location of CommonTypes.xsd
	 * 
	 * @return a map which has:
	 * 	key: 	name of the ComplexType that is used to link between EPL's nodes and CommonType's node.
	 * 			This key will be used for looking up when doing the linking between EPL and CommonType.
	 * 			For example: /root/bundle/BaseAudit/createUser has BaseAudit as a key (EPL.xsd doesnt have BaseAudit/createUser node, that node is in CommonType.xsd)
	 *  value: a list of all nodes that are associated with the key in CommonType.xsd
	 *  		For example: 
	 *  						BaseAudit/createUser - Create User
	 *  						BaseAudit/createTimestamp - Create Timestamp 
	 *  						BaseAudit/lastUpdateUser - Last Update User
	 *   						BaseAudit/lastUpdateTimestamp - Last Update Timestamp
	 *  		Each value in the list is a String[] in which 1st element is the node Path and 2nd element is the name of the node
	 */
    public LinkedHashMap<String, List<String[]>>parseCommonType(File file){
    	LinkedHashMap<String, List<String[]>>resultMap = new LinkedHashMap<>();
    	
    	// storing list of current nodes in a path and later on will be added to the map
    	// using LinkedList to removeLast() faster
    	LinkedList<String>pathList = new LinkedList<>();
    	
    	try{
	    	if(file!=null && FileUtil.isFileValid(file, FILE_EXTENSION)) {	
	    		
	    		Element rootElement=NodeUtil.getDocumentRootNode(file);
	           
	            NodeList complexTypeList = rootElement.getElementsByTagName(DOM._Tag._ComplexType);
	            // find all <xs:complexType>, for each complexType node, extract the name of it and add to the pathList as a root of current path
	            for(int i = 0; i < complexTypeList.getLength(); i++){
	
	            	Node complexNode = complexTypeList.item(i);
	                
	            	String attributeName = ((Element)complexNode).getAttribute(DOM._Attribute._Name);
	                
	            	// if this complexNode is valid, create new path and add first node to the path
	                if(NodeUtil.isNodeValid(complexNode, attributeName)){
	                
	                	pathList = new LinkedList<>();
	                    
	                	pathList.add(attributeName);
	                    
	                	backtracking(complexNode,pathList,resultMap);
	                }
	            }
	    	}
    	}
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        return resultMap;
    }

	/**
	 * Backtracking all the nodes from root (complexType) to all the nested children (xs:element)
	 * For each found child, add it to the current path list
	 * If there're no more children, convert the current path list to a string of node path, add the node path and its final name to the output map.
	 * We need final name to match with the specific roles in the interface
	 *
	 * @author Trang Nguyen
	 *
	 * @param 
	 * 			Node crNode : current node that potentially has children
	 * 			LinkedList<String>currentPathList: a list that contains nodes in a current path
	 * 			Map<String, List<String[]>>output: a map of output
	 * 
	 */
    public void backtracking(Node crNode, LinkedList<String>pathList, Map<String, List<String[]>>output){
    	// base case of the backtracking
        if(crNode == null) return;
        
        // if this doesnt have any more children, add path list to the result
        if(!NodeUtil.containsChildren(crNode, DOM._Tag._Child)){
        	// Find node that contains the name of the child
            Node labelNode = NodeUtil.findNodeRecursively((Element) crNode, DOM._Tag._Label);
            
            if(labelNode!=null){
            	String labelName = labelNode.getTextContent();
            	
            	// convert the current path list to a string of node path
                String path = StringUtil.toStringPath(pathList);                
                
                String key = StringUtil.extractKeyFromPath(path);
                
                // get the current list from key. For example: BaseAudit that has a list of nodes /BaseAudit/createUsers, /BaseAudit/createUsers, /BaseAudit/createTimestamp
                List<String[]> listContainsKey = output.getOrDefault(key, new ArrayList<>());
                
                String[]nodePathWithName = {path, labelName};
                
                listContainsKey.add(nodePathWithName);
                
                output.put(key, listContainsKey);
            }
        }
        else{           
            NodeList elementNodeList = NodeUtil.getChildren(crNode);
            
            for(int i = 0; i < elementNodeList.getLength(); i++){
                Node elementChild = elementNodeList.item(i);
            
                if(!NodeUtil.containsChildren(elementChild, DOM._Tag._Child)){
                    
                	pathList.add(((Element)elementChild).getAttribute(DOM._Attribute._Name));
                
                    backtracking(elementChild, pathList, output);
                    
                    // remove last
                    pathList.removeLast();
                }
            }
        }
    }


}
