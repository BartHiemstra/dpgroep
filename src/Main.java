import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.io.IOException;

public class Main {
	public Main(){}

	/**public static void main(String argv[]) {
		try {
			Document doc = getXMLDoc("res/staff.xml"); //CHANGE TO YOUR OWN DIRECTORY //Requires method to find it dynamically.
			NodeList nList = doc.getElementsByTagName("vak");

			for (int item = 0; item < nList.getLength(); item++) {
				Node nNode = nList.item(item);
				System.out.println("TABLE NAME: " + nNode.getNodeName() + "\n");
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Vak ID: " + eElement.getAttribute("id"));
					System.out.println("Studiepunten: " + eElement.getElementsByTagName("studiepunten").item(0).getTextContent());
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public static Document getXMLDoc(String fileName) {
		try {
			File XMLfile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLfile);
			doc.getDocumentElement().normalize();
			return doc;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//nieuwe functie
	public static void editXMLDoc(String fileName) {
		try {
			String filepath = fileName;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the root element
			Node xml = doc.getFirstChild();

			// Get the main element , it may not working if tag has spaces, or
			// whatever weird characters in front...it's better to use
			// getElementsByTagName() to get it directly.
			// Node staff = company.getFirstChild();

			// Get the main element by tag name directly
			NodeList list = doc.getElementsByTagName("vak").item(0).getChildNodes();     
			//Element newLes = doc.createElement("new_les");
			//xml.appendChild(newLes);
			for(int x = 0; x < list.getLength(); x += 1) {
				Node mainNode = doc.getElementsByTagName("vak").item(x);
				
				// update main attribute
				NamedNodeMap attr = mainNode.getAttributes();
				Node nodeAttr = attr.getNamedItem("id");
				if (nodeAttr.getTextContent().equals("V1OODC")) {
					// append a new node
					Element docent = doc.createElement("docent");
					docent.appendChild(doc.createTextNode("Ellen Leutbecher"));
					mainNode.appendChild(docent);
				}
			}
			
			//add
			//if ("...".equals(node.getNodeName())) {
			//node.setTextContent("...");
		    //}

	        //remove
			//if ("...".equals(node.getNodeName())) {
				//staff.removeChild(node);
			//}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Done");

		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
	}
}