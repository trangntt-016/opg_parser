package parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.NodeUtil;
import utils.StringUtil;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommonTypeParser {
    private static final Logger logger = Logger.getLogger(CommonTypeParser.class.getName());

    public CommonTypeParser(){};

    public Map<String, List<String[]>>parseCommonType(File file){
        Map<String, List<String[]>>output = new HashMap<>();
        LinkedList<String>pathList = new LinkedList<>();
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            Element rootElement=doc.getDocumentElement();

            // find all xs:complexType Nodes
            NodeList complexTypeList = rootElement.getElementsByTagName("xs:complexType");
            for(int i = 0; i < complexTypeList.getLength(); i++){
                Node complexChild = complexTypeList.item(i);
                // check if this node is not null, xsd is pretty weird, sometimes it returns null node
                if(complexChild.getNodeType()==Node.ELEMENT_NODE && !((Element)complexChild).getAttribute("name").equals("")){
                    pathList = new LinkedList<>();
                    pathList.add(((Element)complexChild).getAttribute("name"));
                    backtracking(complexChild,pathList,output);
                }
            }

        }
        catch(ParserConfigurationException | IOException | SAXException e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        return output;
    };


    public void backtracking(Node crNode, LinkedList<String>currentPathList, Map<String, List<String[]>>output){
        if(crNode == null) return;
        // check if this node contains any xs:element
        NodeList elementNodeList = ((Element)crNode).getElementsByTagName("xs:element");
        // if this doesnt have any child xs:element node, check if this node contains any osd:label and add to the output
        if(elementNodeList.getLength() == 0){
            Node labelNode = NodeUtil.getElementNode((Element) crNode, "osd:label");
            if(labelNode!=null){
                String path = StringUtil.toStringPath(currentPathList);
                String labelName = labelNode.getTextContent();
                String key = StringUtil.extractKeyFromPath(path);
                List<String[]> listContainsKey = output.getOrDefault(key, new ArrayList<>());
                String[]nodePathName = {path, labelName};
                listContainsKey.add(nodePathName);
                output.put(key, listContainsKey);
            }
        }
        else{
            // go to the xs:sequence node which contains all other element node. The structure is like <completeType><sequence><element></element></sequence></completeType>
            // Each completeType node only contains one sequence
            if(crNode.getNodeName().equals("xs:complexType")){
                Node sequenceNode = NodeUtil.getElementNode((Element)crNode, "xs:sequence");
                elementNodeList = ((Element)sequenceNode).getElementsByTagName("xs:element");
            }
            for(int i = 0; i < elementNodeList.getLength(); i++){
                Node elementChild = elementNodeList.item(i);
                // check if this node is not null, xsd is pretty weird, sometimes it returns null node
                if(elementChild.getNodeType() == Node.ELEMENT_NODE && !((Element)elementChild).getAttribute("name").equals("")){
                    currentPathList.add(((Element)elementChild).getAttribute("name"));
                    backtracking(elementChild, currentPathList, output);
                    // remove last
                    currentPathList.removeLast();
                }

            }
        }
    }


}
