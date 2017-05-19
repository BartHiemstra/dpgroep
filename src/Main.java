import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Main {

	public static void main(String argv[]) {
		try {
			Document doc = getXMLDoc("C:/Users/bart-/git/dpgroep/res/staff.xml"); //CHANGE TO YOUR OWN DIRECTORY //Requires method to find it dynamically.
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