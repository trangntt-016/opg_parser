import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class CommonTypeParser {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File xsdFile  =new File("D:\\Projects\\parsing-xml\\src\\CommonTypes - Copy.xsd");
        new CommonTypeParser(xsdFile);
    }

    public CommonTypeParser(File file) throws ParserConfigurationException, IOException, SAXException {
        Map<String, List<String[]>>output = new HashMap<>();
        LinkedList<String>pathList = new LinkedList<>();
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

        for(Map.Entry<String, List<String[]>>e : output.entrySet()){
            for(String[] l : e.getValue()){
                System.out.println(e.getKey() + " - " + l[0] + ", " + l[1]);
            }
        }
    }


    public Node getElementNode(Element e, String tagType){
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
                found = getElementNode((Element) children.item(i), tagType);
                if(found!=null){
                    return found;
                }
            }

        }
        return found;
    }

    public void backtracking(Node crNode, LinkedList<String>currentPathList, Map<String, List<String[]>>output){
        if(crNode == null) return;
        // check if this node contains any xs:element
        NodeList elementNodeList = ((Element)crNode).getElementsByTagName("xs:element");
        // if this doesnt have any child xs:element node, check if this node contains any osd:label and add to the output
        if(elementNodeList.getLength() == 0){
            Node labelNode = getElementNode((Element) crNode, "osd:label");
            if(labelNode!=null){
                String path = toStringPath(currentPathList);
                String labelName = labelNode.getTextContent();
                String key = extractKeyFromPath(path);
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
                Node sequenceNode = getElementNode((Element)crNode, "xs:sequence");
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

    public String toStringPath(LinkedList<String>currentPathList){
        StringBuilder sb = new StringBuilder();
        for(String p : currentPathList){
            sb.append(p).append("/");
        }
        sb.deleteCharAt(sb.lastIndexOf("/"));
        return sb.toString();
    }

    public String extractKeyFromPath(String path){
        StringBuilder sb = new StringBuilder(path);
        return sb.substring(0, sb.indexOf("/")).toString();
    }
}
