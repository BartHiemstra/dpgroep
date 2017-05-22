import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utils {
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
	
	public static boolean exists(String docentID) {
		try {
			Document doc = Utils.getXMLDoc("res/docent.xml");
			NodeList nList = doc.getElementsByTagName("docent");

			for (int item = 0; item < nList.getLength(); item++) {
				Node node = nList.item(item);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println(element.getAttribute("id"));
					System.out.println("check 1 2 3");
					if(element.getAttribute("id").equals(docentID)){
						return true;}
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}