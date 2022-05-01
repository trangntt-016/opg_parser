package granting_bulk_permissions.xsd.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XSDParser {
	public static void main(String args[]) {
		try { 
            // parse the document
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("C:\\Users\\NGUYETHI\\OneDrive - Ontario Power Generation\\FreeStuffs\\granting_bulk_permissions\\src\\granting_bulk_permissions\\xsd\\parser\\CommonTypes.xsd")); 
            // this code works for now
//            NodeList list = doc.getElementsByTagName("osd:label"); 
//
//            //loop to print data
//            for(int i = 0 ; i < list.getLength(); i++)
//            {
//                Element first = (Element)list.item(i);
//                Node node = list.item(i);
//                String nodeValue = first.getFirstChild().getNodeValue();
//                if(nodeValue.equals("Create User")) {
//                	System.out.println("hi");
//                	
//                }
//
//            }
            NodeList list = doc.getElementsByTagName("xs:element"); 
            for(int i = 0 ; i < list.getLength(); i++)
              {
                  Element first = (Element)list.item(i);
                  //
                  Node node = list.item(i);
                  if(first.getAttribute("name").equals("createUser")) {
                	  // check if its child elements contains osd:label
                	  NodeList childList = first.getChildNodes();
                	  for(int j = 0; j < childList.getLength(); i++) {
                		  Node child = (Node) childList.item(j);
                		  String nodeValue = child.getFirstChild().getNodeValue();
                		  if(nodeValue.equals("Create User")) {
                          	System.out.println("hi");
                          	
                          }
                	  }
                  }
                  String nodeValue = first.getFirstChild().getNodeValue();
                  if(nodeValue.equals("Create User")) {
                  	System.out.println("hi");
                  	
                  }
  
              }
        } 
        catch (ParserConfigurationException e) 
        {
            e.printStackTrace();
        }
        catch (SAXException e) 
        { 
            e.printStackTrace();
        }
        catch (IOException ed) 
        {
            ed.printStackTrace();
        }
	}
}
