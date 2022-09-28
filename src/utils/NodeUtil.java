package utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dom.DOM;


public class NodeUtil {
    public static Node findNodeRecursively(Element e, String tagType){
    	if(e == null) return null;
        // xs:element or xs:complexType
        if(e.getNodeName().equals(tagType)){
            return (Node)e;
        }
        NodeList children=e.getChildNodes();
        Node found = null;

        for (int i=0; i<children.getLength(); i++){
            Node node=children.item(i);
            // check if it's element, otherwise, cannot cast
            if (node.getNodeType() == Node.ELEMENT_NODE){
                found = findNodeRecursively((Element) children.item(i), tagType);
                if(found!=null){
                    return found;
                }
            }

        }
        return found;
    }

    public static boolean isNodeValid(Node complexNode, String attributeName) {
    	return complexNode.getNodeType()==Node.ELEMENT_NODE && !attributeName.equals("");
    	
    }
    
    public static boolean containsChildren(Node crNode, String childTagName) {  
    	
    	return crNode !=null&&getChildren(crNode).getLength() > 0;
    	
    }
    
    public static Element getDocumentRootNode(File file) throws SAXException, IOException, ParserConfigurationException {
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        return doc.getDocumentElement();
    }
    
    public static NodeList getChildren(Node parentNode) {
    	// For CommonType.xsd, the structure is like <complexType><sequence><element></element></sequence></complexType>
    	// Each <complexType> node only contains one <sequence>
    	// Therefore, to get all child nodes in CommonType.xsd, have to go to the parent nodes which is <sequence>
    	String nodeName = parentNode.getNodeName();
    	if(nodeName.equals(DOM._Tag._Root) ||nodeName.equals(DOM._Tag._ComplexType)){
            parentNode = NodeUtil.findNodeRecursively((Element)parentNode, DOM._Tag._Sequence);
            
        }
    	return ((Element)parentNode).getElementsByTagName(DOM._Tag._Child);
    }
    
    public static Node moveToClosestSequenceNode(Node crNode) {
    	String nodeName = crNode.getNodeName();
    	if(!nodeName.equals(DOM._Tag._Sequence)){
            crNode = NodeUtil.findNodeRecursively((Element)crNode, DOM._Tag._Sequence);
            
        }
    	return crNode;
    	
    }

    public static String extractLabelFromXSElement(Node xsElement) {
    	Node labelNode = NodeUtil.findNodeRecursively((Element)xsElement, DOM._Tag._Label);
        
    	return labelNode!=null? labelNode.getTextContent():((Element)xsElement).getAttribute("name");
    }
    
    public static String extractNameFromNode(Node node) {
    	return ((Element)node).getAttribute(DOM._Attribute._Name);
    	
    }
}
