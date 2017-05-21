import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Main {

	public static void main(String argv[]) {
		try {
			Document doc = Utils.getXMLDoc("res/vak.xml"); //CHANGE TO YOUR OWN DIRECTORY //Requires method to find it dynamically.
			NodeList nList = doc.getElementsByTagName("vak");

			for (int item = 0; item < nList.getLength(); item++) {
				Node nNode = nList.item(item);
				System.out.println("TABLE NAME: " + nNode.getNodeName() + "\n");
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Vak ID: " + eElement.getAttribute("id"));
					System.out.println("Vaknaam: " + eElement.getElementsByTagName("naam").item(0).getTextContent());
					System.out.println("Studiepunten: " + eElement.getElementsByTagName("studiepunten").item(0).getTextContent());
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}