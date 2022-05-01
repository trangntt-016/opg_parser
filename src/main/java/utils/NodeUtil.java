package utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeUtil {
    public static Node getElementNode(Element e, String tagType){
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
}
