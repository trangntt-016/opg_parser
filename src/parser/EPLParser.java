package parser;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import dom.DOM;
import utils.NodeUtil;
import utils.StringUtil;

public class EPLParser {
    private static final Logger logger = Logger.getLogger(EPLParser.class.getName());
    
    private static int level = 0;

    public LinkedHashMap<String, String> parseEPL(File file, Map<String, List<String[]>>commonTypeDict){
    	
    	LinkedHashMap<String, String>output = new LinkedHashMap<>();
        
    	LinkedList<String>pathList = new LinkedList<>();
        
    	try{
            Element rootElement=NodeUtil.getDocumentRootNode(file);

            pathList.add("root");
        
            backtracking(rootElement, pathList,output, commonTypeDict, ++level);
        }
        catch(ParserConfigurationException | IOException | SAXException e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        return output;
    }


    public void backtracking(Node crNode, LinkedList<String>pathList, Map<String, String>output, Map<String, List<String[]>>commonTypeMap, int level){      
        crNode = NodeUtil.moveToClosestSequenceNode(crNode);
    	
        if(crNode == null) {  		
    		return;
    	};
  	
    	NodeList elementNodeList = ((Element)crNode).getChildNodes();
        
    	for(int i = 0; i < elementNodeList.getLength(); i++){
        
    		Node elementChild = elementNodeList.item(i);
            
            if (elementChild instanceof Element == false)
                continue;
            
            String type = ((Element)elementChild).getAttribute(DOM._Attribute._Type);
              
            // if type is a special one (e.g BasicAudit, EmployeeAudit, etc.), concatenate the current node with the special one
            if(type!=""&& !DOM._BasicType.getAllBasicTypes().contains(type)){
            	
            	String label = NodeUtil.extractLabelFromXSElement(elementChild);
            
            	String path = NodeUtil.extractNameFromNode(elementChild);
            	
            	pathList.add(path);            	
            	
            	path = StringUtil.toStringPath(pathList);
            	
            	label = StringUtil.toLabelName(label, level);
            	
            	output.put(path, label);
            	
            	pathList.removeLast();
            	
            	// get the children nodes (createUser, createUserTimestamp, etc.) from CommonTypesMap because EPL.xsl doesnt have those info
                List<String[]> pathAndNameList = commonTypeMap.get(type); 
                
                for(int j = 0; j < pathAndNameList.size(); j++){
                	// 1st element is the path in CommonTypes (e.g BaseAudit/createUser)
                    pathList.add(pathAndNameList.get(j)[0].substring(1));
                
                    path = StringUtil.toStringPath(pathList);
                    
                    // 2nd element is the name of path, (root/ProjectContingencyCCF/EmployeeAudit/createUser : Create User)
                    label = StringUtil.toLabelName(pathAndNameList.get(j)[1], level + 1);
                    
                    output.put(path, label);
                    
                    pathList.removeLast();
                }

                // skip on removing the last element if this is a special type
                continue;
            }
            else {
            	String label = NodeUtil.extractLabelFromXSElement(elementChild);
            	
            	String path = NodeUtil.extractNameFromNode(elementChild);
            	
            	pathList.add(path);            	
            	
            	path = StringUtil.toStringPath(pathList);
            	
            	label = StringUtil.toLabelName(label, level);
            	              
                output.put(path, label);               
            }

            backtracking(elementChild, pathList, output,commonTypeMap,++level);  

            pathList.removeLast();                	

            level--;
        }
        
    }

}
