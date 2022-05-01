import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class EPLParser {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        File xsdFile  =new File("D:\\Projects\\parsing-xml\\src\\EPL.xsd");
        new EPLParser(xsdFile);

    }

    public EPLParser(File file) throws ParserConfigurationException, IOException, SAXException {
        Map<String, String>output = new HashMap<>();
        LinkedList<String>pathList = new LinkedList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        Element rootElement=doc.getDocumentElement();

        Node sequenceNode = getElementNode(((Element)rootElement), "xs:sequence");
        backtracking(rootElement, pathList,output);

        for(Map.Entry<String, String>e : output.entrySet()){
            System.out.println(e.getKey());
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

    public void backtracking(Node crNode, LinkedList<String>currentPathList, Map<String, String>output){
        if(crNode == null) return;
        // check if this node contains any xs:element
        NodeList elementNodeList = ((Element)crNode).getElementsByTagName("xs:element");
        // if this doesnt have any child xs:element node, check if this node contains any osd:label and add to the output
        if(elementNodeList.getLength() == 0){
            Node labelNode = getElementNode((Element) crNode, "osd:label");
            if(labelNode!=null){
                String path = toStringPath(currentPathList);
                String labelName = labelNode.getTextContent();
                output.put(path, labelName);

            }
        }
        else{
            if(getElementNode((Element)crNode, "osd:label")!=null && currentPathList.size() > 0){
                Node labelNode = getElementNode((Element) crNode, "osd:label");
                String path = toStringPath(currentPathList);
                String labelName = labelNode.getTextContent();
                output.put(path, labelName);
            }
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




}
