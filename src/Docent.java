import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Docent {

	public static void main(String argv[]) {
		try {
			Document doc = Utils.getXMLDoc("res/docent.xml"); 
			NodeList nList = doc.getElementsByTagName("docent");
			System.out.println("TABLE NAME: docenten \n");

			for (int item = 0; item < nList.getLength(); item++) {
				Node nNode = nList.item(item);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("docent ID: " + eElement.getAttribute("id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getdocentNaam(int docentID) {
		try {
			Document doc = Utils.getXMLDoc("res/docent.xml");
			NodeList nList = doc.getElementsByTagName("docent");

			for (int item = 0; item < nList.getLength(); item++) {
				Node nNode = nList.item(item);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (Integer.parseInt(eElement.getAttribute("id")) == docentID) {
						return (eElement.getElementsByTagName("naam").item(0).getTextContent());
					}
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return ("Geen docent gevonden!");
	}
}
