import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Klas {
	
	public static void main(String argv[]) {
		try {
			Document doc = Utils.getXMLDoc("res/klas.xml"); //CHANGE TO YOUR OWN DIRECTORY //Requires method to find it dynamically.
			NodeList nList = doc.getElementsByTagName("klas");
			System.out.println("TABLE NAME: klassen \n");

			for (int item = 0; item < nList.getLength(); item++) {
				Node nNode = nList.item(item);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Klas ID: " + eElement.getAttribute("id"));
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
