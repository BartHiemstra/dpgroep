import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Docent {
	
	public static void main(String argv[]) {
		try {
			Document doc = getXMLDoc("res/docent.xml"); //CHANGE TO YOUR OWN DIRECTORY //Requires method to find it dynamically.
			NodeList nList = doc.getElementsByTagName("docent");
			System.out.println("TABLE NAME: docenten \n");

			for (int item = 0; item < nList.getLength(); item++) {
				Node nNode = nList.item(item);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("docent ID: " + eElement.getAttribute("id"));
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

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

}
